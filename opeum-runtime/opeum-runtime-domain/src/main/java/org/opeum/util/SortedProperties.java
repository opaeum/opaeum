package org.opeum.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

public class SortedProperties extends Properties {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Enumeration<Object> keys() {
		final Iterator<Object> iter = new TreeSet<Object>(super.keySet()).iterator();
		return new Enumeration<Object>() {
			@Override
			public boolean hasMoreElements() {
				return iter.hasNext();
			}

			@Override
			public Object nextElement() {
				return iter.next();
			}
		};
	}

	public void store(Writer writer, String comments) throws IOException {
		super.store(new BufferedWriter(writer) {
			@Override
			public void write(String s) throws IOException {
				if (s.length() > 1 && s.startsWith("#")) {
					//ignore the date comment
				} else {
					super.write(s);
				}
			}
		}, comments);
	}

	public static void main(String[] args) throws Exception {
		SortedProperties p = new SortedProperties();
		p.put("a", "asdfafs");
		p.put("e", "asdfafs");
		p.put("b", "asdfafs");
		p.put("d", "asdfafs");
		p.put("c", "asdfafs");
		p.store(new OutputStreamWriter(System.out), "asdf");
	}
}
