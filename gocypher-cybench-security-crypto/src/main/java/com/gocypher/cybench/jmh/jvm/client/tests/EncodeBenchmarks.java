package com.gocypher.cybench.jmh.jvm.client.tests;

import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@State(Scope.Benchmark)
public class EncodeBenchmarks {

    @Param({ "AES/CBC/NoPadding ",
            "AES/CBC/PKCS5Padding ",
            "AES/ECB/NoPadding ", "AES/ECB/PKCS5Padding ", "DES/CBC/NoPadding", "DES/CBC/PKCS5Padding", "DES/ECB/NoPadding", "DES/ECB/PKCS5Padding", "DESede/CBC/NoPadding ", "DESede/CBC/PKCS5Padding ", "DESede/ECB/NoPadding ", "DESede/ECB/PKCS5Padding ", "RSA/ECB/PKCS1Padding", "RSA/ECB/OAEPWithSHA-1AndMGF1Padding ", "RSA/ECB/OAEPWithSHA-256AndMGF1Padding " })
    private String transformation;

    @Benchmark
    @BenchmarkTag(tag = "c9906f2c-b178-45ad-9cc6-d929efa4ba4e")
    public void cipherBenchmark(Blackhole bh) throws Exception {
        Cipher cipher = Cipher.getInstance(this.transformation);
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(128);
        SecretKey secretKey = generator.generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        bh.consume(cipher.doFinal());
    }
}
