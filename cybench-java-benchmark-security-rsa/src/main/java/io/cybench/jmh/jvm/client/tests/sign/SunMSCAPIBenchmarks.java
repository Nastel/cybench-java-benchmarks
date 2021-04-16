package io.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import io.cybench.jmh.jvm.client.tests.definitions.SunMSCAPILibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class SunMSCAPIBenchmarks extends SignBenchmarks implements SunMSCAPILibDefinition {

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD2withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD2withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD2")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    public void SunMSCAPI_MD2withRSA(Blackhole bh) {
        bh.consume(sign("MD2WITHRSA", "SunMSCAPI", "RSA_W", textToSign));
    }
*/
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD5withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD5withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD5")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "1c4687fa-cc55-453f-b750-d4f3fcbedfa5")
    public void sunMSCAPI_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5withRSA", "SunMSCAPI", "RSA_W", textToSign));
    }

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA1withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA1withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA1")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    public void SunMSCAPI_SHA1withRSA(Blackhole bh) {
        bh.consume(sign("SHA1withRSA", "SunMSCAPI", "RSA_W", textToSign));
    }
*/
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA256withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA256withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA256")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "7687f372-bcb8-4d4f-99ef-f47a01f732a6")
    public void sunMSCAPI_SHA256withRSA(Blackhole bh) {
        bh.consume(sign("SHA256withRSA", "SunMSCAPI", "RSA_W", textToSign));
    }
}
