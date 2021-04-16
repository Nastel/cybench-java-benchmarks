package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.SunJSSELibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class SunJSSEKeyPairRSA extends KeyPairGenerationBenchmarks implements SunJSSELibDefinition {

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key RSA (2048)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "keySize", value = "2048")
    @BenchmarkTag(tag = "7b3fa68b-9c14-4d79-b71f-90a0d4c13a9b")
    public void generateRSA_SunJSSE_2048(Blackhole bh) {
        bh.consume(generateKey("SunJSSE", "RSA", 2048));
    }
}
