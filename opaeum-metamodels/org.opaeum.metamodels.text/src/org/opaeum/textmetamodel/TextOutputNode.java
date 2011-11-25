package org.opaeum.textmetamodel;

import java.util.HashMap;
import java.util.Map;

public abstract class TextOutputNode{
	protected TextOutputNode parent;
	protected String name;
	boolean shouldDelete;
	public static Map<Class<?>,Long> counts = new HashMap<Class<?>,Long>();
	public void finalize(){
		counts.put(getClass(), getCount()-1);
	}
	protected Long getCount(){
		Long long1 = counts.get(getClass());
		if(long1==null){
			return 0l;
		}else if(long1%100==0){
			System.out.println(getClass().getName() + " count :" + long1);
		}
		return long1;
	}

	protected TextOutputNode(TextOutputNode parent,String name){
		this(name);
		this.parent = parent;
	}
	protected TextOutputNode(String name){
		super();
		counts.put(getClass(), getCount() + 1);
		this.name = name;
	}
	public TextOutputNode getParent(){
		return this.parent;
	}
	public String getName(){
		return name;
	}
	public abstract boolean hasContent();
	public void markForDeletion(){
		shouldDelete = true;
	}
	public boolean shouldDelete(){
		return shouldDelete;
	}
	public void restore(){
		shouldDelete = false;
	}
	public String toString(){
		if(parent != null){
			return getParent().toString() + "/" + getName();
		}else{
			return getName();
		}
	}
}
