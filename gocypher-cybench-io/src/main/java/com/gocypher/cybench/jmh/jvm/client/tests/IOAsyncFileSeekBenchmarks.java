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
import com.gocypher.cybench.jmh.jvm.utils.IOUtils;
import org.openjdk.jmh.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMetaData(key="isLibraryBenchmark", value="false")
@BenchmarkMetaData(key="context", value="core_IO")
@BenchmarkMetaData(key="domain", value="java")
@BenchmarkMetaData(key="version", value="1.0.0")
@BenchmarkMetaData(key="libVersion", value="-")
@BenchmarkMetaData(key="api", value="java.io.RandomAccessFile")
@BenchmarkMetaData(key="libSymbolicName", value="java.io.RandomAccessFile")
@BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/io/RandomAccessFile.html")
public class IOAsyncFileSeekBenchmarks {

    private static Logger LOG = LoggerFactory.getLogger(IOAsyncFileSeekBenchmarks.class);

    private File srcFile;

    private File targetFile;

    private long fileSize = 0;

    private RandomAccessFile seekSrc;

    private RandomAccessFile seekDst;

    public static int hugeSeekChunk = 16_777_216;
    //private int hugeSeekChunk = 8_388_608;

    public static int smallSeekChunk = 4_096;

    private byte[] dataForSeekAndWriteSmallChunks;

    private byte[] dataForSeekAndWriteHugeChunks;

    private long[] arrayOfRandomNumbersForSmallChunks;

    private long[] arrayOfRandomNumbersForHugeChunks;

    public static int hugeSeekIterationsCount = 64;

    public static int smallSeekIterationsCount = 262_144;

    @Setup(Level.Trial)
    public void setupForFork() throws Exception {
        LOG.info("\n-->Will generate binary file for tests...");
        srcFile = IOUtils.generateBinaryFileForTests();
        fileSize = srcFile.length();
        LOG.info("\n-->Generated file {} for processing, size(B):{}", srcFile, fileSize);
        seekSrc = new RandomAccessFile(srcFile, "rw");
        LOG.info("Will generate an array of random numbers for file positions");
        this.arrayOfRandomNumbersForSmallChunks = IOUtils.getArrayOfRandomNumberUsingLongs(0, fileSize - smallSeekChunk - 10, smallSeekIterationsCount);
        this.arrayOfRandomNumbersForHugeChunks = IOUtils.getArrayOfRandomNumberUsingLongs(0, fileSize - hugeSeekChunk - 10, hugeSeekIterationsCount);
        this.dataForSeekAndWriteSmallChunks = IOUtils.getArrayOfRandomBytes(smallSeekChunk);
        this.dataForSeekAndWriteHugeChunks = IOUtils.getArrayOfRandomBytes(hugeSeekChunk);
        LOG.info("Generated all prerequisites!");
    }

    @Setup(Level.Iteration)
    public void setupForIteration() throws Exception {
        LOG.info("\n Will setup for iteration...");
        targetFile = IOUtils.createOutputFileForTests();
        seekDst = new RandomAccessFile(targetFile, "rw");
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "f5dfcc25-4a38-470e-b9e3-f3cef2ba37e8")
    @BenchmarkMetaData(key="title", value="seek and read Async")
    @BenchmarkMetaData(key="dataSize", value="1073741824")
    @BenchmarkMetaData(key="chunkSize", value="4096")
    @BenchmarkMetaData(key="seekIterations", value="262144")
    @BenchmarkMetaData(key="actionName", value="seekReadAsync")
    @BenchmarkMetaData(key="description", value="Asynchronous file seek and read with RandomAccessFile API and 4KB size chunks.")
    public int seekAndReadFileUsingSmallChunks() throws Exception {
        int bytesRead = 0;
        for (long position : arrayOfRandomNumbersForSmallChunks) {
            bytesRead += IOUtils.seekAndReadFile(seekSrc, (int) fileSize, smallSeekChunk, position);
        }
        // LOG.info("Read bytes:{}",bytesRead);
        return bytesRead;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
    @Warmup(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
    @BenchmarkTag(tag = "34cd7bc4-d6b2-4a33-9ced-ea6a20a1c564")
    @BenchmarkMetaData(key="title", value="seek and read Async")
    @BenchmarkMetaData(key="dataSize", value="1073741824")
    @BenchmarkMetaData(key="chunkSize", value="16777216")
    @BenchmarkMetaData(key="seekIterations", value="64")
    @BenchmarkMetaData(key="actionName", value="seekReadAsync")
    @BenchmarkMetaData(key="description", value="Asynchronous file seek and read with RandomAccessFile API and 16MB size chunks.")
    public int seekAndReadFileUsingHugeChunks() throws Exception {
        int bytesRead = 0;
        for (long position : arrayOfRandomNumbersForHugeChunks) {
            bytesRead += IOUtils.seekAndReadFile(seekSrc, (int) fileSize, hugeSeekChunk, position);
        }
        // LOG.info("Read bytes:{}",bytesRead);
        return bytesRead;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "f4695c18-96c4-4e93-ad8b-4d0c1d4641d4")
    @BenchmarkMetaData(key="title", value="seek and write Async")
    @BenchmarkMetaData(key="dataSize", value="1073741824")
    @BenchmarkMetaData(key="chunkSize", value="4096")
    @BenchmarkMetaData(key="seekIterations", value="262144")
    @BenchmarkMetaData(key="actionName", value="seekWriteAsync")
    @BenchmarkMetaData(key="description", value="Asynchronous file seek and write with RandomAccessFile API and 4KB size chunks.")
    public void seekAndWriteFileUsingSmallChunks() throws Exception {
        for (long position : this.arrayOfRandomNumbersForSmallChunks) {
            IOUtils.seekAndWriteFile(seekSrc, position, dataForSeekAndWriteSmallChunks);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
    @Warmup(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
    @BenchmarkTag(tag = "84825e3e-a06a-4c99-abed-09b16180c76d")
    @BenchmarkMetaData(key="title", value="seek and write Async")
    @BenchmarkMetaData(key="dataSize", value="1073741824")
    @BenchmarkMetaData(key="chunkSize", value="16777216")
    @BenchmarkMetaData(key="seekIterations", value="64")
    @BenchmarkMetaData(key="actionName", value="seekWriteAsync")
    @BenchmarkMetaData(key="description", value="Asynchronous file seek and write with RandomAccessFile API and 16MB size chunks.")
    public void seekAndWriteFileUsingHugeChunks() throws Exception {
        for (long position : this.arrayOfRandomNumbersForHugeChunks) {
            IOUtils.seekAndWriteFile(seekSrc, position, dataForSeekAndWriteHugeChunks);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "21a48cbc-d85d-4193-958e-46e8cbb71bd2")
    @BenchmarkMetaData(key="title", value="seek and copy Async")
    @BenchmarkMetaData(key="dataSize", value="1073741824")
    @BenchmarkMetaData(key="chunkSize", value="4096")
    @BenchmarkMetaData(key="actionName", value="seekCopyAsync")
    @BenchmarkMetaData(key="description", value="Asynchronous file seek and copy with RandomAccessFile API and 4KB size chunks.")
    public void seekAndCopyFileUsingSmallChunks() throws Exception {
        long position = 0;
        while (position <= fileSize) {
            byte[] bytes = IOUtils.seekAndReadFile(seekSrc, smallSeekChunk, position);
            IOUtils.seekAndWriteFile(seekDst, position, bytes);
            position += smallSeekChunk;
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "b91e5be7-5b5c-4463-913d-15bee841531c")
    @BenchmarkMetaData(key="title", value="seek and copy Async")
    @BenchmarkMetaData(key="dataSize", value="1073741824")
    @BenchmarkMetaData(key="chunkSize", value="16777216")
    @BenchmarkMetaData(key="actionName", value="seekCopyAsync")
    @BenchmarkMetaData(key="description", value="Asynchronous file seek and copy with RandomAccessFile API and 16MB size chunks.")
    public void seekAndCopyFileUsingHugeChunks() throws Exception {
        long position = 0;
        while (position <= fileSize) {
            byte[] bytes = IOUtils.seekAndReadFile(seekSrc, hugeSeekChunk, position);
            IOUtils.seekAndWriteFile(seekDst, position, bytes);
            position += hugeSeekChunk;
        }
    }

    @TearDown(Level.Iteration)
    public void cleanUpAfterIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
        try {
            if (seekDst != null) {
                seekDst.close();
            }
            IOUtils.removeFile(targetFile);
            LOG.info("\n==>Iteration clean up successful!");
        } catch (Exception e) {
            LOG.error("Error on file removal", e);
        }
    }

    @TearDown(Level.Trial)
    public void cleanUpAfterFork() {
        try {
            if (seekSrc != null) {
                seekSrc.close();
            }
            // IOUtils.removeFile(srcFile);
            LOG.info("\n==>Generated files were closed successfully!");
        } catch (Exception e) {
            LOG.error("Error on file removal", e);
        }
    }
}
