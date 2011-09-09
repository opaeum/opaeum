package net.sf.nakeduml.feature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class NakedUmlConfig{
	private static final String DB_USER = "nakeduml.database.user";
	private static final String JDBC_DIALECT = "nakeduml.jdbc.dialect";
	private static final String DATA_SOURCE_NAME = "nakeduml.hibernate.ds.name";
	private static final String LIST_COLUMNS = "nakeduml.list.columns";
	private static final String NEED_SCHEMA = "nakeduml.needSchema";
	private static final String DEFAULT_SCHEMA = "nakeduml.default.schema";
	private static final String DATA_GENERATION = "nakeduml.data.generation";
	private static final String REAL_TYPE = "nakeduml.real.type";
	private static final String EMAIL_ADDRESS_TYPE = "nakeduml.email.address.type";
	private static final String DATE_TIME_TYPE = "nakeduml.timestamp.type";
	private static final String DATE_TYPE = "nakeduml.date.type";
	private static final String ID_GENERATOR_STRATEGY = "nakeduml.id.generator.strategy";
	private static final String TEST_DATA_SIZE = "nakeduml.test.data.size";
	private static final String MAVEN_GROUPID = "nakeduml.maven.groupid";
	private static final String MAVEN_GROUP_VERSION = "nakeduml.maven.group.version";
	private static final String GENERATE_MAVEN_POMS = "nakeduml.generate.poms";
	private static final String SCM_TOOL = "nakeduml.scm.tool";
	private static final String WORKSPACE_IDENTIFIER = "nakeduml.workspace.identifier";
	private static final String ADDITIONAL_TRANSFORMATION_STEPS = "nakeduml.additional.transformation.steps";
	private static final String SOURCE_FOLDER_STRATEGY = "nakeduml.source.folder.strategy";
	private static final String WORKSPACE_NAME = "nakeduml.workspace.name";
	private static Map<String,Class<?>> classRegistry = new HashMap<String,Class<?>>();
	private Properties props = new SortedProperties();
	private File outputRoot;
	private Map<ISourceFolderIdentifier,SourceFolderDefinition> sourceFolderDefinitions = new HashMap<ISourceFolderIdentifier,SourceFolderDefinition>();
	private File file;
	private WorkspaceMappingInfo workspaceMappingInfo;
	public NakedUmlConfig(File file){
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
			String name = props.getProperty(SOURCE_FOLDER_STRATEGY, "net.sf.nakeduml.pomgeneration.MavenSourceFolderStrategy");
			Class<?> c = getClass(name);
			return (ISourceFolderStrategy) c.newInstance();
		}catch(Exception e){
			return null;
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
		if(!this.props.containsKey(MAVEN_GROUP_VERSION)){
			this.props.setProperty(MAVEN_GROUP_VERSION, "0.0.1");
		}
		if(!this.props.containsKey(SCM_TOOL)){
			this.props.setProperty(SCM_TOOL, "git");
		}
		if(!this.props.containsKey(SOURCE_FOLDER_STRATEGY)){
			this.props.setProperty(SOURCE_FOLDER_STRATEGY, "net.sf.nakeduml.pomgeneration.MultiProjectMavenSourceFolderStrategy");
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
		setOutputRoot(getSourceFolderStrategy().calculateOutputRoot(file, modelProjectDir,getWorkspaceIdentifier()));
	}
	public void setOutputRoot(File destination){
		this.outputRoot = destination;
//		File t = file;
//		t.c
//		while(t.getParentFile() != null){
//			// Ensure that the output root does not coincide with the model directory
//			if(t.equals(outputRoot)){
//				outputRoot = new File(outputRoot.getParent(), outputRoot.getName() + "-project");
//				break;
//			}
//			t = t.getParentFile();
//		}
	}
	public int getNumberOfColumns(){
		return new Integer(this.props.getProperty(LIST_COLUMNS));
	}
	public boolean needsSchema(){
		return Boolean.valueOf(this.props.getProperty(NEED_SCHEMA,"true"));
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
			props.store(new FileWriter(file), "NakedUML");
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
	public SourceFolderDefinition defineSourceFolder(ISourceFolderIdentifier id,boolean useWorkspaceName,String projectSuffix,String relativeSourceFolder){
		SourceFolderDefinition value = new SourceFolderDefinition(useWorkspaceName, projectSuffix, relativeSourceFolder);
		sourceFolderDefinitions.put(id, value);
		return value;
	}
	public String getMavenGroupVersion(){
		return this.props.getProperty(MAVEN_GROUP_VERSION, "0.0.1-SNAPSHOT");
	}
	public String getScmTool(){
		return this.props.getProperty(SCM_TOOL);
	}
	public String getDbUser(){
		return this.props.getProperty(DB_USER);
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
	public void setAdditionalTransformationSteps(Set<String> s){
		StringBuilder sb = new StringBuilder();
		for(String string:s){
			sb.append(string);
			sb.append(";");
		}
		this.props.setProperty(ADDITIONAL_TRANSFORMATION_STEPS, sb.toString());
	}
	public WorkspaceMappingInfo getWorkspaceMappingInfo(){
		if(this.workspaceMappingInfo==null){
			this.workspaceMappingInfo=new WorkspaceMappingInfo(new File(file.getParent(), getWorkspaceIdentifier() + ".mappinginfo"));
		}
		return this.workspaceMappingInfo;
	}
	public void setWorkspaceName(String name){
		this.props.setProperty(WORKSPACE_NAME, name);
		store();
	}
	public boolean shouldBeCm1Compatible(){
		return true;
	}
}
