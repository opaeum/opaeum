package net.sf.nakeduml.domainmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class DomainProperty extends DomainTypedElement implements CompositionNode {
	private DomainClassifier classifier;
	private Boolean isComposite = false;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public DomainProperty(DomainClassifier owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public DomainProperty() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getClassifier().getProperty().add((DomainProperty)this);
	}
	
	public DomainClassifier getClassifier() {
		return classifier;
	}
	
	public DomainElement getOwner() {
		DomainElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getClassifier()!=null ) {
			ownerSubsetting=getClassifier();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getClassifier();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((DomainClassifier)owner);
		createComponents();
	}
	
	public Boolean isComposite() {
		return isComposite;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getClassifier()!=null ) {
			getClassifier().getProperty().remove((DomainProperty)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setClassifier(DomainClassifier classifier) {
		if ( this.classifier!=null ) {
			this.classifier.getProperty().remove((DomainProperty)this);
		}
		if ( classifier!=null ) {
			classifier.getProperty().add((DomainProperty)this);
			this.classifier=classifier;
		} else {
			this.classifier=null;
		}
	}
	
	public void setComposite(Boolean isComposite) {
		this.isComposite=isComposite;
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
		sb.append("isComposite=");
		sb.append(isComposite());
		sb.append(";");
		if ( getClassifier()==null ) {
			sb.append("classifier=null;");
		} else {
			sb.append("classifier="+getClassifier().getClass().getSimpleName()+"[");
			sb.append(getClassifier().getName());
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
		if ( isComposite()==null ) {
			sb.append("<isComposite/>");
		} else {
			sb.append("<isComposite>");
			sb.append(isComposite());
			sb.append("</isComposite>");
			sb.append("\n");
		}
		if ( getClassifier()==null ) {
			sb.append("<classifier/>");
		} else {
			sb.append("<classifier>");
			sb.append(getClassifier().getClass().getSimpleName());
			sb.append("[");
			sb.append(getClassifier().getName());
			sb.append("]");
			sb.append("</classifier>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(DomainClassifier newOwner) {
		this.classifier=newOwner;
	}
	
	public void copyState(DomainProperty from, DomainProperty to) {
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
		to.setComposite(from.isComposite());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public DomainProperty makeCopy() {
		DomainProperty result = new DomainProperty();
		copyState((DomainProperty)this,result);
		return result;
	}
	
	public void shallowCopyState(DomainProperty from, DomainProperty to) {
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
		to.setComposite(from.isComposite());
	}

}