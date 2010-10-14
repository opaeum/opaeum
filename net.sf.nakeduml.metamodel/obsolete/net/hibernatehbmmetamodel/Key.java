package net.hibernatehbmmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class Key extends HbmElement implements CompositionNode {
	private Join join;
	private String column;

	/** Default constructor for 
	 */
	public Key() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Key(Join owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getJoin().setKey((Key)this);
	}
	
	public String getColumn() {
		return column;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("column=\"".concat(this.getColumn()).concat(("\" ")));
		return hbmAttributes;
	}
	
	public String getHbmName() {
		String hbmName = "key";
		return hbmName;
	}
	
	public Join getJoin() {
		return join;
	}
	
	public HbmElement getOwner() {
		HbmElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getJoin()!=null ) {
			ownerSubsetting=getJoin();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getJoin();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((Join)owner);
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getJoin()!=null ) {
			getJoin().setKey(null);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setColumn(String column) {
		this.column=column;
	}
	
	public void setJoin(Join join) {
		Join oldValue = this.join;
		if ( oldValue==null ) {
			this.join=join;
			if ( join!=null ) {
				join.z_internalAddToKey((Key)this);
			}
		} else {
			if ( !oldValue.equals(join) ) {
				this.join=join;
				oldValue.z_internalRemoveFromKey(this);
				if ( join!=null ) {
					join.z_internalAddToKey((Key)this);
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
		sb.append("column=");
		sb.append(getColumn());
		sb.append(";");
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		if ( getJoin()==null ) {
			sb.append("join=null;");
		} else {
			sb.append("join="+getJoin().getClass().getSimpleName()+"[");
			sb.append(getJoin().hashCode());
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
		if ( getColumn()==null ) {
			sb.append("<column/>");
		} else {
			sb.append("<column>");
			sb.append(getColumn());
			sb.append("</column>");
			sb.append("\n");
		}
		if ( getJoin()==null ) {
			sb.append("<join/>");
		} else {
			sb.append("<join>");
			sb.append(getJoin().getClass().getSimpleName());
			sb.append("[");
			sb.append(getJoin().hashCode());
			sb.append("]");
			sb.append("</join>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void z_internalAddToJoin(Join join) {
		if ( getJoin()==null || !getJoin().equals(join) ) {
			this.join=join;
		}
	}
	
	public void z_internalRemoveFromJoin(Join join) {
		if ( getJoin()!=null && getJoin().equals(join) ) {
			this.join=null;
		}
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(Join newOwner) {
		this.join=newOwner;
	}
	
	public void copyShallowState(Key from, Key to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setColumn(from.getColumn());
	}
	
	public void copyState(Key from, Key to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setColumn(from.getColumn());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public Key makeCopy() {
		Key result = new Key();
		copyState((Key)this,result);
		return result;
	}

}