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


public class NakedUmlConfig {
	private static final String JDBC_CONNECTION_URL = "nakeduml.jdbc.connection.url";
	private static final String DB_USERNAME = "nakeduml.jdbc.connection.username";
	private static final String DB_PASSWORD = "nakeduml.jdbc.connection.password";
	private static final String JDBC_DIALECT = "nakeduml.jdbc.dialect";
	private static final String NAKEDUML_LIST_COLUMNS = "nakeduml.list.columns";
	private static final String NAKEDUML_NEED_SCHEMA = "nakeduml.needSchema";
	private static final String NAKEDUML_SUPPORT_SCHEMA = "nakeduml.supportSchema";
	private static final String NAKEDUML_DEFAULT_SCHEMA = "nakeduml.default.schema";
	private static final String NAKEDUML_DATA_GENERATION = "nakeduml.data.generation";
	private static final String JDBC_DRIVER_CLASS = "nakeduml.jdbc.driver.class";
	private static final String NAKEDUML_REAL_TYPE = "nakeduml.real.type";
	private static final String NAKEDUML_EMAIL_ADDRESS_TYPE = "nakeduml.email.address.type";
	private static final String NAKEDUML_DATE_TIME_TYPE = "nakeduml.timestamp.type";
	private static final String NAKEDUML_DATE_TYPE = "nakeduml.date.type";
	private static final String NAKEDUML_HIBERNATE_DS_NAME = "nakeduml.hibernate.ds.name";
	private static final String NAKEDUML_ID_GENERATOR_STRATEGY = "nakeduml.id.generator.strategy";
	private static final String NAKEDUML_TEST_DATA_SIZE = "nakeduml.test.data.size";
	private static final String MAVEN_GROUPID = "nakeduml.maven.groupid";
	private static final String MAVEN_GROUP_VERSION = "nakeduml.maven.group.version";
	private static final String GENERATE_MAVEN_POMS = "nakeduml.generate.poms";
	private static final String SCM_TOOL = "nakeduml.scm.tool";
	private Properties props = new SortedProperties();
	private Map<String, File> outputRootMap = new HashMap<String, File>();
	private File outputRoot;
	private Map<Enum<?>, OutputRoot> outputRoots = new HashMap<Enum<?>, OutputRoot>();
	private File file;

	public NakedUmlConfig() {
	}

	public void load(File file, String projectName) throws IOException {
		this.file = file;
		if (file.exists()) {
			FileInputStream stream = new FileInputStream(file);
			if (stream != null) {
				this.props.load(stream);
			}
		}
		loadDefaults(projectName);
		this.props.store(new FileOutputStream(file), "NakedUml properties");
	}

	public void loadDefaults(String projectName) {
		if (!this.props.containsKey(JDBC_DIALECT)) {
			this.props.setProperty(JDBC_DIALECT, "org.hibernate.dialect.HSQLDialect");
		}
		if (!this.props.containsKey(JDBC_DRIVER_CLASS)) {
			this.props.setProperty(JDBC_DRIVER_CLASS, "org.hsqldb.jdbcDriver");
		}
		if (!this.props.containsKey(DB_PASSWORD)) {
			this.props.setProperty(DB_PASSWORD, "");
		}
		if (!this.props.containsKey(DB_USERNAME)) {
			this.props.setProperty(DB_USERNAME, "sa");
		}
		if (!this.props.containsKey(JDBC_CONNECTION_URL)) {
			this.props.setProperty(JDBC_CONNECTION_URL, "jdbc:hsqldb:hsql:///" + projectName);
		}
		if (!this.props.containsKey(NAKEDUML_DATE_TIME_TYPE)) {
			this.props.setProperty(NAKEDUML_DATE_TIME_TYPE, "Date");
		}
		if (!this.props.containsKey(NAKEDUML_DATE_TYPE)) {
			this.props.setProperty(NAKEDUML_DATE_TYPE, "Date");
		}
		if (!this.props.containsKey(NAKEDUML_REAL_TYPE)) {
			this.props.setProperty(NAKEDUML_REAL_TYPE, "Real");
		}
		if (!this.props.containsKey(NAKEDUML_EMAIL_ADDRESS_TYPE)) {
			this.props.setProperty(NAKEDUML_EMAIL_ADDRESS_TYPE, "EMailAddress");
		}
		if (!this.props.containsKey(GENERATE_MAVEN_POMS)) {
			this.props.setProperty(GENERATE_MAVEN_POMS, "true");
		}
		if (!this.props.containsKey(MAVEN_GROUPID)) {
			this.props.setProperty(MAVEN_GROUPID, "za.co.companyname." + projectName);
		}
		if (!this.props.containsKey(MAVEN_GROUP_VERSION)) {
			this.props.setProperty(MAVEN_GROUP_VERSION, "0.0.1");
		}
		if (!this.props.containsKey(SCM_TOOL)) {
			this.props.setProperty(SCM_TOOL, "git");
		}
	}

	public String getJdbcDialect() {
		return this.props.getProperty(JDBC_DIALECT);
	}

	public String getJdbcDriverClassName() {
		return this.props.getProperty(JDBC_DRIVER_CLASS);
	}

	public String getDBPassword() {
		return this.props.getProperty(DB_PASSWORD);
	}

	public boolean generateMavenPoms() {
		return "true".equals(this.props.getProperty(GENERATE_MAVEN_POMS,"true"));
	}

	public String getDBUsername() {
		return this.props.getProperty(DB_USERNAME);
	}

	public String getDBConnectionUrl() {
		return this.props.getProperty(JDBC_CONNECTION_URL);
	}

	public String getDateType() {
		return this.props.getProperty(NAKEDUML_DATE_TYPE);
	}

	public String getRealType() {
		return this.props.getProperty(NAKEDUML_REAL_TYPE);
	}

	public String getEMailAddressType() {
		return this.props.getProperty(NAKEDUML_EMAIL_ADDRESS_TYPE);
	}

	public void setOutputRoot(File destination) {
		this.outputRoot = destination;
	}

	public File getMappedDestination(String name) {
		return this.outputRootMap.get(name);
	}

	public int getNumberOfColumns() {
		return new Integer(this.props.getProperty(NAKEDUML_LIST_COLUMNS));
	}

	public boolean needsSchema() {
		return Boolean.valueOf(this.props.getProperty(NAKEDUML_NEED_SCHEMA));
	}

	public boolean supportSchema() {
		return Boolean.valueOf(this.props.getProperty(NAKEDUML_SUPPORT_SCHEMA));
	}

	public String getDefaultSchema() {
		return this.props.getProperty(NAKEDUML_DEFAULT_SCHEMA);
	}

	public Boolean getDataGeneration() {
		return Boolean.valueOf(this.props.getProperty(NAKEDUML_DATA_GENERATION, "true"));
	}

	public String getHibernateDSName() {
		return this.props.getProperty(NAKEDUML_HIBERNATE_DS_NAME, "java:/DefaultDS");
	}

	public String getMavenGroupId() {
		return this.props.getProperty(MAVEN_GROUPID);
	}

	public String getIdGeneratorStrategy() {
		return this.props.getProperty(NAKEDUML_ID_GENERATOR_STRATEGY, "AUTO");
	}

	public void setIdGeneratorStrategy(String name) {
		this.props.setProperty(NAKEDUML_ID_GENERATOR_STRATEGY, name);
	}

	public String getTestDataSize() {
		return this.props.getProperty(NAKEDUML_TEST_DATA_SIZE, "3");
	}

	public void store() {
		try {
			store(new FileWriter(file));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void store(Writer writer) {
		try {
			props.store(writer, "NakedUML");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public File getOutputRoot() {
		return outputRoot;
	}

	public OutputRoot getOutputRoot(Enum<?> id) {
		return outputRoots.get(id);
	}

	public OutputRoot mapOutputRoot(Enum<?> id, boolean useWorkspaceName, String projectSuffix, String relativeSourceFolder) {
		OutputRoot value = new OutputRoot(useWorkspaceName, projectSuffix, relativeSourceFolder);
		outputRoots.put(id, value);
		return value;
	}

	public String getMavenGroupVersion() {
		return this.props.getProperty(MAVEN_GROUP_VERSION, "0.0.1-SNAPSHOT");
	}

	public String getScmTool(){
		return this.props.getProperty(SCM_TOOL);
	}

	public void setMavenGroupId(String string){
		this.props.setProperty(MAVEN_GROUPID, string);
		
	}
}
