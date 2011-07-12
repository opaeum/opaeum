package net.sf.nakeduml.metamodel.mapping.internal;

import java.io.Serializable;

import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;

public abstract class AbstractMappingInfo implements IMappingInfo,Serializable{
	//TODO simplify
	private transient boolean isNewInVersion = false;
	private transient boolean isNewInRevision = false;
	private transient boolean requiresSqlRename = false;
	private transient boolean shouldStore;
	private transient String qualifiedJavaName;
	private transient String javaNameString;
	private transient String singularHumanName;
	private transient String pluralHumanName;
	private transient String qualifiedUmlName;
	@Override
	public void setStore(boolean store){
		this.shouldStore = store;
	}
	@Override
	public boolean shouldStore(){
		return shouldStore;
	}
	/**
	 * 
	 * A Human readable version of name, generated from the "humanName" and "plural" taggedValues to generate user instructions. In most
	 * cases, except for elements with a multiplicity, this would be a singular name. The plural of this NameWrapper would come from the
	 * taggedValue "plural".
	 */
	protected transient NameWrapper humanName;
	private transient NameWrapper javaName;
	private transient NameWrapper sqlName;
	/**
	 * Calculates whether this element should be added to the database 1. For this revision given the currently deployed revision in the
	 * database 2. For this version, given the currently deployed version in the database
	 * 
	 * @param deployedVersion
	 * @param deployedRevision
	 */
	public void calculateMigrationRequirements(float deployedVersion,int deployedRevision){
		if(getSinceVersion() == null || getSinceVersion().floatValue() > deployedVersion || deployedVersion == 0){
			this.isNewInVersion = true;
		}
		if(getSinceRevision() == null || getSinceRevision().intValue() > deployedRevision || deployedRevision == 0){
			this.isNewInRevision = true;
		}
	}
	public boolean isNewInRevision(){
		return this.isNewInRevision;
	}
	public boolean isNewInVersion(){
		return this.isNewInVersion;
	}
	public boolean isRequiresSqlRename(){
		return this.requiresSqlRename;
	}
	public void setRequiresSqlRename(boolean requiresSqlRename){
		this.requiresSqlRename = requiresSqlRename;
	}
	/**
	 * Returns a '/'- delimited path using the java names of the consituents of the path
	 * 
	 * @return
	 */
	public String getJavaPath(){
		return getQualifiedJavaName().replace('.', '/');
	}
	/**
	 * 
	 * If the getSinceVersion() and/or sinceRevision have not been set yet, then this will set them presumably for the first time
	 * Additionally, it updates the supporting tagged values
	 * 
	 * @param version
	 * @param revision
	 */
	public void updateVersionInfo(float version,int revision){
		if(!hasSinceVersion()){
			setSinceVersion(new Float(version));
		}
		if(!hasSinceRevision()){
			setSinceRevision(new Integer(revision));
		}
	}
	protected boolean hasSinceRevision(){
		return getSinceVersion() != null;
	}
	protected boolean hasSinceVersion(){
		return getSinceVersion() != null;
	}
	public boolean hasMappingInfo(){
		return hasSinceVersion();
	}
	public boolean hasPersistentName(){
		return getSqlNameString() != null &&getSqlNameString().trim().length()>0;
	}
	public NameWrapper getJavaName(){
		if(this.javaName == null){
			this.javaName = new SingularNameWrapper(getJavaNameString(), null);
		}
		return this.javaName;
	}
	public void setJavaName(NameWrapper javaName){
		this.javaName = javaName;
		setJavaNameString(javaName.getAsIs());
	}
	public NameWrapper getPersistentName(){
		if(this.sqlName == null){
			this.sqlName = new SingularNameWrapper(getSqlNameString(), null);
		}
		return this.sqlName;
	}
	public void setPersistentName(NameWrapper sqlName){
		this.sqlName = sqlName;
		setSqlNameString(sqlName.getAsIs());
	}
	public NameWrapper getHumanName(){
		if(this.humanName == null){
			this.humanName = new SingularNameWrapper(getSingularHumanName(), getPluralHumanName());
		}
		return this.humanName;
	}
	public void setHumanName(NameWrapper humanName){
		this.humanName = humanName;
		setSingularHumanName(humanName.getSingular().getAsIs());
		setPluralHumanName(humanName.getPlural().getAsIs());
	}
	public IMappingInfo getCopy(){
		IMappingInfo result = createCopy();
		result.setHumanName(getHumanName());
		result.setIdInModel(getIdInModel());
		result.setJavaName(getJavaName());
		result.setPersistentName(getPersistentName());
		result.setQualifiedJavaName(getQualifiedJavaName());
		result.setQualifiedUmlName(getQualifiedUmlName());
		result.setRequiresSqlRename(isRequiresSqlRename());
		result.setSinceRevision(getSinceRevision());
		result.setSinceVersion(getSinceVersion());
		return result;
	}
	protected abstract void setSqlNameString(String name);
	protected abstract String getSqlNameString();
	public String getQualifiedJavaName(){
		return qualifiedJavaName;
	}
	public void setQualifiedJavaName(String qualifiedJavaName){
		this.qualifiedJavaName = qualifiedJavaName;
	}
	public String getJavaNameString(){
		return javaNameString;
	}
	public String getSingularHumanName(){
		return singularHumanName;
	}
	public void setSingularHumanName(String singularHumanName){
		this.singularHumanName = singularHumanName;
	}
	public String getPluralHumanName(){
		return pluralHumanName;
	}
	public void setPluralHumanName(String pluralHumanName){
		this.pluralHumanName = pluralHumanName;
	}
	public String getQualifiedUmlName(){
		return qualifiedUmlName;
	}
	public void setQualifiedUmlName(String qualifiedUmlName){
		this.qualifiedUmlName = qualifiedUmlName;
	}
	public void setJavaNameString(String javaNameString){
		this.javaNameString = javaNameString;
	}
	protected abstract AbstractMappingInfo createCopy();
}
