package org.opaeum.javageneration.maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

public class NakedOperationMap extends OperationMap implements IMessageMap,ExceptionRaisingMap{
	Map<Parameter,NakedClassifierMap> params = null;
	private OJPathName callbackListenerPath;
	private OJPathName handlerPath;
	public List<OJPathName> javaParamTypePathsWithReturnInfo(){
		List<OJPathName> javaParamTypePaths = new ArrayList<OJPathName>();
		if(isLongRunning()){
			javaParamTypePaths.add(Jbpm5Util.getProcessContext());
		}
		javaParamTypePaths.addAll(super.javaParamTypePaths());
		return javaParamTypePaths;
	}
	public NakedOperationMap(final Behavior operation){
		super(operation, operation.getOwnedParameters());
		if(getReturnParameter() == null){
			// this.operationTypeMap=new ClassifierMap();
		}else{
			this.operationTypeMap = OJUtil.buildClassifierMap((Classifier) getReturnParameter().getType(), EmfPropertyUtil.getCollectionKind(getReturnParameter()));
		}
	}
	public NakedOperationMap(final Operation operation){
		super(operation, operation.getOwnedParameters());
		if(getReturnParameter() == null){
			// this.operationTypeMap=new ClassifierMap();
		}else{
			this.operationTypeMap = OJUtil.buildClassifierMap((Classifier) getReturnParameter().getType(), EmfPropertyUtil.getCollectionKind(getReturnParameter()));
		}
	}
	@Override
	public String eventConsumerMethodName(){
		return "consume" + NameConverter.capitalize(operation.getName()) + "Occurrence";
	}
	@Override
	public String javaOperName(){
		if(operation instanceof Behavior){
			Behavior behaviour = (Behavior) operation;
			if(behaviour.getSpecification() != null){
				return behaviour.getSpecification().getName();
			}else{
				return super.javaOperName();
			}
		}else{
			return super.javaOperName();
		}
	}

	private Map<Parameter,NakedClassifierMap> getParamMap(){
		if(params == null){
			params = new HashMap<Parameter,NakedClassifierMap>();
			for(Parameter p:parameters){
				params.put(p, OJUtil.buildClassifierMap((Classifier) p.getType(),p));
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
			while(root.getSuperClasses().size()==1  && root.getSuperClasses().get(0) instanceof Behavior){
				root = (Behavior) root.getSuperClasses().get(0);
				if(root.getSpecification() != null){
					return root.getSpecification();
				}
			}
			return root;
		}
	}

	@Override
	public OJPathName javaParamTypePath(Parameter elem){
		return getParamMap().get(elem).javaTypePath();
	}
	public OJPathName callbackListenerPath(){
		if(callbackListenerPath == null){
			this.callbackListenerPath = OJUtil.packagePathname(getContractDefiningElement().getNamespace()).getCopy();
			callbackListenerPath.addToNames(callbackListener());
		}
		return callbackListenerPath;
	}
	public OJPathName handlerPath(){
		if(handlerPath == null){
			this.handlerPath = OJUtil.packagePathname(getContractDefiningElement().getNamespace()).getCopy();
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
		return "on" +  NameConverter.capitalize(getContractDefiningElement().getName());
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
			return EmfBehaviorUtil.hasExecutionInstance((Operation)operation);
		}else{
			return EmfBehaviorUtil.hasExecutionInstance((Behavior)operation);
		}
	}
	public OJPathName messageStructurePath(){
		if(operation instanceof Operation){
			return OJUtil.classifierPathname(((Operation) operation));
		}else{
			return OJUtil.classifierPathname((Classifier) operation);
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
			return EmfBehaviorUtil.isLongRunning((Operation)operation);
		}else{
			return EmfBehaviorUtil.isLongRunning((Behavior)operation);
		}
	}
	public Collection<Constraint> getPreConditions(){
		if(operation instanceof Operation){
			return ((Operation) operation).getPreconditions();
		}else{
			return ((Behavior)operation).getPreconditions();
		}
	}
	public Classifier getContext(){
		if(operation instanceof Operation){
			return (Classifier) ((Operation) operation).getOwner();
		}else{
			return ((Behavior)operation).getContext();
		}
	}
	public List<Constraint> getPostConditions(){
		if(operation instanceof Operation){
			return ((Operation) operation).getPostconditions();
		}else{
			return ((Behavior)operation).getPostconditions();
		}
	}
	public List<Parameter> getExceptionParameters(){
		List<Parameter> result=new ArrayList<Parameter>();
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
