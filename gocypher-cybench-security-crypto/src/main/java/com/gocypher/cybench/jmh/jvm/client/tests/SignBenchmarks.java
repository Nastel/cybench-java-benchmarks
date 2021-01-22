package com.gocypher.cybench.jmh.jvm.client.tests;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "actionName", value = "sign")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "true")
@BenchmarkMetaData(key = "context", value = "JAVA security")
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "description", value = "Signing with various algorithms")
public class SignBenchmarks {

    @Param({"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris porttitor elementum ipsum ut porta. Mauris aliquam risus in justo maximus, eget sodales metus euismod. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed at eros ultrices, blandit enim at, venenatis augue. Mauris at suscipit metus, sed bibendum sem. Sed nec commodo orci, aliquam ullamcorper est. Ut in dapibus felis, vitae semper elit. Integer fermentum, dui ut lacinia semper, odio nulla aliquet elit, et pellentesque erat sapien id risus. Sed eu vulputate nulla, a pharetra odio. Aliquam id finibus est. Proin aliquam mattis eros, sed viverra sem rhoncus eget. Nunc suscipit molestie sem, sit amet ultrices libero. Donec sed urna eget nibh porttitor sagittis. Nam ac tempor arcu.\n" + "\n" + "Nunc pretium dictum nibh, sed tristique nulla. Donec cursus lacus dictum urna consequat ultricies. Nunc eu venenatis justo. Aliquam erat volutpat. Mauris consequat varius velit at feugiat. Aenean eleifend mauris nec turpis euismod pharetra. Phasellus ullamcorper bibendum mauris sit amet dapibus. Nulla convallis scelerisque urna, at euismod tellus sodales quis. Duis id metus orci. Vivamus commodo massa vel lacus ornare, id venenatis est varius. Donec eu arcu felis. Nullam sapien sapien, porta tempus mattis at, bibendum sit amet orci. Vestibulum consectetur eleifend hendrerit. Donec egestas lobortis maximus. Proin viverra, turpis non aliquam elementum, libero orci gravida metus, nec ultrices metus nisl in risus. Nullam sed facilisis quam.\n" + "\n" + "Mauris quis lectus ac diam porttitor scelerisque id consectetur urna. Proin quis mauris nunc. Cras euismod dolor turpis, at auctor leo sollicitudin fermentum. Praesent nec malesuada metus. Cras semper volutpat metus, sit amet auctor neque fermentum sed. Suspendisse tristique nulla a cursus tempus. Ut efficitur ante ac quam venenatis, quis pellentesque lectus consequat. Praesent condimentum id velit quis ornare. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Mauris eget dui risus. Nulla tristique neque eu tortor laoreet, sed lobortis metus mattis. Cras volutpat porttitor ligula sed accumsan. Suspendisse non ligula vitae orci consectetur scelerisque nec at magna. Aliquam vel accumsan massa, non egestas eros. Donec nibh sapien, auctor sed nulla eu, ornare finibus tortor.\n" + "\n" + "Vivamus quis neque at eros cursus dapibus. Pellentesque diam lacus, elementum non tortor at, semper hendrerit nunc. Aliquam erat volutpat. Vestibulum ac tellus augue. Etiam dui enim, auctor non ante ut, posuere congue dolor. Sed egestas scelerisque velit, at bibendum felis pretium sit amet. Proin mattis elit turpis, id mattis ante lacinia vitae. Sed volutpat eget tellus iaculis condimentum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer pretium ipsum augue, eget iaculis leo vulputate in. Quisque auctor sem eget arcu vestibulum finibus. Fusce fringilla malesuada ex in iaculis. Cras lacinia ac elit quis sodales. Cras vel urna augue. Cras vehicula mi vel sem ultricies pharetra.\n" + "\n" + "Vestibulum sollicitudin elit sit amet dui pharetra, nec eleifend felis scelerisque. Praesent pretium ipsum nec lacus volutpat, non tristique ex ultricies. Praesent quis ultrices sem. Mauris rutrum lectus libero, sed varius nunc pulvinar at. In at tellus nec ligula consequat feugiat. Morbi convallis, metus non tincidunt molestie, lacus nisl pellentesque nulla, luctus mattis justo nulla sit amet libero. In ultrices enim ligula, a venenatis urna malesuada non. In at eros condimentum, porttitor diam sed, egestas arcu. Praesent ultricies elit ut lacus interdum, nec sodales odio commodo. Nunc vulputate enim diam, vitae laoreet purus auctor eu. Etiam pulvinar at dolor id pretium. Praesent vel sapien eu ligula congue interdum. Aenean tristique venenatis urna eu imperdiet. Sed convallis sollicitudin lacus non semper. Proin dictum, tortor sit amet tempor convallis, turpis erat mattis eros, et vehicula dui leo at libero. Curabitur a imperdiet nunc. Aliquam a erat nibh. Nam bibendum massa libero. Suspendissae quisi diamus lacus.", "The quick brown fox jumps over t"})
    private String textToSign = " 123 ";
    private SecureRandom secureRandom = new SecureRandom();

    private HashMap<String, KeyPair> keyVallet = new HashMap<>();

    public static void main(String[] args) throws Exception {
//        Security.addProvider(new BouncyCastleProvider());
//        List<String> algorithms = Arrays.stream(Security.getProviders()).flatMap(provider -> provider.getServices().stream()).filter(service -> "Signature".equals(service.getType())).map(service -> service.getAlgorithm() + " - " + service.getProvider().getName() + "\n").distinct().sorted().collect(Collectors.toList());
//        System.out.println(algorithms);
//        // System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABC");
//        // List<String> algorithmsBC = Arrays.stream(Security.getProviders())
//        // .flatMap(provider -> provider.getServices().stream())
//        // .filter(service -> "Cipher".equals(service.getType()))
//        // .map(service -> service.getAlgorithm() + " - " + service.getProvider().getName()+ "\n")
//        //
//        // .sorted()
//        // .collect(Collectors.toList());
//        // System.out.println(algorithmsBC);
//        // System.out.println(new SignBenchmarks().sign("SHA256WithDSA", "DSA", ));


        Blackhole bh = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");
        SignBenchmarks s = new SignBenchmarks();

        s.setup();

        s.keyVallet.forEach((k,v) -> System.out.println(k + " : " + ((RSAPublicKey)v.getPublic()).getModulus().bitLength()));
        s.BC_MD2withRSA(bh);
        s.SunJSSE_MD2withRSA(bh);
        s.SunRsaSign_MD2withRSA(bh);
        s.SunRsaSign_MD2withRSA(bh);
        s.BC_MD5withRSA(bh);
        s.SunJSSE_MD5withRSA(bh);
        s.SunMSCAPI_MD5withRSA(bh);
        s.SunRsaSign_MD5withRSA(bh);
    }

    @Setup
    public void setup() throws NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());

        KeyPairGenerator keyPairGeneratorRSA = KeyPairGenerator.getInstance("RSA");
      keyPairGeneratorRSA.initialize(4096);
        keyVallet.put("RSA", keyPairGeneratorRSA.generateKeyPair());

        KeyPairGenerator keyPairGeneratorWin = KeyPairGenerator.getInstance("RSA", "SunMSCAPI");
        keyPairGeneratorWin.initialize(4096);

        keyVallet.put("RSA_W", keyPairGeneratorWin.generateKeyPair());

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
