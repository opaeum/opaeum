package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;

import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.name.PluralNameWrapper;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.runtime.domain.TaskDelegation;

public class TaskUtil{
	private static final OJPathName BUSINESS_ROLE = new OJPathName("org.nakeduml.bpm.BusinessRole");
	public static void implementAssignmentsAndDeadlines(OJAnnotatedOperation operation,OJBlock block,INakedResponsibilityDefinition td,String taskName){
		operation.getOwner().addToImports(new OJPathName("org.nakeduml.bpm.TaskParticipationKind"));
		operation.getOwner().addToImports(new OJPathName("org.nakeduml.bpm.RequestParticipationKind"));
		if(td.getPotentialOwners() != null){
			PluralNameWrapper name = new PluralNameWrapper("potentialOwners", "potentialOwner");
			implementTaskRequestAssignment(operation, block, td, taskName, td.getPotentialOwners(), name,"POTENTIALOWNER");
		}
		if(td.getPotentialBusinessAdministrators() != null){
			PluralNameWrapper name = new PluralNameWrapper("potentialBusinessAdministrators", "potentialBusinessAdministrator");
			implementRequestAssignment(operation, block, td, taskName, td.getPotentialBusinessAdministrators(), name,"BUSINESSOWNER");
		}
		if(td.getPotentialBusinessAdministrators() != null){
			PluralNameWrapper name = new PluralNameWrapper("potentialStakeholders", "potentialStakeholders");
			implementRequestAssignment(operation, block, td, taskName, td.getPotentialStakeholders(), name,"STAKEHOLDER");
		}
		operation.getOwner().addToImports(TaskDelegation.class.getName());
		if(td.getDelegation() == null){
			block.addToStatements(taskName + ".getTaskRequest().setDelegation(TaskDelegation.ANYBODY)");
		}else{
			block.addToStatements(taskName + ".getTaskRequest().setDelegation(TaskDelegation." + td.getDelegation().name() + ")");
		}
		Collection<INakedDeadline> deadlines = td.getDeadlines();
		for(INakedDeadline d:deadlines){
			EventUtil.implementDeadlineRequest(operation, block, d, taskName);
		}
	}
	private static void implementTaskRequestAssignment(OJAnnotatedOperation operation,OJBlock block,INakedResponsibilityDefinition td,String taskName,
			INakedValueSpecification potentialOwners,PluralNameWrapper name, String kind){
		String expr = ValueSpecificationUtil.expressValue(operation, potentialOwners, td.getExpressionContext(), null);
		OJIfStatement ifEmpty = new OJIfStatement(taskName + ".getTaskRequest().get" + name.getCapped() + "().isEmpty()");
		block.addToStatements(ifEmpty);
		if(potentialOwners.getOclValue().getExpression().getExpressionType() instanceof StdlibCollectionType){
			operation.getOwner().addToImports(BUSINESS_ROLE);
			OJForStatement forEach = new OJForStatement("participant", BUSINESS_ROLE, expr);
			ifEmpty.getThenPart().addToStatements(forEach);
			forEach.getBody().addToStatements(taskName + ".getTaskRequest().addTaskRequestParticipant(participant,TaskParticipationKind."+ kind+")");
		}else{
			ifEmpty.getThenPart().addToStatements(taskName + ".getTaskRequest().addTaskRequestParticipant("+expr+",TaskParticipationKind."+ kind+")");
		}
	}
	private static void implementRequestAssignment(OJAnnotatedOperation operation,OJBlock block,INakedResponsibilityDefinition td,String taskName,
			INakedValueSpecification potentialOwners,PluralNameWrapper name, String kind){
		String expr = ValueSpecificationUtil.expressValue(operation, potentialOwners, td.getExpressionContext(), null);
		OJIfStatement ifEmpty = new OJIfStatement(taskName + ".getTaskRequest().get" + name.getCapped() + "().isEmpty()");
		block.addToStatements(ifEmpty);
		if(potentialOwners.getOclValue().getExpression().getExpressionType() instanceof StdlibCollectionType){
			operation.getOwner().addToImports(BUSINESS_ROLE);
			OJForStatement forEach = new OJForStatement("participant", BUSINESS_ROLE, expr);
			ifEmpty.getThenPart().addToStatements(forEach);
			forEach.getBody().addToStatements(taskName + ".getTaskRequest().addRequestParticipant(participant,RequestParticipationKind."+ kind+")");
		}else{
			ifEmpty.getThenPart().addToStatements(taskName + ".getTaskRequest().addRequestParticipant("+expr+",RequestParticipationKind."+ kind+")");
		}
	}
}
