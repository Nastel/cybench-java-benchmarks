package com.gocypher.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMetaData(key = "api", value = "SunRsaSign")
@BenchmarkMetaData(key = "libSymbolicName", value = "SunRsaSign")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
@BenchmarkMetaData(key = "libVendor", value = "Oracle")
@BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/technotes/guides/security/SunProviders.html#SunRsaSignProvider")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
public class SunRsaSign extends SignBenchmarks {

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD2withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD2withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD2")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "3235d778-b4d1-425b-9e60-c154cb1bd015")
    public void SunRsaSign_MD2withRSA(Blackhole bh) {
        bh.consume(sign("MD2WITHRSA", "SunRsaSign", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD5withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD5withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD5")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "2128e15a-974f-4a01-99b9-a499279a9dbd")
    public void SunRsaSign_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5withRSA", "SunRsaSign", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA1withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA1")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "444379f0-dd48-44c5-9cac-75695f45b76b")
    public void SunRsaSign_SHA1withRSA(Blackhole bh) {
        bh.consume(sign("SHA1withRSA", "SunRsaSign", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA384withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA384withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA384")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "0fac5c39-f4ad-46a3-be0d-3b515e87abb3")
    public void SunRsaSign_SHA384withRSA(Blackhole bh) {
        bh.consume(sign("SHA384withRSA", "SunRsaSign", "RSA", textToSign));
    }
}
