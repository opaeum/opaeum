package org.opaeum.simulation;

import java.util.List;

import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.opaeum.runtime.domain.CompositionNode;

public abstract class EntityInstanceSimulation extends AbstractBucket{
	List<CompositionNode> values;
	private UniformIntegerDistribution distribution;
	public CompositionNode generateInstance(CompositionNode parent){
		CompositionNode result = createNewObject(parent);
		values.add(result);
		// Remember, multiple instances of this class will be created per property where it is referenced from. it will either be references
		// from
		// a composition context OR a reference contextF
		count++;
		this.distribution = null;
		return result;
	}
	protected abstract CompositionNode createNewObject(CompositionNode parent);
	protected abstract void populateReferences(CompositionNode compositionNode);
	public CompositionNode getNextReference(){
		count++;
		if(getValues().size() == 1){
			return getValues().get(0);
		}else if(getValues().isEmpty()){
			return null;
		}else{
			if(this.distribution == null){
				distribution = new UniformIntegerDistribution(0, getValues().size() - 1);
			}
			return getValues().get(distribution.sample());
		}
	}
	private List<CompositionNode> getValues(){
		return values;
	}
	public void setValues(List<CompositionNode> values){
		this.values = values;
	}
	public void populateReferences(){
		List<CompositionNode> list = values;
		for(CompositionNode compositionNode:list){
			populateReferences(compositionNode);
		}
	}
}
