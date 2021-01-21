package com.gocypher.cybench.jmh.jvm.client.tests;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.security.*;
import java.util.Arrays;

@State(Scope.Benchmark)
public class SignBenchmarks {
    public static void main(String[] args) throws Exception {
//        Security.addProvider(new BouncyCastleProvider());
//        List<String> algorithms = Arrays.stream(Security.getProviders())
//                .flatMap(provider -> provider.getServices().stream())
//                .filter(service -> "Signature".equals(service.getType()))
//                .map(service ->  service.getAlgorithm() + " - " + service.getProvider().getName()+ "\n")
//                .distinct()
//                .sorted()
//                .collect(Collectors.toList());
//        System.out.println(algorithms);
//        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABC");
//        List<String> algorithmsBC = Arrays.stream(Security.getProviders())
//                .flatMap(provider -> provider.getServices().stream())
//                .filter(service -> "Cipher".equals(service.getType()))
//                .map(service -> service.getAlgorithm() + " - " + service.getProvider().getName()+ "\n")
//
//                .sorted()
//                .collect(Collectors.toList());
//        System.out.println(algorithmsBC);
        System.out.println(new SignBenchmarks().sign());
    }


    private boolean sign() throws Exception {
        Signature signature = Signature.getInstance("SHA256WithDSA");
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        signature.initSign(keyPair.getPrivate(), secureRandom);

        byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
        signature.update(data);

        byte[] digitalSignature = signature.sign();
        System.out.println(new String(data));
        System.out.println(Arrays.toString(digitalSignature));


        PublicKey publicKey = keyPair.getPublic();
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(digitalSignature);
    }
}
