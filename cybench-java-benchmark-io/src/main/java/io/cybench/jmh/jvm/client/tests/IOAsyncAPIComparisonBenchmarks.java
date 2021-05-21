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

import java.io.File;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.core.utils.IOUtils;

import io.cybench.jmh.jvm.utils.CyBenchCounters;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
@BenchmarkMetaData(key = "context", value = "core_IO")
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "title", value = "Copy file Async")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libVendor", value = "-")
public class IOAsyncAPIComparisonBenchmarks {

    private static Logger LOG = LoggerFactory.getLogger(IOAsyncAPIComparisonBenchmarks.class);

    private File srcFile;

    private File targetFile;

    private long fileSize = 0;

    private FileChannel readFileChannel;

    private FileChannel writeFileChannel;

    private int smallChunk = 4_096;

    private int hugeChunk = 67_108_864;
    // private int hugeChunk = 8_388_608;

    private boolean isSyncWrite = false;

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(IOAsyncAPIComparisonBenchmarks.class.getSimpleName()).forks(1)
                // .threads(1)
                .measurementIterations(3).warmupIterations(1).build();

        new Runner(opt).run();
    }

    @Setup(Level.Trial)
    public void setupForFork() throws Exception {
        LOG.info("\n-->Will generate binary file for tests...");
        srcFile = IOUtils.generateBinaryFileForTests();
        fileSize = srcFile.length();
        LOG.info("\n-->Generated file {} for processing, size(B):{}", srcFile, fileSize);
        readFileChannel = (FileChannel) Files.newByteChannel(srcFile.toPath(), EnumSet.of(StandardOpenOption.READ));
    }

    @Setup(Level.Iteration)
    public void setupForIteration() throws Exception {
        LOG.info("\n Will setup for iteration...");
        targetFile = IOUtils.createOutputFileForTests();
        LOG.info("\n Will setup for iteration..." + targetFile);
        writeFileChannel = (FileChannel) Files.newByteChannel(targetFile.toPath(), EnumSet.of(StandardOpenOption.READ,
                StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(1)
    @Threads(1)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "f569ac52-072d-4bcc-a896-843b739c4f2b")
    @BenchmarkMetaData(key = "api", value = "java.nio.MappedByteBuffer")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.io.MappedByteBuffer")
    @BenchmarkMetaData(key = "dataSize", value = "1073741824")
    @BenchmarkMetaData(key = "actionName", value = "readWriteAsync")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/nio/MappedByteBuffer.html")
    @BenchmarkMetaData(key = "description", value = "Asynchronous file copying using Mapped Byte Buffer to read/write a generated 1GB binary file.")
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
    @BenchmarkTag(tag = "26be83d9-e801-4fee-a8df-b282bf730382")
    @BenchmarkMetaData(key = "api", value = "java.io.FileStreams")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.io.FileStreams")
    @BenchmarkMetaData(key = "dataSize", value = "1073741824")
    @BenchmarkMetaData(key = "chunkSize", value = "4096")
    @BenchmarkMetaData(key = "actionName", value = "readWriteAsync")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html")
    @BenchmarkMetaData(key = "description", value = "Asynchronous file copying using FileInputStream and FileOutputStream with 4KB chunks to copy 1GB binary file.")
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
    @BenchmarkTag(tag = "790d6d8b-81bb-4599-b0dd-0aed5ed9127f")
    @BenchmarkMetaData(key = "api", value = "java.io.FileStreams")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.io.FileStreams")
    @BenchmarkMetaData(key = "dataSize", value = "1073741824")
    @BenchmarkMetaData(key = "chunkSize", value = "67108864")
    @BenchmarkMetaData(key = "actionName", value = "readWriteAsync")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html")
    @BenchmarkMetaData(key = "description", value = "Asynchronous file copying using FileInputStream and FileOutputStream with 64MB chunks to copy 1GB binary file.")
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
    @BenchmarkTag(tag = "baa29159-7bbf-48e1-9fb9-a9fb05943499")
    @BenchmarkMetaData(key = "api", value = "java.io.BufferedStream")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.io.BufferedStream")
    @BenchmarkMetaData(key = "dataSize", value = "1073741824")
    @BenchmarkMetaData(key = "chunkSize", value = "4096")
    @BenchmarkMetaData(key = "actionName", value = "readWriteAsync")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html")
    @BenchmarkMetaData(key = "description", value = "Asynchronous file copying using BufferedInputStream and BufferedOutputStream with 4KB chunks to copy 1GB binary file.")
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
    @BenchmarkTag(tag = "4be92515-66e9-4255-a7f5-c262e4362f46")
    @BenchmarkMetaData(key = "api", value = "java.io.BufferedStream")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.io.BufferedStream")
    @BenchmarkMetaData(key = "dataSize", value = "1073741824")
    @BenchmarkMetaData(key = "chunkSize", value = "67108864")
    @BenchmarkMetaData(key = "actionName", value = "readWriteAsync")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html")
    @BenchmarkMetaData(key = "description", value = "Asynchronous file copying using BufferedInputStream and BufferedOutputStream with 64MB chunks to copy 1GB binary file.")
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
    @BenchmarkTag(tag = "7ebb2f8e-eb1e-4cd6-ac9b-f8e2270a5358")
    @BenchmarkMetaData(key = "api", value = "java.io.DirectBufferedStream")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.io.DirectBufferedStream")
    @BenchmarkMetaData(key = "dataSize", value = "1073741824")
    @BenchmarkMetaData(key = "chunkSize", value = "4096")
    @BenchmarkMetaData(key = "actionName", value = "readWriteAsync")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html")
    @BenchmarkMetaData(key = "description", value = "Asynchronous file copying using direct BufferedInputStream and BufferedOutputStream with 4KB chunks to copy 1GB binary file.")
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
    @BenchmarkTag(tag = "76eb336e-c80e-48eb-99c4-3b27f9b6c56f")
    @BenchmarkMetaData(key = "api", value = "java.io.DirectBufferedStream")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.io.DirectBufferedStream")
    @BenchmarkMetaData(key = "dataSize", value = "1073741824")
    @BenchmarkMetaData(key = "chunkSize", value = "67108864")
    @BenchmarkMetaData(key = "actionName", value = "readWriteAsync")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html")
    @BenchmarkMetaData(key = "description", value = "Asynchronous file copying using direct BufferedInputStream and BufferedOutputStream with 64MB chunks to copy 1GB binary file.")
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
