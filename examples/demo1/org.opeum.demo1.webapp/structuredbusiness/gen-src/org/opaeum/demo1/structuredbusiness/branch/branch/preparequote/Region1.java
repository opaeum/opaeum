package org.opaeum.demo1.structuredbusiness.branch.branch.preparequote;

import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuote;
import org.opaeum.demo1.structuredbusiness.branch.branch.PrepareQuoteToken;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1.After3BusinessDays;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1.Approved;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1.Draft;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1.Escalated;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1.Initial;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1.OnSubmit;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1.Submitted;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1.Transition0;
import org.opaeum.demo1.structuredbusiness.branch.branch.preparequote.region1.Transition1;
import org.opaeum.runtime.statemachines.RegionActivation;

public class Region1<SME extends PrepareQuote> extends RegionActivation<SME, PrepareQuoteToken<SME>> {
	static public String ID = "914890@_JJlOQBYUEeKsDbmQL25eBw";

	/** Constructor for Region1
	 * 
	 * @param owner 
	 */
	public Region1(SME owner) {
	super(ID,owner);
		vertices.add(new Draft<SME>(this));
		vertices.add(new Submitted<SME>(this));
		vertices.add(new Approved<SME>(this));
		vertices.add(new Initial<SME>(this));
		vertices.add(new Escalated<SME>(this));
	}

	public void linkTransitions() {
		transitions.add(new Transition0<SME>(this));
		transitions.add(new After3BusinessDays<SME>(this));
		transitions.add(new Transition1<SME>(this));
		transitions.add(new OnSubmit<SME>(this));
		super.linkTransitions();
	}

}