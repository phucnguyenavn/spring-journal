package personal.springutility.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class StringUtils {

    public byte[] toByte(String img) {
        if (img == null) {
            return null;
        }
        return Base64.getDecoder().decode(img.getBytes());
    }

    public String toBase64(byte[] img) {
        if (img == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(img);
    }


}
