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
import com.google.gson.Gson;
import org.boon.json.JsonFactory;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;


public class JsonLibraryBenchmark {

    @Benchmark
    public Object gsonWithSmallJSON(SmallJson json, GSON impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    public Object gsonWithAverageJSON(AverageJson json, GSON impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    public Object gsonWithBigJSON(BigJson json, GSON impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    public Object jacksonWithSmallJSON(SmallJson json, Jackson impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    public Object jacksonWithAverageJSON(AverageJson json, Jackson impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    public Object boonWithBigJSON(BigJson json, Jackson impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    public Object boonWithSmallJSON(SmallJson json, Jackson impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    public Object boonWithAverageJSON(AverageJson json, Jackson impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }

    @Benchmark
    public Object jacksonWithBigJSON(BigJson json, Jackson impl, Blackhole bh) {
        return impl.doJob(json.actualJson);
    }


    @State(Scope.Benchmark)
    public static class SmallJson {
        public String actualJson = "{\"name\":\"Bob\",\"salary\":10000, \"spouse\": null}";
    }

    @State(Scope.Benchmark)
    public static class BigJson {
        public String actualJson = new BufferedReader(new InputStreamReader(JsonLibraryBenchmark.class.getResourceAsStream("big.json"))).lines().collect(Collectors.joining("\n"));
    }

    @State(Scope.Benchmark)
    public static class AverageJson {
        public String actualJson = "{\n" +
                "    \"jsonrpc\": \"2.0\",\n" +
                "    \"id\": 2,\n" +
                "    \"result\": {\n" +
                "        \"difficulty\": \"0x574767a266a\",\n" +
                "        \"extraData\": \"0xd583010103844765746885676f312e35856c696e7578\",\n" +
                "        \"gasLimit\": \"0x2fefd8\",\n" +
                "        \"gasUsed\": \"0xf618\",\n" +
                "        \"hash\": \"0x26c947da5bc4bef6b72ee6fdcf07b824999a7dfabb10126b05889e259b904500\",\n" +
                "        \"logsBloom\": \"0x00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000\",\n" +
                "        \"miner\": \"0x1dcb8d1f0fcc8cbc8c2d76528e877f915e299fbe\",\n" +
                "        \"mixHash\": \"0xa153bb2070c9b70974651559c50974e0c0dff832866919a7dda481331b778beb\",\n" +
                "        \"nonce\": \"0x8db450462af941d9\",\n" +
                "        \"number\": \"0x58228\",\n" +
                "        \"parentHash\": \"0x4a06ca1d839bfef2f65fa9a6e1f40414d2adcb5040f0280ceb8b1ca468db1cd9\",\n" +
                "        \"receiptsRoot\": \"0x6c1a596fc98a157722b13a6e2b4057b4f57cb5def984f77ac4d7e04ddcfa034b\",\n" +
                "        \"sha3Uncles\": \"0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347\",\n" +
                "        \"size\": \"0x371\",\n" +
                "        \"stateRoot\": \"0x98e1916d318b22e29fd556f61fad4c8e6f571300ad598720ede28b4ac295e7de\",\n" +
                "        \"timestamp\": \"0x5618b175\",\n" +
                "        \"totalDifficulty\": \"0x189fe5d12a177049\",\n" +
                "        \"transactions\": [\n" +
                "            {\n" +
                "                \"blockHash\": \"0x26c947da5bc4bef6b72ee6fdcf07b824999a7dfabb10126b05889e259b904500\",\n" +
                "                \"blockNumber\": \"0x58228\",\n" +
                "                \"from\": \"0x1dcb8d1f0fcc8cbc8c2d76528e877f915e299fbe\",\n" +
                "                \"gas\": \"0x15f90\",\n" +
                "                \"gasPrice\": \"0xba43b7400\",\n" +
                "                \"hash\": \"0xfa7fc9217bf82d8e67b6fad3bfdaf7d582b68e58656529e64ccc8b587ea1a09e\",\n" +
                "                \"input\": \"0x\",\n" +
                "                \"nonce\": \"0xdfe9\",\n" +
                "                \"r\": \"0xf6c26c89632f0a31cb85da9e78a16a2f23c147d53f5dc4fa68b22a15a503a760\",\n" +
                "                \"s\": \"0x7b13fc9ea7919d79fc8b7ca6bfd9bb5c698af3b57ee6f79b57b832b5eb4b63b\",\n" +
                "                \"to\": \"0xb1abce2918e21ddb93aa452731a12672a3d9f75a\",\n" +
                "                \"transactionIndex\": \"0x0\",\n" +
                "                \"v\": \"0x1b\",\n" +
                "                \"value\": \"0xd4331d13a141e800\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"blockHash\": \"0x26c947da5bc4bef6b72ee6fdcf07b824999a7dfabb10126b05889e259b904500\",\n" +
                "                \"blockNumber\": \"0x58228\",\n" +
                "                \"from\": \"0x2a65aca4d5fc5b5c859090a6c34d164135398226\",\n" +
                "                \"gas\": \"0x15f90\",\n" +
                "                \"gasPrice\": \"0xba43b7400\",\n" +
                "                \"hash\": \"0xa1a3b3d4cc1f23b8518c5b885d491c4a1da179c9252339d7e886c6f0913de9ef\",\n" +
                "                \"input\": \"0x\",\n" +
                "                \"nonce\": \"0x171e\",\n" +
                "                \"r\": \"0xc0bd5241b4f922997f7f6cf28f85304785f88d2ca6d8f2fe64304d269ec4afce\",\n" +
                "                \"s\": \"0x5d6537000ebea42ef1b37caeaee2fae3145e25cf5f4f4fec0faf066f089ca979\",\n" +
                "                \"to\": \"0xfecab546498f74591d4f6d448a4a63552850f122\",\n" +
                "                \"transactionIndex\": \"0x1\",\n" +
                "                \"v\": \"0x1c\",\n" +
                "                \"value\": \"0x13e6a003e7e56c00\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"blockHash\": \"0x26c947da5bc4bef6b72ee6fdcf07b824999a7dfabb10126b05889e259b904500\",\n" +
                "                \"blockNumber\": \"0x58228\",\n" +
                "                \"from\": \"0x1dcb8d1f0fcc8cbc8c2d76528e877f915e299fbe\",\n" +
                "                \"gas\": \"0x15f90\",\n" +
                "                \"gasPrice\": \"0xba43b7400\",\n" +
                "                \"hash\": \"0xa1a83a23ede4e5f4e4a4099cfd7a1764248b4f5b90b82e48dc5f0aebd7faab83\",\n" +
                "                \"input\": \"0x\",\n" +
                "                \"nonce\": \"0xdfea\",\n" +
                "                \"r\": \"0xfdc937fcf20a44bb15cf8e20c313c9c98f5a5e133d2463774bf649fc90eecfb1\",\n" +
                "                \"s\": \"0x7e5f3f214a0f0e3fa233665289d57ebed73947c426ed6097d6978e5596d2ac1c\",\n" +
                "                \"to\": \"0x7c5080988c6d91d090c23d54740f856c69450b29\",\n" +
                "                \"transactionIndex\": \"0x2\",\n" +
                "                \"v\": \"0x1b\",\n" +
                "                \"value\": \"0x43feeb4cddfc00\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"transactionsRoot\": \"0x76c1e06c9486d20d8860aa80af9ca1b17138a38ea770f5242d7aa723d90fa0eb\",\n" +
                "        \"uncles\": []\n" +
                "    }\n" +
                "}";
    }

    @State(Scope.Benchmark)
    public static class LargeJson {

    }

    @State(Scope.Benchmark)
    public static class GSON implements IMPL {
        Gson gson = new Gson();

        @Override
        public Object doJob(String json) {
            return gson.fromJson(json, Map.class);

        }
    }

    @State(Scope.Benchmark)
    public static class Jackson implements IMPL {
        ObjectMapper om = new ObjectMapper();

        @Override
        public Object doJob(String json) {
            try {
                return om.readValue(json, Map.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    @State(Scope.Benchmark)
    public static class Boon implements IMPL {
       org.boon.json.ObjectMapper om =  JsonFactory.create();

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


    interface IMPL {
        Object doJob(String json);
    }


}

