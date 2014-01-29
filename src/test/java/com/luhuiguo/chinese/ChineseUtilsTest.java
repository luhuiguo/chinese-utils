/** 
 * File    : ChineseUtilsTest.java 
 * Created : 2014年1月22日 
 * By      : luhuiguo 
 */
package com.luhuiguo.chinese;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.luhuiguo.chinese.pinyin.PinyinFormat;

/**
 * 
 * @author luhuiguo
 */
public class ChineseUtilsTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {

		// for (char ch = CJK_UNIFIED_IDEOGRAPHS_START; ch <=
		// CJK_UNIFIED_IDEOGRAPHS_END; ch++) {
		// String c = String.valueOf(ch);
		// String s = toSimplified(c);
		// String t = toTraditional(c);
		// String p = toPinyin(c, new PinyinFormat());
		//
		// System.out.println(c + " -> " + s + " => " + t + " => " + p);
		//
		//
		// }

		// String c = "中华人民共和国女人执拗挨打";
		// String s = ChineseUtils.toSimplified(c);
		// String t = ChineseUtils.toTraditional(c);
		// String p1 = ChineseUtils.toPinyin(c,
		// PinyinFormat.DEFAULT_PINYIN_FORMAT);
		// String p2 = ChineseUtils.toPinyin(c,
		// PinyinFormat.UNICODE_PINYIN_FORMAT);
		// String p3 = ChineseUtils.toPinyin(c,
		// PinyinFormat.TONELESS_PINYIN_FORMAT);
		// String p4 = ChineseUtils.toPinyin(c,
		// PinyinFormat.ABBR_PINYIN_FORMAT);
		// System.out.println(c + " -> " + s + " => " + t);
		// System.out.println(p1);
		// System.out.println(p2);
		// System.out.println(p3);
		// System.out.println(p4);

		String s = "头发发财";
		System.out.println(s + " => " + ChineseUtils.toTraditional(s));
		s = "簡訊資料庫";
		System.out.println(s + " => " + ChineseUtils.toSimplified(s));
		s = "长江成长";
		System.out.println(s + " => " + ChineseUtils.toPinyin(s) + " ("
				+ ChineseUtils.toPinyin(s, PinyinFormat.UNICODE_PINYIN_FORMAT)
				+ ") - "
				+ ChineseUtils.toPinyin(s, PinyinFormat.ABBR_PINYIN_FORMAT));
		// fail("Not yet implemented");
	}
}
