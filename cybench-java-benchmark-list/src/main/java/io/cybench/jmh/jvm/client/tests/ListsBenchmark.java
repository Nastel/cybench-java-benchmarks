package io.cybench.jmh.jvm.client.tests;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;

import io.cybench.jmh.jvm.utils.CyBenchCounters;

@State(Scope.Benchmark)
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
@BenchmarkMetaData(key = "context", value = "core_list")
@BenchmarkMetaData(key = "domain", value = "java")
@BenchmarkMetaData(key = "version", value = "1.0.0")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libVendor", value = "-")
public class ListsBenchmark {

    public static final int PRECOMPILED_LIST_SIZE = 1000000;

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OperationsPerInvocation(value = 100)
    @BenchmarkTag(tag = "7e179230-5c62-4a5a-8344-03e723bad434")
    @BenchmarkMetaData(key = "title", value = "Add data into ArrayList")
    @BenchmarkMetaData(key = "api", value = "java.util.ArrayList")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.util.ArrayList")
    @BenchmarkMetaData(key = "actionName", value = "insertData")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html")
    @BenchmarkMetaData(key = "description", value = "Iterative adding of elements from an Object array into ArrayList")
    public void arrayListAdd(ArrayList ar, Add action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OperationsPerInvocation(value = 100)
    @BenchmarkTag(tag = "f2435ab8-5e21-4fe2-bcf1-f97f89987ccd")
    @BenchmarkMetaData(key = "title", value = "Add data into Stack")
    @BenchmarkMetaData(key = "api", value = "java.util.Stack")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.util.Stack")
    @BenchmarkMetaData(key = "actionName", value = "insertData")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html")
    @BenchmarkMetaData(key = "description", value = "Iterative adding of elements from an Object array into Stack")
    public void stackAdd(Stack ar, Add action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @OperationsPerInvocation(value = 100)
    @BenchmarkMode(Mode.SingleShotTime)
    @BenchmarkTag(tag = "b9608d8d-b8dc-4195-9272-9213281af253")
    @BenchmarkMetaData(key = "title", value = "Add data into LinkedList")
    @BenchmarkMetaData(key = "api", value = "java.util.LinkedList")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.util.LinkedList")
    @BenchmarkMetaData(key = "actionName", value = "insertData")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html")
    @BenchmarkMetaData(key = "description", value = "Iterative adding of elements from an Object array into LinkedList")
    public void linkedListAdd(LinkedList ar, Add action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @OperationsPerInvocation(value = 100)
    @BenchmarkMode(Mode.SingleShotTime)
    @BenchmarkTag(tag = "23ae5f95-0ffa-40a0-9559-d6c74e938e61")
    @BenchmarkMetaData(key = "title", value = "Remove data from ArrayList")
    @BenchmarkMetaData(key = "api", value = "java.util.ArrayList")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.util.ArrayList")
    @BenchmarkMetaData(key = "actionName", value = "removeData")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html")
    @BenchmarkMetaData(key = "description", value = "Removing of objects from ArrayList")
    public void arrayListRemove(ArrayList ar, Delete action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @OperationsPerInvocation(value = 100)
    @BenchmarkMode(Mode.SingleShotTime)
    @BenchmarkTag(tag = "42a03e62-3fe8-4ee6-a5ff-e3d6d44bb02b")
    @BenchmarkMetaData(key = "title", value = "Remove data from Stack")
    @BenchmarkMetaData(key = "api", value = "java.util.Stack")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.util.Stack")
    @BenchmarkMetaData(key = "actionName", value = "removeData")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html")
    @BenchmarkMetaData(key = "description", value = "Removing of objects from Stack")
    public void stackRemove(Stack ar, Delete action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @OperationsPerInvocation(value = 100)
    @BenchmarkMode(Mode.SingleShotTime)
    @BenchmarkMetaData(key = "D", value = "B")
    @BenchmarkTag(tag = "1d5c9229-4d21-4ce5-a7fd-362fcfc7e965")
    @BenchmarkMetaData(key = "title", value = "Remove data from LinkedList")
    @BenchmarkMetaData(key = "api", value = "java.util.LinkedList")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.util.LinkedList")
    @BenchmarkMetaData(key = "actionName", value = "removeData")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html")
    @BenchmarkMetaData(key = "description", value = "Removing of objects from LinkedList")
    public void linkedListRemove(LinkedList ar, Delete action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @OperationsPerInvocation(value = 101)
    @BenchmarkMode(Mode.SingleShotTime)
    @BenchmarkTag(tag = "e0eb5644-69fb-42ad-b55c-4ccaa156a5c4")
    @BenchmarkMetaData(key = "title", value = "Update data in ArrayList")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.util.ArrayList")
    @BenchmarkMetaData(key = "api", value = "java.util.ArrayList")
    @BenchmarkMetaData(key = "actionName", value = "updateData")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html")
    @BenchmarkMetaData(key = "description", value = "Updating the ArrayList data.")
    public void arrayListUpdate(ArrayList ar, Update action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @OperationsPerInvocation(value = 100)
    @BenchmarkMode(Mode.SingleShotTime)

    @BenchmarkTag(tag = "ac45b531-2f10-4068-acfc-8bd1c65124dc")
    @BenchmarkMetaData(key = "title", value = "Update data in Stack")
    @BenchmarkMetaData(key = "api", value = "java.util.Stack")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.util.Stack")
    @BenchmarkMetaData(key = "actionName", value = "updateData")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/util/Stack.html")
    @BenchmarkMetaData(key = "description", value = "Updating the Stack data.")
    public void stackUpdate(Stack ar, Update action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @OperationsPerInvocation(value = 100)
    @BenchmarkTag(tag = "90bf9c67-961f-4e0d-aaf9-f89fe34cad5b")
    @BenchmarkMetaData(key = "title", value = "Update data in LinkedList")
    @BenchmarkMetaData(key = "api", value = "java.util.LinkedList")
    @BenchmarkMetaData(key = "libSymbolicName", value = "java.util.LinkedList")
    @BenchmarkMetaData(key = "actionName", value = "updateData")
    @BenchmarkMetaData(key = "libUrl", value = "https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html")
    @BenchmarkMetaData(key = "description", value = "Updating the LinkedList data.")
    public void linkedListUpdate(LinkedList ar, Update action, Blackhole bh) {
        bh.consume(action.doJob(ar));
    }

    public static void main(String[] args) throws InterruptedException {
        Method[] methods = ListsBenchmark.class.getMethods();
        System.out.println(methods);
        Thread.sleep(3000);
    }

    @TearDown(Level.Iteration)
    public void clearIteration(CyBenchCounters.ProfileCounters counters) {
        CyBenchCounters.registerProfileInformation(counters);
    }

    interface IMPL {

        Object doJob(LST c);
    }

    interface LST {
        List getCollection();

        void fill();
    }

    @State(value = Scope.Thread)
    public static class ArrayList implements LST {
        java.util.ArrayList collection = null;

        public ArrayList() {
            fill();
        }

        @Override
        public java.util.ArrayList getCollection() {
            return collection;
        }

        @Override
        public void fill() {
            collection = new java.util.ArrayList(
                    Stream.iterate(0, i -> i + 2).limit(ListsBenchmark.PRECOMPILED_LIST_SIZE).collect(Collectors.toList()));
        }
    }

    @State(value = Scope.Thread)
    public static class Stack implements LST {

        java.util.Stack collection;

        public Stack() {
            collection = new java.util.Stack();
            fill();
        }

        @Override
        public java.util.Stack getCollection() {
            return collection;
        }

        @Override
        public void fill() {
            Stream.iterate(0, i -> i + 2).limit(1000000).forEach(collection::push);
        }
    }

    @State(value = Scope.Thread)
    public static class LinkedList implements LST {

        java.util.LinkedList collection = null;

        public LinkedList() {
            fill();
        }

        @Override
        public java.util.LinkedList getCollection() {
            return collection;
        }

        @Override
        public void fill() {
            collection = new java.util.LinkedList(
                    Stream.iterate(0, i -> i + 2).map(Integer::new).limit(100000).collect(Collectors.toList()));
        }
    }

    @State(value = Scope.Benchmark)
    public static class Add implements IMPL {

        Object[] cache = Stream.iterate(0, i -> i + 2).map(Integer::new).limit(1000000).collect(Collectors.toList())
                .toArray();

        int i = 0;

        @Override
        public Object doJob(LST c) {
            c.getCollection().add(cache[i++ % cache.length]);
            c.getCollection().add(cache[i++ % cache.length]);
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
            List<?> collection = c.getCollection();
            Object remove = collection.remove(0);
            remove = collection.remove(collection.size() / 2);
            remove = collection.remove(collection.size() - 1);
            if (collection.size() < 3) {
                c.fill();
            }
            return remove;
        }
    }

    @State(value = Scope.Benchmark)
    public static class Update implements IMPL {

        Object[] cache = Stream.iterate(0, i -> i + 2).map(Integer::new).limit(1000000).collect(Collectors.toList())
                .toArray();

        int i = 0;

        @Override
        public Object doJob(LST c) {
            c.getCollection().add(0, cache[i++ % cache.length]);
            return null;
        }
    }
}
