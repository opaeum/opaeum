package org.opaeum.feature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Currency;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.opaeum.metamodel.workspace.AbstractStrategyFactory;
import org.opaeum.runtime.environment.VersionNumber;
import org.opaeum.runtime.persistence.DatabaseManagementSystem;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.ProjectNameStrategy;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.util.SortedProperties;

public class OpaeumConfig{
	private static final String DB_USER = "opaeum.database.user";
	private static final String DB_VENDOR = "opaeum.database.vendor";
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
	private static final String PROJECT_NAME_OVERRIDE = "opaeum.projectname.override";
	private static final String DBMS = "opaeum.dbms";
	private static final String JDBC_CONNECTION_URL = "opaeum.jdbc.connection.url";
	private static final String DB_PASSWORD = "opaeum.database.password";
	private static final String SUPPORTED_LOCALES = "opaeum.supported.locales";
	private static final String DEFAULT_CURRENCY = "opaeum.default.currency";
	private static final String UI_MODULE_ACTIVE = "opaeum.ui.module.active";
	private static final String JDBC_DRIVER = "opaeum.jdbc.driver";
	private static final String DB_NAME = "opaeum.database.name";
	private static final String IS_SIMULATION_CONTEXT = "opaeum.is.simulation.context";
	private static final String DEV_USERNAME = "opaeum.developer.username";
	private static final String IMPLEMENT_MAPS = "opaeum.implement.maps";
	private static Map<String,Class<?>> classRegistry = new HashMap<String,Class<?>>();
	private Properties props = new SortedProperties();
	private File outputRoot;
	private Map<ISourceFolderIdentifier,SourceFolderDefinition> sourceFolderDefinitions = new HashMap<ISourceFolderIdentifier,SourceFolderDefinition>();
	private File file;
	private SqlDialect sqlDialect;
	private VersionNumber version;
	public OpaeumConfig(File file){
		this.file = file;
		reload();
	}
	public void reload(){
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
			String name = props.getProperty(SOURCE_FOLDER_STRATEGY, "org.opaeum.sourcefolderstrategies.SingleProjectRapSourceFolderStrategy");
			Class<?> c = getClass(name);
			return (ISourceFolderStrategy) c.newInstance();
		}catch(Exception e){
			try{
				Class<?> c = getClass("org.opaeum.sourcefolderstrategies.SingleProjectRapSourceFolderStrategy");
				return (ISourceFolderStrategy) c.newInstance();
			}catch(Exception e1){
				return null;
			}
		}
	}
	public Currency getDefaultCurrency(){
		return Currency.getInstance(props.getProperty(DEFAULT_CURRENCY, "ZAR"));
	}
	public void setDefaultCurrency(Currency c){
		if(c == null){
			props.remove(DEFAULT_CURRENCY);
		}else{
			props.put(DEFAULT_CURRENCY, c.getCurrencyCode());
		}
	}
	public void setSupportedLocales(Collection<Locale> locales){
		StringBuilder sb = new StringBuilder();
		for(Locale locale:locales){
			sb.append(locale.toString());
			sb.append(";");
		}
		props.put(SUPPORTED_LOCALES, sb.toString());
	}
	public Collection<Locale> getSupportedLocales(){
		Collection<String> s = new HashSet<String>();
		for(String l:props.getProperty(SUPPORTED_LOCALES, "").split("\\;")){
			s.add(l);
		}
		List<Locale> availableLocales = getAvailableLocales();
		Collection<Locale> result = new ArrayList<Locale>();
		for(Locale locale:availableLocales){
			if(s.contains(locale.toString())){
				result.add(locale);
			}
		}
		return result;
	}
	public static Class<?> getClass(String name){
		Class<?> c;
		if(classRegistry.containsKey(name)){
			c = classRegistry.get(name);
		}else{
			try{
				c = Thread.currentThread().getContextClassLoader().loadClass(name);
			}catch(ClassNotFoundException e){
				throw new RuntimeException(name, e);
			}
		}
		return c;
	}
	// A workaround for the Eclipse restriction that upstream plugins do not have access to downstream plugin classes
	public static void registerClass(Class<?> c){
		classRegistry.put(c.getName(), c);
	}
	public void loadDefaults(String projectName){
		if(getApplicationIdentifier() == null){
			this.props.setProperty(WORKSPACE_IDENTIFIER, projectName);
		}else{
			projectName = getApplicationIdentifier();
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
		if(!this.props.containsKey(UI_MODULE_ACTIVE)){
			this.props.setProperty(UI_MODULE_ACTIVE, "true");
		}
		if(!this.props.containsKey(GENERATE_MAVEN_POMS)){
			this.props.setProperty(GENERATE_MAVEN_POMS, "false");
		}
		if(!this.props.containsKey(AUTO_SYNC)){
			this.props.setProperty(AUTO_SYNC, "true");
		}
		if(!this.props.containsKey(SOURCE_FOLDER_STRATEGY)){
			this.props.setProperty(SOURCE_FOLDER_STRATEGY, "org.opaeum.sourcefolderstrategies.SingleProjectRapSourceFolderStrategy");
		}
	}
	public String getJdbcDialect(){
		return this.props.getProperty(JDBC_DIALECT);
	}
	public void setGenerateMavenPoms(boolean b){
		this.props.setProperty(GENERATE_MAVEN_POMS, String.valueOf(b));
	}
	public boolean generateMavenPoms(){
		return "true".equals(this.props.getProperty(GENERATE_MAVEN_POMS, "false"));
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
		setOutputRoot(getSourceFolderStrategy().calculateOutputRoot(getConfigFile(), modelProjectDir, getApplicationIdentifier()));
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
		return this.props.getProperty(ID_GENERATOR_STRATEGY, "TABLE");
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
	public SourceFolderDefinition defineSourceFolder(ISourceFolderIdentifier id,ProjectNameStrategy pns,String projectQualifier,String sourceFolderQualifier){
		SourceFolderDefinition sfd = new SourceFolderDefinition(pns, projectQualifier, sourceFolderQualifier);
		sourceFolderDefinitions.put(id, sfd);
		String p = getProjectNameOverride();
		if(p == null || p.length() == 1){
			sfd.clearProjectNameOverride();
		}else{
			sfd.overrideProjectName(p);
		}
		return sfd;
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
	public String getApplicationIdentifier(){
		return this.props.getProperty(WORKSPACE_IDENTIFIER, "");
	}
	public String getApplicationName(){
		return this.props.getProperty(WORKSPACE_NAME, "");
	}
	public void setApplicationIdentifier(String workspaceIdentifier){
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
				if(string.length() > 0){
					result.add((Class<? extends ITransformationStep>) getClass(string));
				}
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
	public void reset(){
		this.sqlDialect = null;
	}
	public void setApplicationName(String name){
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
	public void setAdditionalPersistentClass(Collection<String> cs){
		if(cs.isEmpty()){
			this.props.remove(ADDITIONAL_PERSISTENT_CLASSES);
		}else{
			StringBuilder sb = new StringBuilder();
			for(String string:cs){
				sb.append(string);
				sb.append(";");
			}
			this.props.setProperty(ADDITIONAL_PERSISTENT_CLASSES, sb.substring(0, sb.length() - 1));
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
	public static Map<String,Class<?>> getClassRegistry(){
		return classRegistry;
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
	public String getProjectNameOverride(){
		return props.getProperty(PROJECT_NAME_OVERRIDE);
	}
	public void setProjectNameOverride(String p){
		for(SourceFolderDefinition sfd:sourceFolderDefinitions.values()){
			if(p == null || p.length() == 0){
				sfd.clearProjectNameOverride();
			}else{
				sfd.overrideProjectName(p);
			}
		}
		props.setProperty(PROJECT_NAME_OVERRIDE, p);
	}
	public String getDbms(){
		if(getJdbcConnectionUrl().contains("postgresql")){
			return DatabaseManagementSystem.POSTGRESQL.name();
		}
		if(getJdbcConnectionUrl().contains("hsql")){
			return DatabaseManagementSystem.HSQL.name();
		}
		// TODO etc
		return DatabaseManagementSystem.GENERIC.name();
	}
	public String getJdbcConnectionUrl(){
		return props.getProperty(JDBC_CONNECTION_URL, "jdbc:postgresql://localhost:5432/" + getApplicationIdentifier());
	}
	public String getDbPassword(){
		return props.getProperty(DB_PASSWORD, "postgres");
	}
	public boolean hasOutputProjectOverride(){
		String projectNameOverride = getProjectNameOverride();
		return projectNameOverride != null && projectNameOverride.length() > 0;
	}
	public static java.util.List<Locale> getAvailableLocales(){
		java.util.List<Locale> availableLocales = new ArrayList<Locale>(Arrays.asList(Locale.getAvailableLocales()));
		availableLocales.add(new Locale("af", "za"));
		availableLocales.add(new Locale("zu", "za"));
		availableLocales.add(new Locale("xh", "za"));
		availableLocales.add(new Locale("nso", "za"));
		Collections.sort(availableLocales, new Comparator<Locale>(){
			@Override
			public int compare(Locale o1,Locale o2){
				int c = o1.getDisplayCountry().compareTo(o2.getDisplayCountry());
				if(c == 0){
					return o1.getDisplayLanguage().compareTo(o2.getDisplayLanguage());
				}
				return c;
			}
		});
		return availableLocales;
	}
	public boolean isUiModelerActive(){
		return "true".equalsIgnoreCase(props.getProperty(UI_MODULE_ACTIVE)) && !isSimulationContext();
	}
	public void setUiModelerActive(boolean t){
		props.setProperty(UI_MODULE_ACTIVE, "" + t);
	}
	public boolean isJpa2(){
		return props.getProperty("opaeum.persistence.jpa2", "true").equals("true");
	}
	public String getDbUserName(){
		return props.getProperty(DB_USER, getApplicationIdentifier() + "_user");
	}
	public void setDbUserName(String dbUserName){
		this.props.setProperty(DB_USER, dbUserName);
	}
	public String getDbVendor(){
		return props.getProperty(DB_VENDOR, "postgres");
	}
	public void setDbVendor(String dbVendor){
		this.props.setProperty(DB_VENDOR, dbVendor);
	}
	public void setDbPassword(String dbPassword){
		this.props.setProperty(DB_PASSWORD, dbPassword);
	}
	public void setJdbcConnectionUrl(String jdbcConnectionUrl){
		this.props.setProperty(JDBC_CONNECTION_URL, jdbcConnectionUrl);
	}
	public void setJdbcDriver(String driver){
		this.props.setProperty(JDBC_DRIVER, driver);
	}
	public String getJdbcDriver(){
		return this.props.getProperty(JDBC_DRIVER, "org.postgresql.Driver");
	}
	public void setDevUsername(String driver){
		this.props.setProperty(DEV_USERNAME, driver);
	}
	public String getDevUsername(){
		return this.props.getProperty(DEV_USERNAME, "ampieb@gmail.com");
	}
	public void setDbName(String driver){
		this.props.setProperty(DB_NAME, driver);
	}
	public String getDbName(){
		return this.props.getProperty(DB_NAME, getApplicationIdentifier() + "_db");
	}
	public void addAdditionalTransformationStep(Class<?> class1){
		String property = this.props.getProperty(ADDITIONAL_TRANSFORMATION_STEPS, "");
		if(!property.contains(class1.getName())){
			property = property + ";" + class1.getName();
			this.props.setProperty(ADDITIONAL_TRANSFORMATION_STEPS, property);
			registerClass(class1);
			store();
		}
	}
	public boolean isSimulationContext(){
		return this.props.getProperty(IS_SIMULATION_CONTEXT, "false").equals("true");
	}
	public void setSimulationContext(boolean t){
		props.setProperty(IS_SIMULATION_CONTEXT, ""+t);
	}
	public boolean implementMaps(){
		return this.props.getProperty(IMPLEMENT_MAPS, "true").equals("true");
	}
}
