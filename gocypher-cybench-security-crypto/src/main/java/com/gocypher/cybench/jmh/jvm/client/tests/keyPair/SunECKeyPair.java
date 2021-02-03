package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMetaData(key = "api", value = "SunEC")
@BenchmarkMetaData(key = "libSymbolicName", value = "SunEC")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libDescription", value = "SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
@BenchmarkMetaData(key = "libVendor", value = "Oracle")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
public class SunECKeyPair extends KeyPairGenerationBenchmarks{

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key EC (224)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "keySize", value = "224")
    @BenchmarkTag(tag = "b0eb841e-cf43-4512-b7e8-cfdae5c766a1")
    public void generateEC_SUN_224(Blackhole bh) {
        bh.consume(generateKey("SunEC", "EC", 224));
    }


    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of asymmetric key EC (521)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "keySize", value = "521")
    @BenchmarkTag(tag = "a435c3e3-1295-4cf4-8808-6c370ab80b7a")
    public void generateEC_SUN_521(Blackhole bh) {
        bh.consume(generateKey("SunEC", "EC", 521));
    }

}
