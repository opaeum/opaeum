package net.sf.nakeduml.userinteractionmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.nakeduml.runtime.domain.CompositionNode;

import util.Stdlib;

public class ParticipationGroup extends UserInteractionElement implements CompositionNode {
	private ClassifierUserInteraction classifierUserInteraction;
	private String displayName;
	private Set<PropertyField> propertyField = new HashSet<PropertyField>();
	private Set<ParameterNavigation> parameterNavigation = new HashSet<ParameterNavigation>();
	private String name;
	private Set<PropertyNavigation> propertyNavigation = new HashSet<PropertyNavigation>();
	private Set<OperationNavigation> operationNavigation = new HashSet<OperationNavigation>();
	private Integer displayIndex = 0;
	private Set<ParameterField> parameterField = new HashSet<ParameterField>();

	/** Default constructor for 
	 */
	public ParticipationGroup() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ParticipationGroup(ClassifierUserInteraction owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	public void addAllToOperationNavigation(Set<OperationNavigation> operationNavigation) {
		for ( OperationNavigation o : operationNavigation ) {
			addToOperationNavigation(o);
		}
	}
	
	public void addAllToParameterField(Set<ParameterField> parameterField) {
		for ( ParameterField o : parameterField ) {
			addToParameterField(o);
		}
	}
	
	public void addAllToParameterNavigation(Set<ParameterNavigation> parameterNavigation) {
		for ( ParameterNavigation o : parameterNavigation ) {
			addToParameterNavigation(o);
		}
	}
	
	public void addAllToPropertyField(Set<PropertyField> propertyField) {
		for ( PropertyField o : propertyField ) {
			addToPropertyField(o);
		}
	}
	
	public void addAllToPropertyNavigation(Set<PropertyNavigation> propertyNavigation) {
		for ( PropertyNavigation o : propertyNavigation ) {
			addToPropertyNavigation(o);
		}
	}
	
	public void addToOperationNavigation(OperationNavigation operationNavigation) {
		operationNavigation.setParticipationGroup(this);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getClassifierUserInteraction().getParticipationGroup().add((ParticipationGroup)this);
	}
	
	public void addToParameterField(ParameterField parameterField) {
		parameterField.setParticipationGroup(this);
	}
	
	public void addToParameterNavigation(ParameterNavigation parameterNavigation) {
		parameterNavigation.setParticipationGroup(this);
	}
	
	public void addToPropertyField(PropertyField propertyField) {
		propertyField.setParticipationGroup(this);
	}
	
	public void addToPropertyNavigation(PropertyNavigation propertyNavigation) {
		propertyNavigation.setParticipationGroup(this);
	}
	
	public void clearOperationNavigation() {
		removeAllFromOperationNavigation(getOperationNavigation());
	}
	
	public void clearParameterField() {
		removeAllFromParameterField(getParameterField());
	}
	
	public void clearParameterNavigation() {
		removeAllFromParameterNavigation(getParameterNavigation());
	}
	
	public void clearPropertyField() {
		removeAllFromPropertyField(getPropertyField());
	}
	
	public void clearPropertyNavigation() {
		removeAllFromPropertyNavigation(getPropertyNavigation());
	}
	
	public ClassifierUserInteraction getClassifierUserInteraction() {
		return classifierUserInteraction;
	}
	
	public Integer getDisplayIndex() {
		return displayIndex;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getName() {
		return name;
	}
	
	public Set<OperationNavigation> getOperationNavigation() {
		return operationNavigation;
	}
	
	public Set<OperationNavigation> getOperationNavigationSourcePopulation() {
		return new HashSet<OperationNavigation>(Stdlib.collectionAsSet(this.getClassifierUserInteraction().getOwnedOperationNavigation()));
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
	
	public Set<ParameterField> getParameterField() {
		return parameterField;
	}
	
	public Set<ParameterField> getParameterFieldSourcePopulation() {
		return new HashSet<ParameterField>(Stdlib.collectionAsSet(collect2()));
	}
	
	public Set<ParameterNavigation> getParameterNavigation() {
		return parameterNavigation;
	}
	
	public Set<ParameterNavigation> getParameterNavigationSourcePopulation() {
		return new HashSet<ParameterNavigation>(Stdlib.collectionAsSet(collect1()));
	}
	
	public Set<Participation> getParticipation() {
		Set<Participation> participationSubsetting = new HashSet<Participation>();
		participationSubsetting.addAll(getPropertyField());
		participationSubsetting.addAll(getParameterNavigation());
		participationSubsetting.addAll(getPropertyNavigation());
		participationSubsetting.addAll(getOperationNavigation());
		participationSubsetting.addAll(getParameterField());
		return participationSubsetting;
	}
	
	public Set<PropertyField> getPropertyField() {
		return propertyField;
	}
	
	public Set<PropertyField> getPropertyFieldSourcePopulation() {
		return new HashSet<PropertyField>(Stdlib.collectionAsSet(this.getClassifierUserInteraction().getOwnedPropertyField()));
	}
	
	public Set<PropertyNavigation> getPropertyNavigation() {
		return propertyNavigation;
	}
	
	public Set<PropertyNavigation> getPropertyNavigationSourcePopulation() {
		return new HashSet<PropertyNavigation>(Stdlib.collectionAsSet(this.getClassifierUserInteraction().getOwnedPropertyNavigation()));
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((ClassifierUserInteraction)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getClassifierUserInteraction()!=null ) {
			getClassifierUserInteraction().getParticipationGroup().remove((ParticipationGroup)this);
		}
	}
	
	public void removeAllFromOperationNavigation(Set<OperationNavigation> operationNavigation) {
		for ( OperationNavigation o : operationNavigation ) {
			removeFromOperationNavigation(o);
		}
	}
	
	public void removeAllFromParameterField(Set<ParameterField> parameterField) {
		for ( ParameterField o : parameterField ) {
			removeFromParameterField(o);
		}
	}
	
	public void removeAllFromParameterNavigation(Set<ParameterNavigation> parameterNavigation) {
		for ( ParameterNavigation o : parameterNavigation ) {
			removeFromParameterNavigation(o);
		}
	}
	
	public void removeAllFromPropertyField(Set<PropertyField> propertyField) {
		for ( PropertyField o : propertyField ) {
			removeFromPropertyField(o);
		}
	}
	
	public void removeAllFromPropertyNavigation(Set<PropertyNavigation> propertyNavigation) {
		for ( PropertyNavigation o : propertyNavigation ) {
			removeFromPropertyNavigation(o);
		}
	}
	
	public void removeFromOperationNavigation(OperationNavigation operationNavigation) {
		operationNavigation.setParticipationGroup(null);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromParameterField(ParameterField parameterField) {
		parameterField.setParticipationGroup(null);
	}
	
	public void removeFromParameterNavigation(ParameterNavigation parameterNavigation) {
		parameterNavigation.setParticipationGroup(null);
	}
	
	public void removeFromPropertyField(PropertyField propertyField) {
		propertyField.setParticipationGroup(null);
	}
	
	public void removeFromPropertyNavigation(PropertyNavigation propertyNavigation) {
		propertyNavigation.setParticipationGroup(null);
	}
	
	public void setClassifierUserInteraction(ClassifierUserInteraction classifierUserInteraction) {
		if ( this.classifierUserInteraction!=null ) {
			this.classifierUserInteraction.getParticipationGroup().remove((ParticipationGroup)this);
		}
		if ( classifierUserInteraction!=null ) {
			classifierUserInteraction.getParticipationGroup().add((ParticipationGroup)this);
			this.classifierUserInteraction=classifierUserInteraction;
		} else {
			this.classifierUserInteraction=null;
		}
	}
	
	public void setDisplayIndex(Integer displayIndex) {
		this.displayIndex=displayIndex;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName=displayName;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setOperationNavigation(Set<OperationNavigation> operationNavigation) {
		for ( OperationNavigation o : new HashSet<OperationNavigation>(this.operationNavigation) ) {
			o.setParticipationGroup(null);
		}
		for ( OperationNavigation o : operationNavigation ) {
			o.setParticipationGroup((ParticipationGroup)this);
		}
	}
	
	public void setParameterField(Set<ParameterField> parameterField) {
		for ( ParameterField o : new HashSet<ParameterField>(this.parameterField) ) {
			o.setParticipationGroup(null);
		}
		for ( ParameterField o : parameterField ) {
			o.setParticipationGroup((ParticipationGroup)this);
		}
	}
	
	public void setParameterNavigation(Set<ParameterNavigation> parameterNavigation) {
		for ( ParameterNavigation o : new HashSet<ParameterNavigation>(this.parameterNavigation) ) {
			o.setParticipationGroup(null);
		}
		for ( ParameterNavigation o : parameterNavigation ) {
			o.setParticipationGroup((ParticipationGroup)this);
		}
	}
	
	public void setPropertyField(Set<PropertyField> propertyField) {
		for ( PropertyField o : new HashSet<PropertyField>(this.propertyField) ) {
			o.setParticipationGroup(null);
		}
		for ( PropertyField o : propertyField ) {
			o.setParticipationGroup((ParticipationGroup)this);
		}
	}
	
	public void setPropertyNavigation(Set<PropertyNavigation> propertyNavigation) {
		for ( PropertyNavigation o : new HashSet<PropertyNavigation>(this.propertyNavigation) ) {
			o.setParticipationGroup(null);
		}
		for ( PropertyNavigation o : propertyNavigation ) {
			o.setParticipationGroup((ParticipationGroup)this);
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
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		sb.append("displayName=");
		sb.append(getDisplayName());
		sb.append(";");
		if ( getClassifierUserInteraction()==null ) {
			sb.append("classifierUserInteraction=null;");
		} else {
			sb.append("classifierUserInteraction="+getClassifierUserInteraction().getClass().getSimpleName()+"[");
			sb.append(getClassifierUserInteraction().getName());
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
		if ( getName()==null ) {
			sb.append("<name/>");
		} else {
			sb.append("<name>");
			sb.append(getName());
			sb.append("</name>");
			sb.append("\n");
		}
		if ( getDisplayName()==null ) {
			sb.append("<displayName/>");
		} else {
			sb.append("<displayName>");
			sb.append(getDisplayName());
			sb.append("</displayName>");
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
		for ( PropertyField propertyField : getPropertyField() ) {
			sb.append("<propertyField>");
			sb.append(propertyField.getClass().getSimpleName());
			sb.append("[");
			sb.append(propertyField.getName());
			sb.append("]");
			sb.append("</propertyField>");
			sb.append("\n");
		}
		for ( PropertyNavigation propertyNavigation : getPropertyNavigation() ) {
			sb.append("<propertyNavigation>");
			sb.append(propertyNavigation.getClass().getSimpleName());
			sb.append("[");
			sb.append(propertyNavigation.getName());
			sb.append("]");
			sb.append("</propertyNavigation>");
			sb.append("\n");
		}
		for ( OperationNavigation operationNavigation : getOperationNavigation() ) {
			sb.append("<operationNavigation>");
			sb.append(operationNavigation.getClass().getSimpleName());
			sb.append("[");
			sb.append(operationNavigation.getName());
			sb.append("]");
			sb.append("</operationNavigation>");
			sb.append("\n");
		}
		for ( ParameterField parameterField : getParameterField() ) {
			sb.append("<parameterField>");
			sb.append(parameterField.getClass().getSimpleName());
			sb.append("[");
			sb.append(parameterField.getName());
			sb.append("]");
			sb.append("</parameterField>");
			sb.append("\n");
		}
		for ( ParameterNavigation parameterNavigation : getParameterNavigation() ) {
			sb.append("<parameterNavigation>");
			sb.append(parameterNavigation.getClass().getSimpleName());
			sb.append("[");
			sb.append(parameterNavigation.getName());
			sb.append("]");
			sb.append("</parameterNavigation>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Implements ->collect( c : OperationUserInteraction | c.parameterNavigation )
	 */
	private List<ParameterNavigation> collect1() {
		List<ParameterNavigation> result = new ArrayList<ParameterNavigation>();
		for ( OperationUserInteraction c : this.getClassifierUserInteraction().getFolder().getOperationUserInteraction() ) {
			Set<ParameterNavigation> bodyExpResult = c.getParameterNavigation();
			result.addAll( bodyExpResult );
		}
		return result;
	}
	
	/** Implements ->collect( c : OperationUserInteraction | c.parameterField )
	 */
	private List<ParameterField> collect2() {
		List<ParameterField> result = new ArrayList<ParameterField>();
		for ( OperationUserInteraction c : this.getClassifierUserInteraction().getFolder().getOperationUserInteraction() ) {
			List<ParameterField> bodyExpResult = c.getParameterField();
			result.addAll( bodyExpResult );
		}
		return result;
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(ClassifierUserInteraction newOwner) {
		this.classifierUserInteraction=newOwner;
	}
	
	public void copyState(ParticipationGroup from, ParticipationGroup to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setDisplayIndex(from.getDisplayIndex());
		to.setName(from.getName());
		to.setDisplayName(from.getDisplayName());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public ParticipationGroup makeCopy() {
		ParticipationGroup result = new ParticipationGroup();
		copyState((ParticipationGroup)this,result);
		return result;
	}
	
	public void shallowCopyState(ParticipationGroup from, ParticipationGroup to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setDisplayIndex(from.getDisplayIndex());
		to.setName(from.getName());
		to.setDisplayName(from.getDisplayName());
	}

}