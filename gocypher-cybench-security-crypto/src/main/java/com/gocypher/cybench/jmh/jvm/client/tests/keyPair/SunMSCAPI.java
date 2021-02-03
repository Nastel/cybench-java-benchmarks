package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;


@BenchmarkMetaData(key = "api", value = "SunMSCAPI")
@BenchmarkMetaData(key = "libSymbolicName", value = "SunMSCAPI")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
@BenchmarkMetaData(key = "libVendor", value = "Oracle")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
public class SunMSCAPI extends KeyPairGenerationBenchmarks{

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key RSA (1024)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "keySize", value = "1024")
    @BenchmarkTag(tag = "fdccae97-af47-49cf-ac20-04121446c3f5")
    public void generateRSA_SunMSCAPI_1024(Blackhole bh) {
        bh.consume(generateKey("SunMSCAPI", "RSA", 1024));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key RSA (4096)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "keySize", value = "4096")
    @BenchmarkTag(tag = "b5ed95ee-eae1-4259-8e1d-bb43ab1f0568")
    public void generateRSA_SunMSCAPI_4096(Blackhole bh) {
        bh.consume(generateKey("SunMSCAPI", "RSA", 4096));
    }

}
