/** 
 * File    : Converter.java 
 * Created : 2014年1月16日 
 * By      : luhuiguo 
 */
package com.luhuiguo.chinese;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * 
 * @author luhuiguo
 */
public enum Converter {
	SIMPLIFIED(false), TRADITIONAL(true);

	public static final char CJK_UNIFIED_IDEOGRAPHS_START = '\u4E00';
	public static final char CJK_UNIFIED_IDEOGRAPHS_END = '\u9FA5';
	public static final String SIMPLIFIED_MAPPING_FILE = "/simp.txt";
	public static final String SIMPLIFIED_LEXEMIC_MAPPING_FILE = "/simplified.txt";
	public static final String TRADITIONAL_MAPPING_FILE = "/trad.txt";
	public static final String TRADITIONAL_LEXEMIC_MAPPING_FILE = "/traditional.txt";

	public static final String EMPTY = "";
	public static final String SHARP = "#";
	public static final String EQUAL = "=";

	private char[] chars = null;

	private Trie<String> dict = null;

	private int maxLen = 2;

	Converter(boolean s2t) {
		loadCharMapping(s2t);
		loadLexemicMapping(s2t);
	}

	public void loadCharMapping(boolean s2t) {

		String mappingFile = SIMPLIFIED_MAPPING_FILE;

		if (s2t) {
			mappingFile = TRADITIONAL_MAPPING_FILE;
		}

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new BufferedInputStream(getClass().getResourceAsStream(
							mappingFile)), StandardCharsets.UTF_8));

			CharArrayWriter out = new CharArrayWriter();
			String line = null;
			while (null != (line = in.readLine())) {
				// line = line.trim();
				out.write(line);
			}
			chars = out.toCharArray();
			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadLexemicMapping(boolean s2t) {

		String mappingFile = SIMPLIFIED_LEXEMIC_MAPPING_FILE;

		if (s2t) {
			mappingFile = TRADITIONAL_LEXEMIC_MAPPING_FILE;
		}

		dict = new Trie<String>();

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new BufferedInputStream(getClass().getResourceAsStream(
							mappingFile)), StandardCharsets.UTF_8));

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
				dict.add(pair[0], pair[1]);
			}

			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public char convert(char ch) {
		// if (Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
		// .equals(Character.UnicodeBlock.of(ch))) {
		if (ch >= CJK_UNIFIED_IDEOGRAPHS_START
				&& ch <= CJK_UNIFIED_IDEOGRAPHS_END) {
			return chars[ch - CJK_UNIFIED_IDEOGRAPHS_START];
		} else {
			return ch;
		}
	}
	
	public void convert(Reader reader, Writer writer) throws IOException {

		PushbackReader in = new PushbackReader(new BufferedReader(reader),
				maxLen);

		char[] buf = new char[maxLen];

		int len = -1;
		while ((len = in.read(buf)) != -1) {
			TrieNode<String> node = dict.bestMatch(buf, 0, len);

			if (node != null) {
				int offset = node.getLevel();
				writer.write(node.getValue());
				in.unread(buf, offset, len - offset);
			} else {
				in.unread(buf, 0, len);
				char ch = (char) in.read();
				writer.write(convert(ch));
			}
			//buf = new char[maxLen];

		}

	}

	public String convert(String str) {
		String ret = str;
		Reader in = new StringReader(str);
		Writer out = new StringWriter();
		try {
			convert(in, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ret = out.toString();
		return ret;

	}

}
