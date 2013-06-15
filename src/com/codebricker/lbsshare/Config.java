package com.codebricker.lbsshare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.codebricker.lbsshare.common.utils.DESCoder;
import com.codebricker.lbsshare.common.utils.HttpUtil;
import com.codebricker.lbsshare.common.utils.HttpUtil.HttpStrResponse;

public class Config {
	private static String CONF_URL = "bsrIGBPKq/pS3To/roH9mkdpj1o0Mmb7NIZfpvtwmeP1mSgSFkJHxI6j5VSh757uQPVsQjv2w1NR8Rl5xWsktA==";
	private static final String CS = "UTF-8";
	private static Properties prop;

	public static String get(String key, String defaultValue) {
		if (prop == null) {
			return defaultValue;
		}
		return prop.getProperty(key, defaultValue);
	}

	private static List<Runnable> listenerList = new ArrayList<Runnable>();

	public static void addConfigListener(Runnable r) {
		listenerList.add(r);
	}

	public static void init() throws IOException {
		DESCoder origDes = new DESCoder("12345678".getBytes(CS));
		CONF_URL = new String(origDes.decrypt(CONF_URL), CS);
		HttpStrResponse resp = HttpUtil.getStr(CONF_URL, null, null);
		String http = resp.getContent();
		BufferedReader br = new BufferedReader(new StringReader(http));
		String firstLine = br.readLine();
		System.out.println("firstLine all:" + firstLine);

		int flagStartIdx = firstLine.indexOf('(');
		int[] startAndEnd = getStartAndEnd(firstLine.substring(flagStartIdx));
		System.out.println("start and end:" + startAndEnd[0] + ","
				+ startAndEnd[1]);
		firstLine = firstLine.substring(0, flagStartIdx);
		byte[] keyContent = origDes.decrypt(firstLine);
		System.out.println("firstLine all:" + new String(keyContent, CS) + "("
				+ startAndEnd[0] + "," + startAndEnd[1] + ")");
		byte[] codeKey = Arrays.copyOfRange(keyContent, startAndEnd[0],
				startAndEnd[1]);
		System.out.println("firstLine:" + firstLine);
		System.out.println("truekey:" + new String(codeKey, CS));

		DESCoder propCoder = new DESCoder(codeKey);
		String propContent = new String(propCoder.decrypt(br.readLine()), CS);
		prop = new Properties();
		StringReader sr = new StringReader(propContent);
		prop.load(sr);
		br.close();
		sr.close();

		for (Runnable r : listenerList) {
			r.run();
		}
		listenerList.clear();
	}

	private static int[] getStartAndEnd(String src) {
		String s = src.substring(1, src.length() - 1);
		String[] part = s.split(",");
		int[] rtn = { Integer.valueOf(part[0]), Integer.valueOf(part[1]) };
		return rtn;
	}
}
