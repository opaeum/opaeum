/*
 * Created on Jul 16, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IParameter;

import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.utilities.JavaPathNames;

/**
 * OperationMap : 
 */
public class OperationMap extends PackageableElementMap {
	protected IOperation 		operation = null;
	protected ClassifierMap  	operationTypeMap = null;
	
	static public String javaPlusOperName() {
		return "plus";
	}

	/**
	 * 
	 */
	public OperationMap(IOperation operation) {
		super(operation);
		this.operation = operation;
		if (operation.getReturnType() != null) {
			this.operationTypeMap = new ClassifierMap(operation.getReturnType());
		}
	}
	/**
	 * @param in
	 * @return
	 */
	public String javaOperName() {
//		Parser only recognises:
//		( <PRIVATE> | <PUBLIC> | <DERIVATION> | <MODEL_LESS> | <MODEL_GT> | <MODEL_LESSEQ>
//		  | <MODEL_GTEQ> | <MODEL_EQUALS> | <MODEL_NOTEQUALS> | <MODEL_MULTIPLY>
		String in = operation.getName();
		String result = in;
		if (in.length() == 1){
			switch (in.charAt(0)) {
				case '+': result = "plus"; break;
				case '-': result = "minus"; break;
				case '/': result = "divide"; break;
				case '<': result = "less"; break;
				case '>': result = "more"; break;
				case '*': result = "times"; break;
				case '=': result = "singleEquals"; break;
				default: result = "unknownOperator"; break;	
			}
		} else if (in.length() == 2){
			if (in.equals("<=")) result = "lessEquals";
			if (in.equals(">=")) result = "moreEquals";
			if (in.equals("<>")) result = "doubleEquals";
		}
		return result;
	}
	/**
	 * @return
	 */
	public String javaReturnDefaultValue() {
		if (operation.getReturnType() != null) {
			return operationTypeMap.javaDefaultValue();
		} else {
			return "null";
		}
	}

	/**
	 * @return
	 */
	public OJPathName javaReturnTypePath() {
		if (operation.getReturnType() != null) {
			return operationTypeMap.javaTypePath();
		} else {
			return JavaPathNames.Void;
		}
	}
	/**
	 * @param elem
	 * @return
	 */
	public OJPathName javaParamTypePath(IParameter elem) {
		ClassifierMap mapper = new ClassifierMap(elem.getType());
		return mapper.javaTypePath();
	}
	/**
	 * @return
	 */
	public OJPathName javaReturnDefaultTypePath() {
		if (operation.getReturnType() != null) {
			return operationTypeMap.javaDefaultTypePath();
		} else {
			return JavaPathNames.Void;
		}
	}
	/**
	 * @return
	 */
	public List<OJPathName> javaParamTypePaths() {
		List<OJPathName> result = new ArrayList<OJPathName>();
		Iterator<?> it = operation.getParameters().iterator();
		while( it.hasNext()) {
			IParameter param = (IParameter) it.next();
			result.add(javaParamTypePath(param));
		}
		return result;
	}

}
