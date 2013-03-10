package org.opaeum.javageneration.bpm;

import java.util.Collection;

import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.feature.TransformationContext;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.name.PluralNameWrapper;
import org.opaeum.metamodel.workspace.IPropertyEmulation;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.ocl.uml.ResponsibilityDefinition;
import org.opaeum.runtime.domain.TaskDelegation;

public class TaskUtil{
	IPropertyEmulation library;
	private ValueSpecificationUtil valueSpecificationUtil;
	private EventUtil eventUtil;
	public TaskUtil(OJUtil ojUtil){
		super();
		this.library = ojUtil.getLibrary();
		this.valueSpecificationUtil = new ValueSpecificationUtil(ojUtil);
		this.eventUtil=new EventUtil(ojUtil);
	}
	private static final OJPathName BUSINESS_ROLE = new OJPathName("org.opaeum.runtime.bpm.BusinessRole");
	public void implementAssignmentsAndEventGeneration(OJAnnotatedOperation operation,OJBlock block,ResponsibilityDefinition td,String taskName){
		operation.getOwner().addToImports(new OJPathName("org.opaeum.runtime.bpm.request.TaskParticipationKind"));
		operation.getOwner().addToImports(new OJPathName("org.opaeum.runtime.bpm.request.RequestParticipationKind"));
		if(td.getPotentialOwners() != null){
			PluralNameWrapper name = new PluralNameWrapper("potentialOwners", "potentialOwner");
			implementTaskRequestAssignment(operation, block, td, taskName, td.getPotentialOwners(), name, "POTENTIALOWNER");
		}
		if(td.getBusinessAdministrators() != null){
			PluralNameWrapper name = new PluralNameWrapper("businessAdministrators", "businessAdministrator");
			implementRequestAssignment(operation, block, td, taskName, td.getBusinessAdministrators(), name, "BUSINESSOWNER");
		}
		if(td.getBusinessAdministrators() != null){
			PluralNameWrapper name = new PluralNameWrapper("stakeholders", "stakeholders");
			implementRequestAssignment(operation, block, td, taskName, td.getStakeholders(), name, "STAKEHOLDER");
		}
		operation.getOwner().addToImports(TaskDelegation.class.getName());
		if(td.getDelegation() == null){
			block.addToStatements("((TaskRequest)" + taskName + ".getRequest()).setDelegation(TaskDelegation.ANYBODY)");
		}else{
			block.addToStatements("((TaskRequest)" + taskName + ".getRequest()).setDelegation(TaskDelegation." + td.getDelegation().name() + ")");
		}
		Collection<TimeEvent> deadlines = td.getDeadlines();
		for(TimeEvent d:deadlines){
			eventUtil.implementDeadlineRequest(operation, block, d, taskName);
		}
		for(Constraint constraint:td.getConditionEscalations()){
			eventUtil.implementChangeEventRequest(operation, constraint,(OpaqueExpression) constraint.getSpecification());
		}
	}
	private void implementTaskRequestAssignment(OJAnnotatedOperation operation,OJBlock block,ResponsibilityDefinition td,String taskName,
			OpaqueExpression potentialOwners,PluralNameWrapper name,String kind){
		OpaqueExpressionContext oclExpressionContext = library.getOclExpressionContext(potentialOwners);
		if(!oclExpressionContext.hasErrors()){
			String expr = valueSpecificationUtil.expressOcl(oclExpressionContext, operation, null);
			OJIfStatement ifEmpty = new OJIfStatement("((TaskRequest)" + taskName + ".getRequest()).get" + name.getCapped() + "().isEmpty()");
			block.addToStatements(ifEmpty);
			if(oclExpressionContext.getExpression().getType() instanceof CollectionType){
				operation.getOwner().addToImports(BUSINESS_ROLE);
				OJForStatement forEach = new OJForStatement("participant", BUSINESS_ROLE, expr);
				ifEmpty.getThenPart().addToStatements(forEach);
				forEach.getBody().addToStatements(
						"((TaskRequest)" + taskName + ".getRequest()).addTaskRequestParticipant(participant,TaskParticipationKind." + kind + ")");
			}else{
				ifEmpty.getThenPart().addToStatements(
						"((TaskRequest)" + taskName + ".getRequest()).addTaskRequestParticipant(" + expr + ",TaskParticipationKind." + kind + ")");
			}
		}
	}
	private void implementRequestAssignment(OJAnnotatedOperation operation,OJBlock block,ResponsibilityDefinition td,String taskName,
			OpaqueExpression potentialOwners,PluralNameWrapper name,String kind){
		OpaqueExpressionContext oclExpressionContext = library.getOclExpressionContext(potentialOwners);
		if(!oclExpressionContext.hasErrors()){
			String expr = valueSpecificationUtil.expressOcl(oclExpressionContext,operation, null);
			OJIfStatement ifEmpty = new OJIfStatement("((TaskRequest)" + taskName + ".getRequest()).get" + name.getCapped() + "().isEmpty()");
			block.addToStatements(ifEmpty);
			if(oclExpressionContext.getExpression().getType() instanceof CollectionType){
				operation.getOwner().addToImports(BUSINESS_ROLE);
				OJForStatement forEach = new OJForStatement("participant", BUSINESS_ROLE, expr);
				ifEmpty.getThenPart().addToStatements(forEach);
				forEach.getBody().addToStatements(
						"((TaskRequest)" + taskName + ".getRequest()).addRequestParticipant(participant,RequestParticipationKind." + kind + ")");
			}else{
				ifEmpty.getThenPart().addToStatements(
						"((TaskRequest)" + taskName + ".getRequest()).addRequestParticipant(" + expr + ",RequestParticipationKind." + kind + ")");
			}
		}
	}
}
