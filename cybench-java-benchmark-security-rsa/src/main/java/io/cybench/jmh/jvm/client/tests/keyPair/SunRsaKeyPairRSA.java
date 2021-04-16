package io.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import io.cybench.jmh.jvm.client.tests.definitions.SunRsaSignLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class SunRsaKeyPairRSA extends KeyPairGenerationBenchmarks implements SunRsaSignLibDefinition {

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key RSA (2048)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "keySize", value = "2048")
    @BenchmarkTag(tag = "3db18adc-89ab-4ffe-8970-5802c094e5b1")
    public void generateRSA_SunRsaSign_2048(Blackhole bh) {
        bh.consume(generateKey("SunRsaSign", "RSA", 2048));
    }
}
