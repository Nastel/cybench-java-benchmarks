package com.gocypher.cybench.jmh.jvm.client.tests.encrypt;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.BouncyCastleLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class BouncyCastleEncrypt extends EncryptBenchmarks implements BouncyCastleLibDefinition {

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/CBC/NoPadding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/CBC/NoPadding (128)")
    @BenchmarkTag(tag = "bc693abb-a73a-48e9-85a6-aa4b247beedd")
    public void AES_CBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/CBC/NoPadding (128)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/CBC/PKCS5Padding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/CBC/PKCS5Padding (128)")
    @BenchmarkTag(tag = "f4de6ab1-624b-4333-b1b2-2fa5b673666d")
    public void AES_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/CBC/PKCS5Padding (128)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/ECB/NoPadding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/ECB/NoPadding (128)")
    @BenchmarkTag(tag = "4f0b8d45-18a9-4409-be1e-059de8ae119c")
    public void AES_ECB_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/ECB/NoPadding (128)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using AES/ECB/PKCS5Padding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/ECB/PKCS5Padding (128)")
    @BenchmarkTag(tag = "75757a8a-9f69-43c8-9bd9-4419f4d4a29a")
    public void AES_ECB_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/ECB/PKCS5Padding (128)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/CBC/NoPadding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/CBC/NoPadding (56)")
    @BenchmarkTag(tag = "495e30c5-4b31-4d18-a7be-6d34acc74470")
    public void DES_CBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/CBC/NoPadding (56)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/CBC/PKCS5Padding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/CBC/PKCS5Padding (56)")
    @BenchmarkTag(tag = "e933ceff-7713-4a37-a503-967ba2d434c8")
    public void DES_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/CBC/PKCS5Padding (56)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/ECB/NoPadding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/ECB/NoPadding (56)")
    @BenchmarkTag(tag = "28877dd6-e2ba-42d6-9025-5250cc601f03")
    public void DES_EBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/ECB/NoPadding (56)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DES/ECB/PKCS5Padding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/ECB/PKCS5Padding (56)")
    @BenchmarkTag(tag = "c527ccbe-3819-420e-8d2c-3e5ae7f222d5")
    public void DES_EBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/ECB/PKCS5Padding (56)");
        encrypt(bh, cipherDescription, "BC", testString);
    }
*/
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/CBC/NoPadding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/CBC/NoPadding (168)")
    @BenchmarkTag(tag = "ede95980-8b8e-4848-b0ff-6eca08f6ebfb")
    public void DESede_CBS_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/CBC/NoPadding (168)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/CBC/PKCS5Padding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/CBC/PKCS5Padding (168)")
    @BenchmarkTag(tag = "253780bf-4666-4c55-833e-df503a62cb2b")
    public void DESede_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/CBC/PKCS5Padding (168)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/ECB/NoPadding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/ECB/NoPadding (168)")
    @BenchmarkTag(tag = "2d53dde6-7a63-4517-9328-b80d932d0f75")
    public void DESede_ECB_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/ECB/NoPadding (168)");
        encrypt(bh, cipherDescription, "BC", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Encrypt using DESede/ECB/PKCS5Padding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/ECB/PKCS5Padding (168)")
    @BenchmarkTag(tag = "fc3fa95e-198b-429c-9304-fc40a946ff04")
    public void DESede_ECB_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/ECB/PKCS5Padding (168)");
        encrypt(bh, cipherDescription, "BC", testString);
    }
}
