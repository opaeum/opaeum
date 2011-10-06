package org.opeum.linkage;

import static org.opeum.metamodel.core.internal.StereotypeNames.HIERARCHY;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.activities.INakedOutputPin;
import org.opeum.metamodel.core.ICompositionParticipant;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedConstraint;
import org.opeum.metamodel.core.INakedEntity;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedMultiplicityElement;
import org.opeum.metamodel.core.INakedParameter;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedTypedElement;
import org.opeum.metamodel.core.internal.CompositionSiblingsFinder;
import org.opeum.metamodel.core.internal.NakedConstraintImpl;
import org.opeum.metamodel.core.internal.StereotypeNames;
import org.opeum.name.NameConverter;

@StepDependency(phase = LinkagePhase.class,after = {
		MappedTypeLinker.class,PinLinker.class,ReferenceResolver.class,TypeResolver.class,CompositionEmulator.class
},requires = {
		MappedTypeLinker.class,PinLinker.class,ReferenceResolver.class,TypeResolver.class,CompositionEmulator.class
},before = NakedParsedOclStringResolver.class)
public class SourcePopulationResolver extends AbstractModelElementLinker{
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
				buildSourcePopulationConstraint(e, p);
			}
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitProperty(INakedProperty p){
		if(p.getOwner() instanceof INakedEntity){
			buildSourcePopulationConstraint((ICompositionParticipant) p.getOwner(), p);
		}
	}
	private void buildSourcePopulationConstraint(ICompositionParticipant owner,INakedProperty p){
		boolean isComposition = p.isComposite() || (p.getOtherEnd() != null && p.getOtherEnd().isComposite());
		if(!isComposition && shouldResolve(p, owner) && !p.isDerived() && !p.isReadOnly()){
			INakedConstraint constr = getSourcePopulationConstraint(p, owner);
			if(constr == null){
				if(owner.hasStereotype(HIERARCHY)){
					// Find concrete owners of root of hierarchy
					ICompositionParticipant e = (ICompositionParticipant) owner.getEndToComposite().getNakedBaseType();
					Collection<INakedClassifier> subClasses = hierarchicalSubClasses.get(e);
					for(INakedClassifier iClassifier:subClasses){
						if(!iClassifier.getIsAbstract()){
							e = (ICompositionParticipant) iClassifier;
							constr = buildOclConstraint(p, constr, e);
						}
					}
					constr = null;
				}else{
					constr = buildOclConstraint(p, constr, owner);
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
	private INakedConstraint buildOclConstraint(INakedProperty p,INakedConstraint constr,ICompositionParticipant e){
		String ocl = buildOcl(e, p);
		if(ocl != null){
			NakedConstraintImpl constraint = ConstraintUtil.buildArtificialConstraint(e, p, ocl, "SourcePopulationFor" + NameConverter.capitalize(p.getName()));
			constraint.setConstrainedElement(p);
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
			commonComposite = calculatePathFromOwnerToCommonCompositionsAncestor(owner, baseType, pathToCommonComposite,30);
		}
		if(commonComposite != null){
			StringBuilder pathFromCommonComposite = new StringBuilder();
			calculatePathFromCommonCompositionsAncestorToBaseType(commonComposite, baseType, pathFromCommonComposite,30);
			ocl = pathToCommonComposite.toString() + pathFromCommonComposite;
		}else{
			System.out.println("No compositional ancestor found between " + getPathNameInModel(owner) + " and " + getPathNameInModel(baseType));
		}
		return ocl;
	}
	private boolean shouldResolve(INakedTypedElement p,INakedClassifier owner){
		INakedClassifier baseType = p.getNakedBaseType();
		return ((baseType instanceof ICompositionParticipant || (baseType instanceof INakedEnumeration) && owner instanceof ICompositionParticipant))
				&& !(baseType.hasStereotype(StereotypeNames.HELPER) || owner.hasStereotype(StereotypeNames.HELPER));
	}
	private INakedConstraint getSourcePopulationConstraint(INakedMultiplicityElement t,INakedClassifier owner){
		for(INakedConstraint n:owner.getOwnedRules()){
			if(t.equals(n.getConstrainedElement())){
				return n;
			}
		}
		return null;
	}
	private ICompositionParticipant calculatePathFromOwnerToCommonCompositionsAncestorForAncestor(ICompositionParticipant ancestor,ICompositionParticipant owner,
			ICompositionParticipant type,StringBuilder expression){
		if(owner.equals(ancestor)){
			return owner;
		}else{
			INakedProperty endToComposite = owner.getEndToComposite();
			if(endToComposite == null){
				return null;
			}else{
				expression.append(".");
				expression.append(endToComposite.getName());
				return calculatePathFromOwnerToCommonCompositionsAncestorForAncestor(ancestor, (INakedEntity) endToComposite.getNakedBaseType(), type, expression);
			}
		}
	}
	private ICompositionParticipant calculatePathFromOwnerToCommonCompositionsAncestor(ICompositionParticipant owner,ICompositionParticipant type,StringBuilder expression, int depth){
		if(CompositionSiblingsFinder.isCompositionAncestorOf(owner, type) || depth==0){
			return owner;
		}else{
			INakedProperty endToComposite = owner.getEndToComposite();
			if(endToComposite == null || endToComposite.getBaseType().equals(owner)){
				return null;
			}else{
				expression.append(".");
				expression.append(endToComposite.getName());
				return calculatePathFromOwnerToCommonCompositionsAncestor((ICompositionParticipant) endToComposite.getNakedBaseType(), type, expression,--depth);
			}
		}
	}
	private void calculatePathFromCommonCompositionsAncestorToBaseType(ICompositionParticipant ancestor,ICompositionParticipant baseType,StringBuilder expression, int depth){
		INakedProperty endToComposite = baseType.getEndToComposite();
		if(endToComposite == null || depth==0){
			return;
		}else if(endToComposite.getNakedBaseType().conformsTo(ancestor)){
			expression.append(".");
			expression.append(endToComposite.getOtherEnd().getName());
		}else{
			calculatePathFromCommonCompositionsAncestorToBaseType(ancestor, (ICompositionParticipant) endToComposite.getNakedBaseType(), expression,--depth);
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
		// TODO provide lookups for Parameters
	}
	@VisitAfter(matchSubclasses = true)
	public void visitParameter(INakedOutputPin p){
		// TODO provide lookups for Output pins for tasks
	}
}
