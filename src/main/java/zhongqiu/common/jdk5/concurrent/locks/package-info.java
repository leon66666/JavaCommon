/*
 * @author wangzhongqiu
 * (0)unsafe.compareAndSwapInt(this, stateOffset, expect, update)。
 *      【说明】CAS原语可以用来实现无锁的数据结构。是CPU指令级的操作，只有一步原子操作
 *      【步骤】有一些状态，创建它的副本，修改它，执行CAS，如果失败，重复尝试
 *      【存在问题】如ABA问题、指令重排序等。
 * (0)LockSupport
 *     park():当前线程进入Waiting状态,等待许可
 *     unpark(thread):给thread线程一个许可
 * (1)可重入锁。ReentrantLock的锁资源以state状态描述，利用CAS则实现对锁资源的抢占，并通过一个FIFO队列阻塞所有竞争线程，
 * 在后续则逐个唤醒等待中的竞争线程。ReentrantLock继承AQS完全从代码层面实现了java的同步机制，
 * 相对于synchronized，更容易实现对各类锁的扩展。同时，AbstractQueuedSynchronizer中的Condition配合ReentrantLock使用，
 * 实现了wait/notify的功能。
 * ReentrantLock implements Lock。成员变量：Sync sync;
 *  【Sync extends AbstractQueuedSynchronizer】成员变量：
 *  【AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer】
 *    成员变量：long stateOffset;int state;Node tail;Node head;内部类Node(waitStatus,prev,next,thread);
 *  【AbstractOwnableSynchronizer】成员变量：Thread exclusiveOwnerThread;
 *  【NonfairSync extends Sync】非公平锁：当锁处于无线程占有的状态，此时其他线程和在队列中等待的线程都可以抢占该锁。
 *  【FairSync extends Sync】公平锁：当锁处于无线程占有的状态，在其他线程抢占该锁的时候，都需要先进入队列中等待。
 *  【NonfairSync】lock();
 *  【lock方法】if compareAndSetState(0, 1);unsafe.compareAndSwapInt(this, stateOffset, expect, update);
 *    setExclusiveOwnerThread(Thread.currentThread());else nonfairTryAcquire(1);
 *    判断state等于0，cas，setExclusiveOwnerThread；不为零且当前线程是持有锁的线程，state+1;
 *    nonfairTryAcquire加锁失败，放入队列中继续尝试获得锁acquireQueued(addWaiter(Node.EXCLUSIVE), arg));
 *    入队列addWaiter,tail不为空，node放入队尾;tail为空，自旋for循环直到初始化并且放入队尾成功。compareAndSetHead,compareAndSetTail
 *    acquireQueued。自旋for循环直到node节点获取到锁。for无限循环，node获取到锁，return
 *                   判断node的pre节点，为头结点参与锁的竞争，竞争成功，把node置成头结点;
 *                   否则判断node前节点的waitStatus是否为-1等待锁状态，是则LockSupport.park(this)，否则跳过状态为1取消等待锁的pre节点
 *
 */
package zhongqiu.common.jdk5.concurrent.locks;