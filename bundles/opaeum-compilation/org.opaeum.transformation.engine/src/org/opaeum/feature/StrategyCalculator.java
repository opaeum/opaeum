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
			for(StrategyRequirement sr:c.getAnnotation(StepDependency.class).strategyRequirement()){
				holders.put(sr.requires(), new StrategyRequirementHolder(sr));
			}
		}
		for(StrategyRequirementHolder h:holders.values()){
			for(Class<?> replacedClass:h.getReplacedStrategyClasses()){
				h.addReplaces(holders.get(replacedClass));
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
	private boolean overrides(StrategyRequirement sr,StrategyRequirement existing){
		return holders.get(sr.requires()).replaces(holders.get(existing.requires()));
	}
	public <T>T newInstance(Class<? extends T> s){
		return (T) IntrospectionUtil.newInstance(strategies.get(s).requires());
	}
}
