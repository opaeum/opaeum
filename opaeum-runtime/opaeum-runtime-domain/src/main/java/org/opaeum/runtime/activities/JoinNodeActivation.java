package org.opaeum.runtime.activities;


public class JoinNodeActivation<AE extends IActivityNodeContainerExecution, T extends IActivityToken<AE>> extends ControlNodeActivation<AE,T>{

	public JoinNodeActivation(AE group,String id){
		super(group, id);
	}
	public boolean isReady() {
		// Check that all incoming edges have sources that are offering tokens.

		boolean ready = true;
		int i = 1;
		while (ready & i <= this.incomingEdges.size()) {
			ready = this.incomingEdges.get(i - 1).hasOffer();
			i = i + 1;
		}

		return ready;
	}
}
