package com.gocypher.cybench.jmh.jvm.client.tests;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "true")
@BenchmarkMetaData(key = "context", value = "JAVA security")
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "description", value = "KeyPair Generation")
public class KeyPairGenerationBenchmarks {

    @Setup
    public void setup() {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SUN")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "DSA")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "18b03312-2e40-4c69-8efb-b07898c2c2ac")
    public void generateDSA_SUN_512(Blackhole bh) {
        bh.consume(generateKey("SUN", "DSA", 512));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "DSA")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "0be3c3ed-c7c0-4a11-a547-b550e3fb3972")
    public void generateDSA_BC_512(Blackhole bh) {
        bh.consume(generateKey("SUN", "DSA", 512));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SUN")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "DSA")
    @BenchmarkMetaData(key = "size", value = "1024")
    @BenchmarkTag(tag = "ccddb700-fa11-4b49-b74a-84d55ee7e83e")
    public void generateDSA_SUN_1024(Blackhole bh) {
        bh.consume(generateKey("SUN", "DSA", 1024));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "DSA")
    @BenchmarkMetaData(key = "size", value = "1024")
    @BenchmarkTag(tag = "9370448d-92d3-4434-9734-620ff7a36794")
    public void generateDSA_BC_1024(Blackhole bh) {
        bh.consume(generateKey("SUN", "DSA", 1024));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SUN")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "DSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "f956957a-1556-4dbd-b4b2-9fbe885ef08b")
    public void generateDSA_SUN_2048(Blackhole bh) {
        bh.consume(generateKey("SUN", "DSA", 2048));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "DSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "c14ba059-ade7-49ce-be89-e249332e173d")
    public void generateDSA_BC_2048(Blackhole bh) {
        bh.consume(generateKey("SUN", "DSA", 2048));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "EC")
    @BenchmarkMetaData(key = "size", value = "224")
    @BenchmarkTag(tag = "b0eb841e-cf43-4512-b7e8-cfdae5c766a1")
    public void generateEC_SUN_224(Blackhole bh) {
        bh.consume(generateKey("SunEC", "EC", 224));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "EC")
    @BenchmarkMetaData(key = "size", value = "224")
    @BenchmarkTag(tag = "43c0cf14-e8ec-4e9b-bbd5-3e24f64b2e76")
    public void generateEC_BC_224(Blackhole bh) {
        bh.consume(generateKey("BC", "EC", 224));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The Java Cryptographic Extension (JCE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "EC")
    @BenchmarkMetaData(key = "size", value = "521")
    @BenchmarkTag(tag = "a435c3e3-1295-4cf4-8808-6c370ab80b7a")
    public void generateEC_SUN_521(Blackhole bh) {
        bh.consume(generateKey("SunEC", "EC", 521));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "EC")
    @BenchmarkMetaData(key = "size", value = "521")
    @BenchmarkTag(tag = "2cfe0800-e033-4648-9555-e98eaf620656")
    public void generateEC_BC_521(Blackhole bh) {
        bh.consume(generateKey("BC", "EC", 521));
    }

    // ***
    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "1024")
    @BenchmarkTag(tag = "c0d03af4-50c3-448e-9870-7730e29c1948")
    public void generateRSA_BC_1024(Blackhole bh) {
        bh.consume(generateKey("BC", "RSA", 1024));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJSSE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "1024")
    @BenchmarkTag(tag = "89397433-4d49-4e87-ad3a-352a1f731cd2")
    public void generateRSA_SunJSSE_1024(Blackhole bh) {
        bh.consume(generateKey("SunJSSE", "RSA", 1024));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunMSCAPI")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "1024")
    @BenchmarkTag(tag = "fdccae97-af47-49cf-ac20-04121446c3f5")
    public void generateRSA_SunMSCAPI_1024(Blackhole bh) {
        bh.consume(generateKey("SunMSCAPI", "RSA", 1024));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunRsaSign")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "1024")
    @BenchmarkTag(tag = "05e75a55-4c8c-47a2-83ac-009fe381b6e2")
    public void generateRSA_SunRsaSign_1024(Blackhole bh) {
        bh.consume(generateKey("SunRsaSign", "RSA", 1024));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "4096")
    @BenchmarkTag(tag = "e035cda8-ba8e-4204-ac9f-7ebb9b0a05f7")
    public void generateRSA_BC_4096(Blackhole bh) {
        bh.consume(generateKey("BC", "RSA", 4096));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJSSE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "4096")
    @BenchmarkTag(tag = "fd3f092b-4993-42ac-9ea2-fe61b7af21b3")
    public void generateRSA_SunJSSE_4096(Blackhole bh) {
        bh.consume(generateKey("SunJSSE", "RSA", 4096));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunMSCAPI")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "4096")
    @BenchmarkTag(tag = "b5ed95ee-eae1-4259-8e1d-bb43ab1f0568")
    public void generateRSA_SunMSCAPI_4096(Blackhole bh) {
        bh.consume(generateKey("SunMSCAPI", "RSA", 4096));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunRsaSign")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "4096")
    @BenchmarkTag(tag = "4c53c58a-469a-4883-8b36-135a7c912485")
    public void generateRSA_SunRsaSign_4096(Blackhole bh) {
        bh.consume(generateKey("SunRsaSign", "RSA", 4096));
    }

    private KeyPair generateKey(String provider, String algorithm, int size) {
        try {
            KeyPairGenerator keyPairGeneratorRSA = KeyPairGenerator.getInstance(algorithm, provider);
            keyPairGeneratorRSA.initialize(size);
            return keyPairGeneratorRSA.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /*
    , KeyPairGenerator - DSA - BC
, KeyPairGenerator - DSA - SUN


    , KeyPairGenerator - EC - BC
, KeyPairGenerator - EC - SunEC

, KeyPairGenerator - RSA - BC
, KeyPairGenerator - RSA - SunJSSE
, KeyPairGenerator - RSA - SunMSCAPI
, KeyPairGenerator - RSA - SunRsaSign
    */
}
