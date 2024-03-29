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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301  USA
 */
package io.cybench.jmh.jvm.client.tests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.nanohttpd.protocols.http.IHTTPSession;
import org.nanohttpd.protocols.http.response.Response;
import org.openjdk.jmh.annotations.*;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Exit;
import org.takes.http.FtCli;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import io.cybench.jmh.jvm.utils.CyBenchCounters;
import io.undertow.Undertow;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "context", value = "JAVA_WEB_Containers")
@BenchmarkMetaData(key = "actionName", value = "sendHTTPGet")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "description", value = "JAVA WEB container benchmark by sending lots of HTTP GET requests.")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "true")
public class HttpServerBenchmark {

    private static String responseString = "<p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p><p>This is response</p>";

    public static final int port = 25555;

    // public static void main(String... args) {
    // new TakesWebServer().setup();
    // new HttpServerBenchmark().get();
    // }
    @Benchmark
    @BenchmarkMode(value = Mode.Throughput)
    // @OperationsPerInvocation(value = 1000000)
    @BenchmarkTag(tag = "76ca1ef3-aa04-4aaf-865f-1b4a1f1267cc")
    @BenchmarkMetaData(key = "api", value = "com.sun.net.httpserver.HttpServer")
    @BenchmarkMetaData(key = "libSymbolicName", value = "com.sun.net.httpserver.HttpServer")
    @BenchmarkMetaData(key = "libVersion", value = "-")
    @BenchmarkMetaData(key = "libDescription", value = "Embedded simple JAVA based HTTP server provided by JDK which was used to launch benchmark.")
    @BenchmarkMetaData(key = "libVendor", value = "-")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/jre/api/net/httpserver/spec/com/sun/net/httpserver/HttpServer.html")
    @BenchmarkMetaData(key = "title", value = "HTTP GET Request to com.sun.net.httpserver.HttpServer")
    public void javaServerBenchmark(JavaServer server) {
        get();
    }

    // @Benchmark
    // @BenchmarkMode(value = Mode.Throughput)
    // // @OperationsPerInvocation(value = 1000000)
    // @BenchmarkTag(tag = "48ea9d22-8af0-4649-b661-e71556fc8952")
    // @BenchmarkMetaData(key = "libVendor", value = "javax.xml.ws")
    // @BenchmarkMetaData(key = "libUrl", value =
    // "https://docs.oracle.com/javase/8/docs/api/javax/xml/ws/package-use.html")
    // @BenchmarkMetaData(key = "libDescription", value = "Jakarta XML Web Services")
    // public void javaWebServiceBenchmark(JavaWebServiceProvider server) {
    // get();
    // }
    @Benchmark
    @BenchmarkMode(value = Mode.Throughput)
    @BenchmarkTag(tag = "6198f242-6081-4351-980c-64f74c6ab855")
    @BenchmarkMetaData(key = "api", value = "Takes")
    @BenchmarkMetaData(key = "libSymbolicName", value = "org.takes")
    @BenchmarkMetaData(key = "libVersion", value = "1.19")
    @BenchmarkMetaData(key = "libDescription", value = "Takes is a true object-oriented and immutable Java web framework.")
    @BenchmarkMetaData(key = "libVendor", value = "org.takes")
    @BenchmarkMetaData(key = "libUrl", value = "https://www.takes.org/")
    @BenchmarkMetaData(key = "title", value = "HTTP GET Request to Takes")

    public // @OperationsPerInvocation(value = 1000000)
    void takesBenchmark(TakesWebServer server) {
        get();
    }

    @Benchmark
    @BenchmarkMode(value = Mode.Throughput)
    @BenchmarkMetaData(key = "api", value = "Jetty")
    @BenchmarkMetaData(key = "libSymbolicName", value = "org.eclipse.jetty.server")
    @BenchmarkMetaData(key = "libVersion", value = "9.4.35.v20201120")
    @BenchmarkMetaData(key = "libDescription", value = "Jetty provides a web server and servlet container.")
    @BenchmarkMetaData(key = "libVendor", value = "Eclipse Jetty Project")
    @BenchmarkMetaData(key = "libUrl", value = "https://www.eclipse.org/jetty/")
    @BenchmarkMetaData(key = "title", value = "HTTP GET Request to Jetty")
    @BenchmarkTag(tag = "7a1b6382-be4c-4c1f-a7fc-067e1bd7bb7c")
    public // @OperationsPerInvocation(value = 1000000)
    void jettyBenchmark(JettyWebServer server) {
        get();
    }

    @Benchmark
    @BenchmarkMode(value = Mode.Throughput)
    @BenchmarkMetaData(key = "api", value = "NanoHTTPD")
    @BenchmarkMetaData(key = "libSymbolicName", value = "org.nanohttpd")
    @BenchmarkMetaData(key = "libVersion", value = "2.2.0")
    @BenchmarkMetaData(key = "libDescription", value = "NanoHTTPD is a light-weight HTTP server designed for embedding in other applications.")
    @BenchmarkMetaData(key = "libVendor", value = "org.nanohttpd")
    @BenchmarkMetaData(key = "libUrl", value = "https://github.com/NanoHttpd/nanohttpd")
    @BenchmarkMetaData(key = "title", value = "HTTP GET Request to NanoHTTPD")
    @BenchmarkTag(tag = "77fc8228-9352-45c3-b960-68cc08ff11d8")
    public // @OperationsPerInvocation(value = 1000000)
    void nanoHttpdBenchmark(NanoHTTPD server) {
        get();
    }

    @Benchmark
    @BenchmarkMode(value = Mode.Throughput)
    @BenchmarkMetaData(key = "api", value = "SparkJava")
    @BenchmarkMetaData(key = "libSymbolicName", value = "com.sparkjava.spark-core")
    @BenchmarkMetaData(key = "libVersion", value = "2.9.3")
    @BenchmarkMetaData(key = "libDescription", value = "A micro framework for creating web applications in Kotlin and Java 8 with minimal effort.")
    @BenchmarkMetaData(key = "libVendor", value = "Spark")
    @BenchmarkMetaData(key = "libUrl", value = "http://www.sparkjava.com")
    @BenchmarkMetaData(key = "title", value = "HTTP GET Request to SparkJava")
    @BenchmarkTag(tag = "5a3f98fd-dcf9-47ab-aa9a-24070ea18d92")
    public // @OperationsPerInvocation(value = 1000000)
    void sparkHttpdBenchmark(SparkHttpd server) {
        get();
    }

    @Benchmark
    @BenchmarkMode(value = Mode.Throughput)
    @BenchmarkMetaData(key = "api", value = "Undertow")
    @BenchmarkMetaData(key = "libSymbolicName", value = "io.undertow.undertow-core")
    @BenchmarkMetaData(key = "libVersion", value = "2.2.3.Final")
    @BenchmarkMetaData(key = "libDescription", value = "Undertow is a flexible performant web server written in java, providing both blocking and non-blocking API’s based on NIO.")
    @BenchmarkMetaData(key = "libVendor", value = "JBoss by Red Hat")
    @BenchmarkMetaData(key = "libUrl", value = "https://undertow.io/")
    @BenchmarkMetaData(key = "title", value = "HTTP GET Request to Undertow")
    @BenchmarkTag(tag = "83bd20e4-ca1c-442d-83b6-a47d6d1f3686")
    public // @OperationsPerInvocation(value = 1000000)
    void undertowHttpdBenchmark(UndertowHttpd server) {
        get();
    }

    @TearDown(Level.Iteration)
    public void clearIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
    }

    private void get() {
        HttpURLConnection con = null;
        try {
            URL url = new URL("http://127.0.0.1:" + port + "/test");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            InputStream inputStream = con.getInputStream();
            StringBuffer content = getString(inputStream);
            if (!content.toString().equals(responseString)) {
                throw new RuntimeException("Response Error");
            }
        } catch (IOException e) {
            System.out.println(getString(con.getErrorStream()));
            e.printStackTrace();
        }
    }

    private StringBuffer getString(InputStream inputStream) {
        StringBuffer content = new StringBuffer();
        if (inputStream == null) {
            return null;
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content;
        } catch (IOException e) {
            e.printStackTrace();
            return content;
        }
    }

    @State(Scope.Benchmark)
    public static class JavaServer {

        String response = HttpServerBenchmark.responseString;

        @Setup
        public void setup() {
            HttpServer server = null;
            try {
                server = HttpServer.create(new InetSocketAddress(HttpServerBenchmark.port), 0);
                server.createContext("/test", new MyHandler());
                // creates a default executor
                server.setExecutor(null);
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private class MyHandler implements HttpHandler {

            @Override
            public void handle(HttpExchange t) throws IOException {
                t.sendResponseHeaders(200, response.length());
                try (OutputStream os = t.getResponseBody()) {
                    os.write(response.getBytes());
                    os.flush();
                }
            }
        }
    }

    // @State(Scope.Benchmark)
    // @WebServiceProvider
    // @ServiceMode(value = Service.Mode.PAYLOAD)
    // public static class JavaWebServiceProvider implements Provider<Source> {
    //
    // public Source invoke(Source request) {
    // return new StreamSource(new StringReader(responseString));
    // }
    //
    // @Setup
    // public void setup() {
    // String address = "http://localhost:8080/test";
    // new Thread(() -> {
    // Endpoint endpoint = Endpoint.create(HTTPBinding.HTTP_BINDING, new JavaWebServiceProvider());
    // endpoint.publish(address);
    // System.out.println(endpoint.isPublished());
    // try {
    // Thread.sleep(Long.MAX_VALUE);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }).start();
    // try {
    // Thread.sleep(3000);
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // }
    @State(Scope.Benchmark)
    public static class TakesWebServer {

        @Setup
        public void setup() {
            new Thread(() -> {
                try {
                    FtCli ftCli = new FtCli(new TkFork(new FkRegex("/test", HttpServerBenchmark.responseString)),
                            "--port=" + HttpServerBenchmark.port, "--threads=10");
                    ftCli.start(Exit.NEVER);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    @State(Scope.Benchmark)
    public static class JettyWebServer extends org.eclipse.jetty.server.handler.AbstractHandler {

        private Server server;

        @Setup
        public void setup() {
            server = new Server();
            ServerConnector connector = new ServerConnector(server);
            connector.setPort(HttpServerBenchmark.port);
            server.setConnectors(new Connector[] { connector });
            server.setHandler(new JettyWebServer());
            try {
                server.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @TearDown
        public void stopS() {
            try {
                server.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void handle(String s, Request request, HttpServletRequest httpServletRequest,
                HttpServletResponse response) throws IOException, ServletException {
            response.setContentType("text/html; charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            try (PrintWriter out = response.getWriter()) {
                out.println(HttpServerBenchmark.responseString);
                out.flush();
                request.setHandled(true);
            }
        }
    }

    @State(Scope.Benchmark)
    public static class NanoHTTPD extends org.nanohttpd.protocols.http.NanoHTTPD {

        public NanoHTTPD() {
            super(HttpServerBenchmark.port);
            try {
                start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public Response serve(IHTTPSession session) {
            return Response.newFixedLengthResponse(HttpServerBenchmark.responseString);
        }

        @TearDown
        @Override
        public void stop() {
            super.stop();
        }
    }

    @State(Scope.Benchmark)
    public static class SparkHttpd {

        @Setup
        public void setup() {
            spark.Spark.port(HttpServerBenchmark.port);
            spark.Spark.get("/test", (req, res) -> HttpServerBenchmark.responseString);
        }

        @TearDown
        public void shutdown() {
            spark.Spark.stop();
        }
    }

    @State(Scope.Benchmark)
    public static class UndertowHttpd {

        private Undertow server;

        @Setup
        public void setup() {
            server = Undertow.builder().addHttpListener(HttpServerBenchmark.port, "localhost")
                    .setHandler(new io.undertow.server.HttpHandler() {

                        @Override
                        public void handleRequest(HttpServerExchange exchange) throws Exception {
                            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                            exchange.getResponseSender().send(HttpServerBenchmark.responseString);
                        }
                    }).build();
            server.start();
        }

        @TearDown
        public void shutdown() {
            server.stop();
        }
    }
    // public static void main(String... args) {
    // NanoHTTPD nanoHTTPD = new NanoHTTPD();
    // new HttpServerBenchmark().get();
    // }
    // @State(Scope.Benchmark)
    // public static class NettyWebServer {
    //
    // @Setup
    // public void setup() {
    // // Configure the server.
    // EventLoopGroup bossGroup = new MultithreadEventLoopGroup(1, NioHandler.newFactory());
    // EventLoopGroup workerGroup = new MultithreadEventLoopGroup(NioHandler.newFactory());
    // try {
    // ServerBootstrap b = new ServerBootstrap();
    // b.group(bossGroup, workerGroup)
    // .channel(NioServerSocketChannel.class)
    // .handler(new LoggingHandler(LogLevel.INFO))
    // .childHandler(new HttpSnoopServerInitializer());
    //
    // Channel ch = b.bind(8080).sync().channel();
    //
    // ch.closeFuture().sync();
    // } catch (InterruptedException e) {
    // e.printStackTrace();
    // } finally {
    // bossGroup.shutdownGracefully();
    // workerGroup.shutdownGracefully();
    // }
    //
    // }
    //
    // public static class HttpSnoopServerInitializer extends ChannelInitializer<SocketChannel> {
    //
    //
    // public HttpSnoopServerInitializer() {
    // }
    //
    // @Override
    // public void initChannel(SocketChannel ch) {
    // ChannelPipeline p = ch.pipeline();
    //
    // p.addLast(new HttpRequestDecoder());
    // // Uncomment the following line if you don't want to handle HttpChunks.
    // //p.addLast(new HttpObjectAggregator(1048576));
    // p.addLast(new HttpResponseEncoder());
    // // Remove the following line if you don't want automatic content compression.
    // //p.addLast(new HttpContentCompressor());
    // p.addLast(new HttpSnoopServerHandler());
    // }
    // }
    //
    //
    // public static class HttpSnoopServerHandler extends SimpleChannelInboundHandler<Object> {
    // private HttpRequest request;
    // private final StringBuilder buf = new StringBuilder();
    // @Override
    // protected void messageReceived(ChannelHandlerContext ctx, Object msg) throws Exception {
    // if (msg instanceof HttpRequest) {
    // HttpRequest request = this.request = (HttpRequest) msg;
    //
    // buf.setLength(0);
    // buf.append("WELCOME TO THE WILD WILD WEB SERVER\r\n");
    // buf.append("===================================\r\n");
    //
    // buf.append("VERSION: ").append(request.protocolVersion()).append("\r\n");
    // buf.append("HOSTNAME: ").append(request.headers().get(HttpHeaderNames.HOST, "unknown")).append("\r\n");
    // buf.append("REQUEST_URI: ").append(request.uri()).append("\r\n\r\n");
    //
    // HttpHeaders headers = request.headers();
    // if (!headers.isEmpty()) {
    // for (Map.Entry<CharSequence, CharSequence> h: headers) {
    // CharSequence key = h.getKey();
    // CharSequence value = h.getValue();
    // buf.append("HEADER: ").append(key).append(" = ").append(value).append("\r\n");
    // }
    // buf.append("\r\n");
    // }
    //
    // appendDecoderResult(buf, request);
    // }
    //
    // if (msg instanceof HttpContent) {
    // HttpContent httpContent = (HttpContent) msg;
    //
    // ByteBuf content = httpContent.content();
    // if (content.isReadable()) {
    // buf.append("CONTENT: ");
    // buf.append(content.toString(CharsetUtil.UTF_8));
    // buf.append("\r\n");
    // appendDecoderResult(buf, request);
    // }
    //
    // if (msg instanceof LastHttpContent) {
    // buf.append("END OF CONTENT\r\n");
    //
    // LastHttpContent trailer = (LastHttpContent) msg;
    // if (!trailer.trailingHeaders().isEmpty()) {
    // buf.append("\r\n");
    // for (CharSequence name: trailer.trailingHeaders().names()) {
    // for (CharSequence value: trailer.trailingHeaders().getAll(name)) {
    // buf.append("TRAILING HEADER: ");
    // buf.append(name).append(" = ").append(value).append("\r\n");
    // }
    // }
    // buf.append("\r\n");
    // }
    //
    // if (!writeResponse(trailer, ctx)) {
    // // If keep-alive is off, close the connection once the content is fully written.
    // ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    // }
    // }
    // }
    // }
    //
    // }
    // }
    // }
}
