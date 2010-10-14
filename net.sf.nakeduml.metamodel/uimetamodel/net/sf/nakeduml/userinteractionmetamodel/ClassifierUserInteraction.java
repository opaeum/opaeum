package net.sf.nakeduml.userinteractionmetamodel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.domainmetamodel.DomainEntity;
import net.sf.nakeduml.util.CompositionNode;
import util.CompOperationNavigationOndouble;
import util.CompPropertyFieldOndouble;
import util.CompPropertyNavigationOndouble;
import util.Stdlib;

public class ClassifierUserInteraction extends UserInteraction implements CompositionNode {
	private AbstractUserInteractionFolder folder;
	private Boolean isTooMany = false;
	private PropertyNavigation originatingPropertyNavigation;
	private Set<ParticipationGroup> participationGroup = new HashSet<ParticipationGroup>();
	private Set<OperationNavigation> ownedOperationNavigation = new HashSet<OperationNavigation>();
	private ClassifierUserInteraction superClassifierUserInteraction;
	private Boolean isCustom = false;
	private Set<PropertyNavigation> ownedPropertyNavigation = new HashSet<PropertyNavigation>();
	private UserInteractionKind userInteractionKind;
	private Set<PropertyField> ownedPropertyField = new HashSet<PropertyField>();

	/** Default constructor for 
	 */
	public ClassifierUserInteraction() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ClassifierUserInteraction(AbstractUserInteractionFolder owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	public void addAllToOwnedOperationNavigation(Set<OperationNavigation> ownedOperationNavigation) {
		for ( OperationNavigation o : ownedOperationNavigation ) {
			addToOwnedOperationNavigation(o);
		}
	}
	
	public void addAllToOwnedPropertyField(Set<PropertyField> ownedPropertyField) {
		for ( PropertyField o : ownedPropertyField ) {
			addToOwnedPropertyField(o);
		}
	}
	
	public void addAllToOwnedPropertyNavigation(Set<PropertyNavigation> ownedPropertyNavigation) {
		for ( PropertyNavigation o : ownedPropertyNavigation ) {
			addToOwnedPropertyNavigation(o);
		}
	}
	
	public void addAllToParticipationGroup(Set<ParticipationGroup> participationGroup) {
		for ( ParticipationGroup o : participationGroup ) {
			addToParticipationGroup(o);
		}
	}
	
	public void addToOwnedOperationNavigation(OperationNavigation ownedOperationNavigation) {
		ownedOperationNavigation.setClassifierUserInteraction(this);
	}
	
	public void addToOwnedPropertyField(PropertyField ownedPropertyField) {
		ownedPropertyField.setClassifierUserInteraction(this);
	}
	
	public void addToOwnedPropertyNavigation(PropertyNavigation ownedPropertyNavigation) {
		ownedPropertyNavigation.setClassifierUserInteraction(this);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getFolder().getEntityUserInteraction().add((ClassifierUserInteraction)this);
	}
	
	public void addToParticipationGroup(ParticipationGroup participationGroup) {
		participationGroup.setClassifierUserInteraction(this);
	}
	
	public void clearOwnedOperationNavigation() {
		removeAllFromOwnedOperationNavigation(getOwnedOperationNavigation());
	}
	
	public void clearOwnedPropertyField() {
		removeAllFromOwnedPropertyField(getOwnedPropertyField());
	}
	
	public void clearOwnedPropertyNavigation() {
		removeAllFromOwnedPropertyNavigation(getOwnedPropertyNavigation());
	}
	
	public void clearParticipationGroup() {
		removeAllFromParticipationGroup(getParticipationGroup());
	}
	
	public OperationNavigation createOwnedOperationNavigation() {
		OperationNavigation newInstance= new OperationNavigation();
		newInstance.init(this);
		return newInstance;
	}
	
	public PropertyField createOwnedPropertyField() {
		PropertyField newInstance= new PropertyField();
		newInstance.init(this);
		return newInstance;
	}
	
	public PropertyNavigation createOwnedPropertyNavigation() {
		PropertyNavigation newInstance= new PropertyNavigation();
		newInstance.init(this);
		return newInstance;
	}
	
	public ParticipationGroup createParticipationGroup() {
		ParticipationGroup newInstance= new ParticipationGroup();
		newInstance.init(this);
		return newInstance;
	}
	
	public List<OperationNavigation> getActionNavigation() {
		List<OperationNavigation> actionNavigation = (List)select2();
		return actionNavigation;
	}
	
	public List<PropertyNavigation> getChildNavigation() {
		List<PropertyNavigation> childNavigation = (List)select3();
		return childNavigation;
	}
	
	public DomainClassifier getClassifier() {
		DomainClassifier classifier = ((DomainEntity) this.getRepresentedElement());
		return classifier;
	}
	
	public AbstractUserInteractionFolder getFolder() {
		AbstractUserInteractionFolder folderSubsetting = null;
		if ( this.folder!=null ) {
			folderSubsetting=this.folder;
		}
		return folderSubsetting;
	}
	
	public List<PropertyNavigation> getLinkNavigation() {
		List<PropertyNavigation> linkNavigation = (List)select9();
		return linkNavigation;
	}
	
	public List<OperationNavigation> getOperationNavigation() {
		List<OperationNavigation> operationNavigation = (List)((this.getSuperClassifierUserInteraction() == null) ?
			(Collection<OperationNavigation>)sortedBy5() :
			(Collection<OperationNavigation>)sortedBy7());
		return operationNavigation;
	}
	
	public PropertyNavigation getOriginatingPropertyNavigation() {
		return originatingPropertyNavigation;
	}
	
	public Set<PropertyNavigation> getOriginatingPropertyNavigationSourcePopulation() {
		return new HashSet<PropertyNavigation>(Stdlib.collectionAsSet(this.getOwnedPropertyNavigation()));
	}
	
	public Set<UserInteractionElement> getOwnedElement() {
		Set<UserInteractionElement> ownedElementSubsetting = new HashSet<UserInteractionElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		ownedElementSubsetting.addAll(getParticipationGroup());
		ownedElementSubsetting.addAll(getOwnedOperationNavigation());
		ownedElementSubsetting.addAll(getOwnedPropertyNavigation());
		ownedElementSubsetting.addAll(getOwnedPropertyField());
		return ownedElementSubsetting;
	}
	
	public Set<OperationNavigation> getOwnedOperationNavigation() {
		return ownedOperationNavigation;
	}
	
	public Set<PropertyField> getOwnedPropertyField() {
		return ownedPropertyField;
	}
	
	public Set<PropertyNavigation> getOwnedPropertyNavigation() {
		return ownedPropertyNavigation;
	}
	
	public UserInteractionElement getOwner() {
		UserInteractionElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getFolder()!=null ) {
			ownerSubsetting=getFolder();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getFolder();
	}
	
	public Set<ParticipationGroup> getParticipationGroup() {
		return participationGroup;
	}
	
	public List<PropertyField> getPropertyField() {
		List<PropertyField> propertyField = (List)sortedBy8();
		return propertyField;
	}
	
	public List<PropertyNavigation> getPropertyNavigation() {
		List<PropertyNavigation> propertyNavigation = (List)sortedBy1();
		return propertyNavigation;
	}
	
	public List<OperationNavigation> getQueryNavigation() {
		List<OperationNavigation> queryNavigation = (List)select4();
		return queryNavigation;
	}
	
	public ClassifierUserInteraction getSuperClassifierUserInteraction() {
		return superClassifierUserInteraction;
	}
	
	public Set<ClassifierUserInteraction> getSuperClassifierUserInteractionSourcePopulation() {
		return new HashSet<ClassifierUserInteraction>(Stdlib.collectionAsSet(this.getFolder().getEntityUserInteraction()));
	}
	
	public UserInteractionKind getUserInteractionKind() {
		return userInteractionKind;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((AbstractUserInteractionFolder)owner);
		this.setCustom( false );
		createComponents();
	}
	
	public Boolean isCustom() {
		return isCustom;
	}
	
	public Boolean isTooMany() {
		return isTooMany;
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getFolder()!=null ) {
			getFolder().getEntityUserInteraction().remove((ClassifierUserInteraction)this);
		}
		for ( ParticipationGroup child : new ArrayList<ParticipationGroup>(getParticipationGroup()) ) {
			child.markDeleted();
		}
		for ( OperationNavigation child : new ArrayList<OperationNavigation>(getOwnedOperationNavigation()) ) {
			child.markDeleted();
		}
		for ( PropertyField child : new ArrayList<PropertyField>(getOwnedPropertyField()) ) {
			child.markDeleted();
		}
		for ( PropertyNavigation child : new ArrayList<PropertyNavigation>(getOwnedPropertyNavigation()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromOwnedOperationNavigation(Set<OperationNavigation> ownedOperationNavigation) {
		for ( OperationNavigation o : ownedOperationNavigation ) {
			removeFromOwnedOperationNavigation(o);
		}
	}
	
	public void removeAllFromOwnedPropertyField(Set<PropertyField> ownedPropertyField) {
		for ( PropertyField o : ownedPropertyField ) {
			removeFromOwnedPropertyField(o);
		}
	}
	
	public void removeAllFromOwnedPropertyNavigation(Set<PropertyNavigation> ownedPropertyNavigation) {
		for ( PropertyNavigation o : ownedPropertyNavigation ) {
			removeFromOwnedPropertyNavigation(o);
		}
	}
	
	public void removeAllFromParticipationGroup(Set<ParticipationGroup> participationGroup) {
		for ( ParticipationGroup o : participationGroup ) {
			removeFromParticipationGroup(o);
		}
	}
	
	public void removeFromOwnedOperationNavigation(OperationNavigation ownedOperationNavigation) {
		ownedOperationNavigation.setClassifierUserInteraction(null);
	}
	
	public void removeFromOwnedPropertyField(PropertyField ownedPropertyField) {
		ownedPropertyField.setClassifierUserInteraction(null);
	}
	
	public void removeFromOwnedPropertyNavigation(PropertyNavigation ownedPropertyNavigation) {
		ownedPropertyNavigation.setClassifierUserInteraction(null);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromParticipationGroup(ParticipationGroup participationGroup) {
		participationGroup.setClassifierUserInteraction(null);
	}
	
	public void setCustom(Boolean isCustom) {
		this.isCustom=isCustom;
	}
	
	public void setFolder(AbstractUserInteractionFolder folder) {
		if ( this.folder!=null ) {
			this.folder.getEntityUserInteraction().remove((ClassifierUserInteraction)this);
		}
		if ( folder!=null ) {
			folder.getEntityUserInteraction().add((ClassifierUserInteraction)this);
			this.folder=folder;
		} else {
			this.folder=null;
		}
	}
	
	public void setOriginatingPropertyNavigation(PropertyNavigation originatingPropertyNavigation) {
		this.originatingPropertyNavigation=originatingPropertyNavigation;
	}
	
	public void setOwnedOperationNavigation(Set<OperationNavigation> ownedOperationNavigation) {
		for ( OperationNavigation o : new HashSet<OperationNavigation>(this.ownedOperationNavigation) ) {
			o.setClassifierUserInteraction(null);
		}
		for ( OperationNavigation o : ownedOperationNavigation ) {
			o.setClassifierUserInteraction((ClassifierUserInteraction)this);
		}
	}
	
	public void setOwnedPropertyField(Set<PropertyField> ownedPropertyField) {
		for ( PropertyField o : new HashSet<PropertyField>(this.ownedPropertyField) ) {
			o.setClassifierUserInteraction(null);
		}
		for ( PropertyField o : ownedPropertyField ) {
			o.setClassifierUserInteraction((ClassifierUserInteraction)this);
		}
	}
	
	public void setOwnedPropertyNavigation(Set<PropertyNavigation> ownedPropertyNavigation) {
		for ( PropertyNavigation o : new HashSet<PropertyNavigation>(this.ownedPropertyNavigation) ) {
			o.setClassifierUserInteraction(null);
		}
		for ( PropertyNavigation o : ownedPropertyNavigation ) {
			o.setClassifierUserInteraction((ClassifierUserInteraction)this);
		}
	}
	
	public void setParticipationGroup(Set<ParticipationGroup> participationGroup) {
		for ( ParticipationGroup o : new HashSet<ParticipationGroup>(this.participationGroup) ) {
			o.setClassifierUserInteraction(null);
		}
		for ( ParticipationGroup o : participationGroup ) {
			o.setClassifierUserInteraction((ClassifierUserInteraction)this);
		}
	}
	
	public void setSuperClassifierUserInteraction(ClassifierUserInteraction superClassifierUserInteraction) {
		this.superClassifierUserInteraction=superClassifierUserInteraction;
	}
	
	public void setTooMany(Boolean isTooMany) {
		this.isTooMany=isTooMany;
	}
	
	public void setUserInteractionKind(UserInteractionKind userInteractionKind) {
		this.userInteractionKind=userInteractionKind;
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
		sb.append("successMessage=");
		sb.append(getSuccessMessage());
		sb.append(";");
		sb.append("instructionToUser=");
		sb.append(getInstructionToUser());
		sb.append(";");
		if ( getFolder()==null ) {
			sb.append("folder=null;");
		} else {
			sb.append("folder="+getFolder().getClass().getSimpleName()+"[");
			sb.append(getFolder().getName());
			sb.append("];");
		}
		sb.append("inHierarchy=");
		sb.append(getInHierarchy());
		sb.append(";");
		sb.append("isCustom=");
		sb.append(isCustom());
		sb.append(";");
		sb.append("isTooMany=");
		sb.append(isTooMany());
		sb.append(";");
		if ( getClassifier()==null ) {
			sb.append("classifier=null;");
		} else {
			sb.append("classifier="+getClassifier().getClass().getSimpleName()+"[");
			sb.append(getClassifier().getName());
			sb.append("];");
		}
		if ( getFolder()==null ) {
			sb.append("folder=null;");
		} else {
			sb.append("folder="+getFolder().getClass().getSimpleName()+"[");
			sb.append(getFolder().getName());
			sb.append("];");
		}
		sb.append("userInteractionKind=");
		sb.append(getUserInteractionKind());
		sb.append(";");
		if ( getOriginatingPropertyNavigation()==null ) {
			sb.append("originatingPropertyNavigation=null;");
		} else {
			sb.append("originatingPropertyNavigation="+getOriginatingPropertyNavigation().getClass().getSimpleName()+"[");
			sb.append(getOriginatingPropertyNavigation().getName());
			sb.append("];");
		}
		if ( getSuperClassifierUserInteraction()==null ) {
			sb.append("superClassifierUserInteraction=null;");
		} else {
			sb.append("superClassifierUserInteraction="+getSuperClassifierUserInteraction().getClass().getSimpleName()+"[");
			sb.append(getSuperClassifierUserInteraction().getName());
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
		if ( getSuccessMessage()==null ) {
			sb.append("<successMessage/>");
		} else {
			sb.append("<successMessage>");
			sb.append(getSuccessMessage());
			sb.append("</successMessage>");
			sb.append("\n");
		}
		if ( getInstructionToUser()==null ) {
			sb.append("<instructionToUser/>");
		} else {
			sb.append("<instructionToUser>");
			sb.append(getInstructionToUser());
			sb.append("</instructionToUser>");
			sb.append("\n");
		}
		if ( getInHierarchy()==null ) {
			sb.append("<inHierarchy/>");
		} else {
			sb.append("<inHierarchy>");
			sb.append(getInHierarchy());
			sb.append("</inHierarchy>");
			sb.append("\n");
		}
		if ( isCustom()==null ) {
			sb.append("<isCustom/>");
		} else {
			sb.append("<isCustom>");
			sb.append(isCustom());
			sb.append("</isCustom>");
			sb.append("\n");
		}
		if ( isTooMany()==null ) {
			sb.append("<isTooMany/>");
		} else {
			sb.append("<isTooMany>");
			sb.append(isTooMany());
			sb.append("</isTooMany>");
			sb.append("\n");
		}
		for ( ParticipationGroup participationGroup : getParticipationGroup() ) {
			sb.append("<participationGroup>");
			sb.append(participationGroup.toXmlString());
			sb.append("</participationGroup>");
			sb.append("\n");
		}
		for ( OperationNavigation ownedOperationNavigation : getOwnedOperationNavigation() ) {
			sb.append("<ownedOperationNavigation>");
			sb.append(ownedOperationNavigation.toXmlString());
			sb.append("</ownedOperationNavigation>");
			sb.append("\n");
		}
		for ( PropertyField ownedPropertyField : getOwnedPropertyField() ) {
			sb.append("<ownedPropertyField>");
			sb.append(ownedPropertyField.toXmlString());
			sb.append("</ownedPropertyField>");
			sb.append("\n");
		}
		if ( getFolder()==null ) {
			sb.append("<folder/>");
		} else {
			sb.append("<folder>");
			sb.append(getFolder().getClass().getSimpleName());
			sb.append("[");
			sb.append(getFolder().getName());
			sb.append("]");
			sb.append("</folder>");
			sb.append("\n");
		}
		if ( getUserInteractionKind()==null ) {
			sb.append("<userInteractionKind/>");
		} else {
			sb.append("<userInteractionKind>");
			sb.append(getUserInteractionKind());
			sb.append("</userInteractionKind>");
			sb.append("\n");
		}
		for ( PropertyNavigation ownedPropertyNavigation : getOwnedPropertyNavigation() ) {
			sb.append("<ownedPropertyNavigation>");
			sb.append(ownedPropertyNavigation.toXmlString());
			sb.append("</ownedPropertyNavigation>");
			sb.append("\n");
		}
		if ( getOriginatingPropertyNavigation()==null ) {
			sb.append("<originatingPropertyNavigation/>");
		} else {
			sb.append("<originatingPropertyNavigation>");
			sb.append(getOriginatingPropertyNavigation().getClass().getSimpleName());
			sb.append("[");
			sb.append(getOriginatingPropertyNavigation().getName());
			sb.append("]");
			sb.append("</originatingPropertyNavigation>");
			sb.append("\n");
		}
		if ( getSuperClassifierUserInteraction()==null ) {
			sb.append("<superClassifierUserInteraction/>");
		} else {
			sb.append("<superClassifierUserInteraction>");
			sb.append(getSuperClassifierUserInteraction().getClass().getSimpleName());
			sb.append("[");
			sb.append(getSuperClassifierUserInteraction().getName());
			sb.append("]");
			sb.append("</superClassifierUserInteraction>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Implements ->select( i_OperationNavigation : OperationNavigation | not i_OperationNavigation.operation.isQuery )
	 */
	private List<OperationNavigation> select2() {
		List<OperationNavigation> result = new ArrayList<OperationNavigation>();
		for ( OperationNavigation i_OperationNavigation : this.getOperationNavigation() ) {
			if ( !i_OperationNavigation.getOperation().isQuery() ) {
				result.add( i_OperationNavigation );
			}
		}
		return result;
	}
	
	/** Implements ->select( i_PropertyNavigation : PropertyNavigation | i_PropertyNavigation.property.isComposite )
	 */
	private List<PropertyNavigation> select3() {
		List<PropertyNavigation> result = new ArrayList<PropertyNavigation>();
		for ( PropertyNavigation i_PropertyNavigation : this.getPropertyNavigation() ) {
			if ( i_PropertyNavigation.getProperty().isComposite() ) {
				result.add( i_PropertyNavigation );
			}
		}
		return result;
	}
	
	/** Implements ->select( i_OperationNavigation : OperationNavigation | i_OperationNavigation.operation.isQuery )
	 */
	private List<OperationNavigation> select4() {
		List<OperationNavigation> result = new ArrayList<OperationNavigation>();
		for ( OperationNavigation i_OperationNavigation : this.getOperationNavigation() ) {
			if ( i_OperationNavigation.getOperation().isQuery() ) {
				result.add( i_OperationNavigation );
			}
		}
		return result;
	}
	
	/** Implements ->select( i_PropertyNavigation : PropertyNavigation | not i_PropertyNavigation.property.isComposite )
	 */
	private List<PropertyNavigation> select9() {
		List<PropertyNavigation> result = new ArrayList<PropertyNavigation>();
		for ( PropertyNavigation i_PropertyNavigation : this.getPropertyNavigation() ) {
			if ( !i_PropertyNavigation.getProperty().isComposite() ) {
				result.add( i_PropertyNavigation );
			}
		}
		return result;
	}
	
	/** Implements ->sortedBy( i_PropertyNavigation : PropertyNavigation | i_PropertyNavigation.displayIndex )
	 */
	private List<PropertyNavigation> sortedBy1() {
		List<PropertyNavigation> result = new ArrayList<PropertyNavigation>();
		result.addAll(this.getOwnedPropertyNavigation());
		// System.out.println("BEFORE SORTING ON i_PropertyNavigation.getDisplayIndex()");
		// System.out.println(Util.collectionToString(result, "\n"));
		// create a new Comparator instance;
		Comparator comp = new CompPropertyNavigationOndouble();
		// sort the result collection;
		Collections.sort(result, comp);
		// System.out.println("AFTER SORTING ON i_PropertyNavigation.getDisplayIndex()");
		// System.out.println(Util.collectionToString(result, "\n"));
		return result;
	}
	
	/** Implements ->sortedBy( i_OperationNavigation : OperationNavigation | i_OperationNavigation.displayIndex )
	 */
	private List<OperationNavigation> sortedBy5() {
		List<OperationNavigation> result = new ArrayList<OperationNavigation>();
		result.addAll(this.getOwnedOperationNavigation());
		// System.out.println("BEFORE SORTING ON i_OperationNavigation.getDisplayIndex()");
		// System.out.println(Util.collectionToString(result, "\n"));
		// create a new Comparator instance;
		Comparator comp = new CompOperationNavigationOndouble();
		// sort the result collection;
		Collections.sort(result, comp);
		// System.out.println("AFTER SORTING ON i_OperationNavigation.getDisplayIndex()");
		// System.out.println(Util.collectionToString(result, "\n"));
		return result;
	}
	
	/** Implements ->sortedBy( i_OperationNavigation : OperationNavigation | i_OperationNavigation.displayIndex )
	 */
	private List<OperationNavigation> sortedBy7() {
		List<OperationNavigation> result = new ArrayList<OperationNavigation>();
		result.addAll(union6());
		// System.out.println("BEFORE SORTING ON i_OperationNavigation.getDisplayIndex()");
		// System.out.println(Util.collectionToString(result, "\n"));
		// create a new Comparator instance;
		Comparator comp = new CompOperationNavigationOndouble();
		// sort the result collection;
		Collections.sort(result, comp);
		// System.out.println("AFTER SORTING ON i_OperationNavigation.getDisplayIndex()");
		// System.out.println(Util.collectionToString(result, "\n"));
		return result;
	}
	
	/** Implements ->sortedBy( i_PropertyField : PropertyField | i_PropertyField.displayIndex )
	 */
	private List<PropertyField> sortedBy8() {
		List<PropertyField> result = new ArrayList<PropertyField>();
		result.addAll(this.getOwnedPropertyField());
		// System.out.println("BEFORE SORTING ON i_PropertyField.getDisplayIndex()");
		// System.out.println(Util.collectionToString(result, "\n"));
		// create a new Comparator instance;
		Comparator comp = new CompPropertyFieldOndouble();
		// sort the result collection;
		Collections.sort(result, comp);
		// System.out.println("AFTER SORTING ON i_PropertyField.getDisplayIndex()");
		// System.out.println(Util.collectionToString(result, "\n"));
		return result;
	}
	
	/** Implements .union(self.ownedOperationNavigation) on .operationNavigation
	 */
	private List<OperationNavigation> union6() {
		List<OperationNavigation> result = new ArrayList<OperationNavigation>(this.getSuperClassifierUserInteraction().getOperationNavigation());
		result.addAll( this.getOwnedOperationNavigation() );
		return result;
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(AbstractUserInteractionFolder newOwner) {
		this.folder=newOwner;
	}
	
	public void copyState(ClassifierUserInteraction from, ClassifierUserInteraction to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setSuccessMessage(from.getSuccessMessage());
		to.setInstructionToUser(from.getInstructionToUser());
		to.setInHierarchy(from.getInHierarchy());
		to.setCustom(from.isCustom());
		to.setTooMany(from.isTooMany());
		for ( ParticipationGroup child : getParticipationGroup() ) {
			to.addToParticipationGroup(child.makeCopy());
		}
		for ( OperationNavigation child : getOwnedOperationNavigation() ) {
			to.addToOwnedOperationNavigation(child.makeCopy());
		}
		for ( PropertyField child : getOwnedPropertyField() ) {
			to.addToOwnedPropertyField(child.makeCopy());
		}
		to.setUserInteractionKind(from.getUserInteractionKind());
		for ( PropertyNavigation child : getOwnedPropertyNavigation() ) {
			to.addToOwnedPropertyNavigation(child.makeCopy());
		}
		to.setOriginatingPropertyNavigation(getOriginatingPropertyNavigation());
		to.setSuperClassifierUserInteraction(getSuperClassifierUserInteraction());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public ClassifierUserInteraction makeCopy() {
		ClassifierUserInteraction result = new ClassifierUserInteraction();
		copyState((ClassifierUserInteraction)this,result);
		return result;
	}
	
	public void shallowCopyState(ClassifierUserInteraction from, ClassifierUserInteraction to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setSuccessMessage(from.getSuccessMessage());
		to.setInstructionToUser(from.getInstructionToUser());
		to.setInHierarchy(from.getInHierarchy());
		to.setCustom(from.isCustom());
		to.setTooMany(from.isTooMany());
		to.setUserInteractionKind(from.getUserInteractionKind());
		to.setOriginatingPropertyNavigation(getOriginatingPropertyNavigation());
		to.setSuperClassifierUserInteraction(getSuperClassifierUserInteraction());
	}

}