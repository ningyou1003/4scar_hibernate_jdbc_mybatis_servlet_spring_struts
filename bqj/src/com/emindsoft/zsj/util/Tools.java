/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.emindsoft.zsj.util;

import com.jfinal.kit.StrKit;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public final class Tools {

	public static final String inputStream2String(InputStream in)
			throws IOException {
		if (in == null)
			return "";
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n, "UTF-8"));
		}
		return out.toString();
	}

	public static final boolean checkSignature(String token, String signature,
			String timestamp, String nonce) {
		List<String> params = new ArrayList<String>();
		params.add(token);
		params.add(timestamp);
		params.add(nonce);
		Collections.sort(params, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		String temp = params.get(0) + params.get(1) + params.get(2);
		return DigestUtils.shaHex(temp).equals(signature);
	}

	public static final <T> T getSubVO(Class<T> clz, HttpServletRequest requst) {
		Object model = null;
		try {
			model = clz.newInstance();
			String modelName = clz.getSimpleName();
			Method[] methods = clz.getMethods();
			for (Method method : methods) {
				String methodName = method.getName();
				if (methodName.startsWith("set") == false) // only setter method
					continue;

				Class<?>[] types = method.getParameterTypes();
				if (types.length != 1) // only one parameter
					continue;

				String attrName = methodName.substring(3);
				attrName = StrKit.firstCharToLowerCase(attrName);
				if (types[0] == String[].class) {
					String[] value = requst.getParameterValues(modelName + "["
							+ StrKit.firstCharToLowerCase(attrName) + "][]");
					if (value != null) {
						method.invoke(model, (Object)value);
					}
				} else {
					String value = requst.getParameter(modelName + "["
							+ StrKit.firstCharToLowerCase(attrName) + "]");
					if (value != null) {
						method.invoke(model,
								TypeConverter.convert(types[0], value));
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return (T) model;
	}

	public static String getRemoteHost(
			javax.servlet.http.HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
	
	public static String getRandomSerial() {
		//日期部分(17位)
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.CHINA);
		//随机数部分(8位)
		Random rad = new Random();
		String num = rad.nextInt(100000000) + "";
		//不够8位给补齐
		for (int i = num.length(); i < 8; i++) {
			num += rad.nextInt(10);
		}
		String serial = df.format(new Date()) + num;
		return serial;
	}
}
