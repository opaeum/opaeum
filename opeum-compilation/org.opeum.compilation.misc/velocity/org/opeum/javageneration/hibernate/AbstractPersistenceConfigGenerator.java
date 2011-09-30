package org.opeum.javageneration.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.opeum.feature.OpeumConfig;
import org.opeum.feature.SortedProperties;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.javageneration.IntegrationCodeGenerator;
import org.opeum.javageneration.JavaTransformationStep;
import org.opeum.javageneration.TextSourceFolderIdentifier;
import org.opeum.javageneration.basicjava.JavaMetaInfoMapGenerator;
import org.opeum.javageneration.jbpm5.Jbpm5JavaStep;
import org.opeum.javageneration.jbpm5.Jbpm5Util;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.bpm.INakedEmbeddedTask;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.INakedElementOwner;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedRootObject;
import org.opeum.metamodel.models.INakedModel;
import org.opeum.metamodel.workspace.INakedModelWorkspace;
import org.opeum.textmetamodel.TextWorkspace;

import org.nakedum.velocity.AbstractTextProducingVisitor;
import org.opeum.java.metamodel.OJPackage;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.runtime.environment.Environment;

public abstract class AbstractPersistenceConfigGenerator extends AbstractTextProducingVisitor implements JavaTransformationStep,IntegrationCodeGenerator{
	public AbstractPersistenceConfigGenerator(){
		super();
	}
	@Override
	public void initialize(OJPackage pac,OpeumConfig config,TextWorkspace textWorkspace,INakedModelWorkspace workspace){
		this.initialize(config, textWorkspace, workspace);
	}
	@SuppressWarnings("unchecked")
	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace workspace){
		if(shouldProcessWorkspace()){
			Collection<INakedRootObject> rootObjects = (Collection<INakedRootObject>) workspace.getOwnedElements();
			generateConfigAndEnvironment(rootObjects, TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE, true, workspace);
		}
	}
	protected boolean shouldProcessWorkspace(){
		return transformationContext.isIntegrationPhase();
	}
	@VisitBefore
	public void visitModel(INakedModel model){
		if(shouldProcessModel()){
			Collection<INakedRootObject> selfAndDependencies = new ArrayList<INakedRootObject>(model.getAllDependencies());
			selfAndDependencies.add(model);
			TextSourceFolderIdentifier domainGenTestResource = TextSourceFolderIdentifier.DOMAIN_GEN_TEST_RESOURCE;
			generateConfigAndEnvironment(selfAndDependencies, domainGenTestResource, false, model);
			TextSourceFolderIdentifier adaptorGenTestResource = TextSourceFolderIdentifier.ADAPTOR_GEN_TEST_RESOURCE;
			if(!adaptorGenTestResource.equals(domainGenTestResource)){
				generateConfigAndEnvironment(selfAndDependencies, adaptorGenTestResource, true, model);
			}
		}
	}
	protected boolean shouldProcessModel(){
		return !transformationContext.isIntegrationPhase();
	}
	protected abstract String getOutputPath(INakedElementOwner model);
	protected abstract String getConfigName(INakedElementOwner model);
	protected abstract String getTemplateName();
	protected abstract String getDomainEnvironmentImplementation();
	protected abstract String getAdaptorEnvironmentImplementation();
	private void generateConfigAndEnvironment(Collection<INakedRootObject> models,TextSourceFolderIdentifier outputRootId,boolean isAdaptorEnvironment,
			INakedElementOwner owner){
		SortedProperties properties = new SortedProperties();
		HashMap<String,Object> vars = buildVars(models, isAdaptorEnvironment, owner);
		if(isAdaptorEnvironment){
			properties.setProperty(Environment.JBPM_KNOWLEDGE_BASE_IMPLEMENTATION, Jbpm5Util.jbpmKnowledgeBase(owner).toJavaString());
			properties.setProperty(Environment.DBMS, "POSTGRESQL");
			properties.setProperty(Environment.PERSISTENT_NAME_CLASS_MAP, JavaMetaInfoMapGenerator.javaMetaInfoMapPath(owner).toJavaString());
			properties.setProperty("opeum.jdbc.connection.url", "jdbc:hsqldb:mem:" + owner.getName() + "DB");
		}else{
			properties.setProperty(Environment.DBMS, "HSQL");
			properties.setProperty(Environment.JBPM_KNOWLEDGE_BASE_IMPLEMENTATION, Jbpm5Util.jbpmKnowledgeBase(owner).toJavaString());
			properties.setProperty(Environment.PERSISTENT_NAME_CLASS_MAP, JavaMetaInfoMapGenerator.javaMetaInfoMapPath(owner).toJavaString());
			// properties.setProperty(Environment.JDBC_CONNECTION_URL, "jdbc:hsqldb:mem:"+owner.getName()+"DB");
			properties.setProperty("opeum.jdbc.connection.url", "jdbc:hsqldb:mem:" + owner.getName() + "DB");
		}
		properties.setProperty(Environment.DB_USER, config.getDbUser());
		properties.setProperty(Environment.ENVIRONMENT_IMPLEMENTATION, isAdaptorEnvironment ? getAdaptorEnvironmentImplementation()
				: getDomainEnvironmentImplementation());
		properties.setProperty(Environment.HIBERNATE_CONFIG_NAME, getConfigName(owner));
		findOrCreateTextFile(properties, outputRootId, Environment.PROPERTIES_FILE_NAME);
		processTemplate(workspace, getTemplateName(), getOutputPath(owner), outputRootId, vars);
	}
	@SuppressWarnings("unchecked")
	private HashMap<String,Object> buildVars(Collection<? extends INakedElement> models,boolean isAdaptorEnvironment,INakedElementOwner owner){
		HashMap<String,Object> vars = new HashMap<String,Object>();
		vars.put("requiresAuditing", true);
		vars.put("config", this.config);
		vars.put("isAdaptorEnvironment", isAdaptorEnvironment);
		vars.put("requiresJbpm", transformationContext.isAnyOfFeaturesSelected(Jbpm5JavaStep.class));
		vars.put("persistenceConfigName", getConfigName(owner));
		Collection<OJPathName> persistentClasses = new HashSet<OJPathName>();
		for(String string:config.getAdditionalPersistentClasses()){
			persistentClasses.add(new OJPathName(string));
		}
		for(INakedElement e:workspace.getAllElements()){
			if(e instanceof INakedComplexStructure && ((INakedComplexStructure) e).isPersistent() && isGeneratingElement(e)){
				persistentClasses.add(OJUtil.classifierPathname((INakedClassifier) e));
			}else if(e instanceof INakedOperation && ((INakedOperation) e).isLongRunning() && isGeneratingElement(e)){
				persistentClasses.add(OJUtil.classifierPathname(((INakedOperation) e).getMessageStructure()));
			}else if(e instanceof INakedEmbeddedTask && isGeneratingElement(e)){
				persistentClasses.add(OJUtil.classifierPathname(((INakedEmbeddedTask) e).getMessageStructure()));
			}
		}
		vars.put("persistentClasses", persistentClasses);
		vars.put("pkg", OJUtil.utilPackagePath(owner));
		return vars;
	}
	private boolean isGeneratingElement(INakedElement e){
		return workspace.getGeneratingModelsOrProfiles().contains(e.getRootObject());
	}
}