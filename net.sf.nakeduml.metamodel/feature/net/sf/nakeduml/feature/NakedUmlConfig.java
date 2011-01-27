package net.sf.nakeduml.feature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class NakedUmlConfig {
	// TODO group these by feature - let every feature contribute its own config
	// properties
	public static final String NAKEDUML_COMPOSITE_NODE_INTERFACE = "nakeduml.compositenode.interface";
	public static final String NAKEDUML_TEST_SOURCE_DIRECTORY = "nakeduml.test.source.directory";
	public static final String NAKEDUML_HAR_SOURCE_DIRECTORY = "nakeduml.har.source.directory";
	public static final String NAKEDUML_SQL_DIRECTORY = "nakeduml.sql.directory";
	public static final String NAKEDUML_WEBUI_PROJECT = "nakeduml.webui.project";
	public static final String NAKEDUML_DOMAIN_PROJECT = "nakeduml.domain.project";
	public static final String JDBC_DIALECT = "nakeduml.jdbc.dialect";
	public static final String NAKEDUML_LIST_COLUMNS = "nakeduml.list.columns";
	public static final String NAKEDUML_NEED_SCHEMA = "nakeduml.needSchema";
	public static final String NAKEDUML_DEFAULT_SCHEMA = "nakeduml.default.schema";
	public static final String NAKEDUML_APPLICATION_PAGES_XML = "naked.uml.application.pages.xml.path";
	public static final String NAKEDUML_TOOMANY_DATATABLE_ROWS = "nakeduml.toomany.datatable.rows";
	public static final String NAKEDUML_ENVERS_AUDITED = "nakeduml.envers.audited";
	public static final String NAKEDUML_DATA_GENERATION = "nakeduml.data.generation";
	private static final String JDBC_DRIVER_CLASS = "nakeduml.jdbc.driver.class";
	private static final String NAKEDUML_REAL_TYPE = "nakeduml.real.type";
	private static final String NAKEDUML_EMAIL_ADDRESS_TYPE = "nakeduml.email.address.type";
	private static final String NAKEDUML_DATE_TIME_TYPE = "nakeduml.timestamp.type";
	private static final String NAKEDUML_DATE_TYPE = "nakeduml.date.type";
	private static final String NAKEDUML_MAPPED_TYPES_PACKAGE = "nakeduml.mapped.types.package";
	private static final String NAKEDUML_SEAM_OR_WELD = "nakeduml.seam.or.weld";
	private static final String NAKEDUML_PROJECT_NAME = "nakeduml.project.name";
	private static final String NAKEDUML_HIBERNATE_CFG_NAME = "nakeduml.hibernate.cfg.name";
	private static final String NAKEDUML_PROJECT_GEN_ROOT = "nakeduml.project.gen.root";
	private static final String NAKEDUML_PROJECT_GEN_NAME = "nakeduml.project.gen.name";
	private static final String NAKEDUML_PROJECT_GEN_GROUPID = "nakeduml.project.gen.groupid";

	private Properties props = new Properties();
	private Map<String, File> outputRootMap = new HashMap<String, File>();
	private Set<String> selectedFeatures = new HashSet<String>();

	public NakedUmlConfig(Properties props2, String projectName) {
		this.props = props2;
		loadDefaults(projectName);
	}

	public void load(File file, String projectName) throws IOException {
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
		if (!this.props.containsKey(NAKEDUML_DOMAIN_PROJECT)) {
			this.props.setProperty(NAKEDUML_DOMAIN_PROJECT, projectName + "Domain");
		}
		if (!this.props.containsKey(NAKEDUML_WEBUI_PROJECT)) {
			this.props.setProperty(NAKEDUML_WEBUI_PROJECT, projectName + "WebUI");
		}
		if (!this.props.containsKey(JDBC_DIALECT)) {
			this.props.setProperty(JDBC_DIALECT, "org.hibernate.dialect.HSQLDialect");
		}
		if (!this.props.containsKey(JDBC_DRIVER_CLASS)) {
			this.props.setProperty(JDBC_DRIVER_CLASS, "org.hsqldb.jdbcDriver");
		}
		if (!this.props.containsKey("nakeduml.jdbc.connection.password")) {
			this.props.setProperty("nakeduml.jdbc.connection.password", "");
		}
		if (!this.props.containsKey("nakeduml.jdbc.connection.username")) {
			this.props.setProperty("nakeduml.jdbc.connection.username", "sa");
		}
		if (!this.props.containsKey("nakeduml.jdbc.connection.url")) {
			this.props.setProperty("nakeduml.jdbc.connection.url", "jdbc:hsqldb:hsql:///test");
		}
		if (!this.props.containsKey(NAKEDUML_HAR_SOURCE_DIRECTORY)) {
			this.props.setProperty(NAKEDUML_HAR_SOURCE_DIRECTORY, "har-src");
		}
		if (!this.props.containsKey(NAKEDUML_TEST_SOURCE_DIRECTORY)) {
			this.props.setProperty(NAKEDUML_TEST_SOURCE_DIRECTORY, "test-src");
		}
		if (!this.props.containsKey("nakeduml.naming.context.factory")) {
			this.props.setProperty("nakeduml.naming.context.factory", "org.jnp.interfaces.NamingContextFactory");
		}
		if (!this.props.containsKey("nakeduml.naming.providerurl")) {
			this.props.setProperty("nakeduml.naming.providerurl", "locModelalhost");
		}
		if (!this.props.containsKey(NAKEDUML_SQL_DIRECTORY)) {
			this.props.setProperty(NAKEDUML_SQL_DIRECTORY, "sql");
		}
		if (!this.props.containsKey("nakeduml.jdbc.datasource.connection.url")) {
			this.props.setProperty("nakeduml.jdbc.datasource.connection.url", "jdbc:hsqldb:hsql://test");
		}
		if (!this.props.containsKey("nakeduml.jdbc.database.name")) {
			this.props.setProperty("nakeduml.jdbc.database.name", projectName);
		}
		if (!this.props.containsKey(NAKEDUML_MAPPED_TYPES_PACKAGE)) {
			this.props.setProperty(NAKEDUML_MAPPED_TYPES_PACKAGE, "Types");
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

	}

	public String getDatasourceConnectionUrl() {
		return this.props.getProperty("nakeduml.jdbc.datasource.connection.url");
	}

	public String getJdbcDialect() {
		return this.props.getProperty(JDBC_DIALECT);
	}

	public String getJdbcDriverClassName() {
		return this.props.getProperty(JDBC_DRIVER_CLASS);
	}

	public String getDBPassword() {
		return this.props.getProperty("nakeduml.jdbc.connection.password");
	}

	public String getDBUsername() {
		return this.props.getProperty("nakeduml.jdbc.connection.username");
	}

	public String getDBConnectionUrl() {
		return this.props.getProperty("nakeduml.jdbc.connection.url");
	}

	public String getHarSourceDirectory() {
		return this.props.getProperty(NAKEDUML_HAR_SOURCE_DIRECTORY);
	}

	public String getGenSourceDirectory() {
		return this.props.getProperty(NAKEDUML_TEST_SOURCE_DIRECTORY);
	}

	public String getInitialContextFactory() {
		return this.props.getProperty("nakeduml.naming.context.factory");
	}

	public String getProviderUrl() {
		return this.props.getProperty("nakeduml.naming.providerurl");
	}

	public String getDomainProject() {
		return this.props.getProperty(NAKEDUML_DOMAIN_PROJECT);
	}

	public String getWebUIProject() {
		return this.props.getProperty(NAKEDUML_WEBUI_PROJECT);
	}

	public String getSqlDirectory() {
		return this.props.getProperty(NAKEDUML_SQL_DIRECTORY);
	}

	public String getDateType() {
		return this.props.getProperty(NAKEDUML_DATE_TYPE);
	}

	public String getRealType() {
		return this.props.getProperty(NAKEDUML_REAL_TYPE);
	}

	public String getMappedTypesPackage() {
		return this.props.getProperty(NAKEDUML_MAPPED_TYPES_PACKAGE);
	}

	public String getEMailAddressType() {
		return this.props.getProperty(NAKEDUML_EMAIL_ADDRESS_TYPE);
	}

	public boolean generateFeature(String featureName) {
		return "true".equals(this.props.getProperty(featureName));
	}

	public String getName() {
		return "SomeName";
	}

	public void mapOutputRoot(String name, File destination) {
		this.outputRootMap.put(name, destination);
	}

	public File getMappedDestination(String name) {
		return this.outputRootMap.get(name);
	}

	@Deprecated
	public Set<String> getSelectedFeatures() {
		return this.selectedFeatures;
	}

	public int getNumberOfColumns() {
		return new Integer(this.props.getProperty(NAKEDUML_LIST_COLUMNS));
	}

	public boolean needsSchema() {
		return Boolean.valueOf(this.props.getProperty(NAKEDUML_NEED_SCHEMA));
	}

	public String getDefaultSchema() {
		return this.props.getProperty(NAKEDUML_DEFAULT_SCHEMA);
	}

	public String getApplicationPagesXmlPath() {
		return this.props.getProperty(NAKEDUML_APPLICATION_PAGES_XML, "");
	}

	public String getTooManyRataTableRows() {
		return this.props.getProperty(NAKEDUML_TOOMANY_DATATABLE_ROWS, "10");
	}

	public String getCompositeNodeInterface() {
		return this.props.getProperty(NAKEDUML_COMPOSITE_NODE_INTERFACE, "util.CompositionNode");
	}

	public Boolean getEnversAudited() {
		return Boolean.valueOf(this.props.getProperty(NAKEDUML_ENVERS_AUDITED, "false"));
	}

	public Boolean getDataGeneration() {
		return Boolean.valueOf(this.props.getProperty(NAKEDUML_DATA_GENERATION, "true"));
	}

	public String getProjectName() {
		return this.props.getProperty(NAKEDUML_PROJECT_NAME, "nakedmonkey");
	}

	public String getHibernateCfgName() {
		return this.props.getProperty(NAKEDUML_HIBERNATE_CFG_NAME, "java:/monkeySessionFactory");
	}
	
	public String getNakedUmlProjectGenName() {
		return this.props.getProperty(NAKEDUML_PROJECT_GEN_NAME, "nakedumlgenproject");
	}

	public void setNakedUmlProjectGenName(String name) {
		this.props.setProperty(NAKEDUML_PROJECT_GEN_NAME, name);
	}

	public String getNakedUmlProjectGenGroupId() {
		return this.props.getProperty(NAKEDUML_PROJECT_GEN_GROUPID, "nakedumlgenprojectgroupid");
	}

	public void setNakedUmlProjectGenGroupId(String name) {
		this.props.setProperty(NAKEDUML_PROJECT_GEN_GROUPID, name);
	}

	public String getNakedUmlProjectGenRoot() {
		return this.props.getProperty(NAKEDUML_PROJECT_GEN_ROOT, "/tmp");
	}

	public void setNakedUmlProjectGenRoot(String name) {
		this.props.setProperty(NAKEDUML_PROJECT_GEN_ROOT, name);
	}
	
	public Boolean isSeamAnnotations() {
		return this.props.getProperty(NAKEDUML_SEAM_OR_WELD, "seam").equals("seam");
	}
	
	public void store(Writer writer) {
		try {
			props.store(writer, "NakedUML");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
