package net.hibernatehbmmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class AbstractColumn extends HbmElement implements CompositionNode {
	private String name;

	/** Default constructor for 
	 */
	public AbstractColumn() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
	}
	
	public void copyShallowState(AbstractColumn from, AbstractColumn to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setName(from.getName());
	}
	
	public void copyState(AbstractColumn from, AbstractColumn to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setName(from.getName());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("name=\"".concat(this.getName()).concat(("\" ")));
		return hbmAttributes;
	}
	
	public String getHbmName() {
		String hbmName = "column";
		return hbmName;
	}
	
	public String getName() {
		return name;
	}
	
	public CompositionNode getOwningObject() {
		return null;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		createComponents();
	}
	
	public AbstractColumn makeCopy() {
		AbstractColumn result = new AbstractColumn();
		copyState((AbstractColumn)this,result);
		return result;
	}
	
	public void markDeleted() {
		super.markDeleted();
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setName(String name) {
		this.name=name;
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
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		sb.append("hbmName=");
		sb.append(getHbmName());
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
		if ( getName()==null ) {
			sb.append("<name/>");
		} else {
			sb.append("<name>");
			sb.append(getName());
			sb.append("</name>");
			sb.append("\n");
		}
		return sb.toString();
	}

}