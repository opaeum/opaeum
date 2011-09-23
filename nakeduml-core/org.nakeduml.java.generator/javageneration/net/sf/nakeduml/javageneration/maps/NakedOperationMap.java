package net.sf.nakeduml.javageneration.maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.internal.types.OperationImpl;

import org.nakeduml.java.metamodel.OJPathName;

public class NakedOperationMap extends OperationMap implements IMessageMap{
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
			this.operationTypeMap = new NakedClassifierMap(super.operation.getReturnType());
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
	private Map<IParameter,NakedClassifierMap> getParamMap(){
		if(params == null){
			params = new HashMap<IParameter,NakedClassifierMap>();
			for(IParameter p:getParameterOwner().getParameters()){
				params.put(p, new NakedClassifierMap(p.getType()));
			}
		}
		return params;
	}
	private IParameterOwner getSpecification(){
		if(parameterOwner instanceof INakedOperation || ((INakedBehavior) parameterOwner).getSpecification() == null){
			return parameterOwner;
		}else{
			return ((INakedBehavior) parameterOwner).getSpecification();
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
		OJPathName path = OJUtil.packagePathname(getSpecification().getNameSpace());
		path.addToNames(callbackListener());
		return path;
	}
	public String callbackListener(){
		return getSpecification().getMappingInfo().getJavaName().getCapped() + "Listener";
	}
	public String callbackOperName(){
		return "on" + getSpecification().getMappingInfo().getJavaName().getCapped() + "Complete";
	}
	@Override
	public String eventGeratorMethodName(){
		return "generate" + getSpecification().getMappingInfo().getJavaName().getCapped() + "Event";
	}
	@Override
	public OJPathName eventHandlerPath(){
		OJPathName path = OJUtil.packagePathname(getSpecification().getNameSpace());
		path.addToNames(getParameterOwner().getMappingInfo().getJavaName().getCapped() + "Handler" + getParameterOwner().getMappingInfo().getNakedUmlId());
		return path;
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
	public String exceptionOperName(INakedElement e){
		return callbackOperName() + e.getMappingInfo().getJavaName().getCapped();
	}
	public String unhandledExceptionOperName(){
		return callbackOperName()+"UnhandledException";
	}
}
