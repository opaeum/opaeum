package org.opaeum.metamodel.core.internal;

import java.util.Collections;
import java.util.List;

import nl.klasse.octopus.model.IState;

import org.opaeum.metamodel.core.CodeGenerationStrategy;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;

/**
 * A simple data type, i.e. a data type that can not be broken down into smaller bits like attributes. See ValueType and Primitive Typically
 * it would be represented in one column in the database. It would be represented in one UIInput in JSF
 * 
 */
public abstract class NakedSimpleDataTypeImpl extends NakedClassifierImpl implements INakedSimpleType{
	public static class NoStrategyFactory extends AbstractStrategyFactory{
		@SuppressWarnings("unchecked")
		NoStrategyFactory(){
			super();
		}
		@Override
		public boolean appliesTo(INakedSimpleType st){
			return false;
		}
		@Override
		public String getRuntimeStrategyFactory(){
			return "org.opaeum.runtime.environment.SimpleTypeRuntimeStrategyFactory";
		}
	}
	private static final long serialVersionUID = 4359784104365005415L;
	private AbstractStrategyFactory strategyFactory = new NoStrategyFactory();
	private String runtimeStrategyFactory;
	public AbstractStrategyFactory getStrategyFactory(){
		return strategyFactory;
	}
	public NakedSimpleDataTypeImpl(){
	}
	public boolean hasStrategy(Class<?> class1){
		return getStrategy(class1) != null;
	}
	public <T>T getStrategy(Class<T> c){
		if(!strategyFactory.hasStrategy(c) && getSupertype() != null){
			return ((NakedSimpleDataTypeImpl) getSupertype()).getStrategy(c);
		}else{
			return strategyFactory.getStrategy(c);
		}
	}
	public void setStrategyFactory(AbstractStrategyFactory f){
		this.strategyFactory = f;
	}
	@Override
	/*
	 * SimpleTypes have to be either built in or pre-implemented
	 */
	public CodeGenerationStrategy getCodeGenerationStrategy(){
		return CodeGenerationStrategy.NO_CODE;
	}
	@Override
	public boolean hasStereotype(String name){
		// TODO Auto-generated method stub
		return super.hasStereotype(name) || (getSupertype() != null && getSupertype().hasStereotype(name));
	}
	// TODO re-evaulate this
	@Override
	public INakedInstanceSpecification getStereotype(String name){
		if(super.hasStereotype(name)){
			return super.getStereotype(name);
		}else if(getSupertype() != null){
			return getSupertype().getStereotype(name);
		}
		return null;
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasValueForFeature("runtimeStrategyFactory")){
			this.runtimeStrategyFactory = stereotype.getFirstValueFor("runtimeStrategyFactory").stringValue();
		}
	}
	@Override
	public String getMetaClass(){
		return "dataType";
	}
	@Override
	public List<IState> getStates(){
		return Collections.emptyList();
	}
	public String getRuntimeStrategyFactory(){
		if(this.runtimeStrategyFactory == null && getSupertype() instanceof INakedSimpleType){
			return ((INakedSimpleType) getSupertype()).getRuntimeStrategyFactory();
		}
		return runtimeStrategyFactory;
	}
}