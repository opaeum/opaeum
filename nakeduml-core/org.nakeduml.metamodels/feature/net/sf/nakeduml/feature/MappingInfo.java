package net.sf.nakeduml.feature;

import java.text.DecimalFormat;
import java.util.Scanner;

import net.sf.nakeduml.metamodel.name.NameWrapper;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;

public class MappingInfo {
	private static final long serialVersionUID = -3340080927536252411L;
	protected static final String DEL = "~";
	public static void main(String[] args){
		String values = "12~1.3213~1~qualifiedJavaName~javaNameString~sqlNameString~SingularHumanName~pluralHumanName~qualifiedUmlName~qualifiedPersistentName";
		MappingInfo m1 = new MappingInfo("", values);
		System.out.println(new MappingInfo("", m1.toString()).toString().equals(m1.toString()));
	}
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
	protected String idInModel;
	protected Integer sinceRevision;
	protected Float sinceVersion;
	protected Integer nakedUmlId;
	protected String qualifiedPersistentName;
	private NameWrapper oldJavaName;
	public MappingInfo(String idInModel,String values){
		this.idInModel = idInModel;
		Scanner scanner = new Scanner(values).useDelimiter(DEL);
		this.sinceRevision = scanner.nextInt();
		this.sinceVersion = scanner.nextFloat();
		this.nakedUmlId = scanner.nextInt();
		setPersistentName(new SingularNameWrapper(scanner.next(),null));
		this.qualifiedPersistentName = scanner.next();
	}
	public MappingInfo(){
	}
	public void setStore(boolean store){
		this.shouldStore = store;
	}
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
	public MappingInfo getCopy(){
		MappingInfo result = createCopy();
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
	public String getIdInModel(){
		return idInModel;
	}
	public void setIdInModel(String idInModel){
		this.idInModel = idInModel;
	}
	public Integer getSinceRevision(){
		return sinceRevision;
	}
	public void setSinceRevision(Integer sinceRevision){
		this.sinceRevision = sinceRevision;
	}
	public Float getSinceVersion(){
		return sinceVersion;
	}
	public void setSinceVersion(Float sinceVersion){
		this.sinceVersion = sinceVersion;
	}
	public Integer getNakedUmlId(){
		return nakedUmlId;
	}
	public void setNakedUmlId(Integer nakedUmlId){
		this.nakedUmlId = nakedUmlId;
	}
	public String toString(){
		return "" + sinceRevision + DEL + new DecimalFormat("#0.0000000").format(sinceVersion) + DEL + nakedUmlId + DEL + getPersistentName() + DEL 
				+ qualifiedPersistentName+DEL;
	}
	protected MappingInfo createCopy(){
		return new MappingInfo(idInModel,toString());
	}
	public String getQualifiedPersistentName(){
		return qualifiedPersistentName;
	}
	public void setQualifiedPersistentName(String string){
		this.qualifiedPersistentName = string;
	}
}
