package com.gocypher.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.SunECLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class SunECBenchmarks extends SignBenchmarks implements SunECLibDefinition {

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA256withECDSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA256withECDSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA256")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "512")
    @BenchmarkTag(tag = "9f3e2339-c82e-45a9-8388-f3c74cf4fb81")
    public void sunEC_SHA256withECDSA(Blackhole bh) {
        bh.consume(sign("SHA256withECDSA", "SunEC", "EC", textToSign));
    }
    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA512withECDSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA512withECDSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA512")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "512")
    @BenchmarkTag(tag = "3da80786-b88a-4126-905a-72f41a2e01df")
    public void SunJSSE_SHA512withECDSA(Blackhole bh) {
        bh.consume(sign("SHA512withECDSA", "SunEC", "EC", textToSign));
    }
*/

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA224withECDSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA224withECDSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA224")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "512")
    @BenchmarkTag(tag = "2bfff1bd-6681-4c8f-af91-ce972495c869")
    public void SunEC_SHA224withECDSA(Blackhole bh) {
        bh.consume(sign("SHA224withECDSA", "SunEC", "EC", textToSign));
    }
    */
}
