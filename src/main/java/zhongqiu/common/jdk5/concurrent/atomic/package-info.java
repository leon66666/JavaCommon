/*
 * @author wangzhongqiu
 * 在非激烈竞争的情况下，开销更小，速度更快
 * 【AtomicInteger extends Number implements java.io.Serializable】
        public final int getAndIncrement() {
            for (;;) {
                int current = get();
                int next = current + 1;
                if (compareAndSet(current, next))
                    return current;
            }
        }
 */
package zhongqiu.common.jdk5.concurrent.atomic;