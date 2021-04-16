package com.gocypher.cybench.jmh.jvm.client.tests;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.lang.reflect.Method;


class HttpServerBenchmarkTest {
    @Test
    public void testAllBenchmarkMethods() throws Exception {
        Blackhole bh = new Blackhole("Today's password is swordfish. I understand instantiating Blackholes directly is dangerous.");
        HttpServerBenchmark signBenchmarks = HttpServerBenchmark.class.getDeclaredConstructor(null).newInstance();
        HttpServerBenchmark.UndertowHttpd undertowHttpd = new HttpServerBenchmark.UndertowHttpd();
        undertowHttpd.setup();
        for (Method declaredMethod : HttpServerBenchmark.class.getDeclaredMethods()) {
            if (declaredMethod.getAnnotation(Benchmark.class) != null) {
                System.out.println("Invoking " + declaredMethod.getName());
                declaredMethod.invoke(signBenchmarks, new Object[]{ null });
            }
        }
        undertowHttpd.shutdown();
    }
}