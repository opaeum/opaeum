package org.opaeum.javageneration.rap;

import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

public class OpaeumApplicationGenerator extends AbstractJavaProducingVisitor{
	@VisitBefore
	public void beforeWorkspace(EmfWorkspace e){
		OJPackage pk = findOrCreatePackage(ojUtil.utilPackagePath(e));
		OJAnnotatedClass entryPoint = createEntryPoint(e, pk);
		OJAnnotatedClass app = createApplication(e, pk, entryPoint);
		createActivator(pk, app);
	}

	public void createActivator(OJPackage pk,OJAnnotatedClass app){
		OJPathName activatorPath = ojUtil.utilClass(workspace, "Activator");
		OJAnnotatedClass activator=new OJAnnotatedClass(activatorPath.getLast());
		activator.addToImplementedInterfaces(new OJPathName("org.osgi.framework.BundleActivator"));
		activator.setSuperclass(new OJPathName("org.opaeum.runtime.rwt.AbstractOpaeumActivator"));
		pk.addToClasses(activator);
		createTextPath(activator, JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
		OJAnnotatedOperation start = new OJAnnotatedOperation("createApplication", new OJPathName("org.opaeum.runtime.rwt.IOpaeumApplication"));
		start.addParam("bundle", new OJPathName("org.osgi.framework.Bundle"));
		OJIfStatement ifNull=new OJIfStatement(app.getName()+".INSTANCE==null",app.getName()+".INSTANCE=new " +app.getName()+ "(bundle)");
		start.getBody().addToStatements(ifNull);
		start.getBody().addToStatements("result="+app.getName()+".INSTANCE");
		start.initializeResultVariable("null");
		activator.addToOperations(start);
	}

	public OJAnnotatedClass createApplication(EmfWorkspace e,OJPackage pk,OJAnnotatedClass entryPoint){
		OJPathName pn = ojUtil.utilClass(e, "Application");
		OJAnnotatedClass app= new OJAnnotatedClass(pn.getLast());
		pk.addToClasses(app);
		createTextPath(app, JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
		app.setSuperclass(new OJPathName("org.opaeum.runtime.rwt.AbstractOpaeumApplication"));
		OJConstructor con = new OJConstructor();
		con.addParam("bundle", new OJPathName("org.osgi.framework.Bundle"));
		con.setDelegateConstructor("super(bundle)");
		app.addToConstructors(con);
		OJAnnotatedField instance = new OJAnnotatedField("INSTANCE", app.getPathName());
		app.addToFields(instance);
		instance.setVisibility(OJVisibilityKind.PUBLIC);
		instance.setStatic(true);
		OJPathName entrypointType = new OJPathName("Class");
		entrypointType.addToElementTypes(entryPoint.getPathName());
		OJPathName env = ojUtil.utilClass(e, "Environment");
		OJAnnotatedOperation getEnvironment= new OJAnnotatedOperation("getEnvironment", env);
		app.addToOperations(getEnvironment);
		getEnvironment.initializeResultVariable(env.getLast()+ ".INSTANCE");
		OJAnnotatedOperation getEntryPoinType = new OJAnnotatedOperation("getEntryPointType", entrypointType);
		app.addToOperations(getEntryPoinType);
		getEntryPoinType.initializeResultVariable(entryPoint.getName()+ ".class");
		OJAnnotatedOperation getRootBusinessCollaboration=new OJAnnotatedOperation("getRootBusinessCollaboration",new OJPathName("org.opaeum.runtime.organization.IBusinessCollaborationBase"));
		getRootBusinessCollaboration.initializeResultVariable("null");
		app.addToOperations(getRootBusinessCollaboration);
		OJAnnotatedOperation createRootBusinessCollaboration=new OJAnnotatedOperation("createRootBusinessCollaboration",new OJPathName("org.opaeum.runtime.organization.IBusinessCollaborationBase"));
		createRootBusinessCollaboration.initializeResultVariable("null");
		app.addToOperations(createRootBusinessCollaboration);
		OJAnnotatedOperation newBusinessCollaboration=new OJAnnotatedOperation("newBusinessCollaboration",new OJPathName("org.opaeum.runtime.organization.IBusinessCollaborationBase"));
		newBusinessCollaboration.initializeResultVariable("null");
		newBusinessCollaboration.addParam("bn", new OJPathName("org.opaeum.runtime.organization.IBusinessNetwork"));
		app.addToOperations(newBusinessCollaboration);
		OJAnnotatedOperation newPersonNode=new OJAnnotatedOperation("newPersonNode",new OJPathName("org.opaeum.runtime.organization.IPersonNode"));
		newPersonNode.initializeResultVariable("null");
		newPersonNode.addParam("bn", new OJPathName("org.opaeum.runtime.organization.IBusinessNetwork"));
		newPersonNode.addParam("email", new OJPathName("String"));
		app.addToOperations(newPersonNode);
		OJAnnotatedOperation newBusinessNetwork=new OJAnnotatedOperation("newBusinessNetwork",new OJPathName("org.opaeum.runtime.organization.IBusinessNetwork"));
		newBusinessNetwork.initializeResultVariable("null");
		app.addToOperations(newBusinessNetwork);
		return app;
	}

	public OJAnnotatedClass createEntryPoint(EmfWorkspace e,OJPackage pk){
		OJPathName entryPointPath = ojUtil.utilClass(e, "EntryPoint");
		OJAnnotatedClass entryPoint= new OJAnnotatedClass(entryPointPath.getLast());
		pk.addToClasses(entryPoint);
		entryPoint.getDefaultConstructor().setDelegateConstructor("super(" + ojUtil.utilClass(e, "Application")+".INSTANCE)");
		createTextPath(entryPoint, JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
		entryPoint.setSuperclass(new OJPathName("org.opaeum.runtime.rwt.AbstractOpaeumEntryPoint"));
		return entryPoint;
	}
}
