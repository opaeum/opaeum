package net.sf.nakeduml.javageneration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.internal.types.OperationImpl;

import org.nakeduml.java.metamodel.OJPathName;

public class NakedOperationMap extends OperationMap{
	private IParameterOwner parameterOwner;
	@Override
	public List<OJPathName> javaParamTypePaths(){
		List<OJPathName> javaParamTypePaths = new ArrayList<OJPathName>();
		
		if(parameterOwner instanceof INakedOperation &&((INakedOperation) parameterOwner).isLongRunning()){
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
	public String eventOperName(){
		return "on"+((INakedOperation)parameterOwner).getMappingInfo().getJavaName().getCapped();
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
			for(IParameter p:getOperation().getParameters()){
				params.put(p, new NakedClassifierMap(p.getType()));
			}
		}
		return params;
	}
	public IParameterOwner getOperation(){
		return this.parameterOwner;
	}
	@Override
	public OJPathName javaParamTypePath(IParameter elem){
		return getParamMap().get(elem).javaTypePath();
	}
	public OJPathName callbackListenerPath(){
		OJPathName path = OJUtil.packagePathname(getOperation().getNameSpace());
		path.addToNames(callbackListener());
		return path;
	}
	public String callbackListener(){
		return getOperation().getMappingInfo().getJavaName().getCapped() + "Listener";
	}
	public String callbackOperName(){
		return "on" + getOperation().getMappingInfo().getJavaName().getCapped() + "Complete";
	}
}
