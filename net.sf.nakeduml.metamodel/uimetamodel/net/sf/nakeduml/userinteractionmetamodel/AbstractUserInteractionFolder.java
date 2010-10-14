package net.sf.nakeduml.userinteractionmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.util.CompositionNode;

abstract public class AbstractUserInteractionFolder extends UserInteractionElement implements CompositionNode {
	private Set<OperationUserInteraction> operationUserInteraction = new HashSet<OperationUserInteraction>();
	private Set<ClassifierUserInteraction> entityUserInteraction = new HashSet<ClassifierUserInteraction>();
	private Set<UserInteractionFolder> childFolder = new HashSet<UserInteractionFolder>();

	/** Default constructor for 
	 */
	public AbstractUserInteractionFolder() {
	}

	public void addAllToChildFolder(Set<UserInteractionFolder> childFolder) {
		for ( UserInteractionFolder o : childFolder ) {
			addToChildFolder(o);
		}
	}
	
	public void addAllToEntityUserInteraction(Set<ClassifierUserInteraction> entityUserInteraction) {
		for ( ClassifierUserInteraction o : entityUserInteraction ) {
			addToEntityUserInteraction(o);
		}
	}
	
	public void addAllToOperationUserInteraction(Set<OperationUserInteraction> operationUserInteraction) {
		for ( OperationUserInteraction o : operationUserInteraction ) {
			addToOperationUserInteraction(o);
		}
	}
	
	public void addToChildFolder(UserInteractionFolder childFolder) {
		childFolder.setParentFolder(this);
	}
	
	public void addToEntityUserInteraction(ClassifierUserInteraction entityUserInteraction) {
		entityUserInteraction.setFolder(this);
	}
	
	public void addToOperationUserInteraction(OperationUserInteraction operationUserInteraction) {
		operationUserInteraction.setFolder(this);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void clearChildFolder() {
		removeAllFromChildFolder(getChildFolder());
	}
	
	public void clearEntityUserInteraction() {
		removeAllFromEntityUserInteraction(getEntityUserInteraction());
	}
	
	public void clearOperationUserInteraction() {
		removeAllFromOperationUserInteraction(getOperationUserInteraction());
	}
	
	public void copyState(AbstractUserInteractionFolder from, AbstractUserInteractionFolder to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
		for ( OperationUserInteraction child : getOperationUserInteraction() ) {
			to.addToOperationUserInteraction(child.makeCopy());
		}
		for ( ClassifierUserInteraction child : getEntityUserInteraction() ) {
			to.addToEntityUserInteraction(child.makeCopy());
		}
		for ( UserInteractionFolder child : getChildFolder() ) {
			to.addToChildFolder(child.makeCopy());
		}
	}
	
	public UserInteractionFolder createChildFolder() {
		UserInteractionFolder newInstance= new UserInteractionFolder();
		newInstance.init(this);
		return newInstance;
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public ClassifierUserInteraction createEntityUserInteraction() {
		ClassifierUserInteraction newInstance= new ClassifierUserInteraction();
		newInstance.init(this);
		return newInstance;
	}
	
	public OperationUserInteraction createOperationUserInteraction() {
		OperationUserInteraction newInstance= new OperationUserInteraction();
		newInstance.init(this);
		return newInstance;
	}
	
	public Set<UserInteractionFolder> getChildFolder() {
		return childFolder;
	}
	
	public Set<ClassifierUserInteraction> getEntityUserInteraction() {
		return entityUserInteraction;
	}
	
	public Set<OperationUserInteraction> getOperationUserInteraction() {
		return operationUserInteraction;
	}
	
	public Set<UserInteractionElement> getOwnedElement() {
		Set<UserInteractionElement> ownedElementSubsetting = new HashSet<UserInteractionElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		ownedElementSubsetting.addAll(getOperationUserInteraction());
		ownedElementSubsetting.addAll(getEntityUserInteraction());
		ownedElementSubsetting.addAll(getChildFolder());
		return ownedElementSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public List<AbstractUserInteractionFolder> getPathFromRoot() {
		List<AbstractUserInteractionFolder> pathFromRoot = (List)collectionLiteral1();
		return pathFromRoot;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		createComponents();
	}
	
	abstract public AbstractUserInteractionFolder makeCopy();
	
	public void markDeleted() {
		super.markDeleted();
		for ( OperationUserInteraction child : new ArrayList<OperationUserInteraction>(getOperationUserInteraction()) ) {
			child.markDeleted();
		}
		for ( ClassifierUserInteraction child : new ArrayList<ClassifierUserInteraction>(getEntityUserInteraction()) ) {
			child.markDeleted();
		}
		for ( UserInteractionFolder child : new ArrayList<UserInteractionFolder>(getChildFolder()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromChildFolder(Set<UserInteractionFolder> childFolder) {
		for ( UserInteractionFolder o : childFolder ) {
			removeFromChildFolder(o);
		}
	}
	
	public void removeAllFromEntityUserInteraction(Set<ClassifierUserInteraction> entityUserInteraction) {
		for ( ClassifierUserInteraction o : entityUserInteraction ) {
			removeFromEntityUserInteraction(o);
		}
	}
	
	public void removeAllFromOperationUserInteraction(Set<OperationUserInteraction> operationUserInteraction) {
		for ( OperationUserInteraction o : operationUserInteraction ) {
			removeFromOperationUserInteraction(o);
		}
	}
	
	public void removeFromChildFolder(UserInteractionFolder childFolder) {
		childFolder.setParentFolder(null);
	}
	
	public void removeFromEntityUserInteraction(ClassifierUserInteraction entityUserInteraction) {
		entityUserInteraction.setFolder(null);
	}
	
	public void removeFromOperationUserInteraction(OperationUserInteraction operationUserInteraction) {
		operationUserInteraction.setFolder(null);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setChildFolder(Set<UserInteractionFolder> childFolder) {
		for ( UserInteractionFolder o : new HashSet<UserInteractionFolder>(this.childFolder) ) {
			o.setParentFolder(null);
		}
		for ( UserInteractionFolder o : childFolder ) {
			o.setParentFolder((AbstractUserInteractionFolder)this);
		}
	}
	
	public void setEntityUserInteraction(Set<ClassifierUserInteraction> entityUserInteraction) {
		for ( ClassifierUserInteraction o : new HashSet<ClassifierUserInteraction>(this.entityUserInteraction) ) {
			o.setFolder(null);
		}
		for ( ClassifierUserInteraction o : entityUserInteraction ) {
			o.setFolder((AbstractUserInteractionFolder)this);
		}
	}
	
	public void setOperationUserInteraction(Set<OperationUserInteraction> operationUserInteraction) {
		for ( OperationUserInteraction o : new HashSet<OperationUserInteraction>(this.operationUserInteraction) ) {
			o.setFolder(null);
		}
		for ( OperationUserInteraction o : operationUserInteraction ) {
			o.setFolder((AbstractUserInteractionFolder)this);
		}
	}
	
	public void shallowCopyState(AbstractUserInteractionFolder from, AbstractUserInteractionFolder to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
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
		for ( OperationUserInteraction operationUserInteraction : getOperationUserInteraction() ) {
			sb.append("<operationUserInteraction>");
			sb.append(operationUserInteraction.toXmlString());
			sb.append("</operationUserInteraction>");
			sb.append("\n");
		}
		for ( ClassifierUserInteraction entityUserInteraction : getEntityUserInteraction() ) {
			sb.append("<entityUserInteraction>");
			sb.append(entityUserInteraction.toXmlString());
			sb.append("</entityUserInteraction>");
			sb.append("\n");
		}
		for ( UserInteractionFolder childFolder : getChildFolder() ) {
			sb.append("<childFolder>");
			sb.append(childFolder.toXmlString());
			sb.append("</childFolder>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Implements Sequence{}
	 */
	private List collectionLiteral1() {
		List myList = new ArrayList();
		return myList;
	}

}