package net.sf.nakeduml.userinteractionmetamodel;

import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.domainmetamodel.DomainProperty;
import net.sf.nakeduml.util.CompositionNode;
import util.Stdlib;

public class PropertyNavigation extends TypedElementNavigation implements CompositionNode {
	private ClassifierUserInteraction classifierUserInteraction;
	private ParticipationGroup participationGroup;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public PropertyNavigation(ClassifierUserInteraction owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public PropertyNavigation() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getClassifierUserInteraction().getOwnedPropertyNavigation().add((PropertyNavigation)this);
	}
	
	public ClassifierUserInteraction getClassifierUserInteraction() {
		return classifierUserInteraction;
	}
	
	public UserInteractionElement getOwner() {
		UserInteractionElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getClassifierUserInteraction()!=null ) {
			ownerSubsetting=getClassifierUserInteraction();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getClassifierUserInteraction();
	}
	
	public ParticipationGroup getParticipationGroup() {
		ParticipationGroup participationGroupSubsetting = null;
		if ( this.participationGroup!=null ) {
			participationGroupSubsetting=this.participationGroup;
		}
		return participationGroupSubsetting;
	}
	
	public Set<ParticipationGroup> getParticipationGroupSourcePopulation() {
		return new HashSet<ParticipationGroup>(Stdlib.collectionAsSet(this.getClassifierUserInteraction().getParticipationGroup()));
	}
	
	public DomainProperty getProperty() {
		DomainProperty property = ((DomainProperty) this.getRepresentedElement());
		return property;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((ClassifierUserInteraction)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParticipationGroup()!=null ) {
			getParticipationGroup().getParticipation().remove((PropertyNavigation)this);
		}
		if ( getClassifierUserInteraction()!=null ) {
			getClassifierUserInteraction().getOwnedPropertyNavigation().remove((PropertyNavigation)this);
		}
		if ( getParticipationGroup()!=null ) {
			getParticipationGroup().getPropertyNavigation().remove((PropertyNavigation)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setClassifierUserInteraction(ClassifierUserInteraction classifierUserInteraction) {
		if ( this.classifierUserInteraction!=null ) {
			this.classifierUserInteraction.getOwnedPropertyNavigation().remove((PropertyNavigation)this);
		}
		if ( classifierUserInteraction!=null ) {
			classifierUserInteraction.getOwnedPropertyNavigation().add((PropertyNavigation)this);
			this.classifierUserInteraction=classifierUserInteraction;
		} else {
			this.classifierUserInteraction=null;
		}
	}
	
	public void setParticipationGroup(ParticipationGroup participationGroup) {
		if ( this.participationGroup!=null ) {
			this.participationGroup.getPropertyNavigation().remove((PropertyNavigation)this);
		}
		if ( participationGroup!=null ) {
			participationGroup.getPropertyNavigation().add((PropertyNavigation)this);
			this.participationGroup=participationGroup;
		} else {
			this.participationGroup=null;
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
		if ( getProperty()==null ) {
			sb.append("property=null;");
		} else {
			sb.append("property="+getProperty().getClass().getSimpleName()+"[");
			sb.append(getProperty().getName());
			sb.append("];");
		}
		if ( getClassifierUserInteraction()==null ) {
			sb.append("classifierUserInteraction=null;");
		} else {
			sb.append("classifierUserInteraction="+getClassifierUserInteraction().getClass().getSimpleName()+"[");
			sb.append(getClassifierUserInteraction().getName());
			sb.append("];");
		}
		if ( getParticipationGroup()==null ) {
			sb.append("participationGroup=null;");
		} else {
			sb.append("participationGroup="+getParticipationGroup().getClass().getSimpleName()+"[");
			sb.append(getParticipationGroup().getName());
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
		if ( getClassifierUserInteraction()==null ) {
			sb.append("<classifierUserInteraction/>");
		} else {
			sb.append("<classifierUserInteraction>");
			sb.append(getClassifierUserInteraction().getClass().getSimpleName());
			sb.append("[");
			sb.append(getClassifierUserInteraction().getName());
			sb.append("]");
			sb.append("</classifierUserInteraction>");
			sb.append("\n");
		}
		if ( getParticipationGroup()==null ) {
			sb.append("<participationGroup/>");
		} else {
			sb.append("<participationGroup>");
			sb.append(getParticipationGroup().getClass().getSimpleName());
			sb.append("[");
			sb.append(getParticipationGroup().getName());
			sb.append("]");
			sb.append("</participationGroup>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(ClassifierUserInteraction newOwner) {
		this.classifierUserInteraction=newOwner;
	}
	
	public void copyState(PropertyNavigation from, PropertyNavigation to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setDisplayIndex(from.getDisplayIndex());
		to.setParticipationKind(from.getParticipationKind());
		if ( getAdditionalSecurityOnView()!=null ) {
			to.setAdditionalSecurityOnView(getAdditionalSecurityOnView().makeCopy());
		}
		to.setResultingUserInteraction(getResultingUserInteraction());
		to.setParticipationGroup(getParticipationGroup());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public PropertyNavigation makeCopy() {
		PropertyNavigation result = new PropertyNavigation();
		copyState((PropertyNavigation)this,result);
		return result;
	}
	
	public void shallowCopyState(PropertyNavigation from, PropertyNavigation to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setDisplayIndex(from.getDisplayIndex());
		to.setParticipationKind(from.getParticipationKind());
		if ( getAdditionalSecurityOnView()!=null ) {
			to.setAdditionalSecurityOnView(getAdditionalSecurityOnView().makeCopy());
		}
		to.setResultingUserInteraction(getResultingUserInteraction());
		to.setParticipationGroup(getParticipationGroup());
	}

}