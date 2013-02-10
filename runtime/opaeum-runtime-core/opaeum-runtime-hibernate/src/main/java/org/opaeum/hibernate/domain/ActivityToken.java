package org.opaeum.hibernate.domain;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.opaeum.runtime.activities.ActivityEdgeInstance;
import org.opaeum.runtime.activities.ActivityNodeActivation;
import org.opaeum.runtime.activities.IActivityNodeContainerExecution;
import org.opaeum.runtime.activities.IActivityToken;
import org.opaeum.runtime.activities.TokenKind;
import org.opaeum.runtime.persistence.AbstractPersistence;

@MappedSuperclass
public abstract class ActivityToken <AE extends IActivityNodeContainerExecution> extends AbstractToken<AE> implements IActivityToken<AE>{
	private static final long serialVersionUID = 3975628105784467069L;
	private IActivityToken<AE> baseToken;
	private TokenKind kind;
	private String holderId;
	private String offeredToId;
	@Embedded
	private Value value;
	@Transient
	private AbstractPersistence persistence;
	private int remainingOffersCount;
	protected ActivityToken(){
		
	}
	protected ActivityToken(AbstractPersistence p){
		this.persistence=p;
	}
	protected abstract IActivityNodeContainerExecution getBehaviorExeution();
	protected abstract IActivityToken<AE> createNewToken();
	@SuppressWarnings("unchecked")
	public ActivityNodeActivation<AE,?> getHolder(){
		return (ActivityNodeActivation<AE,?>) getBehaviorExeution().getActivityNodeActivation(holderId);
	}
	@SuppressWarnings("unchecked")
	public ActivityEdgeInstance<AE,?> getOfferedTo(){
		return (ActivityEdgeInstance<AE,?>) getBehaviorExeution().getActivityEdgeInstance(offeredToId);
	}
	public IActivityToken<AE> transfer(ActivityNodeActivation<AE,?> holder){
		// if this token does not have any holder, make the given holder its
		// holder.
		// Otherwise, remove this token from its holder and return a copy of it
		// transfered to a new holder.
		if(this.getHolder() != null){
			this.withdraw();
		}
		this.setHolder(holder);
		this.holderId = holder.getId();
		return (IActivityToken<AE>) this;
	}
	public void withdraw(){
		if(!this.isWithdrawn()){
			if(this.getBaseToken() != null){
				if(!this.getBaseToken().isWithdrawn()){
					this.getBaseToken().withdraw();
				}
				if(this.getRemainingOffersCount() > 0){
					this.setRemainingOffersCount(this.getRemainingOffersCount() - 1);
				}
				if(this.getRemainingOffersCount() == 0){
					this.getHolder().removeToken(this);
					this.setHolder(null);
				}
			}else{
				this.getHolder().removeToken(this);
				this.setHolder(null);
			}
		}
	}
	public IActivityToken<AE> copy(){
		if(getBaseToken() != null){
			return ((ActivityToken<AE>) getBaseToken()).copy();
		}else{
			ActivityToken<AE> result = (ActivityToken<AE>) createNewToken();
			result.value = value;
			result.setKind(kind);
			return result;
		}
	}
	public boolean isWithdrawn(){
		return this.holderId == null;
	}
	public boolean isControl(){
		if(getBaseToken() != null){
			return getBaseToken().isControl();
		}else{
			return getKind() == TokenKind.CONTROl;
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
	public void offeredTo(ActivityEdgeInstance<AE,?> activityEdgeInstance){
		offeredToId = activityEdgeInstance.getId();
	}
	public boolean isOffered(){
		return offeredToId != null;
	}
	public void setValue(Object value2){
		if(this.value == null){
			value = new Value();
		}
		value.setValue(value2,persistence.getMetaInfoMap());
	}
	public boolean hasValue(){
		return value != null && value.hasValue();
	}
	public void setHolder(ActivityNodeActivation<AE,?> holder){
		this.holderId = holder.getId();
	}
	public  IActivityToken<AE> getBaseToken(){
		return baseToken;
	}
	public void setBaseToken(IActivityToken<AE> baseToken){
		this.baseToken = baseToken;
	}
	public TokenKind getKind(){
		return kind;
	}
	public void setKind(TokenKind kind){
		this.kind = kind;
	}
	@Override
	public int getRemainingOffersCount(){
		return remainingOffersCount;
	}
	@Override
	public void setRemainingOffersCount(int remainingOffersCount){
		this.remainingOffersCount = remainingOffersCount;
	}
}
