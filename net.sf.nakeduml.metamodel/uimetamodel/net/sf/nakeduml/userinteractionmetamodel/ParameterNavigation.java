package net.sf.nakeduml.userinteractionmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.domainmetamodel.DomainParameter;

import org.nakeduml.runtime.domain.CompositionNode;

import util.Stdlib;

public class ParameterNavigation extends TypedElementNavigation implements CompositionNode {
	private ParticipationGroup participationGroup;
	private OperationUserInteraction operationUserInteraction;

	/** Default constructor for 
	 */
	public ParameterNavigation() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ParameterNavigation(OperationUserInteraction owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getOperationUserInteraction().getParameterNavigation().add((ParameterNavigation)this);
	}
	
	public OperationUserInteraction getOperationUserInteraction() {
		return operationUserInteraction;
	}
	
	public UserInteractionElement getOwner() {
		UserInteractionElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getOperationUserInteraction()!=null ) {
			ownerSubsetting=getOperationUserInteraction();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getOperationUserInteraction();
	}
	
	public DomainParameter getParameter() {
		DomainParameter parameter = ((DomainParameter) this.getRepresentedElement());
		return parameter;
	}
	
	public ParticipationGroup getParticipationGroup() {
		ParticipationGroup participationGroupSubsetting = null;
		if ( this.participationGroup!=null ) {
			participationGroupSubsetting=this.participationGroup;
		}
		return participationGroupSubsetting;
	}
	
	public Set<ParticipationGroup> getParticipationGroupSourcePopulation() {
		return new HashSet<ParticipationGroup>(Stdlib.collectionAsSet(collect1()));
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((OperationUserInteraction)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParticipationGroup()!=null ) {
			getParticipationGroup().getParticipation().remove((ParameterNavigation)this);
		}
		if ( getOperationUserInteraction()!=null ) {
			getOperationUserInteraction().getParameterNavigation().remove((ParameterNavigation)this);
		}
		if ( getParticipationGroup()!=null ) {
			getParticipationGroup().getParameterNavigation().remove((ParameterNavigation)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setOperationUserInteraction(OperationUserInteraction operationUserInteraction) {
		if ( this.operationUserInteraction!=null ) {
			this.operationUserInteraction.getParameterNavigation().remove((ParameterNavigation)this);
		}
		if ( operationUserInteraction!=null ) {
			operationUserInteraction.getParameterNavigation().add((ParameterNavigation)this);
			this.operationUserInteraction=operationUserInteraction;
		} else {
			this.operationUserInteraction=null;
		}
	}
	
	public void setParticipationGroup(ParticipationGroup participationGroup) {
		if ( this.participationGroup!=null ) {
			this.participationGroup.getParameterNavigation().remove((ParameterNavigation)this);
		}
		if ( participationGroup!=null ) {
			participationGroup.getParameterNavigation().add((ParameterNavigation)this);
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
		if ( getOperationUserInteraction()==null ) {
			sb.append("operationUserInteraction=null;");
		} else {
			sb.append("operationUserInteraction="+getOperationUserInteraction().getClass().getSimpleName()+"[");
			sb.append(getOperationUserInteraction().getName());
			sb.append("];");
		}
		if ( getParameter()==null ) {
			sb.append("parameter=null;");
		} else {
			sb.append("parameter="+getParameter().getClass().getSimpleName()+"[");
			sb.append(getParameter().getName());
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
	
	/** Implements ->collect( c : ClassifierUserInteraction | c.participationGroup )
	 */
	private List<ParticipationGroup> collect1() {
		List<ParticipationGroup> result = new ArrayList<ParticipationGroup>();
		for ( ClassifierUserInteraction c : this.getOperationUserInteraction().getFolder().getEntityUserInteraction() ) {
			Set<ParticipationGroup> bodyExpResult = c.getParticipationGroup();
			result.addAll( bodyExpResult );
		}
		return result;
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(OperationUserInteraction newOwner) {
		this.operationUserInteraction=newOwner;
	}
	
	public void copyState(ParameterNavigation from, ParameterNavigation to) {
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
	
	public ParameterNavigation makeCopy() {
		ParameterNavigation result = new ParameterNavigation();
		copyState((ParameterNavigation)this,result);
		return result;
	}
	
	public void shallowCopyState(ParameterNavigation from, ParameterNavigation to) {
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