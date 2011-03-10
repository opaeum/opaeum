/*
 * Created on Jun 15, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.tools.common.StringHelpers;

import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;

/**
 * ExpGeneratorHelper : 
 */
public class ExpGeneratorHelper {

	/**
	 * 
	 */
	public ExpGeneratorHelper() {
		super();
	}

	/** The let variable needs to be added to any generated operation implementing
	 * the body expression. This method adds the let variable and replaces any variable
	 * that will be eclipsed by it in the scope of the body expression.
	 * @param params
	 * @return
	 */
	static public List<OJParameter> addVarToParams(IVariableDeclaration elem, List<OJParameter> params) {
		List<OJParameter> result = new ArrayList<OJParameter>(params);
		Iterator it = params.iterator();
		while (it.hasNext()){
			OJParameter par = (OJParameter) it.next();
			if ( elem.getName().equals(par.getName())) {
				result.remove(par);
			}
		}
		result.add(varDeclToOJPar(elem));
		return result;
	}
	
	/**
	 * @param elem
	 * @return
	 */
	static public OJParameter varDeclToOJPar(IVariableDeclaration elem) {
		OJParameter result = new OJParameter();
		result.setName(elem.getName());
		result.setType(new ClassifierMap(elem.getType()).javaTypePath());
		return result;
	}	

	static public OJPathName makeListType(IClassifier elementType) {
		OJPathName myType;
		ClassifierMap elementMap = new ClassifierMap(elementType);
		if (elementMap.isJavaPrimitive() ) {
			myType = elementMap.javaObjectTypePath();
		} else {
			myType = elementMap.javaFacadeTypePath();
		}
		return myType;
	}

	static public String makeListElem(OJClass myClass, IClassifier type, String argStr) {
		ClassifierMap typeMap = new ClassifierMap(type);
		if (typeMap.isJavaPrimitive() ) {
			String myType = typeMap.javaObjectType();
			myClass.addToImports(typeMap.javaObjectTypePath());
			argStr = "new " + myType + "(" + argStr + ")";
		} else {
			myClass.addToImports(typeMap.javaFacadeTypePath());
			argStr = StringHelpers.addBrackets(argStr);
		}
		return argStr;
	}
	
	static public String buildMessage(IOclContext cont) {
		String MESS = "";
		if (cont.getName().equals("")) {
			MESS = cont.getExpressionString();
			if (MESS.length() > 60) MESS = MESS.substring(0,60) + "...";
			MESS = StringHelpers.replaceAllSubstrings(MESS, "\n", " ");
		} else {
			MESS = cont.getName();
		}
		return MESS;
	}



}
