package vn.itechcorp.admin.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Util {
    public static boolean isNumericInteger(String str) {
        return str.matches("-?\\d+");  //match a number with optional '-' and decimal.
    }

    public static boolean isNumericFloat(String str) {
        return str.matches("-?\\d+((\\.|,)\\d+)?");  //match a number with optional '-' and decimal.
    }



    public static String byteToImageSrc(byte[] data) {
        if (data == null) return null;

        try (InputStream is = new BufferedInputStream(new ByteArrayInputStream(data))) {
            String mimeType = URLConnection.guessContentTypeFromStream(is);
            return "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(data);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String toCleanText(String text) {
        if (text == null || text.trim().isEmpty()) return null;
        return text.trim();
    }

    public static Long toCleanID(Long id) {
        if (id == null || id <= 0) return null;
        return id;
    }

    public static String toCleanDate(String dateString, String dateFormat) {
        dateString = toCleanText(dateString);
        if (dateString == null) return null;

        try {
            Date date = new SimpleDateFormat(dateFormat).parse(dateString);
            if (date == null) return null;
            return dateString;
        } catch (Exception ex) {
            return null;
        }
    }

}
