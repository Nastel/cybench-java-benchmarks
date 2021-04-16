package com.gocypher.cybench.jmh.jvm.client.tests.key;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.SunJCELibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class SunJceKey extends KeyGenerationBenchmarks implements SunJCELibDefinition {

    /*@Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key AES (128)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "AES")
    @BenchmarkMetaData(key = "keySize", value = "128")
    public void generateAES_JCE_128(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "AES", 128));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key AES (192)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "AES")
    @BenchmarkMetaData(key = "keySize", value = "192")
    public void generateAES_JCE_192(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "AES", 192));
    }
    */
    // @Benchmark
    // @BenchmarkMetaData(key = "title", value = "Generation of symmetric key AES (256)")
    // @BenchmarkMetaData(key = "keyAlgorithm", value = "AES")
    // @BenchmarkMetaData(key = "keySize", value = "256")
    // public void generateDSA_JCE_256(Blackhole bh) {
    // bh.consume(generateKey("SunJCE", "AES", 256));
    // }
    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key DES (56)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DES")
    @BenchmarkMetaData(key = "keySize", value = "56")
    public void generateDES_JCE_56(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "DES", 56));
    }
    */
    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key DESEDE (112)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "keySize", value = "112")
    public void generateDESEDE_JCE_112(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "DESEDE", 112));
    }
    */
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key DESEDE (168)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "keySize", value = "168")
    @BenchmarkTag(tag = "7e77c5c5-4f8f-48b2-b4fe-47afc2c08909")
    public void generateDESEDE_JCE_168(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "DESEDE", 168));
    }

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key RC2 (40)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RC2")
    @BenchmarkMetaData(key = "keySize", value = "40")
    public void generateRC2_JCE_40(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "RC2", 40));
    }
    */
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key RC2 (128)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RC2")
    @BenchmarkMetaData(key = "keySize", value = "128")
    @BenchmarkTag(tag = "3feb4642-e1eb-4222-bdda-651ab684e0fe")
    public void generateRC2_JCE_128(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "RC2", 128));
    }
}
