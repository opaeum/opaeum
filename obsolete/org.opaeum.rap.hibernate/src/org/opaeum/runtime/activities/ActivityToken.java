package org.opaeum.runtime.activities;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.opaeum.hibernate.domain.AbstractToken;
import org.opaeum.runtime.persistence.AbstractPersistence;

@MappedSuperclass
public abstract class ActivityToken extends AbstractToken{
	ActivityToken baseToken;
	TokenKind kind;
	private String holderId;
	private String offeredToId;
	@Embedded
	private Value value;
	public ActivityNodeActivation holder = null;
	@Transient
	private AbstractPersistence persistence;
	public int remainingOffersCount;
	protected abstract IActivityNodeContainerExecution getBehaviorExeution();
	protected abstract ActivityToken createNewToken();
	public ActivityNodeActivation getHolder(){
		return getBehaviorExeution().getActivityNodeActivation(holderId);
	}
	public ActivityEdgeInstance getOfferedTo(){
		return getBehaviorExeution().getActivityEdgeInstance(offeredToId);
	}
	public ActivityToken transfer(ActivityNodeActivation holder){
		// if this token does not have any holder, make the given holder its
		// holder.
		// Otherwise, remove this token from its holder and return a copy of it
		// transfered to a new holder.
		if(this.holder != null){
			this.withdraw();
		}
		this.holder = holder;
		this.holderId = holder.getId();
		return this;
	}
	public void withdraw(){
		if(!this.isWithdrawn()){
			if(this.baseToken != null){
				if(!this.baseToken.isWithdrawn()){
					this.baseToken.withdraw();
				}
				if(this.remainingOffersCount > 0){
					this.remainingOffersCount = this.remainingOffersCount - 1;
				}
				if(this.remainingOffersCount == 0){
					this.holder.removeToken(this);
					this.holder = null;
				}
			}else{
				this.holder.removeToken(this);
				this.holder = null;
			}
		}
	}
	public ActivityToken copy(){
		if(baseToken != null){
			return baseToken.copy();
		}else{
			ActivityToken result = createNewToken();
			result.value = value;
			result.kind = kind;
			return result;
		}
	}
	public boolean isWithdrawn(){
		return this.holderId == null;
	}
	public boolean isControl(){
		if(baseToken != null){
			return baseToken.isControl();
		}else{
			return kind == TokenKind.CONTROl;
		}
	}
	public Object getValue(){
		if(value == null){
			return null;
		}else{
			return value.getValue(persistence);
		}
	}
	public void unOffer(){
		offeredToId = null;
	}
	public void offeredTo(ActivityEdgeInstance activityEdgeInstance){
		offeredToId = activityEdgeInstance.getId();
	}
	public boolean isOffered(){
		return offeredToId != null;
	}
	public void setValue(Object value2){
		if(this.value == null){
			value = new Value();
		}
		value.setValue(value2);
	}
	public boolean hasValue(){
		return value != null && value.hasValue();
	}
}
