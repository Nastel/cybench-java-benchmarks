package com.gocypher.cybench.jmh.jvm.client.tests.definitions;


import com.gocypher.cybench.core.annotation.BenchmarkMetaData;

@BenchmarkMetaData(key = "api", value = "SunRsaSign")
@BenchmarkMetaData(key = "libSymbolicName", value = "SunRsaSign")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libDescription", value = "The SunRsaSign provider was introduced in JDK 1.3 as an enhanced replacement for the RSA signatures in the SunJSSE provider.")
@BenchmarkMetaData(key = "libVendor", value = "-")
@BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/technotes/guides/security/SunProviders.html#SunRsaSignProvider")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
public interface SunRsaSignLibDefinition {
}
