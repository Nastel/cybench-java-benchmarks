package com.gocypher.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.SunJSSELibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class SunJSSEBenchmarks extends SignBenchmarks implements SunJSSELibDefinition {

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD2withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD2withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD2")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
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
    @BenchmarkTag(tag = "f5ed9985-7321-4f03-a70f-e51525a06126")
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
    @BenchmarkTag(tag = "adef5e07-20a5-48bd-ae86-06ded1f79612")
    public void sunJSSE_SHA256withRSA(Blackhole bh) {
        bh.consume(sign("SHA256withRSA", "SunJSSE", "RSA", textToSign));
    }
}
