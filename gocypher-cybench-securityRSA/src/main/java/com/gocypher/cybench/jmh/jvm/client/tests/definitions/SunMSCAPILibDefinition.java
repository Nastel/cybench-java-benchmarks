package com.gocypher.cybench.jmh.jvm.client.tests.definitions;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;

@BenchmarkMetaData(key = "api", value = "SunMSCAPI")
@BenchmarkMetaData(key = "libVendor", value = "-")
@BenchmarkMetaData(key = "libDescription", value = "Standard JCA/JCE APIs to access the native cryptographic libraries, certificates stores and key containers on the Microsoft Windows platform")
@BenchmarkMetaData(key = "libSymbolicName", value = "SunMSCAPI")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/technotes/guides/security/SunProviders.html#SunMSCAPI")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
public interface SunMSCAPILibDefinition {
}
