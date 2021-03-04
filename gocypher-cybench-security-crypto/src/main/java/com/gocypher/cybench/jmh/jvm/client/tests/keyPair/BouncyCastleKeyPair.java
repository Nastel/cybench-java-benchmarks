package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.BouncyCastleLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class BouncyCastleKeyPair extends KeyPairGenerationBenchmarks implements BouncyCastleLibDefinition {


    /*@Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key DSA (512)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "512")
    @BenchmarkTag(tag = "0be3c3ed-c7c0-4a11-a547-b550e3fb3972")
    public void generateDSA_BC_512(Blackhole bh) {
        bh.consume(generateKey("BC", "DSA", 512));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key DSA (1024)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "1024")
    @BenchmarkTag(tag = "9370448d-92d3-4434-9734-620ff7a36794")
    public void generateDSA_BC_1024(Blackhole bh) {
        bh.consume(generateKey("BC", "DSA", 1024));
    }
    */

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key DSA (2048)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "2048")
    @BenchmarkTag(tag = "c14ba059-ade7-49ce-be89-e249332e173d")
    public void generateDSA_BC_2048(Blackhole bh) {
        bh.consume(generateKey("BC", "DSA", 2048));
    }

    /*@Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key EC (224)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "keySize", value = "224")
    @BenchmarkTag(tag = "43c0cf14-e8ec-4e9b-bbd5-3e24f64b2e76")
    public void generateEC_BC_224(Blackhole bh) {
        bh.consume(generateKey("BC", "EC", 224));
    }
    */

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key EC (521)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "keySize", value = "521")
    @BenchmarkTag(tag = "2cfe0800-e033-4648-9555-e98eaf620656")
    public void generateEC_BC_521(Blackhole bh) {
        bh.consume(generateKey("BC", "EC", 521));
    }

}
