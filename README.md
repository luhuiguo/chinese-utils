chinese-utils
=============

很久以前的项目 http://chineseutils.sourceforge.net/ 升级

中文相关工具包，目前提供中文简繁体互转，以及中文转拼音。未来会提供中文分词。

使用
--------------------
### Maven依赖
```xml
<dependency>
    <groupId>com.luhuiguo</groupId>
    <artifactId>chinese-utils</artifactId>
    <version>1.0</version>
</dependency>
```

### 命令行
```
 	java -jar chinese-utils-1.0.jar
```
会出现交互界面
```
请输入希望转换的中文或输入[q]退出、[s]转简体、[t]转繁体、[p]转拼音。
转拼> 
```

### 转简体

```java
	ChineseUtils.toSimplified(String str)
```

### 转繁体
```java
	ChineseUtils.toTraditional(String str)
```

### 转拼音
```java
 	ChineseUtils.toPinyin(String str, PinyinFormat format)
```

示例
--------------------


```java
	String s = "头发发财";
	System.out.println(s + " => " + ChineseUtils.toTraditional(s));
	s = "簡訊資料庫";
	System.out.println(s + " => " + ChineseUtils.toSimplified(s));
	s = "长江成长";
	System.out.println(s + " => " + ChineseUtils.toPinyin(s) + " ("
			+ ChineseUtils.toPinyin(s, PinyinFormat.UNICODE_PINYIN_FORMAT)
			+ ") - "
			+ ChineseUtils.toPinyin(s, PinyinFormat.ABBR_PINYIN_FORMAT));
```
将会输出
```
头发发财 => 頭髮發財
簡訊資料庫 => 短信数据库
长江成长 => chang2 jiang1 cheng2 zhang3 (cháng jiāng chéng zhăng) - cjcz
```

多音字以及简繁体消除歧义
--------------------
目前已经整理了常用多音字词组和简繁体一些语言差异和一对多现象。

请查看源代码中
+ 多音字消除歧义表 - polyphone.txt
+ 繁体转简体消除歧义表 - simplified.txt
+ 简体转繁体消除歧义表 - traditional.txt

欢迎大家继续完善简繁体转换和拼音转换以消除歧义。
未来可能先做中文分词在进行转换。

参考
--------------------

http://www.cjk.org/cjk/c2c/c2cbasis.htm

http://zh.wikipedia.org/zh-cn/Wikipedia:繁简分歧词表

http://chineseutils.sourceforge.net

http://pinyin4j.sourceforge.net

https://code.google.com/p/mmseg4j/



