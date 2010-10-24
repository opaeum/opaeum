package net.sf.nakeduml.javametamodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.nakeduml.javametamodel.generated.OJPathNameGEN;
import net.sf.nakeduml.javametamodel.utilities.JavaUtil;



public class OJPathName extends OJPathNameGEN {
//	static public OJPathName VOID   = new OJPathName("java.lang.void");
	
	/******************************************************
	 * The constructor for this classifier.
	*******************************************************/	
	public OJPathName() {
		super();
	}
	public OJPathName(String name) {
		super();
		StringTokenizer st = new StringTokenizer(name, ".");
		while (st.hasMoreTokens()) {
			this.addToNames(st.nextToken());
		}
	}
	/******************************************************
	 * The following operations are the implementations
	 * of the operations defined for this classifier.
	*******************************************************/
	public String getFirst(){
		String result = "";
		if (getNames().size() > 0) result = (String) getNames().get(0);
		return result;
	}
	public String getLast(){
		String result = "";
		if (getNames().size() > 0) result = (String) getNames().get( getNames().size() - 1 );
		return result;
	}
	public OJPathName getHead(){
		OJPathName result = new OJPathName();
		if (getNames().size() > 0) result.setNames( getNames().subList(0, getNames().size() - 1) );
		return result;
	}
	public OJPathName getTail(){
		OJPathName result = new OJPathName();
		if (getNames().size() > 0) result.setNames( getNames().subList(1, getNames().size()) );
		return result;
	}
	
	public String getTypeName() {
		return getLast();
	}
	
	public String getCollectionTypeName() {
		if (getElementTypes().isEmpty()) {
			return getLast();
		} else {
			return getLast() + elementTypesToJavaString();
		}
	}
	/******************************************************
	 * End of implemented operations.
	*******************************************************/
		
//	public boolean equals(OJPathName other){
//		// TODO improve this: check order
//		if (this.getNames().containsAll(other.getNames()) &&
//		    other.getNames().containsAll(this.getNames())) {
//			return true;
//		}
//		return false;
//	}

	public int hashCode() {
		return this.getLast().hashCode();
	}

	public String toJavaString(){
		StringBuilder pathInfo = new StringBuilder();
		boolean first = true;
		Iterator it = getNames().iterator();
		while (it.hasNext()) {
			if (first){
				first = false;
			} else {
				pathInfo.append(".");
			}
			String elem = (String) it.next();
			pathInfo.append(elem);
		}
		return pathInfo.toString();
	}
	
	private String elementTypesToJavaString() {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		Iterator it = getElementTypes().iterator();
		while (it.hasNext()) {
			OJPathName elemType = (OJPathName) it.next();
			if (!elemType.getLast().equals("void")) {
				if (first){
					first = false;
					result.append("<");
				} else {
					result.append(", ");
				}
				result.append(elemType.getCollectionTypeName());
			}
		}
		if (result.length() != 0) result.append(">");
		return result.toString();		
	}
	
	//
	public String toString(){
		return JavaUtil.collectionToString(getNames(), ".");
	}
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public OJPathName getCopy() {
		OJPathName result = new OJPathName();
		result.setNames(new ArrayList(this.getNames()));
		return result;
	}
	
	public OJPathName getDeepCopy() {
		OJPathName result = new OJPathName();
		result.setNames(new ArrayList(this.getNames()));
		List<OJPathName> elementTypes = getElementTypes();
		for (OJPathName elementType : elementTypes) {
			OJPathName elementTypeCopy = elementType.getDeepCopy();
			result.addToElementTypes(elementTypeCopy);
		}
		return result;
	}

	
	/**
	 * @return
	 */
	public boolean isSingleName() {
		return getNames().size() == 1;
	}
	
	@SuppressWarnings("unchecked")
	public void replaceTail(String newtail){
		getNames().set( getNames().size() -1, newtail );
	}
	
	@SuppressWarnings("unchecked")
	public void insertBeforeTail(String name){
		getNames().add(getNames().size() - 1, name);
	}
	
	public OJPathName append(String str) {
		this.addToNames(str);
		return this;
	}

	//TODO This is done dum, redo
	public void renameAll(Map<String, OJPathName> renamePathNames, String newName) {
		if (renamePathNames.containsKey(toJavaString())) {
			String oldName = getNames().remove(getNames().size()-1);
			getNames().add(oldName + newName);
		}
		for (OJPathName elementType : getElementTypes()) {
			elementType.renameAll(renamePathNames, newName);
		}
	}	

}