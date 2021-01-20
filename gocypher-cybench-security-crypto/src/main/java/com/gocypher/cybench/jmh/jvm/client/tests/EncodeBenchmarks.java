package com.gocypher.cybench.jmh.jvm.client.tests;

import com.gocypher.cybench.core.annotation.BenchmarkTag;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@State(Scope.Benchmark)
public class EncodeBenchmarks {

    @Param({
            "AES/CBC/NoPadding (128)",
            "AES/CBC/PKCS5Padding (128)",
            "AES/ECB/NoPadding (128)",
            "AES/ECB/PKCS5Padding (128)",
            "DES/CBC/NoPadding (56)",
            "DES/CBC/PKCS5Padding (56)",
            "DES/ECB/NoPadding (56)",
            "DES/ECB/PKCS5Padding (56)",
            "DESede/CBC/NoPadding (168)",
            "DESede/CBC/PKCS5Padding (168)",
            "DESede/ECB/NoPadding (168)",
            "DESede/ECB/PKCS5Padding (168)"
//            "RSA/ECB/PKCS1Padding (1024)",
//            "RSA/ECB/PKCS1Padding (2048)",
//            "RSA/ECB/OAEPWithSHA-1AndMGF1Padding (1024)",
//            "RSA/ECB/OAEPWithSHA-1AndMGF1Padding (2048)",
//            "RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024)",
//            "RSA/ECB/OAEPWithSHA-256AndMGF1Padding (1024)"
    })
    private String cipherSymmetric;

    @Param({"The quick brown fox jumps over the lazy dog", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec leo risus, aliquet in ligula non, maximus rhoncus eros. Donec suscipit, purus at feugiat viverra, sem ante sodales turpis, et condimentum mi dui eu nisl. Sed id leo commodo, tincidunt neque et, consequat ipsum. Vivamus elementum malesuada varius. Curabitur a mi nec nibh dapibus fermentum. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Mauris sed leo elementum, finibus ligula ac, sollicitudin sapien. Cras gravida nisi in augue pulvinar, eget aliquet nunc porta. Curabitur ut rutrum nunc. Curabitur vel arcu velit. Duis tincidunt nisi sed fermentum iaculis. Nam fermentum elementum ex. Fusce convallis turpis vitae convallis hendrerit. Mauris finibus, tellus eleifend interdum mattis, nunc nisl condimentum enim, vel faucibus risus purus in enim.\n" +
            "\n" +
            "Integer in neque elementum, egestas turpis in, porta orci. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Suspendisse sit amet mauris quis urna vestibulum feugiat. Cras quis leo sollicitudin, eleifend nibh a, sodales libero. Suspendisse ac porttitor ipsum, sit amet pellentesque magna. Vestibulum sed diam a risus tempus placerat. Suspendisse eu orci pretium, eleifend lectus ut, mollis nisl. Cras accumsan mi at ornare fermentum. Donec velit justo, vestibulum nec convallis ac, blandit et lectus. Cras sit amet molestie leo, ac aliquam nisl. Nunc laoreet laoreet erat quis laoreet. Curabitur vel nibh posuere, dignissim lectus malesuada, commodo eros. Phasellus quis aliquet velit, ac posuere magna.\n" +
            "\n" +
            "Praesent felis nibh, sollicitudin id egestas sed, consectetur nec tortor. Proin pretium risus purus. Nulla sit amet sollicitudin urna, sit amet volutpat leo. Aenean sodales nisi id elit mollis feugiat. Donec iaculis molestie magna, vitae varius massa dictum egestas. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse consectetur nisi sed tempus rutrum.\n" +
            "\n" +
            "Integer faucibus eleifend tortor, id rhoncus tellus. Pellentesque ut mollis risus. Curabitur quis aliquet tortor. Aenean vehicula facilisis tristique. Suspendisse potenti. Aliquam et volutpat nisi. Sed dictum mi sed porta hendrerit. Fusce at turpis arcu. Vivamus nisl leo, gravida non tempus ut, accumsan ac ligula. In sodales est elit, sed maximus augue hendrerit sit amet. Morbi non diam non orci suscipit tempor non vel turpis. Suspendisse interdum mattis ex.\n" +
            "\n" +
            "Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec dignissim dolor id sem hendrerit rutrum. Pellentesque vulputate hendrerit aliquet. Maecenas congue convallis ipsum, quis suscipit mi bibendum et. Sed ac dolor sed lectus bibendum convallis. Vivamus a venenatis ex, sed pulvinar orci. Fusce ullamcorper nunc non leo porttitor mollis. Donec hendrerit ipsum pulvinar tempus molestie. Nunc id urna neque. Suspendisse eget nulla ornare, tincidunt arcu eu, tincidunt leo. Curabitur semper elit tellus, nec feugiat nunc sagittis eu. Pellentesque dui mi, egestas et felis aliquet, fermentum fermentum justo. Curabitur massa enim, tristique maximus sem non, interdum placerat elit. Sed lobortis lacus eget ante mollis lobortis.\n" +
            "\n" +
            "Sed accumsan pharetra erat quis lobortis. Nam sed sodales justo. Donec ut aliquet augue. Morbi ac varius turpis. Suspendisse potenti. Donec non neque augue. Nulla congue porttitor massa, mattis aliquet sapien sodales vitae. Nulla efficitur enim ut velit viverra, nec faucibus sem auctor. Fusce sit amet lobortis ex. Vivamus sit amet est non purus pellentesque eleifend.\n" +
            "\n" +
            "Donec fermentum orci nisl, ut maximus quam lacinia ac. Aliquam sed dolor a est elementum interdum eget tempus tortor. Donec erat ligula, congue at porta vel, finibus accumsan arcu. Maecenas eget risus ante. Maecenas nec."})
    private String testString;


    
    @Benchmark
    @BenchmarkTag(tag = "c9906f2c-b178-45ad-9cc6-d929efa4ba4e")
    public void cipherBenchmark(Blackhole bh) throws Exception {
        CipherDescription cipherDescription = new CipherDescription().invoke(this.cipherSymmetric);
        String cipherName = cipherDescription.getCipherName();
        String keyType = cipherDescription.getKeyType();
        Integer keySize = cipherDescription.getKeySize();


        Cipher cipher = Cipher.getInstance(cipherName);
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
            return "CipherDescription{" +
                    "cipherName='" + cipherName + '\'' +
                    ", keyType='" + keyType + '\'' +
                    ", keySize=" + keySize +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        String[] transformations = EncodeBenchmarks.class.getDeclaredField("cipherSymmetric").getDeclaredAnnotation(Param.class).value();
        for (String t : transformations) {

            CipherDescription cipherDescription = new CipherDescription().invoke(t);
            System.out.println(cipherDescription);
            String cipherName = cipherDescription.getCipherName();
            String keyType = cipherDescription.getKeyType();
            Integer keySize = cipherDescription.getKeySize();
            Cipher cipher = Cipher.getInstance(cipherName);
            KeyGenerator generator = KeyGenerator.getInstance(keyType);
            generator.init(keySize);
            SecretKey secretKey = generator.generateKey();
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            cipher.doFinal();
        }
    }
}
