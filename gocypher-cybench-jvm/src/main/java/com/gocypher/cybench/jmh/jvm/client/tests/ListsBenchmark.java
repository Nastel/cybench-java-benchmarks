package com.gocypher.cybench.jmh.jvm.client.tests;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.gocypher.cybench.core.annotation.BenchmarkTag;

public class ListsBenchmark {

    @Benchmark
    @Fork(jvmArgsAppend = "-Xmx2048m")
    @BenchmarkTag(tag = "7e179230-5c62-4a5a-8344-03e723bad434")
    public void arrayListAdd(ArrayList ar, Add action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @BenchmarkTag(tag = "f2435ab8-5e21-4fe2-bcf1-f97f89987ccd")
    public void stackAdd(Stack ar, Add action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @BenchmarkTag(tag = "b9608d8d-b8dc-4195-9272-9213281af253")
    public void linkedListAdd(LinkedList ar, Add action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @BenchmarkTag(tag = "23ae5f95-0ffa-40a0-9559-d6c74e938e61")
    public void arrayListRemove(ArrayList ar, Delete action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @BenchmarkTag(tag = "42a03e62-3fe8-4ee6-a5ff-e3d6d44bb02b")
    public void stackRemove(Stack ar, Delete action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    public static void main(String[] args) {
        ArrayList c = new ArrayList();
        Add delete = new Add();
        for (int i = 0; i < 100000001; i++) {
            delete.doJob(c);

        }


    }

    @Benchmark
    @BenchmarkTag(tag = "1d5c9229-4d21-4ce5-a7fd-362fcfc7e965")
    public void linkedListRemove(LinkedList ar, Delete action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @BenchmarkTag(tag = "e0eb5644-69fb-42ad-b55c-4ccaa156a5c4")
    public void arrayListUpdate(ArrayList ar, Update action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @BenchmarkTag(tag = "ac45b531-2f10-4068-acfc-8bd1c65124dc")
    public void stackUpdate(Stack ar, Update action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @BenchmarkTag(tag = "90bf9c67-961f-4e0d-aaf9-f89fe34cad5b")
    public void linkedListUpdate(LinkedList ar, Update action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @State(value = Scope.Thread)
    public static class ArrayList implements LST {
        java.util.ArrayList collection = null;

        public java.util.ArrayList getCollection() {
            return collection;
        }

        public ArrayList() {
            fill();
        }

        public void fill() {
            collection = new java.util.ArrayList(Stream.iterate(0, i -> i + 2).limit(100000).collect(Collectors.toList()));
        }
    }

    @State(value = Scope.Thread)
    public static class Stack implements LST {

        java.util.Stack collection = null;

        public Stack() {
            collection = new java.util.Stack();
            fill();
        }

        public java.util.Stack getCollection() {
            return collection;
        }

        public void fill() {
            Stream.iterate(0, i -> i + 2).limit(100000).forEach(collection::push);
        }
    }

    @State(value = Scope.Thread)
    public static class LinkedList implements LST {

        java.util.LinkedList collection = null;

        public LinkedList() {
            fill();
        }

        public java.util.LinkedList getCollection() {
            return collection;
        }

        public void fill() {
            collection = new java.util.LinkedList(Stream.iterate(0, i -> i + 2).limit(100000).collect(Collectors.toList()));
        }
    }

    @State(value = Scope.Benchmark)
    public static class Add implements IMPL {

        Object[] cache = Stream.iterate(0, i -> i + 2).limit(100000).collect(Collectors.toList()).toArray();

        int i = 0;

        @Override
        public Object doJob(LST c) {
            return c.getCollection().add(cache[i++ % cache.length]);
        }
    }

    @State(value = Scope.Benchmark)
    public static class Read implements IMPL {

        @Override
        public Object doJob(LST c) {
            return null;
        }
    }

    @State(value = Scope.Benchmark)
    public static class Delete implements IMPL {

        @Override
        public Object doJob(LST c) {
            List collection = c.getCollection();
            Object remove = collection.remove(0);
            remove = collection.remove(collection.size()-1);
            if (collection.size() == 0) {
                c.fill();
            }
            return remove;
        }
    }

    @State(value = Scope.Benchmark)
    public static class Update implements IMPL {

        Object[] cache = Stream.iterate(0, i -> i + 2).limit(100000).collect(Collectors.toList()).toArray();

        int i = 0;

        @Override
        public Object doJob(LST c) {
            c.getCollection().add(0, cache[i++ % cache.length]);
            return null;
        }
    }

    interface IMPL {

        Object doJob(LST c);
    }

    interface LST {
        List getCollection();

        void fill();
    }
}
