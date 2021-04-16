package io.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import io.cybench.jmh.jvm.client.tests.definitions.SunRsaSignLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class SunRsaSign extends SignBenchmarks implements SunRsaSignLibDefinition {

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD2withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD2withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD2")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
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
    @BenchmarkTag(tag = "7dad272e-3fba-4d36-8099-0017942561a7")
    public void sunRsaSign_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5withRSA", "SunRsaSign", "RSA", textToSign));
    }

    /*
    @Benchmark
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA1withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA1")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
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
    @BenchmarkTag(tag = "6d780524-6b67-4467-9b06-cdf40f295bc6")
    public void sunRsaSign_SHA256withRSA(Blackhole bh) {
        bh.consume(sign("SHA256withRSA", "SunRsaSign", "RSA", textToSign));
    }
}
