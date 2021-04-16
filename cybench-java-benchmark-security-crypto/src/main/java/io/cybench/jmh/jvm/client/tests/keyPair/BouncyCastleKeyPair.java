package io.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import io.cybench.jmh.jvm.client.tests.definitions.BouncyCastleLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class BouncyCastleKeyPair extends KeyPairGenerationBenchmarks implements BouncyCastleLibDefinition {

    /*@Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key DSA (512)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "512")
    public void generateDSA_BC_512(Blackhole bh) {
        bh.consume(generateKey("BC", "DSA", 512));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key DSA (1024)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "1024")
    public void generateDSA_BC_1024(Blackhole bh) {
        bh.consume(generateKey("BC", "DSA", 1024));
    }
    */
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key DSA (2048)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "2048")
    @BenchmarkTag(tag = "03b6e6ae-c79f-48bb-b378-4b02bfc926d4")
    public void generateDSA_BC_2048(Blackhole bh) {
        bh.consume(generateKey("BC", "DSA", 2048));
    }

    /*@Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key EC (224)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "keySize", value = "224")
    public void generateEC_BC_224(Blackhole bh) {
        bh.consume(generateKey("BC", "EC", 224));
    }
    */
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key EC (521)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "keySize", value = "521")
    @BenchmarkTag(tag = "1cc6c8a1-4a49-497a-b4e0-e0d970de4abd")
    public void generateEC_BC_521(Blackhole bh) {
        bh.consume(generateKey("BC", "EC", 521));
    }
}
