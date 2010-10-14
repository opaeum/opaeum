package net.hibernatehbmmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class Discriminator extends HbmElement implements CompositionNode {
	private Boolean insert = false;
	private HbmClass hbmClass;
	private Boolean force = false;
	private String hbmName;
	private Boolean notNull = false;
	private String column;

	/** Default constructor for 
	 */
	public Discriminator() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Discriminator(HbmClass owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getHbmClass().setDiscriminator((Discriminator)this);
	}
	
	public String getColumn() {
		return column;
	}
	
	public Boolean getForce() {
		return force;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("force=\"".concat((this.getForce() ?
			"true" :
			"false"))).concat(("\" ")).concat(("insert=\"".concat((this.getInsert() ?
			"true" :
			"false"))).concat(("\" "))).concat(("not-null=\"".concat((this.getNotNull() ?
			"true" :
			"false"))).concat(("\" "))).concat("column=\"").concat(this.getColumn()).concat(("\" "));
		return hbmAttributes;
	}
	
	public HbmClass getHbmClass() {
		return hbmClass;
	}
	
	public String getHbmName() {
		return hbmName;
	}
	
	public Boolean getInsert() {
		return insert;
	}
	
	public Boolean getNotNull() {
		return notNull;
	}
	
	public HbmElement getOwner() {
		HbmElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getHbmClass()!=null ) {
			ownerSubsetting=getHbmClass();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getHbmClass();
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((HbmClass)owner);
		this.setHbmName( "discriminator" );
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getHbmClass()!=null ) {
			getHbmClass().setDiscriminator(null);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setColumn(String column) {
		this.column=column;
	}
	
	public void setForce(Boolean force) {
		this.force=force;
	}
	
	public void setHbmClass(HbmClass hbmClass) {
		HbmClass oldValue = this.hbmClass;
		if ( oldValue==null ) {
			this.hbmClass=hbmClass;
			if ( hbmClass!=null ) {
				hbmClass.z_internalAddToDiscriminator((Discriminator)this);
			}
		} else {
			if ( !oldValue.equals(hbmClass) ) {
				this.hbmClass=hbmClass;
				oldValue.z_internalRemoveFromDiscriminator(this);
				if ( hbmClass!=null ) {
					hbmClass.z_internalAddToDiscriminator((Discriminator)this);
				}
			}
		}
	}
	
	public void setHbmName(String hbmName) {
		this.hbmName=hbmName;
	}
	
	public void setInsert(Boolean insert) {
		this.insert=insert;
	}
	
	public void setNotNull(Boolean notNull) {
		this.notNull=notNull;
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
		sb.append("force=");
		sb.append(getForce());
		sb.append(";");
		sb.append("insert=");
		sb.append(getInsert());
		sb.append(";");
		sb.append("notNull=");
		sb.append(getNotNull());
		sb.append(";");
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		if ( getHbmClass()==null ) {
			sb.append("hbmClass=null;");
		} else {
			sb.append("hbmClass="+getHbmClass().getClass().getSimpleName()+"[");
			sb.append(getHbmClass().getName());
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
		if ( getForce()==null ) {
			sb.append("<force/>");
		} else {
			sb.append("<force>");
			sb.append(getForce());
			sb.append("</force>");
			sb.append("\n");
		}
		if ( getInsert()==null ) {
			sb.append("<insert/>");
		} else {
			sb.append("<insert>");
			sb.append(getInsert());
			sb.append("</insert>");
			sb.append("\n");
		}
		if ( getNotNull()==null ) {
			sb.append("<notNull/>");
		} else {
			sb.append("<notNull>");
			sb.append(getNotNull());
			sb.append("</notNull>");
			sb.append("\n");
		}
		if ( getHbmName()==null ) {
			sb.append("<hbmName/>");
		} else {
			sb.append("<hbmName>");
			sb.append(getHbmName());
			sb.append("</hbmName>");
			sb.append("\n");
		}
		if ( getHbmClass()==null ) {
			sb.append("<hbmClass/>");
		} else {
			sb.append("<hbmClass>");
			sb.append(getHbmClass().getClass().getSimpleName());
			sb.append("[");
			sb.append(getHbmClass().getName());
			sb.append("]");
			sb.append("</hbmClass>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void z_internalAddToHbmClass(HbmClass hbmClass) {
		if ( getHbmClass()==null || !getHbmClass().equals(hbmClass) ) {
			this.hbmClass=hbmClass;
		}
	}
	
	public void z_internalRemoveFromHbmClass(HbmClass hbmClass) {
		if ( getHbmClass()!=null && getHbmClass().equals(hbmClass) ) {
			this.hbmClass=null;
		}
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(HbmClass newOwner) {
		this.hbmClass=newOwner;
	}
	
	public void copyShallowState(Discriminator from, Discriminator to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setColumn(from.getColumn());
		to.setForce(from.getForce());
		to.setInsert(from.getInsert());
		to.setNotNull(from.getNotNull());
		to.setHbmName(from.getHbmName());
	}
	
	public void copyState(Discriminator from, Discriminator to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setColumn(from.getColumn());
		to.setForce(from.getForce());
		to.setInsert(from.getInsert());
		to.setNotNull(from.getNotNull());
		to.setHbmName(from.getHbmName());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public Discriminator makeCopy() {
		Discriminator result = new Discriminator();
		copyState((Discriminator)this,result);
		return result;
	}

}