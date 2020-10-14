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

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
public class NumberBenchmarks {

    //@Param({"1000000"})
    //public int listSize;

    public List<Integer> testList;

    Random randomGenerator;
    double rangeMax = 100000 ;
    double rangeMin = 0 ;

    @Setup
    public void setUp() {
        /*testList = new Random()
                .ints()
                .limit(listSize)
                .boxed()
                .collect(Collectors.toList());
                */
        randomGenerator = new Random();
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void generateAndAddDoubleNumbers (Blackhole blackHole){
        Double sum = 0.0 ;
        sum += Double.valueOf(rangeMin + (rangeMax - rangeMin) * this.randomGenerator.nextDouble()) ;
        sum += Double.valueOf(rangeMin + (rangeMax - rangeMin) *this.randomGenerator.nextDouble() );
        blackHole.consume(sum);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void generateAndAddAtomicNumbers (Blackhole blackHole){
        int num = (int)(rangeMin + (int) (this.randomGenerator.nextFloat() * (rangeMax - rangeMin)));
        AtomicLong atomicLong = new AtomicLong() ;
        atomicLong.addAndGet(Long.valueOf(num));
        int num2 = (int)(rangeMin + (int) this.randomGenerator.nextFloat() * (rangeMax - rangeMin));
        atomicLong.addAndGet(Long.valueOf(num2));
        Long result = atomicLong.get() ;
        blackHole.consume(result);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void generateAndAddBigDecimalNumbers (Blackhole blackHole){
        int num = (int)(rangeMin + (int) (this.randomGenerator.nextFloat() * (rangeMax - rangeMin)));
        BigDecimal sum = BigDecimal.ZERO ;
        sum = sum.add(BigDecimal.valueOf(num)) ;
        int num2 = (int)(rangeMin + (int) (this.randomGenerator.nextFloat() * (rangeMax - rangeMin)));
        sum = sum.add(BigDecimal.valueOf(num2)) ;
        blackHole.consume(sum);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void generateAndLogarithmDoubleNumbers (Blackhole blackHole){
        Double number= Double.valueOf(Math.log10(rangeMin + (rangeMax - rangeMin) * this.randomGenerator.nextDouble())) ;
        blackHole.consume(number);
    }
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void generateAndPowerDoubleNumbers (Blackhole blackHole){
        Double number= Double.valueOf(Math.pow(rangeMin + (rangeMax - rangeMin) * this.randomGenerator.nextDouble(),10)) ;
        blackHole.consume(number);
    }
    /*@Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void sumNumbersAtomicLongStream (Blackhole blackHole){
        AtomicLong atomicLong = new AtomicLong();
        testList.parallelStream().forEach(atomicLong::addAndGet);
        blackHole.consume(atomicLong.get());
    }
    */

    @TearDown
    public void cleanUp (){
        if (this.testList != null) {
            this.testList.clear();
        }
    }
}
