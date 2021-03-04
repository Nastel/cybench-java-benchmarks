package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.SunMSCAPILibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;



public class SunMSCAPI extends KeyPairGenerationBenchmarks implements SunMSCAPILibDefinition {

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key RSA (2048)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "keySize", value = "2048")
    @BenchmarkTag(tag = "b5ed95ee-eae1-4259-8e1d-bb43ab1f0568")
    public void generateRSA_SunMSCAPI_2048(Blackhole bh) {
        bh.consume(generateKey("SunMSCAPI", "RSA", 2048));
    }

}
