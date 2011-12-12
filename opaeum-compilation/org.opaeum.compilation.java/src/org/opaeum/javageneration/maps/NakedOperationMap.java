package org.opaeum.javageneration.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.internal.types.OperationImpl;

import org.opaeum.feature.MappingInfo;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.IParameterOwner;
import org.opaeum.metamodel.name.NameWrapper;

public class NakedOperationMap extends OperationMap implements IMessageMap,ExceptionRaisingMap{
	private IParameterOwner parameterOwner;
	public List<OJPathName> javaParamTypePathsWithReturnInfo(){
		List<OJPathName> javaParamTypePaths = new ArrayList<OJPathName>();
		if(parameterOwner.isLongRunning()){
			javaParamTypePaths.add(Jbpm5Util.getProcessContext());
		}
		javaParamTypePaths.addAll(super.javaParamTypePaths());
		return javaParamTypePaths;
	}
	public NakedOperationMap(final IParameterOwner operation){
		super(new OperationImpl(operation.getName()){
			@Override
			public IClassifier getReturnType(){
				return operation.getReturnParameter() != null ? operation.getReturnParameter().getType() : null;
			}
			@Override
			public List<IParameter> getParameters(){
				return operation.getParameters();
			}
		});
		this.parameterOwner = operation;
		if(super.operation.getReturnType() == null){
			// this.operationTypeMap=new ClassifierMap();
		}else{
			this.operationTypeMap = OJUtil.buildClassifierMap(super.operation.getReturnType());
		}
	}
	@Override
	public String eventConsumerMethodName(){
		return "consume" + ((INakedOperation) getParameterOwner()).getMappingInfo().getJavaName().getCapped() + "Occurrence";
	}
	@Override
	public String javaOperName(){

		if(parameterOwner instanceof INakedBehavior){
			INakedBehavior behaviour = (INakedBehavior) parameterOwner;
			if(behaviour.getSpecification() != null){
				return behaviour.getSpecification().getName();
			}else{
				return super.javaOperName();
			}
		}else{
			return super.javaOperName();
		}
	}
	Map<IParameter,NakedClassifierMap> params = null;
	private OJPathName callbackListenerPath;
	private OJPathName handlerPath;
	private Map<IParameter,NakedClassifierMap> getParamMap(){
		if(params == null){
			params = new HashMap<IParameter,NakedClassifierMap>();
			for(IParameter p:getContractDefiningElement().getParameters()){
				params.put(p, OJUtil.buildClassifierMap(p.getType()));
			}
		}
		return params;
	}
	public boolean hasContract(){
		return parameterOwner != getContractDefiningElement();
	}
	private IParameterOwner getContractDefiningElement(){
		if(parameterOwner instanceof INakedOperation){
			return parameterOwner;
		}else{
			INakedBehavior root = (INakedBehavior) parameterOwner;
			while(root.getSupertype() instanceof INakedBehavior){
				root = (INakedBehavior) root.getSupertype();
				if(root.getSpecification() != null){
					return root.getSpecification();
				}
			}
			return root;
		}
	}
	public IParameterOwner getParameterOwner(){
		return this.parameterOwner;
	}
	@Override
	public OJPathName javaParamTypePath(IParameter elem){
		return getParamMap().get(elem).javaTypePath();
	}
	public OJPathName callbackListenerPath(){
		if(callbackListenerPath == null){
			this.callbackListenerPath = OJUtil.packagePathname(getContractDefiningElement().getNameSpace()).getCopy();
			callbackListenerPath.addToNames(callbackListener());
		}
		return callbackListenerPath;
	}
	public OJPathName handlerPath(){
		if(handlerPath == null){
			this.handlerPath = OJUtil.packagePathname(getContractDefiningElement().getNameSpace()).getCopy();
			MappingInfo mi = this.parameterOwner.getMappingInfo();
			handlerPath.addToNames(mi.getJavaName().getCapped() + "Handler" + mi.getOpaeumId());
		}
		return handlerPath;
	}
	public String callbackListener(){
		return getContractDefiningElement().getMappingInfo().getJavaName().getCapped() + "Listener";
	}
	public String callbackOperName(){
		return baseMethodName() + "Complete";
	}
	protected String baseMethodName(){
		return "on" + getContractDefiningElement().getMappingInfo().getJavaName().getCapped();
	}
	@Override
	public String eventGeratorMethodName(){
		NameWrapper javaName = getParameterOwner().getMappingInfo().getJavaName();
		return "generate" + javaName.getCapped() + "Event";
	}
	@Override
	public OJPathName eventHandlerPath(){
		return handlerPath();
	}
	public boolean hasMessageStructure(){
		return BehaviorUtil.hasExecutionInstance(getParameterOwner());
	}
	public OJPathName messageStructurePath(){
		if(getParameterOwner() instanceof INakedOperation){
			return OJUtil.classifierPathname(((INakedOperation) getParameterOwner()).getMessageStructure());
		}else{
			return OJUtil.classifierPathname((INakedClassifier) getParameterOwner());
		}
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opaeum.javageneration.maps.ExceptionRaisingMap#exceptionOperName(org.opaeum.metamodel.core.INakedElement)
	 */
	@Override
	public String exceptionOperName(INakedElement e){
		return baseMethodName() + e.getMappingInfo().getJavaName().getCapped();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opaeum.javageneration.maps.ExceptionRaisingMap#unhandledExceptionOperName()
	 */
	@Override
	public String unhandledExceptionOperName(){
		return baseMethodName() + "UnhandledException";
	}
}
