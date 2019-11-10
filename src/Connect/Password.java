package Connect;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    public static String hasher(String password) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        byte[] data;
        try {
            data = messageDigest.digest(password.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        String hashedPassword = "";
        for (byte a : data) {
            hashedPassword += Integer.toHexString(0xff & a);
        }
        return hashedPassword;
    }

}
