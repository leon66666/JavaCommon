/*
 * @author wangzhongqiu
 * 可重入锁。ReentrantLock的锁资源以state状态描述，利用CAS则实现对锁资源的抢占，并通过一个CLH队列阻塞所有竞争线程，
 * 在后续则逐个唤醒等待中的竞争线程。ReentrantLock继承AQS完全从代码层面实现了java的同步机制，
 * 相对于synchronized，更容易实现对各类锁的扩展。同时，AbstractQueuedSynchronizer中的Condition配合ReentrantLock使用，
 * 实现了wait/notify的功能。
 * ReentrantLock ReentrantLock implements Lock。成员变量：Sync sync;
 *      【Sync extends AbstractQueuedSynchronizer】成员变量：long stateOffset;int state
 *      【AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer】成员变量：Thread exclusiveOwnerThread;
 *      【NonfairSync extends Sync】非公平锁：当锁处于无线程占有的状态，此时其他线程和在队列中等待的线程都可以抢占该锁。
 *      【FairSync extends Sync】公平锁：当锁处于无线程占有的状态，在其他线程抢占该锁的时候，都需要先进入队列中等待。
 *      【NonfairSync】lock,
 *      【lock方法】if compareAndSetState(0, 1);unsafe.compareAndSwapInt(this, stateOffset, expect, update);
 *        setExclusiveOwnerThread(Thread.currentThread());else acquire(1);tryAcquire(1);nonfairTryAcquire(1);
 *        nonfairTryAcquire加锁失败，放入队列中继续尝试获得锁acquireQueued(addWaiter(Node.EXCLUSIVE), arg));
 */
package zhongqiu.common.jdk5.concurrent.locks;