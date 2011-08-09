package net.sf.nakeduml.metamodel.mapping.internal;

import java.io.Serializable;

import net.sf.nakeduml.metamodel.mapping.IMappingInfo;
import net.sf.nakeduml.metamodel.name.NameWrapper;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;

public abstract class AbstractMappingInfo implements IMappingInfo{
	//TODO simplify
	private boolean isNewInVersion = false;
	private boolean isNewInRevision = false;
	private boolean requiresSqlRename = false;
	private boolean shouldStore;
	private String qualifiedJavaName;
	private String oldQualifiedJavaName;
	private String singularHumanName;
	private String pluralHumanName;
	private String qualifiedUmlName;
	/**
	 * 
	 * A Human readable version of name, generated from the "humanName" and "plural" taggedValues to generate user instructions. In most
	 * cases, except for elements with a multiplicity, this would be a singular name. The plural of this NameWrapper would come from the
	 * taggedValue "plural".
	 */
	protected NameWrapper humanName;
	private NameWrapper javaName;
	private NameWrapper oldPersistentName;
	private NameWrapper persistentName;
	private NameWrapper oldJavaName;
	@Override
	public void setStore(boolean store){
		this.shouldStore = store;
	}
	@Override
	public boolean shouldStore(){
		return shouldStore;
	}
	/**
	 * Calculates whether this originalElement should be added to the database 1. For this revision given the currently deployed revision in the
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
	public boolean requiresJavaRename(){
		return oldQualifiedJavaName!=null && !oldQualifiedJavaName.equals(qualifiedJavaName);
	}
	public boolean requiresPersistentRename(){
		return oldPersistentName!=null && !oldPersistentName.equals(oldPersistentName);
	}
	public boolean hasMappingInfo(){
		return hasSinceVersion();
	}
	public boolean hasPersistentName(){
		return getPersistentName()!=null && getPersistentName().getAsIs()!=null && getPersistentName().getAsIs().trim().length()>0;
	}
	public NameWrapper getJavaName(){
		return this.javaName;
	}
	public void setJavaName(NameWrapper javaName){
		this.oldJavaName=this.javaName;
		this.javaName = javaName;
	}
	public NameWrapper getPersistentName(){
		return this.persistentName;
	}
	public void setPersistentName(NameWrapper sqlName){
		this.oldPersistentName=this.persistentName;
		this.persistentName = sqlName;
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
	public String getQualifiedJavaName(){
		return qualifiedJavaName;
	}
	public void setQualifiedJavaName(String qualifiedJavaName){
		this.oldQualifiedJavaName=this.qualifiedJavaName;
		this.qualifiedJavaName = qualifiedJavaName;
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
	public String getOldQualifiedJavaName(){
		return oldQualifiedJavaName;
	}
	public NameWrapper getOldPersistentName(){
		return oldPersistentName;
	}
	public NameWrapper getOldJavaName(){
		return oldJavaName;
	}
	protected abstract AbstractMappingInfo createCopy();
}
