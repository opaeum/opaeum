package org.opaeum.emf.extraction;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.ExtensionEnd;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Reception;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfParameterUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.bpm.internal.NakedResponsibilityImpl;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.commonbehaviors.internal.NakedReceptionImpl;
import org.opaeum.metamodel.components.internal.NakedPortImpl;
import org.opaeum.metamodel.core.INakedAssociation;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedElementImpl;
import org.opaeum.metamodel.core.internal.NakedOperationImpl;
import org.opaeum.metamodel.core.internal.NakedParameterImpl;
import org.opaeum.metamodel.core.internal.NakedPropertyImpl;
import org.opaeum.metamodel.core.internal.StereotypeNames;

/**
 * Builds operations, properties,parameter and associations. Only builds associations if they are supported by Opaeum and Octopus
 */
@StepDependency(phase = EmfExtractionPhase.class,requires = GeneralizationExtractor.class,after = GeneralizationExtractor.class)
public class FeatureExtractor extends AbstractExtractorFromEmf{
	public int getThreadPoolSize(){
		return 12;
	}
	@Override
	public void visitRecursively(Element o){
		super.visitRecursively(o);
	}
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
			if(p instanceof ExtensionEnd || p.getOtherEnd() instanceof ExtensionEnd || p.getOwner() instanceof Property
					|| p.getAssociation() instanceof Extension){
				return null;
			}else{
				if(p.getAssociation() != null){
					if(p.getAssociation().getMemberEnds().size() < 2){
						getErrorMap().putError(getId(e), EmfValidationRule.BROKEN_ASSOCIATION);
						// broken association a'la topcased
						return null;
					}else{
						for(Property property:p.getAssociation().getMemberEnds()){
							if(property.getType() == null){
								getErrorMap().putError(getId(e), EmfValidationRule.BROKEN_ASSOCIATION);
								// broken association a'la topcased
								return null;
							}
						}
					}
				}
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
	@VisitBefore(matchSubclasses = false,match = {Property.class,ExtensionEnd.class,Port.class})
	public void visitProperty(Property p,NakedPropertyImpl np){
		//NB!! the navigability logic needs to sit here as it affects the containment tree
		boolean navigable = p.isNavigable() || p.isComposite() || p.getAssociation() == null || p.getAssociation().getMemberEnds().size() < 2;
		if(p.getOtherEnd() != null && p.getOtherEnd().getType()!=null && p.getType()!=null){//Could be deleting
			Property opposite = p.getOtherEnd();
			if(opposite.isComposite()){
				if(opposite.getType().eClass().equals(UMLPackage.eINSTANCE.getDataType())){
					// Datatypes should have no knowledge of where they are contained from
					navigable = false;
				}else if(opposite.getType() instanceof org.eclipse.uml2.uml.Class){
					// force bidirectionality for composition between two classes
					navigable = true;
				}
			}
			if(p.getType().eClass().equals(UMLPackage.eINSTANCE.getDataType())  && !(p.isComposite())){
				//DataTypes can only be navigated to through composition
				navigable=false;
			}
			// if(!isAllowedAssociationEnd(opposite.getType(), p.getType())){
			// navigable = false;
			// }
			INakedClassifier owner = null;
			if(np.getOwnerElement() != null){
				// Hack!! this null check is necessary due to an intermittent bug where the owning association cannot be found
				np.getOwnerElement().removeOwnedElement(np, false);
			}
			if(navigable){
				// The classifier should be the owner of navigable ends
				owner = (INakedClassifier) getNakedPeer(opposite.getType());
			}else{
				// The association should be the owner of
				// non-navigable ends
				owner = (INakedClassifier) getNakedPeer(p.getAssociation());
			}
			np.setOwnerElement(owner);
			if(owner == null){
				System.err.println("The owner of property" + p.getQualifiedName() + " could not be found");
			}else{
				owner.addOwnedElement(np);
			}
			INakedAssociation nakedAss = (INakedAssociation) getNakedPeer(p.getAssociation());
			if(nakedAss != null){
				// sometimes during deletion the association is null
				np.setAssociation(nakedAss);
				if(nakedAss.isMarkedForDeletion()){
					np.markForDeletion();
					owner.removeOwnedElement(np, true);
				}
				addAffectedElement(nakedAss);
				int index = p.getAssociation().getMemberEnds().indexOf(p);
				nakedAss.setEnd(index, np);
			}
			EList<Type> endTypes = p.getAssociation().getEndTypes();
			for(Type type:endTypes){
				addAffectedElement(getNakedPeer(type));
			}
		}
		np.setNavigable(navigable);
		populateMultiplicityAndBaseType(p, p.getType(), np);
		np.setComposite(p.isComposite());
		populateProperty(np, p);
		addAffectedImplementingClassifiers(np);

	}
	protected void populateProperty(NakedPropertyImpl np,Property p){
		np.setReadOnly(p.isReadOnly());
		np.setStatic(p.isStatic());
		np.setDerived(p.isDerived());
		np.setDerivedUnion(p.isDerivedUnion());
		np.setIsOrdered(p.isOrdered());
		np.setIsUnique(p.isUnique());
		setOwnedAttributeIndexIfNecessary(p, np);
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
	@VisitBefore(matchSubclasses = true)
	public void visitReception(Reception emfRec,NakedReceptionImpl nakedRec){
		nakedRec.setSignal((INakedSignal) getNakedPeer(emfRec.getSignal()));
		for(Behavior b:emfRec.getMethods()){
			nakedRec.addMethod((INakedBehavior) getNakedPeer(b));
		}
		addAffectedImplementingClassifiers(nakedRec);
	}
	protected void addAffectedImplementingClassifiers(NakedElementImpl nakedRec){
		if(nakedRec.getOwnerElement() instanceof INakedInterface){
			INakedInterface intf = (INakedInterface) nakedRec.getOwnerElement();
			for(INakedBehavioredClassifier b:intf.getImplementingClassifiers()){
				addAffectedElement(b);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(Operation emfOper,NakedOperationImpl nakedOper){
		Stereotype responsibility = StereotypesHelper.getStereotype(emfOper, StereotypeNames.RESPONSIBILITY);
		if(responsibility != null){
			initializeDeadlines(responsibility, emfOper);
		}
		nakedOper.setQuery(emfOper.isQuery());
		nakedOper.setStatic(emfOper.isStatic());
		for(Behavior b:emfOper.getMethods()){
			INakedElement nakedPeer = getNakedPeer(b);
			if(nakedPeer != null){
				nakedOper.addMethod((INakedBehavior) nakedPeer);
			}else{
				System.out.println("MEthod not found" + b.getQualifiedName());
			}
		}
		Collection<INakedClassifier> raisedExceptions = new HashSet<INakedClassifier>();
		for(Type type:emfOper.getRaisedExceptions()){
			raisedExceptions.add((INakedClassifier) getNakedPeer(type));
		}
		nakedOper.setRaisedExceptions(raisedExceptions);
		addAffectedImplementingClassifiers(nakedOper);
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
