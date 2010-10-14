package net.hibernatehbmmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class Generator extends HbmElement implements CompositionNode {
	private Id id;
	private String hbmName;
	private GeneratorClass generatorClass;

	/** Default constructor for 
	 */
	public Generator() {
	}
	
	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public Generator(Id owningObject) {
		init(owningObject);
		addToOwningObject();
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getId().setGenerator((Generator)this);
	}
	
	public GeneratorClass getGeneratorClass() {
		return generatorClass;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("class=\"".concat(this.getGeneratorClass().getGeneratorClassName()).concat(("\" ")));
		return hbmAttributes;
	}
	
	public String getHbmName() {
		return hbmName;
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
		this.setHbmName( "generator" );
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getId()!=null ) {
			getId().setGenerator(null);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setGeneratorClass(GeneratorClass generatorClass) {
		this.generatorClass=generatorClass;
	}
	
	public void setHbmName(String hbmName) {
		this.hbmName=hbmName;
	}
	
	public void setId(Id id) {
		Id oldValue = this.id;
		if ( oldValue==null ) {
			this.id=id;
			if ( id!=null ) {
				id.z_internalAddToGenerator((Generator)this);
			}
		} else {
			if ( !oldValue.equals(id) ) {
				this.id=id;
				oldValue.z_internalRemoveFromGenerator(this);
				if ( id!=null ) {
					id.z_internalAddToGenerator((Generator)this);
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
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		if ( getId()==null ) {
			sb.append("id=null;");
		} else {
			sb.append("id="+getId().getClass().getSimpleName()+"[");
			sb.append(getId().getName());
			sb.append("];");
		}
		sb.append("generatorClass=");
		sb.append(getGeneratorClass());
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
		if ( getHbmName()==null ) {
			sb.append("<hbmName/>");
		} else {
			sb.append("<hbmName>");
			sb.append(getHbmName());
			sb.append("</hbmName>");
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
		if ( getGeneratorClass()==null ) {
			sb.append("<generatorClass/>");
		} else {
			sb.append("<generatorClass>");
			sb.append(getGeneratorClass());
			sb.append("</generatorClass>");
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
	
	public void copyShallowState(Generator from, Generator to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setHbmName(from.getHbmName());
		to.setGeneratorClass(from.getGeneratorClass());
	}
	
	public void copyState(Generator from, Generator to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setHbmName(from.getHbmName());
		to.setGeneratorClass(from.getGeneratorClass());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public Generator makeCopy() {
		Generator result = new Generator();
		copyState((Generator)this,result);
		return result;
	}

}