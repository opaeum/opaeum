package org.opaeum.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StructValueProvider{
	List<StructInstanceSimulation> buckets = new ArrayList<StructInstanceSimulation>();
	public Object createNewInstance() throws Exception{
		Collections.sort(buckets);
		return buckets.get(0).generateInstance();
	}
	public void populateReferences() throws Exception{
		List<StructInstanceSimulation> list = buckets;
		for(StructInstanceSimulation structInstanceSimulation:list){
			structInstanceSimulation.populateReferences();
			
		}
		// TODO Auto-generated method stub
		
	}
}