package org.koala.util;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

public class MD5Tool {

	public static final String PREFIX_ZERO = "0";
	private static ThreadLocal<MD5Tool> threadLocal = new ThreadLocal<MD5Tool>();

	private MD5Tool() {

	}

	public static MD5Tool getInstance() {
		MD5Tool mt = threadLocal.get();
		if (mt == null) {
			mt = new MD5Tool();
			threadLocal.set(mt);
		}
		return mt;
	}

	public String byteArrayToHexString(byte[] b) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			builder.append(byteToHexString(b[i]));
		}
		return builder.toString();
	}

	public String byteToHexString(byte b) {
		int value = b & 0xFF;
		StringBuilder builder = new StringBuilder();
		if (value >= 0x10) {
			builder.append(Integer.toHexString(value));
		} else {
			builder.append(PREFIX_ZERO);
			builder.append(Integer.toHexString(value));
		}
		return builder.toString();
	}

	/**
	 * Handle the file input and return the MD5 code.
	 *
	 * @param file the file path to read from
	 * @return the MD5 code
	 */
	public String handleFileInput(File file) throws Exception {
		FileChannel fc = null;
		try {
			fc = new FileInputStream(file).getChannel();
			MessageDigest md = MessageDigest.getInstance("MD5");
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			while (fc.read(buffer) != -1) {
				buffer.flip();
				md.update(buffer);
				buffer.clear();
			}
			return new String(byteArrayToHexString(md.digest()));
		} finally {
			if (fc != null) {
				fc.close();
			}
		}
	}

	public String handleString(String str) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str.getBytes());
		return new String(byteArrayToHexString(md.digest()));
	}
}
