package com.gocypher.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class SunRsaSign extends SignBenchmarks{


    @Benchmark

    @BenchmarkMetaData(key = "algorithm", value = "MD2")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "3235d778-b4d1-425b-9e60-c154cb1bd015")
    public void SunRsaSign_MD2withRSA(Blackhole bh) {
        bh.consume(sign("MD2WITHRSA", "SunRsaSign", "RSA", textToSign));
    }

    @Benchmark

    @BenchmarkMetaData(key = "algorithm", value = "MD5")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "2128e15a-974f-4a01-99b9-a499279a9dbd")
    public void SunRsaSign_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5withRSA", "SunRsaSign", "RSA", textToSign));
    }

    @Benchmark

    @BenchmarkMetaData(key = "algorithm", value = "SHA1")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "444379f0-dd48-44c5-9cac-75695f45b76b")
    public void SunRsaSign_SHA1withRSA(Blackhole bh) {
        bh.consume(sign("SHA1withRSA", "SunRsaSign", "RSA", textToSign));
    }

    @Benchmark

    @BenchmarkMetaData(key = "algorithm", value = "SHA384")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "2d10ee15-1d5f-4902-9981-9cb3b3d1d3ad")
    public void SunRsaSign_SHA384withRSA(Blackhole bh) {
        bh.consume(sign("SHA384withRSA", "SunRsaSign", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA224")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "2bfff1bd-6681-4c8f-af91-ce972495c869")
    public void SunEC_SHA224withECDSA(Blackhole bh) {
        bh.consume(sign("SHA224withECDSA", "SunEC", "EC", textToSign));
    }
}