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
package io.cybench.jmh.jvm.client.tests;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import io.cybench.jmh.jvm.utils.CyBenchCounters;
import io.cybench.jmh.jvm.utils.IOUtils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMetaData(key="isLibraryBenchmark", value="false")
@BenchmarkMetaData(key="context", value="core_IO")
@BenchmarkMetaData(key="domain", value="java")
@BenchmarkMetaData(key="title", value="Copy file Sync")
@BenchmarkMetaData(key="version", value="1.0.0")
@BenchmarkMetaData(key="libVersion", value="-")
@BenchmarkMetaData(key="libVendor", value="-")
public class IOSyncAPIComparisonBenchmarks {

    private static Logger LOG = LoggerFactory.getLogger(IOSyncAPIComparisonBenchmarks.class);

    private File srcFile;

    private File targetFile;

    private long fileSize = 0;

    private FileChannel readFileChannel;

    private FileChannel writeFileChannel;

    // private int smallChunk = 4_096 ;
    private int smallChunk = 16_384;

    private int hugeChunk = 67_108_864;
    // private int hugeChunk = 8_388_608;

    private boolean isSyncWrite = true;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(IOSyncAPIComparisonBenchmarks.class.getSimpleName())
                .forks(1)
                .threads(10)
                .measurementIterations(3)
                .warmupIterations(1)
                .build();

        new Runner(opt).run();
    }

    @Setup(Level.Trial)
    public void setupForFork() throws Exception {
        LOG.info("\n-->Will generate binary file for tests...");
        srcFile = IOUtils.generateSmallBinaryFileForTests();
        fileSize = srcFile.length();
        LOG.info("\n-->Generated file {} for processing, size(B):{}", srcFile, fileSize);
        readFileChannel = (FileChannel) Files.newByteChannel(srcFile.toPath(), EnumSet.of(StandardOpenOption.READ));
    }

    @Setup(Level.Iteration)
    public void setupForIteration() throws Exception {
        LOG.info("\n Will setup for iteration...");
        targetFile = IOUtils.createSmallOutputFileForTests();
        writeFileChannel = (FileChannel) Files.newByteChannel(targetFile.toPath(), EnumSet.of(StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "9badbef6-11a7-4f6d-a83f-e97dee8833b6")
    @BenchmarkMetaData(key="api", value="java.nio.MappedByteBuffer")
    @BenchmarkMetaData(key="libSymbolicName", value="java.nio.MappedByteBuffer")
    @BenchmarkMetaData(key="dataSize", value="134217728")
    @BenchmarkMetaData(key="actionName", value="readWriteSync")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/nio/MappedByteBuffer.html")
    @BenchmarkMetaData(key="description", value="Synchronous file copying using Mapped Byte Buffer to read/write a generated 128MB binary file.")
    public void copyFileUsingMappedByteBuffer() throws Exception {
        IOUtils.rwFileUsingMappedByteBuffer(readFileChannel, writeFileChannel, isSyncWrite);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "d1dc08aa-a013-4a1b-8c82-22261cce5b4f")
    @BenchmarkMetaData(key="api", value="java.io.FileStreams")
    @BenchmarkMetaData(key="libSymbolicName", value="java.io.FileStreams")
    @BenchmarkMetaData(key="dataSize", value="1073741824")
    @BenchmarkMetaData(key="chunkSize", value="67108864")
    @BenchmarkMetaData(key="actionName", value="readWriteSync")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html")
    @BenchmarkMetaData(key="description", value="Synchronous file copying using FileInputStream and FileOutputStream with 64MB chunks to copy 128MB binary file.")
    public void copyFileUsingFileStreamAndSmallChunks() throws Exception {
        long bytesCopied = IOUtils.copyFileUsingFileStreams(srcFile, targetFile, smallChunk, isSyncWrite);
        assert bytesCopied == fileSize;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "afdad4a2-41d5-4bf7-8dac-987ccef2edd4")
    @BenchmarkMetaData(key="api", value="java.io.FileStreams")
    @BenchmarkMetaData(key="libSymbolicName", value="java.io.FileStreams")
    @BenchmarkMetaData(key="dataSize", value="1073741824")
    @BenchmarkMetaData(key="chunkSize", value="4096")
    @BenchmarkMetaData(key="actionName", value="readWriteSync")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html")
    @BenchmarkMetaData(key="description", value="Synchronous file copying using FileInputStream and FileOutputStream with 4KB chunks to copy 128MB binary file.")
    public void copyFileUsingFileStreamAndHugeChunks() throws Exception {
        long bytesCopied = IOUtils.copyFileUsingFileStreams(srcFile, targetFile, hugeChunk, isSyncWrite);
        assert bytesCopied == fileSize;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "25e27373-c7b9-431e-abf3-4d55b20d788a")
    @BenchmarkMetaData(key="api", value="java.io.BufferedStream")
    @BenchmarkMetaData(key="libSymbolicName", value="java.io.BufferedStream")
    @BenchmarkMetaData(key="dataSize", value="1073741824")
    @BenchmarkMetaData(key="chunkSize", value="4096")
    @BenchmarkMetaData(key="actionName", value="readWriteSync")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html")
    @BenchmarkMetaData(key="description", value="Synchronous file copying using BufferedInputStream and BufferedInputStream with 4KB chunks to copy 128MB binary file.")
    public void copyFileUsingBufferedStreamAndSmallChunks() throws Exception {
        long bytesCopied = IOUtils.copyFileUsingBufferedStreams(srcFile, targetFile, smallChunk, isSyncWrite);
        assert bytesCopied == fileSize;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "d45422f6-58ac-49c3-a749-24e5a3dbfc2d")
    @BenchmarkMetaData(key="api", value="java.io.BufferedStream")
    @BenchmarkMetaData(key="libSymbolicName", value="java.io.BufferedStream")
    @BenchmarkMetaData(key="dataSize", value="1073741824")
    @BenchmarkMetaData(key="chunkSize", value="67108864")
    @BenchmarkMetaData(key="actionName", value="readWriteSync")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html")
    @BenchmarkMetaData(key="description", value="Synchronous file copying using BufferedInputStream and BufferedInputStream with 64MB chunks to copy 128MB binary file.")
    public void copyFileUsingBufferedStreamAndHugeChunks() throws Exception {
        long bytesCopied = IOUtils.copyFileUsingBufferedStreams(srcFile, targetFile, hugeChunk, isSyncWrite);
        assert bytesCopied == fileSize;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "a68bc04c-f4e9-46fd-aba7-833623f0d94e")
    @BenchmarkMetaData(key="api", value="java.io.DirectBufferedStream")
    @BenchmarkMetaData(key="libSymbolicName", value="java.io.DirectBufferedStream")
    @BenchmarkMetaData(key="dataSize", value="1073741824")
    @BenchmarkMetaData(key="chunkSize", value="4096")
    @BenchmarkMetaData(key="actionName", value="readWriteSync")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html")
    @BenchmarkMetaData(key="description", value="Synchronous file copying using direct BufferedInputStream and BufferedInputStream with 4KB chunks to copy 128MB binary file.")
    public void copyFileUsingDirectBufferedStreamAndSmallChunks() throws Exception {
        long bytesCopied = IOUtils.copyFileUsingDirectBufferedStreams(srcFile, targetFile, smallChunk, isSyncWrite);
        assert bytesCopied == fileSize;
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "9756e9f2-bd85-4781-bfb7-5bb8e7986bf8")
    @BenchmarkMetaData(key="api", value="java.io.DirectBufferedStream")
    @BenchmarkMetaData(key="libSymbolicName", value="java.io.DirectBufferedStream")
    @BenchmarkMetaData(key="dataSize", value="1073741824")
    @BenchmarkMetaData(key="chunkSize", value="67108864")
    @BenchmarkMetaData(key="actionName", value="readWriteSync")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html")
    @BenchmarkMetaData(key="description", value="Synchronous file copying using FileInputStream and FileOutputStream with 64MB chunks to copy 128MB binary file.")
    public void copyFileUsingDirectBufferedStreamAndHugeChunks() throws Exception {
        long bytesCopied = IOUtils.copyFileUsingDirectBufferedStreams(srcFile, targetFile, hugeChunk, isSyncWrite);
        assert bytesCopied == fileSize;
    }

    @TearDown(Level.Iteration)
    public void cleanUpAfterIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
        try {
            if (writeFileChannel != null) {
                writeFileChannel.close();
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
            if (readFileChannel != null) {
                readFileChannel.close();
            }
            // IOUtils.removeFile(srcFile);
            LOG.info("\n==>Generated files were closed successfully!");
        } catch (Exception e) {
            LOG.error("Error on file removal", e);
        }
    }
}
