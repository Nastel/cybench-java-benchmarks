package $package;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.jmh.jvm.utils.CyBenchCounters;
import com.gocypher.cybench.launcher.utils.Constants;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;


@State(Scope.Benchmark)
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "context", value = "security")
@BenchmarkMetaData(key = "actionName", value = "sign")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "dataSize", value = "4144")
@BenchmarkMetaData(key = "description", value = "Text message sign and verification.")
public class MyBenchmark implements MyBenchmarkInterface
{
    @Benchmark
    @BenchmarkMetaData(key = "title", value = "Simple benchmark from archetype")
    @BenchmarkTag(tag = "2128e15a-974f-4a01-99b9-a499279a9dbd") // REPLACE or DELETE this
    public void simpleBenchmark(Blackhole bh) {
        bh.consume("AA".concat("BB"));
    }

    @TearDown(Level.Iteration)
    public void clearIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
    }

}
