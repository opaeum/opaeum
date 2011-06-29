package net.sf.nakeduml.feature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.nakeduml.environment.Environment;


public class NakedUmlConfig {
	private static final String DB_USER = "nakeduml.database.user";
	private static final String JDBC_DIALECT = "nakeduml.jdbc.dialect";
	private static final String DATA_SOURCE_NAME = "nakeduml.hibernate.ds.name";
	private static final String LIST_COLUMNS = "nakeduml.list.columns";
	private static final String NEED_SCHEMA = "nakeduml.needSchema";
	private static final String SUPPORT_SCHEMA = "nakeduml.supportSchema";
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
	private static final String ATTRIBUTE_IMPLEMENTATION_STRATEGY = "attribute.implementation.strategy";
	private static final String COMPOSITION_NODE_IMPLEMENTATION_STRATEGY = "composition.node.implementation.strategy";
	private static final String WORKSPACE_IDENTIFIER = "nakeduml.workspace.identifier";
	private Properties props = new SortedProperties();
	private Map<String,File> outputRootMap = new HashMap<String,File>();
	private File outputRoot;
	private Map<Enum<?>,OutputRoot> outputRoots = new HashMap<Enum<?>,OutputRoot>();
	private File file;
	public NakedUmlConfig(){
	}
	public void load(File file,String workspaceIdentifier) throws IOException{
		this.file = file;
		if(file.exists()){
			FileInputStream stream = new FileInputStream(file);
			if(stream != null){
				this.props.load(stream);
			}
		}
		loadDefaults(workspaceIdentifier);
		this.props.store(new FileOutputStream(file), "NakedUml properties");
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
		if(!this.props.containsKey(MAVEN_GROUP_VERSION)){
			this.props.setProperty(MAVEN_GROUP_VERSION, "0.0.1");
		}
		if(!this.props.containsKey(SCM_TOOL)){
			this.props.setProperty(SCM_TOOL, "git");
		}
		if(!this.props.containsKey(ATTRIBUTE_IMPLEMENTATION_STRATEGY)){
			this.props.setProperty(ATTRIBUTE_IMPLEMENTATION_STRATEGY, "net.sf.nakeduml.javageneration.basicjava.DefaultAttributeImplementorStrategy");
		}
		if(!this.props.containsKey(COMPOSITION_NODE_IMPLEMENTATION_STRATEGY)){
			this.props.setProperty(COMPOSITION_NODE_IMPLEMENTATION_STRATEGY, "net.sf.nakeduml.javageneration.composition.DefaultCompositionNodeStrategy");
		}
	}
	public String getJdbcDialect(){
		return this.props.getProperty(JDBC_DIALECT);
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
	public void setOutputRoot(File destination){
		this.outputRoot = destination;
	}
	public File getMappedDestination(String name){
		return this.outputRootMap.get(name);
	}
	public int getNumberOfColumns(){
		return new Integer(this.props.getProperty(LIST_COLUMNS));
	}
	public boolean needsSchema(){
		return Boolean.valueOf(this.props.getProperty(NEED_SCHEMA));
	}
	public boolean supportSchema(){
		return Boolean.valueOf(this.props.getProperty(SUPPORT_SCHEMA));
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
		return this.props.getProperty(MAVEN_GROUPID);
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
			store(new FileWriter(file));
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	public void store(Writer writer){
		try{
			props.store(writer, "NakedUML");
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	public File getOutputRoot(){
		return outputRoot;
	}
	public OutputRoot getOutputRoot(Enum<?> id){
		return outputRoots.get(id);
	}
	public OutputRoot mapOutputRoot(Enum<?> id,boolean useWorkspaceName,String projectSuffix,String relativeSourceFolder){
		OutputRoot value = new OutputRoot(useWorkspaceName, projectSuffix, relativeSourceFolder);
		outputRoots.put(id, value);
		return value;
	}
	public String getMavenGroupVersion(){
		return this.props.getProperty(MAVEN_GROUP_VERSION, "0.0.1-SNAPSHOT");
	}
	public String getScmTool(){
		return this.props.getProperty(SCM_TOOL);
	}
	public String getAttributeImplementationStrategy(){
		return this.props.getProperty(ATTRIBUTE_IMPLEMENTATION_STRATEGY);
	}
	public String getCompositionNodeImplementationStrategy(){
		return this.props.getProperty(COMPOSITION_NODE_IMPLEMENTATION_STRATEGY);
	}
	public String getDbUser(){
		return this.props.getProperty(DB_USER);
	}

	public void setMavenGroupId(String string){
		this.props.setProperty(MAVEN_GROUPID, string);
	}
	public String getWorkspaceIdentifier(){
		return this.props.getProperty(WORKSPACE_IDENTIFIER);
	}
	public void setWorkspaceIdentifier(String workspaceIdentifier){
		this.props.setProperty(WORKSPACE_IDENTIFIER, workspaceIdentifier);
	}
}
