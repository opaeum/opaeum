package org.opaeum.runtime.bpm.request.abstractrequest;

import org.opaeum.runtime.bpm.request.AbstractRequest;
import org.opaeum.runtime.bpm.request.AbstractRequestToken;
import org.opaeum.runtime.bpm.request.abstractrequest.region1.Active;
import org.opaeum.runtime.bpm.request.abstractrequest.region1.Complete;
import org.opaeum.runtime.bpm.request.abstractrequest.region1.Created;
import org.opaeum.runtime.bpm.request.abstractrequest.region1.InitialNode;
import org.opaeum.runtime.bpm.request.abstractrequest.region1.Suspended;
import org.opaeum.runtime.statemachines.RegionActivation;

public class Region1<SME extends AbstractRequest> extends RegionActivation<SME, AbstractRequestToken<SME>> {
	static public String ID = "252060@_FgGnMI6lEeCFsPOcAnk69Q";

	/** Constructor for Region1
	 * 
	 * @param owner 
	 */
	public Region1(SME owner) {
	super(ID,owner);
		vertices.add(new InitialNode<SME>(this));
		vertices.add(new Active<SME>(this));
		vertices.add(new Suspended<SME>(this));
		vertices.add(new Created<SME>(this));
		vertices.add(new Complete<SME>(this));
	}

	public void linkTransitions() {
		super.linkTransitions();
	}

}