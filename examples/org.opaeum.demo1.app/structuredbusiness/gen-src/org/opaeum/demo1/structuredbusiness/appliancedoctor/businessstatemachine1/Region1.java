package org.opaeum.demo1.structuredbusiness.appliancedoctor.businessstatemachine1;

import org.opaeum.demo1.structuredbusiness.appliancedoctor.BusinessStateMachine1;
import org.opaeum.demo1.structuredbusiness.appliancedoctor.BusinessStateMachine1Token;
import org.opaeum.demo1.structuredbusiness.appliancedoctor.businessstatemachine1.region1.State0;
import org.opaeum.demo1.structuredbusiness.appliancedoctor.businessstatemachine1.region1.State1;
import org.opaeum.runtime.statemachines.RegionActivation;

public class Region1<SME extends BusinessStateMachine1> extends RegionActivation<SME, BusinessStateMachine1Token<SME>> {
	static public String ID = "914890@_dt2BAH2lEeK5F45wEGRv4A";

	/** Constructor for Region1
	 * 
	 * @param owner 
	 */
	public Region1(SME owner) {
	super(ID,owner);
		vertices.add(new State0<SME>(this));
		vertices.add(new State1<SME>(this));
	}

	public void linkTransitions() {
		super.linkTransitions();
	}

}