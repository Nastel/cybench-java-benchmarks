package io.cybench.matrices.benchmark;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.ejml.simple.SimpleMatrix;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.openjdk.jmh.annotations.Benchmark;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;

@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "context", value = "matrices")
@BenchmarkMetaData(key = "actionName", value = "multiply")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "description", value = "Matrix processing frameworks benchmark by continuously multiplying matrices.")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "true")
public class BigMatrixMultiplicationBenchmarking {

    @Benchmark
    @BenchmarkTag(tag = "b829ad1a-a21f-4c7e-9658-ceb4622470ec")
    @BenchmarkMetaData(key = "api", value = "ejml")
    @BenchmarkMetaData(key = "libSymbolicName", value = "org.ejml")
    @BenchmarkMetaData(key = "libVersion", value = "0.30")
    @BenchmarkMetaData(key = "libDescription", value = "Efficient Java Matrix Library (EJML) is a linear algebra library for manipulating real/complex/dense/sparse matrices.")
    @BenchmarkMetaData(key = "libVendor", value = "EJML")
    @BenchmarkMetaData(key = "libUrl", value = "http://ejml.org/")
    @BenchmarkMetaData(key = "title", value = "Multiply matrices using EJML")

    public Object ejmlMatrixMultiplication(BigMatrixProvider matrixProvider) {
        SimpleMatrix firstMatrix = new SimpleMatrix(matrixProvider.getFirstMatrix());
        SimpleMatrix secondMatrix = new SimpleMatrix(matrixProvider.getSecondMatrix());
        return firstMatrix.mult(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "cb8987c1-4ecf-4cb1-8abe-ce8d8ef3a3d0")
    @BenchmarkMetaData(key = "api", value = "Apache Commons Math")
    @BenchmarkMetaData(key = "libSymbolicName", value = "org.apache.commons.math3")
    @BenchmarkMetaData(key = "libVendor", value = "The Apache Software Foundation")
    @BenchmarkMetaData(key = "libUrl", value = "http://commons.apache.org/proper/commons-math/")
    @BenchmarkMetaData(key = "libVersion", value = "3.6.1")
    @BenchmarkMetaData(key = "libDescription", value = "The Apache Commons Math project is a library of lightweight, self-contained mathematics and statistics components addressing the most common practical problems not immediately available in the Java programming language or commons-lang.")
    @BenchmarkMetaData(key = "title", value = "Multiply matrices using Apache Commons Math")
    public Object apacheCommonsMatrixMultiplication(BigMatrixProvider matrixProvider) {
        RealMatrix firstMatrix = new Array2DRowRealMatrix(matrixProvider.getFirstMatrix());
        RealMatrix secondMatrix = new Array2DRowRealMatrix(matrixProvider.getSecondMatrix());
        return firstMatrix.multiply(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "9010475f-4229-4bc7-8d86-49133658094e")
    @BenchmarkMetaData(key = "api", value = "la4j")
    @BenchmarkMetaData(key = "libSymbolicName", value = "org.la4j")
    @BenchmarkMetaData(key = "libVendor", value = "la4j")
    @BenchmarkMetaData(key = "libUrl", value = "http://la4j.org/")
    @BenchmarkMetaData(key = "libVersion", value = "0.6.0")
    @BenchmarkMetaData(key = "libDescription", value = "The la4j library is open source and 100% Java library that provides Linear Algebra primitives (matrices and vectors) and algorithms. ")
    @BenchmarkMetaData(key = "title", value = "Multiply matrices using LA4J")
    public Object la4jMatrixMultiplication(BigMatrixProvider matrixProvider) {
        Matrix firstMatrix = new Basic2DMatrix(matrixProvider.getFirstMatrix());
        Matrix secondMatrix = new Basic2DMatrix(matrixProvider.getSecondMatrix());
        return firstMatrix.multiply(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "2b055e49-8453-4861-bab4-5488dfe65ec1")
    @BenchmarkMetaData(key = "api", value = "nd4j")
    @BenchmarkMetaData(key = "libSymbolicName", value = "org.nd4j.nd4j-api")
    @BenchmarkMetaData(key = "libVendor", value = "org.deeplearning4j")
    @BenchmarkMetaData(key = "libUrl", value = "https://deeplearning4j.org")
    @BenchmarkMetaData(key = "libVersion", value = "1.0.0-beta7")
    @BenchmarkMetaData(key = "libDescription", value = "Eclipse Deeplearning4j is the first commercial-grade, open-source, distributed deep-learning library written for Java and Scala.")
    @BenchmarkMetaData(key = "title", value = "Multiply matrices using ND4J")
    public Object nd4jMatrixMultiplication(BigMatrixProvider matrixProvider) {
        try (INDArray firstMatrix = Nd4j.create(matrixProvider.getFirstMatrix());
                INDArray secondMatrix = Nd4j.create(matrixProvider.getSecondMatrix())) {
            return firstMatrix.mmul(secondMatrix);
        }
    }

    @Benchmark
    @BenchmarkTag(tag = "495d60d6-0fcc-44a8-9e2c-4fa6d3741087")
    @BenchmarkMetaData(key = "api", value = "colt")
    @BenchmarkMetaData(key = "libSymbolicName", value = "colt")
    @BenchmarkMetaData(key = "libVendor", value = "CERN")
    @BenchmarkMetaData(key = "libUrl", value = "https://dst.lbl.gov/ACSSoftware/colt/")
    @BenchmarkMetaData(key = "libVersion", value = "1.2.0")
    @BenchmarkMetaData(key = "libDescription", value = "Colt provides a set of Open Source Libraries for High Performance Scientific and Technical Computing in Java.")
    @BenchmarkMetaData(key = "title", value = "Multiply matrices using Colt")
    public Object coltMatrixMultiplication(BigMatrixProvider matrixProvider) {
        DoubleFactory2D doubleFactory2D = DoubleFactory2D.dense;
        DoubleMatrix2D firstMatrix = doubleFactory2D.make(matrixProvider.getFirstMatrix());
        DoubleMatrix2D secondMatrix = doubleFactory2D.make(matrixProvider.getSecondMatrix());
        Algebra algebra = new Algebra();
        return algebra.mult(firstMatrix, secondMatrix);
    }
}
