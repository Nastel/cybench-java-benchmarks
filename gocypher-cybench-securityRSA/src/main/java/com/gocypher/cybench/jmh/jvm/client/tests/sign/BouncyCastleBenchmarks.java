package com.gocypher.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.BouncyCastleLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class BouncyCastleBenchmarks extends SignBenchmarks implements BouncyCastleLibDefinition {



    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD5withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD5withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD5")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "4857d113-2bdf-437c-b538-200f1ad7dac2")
    public void bc_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5WITHRSA", "BC", "RSA", textToSign));
    }


    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA256withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA256withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA256")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "36fa169e-7025-41a8-9afa-8f34d019fce4")
    public void bc_SHA256withRSA(Blackhole bh) {
        bh.consume(sign("SHA256withRSA", "BC", "RSA", textToSign));
    }


}
