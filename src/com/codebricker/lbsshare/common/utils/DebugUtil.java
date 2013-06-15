package com.codebricker.lbsshare.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public final class DebugUtil {
	public static <T> void printArr(T[] arr) {
		if (arr != null) {
			for (T t : arr) {
				System.out.println(t);
			}
		}
	}

	public static void close(Object... objs) {
		if (CommUtil.isNull(objs)) {
			return;
		}
		try {
			for (Object obj : objs) {
				if (obj instanceof InputStream) {
					((InputStream) obj).close();
				} else if (obj instanceof OutputStream) {
					((OutputStream) obj).close();
				} else if (obj instanceof Reader) {
					((Reader) obj).close();
				} else if (obj instanceof Writer) {
					((Writer) obj).close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
