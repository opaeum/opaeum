package org.opaeum.simulation;

import java.util.List;

import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.opaeum.runtime.domain.CompositionNode;

public abstract class EntityInstanceSimulation <T extends CompositionNode> extends AbstractBucket{
	List<T> values;
	private UniformIntegerDistribution distribution;
	public T generateInstance(CompositionNode parent) throws Exception{
		T result = createNewObject(parent);
		values.add(result);
		// Remember, multiple instances of this class will be created per property where it is referenced from. it will either be references
		// from
		// a composition context OR a reference contextF
		count++;
		this.distribution = null;
		return result;
	}
	protected abstract T createNewObject(CompositionNode parent) throws Exception;
	protected abstract void populateReferences(CompositionNode compositionNode) throws Exception;
	public T getNextReference(){
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
	private List<T> getValues(){
		return values;
	}
	public void setValues(List<T> values){
		this.values = values;
	}
	public void populateReferences() throws Exception{
		List<T> list = values;
		for(T compositionNode:list){
			populateReferences(compositionNode);
		}
	}
}
