package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.SunMSCAPILibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class SunMSCAPIKeyPairRSA extends KeyPairGenerationBenchmarks implements SunMSCAPILibDefinition {

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key RSA (2048)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "keySize", value = "2048")
    @BenchmarkTag(tag = "5e872eeb-b073-4c85-9074-c809ecaddfb4")
    public void generateRSA_SunMSCAPI_2048(Blackhole bh) {
        bh.consume(generateKey("SunMSCAPI", "RSA", 2048));
    }
}
