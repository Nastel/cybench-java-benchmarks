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
    @BenchmarkTag(tag = "6ac49e4f-3f8e-401b-96c9-2e701ef62890")
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
    @BenchmarkTag(tag = "ec31191c-cf65-42b0-9126-bff9ad4f7e16")
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

    // *****
    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "DES")
    @BenchmarkMetaData(key = "size", value = "56")
    @BenchmarkTag(tag = "aaf66887-0793-4460-8258-04428ddaf434")
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
    @BenchmarkTag(tag = "af31c269-154c-457a-b9d8-446e86d6dce8")
    public void generateDES_JCE_56(Blackhole bh) {
        bh.consume(generateKey("SunJCE", "DES", 56));
    }

    /**
     * ***
     */
    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "size", value = "112")
    @BenchmarkTag(tag = "50a2d237-06a7-4c4a-9c8f-840f5c91fa99")
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
    @BenchmarkTag(tag = "579c8328-1107-4034-92aa-4276d28aec22")
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
    @BenchmarkTag(tag = "b55acc52-3c95-431e-bd72-d7f3ead3de2c")
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
    @BenchmarkTag(tag = "fd664bbf-a2a5-4254-9b9f-234c121aad7d")
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
    @BenchmarkTag(tag = "86cd39f7-7e6b-4f77-a83e-4950c138c4b4")
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
    @BenchmarkTag(tag = "781f2408-6889-4bf7-8b94-70a44f2f2e80")
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
    @BenchmarkTag(tag = "d94a8681-7790-4c78-ab22-f58ff6a14c59")
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
    @BenchmarkTag(tag = "aa55ebad-5e41-4cac-be1c-bbd4ffec8e07")
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
