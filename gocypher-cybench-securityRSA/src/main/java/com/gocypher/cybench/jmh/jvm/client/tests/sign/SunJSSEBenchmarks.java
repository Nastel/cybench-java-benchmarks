package com.gocypher.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.SunJSSELibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class SunJSSEBenchmarks extends SignBenchmarks implements SunJSSELibDefinition {

/*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD2withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD2withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD2")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "677aa315-c2cc-48b9-aaca-c5268997d67a")
    public void SunJSSE_MD2withRSA(Blackhole bh) {
        bh.consume(sign("MD2WITHRSA", "SunJSSE", "RSA", textToSign));
    }
*/

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD5withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD5withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD5")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "3167bec7-b6d5-4d9f-9742-4f576b178a03")
    public void sunJSSE_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5withRSA", "SunJSSE", "RSA", textToSign));
    }

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA1withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA1withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA1")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "1cf3a5a7-3b60-4873-af52-741f547334bd")
    public void SunJSSE_SHA1withRSA(Blackhole bh) {
        bh.consume(sign("SHA1withRSA", "SunJSSE", "RSA", textToSign));
    }
*/

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA256withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA256withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA256")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "f7271b7f-b7a1-4099-802d-ab9aaf89c2d0")
    public void sunJSSE_SHA256withRSA(Blackhole bh) {
        bh.consume(sign("SHA256withRSA", "SunJSSE", "RSA", textToSign));
    }





}
