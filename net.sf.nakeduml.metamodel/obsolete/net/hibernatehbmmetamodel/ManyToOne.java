package net.hibernatehbmmetamodel;

import net.sf.nakeduml.util.CompositionNode;

public class ManyToOne extends HbmElement implements CompositionNode {
	private String column;
	private Access access;
	private String uniqueKey;
	private AbstractClass abstractClass;
	private String className;
	private String name;
	private String entityName;
	private ManyToOneLazy manyToOneLazy;

	/** This constructor is intended for easy initialization in unit tests
	 * 
	 * @param owningObject 
	 */
	public ManyToOne(AbstractClass owningObject) {
		init(owningObject);
		addToOwningObject();
	}
	
	/** Default constructor for 
	 */
	public ManyToOne() {
	}

	/** Call this method when you want to attach this object to the containment tree. Useful with transitive persistence
	 */
	public void addToOwningObject() {
		getAbstractClass().getManyToOne().add((ManyToOne)this);
	}
	
	public AbstractClass getAbstractClass() {
		return abstractClass;
	}
	
	public Access getAccess() {
		return access;
	}
	
	public String getClassName() {
		return className;
	}
	
	public String getColumn() {
		return column;
	}
	
	public String getEntityName() {
		return entityName;
	}
	
	public String getHbmAttributes() {
		String hbmAttributes = ("class=\"".concat(this.getClassName()).concat(("\" "))).concat(("name=\"".concat(this.getName()).concat(("\" ")))).concat(("entity-name=\"".concat(this.getEntityName()).concat(("\" ")))).concat(("access=\"".concat(this.getAccess().getAccessName()).concat(("\" ")))).concat((this.getUniqueKey().equals("") ?
			"" :
			("unique-key=\"".concat(this.getUniqueKey()).concat(("\" "))))).concat((this.getColumn().equals("") ?
			"" :
			("column=\"".concat(this.getColumn()).concat(("\" "))))).concat("lazy=\"").concat(this.getManyToOneLazy().getManyToOneLazyValue()).concat(("\" "));
		return hbmAttributes;
	}
	
	public String getHbmName() {
		String hbmName = "many-to-one";
		return hbmName;
	}
	
	public ManyToOneLazy getManyToOneLazy() {
		return manyToOneLazy;
	}
	
	public String getName() {
		return name;
	}
	
	public HbmElement getOwner() {
		HbmElement ownerSubsetting = null;
		ownerSubsetting=super.getOwner();
		if ( getAbstractClass()!=null ) {
			ownerSubsetting=getAbstractClass();
		}
		return ownerSubsetting;
	}
	
	public CompositionNode getOwningObject() {
		return getAbstractClass();
	}
	
	public String getUniqueKey() {
		return uniqueKey;
	}
	
	public void init(CompositionNode owner) {
		super.init(owner);
		internalSetOwner((AbstractClass)owner);
		this.setColumn( "" );
		this.setUniqueKey( "" );
		createComponents();
	}
	
	public void markDeleted() {
		super.markDeleted();
		if ( getAbstractClass()!=null ) {
			getAbstractClass().getManyToOne().remove((ManyToOne)this);
		}
	}
	
	public void removeFromOwningObject() {
		this.markDeleted();
	}
	
	public void setAbstractClass(AbstractClass abstractClass) {
		if ( this.abstractClass!=null ) {
			this.abstractClass.getManyToOne().remove((ManyToOne)this);
		}
		if ( abstractClass!=null ) {
			abstractClass.getManyToOne().add((ManyToOne)this);
			this.abstractClass=abstractClass;
		} else {
			this.abstractClass=null;
		}
	}
	
	public void setAccess(Access access) {
		this.access=access;
	}
	
	public void setClassName(String className) {
		this.className=className;
	}
	
	public void setColumn(String column) {
		this.column=column;
	}
	
	public void setEntityName(String entityName) {
		this.entityName=entityName;
	}
	
	public void setManyToOneLazy(ManyToOneLazy manyToOneLazy) {
		this.manyToOneLazy=manyToOneLazy;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey=uniqueKey;
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
		sb.append("className=");
		sb.append(getClassName());
		sb.append(";");
		sb.append("name=");
		sb.append(getName());
		sb.append(";");
		sb.append("hbmName=");
		sb.append(getHbmName());
		sb.append(";");
		sb.append("hbmAttributes=");
		sb.append(getHbmAttributes());
		sb.append(";");
		sb.append("entityName=");
		sb.append(getEntityName());
		sb.append(";");
		sb.append("uniqueKey=");
		sb.append(getUniqueKey());
		sb.append(";");
		sb.append("column=");
		sb.append(getColumn());
		sb.append(";");
		sb.append("access=");
		sb.append(getAccess());
		sb.append(";");
		if ( getAbstractClass()==null ) {
			sb.append("abstractClass=null;");
		} else {
			sb.append("abstractClass="+getAbstractClass().getClass().getSimpleName()+"[");
			sb.append(getAbstractClass().hashCode());
			sb.append("];");
		}
		sb.append("manyToOneLazy=");
		sb.append(getManyToOneLazy());
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
		if ( getClassName()==null ) {
			sb.append("<className/>");
		} else {
			sb.append("<className>");
			sb.append(getClassName());
			sb.append("</className>");
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
		if ( getEntityName()==null ) {
			sb.append("<entityName/>");
		} else {
			sb.append("<entityName>");
			sb.append(getEntityName());
			sb.append("</entityName>");
			sb.append("\n");
		}
		if ( getUniqueKey()==null ) {
			sb.append("<uniqueKey/>");
		} else {
			sb.append("<uniqueKey>");
			sb.append(getUniqueKey());
			sb.append("</uniqueKey>");
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
		if ( getAccess()==null ) {
			sb.append("<access/>");
		} else {
			sb.append("<access>");
			sb.append(getAccess());
			sb.append("</access>");
			sb.append("\n");
		}
		if ( getAbstractClass()==null ) {
			sb.append("<abstractClass/>");
		} else {
			sb.append("<abstractClass>");
			sb.append(getAbstractClass().getClass().getSimpleName());
			sb.append("[");
			sb.append(getAbstractClass().hashCode());
			sb.append("]");
			sb.append("</abstractClass>");
			sb.append("\n");
		}
		if ( getManyToOneLazy()==null ) {
			sb.append("<manyToOneLazy/>");
		} else {
			sb.append("<manyToOneLazy>");
			sb.append(getManyToOneLazy());
			sb.append("</manyToOneLazy>");
			sb.append("\n");
		}
		return sb.toString();
	}
	
	/** Used to set the owner internally in extended composition semantics
	 * 
	 * @param newOwner 
	 */
	protected void internalSetOwner(AbstractClass newOwner) {
		this.abstractClass=newOwner;
	}
	
	public void copyShallowState(ManyToOne from, ManyToOne to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setClassName(from.getClassName());
		to.setName(from.getName());
		to.setEntityName(from.getEntityName());
		to.setUniqueKey(from.getUniqueKey());
		to.setColumn(from.getColumn());
		to.setAccess(from.getAccess());
		to.setManyToOneLazy(from.getManyToOneLazy());
	}
	
	public void copyState(ManyToOne from, ManyToOne to) {
		to.setQualifiedName(from.getQualifiedName());
		to.setClassName(from.getClassName());
		to.setName(from.getName());
		to.setEntityName(from.getEntityName());
		to.setUniqueKey(from.getUniqueKey());
		to.setColumn(from.getColumn());
		to.setAccess(from.getAccess());
		to.setManyToOneLazy(from.getManyToOneLazy());
	}
	
	public void createComponents() {
		super.createComponents();
	}
	
	public ManyToOne makeCopy() {
		ManyToOne result = new ManyToOne();
		copyState((ManyToOne)this,result);
		return result;
	}

}