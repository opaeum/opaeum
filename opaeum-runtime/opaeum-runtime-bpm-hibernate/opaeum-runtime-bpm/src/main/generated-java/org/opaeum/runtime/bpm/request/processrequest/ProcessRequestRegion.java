package org.opaeum.runtime.bpm.request.processrequest;

import org.opaeum.runtime.bpm.request.ProcessRequest;
import org.opaeum.runtime.bpm.request.ProcessRequestToken;
import org.opaeum.runtime.statemachines.RegionActivation;

public class ProcessRequestRegion extends RegionActivation<ProcessRequest, ProcessRequestToken> {
	static public String ID = "252060@_e2jAAI2-EeCrtavWRHwoHg";

	/** Constructor for ProcessRequestRegion
	 * 
	 * @param owner 
	 */
	public ProcessRequestRegion(ProcessRequest owner) {
	super("252060@_e2jAAI2-EeCrtavWRHwoHg",owner);
	}

	public void linkTransitions() {
		super.linkTransitions();
	}

}