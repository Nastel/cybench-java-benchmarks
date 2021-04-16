package io.cybench.jmh.jvm.client.tests.encrypt;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import io.cybench.jmh.jvm.client.tests.definitions.SunJCELibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class SunJceEncrypt extends EncryptBenchmarks implements SunJCELibDefinition
{

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/CBC/NoPadding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/CBC/NoPadding (128)")
    @BenchmarkTag(tag = "67ec01e5-410a-45c3-8b6d-cd72c7c4e110")
    public void AES_CBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/CBC/NoPadding (128)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/CBC/PKCS5Padding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/CBC/PKCS5Padding (128)")
    @BenchmarkTag(tag = "291eeb8d-5ac2-4a82-b132-5a923653d8dc")
    public void AES_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/CBC/PKCS5Padding (128)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/ECB/NoPadding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/ECB/NoPadding (128)")
    @BenchmarkTag(tag = "3f6a2025-9b46-4b4e-ad72-63080cd48a11")
    public void AES_ECB_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/ECB/NoPadding (128)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/ECB/PKCS5Padding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/ECB/PKCS5Padding (128)")
    @BenchmarkTag(tag = "593fcd3f-89ff-49d8-9cd4-c958e374cdb4")
    public void AES_ECB_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/ECB/PKCS5Padding (128)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/CBC/NoPadding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/CBC/NoPadding (56)")
    public void DES_CBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/CBC/NoPadding (56)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/CBC/PKCS5Padding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/CBC/PKCS5Padding (56)")
    public void DES_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/CBC/PKCS5Padding (56)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/ECB/NoPadding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/ECB/NoPadding (56)")
    public void DES_EBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/ECB/NoPadding (56)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/ECB/PKCS5Padding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/ECB/PKCS5Padding (56)")
    public void DES_EBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/ECB/PKCS5Padding (56)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }
*/
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/CBC/NoPadding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/CBC/NoPadding (168)")
    @BenchmarkTag(tag = "ee561641-9184-43c5-9f1c-2441f32a82da")
    public void DESede_CBS_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/CBC/NoPadding (168)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/CBC/PKCS5Padding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/CBC/PKCS5Padding (168)")
    @BenchmarkTag(tag = "90c7ad7e-7d91-49cb-a274-f63250014433")
    public void DESede_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/CBC/PKCS5Padding (168)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/ECB/NoPadding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/ECB/NoPadding (168)")
    @BenchmarkTag(tag = "af9ab795-f20e-4590-ab75-813f9f8065e7")
    public void DESede_ECB_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/ECB/NoPadding (168)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/ECB/PKCS5Padding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/ECB/PKCS5Padding (168)")
    @BenchmarkTag(tag = "db8c2e01-183e-4dab-8b0a-307c535e5b72")
    public void DESede_ECB_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/ECB/PKCS5Padding (168)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }
}
