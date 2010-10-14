package net.hibernatehbmmetamodel;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.util.CompositionNode;

abstract public class HbmElement implements CompositionNode {
	private String qualifiedName;

	/** Default constructor for 
	 */
	public HbmElement() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void copyShallowState(HbmElement from, HbmElement to) {
		to.setQualifiedName(from.getQualifiedName());
	}
	
	public void copyState(HbmElement from, HbmElement to) {
		to.setQualifiedName(from.getQualifiedName());
	}
	
	public void createComponents() {
	}
	
	public HbmElement findOwnedElement(String name) {
		HbmElement result = null;
		result= any2(name);
		return result;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = "";
		return hbmAttributes;
	}
	
	public String getHbmName() {
		String hbmName = "";
		return hbmName;
	}
	
	public String getHbmString2() {
		String result = "";
		result= ("<".concat(this.getHbmName()).concat((" "))).concat(this.getHbmAttributes()).concat(">\n").concat(iterate1()).concat("</").concat(this.getHbmName()).concat(">");
		return result;
	}
	
	public List<HbmElement> getOwnedElement() {
		List<HbmElement> ownedElementSubsetting = new ArrayList<HbmElement>();
		return new ArrayList<HbmElement>();
	}
	
	public HbmElement getOwner() {
		return null;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public String getQualifiedName() {
		return qualifiedName;
	}
	
	public void init(CompositionNode owner) {
		createComponents();
	}
	
	abstract public HbmElement makeCopy();
	
	public void markDeleted() {
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setQualifiedName(String qualifiedName) {
		this.qualifiedName=qualifiedName;
	}
	
	public String toHbmString() {
		String result = "";
		return result;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		if ( getOwner()==null ) {
			sb.append("owner=null;");
		} else {
			sb.append("owner="+getOwner().getClass().getSimpleName()+"[");
			sb.append(getOwner().hashCode());
			sb.append("];");
		}
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		sb.append("qualifiedName=");
		sb.append(getQualifiedName());
		sb.append(";");
		return sb.toString();
	}
	
	public String toXmlString() {
		StringBuilder sb = new StringBuilder();
		if ( getQualifiedName()==null ) {
			sb.append("<qualifiedName/>");
		} else {
			sb.append("<qualifiedName>");
			sb.append(getQualifiedName());
			sb.append("</qualifiedName>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Implements ->any( e : HbmElement | e.qualifiedName = name )
	 * 
	 * @param name 
	 */
	private HbmElement any2(String name) {
		HbmElement result = null;
		for ( HbmElement e : this.getOwnedElement() ) {
			if ( e.getQualifiedName().equals(name) ) {
				return e;
			}
		}
		return result;
	}
	
	/** Implements ->iterate( element : HbmElement; result : String = '' | ''.concat(result).concat(element.getHbmString2()) )
	 */
	private String iterate1() {
		String result = "";
		for ( HbmElement element : this.getOwnedElement() ) {
			result = "".concat(result).concat(element.getHbmString2());
		}
		return result;
	}

}