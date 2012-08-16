package org.opaeum.java.metamodel.generated;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opaeum.java.metamodel.OJElement;
import org.opaeum.java.metamodel.OJPathName;

abstract public class OJPathNameGEN extends OJElement{
	private List<String> names = new ArrayList<String>();
	private List<OJPathName> elementTypes = new ArrayList<OJPathName>();
	protected OJPathNameGEN(){
		super("");
	}
	public OJPathName append(String name){
		OJPathName result = null;
		return result;
	}
	public OJPathName prepend(String name){
		OJPathName result = null;
		return result;
	}
	public String getFirst(){
		return(this.getNames().size() > 0 ? ((String) this.getNames().get(0)) : "");
	}
	public String getLast(){
		return(this.getNames().size() > 0 ? ((String) this.getNames().get(this.getNames().size() - 1)) : "");
	}
	public OJPathName getTail(){
		OJPathName result = null;
		return result;
	}
	public OJPathName getHead(){
		OJPathName result = null;
		return result;
	}
	public boolean isSingleName(){
		return(this.getNames().size() == 1);
	}
	public boolean equals(OJPathName other){
		return(sequenceEquals(this.getNames(), other.getNames()));
	}
	private boolean sequenceEquals(List<?> source,List<?> arg){
		if(source.size() != arg.size()){
			return false;
		}
		Iterator<?> it1 = source.iterator();
		Iterator<?> it2 = arg.iterator();
		while(it1.hasNext()){
			Object elem1 = it1.next();
			Object elem2 = it2.next();
			if(elem1 instanceof Integer){
				if(((Integer) elem1).intValue() != ((Integer) elem2).intValue()){
					return false;
				}
			}else{
				if(elem1 instanceof Float){
					if(((Float) elem1).floatValue() != ((Float) elem2).floatValue()){
						return false;
					}
				}else{
					if(elem1 instanceof Boolean){
						if(((Boolean) elem1).booleanValue() != ((Boolean) elem2).booleanValue()){
							return false;
						}
					}else{
						if(!elem1.equals(elem2)){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	public List<String> getNames(){
		return names;
	}
	public void setNames(List<String> element){
		if(names != element){
			names = element;
		}
	}
	public void addToNames(String element){
		names.add(element);
	}
	public void removeFromNames(String element){
		names.remove(element);
	}
	public List<OJPathName> getElementTypes(){
		return elementTypes;
	}
	public void setElementTypes(List<OJPathName> element){
		if(elementTypes != element){
			elementTypes = element;
		}
	}
	public void addToElementTypes(OJPathName element){
		elementTypes.add(element);
	}
	public void removeFromElementTypes(OJPathName element){
		elementTypes.remove(element);
	}
	public String toString(){
		String result = "";
		result = super.toString();
		if(this.getNames() != null){
			result = result + " names:" + this.getNames();
		}
		return result;
	}
	public OJElement getCopy(){
		OJPathName result = new OJPathName();
		this.copyInfoInto(result);
		return result;
	}
	public void copyInfoInto(OJPathName copy){
		super.copyInfoInto(copy);
		Iterator<String> namesIt = new ArrayList<String>(getNames()).iterator();
		while(namesIt.hasNext()){
			String elem = (String) namesIt.next();
			copy.addToNames(elem);
		}
		Iterator<OJPathName> elementTypesIt = new ArrayList<OJPathName>(getElementTypes()).iterator();
		while(elementTypesIt.hasNext()){
			OJPathName elem = (OJPathName) elementTypesIt.next();
			copy.addToElementTypes(elem);
		}
	}
	public void removeAllFromElementTypes(){
		this.
		elementTypes.clear();
		
	}

}