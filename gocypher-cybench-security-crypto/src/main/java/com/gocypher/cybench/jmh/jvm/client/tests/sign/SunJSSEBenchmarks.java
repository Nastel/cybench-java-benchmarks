package com.gocypher.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMetaData(key = "api", value = "SunJSSE")
@BenchmarkMetaData(key = "libVendor", value = "com.sun")
@BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)")
public class SunJSSEBenchmarks extends SignBenchmarks {


    @Benchmark

    @BenchmarkMetaData(key = "algorithm", value = "MD2")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "677aa315-c2cc-48b9-aaca-c5268997d67a")
    public void SunJSSE_MD2withRSA(Blackhole bh) {
        bh.consume(sign("MD2WITHRSA", "SunJSSE", "RSA", textToSign));
    }

    @Benchmark

    @BenchmarkMetaData(key = "algorithm", value = "MD5")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "3167bec7-b6d5-4d9f-9742-4f576b178a03")
    public void SunJSSE_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5withRSA", "SunJSSE", "RSA", textToSign));
    }

    @Benchmark

    @BenchmarkMetaData(key = "algorithm", value = "SHA1")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "1cf3a5a7-3b60-4873-af52-741f547334bd")
    public void SunJSSE_SHA1withRSA(Blackhole bh) {
        bh.consume(sign("SHA1withRSA", "SunJSSE", "RSA", textToSign));
    }

    @Benchmark

    @BenchmarkMetaData(key = "algorithm", value = "SHA384")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "f7271b7f-b7a1-4099-802d-ab9aaf89c2d0")
    public void SunJSSE_SHA384withRSA(Blackhole bh) {
        bh.consume(sign("SHA384withRSA", "SunJSSE", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA256")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "9f3e2339-c82e-45a9-8388-f3c74cf4fb81")
    public void SunJSSE_SHA256withECDSA(Blackhole bh) {
        bh.consume(sign("SHA256withECDSA", "SunEC", "EC", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA512")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "3da80786-b88a-4126-905a-72f41a2e01df")
    public void SunJSSE_SHA512withECDSA(Blackhole bh) {
        bh.consume(sign("SHA512withECDSA", "SunEC", "EC", textToSign));
    }
}