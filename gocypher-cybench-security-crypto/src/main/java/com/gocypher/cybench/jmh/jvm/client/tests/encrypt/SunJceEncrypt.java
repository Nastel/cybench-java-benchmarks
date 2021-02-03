package com.gocypher.cybench.jmh.jvm.client.tests.encrypt;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.SunJCELibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;



public class SunJceEncrypt extends EncryptBenchmarks implements SunJCELibDefinition {

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/CBC/NoPadding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/CBC/NoPadding (128)")
    @BenchmarkTag(tag = "b295fa9a-c524-44bc-9412-87500049612f")
    public void AES_CBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/CBC/NoPadding (128)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/CBC/PKCS5Padding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/CBC/PKCS5Padding (128)")
    @BenchmarkTag(tag = "1250fbeb-e406-4aab-9d3b-81c5d30e0c68")
    public void AES_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/CBC/PKCS5Padding (128)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/ECB/NoPadding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/ECB/NoPadding (128)")
    @BenchmarkTag(tag = "2ee60340-2cb0-4b4c-8fb4-fd3e8a0e843e")
    public void AES_ECB_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/ECB/NoPadding (128)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/ECB/PKCS5Padding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/ECB/PKCS5Padding (128)")
    @BenchmarkTag(tag = "d5d03948-8ab2-4156-a122-9262be601a88")
    public void AES_ECB_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/ECB/PKCS5Padding (128)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }
    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/CBC/NoPadding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/CBC/NoPadding (56)")
    @BenchmarkTag(tag = "096b41fe-319d-4d61-b484-c1cdc7e36bc7")
    public void DES_CBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/CBC/NoPadding (56)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/CBC/PKCS5Padding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/CBC/PKCS5Padding (56)")
    @BenchmarkTag(tag = "a7f47c06-7aab-47c5-9fbf-0ec314af9c71")
    public void DES_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/CBC/PKCS5Padding (56)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/ECB/NoPadding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/ECB/NoPadding (56)")
    @BenchmarkTag(tag = "88fda37c-ee51-49aa-afa7-9c0b958a5425")
    public void DES_EBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/ECB/NoPadding (56)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/ECB/PKCS5Padding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/ECB/PKCS5Padding (56)")
    @BenchmarkTag(tag = "8914e01b-9e6b-4883-b169-e00ee8c77427")
    public void DES_EBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/ECB/PKCS5Padding (56)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }
*/
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/CBC/NoPadding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/CBC/NoPadding (168)")
    @BenchmarkTag(tag = "9b334129-fc71-4f74-b645-cba87a8f3de1")
    public void DESede_CBS_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/CBC/NoPadding (168)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/CBC/PKCS5Padding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/CBC/PKCS5Padding (168)")
    @BenchmarkTag(tag = "51e5cd3d-2b27-4e2e-a39a-3bfb819f5ad4")
    public void DESede_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/CBC/PKCS5Padding (168)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/ECB/NoPadding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/ECB/NoPadding (168)")
    @BenchmarkTag(tag = "969401cd-8117-459c-b0c6-6ad4e0032386")
    public void DESede_ECB_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/ECB/NoPadding (168)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/ECB/PKCS5Padding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/ECB/PKCS5Padding (168)")
    @BenchmarkTag(tag = "c35a4abe-bd48-4780-b9a0-e03e6a1eba08")
    public void DESede_ECB_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/ECB/PKCS5Padding (168)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }
}
