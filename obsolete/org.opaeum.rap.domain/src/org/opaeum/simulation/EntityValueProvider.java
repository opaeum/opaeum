package org.opaeum.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opaeum.runtime.domain.CompositionNode;

public class EntityValueProvider{
	List<EntityInstanceSimulation> buckets = new ArrayList<EntityInstanceSimulation>();
	public Object getNextReference(){
		Collections.sort(buckets);
		return buckets.get(0).getNextReference();
	}
	public Object createNewInstance(CompositionNode parent){
		Collections.sort(buckets);
		return buckets.get(0).generateInstance(parent);
	}
	public void populateReferences(){
		for(EntityInstanceSimulation eis:buckets){
			eis.populateReferences();
		}
	}
}