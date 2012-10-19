package structuredbusiness.branch.businessstatemachine1;

import org.opaeum.runtime.statemachines.RegionActivation;

import structuredbusiness.branch.BusinessStateMachine1;
import structuredbusiness.branch.BusinessStateMachine1Token;
import structuredbusiness.branch.businessstatemachine1.region1.Approved;
import structuredbusiness.branch.businessstatemachine1.region1.Draft;
import structuredbusiness.branch.businessstatemachine1.region1.Initial;
import structuredbusiness.branch.businessstatemachine1.region1.State1;
import structuredbusiness.branch.businessstatemachine1.region1.Submitted;
import structuredbusiness.branch.businessstatemachine1.region1.Transition0;
import structuredbusiness.branch.businessstatemachine1.region1.Transition1;

public class Region1<SME extends BusinessStateMachine1> extends RegionActivation<SME, BusinessStateMachine1Token<SME>> {
	static public String ID = "914890@_JJlOQBYUEeKsDbmQL25eBw";

	/** Constructor for Region1
	 * 
	 * @param owner 
	 */
	public Region1(SME owner) {
	super(ID,owner);
		vertices.add(new Draft<SME>(this));
		vertices.add(new State1<SME>(this));
		vertices.add(new Approved<SME>(this));
		vertices.add(new Initial<SME>(this));
	}

	public void linkTransitions() {
		transitions.add(new Transition0<SME>(this));
		transitions.add(new Submitted<SME>(this));
		transitions.add(new Transition1<SME>(this));
		super.linkTransitions();
	}

}