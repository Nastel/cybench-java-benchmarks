package com.gocypher.cybench.jmh.jvm.utils;

import com.sun.management.OperatingSystemMXBean;
import org.openjdk.jmh.annotations.*;

import java.lang.management.ManagementFactory;

public class CyBenchCounters {

    @AuxCounters(AuxCounters.Type.EVENTS)
    @State(Scope.Thread)
    public static class ProfileCounters {
        public double performanceProcessCpuLoad = 0.0 ;
        public long performanceProcessHeapMemoryUsed = 0 ;
        public long performanceProcessNonHeapMemoryUsed = 0 ;
        public double performanceSystemCpuLoad = 0.0 ;
    }

    public static void registerProfileInformation (ProfileCounters counters){
        counters.performanceProcessHeapMemoryUsed = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
        counters.performanceProcessNonHeapMemoryUsed = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage().getUsed();
        OperatingSystemMXBean operatingSystemMXBean =
                (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        counters.performanceProcessCpuLoad = operatingSystemMXBean.getProcessCpuLoad() ;
        counters.performanceSystemCpuLoad = operatingSystemMXBean.getSystemCpuLoad() ;
    }
}
