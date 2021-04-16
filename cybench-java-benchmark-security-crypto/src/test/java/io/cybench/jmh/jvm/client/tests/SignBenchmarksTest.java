package io.cybench.jmh.jvm.client.tests;

import io.cybench.jmh.jvm.client.tests.sign.*;
import io.cybench.jmh.jvm.client.tests.sign.BouncyCastleBenchmarks;
import io.cybench.jmh.jvm.client.tests.sign.SignBenchmarks;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SignBenchmarksTest {

    @Test
    public void testAllBenchmarkMethods() throws Exception {
        Blackhole bh = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");
        List<SignBenchmarks> tests = new ArrayList();
        tests.add(BouncyCastleBenchmarks.class.getDeclaredConstructor(null).newInstance());

        tests.forEach(t -> {
            try {
                t.setup();
            } catch (Exception e) {
            }
        });


        tests.stream().forEach(t -> {
            Arrays.stream(t.getClass().getDeclaredMethods()).forEach(declaredMethod -> {
                if (declaredMethod.getAnnotation(Benchmark.class) != null) {
                    System.out.println("Invoking " + declaredMethod.getName());
                    try {
                        declaredMethod.invoke(t, bh);
                    } catch (Exception e) {
                    }
                }
            });
        });
    }


    @Test
    public void getProviders() {
        Security.addProvider(new BouncyCastleProvider());
        List<String> algorithms = Arrays.stream(Security.getProviders())
                .flatMap(provider -> provider.getServices().stream())
                // .filter(service -> "Signature".equals(service.getType()))
                .map(service -> service.getType() + " - " + service.getAlgorithm() + " - " + service.getProvider().getName() + "\n")
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(algorithms);
        // System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABC");
        // List<String> algorithmsBC = Arrays.stream(Security.getProviders())
        // .flatMap(provider -> provider.getServices().stream())
        // .filter(service -> "Cipher".equals(service.getType()))
        // .map(service -> service.getAlgorithm() + " - " + service.getProvider().getName()+ "\n")
        //
        // .sorted()
        // .collect(Collectors.toList());
        // System.out.println(algorithmsBC);
        // System.out.println(new SignBenchmarks().sign("SHA256WithDSA", "DSA", ));
    }

    /*    public static void main(String[] args) throws Exception {
        // Security.addProvider(new BouncyCastleProvider());
        // List<String> algorithms = Arrays.stream(Security.getProviders()).flatMap(provider -> provider.getServices().stream()).filter(service -> "Signature".equals(service.getType())).map(service -> service.getAlgorithm() + " - " + service.getProvider().getName() + "\n").distinct().sorted().collect(Collectors.toList());
        // System.out.println(algorithms);
        // // System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABC");
        // // List<String> algorithmsBC = Arrays.stream(Security.getProviders())
        // // .flatMap(provider -> provider.getServices().stream())
        // // .filter(service -> "Cipher".equals(service.getType()))
        // // .map(service -> service.getAlgorithm() + " - " + service.getProvider().getName()+ "\n")
        // //
        // // .sorted()
        // // .collect(Collectors.toList());
        // // System.out.println(algorithmsBC);
        // // System.out.println(new SignBenchmarks().sign("SHA256WithDSA", "DSA", ));
        Blackhole bh = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");
        SignBenchmarks s = new SignBenchmarks();
        s.setup();
        s.keyVallet.forEach((k, v) -> System.out.println(k + " : " + ((RSAPublicKey) v.getPublic()).getModulus().bitLength()));
        s.BC_MD2withRSA(bh);
        s.SunJSSE_MD2withRSA(bh);
        s.SunRsaSign_MD2withRSA(bh);
        s.SunRsaSign_MD2withRSA(bh);
        s.BC_MD5withRSA(bh);
        s.SunJSSE_MD5withRSA(bh);
        s.SunMSCAPI_MD5withRSA(bh);
        s.SunRsaSign_MD5withRSA(bh);
    }

     */
}
