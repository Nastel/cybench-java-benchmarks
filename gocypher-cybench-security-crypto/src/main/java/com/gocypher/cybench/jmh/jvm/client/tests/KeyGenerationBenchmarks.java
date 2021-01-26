package com.gocypher.cybench.jmh.jvm.client.tests;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Security;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "true")
@BenchmarkMetaData(key = "context", value = "JAVA security")
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "description", value = "Key Generation")
public class KeyGenerationBenchmarks {

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "AES")
    @BenchmarkMetaData(key = "size", value = "128")
    @BenchmarkTag(tag = "962a3aef-6504-423b-8cc3-fc61803b6c23")
    public void generateAES_BC_128(Blackhole bh) {
        bh.consume(generateKey("BC", "AES", 128));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJCE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "AES")
    @BenchmarkMetaData(key = "size", value = "128")
    @BenchmarkTag(tag = "624cbaad-73a3-4580-8be0-4a8ccfcc240b")
    public void generateAES_JCE_128(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "AES", 128));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "AES")
    @BenchmarkMetaData(key = "size", value = "192")
    @BenchmarkTag(tag = "bd8855d1-18f5-4247-a05f-6d39f9d1e653")
    public void generateAES_BC_192(Blackhole bh) {
        bh.consume(generateKey("BC", "AES", 192));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJCE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "AES")
    @BenchmarkMetaData(key = "size", value = "192")
    @BenchmarkTag(tag = "8268d8a9-f303-48e4-a960-13ebb228a509")
    public void generateAES_JCE_192(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "AES", 192));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "AES")
    @BenchmarkMetaData(key = "size", value = "256")
    @BenchmarkTag(tag = "4d294ae1-0c7f-41b0-b5a4-8e110571b78a")
    public void generateAES_BC_256(Blackhole bh) {
        bh.consume(generateKey("BC", "AES", 256));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJCE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "AES")
    @BenchmarkMetaData(key = "size", value = "256")
    @BenchmarkTag(tag = "066ac890-1965-4901-b176-b6e38bdd2caa")
    public void generateDSA_JCE_256(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "AES", 256));
    }

    
    
    //*****


    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "DES")
    @BenchmarkMetaData(key = "size", value = "56")
    @BenchmarkTag(tag = "962a3aef-6504-423b-8cc3-fc61803b6c23")
    public void generateDES_BC_56(Blackhole bh) {
        bh.consume(generateKey("BC", "DES", 56));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJCE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "DES")
    @BenchmarkMetaData(key = "size", value = "56")
    @BenchmarkTag(tag = "624cbaad-73a3-4580-8be0-4a8ccfcc240b")
    public void generateDES_JCE_56(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "DES", 56));
    }

    /*******/



    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "size", value = "112")
    @BenchmarkTag(tag = "962a3aef-6504-423b-8cc3-fc61803b6c23")
    public void generateDESEDE_BC_112(Blackhole bh) {
        bh.consume(generateKey("BC", "DESEDE", 112));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJCE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "size", value = "112")
    @BenchmarkTag(tag = "624cbaad-73a3-4580-8be0-4a8ccfcc240b")
    public void generateDESEDE_JCE_112(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "DESEDE", 112));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "size", value = "168")
    @BenchmarkTag(tag = "962a3aef-6504-423b-8cc3-fc61803b6c23")
    public void generateDESEDE_BC_168(Blackhole bh) {
        bh.consume(generateKey("BC", "DESEDE", 168));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJCE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "size", value = "168")
    @BenchmarkTag(tag = "624cbaad-73a3-4580-8be0-4a8ccfcc240b")
    public void generateDESEDE_JCE_168(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "DESEDE", 168));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "RC2")
    @BenchmarkMetaData(key = "size", value = "40")
    @BenchmarkTag(tag = "962a3aef-6504-423b-8cc3-fc61803b6c23")
    public void generateRC2_BC_40(Blackhole bh) {
        bh.consume(generateKey("BC", "RC2", 40));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJCE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "RC2")
    @BenchmarkMetaData(key = "size", value = "40")
    @BenchmarkTag(tag = "624cbaad-73a3-4580-8be0-4a8ccfcc240b")
    public void generateRC2_JCE_40(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "RC2", 40));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "RC2")
    @BenchmarkMetaData(key = "size", value = "128")
    @BenchmarkTag(tag = "962a3aef-6504-423b-8cc3-fc61803b6c23")
    public void generateRC2_BC_128(Blackhole bh) {
        bh.consume(generateKey("BC", "RC2", 128));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJCE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "RC2")
    @BenchmarkMetaData(key = "size", value = "40")
    @BenchmarkTag(tag = "624cbaad-73a3-4580-8be0-4a8ccfcc240b")
    public void generateRC2_JCE_128(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "RC2", 128));
    }

    @Setup
    public void setup() {
        Security.addProvider(new BouncyCastleProvider());
    }

    private SecretKey generateKey(String provider, String keyType, Integer keySize) {
        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance(keyType, provider);
            generator.init(keySize);
            return generator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
/*
, KeyGenerator - AES - BC
, KeyGenerator - AES - SunJCE
, KeyGenerator - DES - BC
, KeyGenerator - DES - SunJCE
, KeyGenerator - DESEDE - BC
, KeyGenerator - DESede - SunJCE
, KeyGenerator - RC2 - BC
, KeyGenerator - RC2 - SunJCE
 */
