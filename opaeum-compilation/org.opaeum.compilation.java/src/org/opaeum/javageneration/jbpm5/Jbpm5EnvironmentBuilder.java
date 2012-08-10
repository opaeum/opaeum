package org.opaeum.javageneration.jbpm5;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Package;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.emf.workspace.DefaultOpaeumComparator;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.feature.visit.VisitorAdapter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedPackageInfo;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.IntegrationCodeGenerator;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.persistence.JpaUtil;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class Jbpm5EnvironmentBuilder extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	public static final String JBPM_KNOWLEDGE_BASE = "JbpmKnowledgeBase";
	public static class ProcessCollector extends VisitorAdapter<Element,Model>{
		@Override
		protected int getThreadPoolSize(){
			return 1;
		}
		private SortedSet<Behavior> processes = new TreeSet<Behavior>(new DefaultOpaeumComparator());
		@VisitBefore(matchSubclasses = true)
		public void visitBehavior(Behavior b){
			if(EmfBehaviorUtil.isProcess( b)){
				this.processes.add(b);
			}
		}
		@Override
		public Collection<? extends Element> getChildren(Element parent){
			return parent.getOwnedElements();
		}
		public Collection<Behavior> getProcesses(){
			return this.processes;
		}
	}
	public Jbpm5EnvironmentBuilder(){
	}
	@VisitBefore(matchSubclasses = true)
	public void visitWorkspace(EmfWorkspace workspace){
		if(transformationContext.isIntegrationPhase()){
			SortedSet<Package> primaryRootObjects2 = new TreeSet<Package>(new DefaultOpaeumComparator());
			primaryRootObjects2.addAll(workspace.getPrimaryRootObjects());
			OJPathName pn = ojUtil.utilClass(workspace,JBPM_KNOWLEDGE_BASE);
			SortedSet<Behavior> emptySet = new TreeSet<Behavior>();
			createKnowledgeBase(primaryRootObjects2, emptySet, pn, JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
			OJAnnotatedPackageInfo pkgInfo = findOrCreatePackageInfo(pn.getHead(), JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
			JpaUtil
					.addNamedQueries(
							pkgInfo,
							"ProcessInstancesWaitingForEvent",
							"select processInstanceInfo.processInstanceId from org.jbpm.persistence.processinstance.ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)");
		}
	}
	protected void createKnowledgeBase(SortedSet<Package> bpmModels,SortedSet<Behavior> processes,OJPathName pn,JavaSourceFolderIdentifier i){
		OJAnnotatedClass knowledgeBase = new OJAnnotatedClass(pn.getLast());
		findOrCreatePackage(pn.getHead()).addToClasses(knowledgeBase);
		knowledgeBase.addToImports(new OJPathName("java.util.Set"));
		knowledgeBase.addToImports(new OJPathName("java.util.HashSet"));
		knowledgeBase.addToImports("org.drools.KnowledgeBase");
		knowledgeBase.addToImports("org.drools.builder.KnowledgeBuilder");
		knowledgeBase.addToImports("org.drools.io.ResourceFactory");
		knowledgeBase.addToImports("org.drools.builder.ResourceType");
		OJPathName setOfString = new OJPathName("java.util.Set");
		setOfString.addToElementTypes(new OJPathName("String"));
		OJAnnotatedOperation getProcessLocations = new OJAnnotatedOperation("getProcessLocations", setOfString);
		OJAnnotatedField result = new OJAnnotatedField("result", setOfString);
		result.setInitExp("new HashSet<String>()");
		getProcessLocations.getBody().addToLocals(result);
		knowledgeBase.addToOperations(getProcessLocations);
		createTextPath(knowledgeBase, i);
		for(Package ro:bpmModels){
			if(ro instanceof Model && (!EmfPackageUtil.isLibrary( (Model) ro) || EmfPackageUtil.isRegeneratingLibrary((Model) ro))){
				getProcessLocations.getBody().addToStatements(
						"result.addAll(" + ojUtil.utilClass(ro,JBPM_KNOWLEDGE_BASE) + ".INSTANCE.getProcessLocations())");
			}
		}
		for(Behavior p:processes){
			String javaString = ojUtil.classifierPathname(p).toJavaString();
			javaString=javaString.replaceAll("\\.", "\\/");
			getProcessLocations.getBody().addToStatements("result.add(\"" +javaString + ".rf\")");
		}
		getProcessLocations.getBody().addToStatements("return result");
		OJAnnotatedField instance = new OJAnnotatedField("INSTANCE", pn);
		instance.setStatic(true);
		instance.setVisibility(OJVisibilityKind.PUBLIC);
		instance.setFinal(true);
		instance.setInitExp("new " + pn.getLast() + "()");
		knowledgeBase.addToFields(instance);
		// TODO introduce constant
		knowledgeBase.setSuperclass(new OJPathName("org.opaeum.runtime.jbpm.AbstractJbpmKnowledgeBase"));
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
	@VisitBefore(matchSubclasses = true)
	public void visitModel(Model model){
		if(!transformationContext.isIntegrationPhase()){
			ProcessCollector processCollector = new ProcessCollector();
			processCollector.visitRecursively(model);
			SortedSet<Package> dependencies = new TreeSet<Package>(new DefaultOpaeumComparator());
			for(Package ie:model.getImportedPackages()){
				if(ie instanceof Model && isBpmModel((Model) ie)){
					dependencies.add((Model) ie);
				}
			}
			createKnowledgeBase(dependencies, processCollector.processes, ojUtil.utilClass(workspace,JBPM_KNOWLEDGE_BASE),
					JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			if(!config.getSourceFolderStrategy().isSingleProjectStrategy()){
				// Could lead to duplicate declarations of this query in the same integrated jar so we have to make sure we don't
				// accidentally install this package in JPA
				JpaUtil
						.addNamedQueries(
								findOrCreatePackageInfo(ojUtil.utilPackagePath(model), JavaSourceFolderIdentifier.DOMAIN_GEN_SRC),
								"ProcessInstancesWaitingForEvent",
								"select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)");
			}
		}
	}
	protected boolean isBpmModel(Model element){
		if(workspace.getPrimaryRootObjects().contains(element)){
			return true;
		}else{
			Set<String> libs = new HashSet<String>();
			libs.add("OpaeumSimpleTypes".toLowerCase());
			libs.add("UMLPrimitiveTypes".toLowerCase());
			libs.add("PrimitiveTypes".toLowerCase());
			libs.add("JavaPrimitiveTypes".toLowerCase());
			libs.add("OpaeumSimpleTypes".toLowerCase());
			return !libs.contains(element.getName().toLowerCase());
		}
	}
}
