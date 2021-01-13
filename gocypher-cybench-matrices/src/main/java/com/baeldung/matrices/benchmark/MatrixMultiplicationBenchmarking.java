package com.baeldung.matrices.benchmark;

import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import com.baeldung.matrices.HomemadeMatrix;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.ejml.simple.SimpleMatrix;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import java.util.concurrent.TimeUnit;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class MatrixMultiplicationBenchmarking {

    @Benchmark
    @BenchmarkTag(tag = "fe71d7dc-389b-4e4a-ab7b-b8209d6d3f04")
    public Object homemadeMatrixMultiplication(MatrixProvider matrixProvider) {
        return HomemadeMatrix.multiplyMatrices(matrixProvider.getFirstMatrix(), matrixProvider.getSecondMatrix());
    }

    @Benchmark
    @BenchmarkTag(tag = "ba284af6-2df3-4323-aa57-a6805583a1a9")
    public Object ejmlMatrixMultiplication(MatrixProvider matrixProvider) {
        SimpleMatrix firstMatrix = new SimpleMatrix(matrixProvider.getFirstMatrix());
        SimpleMatrix secondMatrix = new SimpleMatrix(matrixProvider.getSecondMatrix());
        return firstMatrix.mult(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "b6fed266-707f-486c-8a71-ca888a68cad0")
    public Object apacheCommonsMatrixMultiplication(MatrixProvider matrixProvider) {
        RealMatrix firstMatrix = new Array2DRowRealMatrix(matrixProvider.getFirstMatrix());
        RealMatrix secondMatrix = new Array2DRowRealMatrix(matrixProvider.getSecondMatrix());
        return firstMatrix.multiply(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "8f080ed8-6cfd-4041-be04-09c85573675f")
    public Object la4jMatrixMultiplication(MatrixProvider matrixProvider) {
        Matrix firstMatrix = new Basic2DMatrix(matrixProvider.getFirstMatrix());
        Matrix secondMatrix = new Basic2DMatrix(matrixProvider.getSecondMatrix());
        return firstMatrix.multiply(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "1fea74d5-3713-460b-89d6-bf15a1675eb2")
    public Object nd4jMatrixMultiplication(MatrixProvider matrixProvider) {
        INDArray firstMatrix = Nd4j.create(matrixProvider.getFirstMatrix());
        INDArray secondMatrix = Nd4j.create(matrixProvider.getSecondMatrix());
        return firstMatrix.mmul(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "3574dfe0-8f14-447c-b1b7-59a6b3040f36")
    public Object coltMatrixMultiplication(MatrixProvider matrixProvider) {
        DoubleFactory2D doubleFactory2D = DoubleFactory2D.dense;
        DoubleMatrix2D firstMatrix = doubleFactory2D.make(matrixProvider.getFirstMatrix());
        DoubleMatrix2D secondMatrix = doubleFactory2D.make(matrixProvider.getSecondMatrix());
        Algebra algebra = new Algebra();
        return algebra.mult(firstMatrix, secondMatrix);
    }
}
