package net.sf.nakeduml.emf.extraction;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.bpm.internal.NakedResponsibilityImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedReceptionImpl;
import net.sf.nakeduml.metamodel.components.internal.NakedPortImpl;
import net.sf.nakeduml.metamodel.core.INakedAssociation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.NakedConstraintImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedOperationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedParameterImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPropertyImpl;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.validation.CoreValidationRule;
import nl.klasse.octopus.model.OclUsageType;

import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.ValueSpecification;
import org.nakeduml.eclipse.EmfParameterUtil;

/**
 * Builds operations, properties,parameter and associations. Only builds associations if they are supported by NakedUml and Octopus
 */
@StepDependency(phase = EmfExtractionPhase.class,requires = GeneralizationExtractor.class,after = GeneralizationExtractor.class)
public class FeatureExtractor extends AbstractExtractorFromEmf{
	@VisitBefore(matchSubclasses = true)
	public void visitPort(Port p,NakedPortImpl np){
		populateMultiplicityAndBaseType(p, p.getType(), np);
		populateProperty(np, p);
	}
	@Override
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		if(e instanceof Port){
			return new NakedPortImpl();
		}else if(e instanceof Property){
			Property p = (Property) e;
			if(p.getOwner() instanceof Property || p.getAssociation() instanceof Extension){
				return null;
			}else{
				return new NakedPropertyImpl();
			}
		}else if(e instanceof Operation){
			Stereotype responsibility = StereotypesHelper.getStereotype(e, StereotypeNames.RESPONSIBILITY);
			if(responsibility != null){
				return new NakedResponsibilityImpl();
			}else{
				return new NakedOperationImpl();
			}
		}else{
			return super.createElementFor(e, peerClass);
		}
	}
	@VisitBefore(matchSubclasses = false,match = {
			Property.class,ExtensionEnd.class
	})
	public void visitProperty(Property p,NakedPropertyImpl np){
		boolean navigable = p.isNavigable() || p.isComposite()||p.getAssociation() == null || p.getAssociation().getMemberEnds().size() < 2;
		if(p.getOtherEnd() != null){
			Property opposite = p.getOtherEnd();
			if(opposite.isComposite() && opposite.getType() instanceof org.eclipse.uml2.uml.Class){
				// force bidirectionality for composition between two classes
				navigable = true;
			}
			if(!isAllowedAssociationEnd(opposite.getType(), p.getType())){
				navigable = true;
			}
			INakedClassifier owner =null;
			np.getOwner().removeOwnedElement(np);
			if(navigable){
				// The classifier should be the owner of navigable ends
				owner=(INakedClassifier) getNakedPeer(opposite.getType());
			}else{
				// The association should be the owner of
				// non-navigable ends
				owner=(INakedClassifier) getNakedPeer(p.getAssociation());
			}
			np.setOwnerElement(owner);
			owner.addOwnedElement(np);
			np.setAssociation((INakedAssociation) getNakedPeer(p.getAssociation()));
			INakedAssociation a = (INakedAssociation) getNakedPeer(p.getAssociation());
			int index = p.getAssociation().getMemberEnds().indexOf(p);
			a.setEnd(index, np);
		}
		np.setNavigable(navigable);
		populateMultiplicityAndBaseType(p, p.getType(), np);
		np.setComposite(p.isComposite());
		populateProperty(np, p);
	}
	private void populateProperty(NakedPropertyImpl np,Property p){
		np.setReadOnly(p.isReadOnly());
		np.setDerived(p.isDerived());
		np.setDerivedUnion(p.isDerivedUnion());
		np.setIsOrdered(p.isOrdered());
		np.setIsUnique(p.isUnique());
		setOwnedAttributeIndexIfNecessary(p, np);
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
	public void visitReception(Reception emfRec,NakedReceptionImpl nakedRec){
		nakedRec.setSignal((INakedSignal) getNakedPeer(emfRec.getSignal()));
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(Operation emfOper,NakedOperationImpl nakedOper){
		Stereotype responsibility = StereotypesHelper.getStereotype(emfOper, StereotypeNames.RESPONSIBILITY);
		if(responsibility != null){
			initializeDeadlines(responsibility, emfOper);
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
				constraint.setSpecification(getValueSpecification(constraint, body, OclUsageType.BODY));
				nakedOper.setBodyCondition(constraint);
			}else{
				getErrorMap().putError(nakedOper, CoreValidationRule.OCL, "Operation has a bodyCondition, but no return parameter");
			}
		}
		addConstraints(nakedOper, preconditions, postconditions);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitParameter(Parameter emfParameter,NakedParameterImpl nakedParameter){
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
		populateMultiplicityAndBaseType(emfParameter, emfParameter.getType(), nakedParameter);
	}
}
