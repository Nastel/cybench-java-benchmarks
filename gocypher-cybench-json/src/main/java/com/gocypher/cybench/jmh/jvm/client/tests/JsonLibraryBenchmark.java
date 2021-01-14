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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.utils.CyBenchCounters;
import com.google.gson.Gson;
import com.jsoniter.JsonIterator;
import com.jsoniter.output.JsonStream;
import com.owlike.genson.Genson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import io.quarkus.qson.generator.QsonMapper;
import org.boon.json.JsonFactory;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

@State(Scope.Benchmark)
@BenchmarkMetaData(key="isLibraryBenchmark", value="true")
@BenchmarkMetaData(key="context", value="JSON_parsers")
@BenchmarkMetaData(key="domain", value="java")
@BenchmarkMetaData(key="version", value="1.0.0")
@BenchmarkMetaData(key="description", value="Serialization/deserialization of object/JSON (in memory)")

public class JsonLibraryBenchmark {

    /**
     * <b>GSON </b>
     * This benchmark deserializes small 45 bytes json {@link SmallJson#actualJson}
     * with GSON 2.8.6
     */
    @Benchmark
    @BenchmarkTag(tag = "40541ade-f193-43b1-9b47-bf3fd1bfe91d")
    @BenchmarkMetaData(key="api", value="gson")
    @BenchmarkMetaData(key="libVendor", value="com.google")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/google/gson")
    @BenchmarkMetaData(key="libVersion", value="2.8.6")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.google.gson")
    @BenchmarkMetaData(key="libDescription", value="Gson JSON library")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object gsonWithSmallJSON(SmallJson json, GsonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "c72a5095-f613-4176-a76a-bcef763b6afe")
    @BenchmarkMetaData(key="api", value="gson")
    @BenchmarkMetaData(key="libVendor", value="com.google")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/google/gson")
    @BenchmarkMetaData(key="libVersion", value="2.8.6")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.google.gson")
    @BenchmarkMetaData(key="libDescription", value="Gson JSON library")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object gsonWithAverageJSON(AverageJson json, GsonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }


    @Benchmark
    @BenchmarkTag(tag = "45da6fbf-ceb1-4e99-9fdb-94994f8b3d1d")
    @BenchmarkMetaData(key="api", value="gson")
    @BenchmarkMetaData(key="libVendor", value="com.google")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/google/gson")
    @BenchmarkMetaData(key="libVersion", value="2.8.6")
    @BenchmarkMetaData(key="dataSize", value="440203")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.google.gson")
    @BenchmarkMetaData(key="libDescription", value="Gson JSON library")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object gsonWithBigJSON(BigJson json, GsonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "6e15fcde-b18d-4a43-9b0d-327727a1c18f")
    @BenchmarkMetaData(key="api", value="moshi")
    @BenchmarkMetaData(key="libVendor", value="com.squareup")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/square/moshi")
    @BenchmarkMetaData(key="libVersion", value="1.11.0")
    @BenchmarkMetaData(key="dataSize", value="440203")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.squareup.moshi")
    @BenchmarkMetaData(key="libDescription", value="Modern JSON library for Java ")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object moshiWithBigJSON(BigJson json, MoshiDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "91acab8e-59f3-40db-8afb-a14db22369b2")
    @BenchmarkMetaData(key="api", value="moshi")
    @BenchmarkMetaData(key="libVendor", value="com.squareup")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/square/moshi")
    @BenchmarkMetaData(key="libVersion", value="1.11.0")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.squareup.moshi")
    @BenchmarkMetaData(key="libDescription", value="Modern JSON library for Java ")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object moshiWithSmallJSON(SmallJson json, MoshiDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "b9fe98b5-8c12-477c-9422-ffdb23a2cac0")
    @BenchmarkMetaData(key="api", value="moshi")
    @BenchmarkMetaData(key="libVendor", value="com.squareup")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/square/moshi")
    @BenchmarkMetaData(key="libVersion", value="1.11.0")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.squareup.moshi")
    @BenchmarkMetaData(key="libDescription", value="Modern JSON library for Java ")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object moshiWithAverageJSON(AverageJson json, MoshiDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "71826df8-c9ac-483b-b391-85d1a86e6000")
    @BenchmarkMetaData(key="api", value="jsoniter")
    @BenchmarkMetaData(key="libVendor", value="com.jsoniter")
    @BenchmarkMetaData(key="libUrl", value="http://jsoniter.com/")
    @BenchmarkMetaData(key="libVersion", value="0.9.23")
    @BenchmarkMetaData(key="dataSize", value="440203")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.jsoniter")
    @BenchmarkMetaData(key="libDescription", value="Fast and flexible JSON parser available in Java and Go")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object jsonIteratorWithBigJSON(BigJson json, JSonIteratorDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "3eb37d7c-9345-4d70-8299-d592a7bcf541")
    @BenchmarkMetaData(key="api", value="jsoniter")
    @BenchmarkMetaData(key="libVendor", value="com.jsoniter")
    @BenchmarkMetaData(key="libUrl", value="http://jsoniter.com/")
    @BenchmarkMetaData(key="libVersion", value="0.9.23")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.jsoniter")
    @BenchmarkMetaData(key="libDescription", value="Fast and flexible JSON parser available in Java and Go")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object jsonIteratorWithSmallJSON(SmallJson json, JSonIteratorDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "9b288275-ecc9-41de-a20a-c4ae4c233b04")
    @BenchmarkMetaData(key="api", value="jsoniter")
    @BenchmarkMetaData(key="libVendor", value="com.jsoniter")
    @BenchmarkMetaData(key="libUrl", value="http://jsoniter.com/")
    @BenchmarkMetaData(key="libVersion", value="0.9.23")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.jsoniter")
    @BenchmarkMetaData(key="libDescription", value="Fast and flexible JSON parser available in Java and Go")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object jsonIteratorWithAverageJSON(AverageJson json, JSonIteratorDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "39f78124-8aef-4f94-a0ca-95cb44302d32")
    @BenchmarkMetaData(key="api", value="qson")
    @BenchmarkMetaData(key="libVendor", value="io.quarkus")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/quarkusio/qson")
    @BenchmarkMetaData(key="libVersion", value="1.0.Final")
    @BenchmarkMetaData(key="dataSize", value="440203")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="io.quarkus.qson")
    @BenchmarkMetaData(key="libDescription", value="JSON serializer primary for QUARKUS/GRAAL")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object qsonWithBigJSON(BigJson json, QSonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "c77e0a09-6893-4cb7-96d9-1d422f24e8ab")
    @BenchmarkMetaData(key="api", value="qson")
    @BenchmarkMetaData(key="libVendor", value="io.quarkus")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/quarkusio/qson")
    @BenchmarkMetaData(key="libVersion", value="1.0.Final")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="io.quarkus.qson")
    @BenchmarkMetaData(key="libDescription", value="JSON serializer primary for QUARKUS/GRAAL")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object qsonWithSmallJSON(SmallJson json, QSonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "e211ae89-ca0e-45c2-b5e1-6e97d7570537")
    @BenchmarkMetaData(key="api", value="qson")
    @BenchmarkMetaData(key="libVendor", value="io.quarkus")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/quarkusio/qson")
    @BenchmarkMetaData(key="libVersion", value="1.0.Final")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="io.quarkus.qson")
    @BenchmarkMetaData(key="libDescription", value="JSON serializer primary for QUARKUS/GRAAL")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object qsonWithAverageJSON(AverageJson json, QSonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "fef9b157-1b3a-4c3a-8225-d6759775bb23")
    @BenchmarkMetaData(key="api", value="genson")
    @BenchmarkMetaData(key="libVendor", value="com.owlike")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/owlike/genson")
    @BenchmarkMetaData(key="libVersion", value="1.6")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.owlike.genson")
    @BenchmarkMetaData(key="libDescription", value="Genson API is designed to be easy to use, it handles for you all the databinding, streaming and much more.")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object gensonWithAverageJSON(AverageJson json, GensonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "0d64df0f-e8f1-4f5c-a862-e6a8bacf1422")
    @BenchmarkMetaData(key="api", value="genson")
    @BenchmarkMetaData(key="libVendor", value="com.owlike")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/owlike/genson")
    @BenchmarkMetaData(key="libVersion", value="1.6")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.owlike.genson")
    @BenchmarkMetaData(key="libDescription", value="Genson API is designed to be easy to use, it handles for you all the databinding, streaming and much more.")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object gensonWithBigJSON(BigJson json, GensonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "d6402516-6bb0-424c-8c3a-279ae80f13e6")
    @BenchmarkMetaData(key="api", value="genson")
    @BenchmarkMetaData(key="libVendor", value="com.owlike")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/owlike/genson")
    @BenchmarkMetaData(key="libVersion", value="1.6")
    @BenchmarkMetaData(key="dataSize", value="428000")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.owlike.genson")
    @BenchmarkMetaData(key="libDescription", value="Genson API is designed to be easy to use, it handles for you all the databinding, streaming and much more.")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object gensonWithSmallJSON(SmallJson json, GensonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "38cddf00-9a51-4351-bd30-b9a6ba0d195c")
    @BenchmarkMetaData(key="api", value="jackson")
    @BenchmarkMetaData(key="libVendor", value="com.fasterxml.jackson")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/FasterXML/jackson")
    @BenchmarkMetaData(key="libVersion", value="2.11.2")
    @BenchmarkMetaData(key="libDescription", value="Core Jackson processing abstractions (aka Streaming API), implementation for JSON")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.fasterxml.jackson.core.jackson")
    @BenchmarkMetaData(key="libDescription", value="Jackson is most popular java based library to serialize or map java objects to JSON and vice versa.")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object jacksonWithSmallJSON(SmallJson json, JacksonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "1ba08cd2-f2da-43a7-b720-8ea886543503")
    @BenchmarkMetaData(key="api", value="jackson")
    @BenchmarkMetaData(key="libVendor", value="com.fasterxml.jackson")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/FasterXML/jackson")
    @BenchmarkMetaData(key="libVersion", value="2.11.2")
    @BenchmarkMetaData(key="libDescription", value="Core Jackson processing abstractions (aka Streaming API), implementation for JSON")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.fasterxml.jackson.core.jackson")
    @BenchmarkMetaData(key="libDescription", value="Jackson is most popular java based library to serialize or map java objects to JSON and vice versa.")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object jacksonWithAverageJSON(AverageJson json, JacksonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "813b142a-e8c9-4e6e-b3fb-ad3512517e7b")
    @BenchmarkMetaData(key="api", value="jackson")
    @BenchmarkMetaData(key="libVendor", value="com.fasterxml.jackson")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/FasterXML/jackson")
    @BenchmarkMetaData(key="libVersion", value="2.11.2")
    @BenchmarkMetaData(key="libDescription", value="Core Jackson processing abstractions (aka Streaming API), implementation for JSON")
    @BenchmarkMetaData(key="dataSize", value="440203")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.fasterxml.jackson.core.jackson")
    @BenchmarkMetaData(key="libDescription", value="Jackson is most popular java based library to serialize or map java objects to JSON and vice versa.")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object jacksonWithBigJSON(BigJson json, JacksonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "351f821f-ca69-4e35-9a82-e9c9f34478f9")
    @BenchmarkMetaData(key="api", value="boon")
    @BenchmarkMetaData(key="libVendor", value="io.fastjson")
    @BenchmarkMetaData(key="libUrl", value="https://mvnrepository.com/artifact/io.fastjson/boon")
    @BenchmarkMetaData(key="libVersion", value="0.34")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="io.fastjson.boon")
    @BenchmarkMetaData(key="libDescription", value="Simple opinionated Java for the novice to expert level Java Programmer. Low Ceremony. High Productivit y. A real boon to Java to developers!")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object boonWithSmallJSON(SmallJson json, JacksonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "ec3fb51a-270b-4249-ae5f-9b70e2563e1c")
    @BenchmarkMetaData(key="api", value="boon")
    @BenchmarkMetaData(key="libVendor", value="io.fastjson")
    @BenchmarkMetaData(key="libUrl", value="https://mvnrepository.com/artifact/io.fastjson/boon")
    @BenchmarkMetaData(key="libVersion", value="0.34")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="io.fastjson.boon")
    @BenchmarkMetaData(key="libDescription", value="Simple opinionated Java for the novice to expert level Java Programmer. Low Ceremony. High Productivit y. A real boon to Java to developers!")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object boonWithAverageJSON(AverageJson json, BoonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    @BenchmarkTag(tag = "c437b7ab-49c1-462c-885e-6da3c144f7dd")
    @BenchmarkMetaData(key="api", value="boon")
    @BenchmarkMetaData(key="libVendor", value="io.fastjson")
    @BenchmarkMetaData(key="libUrl", value="https://mvnrepository.com/artifact/io.fastjson/boon")
    @BenchmarkMetaData(key="libVersion", value="0.34")
    @BenchmarkMetaData(key="dataSize", value="440203")
    @BenchmarkMetaData(key="actionName", value="deserialize")
    @BenchmarkMetaData(key="libSymbolicName", value="io.fastjson.boon")
    @BenchmarkMetaData(key="libDescription", value="Simple opinionated Java for the novice to expert level Java Programmer. Low Ceremony. High Productivit y. A real boon to Java to developers!")
    @BenchmarkMetaData(key="title", value="Deserialization of JSON")
    public Object boonWithBigJSON(BigJson json, JacksonDeserialize impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    // ######################################################################
    // ####################Deserialisation #################################
    // ######################################################################
    @Benchmark
    @BenchmarkTag(tag = "3158b5a3-b37d-40f3-9c7b-aa4d422fb4af")
    @BenchmarkMetaData(key="api", value="gson")
    @BenchmarkMetaData(key="libVendor", value="com.google")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/google/gson")
    @BenchmarkMetaData(key="libVersion", value="2.8.6")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.google.gson")
    @BenchmarkMetaData(key="libDescription", value="Gson JSON library")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object gsonWithSmallObject(SmallJson json, GsonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "4a475f6f-0f1a-4633-a786-6083da40b898")
    @BenchmarkMetaData(key="api", value="gson")
    @BenchmarkMetaData(key="libVendor", value="com.google")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/google/gson")
    @BenchmarkMetaData(key="libVersion", value="2.8.6")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.google.gson")
    @BenchmarkMetaData(key="libDescription", value="Gson JSON library")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object gsonWithAverageObject(AverageJson json, GsonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "28975a65-c685-464c-acee-aa4a1e7b51cd")
    @BenchmarkMetaData(key="api", value="gson")
    @BenchmarkMetaData(key="libVendor", value="com.google")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/google/gson")
    @BenchmarkMetaData(key="libVersion", value="2.8.6")
    @BenchmarkMetaData(key="dataSize", value="440203")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.google.gson")
    @BenchmarkMetaData(key="libDescription", value="Gson JSON library")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object gsonWithBigObject(BigJson json, GsonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "74af4f22-c7a9-49f7-947c-54b5e283acc9")
    @BenchmarkMetaData(key="api", value="jackson")
    @BenchmarkMetaData(key="libVendor", value="com.fasterxml.jackson")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/FasterXML/jackson")
    @BenchmarkMetaData(key="libVersion", value="2.11.2")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.fasterxml.jackson.core.jackson")
    @BenchmarkMetaData(key="libDescription", value="Jackson is most popular java based library to serialize or map java objects to JSON and vice versa.")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object jacksonWithSmallObject(SmallJson json, JacksonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "1aea3b3a-a521-4ed4-97fb-adc6126f398e")
    @BenchmarkMetaData(key="api", value="jackson")
    @BenchmarkMetaData(key="libVendor", value="com.fasterxml.jackson")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/FasterXML/jackson")
    @BenchmarkMetaData(key="libVersion", value="2.11.2")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.fasterxml.jackson.core.jackson")
    @BenchmarkMetaData(key="libDescription", value="Jackson is most popular java based library to serialize or map java objects to JSON and vice versa.")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object jacksonWithAverageObject(AverageJson json, JacksonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "4877323c-5def-41da-830f-d518d29c5da5")
    @BenchmarkMetaData(key="api", value="jackson")
    @BenchmarkMetaData(key="libVendor", value="com.fasterxml.jackson")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/FasterXML/jackson")
    @BenchmarkMetaData(key="libVersion", value="2.11.2")
    @BenchmarkMetaData(key="dataSize", value="440203")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.fasterxml.jackson.core.jackson")
    @BenchmarkMetaData(key="libDescription", value="Jackson is most popular java based library to serialize or map java objects to JSON and vice versa.")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object jacksonWithBigObject(BigJson json, JacksonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "6f17dcf4-460e-4a57-8959-7cf20d1fb92e")
    @BenchmarkMetaData(key="api", value="boon")
    @BenchmarkMetaData(key="libVendor", value="io.fastjson")
    @BenchmarkMetaData(key="libUrl", value="https://mvnrepository.com/artifact/io.fastjson/boon")
    @BenchmarkMetaData(key="libVersion", value="0.34")
    @BenchmarkMetaData(key="dataSize", value="440203")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="io.fastjson.boon")
    @BenchmarkMetaData(key="libDescription", value="Simple opinionated Java for the novice to expert level Java Programmer. Low Ceremony. High Productivit y. A real boon to Java to developers!")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object boonWithBigObject(BigJson json, JacksonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "fd75ae2e-28c2-4c23-8cb9-a4d399551041")
    @BenchmarkMetaData(key="api", value="boon")
    @BenchmarkMetaData(key="libVendor", value="io.fastjson")
    @BenchmarkMetaData(key="libUrl", value="https://mvnrepository.com/artifact/io.fastjson/boon")
    @BenchmarkMetaData(key="libVersion", value="0.34")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="io.fastjson.boon")
    @BenchmarkMetaData(key="libDescription", value="Simple opinionated Java for the novice to expert level Java Programmer. Low Ceremony. High Productivit y. A real boon to Java to developers!")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object boonWithSmallObject(SmallJson json, JacksonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "64eba044-96dc-4ac8-91aa-25a95e63745a")
    @BenchmarkMetaData(key="api", value="boon")
    @BenchmarkMetaData(key="libVendor", value="io.fastjson")
    @BenchmarkMetaData(key="libUrl", value="https://mvnrepository.com/artifact/io.fastjson/boon")
    @BenchmarkMetaData(key="libVersion", value="0.34")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="io.fastjson.boon")
    @BenchmarkMetaData(key="libDescription", value="Simple opinionated Java for the novice to expert level Java Programmer. Low Ceremony. High Productivit y. A real boon to Java to developers!")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object boonWithAverageObject(AverageJson json, BoonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "23374b54-1256-46f8-9621-87d1eccf43c1")
    @BenchmarkMetaData(key="api", value="genson")
    @BenchmarkMetaData(key="libVendor", value="com.owlike")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/owlike/genson")
    @BenchmarkMetaData(key="libVersion", value="1.6")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.owlike.genson")
    @BenchmarkMetaData(key="libDescription", value="Genson API is designed to be easy to use, it handles for you all the databinding, streaming and much more.")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object gensonWithBigObject(BigJson json, GensonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "e4713c41-daab-4a7c-b688-ea14534f7053")
    @BenchmarkMetaData(key="api", value="genson")
    @BenchmarkMetaData(key="libVendor", value="com.owlike")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/owlike/genson")
    @BenchmarkMetaData(key="libVersion", value="1.6")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.owlike.genson")
    @BenchmarkMetaData(key="libDescription", value="Genson API is designed to be easy to use, it handles for you all the databinding, streaming and much more.")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object gensonWithSmallObject(SmallJson json, GensonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "ac39da76-28a2-40f3-a8b4-1052c73ddacf")
    @BenchmarkMetaData(key="api", value="genson")
    @BenchmarkMetaData(key="libVendor", value="com.owlike")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/owlike/genson")
    @BenchmarkMetaData(key="libVersion", value="1.6")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.owlike.genson")
    @BenchmarkMetaData(key="libDescription", value="Genson API is designed to be easy to use, it handles for you all the databinding, streaming and much more.")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object gensonWithAverageObject(AverageJson json, GensonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "e93a9640-bf4b-4b32-8d6b-4c5792cbe50f")
    @BenchmarkMetaData(key="api", value="moshi")
    @BenchmarkMetaData(key="libVendor", value="com.squareup")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/square/moshi")
    @BenchmarkMetaData(key="libVersion", value="1.11.0")
    @BenchmarkMetaData(key="dataSize", value="440203")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.squareup.moshi")
    @BenchmarkMetaData(key="libDescription", value="Modern JSON library for Java ")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object moshiWithBigObject(BigJson json, MoshiSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "23084973-5f77-4e0e-9e3b-52cb756321a0")
    @BenchmarkMetaData(key="api", value="moshi")
    @BenchmarkMetaData(key="libVendor", value="com.squareup")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/square/moshi")
    @BenchmarkMetaData(key="libVersion", value="1.11.0")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.squareup.moshi")
    @BenchmarkMetaData(key="libDescription", value="Modern JSON library for Java ")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object moshiWithSmallObject(SmallJson json, MoshiSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "0050f409-d663-4a57-bffb-cf94b2ef0c26")
    @BenchmarkMetaData(key="api", value="moshi")
    @BenchmarkMetaData(key="libVendor", value="com.squareup")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/square/moshi")
    @BenchmarkMetaData(key="libVersion", value="1.11.0")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.squareup.moshi")
    @BenchmarkMetaData(key="libDescription", value="Modern JSON library for Java ")
    @BenchmarkMetaData(key="title", value="Serialization of Object")
    public Object moshiWithAverageObject(AverageJson json, MoshiSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "43358e4e-9dfc-45f1-87d4-bed656e93130")
    @BenchmarkMetaData(key="api", value="qson")
    @BenchmarkMetaData(key="libVendor", value="io.quarkus")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/quarkusio/qson")
    @BenchmarkMetaData(key="libVersion", value="1.0.Final")
    @BenchmarkMetaData(key="dataSize", value="440203")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="io.quarkus.qson")
    @BenchmarkMetaData(key="libDescription", value="JSON serializer primary for QUARKUS/GRAAL")
    @BenchmarkMetaData(key="title", value="Serialization of JSON")
    public Object qsonWithBigObject(BigJson json, QsonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "476f6e54-87db-4351-867d-47303432979b")
    @BenchmarkMetaData(key="api", value="qson")
    @BenchmarkMetaData(key="libVendor", value="io.quarkus")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/quarkusio/qson")
    @BenchmarkMetaData(key="libVersion", value="1.0.Final")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="io.quarkus.qson")
    @BenchmarkMetaData(key="libDescription", value="JSON serializer primary for QUARKUS/GRAAL")
    @BenchmarkMetaData(key="title", value="Serialization of JSON")
    public Object qsonWithSmallObject(SmallJson json, QsonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "ac298664-6455-4128-9c2a-33615db2d30e")
    @BenchmarkMetaData(key="api", value="qson")
    @BenchmarkMetaData(key="libVendor", value="io.quarkus")
    @BenchmarkMetaData(key="libUrl", value="https://github.com/quarkusio/qson")
    @BenchmarkMetaData(key="libVersion", value="1.0.Final")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="io.quarkus.qson")
    @BenchmarkMetaData(key="libDescription", value="JSON serializer primary for QUARKUS/GRAAL")
    @BenchmarkMetaData(key="title", value="Serialization of JSON")
    public Object qsonWithAverageObject(AverageJson json, QsonSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "f36defd9-ffc3-416d-8dc0-8cfedfb312b8")
    @BenchmarkMetaData(key="api", value="jsoniter")
    @BenchmarkMetaData(key="libVendor", value="com.jsoniter")
    @BenchmarkMetaData(key="libUrl", value="http://jsoniter.com/")
    @BenchmarkMetaData(key="libVersion", value="0.9.23")
    @BenchmarkMetaData(key="dataSize", value="440203")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.jsoniter")
    @BenchmarkMetaData(key="libDescription", value="Fast and flexible JSON parser available in Java and Go")
    @BenchmarkMetaData(key="title", value="Serialization of JSON")
    public Object jsonIteratorWithBigObject(BigJson json, JsonIteratorSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "7d6b9aa5-195f-4582-9739-90783405bc89")
    @BenchmarkMetaData(key="api", value="jsoniter")
    @BenchmarkMetaData(key="libVendor", value="com.jsoniter")
    @BenchmarkMetaData(key="libUrl", value="http://jsoniter.com/")
    @BenchmarkMetaData(key="libVersion", value="0.9.23")
    @BenchmarkMetaData(key="dataSize", value="45")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.jsoniter")
    @BenchmarkMetaData(key="libDescription", value="Fast and flexible JSON parser available in Java and Go")
    @BenchmarkMetaData(key="title", value="Serialization of JSON")
    public Object jsonIteratorWithSmallObject(SmallJson json, JsonIteratorSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @Benchmark
    @BenchmarkTag(tag = "ec2cf16b-5e7f-418e-8fe0-f5a7b894711b")
    @BenchmarkMetaData(key="api", value="jsoniter")
    @BenchmarkMetaData(key="libVendor", value="com.jsoniter")
    @BenchmarkMetaData(key="libUrl", value="http://jsoniter.com/")
    @BenchmarkMetaData(key="libVersion", value="0.9.23")
    @BenchmarkMetaData(key="dataSize", value="4280")
    @BenchmarkMetaData(key="actionName", value="serialize")
    @BenchmarkMetaData(key="libSymbolicName", value="com.jsoniter")
    @BenchmarkMetaData(key="libDescription", value="Fast and flexible JSON parser available in Java and Go")
    @BenchmarkMetaData(key="title", value="Serialization of JSON")
    public Object jsonIteratorWithAverageObject(AverageJson json, JsonIteratorSerialize impl, Blackhole bh) {
        return impl.doJob(json.object);
    }

    @TearDown(Level.Iteration)
    public void clearIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
    }

    interface IMPL {

        Object doJob(String json);
    }

    interface IMPLSerialize {

        String doJob(Object o);
    }

    @State(Scope.Thread)
    public static class SmallJson {

        public String actualJson = "{\"name\":\"Bob\",\"salary\":10000, \"spouse\": null}";

        public Object object = new JacksonDeserialize().doJob(actualJson);
    }

    @State(Scope.Benchmark)
    public static class BigJson {

        public String actualJson;

        public Object object;

        @Setup(value = Level.Trial)
        public void setup() {
            actualJson = new BufferedReader(new InputStreamReader(JsonLibraryBenchmark.class.getResourceAsStream("big.json"))).lines().collect(Collectors.joining("\n"));
            object = new JacksonDeserialize().doJob(actualJson);
        }
    }

    @State(Scope.Benchmark)
    public static class AverageJson {

        public String actualJson = "{\n" + "    \"jsonrpc\": \"2.0\",\n" + "    \"id\": 2,\n" + "    \"result\": {\n" + "        \"difficulty\": \"0x574767a266a\",\n" + "        \"extraData\": \"0xd583010103844765746885676f312e35856c696e7578\",\n" + "        \"gasLimit\": \"0x2fefd8\",\n" + "        \"gasUsed\": \"0xf618\",\n" + "        \"hash\": \"0x26c947da5bc4bef6b72ee6fdcf07b824999a7dfabb10126b05889e259b904500\",\n" + "        \"logsBloom\": \"0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\",\n" + "        \"miner\": \"0x1dcb8d1f0fcc8cbc8c2d76528e877f915e299fbe\",\n" + "        \"mixHash\": \"0xa153bb2070c9b70974651559c50974e0c0dff832866919a7dda481331b778beb\",\n" + "        \"nonce\": \"0x8db450462af941d9\",\n" + "        \"number\": \"0x58228\",\n" + "        \"parentHash\": \"0x4a06ca1d839bfef2f65fa9a6e1f40414d2adcb5040f0280ceb8b1ca468db1cd9\",\n" + "        \"receiptsRoot\": \"0x6c1a596fc98a157722b13a6e2b4057b4f57cb5def984f77ac4d7e04ddcfa034b\",\n" + "        \"sha3Uncles\": \"0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347\",\n" + "        \"size\": \"0x371\",\n" + "        \"stateRoot\": \"0x98e1916d318b22e29fd556f61fad4c8e6f571300ad598720ede28b4ac295e7de\",\n" + "        \"timestamp\": \"0x5618b175\",\n" + "        \"totalDifficulty\": \"0x189fe5d12a177049\",\n" + "        \"transactions\": [\n" + "            {\n" + "                \"blockHash\": \"0x26c947da5bc4bef6b72ee6fdcf07b824999a7dfabb10126b05889e259b904500\",\n" + "                \"blockNumber\": \"0x58228\",\n" + "                \"from\": \"0x1dcb8d1f0fcc8cbc8c2d76528e877f915e299fbe\",\n" + "                \"gas\": \"0x15f90\",\n" + "                \"gasPrice\": \"0xba43b7400\",\n" + "                \"hash\": \"0xfa7fc9217bf82d8e67b6fad3bfdaf7d582b68e58656529e64ccc8b587ea1a09e\",\n" + "                \"input\": \"0x\",\n" + "                \"nonce\": \"0xdfe9\",\n" + "                \"r\": \"0xf6c26c89632f0a31cb85da9e78a16a2f23c147d53f5dc4fa68b22a15a503a760\",\n" + "                \"s\": \"0x7b13fc9ea7919d79fc8b7ca6bfd9bb5c698af3b57ee6f79b57b832b5eb4b63b\",\n" + "                \"to\": \"0xb1abce2918e21ddb93aa452731a12672a3d9f75a\",\n" + "                \"transactionIndex\": \"0x0\",\n" + "                \"v\": \"0x1b\",\n" + "                \"value\": \"0xd4331d13a141e800\"\n" + "            },\n" + "            {\n" + "                \"blockHash\": \"0x26c947da5bc4bef6b72ee6fdcf07b824999a7dfabb10126b05889e259b904500\",\n" + "                \"blockNumber\": \"0x58228\",\n" + "                \"from\": \"0x2a65aca4d5fc5b5c859090a6c34d164135398226\",\n" + "                \"gas\": \"0x15f90\",\n" + "                \"gasPrice\": \"0xba43b7400\",\n" + "                \"hash\": \"0xa1a3b3d4cc1f23b8518c5b885d491c4a1da179c9252339d7e886c6f0913de9ef\",\n" + "                \"input\": \"0x\",\n" + "                \"nonce\": \"0x171e\",\n" + "                \"r\": \"0xc0bd5241b4f922997f7f6cf28f85304785f88d2ca6d8f2fe64304d269ec4afce\",\n" + "                \"s\": \"0x5d6537000ebea42ef1b37caeaee2fae3145e25cf5f4f4fec0faf066f089ca979\",\n" + "                \"to\": \"0xfecab546498f74591d4f6d448a4a63552850f122\",\n" + "                \"transactionIndex\": \"0x1\",\n" + "                \"v\": \"0x1c\",\n" + "                \"value\": \"0x13e6a003e7e56c00\"\n" + "            },\n" + "            {\n" + "                \"blockHash\": \"0x26c947da5bc4bef6b72ee6fdcf07b824999a7dfabb10126b05889e259b904500\",\n" + "                \"blockNumber\": \"0x58228\",\n" + "                \"from\": \"0x1dcb8d1f0fcc8cbc8c2d76528e877f915e299fbe\",\n" + "                \"gas\": \"0x15f90\",\n" + "                \"gasPrice\": \"0xba43b7400\",\n" + "                \"hash\": \"0xa1a83a23ede4e5f4e4a4099cfd7a1764248b4f5b90b82e48dc5f0aebd7faab83\",\n" + "                \"input\": \"0x\",\n" + "                \"nonce\": \"0xdfea\",\n" + "                \"r\": \"0xfdc937fcf20a44bb15cf8e20c313c9c98f5a5e133d2463774bf649fc90eecfb1\",\n" + "                \"s\": \"0x7e5f3f214a0f0e3fa233665289d57ebed73947c426ed6097d6978e5596d2ac1c\",\n" + "                \"to\": \"0x7c5080988c6d91d090c23d54740f856c69450b29\",\n" + "                \"transactionIndex\": \"0x2\",\n" + "                \"v\": \"0x1b\",\n" + "                \"value\": \"0x43feeb4cddfc00\"\n" + "            }\n" + "        ],\n" + "        \"transactionsRoot\": \"0x76c1e06c9486d20d8860aa80af9ca1b17138a38ea770f5242d7aa723d90fa0eb\",\n" + "        \"uncles\": []\n" + "    }\n" + "}";

        public Object object = new JacksonDeserialize().doJob(actualJson);
    }

    @State(Scope.Benchmark)
    public static class LargeJson {
    }

    @State(Scope.Benchmark)
    public static class GsonDeserialize implements IMPL {

        Gson gson = new Gson();

        @Override
        public Object doJob(String json) {
            return gson.fromJson(json, Map.class);
        }
    }

    @State(Scope.Benchmark)
    public static class JacksonDeserialize implements IMPL {

        ObjectMapper om = new ObjectMapper();

        @Override
        public Object doJob(String json) {
            try {
                return om.readValue(json, Map.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @State(Scope.Benchmark)
    public static class BoonDeserialize implements IMPL {

        org.boon.json.ObjectMapper om = JsonFactory.create();

        @Override
        public Object doJob(String json) {
            try {
                return om.readValue(json, Map.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @State(Scope.Benchmark)
    public static class MoshiDeserialize implements IMPL {

        private JsonAdapter<Map> jsonAdapter;

        @Setup
        public void setup() {
            Moshi moshi = new Moshi.Builder().build();
            jsonAdapter = moshi.adapter(Map.class);
        }

        @Override
        public Object doJob(String json) {
            try {
                return jsonAdapter.fromJson(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @State(Scope.Benchmark)
    public static class QSonDeserialize implements IMPL {

        QsonMapper mapper;

        @Setup
        public void setup() {
            mapper = new QsonMapper();
        }

        @Override
        public Object doJob(String json) {
            try {
                return mapper.read(json, Map.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @State(Scope.Benchmark)
    public static class JSonIteratorDeserialize implements IMPL {

        @Override
        public Object doJob(String json) {
            return JsonIterator.deserialize(json, Map.class);
        }
    }

    @State(Scope.Benchmark)
    public static class GensonDeserialize implements IMPL {

        private Genson genson;

        @Setup
        public void setup() {
            genson = new Genson();
        }

        @Override
        public Object doJob(String json) {
            try {
                return genson.deserialize(json, Map.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @State(Scope.Benchmark)
    public static class GsonSerialize implements IMPLSerialize {

        Gson gson = new Gson();

        @Override
        public String doJob(Object o) {
            return gson.toJson(o);
        }
    }

    @State(Scope.Benchmark)
    public static class JacksonSerialize implements IMPLSerialize {

        ObjectMapper om = new ObjectMapper();

        @Override
        public String doJob(Object o) {
            try {
                return om.writeValueAsString(o);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @State(Scope.Benchmark)
    public static class BoonSerialize implements IMPLSerialize {

        org.boon.json.ObjectMapper om = JsonFactory.create();

        @Override
        public String doJob(Object o) {
            return om.writeValueAsString(o);
        }
    }

    @State(Scope.Benchmark)
    public static class JsonIteratorSerialize implements IMPLSerialize {

        @Override
        public String doJob(Object o) {
            return JsonStream.serialize(o);
        }
    }

    @State(Scope.Benchmark)
    public static class QsonSerialize implements IMPLSerialize {

        private QsonMapper mapper;

        @Setup
        public void setup() {
            mapper = new QsonMapper();
        }

        @Override
        public String doJob(Object o) {
            return mapper.writeString(o);
        }
    }

    @State(Scope.Benchmark)
    public static class GensonSerialize implements IMPLSerialize {

        private Genson mapper;

        @Setup
        public void setup() {
            mapper = new Genson();
        }

        @Override
        public String doJob(Object o) {
            try {
                return mapper.serialize(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @State(Scope.Benchmark)
    public static class MoshiSerialize implements IMPLSerialize {

        private JsonAdapter<Map> jsonAdapter;

        @Setup
        public void setup() {
            Moshi moshi = new Moshi.Builder().build();
            jsonAdapter = moshi.adapter(Map.class);
        }

        @Override
        public String doJob(Object o) {
            try {
                return jsonAdapter.toJson((Map) o);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
