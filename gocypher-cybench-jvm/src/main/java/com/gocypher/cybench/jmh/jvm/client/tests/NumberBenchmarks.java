/*
 * Copyright (C) 2020, K2N.IO.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */
package com.gocypher.cybench.jmh.jvm.client.tests;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.gocypher.cybench.jmh.jvm.utils.CyBenchCounters;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

@State(Scope.Benchmark)
public class NumberBenchmarks {

    public List<Integer> testList;

    Random randomGenerator;

    double rangeMax = 100000;

    double rangeMin = 0;

    @Setup
    public void setUp() {
        randomGenerator = new Random();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkTag(tag = "e57460e6-9589-4d64-92e1-8e6a36ecc93c")
    public void generateAndAddDoubleNumbers(Blackhole blackHole) {
        Double sum = 0.0;
        sum += Double.valueOf(rangeMin + (rangeMax - rangeMin) * this.randomGenerator.nextDouble());
        sum += Double.valueOf(rangeMin + (rangeMax - rangeMin) * this.randomGenerator.nextDouble());
        blackHole.consume(sum);
    }

   @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkTag(tag = "d55b00f0-cdb6-46e9-8b74-3c575e5f1e5a")
    public void generateAndAddAtomicNumbers(Blackhole blackHole) {
        int num = (int) (rangeMin + (int) (this.randomGenerator.nextFloat() * (rangeMax - rangeMin)));
        AtomicLong atomicLong = new AtomicLong();
        atomicLong.addAndGet(Long.valueOf(num));
        int num2 = (int) (rangeMin + (int) this.randomGenerator.nextFloat() * (rangeMax - rangeMin));
        atomicLong.addAndGet(Long.valueOf(num2));
        Long result = atomicLong.get();
        blackHole.consume(result);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkTag(tag = "4eb23d57-1d6a-4eb9-8d52-0b0a82de92d5")
    public void generateAndAddBigDecimalNumbers(Blackhole blackHole) {
        int num = (int) (rangeMin + (int) (this.randomGenerator.nextFloat() * (rangeMax - rangeMin)));
        BigDecimal sum = BigDecimal.ZERO;
        sum = sum.add(BigDecimal.valueOf(num));
        int num2 = (int) (rangeMin + (int) (this.randomGenerator.nextFloat() * (rangeMax - rangeMin)));
        sum = sum.add(BigDecimal.valueOf(num2));
        blackHole.consume(sum);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkTag(tag = "3085ca56-8f30-4b2d-add8-b86258f63f6e")
    public void generateAndLogarithmDoubleNumbers(Blackhole blackHole) {
        Double number = Double.valueOf(Math.log10(rangeMin + (rangeMax - rangeMin) * this.randomGenerator.nextDouble()));
        blackHole.consume(number);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @BenchmarkTag(tag = "4fcd4596-3dbf-492b-9602-c66a1c26d648")
    public void generateAndPowerDoubleNumbers(Blackhole blackHole) {
        Double number = Double.valueOf(Math.pow(rangeMin + (rangeMax - rangeMin) * this.randomGenerator.nextDouble(), 10));
        blackHole.consume(number);
    }
    @TearDown(Level.Iteration)
    public void clearIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
    }

    @TearDown
    public void cleanUp() {
        if (this.testList != null) {
            this.testList.clear();
        }
    }
}
