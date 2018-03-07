/*
 * @author wangzhongqiu
 * (0)Java无法直接访问底层操作系统，而是通过本地（native）方法来访问。
 *    不过尽管如此，JVM还是开了一个后门，JDK中有一个类Unsafe，它提供了硬件级别的原子操作。
 *    当前的处理器基本都支持CAS，只不过不同的厂家的实现不一样罢了。
 *    unsafe.compareAndSwapInt(this, stateOffset, expect, update)。
 *      【说明】CAS原语可以用来实现无锁的数据结构。是CPU指令级的操作，只有一步原子操作
 *      【步骤】有一些状态，创建它的副本，修改它，执行CAS，如果失败，重复尝试
 *      【存在问题】如ABA问题、指令重排序等。
 * (0)LockSupport
 *     park():当前线程进入Waiting状态,等待许可
 *     unpark(thread):给thread线程一个许可
 * (1)可重入独占锁。ReentrantLock的锁资源以state状态描述，利用CAS则实现对锁资源的抢占，并通过一个FIFO队列阻塞所有竞争线程，
 * 在后续则逐个唤醒等待中的竞争线程。ReentrantLock继承AQS完全从代码层面实现了java的同步机制，
 * 相对于synchronized，更容易实现对各类锁的扩展。同时，AbstractQueuedSynchronizer中的Condition配合ReentrantLock使用，
 * 实现了wait/notify的功能。
 * ReentrantLock implements Lock。成员变量：Sync sync;
 *  【Sync extends AbstractQueuedSynchronizer】实现了CLH锁算法，CLH锁是一个自旋锁，能确保无饥饿性，提供先来先服务的公平性。
 *  【AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer】
 *    成员变量：long stateOffset;int state;Node tail;Node head;内部类Node(waitStatus,prev,next,thread,nextWaiter锁类型);
 *  【AbstractOwnableSynchronizer】成员变量：Thread exclusiveOwnerThread;
 *  【NonfairSync extends Sync】非公平锁：当锁处于无线程占有的状态，此时其他线程和在队列中等待的线程都可以抢占该锁。
 *  【FairSync extends Sync】公平锁：当锁处于无线程占有的状态，在其他线程抢占该锁的时候，都需要先进入队列中等待。
 *    【非公平锁 lock方法】
 *       所有线程先执行一遍cas竞争锁，if compareAndSetState(0, 1);setExclusiveOwnerThread(Thread.currentThread());
 *       CAS失败，执行带有阻塞队列FIFO的acquire(1)方法
 *        cas竞争锁：判断state等于0，cas竞争锁，setExclusiveOwnerThread；不为零且当前线程是持有锁的线程，state+1;
 *        竞争锁失败，放入队列中继续尝试获得锁acquireQueued(addWaiter(Node.EXCLUSIVE), arg));
 *        自旋入队列addWaiter,tail不为空，node放入队尾;tail为空，自旋for循环直到初始化并且放入队尾成功。compareAndSetHead,compareAndSetTail
 *        acquireQueued。for无限循环。自旋在阻塞队列中竞争锁
 *              判断node节点的pre节点是否为头结点,是则node去竞争锁,获取到锁,把node置成头结点,return interrupted(初始为false)
 *              没有获取到锁或者不是则执行方法【shouldParkAfterFailedAcquire】：判断node前节点的waitStatus是否为-1(等待锁状态)，
 *              是则返回true。调用LockSupport.park(this)阻塞等待许可，获取许可，更新interrupted的值为Thread.interrupted(),重新下一次for循环
 *              不是且waitStatus>0,则跳过状态为1(取消等待锁)的pre节点，返回false，重新下一次for循环
 *        如果acquireQueued方法返回true，执行 Thread.currentThread().interrupt();
 *    【公平锁 lock方法】
 *        【lock()】->【acquire(1)】->【if (!tryAcquire(arg) &&acquireQueued(addWaiter(Node.EXCLUSIVE), arg))】->
 *        【if (c == 0) {
                if (!hasQueuedPredecessors() &&
                    compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }】
 *    【unlock方法】
 *      tryRelease(1)->state减小，如果减小到了0，则setExclusiveOwnerThread(null);
 *      unparkSuccessor(h)，如果头结点的next节点不为空，LockSupport.unpark(s.thread)
 *      如果next节点为空或者waitStatus > 0，从tail往前遍历到离head最近的waitStatus <= 0的节点，LockSupport.unpark(s.thread)
 * (2)读写锁 ReentrantReadWriteLock【参考文章：http://www.cnblogs.com/wangzhongqiu/p/8422925.html】
 *    【readLock().lock()】->【acquireShared(int arg)】->【if (tryAcquireShared(arg) < 0)】->【doAcquireShared(arg)】
 *       【tryAcquireShared没有阻塞方法，没有自旋】【doAcquireShared竞争锁失败，park，CLH锁，FIFO队列】
 *    【readLock().unlock()】->【releaseShared(int arg)】->【if (tryReleaseShared(arg))】->【doReleaseShared()】
 *       【tryReleaseShared 自旋释放锁直到成功，return nextc == 0;】【doReleaseShared 自旋直到把队列中第一个阻塞的线程唤醒】
 *    【writeLock().lock()】->
 *       【acquire(1)】
 *          if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
               selfInterrupt();
 *       【tryAcquire(1) 已经有读锁,失败;已经有写锁了,累加state;读锁和写锁都没有,CAS竞争锁（公平锁还要在竞争锁之前判断是否有线程已经在wait）】
 *    【writeLock().unlock()】->【release(1)】->【tryRelease(1)】->【unparkSuccessor(h)】
 */
package zhongqiu.common.jdk5.concurrent.locks;