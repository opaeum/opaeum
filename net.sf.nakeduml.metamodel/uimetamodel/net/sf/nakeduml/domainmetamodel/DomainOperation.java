package net.sf.nakeduml.domainmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.util.CompositionNode;

public class DomainOperation extends DomainElement implements CompositionNode {
	private DomainEntity entity;
	private Set<DomainParameter> parameter = new HashSet<DomainParameter>();
	private Boolean isQuery = false;
	private SecurityOnUserAction additionalSecurityOnInvoke;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public DomainOperation(DomainEntity owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public DomainOperation() {
	}

	public void addAllToParameter(Set<DomainParameter> parameter) {
		for ( DomainParameter o : parameter ) {
			addToParameter(o);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getEntity().getOperation().add((DomainOperation)this);
	}
	
	public void addToParameter(DomainParameter parameter) {
		parameter.setOperation(this);
	}
	
	public void clearParameter() {
		removeAllFromParameter(getParameter());
	}
	
	public void copyState(DomainOperation from, DomainOperation to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
		to.setQuery(from.isQuery());
		for ( DomainParameter child : getParameter() ) {
			to.addToParameter(child.makeCopy());
		}
		if ( getAdditionalSecurityOnInvoke()!=null ) {
			to.setAdditionalSecurityOnInvoke(getAdditionalSecurityOnInvoke().makeCopy());
		}
	}
	
	public SecurityOnUserAction createAdditionalSecurityOnInvoke() {
		SecurityOnUserAction newInstance= new SecurityOnUserAction();
		return newInstance;
	}
	
	public DomainParameter createParameter() {
		DomainParameter newInstance= new DomainParameter();
		newInstance.init(this);
		return newInstance;
	}
	
	public SecurityOnUserAction getAdditionalSecurityOnInvoke() {
		return additionalSecurityOnInvoke;
	}
	
	public DomainEntity getEntity() {
		return entity;
	}
	
	public Set<DomainElement> getOwnedElement() {
		Set<DomainElement> ownedElementSubsetting = new HashSet<DomainElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		ownedElementSubsetting.addAll(getParameter());
		return ownedElementSubsetting;
	}
	
	public DomainElement getOwner() {
		DomainElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getEntity()!=null ) {
			ownerSubsetting=getEntity();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getEntity();
	}
	
	public Set<DomainParameter> getParameter() {
		return parameter;
	}
	
	public DomainParameter getReturnParameter() {
		DomainParameter returnParameter = any1();
		return returnParameter;
	}
	
	public SecurityOnUserAction getSecurityOnInvoke() {
		SecurityOnUserAction securityOnInvoke = ((this.getAdditionalSecurityOnInvoke() == null) && (!(this.getReturnParameter() == null)) ?
			this.getReturnParameter().getType().getSecurityOnView() :
			this.getAdditionalSecurityOnInvoke());
		return securityOnInvoke;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((DomainEntity)owner);
		createComponents();
	}
	
	public Boolean isQuery() {
		return isQuery;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getEntity()!=null ) {
			getEntity().getOperation().remove((DomainOperation)this);
		}
		for ( DomainParameter child : new ArrayList<DomainParameter>(getParameter()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromParameter(Set<DomainParameter> parameter) {
		for ( DomainParameter o : parameter ) {
			removeFromParameter(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromParameter(DomainParameter parameter) {
		parameter.setOperation(null);
	}
	
	public void setAdditionalSecurityOnInvoke(SecurityOnUserAction additionalSecurityOnInvoke) {
		this.additionalSecurityOnInvoke=additionalSecurityOnInvoke;
	}
	
	public void setEntity(DomainEntity entity) {
		if ( this.entity!=null ) {
			this.entity.getOperation().remove((DomainOperation)this);
		}
		if ( entity!=null ) {
			entity.getOperation().add((DomainOperation)this);
			this.entity=entity;
		} else {
			this.entity=null;
		}
	}
	
	public void setParameter(Set<DomainParameter> parameter) {
		for ( DomainParameter o : new HashSet<DomainParameter>(this.parameter) ) {
			o.setOperation(null);
		}
		for ( DomainParameter o : parameter ) {
			o.setOperation((DomainOperation)this);
		}
	}
	
	public void setQuery(Boolean isQuery) {
		this.isQuery=isQuery;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if ( getOwner()==null ) {
			sb.append("owner=null;");
		} else {
			sb.append("owner="+getOwner().getClass().getSimpleName()+"[");
			sb.append(getOwner().getName());
			sb.append("];");
		}
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		sb.append("humanName=");
		sb.append(getHumanName());
		sb.append(";");
		sb.append("qualifiedName=");
		sb.append(getQualifiedName());
		sb.append(";");
		sb.append("isQuery=");
		sb.append(isQuery());
		sb.append(";");
		if ( getReturnParameter()==null ) {
			sb.append("returnParameter=null;");
		} else {
			sb.append("returnParameter="+getReturnParameter().getClass().getSimpleName()+"[");
			sb.append(getReturnParameter().getName());
			sb.append("];");
		}
		if ( getSecurityOnInvoke()==null ) {
			sb.append("securityOnInvoke=null;");
		} else {
			sb.append("securityOnInvoke="+getSecurityOnInvoke().getClass().getSimpleName()+"[");
			sb.append(getSecurityOnInvoke().hashCode());
			sb.append("];");
		}
		if ( getEntity()==null ) {
			sb.append("entity=null;");
		} else {
			sb.append("entity="+getEntity().getClass().getSimpleName()+"[");
			sb.append(getEntity().getName());
			sb.append("];");
		}
		if ( getAdditionalSecurityOnInvoke()==null ) {
			sb.append("additionalSecurityOnInvoke=null;");
		} else {
			sb.append("additionalSecurityOnInvoke="+getAdditionalSecurityOnInvoke().getClass().getSimpleName()+"[");
			sb.append(getAdditionalSecurityOnInvoke().hashCode());
			sb.append("];");
		}
		return sb.toString();
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		if ( getName()==null ) {
			sb.append("<name/>");
		} else {
			sb.append("<name>");
			sb.append(getName());
			sb.append("</name>");
			sb.append("\n");
		}
		if ( getHumanName()==null ) {
			sb.append("<humanName/>");
		} else {
			sb.append("<humanName>");
			sb.append(getHumanName());
			sb.append("</humanName>");
			sb.append("\n");
		}
		if ( isQuery()==null ) {
			sb.append("<isQuery/>");
		} else {
			sb.append("<isQuery>");
			sb.append(isQuery());
			sb.append("</isQuery>");
			sb.append("\n");
		}
		for ( DomainParameter parameter : getParameter() ) {
			sb.append("<parameter>");
			sb.append(parameter.toXmlString());
			sb.append("</parameter>");
			sb.append("\n");
		}
		if ( getEntity()==null ) {
			sb.append("<entity/>");
		} else {
			sb.append("<entity>");
			sb.append(getEntity().getClass().getSimpleName());
			sb.append("[");
			sb.append(getEntity().getName());
			sb.append("]");
			sb.append("</entity>");
			sb.append("\n");
		}
		if ( getAdditionalSecurityOnInvoke()==null ) {
			sb.append("<additionalSecurityOnInvoke/>");
		} else {
			sb.append("<additionalSecurityOnInvoke>");
			sb.append(getAdditionalSecurityOnInvoke().toXmlString());
			sb.append("</additionalSecurityOnInvoke>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Implements ->any( i_DomainParameter : DomainParameter | i_DomainParameter.direction = ParameterDirection::return )
	 */
	private DomainParameter any1() {
		DomainParameter result = null;
		for ( DomainParameter i_DomainParameter : this.getParameter() ) {
			if ( (i_DomainParameter.getDirection().equals( ParameterDirection.RETURN)) ) {
				return i_DomainParameter;
			}
		}
		return result;
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(DomainEntity newOwner) {
		this.entity=newOwner;
	}
	
	public void createComponents() {
		super.createComponents();
		if ( getAdditionalSecurityOnInvoke()==null ) {
			setAdditionalSecurityOnInvoke(new SecurityOnUserAction());
		}
	}
	
	public DomainOperation makeCopy() {
		DomainOperation result = new DomainOperation();
		copyState((DomainOperation)this,result);
		return result;
	}
	
	public void shallowCopyState(DomainOperation from, DomainOperation to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
		to.setQuery(from.isQuery());
		if ( getAdditionalSecurityOnInvoke()!=null ) {
			to.setAdditionalSecurityOnInvoke(getAdditionalSecurityOnInvoke().makeCopy());
		}
	}

}