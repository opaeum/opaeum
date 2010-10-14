package net.sf.nakeduml.userinteractionmetamodel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.domainmetamodel.RootDomainPackage;
import net.sf.nakeduml.util.CompositionNode;

public class UserInteractionWorkspace extends AbstractUserInteractionFolder implements CompositionNode {
	private Set<RootDomainPackage> domainPackage = new HashSet<RootDomainPackage>();

	/** Default constructor for 
	 */
	public UserInteractionWorkspace() {
	}

	public void addAllToDomainPackage(Set<RootDomainPackage> domainPackage) {
		for ( RootDomainPackage o : domainPackage ) {
			addToDomainPackage(o);
		}
	}
	
	public void addToDomainPackage(RootDomainPackage domainPackage) {
		domainPackage.setUserInteractionWorkspace(this);
	}
	
	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void clearDomainPackage() {
		removeAllFromDomainPackage(getDomainPackage());
	}
	
	public void copyState(UserInteractionWorkspace from, UserInteractionWorkspace to) {
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
		for ( RootDomainPackage child : getDomainPackage() ) {
			to.addToDomainPackage(child.makeCopy());
		}
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public RootDomainPackage createDomainPackage() {
		RootDomainPackage newInstance= new RootDomainPackage();
		newInstance.init(this);
		return newInstance;
	}
	
	public AbstractUserInteractionFolder findFolder(String name) {
		AbstractUserInteractionFolder result = null;
		result= any1(name);
		return result;
	}
	
	public RootDomainPackage findPackage(String name) {
		RootDomainPackage result = null;
		result= any2(name);
		return result;
	}
	
	public Set<RootDomainPackage> getDomainPackage() {
		return domainPackage;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		createComponents();
	}
	
	public UserInteractionWorkspace makeCopy() {
		UserInteractionWorkspace result = new UserInteractionWorkspace();
		copyState((UserInteractionWorkspace)this,result);
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
		for ( RootDomainPackage child : new ArrayList<RootDomainPackage>(getDomainPackage()) ) {
			child.markDeleted();
		}
	}
	
	public void removeAllFromDomainPackage(Set<RootDomainPackage> domainPackage) {
		for ( RootDomainPackage o : domainPackage ) {
			removeFromDomainPackage(o);
		}
	}
	
	public void removeFromDomainPackage(RootDomainPackage domainPackage) {
		domainPackage.setUserInteractionWorkspace(null);
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setDomainPackage(Set<RootDomainPackage> domainPackage) {
		for ( RootDomainPackage o : new HashSet<RootDomainPackage>(this.domainPackage) ) {
			o.setUserInteractionWorkspace(null);
		}
		for ( RootDomainPackage o : domainPackage ) {
			o.setUserInteractionWorkspace((UserInteractionWorkspace)this);
		}
	}
	
	public void shallowCopyState(UserInteractionWorkspace from, UserInteractionWorkspace to) {
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
		for ( RootDomainPackage domainPackage : getDomainPackage() ) {
			sb.append("<domainPackage>");
			sb.append(domainPackage.toXmlString());
			sb.append("</domainPackage>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Implements ->any( p : UserInteractionFolder | p.name = name )
	 * 
	 * @param name 
	 */
	private UserInteractionFolder any1(String name) {
		UserInteractionFolder result = null;
		for ( UserInteractionFolder p : this.getChildFolder() ) {
			if ( p.getName().equals(name) ) {
				return p;
			}
		}
		return result;
	}
	
	/** Implements ->any( p : RootDomainPackage | p.name = name )
	 * 
	 * @param name 
	 */
	private RootDomainPackage any2(String name) {
		RootDomainPackage result = null;
		for ( RootDomainPackage p : this.getDomainPackage() ) {
			if ( p.getName().equals(name) ) {
				return p;
			}
		}
		return result;
	}

}