package org.opaeum.eclipse;

import java.util.Collection;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.metamodel.workspace.IPropertyEmulation;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.ocl.uml.ResponsibilityDefinition;
import org.opaeum.runtime.domain.TaskDelegation;

public final class ResponsibilityDefinitionImpl implements ResponsibilityDefinition{
	private final Element node;
	private final Stereotype stereotype;
	IPropertyEmulation emulation;
	public ResponsibilityDefinitionImpl(IPropertyEmulation emulation, Element node,Stereotype stereotype){
		this.node = node;
		this.emulation=emulation;
		this.stereotype = stereotype;
	}
	@Override
	public OpaqueExpression getPotentialStakeholders(){
		return (OpaqueExpression) node.getValue(stereotype, "potentialStakeholders");
	}
	@Override
	public OpaqueExpression getPotentialOwners(){
		return (OpaqueExpression) node.getValue(stereotype, "potentialOwners");
	}
	@Override
	public OpaqueExpression getPotentialBusinessAdministrators(){
		return (OpaqueExpression) node.getValue(stereotype, "potentialBusinessAdministrators");
	}
	@Override
	public Classifier getExpressionContext(){
		if(node instanceof Operation){
			return (Classifier) node.getOwner();
		}else if(node instanceof OpaqueAction){
			return emulation.getMessageStructure((OpaqueAction) node);
		}
		return null;
	}
	public Collection<TimeEvent> getDeadlines(){
		return (Collection<TimeEvent>) node.getValue(stereotype, "deadlines");
	}
	@Override
	public TaskDelegation getDelegation(){
		Object value = node.getValue(stereotype, "taskDelegation");
		if(value instanceof EnumerationLiteral){
			return TaskDelegation.valueOf(((EnumerationLiteral) value).getName().toUpperCase());
		}
		return null;
	}
	@Override
	public Element getDefiningElement(){
		return node;
	}
}