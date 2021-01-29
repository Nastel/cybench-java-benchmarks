package com.gocypher.cybench.jmh.jvm.client.tests.encrypt;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.bouncycastle.jce.provider.BouncyCastleProvider;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "actionName", value = "encrypt")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "true")
@BenchmarkMetaData(key = "context", value = "JAVA security")
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "description", value = "Signing with various algorithms")
public class EncryptBenchmarks {

    @Setup
    public void setup() {
        Security.addProvider(new BouncyCastleProvider());
    }

    protected void encrypt(Blackhole bh, CipherDescription cipherDescription, String provider, String testString) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException {
        String cipherName = cipherDescription.getCipherName();
        String keyType = cipherDescription.getKeyType();
        Integer keySize = cipherDescription.getKeySize();
        Cipher cipher = Cipher.getInstance(cipherName, provider);
        KeyGenerator generator = KeyGenerator.getInstance(keyType);
        generator.init(keySize);
        SecretKey secretKey = generator.generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        bh.consume(cipher.doFinal(testString.getBytes()));
    }

    protected static class CipherDescription {

        private String cipherName;

        private String keyType;

        private Integer keySize;

        public String getCipherName() {
            return cipherName;
        }

        public String getKeyType() {
            return keyType;
        }

        public Integer getKeySize() {
            return keySize;
        }

        public CipherDescription invoke(String descpr) {
            Pattern pattern = Pattern.compile("(.*)(\\/.*\\/.*) \\((\\d*)\\)");
            Matcher matcher = pattern.matcher(descpr);
            if (matcher.matches()) {
                cipherName = matcher.group(1) + matcher.group(2);
                keyType = matcher.group(1);
                keySize = Integer.parseInt(matcher.group(3));
            }
            return this;
        }

        @Override
        public String toString() {
            return "CipherDescription{" + "cipherName='" + cipherName + '\'' + ", keyType='" + keyType + '\'' + ", keySize=" + keySize + '}';
        }
    }
}
