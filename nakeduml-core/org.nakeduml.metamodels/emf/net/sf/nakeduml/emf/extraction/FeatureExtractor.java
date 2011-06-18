package net.sf.nakeduml.emf.extraction;

import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.bpm.INakedResponsibility;
import net.sf.nakeduml.metamodel.bpm.internal.NakedDeadlineImpl;
import net.sf.nakeduml.metamodel.bpm.internal.NakedResponsibilityImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedReception;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedReceptionImpl;
import net.sf.nakeduml.metamodel.components.internal.NakedConnectorEndImpl;
import net.sf.nakeduml.metamodel.components.internal.NakedConnectorImpl;
import net.sf.nakeduml.metamodel.components.internal.NakedPortImpl;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedConstraintImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedOperationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedParameterImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPropertyImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedTypedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.validation.CoreValidationRule;
import nl.klasse.octopus.model.OclUsageType;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;
import org.nakeduml.eclipse.EmfParameterUtil;

/**
 * Builds operations, properties,parameter and associations. Only builds associations if they are supported by NakedUml and Octopus
 */
@StepDependency(phase = EmfExtractionPhase.class,requires = GeneralizationExtractor.class,after = GeneralizationExtractor.class)
public class FeatureExtractor extends AbstractExtractorFromEmf{
	@VisitBefore(matchSubclasses = true)
	public void visitPort(Port p){
		NakedPortImpl np = new NakedPortImpl();
		initializeTypedElement(np, p, p.getType(), p.getOwner());
		populateProperty(np, p);
	}
	@VisitBefore(matchSubclasses = false,match = {
			Property.class,ExtensionEnd.class
	})
	public void visitProperty(Property p){
		// only create properties that have not been created yet
		if(this.workspace.getModelElement(getId(p)) == null){
			if(p.getOwner() instanceof Property){
				// System.out.println("Qualifier found: " + p.getQualifiedName());
			}else if(p.getAssociation() instanceof Extension){
				// TODO convert these to some enum property that
				// can map to target type in Java
			}else{
				NakedPropertyImpl np = null;
				if(p.getAssociation() == null || p.getAssociation().getMemberEnds().size() < 2){
					np = buildAttribute(p);
				}else{
					int otherIndex = p.getAssociation().getMemberEnds().indexOf(p);
					otherIndex = otherIndex == 1 ? 0 : 1;
					Property opposite = p.getAssociation().getMemberEnds().get(otherIndex);
					// Octopus doesn't like associations on enums, primitives or
					// dataTypes, or stereotypes
					// Some relationships should not be represented as associations,
					// but
					// rather as ordinary attributes.
					if(isAllowedAssociationEnd(opposite.getType(), p.getType())){
						// Is Other End Also allowed?
						np = buildAssociationEnd(p, opposite);
						INakedAssociation a = (INakedAssociation) getNakedPeer(p.getAssociation());
						int index = p.getAssociation().getMemberEnds().indexOf(p);
						a.setEnd(index, np);
					}else{
						np = buildAttribute(p);
					}
					np.setComposite(p.isComposite());
				}
				populateProperty(np, p);
			}
		}
	}
	private NakedPropertyImpl buildAttribute(Property thisEnd){
		// Represent this associationEnd as an Attribute in Octopus
		NakedPropertyImpl aew = new NakedPropertyImpl();
		initializeTypedElement(aew, thisEnd, thisEnd.getType(), thisEnd.getOwner());
		aew.setNavigable(true);
		return aew;
	}
	// Only call this if both ends of the association are allowed
	private NakedPropertyImpl buildAssociationEnd(Property assEnd,Property opposite){
		NakedPropertyImpl aew = new NakedPropertyImpl();
		if(assEnd.isNavigable()){
			// In UML2, the classifier should be the owner of navigable ends
			initializeTypedElement(aew, assEnd, assEnd.getType(), opposite.getType());
		}else{
			// In UML2, the association should be the owner of
			// non-navigable ends
			initializeTypedElement(aew, assEnd, assEnd.getType(), assEnd.getAssociation());
		}
		aew.setAssociation((INakedAssociation) getNakedPeer(assEnd.getAssociation()));
		aew.setNavigable(assEnd.isNavigable());
		return aew;
	}
	private void populateProperty(NakedPropertyImpl np,Property p){
		np.setReadOnly(p.isReadOnly());
		np.setDerived(p.isDerived());
		np.setDerivedUnion(p.isDerivedUnion());
		np.setIsOrdered(p.isOrdered());
		np.setIsUnique(p.isUnique());
		setOwnedAttributeIndexIfNecessary(p, np);
		for(Property sp:p.getSubsettedProperties()){
			np.getSubsettedProperties().add(getProperty(sp));
		}
		for(Property sp:p.getRedefinedProperties()){
			np.getRedefinedProperties().add(getProperty(sp));
		}
		if(p.getDefaultValue() != null){
			OclUsageType ut = p.isDerived() ? OclUsageType.DERIVE : OclUsageType.INIT;
			INakedValueSpecification vs = getValueSpecification(np, p.getDefaultValue(), ut);
			if(vs != null){
				np.setInitialValue(vs);
			}
		}
		// TODO look at implementing qualifiers as free attributes of the association
		String[] qualifierNames = new String[p.getQualifiers().size()];
		int i = 0;
		for(Property attr:p.getQualifiers()){
			qualifierNames[i++] = attr.getName();
		}
		np.setQualifierNames(qualifierNames);
	}
	private void setOwnedAttributeIndexIfNecessary(Property assEnd,INakedProperty aew){
		if(assEnd.isNavigable()){
			int indexOf = EmfParameterUtil.calculateOwnedAttributeIndex(assEnd);
			aew.setOwnedAttributeIndex(indexOf);
		}
	}
	private INakedProperty getProperty(Property p){
		INakedProperty np = (INakedProperty) getNakedPeer(p);
		if(np == null){
			visitProperty(p);
			np = (INakedProperty) getNakedPeer(p);
		}
		return np;
	}
	private boolean isAllowedAssociationEnd(Type owner,Type thisEndType){
		// Theoretically ends owned by primitives and enumerations
		// cannot navigable, and Octopus does not like such an
		// association, so let's pretend this end and the association do not exist
		// Associations TO stereotypes are from MetaModel elements and should
		// not be represented at all associations FROM primitives or enumerations does not make sense to
		// Octopus, and these will be modelled as attributes instead.
		return !(owner instanceof PrimitiveType || owner instanceof Enumeration || thisEndType instanceof Stereotype);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitReception(Reception emfRec){
		INakedReception nakedRec = new NakedReceptionImpl();
		initialize(nakedRec, emfRec, emfRec.getOwner());
		nakedRec.setSignal((INakedSignal) getNakedPeer(emfRec.getSignal()));
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(Operation emfOper){
		NakedOperationImpl nakedOper;
		Stereotype responsibility = StereotypesHelper.getStereotype(emfOper, StereotypeNames.RESPONSIBILITY);
		if(responsibility != null){
			nakedOper = new NakedResponsibilityImpl();
			initialize(nakedOper, emfOper, emfOper.getOwner());
			initializeDeadlines(responsibility, emfOper);
		}else{
			nakedOper = new NakedOperationImpl();
			initialize(nakedOper, emfOper, emfOper.getOwner());
		}
		nakedOper.setQuery(emfOper.isQuery());
		nakedOper.setStatic(emfOper.isStatic());
		List<Constraint> preconditions = emfOper.getPreconditions();
		List<Constraint> postconditions = emfOper.getPostconditions();
		if(emfOper.getMethods().size() > 0){
			for(Behavior b:emfOper.getMethods()){
				nakedOper.addMethod((INakedBehavior) getNakedPeer(b));
			}
		}
		ValueSpecification body = emfOper.getBodyCondition() != null ? emfOper.getBodyCondition().getSpecification() : null;
		if(body != null){
			if(emfOper.getReturnResult() != null){
				INakedConstraint constraint = new NakedConstraintImpl();
				initialize(constraint, emfOper.getBodyCondition(), emfOper);
				constraint.setSpecification(getValueSpecification(nakedOper, body, OclUsageType.BODY));
				nakedOper.setBodyCondition(constraint);
			}else{
				getErrorMap().putError(nakedOper, CoreValidationRule.OCL, "Operation has a bodyCondition, but no return parameter");
			}
		}
		addConstraints(nakedOper, preconditions, postconditions);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitParameter(Parameter emfParameter){
		NakedParameterImpl nakedParameter = new NakedParameterImpl();
		if(emfParameter.getDirection().equals(ParameterDirectionKind.IN_LITERAL)){
			nakedParameter.setArgumentIndex(EmfParameterUtil.calculateIndex(emfParameter, EmfParameterUtil.ARGUMENT));
			nakedParameter.setResultIndex(-1);
			nakedParameter.setExceptionIndex(-1);
			nakedParameter.setReturn(false);
			nakedParameter.setException(false);
			nakedParameter.setDirection(nl.klasse.octopus.model.ParameterDirectionKind.IN);
		}else if(emfParameter.getDirection().equals(ParameterDirectionKind.INOUT_LITERAL)){
			nakedParameter.setArgumentIndex(EmfParameterUtil.calculateIndex(emfParameter, EmfParameterUtil.ARGUMENT));
			nakedParameter.setResultIndex(EmfParameterUtil.calculateIndex(emfParameter, EmfParameterUtil.RESULT));
			nakedParameter.setExceptionIndex(-1);
			nakedParameter.setReturn(false);
			nakedParameter.setException(false);
			nakedParameter.setDirection(nl.klasse.octopus.model.ParameterDirectionKind.INOUT);
		}else if(emfParameter.getDirection().equals(ParameterDirectionKind.RETURN_LITERAL)){
			// Octopus does not have a RETURN literal
			nakedParameter.setReturn(true);
			nakedParameter.setArgumentIndex(-1);
			nakedParameter.setResultIndex(EmfParameterUtil.calculateIndex(emfParameter, EmfParameterUtil.RESULT));
			if(emfParameter.isException()){
				nakedParameter.setExceptionIndex(EmfParameterUtil.calculateIndex(emfParameter, EmfParameterUtil.EXCEPTION));
				nakedParameter.setException(true);
			}else{
				nakedParameter.setExceptionIndex(-1);
				nakedParameter.setException(false);
			}
			nakedParameter.setDirection(nl.klasse.octopus.model.ParameterDirectionKind.OUT);
		}else if(emfParameter.getDirection().equals(ParameterDirectionKind.OUT_LITERAL)){
			nakedParameter.setReturn(false);
			nakedParameter.setArgumentIndex(-1);
			nakedParameter.setResultIndex(EmfParameterUtil.calculateIndex(emfParameter, EmfParameterUtil.RESULT));
			if(emfParameter.isException()){
				nakedParameter.setExceptionIndex(EmfParameterUtil.calculateIndex(emfParameter, EmfParameterUtil.EXCEPTION));
				nakedParameter.setException(true);
			}else{
				nakedParameter.setExceptionIndex(-1);
				nakedParameter.setException(false);
			}
			nakedParameter.setDirection(nl.klasse.octopus.model.ParameterDirectionKind.OUT);
		}
		initializeTypedElement(nakedParameter, emfParameter, emfParameter.getType(), emfParameter.getOwner());
	}
	private void initializeTypedElement(NakedTypedElementImpl element,MultiplicityElement modelElement,Type type,Element nameSpace){
		initialize(element, modelElement, nameSpace);
		populateMultiplicityAndBaseType(modelElement, type, element);
	}
}
