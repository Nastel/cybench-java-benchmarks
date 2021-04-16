package io.cybench.jmh.jvm.utils;

import com.sun.management.OperatingSystemMXBean;
import org.openjdk.jmh.annotations.AuxCounters;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class CyBenchCounters {
    private static Logger LOG = LoggerFactory.getLogger(CyBenchCounters.class);

    @AuxCounters(AuxCounters.Type.EVENTS)
    @State(Scope.Thread)
    public static class ProfileCounters {
        public double performanceProcessCpuLoad = 0.0 ;
        public long performanceProcessHeapMemoryUsed = 0 ;
        public long performanceProcessNonHeapMemoryUsed = 0 ;
        public double performanceSystemCpuLoad = 0.0 ;
    }

    public static void registerProfileInformation (ProfileCounters counters){
        // All the properties including the memory and time except the cpu load and commited virtualized memory are incremental and affected by warm-ups and iterations.
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        counters.performanceProcessHeapMemoryUsed = memoryMXBean.getHeapMemoryUsage().getUsed();
        counters.performanceProcessNonHeapMemoryUsed = memoryMXBean.getNonHeapMemoryUsage().getUsed();
//        LOG.info("memoryMXBean.getHeapMemoryUsage().getUsed(): {}",memoryMXBean.getHeapMemoryUsage().getUsed() );
//        LOG.info("memoryMXBean.getNonHeapMemoryUsage().getUsed(): {}",memoryMXBean.getNonHeapMemoryUsage().getUsed() );

        OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        counters.performanceProcessCpuLoad = operatingSystemMXBean.getProcessCpuLoad() ;
        counters.performanceSystemCpuLoad = operatingSystemMXBean.getSystemCpuLoad() ;
//        LOG.info("operatingSystemMXBean.getProcessCpuTime(): {}",operatingSystemMXBean.getProcessCpuTime() ); // The process time includes the threads run from JMV and threads run from application total as on the load percentage.
//        LOG.info("operatingSystemMXBean.getCommittedVirtualMemorySize(): {}",operatingSystemMXBean.getCommittedVirtualMemorySize() ); // The amount of virtual memory guaranteed to be available for the JVM process.

//        for(java.lang.management.GarbageCollectorMXBean garbageCollector : ManagementFactory.getGarbageCollectorMXBeans()) {
//            GarbageCollectorMXBean garbageCollectorMXBean = (GarbageCollectorMXBean) garbageCollector;
//            garbageCollectorMXBean.getLastGcInfo(); // Return's in depth parameter for the latest gc call information, not very useful since applies only for the last gc call.
//            LOG.info("garbageCollectorMXBean.getName(): {}", garbageCollectorMXBean.getName()); // The GC name  ( PS Scavenge or PS MarkSweep -  the difference is that PS Scavange collects information only for the young (eden, survivor) generation while PS MarkSweep works with the old some of which will be passed from PS Scavange )
//            LOG.info("garbageCollectorMXBean.getCollectionCount(): {}", garbageCollectorMXBean.getCollectionCount()); // The collection count for specific garbage collector
//            LOG.info("garbageCollectorMXBean.getCollectionTime(): {}", garbageCollectorMXBean.getCollectionTime()); // The collection time for the specific garbage collector
//        }

//        ThreadMXBean threadMXBean = (ThreadMXBean) ManagementFactory.getThreadMXBean();
//        LOG.info("threadMXBean.isThreadCpuTimeEnabled(): {}",threadMXBean.isThreadCpuTimeEnabled()); // Need this check for below method not to return unsupported operation exception on some VM's
        // Operations below can be done by thread id for multi thread runs.
//        if(threadMXBean.isThreadCpuTimeEnabled()) {
//            LOG.info("threadMXBean.getCurrentThreadCpuTime(): {}", threadMXBean.getCurrentThreadCpuTime()); // Return the time the thread is operational This time is System time + User time.
//        }
//        LOG.info("threadMXBean.getCurrentThreadUserTime(): {}",threadMXBean.getCurrentThreadUserTime()); // Return the user time of the thread operation.


        /* ALL properties that uses java lang management api */
//        ManagementFactory.getPlatformMBeanServer();
//        ManagementFactory.getRuntimeMXBean();
//        ManagementFactory.getThreadMXBean(); // The same as in sun management API
//        ManagementFactory.getOperatingSystemMXBean();
//        LOG.info("ManagementFactory.getRuntimeMXBean().getUptime(): {}", ManagementFactory.getRuntimeMXBean().getUptime());

    }
}
