/** 
 * File    : TrieNode.java 
 * Created : 2014年1月20日 
 * By      : luhuiguo 
 */
package com.luhuiguo.chinese;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author luhuiguo
 */
public class TrieNode<T> {

	private int level;

	private char key;

	private Map<Character, TrieNode<T>> children;

	private boolean leaf;

	private T value;

	public TrieNode(char key) {
		super();
		this.level = 0;
		this.key = key;
		this.children = new HashMap<Character, TrieNode<T>>();
	}

	public char getKey() {
		return key;
	}

	public void setKey(char key) {
		this.key = key;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public TrieNode<T> addChild(char k) {
		TrieNode<T> node = new TrieNode<T>(k);
		node.level = this.level + 1;
		children.put(k, node);
		return node;
	}

	public TrieNode<T> child(char k) {
		return children.get(k);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(key);
		if (value != null) {
			sb.append(":").append(value);
		}
		return sb.toString();
	}

}
