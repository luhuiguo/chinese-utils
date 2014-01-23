/** 
 * File    : ChineseUtils.java 
 * Created : 2014年1月16日 
 * By      : luhuiguo 
 */
package com.luhuiguo.chinese;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.luhuiguo.chinese.pinyin.Pinyin;
import com.luhuiguo.chinese.pinyin.PinyinFormat;

/**
 * 中文相关工具类。
 * 
 * @author luhuiguo
 */
public class ChineseUtils {

	/**
	 * 转换成简体中文。
	 * 
	 * @param str
	 *            输入的字符串。
	 * @return 转换后的简体中文字符串。
	 */
	public static String toSimplified(String str) {
		return Converter.SIMPLIFIED.convert(str);
	}

	/**
	 * 转换成繁体中文。
	 * 
	 * @param str
	 *            输入的字符串。
	 * @return 转换后的繁体中文字符串。
	 */
	public static String toTraditional(String str) {
		return Converter.TRADITIONAL.convert(str);
	}

	/**
	 * 转换成中文拼音字符串。
	 * 
	 * @param str
	 *            输入的字符串。
	 * @param format
	 *            输出拼音的格式。
	 * @return 转换后的中文拼音字符串。
	 */
	public static String toPinyin(String str, PinyinFormat format) {
		return Pinyin.INSTANCE.convert(str, format);
	}

	/**
	 * 转换成中文拼音字符串。
	 * 
	 * @param str
	 *            输入的字符串。
	 * @return 转换后的中文拼音字符串。
	 */
	public static String toPinyin(String str) {
		return Pinyin.INSTANCE.convert(str, PinyinFormat.DEFAULT_PINYIN_FORMAT);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int mode = 0;
		String textLine = "";
		printHelp();
		printHit(mode);
		while (true) {
			textLine = readInputTextLine();
			if (textLine == null || textLine.length() < 1) {
				printHelp();
				printHit(mode);
			}
			if ("q".equals(textLine)) {
				System.exit(0);
			} else if ("t".equals(textLine)) {
				mode = 2;

			} else if ("s".equals(textLine)) {
				mode = 1;

			} else if ("".equals(textLine)) {
				mode = 0;

			} else {

				switch (mode) {
				case 1:
					System.out.println("简体: "+ChineseUtils.toSimplified(textLine));
					break;
				case 2:
					System.out.println("繁体: "+ChineseUtils.toTraditional(textLine));
					break;
				default:
					System.out.println("拼音: "+ChineseUtils.toPinyin(textLine,PinyinFormat.UNICODE_PINYIN_FORMAT));
					break;
				}

			}
			printHit(mode);

		}

	}

	public static String readInputTextLine() {
		BufferedReader lineOfText = new BufferedReader(new InputStreamReader(
				System.in));
		String textLine = null;
		try {
			textLine = lineOfText.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return textLine;
	}

	public static void printHelp() {
		System.out
				.println("请输入希望转换的中文或输入[q]退出、[s]转简体、[t]转繁体、[p]转拼音。");
	}
	
	public static void printHit(int mode) {
		switch (mode) {
		case 1:
			System.out.print("转简> ");
			break;
		case 2:
			System.out.print("转繁> ");
			break;
		default:
			System.out.print("转拼> ");
			break;
		}
	}
}
