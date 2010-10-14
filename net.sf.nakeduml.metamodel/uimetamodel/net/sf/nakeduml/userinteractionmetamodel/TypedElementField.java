package net.sf.nakeduml.userinteractionmetamodel;

import net.sf.nakeduml.domainmetamodel.SecurityOnUserAction;
import net.sf.nakeduml.util.CompositionNode;

public class TypedElementField extends TypedElementParticipation implements CompositionNode {
	private LookupKind lookupKind;
	private SecurityOnUserAction additionalSecurityOnEdit;

	/** Default constructor for 
	 */
	public TypedElementField() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void copyState(TypedElementField from, TypedElementField to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setDisplayIndex(from.getDisplayIndex());
		to.setParticipationKind(from.getParticipationKind());
		if ( getAdditionalSecurityOnView()!=null ) {
			to.setAdditionalSecurityOnView(getAdditionalSecurityOnView().makeCopy());
		}
		if ( getAdditionalSecurityOnEdit()!=null ) {
			to.setAdditionalSecurityOnEdit(getAdditionalSecurityOnEdit().makeCopy());
		}
		to.setLookupKind(from.getLookupKind());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public SecurityOnUserAction getAdditionalSecurityOnEdit() {
		return additionalSecurityOnEdit;
	}
	
	public LookupKind getLookupKind() {
		return lookupKind;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public SecurityOnUserAction getSecurityOnEdit() {
		SecurityOnUserAction securityOnEdit = ((this.getAdditionalSecurityOnEdit() == null) ?
			this.getTypedElement().getSecurityOnEdit() :
			this.getAdditionalSecurityOnEdit());
		return securityOnEdit;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		createComponents();
	}
	
	public TypedElementField makeCopy() {
		TypedElementField result = new TypedElementField();
		copyState((TypedElementField)this,result);
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParticipationGroup()!=null ) {
			getParticipationGroup().getParticipation().remove((TypedElementField)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setAdditionalSecurityOnEdit(SecurityOnUserAction additionalSecurityOnEdit) {
		this.additionalSecurityOnEdit=additionalSecurityOnEdit;
	}
	
	public void setLookupKind(LookupKind lookupKind) {
		this.lookupKind=lookupKind;
	}
	
	public void shallowCopyState(TypedElementField from, TypedElementField to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setDisplayIndex(from.getDisplayIndex());
		to.setParticipationKind(from.getParticipationKind());
		if ( getAdditionalSecurityOnView()!=null ) {
			to.setAdditionalSecurityOnView(getAdditionalSecurityOnView().makeCopy());
		}
		if ( getAdditionalSecurityOnEdit()!=null ) {
			to.setAdditionalSecurityOnEdit(getAdditionalSecurityOnEdit().makeCopy());
		}
		to.setLookupKind(from.getLookupKind());
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
		sb.append("additionalHumanName=");
		sb.append(getAdditionalHumanName());
		sb.append(";");
		sb.append("humanName=");
		sb.append(getHumanName());
		sb.append(";");
		sb.append("qualifiedName=");
		sb.append(getQualifiedName());
		sb.append(";");
		if ( getRepresentedElement()==null ) {
			sb.append("representedElement=null;");
		} else {
			sb.append("representedElement="+getRepresentedElement().getClass().getSimpleName()+"[");
			sb.append(getRepresentedElement().getName());
			sb.append("];");
		}
		sb.append("displayIndex=");
		sb.append(getDisplayIndex());
		sb.append(";");
		if ( getParticipationGroup()==null ) {
			sb.append("participationGroup=null;");
		} else {
			sb.append("participationGroup="+getParticipationGroup().getClass().getSimpleName()+"[");
			sb.append(getParticipationGroup().getName());
			sb.append("];");
		}
		if ( getSecurityOnView()==null ) {
			sb.append("securityOnView=null;");
		} else {
			sb.append("securityOnView="+getSecurityOnView().getClass().getSimpleName()+"[");
			sb.append(getSecurityOnView().hashCode());
			sb.append("];");
		}
		sb.append("participationKind=");
		sb.append(getParticipationKind());
		sb.append(";");
		if ( getTypedElement()==null ) {
			sb.append("typedElement=null;");
		} else {
			sb.append("typedElement="+getTypedElement().getClass().getSimpleName()+"[");
			sb.append(getTypedElement().getName());
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
		if ( getSecurityOnEdit()==null ) {
			sb.append("securityOnEdit=null;");
		} else {
			sb.append("securityOnEdit="+getSecurityOnEdit().getClass().getSimpleName()+"[");
			sb.append(getSecurityOnEdit().hashCode());
			sb.append("];");
		}
		sb.append("lookupKind=");
		sb.append(getLookupKind());
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
		if ( getAdditionalHumanName()==null ) {
			sb.append("<additionalHumanName/>");
		} else {
			sb.append("<additionalHumanName>");
			sb.append(getAdditionalHumanName());
			sb.append("</additionalHumanName>");
			sb.append("\n");
		}
		if ( getRepresentedElement()==null ) {
			sb.append("<representedElement/>");
		} else {
			sb.append("<representedElement>");
			sb.append(getRepresentedElement().getClass().getSimpleName());
			sb.append("[");
			sb.append(getRepresentedElement().getName());
			sb.append("]");
			sb.append("</representedElement>");
			sb.append("\n");
		}
		if ( getDisplayIndex()==null ) {
			sb.append("<displayIndex/>");
		} else {
			sb.append("<displayIndex>");
			sb.append(getDisplayIndex());
			sb.append("</displayIndex>");
			sb.append("\n");
		}
		if ( getParticipationKind()==null ) {
			sb.append("<participationKind/>");
		} else {
			sb.append("<participationKind>");
			sb.append(getParticipationKind());
			sb.append("</participationKind>");
			sb.append("\n");
		}
		if ( getAdditionalSecurityOnView()==null ) {
			sb.append("<additionalSecurityOnView/>");
		} else {
			sb.append("<additionalSecurityOnView>");
			sb.append(getAdditionalSecurityOnView().getClass().getSimpleName());
			sb.append("[");
			sb.append(getAdditionalSecurityOnView().hashCode());
			sb.append("]");
			sb.append("</additionalSecurityOnView>");
			sb.append("\n");
		}
		if ( getAdditionalSecurityOnEdit()==null ) {
			sb.append("<additionalSecurityOnEdit/>");
		} else {
			sb.append("<additionalSecurityOnEdit>");
			sb.append(getAdditionalSecurityOnEdit().getClass().getSimpleName());
			sb.append("[");
			sb.append(getAdditionalSecurityOnEdit().hashCode());
			sb.append("]");
			sb.append("</additionalSecurityOnEdit>");
			sb.append("\n");
		}
		if ( getLookupKind()==null ) {
			sb.append("<lookupKind/>");
		} else {
			sb.append("<lookupKind>");
			sb.append(getLookupKind());
			sb.append("</lookupKind>");
			sb.append("\n");
		}
		return sb.toString();
	}

}