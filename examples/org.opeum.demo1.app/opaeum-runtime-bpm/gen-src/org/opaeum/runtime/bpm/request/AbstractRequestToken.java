package org.opaeum.runtime.bpm.request;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.hibernate.domain.StateMachineToken;
import org.opaeum.runtime.domain.IToken;

@NumlMetaInfo(uuid="252060@_6MA8UI2-EeCrtavWRHwoHg")
@Table(name="abstract_request_token")
@Inheritance(strategy=javax.persistence.InheritanceType.SINGLE_TABLE)
@Entity(name="AbstractRequestToken")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING)
public class AbstractRequestToken<SME extends AbstractRequest> extends StateMachineToken<SME> {
	@ManyToOne
	@JoinColumn(name="behavior_execution_id",nullable=true)
	private AbstractRequest behaviorExecution;
	@OneToMany(mappedBy="parentToken",targetEntity=AbstractRequestToken.class)
	private Set<IToken<SME>> childTokens = new HashSet<IToken<SME>>();
	@ManyToOne(targetEntity=AbstractRequestToken.class)
	@JoinColumn(name="parent_token_id",nullable=true)
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
	
	/** Constructor for AbstractRequestToken
	 */
	public AbstractRequestToken() {
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