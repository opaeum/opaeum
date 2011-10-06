package org.opaeum.textmetamodel;

import java.util.SortedSet;

public class SortedSetSource implements TextSource{
	SortedSet<String> set;
	public SortedSetSource(SortedSet<String> set){
		super();
		this.set = set;
	}
	@Override
	public char[] toCharArray(){
		StringBuilder sb = new StringBuilder();
		for(String s:set){
			sb.append(s);
			sb.append('\n');
		}
		return sb.toString().toCharArray();
	}
	@Override
	public boolean hasContent(){
		return false;
	}
}
