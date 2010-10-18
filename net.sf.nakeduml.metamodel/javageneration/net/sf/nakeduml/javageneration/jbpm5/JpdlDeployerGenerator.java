package net.sf.nakeduml.javageneration.jbpm5;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

public class JpdlDeployerGenerator extends AbstractJavaProducingVisitor{
	OJAnnotatedOperation main;
	@VisitBefore
	public void generateDeployer(INakedModel model){
		OJAnnotatedClass ojClass = new OJAnnotatedClass();
		ojClass.setName("JpdlDeployer");
		UtilityCreator.getUtilPack().addToClasses(ojClass);
		main = OJUtil.buildMain(ojClass);
		createTextPath(ojClass, JavaTextSource.TEST_SRC);
		ojClass.addToImports("org.hibernate.Session");
		ojClass.addToImports("org.hibernate.Transaction");
		ojClass.addToImports("org.jbpm.JbpmConfiguration");
		ojClass.addToImports("org.jbpm.JbpmContext");
		ojClass.addToImports("org.jbpm.graph.def.ProcessDefinition");
		main.getBody().addToStatements("JbpmContext ctx=JbpmConfiguration.getInstance().createJbpmContext()");
		main.getBody().addToStatements("Session session=(Session)HibernateConfigurator.getInstance().getEntityManager().getDelegate()");
		main.getBody().addToStatements("ctx.setSession(session)");
		main.getBody().addToStatements("Transaction tx=session.beginTransaction()");
	}
	@VisitAfter
	public void commit(INakedModel model){
		main.getBody().addToStatements("tx.commit()");
		main.getBody().addToStatements("session.flush()");
	}
	@VisitAfter
	public void visitStateMachine(INakedStateMachine sm){
		if(sm.isProcess()){
			addDeployment(sm);
		}
	}
	private void addDeployment(INakedElement element){
		String f = element.getMappingInfo().getJavaPath();
		main.getBody().addToStatements("ctx.deployProcessDefinition(ProcessDefinition.parseXmlResource(\"" + f + ".jpdl.xml\"))");
	}
	@VisitAfter
	public void visitActivity(INakedActivity a){
		if(a.isProcess()){
			addDeployment(a);
		}
	}
}
