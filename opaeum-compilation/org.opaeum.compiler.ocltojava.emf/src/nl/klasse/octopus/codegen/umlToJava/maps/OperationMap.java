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
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.utilities.JavaPathNames;


public class OperationMap extends PackageableElementMap{
	protected NamedElement operation = null;
	protected ClassifierMap operationTypeMap = null;
	
	private ArrayList<OJPathName> paramTypePaths;
	protected List<Parameter> parameters;
	protected Parameter returnParameter;
	static public String javaPlusOperName(){
		return "plus";
	}
	public OperationMap(NamedElement operation,List<Parameter> parameters){
		super(operation);
		this.parameters=parameters;
		this.operation = operation;
		for(Parameter parameter:parameters){
			if(parameter.getDirection()==ParameterDirectionKind.RETURN_LITERAL){
				this.returnParameter=parameter;
				this.operationTypeMap = new ClassifierMap((Classifier) parameter.getType(),getCollectionKind(parameter));
			}
		}
	}
	public OperationMap(Operation operation){
		this(operation,operation.getOwnedParameters());
	}

	public String javaOperName(){
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

	public String javaReturnDefaultValue(){
		if(operationTypeMap != null){
			return operationTypeMap.javaDefaultValue();
		}else{
			return "null";
		}
	}

	public OJPathName javaReturnTypePath(){
		if(operationTypeMap != null){
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
		ClassifierMap mapper = new ClassifierMap((Classifier) elem.getType(), getCollectionKind(elem));
		return mapper.javaTypePath();
	}
	/**
	 * @return
	 */
	public OJPathName javaReturnDefaultTypePath(){
		if(operationTypeMap != null){
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
			Iterator<?> it = parameters.iterator();
			while(it.hasNext()){
				Parameter param = (Parameter) it.next();
				paramTypePaths.add(javaParamTypePath(param));
			}
		}
		return paramTypePaths;
	}
}
