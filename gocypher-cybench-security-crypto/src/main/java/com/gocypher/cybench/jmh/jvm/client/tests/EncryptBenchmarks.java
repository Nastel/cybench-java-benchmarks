package com.gocypher.cybench.jmh.jvm.client.tests;

import com.gocypher.cybench.core.annotation.BenchmarkMetaData;
import com.gocypher.cybench.core.annotation.BenchmarkTag;
import com.gocypher.cybench.core.utils.SecurityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.bouncycastle.jce.provider.BouncyCastleProvider;

@State(Scope.Benchmark)
public class EncryptBenchmarks {

    @Param({"SunJCE", "BC"})
    private String provider;
    // @Param({
    // "AES/CBC/NoPadding (128)",
    // "AES/CBC/PKCS5Padding (128)",
    // "AES/ECB/NoPadding (128)",
    // "AES/ECB/PKCS5Padding (128)",
    // "DES/CBC/NoPadding (56)",
    // "DES/CBC/PKCS5Padding (56)",
    // "DES/ECB/NoPadding (56)",
    // "DES/ECB/PKCS5Padding (56)",
    // "DESede/CBC/NoPadding (168)",
    // "DESede/CBC/PKCS5Padding (168)",
    // "DESede/ECB/NoPadding (168)",
    // "DESede/ECB/PKCS5Padding (168)"
    // //            "RSA/ECB/PKCS1Padding (1024)",
    // //            "RSA/ECB/PKCS1Padding (2048)",
    // //            "RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024)",
    // //            "RSA/ECB/OAEPWithSHA-1AndMGF1Padding (2048)",
    // //            "RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024)",
    // //            "RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024)"
    // })
    // private String cipherSymmetric;
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




    public static void main(String[] args) throws Exception {

        SecurityUtils.computeClassHashForMethods(EncryptBenchmarks.class, new HashMap<>());
    }

    @Setup
    public void setup() {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "AES/CBC/NoPadding (128)")
    @BenchmarkMetaData(key = "libVendor", value = "javax.crypto")
    @BenchmarkMetaData(key = "actionName", value = "encrypt")
    @BenchmarkTag(tag = "772da461-0aeb-479d-af86-fdcf79a157db")
    public void AES_CBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/CBC/NoPadding (128)");
        encrypt(bh, cipherDescription);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "AES/CBC/PKCS5Padding (128)")
    @BenchmarkMetaData(key = "libVendor", value = "javax.crypto")
    @BenchmarkMetaData(key = "actionName", value = "encrypt")
    @BenchmarkTag(tag = "b6f05036-7bb4-445c-b33c-64a8f65f1cf0")
    public void AES_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/CBC/PKCS5Padding (128)");
        encrypt(bh, cipherDescription);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "AES/ECB/NoPadding (128)")
    @BenchmarkMetaData(key = "libVendor", value = "javax.crypto")
    @BenchmarkMetaData(key = "actionName", value = "encrypt")
    @BenchmarkTag(tag = "caf457f7-69b0-4bc5-92e1-49f3bc7207ff")
    public void AES_ECB_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/ECB/NoPadding (128)");
        encrypt(bh, cipherDescription);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "AES/ECB/PKCS5Padding (128)")
    @BenchmarkMetaData(key = "libVendor", value = "javax.crypto")
    @BenchmarkMetaData(key = "actionName", value = "encrypt")
    @BenchmarkTag(tag = "b30895f0-33a6-4f7f-b847-3cfcc39d9e2a")
    public void AES_ECB_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("AES/ECB/PKCS5Padding (128)");
        encrypt(bh, cipherDescription);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "DES/CBC/NoPadding (56)")
    @BenchmarkMetaData(key = "libVendor", value = "javax.crypto")
    @BenchmarkMetaData(key = "actionName", value = "encrypt")
    @BenchmarkTag(tag = "2d79a168-7376-4e6c-baeb-6b649e8189da")
    public void DES_CBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/CBC/NoPadding (56)");
        encrypt(bh, cipherDescription);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "DES/CBC/PKCS5Padding (56)")
    @BenchmarkMetaData(key = "libVendor", value = "javax.crypto")
    @BenchmarkMetaData(key = "actionName", value = "encrypt")
    @BenchmarkTag(tag = "29c479a1-4b71-4934-9b70-7e85f6b63dde")
    public void DES_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/CBC/PKCS5Padding (56)");
        encrypt(bh, cipherDescription);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "DES/EBC/NoPadding (56)")
    @BenchmarkMetaData(key = "libVendor", value = "javax.crypto")
    @BenchmarkMetaData(key = "actionName", value = "encrypt")
    @BenchmarkTag(tag = "8017f276-5a25-4f27-8144-fe21001424a1")
    public void DES_EBC_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/ECB/NoPadding (56)");
        encrypt(bh, cipherDescription);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "DES/EBC/PKCS5Padding (56)")
    @BenchmarkMetaData(key = "libVendor", value = "javax.crypto")
    @BenchmarkMetaData(key = "actionName", value = "encrypt")
    @BenchmarkTag(tag = "7cc0cf8e-446c-45ff-ba7a-2ebb774606c0")
    public void DES_EBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DES/ECB/PKCS5Padding (56)");
        encrypt(bh, cipherDescription);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "DESede/CBC/NoPadding (168)")
    @BenchmarkMetaData(key = "libVendor", value = "javax.crypto")
    @BenchmarkMetaData(key = "actionName", value = "encrypt")
    @BenchmarkTag(tag = "d30810f9-19e6-4c08-bc73-a8c89152481f")
    public void DESede_CBS_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/CBC/NoPadding (168)");
        encrypt(bh, cipherDescription);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "DESede/CBC/PKCS5Padding (168)")
    @BenchmarkMetaData(key = "libVendor", value = "javax.crypto")
    @BenchmarkMetaData(key = "actionName", value = "encrypt")
    @BenchmarkTag(tag = "a5a880b9-901b-48ad-9d2d-c43e4b0779b5")
    public void DESede_CBC_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/CBC/PKCS5Padding (168)");
        encrypt(bh, cipherDescription);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "DESede/ECB/NoPadding (168)")
    @BenchmarkMetaData(key = "libVendor", value = "javax.crypto")
    @BenchmarkMetaData(key = "actionName", value = "encrypt")
    @BenchmarkTag(tag = "318bd149-b213-4f87-b586-2add4d49b025")
    public void DESede_ECB_NoPadding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/ECB/NoPadding (168)");
        encrypt(bh, cipherDescription);
    }

    @Benchmark
    @BenchmarkMetaData(key = "api", value = "DESede/ECB/PKCS5Padding (168)")
    @BenchmarkMetaData(key = "libVendor", value = "javax.crypto")
    @BenchmarkMetaData(key = "actionName", value = "encrypt")
    @BenchmarkTag(tag = "549b8eb6-31e9-4b09-a56d-07a37c8c225d")
    public void DESede_ECB_PKCS5Padding(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke("DESede/ECB/PKCS5Padding (168)");
        encrypt(bh, cipherDescription);
    }


    private void encrypt(Blackhole bh, CipherDescription cipherDescription) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchProviderException {
        String cipherName = cipherDescription.getCipherName();
        String keyType = cipherDescription.getKeyType();
        Integer keySize = cipherDescription.getKeySize();
        Cipher cipher = Cipher.getInstance(cipherName, provider);
        KeyGenerator generator = KeyGenerator.getInstance(keyType);
        generator.init(keySize);
        SecretKey secretKey = generator.generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        bh.consume(cipher.doFinal(testString.getBytes()));
    }

    private static class CipherDescription {

        private String cipherName;

        private String keyType;

        private Integer keySize;

        public String getCipherName() {
            return cipherName;
        }

        public String getKeyType() {
            return keyType;
        }

        public Integer getKeySize() {
            return keySize;
        }

        public CipherDescription invoke(String descpr) {
            Pattern pattern = Pattern.compile("(.*)(\\/.*\\/.*) \\((\\d*)\\)");
            Matcher matcher = pattern.matcher(descpr);
            if (matcher.matches()) {
                cipherName = matcher.group(1) + matcher.group(2);
                keyType = matcher.group(1);
                keySize = Integer.parseInt(matcher.group(3));
            }
            return this;
        }

        @Override
        public String toString() {
            return "CipherDescription{" + "cipherName='" + cipherName + '\'' + ", keyType='" + keyType + '\'' + ", keySize=" + keySize + '}';
        }
    }
}
