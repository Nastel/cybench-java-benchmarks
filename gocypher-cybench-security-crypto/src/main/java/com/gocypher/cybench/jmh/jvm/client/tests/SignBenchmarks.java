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
    @BenchmarkTag(tag = "146615ad-eeee-41af-91a6-ee54999368d4")
    public void BC_MD2withRSA(Blackhole bh) {
        sign("MD2WITHRSA", "BC", "RSA", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJSSE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "MD2")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "8094460b-c001-47d2-b1dc-fa03e76fc168")
    public void SunJSSE_MD2withRSA(Blackhole bh) {
        sign("MD2WITHRSA", "SunJSSE", "RSA", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunMSCAPI")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "MD2")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "06f56afd-001d-442c-81cf-cabc491aed12")
    public void SunMSCAPI_MD2withRSA(Blackhole bh) {
        sign("MD2WITHRSA", "SunMSCAPI", "RSA_W", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunRsaSign")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "MD2")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "5510b55c-dae9-485c-a605-6b9536d67d53")
    public void SunRsaSign_MD2withRSA(Blackhole bh) {
        sign("MD2WITHRSA", "SunRsaSign", "RSA", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "MD5")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "146615ad-eeee-41af-91a6-ee54999368d4")
    public void BC_MD5withRSA(Blackhole bh) {
        sign("MD5WITHRSA", "BC", "RSA", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJSSE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "MD5")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "8094460b-c001-47d2-b1dc-fa03e76fc168")
    public void SunJSSE_MD5withRSA(Blackhole bh) {
        sign("MD5withRSA", "SunJSSE", "RSA", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunMSCAPI")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "MD5")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "06f56afd-001d-442c-81cf-cabc491aed12")
    public void SunMSCAPI_MD5withRSA(Blackhole bh) {
        sign("MD5withRSA", "SunMSCAPI", "RSA_W", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunRsaSign")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "MD5")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "5510b55c-dae9-485c-a605-6b9536d67d53")
    public void SunRsaSign_MD5withRSA(Blackhole bh) {
        sign("MD5withRSA", "SunRsaSign", "RSA", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "SHA1")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "a69ff42a-2a5a-4590-b5ce-61404ac0a4a8")
    public void BC_SHA1withRSA(Blackhole bh) {
        sign("SHA1WITHRSA", "BC", "RSA", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJSSE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA1")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "476078fb-c3fe-4f4c-8c80-696ae94af433")
    public void SunJSSE_SHA1withRSA(Blackhole bh) {
        sign("SHA1withRSA", "SunJSSE", "RSA", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunMSCAPI")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA1")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "2cef7355-9553-4a9d-b5fc-097d7fd98563")
    public void SunMSCAPI_SHA1withRSA(Blackhole bh) {
        sign("SHA1withRSA", "SunMSCAPI", "RSA_W", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunRsaSign")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA1")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "5510b55c-dae9-485c-a605-6b9536d67d53")
    public void SunRsaSign_SHA1withRSA(Blackhole bh) {
        sign("SHA1withRSA", "SunRsaSign", "RSA", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "SHA384")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "7892b080-361c-4069-b12a-1071adbe7f87")
    public void BC_SHA384withRSA(Blackhole bh) {
        sign("SHA384withRSA", "BC", "RSA", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunJSSE")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA384")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "8de2c207-8ae9-4705-ac2d-2dc14f620f82")
    public void SunJSSE_SHA384withRSA(Blackhole bh) {
        sign("SHA384withRSA", "SunJSSE", "RSA", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunMSCAPI")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA384")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "833ea8f9-0dea-42c4-a0c0-d241b08c2e58")
    public void SunMSCAPI_SHA384withRSA(Blackhole bh) {
        sign("SHA384withRSA", "SunMSCAPI", "RSA_W", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunRsaSign")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA384")
    @BenchmarkMetaData(key = "key", value = "RSA")
    @BenchmarkMetaData(key = "size", value = "2048")
    @BenchmarkTag(tag = "5510b55c-dae9-485c-a605-6b9536d67d53")
    public void SunRsaSign_SHA384withRSA(Blackhole bh) {
        sign("SHA384withRSA", "SunRsaSign", "RSA", textToSign);
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
    @BenchmarkTag(tag = "5e831d4c-45bb-4bcf-bc9a-bc9e13de43ec")
    public void BC_SHA224withECDSA(Blackhole bh) {
        sign("SHA224withECDSA", "BC", "EC", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA224")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "ba4f9f76-b545-42c8-88d8-353ca82385e3")
    public void SunEC_SHA224withECDSA(Blackhole bh) {
        sign("SHA224withECDSA", "SunEC", "EC", textToSign);
    }


    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "SHA256")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "23f1dc69-fc4c-484e-a6ad-3aec424be723")
    public void BC_SHA256withECDSA(Blackhole bh) {
        sign("SHA256withECDSA", "BC", "EC", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA256")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "56cd4769-68e2-46af-a82a-b484af2e8b10")
    public void SunJSSE_SHA256withECDSA(Blackhole bh) {
        sign("SHA256withECDSA", "SunEC", "EC", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "BouncyCastle")
    @BenchmarkMetaData(key = "libVendor", value = "org.bouncycastle")
    @BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
    @BenchmarkMetaData(key = "libVersion", value = "1.58")
    @BenchmarkMetaData(key = "algorithm", value = "SHA512")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "f875749b-7a67-4e62-982a-511046b4aa91")
    public void BC_SHA512withECDSA(Blackhole bh) {
        sign("SHA512withECDSA", "BC", "EC", textToSign);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "SunEC")
    @BenchmarkMetaData(key = "libVendor", value = "com.sun")
    @BenchmarkMetaData(key = "libDescription", value = "The SunEC provider implements Elliptical Curve Cryptography (ECC). ECC is emerging as an attractive public-key cryptosystem for mobile/wireless and other environments. Compared to traditional cryptosystems like RSA, ECC offers equivalent security with smaller key sizes, which results in faster computations, lower power consumption, as well as memory and bandwidth savings.")
    @BenchmarkMetaData(key = "libVersion", value = "JAVA_VERSION")
    @BenchmarkMetaData(key = "algorithm", value = "SHA512")
    @BenchmarkMetaData(key = "key", value = "EC")
    @BenchmarkMetaData(key = "size", value = "512")
    @BenchmarkTag(tag = "451fd17a-01e6-4b80-8f8e-f8f98e4deb78")
    public void SunJSSE_SHA512withECDSA(Blackhole bh) {
        sign("SHA512withECDSA", "SunEC", "EC", textToSign);
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
