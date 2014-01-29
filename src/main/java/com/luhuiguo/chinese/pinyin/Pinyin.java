/** 
 * File    : Pinyin.java 
 * Created : 2014年1月22日 
 * By      : luhuiguo 
 */
package com.luhuiguo.chinese.pinyin;

import static com.luhuiguo.chinese.Converter.CJK_UNIFIED_IDEOGRAPHS_END;
import static com.luhuiguo.chinese.Converter.CJK_UNIFIED_IDEOGRAPHS_START;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.luhuiguo.chinese.Trie;
import com.luhuiguo.chinese.TrieNode;

/**
 * 
 * @author luhuiguo
 */
public enum Pinyin {
	INSTANCE;

	public static final String PINYIN_MAPPING_FILE = "/pinyin.txt";
	public static final String POLYPHONE_MAPPING_FILE = "/polyphone.txt";

	public static final String EMPTY = "";
	public static final String SHARP = "#";
	public static final String EQUAL = "=";
	public static final String COMMA = ",";
	public static final String SPACE = " ";

	private List<String> pinyinDict = null;

	private Trie<String> polyphoneDict = null;

	private int maxLen = 2;

	Pinyin() {
		loadPinyinMapping();
		loadPolyphoneMapping();
	}

	public void loadPinyinMapping() {

		pinyinDict = new ArrayList<String>();

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new BufferedInputStream(getClass().getResourceAsStream(
							PINYIN_MAPPING_FILE)), StandardCharsets.UTF_8));
			String line = null;
			while (null != (line = in.readLine())) {
				if (line.length() == 0 || line.startsWith(SHARP)) {
					continue;
				}
				String[] pair = line.split(EQUAL);

				if (pair.length < 2) {
					pinyinDict.add(EMPTY);
				} else {
					pinyinDict.add(pair[1]);
				}
			}

			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadPolyphoneMapping() {

		polyphoneDict = new Trie<String>();

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new BufferedInputStream(getClass().getResourceAsStream(
							POLYPHONE_MAPPING_FILE)), StandardCharsets.UTF_8));

			String line = null;
			while (null != (line = in.readLine())) {
				// line = line.trim();
				if (line.length() == 0 || line.startsWith(SHARP)) {
					continue;
				}
				String[] pair = line.split(EQUAL);

				if (pair.length < 2) {
					continue;
				}
				maxLen = maxLen < pair[0].length() ? pair[0].length() : maxLen;
				polyphoneDict.add(pair[0], pair[1]);
			}

			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String[] toUnformattedPinyin(char ch) {

		if (ch >= CJK_UNIFIED_IDEOGRAPHS_START
				&& ch <= CJK_UNIFIED_IDEOGRAPHS_END) {
			String pinyinStr = pinyinDict
					.get(ch - CJK_UNIFIED_IDEOGRAPHS_START);
			return pinyinStr.split(COMMA);

		} else {
			return null;
		}
	}

	public String[] toFormattedPinyin(char ch, PinyinFormat format) {
		String[] pinyinStrArray = toUnformattedPinyin(ch);
		if (null != pinyinStrArray) {
			for (int i = 0; i < pinyinStrArray.length; i++) {
				pinyinStrArray[i] = PinyinFormatter.formatPinyin(
						pinyinStrArray[i], format);
			}
			return pinyinStrArray;
		} else
			return null;
	}

	public String toPinyin(char ch) {
		String[] pinyinStrArray = toUnformattedPinyin(ch);

		if (null != pinyinStrArray && pinyinStrArray.length > 0) {
			return pinyinStrArray[0];
		}
		return null;
	}

	public String toPinyin(char ch, PinyinFormat format) {

		String[] pinyinStrArray = null;

		pinyinStrArray = toFormattedPinyin(ch, format);

		if (null != pinyinStrArray && pinyinStrArray.length > 0) {
			return pinyinStrArray[0];
		}
		return null;
	}

	public void convert(Reader reader, Writer writer, PinyinFormat format)
			throws IOException {

		PushbackReader in = new PushbackReader(new BufferedReader(reader),
				maxLen);

		char[] buf = new char[maxLen];

		boolean writeSeparator = false;

		int len = -1;
		while ((len = in.read(buf)) != -1) {
			TrieNode<String> node = polyphoneDict.bestMatch(buf, 0, len);


			if (node != null) {
				int offset = node.getLevel();

				String str = node.getValue();

				String[] arr = str.split(SPACE);

				for (String s : arr) {
					String pinyinStr = PinyinFormatter.formatPinyin(s, format);

					if (null != pinyinStr) {
						if (writeSeparator) {
							writer.write(format.getSeparator());
						}
						writer.write(pinyinStr);
						writeSeparator = true;
					}

				}

				in.unread(buf, offset, len - offset);
			} else {
				in.unread(buf, 0, len);
				char ch = (char) in.read();
				String pinyinStr = toPinyin(ch, format);

				if (null != pinyinStr) {
					if (writeSeparator) {
						writer.write(format.getSeparator());
					}
					writer.write(pinyinStr);
					writeSeparator = true;
				} else {
					if (!format.isOnlyPinyin()) {
						writer.write(ch);
						writeSeparator = true;
					}

				}

			}
			//buf = new char[maxLen];

		}

	}

	public String convert(String str, PinyinFormat format) {
		String ret = str;
		Reader in = new StringReader(str);
		Writer out = new StringWriter();
		try {
			convert(in, out, format);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ret = out.toString();
		return ret;

	}
}
