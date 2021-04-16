package io.cybench.jmh.jvm.client.tests.keyPair;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import io.cybench.jmh.jvm.utils.CyBenchCounters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.*;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "context", value = "security_RSA")
@BenchmarkMetaData(key = "actionName", value = "generateKeyPair")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "description", value = "Asymmetric key pair generation benchmarks.")
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
    @TearDown(Level.Iteration)
    public void clearIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
    }

}
