package org.opaeum.javageneration.jbpm5;

import java.util.Collection;

import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.name.PluralNameWrapper;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.ocl.uml.OclContext;
import org.opaeum.ocl.uml.ResponsibilityDefinition;
import org.opaeum.runtime.domain.TaskDelegation;

public class TaskUtil{
	OpaeumLibrary library;
	private ValueSpecificationUtil valueSpecificationUtil;
	private EventUtil eventUtil;
	public TaskUtil(OJUtil ojUtil){
		super();
		this.library = ojUtil.getLibrary();
		this.valueSpecificationUtil = new ValueSpecificationUtil(ojUtil);
		this.eventUtil=new EventUtil(ojUtil);
	}
	private static final OJPathName BUSINESS_ROLE = new OJPathName("org.opaeum.runtime.bpm.BusinessRole");
	public void implementAssignmentsAndDeadlines(OJAnnotatedOperation operation,OJBlock block,ResponsibilityDefinition td,String taskName){
		operation.getOwner().addToImports(new OJPathName("org.opaeum.runtime.bpm.TaskParticipationKind"));
		operation.getOwner().addToImports(new OJPathName("org.opaeum.runtime.bpm.RequestParticipationKind"));
		if(td.getPotentialOwners() != null){
			PluralNameWrapper name = new PluralNameWrapper("potentialOwners", "potentialOwner");
			implementTaskRequestAssignment(operation, block, td, taskName, td.getPotentialOwners(), name, "POTENTIALOWNER");
		}
		if(td.getPotentialBusinessAdministrators() != null){
			PluralNameWrapper name = new PluralNameWrapper("potentialBusinessAdministrators", "potentialBusinessAdministrator");
			implementRequestAssignment(operation, block, td, taskName, td.getPotentialBusinessAdministrators(), name, "BUSINESSOWNER");
		}
		if(td.getPotentialBusinessAdministrators() != null){
			PluralNameWrapper name = new PluralNameWrapper("potentialStakeholders", "potentialStakeholders");
			implementRequestAssignment(operation, block, td, taskName, td.getPotentialStakeholders(), name, "STAKEHOLDER");
		}
		operation.getOwner().addToImports(TaskDelegation.class.getName());
		if(td.getDelegation() == null){
			block.addToStatements(taskName + ".getTaskRequest().setDelegation(TaskDelegation.ANYBODY)");
		}else{
			block.addToStatements(taskName + ".getTaskRequest().setDelegation(TaskDelegation." + td.getDelegation().name() + ")");
		}
		Collection<TimeEvent> deadlines = td.getDeadlines();
		for(TimeEvent d:deadlines){
			eventUtil.implementDeadlineRequest(operation, block, d, taskName);
		}
	}
	private void implementTaskRequestAssignment(OJAnnotatedOperation operation,OJBlock block,ResponsibilityDefinition td,String taskName,
			OpaqueExpression potentialOwners,PluralNameWrapper name,String kind){
		OclContext oclExpressionContext = library.getOclExpressionContext(potentialOwners);
		if(!oclExpressionContext.hasErrors()){
			String expr = valueSpecificationUtil.expressOcl(oclExpressionContext, operation, null);
			OJIfStatement ifEmpty = new OJIfStatement(taskName + ".getTaskRequest().get" + name.getCapped() + "().isEmpty()");
			block.addToStatements(ifEmpty);
			if(oclExpressionContext.getExpression().getType() instanceof CollectionType){
				operation.getOwner().addToImports(BUSINESS_ROLE);
				OJForStatement forEach = new OJForStatement("participant", BUSINESS_ROLE, expr);
				ifEmpty.getThenPart().addToStatements(forEach);
				forEach.getBody().addToStatements(
						taskName + ".getTaskRequest().addTaskRequestParticipant(participant,TaskParticipationKind." + kind + ")");
			}else{
				ifEmpty.getThenPart().addToStatements(
						taskName + ".getTaskRequest().addTaskRequestParticipant(" + expr + ",TaskParticipationKind." + kind + ")");
			}
		}
	}
	private void implementRequestAssignment(OJAnnotatedOperation operation,OJBlock block,ResponsibilityDefinition td,String taskName,
			OpaqueExpression potentialOwners,PluralNameWrapper name,String kind){
		OclContext oclExpressionContext = library.getOclExpressionContext(potentialOwners);
		if(!oclExpressionContext.hasErrors()){
			String expr = valueSpecificationUtil.expressOcl(oclExpressionContext,operation, null);
			OJIfStatement ifEmpty = new OJIfStatement(taskName + ".getTaskRequest().get" + name.getCapped() + "().isEmpty()");
			block.addToStatements(ifEmpty);
			if(oclExpressionContext.getExpression().getType() instanceof CollectionType){
				operation.getOwner().addToImports(BUSINESS_ROLE);
				OJForStatement forEach = new OJForStatement("participant", BUSINESS_ROLE, expr);
				ifEmpty.getThenPart().addToStatements(forEach);
				forEach.getBody().addToStatements(
						taskName + ".getTaskRequest().addRequestParticipant(participant,RequestParticipationKind." + kind + ")");
			}else{
				ifEmpty.getThenPart().addToStatements(
						taskName + ".getTaskRequest().addRequestParticipant(" + expr + ",RequestParticipationKind." + kind + ")");
			}
		}
	}
}
