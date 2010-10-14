package net.sf.nakeduml.domainmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class DomainParameter extends DomainTypedElement implements CompositionNode {
	private ParameterDirection direction;
	private DomainOperation operation;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public DomainParameter(DomainOperation owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public DomainParameter() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getOperation().getParameter().add((DomainParameter)this);
	}
	
	public ParameterDirection getDirection() {
		return direction;
	}
	
	public DomainOperation getOperation() {
		return operation;
	}
	
	public DomainElement getOwner() {
		DomainElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getOperation()!=null ) {
			ownerSubsetting=getOperation();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getOperation();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((DomainOperation)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getOperation()!=null ) {
			getOperation().getParameter().remove((DomainParameter)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setDirection(ParameterDirection direction) {
		this.direction=direction;
	}
	
	public void setOperation(DomainOperation operation) {
		if ( this.operation!=null ) {
			this.operation.getParameter().remove((DomainParameter)this);
		}
		if ( operation!=null ) {
			operation.getParameter().add((DomainParameter)this);
			this.operation=operation;
		} else {
			this.operation=null;
		}
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
		if ( getType()==null ) {
			sb.append("type=null;");
		} else {
			sb.append("type="+getType().getClass().getSimpleName()+"[");
			sb.append(getType().getName());
			sb.append("];");
		}
		if ( getAdditionalSecurityOnView()==null ) {
			sb.append("additionalSecurityOnView=null;");
		} else {
			sb.append("additionalSecurityOnView="+getAdditionalSecurityOnView().getClass().getSimpleName()+"[");
			sb.append(getAdditionalSecurityOnView().hashCode());
			sb.append("];");
		}
		if ( getAdditionalSecurityOnEdit()==null ) {
			sb.append("additionalSecurityOnEdit=null;");
		} else {
			sb.append("additionalSecurityOnEdit="+getAdditionalSecurityOnEdit().getClass().getSimpleName()+"[");
			sb.append(getAdditionalSecurityOnEdit().hashCode());
			sb.append("];");
		}
		sb.append("upperLimit=");
		sb.append(getUpperLimit());
		sb.append(";");
		sb.append("lowerLimit=");
		sb.append(getLowerLimit());
		sb.append(";");
		if ( getSecurityOnEdit()==null ) {
			sb.append("securityOnEdit=null;");
		} else {
			sb.append("securityOnEdit="+getSecurityOnEdit().getClass().getSimpleName()+"[");
			sb.append(getSecurityOnEdit().hashCode());
			sb.append("];");
		}
		if ( getSecurityOnView()==null ) {
			sb.append("securityOnView=null;");
		} else {
			sb.append("securityOnView="+getSecurityOnView().getClass().getSimpleName()+"[");
			sb.append(getSecurityOnView().hashCode());
			sb.append("];");
		}
		if ( getAdditionalSecurityOnAdd()==null ) {
			sb.append("additionalSecurityOnAdd=null;");
		} else {
			sb.append("additionalSecurityOnAdd="+getAdditionalSecurityOnAdd().getClass().getSimpleName()+"[");
			sb.append(getAdditionalSecurityOnAdd().hashCode());
			sb.append("];");
		}
		if ( getSecurityOnAdd()==null ) {
			sb.append("securityOnAdd=null;");
		} else {
			sb.append("securityOnAdd="+getSecurityOnAdd().getClass().getSimpleName()+"[");
			sb.append(getSecurityOnAdd().hashCode());
			sb.append("];");
		}
		if ( getOperation()==null ) {
			sb.append("operation=null;");
		} else {
			sb.append("operation="+getOperation().getClass().getSimpleName()+"[");
			sb.append(getOperation().getName());
			sb.append("];");
		}
		sb.append("direction=");
		sb.append(getDirection());
		sb.append(";");
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
		if ( getType()==null ) {
			sb.append("<type/>");
		} else {
			sb.append("<type>");
			sb.append(getType().getClass().getSimpleName());
			sb.append("[");
			sb.append(getType().getName());
			sb.append("]");
			sb.append("</type>");
			sb.append("\n");
		}
		if ( getAdditionalSecurityOnView()==null ) {
			sb.append("<additionalSecurityOnView/>");
		} else {
			sb.append("<additionalSecurityOnView>");
			sb.append(getAdditionalSecurityOnView().toXmlString());
			sb.append("</additionalSecurityOnView>");
			sb.append("\n");
		}
		if ( getAdditionalSecurityOnEdit()==null ) {
			sb.append("<additionalSecurityOnEdit/>");
		} else {
			sb.append("<additionalSecurityOnEdit>");
			sb.append(getAdditionalSecurityOnEdit().toXmlString());
			sb.append("</additionalSecurityOnEdit>");
			sb.append("\n");
		}
		if ( getUpperLimit()==null ) {
			sb.append("<upperLimit/>");
		} else {
			sb.append("<upperLimit>");
			sb.append(getUpperLimit());
			sb.append("</upperLimit>");
			sb.append("\n");
		}
		if ( getLowerLimit()==null ) {
			sb.append("<lowerLimit/>");
		} else {
			sb.append("<lowerLimit>");
			sb.append(getLowerLimit());
			sb.append("</lowerLimit>");
			sb.append("\n");
		}
		if ( getAdditionalSecurityOnAdd()==null ) {
			sb.append("<additionalSecurityOnAdd/>");
		} else {
			sb.append("<additionalSecurityOnAdd>");
			sb.append(getAdditionalSecurityOnAdd().toXmlString());
			sb.append("</additionalSecurityOnAdd>");
			sb.append("\n");
		}
		if ( getOperation()==null ) {
			sb.append("<operation/>");
		} else {
			sb.append("<operation>");
			sb.append(getOperation().getClass().getSimpleName());
			sb.append("[");
			sb.append(getOperation().getName());
			sb.append("]");
			sb.append("</operation>");
			sb.append("\n");
		}
		if ( getDirection()==null ) {
			sb.append("<direction/>");
		} else {
			sb.append("<direction>");
			sb.append(getDirection());
			sb.append("</direction>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(DomainOperation newOwner) {
		this.operation=newOwner;
	}
	
	public void copyState(DomainParameter from, DomainParameter to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
		to.setType(getType());
		if ( getAdditionalSecurityOnView()!=null ) {
			to.setAdditionalSecurityOnView(getAdditionalSecurityOnView().makeCopy());
		}
		if ( getAdditionalSecurityOnEdit()!=null ) {
			to.setAdditionalSecurityOnEdit(getAdditionalSecurityOnEdit().makeCopy());
		}
		to.setUpperLimit(from.getUpperLimit());
		to.setLowerLimit(from.getLowerLimit());
		if ( getAdditionalSecurityOnAdd()!=null ) {
			to.setAdditionalSecurityOnAdd(getAdditionalSecurityOnAdd().makeCopy());
		}
		to.setDirection(from.getDirection());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public DomainParameter makeCopy() {
		DomainParameter result = new DomainParameter();
		copyState((DomainParameter)this,result);
		return result;
	}
	
	public void shallowCopyState(DomainParameter from, DomainParameter to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
		to.setType(getType());
		if ( getAdditionalSecurityOnView()!=null ) {
			to.setAdditionalSecurityOnView(getAdditionalSecurityOnView().makeCopy());
		}
		if ( getAdditionalSecurityOnEdit()!=null ) {
			to.setAdditionalSecurityOnEdit(getAdditionalSecurityOnEdit().makeCopy());
		}
		to.setUpperLimit(from.getUpperLimit());
		to.setLowerLimit(from.getLowerLimit());
		if ( getAdditionalSecurityOnAdd()!=null ) {
			to.setAdditionalSecurityOnAdd(getAdditionalSecurityOnAdd().makeCopy());
		}
		to.setDirection(from.getDirection());
	}

}