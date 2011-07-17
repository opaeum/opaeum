package net.sf.nakeduml.javageneration.jbpm5;

import java.util.Collection;

import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.metamodel.bpm.INakedDeadline;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibilityDefinition;
import net.sf.nakeduml.metamodel.bpm.TaskDelegation;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.name.PluralNameWrapper;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class TaskUtil{
	private static final OJPathName BUSINESS_ROLE = new OJPathName("org.nakeduml.bpm.BusinessRole");
	public static void implementAssignmentsAndDeadlines(OJAnnotatedOperation operation,OJBlock block,INakedResponsibilityDefinition td,String taskName){
		if(td.getPotentialOwners() != null){
			PluralNameWrapper name = new PluralNameWrapper("potentialOwners", "potentialOwner");
			implementAssignment(operation, block, td, taskName, td.getPotentialOwners(), name);
		}
		if(td.getPotentialBusinessAdministrators() != null){
			PluralNameWrapper name = new PluralNameWrapper("potentialBusinessAdministrators", "potentialBusinessAdministrator");
			implementAssignment(operation, block, td, taskName, td.getPotentialBusinessAdministrators(), name);
		}
		if(td.getPotentialBusinessAdministrators() != null){
			PluralNameWrapper name = new PluralNameWrapper("potentialStakeholders", "potentialStakeholders");
			implementAssignment(operation, block, td, taskName, td.getPotentialStakeholders(), name);
		}
		operation.getOwner().addToImports(TaskDelegation.class.getName());
		if(td.getDelegation() == null){
			block.addToStatements(taskName + ".getTaskInstance().setDelegation(TaskDelegation.ANYBODY)");
		}else{
			block.addToStatements(taskName + ".getTaskInstance().setDelegation(TaskDelegation." + td.getDelegation().name() + ")");
		}
		Collection<INakedDeadline> deadlines = td.getDeadlines();
		for(INakedDeadline d:deadlines){
			EventUtil.implementDeadlineRequest(operation, block, d, taskName);
		}
	}
	private static void implementAssignment(OJAnnotatedOperation operation,OJBlock block,INakedResponsibilityDefinition td,String taskName,
			INakedValueSpecification potentialOwners,PluralNameWrapper name){
		String expr = ValueSpecificationUtil.expressValue(operation, potentialOwners, td.getExpressionContext(), null);
		OJIfStatement ifEmpty = new OJIfStatement(taskName + ".getTaskInstance().get" + name.getAsIs() + "().isEmpty()");
		block.addToStatements(ifEmpty);
		if(potentialOwners.getOclValue().getExpression().getExpressionType() instanceof StdlibCollectionType){
			OJForStatement forEach = new OJForStatement("participant", BUSINESS_ROLE, expr);
			ifEmpty.getThenPart().addToStatements(forEach);
			OJPathName assignment = new OJPathName("org.nakeduml.runtime.bpm.Assignment");
			OJAnnotatedField z = new OJAnnotatedField(name.getSingular().getAsIs(), assignment);
			forEach.getBody().addToLocals(z);
			z.setInitExp("new " + assignment.getLast() + "()");
			forEach.getBody().addToStatements(name.getSingular().getAsIs() + ".addTo" + name.getCapped() + "(participant)");
			forEach.getBody().addToStatements(taskName + ".getTaskInstance().addTo" + name.getCapped() + "(" + name.getSingular().getAsIs() + ")");
		}else{
			OJPathName assignment = new OJPathName("org.nakeduml.runtime.bpm.Assignment");
			OJAnnotatedField z = new OJAnnotatedField(name.getSingular().getAsIs(), assignment);
			ifEmpty.getThenPart().addToLocals(z);
			z.setInitExp("new " + assignment.getLast() + "()");
			ifEmpty.getThenPart().addToStatements(name.getSingular().getAsIs() + ".addTo" + name.getCapped() + "(" + expr + ")");
			ifEmpty.getThenPart().addToStatements(taskName + ".getTaskInstance().addTo" + name.getCapped() + "(" + name.getSingular().getAsIs() + ")");
		}
	}
}
