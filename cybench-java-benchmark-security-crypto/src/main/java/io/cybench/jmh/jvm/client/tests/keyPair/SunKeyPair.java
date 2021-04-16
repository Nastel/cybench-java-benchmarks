package io.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import io.cybench.jmh.jvm.client.tests.definitions.SunLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class SunKeyPair extends KeyPairGenerationBenchmarks implements SunLibDefinition {

    /*@Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key DSA (512)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "512")
    public void generateDSA_SUN_512(Blackhole bh) {
        bh.consume(generateKey("SUN", "DSA", 512));
    }


    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key DSA (1024)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "1024")
    public void generateDSA_SUN_1024(Blackhole bh) {
        bh.consume(generateKey("SUN", "DSA", 1024));
    }
    */
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key DSA (2048)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "2048")
    @BenchmarkTag(tag = "236baa1a-4311-4260-8a30-aad7798b4ea9")
    public void generateDSA_SUN_2048(Blackhole bh) {
        bh.consume(generateKey("SUN", "DSA", 2048));
    }
}
