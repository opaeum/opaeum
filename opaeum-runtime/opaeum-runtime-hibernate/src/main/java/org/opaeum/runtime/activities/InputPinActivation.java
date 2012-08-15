package org.opaeum.runtime.activities;

public class InputPinActivation<AE extends IActivityNodeContainerExecution, T extends ActivityToken<AE>>   extends PinActivation<AE,T>{
	public InputPinActivation(AE group,String id,String name,int lower,int upper){
		super(group, id, name,lower, upper);
	}
	public void receiveOffer(){
		// Forward the offer to the action activation. [When all input pins are
		// ready, the action will fire them.]
		this.actionActivation.receiveOffer();
	}
	public boolean isReady(){
		// Return true if the total number of values already being offered by
		// this pin plus those being offered
		// by the sources of incoming edges is at least equal to the minimum
		// multiplicity of the pin.
		boolean ready = super.isReady();
		if(ready){
			int totalValueCount = this.countUnofferedTokens() + this.countOfferedValues();
			ready = totalValueCount >= lower;
		}
		return ready;
	}
}
