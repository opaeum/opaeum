package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.INakedActivityEdge;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityEdgeImpl;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.internal.NakedConstraintImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedValueSpecificationImpl;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.ActivityEdge;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.ChangeEvent;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceValue;
import org.eclipse.uml2.uml.LiteralBoolean;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralString;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.TimeExpression;
import org.eclipse.uml2.uml.ValuePin;
import org.eclipse.uml2.uml.ValueSpecification;

/**
 * THe constraint extraction is done AFTER all possible constrained elements have been populated
 * 
 * @author ampie
 * 
 */
@StepDependency(phase = EmfExtractionPhase.class,requires = {
		InstanceExtractor.class,TriggerExtractor.class,ActivityEdgeExtractor.class
},after = {
		InstanceExtractor.class,TriggerExtractor.class,ActivityEdgeExtractor.class
})
public class ValueSpecificationExtractor extends AbstractExtractorFromEmf{
	@Override
	protected int getThreadPoolSize(){
		return 12;
	}
	@VisitBefore()
	public void visitConstraint(Constraint c,NakedConstraintImpl nc){
		addAffectedElement(getNakedPeer(c.getOwner()));
	}
	@VisitAfter()
	public void visitConstraintAfter(Constraint c,NakedConstraintImpl nc){
		// Force proper population of ownedElement collections(Pre/Post conditions
		nc.getOwnerElement().addOwnedElement(nc);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitValueSpecification(ValueSpecification value,NakedValueSpecificationImpl result){
		if(value instanceof OpaqueExpression){
			OpaqueExpression oe = ((OpaqueExpression) value);
			ParsedOclString pcs = buildParsedOclString(value, oe.getLanguages(), oe.getBodies(), calcOclUsage(value));
			if(pcs.getExpressionString().equalsIgnoreCase("else")){
				result.setValue(INakedActivityEdge.ELSE);
			}else{
				result.setValue(pcs);
			}
		}else if(value instanceof TimeExpression && ((TimeExpression) value).getExpr() instanceof OpaqueExpression){
			OpaqueExpression oe = (OpaqueExpression) ((TimeExpression) value).getExpr();
			result.setValue(buildParsedOclString(value, oe.getLanguages(), oe.getBodies(), OclUsageType.DEF));
		}else if(value instanceof LiteralString){
			result.setValue(((LiteralString) value).stringValue());
			result.setType(getOclLibrary().lookupStandardType(IOclLibrary.StringTypeName));
		}else if(value instanceof LiteralBoolean){
			result.setValue(new Boolean(((LiteralBoolean) value).booleanValue()));
			result.setType(getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
		}else if(value instanceof LiteralInteger){
			result.setValue(new Integer(((LiteralInteger) value).integerValue()));
			result.setType(getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
		}else if(value instanceof LiteralUnlimitedNatural){
			LiteralUnlimitedNatural lun = (LiteralUnlimitedNatural) value;
			result.setValue(new Integer(lun.unlimitedValue()));
			result.setType(getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
		}else if(value instanceof InstanceValue){
			INakedElement instance = getNakedPeer(((InstanceValue) value).getInstance());
			((NakedValueSpecificationImpl) result).setValue(instance);
			if(!(instance instanceof INakedEnumerationLiteral || instance == null)){
				result.addOwnedElement(instance);
			}
		}
		if(value.getOwner() instanceof ActivityEdge){
			NakedActivityEdgeImpl nae = (NakedActivityEdgeImpl) getNakedPeer(value.getOwner());
			ActivityEdge ae = (ActivityEdge) value.getOwner();
			if(value == ae.getGuard()){
				nae.setGuard(result);
			}
			if(value == ae.getWeight()){
				nae.setWeight(result);
			}
		}
	}
	@Override
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		if(e instanceof ValueSpecification){
			EObject owner = e.eContainer();
			if(owner instanceof TimeExpression){
				return null;
			}else if(owner instanceof Property && e == ((Property) owner).getDefaultValue()){
				return super.createElementFor(e, peerClass);
			}else if(owner instanceof ValuePin && e == ((ValuePin) owner).getValue()){
				return super.createElementFor(e, peerClass);
			}else if(owner instanceof Constraint || owner instanceof ActivityEdge || owner instanceof ChangeEvent || owner instanceof EAnnotation
					|| owner instanceof Slot){
				return super.createElementFor(e, peerClass);
			}else{
				return null;
			}
		}
		return super.createElementFor(e, peerClass);
	}
	private OclUsageType calcOclUsage(ValueSpecification value){
		if(value.getOwner() instanceof Constraint){
			Constraint c = (Constraint) value.getOwner();
			Element owner = c.getOwner();
			if(owner instanceof Action){
				if(((Action) owner).getLocalPreconditions().contains(c)){
					return OclUsageType.PRE;
				}else{
					return OclUsageType.POST;
				}
			}
			if(owner instanceof Behavior){
				if(((Behavior) owner).getPreconditions().contains(c)){
					return OclUsageType.PRE;
				}else{
					return OclUsageType.POST;
				}
			}
			if(owner instanceof Operation){
				if(((Operation) owner).getPreconditions().contains(c)){
					return OclUsageType.PRE;
				}else if(((Operation) owner).getPostconditions().contains(c)){
					return OclUsageType.POST;
				}else{
					return OclUsageType.BODY;
				}
			}
			return OclUsageType.INV;
		}
		return OclUsageType.DERIVE;
	}
	protected ParsedOclString buildParsedOclString(NamedElement oe,EList<String> languages,EList<String> bodies,OclUsageType usage){
		return super.buildParsedOclString(oe, languages, bodies, usage);
	}
}
