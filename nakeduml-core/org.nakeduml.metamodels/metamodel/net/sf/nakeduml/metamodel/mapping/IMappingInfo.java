package net.sf.nakeduml.metamodel.mapping;

import net.sf.nakeduml.metamodel.name.NameWrapper;

/**
 * This class evolved from an need to simplify the Velocity code generation templates. From an aesthetic perspective one would rather not
 * contaminate a PIM with PSM issue like "mapping information". Such a rule is easy enough to adhere to when one uses OO transformation
 * patterns like the visitor pattern.<BR>
 * Unfortunately this is not so easy to adhere to when using a simple templating mechanism like Velocity. Here one needs to be able to
 * navigate directly to the mapping information of the different model elements to the various PSM's one needs to generate code for.<BR>
 * This contamination does however have the following advantage: one does not need a metamodel for every platform one wants to generat code
 * for. This allowed us to generate Ant files, JBoss TreeCache deployment descriptors, SQL, Hibernate and Java code without using any PSM
 * whatsoever. <BR>
 * The contamination has been limited to a minimum by separating the calculation of the mapping information from the actual PIM. This
 * responsibility s currently implemented in the classes in the net.sf.nakeduml.generator package.
 */
public interface IMappingInfo{
	public String getJavaPath();
	/**
	 * Calculates whether this originalElement should be added to the database 1. For this revision given the currently deployed revision in the
	 * database 2. For this version, given the currently deployed version in the database
	 * 
	 * @param deployedVersion
	 *            The version currently deployed to the active database
	 * @param deployedRevision
	 *            The revision currently deployed to the active database
	 */
	void calculateMigrationRequirements(float deployedVersion,int deployedRevision);
	/**
	 * 
	 * If the sinceVersion and/or sinceRevision have not been set yet, then this will set them presumably for the first time Additionally,
	 * it updates the supporting tagged values
	 * 
	 * @param version
	 *            The current version of the model
	 * @param revision
	 *            The current revision of the model
	 */
	void updateVersionInfo(float version,int revision);
	/**
	 * Returns true if this model originalElement has been added since the last revision that was deployed to the active database
	 */
	boolean isNewInRevision();
	/**
	 * Returns true if this model originalElement has been added since the last version that was deployed to the active database
	 */
	boolean isNewInVersion();
	/**
	 * Returns the (most likely universally unique) id used to identify this model originalElement in the source model. Semantically equivalent to
	 * the mofId in MOF
	 */
	String getIdInModel();
	void setIdInModel(String idInModel);
	/**
	 * The NakedUmlId is an auto-generated id that is guarranteed to be unique for each originalElement within the model. Unique ids are also
	 * generated for imported models/model libraries but are guarranteed to be unique only within the importing model. Whenever meta
	 * information needs to be persisted in the system, this id is used to allow for names to change without requiring all the references to
	 * the name to be manually updated.
	 * 
	 * Examples are: Signals and their properties that have been stored to timer events and messages. Type aware entity-references that have
	 * been stored to timer events and messages. Enumeration Literals that have been persisted to the database Statemachine states that have
	 * been persisted to the database. Discriminator fields for hibernate 'Any' mappings in hibernate Tree state serialization in JSF
	 */
	Integer getNakedUmlId();
	void setNakedUmlId(Integer nakedUmlId);
	/**
	 * Returns the revision number during which this originalElement was added to the model
	 */
	Integer getSinceRevision();
	void setSinceRevision(Integer revision);
	/**
	 * Returns the version number during which this originalElement was added to the model
	 */
	Float getSinceVersion();
	void setSinceVersion(Float version);
	boolean hasMappingInfo();
	boolean hasPersistentName();
	NameWrapper getJavaName();
	void setJavaName(NameWrapper javaName);
	NameWrapper getPersistentName();
	void setPersistentName(NameWrapper sqlName);
	String getQualifiedJavaName();
	void setQualifiedJavaName(String qualifiedJavaName);
	boolean isRequiresSqlRename();
	void setRequiresSqlRename(boolean requiresSqlRename);
	NameWrapper getHumanName();
	void setHumanName(NameWrapper humanName);
	@Deprecated
	String getQualifiedUmlName();
	void setQualifiedUmlName(String qualifiedUmlName);
	IMappingInfo getCopy();
	public void setStore(boolean store);
	public boolean shouldStore();
	public String getQualifiedPersistentName();
	public void setQualifiedPersistentName(String string);
}