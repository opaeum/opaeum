package net.sf.nakeduml.javageneration.jbpm5;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.nakeduml.feature.ISourceFolderIdentifier;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.IntegrationCodeGenerator;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.hibernate.HibernateUtil;
import net.sf.nakeduml.javageneration.persistence.JpaUtil;
import net.sf.nakeduml.linkage.ProcessIdentifier;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.jbpm.AbstractJbpmKnowledgeBase;
@StepDependency(phase = JavaTransformationPhase.class, requires = {ProcessIdentifier.class}, after = {})
public class Jbpm5EnvironmentBuilder extends AbstractJavaProducingVisitor implements IntegrationCodeGenerator{
	public static class ProcessCollector extends VisitorAdapter<INakedElement,INakedModel>{
		private Collection<INakedBehavior> processes = new ArrayList<INakedBehavior>();
		public ProcessCollector(Collection<? extends INakedElement> roots){
			for(INakedElement root:roots){
				if(root instanceof INakedModel){
					startVisiting((INakedModel) root);
				}
			}
		}
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
		if(isIntegrationPhase()){
			OJPathName pn = new OJPathName(config.getMavenGroupId() + ".util.jbpm.adaptor");
			createJbpmKnowledgeBase(pn, JavaSourceFolderIdentifier.INTEGRATED_ADAPTOR_GEN_SRC, visitModels(workspace.getOwnedElements()).getProcesses());
			JpaUtil.addNamedQueries(findOrCreatePackage(HibernateUtil.getHibernatePackage(true)), "ProcessInstancesWaitingForEvent",
					"select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)");
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitModel(INakedModel model){
		if(!isIntegrationPhase()){
			OJPathName adaptorUtilPathname = UtilityCreator.getUtilPathName().append("jbpm").append("adaptor");
			ProcessCollector visitor = visitModels(getModelInScope());
			createJbpmKnowledgeBase(adaptorUtilPathname, JavaSourceFolderIdentifier.ADAPTOR_GEN_TEST_SRC, visitor.getProcesses());
			OJPathName domainUtilPathname = UtilityCreator.getUtilPathName().append("jbpm").append("domain");
			createJbpmKnowledgeBase(domainUtilPathname, JavaSourceFolderIdentifier.DOMAIN_GEN_TEST_SRC, visitor.getProcesses());
			JpaUtil.addNamedQueries(findOrCreatePackage(HibernateUtil.getHibernatePackage(true)), "ProcessInstancesWaitingForEvent",
					"select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)");
			JpaUtil.addNamedQueries(findOrCreatePackage(HibernateUtil.getHibernatePackage(false)), "ProcessInstancesWaitingForEvent",
					"select processInstanceInfo.processInstanceId from ProcessInstanceInfo processInstanceInfo where :type in elements(processInstanceInfo.eventTypes)");
		}
	}
	private ProcessCollector visitModels(Collection<? extends INakedElement> roots){
		ProcessCollector visitor = new ProcessCollector(roots);
		return visitor;
	}
	private void createJbpmKnowledgeBase(OJPathName utilPathName,ISourceFolderIdentifier outputRootId,Collection<INakedBehavior> processes){
		OJAnnotatedClass jbpmKnowledgeBase = new OJAnnotatedClass("JbpmKnowledgeBase");
		OJPackage utilPack = findOrCreatePackage(utilPathName);
		utilPack.addToClasses(jbpmKnowledgeBase);
		super.createTextPath(jbpmKnowledgeBase, outputRootId);
		addCommonImports(jbpmKnowledgeBase);
		jbpmKnowledgeBase.setSuperclass(new OJPathName(AbstractJbpmKnowledgeBase.class.getName()));
		OJAnnotatedOperation prepareKnowledgeBase = new OJAnnotatedOperation("prepareKnowledgeBuilder");
		jbpmKnowledgeBase.addToOperations(prepareKnowledgeBase);
		prepareKnowledgeBase.addParam("kbuilder", new OJPathName("KnowledgeBuilder"));
		OJBlock prepareKnowledgeBaseBody;
		prepareKnowledgeBaseBody = prepareKnowledgeBase.getBody();
		for(INakedBehavior p:processes){
			prepareKnowledgeBaseBody.addToStatements("kbuilder.add(ResourceFactory.newClassPathResource(\"" + p.getMappingInfo().getJavaPath()
					+ ".rf\"), ResourceType.DRF)");
		}
		OJAnnotatedField instance = new OJAnnotatedField("mockInstance", jbpmKnowledgeBase.getPathName());
		jbpmKnowledgeBase.addToFields(instance);
		instance.setStatic(true);
	}
	private void addCommonImports(OJAnnotatedClass jbpmKnowledgeSession){
		jbpmKnowledgeSession.addToImports("org.drools.KnowledgeBase");
		jbpmKnowledgeSession.addToImports("org.drools.builder.KnowledgeBuilder");
		jbpmKnowledgeSession.addToImports("org.drools.io.ResourceFactory");
		jbpmKnowledgeSession.addToImports("org.drools.builder.ResourceType");
	}
}
