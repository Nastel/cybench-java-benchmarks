package io.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import io.cybench.jmh.jvm.client.tests.definitions.SunECLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class SunECKeyPair extends KeyPairGenerationBenchmarks implements SunECLibDefinition {

    /*@Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key EC (224)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "keySize", value = "224")
    public void generateEC_SUN_224(Blackhole bh) {
        bh.consume(generateKey("SunEC", "EC", 224));
    }
    */
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key EC (521)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "keySize", value = "521")
    @BenchmarkTag(tag = "2fb0d2a2-abd3-49b4-87dd-4f7e15507373")
    public void generateEC_SUN_521(Blackhole bh) {
        bh.consume(generateKey("SunEC", "EC", 521));
    }
}
