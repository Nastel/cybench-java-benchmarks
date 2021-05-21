package io.cybench.client;

import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import org.jctools.maps.NonBlockingHashMap;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

import io.cybench.jmh.jvm.utils.CyBenchCounters;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "true")
@BenchmarkMetaData(key = "context", value = "mapping")
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libVendor", value = "-")
@BenchmarkMetaData(key = "api", value = "mapping")

public class MapValueBenchmark {

    @Param(value = { "INFO", "HALT" })
    OpLevel sev;

    private int begin = 1;

    private double end = 256;

    HashMap<OpLevel, Level> levelMap = new HashMap() {
        private static final long serialVersionUID = 1935906464773166800L;

        {
            put(OpLevel.NONE, Level.OFF);
            put(OpLevel.TRACE, Level.FINER);
            put(OpLevel.DEBUG, Level.FINE);
            put(OpLevel.INFO, Level.INFO);
            put(OpLevel.NOTICE, Level.WARNING);
            put(OpLevel.WARNING, Level.WARNING);
            put(OpLevel.ERROR, Level.SEVERE);
            put(OpLevel.FAILURE, Level.SEVERE);
            put(OpLevel.CRITICAL, Level.SEVERE);
            put(OpLevel.FATAL, Level.SEVERE);
            put(OpLevel.HALT, Level.SEVERE);
        }
    };

    TreeMap<OpLevel, Level> treeMap = new TreeMap() {
        private static final long serialVersionUID = 6509087217711069934L;

        {
            put(OpLevel.NONE, Level.OFF);
            put(OpLevel.TRACE, Level.FINER);
            put(OpLevel.DEBUG, Level.FINE);
            put(OpLevel.INFO, Level.INFO);
            put(OpLevel.NOTICE, Level.WARNING);
            put(OpLevel.WARNING, Level.WARNING);
            put(OpLevel.ERROR, Level.SEVERE);
            put(OpLevel.FAILURE, Level.SEVERE);
            put(OpLevel.CRITICAL, Level.SEVERE);
            put(OpLevel.FATAL, Level.SEVERE);
            put(OpLevel.HALT, Level.SEVERE);
        }
    };

    ConcurrentHashMap<OpLevel, Level> concurrentMap = new ConcurrentHashMap() {
        private static final long serialVersionUID = -299666737887362034L;

        {
            put(OpLevel.NONE, Level.OFF);
            put(OpLevel.TRACE, Level.FINER);
            put(OpLevel.DEBUG, Level.FINE);
            put(OpLevel.INFO, Level.INFO);
            put(OpLevel.NOTICE, Level.WARNING);
            put(OpLevel.WARNING, Level.WARNING);
            put(OpLevel.ERROR, Level.SEVERE);
            put(OpLevel.FAILURE, Level.SEVERE);
            put(OpLevel.CRITICAL, Level.SEVERE);
            put(OpLevel.FATAL, Level.SEVERE);
            put(OpLevel.HALT, Level.SEVERE);
        }
    };

    NonBlockingHashMap<OpLevel, Level> nbhm = new NonBlockingHashMap() {
        private static final long serialVersionUID = 4324784836555911191L;

        {
            put(OpLevel.NONE, Level.OFF);
            put(OpLevel.TRACE, Level.FINER);
            put(OpLevel.DEBUG, Level.FINE);
            put(OpLevel.INFO, Level.INFO);
            put(OpLevel.NOTICE, Level.WARNING);
            put(OpLevel.WARNING, Level.WARNING);
            put(OpLevel.ERROR, Level.SEVERE);
            put(OpLevel.FAILURE, Level.SEVERE);
            put(OpLevel.CRITICAL, Level.SEVERE);
            put(OpLevel.FATAL, Level.SEVERE);
            put(OpLevel.HALT, Level.SEVERE);
        }
    };

    Level[] levelMapArray = levelMap.values().toArray(new Level[0]);

    @Benchmark
    @BenchmarkTag(tag = "ffe97eb3-c51d-4514-84d3-e4a384d1ea23")
    @BenchmarkMetaData(key = "libSymbolicName", value = "enum")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/technotes/guides/language/enums.html")
    public void getLevel(Blackhole bh) {
        for (int i = begin; i < end; i++) {
            switch (sev) {
            case INFO:
                bh.consume(Level.INFO);
            case DEBUG:
                bh.consume(Level.FINE);
            case TRACE:
                bh.consume(Level.FINER);
            case NOTICE:
            case WARNING:
                bh.consume(Level.WARNING);
            case ERROR:
            case CRITICAL:
            case FAILURE:
            case FATAL:
            case HALT:
                bh.consume(Level.SEVERE);
            case NONE:
                bh.consume(Level.OFF);
            default:
                bh.consume(Level.INFO);
            }
        }
    }

    @Benchmark
    @BenchmarkTag(tag = "c06d47f0-a389-43d8-b392-9f1fabc36c6f")
    @BenchmarkMetaData(key = "libSymbolicName", value = "if_else")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/if.html")
    public void getLevelIfElse(Blackhole bh) {
        for (int i = begin; i < end; i++) {
            if (sev == OpLevel.INFO) {
                bh.consume(Level.INFO);
            } else if (sev == OpLevel.DEBUG) {
                bh.consume(Level.FINE);
            } else if (sev == OpLevel.TRACE) {
                bh.consume(Level.FINER);
            } else if (sev == OpLevel.NOTICE || sev == OpLevel.WARNING) {
                bh.consume(Level.WARNING);
            } else if (sev == OpLevel.ERROR || sev == OpLevel.CRITICAL || sev == OpLevel.FAILURE || sev == OpLevel.FATAL
                    || sev == OpLevel.HALT) {
                bh.consume(Level.SEVERE);
            } else if (sev == OpLevel.NONE) {
                bh.consume(Level.OFF);
            }
            bh.consume(Level.INFO);
        }
    }

    @Benchmark
    @BenchmarkTag(tag = "9057176b-c646-497f-b19a-b18d6cd79969")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.util.HashMap")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html")
    public void getLevelFromMap(Blackhole bh) {
        for (int i = begin; i < end; i++) {
            bh.consume(levelMap.get(sev));
        }
    }

    @Benchmark
    @BenchmarkTag(tag = "c1be7ae5-bd88-4f26-a73b-28b2a3346e55")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.util.TreeMap")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html")
    public void getLevelFromTreeMap(Blackhole bh) {
        for (int i = begin; i < end; i++) {
            bh.consume(treeMap.get(sev));
        }
    }

    @Benchmark
    @BenchmarkTag(tag = "256f72a6-9044-4eb3-b733-cfaa44b9b04f")
    @BenchmarkMetaData(key = "libSymbolicName", value = "array")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html")
    public void getLevelFromArray(Blackhole bh) {
        for (int i = begin; i < end; i++) {
            bh.consume(levelMapArray[sev.ordinal()]);
        }
    }

    @Benchmark
    @BenchmarkTag(tag = "930256ae-eaa0-4f36-b185-cf2642b4d3f0")
    @BenchmarkMetaData(key = "libSymbolicName", value = "array")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html")
    public void getLevelFromConcurrentMap(Blackhole bh) {
        for (int i = begin; i < end; i++) {
            bh.consume(concurrentMap.get(sev));
        }
    }

    @Benchmark
    @BenchmarkTag(tag = "4f3fe091-949f-4c36-851f-e7fd01c22075")
    @BenchmarkMetaData(key = "libSymbolicName", value = "array")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html")
    @BenchmarkMetaData(key = "libVersion", value = "3.3.0")
    @BenchmarkMetaData(key = "libVendor", value = "JCTools")
    public void getLevelFromNonBlockingHashMap(Blackhole bh) {
        for (int i = begin; i < end; i++) {
            bh.consume(nbhm.get(sev));
        }
    }

    public enum OpLevel {

        NONE, TRACE, DEBUG, INFO, NOTICE, WARNING, ERROR, FAILURE, CRITICAL, FATAL, HALT
    }

    @TearDown(org.openjdk.jmh.annotations.Level.Iteration)
    public void clearIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
    }
}
