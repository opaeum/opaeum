package org.opaeum.javageneration.jbpm5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IModelElement;

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
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.ProcessIdentifier;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	ProcessIdentifier.class
},after = {})
public class Jbpm5EnvironmentBuilder extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	public static class ProcessCollector extends VisitorAdapter<INakedElement,INakedModel>{
		@Override
		protected int getThreadPoolSize(){
			return 1;
		}
		private Collection<INakedBehavior> processes = new ArrayList<INakedBehavior>();
		@VisitBefore(matchSubclasses = true)
		public void visitBehavior(INakedBehavior b){
			if(b.isProcess()){
				this.processes.add(b);
			}
		}
		@Override
		public Collection<? extends INakedElement> getChildren(INakedElement parent){
			return parent.getOwnedElements();
		}
		public Collection<INakedBehavior> getProcesses(){
			return this.processes;
		}
	}
	public Jbpm5EnvironmentBuilder(){
	}
	@VisitBefore(matchSubclasses = true)
	public void visitWorkspace(INakedModelWorkspace workspace){
		if(transformationContext.isIntegrationPhase()){
			Collection<INakedRootObject> primaryRootObjects2 = workspace.getPrimaryRootObjects();
			Collection<INakedBehavior> processes = new HashSet<INakedBehavior>();
			OJPathName pn = Jbpm5Util.jbpmKnowledgeBase(workspace);
			createKnowledgeBase(primaryRootObjects2, processes, pn, JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
			OJAnnotatedPackageInfo pkgInfo = findOrCreatePackageInfo(pn.getHead(),JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC);
			JpaUtil.addNamedQueries(pkgInfo, "ProcessInstancesWaitingForEvent",
					"select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)");
		}
	}
	protected void createKnowledgeBase(Collection<INakedRootObject> bpmModels,Collection<INakedBehavior> processes,OJPathName pn,JavaSourceFolderIdentifier i){
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
		for(INakedRootObject ro:bpmModels){
			if(ro instanceof INakedModel){
				getProcessLocations.getBody().addToStatements("result.addAll(" + Jbpm5Util.jbpmKnowledgeBase(ro) + ".INSTANCE.getProcessLocations())");
			}
		}
		for(INakedBehavior p:processes){
			getProcessLocations.getBody().addToStatements("result.add(\"" + p.getMappingInfo().getJavaPath() + ".rf\")");
		}
		getProcessLocations.getBody().addToStatements("return result");
		OJAnnotatedField instance = new OJAnnotatedField("INSTANCE", pn);
		instance.setStatic(true);
		instance.setVisibility(OJVisibilityKind.PUBLIC);
		instance.setFinal(true);
		instance.setInitExp("new " + pn.getLast() + "()");
		knowledgeBase.addToFields(instance);
		//TODO introduce constant
		knowledgeBase.setSuperclass(new OJPathName("org.opeum.runtime.jbpm.AbstractJbpmKnowledgeBase"));
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}

	@VisitBefore(matchSubclasses = true)
	public void visitModel(INakedModel model){
		if(!transformationContext.isIntegrationPhase()){
			ProcessCollector processCollector = new ProcessCollector();
			processCollector.visitRecursively(model);
			Collection<INakedRootObject> dependencies = new HashSet<INakedRootObject>();
			for(IImportedElement ie:model.getImports()){
				IModelElement element = ie.getElement();
				if(element instanceof INakedModel && isBpmModel((INakedModel) element)){
					dependencies.add((INakedModel) element);
				}
			}
			createKnowledgeBase(dependencies, processCollector.processes, Jbpm5Util.jbpmKnowledgeBase(model), JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			if(!config.getSourceFolderStrategy().isSingleProjectStrategy()){
				// Could lead to duplicate declarations of this query in the same integrated jar so we have to make sure we don't
				// accidentally install this package in JPA
				JpaUtil.addNamedQueries(findOrCreatePackageInfo(OJUtil.utilPackagePath(model),JavaSourceFolderIdentifier.DOMAIN_GEN_SRC), "ProcessInstancesWaitingForEvent",
						"select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)");
			}
		}
	}
	protected boolean isBpmModel(INakedModel element){
		if(workspace.getPrimaryRootObjects().contains(element)){
			return true;
		}else{
			Set<String> libs = new HashSet<String>();
			libs.add("OpaeumSimpleTypes".toLowerCase());
			libs.add("UMLPrimitiveTypes".toLowerCase());
			libs.add("JavaPrimitiveTypes".toLowerCase());
			libs.add("OpaeumSimpleTypes".toLowerCase());
			return !libs.contains(element.getName().toLowerCase());
		}
	}
}
