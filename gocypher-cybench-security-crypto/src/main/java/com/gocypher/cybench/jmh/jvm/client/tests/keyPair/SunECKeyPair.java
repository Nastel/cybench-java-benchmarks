package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMetaData(key = "api", value = "SunEC")
@BenchmarkMetaData(key = "libVendor", value = "com.sun")
@BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
@BenchmarkMetaData(key = "algorithm", value = "EC")
public class SunECKeyPair extends KeyPairGenerationBenchmarks{

    @Benchmark
    @BenchmarkMetaData(key = "size", value = "224")
    @BenchmarkTag(tag = "b0eb841e-cf43-4512-b7e8-cfdae5c766a1")
    public void generateEC_SUN_224(Blackhole bh) {
        bh.consume(generateKey("SunEC", "EC", 224));
    }


    @Benchmark
    @BenchmarkMetaData(key = "size", value = "521")
    @BenchmarkTag(tag = "a435c3e3-1295-4cf4-8808-6c370ab80b7a")
    public void generateEC_SUN_521(Blackhole bh) {
        bh.consume(generateKey("SunEC", "EC", 521));
    }

}
