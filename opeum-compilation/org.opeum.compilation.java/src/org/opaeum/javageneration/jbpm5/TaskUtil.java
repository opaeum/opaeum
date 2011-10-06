package org.opeum.javageneration.jbpm5;

import java.util.Collection;

import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJForStatement;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opeum.metamodel.bpm.INakedDeadline;
import org.opeum.metamodel.bpm.INakedResponsibilityDefinition;
import org.opeum.metamodel.core.INakedValueSpecification;
import org.opeum.metamodel.name.PluralNameWrapper;
import org.opeum.runtime.domain.TaskDelegation;

public class TaskUtil{
	private static final OJPathName BUSINESS_ROLE = new OJPathName("org.opeum.runtime.bpm.BusinessRole");
	public static void implementAssignmentsAndDeadlines(OJAnnotatedOperation operation,OJBlock block,INakedResponsibilityDefinition td,String taskName){
		operation.getOwner().addToImports(new OJPathName("org.opeum.runtime.bpm.TaskParticipationKind"));
		operation.getOwner().addToImports(new OJPathName("org.opeum.runtime.bpm.RequestParticipationKind"));
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
