package org.opaeum.uim.uml2uim;

import java.util.WeakHashMap;

public class Main{
	private static WeakHashMap<String,Object> map=new WeakHashMap<String,Object>();
	{
		new Object(){
			@Override
			protected void finalize() throws Throwable{
				System.out.println("inner");
			}
		};

	}
	@Override
	protected void finalize() throws Throwable{
		System.out.println("outer");
	}
	@Override
	public String toString(){
		return "toString";
	}
	public static void main(String[] args){
		asdf();
		System.gc();
		System.runFinalization();
	}
	public static void asdf(){
		Main m= new Main();
		System.gc();
		System.out.println(m);
	}
}
