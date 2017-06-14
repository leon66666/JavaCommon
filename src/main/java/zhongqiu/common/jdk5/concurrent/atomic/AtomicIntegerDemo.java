package zhongqiu.common.jdk5.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;


//那么为什么不使用记数器自加呢，例如count++这样的，因为这种计数是线程不安全的，高并发访问时统计会有误，
//而AtomicInteger为什么能够达到多而不乱，处理高并发应付自如呢？
//这是由硬件提供原子操作指令实现的。在非激烈竞争的情况下，开销更小，速度更快。Java.util.concurrent中实现的原子操作类包括：
//AtomicBoolean、AtomicInteger、AtomicLong、AtomicReference。
public class AtomicIntegerDemo {

}

class CounterSyn {
	private volatile int count = 0;

	public synchronized void increment() {
		count++; // 若要线程安全执行执行count++，需要加锁
	}

	public int getCount() {
		return count;
	}
}

class CounterAtomic {
	private AtomicInteger count = new AtomicInteger();

	public void increment() {
		count.incrementAndGet();
	}

	// 使用AtomicInteger之后，不需要加锁，也可以实现线程安全。
	public int getCount() {
		return count.get();
	}
}
