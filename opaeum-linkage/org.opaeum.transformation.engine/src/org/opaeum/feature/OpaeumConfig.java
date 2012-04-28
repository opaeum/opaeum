package org.opaeum.feature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.opaeum.metamodel.workspace.AbstractStrategyFactory;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.ProjectNameStrategy;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.runtime.environment.VersionNumber;
import org.opaeum.runtime.persistence.DatabaseManagementSystem;
import org.opaeum.util.SortedProperties;

public class OpaeumConfig{
	private static final String DB_USER = "opaeum.database.user";
	private static final String JDBC_DIALECT = "opaeum.jdbc.dialect";
	private static final String DATA_SOURCE_NAME = "opaeum.hibernate.ds.name";
	private static final String LIST_COLUMNS = "opaeum.list.columns";
	private static final String NEED_SCHEMA = "opaeum.needSchema";
	private static final String DEFAULT_SCHEMA = "opaeum.default.schema";
	private static final String DATA_GENERATION = "opaeum.data.generation";
	private static final String REAL_TYPE = "opaeum.real.type";
	private static final String EMAIL_ADDRESS_TYPE = "opaeum.email.address.type";
	private static final String DATE_TIME_TYPE = "opaeum.timestamp.type";
	private static final String DATE_TYPE = "opaeum.date.type";
	private static final String ID_GENERATOR_STRATEGY = "opaeum.id.generator.strategy";
	private static final String TEST_DATA_SIZE = "opaeum.test.data.size";
	private static final String MAVEN_GROUPID = "opaeum.maven.groupid";
	private static final String GENERATE_MAVEN_POMS = "opaeum.generate.poms";
	private static final String SCM_TOOL = "opaeum.scm.tool";
	private static final String WORKSPACE_IDENTIFIER = "opaeum.workspace.identifier";
	private static final String ADDITIONAL_TRANSFORMATION_STEPS = "opaeum.additional.transformation.steps";
	private static final String SOURCE_FOLDER_STRATEGY = "opaeum.source.folder.strategy";
	private static final String WORKSPACE_NAME = "opaeum.workspace.name";
	private static final String ADDITIONAL_PERSISTENT_CLASSES = "opaeum.additional.persistent.classes";
	private static final String AUTO_SYNC = "opaeum.eclipse.autosync";
	private static final String DBMS = "opaeum.dbms";
	private static final String JDBC_CONNECTION_URL = "opaeum.jdbc.connection.url";
	private static final String DB_PASSWORD = "opaeum.database.password";
	private static Map<String,Class<?>> classRegistry = new HashMap<String,Class<?>>();
	private Properties props = new SortedProperties();
	private File outputRoot;
	private Map<ISourceFolderIdentifier,SourceFolderDefinition> sourceFolderDefinitions = new HashMap<ISourceFolderIdentifier,SourceFolderDefinition>();
	private File file;
	private WorkspaceMappingInfo workspaceMappingInfo;
	private SqlDialect sqlDialect;
	private VersionNumber version;
	public OpaeumConfig(File file){
		this.file = file;
		if(file.exists()){
			FileInputStream stream;
			try{
				stream = new FileInputStream(file);
				if(stream != null){
					this.props.load(stream);
					ISourceFolderStrategy sfs = getSourceFolderStrategy();
					if(sfs != null){
						sfs.defineSourceFolders(this);
					}
				}
			}catch(IOException e){
				throw new RuntimeException(e);
			}
		}
	}
	public File getConfigFile(){
		return file;
	}
	public ISourceFolderStrategy getSourceFolderStrategy(){
		try{
			String name = props.getProperty(SOURCE_FOLDER_STRATEGY, "org.opaeum.pomgeneration.SingleProjectMavenSourceFolderStrategy");
			Class<?> c = getClass(name);
			return (ISourceFolderStrategy) c.newInstance();
		}catch(Exception e){
			try{
				Class<?> c = getClass("org.opaeum.pomgeneration.SingleProjectMavenSourceFolderStrategy");
				return (ISourceFolderStrategy) c.newInstance();
			}catch(Exception e1){
				return null;
			}
		}
	}
	public static Class<?> getClass(String name){
		Class<?> c;
		if(classRegistry.containsKey(name)){
			c = classRegistry.get(name);
		}else{
			try{
				c = Thread.currentThread().getContextClassLoader().loadClass(name);
			}catch(ClassNotFoundException e){
				throw new RuntimeException(e);
			}
		}
		return c;
	}
	// A workaround for the Eclipse restriction that upstream plugins do not have access to downstream plugin classes
	public static void registerClass(Class<?> c){
		classRegistry.put(c.getName(), c);
	}
	public void loadDefaults(String projectName){
		if(getWorkspaceIdentifier() == null){
			this.props.setProperty(WORKSPACE_IDENTIFIER, projectName);
		}else{
			projectName = getWorkspaceIdentifier();
		}
		if(!this.props.containsKey(JDBC_DIALECT)){
			this.props.setProperty(JDBC_DIALECT, "org.hibernate.dialect.HSQLDialect");
		}
		if(!this.props.containsKey(DATA_SOURCE_NAME)){
			this.props.setProperty(DATA_SOURCE_NAME, "java:/" + projectName + "DataSource");
		}
		if(!this.props.containsKey(DATE_TIME_TYPE)){
			this.props.setProperty(DATE_TIME_TYPE, "Date");
		}
		if(!this.props.containsKey(DATE_TYPE)){
			this.props.setProperty(DATE_TYPE, "Date");
		}
		if(!this.props.containsKey(REAL_TYPE)){
			this.props.setProperty(REAL_TYPE, "Real");
		}
		if(!this.props.containsKey(EMAIL_ADDRESS_TYPE)){
			this.props.setProperty(EMAIL_ADDRESS_TYPE, "EMailAddress");
		}
		if(!this.props.containsKey(GENERATE_MAVEN_POMS)){
			this.props.setProperty(GENERATE_MAVEN_POMS, "true");
		}
		if(!this.props.containsKey(MAVEN_GROUPID)){
			this.props.setProperty(MAVEN_GROUPID, "za.co.companyname." + projectName);
		}
		if(!this.props.containsKey(DB_USER)){
			this.props.setProperty(DB_USER, projectName);
		}
		if(!this.props.containsKey(SCM_TOOL)){
			this.props.setProperty(SCM_TOOL, "git");
		}
		if(!this.props.containsKey(SOURCE_FOLDER_STRATEGY)){
			this.props.setProperty(SOURCE_FOLDER_STRATEGY, "org.opaeum.pomgeneration.MultiProjectMavenSourceFolderStrategy");
		}
	}
	public String getJdbcDialect(){
		return this.props.getProperty(JDBC_DIALECT);
	}
	public void setGenerateMavenPoms(boolean b){
		this.props.setProperty(GENERATE_MAVEN_POMS, String.valueOf(b));
	}
	public boolean generateMavenPoms(){
		return "true".equals(this.props.getProperty(GENERATE_MAVEN_POMS, "true"));
	}
	public String getDateType(){
		return this.props.getProperty(DATE_TYPE);
	}
	public String getRealType(){
		return this.props.getProperty(REAL_TYPE);
	}
	public String getEMailAddressType(){
		return this.props.getProperty(EMAIL_ADDRESS_TYPE);
	}
	public void calculateOutputRoot(File modelProjectDir){
		setOutputRoot(getSourceFolderStrategy().calculateOutputRoot(file, modelProjectDir, getWorkspaceIdentifier()));
	}
	public void setOutputRoot(File destination){
		this.outputRoot = destination;
		// File t = file;
		// t.c
		// while(t.getParentFile() != null){
		// // Ensure that the output root does not coincide with the model directory
		// if(t.equals(outputRoot)){
		// outputRoot = new File(outputRoot.getParent(), outputRoot.getName() + "-project");
		// break;
		// }
		// t = t.getParentFile();
		// }
	}
	public int getNumberOfColumns(){
		return new Integer(this.props.getProperty(LIST_COLUMNS));
	}
	public boolean needsSchema(){
		return Boolean.valueOf(this.props.getProperty(NEED_SCHEMA, "true"));
	}
	public String getDefaultSchema(){
		return this.props.getProperty(DEFAULT_SCHEMA);
	}
	public Boolean getDataGeneration(){
		return Boolean.valueOf(this.props.getProperty(DATA_GENERATION, "true"));
	}
	public String getDataSourceName(){
		return this.props.getProperty(DATA_SOURCE_NAME);
	}
	public String getMavenGroupId(){
		return this.props.getProperty(MAVEN_GROUPID, "");
	}
	public String getIdGeneratorStrategy(){
		return this.props.getProperty(ID_GENERATOR_STRATEGY, "AUTO");
	}
	public void setIdGeneratorStrategy(String name){
		this.props.setProperty(ID_GENERATOR_STRATEGY, name);
	}
	public String getTestDataSize(){
		return this.props.getProperty(TEST_DATA_SIZE, "3");
	}
	public void store(){
		try{
			getVersion().writeTo(props);
			props.store(new FileWriter(file), "Opaeum");
			getSourceFolderStrategy().defineSourceFolders(this);
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	public File getOutputRoot(){
		return outputRoot;
	}
	public SourceFolderDefinition getSourceFolderDefinition(ISourceFolderIdentifier id){
		return sourceFolderDefinitions.get(id);
	}
	public SourceFolderDefinition defineSourceFolder(ISourceFolderIdentifier id,boolean useWorkspaceName,String projectSuffix,
			String relativeSourceFolder){
		SourceFolderDefinition value = new SourceFolderDefinition(useWorkspaceName ? ProjectNameStrategy.WORKSPACE_NAME_AND_SUFFIX
				: ProjectNameStrategy.MODEL_NAME_AND_SUFFIX, projectSuffix, relativeSourceFolder);
		sourceFolderDefinitions.put(id, value);
		return value;
	}
	public SourceFolderDefinition defineSourceFolder(ISourceFolderIdentifier id,ProjectNameStrategy pns,String projectSuffix,
			String relativeSourceFolder){
		SourceFolderDefinition value = new SourceFolderDefinition(pns, projectSuffix, relativeSourceFolder);
		sourceFolderDefinitions.put(id, value);
		return value;
	}
	public String getScmTool(){
		return this.props.getProperty(SCM_TOOL);
	}
	public String getDbUser(){
		return this.props.getProperty(DB_USER, "postgres");
	}
	public void setMavenGroupId(String string){
		this.props.setProperty(MAVEN_GROUPID, string);
		store();
	}
	public String getWorkspaceIdentifier(){
		return this.props.getProperty(WORKSPACE_IDENTIFIER, "");
	}
	public String getWorkspaceName(){
		return this.props.getProperty(WORKSPACE_NAME, "");
	}
	public void setWorkspaceIdentifier(String workspaceIdentifier){
		this.props.setProperty(WORKSPACE_IDENTIFIER, workspaceIdentifier);
		store();
	}
	public Map<ISourceFolderIdentifier,SourceFolderDefinition> getSourceFolderDefinitions(){
		return sourceFolderDefinitions;
	}
	public void setSourceFolderStrategy(String b){
		this.props.setProperty(SOURCE_FOLDER_STRATEGY, b);
	}
	@SuppressWarnings("unchecked")
	public Set<Class<? extends ITransformationStep>> getAdditionalTransformationSteps(){
		String property = this.props.getProperty(ADDITIONAL_TRANSFORMATION_STEPS, "");
		Set<Class<? extends ITransformationStep>> result = new HashSet<Class<? extends ITransformationStep>>();
		if(property.trim().length() > 0){
			String[] split = property.split(";");
			for(String string:split){
				result.add((Class<? extends ITransformationStep>) getClass(string));
			}
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	public Set<Class<? extends AbstractStrategyFactory>> getStrategyFactories(){
		String property = this.props.getProperty("opaeum.strategy.factories", "");
		Set<Class<? extends AbstractStrategyFactory>> result = new HashSet<Class<? extends AbstractStrategyFactory>>();
		if(property.trim().length() > 0){
			String[] split = property.split(";");
			for(String string:split){
				result.add((Class<? extends AbstractStrategyFactory>) getClass(string));
			}
		}
		for(Class<?> class1:OpaeumConfig.classRegistry.values()){
			if(AbstractStrategyFactory.class.isAssignableFrom(class1)){
				result.add((Class<? extends AbstractStrategyFactory>) class1);
			}
		}
		return result;
	}
	public void setAdditionalTransformationSteps(Set<String> s){
		StringBuilder sb = new StringBuilder();
		for(String string:s){
			sb.append(string);
			sb.append(";");
		}
		this.props.setProperty(ADDITIONAL_TRANSFORMATION_STEPS, sb.toString());
	}
	public WorkspaceMappingInfo getWorkspaceMappingInfo(){
		if(this.workspaceMappingInfo == null){
			this.workspaceMappingInfo = new WorkspaceMappingInfo(new File(file.getParent(), getWorkspaceIdentifier() + ".mappinginfo"));
			this.workspaceMappingInfo.setVersion(getVersion());
		}
		return this.workspaceMappingInfo;
	}
	public void reset(){
		workspaceMappingInfo = null;
		this.sqlDialect = null;
		this.sqlDialect = null;
	}
	public void setWorkspaceName(String name){
		this.props.setProperty(WORKSPACE_NAME, name);
		store();
	}
	public boolean shouldBeCm1Compatible(){
		return "true".equals(props.getProperty("cm1.compatible"));
	}
	public void setAutoSync(Boolean b){
		this.props.setProperty(AUTO_SYNC, b.toString());
	}
	public boolean synchronizeAutomatically(){
		return "true".equals(props.getProperty(AUTO_SYNC));
	}
	public SqlDialect getDbDialect(){
		if(this.sqlDialect == null){
			this.sqlDialect = new SqlDialect(){
				@Override
				public String getCurrentTimeStampString(){
					return "current_timestamp";
				}
			};
		}
		return this.sqlDialect;
	}
	public Collection<String> getAdditionalPersistentClasses(){
		String property = this.props.getProperty(ADDITIONAL_PERSISTENT_CLASSES);
		if(property == null){
			return Collections.emptySet();
		}else{
			return Arrays.asList(property.split(";"));
		}
	}
	public void setVersion(String version){
		getVersion().parse(version);
		store();
	}
	public String getMavenGroupVersionSuffix(){
		return getVersion().getSuffix();
	}
	public VersionNumber getVersion(){
		if(this.version == null){
			this.version = new VersionNumber();
			this.version.readFrom(props);
		}
		return this.version;
	}
	public static boolean isValidVersionNumber(String name){
		String REG_EXP = "[0-9]+(\\.[0-9]+){0,3}";
		boolean matches = name.matches(REG_EXP);
		return matches;
	}
	public void setVersion(VersionNumber version2){
		this.version = version2;
		store();
	}
	public Class<?> getErrorMarker(){
		Collection<Class<?>> values = classRegistry.values();
		for(Class<?> class1:values){
			// Temporary TODO fix when migrating away from topcased
			if(class1.getName().endsWith("ErrorMarker")){
				return class1;
			}
		}
		return null;
	}
	public OpaeumConfig getCopy(){
		OpaeumConfig result = new OpaeumConfig(getConfigFile());
		return result;
	}
	public String getDbms(){
		return props.getProperty(DBMS,DatabaseManagementSystem.POSTGRESQL.name());
	}
	public String getJdbcConnectionUrl(){
		return props.getProperty(JDBC_CONNECTION_URL,"jdbc:postgresql://localhost:5433/"+getWorkspaceIdentifier());
	}
	public String getDbPassword(){
		return props.getProperty(DB_PASSWORD,"postgres");
	}
}
