package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMetaData(key = "api", value = "SunRsaSign")
@BenchmarkMetaData(key = "libVendor", value = "com.sun")
@BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
@BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
@BenchmarkMetaData(key = "algorithm", value = "RSA")
public class SunRsaKeyPair extends KeyPairGenerationBenchmarks{

    @Benchmark
    @BenchmarkMetaData(key = "size", value = "1024")
    @BenchmarkTag(tag = "05e75a55-4c8c-47a2-83ac-009fe381b6e2")
    public void generateRSA_SunRsaSign_1024(Blackhole bh) {
        bh.consume(generateKey("SunRsaSign", "RSA", 1024));
    }


    @Benchmark
    @BenchmarkMetaData(key = "size", value = "4096")
    @BenchmarkTag(tag = "4c53c58a-469a-4883-8b36-135a7c912485")
    public void generateRSA_SunRsaSign_4096(Blackhole bh) {
        bh.consume(generateKey("SunRsaSign", "RSA", 4096));
    }

}
