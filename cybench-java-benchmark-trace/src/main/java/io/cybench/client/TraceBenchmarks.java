package io.cybench.client;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import io.cybench.jmh.jvm.utils.CyBenchCounters;
import com.jkoolcloud.tnt4j.TrackingLogger;
import com.jkoolcloud.tnt4j.tracker.TrackingActivity;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.logging.LoggingSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.SimpleSpanProcessor;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "context", value = "trackAndTrace")
@BenchmarkMetaData(key = "actionName", value = "Create activity")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "description", value = "Create activity and log using JUL")
public class TraceBenchmarks  {

    private static Tracer tracer;

    private static TrackingLogger logger;

    @Benchmark
    @BenchmarkMetaData(key = "libSymbolicName", value = "OpenTelemetry")
    @BenchmarkMetaData(key = "libVersion", value = "1.0.0")
    @BenchmarkMetaData(key = "libDescription", value = "")
    @BenchmarkMetaData(key = "libVendor", value = "OpenTelemetry")
    @BenchmarkMetaData(key = "libUrl", value = "https://opentelemetry.io/")
    @BenchmarkMetaData(key = "api", value = "OpenTelemetry")
    @BenchmarkMetaData(key = "title", value = "OpenTelemetry create and log activity")
    @BenchmarkTag(tag = "d81aa931-9c4d-4429-bd71-b343a3a7c09b")
    public static void otLog() {
        Span span = tracer.spanBuilder("New").startSpan();
        span.addEvent("asda");
        span.setStatus(StatusCode.OK);
        span.setAttribute("PID", 8684);
        span.setAttribute("TID", 5);
        span.setAttribute("Application", 5);
        span.setAttribute("USER", "SLABS");
        span.setAttribute("FILLIN", "PPL=java.lang.Class#RUNTIME=20756@slabs-marius-PC#SERVER=slabs-marius-PC#NETADDR=192.168.56.1#DATACENTER=UNKNOWN#GEOADDR=0,0,IdCount=0,SnapCount=0,StartTime:[2021-03-08 16:46:09.128010 EET],EndTime:[2021-03-08 16:46:09.128010 EET]} | com.jkoolcloud.tnt4j.source.DefaultSource@2d6175c2{FQName: APPL=java.lang.Class#RUNTIME=20756@slabs-marius-PC#SERVER=slabs-marius-PC#NETADDR=192.168.56.1#DATACENTER=UNKNOWN#GEOADDR=0,0, Name: java.lang.Class, User: slabs, Type: APPL, URL: \"null\", SSN: \"tnt4j-stream\"}");
        span.end();
    }

    @Benchmark
    @BenchmarkMetaData(key = "libSymbolicName", value = "OpenTelemetry")
    @BenchmarkMetaData(key = "libVersion", value = "2.13.2")
    @BenchmarkMetaData(key = "libDescription", value = "")
    @BenchmarkMetaData(key = "libVendor", value = "Nastel")
    @BenchmarkMetaData(key = "libUrl", value = "https://github.com/Nastel/TNT4J")
    @BenchmarkMetaData(key = "api", value = "TNT4J")
    @BenchmarkMetaData(key = "title", value = "TNT4J create and log activity")
    @BenchmarkTag(tag = "9223353d-8cb9-41be-9f42-a154e527f7f8")
    public static void tnt4jLog() {
        TrackingActivity trackingActivity = logger.newActivity();
        trackingActivity.add(logger.newEvent("asda", "ASDA"));
        logger.tnt(trackingActivity);
    }

    private static Tracer setupOTLPtracer() {
        SdkTracerProvider tracerProvider = SdkTracerProvider.builder().addSpanProcessor(SimpleSpanProcessor.create(new LoggingSpanExporter())).build();
        OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder().setTracerProvider(tracerProvider).buildAndRegisterGlobal();
        return openTelemetrySdk.getTracer("instrumentation-library-name");
    }

    private static TrackingLogger setupTNT4Jtracer() {
        return TrackingLogger.getInstance(TraceBenchmarks.class.getClass());
    }

    @Setup(Level.Trial)
    public void setup() {
        tracer = setupOTLPtracer();
        logger = setupTNT4Jtracer();
    }

    public void simpleBenchmark(Blackhole bh) {
        bh.consume("AA".concat("BB"));
    }

    @TearDown(Level.Iteration)
    public void clearIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
    }
}
