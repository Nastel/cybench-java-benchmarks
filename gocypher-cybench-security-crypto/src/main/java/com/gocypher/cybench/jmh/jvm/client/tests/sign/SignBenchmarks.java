package com.gocypher.cybench.jmh.jvm.client.tests.sign;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.*;

import java.security.*;
import java.util.HashMap;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "actionName", value = "sign")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "true")
@BenchmarkMetaData(key = "context", value = "JAVA security")
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "description", value = "Signing with various algorithms")
public class SignBenchmarks  {

    String textToSign = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris porttitor elementum ipsum ut porta. Mauris aliquam risus in justo maximus, eget sodales metus euismod. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed at eros ultrices, blandit enim at, venenatis augue. Mauris at suscipit metus, sed bibendum sem. Sed nec commodo orci, aliquam ullamcorper est. Ut in dapibus felis, vitae semper elit. Integer fermentum, dui ut lacinia semper, odio nulla aliquet elit, et pellentesque erat sapien id risus. Sed eu vulputate nulla, a pharetra odio. Aliquam id finibus est. Proin aliquam mattis eros, sed viverra sem rhoncus eget. Nunc suscipit molestie sem, sit amet ultrices libero. Donec sed urna eget nibh porttitor sagittis. Nam ac tempor arcu.\n" + "\n" + "Nunc pretium dictum nibh, sed tristique nulla. Donec cursus lacus dictum urna consequat ultricies. Nunc eu venenatis justo. Aliquam erat volutpat. Mauris consequat varius velit at feugiat. Aenean eleifend mauris nec turpis euismod pharetra. Phasellus ullamcorper bibendum mauris sit amet dapibus. Nulla convallis scelerisque urna, at euismod tellus sodales quis. Duis id metus orci. Vivamus commodo massa vel lacus ornare, id venenatis est varius. Donec eu arcu felis. Nullam sapien sapien, porta tempus mattis at, bibendum sit amet orci. Vestibulum consectetur eleifend hendrerit. Donec egestas lobortis maximus. Proin viverra, turpis non aliquam elementum, libero orci gravida metus, nec ultrices metus nisl in risus. Nullam sed facilisis quam.\n" + "\n" + "Mauris quis lectus ac diam porttitor scelerisque id consectetur urna. Proin quis mauris nunc. Cras euismod dolor turpis, at auctor leo sollicitudin fermentum. Praesent nec malesuada metus. Cras semper volutpat metus, sit amet auctor neque fermentum sed. Suspendisse tristique nulla a cursus tempus. Ut efficitur ante ac quam venenatis, quis pellentesque lectus consequat. Praesent condimentum id velit quis ornare. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Mauris eget dui risus. Nulla tristique neque eu tortor laoreet, sed lobortis metus mattis. Cras volutpat porttitor ligula sed accumsan. Suspendisse non ligula vitae orci consectetur scelerisque nec at magna. Aliquam vel accumsan massa, non egestas eros. Donec nibh sapien, auctor sed nulla eu, ornare finibus tortor.\n" + "\n" + "Vivamus quis neque at eros cursus dapibus. Pellentesque diam lacus, elementum non tortor at, semper hendrerit nunc. Aliquam erat volutpat. Vestibulum ac tellus augue. Etiam dui enim, auctor non ante ut, posuere congue dolor. Sed egestas scelerisque velit, at bibendum felis pretium sit amet. Proin mattis elit turpis, id mattis ante lacinia vitae. Sed volutpat eget tellus iaculis condimentum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer pretium ipsum augue, eget iaculis leo vulputate in. Quisque auctor sem eget arcu vestibulum finibus. Fusce fringilla malesuada ex in iaculis. Cras lacinia ac elit quis sodales. Cras vel urna augue. Cras vehicula mi vel sem ultricies pharetra.\n" + "\n" + "Vestibulum sollicitudin elit sit amet dui pharetra, nec eleifend felis scelerisque. Praesent pretium ipsum nec lacus volutpat, non tristique ex ultricies. Praesent quis ultrices sem. Mauris rutrum lectus libero, sed varius nunc pulvinar at. In at tellus nec ligula consequat feugiat. Morbi convallis, metus non tincidunt molestie, lacus nisl pellentesque nulla, luctus mattis justo nulla sit amet libero. In ultrices enim ligula, a venenatis urna malesuada non. In at eros condimentum, porttitor diam sed, egestas arcu. Praesent ultricies elit ut lacus interdum, nec sodales odio commodo. Nunc vulputate enim diam, vitae laoreet purus auctor eu. Etiam pulvinar at dolor id pretium. Praesent vel sapien eu ligula congue interdum. Aenean tristique venenatis urna eu imperdiet. Sed convallis sollicitudin lacus non semper. Proin dictum, tortor sit amet tempor convallis, turpis erat mattis eros, et vehicula dui leo at libero. Curabitur a imperdiet nunc. Aliquam a erat nibh. Nam bibendum massa libero. Suspendissae quisi diamus lacus.";

    SecureRandom secureRandom = new SecureRandom();

    HashMap<String, KeyPair> keyVallet = new HashMap<>();

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

    boolean sign(String alg, String provider, String key, String text) {
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
