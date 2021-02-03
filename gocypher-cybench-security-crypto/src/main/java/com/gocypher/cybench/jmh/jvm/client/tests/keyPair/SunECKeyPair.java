package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.SunECLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class SunECKeyPair extends KeyPairGenerationBenchmarks implements SunECLibDefinition {

    /*@Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key EC (224)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "keySize", value = "224")
    @BenchmarkTag(tag = "b0eb841e-cf43-4512-b7e8-cfdae5c766a1")
    public void generateEC_SUN_224(Blackhole bh) {
        bh.consume(generateKey("SunEC", "EC", 224));
    }
    */

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key EC (521)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "keySize", value = "521")
    @BenchmarkTag(tag = "a435c3e3-1295-4cf4-8808-6c370ab80b7a")
    public void generateEC_SUN_521(Blackhole bh) {
        bh.consume(generateKey("SunEC", "EC", 521));
    }

}
