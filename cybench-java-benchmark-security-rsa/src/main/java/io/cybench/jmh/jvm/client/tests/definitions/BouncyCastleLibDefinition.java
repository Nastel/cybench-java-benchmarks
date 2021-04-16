package io.cybench.jmh.jvm.client.tests.definitions;


import com.gocypher.cybench.core.annotation.BenchmarkMetaData;

@BenchmarkMetaData(key = "api", value = "BouncyCastle")
@BenchmarkMetaData(key = "libSymbolicName", value = "org.bouncycastle.bcprovider")
@BenchmarkMetaData(key = "libVersion", value = "1.58")
@BenchmarkMetaData(key = "libDescription", value = "Bouncy Castle Java cryptography APIs")
@BenchmarkMetaData(key = "libVendor", value = "BouncyCastle.org")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "true")
@BenchmarkMetaData(key = "libUrl", value = "https://www.bouncycastle.org/")
public interface BouncyCastleLibDefinition {
}
