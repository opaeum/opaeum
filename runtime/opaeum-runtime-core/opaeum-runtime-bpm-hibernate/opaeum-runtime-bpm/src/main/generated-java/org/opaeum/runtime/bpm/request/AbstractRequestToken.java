package org.opaeum.runtime.bpm.request;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.OneToMany;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.domain.IToken;

@NumlMetaInfo(uuid="252060@_6MA8UI2-EeCrtavWRHwoHg")
public class AbstractRequestToken<SME extends AbstractRequest> extends StateMachineToken<SME> {
	private AbstractRequest behaviorExecution;
	@OneToMany(mappedBy="parentToken")
	private Set<IToken<SME>> childTokens = new HashSet<IToken<SME>>();
	private IToken<SME> parentToken;

	/** Constructor for AbstractRequestToken
	 * 
	 * @param parentToken 
	 */
	public AbstractRequestToken(AbstractRequestToken parentToken) {
		this.parentToken=parentToken;
		if ( parentToken!=null ) {
			parentToken.getChildTokens().add(this);
		}
	}

	public SME getBehaviorExecution() {
		SME result = (SME)behaviorExecution;
		
		return result;
	}
	
	public Set<IToken<SME>> getChildTokens() {
		return this.childTokens;
	}
	
	public IToken<SME> getParentToken() {
		return this.parentToken;
	}
	
	public void setBehaviorExecution(AbstractRequest behaviorExecution) {
		this.behaviorExecution=behaviorExecution;
	}
	
	public void setChildTokens(Set<IToken<SME>> childTokens) {
		this.childTokens=childTokens;
	}
	
	public void setParentToken(IToken<SME> parentToken) {
		this.parentToken=parentToken;
	}

}