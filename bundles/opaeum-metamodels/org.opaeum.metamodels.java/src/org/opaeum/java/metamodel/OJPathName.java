package org.opaeum.java.metamodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.opaeum.java.metamodel.generated.OJPathNameGEN;
import org.opaeum.java.metamodel.utilities.JavaUtil;

public class OJPathName extends OJPathNameGEN implements Comparable<OJPathName>{

	public OJPathName(){
		super();
	}
	public OJPathName(String name){
		super();
		StringTokenizer st = new StringTokenizer(name, ".");
		while(st.hasMoreTokens()){
			this.addToNames(st.nextToken());
		}
	}
	public String getFirst(){
		String result = "";
		if(getNames().size() > 0)
			result = (String) getNames().get(0);
		return result;
	}
	public String getLast(){
		String result = "";
		if(getNames().size() > 0) {
			result = (String) getNames().get(getNames().size() - 1);
		}
		return result;
	}
	public OJPathName getHead(){
		OJPathName result = new OJPathName();
		if(getNames().size() > 0)
			result.setNames(getNames().subList(0, getNames().size() - 1));
		return result;
	}
	public OJPathName getTail(){
		OJPathName result = new OJPathName();
		if(getNames().size() > 0)
			result.setNames(getNames().subList(1, getNames().size()));
		return result;
	}
	public String getTypeName(){
		return getLast();
	}
	public String getTypeNameWithTypeArguments(){
		String result=null;
		if(getElementTypes().isEmpty()){
			result= getLast();
		}else{
			result= getLast() + elementTypesToJavaString();
		}
			return result;
	}
	/******************************************************
	 * End of implemented operations.
	 *******************************************************/
	public boolean equals(Object other){
		return other instanceof OJPathName && super.equals((OJPathName) other);
	}
	public int hashCode(){
		return this.getLast().hashCode();
	}
	public String toJavaString(){
		StringBuilder pathInfo = new StringBuilder();
		boolean first = true;
		Iterator<String> it = getNames().iterator();
		while(it.hasNext()){
			if(first){
				first = false;
			}else{
				pathInfo.append(".");
			}
			String elem = it.next();
			pathInfo.append(elem);
		}
		return pathInfo.toString();
	}
	private String elementTypesToJavaString(){
		StringBuilder result = new StringBuilder();
		boolean first = true;
		Iterator<OJPathName> it = getElementTypes().iterator();
		while(it.hasNext()){
			OJPathName elemType = it.next();
			if(!elemType.getLast().equals("void")){
				if(first){
					first = false;
					result.append("<");
				}else{
					result.append(", ");
				}
				if(extendsElements.contains(elemType)){
					result.append("? extends ");
				}
				result.append(elemType.getTypeNameWithTypeArguments());
			}
		}
		if(result.length() != 0)
			result.append(">");
		return result.toString();
	}
	public String toString(){
		return JavaUtil.collectionToString(getNames(), ".");
	}
	public OJPathName getCopy(){
		OJPathName result = new OJPathName();
		result.setNames(new ArrayList<String>(this.getNames()));
		return result;
	}
	public OJPathName getDeepCopy(){
		OJPathName result = new OJPathName();
		result.setNames(new ArrayList<String>(this.getNames()));
		List<OJPathName> elementTypes = getElementTypes();
		for(OJPathName elementType:elementTypes){
			OJPathName elementTypeCopy = elementType.getDeepCopy();
			result.addToElementTypes(elementTypeCopy);
		}
		return result;
	}
	public boolean isSingleName(){
		return getNames().size() == 1;
	}
	public void replaceTail(String newtail){
		getNames().set(getNames().size() - 1, newtail);
	}
	public void insertBeforeTail(String name){
		getNames().add(getNames().size() - 1, name);
	}
	public OJPathName append(String str){
		this.addToNames(str);
		return this;
	}
	// TODO This is done dum, redo
	public void renameAll(Set<OJPathName> renamePathNames,String newName){
		if(getLast().charAt(0) == '?'){
			replaceTail(replaceAll(getLast(), renamePathNames, newName));
		}else if(renamePathNames.contains(this)){
			replaceTail(getLast() + newName);
		}
		for(OJPathName elementType:getElementTypes()){
			elementType.renameAll(renamePathNames, newName);
		}
	}
	@Override
	public int compareTo(OJPathName o){
		List<String> myNames = getNames();
		List<String> otherNames = o.getNames();
		for(int i = 0;i < myNames.size();i++){
			if(otherNames.size() > i){
				int compareTo = otherNames.get(i).compareTo(myNames.get(i));
				if(compareTo != 0){
					return compareTo;
				}
			}else{
				return 1;// Longest is greater
			}
		}
		if(myNames.size()<otherNames.size()){
			return -1;//Shortest is less
		}
		return 0;
	}
	
}