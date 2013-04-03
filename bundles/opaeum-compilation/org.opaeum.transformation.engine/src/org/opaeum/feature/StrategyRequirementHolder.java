package org.opaeum.feature;

import java.util.Collection;
import java.util.HashSet;

import org.opaeum.feature.StepDependency.StrategyRequirement;

public class StrategyRequirementHolder{
	private StepDependency.StrategyRequirement strategyRequirement;
	private Collection<StrategyRequirementHolder> replaces=new HashSet<StrategyRequirementHolder>();
	
	public StrategyRequirementHolder(StrategyRequirement strategyRequirement){
		super();
		this.strategyRequirement = strategyRequirement;
	}
	public Class<?> getStrategyClass(){
		return strategyRequirement.requires();
	}
	public Class<?>[] getReplacedStrategyClasses(){
		return strategyRequirement.replaces();
	}
	public void addReplaces(StrategyRequirementHolder holder){
		replaces.add(holder);
	}
	public boolean replaces(StrategyRequirementHolder other){
		if(replaces.contains(other)){
			return true;
		}else{
			for(StrategyRequirementHolder h:this.replaces){
				if(h.replaces(other)){
					return true;
				}
			}
			return false;
		}
	}
}
