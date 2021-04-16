package io.cybench.jmh.jvm.client.tests.key;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import io.cybench.jmh.jvm.client.tests.definitions.BouncyCastleLibDefinition;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class BouncyCastleKey extends KeyGenerationBenchmarks implements BouncyCastleLibDefinition
{

    /*@Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key AES (128)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "AES")
    @BenchmarkMetaData(key = "keySize", value = "128")
    public void generateAES_BC_128(Blackhole bh) {
        bh.consume(generateKey("BC", "AES", 128));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key AES (192)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "AES")
    @BenchmarkMetaData(key = "keySize", value = "192")
    public void generateAES_BC_192(Blackhole bh) {
        bh.consume(generateKey("BC", "AES", 192));
    }
    */
    // /**
    // * AES is a symmetric key cipher. This means the same secret key is used for both encryption and decryption,
    // * and both the sender and receiver of the data need a copy of the key. By contrast, asymmetric key systems
    // * use a different key for each of the two processes. Asymmetric keys are best for external file transfers,
    // * whereas symmetric keys are better suited to internal encryption. The advantage of symmetric systems like
    // * AES is their speed. Because a symmetric key algorithm requires less computational power than an asymmetric
    // * one, its faster and more efficient to run.
    // * <p>
    // * AES is also characterized as a block cipher. In this type of cipher, the information to be encrypted
    // * (known as plaintext) is divided into sections called blocks. AES uses a 128-bit block size, in which data
    // * is divided into a four-by-four array containing 16 bytes. Since there are eight bits per byte, the total in
    // * each block is 128 bits. The size of the encrypted data remains the same: 128 bits of plaintext yields 128
    // * bits of ciphertext.
    // *
    // * @see <a href="https://www.solarwindsmsp.com/blog/aes-256-encryption-algorithm">Source</a>
    // */
    // @Benchmark
    // @BenchmarkMetaData(key = "title", value = "Generation of symmetric key AES (256)")
    // @BenchmarkMetaData(key = "keyAlgorithm", value = "AES")
    // @BenchmarkMetaData(key = "keySize", value = "256")
    // public void generateAES_BC_256(Blackhole bh) {
    // bh.consume(generateKey("BC", "AES", 256));
    // }
    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key DES (56)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DES")
    @BenchmarkMetaData(key = "keySize", value = "56")
    public void generateDES_BC_56(Blackhole bh) {
        bh.consume(generateKey("BC", "DES", 56));
    }

    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key DESEDE (112)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "keySize", value = "112")
    public void generateDESEDE_BC_112(Blackhole bh) {
        bh.consume(generateKey("BC", "DESEDE", 112));
    }
    */
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key DESEDE (168)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "DESEDE")
    @BenchmarkMetaData(key = "keySize", value = "168")
    @BenchmarkTag(tag = "17c934d9-11f2-4be8-9b29-aee79cfbe0b1")
    public void generateDESEDE_BC_168(Blackhole bh) {
        bh.consume(generateKey("BC", "DESEDE", 168));
    }

    /*
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key RC2 (40)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RC2")
    @BenchmarkMetaData(key = "keySize", value = "40")
    public void generateRC2_BC_40(Blackhole bh) {
        bh.consume(generateKey("BC", "RC2", 40));
    }
    */
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Generation of symmetric key RC2 (128)")
    @BenchmarkMetaData(key = "keyAlgorithm", value = "RC2")
    @BenchmarkMetaData(key = "keySize", value = "128")
    @BenchmarkTag(tag = "fafe6032-e166-426d-b116-e2b67378f194")
    public void generateRC2_BC_128(Blackhole bh) {
        bh.consume(generateKey("BC", "RC2", 128));
    }
}
