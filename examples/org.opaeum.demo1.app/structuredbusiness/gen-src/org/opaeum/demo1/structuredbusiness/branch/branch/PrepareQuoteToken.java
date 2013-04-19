package org.opaeum.demo1.structuredbusiness.branch.branch;

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

@NumlMetaInfo(uuid="914890@_HHdlgBYUEeKsDbmQL25eBw")
@Table(name="prepare_quote_token",schema="structuredbusiness")
@Inheritance(strategy=javax.persistence.InheritanceType.SINGLE_TABLE)
@Entity(name="PrepareQuoteToken")
@DiscriminatorColumn(discriminatorType=javax.persistence.DiscriminatorType.STRING)
public class PrepareQuoteToken<SME extends PrepareQuote> extends StateMachineToken<SME> {
	@ManyToOne
	@JoinColumn(name="behavior_execution_id",nullable=true)
	private PrepareQuote behaviorExecution;
	@OneToMany(mappedBy="parentToken",targetEntity=PrepareQuoteToken.class)
	private Set<IToken<SME>> childTokens = new HashSet<IToken<SME>>();
	@ManyToOne(targetEntity=PrepareQuoteToken.class)
	@JoinColumn(name="parent_token_id",nullable=true)
	private IToken<SME> parentToken;

	/** Constructor for PrepareQuoteToken
	 * 
	 * @param parentToken 
	 */
	public PrepareQuoteToken(PrepareQuoteToken parentToken) {
		this.parentToken=parentToken;
		if ( parentToken!=null ) {
			parentToken.getChildTokens().add(this);
		}
	}
	
	/** Constructor for PrepareQuoteToken
	 */
	public PrepareQuoteToken() {
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
	
	public void setBehaviorExecution(PrepareQuote behaviorExecution) {
		this.behaviorExecution=behaviorExecution;
	}
	
	public void setChildTokens(Set<IToken<SME>> childTokens) {
		this.childTokens=childTokens;
	}
	
	public void setParentToken(IToken<SME> parentToken) {
		this.parentToken=parentToken;
	}

}