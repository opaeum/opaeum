package org.opaeum.feature;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.opaeum.feature.StepDependency.StrategyRequirement;
import org.opaeum.runtime.domain.IntrospectionUtil;

public class StrategyCalculator{
	Set<Class<? extends ITransformationStep>> actualClasses;
	Map<Class<?>,StrategyRequirementHolder> holders = new HashMap<Class<?>,StrategyRequirementHolder>();
	private HashMap<Class<?>,StrategyRequirement> strategies;
	public StrategyCalculator(Set<Class<? extends ITransformationStep>> actualClasses){
		super();
		this.actualClasses = actualClasses;
		for(Class<? extends ITransformationStep> c:this.actualClasses){
			addRequiredStrategiesRecursively(c);
		}
		for(StrategyRequirementHolder h:holders.values()){
			for(Class<?> replacedClass:h.getReplacedStrategyClasses()){
				StrategyRequirementHolder holder = holders.get(replacedClass);
				if(holder!=null){
					//Replaced strategy may not have been selected in the transformation process 
					h.addReplaces(holder);
				}
			}
		}
		this.strategies = new HashMap<Class<?>,StepDependency.StrategyRequirement>();
		for(Class<? extends ITransformationStep> c:this.actualClasses){
			for(StrategyRequirement sr:c.getAnnotation(StepDependency.class).strategyRequirement()){
				StrategyRequirement existing = strategies.get(sr.strategyContract());
				if(existing == null || overrides(sr, existing)){
					strategies.put(sr.strategyContract(), sr);
				}
			}
		}
	}
	private void addRequiredStrategiesRecursively(Class<? extends ITransformationStep> c){
		StepDependency sd = c.getAnnotation(StepDependency.class);
		for(StrategyRequirement sr:sd.strategyRequirement()){
			holders.put(sr.requires(), new StrategyRequirementHolder(sr));
		}
		for(Class<? extends ITransformationStep> replacedTransformation:sd.replaces()){
			addRequiredStrategiesRecursively(replacedTransformation);
		}
	}
	private boolean overrides(StrategyRequirement sr,StrategyRequirement existing){
		return holders.get(sr.requires()).replaces(holders.get(existing.requires()));
	}
	public <T>T newInstance(Class<? extends T> s){
		return (T) IntrospectionUtil.newInstance(strategies.get(s).requires());
	}
}
