package net.hibernatehbmmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class Column extends AbstractColumn implements CompositionNode {
	private Id id;

	/** Default constructor for 
	 */
	public Column() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Column(Id owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getId().setColumn((Column)this);
	}
	
	public Id getId() {
		return id;
	}
	
	public HbmElement getOwner() {
		HbmElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getId()!=null ) {
			ownerSubsetting=getId();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getId();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((Id)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getId()!=null ) {
			getId().setColumn(null);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setId(Id id) {
		Id oldValue = this.id;
		if ( oldValue==null ) {
			this.id=id;
			if ( id!=null ) {
				id.z_internalAddToColumn((Column)this);
			}
		} else {
			if ( !oldValue.equals(id) ) {
				this.id=id;
				oldValue.z_internalRemoveFromColumn(this);
				if ( id!=null ) {
					id.z_internalAddToColumn((Column)this);
				}
			}
		}
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
		if ( getId()==null ) {
			sb.append("id=null;");
		} else {
			sb.append("id="+getId().getClass().getSimpleName()+"[");
			sb.append(getId().getName());
			sb.append("];");
		}
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
		if ( getId()==null ) {
			sb.append("<id/>");
		} else {
			sb.append("<id>");
			sb.append(getId().getClass().getSimpleName());
			sb.append("[");
			sb.append(getId().getName());
			sb.append("]");
			sb.append("</id>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void z_internalAddToId(Id id) {
		if ( getId()==null || !getId().equals(id) ) {
			this.id=id;
		}
	}
	
	public void z_internalRemoveFromId(Id id) {
		if ( getId()!=null && getId().equals(id) ) {
			this.id=null;
		}
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(Id newOwner) {
		this.id=newOwner;
	}
	
	public void copyShallowState(Column from, Column to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setName(from.getName());
	}
	
	public void copyState(Column from, Column to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setName(from.getName());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public Column makeCopy() {
		Column result = new Column();
		copyState((Column)this,result);
		return result;
	}

}