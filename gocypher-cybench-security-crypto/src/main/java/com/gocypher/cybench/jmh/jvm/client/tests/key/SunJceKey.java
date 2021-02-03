package com.gocypher.cybench.jmh.jvm.client.tests.key;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMetaData(key = "api", value = "SunJCE")
@BenchmarkMetaData(key = "libSymbolicName", value = "SunJCE")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libDescription", value = "The Java Cryptography Extension (JCE) from Sun Microsystems.It is a framework for implementing encryption, key generation and key agreement, and Message Authentication Code (MAC) algorithms.")
@BenchmarkMetaData(key = "libVendor", value = "Oracle")
@BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/technotes/guides/security/SunProviders.html#SunJCEProvider")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")

public class SunJceKey extends KeyGenerationBenchmarks {

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key AES (128)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "AES")
    @BenchmarkMetaData(key = "keySize", value = "128")
    @BenchmarkTag(tag = "ec31191c-cf65-42b0-9126-bff9ad4f7e16")
    public void generateAES_JCE_128(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "AES", 128));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key AES (192)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "AES")
    @BenchmarkMetaData(key = "keySize", value = "192")
    @BenchmarkTag(tag = "8268d8a9-f303-48e4-a960-13ebb228a509")
    public void generateAES_JCE_192(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "AES", 192));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key AES (256)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "AES")
    @BenchmarkMetaData(key = "keySize", value = "256")
    @BenchmarkTag(tag = "066ac890-1965-4901-b176-b6e38bdd2caa")
    public void generateDSA_JCE_256(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "AES", 256));
    }


    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key DES (56)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DES")
    @BenchmarkMetaData(key = "keySize", value = "56")
    @BenchmarkTag(tag = "af31c269-154c-457a-b9d8-446e86d6dce8")
    public void generateDES_JCE_56(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "DES", 56));
    }


    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key DESEDE (112)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "keySize", value = "112")
    @BenchmarkTag(tag = "579c8328-1107-4034-92aa-4276d28aec22")
    public void generateDESEDE_JCE_112(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "DESEDE", 112));
    }


    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key DESEDE (168)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "keySize", value = "168")
    @BenchmarkTag(tag = "fd664bbf-a2a5-4254-9b9f-234c121aad7d")
    public void generateDESEDE_JCE_168(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "DESEDE", 168));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key RC2 (40)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RC2")
    @BenchmarkMetaData(key = "keySize", value = "40")
    @BenchmarkTag(tag = "781f2408-6889-4bf7-8b94-70a44f2f2e80")
    public void generateRC2_JCE_40(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "RC2", 40));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key RC2 (128)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RC2")
    @BenchmarkMetaData(key = "keySize", value = "128")
    @BenchmarkTag(tag = "aa55ebad-5e41-4cac-be1c-bbd4ffec8e07")
    public void generateRC2_JCE_128(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "RC2", 128));
    }

}
