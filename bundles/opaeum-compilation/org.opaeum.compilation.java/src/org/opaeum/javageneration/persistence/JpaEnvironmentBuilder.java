package org.opaeum.javageneration.persistence;

import java.io.File;
import java.util.Arrays;

import javax.persistence.spi.PersistenceUnitInfo;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityNode;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Operation;
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
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.IntegrationCodeGenerator;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.Java6ModelGenerator;
import org.opaeum.javageneration.basicjava.JavaMetaInfoMapGenerator;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.PropertiesSource;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.TextSourceFolderIdentifier;
import org.opaeum.util.SortedProperties;

@StepDependency(phase = JavaTransformationPhase.class,requires = {Java6ModelGenerator.class},after = {Java6ModelGenerator.class})
public class JpaEnvironmentBuilder extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	public static boolean DEVELOPMENT_MODE = true;
	@VisitBefore()
	public void visitWorkspace(EmfWorkspace ew){
		if(transformationContext.isIntegrationPhase()){
			OJPathName pathName = ojUtil.utilClass(ew, "Environment");
			OJClass envClass = new OJAnnotatedClass(pathName.getLast());
			envClass.setSuperclass(new OJPathName(Environment.class.getName()));
			findOrCreatePackage(pathName.getHead()).addToClasses(envClass);
			super.createTextPath(envClass, JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
			envClass.addToImports(IPersistentObject.class.getName());
			OJAnnotatedField instance = new OJAnnotatedField("INSTANCE", pathName);
			instance.setStatic(true);
			instance.setVisibility(OJVisibilityKind.PUBLIC);
			instance.setInitExp("new " + pathName.getLast() + "()");
			envClass.addToFields(instance);
			OJAnnotatedOperation register = new OJAnnotatedOperation("register");
			envClass.addToOperations(register);
			register.getBody().addToStatements("super.register()");
			register.getBody().addToStatements("INSTANCE=new " + pathName.getLast() + "()");
			OJAnnotatedOperation unregister = new OJAnnotatedOperation("unregister");
			envClass.addToOperations(unregister);
			unregister.getBody().addToStatements("super.unregister()");
			unregister.getBody().addToStatements("INSTANCE=null");
			OJAnnotatedOperation getId = new OJAnnotatedOperation("getApplicationIdentifier", new OJPathName("String"));
			envClass.addToOperations(getId);
			getId.initializeResultVariable("\"" + ew.getIdentifier() + "\"");
			OJPathName mim = ojUtil.utilClass(ew, JavaMetaInfoMapGenerator.JAVA_META_INFO_MAP_SUFFIX);
			envClass.addToFields(new OJAnnotatedField("metaInfoMap", mim));
			OJAnnotatedOperation getMetaInfoMap = new OJAnnotatedOperation("getMetaInfoMap", mim);
			getMetaInfoMap.initializeResultVariable("metaInfoMap");
			getMetaInfoMap.getBody().addToStatements(new OJIfStatement("metaInfoMap==null", "result=metaInfoMap=new " + mim.getLast() + "()"));
			envClass.addToOperations(getMetaInfoMap);
			SortedProperties properties = new SortedProperties();
			properties.setProperty(Environment.DBMS, config.getDbms());
			properties.setProperty(Environment.JDBC_CONNECTION_URL, config.getJdbcConnectionUrl());
			properties.setProperty(Environment.DB_USER, config.getDbUser());
			properties.setProperty(Environment.DB_PASSWORD, config.getDbPassword());
			properties.setProperty(Environment.JDBC_DRIVER_CLASS, config.getJdbcDriver());
			properties.setProperty(Environment.DEV_USERNAME, config.getDevUsername());
			if(config.getSourceFolderStrategy().isSingleProjectStrategy()){
				SourceFolder sourceFolder = getSourceFolder(config.getSourceFolderDefinition(JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC));
				String path = new File(new File(config.getOutputRoot(), sourceFolder.getProject().getName()), "ui").getAbsolutePath();
				properties.setProperty(Environment.DEV_UI_DIR, path);
			}
			properties.setProperty(Environment.UPDATE_DB_DEF, "false");
			findOrCreateTextFile(properties, TextSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_RESOURCE, Environment.PROPERTIES_FILE_NAME);
			OJAnnotatedOperation getPersistenceUnitInfo = new OJAnnotatedOperation("getPersistenceUnitInfo", new OJPathName(PersistenceUnitInfo.class.getName()));
			OJPathName pui = ojUtil.utilClass(workspace, "PersistenceUnitInfo");
			getPersistenceUnitInfo.initializeResultVariable("new " + pui + "(this)");
			envClass.addToOperations(getPersistenceUnitInfo);
			envClass.setSuperclass(new OJPathName("org.opaeum.runtime.jpa.AbstractJpaEnvironment"));
			OJAnnotatedClass puic = new OJAnnotatedClass(pui.getLast());
			puic.setSuperclass(new OJPathName("org.opaeum.runtime.jpa.AbstractPersistenceUnitInfo"));
			findOrCreatePackage(pui.getHead()).addToClasses(puic);
			OJConstructor constr = new OJConstructor();
			constr.addParam("env", envClass.getPathName());
			constr.setDelegateConstructor("super(env)");
			puic.addToConstructors(constr);
			createTextPath(puic, JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
			OJAnnotatedOperation getName = new OJAnnotatedOperation("getPersistenceUnitName", new OJPathName("String"));
			puic.addToOperations(getName);
			getName.initializeResultVariable("\"" + ew.getIdentifier() + "\"");
			OJPathName list = new OJPathName("java.util.List");
			list.addToElementTypes(new OJPathName("String"));
			OJAnnotatedOperation getManagedClassNames = new OJAnnotatedOperation("getManagedClassNames", list);
			puic.addToOperations(getManagedClassNames);
			puic.addToImports("java.util.ArrayList");
			for(String string:config.getAdditionalPersistentClasses()){
				getManagedClassNames.getBody().addToStatements("result.add(\"" + string + "\")");
			}
			getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.hibernate.domain.EventOccurrence\")");
			getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.hibernate.domain.AbstractPersistentEnum\")");
			getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.hibernate.domain.AbstractPersistentOpaeumIdEnum\")");
			// getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.audit.AuditEntry\")");
			// getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.audit.AuditEntryPropertyChange\")");
			// getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.audit.EntityPropertyChange\")");
			// getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.audit.PropertyChange\")");
			// getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.audit.StringPropertyChange\")");
			// getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.audit.BooleanPropertyChange\")");
			// getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.audit.DateTimePropertyChange\")");
			// getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.audit.IntegerPropertyChange\")");
			// getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.audit.FloatingPointPropertyChange\")");
			// getManagedClassNames.getBody().addToStatements("result.add(\"org.opaeum.audit.NullPropertyChange\")");
			getManagedClassNames.getBody().addToStatements("result.add(\"" + ojUtil.utilPackagePath(ew) + "\")");
			// CLasses across multiple jars need to be registered explicitly
			TreeIterator<Notifier> iter = workspace.getResourceSet().getAllContents();
			while(iter.hasNext()){
				Notifier n = iter.next();
				if(n instanceof Element){
					Element e = (Element) n;
					if(e instanceof Classifier && EmfClassifierUtil.isComplexStructure((Classifier) e) && EmfClassifierUtil.isPersistent((Type) e)
							&& isGeneratingElement(e)){
						getManagedClassNames.getBody().addToStatements("result.add(\"" + ojUtil.classifierPathname((Classifier) e) + "\")");
						if(e instanceof Behavior && EmfBehaviorUtil.isProcess((Behavior) e)){
							getManagedClassNames.getBody().addToStatements("result.add(\"" + ojUtil.tokenPathName((Behavior) e) + "\")");
						}
					}else if(e instanceof Operation && EmfBehaviorUtil.isLongRunning(((Operation) e)) && isGeneratingElement(e)){
						getManagedClassNames.getBody().addToStatements("result.add(\"" + ojUtil.classifierPathname((Operation) e) + "\")");
					}else if(e instanceof Enumeration && isGeneratingElement(e) && ojUtil.getCodeGenerationStrategy((Classifier) e) == CodeGenerationStrategy.ALL
							&& !(EmfElementFinder.getRootObject(e) instanceof Profile)){
						getManagedClassNames.getBody().addToStatements("result.add(\"" + new OJPathName(ojUtil.classifierPathname((Enumeration) e) + "Entity") + "\")");
					}else if(e instanceof Action && EmfActionUtil.isEmbeddedTask((ActivityNode) e) && isGeneratingElement(e)){
						getManagedClassNames.getBody().addToStatements("result.add(\"" + ojUtil.classifierPathname(((Action) e)) + "\")");
					}else if(e instanceof StructuredActivityNode
							&& EmfBehaviorUtil.hasExecutionInstance(EmfActivityUtil.getContainingActivity(((StructuredActivityNode) e))) && isGeneratingElement(e)){
						getManagedClassNames.getBody().addToStatements("result.add(\"" + ojUtil.classifierPathname((StructuredActivityNode) e) + "\")");
						getManagedClassNames.getBody().addToStatements("result.add(\"" + ojUtil.tokenPathName((StructuredActivityNode) e) + "\")");
					}
				}
			}
			getManagedClassNames.initializeResultVariable("new ArrayList<String>()");
		}
	}
	private boolean isGeneratingElement(Element e){
		return e.getModel() != null && (workspace.getPrimaryRootObjects().contains(e.getModel()) || EmfPackageUtil.isRegeneratingLibrary(e.getModel()));
	}
	public void findOrCreateTextFile(SortedProperties outputBuilder,ISourceFolderIdentifier outputRootId,String...names){
		createTextPath(outputRootId, Arrays.asList(names)).setTextSource(new PropertiesSource(outputBuilder));
	}
}
