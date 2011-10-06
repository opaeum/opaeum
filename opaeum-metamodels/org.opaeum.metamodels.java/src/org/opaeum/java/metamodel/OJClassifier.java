package org.opaeum.java.metamodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.opaeum.java.metamodel.generated.OJClassifierGEN;
import org.opaeum.java.metamodel.utilities.JavaStringHelpers;
import org.opaeum.java.metamodel.utilities.JavaUtil;
import org.opaeum.java.metamodel.utilities.OJOperationComparator;
import org.opaeum.java.metamodel.utilities.OJPathNameComparator;




public class OJClassifier extends OJClassifierGEN {

	protected OJPackage f_myPackage;

	/******************************************************
	 * The constructor for this classifier.
	*******************************************************/	
	public OJClassifier() {
		super();
	}

	public void calcImports() {
		// operations
		for( OJOperation oper : getOperations() ) {
			addAll(oper.getParamTypes());
			this.addToImports(oper.getReturnType());
		}
	}

	private void addAll(List<OJPathName> types) {
		for(OJPathName type : types) {
			if (type != null) {
				this.addToImports(type);
				if (!type.getElementTypes().isEmpty()) {
					addAll(type.getElementTypes());
				}
			}
		}
	}
	
	public void addToImports(OJPathName path) {
		if (path == null) return;
		if (path.isSingleName()) {
			// do nothing, imported element is in same package
		} else if (path.getLast().equals("void")){
			// do nothing, no need to import "void"
		} else if (path.getHead().equals(new OJPathName("java.lang"))){
			// do nothing, no need to import "java.lang.*"
		} else if (path.getLast().equals("int")){
			// do nothing, no need to import "int"
		} else if (path.getLast().equals("String")){
			// do nothing, no need to import "String"
		} else if (path.getLast().equals("boolean")){
			// do nothing, no need to import "boolean"
		} else if (path.getLast().equals("float")){
			// do nothing, no need to import "float"
		} else if (path.getLast().equals("Object")){
			// do nothing, no need to import "Object"
		} else if (path.getLast().charAt(path.getLast().length()-1) == ']'){
			// some array type, remove '[]'
			String lastEntry = path.getLast();
			lastEntry = lastEntry.substring(0, lastEntry.length()-2);
			OJPathName path2 = path.getCopy().getHead();
			path2.addToNames(lastEntry);
			this.addToImports(path2);
		} else {
			// check whether path is already present is performed by super
			super.addToImports(path);
			if (!path.getElementTypes().isEmpty()) {
				addAll(path.getElementTypes());
			}

		}
	}
	
	public void addToImports(String pathName) {
		if (pathName == null) return;
		OJPathName path = new OJPathName(pathName);
		addToImports(path);
	}
		
	public int getUniqueNumber() {
		int i = super.getUniqueNumber() + 1;
		super.setUniqueNumber(i);
		return i;
	}
	
	/**
	 * 
	 */
	public OJOperation findToString() {
		OJOperation result = null;
		Iterator it = getOperations().iterator();
		while (it.hasNext()) {
			OJOperation oper = (OJOperation) it.next();
			if (oper.getName().equals("toString")) result = oper;
		}
		return result;
	}
	/**
	 * 
	 */
	public OJOperation findIdentOper() {
		OJOperation result = null;
		Iterator it = getOperations().iterator();
		while (it.hasNext()) {
			OJOperation oper = (OJOperation) it.next();
			if (oper.getName().equals("getIdentifyingString")) result = oper;
		}
		return result;
	}
	
	/******************************************************
	 * End of getters and setters.
	*******************************************************/
	/**
	 * @param result
	 */
	protected void addJavaDocComment(StringBuilder result) {
		String comment = JavaStringHelpers.firstCharToUpper(getComment());
		result.append("/** " + comment);
		result.append("\n */\n");
	}

	/**
	 * @return
	 */
	protected StringBuilder operations() {
		// sort the operations on visibilityKind, then name
		List<OJOperation> temp = new ArrayList<OJOperation>(this.getOperations());
		Collections.sort(temp, new OJOperationComparator());
		//
		StringBuilder result = new StringBuilder();
		result.append(JavaUtil.collectionToJavaString(temp, "\n"));
//		if ( result.length() > 0) result.append("\n");
		return result;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected StringBuilder imports() {
		// sort the imports by alphabeth
		Set myImports = new TreeSet(new OJPathNameComparator());
		myImports.addAll(this.getImports());
		//
		StringBuilder result = new StringBuilder();
		Iterator it = myImports.iterator();
		String prevPackageName = "";
		while (it.hasNext()) {
			OJPathName path = (OJPathName) it.next();
			if (this.getMyPackage().getPathName().equals(path.getHead())) {
				// do nothing, imported element is in same package
			} else {
				if (!path.getFirst().equals(prevPackageName)) {
					result.append("\n");
				}
				result.append("import " + path.toString() + ";\n");
				prevPackageName = path.getFirst();
			}
		}
		return result;
	}
	
	public OJOperation findOperation(String name, List /*(OJPathName)*/ types) {
		OJOperation result = null;
		Iterator it = getOperations().iterator();
		while( it.hasNext()) {
			OJOperation elem = (OJOperation) it.next();
			if (elem.isEqual(name, types)) return elem;
		}
		return result;
	}

	public void copyDeepInfoInto(OJClassifier copy) {
		super.copyDeepInfoInto(copy);
		copy.setUniqueNumber(getUniqueNumber());
		copy.setDerived(isDerived());
		copy.setAbstract(isAbstract());
		Iterator operationsIt = new ArrayList<OJOperation>(getOperations()).iterator();
		while ( operationsIt.hasNext() ) {
			OJOperation elem = (OJOperation) operationsIt.next();
			copy.addToOperations(elem.getDeepCopy());
		}
		Iterator importsIt = new ArrayList<OJPathName>(getImports()).iterator();
		while ( importsIt.hasNext() ) {
			OJPathName elem = (OJPathName) importsIt.next();
			copy.addToImports(elem.getCopy());
		}
	}	
}