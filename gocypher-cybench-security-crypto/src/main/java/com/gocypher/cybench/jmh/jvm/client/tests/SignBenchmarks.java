package com.gocypher.cybench.jmh.jvm.client.tests;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.security.*;
import java.util.HashMap;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "actionName", value = "sign")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "true")
@BenchmarkMetaData(key = "context", value = "JAVA security")
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "description", value = "Signing with various algorithms")
public class SignBenchmarks {

    private String textToSign = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris porttitor elementum ipsum ut porta. Mauris aliquam risus in justo maximus, eget sodales metus euismod. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed at eros ultrices, blandit enim at, venenatis augue. Mauris at suscipit metus, sed bibendum sem. Sed nec commodo orci, aliquam ullamcorper est. Ut in dapibus felis, vitae semper elit. Integer fermentum, dui ut lacinia semper, odio nulla aliquet elit, et pellentesque erat sapien id risus. Sed eu vulputate nulla, a pharetra odio. Aliquam id finibus est. Proin aliquam mattis eros, sed viverra sem rhoncus eget. Nunc suscipit molestie sem, sit amet ultrices libero. Donec sed urna eget nibh porttitor sagittis. Nam ac tempor arcu.\n" + "\n" + "Nunc pretium dictum nibh, sed tristique nulla. Donec cursus lacus dictum urna consequat ultricies. Nunc eu venenatis justo. Aliquam erat volutpat. Mauris consequat varius velit at feugiat. Aenean eleifend mauris nec turpis euismod pharetra. Phasellus ullamcorper bibendum mauris sit amet dapibus. Nulla convallis scelerisque urna, at euismod tellus sodales quis. Duis id metus orci. Vivamus commodo massa vel lacus ornare, id venenatis est varius. Donec eu arcu felis. Nullam sapien sapien, porta tempus mattis at, bibendum sit amet orci. Vestibulum consectetur eleifend hendrerit. Donec egestas lobortis maximus. Proin viverra, turpis non aliquam elementum, libero orci gravida metus, nec ultrices metus nisl in risus. Nullam sed facilisis quam.\n" + "\n" + "Mauris quis lectus ac diam porttitor scelerisque id consectetur urna. Proin quis mauris nunc. Cras euismod dolor turpis, at auctor leo sollicitudin fermentum. Praesent nec malesuada metus. Cras semper volutpat metus, sit amet auctor neque fermentum sed. Suspendisse tristique nulla a cursus tempus. Ut efficitur ante ac quam venenatis, quis pellentesque lectus consequat. Praesent condimentum id velit quis ornare. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Mauris eget dui risus. Nulla tristique neque eu tortor laoreet, sed lobortis metus mattis. Cras volutpat porttitor ligula sed accumsan. Suspendisse non ligula vitae orci consectetur scelerisque nec at magna. Aliquam vel accumsan massa, non egestas eros. Donec nibh sapien, auctor sed nulla eu, ornare finibus tortor.\n" + "\n" + "Vivamus quis neque at eros cursus dapibus. Pellentesque diam lacus, elementum non tortor at, semper hendrerit nunc. Aliquam erat volutpat. Vestibulum ac tellus augue. Etiam dui enim, auctor non ante ut, posuere congue dolor. Sed egestas scelerisque velit, at bibendum felis pretium sit amet. Proin mattis elit turpis, id mattis ante lacinia vitae. Sed volutpat eget tellus iaculis condimentum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer pretium ipsum augue, eget iaculis leo vulputate in. Quisque auctor sem eget arcu vestibulum finibus. Fusce fringilla malesuada ex in iaculis. Cras lacinia ac elit quis sodales. Cras vel urna augue. Cras vehicula mi vel sem ultricies pharetra.\n" + "\n" + "Vestibulum sollicitudin elit sit amet dui pharetra, nec eleifend felis scelerisque. Praesent pretium ipsum nec lacus volutpat, non tristique ex ultricies. Praesent quis ultrices sem. Mauris rutrum lectus libero, sed varius nunc pulvinar at. In at tellus nec ligula consequat feugiat. Morbi convallis, metus non tincidunt molestie, lacus nisl pellentesque nulla, luctus mattis justo nulla sit amet libero. In ultrices enim ligula, a venenatis urna malesuada non. In at eros condimentum, porttitor diam sed, egestas arcu. Praesent ultricies elit ut lacus interdum, nec sodales odio commodo. Nunc vulputate enim diam, vitae laoreet purus auctor eu. Etiam pulvinar at dolor id pretium. Praesent vel sapien eu ligula congue interdum. Aenean tristique venenatis urna eu imperdiet. Sed convallis sollicitudin lacus non semper. Proin dictum, tortor sit amet tempor convallis, turpis erat mattis eros, et vehicula dui leo at libero. Curabitur a imperdiet nunc. Aliquam a erat nibh. Nam bibendum massa libero. Suspendissae quisi diamus lacus.";

    private SecureRandom secureRandom = new SecureRandom();

    private HashMap<String, KeyPair> keyVallet = new HashMap<>();

    @Setup
    public void setup() throws NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator keyPairGeneratorRSA = KeyPairGenerator.getInstance("RSA");
        keyPairGeneratorRSA.initialize(4096);
        keyVallet.put("RSA", keyPairGeneratorRSA.generateKeyPair());
        KeyPairGenerator keyPairGeneratorWin = KeyPairGenerator.getInstance("RSA", "SunMSCAPI");
        keyPairGeneratorWin.initialize(4096);
        keyVallet.put("RSA_W", keyPairGeneratorWin.generateKeyPair());
        KeyPairGenerator keyPairGeneratorEC_W = KeyPairGenerator.getInstance("EC", "SunEC");
        keyVallet.put("EC", keyPairGeneratorEC_W.generateKeyPair());
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "MD2")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "da6337ac-c563-417d-bb59-9a3882bd3d2e")
    public void BC_MD2withRSA(Blackhole bh) {
        bh.consume(sign("MD2WITHRSA", "BC", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJSSE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "MD2")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "677aa315-c2cc-48b9-aaca-c5268997d67a")
    public void SunJSSE_MD2withRSA(Blackhole bh) {
        bh.consume(sign("MD2WITHRSA", "SunJSSE", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunMSCAPI")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "MD2")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "1e7879ab-1c52-4d60-ab4c-9af8924198dc")
    public void SunMSCAPI_MD2withRSA(Blackhole bh) {
        bh.consume(sign("MD2WITHRSA", "SunMSCAPI", "RSA_W", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunRsaSign")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "MD2")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "3235d778-b4d1-425b-9e60-c154cb1bd015")
    public void SunRsaSign_MD2withRSA(Blackhole bh) {
        bh.consume(sign("MD2WITHRSA", "SunRsaSign", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "MD5")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "4857d113-2bdf-437c-b538-200f1ad7dac2")
    public void BC_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5WITHRSA", "BC", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJSSE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "MD5")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "3167bec7-b6d5-4d9f-9742-4f576b178a03")
    public void SunJSSE_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5withRSA", "SunJSSE", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunMSCAPI")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "MD5")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "444b9dd4-521c-4670-974b-08f117ede922")
    public void SunMSCAPI_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5withRSA", "SunMSCAPI", "RSA_W", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunRsaSign")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "MD5")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "2128e15a-974f-4a01-99b9-a499279a9dbd")
    public void SunRsaSign_MD5withRSA(Blackhole bh) {
        bh.consume(sign("MD5withRSA", "SunRsaSign", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "SHA1")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "4e0fcba7-798c-4f26-97b3-57a0c8ecb1bd")
    public void BC_SHA1withRSA(Blackhole bh) {
        bh.consume(sign("SHA1WITHRSA", "BC", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJSSE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA1")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "1cf3a5a7-3b60-4873-af52-741f547334bd")
    public void SunJSSE_SHA1withRSA(Blackhole bh) {
        bh.consume(sign("SHA1withRSA", "SunJSSE", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunMSCAPI")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA1")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "f837a643-38b8-472e-85a7-81e01e4eba6e")
    public void SunMSCAPI_SHA1withRSA(Blackhole bh) {
        bh.consume(sign("SHA1withRSA", "SunMSCAPI", "RSA_W", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunRsaSign")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA1")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "444379f0-dd48-44c5-9cac-75695f45b76b")
    public void SunRsaSign_SHA1withRSA(Blackhole bh) {
        bh.consume(sign("SHA1withRSA", "SunRsaSign", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "SHA384")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "36fa169e-7025-41a8-9afa-8f34d019fce4")
    public void BC_SHA384withRSA(Blackhole bh) {
        bh.consume(sign("SHA384withRSA", "BC", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJSSE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA384")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "f7271b7f-b7a1-4099-802d-ab9aaf89c2d0")
    public void SunJSSE_SHA384withRSA(Blackhole bh) {
        bh.consume(sign("SHA384withRSA", "SunJSSE", "RSA", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunMSCAPI")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA384")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "c209a190-c6b6-4ef0-8fd5-14ef5384e736")
    public void SunMSCAPI_SHA384withRSA(Blackhole bh) {
        bh.consume(sign("SHA384withRSA", "SunMSCAPI", "RSA_W", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunRsaSign")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA384")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "2d10ee15-1d5f-4902-9981-9cb3b3d1d3ad")
    public void SunRsaSign_SHA384withRSA(Blackhole bh) {
        bh.consume(sign("SHA384withRSA", "SunRsaSign", "RSA", textToSign));
    }

    // ------------
    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "SHA224")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "06209768-d5a2-4ea2-b2f8-b69db2f0b7af")
    public void BC_SHA224withECDSA(Blackhole bh) {
        bh.consume(sign("SHA224withECDSA", "BC", "EC", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA224")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "2bfff1bd-6681-4c8f-af91-ce972495c869")
    public void SunEC_SHA224withECDSA(Blackhole bh) {
        bh.consume(sign("SHA224withECDSA", "SunEC", "EC", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "SHA256")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "fd2da744-afbe-4d22-8dbf-022809a4ddeb")
    public void BC_SHA256withECDSA(Blackhole bh) {
        bh.consume(sign("SHA256withECDSA", "BC", "EC", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA256")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "9f3e2339-c82e-45a9-8388-f3c74cf4fb81")
    public void SunJSSE_SHA256withECDSA(Blackhole bh) {
        bh.consume(sign("SHA256withECDSA", "SunEC", "EC", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "SHA512")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "8810185b-4eb7-4272-aba8-1a1d30a8a67d")
    public void BC_SHA512withECDSA(Blackhole bh) {
        bh.consume(sign("SHA512withECDSA", "BC", "EC", textToSign));
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA512")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "3da80786-b88a-4126-905a-72f41a2e01df")
    public void SunJSSE_SHA512withECDSA(Blackhole bh) {
        bh.consume(sign("SHA512withECDSA", "SunEC", "EC", textToSign));
    }

    private boolean sign(String alg, String provider, String key, String text) {
        try {
            Signature signature = Signature.getInstance(alg, provider);
            KeyPair keyPair = keyVallet.get(key);
            signature.initSign(keyPair.getPrivate(), secureRandom);
            byte[] data = text.getBytes("UTF-8");
            signature.update(data);
            byte[] digitalSignature = signature.sign();
            PublicKey publicKey = keyPair.getPublic();
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(digitalSignature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
