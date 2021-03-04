package com.gocypher.cybench.jmh.jvm.client.tests.encrypt;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.BouncyCastleLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class BouncyCastleEncrypt extends EncryptBenchmarks implements BouncyCastleLibDefinition {

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/CBC/NoPadding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/CBC/NoPadding (128)")
    @BenchmarkTag(tag = "49d59afa-2b44-4ed1-a180-3a850ca46e64")
    public void AES_CBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/CBC/NoPadding (128)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/CBC/PKCS5Padding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/CBC/PKCS5Padding (128)")
    @BenchmarkTag(tag = "49813661-a88e-4c4e-a7c8-4de31a5628f9")
    public void AES_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/CBC/PKCS5Padding (128)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/ECB/NoPadding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/ECB/NoPadding (128)")
    @BenchmarkTag(tag = "581e87a7-c458-41c2-9a8c-4e5ee171e3d8")
    public void AES_ECB_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/ECB/NoPadding (128)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/ECB/PKCS5Padding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/ECB/PKCS5Padding (128)")
    @BenchmarkTag(tag = "229f0755-aa7f-4c18-880d-06e6ca1a63b3")
    public void AES_ECB_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/ECB/PKCS5Padding (128)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/CBC/NoPadding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/CBC/NoPadding (56)")
    public void DES_CBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/CBC/NoPadding (56)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/CBC/PKCS5Padding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/CBC/PKCS5Padding (56)")
    public void DES_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/CBC/PKCS5Padding (56)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/ECB/NoPadding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/ECB/NoPadding (56)")
    public void DES_EBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/ECB/NoPadding (56)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/ECB/PKCS5Padding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/ECB/PKCS5Padding (56)")
    public void DES_EBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/ECB/PKCS5Padding (56)");
        encrypt(bh, cipherDescription, "BC", testString);
    }
*/
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/CBC/NoPadding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/CBC/NoPadding (168)")
    @BenchmarkTag(tag = "c06810c3-eabe-42ee-a166-ad872fa67f84")
    public void DESede_CBS_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/CBC/NoPadding (168)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/CBC/PKCS5Padding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/CBC/PKCS5Padding (168)")
    @BenchmarkTag(tag = "b52fc21e-781f-456c-b3e8-f421e925603c")
    public void DESede_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/CBC/PKCS5Padding (168)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/ECB/NoPadding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/ECB/NoPadding (168)")
    @BenchmarkTag(tag = "879c8e11-7ef2-4c21-8710-da65dd5d435d")
    public void DESede_ECB_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/ECB/NoPadding (168)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/ECB/PKCS5Padding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/ECB/PKCS5Padding (168)")
    @BenchmarkTag(tag = "252756b4-8525-46cb-aed3-81f5c940b376")
    public void DESede_ECB_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/ECB/PKCS5Padding (168)");
        encrypt(bh, cipherDescription, "BC", testString);
    }
}
