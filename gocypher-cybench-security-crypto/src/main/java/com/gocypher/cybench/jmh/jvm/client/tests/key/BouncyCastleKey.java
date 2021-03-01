package com.gocypher.cybench.jmh.jvm.client.tests.key;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.client.tests.definitions.BouncyCastleLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

public class BouncyCastleKey extends KeyGenerationBenchmarks implements BouncyCastleLibDefinition {

    /*@Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key AES (128)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "AES")
    @BenchmarkMetaData(key = "keySize", value = "128")
    @BenchmarkTag(tag = "6ac49e4f-3f8e-401b-96c9-2e701ef62890")
    public void generateAES_BC_128(Blackhole bh) {
        bh.consume(generateKey("BC", "AES", 128));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key AES (192)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "AES")
    @BenchmarkMetaData(key = "keySize", value = "192")
    @BenchmarkTag(tag = "bd8855d1-18f5-4247-a05f-6d39f9d1e653")
    public void generateAES_BC_192(Blackhole bh) {
        bh.consume(generateKey("BC", "AES", 192));
    }
    */

//    /**
//     * AES is a symmetric key cipher. This means the same secret key is used for both encryption and decryption,
//     * and both the sender and receiver of the data need a copy of the key. By contrast, asymmetric key systems
//     * use a different key for each of the two processes. Asymmetric keys are best for external file transfers,
//     * whereas symmetric keys are better suited to internal encryption. The advantage of symmetric systems like
//     * AES is their speed. Because a symmetric key algorithm requires less computational power than an asymmetric
//     * one, it’s faster and more efficient to run.
//     * <p>
//     * AES is also characterized as a block cipher. In this type of cipher, the information to be encrypted
//     * (known as plaintext) is divided into sections called blocks. AES uses a 128-bit block size, in which data
//     * is divided into a four-by-four array containing 16 bytes. Since there are eight bits per byte, the total in
//     * each block is 128 bits. The size of the encrypted data remains the same: 128 bits of plaintext yields 128
//     * bits of ciphertext.
//     *
//     * @see <a href="https://www.solarwindsmsp.com/blog/aes-256-encryption-algorithm">Source</a>
//     */
//    @Benchmark
//    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key AES (256)")
//    @BenchmarkMetaData(key = "keyAlgorithm", value = "AES")
//    @BenchmarkMetaData(key = "keySize", value = "256")
//    @BenchmarkTag(tag = "4d294ae1-0c7f-41b0-b5a4-8e110571b78a")
//    public void generateAES_BC_256(Blackhole bh) {
//        bh.consume(generateKey("BC", "AES", 256));
//    }

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key DES (56)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DES")
    @BenchmarkMetaData(key = "keySize", value = "56")
    @BenchmarkTag(tag = "aaf66887-0793-4460-8258-04428ddaf434")
    public void generateDES_BC_56(Blackhole bh) {
        bh.consume(generateKey("BC", "DES", 56));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key DESEDE (112)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "keySize", value = "112")
    @BenchmarkTag(tag = "50a2d237-06a7-4c4a-9c8f-840f5c91fa99")
    public void generateDESEDE_BC_112(Blackhole bh) {
        bh.consume(generateKey("BC", "DESEDE", 112));
    }
    */

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key DESEDE (168)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "keySize", value = "168")
    @BenchmarkTag(tag = "b55acc52-3c95-431e-bd72-d7f3ead3de2c")
    @BenchmarkMetaData(key = "api", value = "BouncyCastle_")
    public void generateDESEDE_BC_168(Blackhole bh) {
        bh.consume(generateKey("BC", "DESEDE", 168));
    }

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key RC2 (40)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RC2")
    @BenchmarkMetaData(key = "keySize", value = "40")
    @BenchmarkTag(tag = "86cd39f7-7e6b-4f77-a83e-4950c138c4b4")
    public void generateRC2_BC_40(Blackhole bh) {
        bh.consume(generateKey("BC", "RC2", 40));
    }
    */

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key RC2 (128)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RC2")
    @BenchmarkMetaData(key = "keySize", value = "128")
    @BenchmarkTag(tag = "d94a8681-7790-4c78-ab22-f58ff6a14c59")
    @BenchmarkMetaData(key = "api", value = "BouncyCastle_")
    public void generateRC2_BC_128(Blackhole bh) {
        bh.consume(generateKey("BC", "RC2", 128));
    }

}
