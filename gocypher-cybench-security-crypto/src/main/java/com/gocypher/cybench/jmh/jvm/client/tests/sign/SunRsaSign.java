package com.gocypher.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.SunRsaSignLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;


public class SunRsaSign extends SignBenchmarks implements SunRsaSignLibDefinition {
/*
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
*/
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD5withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD5withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD5")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "2128e15a-974f-4a01-99b9-a499279a9dbd")
    public void sunRsaSign_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5withRSA", "SunRsaSign", "RSA", textToSign));
    }
/*
    @Benchmark
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA1withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA1")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "444379f0-dd48-44c5-9cac-75695f45b76b")
    public void SunRsaSign_SHA1withRSA(Blackhole bh) {
        bh.consume(sign("SHA1withRSA", "SunRsaSign", "RSA", textToSign));
    }
*/
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA256withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA256withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA256")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "0fac5c39-f4ad-46a3-be0d-3b515e87abb3")
    public void sunRsaSign_SHA256withRSA(Blackhole bh) {
        bh.consume(sign("SHA256withRSA", "SunRsaSign", "RSA", textToSign));
    }
}
