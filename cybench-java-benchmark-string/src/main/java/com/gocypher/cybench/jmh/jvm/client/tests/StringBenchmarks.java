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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 */
package com.gocypher.cybench.jmh.jvm.client.tests;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.jmh.jvm.utils.CyBenchCounters;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@State(Scope.Benchmark)
@BenchmarkMetaData(key="isLibraryBenchmark", value="false")
@BenchmarkMetaData(key="context", value="core_strings")
@BenchmarkMetaData(key="domain", value="java")
@BenchmarkMetaData(key="version", value="1.0.0")
@BenchmarkMetaData(key="libVersion", value="-")
@BenchmarkMetaData(key="libVendor", value="-")
public class StringBenchmarks {

    private String label1 = "for a real test, make this big and this big,for a real test, make this big and this big,for a real test, make this big and this big";

    private String label2 = "so that we can see GC effects,so that we can see GC effects,so that we can see GC effects";

    private String labelForReplacement = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent tempus fringilla ligula id maximus. Ut quis rhoncus risus. Nunc et libero ac urna ultrices suscipit eu a tellus. Nulla ac ligula risus. Pellentesque viverra, mi dapibus porta porttitor, ex metus malesuada orci, sed suscipit mauris quam vel tortor. Fusce venenatis ante quis efficitur vestibulum. Sed sed malesuada quam. Vivamus tincidunt quam id enim molestie placerat. Etiam commodo euismod nisl in consectetur. Ut sed euismod erat. Aliquam vitae ornare leo, non ultricies orci. Sed euismod mauris quis justo viverra volutpat. Etiam sit amet luctus eros, ac porttitor purus. Pellentesque laoreet neque at ipsum sodales mattis.\n" + "\n" + "Quisque efficitur leo vitae lectus lacinia malesuada. Sed nec arcu vel sapien volutpat gravida. Aenean tempor maximus pharetra. Quisque suscipit magna sit amet augue cursus feugiat. Fusce sed erat vitae nulla vehicula auctor. Aliquam at lacus tristique, tempor neque quis, imperdiet nibh. Praesent id dignissim lorem. Vivamus in ultrices orci, eget varius turpis. Donec ut tincidunt risus, eget varius risus. Cras nec posuere metus, sit amet convallis tortor. Quisque gravida semper sem, eget malesuada libero tempus sed.\n" + "\n" + "Nam vitae iaculis sapien. Fusce maximus enim odio, nec facilisis eros vehicula vel. Etiam rutrum varius iaculis. Donec ac lectus feugiat, fermentum ligula a, bibendum mauris. Sed aliquam erat eget elit finibus, et lacinia augue venenatis. Phasellus et elit eu risus tristique iaculis nec dignissim leo. Nam nulla odio, commodo ut felis ut, cursus pulvinar nunc. Nulla turpis turpis, euismod nec justo eu, vestibulum sagittis elit. In hac habitasse platea dictumst. Curabitur porta, mi quis ultricies lobortis, erat sem molestie neque, vehicula commodo neque leo vel purus.\n" + "\n" + "Duis eget ullamcorper lectus. Quisque pharetra tortor vitae quam consequat fermentum. Mauris facilisis, nisi at dignissim efficitur, sem mauris accumsan ipsum, vitae ultricies urna eros at metus. Nulla facilisis, ipsum a vestibulum viverra, diam turpis placerat enim, nec vestibulum diam dolor eget purus. Proin ornare enim nunc, eu ullamcorper sapien elementum a. Donec quam est, rhoncus quis lacinia quis, fringilla et lectus. Integer tristique, est a blandit tincidunt, ante velit suscipit augue, a euismod massa lacus et diam.\n" + "\n" + "Integer varius eu mauris sit amet aliquet. In hac habitasse platea dictumst. Donec feugiat gravida ex. Quisque scelerisque consequat odio non sodales. Sed convallis turpis nunc, vitae efficitur nunc commodo quis. Suspendisse potenti. Sed ac bibendum ipsum, a convallis lacus. Praesent mollis lorem metus, quis semper sapien tincidunt volutpat. Suspendisse porttitor ultricies libero. Donec non luctus metus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vivamus rutrum, ex id condimentum hendrerit, est nisl condimentum orci, eu ornare massa ante quis metus.";

    // private static final String REGEX="lor(em|ce|de)m" ;
    private static final String REGEX = "([Ll]orem)[\\s,.!:\\-()?]+(ipsum)[\\s,.!:\\-()?]+(sit)[\\s,.!:\\-()?]+(amet)";

    public static int numberOfIterations = 100_000;

    private int count = 0;

    private Matcher matcher;

    @Setup(Level.Iteration)
    public void setUp() {
        this.count = 0;
        matcher = Pattern.compile(REGEX).matcher("");
    }

    // FIXME Correct tests uncomment and use them
    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(1)
    @Threads(5)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "ff3ed9f5-bed9-40f2-b64f-5cd049ae29b5")
    @BenchmarkMetaData(key="title", value="String and number concatenation")
    @BenchmarkMetaData(key="api", value="java.lang.String")
    @BenchmarkMetaData(key="libSymbolicName", value="java.lang.String")
    @BenchmarkMetaData(key="actionName", value="concatString")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/lang/String.html")
    @BenchmarkMetaData(key="description", value="Concatenating String from multiple numbers and other String text.")
    public String stringConcatMultiChars() {
        String buff = "";
        for (int i = 0; i < 5; i++) {
            buff += label1;
            buff += this.count;
            buff += i;
            buff += label2;
            buff += this.count * 2;
            buff += i;
        }
        this.count++;
        return buff;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(1)
    @Threads(5)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "175e9abd-fbb0-4eef-ac97-293ca7347f8b")
    @BenchmarkMetaData(key="title", value="String and number concatenation")
    @BenchmarkMetaData(key="api", value="java.lang.StringBuffer")
    @BenchmarkMetaData(key="libSymbolicName", value="java.lang.StringBuffer")
    @BenchmarkMetaData(key="actionName", value="concatString")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuffer.html")
    @BenchmarkMetaData(key="description", value="Appending StringBuffer with multiple numbers and other String text.")
    public StringBuffer stringBufferConcatMultiChars() {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            buff.append(label1);
            buff.append(count);
            buff.append(i);
            buff.append(label2);
            buff.append(count * 2);
            buff.append(i);
        }
        this.count++;
        return buff;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(1)
    @Threads(5)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "971ed476-bfc7-432c-bbba-a2f833ee35da")
    @BenchmarkMetaData(key="title", value="String replace all")
    @BenchmarkMetaData(key="api", value="java.lang.String")
    @BenchmarkMetaData(key="libSymbolicName", value="java.lang.String")
    @BenchmarkMetaData(key="actionName", value="replaceAll")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/lang/String.html")
    @BenchmarkMetaData(key="description", value="Replacing all occurrences found in String with other Strings and then replacing them back to initial value.")
    public String stringReplaceAll() {
        String s = labelForReplacement;
        s = s.replaceAll("ipsum", "12345");
        s = s.replaceAll("12345", "ipsum");
        return s;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(1)
    @Threads(5)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "693ceffb-6843-4157-b1eb-9abcf078eab9")
    @BenchmarkMetaData(key="title", value="String Buffer replace all")
    @BenchmarkMetaData(key="api", value="java.lang.StringBuffer")
    @BenchmarkMetaData(key="libSymbolicName", value="java.lang.StringBuffer")
    @BenchmarkMetaData(key="actionName", value="replaceAll")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuffer.html")
    @BenchmarkMetaData(key="description", value="Replacing all occurrences found in StringBuffer with other Strings and then replacing them back to initial value.")
    public StringBuffer stringBufferReplaceAll() {
        StringBuffer buff = new StringBuffer(labelForReplacement);
        buff = replaceAll(buff, "ipsum", "12345");
        buff = replaceAll(buff, "12345", "ipsum");
        return buff;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(1)
    @Threads(5)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "2f75e07b-bd24-455f-8aaf-a1930d11a8eb")
    @BenchmarkMetaData(key="title", value="Compiled Regex pattern matcher")
    @BenchmarkMetaData(key="api", value="java.util.regex.Matcher")
    @BenchmarkMetaData(key="libSymbolicName", value="java.util.regex.Matcher")
    @BenchmarkMetaData(key="actionName", value="matchPatternCompiled")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html")
    @BenchmarkMetaData(key="description", value="Using a compiled Regex pattern to find if it exists in given String.")
    public boolean findRegexCompiled() {
        boolean flag = matcher.reset(labelForReplacement).matches();
        return flag;
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(1)
    @Threads(5)
    @Measurement(iterations = 5, time = 5)
    @Warmup(iterations = 1, time = 5)
    @BenchmarkTag(tag = "5b0f8faa-e740-401e-85b3-6e0ec3714510")
    @BenchmarkMetaData(key="title", value="Not compiled Regex pattern matcher")
    @BenchmarkMetaData(key="api", value="java.util.regex.Matcher")
    @BenchmarkMetaData(key="libSymbolicName", value="java.util.regex.Matcher")
    @BenchmarkMetaData(key="actionName", value="matchPatternUnCompiled")
    @BenchmarkMetaData(key="libUrl", value="https://docs.oracle.com/javase/8/docs/api/java/util/regex/Matcher.html")
    @BenchmarkMetaData(key="description", value="Using a not compiled Regex pattern to find if it exists in given String.")
    public boolean findRegexUnCompiled() {
        boolean flag = labelForReplacement.matches(REGEX);
        return flag;
    }

    @TearDown(Level.Iteration)
    public void clearIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
    }

    private StringBuffer replaceAll(StringBuffer buff, String srcLabel, String targetLabel) {
        int pos = 0;
        while (pos >= 0) {
            pos = buff.indexOf(srcLabel, pos);
            if (pos > 0) {
                buff = buff.replace(pos, pos + targetLabel.length(), targetLabel);
            }
        }
        return buff;
    }
}
