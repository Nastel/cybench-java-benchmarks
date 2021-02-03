package com.gocypher.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMetaData(key = "api", value = "SunMSCAPI")
@BenchmarkMetaData(key = "libVendor", value = "Oracle")
@BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
@BenchmarkMetaData(key = "libSymbolicName", value = "SunMSCAPI")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/technotes/guides/security/SunProviders.html#SunMSCAPI")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
public class SunMSCAPIBenchmarks extends SignBenchmarks{
    @Benchmark

    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD2withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD2withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD2")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "1e7879ab-1c52-4d60-ab4c-9af8924198dc")
    public void SunMSCAPI_MD2withRSA(Blackhole bh) {
        bh.consume(sign("MD2WITHRSA", "SunMSCAPI", "RSA_W", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD5withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD5withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD5")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "444b9dd4-521c-4670-974b-08f117ede922")
    public void SunMSCAPI_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5withRSA", "SunMSCAPI", "RSA_W", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA1withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA1withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA1")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "f837a643-38b8-472e-85a7-81e01e4eba6e")
    public void SunMSCAPI_SHA1withRSA(Blackhole bh) {
        bh.consume(sign("SHA1withRSA", "SunMSCAPI", "RSA_W", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA384withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA384withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA384")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "c209a190-c6b6-4ef0-8fd5-14ef5384e736")
    public void SunMSCAPI_SHA384withRSA(Blackhole bh) {
        bh.consume(sign("SHA384withRSA", "SunMSCAPI", "RSA_W", textToSign));
    }
}
