/*
 * Created on Jul 16, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.utilities.JavaPathNames;
import org.opaeum.javageneration.maps.ExceptionRaisingMap;
import org.opaeum.javageneration.maps.IMessageMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

public class OperationMap extends PackageableElementMap implements IMessageMap,ExceptionRaisingMap{
	private NamedElement operation = null;
	private ClassifierMap operationTypeMap = null;
	private Map<Parameter,ClassifierMap> params = null;
	private OJPathName callbackListenerPath;
	private OJPathName handlerPath;
	private ArrayList<OJPathName> paramTypePaths;
	private List<Parameter> parameters;
	private Parameter returnParameter;
	static public String javaPlusOperName(){
		return "plus";
	}
	public OperationMap(OJUtil ojUtil,NamedElement operation,List<Parameter> parameters){
		super(ojUtil, operation);
		this.parameters = parameters;
		this.operation = operation;
		for(Parameter parameter:parameters){
			if(parameter.getDirection() == ParameterDirectionKind.RETURN_LITERAL){
				this.returnParameter = parameter;
				this.operationTypeMap = ojUtil.buildClassifierMap((Classifier) parameter.getType(), getCollectionKind(parameter));
			}
		}
	}
	public OperationMap(OJUtil ojUtil,Operation operation){
		this(ojUtil, operation, operation.getOwnedParameters());
	}
	public List<OJPathName> javaParamTypePathsWithReturnInfo(){
		List<OJPathName> javaParamTypePaths = new ArrayList<OJPathName>();
		if(isLongRunning()){
			javaParamTypePaths.add(new OJPathName("org.opaeum.runtime.activities.Token"));
		}
		javaParamTypePaths.addAll(javaParamTypePaths());
		return javaParamTypePaths;
	}
	public String javaOperName(){
		if(operation instanceof Behavior){
			Behavior behaviour = (Behavior) operation;
			if(behaviour.getSpecification() != null){
				return behaviour.getSpecification().getName();
			}else{
				return behaviour.getName();
			}
		}else{
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
	public OJPathName javaParamTypePath(Parameter elem){
		return getParamMap().get(elem).javaTypePath();
	}
	public OJPathName javaReturnDefaultTypePath(){
		if(operationTypeMap != null){
			return operationTypeMap.javaDefaultTypePath();
		}else{
			return JavaPathNames.Void;
		}
	}
	public List<OJPathName> javaParamTypePaths(){
		if(paramTypePaths == null){
			paramTypePaths = new ArrayList<OJPathName>();
			
			Iterator<?> it = getArgumentParameters().iterator();
			while(it.hasNext()){
				Parameter param = (Parameter) it.next();
				paramTypePaths.add(javaParamTypePath(param));
			}
		}
		return paramTypePaths;
	}
	public OJPathName callbackListenerPath(){
		if(callbackListenerPath == null){
			this.callbackListenerPath = ojUtil.packagePathname(getContractDefiningElement().getNamespace()).getCopy();
			callbackListenerPath.addToNames(callbackListener());
		}
		return callbackListenerPath;
	}
	@Override
	public String eventConsumerMethodName(){
		return "consume" + NameConverter.capitalize(operation.getName()) + "Occurrence";
	}
	private Map<Parameter,ClassifierMap> getParamMap(){
		if(params == null){
			params = new HashMap<Parameter,ClassifierMap>();
			for(Parameter p:parameters){
				Classifier type = (Classifier) p.getType();
				if(type==null){
					type=ojUtil.getLibrary().getDefaultType();
				}
				params.put(p, ojUtil.buildClassifierMap(type, p));
			}
		}
		return params;
	}
	public boolean hasContract(){
		return operation != getContractDefiningElement();
	}
	private NamedElement getContractDefiningElement(){
		if(operation instanceof Operation){
			return operation;
		}else{
			Behavior root = (Behavior) operation;
			while(root.getSuperClasses().size() == 1 && root.getSuperClasses().get(0) instanceof Behavior){
				root = (Behavior) root.getSuperClasses().get(0);
				if(root.getSpecification() != null){
					return root.getSpecification();
				}
			}
			return root;
		}
	}
	public OJPathName handlerPath(){
		if(handlerPath == null){
			this.handlerPath = ojUtil.packagePathname(getContractDefiningElement().getNamespace()).getCopy();
			handlerPath.addToNames(NameConverter.capitalize(operation.getName()) + "Handler" + EmfWorkspace.getOpaeumId(operation));
		}
		return handlerPath;
	}
	public String callbackListener(){
		return NameConverter.capitalize(getContractDefiningElement().getName()) + "Listener";
	}
	public String callbackOperName(){
		return baseMethodName() + "Complete";
	}
	protected String baseMethodName(){
		return "on" + NameConverter.capitalize(getContractDefiningElement().getName());
	}
	@Override
	public String eventGeratorMethodName(){
		String javaName = operation.getName();
		return "generate" + NameConverter.capitalize(javaName) + "Event";
	}
	@Override
	public OJPathName eventHandlerPath(){
		return handlerPath();
	}
	public boolean hasMessageStructure(){
		if(operation instanceof Operation){
			return EmfBehaviorUtil.hasExecutionInstance((Operation) operation);
		}else{
			return EmfBehaviorUtil.hasExecutionInstance((Behavior) operation);
		}
	}
	public OJPathName messageStructurePath(){
		if(operation instanceof Operation){
			return ojUtil.classifierPathname(((Operation) operation));
		}else{
			return ojUtil.classifierPathname((Classifier) operation);
		}
	}
	@Override
	public String exceptionOperName(NamedElement e){
		return baseMethodName() + NameConverter.capitalize(e.getName());
	}
	@Override
	public String unhandledExceptionOperName(){
		return baseMethodName() + "UnhandledException";
	}
	public List<Parameter> getArgumentParameters(){
		return EmfBehaviorUtil.getArgumentParameters(operation);
	}
	public List<Parameter> getResultParameters(){
		return EmfBehaviorUtil.getResultParameters(operation);
	}
	public Parameter getReturnParameter(){
		return returnParameter;
	}
	public boolean isLongRunning(){
		if(operation instanceof Operation){
			return EmfBehaviorUtil.isLongRunning((Operation) operation);
		}else{
			return EmfBehaviorUtil.isLongRunning((Behavior) operation);
		}
	}
	public Collection<Constraint> getPreConditions(){
		if(operation instanceof Operation){
			return ((Operation) operation).getPreconditions();
		}else{
			return ((Behavior) operation).getPreconditions();
		}
	}
	public Classifier getContext(){
		if(operation instanceof Operation){
			return (Classifier) ((Operation) operation).getOwner();
		}else{
			return ((Behavior) operation).getContext();
		}
	}
	public List<Constraint> getPostConditions(){
		if(operation instanceof Operation){
			return ((Operation) operation).getPostconditions();
		}else{
			return ((Behavior) operation).getPostconditions();
		}
	}
	public List<Parameter> getExceptionParameters(){
		List<Parameter> result = new ArrayList<Parameter>();
		for(Parameter parameter:this.parameters){
			if(parameter.isException()){
				result.add(parameter);
			}
		}
		return result;
	}
	public NamedElement getNamedElement(){
		return operation;
	}
}
