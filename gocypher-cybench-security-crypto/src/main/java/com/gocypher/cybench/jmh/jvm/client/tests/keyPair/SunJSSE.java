package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class SunJSSE extends KeyPairGenerationBenchmarks{


    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJSSE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "4096")
    @BenchmarkTag(tag = "fd3f092b-4993-42ac-9ea2-fe61b7af21b3")
    public void generateRSA_SunJSSE_4096(Blackhole bh) {
        bh.consume(generateKey("SunJSSE", "RSA", 4096));
    }

}
