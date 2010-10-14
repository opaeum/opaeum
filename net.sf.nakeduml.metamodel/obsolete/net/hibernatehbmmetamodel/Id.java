package net.hibernatehbmmetamodel;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.util.CompositionNode;

public class Id extends HbmElement implements CompositionNode {
	private String type;
	private HbmClass hbmClass;
	private Column column;
	private Generator generator;
	private String hbmName;
	private String name;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Id(HbmClass owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public Id() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getHbmClass().setId((Id)this);
	}
	
	public void copyShallowState(Id from, Id to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setColumn(getColumn());
		to.setGenerator(getGenerator());
		to.setName(from.getName());
		to.setType(from.getType());
		to.setHbmName(from.getHbmName());
	}
	
	public void copyState(Id from, Id to) {
		to.setQualifiedName(from.getQualifiedName());
		if ( from.getColumn()!=null ) {
			to.setColumn(from.getColumn().makeCopy());
		}
		if ( from.getGenerator()!=null ) {
			to.setGenerator(from.getGenerator().makeCopy());
		}
		to.setName(from.getName());
		to.setType(from.getType());
		to.setHbmName(from.getHbmName());
	}
	
	public Column createColumn() {
		Column newInstance= new Column();
		newInstance.init(this);
		return newInstance;
	}
	
	public Generator createGenerator() {
		Generator newInstance= new Generator();
		newInstance.init(this);
		return newInstance;
	}
	
	public Column getColumn() {
		return column;
	}
	
	public Generator getGenerator() {
		return generator;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("name=\"".concat(this.getName()).concat(("\" "))).concat(("type=\"".concat(this.getType()).concat(("\" "))));
		return hbmAttributes;
	}
	
	public HbmClass getHbmClass() {
		return hbmClass;
	}
	
	public String getHbmName() {
		return hbmName;
	}
	
	public String getHbmString2() {
		String result = "";
		result= ("<".concat(this.getHbmName()).concat((" "))).concat(this.getHbmAttributes()).concat(">").concat("\n").concat(this.getColumn().getHbmString2()).concat(this.getGenerator().getHbmString2()).concat("\n").concat("</").concat(this.getHbmName()).concat(">");
		return result;
	}
	
	public String getName() {
		return name;
	}
	
	public List<HbmElement> getOwnedElement() {
		List<HbmElement> ownedElementSubsetting = new ArrayList<HbmElement>();
		ownedElementSubsetting.addAll(super.getOwnedElement());
		if ( getColumn()!=null ) {
			ownedElementSubsetting.add(getColumn());
		}
		if ( getGenerator()!=null ) {
			ownedElementSubsetting.add(getGenerator());
		}
		return ownedElementSubsetting;
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
	
	public String getType() {
		return type;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((HbmClass)owner);
		this.setHbmName( "id" );
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getColumn()!=null ) {
			getColumn().setId(null);
		}
		if ( getGenerator()!=null ) {
			getGenerator().setId(null);
		}
		if ( getHbmClass()!=null ) {
			getHbmClass().setId(null);
		}
		getColumn().markDeleted();
		getGenerator().markDeleted();
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setColumn(Column column) {
		Column oldValue = this.column;
		if ( oldValue==null ) {
			this.column=column;
			if ( column!=null ) {
				column.z_internalAddToId((Id)this);
			}
		} else {
			if ( !oldValue.equals(column) ) {
				this.column=column;
				oldValue.z_internalRemoveFromId(this);
				if ( column!=null ) {
					column.z_internalAddToId((Id)this);
				}
			}
		}
	}
	
	public void setGenerator(Generator generator) {
		Generator oldValue = this.generator;
		if ( oldValue==null ) {
			this.generator=generator;
			if ( generator!=null ) {
				generator.z_internalAddToId((Id)this);
			}
		} else {
			if ( !oldValue.equals(generator) ) {
				this.generator=generator;
				oldValue.z_internalRemoveFromId(this);
				if ( generator!=null ) {
					generator.z_internalAddToId((Id)this);
				}
			}
		}
	}
	
	public void setHbmClass(HbmClass hbmClass) {
		HbmClass oldValue = this.hbmClass;
		if ( oldValue==null ) {
			this.hbmClass=hbmClass;
			if ( hbmClass!=null ) {
				hbmClass.z_internalAddToId((Id)this);
			}
		} else {
			if ( !oldValue.equals(hbmClass) ) {
				this.hbmClass=hbmClass;
				oldValue.z_internalRemoveFromId(this);
				if ( hbmClass!=null ) {
					hbmClass.z_internalAddToId((Id)this);
				}
			}
		}
	}
	
	public void setHbmName(String hbmName) {
		this.hbmName=hbmName;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setType(String type) {
		this.type=type;
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
		if ( getColumn()==null ) {
			sb.append("column=null;");
		} else {
			sb.append("column="+getColumn().getClass().getSimpleName()+"[");
			sb.append(getColumn().getName());
			sb.append("];");
		}
		if ( getGenerator()==null ) {
			sb.append("generator=null;");
		} else {
			sb.append("generator="+getGenerator().getClass().getSimpleName()+"[");
			sb.append(getGenerator().hashCode());
			sb.append("];");
		}
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		sb.append("type=");
		sb.append(getType());
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
			sb.append(getColumn().toXmlString());
			sb.append("</column>");
			sb.append("\n");
		}
		if ( getGenerator()==null ) {
			sb.append("<generator/>");
		} else {
			sb.append("<generator>");
			sb.append(getGenerator().toXmlString());
			sb.append("</generator>");
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
		if ( getType()==null ) {
			sb.append("<type/>");
		} else {
			sb.append("<type>");
			sb.append(getType());
			sb.append("</type>");
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
	
	public void z_internalAddToColumn(Column column) {
		if ( getColumn()==null || !getColumn().equals(column) ) {
			this.column=column;
		}
	}
	
	public void z_internalAddToGenerator(Generator generator) {
		if ( getGenerator()==null || !getGenerator().equals(generator) ) {
			this.generator=generator;
		}
	}
	
	public void z_internalAddToHbmClass(HbmClass hbmClass) {
		if ( getHbmClass()==null || !getHbmClass().equals(hbmClass) ) {
			this.hbmClass=hbmClass;
		}
	}
	
	public void z_internalRemoveFromColumn(Column column) {
		if ( getColumn()!=null && getColumn().equals(column) ) {
			this.column=null;
		}
	}
	
	public void z_internalRemoveFromGenerator(Generator generator) {
		if ( getGenerator()!=null && getGenerator().equals(generator) ) {
			this.generator=null;
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
	
	public void createComponents() {
		super.createComponents();
	}
	
	public Id makeCopy() {
		Id result = new Id();
		copyState((Id)this,result);
		return result;
	}

}