package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.SunLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class SunKeyPair extends KeyPairGenerationBenchmarks implements SunLibDefinition {

    /*@Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key DSA (512)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "512")
    @BenchmarkTag(tag = "18b03312-2e40-4c69-8efb-b07898c2c2ac")
    public void generateDSA_SUN_512(Blackhole bh) {
        bh.consume(generateKey("SUN", "DSA", 512));
    }


    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key DSA (1024)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "1024")
    @BenchmarkTag(tag = "ccddb700-fa11-4b49-b74a-84d55ee7e83e")
    public void generateDSA_SUN_1024(Blackhole bh) {
        bh.consume(generateKey("SUN", "DSA", 1024));
    }
    */

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key DSA (2048)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DSA")
    @BenchmarkMetaData(key = "keySize", value = "2048")
    @BenchmarkTag(tag = "f956957a-1556-4dbd-b4b2-9fbe885ef08b")
    public void generateDSA_SUN_2048(Blackhole bh) {
        bh.consume(generateKey("SUN", "DSA", 2048));
    }



}
