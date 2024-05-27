package Server.Controller;

import java.security.MessageDigest;

public class HashController {
    public static String bytesToHex(byte[] bytes) {
        //su dung String builder de noi chuoi ma khong can tao them doi tuong khac
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            //chuyen hoa thanh hex va noi vao cuoi chuoi
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String sha256(String input) {
        try {
            //ma hoa
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            return bytesToHex(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
