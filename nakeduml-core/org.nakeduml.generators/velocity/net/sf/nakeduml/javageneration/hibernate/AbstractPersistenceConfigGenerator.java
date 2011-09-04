package net.sf.nakeduml.javageneration.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.SortedProperties;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.IntegrationCodeGenerator;
import net.sf.nakeduml.javageneration.JavaTransformationStep;
import net.sf.nakeduml.javageneration.TextSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.basicjava.JavaMetaInfoMapGenerator;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5JavaStep;
import net.sf.nakeduml.javageneration.jbpm5.Jbpm5Util;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

import org.nakedum.velocity.AbstractTextProducingVisitor;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.runtime.environment.Environment;

public abstract class AbstractPersistenceConfigGenerator extends AbstractTextProducingVisitor implements JavaTransformationStep,IntegrationCodeGenerator{
	public final class MappingCollector extends AbstractJavaProducingVisitor{
		final HashSet<OJPathName> classes = new HashSet<OJPathName>();
		final HashSet<OJPathName> signals = new HashSet<OJPathName>();
		public MappingCollector(INakedModelWorkspace workspace){
			this.workspace = workspace;
		}
		@VisitBefore
		public void signal(INakedSignal s){
			signals.add(OJUtil.classifierPathname(s));
		}
		@VisitBefore(matchSubclasses = true)
		public void visitClassifier(INakedClassifier c){
			if(isPersistent(c)){
				classes.add(OJUtil.classifierPathname(c));
			}
		}
		@VisitBefore(matchSubclasses = true)
		public void visitOpaqueAction(INakedEmbeddedTask a){
			classes.add(OJUtil.classifierPathname(a.getMessageStructure()));
		}
		@VisitBefore(matchSubclasses = true)
		public void visitOperation(INakedOperation o){
			if(o.isLongRunning()){
				classes.add(OJUtil.classifierPathname(o.getMessageStructure()));
			}
		}
		@Override
		public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root){
			return super.getChildren(root);
		}
	}
	public AbstractPersistenceConfigGenerator(){
		super();
	}
	@Override
	public void initialize(OJPackage pac,NakedUmlConfig config,TextWorkspace textWorkspace,INakedModelWorkspace workspace){
		this.initialize(config, textWorkspace, workspace);
	}
	@SuppressWarnings("unchecked")
	@VisitBefore
	public void visitWorkspace(INakedModelWorkspace workspace){
		if(requiresIntegration()){
			Collection<INakedRootObject> rootObjects = (Collection<INakedRootObject>) workspace.getOwnedElements();
			generateConfigAndEnvironment(rootObjects, TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE, true, workspace);
		}
	}
	protected boolean requiresIntegration(){
		return transformationContext.isIntegrationPhase() || config.getSourceFolderStrategy().isSingleProjectStrategy();
	}
	@VisitBefore
	public void visitModel(INakedModel model){
		if(!requiresIntegration()){
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
			properties.setProperty("nakeduml.jdbc.connection.url", "jdbc:hsqldb:mem:"+owner.getName()+"DB");
		}else{
			properties.setProperty(Environment.DBMS, "HSQL");
			properties.setProperty(Environment.JBPM_KNOWLEDGE_BASE_IMPLEMENTATION, Jbpm5Util.jbpmKnowledgeBase(owner).toJavaString());
			properties.setProperty(Environment.PERSISTENT_NAME_CLASS_MAP, JavaMetaInfoMapGenerator.javaMetaInfoMapPath(owner).toJavaString());
//			properties.setProperty(Environment.JDBC_CONNECTION_URL, "jdbc:hsqldb:mem:"+owner.getName()+"DB");
			properties.setProperty("nakeduml.jdbc.connection.url", "jdbc:hsqldb:mem:"+owner.getName()+"DB");
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
		MappingCollector collector = new MappingCollector(workspace);
		// do all models
		for(INakedElement element:models){
			if(element instanceof INakedModel){
				collector.visitRecursively(element);
			}
		}
		vars.put("persistentClasses", collector.classes);
		vars.put("signals", collector.signals);
		vars.put("pkg", OJUtil.utilPackagePath(owner));
		return vars;
	}
}