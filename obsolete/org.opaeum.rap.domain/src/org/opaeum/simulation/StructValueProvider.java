package org.opaeum.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opaeum.runtime.domain.CompositionNode;

public class StructValueProvider{
	List<StructInstanceSimulation> buckets = new ArrayList<StructInstanceSimulation>();
	public Object createNewInstance(){
		Collections.sort(buckets);
		return buckets.get(0).generateInstance();
	}
	public void populateReferences(){
		List<StructInstanceSimulation> list = buckets;
		for(StructInstanceSimulation structInstanceSimulation:list){
			structInstanceSimulation.populateReferences();
			
		}
		// TODO Auto-generated method stub
		
	}
}