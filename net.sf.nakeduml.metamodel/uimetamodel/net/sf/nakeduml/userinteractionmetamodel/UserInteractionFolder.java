package net.sf.nakeduml.userinteractionmetamodel;

import java.util.ArrayList;
import java.util.List;

import org.nakeduml.runtime.domain.CompositionNode;


public class UserInteractionFolder extends AbstractUserInteractionFolder implements CompositionNode {
	private AbstractUserInteractionFolder parentFolder;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public UserInteractionFolder(AbstractUserInteractionFolder owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public UserInteractionFolder() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getParentFolder().getChildFolder().add((UserInteractionFolder)this);
	}
	
	public UserInteractionElement getOwner() {
		UserInteractionElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getParentFolder()!=null ) {
			ownerSubsetting=getParentFolder();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getParentFolder();
	}
	
	public AbstractUserInteractionFolder getParentFolder() {
		return parentFolder;
	}
	
	public List<AbstractUserInteractionFolder> getPathFromRoot() {
		List<AbstractUserInteractionFolder> pathFromRoot = (List)append1();
		return pathFromRoot;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((AbstractUserInteractionFolder)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getParentFolder()!=null ) {
			getParentFolder().getChildFolder().remove((UserInteractionFolder)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setParentFolder(AbstractUserInteractionFolder parentFolder) {
		if ( this.parentFolder!=null ) {
			this.parentFolder.getChildFolder().remove((UserInteractionFolder)this);
		}
		if ( parentFolder!=null ) {
			parentFolder.getChildFolder().add((UserInteractionFolder)this);
			this.parentFolder=parentFolder;
		} else {
			this.parentFolder=null;
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
		if ( getParentFolder()==null ) {
			sb.append("parentFolder=null;");
		} else {
			sb.append("parentFolder="+getParentFolder().getClass().getSimpleName()+"[");
			sb.append(getParentFolder().getName());
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
		if ( getParentFolder()==null ) {
			sb.append("<parentFolder/>");
		} else {
			sb.append("<parentFolder>");
			sb.append(getParentFolder().getClass().getSimpleName());
			sb.append("[");
			sb.append(getParentFolder().getName());
			sb.append("]");
			sb.append("</parentFolder>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Implements .append(self) on .pathFromRoot
	 */
	private List<AbstractUserInteractionFolder> append1() {
		List<AbstractUserInteractionFolder> result = new ArrayList<AbstractUserInteractionFolder>(this.getParentFolder().getPathFromRoot());
		result.add( this );
		return result;
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(AbstractUserInteractionFolder newOwner) {
		this.parentFolder=newOwner;
	}
	
	public void copyState(UserInteractionFolder from, UserInteractionFolder to) {
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
	
	public void createComponents() {
		super.createComponents();
	}
	
	public UserInteractionFolder makeCopy() {
		UserInteractionFolder result = new UserInteractionFolder();
		copyState((UserInteractionFolder)this,result);
		return result;
	}
	
	public void shallowCopyState(UserInteractionFolder from, UserInteractionFolder to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
	}

}