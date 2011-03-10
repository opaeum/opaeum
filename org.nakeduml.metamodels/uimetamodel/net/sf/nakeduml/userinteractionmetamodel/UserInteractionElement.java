package net.sf.nakeduml.userinteractionmetamodel;

import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.domainmetamodel.DomainElement;

import org.nakeduml.runtime.domain.CompositionNode;

import util.Stdlib;

public class UserInteractionElement implements CompositionNode {
	private String additionalHumanName;
	private String name;
	private DomainElement representedElement;

	/** Default constructor for 
	 */
	public UserInteractionElement() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void copyState(UserInteractionElement from, UserInteractionElement to) {
		to.setName(from.getName());
		to.setAdditionalHumanName(from.getAdditionalHumanName());
		to.setRepresentedElement(getRepresentedElement());
	}
	
	public void createComponents() {
	}
	
	public UserInteractionElement findOwnedElement(String name) {
		UserInteractionElement result = null;
		result= any1(name);
		return result;
	}
	
	public String getAdditionalHumanName() {
		return additionalHumanName;
	}
	
	public String getHumanName() {
		String humanName = (Stdlib.stringAsSet(this.getAdditionalHumanName()).isEmpty() ?
			this.getRepresentedElement().getHumanName() :
			this.getAdditionalHumanName());
		return humanName;
	}
	
	public String getName() {
		return name;
	}
	
	public Set<UserInteractionElement> getOwnedElement() {
		Set<UserInteractionElement> ownedElementSubsetting = new HashSet<UserInteractionElement>();
		return new HashSet<UserInteractionElement>();
	}
	
	public UserInteractionElement getOwner() {
		return null;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public String getQualifiedName() {
		String qualifiedName = ((this.getOwner() == null) || (this.getOwner().getClass() == UserInteractionWorkspace.class) ?
			this.getName() :
			this.getOwner().getQualifiedName().concat("::").concat(this.getName()));
		return qualifiedName;
	}
	
	public DomainElement getRepresentedElement() {
		return representedElement;
	}
	
	public void init(CompositionNode owner) {
		createComponents();
	}
	
	public UserInteractionElement makeCopy() {
		UserInteractionElement result = new UserInteractionElement();
		copyState((UserInteractionElement)this,result);
		return result;
	}
	
	public void markDeleted() {
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setAdditionalHumanName(String additionalHumanName) {
		this.additionalHumanName=additionalHumanName;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setRepresentedElement(DomainElement representedElement) {
		this.representedElement=representedElement;
	}
	
	public void shallowCopyState(UserInteractionElement from, UserInteractionElement to) {
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
		return sb.toString();
	}
	
	/** Implements ->any( e : UserInteractionElement | e.qualifiedName = name )
	 * 
	 * @param name 
	 */
	private UserInteractionElement any1(String name) {
		UserInteractionElement result = null;
		for ( UserInteractionElement e : this.getOwnedElement() ) {
			if ( e.getQualifiedName().equals(name) ) {
				return e;
			}
		}
		return result;
	}

}