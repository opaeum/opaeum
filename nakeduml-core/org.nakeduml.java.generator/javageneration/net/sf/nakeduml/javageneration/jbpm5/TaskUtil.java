package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;

import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.bpm.TaskDelegation;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.name.SingularNameWrapper;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.IUserInRole;

public class TaskUtil{
	public static void implementAssignmentsAndDeadlines(OJAnnotatedOperation operation,OJBlock block,INakedResponsibilityDefinition td,String taskName){
		if(td.getPotentialOwners() != null){
			SingularNameWrapper name = new SingularNameWrapper("potentialOwners", null);
			implementAssignment(operation, block, td, taskName, td.getPotentialOwners(), name);
		}
		if(td.getPotentialBusinessAdministrators() != null){
			SingularNameWrapper name = new SingularNameWrapper("potentialBusinessAdministrators", null);
			implementAssignment(operation, block, td, taskName, td.getPotentialBusinessAdministrators(), name);
		}
		if(td.getPotentialBusinessAdministrators() != null){
			SingularNameWrapper name = new SingularNameWrapper("potentialStakeholders", null);
			implementAssignment(operation, block, td, taskName, td.getPotentialStakeholders(), name);
		}
		operation.getOwner().addToImports(TaskDelegation.class.getName());
		block.addToStatements(taskName + ".getTaskInstance().setDelegation(TaskDelegation." + td.getDelegation().name() + ")");
		Collection<INakedDeadline> deadlines = td.getDeadlines();
		for(INakedDeadline d:deadlines){
			EventUtil.implementTimeEventRequest(operation, block, td.getContext(), d, taskName);
		}
	}

	private static void implementAssignment(OJAnnotatedOperation operation,OJBlock block,INakedResponsibilityDefinition td,String taskName,
			INakedValueSpecification potentialOwners,SingularNameWrapper name){
		String expr = ValueSpecificationUtil.expressValue(operation, potentialOwners, td.getContext(), null);
		//TODO for Responsibilities select if one and call correct trigger
		
		if(potentialOwners.getOclValue().getExpression().getExpressionType() instanceof StdlibCollectionType){
			OJForStatement forEach = new OJForStatement("participant", new OJPathName(IUserInRole.class.getName()), expr);
			block.addToStatements(forEach);
			OJPathName assignment = new OJPathName("org.nakeduml.runtime.bpm.Assignment");
			OJAnnotatedField z = new OJAnnotatedField("z", assignment);
			forEach.getBody().addToLocals(z);
			z.setInitExp("new " + assignment.getLast() + "()");
			forEach.getBody().addToStatements("z.addTo" + name.getCapped() + "(z");
		}else{
			block.addToStatements(taskName + ".getTaskInstance().addTo" + name.getCapped() + "(" + expr + ")");
		}
	}
}
