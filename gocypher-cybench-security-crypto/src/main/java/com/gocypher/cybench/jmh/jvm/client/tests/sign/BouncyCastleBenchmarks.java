package com.gocypher.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.BouncyCastleLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class BouncyCastleBenchmarks extends SignBenchmarks implements BouncyCastleLibDefinition {


    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA256withECDSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA256withECDSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA256")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "512")
    @BenchmarkTag(tag = "fd2da744-afbe-4d22-8dbf-022809a4ddeb")
    public void bc_SHA256withECDSA(Blackhole bh) {
        bh.consume(sign("SHA256withECDSA", "BC", "EC", textToSign));
    }

}
