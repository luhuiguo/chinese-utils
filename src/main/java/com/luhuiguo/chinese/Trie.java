/** 
 * File    : Trie.java 
 * Created : 2014年1月20日 
 * By      : luhuiguo 
 */
package com.luhuiguo.chinese;

/**
 * 
 * @author luhuiguo
 */
public class Trie<T> {

	private TrieNode<T> root = new TrieNode<T>(' ');

	public Trie() {
		super();
	}

	public void add(char[] w, T value) {

		if (w.length < 1) {
			return;
		}
		TrieNode<T> p = root;
		for (int i = 0; i < w.length; i++) {
			TrieNode<T> n = p.child(w[i]);
			if (n == null) {
				n = p.addChild(w[i]);
			}
			p = n;
		}
		p.setLeaf(true);
		p.setValue(value);

	}

	public void add(String w, T value) {
		if (null == w) {
			return;
		}
		add(w.toCharArray(), value);

	}

	public TrieNode<T> match(char[] sen, int offset, int len) {
		TrieNode<T> node = root;
		for (int i = 0; i < len; i++) {
			node = node.child(sen[offset + i]);
			if (node == null) {
				return null;
			}
		}
		return node;
	}

	public TrieNode<T> bestMatch(char[] sen, int offset, int len) {
		TrieNode<T> ret = null;

		TrieNode<T> node = root;
		for (int i = offset; i < len; i++) {
			node = node.child(sen[i]);
			if (node != null) {
				if (node.isLeaf()) {
					ret = node;
				}
			} else {
				break;
			}
		}
		return ret;
	}

	public TrieNode<T> bestMatch(char[] sen, int offset) {

		return bestMatch(sen, offset, sen.length);
	}

}
