package com.gocypher.cybench.jmh.jvm.client.tests.key;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

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

    @Setup
    public void setup() {
        Security.addProvider(new BouncyCastleProvider());
    }

    protected SecretKey generateKey(String provider, String keyType, Integer keySize) {
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

