package net.sf.nakeduml.domainmetamodel;

import java.util.HashSet;
import java.util.Set;

import org.nakeduml.runtime.domain.CompositionNode;


public class DomainElement implements CompositionNode {
	private String humanName;
	private String name;

	/** Default constructor for 
	 */
	public DomainElement() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void copyState(DomainElement from, DomainElement to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
	}
	
	public void createComponents() {
	}
	
	public DomainElement findOwnedElement(String name) {
		DomainElement result = null;
		result= any1(name);
		return result;
	}
	
	public String getHumanName() {
		return humanName;
	}
	
	public String getName() {
		return name;
	}
	
	public Set<DomainElement> getOwnedElement() {
		Set<DomainElement> ownedElementSubsetting = new HashSet<DomainElement>();
		return new HashSet<DomainElement>();
	}
	
	public DomainElement getOwner() {
		return null;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public String getQualifiedName() {
		String qualifiedName = ((this.getOwner() == null) ?
			this.getName() :
			this.getOwner().getQualifiedName().concat("::").concat(this.getName()));
		return qualifiedName;
	}
	
	public void init(CompositionNode owner) {
		createComponents();
	}
	
	public DomainElement makeCopy() {
		DomainElement result = new DomainElement();
		copyState((DomainElement)this,result);
		return result;
	}
	
	public void markDeleted() {
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setHumanName(String humanName) {
		this.humanName=humanName;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void shallowCopyState(DomainElement from, DomainElement to) {
		to.setName(from.getName());
		to.setHumanName(from.getHumanName());
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
		sb.append("humanName=");
		sb.append(getHumanName());
		sb.append(";");
		sb.append("qualifiedName=");
		sb.append(getQualifiedName());
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
		if ( getHumanName()==null ) {
			sb.append("<humanName/>");
		} else {
			sb.append("<humanName>");
			sb.append(getHumanName());
			sb.append("</humanName>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Implements ->any( e : DomainElement | e.name = name )
	 * 
	 * @param name 
	 */
	private DomainElement any1(String name) {
		DomainElement result = null;
		for ( DomainElement e : this.getOwnedElement() ) {
			if ( e.getName().equals(name) ) {
				return e;
			}
		}
		return result;
	}

}