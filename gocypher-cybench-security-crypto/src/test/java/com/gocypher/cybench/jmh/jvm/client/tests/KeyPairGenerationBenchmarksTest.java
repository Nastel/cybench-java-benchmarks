package com.gocypher.cybench.jmh.jvm.client.tests;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class KeyPairGenerationBenchmarksTest {

    @Test
    public void testAllBenchmarkMethods() throws Exception {
        Blackhole bh = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");
        KeyPairGenerationBenchmarks signBenchmarks = KeyPairGenerationBenchmarks.class.getDeclaredConstructor(null).newInstance();
        signBenchmarks.setup();
        for (Method declaredMethod : KeyPairGenerationBenchmarks.class.getDeclaredMethods()) {
            if (declaredMethod.getAnnotation(Benchmark.class) != null) {
                System.out.println("Invoking " + declaredMethod.getName());
                declaredMethod.invoke(signBenchmarks, bh);
            }
        }
    }

}