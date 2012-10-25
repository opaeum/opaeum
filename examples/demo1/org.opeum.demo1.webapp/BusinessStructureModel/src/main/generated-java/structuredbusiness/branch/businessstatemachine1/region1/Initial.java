package structuredbusiness.branch.businessstatemachine1.region1;

import java.util.Set;

import javax.persistence.Transient;

import org.opaeum.runtime.domain.CancelledEvent;
import org.opaeum.runtime.domain.OutgoingEvent;
import org.opaeum.runtime.statemachines.PseudoStateActivation;

import structuredbusiness.Branch;
import structuredbusiness.branch.BusinessStateMachine1;
import structuredbusiness.branch.BusinessStateMachine1State;
import structuredbusiness.branch.BusinessStateMachine1Token;
import structuredbusiness.branch.businessstatemachine1.Region1;

public class Initial<SME extends BusinessStateMachine1> extends PseudoStateActivation<SME, BusinessStateMachine1Token<SME>> {
	static public String ID = "914890@_MwyhUBYbEeKsDbmQL25eBw";
	@Transient
	private Transition0<SME> Transition0;

	/** Constructor for Initial
	 * 
	 * @param region 
	 */
	public Initial(Region1<SME> region) {
	super(ID,region);
		setInitial(true);
	}

	public SME getBehaviorExecution() {
		SME result = (SME)super.getBehaviorExecution();
		
		return result;
	}
	
	public Set<CancelledEvent> getCancelledEvents() {
		Set<CancelledEvent> result = getBehaviorExecution().getCancelledEvents();
		
		return result;
	}
	
	public Branch getContextObject() {
		Branch result = ((BusinessStateMachine1)super.getBehaviorExecution()).getContextObject();
		
		return result;
	}
	
	public Set<OutgoingEvent> getOutgoingEvents() {
		Set<OutgoingEvent> result = getBehaviorExecution().getOutgoingEvents();
		
		return result;
	}
	
	public Transition0<SME> getTransition0() {
		return this.Transition0;
	}
	
	public boolean onCompletion() {
		boolean result = false;
		
		return result;
	}
	
	public void setTransition0(Transition0<SME> Transition0) {
		this.Transition0=Transition0;
	}

}