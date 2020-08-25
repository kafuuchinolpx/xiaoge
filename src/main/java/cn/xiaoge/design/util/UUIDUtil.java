package cn.xiaoge.design.util;


import java.util.Base64;
import java.util.UUID;

public class UUIDUtil {
    private UUIDUtil(){
    }
    /**
    * 获取32位的UUID
    * @return UUID字符串
    */
    public static String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
    * 获取36位的UUID
    * @return UUID字符串
    */
    public static String getUUID36() {
        return UUID.randomUUID().toString();
    }

    public static String[] chars = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    /**
    * 获取8位的UUID
    * @return UUID字符串
    */
    public static String getUUID8() {
        StringBuffer stringBuffer = new StringBuffer();
        String uuid = getUUID32();
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            // 16进制为基解析
            int strInteger = Integer.parseInt(str, 16);
            // 0x3E -> 字典总数 62
            stringBuffer.append(chars[strInteger % 0x3E]);
        }
        return stringBuffer.toString();
    }

    /**
    * 获取16位的UUID
    * @return UUID字符串
    */
    public static String getUUID16() {
        StringBuffer stringBuffer = new StringBuffer();
        String uuid = getUUID32();
        for (int i = 0; i < 16; i++) {
            String str = uuid.substring(i * 2, i * 2 + 2);
            // 16进制为基解析
            int strInteger = Integer.parseInt(str, 16);
            // 0x3E -> 字典总数 62
            stringBuffer.append(chars[strInteger % 0x3E]);
        }
        return stringBuffer.toString();
    }

    /**
    * 获取48位的UUID
    * @return UUID字符串
    */
    public static String getUUID48() {
        return Base64.getEncoder().encodeToString(getUUID36().getBytes());
    }

    public static void main(String[] args) {
        System.out.println(getUUID8());
    }

}
