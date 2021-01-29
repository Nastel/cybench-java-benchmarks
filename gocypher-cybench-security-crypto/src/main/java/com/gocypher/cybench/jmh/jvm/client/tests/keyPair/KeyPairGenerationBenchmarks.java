package com.gocypher.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

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

    protected KeyPair generateKey(String provider, String algorithm, int size) {
        try {
            KeyPairGenerator keyPairGeneratorRSA = KeyPairGenerator.getInstance(algorithm, provider);
            keyPairGeneratorRSA.initialize(size);
            return keyPairGeneratorRSA.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
