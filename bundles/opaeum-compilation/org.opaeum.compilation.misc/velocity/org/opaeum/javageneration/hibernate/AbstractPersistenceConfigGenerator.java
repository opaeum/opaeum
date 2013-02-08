package org.opaeum.javageneration.hibernate;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.CodeGenerationStrategy;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.javageneration.IntegrationCodeGenerator;
import org.opaeum.javageneration.JavaTransformationStep;
import org.opaeum.javageneration.basicjava.JavaMetaInfoMapGenerator;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.ModelWorkspace;
import org.opaeum.rap.RapCapabilities;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;
import org.opaeum.textmetamodel.TextWorkspace;
import org.opaeum.util.SortedProperties;
import org.opaeum.velocity.AbstractTextProducingVisitor;

public abstract class AbstractPersistenceConfigGenerator extends AbstractTextProducingVisitor implements JavaTransformationStep,
		IntegrationCodeGenerator{
	private OJUtil ojUtil;
	public AbstractPersistenceConfigGenerator(){
		super();
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	@VisitBefore
	public void visitWorkspace(ModelWorkspace workspace){
		if(shouldProcessWorkspace()){
			Collection<Package> rootObjects = (Collection) workspace.getOwnedElements();
			generateConfigAndEnvironment(rootObjects, TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE, true, workspace);
		}
	}
	protected boolean shouldProcessWorkspace(){
		return transformationContext.isIntegrationPhase();
	}
	@VisitBefore
	public void visitModel(Model model){
		if(shouldProcessModel()){
			Collection<Package> selfAndDependencies = EmfPackageUtil.getAllDependencies(model);
			selfAndDependencies.add(model);
			TextSourceFolderIdentifier domainGenTestResource = TextSourceFolderIdentifier.DOMAIN_GEN_TEST_RESOURCE;
			generateConfigAndEnvironment(selfAndDependencies, domainGenTestResource, false, model);
		}
	}
	protected boolean shouldProcessModel(){
		SourceFolderDefinition sfd = config.getSourceFolderDefinition(TextSourceFolderIdentifier.DOMAIN_GEN_TEST_RESOURCE);
		return !(transformationContext.isIntegrationPhase() || sfd.isOneProjectPerWorkspace());
	}
	protected abstract String getOutputPath(Element model);
	protected abstract String getConfigName(Element model);
	protected abstract String getTemplateName();
	protected abstract String getDomainEnvironmentImplementation();
	protected abstract String getAdaptorEnvironmentImplementation();
	private void generateConfigAndEnvironment(Collection<Package> models,TextSourceFolderIdentifier outputRootId,
			boolean isAdaptorEnvironment,Element owner){
		SortedProperties properties = new SortedProperties();
		HashMap<String,Object> vars = buildVars(models, isAdaptorEnvironment, owner);
		properties.setProperty(Environment.DBMS, config.getDbms());
		properties.setProperty(Environment.PERSISTENT_NAME_CLASS_MAP, ojUtil.utilClass(owner, JavaMetaInfoMapGenerator.JAVA_META_INFO_MAP_SUFFIX).toJavaString());
		properties.setProperty(Environment.JDBC_CONNECTION_URL, config.getJdbcConnectionUrl());
		properties.setProperty(Environment.DB_USER, config.getDbUser());
		properties.setProperty(Environment.DB_PASSWORD, config.getDbPassword());
		properties.setProperty(Environment.ENVIRONMENT_IMPLEMENTATION, isAdaptorEnvironment ? getAdaptorEnvironmentImplementation()
				: getDomainEnvironmentImplementation());
		properties.setProperty(Environment.HIBERNATE_CONFIG_NAME, getConfigName(owner));
//		findOrCreateTextFile(properties, outputRootId, Environment.PROPERTIES_FILE_NAME);
		processTemplate(workspace, getTemplateName(), getOutputPath(owner), outputRootId, vars);
	}
	private HashMap<String,Object> buildVars(Collection<? extends Element> models,boolean isAdaptorEnvironment,Element owner){
		HashMap<String,Object> vars = new HashMap<String,Object>();
		vars.put("requiresAuditing", true);
		vars.put("config", this.config);
		vars.put("isAdaptorEnvironment", isAdaptorEnvironment);
		vars.put("persistenceConfigName", getConfigName(owner));
		Collection<OJPathName> persistentClasses = new HashSet<OJPathName>();
		for(String string:config.getAdditionalPersistentClasses()){
			persistentClasses.add(new OJPathName(string));
		}
			// CLasses across multiple jars need to be registered explicitly
			TreeIterator<Notifier> iter = workspace.getResourceSet().getAllContents();
			while(iter.hasNext()){
				Notifier n = iter.next();
				if(n instanceof Element){
					Element e = (Element) n;
					if(e instanceof Classifier && EmfClassifierUtil.isComplexStructure((Classifier) e) && EmfClassifierUtil.isPersistent((Type) e)
							&& isGeneratingElement(e)){
						persistentClasses.add(ojUtil.classifierPathname((Classifier) e));
						if( e instanceof Behavior){
							persistentClasses.add(ojUtil.tokenPathName((Behavior)  e));
						}
					}else if(e instanceof Operation && EmfBehaviorUtil.isLongRunning(((Operation) e)) && isGeneratingElement(e)){
						persistentClasses.add(ojUtil.classifierPathname((Operation) e));
					}else if(e instanceof Enumeration && isGeneratingElement(e)
							&& ojUtil.getCodeGenerationStrategy((Classifier) e) == CodeGenerationStrategy.ALL
							&& !(EmfElementFinder.getRootObject(e) instanceof Profile)){
						persistentClasses.add(new OJPathName(ojUtil.classifierPathname((Enumeration) e) + "Entity"));
					}else if(e instanceof Action && EmfActionUtil.isEmbeddedTask((ActivityNode) e) && isGeneratingElement(e)){
						persistentClasses.add(ojUtil.classifierPathname(((Action) e)));
					}else if(e instanceof StructuredActivityNode
							&& EmfBehaviorUtil.hasExecutionInstance(EmfActivityUtil.getContainingActivity(((StructuredActivityNode) e)))
							&& isGeneratingElement(e)){
						persistentClasses.add(ojUtil.classifierPathname((StructuredActivityNode) e));
					}
				}
			vars.put("persistentClasses", persistentClasses);
			vars.put("pkg", ojUtil.utilPackagePath(owner));
		}
		return vars;
	}
	@Override
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace,EmfWorkspace workspace, OJUtil ojUtil){
		super.initialize(config, textWorkspace, workspace,ojUtil);
		this.ojUtil=ojUtil;

		// TODO Auto-generated method stub
	}
	private boolean isGeneratingElement(Element e){
		Package rootObject = EmfElementFinder.getRootObject(e);
		if(shouldProcessWorkspace()){
			return workspace.getRootObjects().contains(rootObject) || (rootObject instanceof Model && EmfPackageUtil.isRegeneratingLibrary((Model) rootObject)) ;
		}else{
			return workspace.getGeneratingModelsOrProfiles().contains(rootObject);
		}
	}
}