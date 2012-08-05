package org.opaeum.javageneration.util;

import java.util.Collection;
import java.util.StringTokenizer;

import org.opaeum.java.metamodel.OJPathName;

class ImmutablePathName extends OJPathName{
	ImmutablePathName(String name){
		StringTokenizer st = new StringTokenizer(name, ".");
		while(st.hasMoreTokens()){
			this.getNames().add(st.nextToken());
		}
	}
	public ImmutablePathName(OJPathName classifierPathname,String string){
		this(classifierPathname.toJavaString());
		super.getNames().add(string);
	}
	@Override
	public void addToElementTypes(OJPathName element){
		throw new UnsupportedOperationException();
	}
	@Override
	public void addToElementTypes(Collection<OJPathName> newElems){
		throw new UnsupportedOperationException();
	}
	@Override
	public void addToNames(Collection<String> newElems){
		throw new UnsupportedOperationException();
	}
	@Override
	public void addToNames(String element){
		throw new UnsupportedOperationException();
	}
	@Override
	public void insertBeforeTail(String name){
		throw new UnsupportedOperationException();
	}
	@Override
	public OJPathName append(String str){
		throw new UnsupportedOperationException();
	}
	@Override
	public void replaceTail(String newtail){
		throw new UnsupportedOperationException();
	}
}