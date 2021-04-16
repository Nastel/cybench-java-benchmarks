package io.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import io.cybench.jmh.jvm.client.tests.definitions.SunECLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class SunECBenchmarks extends SignBenchmarks implements SunECLibDefinition
{

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA256withECDSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA256withECDSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA256")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "512")
    @BenchmarkTag(tag = "d7607220-5839-4a3b-99b9-2db169ebf324")
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
    public void SunEC_SHA224withECDSA(Blackhole bh) {
        bh.consume(sign("SHA224withECDSA", "SunEC", "EC", textToSign));
    }
    */
}
