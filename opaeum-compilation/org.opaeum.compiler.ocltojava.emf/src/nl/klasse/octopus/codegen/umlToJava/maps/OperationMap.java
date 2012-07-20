/*
 * Created on Jul 16, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.utilities.JavaPathNames;

/**
 * OperationMap :
 */
public class OperationMap extends PackageableElementMap{
	protected Operation operation = null;
	protected ClassifierMap operationTypeMap = null;
	private ArrayList<OJPathName> paramTypePaths;
	static public String javaPlusOperName(){
		return "plus";
	}
	/**
	 * 
	 */
	public OperationMap(Operation operation){
		super(operation);
		this.operation = operation;
		if(operation.getType() != null){
			this.operationTypeMap = new ClassifierMap((Classifier) operation.getType());
		}
	}
	/**
	 * @param in
	 * @return
	 */
	public String javaOperName(){
		// Parser only recognises:
		// ( <PRIVATE> | <PUBLIC> | <DERIVATION> | <MODEL_LESS> | <MODEL_GT> | <MODEL_LESSEQ>
		// | <MODEL_GTEQ> | <MODEL_EQUALS> | <MODEL_NOTEQUALS> | <MODEL_MULTIPLY>
		String in = operation.getName();
		String result = in;
		if(in.length() == 1){
			switch(in.charAt(0)){
			case '+':
				result = "plus";
				break;
			case '-':
				result = "minus";
				break;
			case '/':
				result = "divide";
				break;
			case '<':
				result = "less";
				break;
			case '>':
				result = "more";
				break;
			case '*':
				result = "times";
				break;
			case '=':
				result = "singleEquals";
				break;
			default:
				result = "unknownOperator";
				break;
			}
		}else if(in.length() == 2){
			if(in.equals("<="))
				result = "lessEquals";
			if(in.equals(">="))
				result = "moreEquals";
			if(in.equals("<>"))
				result = "doubleEquals";
		}
		return result;
	}
	/**
	 * @return
	 */
	public String javaReturnDefaultValue(){
		if(operation.getType() != null){
			return operationTypeMap.javaDefaultValue();
		}else{
			return "null";
		}
	}
	/**
	 * @return
	 */
	public OJPathName javaReturnTypePath(){
		if(operation.getType() != null){
			return operationTypeMap.javaTypePath();
		}else{
			return JavaPathNames.Void;
		}
	}
	/**
	 * @param elem
	 * @return
	 */
	public OJPathName javaParamTypePath(Parameter elem){
		ClassifierMap mapper = new ClassifierMap((Classifier) elem.getType());
		return mapper.javaTypePath();
	}
	/**
	 * @return
	 */
	public OJPathName javaReturnDefaultTypePath(){
		if(operation.getType() != null){
			return operationTypeMap.javaDefaultTypePath();
		}else{
			return JavaPathNames.Void;
		}
	}
	/**
	 * @return
	 */
	public List<OJPathName> javaParamTypePaths(){
		if(paramTypePaths == null){
			paramTypePaths = new ArrayList<OJPathName>();
			Iterator<?> it = operation.getOwnedParameters().iterator();
			while(it.hasNext()){
				Parameter param = (Parameter) it.next();
				paramTypePaths.add(javaParamTypePath(param));
			}
		}
		return paramTypePaths;
	}
}
