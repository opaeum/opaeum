package org.opaeum.eclipse;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.metamodel.workspace.IPropertyEmulation;
import org.opaeum.ocl.uml.ResponsibilityDefinition;
import org.opaeum.runtime.domain.TaskDelegation;

public final class ResponsibilityDefinitionImpl implements ResponsibilityDefinition{
	private final Element node;
	private final Stereotype stereotype;
	IPropertyEmulation emulation;
	public ResponsibilityDefinitionImpl(IPropertyEmulation emulation,Element node,Stereotype stereotype){
		this.node = node;
		this.emulation = emulation;
		this.stereotype = stereotype;
	}
	@Override
	public OpaqueExpression getStakeholders(){
		if(stereotype != null){
			return (OpaqueExpression) node.getValue(stereotype, TagNames.STAKEHOLDERS);
		}else{
			return null;
		}
	}
	@Override
	public OpaqueExpression getPotentialOwners(){
		if(stereotype != null && stereotype.getDefinition().getEStructuralFeature(TagNames.POTENTIAL_OWNERS) != null){
			return (OpaqueExpression) node.getValue(stereotype, TagNames.POTENTIAL_OWNERS);
		}else{
			return null;
		}
	}
	@Override
	public OpaqueExpression getBusinessAdministrators(){
		if(stereotype != null && stereotype.getDefinition().getEStructuralFeature(TagNames.BUSINESS_ADMINISTRATORS) != null){
			return (OpaqueExpression) node.getValue(stereotype, TagNames.BUSINESS_ADMINISTRATORS);
		}else{
			return null;
		}
	}
	@Override
	public Classifier getExpressionContext(){
		if(node instanceof Operation){
			return (Classifier) node.getOwner();
		}else if(node instanceof OpaqueAction){
			return emulation.getMessageStructure((OpaqueAction) node);
		}
		if(node instanceof Behavior){
			return (Behavior) node;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public Collection<TimeEvent> getDeadlines(){
		if(stereotype != null && stereotype.getDefinition().getEStructuralFeature(TagNames.DEADLINES) != null){
			return (Collection<TimeEvent>) node.getValue(stereotype, TagNames.DEADLINES);
		}else{
			return Collections.emptySet();
		}
	}
	public Collection<Constraint> getConditionEscalations(){
		return getTimeEscalations(null);
	}
	public Collection<Constraint> getTimeEscalations(TimeEvent te){
		Collection<Constraint> result = new HashSet<Constraint>();
		Collection<Constraint> source = null;
		if(node instanceof Namespace){
			source = ((Namespace) node).getOwnedRules();
		}else if(node instanceof Action){
			source = ((Action) node).getLocalPostconditions();
		}else{
			source = Collections.emptySet();
		}
		for(Constraint constraint:source){
			if(StereotypesHelper.hasStereotype(constraint, StereotypeNames.ESCALATION)){
				Object d = constraint.getValue(StereotypesHelper.getStereotype(constraint, StereotypeNames.ESCALATION), TagNames.DEADLINE);
				if(d == te || (d == null && te == null)){
					result.add(constraint);
				}
			}
		}
		return result;
	}
	@Override
	public TaskDelegation getDelegation(){
		if(stereotype != null && stereotype.getDefinition().getEStructuralFeature(TagNames.TASK_DELEGATION) != null){
			Object value = node.getValue(stereotype, TagNames.TASK_DELEGATION);
			if(value instanceof EnumerationLiteral){
				return TaskDelegation.valueOf(((EnumerationLiteral) value).getName().toUpperCase());
			}
		}
		return null;
	}
	@Override
	public Element getDefiningElement(){
		return node;
	}
}