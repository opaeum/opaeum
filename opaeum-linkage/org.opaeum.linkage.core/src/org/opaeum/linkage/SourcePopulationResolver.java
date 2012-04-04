package org.opaeum.linkage;

import static org.opaeum.metamodel.core.internal.StereotypeNames.HIERARCHY;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.activities.INakedOutputPin;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedConstraint;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedMultiplicityElement;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedTypedElement;
import org.opaeum.metamodel.core.internal.CompositionSiblingsFinder;
import org.opaeum.metamodel.core.internal.NakedConstraintImpl;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import org.opaeum.name.NameConverter;

@StepDependency(phase = LinkagePhase.class,after = {MappedTypeLinker.class,PinLinker.class,ReferenceResolver.class,TypeResolver.class,
		CompositionEmulator.class},requires = {MappedTypeLinker.class,PinLinker.class,ReferenceResolver.class,TypeResolver.class,
		CompositionEmulator.class},before = NakedParsedOclStringResolver.class)
public class SourcePopulationResolver extends AbstractModelElementLinker{
	@Deprecated
	private Map<INakedClassifier,Collection<INakedClassifier>> hierarchicalSubClasses = new HashMap<INakedClassifier,Collection<INakedClassifier>>();
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedEntity c){
		if(c.getStereotype(HIERARCHY) != null){
			INakedClassifier baseType = c.getEndToComposite().getNakedBaseType();
			hierarchicalSubClasses.put(baseType, GeneralizationUtil.getAllSubClassifiers(baseType, getModelInScope()));
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitEntity(INakedEntity e){
		for(INakedProperty p:e.getEffectiveAttributes()){
			if(p.getOwner() instanceof INakedInterface){
				buildSourcePopulationConstraint(e, p, "SourcePopulationFor" + NameConverter.capitalize(p.getName()));
			}
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitProperty(INakedProperty p){
		if(p.getOwner() instanceof INakedEntity){
			buildSourcePopulationConstraint((ICompositionParticipant) p.getOwner(), p,
					"SourcePopulationFor" + NameConverter.capitalize(p.getName()));
		}
	}
	private void buildSourcePopulationConstraint(ICompositionParticipant owner,INakedProperty p,String name){
		INakedConstraint constr = null;
		boolean isComposition = p.isComposite() || (p.getOtherEnd() != null && p.getOtherEnd().isComposite());
		if(!isComposition && shouldResolve(p, owner) && !p.isDerived() && !p.isReadOnly()){
			constr = getSourcePopulationConstraint(p, owner);
			if(constr == null){
				if(owner.hasStereotype(HIERARCHY)){
					// Find concrete owners of root of hierarchy
					ICompositionParticipant e = (ICompositionParticipant) owner.getEndToComposite().getNakedBaseType();
					Collection<INakedClassifier> subClasses = hierarchicalSubClasses.get(e);
					for(INakedClassifier iClassifier:subClasses){
						if(!iClassifier.getIsAbstract()){
							e = (ICompositionParticipant) iClassifier;
							constr = buildOclConstraint(p, constr, e, name);
						}
					}
				}else{
					constr = buildOclConstraint(p, constr, owner, name);
				}
			}
			if(constr != null && constr.getSpecification().getOclValue() instanceof ParsedOclString){
				ensureSetsAndRemoveUsedOneToOnes(p, constr);
				if(workspace.isPrimaryModel(getCurrentRootObject())){
					super.addAffectedElement(p.getOwner());
				}
			}
		}
	}
	private void ensureSetsAndRemoveUsedOneToOnes(INakedProperty p,INakedConstraint constr){
		if(PropertyUtil.isOneToOne(p) && p.isNavigable() && p.getOtherEnd() != null && p.getOtherEnd().isNavigable()){
			ParsedOclString pcs = (ParsedOclString) constr.getSpecification().getOclValue();
			String other = p.getOtherEnd().getName();
			pcs.setExpressionString(pcs.getExpressionString() + "->select(o|o." + other + ".oclIsUndefined() or o." + other + "=self)");
		}else{
			ParsedOclString pcs = (ParsedOclString) constr.getSpecification().getOclValue();
			pcs.setExpressionString(pcs.getExpressionString() + "->asSet()");
		}
	}
	private INakedConstraint buildOclConstraint(INakedProperty p,INakedConstraint constr,ICompositionParticipant e,String name){
		String ocl = buildOcl(e, p);
		if(ocl != null){
			NakedConstraintImpl constraint = ConstraintUtil.buildArtificialConstraint(e, p, ocl, name);
			constr = constraint;
		}
		return constr;
	}
	private String buildOcl(ICompositionParticipant owner,INakedProperty p){
		String ocl = null;
		if(p.getNakedBaseType() instanceof ICompositionParticipant){
			Collection<INakedClassifier> subClassifiers = GeneralizationUtil.getAllSubClassifiers(p.getNakedBaseType(), getModelInScope());
			if(subClassifiers.isEmpty()){
				ICompositionParticipant baseType = (ICompositionParticipant) p.getNakedBaseType();
				ocl = buildOcl(owner, p, baseType);
			}else{
				StringBuilder union = new StringBuilder();
				for(INakedClassifier c:subClassifiers){
					String builtOcl = buildOcl(owner, p, (ICompositionParticipant) c);
					if(builtOcl != null){
						if(union.length() == 0){
							union.append(builtOcl);
							union.append("->collect(g|g.oclAsType(");
							union.append(getPathNameInModel(p.getNakedBaseType()));
							union.append("))->asSet()");
						}else if(PropertyUtil.isMany(p) && p.isUnique()){
							union.append("->union(").append(builtOcl).append(")");
						}else{
							union.append("->union(").append(builtOcl).append("->asSet())");
						}
					}
				}
				if(union.length() > 0){
					ocl = union.toString();
				}
			}
		}else if(p.getNakedBaseType() instanceof INakedEnumeration){
			if(p.getNakedBaseType().findAttribute("values") != null){
				INakedEnumeration en = (INakedEnumeration) p.getNakedBaseType();
				ocl = getPathNameInModel(en).toString() + "::values";
			}
		}
		return ocl;
	}
	private String buildOcl(ICompositionParticipant owner,INakedProperty p,ICompositionParticipant baseType){
		String ocl = null;
		StringBuilder pathToCommonComposite = new StringBuilder("self");
		ICompositionParticipant commonComposite = p.getTaggedValue("SourcePopulationCommonComposite", "commonComposite");
		if(commonComposite != null){
			if(!CompositionSiblingsFinder.isCompositionAncestorOf(commonComposite, baseType)){
				throw new IllegalStateException("The stereotype for the common composition ancestor on " + p.getName()
						+ " is not a compositional ancestor!, Fix the model.");
			}
			calculatePathFromOwnerToCommonCompositionsAncestorForAncestor(commonComposite, owner, baseType, pathToCommonComposite);
		}else{
			commonComposite = calculatePathFromOwnerToCommonCompositionsAncestor(owner, baseType, pathToCommonComposite, 30);
		}
		if(commonComposite != null){
			StringBuilder pathFromCommonComposite = new StringBuilder();
			calculatePathFromCommonCompositionsAncestorToBaseType(commonComposite, baseType, pathFromCommonComposite, 30);
			ocl = pathToCommonComposite.toString() + pathFromCommonComposite;
		}else{
			System.out.println("No compositional ancestor found between " + getPathNameInModel(owner) + " and " + getPathNameInModel(baseType));
		}
		return ocl;
	}
	private boolean shouldResolve(INakedTypedElement p,INakedClassifier owner){
		INakedClassifier baseType = p.getNakedBaseType();
		return ((baseType instanceof ICompositionParticipant || (baseType instanceof INakedEnumeration)
				&& owner instanceof ICompositionParticipant))
				&& !(baseType.hasStereotype(StereotypeNames.HELPER) || owner.hasStereotype(StereotypeNames.HELPER));
	}
	private INakedConstraint getSourcePopulationConstraint(INakedMultiplicityElement t,INakedClassifier owner){
		for(INakedConstraint n:owner.getOwnedRules()){
			if(n.getConstrainedElements().contains(t)){
				return n;
			}
		}
		return null;
	}
	private ICompositionParticipant calculatePathFromOwnerToCommonCompositionsAncestorForAncestor(ICompositionParticipant ancestor,
			ICompositionParticipant owner,ICompositionParticipant type,StringBuilder expression){
		if(owner.equals(ancestor)){
			return owner;
		}else{
			INakedProperty endToComposite = owner.getEndToComposite();
			if(endToComposite == null){
				return null;
			}else{
				expression.append(".");
				expression.append(endToComposite.getName());
				return calculatePathFromOwnerToCommonCompositionsAncestorForAncestor(ancestor, (INakedEntity) endToComposite.getNakedBaseType(),
						type, expression);
			}
		}
	}
	private ICompositionParticipant calculatePathFromOwnerToCommonCompositionsAncestor(ICompositionParticipant owner,
			ICompositionParticipant type,StringBuilder expression,int depth){
		if(CompositionSiblingsFinder.isCompositionAncestorOf(owner, type) || depth == 0){
			return owner;
		}else{
			INakedProperty endToComposite = owner.getEndToComposite();
			if(endToComposite == null || endToComposite.getBaseType().equals(owner)){
				return null;
			}else{
				expression.append(".");
				expression.append(endToComposite.getName());
				return calculatePathFromOwnerToCommonCompositionsAncestor((ICompositionParticipant) endToComposite.getNakedBaseType(), type,
						expression, --depth);
			}
		}
	}
	private void calculatePathFromCommonCompositionsAncestorToBaseType(ICompositionParticipant ancestor,ICompositionParticipant baseType,
			StringBuilder expression,int depth){
		INakedProperty endToComposite = baseType.getEndToComposite();
		if(endToComposite == null || depth == 0){
			return;
		}else if(endToComposite.getNakedBaseType().conformsTo(ancestor)){
			expression.append(".");
			expression.append(endToComposite.getOtherEnd().getName());
		}else{
			calculatePathFromCommonCompositionsAncestorToBaseType(ancestor, (ICompositionParticipant) endToComposite.getNakedBaseType(),
					expression, --depth);
			expression.append("->collect(c|c.");
			expression.append(endToComposite.getOtherEnd().getName());
			expression.append(")");
		}
		if(!baseType.equals(endToComposite.getOtherEnd().getNakedBaseType())){
			// When the compositional relationship is inherited by baseType, the
			// otherEnd's type may be the superType
			// Select only those that are of baseType,
			expression.append("->select(c|c.oclIsKindOf(");
			expression.append(getPathNameInModel(baseType));
			expression.append("))->collect(c|c.oclAsType(");
			expression.append(getPathNameInModel(baseType));
			expression.append("))");
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitParameter(INakedParameter p){
		INakedElement owner = (INakedElement) p.getOwnerElement();
		if(owner instanceof INakedOperation && owner.getOwnerElement() instanceof ICompositionParticipant){

			ICompositionParticipant compositionParticipant = (ICompositionParticipant) owner.getOwnerElement();
			TypedElementPropertyBridge tepb = new TypedElementPropertyBridge(compositionParticipant, p);
			String name = "SourcePopulationFor" + NameConverter.capitalize(owner.getName()) + NameConverter.capitalize(p.getName());
			buildSourcePopulationConstraint(compositionParticipant, tepb, name);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitParameter(INakedOutputPin p){
		String name = "SourcePopulationFor" + NameConverter.capitalize(p.getAction().getName()) + NameConverter.capitalize(p.getName());
		buildSourcePopulationConstraint(p.getActivity(), new TypedElementPropertyBridge(p.getActivity(), p), name);
	}
}
