package net.sf.nakeduml.userinteractionmetamodel;

import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.domainmetamodel.DomainOperation;
import net.sf.nakeduml.domainmetamodel.SecurityOnUserAction;

import org.nakeduml.runtime.domain.CompositionNode;

import util.Stdlib;

public class OperationNavigation extends Participation implements CompositionNode, Navigation {
	private ParticipationGroup participationGroup;
	private OperationParticipationKind participationKind;
	private OperationUserInteraction operationUserInteraction;
	private ClassifierUserInteraction classifierUserInteraction;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public OperationNavigation(ClassifierUserInteraction owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public OperationNavigation() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getClassifierUserInteraction().getOwnedOperationNavigation().add((OperationNavigation)this);
	}
	
	public ClassifierUserInteraction getClassifierUserInteraction() {
		return classifierUserInteraction;
	}
	
	public DomainOperation getOperation() {
		DomainOperation operation = ((DomainOperation) this.getRepresentedElement());
		return operation;
	}
	
	public OperationUserInteraction getOperationUserInteraction() {
		return operationUserInteraction;
	}
	
	public Set<OperationUserInteraction> getOperationUserInteractionSourcePopulation() {
		return new HashSet<OperationUserInteraction>(Stdlib.collectionAsSet(this.getClassifierUserInteraction().getFolder().getOperationUserInteraction()));
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
	
	public OperationParticipationKind getParticipationKind() {
		return participationKind;
	}
	
	public OperationUserInteraction getResultingUserInteraction() {
		OperationUserInteraction resultingUserInteraction = this.getOperationUserInteraction();
		return resultingUserInteraction;
	}
	
	public SecurityOnUserAction getSecurityOnView() {
		SecurityOnUserAction securityOnView = this.getOperation().getSecurityOnInvoke();
		return securityOnView;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((ClassifierUserInteraction)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParticipationGroup()!=null ) {
			getParticipationGroup().getParticipation().remove((OperationNavigation)this);
		}
		if ( getClassifierUserInteraction()!=null ) {
			getClassifierUserInteraction().getOwnedOperationNavigation().remove((OperationNavigation)this);
		}
		if ( getParticipationGroup()!=null ) {
			getParticipationGroup().getOperationNavigation().remove((OperationNavigation)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setClassifierUserInteraction(ClassifierUserInteraction classifierUserInteraction) {
		if ( this.classifierUserInteraction!=null ) {
			this.classifierUserInteraction.getOwnedOperationNavigation().remove((OperationNavigation)this);
		}
		if ( classifierUserInteraction!=null ) {
			classifierUserInteraction.getOwnedOperationNavigation().add((OperationNavigation)this);
			this.classifierUserInteraction=classifierUserInteraction;
		} else {
			this.classifierUserInteraction=null;
		}
	}
	
	public void setOperationUserInteraction(OperationUserInteraction operationUserInteraction) {
		this.operationUserInteraction=operationUserInteraction;
	}
	
	public void setParticipationGroup(ParticipationGroup participationGroup) {
		if ( this.participationGroup!=null ) {
			this.participationGroup.getOperationNavigation().remove((OperationNavigation)this);
		}
		if ( participationGroup!=null ) {
			participationGroup.getOperationNavigation().add((OperationNavigation)this);
			this.participationGroup=participationGroup;
		} else {
			this.participationGroup=null;
		}
	}
	
	public void setParticipationKind(OperationParticipationKind participationKind) {
		this.participationKind=participationKind;
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
		if ( getResultingUserInteraction()==null ) {
			sb.append("resultingUserInteraction=null;");
		} else {
			sb.append("resultingUserInteraction="+getResultingUserInteraction().getClass().getSimpleName()+"[");
			sb.append(getResultingUserInteraction().getName());
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
		if ( getOperation()==null ) {
			sb.append("operation=null;");
		} else {
			sb.append("operation="+getOperation().getClass().getSimpleName()+"[");
			sb.append(getOperation().getName());
			sb.append("];");
		}
		if ( getClassifierUserInteraction()==null ) {
			sb.append("classifierUserInteraction=null;");
		} else {
			sb.append("classifierUserInteraction="+getClassifierUserInteraction().getClass().getSimpleName()+"[");
			sb.append(getClassifierUserInteraction().getName());
			sb.append("];");
		}
		sb.append("participationKind=");
		sb.append(getParticipationKind());
		sb.append(";");
		if ( getOperationUserInteraction()==null ) {
			sb.append("operationUserInteraction=null;");
		} else {
			sb.append("operationUserInteraction="+getOperationUserInteraction().getClass().getSimpleName()+"[");
			sb.append(getOperationUserInteraction().getName());
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
		if ( getParticipationKind()==null ) {
			sb.append("<participationKind/>");
		} else {
			sb.append("<participationKind>");
			sb.append(getParticipationKind());
			sb.append("</participationKind>");
			sb.append("\n");
		}
		if ( getOperationUserInteraction()==null ) {
			sb.append("<operationUserInteraction/>");
		} else {
			sb.append("<operationUserInteraction>");
			sb.append(getOperationUserInteraction().getClass().getSimpleName());
			sb.append("[");
			sb.append(getOperationUserInteraction().getName());
			sb.append("]");
			sb.append("</operationUserInteraction>");
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
	
	public void copyState(OperationNavigation from, OperationNavigation to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setDisplayIndex(from.getDisplayIndex());
		to.setParticipationKind(from.getParticipationKind());
		to.setOperationUserInteraction(getOperationUserInteraction());
		to.setParticipationGroup(getParticipationGroup());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public OperationNavigation makeCopy() {
		OperationNavigation result = new OperationNavigation();
		copyState((OperationNavigation)this,result);
		return result;
	}
	
	public void shallowCopyState(OperationNavigation from, OperationNavigation to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setDisplayIndex(from.getDisplayIndex());
		to.setParticipationKind(from.getParticipationKind());
		to.setOperationUserInteraction(getOperationUserInteraction());
		to.setParticipationGroup(getParticipationGroup());
	}

}