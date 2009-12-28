package org.koala.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class MD5Tool {

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"
    };

    private String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * Handle the file input and return the MD5 code.
     * 
     * @param file
     *            the file path to read from
     * @return the MD5 code
     */
    public String handleFileInput(File file) throws Exception {
        BufferedInputStream br = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            br = new BufferedInputStream(fis);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[256];
            int bytesRead = 0;
            while ((bytesRead = br.read(buffer, 0, 256)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
            return new String(byteArrayToHexString(md.digest()));
        } catch (Exception e) {
            throw e;
        } finally {
            if (br != null) {
                br.close();
            }
        }
    }

    public String handleString(String str) throws Exception {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new String(byteArrayToHexString(md.digest()));
        } catch (Exception e) {
            throw e;
        }
    }
}
