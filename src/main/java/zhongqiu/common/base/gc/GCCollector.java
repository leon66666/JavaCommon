package zhongqiu.common.base.gc;


/*
(a)Serial收集器
串行收集器是最古老，最稳定以及效率高的收集器，可能会产生较长的停顿，只使用一个线程去回收。
新生代、老年代使用串行回收；新生代复制算法、老年代标记-压缩；垃圾收集的过程中会Stop The World（服务暂停）
参数控制：-XX:+UseSerialGC  串行收集器
（b）吐量优先的并行收集器
-XX:+UseParallelGC：选择垃圾收集器为并行收集器。此配置仅对年轻代有效。即上述配置下，年轻代使用并发收集，而年老代仍旧使用串行收集。
-XX:ParallelGCThreads=20：配置并行收集器的线程数，即：同时多少个线程一起进行垃圾回收。此值最好配置与处理器数目相等。
-XX:+UseParallelOldGC：配置年老代垃圾收集方式为并行收集。JDK6.0支持对年老代并行收集。Parallel Old是Serial Old的并行版本
-XX:MaxGCPauseMillis=100:设置每次年轻代垃圾回收的最长时间，如果无法满足此时间，JVM会自动调整年轻代大小，以满足此值。
-XX:+UseAdaptiveSizePolicy：设置此选项后，并行收集器会自动选择年轻代区大小和相应的Survivor区比例，
       以达到目标系统规定的最低相应时间或者收集频率等，此值建议使用并行收集器时，一直打开。
（c）响应时间优先的并发收集器
-XX:+UseParNewGC:ParNew是Serial的并行版本。
     设置年轻代为并行收集。可与CMS收集同时使用。JDK5.0以上，JVM会根据系统配置自行设置，所以无需再设置此值。
（d）CMS收集器。优点:并发收集、低停顿。缺点：产生大量空间碎片、并发阶段会降低吞吐量
-XX:+UseConcMarkSweepGC  设置年老代使用CMS收集器
-XX:+UseCMSCompactAtFullCollection  FullGC后，进行一次碎片整理；整理过程是独占的，会引起停顿时间变长
-XX:+CMSFullGCsBeforeCompaction  设置进行几次Full GC后，进行一次碎片整理
-XX:ParallelCMSThreads  设定CMS的线程数量（一般情况约等于可用CPU数量）
（e）G1收集器
G1收集器采用标记整理算法。 可预测停顿，能让使用者明确指定在一个长度为N毫秒的时间片段内，消耗在垃圾收集上的时间不得超过N毫秒
-XX:+UseG1GC
*/
public class GCCollector {

}
