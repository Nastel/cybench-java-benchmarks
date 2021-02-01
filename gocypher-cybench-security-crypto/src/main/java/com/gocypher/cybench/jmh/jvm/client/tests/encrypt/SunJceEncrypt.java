package com.gocypher.cybench.jmh.jvm.client.tests.encrypt;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.infra.Blackhole;
import com.gocypher.cybench.core.annotation.BenchmarkTag;


@BenchmarkMetaData(key = "api", value = "SunJCE")
@BenchmarkMetaData(key = "libSymbolicName", value = "SunJCE")
@BenchmarkMetaData(key = "libVersion", value = "-")
@BenchmarkMetaData(key = "libDescription", value = "The Java Cryptography Extension (JCE) from Sun Microsystems.It is a framework for implementing encryption, key generation and key agreement, and Message Authentication Code (MAC) algorithms.")
@BenchmarkMetaData(key = "libVendor", value = "Oracle")
@BenchmarkMetaData(key = "isLibraryBenchmark", value = "false")
@BenchmarkMetaData(key = "dataSize", value = "4128")

public class SunJceEncrypt extends EncryptBenchmarks {

    @Param({"The quick brown fox jumps over t", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris porttitor elementum ipsum ut porta. Mauris aliquam risus in justo maximus, eget sodales metus euismod. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed at eros ultrices, blandit enim at, venenatis augue. Mauris at suscipit metus, sed bibendum sem. Sed nec commodo orci, aliquam ullamcorper est. Ut in dapibus felis, vitae semper elit. Integer fermentum, dui ut lacinia semper, odio nulla aliquet elit, et pellentesque erat sapien id risus. Sed eu vulputate nulla, a pharetra odio. Aliquam id finibus est. Proin aliquam mattis eros, sed viverra sem rhoncus eget. Nunc suscipit molestie sem, sit amet ultrices libero. Donec sed urna eget nibh porttitor sagittis. Nam ac tempor arcu.\n" +
            "\n" +
            "Nunc pretium dictum nibh, sed tristique nulla. Donec cursus lacus dictum urna consequat ultricies. Nunc eu venenatis justo. Aliquam erat volutpat. Mauris consequat varius velit at feugiat. Aenean eleifend mauris nec turpis euismod pharetra. Phasellus ullamcorper bibendum mauris sit amet dapibus. Nulla convallis scelerisque urna, at euismod tellus sodales quis. Duis id metus orci. Vivamus commodo massa vel lacus ornare, id venenatis est varius. Donec eu arcu felis. Nullam sapien sapien, porta tempus mattis at, bibendum sit amet orci. Vestibulum consectetur eleifend hendrerit. Donec egestas lobortis maximus. Proin viverra, turpis non aliquam elementum, libero orci gravida metus, nec ultrices metus nisl in risus. Nullam sed facilisis quam.\n" +
            "\n" +
            "Mauris quis lectus ac diam porttitor scelerisque id consectetur urna. Proin quis mauris nunc. Cras euismod dolor turpis, at auctor leo sollicitudin fermentum. Praesent nec malesuada metus. Cras semper volutpat metus, sit amet auctor neque fermentum sed. Suspendisse tristique nulla a cursus tempus. Ut efficitur ante ac quam venenatis, quis pellentesque lectus consequat. Praesent condimentum id velit quis ornare. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Mauris eget dui risus. Nulla tristique neque eu tortor laoreet, sed lobortis metus mattis. Cras volutpat porttitor ligula sed accumsan. Suspendisse non ligula vitae orci consectetur scelerisque nec at magna. Aliquam vel accumsan massa, non egestas eros. Donec nibh sapien, auctor sed nulla eu, ornare finibus tortor.\n" +
            "\n" +
            "Vivamus quis neque at eros cursus dapibus. Pellentesque diam lacus, elementum non tortor at, semper hendrerit nunc. Aliquam erat volutpat. Vestibulum ac tellus augue. Etiam dui enim, auctor non ante ut, posuere congue dolor. Sed egestas scelerisque velit, at bibendum felis pretium sit amet. Proin mattis elit turpis, id mattis ante lacinia vitae. Sed volutpat eget tellus iaculis condimentum. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer pretium ipsum augue, eget iaculis leo vulputate in. Quisque auctor sem eget arcu vestibulum finibus. Fusce fringilla malesuada ex in iaculis. Cras lacinia ac elit quis sodales. Cras vel urna augue. Cras vehicula mi vel sem ultricies pharetra.\n" +
            "\n" +
            "Vestibulum sollicitudin elit sit amet dui pharetra, nec eleifend felis scelerisque. Praesent pretium ipsum nec lacus volutpat, non tristique ex ultricies. Praesent quis ultrices sem. Mauris rutrum lectus libero, sed varius nunc pulvinar at. In at tellus nec ligula consequat feugiat. Morbi convallis, metus non tincidunt molestie, lacus nisl pellentesque nulla, luctus mattis justo nulla sit amet libero. In ultrices enim ligula, a venenatis urna malesuada non. In at eros condimentum, porttitor diam sed, egestas arcu. Praesent ultricies elit ut lacus interdum, nec sodales odio commodo. Nunc vulputate enim diam, vitae laoreet purus auctor eu. Etiam pulvinar at dolor id pretium. Praesent vel sapien eu ligula congue interdum. Aenean tristique venenatis urna eu imperdiet. Sed convallis sollicitudin lacus non semper. Proin dictum, tortor sit amet tempor convallis, turpis erat mattis eros, et vehicula dui leo at libero. Curabitur a imperdiet nunc. Aliquam a erat nibh. Nam bibendum massa libero. Suspendissae quisi diamus lacus."})
    private String testString;

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Encrypt using AES/CBC/NoPadding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/CBC/NoPadding (128)")
    @BenchmarkTag(tag = "b295fa9a-c524-44bc-9412-87500049612f")
    public void AES_CBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/CBC/NoPadding (128)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Encrypt using AES/CBC/PKCS5Padding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/CBC/PKCS5Padding (128)")
    @BenchmarkTag(tag = "1250fbeb-e406-4aab-9d3b-81c5d30e0c68")
    public void AES_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/CBC/PKCS5Padding (128)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Encrypt using AES/ECB/NoPadding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/ECB/NoPadding (128)")
    @BenchmarkTag(tag = "2ee60340-2cb0-4b4c-8fb4-fd3e8a0e843e")
    public void AES_ECB_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/ECB/NoPadding (128)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Encrypt using AES/ECB/PKCS5Padding (128)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "AES/ECB/PKCS5Padding (128)")
    @BenchmarkTag(tag = "d5d03948-8ab2-4156-a122-9262be601a88")
    public void AES_ECB_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/ECB/PKCS5Padding (128)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Encrypt using DES/CBC/NoPadding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/CBC/NoPadding (56)")
    @BenchmarkTag(tag = "096b41fe-319d-4d61-b484-c1cdc7e36bc7")
    public void DES_CBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/CBC/NoPadding (56)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Encrypt using DES/CBC/PKCS5Padding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/CBC/PKCS5Padding (56)")
    @BenchmarkTag(tag = "a7f47c06-7aab-47c5-9fbf-0ec314af9c71")
    public void DES_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/CBC/PKCS5Padding (56)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Encrypt using DES/ECB/NoPadding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/ECB/NoPadding (56)")
    @BenchmarkTag(tag = "88fda37c-ee51-49aa-afa7-9c0b958a5425")
    public void DES_EBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/ECB/NoPadding (56)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Encrypt using DES/ECB/PKCS5Padding (56)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DES/ECB/PKCS5Padding (56)")
    @BenchmarkTag(tag = "8914e01b-9e6b-4883-b169-e00ee8c77427")
    public void DES_EBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/ECB/PKCS5Padding (56)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Encrypt using DESede/CBC/NoPadding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/CBC/NoPadding (168)")
    @BenchmarkTag(tag = "9b334129-fc71-4f74-b645-cba87a8f3de1")
    public void DESede_CBS_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/CBC/NoPadding (168)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Encrypt using DESede/CBC/PKCS5Padding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/CBC/PKCS5Padding (168)")
    @BenchmarkTag(tag = "51e5cd3d-2b27-4e2e-a39a-3bfb819f5ad4")
    public void DESede_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/CBC/PKCS5Padding (168)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Encrypt using DESede/ECB/NoPadding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/ECB/NoPadding (168)")
    @BenchmarkTag(tag = "969401cd-8117-459c-b0c6-6ad4e0032386")
    public void DESede_ECB_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/ECB/NoPadding (168)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }

    @Benchmark
    @BenchmarkMetaData(key = "benchTitle", value = "Encrypt using DESede/ECB/PKCS5Padding (168)")
    @BenchmarkMetaData(key = "encryptionAlgorithm", value = "DESede/ECB/PKCS5Padding (168)")
    @BenchmarkTag(tag = "c35a4abe-bd48-4780-b9a0-e03e6a1eba08")
    public void DESede_ECB_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/ECB/PKCS5Padding (168)");
        encrypt(bh, cipherDescription, "SunJCE", testString);
    }
}
