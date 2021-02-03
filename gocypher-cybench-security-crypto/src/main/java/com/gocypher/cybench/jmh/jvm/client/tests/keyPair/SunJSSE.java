package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMetaData(key = "api", value = "SunJSSE")
@BenchmarkMetaData(key = "libSymbolicName", value = "SunJSSE")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)  provides a set of packages that enable secure Internet communications.")
@BenchmarkMetaData(key = "libVendor", value = "Oracle")
@BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/technotes/guides/security/SunProviders.html#SunJSSEProvider")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")

public class SunJSSE extends KeyPairGenerationBenchmarks{


    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key RSA (1024)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "keySize", value = "1024")
    @BenchmarkTag(tag = "89397433-4d49-4e87-ad3a-352a1f731cd2")
    public void generateRSA_SunJSSE_1024(Blackhole bh) {
        bh.consume(generateKey("SunJSSE", "RSA", 1024));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key RSA (4096)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "keySize", value = "4096")
    @BenchmarkTag(tag = "fd3f092b-4993-42ac-9ea2-fe61b7af21b3")
    public void generateRSA_SunJSSE_4096(Blackhole bh) {
        bh.consume(generateKey("SunJSSE", "RSA", 4096));
    }

}
