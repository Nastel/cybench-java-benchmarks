package com.baeldung.matrices.benchmark;

import com.gocypher.cybench.jmh.jvm.utils.CyBenchCounters;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.BenchmarkParams;

import java.util.Random;

@State(Scope.Benchmark)
public class BigMatrixProvider {
    @Param({"2", "3"})
    private int matrixSize;
    @Param({"2", "3"})
    private int matrixSize2;
    private double[][] firstMatrix;
    private double[][] secondMatrix;

    public BigMatrixProvider() {}

    @Setup
    public void setup(BenchmarkParams parameters) {
        firstMatrix = createMatrix(matrixSize);
        secondMatrix = createMatrix(matrixSize);
    }

    private double[][] createMatrix(int matrixSize) {
        Random random = new Random();

        double[][] result = new double[matrixSize][matrixSize];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = random.nextDouble();
            }
        }
        return result;
    }
    @TearDown(Level.Iteration)
    public void clearIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
    }

    public double[][] getFirstMatrix() {
        return firstMatrix;
    }

    public double[][] getSecondMatrix() {
        return secondMatrix;
    }
}
