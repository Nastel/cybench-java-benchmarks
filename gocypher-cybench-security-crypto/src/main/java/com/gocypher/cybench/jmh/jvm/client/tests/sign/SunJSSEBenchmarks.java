package com.gocypher.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMetaData(key = "api", value = "SunJSSE")
@BenchmarkMetaData(key = "libSymbolicName", value = "SunJSSE")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)  provides a set of packages that enable secure Internet communications.")
@BenchmarkMetaData(key = "libVendor", value = "Oracle")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")

public class SunJSSEBenchmarks extends SignBenchmarks {


    @Benchmark

    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD2withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD2withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD2")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "677aa315-c2cc-48b9-aaca-c5268997d67a")
    public void SunJSSE_MD2withRSA(Blackhole bh) {
        bh.consume(sign("MD2WITHRSA", "SunJSSE", "RSA", textToSign));
    }

    @Benchmark

    @BenchmarkMetaData(key = "title", value = "Sign and verify using MD5withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "MD5withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "MD5")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "3167bec7-b6d5-4d9f-9742-4f576b178a03")
    public void SunJSSE_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5withRSA", "SunJSSE", "RSA", textToSign));
    }

    @Benchmark

    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA1withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA1withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA1")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "1cf3a5a7-3b60-4873-af52-741f547334bd")
    public void SunJSSE_SHA1withRSA(Blackhole bh) {
        bh.consume(sign("SHA1withRSA", "SunJSSE", "RSA", textToSign));
    }

    @Benchmark

    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA384withRSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA384withRSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA384")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "RSA")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "2048")
    @BenchmarkTag(tag = "f7271b7f-b7a1-4099-802d-ab9aaf89c2d0")
    public void SunJSSE_SHA384withRSA(Blackhole bh) {
        bh.consume(sign("SHA384withRSA", "SunJSSE", "RSA", textToSign));
    }




    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "Oracle")
    @BenchmarkMetaData(key = "libDescription", value = "The SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
    @BenchmarkMetaData(key = "libSymbolicName", value = "SunEC")
    @BenchmarkMetaData(key = "libVersion", value = "-")
    @BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")

    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA256withECDSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA256withECDSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA256")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "512")
    @BenchmarkTag(tag = "9f3e2339-c82e-45a9-8388-f3c74cf4fb81")
    public void SunJSSE_SHA256withECDSA(Blackhole bh) {
        bh.consume(sign("SHA256withECDSA", "SunEC", "EC", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "Oracle")
    @BenchmarkMetaData(key = "libDescription", value = "The SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
    @BenchmarkMetaData(key = "libSymbolicName", value = "SunEC")
    @BenchmarkMetaData(key = "libVersion", value = "-")
    @BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")

    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA512withECDSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA512withECDSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA512")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "512")
    @BenchmarkTag(tag = "3da80786-b88a-4126-905a-72f41a2e01df")
    public void SunJSSE_SHA512withECDSA(Blackhole bh) {
        bh.consume(sign("SHA512withECDSA", "SunEC", "EC", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "Oracle")
    @BenchmarkMetaData(key = "libDescription", value = "The SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
    @BenchmarkMetaData(key = "libSymbolicName", value = "SunEC")
    @BenchmarkMetaData(key = "libVersion", value = "-")
    @BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")

    @BenchmarkMetaData(key = "title", value = "Sign and verify using SHA224withECDSA")
    @BenchmarkMetaData(key = "signAlgorithm", value = "SHA224withECDSA")
    @BenchmarkMetaData(key = "hashAlgorithm", value = "SHA224")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "EC")
    @BenchmarkMetaData(key = "encryptionKeySize", value = "512")
    @BenchmarkTag(tag = "2bfff1bd-6681-4c8f-af91-ce972495c869")
    public void SunEC_SHA224withECDSA(Blackhole bh) {
        bh.consume(sign("SHA224withECDSA", "SunEC", "EC", textToSign));
    }
}