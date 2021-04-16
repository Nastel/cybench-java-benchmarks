package $package;
import com.gocypher.cybench.core.annotation.BenchmarkMetaData;

@BenchmarkMetaData(key = "api", value = "CyBench")
@BenchmarkMetaData(key = "libSymbolicName", value = "CyBenchArchetype")
@BenchmarkMetaData(key = "libVersion", value = "1.0.0")
@BenchmarkMetaData(key = "libDescription", value = "")
@BenchmarkMetaData(key = "libVendor", value = "CyBench")
@BenchmarkMetaData(key = "libUrl", value = "https://cybench.io/")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
public interface MyBenchmarkInterface {
}
