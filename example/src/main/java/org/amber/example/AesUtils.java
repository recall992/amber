package org.amber.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

public class AesUtils {
    /**
     * 算法/模式/填充
     **/
    private static final String CipherMode = "AES/ECB/PKCS5Padding";

    /**
     * 创建密钥
     **/
    private static SecretKeySpec createKey(String password) {
        byte[] data = null;
        if (password == null) {
            password = "";
        }
        StringBuffer sb = new StringBuffer(32);
        sb.append(password);
        while (sb.length() < 32) {
            sb.append("0");
        }
        if (sb.length() > 32) {
            sb.setLength(32);
        }

        try {
            data = sb.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        return new SecretKeySpec(data, "AES");
    }

    public static String create32Key(String password) {
        StringBuffer sb = new StringBuffer(32);
        sb.append(password);
        while (sb.length() < 32) {
            sb.append("0");
        }
        if (sb.length() > 32) {
            sb.setLength(32);
        }
        return sb.toString();
    }

    /**
     * 加密字节数据
     **/
    public static byte[] encryptJava(byte[] content, String password) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

            SecretKeySpec key = createKey(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 解密字节数组
     **/
    public static byte[] decryptJava(byte[] content, String password) {
        try {
            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            SecretKeySpec key = initKeyForAES(password);
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static SecretKeySpec initKeyForAES(String key) throws NoSuchAlgorithmException {
        if (null == key || key.length() == 0) {
            throw new NullPointerException("key not is null");
        }
        SecretKeySpec key2 = null;
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(key.getBytes());
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            key2 = new SecretKeySpec(enCodeFormat, "AES");
        } catch (NoSuchAlgorithmException ex) {
            throw new NoSuchAlgorithmException();
        }
        return key2;

    }

    /**
     * 加密(结果为16进制字符串)
     **/
    public static String encryptJava(String content, String password) {
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(content)) {
            return content;
        }
        byte[] data = null;
        try {
            data = content.getBytes("UTF-8");
        } catch (Exception e) {

        }
        data = encryptJava(data, password);
        return Base64Utils.encode(data);
    }

    public static String decryptJava(String content, String password) {
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(content)) {
            return content;
        }
        if (StringUtils.isEmpty(password)) {
            return content;
        }
        byte[] data = decryptJava(Base64Utils.decode(content), password);
        if (data == null) {
            return null;
        }

        String result = "";
        try {
            result = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {

        }
        return result;
    }


    /**
     * 加密(结果为16进制字符串)
     **/
    public static String encrypt(String content, String password) {
        return encryptJava(content, password);
    }

    /**
     * 解密16进制的字符串为字符串
     **/
    public static String decrypt(String content, String password) {
        return decryptJava(content, password);
    }

    public static void main(String[] args) {
        String value = "0008+tAJciCqY3XPIe3rmQ==";
        System.out.println(AesUtils.decryptJava(value, "09415d320f19e067725f3a199e2956de"));
    }
}