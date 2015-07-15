chinese-utils
=============
项目将并入NLPchina
https://github.com/NLPchina/

请前往
https://github.com/NLPchina/nlp-lang
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
	String ChineseUtils.toSimplified(String str);

	void Converter.SIMPLIFIED.convert(Reader reader, Writer writer) throws IOException; 
    String Converter.SIMPLIFIED.convert(String str);
```

### 转繁体
```java
	String ChineseUtils.toTraditional(String str);

	void Converter.TRADITIONAL.convert(Reader reader, Writer writer) throws IOException; 
    String Converter.TRADITIONAL.convert(String str);
```

### 转拼音
```java
 	String ChineseUtils.toPinyin(String str, PinyinFormat format);

    String[] Pinyin.INSTANCE.toUnformattedPinyin(char ch);
    String[] Pinyin.INSTANCE.toFormattedPinyin(char ch, PinyinFormat format);
    String Pinyin.INSTANCE.toPinyin(char ch);
    String Pinyin.INSTANCE.toPinyin(char ch, PinyinFormat format);
    void Pinyin.INSTANCE.convert(Reader reader, Writer writer, PinyinFormat format) throws IOException;
    String Pinyin.INSTANCE.convert(String str, PinyinFormat format);
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

多音字以及简繁体分歧
--------------------
目前已经整理了常用多音字词组和简繁体一些语言差异和一对多现象。

比如多音字默认第一个读音，其他读音需要在字典文件中列出
```
#龟=gui1,jun1,qiu1
#龟=gui1

#龟=jun1
龟裂=jun1 lie4

#龟=qiu1
龟兹=qiu1 ci2
```
简繁转换中一对多或者两地语言习惯照成的不同也需列在字典文件中
```
# 计算机
打印机=印表機
内存=記憶體
以太网=乙太網
光标=游標
光盘=光碟
光驱=光碟機
软驱=軟碟機
总线=匯流排
盘片=碟片
硬件=硬體
硅谷=矽谷
硬盘=硬碟
```

欢迎大家继续完善简繁体转换和拼音转换以消除分歧。
未来可能先做中文分词在进行转换。

参考
--------------------

http://www.cjk.org/cjk/c2c/c2cbasis.htm

http://zh.wikipedia.org/zh-cn/Wikipedia:繁简分歧词表

http://www.edu.tw/pages/detail.aspx?Node=3692&Page=16373&WID=c5ad5187-55ef-4811-8219-e946fe04f725


