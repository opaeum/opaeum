package org.opaeum.runtime.bpm.request;

import java.util.Set;

import javax.persistence.OneToMany;

import org.opaeum.hibernate.domain.StateMachineToken;

public class AbstractRequestToken extends StateMachineToken {
	@OneToMany(mappedBy="parentToken")
	private Set<AbstractRequestToken> childTokens;
	private AbstractRequestToken parentToken;
	private AbstractRequest stateMachineExecution;

	/** Constructor for AbstractRequestToken
	 * 
	 * @param parentToken 
	 */
	public AbstractRequestToken(AbstractRequestToken parentToken) {
		this.parentToken=parentToken;
	}

	public Set<AbstractRequestToken> getChildTokens() {
		return this.childTokens;
	}
	
	public AbstractRequestToken getParentToken() {
		return this.parentToken;
	}
	
	public AbstractRequest getStateMachineExecution() {
		return this.stateMachineExecution;
	}
	
	public void setChildTokens(Set<AbstractRequestToken> childTokens) {
		this.childTokens=childTokens;
	}
	
	public void setParentToken(AbstractRequestToken parentToken) {
		this.parentToken=parentToken;
	}
	
	public void setStateMachineExecution(AbstractRequest stateMachineExecution) {
		this.stateMachineExecution=stateMachineExecution;
	}

}