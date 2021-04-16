package io.cybench.jmh.jvm.client.tests;

import io.cybench.jmh.jvm.client.tests.key.BouncyCastleKey;
import io.cybench.jmh.jvm.client.tests.key.KeyGenerationBenchmarks;
import io.cybench.jmh.jvm.client.tests.key.SunJceKey;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class KeyGenerationBenchmarksTest {

    @Test
    public void testAllBenchmarkMethods() throws Exception {
        Blackhole bh = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");
        List<KeyGenerationBenchmarks> tests = new ArrayList();
        tests.add(BouncyCastleKey.class.getDeclaredConstructor(null).newInstance());
        tests.add(SunJceKey.class.getDeclaredConstructor(null).newInstance());

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