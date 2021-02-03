package com.gocypher.cybench.jmh.jvm.client.tests;

import com.gocypher.cybench.jmh.jvm.client.tests.keyPair.*;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class KeyPairGenerationBenchmarksTest {

    @Test
    public void testAllBenchmarkMethods() throws Exception {
        Blackhole bh = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");
        List<KeyPairGenerationBenchmarks> tests = new ArrayList();
        tests.add(BouncyCastleKeyPair.class.getDeclaredConstructor(null).newInstance());
        tests.add(SunECKeyPair.class.getDeclaredConstructor(null).newInstance());
        tests.add(SunJSSE.class.getDeclaredConstructor(null).newInstance());
        tests.add(SunKeyPair.class.getDeclaredConstructor(null).newInstance());
        tests.add(SunMSCAPI.class.getDeclaredConstructor(null).newInstance());
        tests.add(SunRsaKeyPair.class.getDeclaredConstructor(null).newInstance());

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

}