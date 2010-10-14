package net.sf.nakeduml.userinteractionmetamodel;

import net.sf.nakeduml.domainmetamodel.DomainTypedElement;
import net.sf.nakeduml.domainmetamodel.SecurityOnUserAction;
import net.sf.nakeduml.util.CompositionNode;

abstract public class TypedElementParticipation extends Participation implements CompositionNode {
	private SecurityOnUserAction additionalSecurityOnView;
	private TypedElementParticipationKind participationKind;

	/** Default constructor for 
	 */
	public TypedElementParticipation() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void copyState(TypedElementParticipation from, TypedElementParticipation to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setDisplayIndex(from.getDisplayIndex());
		to.setParticipationKind(from.getParticipationKind());
		if ( getAdditionalSecurityOnView()!=null ) {
			to.setAdditionalSecurityOnView(getAdditionalSecurityOnView().makeCopy());
		}
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public SecurityOnUserAction getAdditionalSecurityOnView() {
		return additionalSecurityOnView;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public TypedElementParticipationKind getParticipationKind() {
		return participationKind;
	}
	
	public SecurityOnUserAction getSecurityOnView() {
		SecurityOnUserAction securityOnView = ((this.getAdditionalSecurityOnView() == null) ?
			this.getTypedElement().getSecurityOnView() :
			this.getAdditionalSecurityOnView());
		return securityOnView;
	}
	
	public DomainTypedElement getTypedElement() {
		DomainTypedElement typedElement = ((DomainTypedElement) this.getRepresentedElement());
		return typedElement;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		createComponents();
	}
	
	abstract public TypedElementParticipation makeCopy();
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParticipationGroup()!=null ) {
			getParticipationGroup().getParticipation().remove((TypedElementParticipation)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setAdditionalSecurityOnView(SecurityOnUserAction additionalSecurityOnView) {
		this.additionalSecurityOnView=additionalSecurityOnView;
	}
	
	public void setParticipationKind(TypedElementParticipationKind participationKind) {
		this.participationKind=participationKind;
	}
	
	public void shallowCopyState(TypedElementParticipation from, TypedElementParticipation to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setDisplayIndex(from.getDisplayIndex());
		to.setParticipationKind(from.getParticipationKind());
		if ( getAdditionalSecurityOnView()!=null ) {
			to.setAdditionalSecurityOnView(getAdditionalSecurityOnView().makeCopy());
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
		return sb.toString();
	}

}