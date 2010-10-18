package net.sf.nakeduml.javageneration.jbpm5;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractTextProducingVisitor;
import net.sf.nakeduml.javageneration.CharArrayTextSource;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.statemachines.INakedStateMachine;

public class JpdlGenerator extends AbstractTextProducingVisitor{
	@VisitBefore
	public void generateOperationProcess(INakedOperation o){
		processTemplate(o, "/Operation/JbpmProcess.vsl", "${operation.mappingInfo.javaPath}.jpdl.xml", CharArrayTextSource.EJB_JAR_RESOURCE);
	}
	@VisitBefore
	public void generateStateMachine(INakedStateMachine sm) {
		OJAnnotatedClass ojClass=findJavaClass(sm);
		OJAnnotatedField field=new OJAnnotatedField();
		field.setStatic(true);
		field.setType(new OJPathName("org.jbpm.graph.def.ProcessDefinition"));
		field.setName("PROCESS_DEFINITION");
		ojClass.addToFields(field);
		OJAnnotatedOperation oper = new OJAnnotatedOperation();
		oper.setReturnType(field.getType());
		oper.setStatic(true);
		oper.setName("getProcessDefinition");
		OJIfStatement ifNull = new OJIfStatement("PROCESS_DEFINITION==null", "PROCESS_DEFINITION=ProcessDefinition.parseXmlResource(\"" +sm.getMappingInfo().getJavaPath()+".jpdl.xml\")");
		oper.getBody().addToStatements(ifNull);
		oper.getBody().addToStatements("return PROCESS_DEFINITION");
		ojClass.addToOperations(oper);
		processTemplate(sm, "/StateMachine/JbpmProcess.vsl", "${stateMachine.mappingInfo.javaPath}.jpdl.xml", CharArrayTextSource.EJB_JAR_RESOURCE);
	}
	@VisitBefore
	public void generateActivity(INakedActivity a){
		if(a.getActivityKind() != ActivityKind.SIMPLE_SYNCHRONOUS_METHOD){
			System.out.println("JpdlGenerator.generateActivity()");
			processTemplate(a, "/Activity/JbpmProcess.vsl", "${activity.mappingInfo.javaPath}.jpdl.xml", CharArrayTextSource.EJB_JAR_RESOURCE);
		}
	}
}
