package com.baeldung.matrices.benchmark;

import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.ejml.simple.SimpleMatrix;
import org.la4j.Matrix;
import org.la4j.matrix.dense.Basic2DMatrix;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;
import org.openjdk.jmh.annotations.Benchmark;
import com.gocypher.cybench.core.annotation.BenchmarkTag;



@BenchmarkMetaData(key="isLibraryBenchmark", value="true")
@BenchmarkMetaData(key="context", value="matrices")
@BenchmarkMetaData(key="domain", value="java")
@BenchmarkMetaData(key="version", value="1.0.0")
@BenchmarkMetaData(key="actionName", value="multiply")
@BenchmarkMetaData(key="description", value="Multiplying double numbers matrices with different libraries")
public class BigMatrixMultiplicationBenchmarking {



    @Benchmark
    @BenchmarkTag(tag = "b829ad1a-a21f-4c7e-9658-ceb4622470ec")
    @BenchmarkMetaData(key="api", value="ejml")
    @BenchmarkMetaData(key="libVendor", value="org.ejml")
    @BenchmarkMetaData(key="libUrl", value="http://ejml.org/wiki/index.php?title=Main_Page")
    @BenchmarkMetaData(key="libVersion", value="0.30")
    @BenchmarkMetaData(key="libDescription", value="Efficient Java Matrix Library")
    @BenchmarkMetaData(key="title", value="EJML Matrices multiplication")
    public Object ejmlMatrixMultiplication(BigMatrixProvider matrixProvider) {
        SimpleMatrix firstMatrix = new SimpleMatrix(matrixProvider.getFirstMatrix());
        SimpleMatrix secondMatrix = new SimpleMatrix(matrixProvider.getSecondMatrix());
        return firstMatrix.mult(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "cb8987c1-4ecf-4cb1-8abe-ce8d8ef3a3d0")
    @BenchmarkMetaData(key="api", value="apache.commons.math3")
    @BenchmarkMetaData(key="libVendor", value="org.apache")
    @BenchmarkMetaData(key="libUrl", value="https://commons.apache.org/proper/commons-math/")
    @BenchmarkMetaData(key="libVersion", value="3.6.1")
    @BenchmarkMetaData(key="libDescription", value="Apache Commons Math3 module")
    @BenchmarkMetaData(key="title", value="Apache commons Matrices multiplication")
    public Object apacheCommonsMatrixMultiplication(BigMatrixProvider matrixProvider) {
        RealMatrix firstMatrix = new Array2DRowRealMatrix(matrixProvider.getFirstMatrix());
        RealMatrix secondMatrix = new Array2DRowRealMatrix(matrixProvider.getSecondMatrix());
        return firstMatrix.multiply(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "9010475f-4229-4bc7-8d86-49133658094e")
    @BenchmarkMetaData(key="api", value="la4j")
    @BenchmarkMetaData(key="libVendor", value="org.la4j")
    @BenchmarkMetaData(key="libUrl", value="http://la4j.org/")
    @BenchmarkMetaData(key="libVersion", value="0.6.0")
    @BenchmarkMetaData(key="libDescription", value="Linear Algebra for Java")
    @BenchmarkMetaData(key="title", value="LA4J Matrices multiplication")
    public Object la4jMatrixMultiplication(BigMatrixProvider matrixProvider) {
        Matrix firstMatrix = new Basic2DMatrix(matrixProvider.getFirstMatrix());
        Matrix secondMatrix = new Basic2DMatrix(matrixProvider.getSecondMatrix());
        return firstMatrix.multiply(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "2b055e49-8453-4861-bab4-5488dfe65ec1")
    @BenchmarkMetaData(key="api", value="nd4j")
    @BenchmarkMetaData(key="libVendor", value="org.deeplearning4j")
    @BenchmarkMetaData(key="libUrl", value="https://deeplearning4j.org/docs/latest/nd4j-overview")
    @BenchmarkMetaData(key="libVersion", value="1.0.0-beta7")
    @BenchmarkMetaData(key="libDescription", value="Eclipse Deeplearning4j")
    @BenchmarkMetaData(key="title", value="ND4J Matrices multiplication")
    public Object nd4jMatrixMultiplication(BigMatrixProvider matrixProvider) {
        INDArray firstMatrix = Nd4j.create(matrixProvider.getFirstMatrix());
        INDArray secondMatrix = Nd4j.create(matrixProvider.getSecondMatrix());
        return firstMatrix.mmul(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "495d60d6-0fcc-44a8-9e2c-4fa6d3741087")
    @BenchmarkMetaData(key="api", value="colt")
    @BenchmarkMetaData(key="libVendor", value="CERN")
    @BenchmarkMetaData(key="libUrl", value="https://dst.lbl.gov/ACSSoftware/colt/")
    @BenchmarkMetaData(key="libVersion", value="1.2.0")
    @BenchmarkMetaData(key="libDescription", value="Colt scientific and technical computing library")
    @BenchmarkMetaData(key="title", value="Colt Matrices multiplication")
    public Object coltMatrixMultiplication(BigMatrixProvider matrixProvider) {
        DoubleFactory2D doubleFactory2D = DoubleFactory2D.dense;
        DoubleMatrix2D firstMatrix = doubleFactory2D.make(matrixProvider.getFirstMatrix());
        DoubleMatrix2D secondMatrix = doubleFactory2D.make(matrixProvider.getSecondMatrix());
        Algebra algebra = new Algebra();
        return algebra.mult(firstMatrix, secondMatrix);
    }
}
