package com.baeldung.matrices.benchmark;

import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix2D;
import cern.colt.matrix.linalg.Algebra;
import com.baeldung.matrices.HomemadeMatrix;
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


@BenchmarkMetaData(key="action", value="multiiplication")
public class BigMatrixMultiplicationBenchmarking {



    @Benchmark

    @BenchmarkTag(tag = "b829ad1a-a21f-4c7e-9658-ceb4622470ec")
    @BenchmarkMetaData(key="library", value="Efficient Java Matrix Library")
    @BenchmarkMetaData(key="vendor", value="org.ejml")
    @BenchmarkMetaData(key="link", value="http://ejml.org/wiki/index.php?title=Main_Page")
    @BenchmarkMetaData(key="version", value="0.30")
    public Object ejmlMatrixMultiplication(BigMatrixProvider matrixProvider) {
        SimpleMatrix firstMatrix = new SimpleMatrix(matrixProvider.getFirstMatrix());
        SimpleMatrix secondMatrix = new SimpleMatrix(matrixProvider.getSecondMatrix());
        return firstMatrix.mult(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "cb8987c1-4ecf-4cb1-8abe-ce8d8ef3a3d0")
    @BenchmarkMetaData(key="library", value="Apache Commons Math3 module")
    @BenchmarkMetaData(key="vendor", value="org.apache")
    @BenchmarkMetaData(key="link", value="https://commons.apache.org/proper/commons-math/")
    @BenchmarkMetaData(key="version", value="3.6.1")
    public Object apacheCommonsMatrixMultiplication(BigMatrixProvider matrixProvider) {
        RealMatrix firstMatrix = new Array2DRowRealMatrix(matrixProvider.getFirstMatrix());
        RealMatrix secondMatrix = new Array2DRowRealMatrix(matrixProvider.getSecondMatrix());
        return firstMatrix.multiply(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "9010475f-4229-4bc7-8d86-49133658094e")
    @BenchmarkMetaData(key="library", value="Linear Algebra for Java")
    @BenchmarkMetaData(key="vendor", value="org.la4j")
    @BenchmarkMetaData(key="link", value="http://la4j.org/")
    @BenchmarkMetaData(key="version", value="0.6.0")
    public Object la4jMatrixMultiplication(BigMatrixProvider matrixProvider) {
        Matrix firstMatrix = new Basic2DMatrix(matrixProvider.getFirstMatrix());
        Matrix secondMatrix = new Basic2DMatrix(matrixProvider.getSecondMatrix());
        return firstMatrix.multiply(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "2b055e49-8453-4861-bab4-5488dfe65ec1")
    @BenchmarkMetaData(key="library", value="nd4j")
    @BenchmarkMetaData(key="vendor", value="org.deeplearning4j")
    @BenchmarkMetaData(key="link", value="https://deeplearning4j.org/docs/latest/nd4j-overview")
    @BenchmarkMetaData(key="version", value="1.0.0-beta7")
    public Object nd4jMatrixMultiplication(BigMatrixProvider matrixProvider) {
        INDArray firstMatrix = Nd4j.create(matrixProvider.getFirstMatrix());
        INDArray secondMatrix = Nd4j.create(matrixProvider.getSecondMatrix());
        return firstMatrix.mmul(secondMatrix);
    }

    @Benchmark
    @BenchmarkTag(tag = "495d60d6-0fcc-44a8-9e2c-4fa6d3741087")
    @BenchmarkMetaData(key="library", value="colt")
    @BenchmarkMetaData(key="vendor", value="CERN")
    @BenchmarkMetaData(key="link", value="https://dst.lbl.gov/ACSSoftware/colt/")
    @BenchmarkMetaData(key="version", value="1.2.0")
    public Object coltMatrixMultiplication(BigMatrixProvider matrixProvider) {
        DoubleFactory2D doubleFactory2D = DoubleFactory2D.dense;
        DoubleMatrix2D firstMatrix = doubleFactory2D.make(matrixProvider.getFirstMatrix());
        DoubleMatrix2D secondMatrix = doubleFactory2D.make(matrixProvider.getSecondMatrix());
        Algebra algebra = new Algebra();
        return algebra.mult(firstMatrix, secondMatrix);
    }
}
