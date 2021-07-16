package com.ljm.resource.rx;


import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

public class MainTest {
    private static String code = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApCNjsTlE3AIR1YXyhE9M5SQ9xf1o0+W528nLdvI7ZmCOdFMed8SA3L91YFP+8wBl1we6BwVQBHo/4OQwUNlwVE7BgtsV5D67/rR74d0vGJNLBNMx6V3D/Uf+QgXBlPESWRwRkkHl2RbzTWbI60X0mLTxkvvpEYSvgFytlv5QL+on3TKp/Q4UiUk4MmWnY1taNLw7rAM8/HXYotC+jnhMgjvYEf5Ank/F0Tm4WZq/QlJcT2pOEN8vanGT325XhyshdqZJgG2IT1nt5EdtVjXySF9AMpnA2Cmz35Qygy/rx3+0+82yBpCgxjv0O7pYvtJ5tIXWkcUB3V6HtXiCkMNWRwIDAQAB";

    public static void main(String[] args) {
        String encryptPsw = null;
        try {
            byte[] bytes = encryptByPublicKey("123456".getBytes("utf-8"), code);
            encryptPsw = byteArrayToBase64(bytes);
//            byte[] bytes = RSACoderUtil.encryptByPublicKey("1111111w".getBytes("utf-8"), code);
//            encryptPsw = Base64Util.byteArrayToBase64(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(encryptPsw);
    }
    public static String byteArrayToBase64(byte[] a) {
        return byteArrayToBase64(a, false);
    }

    public static byte[] base64ToByteArray(String s) {
        return base64ToByteArray(s, false);
    }

    private static int base64toInt(char c, byte[] alphaToInt) {
        int result = alphaToInt[c];
        if (result < 0) {
            throw new IllegalArgumentException("Illegal character " + c);
        }
        return result;
    }
    private static final byte[] base64ToInt = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
            22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51 };

    private static final byte[] altBase64ToInt = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
            5, 6, 7, 8, -1, 62, 9, 10, 11, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 12, 13, 14, -1, 15, 63, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, 17, -1, 18, 19, 21, 20, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 22, 23, 24, 25 };

    private static byte[] base64ToByteArray(String s, boolean alternate) {
        byte[] alphaToInt = (alternate) ? altBase64ToInt : base64ToInt;
        int sLen = s.length();
        int numGroups = sLen / 4;
        if (4 * numGroups != sLen) {
            throw new IllegalArgumentException("String length must be a multiple of four.");
        }
        int missingBytesInLastGroup = 0;
        int numFullGroups = numGroups;
        if (sLen != 0) {
            if (s.charAt(sLen - 1) == '=') {
                ++missingBytesInLastGroup;
                --numFullGroups;
            }
            if (s.charAt(sLen - 2) == '=') {
                ++missingBytesInLastGroup;
            }
        }
        byte[] result = new byte[3 * numGroups - missingBytesInLastGroup];

        int inCursor = 0;
        int outCursor = 0;
        for (int i = 0; i < numFullGroups; ++i) {
            int ch0 = base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch1 = base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch2 = base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch3 = base64toInt(s.charAt(inCursor++), alphaToInt);
            result[(outCursor++)] = (byte) (ch0 << 2 | ch1 >> 4);
            result[(outCursor++)] = (byte) (ch1 << 4 | ch2 >> 2);
            result[(outCursor++)] = (byte) (ch2 << 6 | ch3);
        }

        if (missingBytesInLastGroup != 0) {
            int ch0 = base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch1 = base64toInt(s.charAt(inCursor++), alphaToInt);
            result[(outCursor++)] = (byte) (ch0 << 2 | ch1 >> 4);

            if (missingBytesInLastGroup == 1) {
                int ch2 = base64toInt(s.charAt(inCursor++), alphaToInt);
                result[(outCursor++)] = (byte) (ch1 << 4 | ch2 >> 2);
            }

        }

        return result;
    }
    private static final char[] intToBase64 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
            'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };

    private static final char[] intToAltBase64 = { '!', '"', '#', '$', '%', '&', '\'', '(', ')', ',', '-', '.', ':', ';', '<', '>', '@', '[', ']', '^', '`', '_', '{', '|', '}', '~', 'a', 'b', 'c',
            'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '?' };

    private static String byteArrayToBase64(byte[] a, boolean alternate) {
        int aLen = a.length;
        int numFullGroups = aLen / 3;
        int numBytesInPartialGroup = aLen - 3 * numFullGroups;
        int resultLen = 4 * ((aLen + 2) / 3);
        StringBuffer result = new StringBuffer(resultLen);
        char[] intToAlpha = (alternate) ? intToAltBase64 : intToBase64;

        int inCursor = 0;
        for (int i = 0; i < numFullGroups; ++i) {
            int byte0 = a[(inCursor++)] & 0xFF;
            int byte1 = a[(inCursor++)] & 0xFF;
            int byte2 = a[(inCursor++)] & 0xFF;
            result.append(intToAlpha[(byte0 >> 2)]);
            result.append(intToAlpha[(byte0 << 4 & 0x3F | byte1 >> 4)]);
            result.append(intToAlpha[(byte1 << 2 & 0x3F | byte2 >> 6)]);
            result.append(intToAlpha[(byte2 & 0x3F)]);
        }

        if (numBytesInPartialGroup != 0) {
            int byte0 = a[(inCursor++)] & 0xFF;
            result.append(intToAlpha[(byte0 >> 2)]);
            if (numBytesInPartialGroup == 1) {
                result.append(intToAlpha[(byte0 << 4 & 0x3F)]);
                result.append("==");
            } else {
                int byte1 = a[(inCursor++)] & 0xFF;
                result.append(intToAlpha[(byte0 << 4 & 0x3F | byte1 >> 4)]);
                result.append(intToAlpha[(byte1 << 2 & 0x3F)]);
                result.append('=');
            }

        }

        return result.toString();
    }

    public static byte[] decryptBASE64(String key) {
        return base64ToByteArray(key);
    }
    public static byte[] encryptByPublicKey(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = decryptBASE64(publicKey);

        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicKeyObj = keyFactory.generatePublic(x509EncodedKeySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(1, publicKeyObj);

        byte[] dataReturn = null;
        for (int i = 0; i < data.length; i += 117) {
            byte[] doFinal = cipher.doFinal(subarray(data, i, i + 117));
            dataReturn = addAll(dataReturn, doFinal);
        }

        return dataReturn;
    }

    public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        if (newSize <= 0) {
            return new byte[0];
        }

        byte[] subarray = new byte[newSize];
        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);
        return subarray;
    }

    public static byte[] clone(byte[] array) {
        if (array == null) {
            return null;
        }
        return (byte[]) (byte[]) array.clone();
    }
    public static byte[] addAll(byte[] array1, byte[] array2) {
        if (array1 == null)
            return clone(array2);
        if (array2 == null) {
            return clone(array1);
        }
        byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }
}
