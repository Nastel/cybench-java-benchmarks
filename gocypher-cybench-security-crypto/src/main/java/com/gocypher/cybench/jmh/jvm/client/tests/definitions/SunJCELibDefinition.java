package com.gocypher.cybench.jmh.jvm.client.tests.definitions;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;

@BenchmarkMetaData(key = "api", value = "SunJCE")
@BenchmarkMetaData(key = "libSymbolicName", value = "SunJCE")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libDescription", value = "The Java Cryptography Extension (JCE) from Sun Microsystems.It is a framework for implementing encryption, key generation and key agreement, and Message Authentication Code (MAC) algorithms.")
@BenchmarkMetaData(key = "libVendor", value = "Oracle")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
@BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/technotes/guides/security/SunProviders.html#SunJCEProvider")
public interface SunJCELibDefinition {
}
