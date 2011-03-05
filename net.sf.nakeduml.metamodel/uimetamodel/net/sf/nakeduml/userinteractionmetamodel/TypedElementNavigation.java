package net.sf.nakeduml.userinteractionmetamodel;

import org.nakeduml.runtime.domain.CompositionNode;

abstract public class TypedElementNavigation extends TypedElementParticipation implements CompositionNode, Navigation {
	private ClassifierUserInteraction resultingUserInteraction;

	/** Default constructor for 
	 */
	public TypedElementNavigation() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void copyState(TypedElementNavigation from, TypedElementNavigation to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setDisplayIndex(from.getDisplayIndex());
		to.setParticipationKind(from.getParticipationKind());
		if ( getAdditionalSecurityOnView()!=null ) {
			to.setAdditionalSecurityOnView(getAdditionalSecurityOnView().makeCopy());
		}
		to.setResultingUserInteraction(getResultingUserInteraction());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public TypedElementParticipationKind getParticipationKind() {
		TypedElementParticipationKind participationKind = TypedElementParticipationKind.NAVIGATION;
		return participationKind;
	}
	
	public ClassifierUserInteraction getResultingUserInteraction() {
		return resultingUserInteraction;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		createComponents();
	}
	
	abstract public TypedElementNavigation makeCopy();
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParticipationGroup()!=null ) {
			getParticipationGroup().getParticipation().remove((TypedElementNavigation)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setResultingUserInteraction(ClassifierUserInteraction resultingUserInteraction) {
		this.resultingUserInteraction=resultingUserInteraction;
	}
	
	public void shallowCopyState(TypedElementNavigation from, TypedElementNavigation to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setDisplayIndex(from.getDisplayIndex());
		to.setParticipationKind(from.getParticipationKind());
		if ( getAdditionalSecurityOnView()!=null ) {
			to.setAdditionalSecurityOnView(getAdditionalSecurityOnView().makeCopy());
		}
		to.setResultingUserInteraction(getResultingUserInteraction());
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
		if ( getSecurityOnView()==null ) {
			sb.append("securityOnView=null;");
		} else {
			sb.append("securityOnView="+getSecurityOnView().getClass().getSimpleName()+"[");
			sb.append(getSecurityOnView().hashCode());
			sb.append("];");
		}
		if ( getResultingUserInteraction()==null ) {
			sb.append("resultingUserInteraction=null;");
		} else {
			sb.append("resultingUserInteraction="+getResultingUserInteraction().getClass().getSimpleName()+"[");
			sb.append(getResultingUserInteraction().getName());
			sb.append("];");
		}
		if ( getResultingUserInteraction()==null ) {
			sb.append("resultingUserInteraction=null;");
		} else {
			sb.append("resultingUserInteraction="+getResultingUserInteraction().getClass().getSimpleName()+"[");
			sb.append(getResultingUserInteraction().getName());
			sb.append("];");
		}
		sb.append("participationKind=");
		sb.append(getParticipationKind());
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
		if ( getResultingUserInteraction()==null ) {
			sb.append("<resultingUserInteraction/>");
		} else {
			sb.append("<resultingUserInteraction>");
			sb.append(getResultingUserInteraction().getClass().getSimpleName());
			sb.append("[");
			sb.append(getResultingUserInteraction().getName());
			sb.append("]");
			sb.append("</resultingUserInteraction>");
			sb.append("\n");
		}
		return sb.toString();
	}

}