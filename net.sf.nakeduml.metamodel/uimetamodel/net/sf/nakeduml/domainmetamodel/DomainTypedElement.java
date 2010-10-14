package net.sf.nakeduml.domainmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class DomainTypedElement extends DomainElement implements CompositionNode {
	private Integer upperLimit = 0;
	private SecurityOnUserAction additionalSecurityOnAdd;
	private DomainClassifier type;
	private SecurityOnUserAction additionalSecurityOnEdit;
	private Integer lowerLimit = 0;
	private SecurityOnUserAction additionalSecurityOnView;

	/** Default constructor for 
	 */
	public DomainTypedElement() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void copyState(DomainTypedElement from, DomainTypedElement to) {
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
	}
	
	public SecurityOnUserAction createAdditionalSecurityOnAdd() {
		SecurityOnUserAction newInstance= new SecurityOnUserAction();
		return newInstance;
	}
	
	public SecurityOnUserAction createAdditionalSecurityOnEdit() {
		SecurityOnUserAction newInstance= new SecurityOnUserAction();
		return newInstance;
	}
	
	public SecurityOnUserAction createAdditionalSecurityOnView() {
		SecurityOnUserAction newInstance= new SecurityOnUserAction();
		return newInstance;
	}
	
	public void createComponents() {
		super.createComponents();
		if ( getAdditionalSecurityOnView()==null ) {
			setAdditionalSecurityOnView(new SecurityOnUserAction());
		}
		if ( getAdditionalSecurityOnEdit()==null ) {
			setAdditionalSecurityOnEdit(new SecurityOnUserAction());
		}
		if ( getAdditionalSecurityOnAdd()==null ) {
			setAdditionalSecurityOnAdd(new SecurityOnUserAction());
		}
	}
	
	public SecurityOnUserAction getAdditionalSecurityOnAdd() {
		return additionalSecurityOnAdd;
	}
	
	public SecurityOnUserAction getAdditionalSecurityOnEdit() {
		return additionalSecurityOnEdit;
	}
	
	public SecurityOnUserAction getAdditionalSecurityOnView() {
		return additionalSecurityOnView;
	}
	
	public Integer getLowerLimit() {
		return lowerLimit;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public SecurityOnUserAction getSecurityOnAdd() {
		SecurityOnUserAction securityOnAdd = ((this.getAdditionalSecurityOnAdd() == null) ?
			this.getType().getSecurityOnAdd() :
			this.getAdditionalSecurityOnAdd());
		return securityOnAdd;
	}
	
	public SecurityOnUserAction getSecurityOnEdit() {
		SecurityOnUserAction securityOnEdit = ((this.getAdditionalSecurityOnEdit() == null) ?
			this.getType().getSecurityOnEdit() :
			this.getAdditionalSecurityOnEdit());
		return securityOnEdit;
	}
	
	public SecurityOnUserAction getSecurityOnView() {
		SecurityOnUserAction securityOnView = ((this.getAdditionalSecurityOnView() == null) ?
			this.getType().getSecurityOnView() :
			this.getAdditionalSecurityOnView());
		return securityOnView;
	}
	
	public DomainClassifier getType() {
		return type;
	}
	
	public Integer getUpperLimit() {
		return upperLimit;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		createComponents();
	}
	
	public DomainTypedElement makeCopy() {
		DomainTypedElement result = new DomainTypedElement();
		copyState((DomainTypedElement)this,result);
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setAdditionalSecurityOnAdd(SecurityOnUserAction additionalSecurityOnAdd) {
		this.additionalSecurityOnAdd=additionalSecurityOnAdd;
	}
	
	public void setAdditionalSecurityOnEdit(SecurityOnUserAction additionalSecurityOnEdit) {
		this.additionalSecurityOnEdit=additionalSecurityOnEdit;
	}
	
	public void setAdditionalSecurityOnView(SecurityOnUserAction additionalSecurityOnView) {
		this.additionalSecurityOnView=additionalSecurityOnView;
	}
	
	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit=lowerLimit;
	}
	
	public void setType(DomainClassifier type) {
		this.type=type;
	}
	
	public void setUpperLimit(Integer upperLimit) {
		this.upperLimit=upperLimit;
	}
	
	public void shallowCopyState(DomainTypedElement from, DomainTypedElement to) {
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
		return sb.toString();
	}

}