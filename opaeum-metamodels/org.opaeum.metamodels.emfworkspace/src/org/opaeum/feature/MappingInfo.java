package org.opaeum.feature;

import java.text.DecimalFormat;
import java.util.Scanner;

import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.metamodel.name.SingularNameWrapper;

public class MappingInfo{
	protected static final String DEL = "~";
	public static void main(String[] args){
		String values = "12~1.3213~1~qualifiedJavaName~javaNameString~sqlNameString~SingularHumanName~pluralHumanName~qualifiedUmlName~qualifiedPersistentName";
		MappingInfo m1 = new MappingInfo("", values);
		System.out.println(new MappingInfo("", m1.toString()).toString().equals(m1.toString()));
	}
	// TODO simplify
	private boolean isVersioned = true;
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
		setPersistentName(new SingularNameWrapper(scanner.next(), null));
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
	 * Returns a '/'- delimited path using the java names of the consituents of the path
	 * 
	 * @return
	 */
	public String getJavaPath(){
		return getQualifiedJavaName().replace('.', '/');
	}
	public boolean requiresJavaRename(){
		return oldQualifiedJavaName != null && !oldQualifiedJavaName.equals(qualifiedJavaName);
	}
	public boolean requiresPersistentRename(){
		return oldPersistentName != null && !oldPersistentName.equals(oldPersistentName);
	}
	public boolean hasPersistentName(){
		return getPersistentName() != null && getPersistentName().getAsIs() != null && getPersistentName().getAsIs().trim().length() > 0;
	}
	public NameWrapper getJavaName(){
		return this.javaName;
	}
	public void setJavaName(NameWrapper javaName){
		this.oldJavaName = this.javaName;
		this.javaName = javaName;
	}
	public NameWrapper getPersistentName(){
		return this.persistentName;
	}
	public void setPersistentName(NameWrapper sqlName){
		this.oldPersistentName = this.persistentName;
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
		return result;
	}
	public String getQualifiedJavaName(){
		return qualifiedJavaName;
	}
	public void setQualifiedJavaName(String qualifiedJavaName){
		this.oldQualifiedJavaName = this.qualifiedJavaName;
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
	public Long getOpaeumId(){
		String idInModel2 = getIdInModel();
		if(idInModel2 == null){
			return 0l;
		}else{
			// TODO currently could lead to duplication in approximately 0.01% of cases
			// Tested with a model directory containing 300 000 elements. Chances of collisions on the same type are minimal
			return toOpaeumId(idInModel2);
		}
	}
	public static Long toOpaeumId(String idInModel2){
		char[] charArray = idInModel2.toCharArray();
		long result = 1;
		int atSignIndex = 0;
		for(int i = 0;i < charArray.length;i++){
			if(charArray[i] == '@'){
				atSignIndex = i;
			}
			result = (result * 31) + charArray[i] - i;
		}
		if(charArray.length > atSignIndex + 10){
			// THis is where the most variation takes place in the emf id
			// Introduce some variation in the calculation
			for(int i = atSignIndex + 2;i < atSignIndex + 10;i++){
				if(Character.isLowerCase(charArray[i])){
					result = (result * 31) + Character.toUpperCase(charArray[i]) - i;
				}else{
					result = (result * 31) + Character.toLowerCase(charArray[i]) - i;
				}
			}
		}
		return Math.abs(result);
	}
	public void setOpaeumId(Integer nakedUmlId){
		this.nakedUmlId = nakedUmlId;
	}
	public String toString(){
		return "" + (sinceRevision == null ? 0 : sinceRevision) + DEL + new DecimalFormat("#0.0000000").format(sinceVersion == null ? 0 : sinceVersion) + DEL
				+ nakedUmlId + DEL + getPersistentName() + DEL;
	}
	protected MappingInfo createCopy(){
		return new MappingInfo(idInModel, toString());
	}
	public boolean isVersioned(){
		return isVersioned;
	}
	public void setVersioned(boolean isVersioned){
		this.isVersioned = isVersioned;
	}
}
