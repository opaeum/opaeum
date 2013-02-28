package org.opaeum.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opaeum.runtime.domain.CompositionNode;

public class EntityValueProvider{
	List<EntityInstanceSimulation> buckets = new ArrayList<EntityInstanceSimulation>();
	int lastBuck=-1;
	public Object getNextReference(){
		Collections.sort(buckets);
		return buckets.get(getNextBuckIndex()).getNextReference();
	}
	private int getNextBuckIndex(){
		if(lastBuck+1==buckets.size()){
			lastBuck=0;
		}else{
			lastBuck++;
		}
		return lastBuck;
	}
	public Object createNewInstance(CompositionNode parent) throws Exception{
		Collections.sort(buckets);
		return buckets.get(getNextBuckIndex()).generateInstance(parent);
	}
	public void populateReferences() throws Exception{
		for(EntityInstanceSimulation eis:buckets){
			eis.populateReferences();
		}
	}
}