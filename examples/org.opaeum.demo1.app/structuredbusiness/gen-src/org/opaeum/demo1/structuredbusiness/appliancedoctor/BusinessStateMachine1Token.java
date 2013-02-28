package org.opaeum.demo1.structuredbusiness.appliancedoctor;

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

@NumlMetaInfo(uuid="914890@_cTMn8H2lEeK5F45wEGRv4A")
@Table(name="business_state_machine1_token",schema="structuredbusiness")
@Inheritance(strategy=javax.persistence.InheritanceType.SINGLE_TABLE)
@Entity(name="BusinessStateMachine1Token")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING)
public class BusinessStateMachine1Token<SME extends BusinessStateMachine1> extends StateMachineToken<SME> {
	@ManyToOne
	@JoinColumn(name="behavior_execution_id",nullable=true)
	private BusinessStateMachine1 behaviorExecution;
	@OneToMany(mappedBy="parentToken",targetEntity=BusinessStateMachine1Token.class)
	private Set<IToken<SME>> childTokens = new HashSet<IToken<SME>>();
	@ManyToOne(targetEntity=BusinessStateMachine1Token.class)
	@JoinColumn(name="parent_token_id",nullable=true)
	private IToken<SME> parentToken;

	/** Constructor for BusinessStateMachine1Token
	 * 
	 * @param parentToken 
	 */
	public BusinessStateMachine1Token(BusinessStateMachine1Token parentToken) {
		this.parentToken=parentToken;
		if ( parentToken!=null ) {
			parentToken.getChildTokens().add(this);
		}
	}
	
	/** Constructor for BusinessStateMachine1Token
	 */
	public BusinessStateMachine1Token() {
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
	
	public void setBehaviorExecution(BusinessStateMachine1 behaviorExecution) {
		this.behaviorExecution=behaviorExecution;
	}
	
	public void setChildTokens(Set<IToken<SME>> childTokens) {
		this.childTokens=childTokens;
	}
	
	public void setParentToken(IToken<SME> parentToken) {
		this.parentToken=parentToken;
	}

}