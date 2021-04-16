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

package io.cybench.jmh.jvm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.SecureRandom;
import java.util.Random;

public class IOUtils {
	private static Logger LOG = LoggerFactory.getLogger(IOUtils.class);
	private static int randomFileChunkSize = 65536;
	private static long fileSizeMultiplierPerChunkSize = 16384;
	private static long fileSizeSmallMultiplierPerChunkSize = 2048;
	// private static long fileSizeMultiplierPerChunkSize = 1024 ;
	private static String FILE_NAME_AS_SRC = "binary.bin";
	private static String FILE_NAME_AS_SRC_FOR_SMALL_CASES = "small_binary.bin";
	private static String FILE_NAME_AS_DST = "output-binary-test.txt";
	private static String FILE_NAME_AS_DST_FOR_SMALL_CASES = "output_small_binary_test.bin";

	public static File createOutputFileForTests() {
		return createFile(FILE_NAME_AS_DST);
	}

	public static File createSmallOutputFileForTests() {
		return createFile(FILE_NAME_AS_DST_FOR_SMALL_CASES);
	}

	public static File createFile(String name) {
		return new File(name);
	}

	public static File generateBinaryFileForTests() throws Exception {
		createRandomBinaryFileIfNotExists(FILE_NAME_AS_SRC, fileSizeMultiplierPerChunkSize,
				randomFileChunkSize * fileSizeMultiplierPerChunkSize);
		File f = new File(FILE_NAME_AS_SRC);
		return f;
	}

	public static File generateSmallBinaryFileForTests() throws Exception {
		createRandomBinaryFileIfNotExists(FILE_NAME_AS_SRC_FOR_SMALL_CASES, fileSizeSmallMultiplierPerChunkSize,
				randomFileChunkSize * fileSizeSmallMultiplierPerChunkSize);
		File f = new File(FILE_NAME_AS_SRC_FOR_SMALL_CASES);
		return f;
	}

	/*
	 * public static File openFile (String fileName) throws Exception{ File srcFile
	 * = createFile(fileName); InputStream is = openFileAsInputStream(fileName) ;
	 * try { long copiedBytes = copyFileUsingBufferedStreams(is, srcFile, 65536); }
	 * finally { is.close(); } return srcFile ; }
	 */
	public static void removeTestDataFiles() {

		removeFile(new File(FILE_NAME_AS_SRC));
		removeFile(new File(FILE_NAME_AS_DST));
		removeFile(new File(FILE_NAME_AS_SRC_FOR_SMALL_CASES));
		removeFile(new File(FILE_NAME_AS_DST_FOR_SMALL_CASES));
	}

	public static InputStream openFileAsInputStream(String fileName) {
		ClassLoader CLDR = IOUtils.class.getClassLoader();
		InputStream in = CLDR.getResourceAsStream(fileName);
		return in;
	}

	public static void removeFile(File file) {
		try {

			if (file != null && file.exists()) {
				// LOG.info("Will delete file:{}",file.getAbsolutePath());
				file.delete();
			}
		} catch (Exception e) {
			LOG.error("Error on removing file", e);
		}
	}

	public static long copyFileUsingFileStreams(File srcFile, File targetFile, int bufferSize, boolean isSyncWrite)
			throws IOException {
		long bytesCopied = 0L;
		byte[] buffer = new byte[bufferSize];

		try (InputStream in = new FileInputStream(srcFile)) {
			try (OutputStream out = new FileOutputStream(targetFile)) {
				int bytesRead;
				while ((bytesRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
					if (isSyncWrite) {
						out.flush();
					}
					bytesCopied += bytesRead;
				}
			}
		}

		return bytesCopied;
	}

	public static long copyFileUsingBufferedStreams(File srcFile, File targetFile, int bufferSize, boolean isSyncWrite)
			throws IOException {
		long bytesCopied = 0L;
		byte[] buffer = new byte[bufferSize];

		try (InputStream in = new BufferedInputStream(new FileInputStream(srcFile))) {
			try (OutputStream out = new BufferedOutputStream(new FileOutputStream(targetFile))) {
				int bytesRead;
				while ((bytesRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
					if (isSyncWrite) {
						out.flush();
					}
					bytesCopied += bytesRead;
				}
			}
		}

		return bytesCopied;
	}

	public static long copyFileUsingDirectBufferedStreams(File srcFile, File targetFile, int bufferSize,
			boolean isSyncWrite) throws IOException {
		long bytesCopied = 0L;
		byte[] buffer = new byte[bufferSize];

		try (InputStream in = new BufferedInputStream(new FileInputStream(srcFile), bufferSize * 2)) {
			try (OutputStream out = new BufferedOutputStream(new FileOutputStream(targetFile), bufferSize * 2)) {
				int bytesRead;
				while ((bytesRead = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
					if (isSyncWrite) {
						out.flush();
					}
					bytesCopied += bytesRead;
				}
			}
		}

		return bytesCopied;
	}

	public static long copyFileUsingBufferedStreams(InputStream inputStream, File targetFile, int bufferSize)
			throws IOException {
		long bytesCopied = 0L;
		byte[] buffer = new byte[bufferSize];

		try (OutputStream out = new BufferedOutputStream(new FileOutputStream(targetFile))) {
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				bytesCopied += bytesRead;
			}
		}
		return bytesCopied;
	}

	public static void storeResultsToFile(String fileName, String content) {
		FileWriter file = null;
		try {
			file = new FileWriter(fileName);
			file.write(content);

		} catch (Exception e) {
			e.printStackTrace();
			LOG.error("Error on storing results to file", e);

		} finally {

			try {
				file.flush();
				file.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void createRandomBinaryFileIfNotExists(String name, long sizePer65KB, long preferredFileSize) {
		File file = new File(name);
		if (!file.exists() || (file.exists() && file.length() < preferredFileSize)) {
			try (FileOutputStream out = new FileOutputStream(file)) {
				SecureRandom rnd = new SecureRandom();
				for (int i = 0; i < sizePer65KB; i++) {
					byte[] bytes = new byte[randomFileChunkSize];
					rnd.nextBytes(bytes);
					out.write(bytes);
				}
				out.flush();
			} catch (Exception e) {
				LOG.error("Error during generation of tmp file", e);
			}
		} else {
			LOG.info("Data file for tests exists, a new one will not be created");
		}
	}

	public static long getHugeRandomBinaryFileSizeInBytes() {
		return randomFileChunkSize * fileSizeMultiplierPerChunkSize;
	}

	public static long getSmallRandomBinaryFileSizeInBytes() {
		return randomFileChunkSize * fileSizeSmallMultiplierPerChunkSize;
	}

	public static int seekAndReadFile(RandomAccessFile file, long fileSize, int pageSize) throws Exception {
		// int pageSize = 4096 ;
		// int pageSize = 1048576 ;
		long position = getRandomNumberUsingIntegers(0, fileSize - pageSize - 10);
		byte[] pageBytes = new byte[pageSize];
		int offset = 0;
		file.seek(position);
		int bytesRead = file.read(pageBytes, offset, pageSize);
		file.seek(0);
		return bytesRead;
	}

	public static int seekAndReadFile(RandomAccessFile file, long fileSize, int pageSize, long position)
			throws Exception {
		// int pageSize = 4096 ;
		// int pageSize = 1048576 ;
		// long position = getRandomNumberUsingIntegers (0,fileSize-pageSize-10) ;
		byte[] pageBytes = new byte[pageSize];
		int offset = 0;
		file.seek(position);
		int bytesRead = file.read(pageBytes, offset, pageSize);
		file.seek(0);
		return bytesRead;
	}

	public static byte[] seekAndReadFile(RandomAccessFile file, int pageSize, long position) throws Exception {
		byte[] pageBytes = new byte[pageSize];
		int offset = 0;
		file.seek(position);
		file.read(pageBytes, offset, pageSize);
		file.seek(0);
		return pageBytes;
	}

	public static void seekAndWriteFile(RandomAccessFile file, long position, byte[] data) throws Exception {
		file.seek(position);
		file.write(data);
		file.seek(0);
	}

	public static long getRandomNumberUsingIntegers(long min, long max) {
		Random random = new Random();
		return random.longs(min, max).findFirst().getAsLong();
	}

	public static long[] getArrayOfRandomNumberUsingLongs(long min, long max, int amount) {
		Random random = new Random();
		return random.longs(min, max).limit(amount).toArray();
	}

	public static byte[] getArrayOfRandomBytes(int amount) {
		Random random = new Random();
		byte[] arr = new byte[amount];
		random.nextBytes(arr);
		return arr;
	}

	public static void rwFileUsingMappedByteBuffer(FileChannel readFileChannel, FileChannel writeFileChannel,
			boolean isSyncWrite) throws Exception {

		MappedByteBuffer mappedByteBufferSrc = readFileChannel.map(FileChannel.MapMode.READ_ONLY, 0,
				readFileChannel.size());

		if (mappedByteBufferSrc != null) {
			byte[] rs = new byte[mappedByteBufferSrc.remaining()];
			mappedByteBufferSrc.get(rs);
			MappedByteBuffer mappedByteBufferDst = writeFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, rs.length);
			mappedByteBufferDst.put(rs);
			if (isSyncWrite) {
				mappedByteBufferDst.force();
			}
			cleanMappedByteBuffer(mappedByteBufferSrc);
			cleanMappedByteBuffer(mappedByteBufferDst);
		}
	}

	private static void cleanMappedByteBuffer(MappedByteBuffer byteBuffer) {
		/*
		 * Cleaner cleaner = ((sun.nio.ch.DirectBuffer) byteBuffer).cleaner(); if
		 * (cleaner != null) { cleaner.clean(); }
		 */
		byteBuffer.clear();
	}

}
