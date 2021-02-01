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

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.utils.CyBenchCounters;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@State(Scope.Benchmark)
@BenchmarkMetaData(key="isLibraryBenchmark", value="false")
@BenchmarkMetaData(key="context", value="core_numbers")
@BenchmarkMetaData(key="domain", value="java")
@BenchmarkMetaData(key="version", value="1.0.0")
@BenchmarkMetaData(key="libVersion", value="-")
public class NumberBenchmarks {

    public List<Integer> testList;
    double rangeMax = 100000;
    double rangeMin = 0;

    @Setup
    public void setUp() {
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(1)
    @Threads(5)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "e57460e6-9589-4d64-92e1-8e6a36ecc93c")
    @BenchmarkMetaData(key="title", value="Add random double numbers")
    @BenchmarkMetaData(key="api", value="java.lang.Double")
    @BenchmarkMetaData(key="actionName", value="generateAddNumbers")
    @BenchmarkMetaData(key="description", value="Generate two double numbers with ThreadLocalRandom multiple them and add them into the final double sum")
    public void generateAndAddDoubleNumbers(Blackhole blackHole) {
        double sum = 0.0;
        sum += rangeMin + (rangeMax - rangeMin) * ThreadLocalRandom.current().nextDouble();
        sum += rangeMin + (rangeMax - rangeMin) * ThreadLocalRandom.current().nextDouble();
        blackHole.consume(sum);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(1)
    @Threads(5)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "d55b00f0-cdb6-46e9-8b74-3c575e5f1e5a")
    @BenchmarkMetaData(key="title", value="Add random atomic numbers")
    @BenchmarkMetaData(key="api", value="java.util.concurrent.atomic.AtomicLong")
    @BenchmarkMetaData(key="actionName", value="generateAddNumbers")
    @BenchmarkMetaData(key="description", value="Generate two int type numbers create an AtomicLong number object and add both numbers into it.")
    public void generateAndAddAtomicNumbers(Blackhole blackHole) {
        int num = (int) (rangeMin + (int) (ThreadLocalRandom.current().nextFloat() * (rangeMax - rangeMin)));
        AtomicLong atomicLong = new AtomicLong();
        atomicLong.addAndGet(Long.valueOf(num));
        int num2 = (int) (rangeMin + (int) ThreadLocalRandom.current().nextFloat() * (rangeMax - rangeMin));
        atomicLong.addAndGet(Long.valueOf(num2));
        Long result = atomicLong.get();
        blackHole.consume(result);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(1)
    @Threads(5)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "4eb23d57-1d6a-4eb9-8d52-0b0a82de92d5")
    @BenchmarkMetaData(key="title", value="Add random big decimal numbers")
    @BenchmarkMetaData(key="api", value="java.math.BigDecimal")
    @BenchmarkMetaData(key="actionName", value="generateAddNumbers")
    @BenchmarkMetaData(key="description", value="Generate two int numbers and add them while converting to BigDecimal.")
    public void generateAndAddBigDecimalNumbers(Blackhole blackHole) {
        int num = (int) (rangeMin + (int) (ThreadLocalRandom.current().nextFloat() * (rangeMax - rangeMin)));
        BigDecimal sum = BigDecimal.ZERO;
        sum = sum.add(BigDecimal.valueOf(num));
        int num2 = (int) (rangeMin + (int) (ThreadLocalRandom.current().nextFloat() * (rangeMax - rangeMin)));
        sum = sum.add(BigDecimal.valueOf(num2));
        blackHole.consume(sum);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(1)
    @Threads(5)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "3085ca56-8f30-4b2d-add8-b86258f63f6e")
    @BenchmarkMetaData(key="title", value="Logarithm double numbers")
    @BenchmarkMetaData(key="api", value="java.lang.Double")
    @BenchmarkMetaData(key="actionName", value="generateLogarithmNumbers")
    @BenchmarkMetaData(key="description", value="Generate a double number and do Math.log10.")
    public void generateAndLogarithmDoubleNumbers(Blackhole blackHole) {
        Double number = Double.valueOf(Math.log10(rangeMin + (rangeMax - rangeMin) * ThreadLocalRandom.current().nextDouble()));
        blackHole.consume(number);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(1)
    @Threads(5)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "4fcd4596-3dbf-492b-9602-c66a1c26d648")
    @BenchmarkMetaData(key="title", value="Power double numbers")
    @BenchmarkMetaData(key="api", value="java.lang.Double")
    @BenchmarkMetaData(key="actionName", value="generatePowerNumbers")
    @BenchmarkMetaData(key="description", value="Generate a double number and Power by 10.")
    public void generateAndPowerDoubleNumbers(Blackhole blackHole) {
        Double number = Double.valueOf(Math.pow(rangeMin + (rangeMax - rangeMin) * ThreadLocalRandom.current().nextDouble(), 10));
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
