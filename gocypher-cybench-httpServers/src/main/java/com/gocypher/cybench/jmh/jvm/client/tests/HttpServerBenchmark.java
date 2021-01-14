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
package com.gocypher.cybench.jmh.jvm.client.tests;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.openjdk.jmh.annotations.*;
import org.takes.facets.fork.FkRegex;
import org.takes.facets.fork.TkFork;
import org.takes.http.Exit;
import org.takes.http.FtBasic;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.*;
import javax.xml.ws.http.HTTPBinding;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "true")
@BenchmarkMetaData(key = "context", value = "HttpServers")
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "version", value = "1.0")
@BenchmarkMetaData(key = "description", value = "HttpServers implementation benchmark")
public class HttpServerBenchmark {

    @Param({"This is response"})
    private static String responseString = "\"This is response\"";

    @Benchmark
    @BenchmarkMode(value = Mode.Throughput)
    // @OperationsPerInvocation(value = 1000000)
    @BenchmarkTag(tag = "76ca1ef3-aa04-4aaf-865f-1b4a1f1267cc")
    public void javaServerBenchmark(JavaServer server) {
        get();
    }

    @Benchmark
    @BenchmarkMode(value = Mode.Throughput)
    // @OperationsPerInvocation(value = 1000000)
    @BenchmarkTag(tag = "48ea9d22-8af0-4649-b661-e71556fc8952")
    public void javaWebServiceBenchmark(JavaWebServiceProvider server) {
        get();
    }

    @Benchmark
    @BenchmarkMode(value = Mode.Throughput)
    @BenchmarkTag(tag = "6198f242-6081-4351-980c-64f74c6ab855")
    public // @OperationsPerInvocation(value = 1000000)
    void takesBenchmark(TakesWebServer server) {
        get();
    }

    private void get() {
        HttpURLConnection con = null;
        try {
            URL url = new URL("http://127.0.0.1:8080/test");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuffer content = new StringBuffer();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @State(Scope.Benchmark)
    public static class JavaServer {

        String response = HttpServerBenchmark.responseString;

        @Setup
        public void setup() {
            HttpServer server = null;
            try {
                server = HttpServer.create(new InetSocketAddress(8080), 0);
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
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    @State(Scope.Benchmark)
    @WebServiceProvider
    @ServiceMode(value = Service.Mode.PAYLOAD)
    public static class JavaWebServiceProvider implements Provider<Source> {

        public Source invoke(Source request) {
            return new StreamSource(new StringReader(responseString));
        }

        @Setup
        public void setup() {
            String address = "http://127.0.0.1:8080/";
            Endpoint.create(HTTPBinding.HTTP_BINDING, new JavaWebServiceProvider()).publish(address);
        }
    }

    @State(Scope.Benchmark)
    public static class TakesWebServer {

        @Setup
        public void setup() {
            new Thread(() ->
            {
                try {
                    new FtBasic(new TkFork(new FkRegex("/test", responseString)), 8080).start(Exit.NEVER);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }
}
