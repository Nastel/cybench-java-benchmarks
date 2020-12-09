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

import com.gocypher.cybench.jmh.jvm.utils.IOUtils;
import org.openjdk.jmh.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

@State(Scope.Benchmark)
public class IOSyncFileSeekBenchmarks {

    private static Logger LOG = LoggerFactory.getLogger(IOSyncFileSeekBenchmarks.class);

    private File srcFile;

    private File targetFile;

    private long fileSize = 0;

    private RandomAccessFile seekSrc;

    private RandomAccessFile seekDst;

    public static int hugeSeekChunk = 16_777_216;

    // public static int smallSeekChunk = 4_096 ;
    public static int smallSeekChunk = 16_384;

    private byte[] dataForSeekAndWriteSmallChunks;

    private byte[] dataForSeekAndWriteHugeChunks;

    private long[] arrayOfRandomNumbersForSmallChunks;

    private long[] arrayOfRandomNumbersForHugeChunks;

    public static int hugeSeekIterationsCount = 8;

    // public static int smallSeekIterationsCount = 32_768 ;
    public static int smallSeekIterationsCount = 8_192;

    @Setup(Level.Trial)
    public void setupForFork() throws Exception {
        LOG.info("\n-->Will generate binary file for tests...");
        srcFile = IOUtils.generateSmallBinaryFileForTests();
        fileSize = srcFile.length();
        LOG.info("\n-->Generated file {} for processing, size(B):{}", srcFile, fileSize);
        seekSrc = new RandomAccessFile(srcFile, "rwd");
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
        targetFile = IOUtils.createSmallOutputFileForTests();
        seekDst = new RandomAccessFile(targetFile, "rwd");
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkTag(tag = "62a03d1d-051f-4ac6-80a6-ed516ad85518")
    public int seekAndReadFileUsingSmallChunks() throws Exception {
        int bytesRead = 0;
        for (long position : arrayOfRandomNumbersForSmallChunks) {
            bytesRead += IOUtils.seekAndReadFile(seekSrc, (int) fileSize, smallSeekChunk, position);
        }
        return bytesRead;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkTag(tag = "97aa1891-f7f8-41d9-8221-16e0cf0ec587")
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
    @BenchmarkTag(tag = "c4bfa008-6616-4b4b-b8f1-98be4e488423")
    public void seekAndWriteFileUsingSmallChunks() throws Exception {
        for (long position : this.arrayOfRandomNumbersForSmallChunks) {
            IOUtils.seekAndWriteFile(seekSrc, position, dataForSeekAndWriteSmallChunks);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkTag(tag = "3f53f832-28fb-4e9c-86b5-e6bc9d52c9bf")
    public void seekAndWriteFileUsingHugeChunks() throws Exception {
        for (long position : this.arrayOfRandomNumbersForHugeChunks) {
            IOUtils.seekAndWriteFile(seekSrc, position, dataForSeekAndWriteHugeChunks);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @BenchmarkTag(tag = "be7d5a60-154d-43ca-95fe-2606bda9b6d5")
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
    @BenchmarkTag(tag = "da2ef185-34ba-4bdd-8b87-7c13ed89d25d")
    public void seekAndCopyFileUsingHugeChunks() throws Exception {
        long position = 0;
        while (position <= fileSize) {
            byte[] bytes = IOUtils.seekAndReadFile(seekSrc, hugeSeekChunk, position);
            IOUtils.seekAndWriteFile(seekDst, position, bytes);
            position += hugeSeekChunk;
        }
    }

    @TearDown(Level.Iteration)
    public void cleanUpAfterIteration() {
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
