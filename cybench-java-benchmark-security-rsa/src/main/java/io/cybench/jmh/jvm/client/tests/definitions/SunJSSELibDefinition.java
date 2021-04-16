package io.cybench.jmh.jvm.client.tests.definitions;


import com.gocypher.cybench.core.annotation.BenchmarkMetaData;

@BenchmarkMetaData(key = "api", value = "SunJSSE")
@BenchmarkMetaData(key = "libSymbolicName", value = "SunJSSE")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libDescription", value = "Java Secure Socket Extension (JSSE)  provides a set of packages that enable secure Internet communications.")
@BenchmarkMetaData(key = "libVendor", value = "-")
@BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/technotes/guides/security/SunProviders.html#SunJSSEProvider")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
public interface SunJSSELibDefinition {
}
