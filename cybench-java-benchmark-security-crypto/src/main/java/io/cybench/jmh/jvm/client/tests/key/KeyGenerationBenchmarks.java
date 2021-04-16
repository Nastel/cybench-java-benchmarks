package io.cybench.jmh.jvm.client.tests.key;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import io.cybench.jmh.jvm.utils.CyBenchCounters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.*;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Security;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "context", value = "security_crypto")
@BenchmarkMetaData(key = "actionName", value = "generateKey")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "description", value = "Symmetric key generation benchmarks.")
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

    @TearDown(Level.Iteration)
    public void clearIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
    }

}

