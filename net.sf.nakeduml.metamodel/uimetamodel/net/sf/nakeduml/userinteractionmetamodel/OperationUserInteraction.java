package net.sf.nakeduml.userinteractionmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.domainmetamodel.DomainOperation;
import net.sf.nakeduml.util.CompositionNode;
import util.Stdlib;

public class OperationUserInteraction extends UserInteraction implements CompositionNode {
	private List<ParameterField> parameterField = new ArrayList<ParameterField>();
	private OperationUserInteractionKind operationUserInteractionKind;
	private Set<ParameterNavigation> parameterNavigation = new HashSet<ParameterNavigation>();
	private AbstractUserInteractionFolder folder;
	private ClassifierUserInteraction userInteractionForOwner;
	private ClassifierUserInteraction userInteractionForResult;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public OperationUserInteraction(AbstractUserInteractionFolder owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public OperationUserInteraction() {
	}

	public void addAllToParameterField(List<ParameterField> parameterField) {
		for ( ParameterField o : parameterField ) {
			addToParameterField(o);
		}
	}
	
	public void addAllToParameterNavigation(Set<ParameterNavigation> parameterNavigation) {
		for ( ParameterNavigation o : parameterNavigation ) {
			addToParameterNavigation(o);
		}
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getFolder().getOperationUserInteraction().add((OperationUserInteraction)this);
	}
	
	public void addToParameterField(ParameterField parameterField) {
		parameterField.setOperationUserInteraction(this);
	}
	
	public void addToParameterNavigation(ParameterNavigation parameterNavigation) {
		parameterNavigation.setOperationUserInteraction(this);
	}
	
	public void clearParameterField() {
		removeAllFromParameterField(getParameterField());
	}
	
	public void clearParameterNavigation() {
		removeAllFromParameterNavigation(getParameterNavigation());
	}
	
	public AbstractUserInteractionFolder getFolder() {
		AbstractUserInteractionFolder folderSubsetting = null;
		if ( this.folder!=null ) {
			folderSubsetting=this.folder;
		}
		return folderSubsetting;
	}
	
	public DomainOperation getOperation() {
		DomainOperation operation = ((DomainOperation) this.getRepresentedElement());
		return operation;
	}
	
	public OperationUserInteractionKind getOperationUserInteractionKind() {
		return operationUserInteractionKind;
	}
	
	public Set<UserInteractionElement> getOwnedElement() {
		Set<UserInteractionElement> ownedElementSubsetting = new HashSet<UserInteractionElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		ownedElementSubsetting.addAll(getParameterField());
		return ownedElementSubsetting;
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
	
	public List<ParameterField> getParameterField() {
		return parameterField;
	}
	
	public Set<ParameterNavigation> getParameterNavigation() {
		return parameterNavigation;
	}
	
	public ClassifierUserInteraction getUserInteractionForOwner() {
		return userInteractionForOwner;
	}
	
	public Set<ClassifierUserInteraction> getUserInteractionForOwnerSourcePopulation() {
		return new HashSet<ClassifierUserInteraction>(Stdlib.collectionAsSet(this.getFolder().getEntityUserInteraction()));
	}
	
	public ClassifierUserInteraction getUserInteractionForResult() {
		return userInteractionForResult;
	}
	
	public Set<ClassifierUserInteraction> getUserInteractionForResultSourcePopulation() {
		return new HashSet<ClassifierUserInteraction>(Stdlib.collectionAsSet(this.getFolder().getEntityUserInteraction()));
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((AbstractUserInteractionFolder)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getFolder()!=null ) {
			getFolder().getOperationUserInteraction().remove((OperationUserInteraction)this);
		}
		for ( ParameterField child : new ArrayList<ParameterField>(getParameterField()) ) {
			child.markDeleted();
		}
		for ( ParameterNavigation child : new ArrayList<ParameterNavigation>(getParameterNavigation()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromParameterField(List<ParameterField> parameterField) {
		for ( ParameterField o : parameterField ) {
			removeFromParameterField(o);
		}
	}
	
	public void removeAllFromParameterNavigation(Set<ParameterNavigation> parameterNavigation) {
		for ( ParameterNavigation o : parameterNavigation ) {
			removeFromParameterNavigation(o);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void removeFromParameterField(ParameterField parameterField) {
		parameterField.setOperationUserInteraction(null);
	}
	
	public void removeFromParameterNavigation(ParameterNavigation parameterNavigation) {
		parameterNavigation.setOperationUserInteraction(null);
	}
	
	public void setFolder(AbstractUserInteractionFolder folder) {
		if ( this.folder!=null ) {
			this.folder.getOperationUserInteraction().remove((OperationUserInteraction)this);
		}
		if ( folder!=null ) {
			folder.getOperationUserInteraction().add((OperationUserInteraction)this);
			this.folder=folder;
		} else {
			this.folder=null;
		}
	}
	
	public void setOperationUserInteractionKind(OperationUserInteractionKind operationUserInteractionKind) {
		this.operationUserInteractionKind=operationUserInteractionKind;
	}
	
	public void setParameterField(List<ParameterField> parameterField) {
		for ( ParameterField o : new ArrayList<ParameterField>(this.parameterField) ) {
			o.setOperationUserInteraction(null);
		}
		for ( ParameterField o : parameterField ) {
			o.setOperationUserInteraction((OperationUserInteraction)this);
		}
	}
	
	public void setParameterNavigation(Set<ParameterNavigation> parameterNavigation) {
		for ( ParameterNavigation o : new HashSet<ParameterNavigation>(this.parameterNavigation) ) {
			o.setOperationUserInteraction(null);
		}
		for ( ParameterNavigation o : parameterNavigation ) {
			o.setOperationUserInteraction((OperationUserInteraction)this);
		}
	}
	
	public void setUserInteractionForOwner(ClassifierUserInteraction userInteractionForOwner) {
		this.userInteractionForOwner=userInteractionForOwner;
	}
	
	public void setUserInteractionForResult(ClassifierUserInteraction userInteractionForResult) {
		this.userInteractionForResult=userInteractionForResult;
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
		if ( getOperation()==null ) {
			sb.append("operation=null;");
		} else {
			sb.append("operation="+getOperation().getClass().getSimpleName()+"[");
			sb.append(getOperation().getName());
			sb.append("];");
		}
		if ( getFolder()==null ) {
			sb.append("folder=null;");
		} else {
			sb.append("folder="+getFolder().getClass().getSimpleName()+"[");
			sb.append(getFolder().getName());
			sb.append("];");
		}
		if ( getUserInteractionForOwner()==null ) {
			sb.append("userInteractionForOwner=null;");
		} else {
			sb.append("userInteractionForOwner="+getUserInteractionForOwner().getClass().getSimpleName()+"[");
			sb.append(getUserInteractionForOwner().getName());
			sb.append("];");
		}
		if ( getUserInteractionForResult()==null ) {
			sb.append("userInteractionForResult=null;");
		} else {
			sb.append("userInteractionForResult="+getUserInteractionForResult().getClass().getSimpleName()+"[");
			sb.append(getUserInteractionForResult().getName());
			sb.append("];");
		}
		sb.append("operationUserInteractionKind=");
		sb.append(getOperationUserInteractionKind());
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
		for ( ParameterField parameterField : getParameterField() ) {
			sb.append("<parameterField>");
			sb.append(parameterField.toXmlString());
			sb.append("</parameterField>");
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
		if ( getUserInteractionForOwner()==null ) {
			sb.append("<userInteractionForOwner/>");
		} else {
			sb.append("<userInteractionForOwner>");
			sb.append(getUserInteractionForOwner().getClass().getSimpleName());
			sb.append("[");
			sb.append(getUserInteractionForOwner().getName());
			sb.append("]");
			sb.append("</userInteractionForOwner>");
			sb.append("\n");
		}
		if ( getUserInteractionForResult()==null ) {
			sb.append("<userInteractionForResult/>");
		} else {
			sb.append("<userInteractionForResult>");
			sb.append(getUserInteractionForResult().getClass().getSimpleName());
			sb.append("[");
			sb.append(getUserInteractionForResult().getName());
			sb.append("]");
			sb.append("</userInteractionForResult>");
			sb.append("\n");
		}
		if ( getOperationUserInteractionKind()==null ) {
			sb.append("<operationUserInteractionKind/>");
		} else {
			sb.append("<operationUserInteractionKind>");
			sb.append(getOperationUserInteractionKind());
			sb.append("</operationUserInteractionKind>");
			sb.append("\n");
		}
		for ( ParameterNavigation parameterNavigation : getParameterNavigation() ) {
			sb.append("<parameterNavigation>");
			sb.append(parameterNavigation.toXmlString());
			sb.append("</parameterNavigation>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(AbstractUserInteractionFolder newOwner) {
		this.folder=newOwner;
	}
	
	public void copyState(OperationUserInteraction from, OperationUserInteraction to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setSuccessMessage(from.getSuccessMessage());
		to.setInstructionToUser(from.getInstructionToUser());
		to.setInHierarchy(from.getInHierarchy());
		for ( ParameterField child : getParameterField() ) {
			to.addToParameterField(child.makeCopy());
		}
		to.setUserInteractionForOwner(getUserInteractionForOwner());
		to.setUserInteractionForResult(getUserInteractionForResult());
		to.setOperationUserInteractionKind(from.getOperationUserInteractionKind());
		for ( ParameterNavigation child : getParameterNavigation() ) {
			to.addToParameterNavigation(child.makeCopy());
		}
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public ParameterField createParameterField() {
		ParameterField newInstance= new ParameterField();
		newInstance.init(this);
		return newInstance;
	}
	
	public ParameterNavigation createParameterNavigation() {
		ParameterNavigation newInstance= new ParameterNavigation();
		newInstance.init(this);
		return newInstance;
	}
	
	public OperationUserInteraction makeCopy() {
		OperationUserInteraction result = new OperationUserInteraction();
		copyState((OperationUserInteraction)this,result);
		return result;
	}
	
	public void shallowCopyState(OperationUserInteraction from, OperationUserInteraction to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		to.setSuccessMessage(from.getSuccessMessage());
		to.setInstructionToUser(from.getInstructionToUser());
		to.setInHierarchy(from.getInHierarchy());
		to.setUserInteractionForOwner(getUserInteractionForOwner());
		to.setUserInteractionForResult(getUserInteractionForResult());
		to.setOperationUserInteractionKind(from.getOperationUserInteractionKind());
	}

}