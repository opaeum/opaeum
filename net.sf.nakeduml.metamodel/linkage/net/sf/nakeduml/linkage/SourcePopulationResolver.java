package net.sf.nakeduml.linkage;

import static net.sf.nakeduml.metamodel.core.internal.StereotypeNames.HIERARCHY;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.internal.CompositionSiblingsFinder;
import net.sf.nakeduml.metamodel.core.internal.NakedConstraintImpl;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;

@StepDependency(phase = LinkagePhase.class,after = {MappedTypeLinker.class,PinLinker.class,ReferenceResolver.class,TypeResolver.class,
		ValueSpecificationTypeResolver.class},requires = {MappedTypeLinker.class,PinLinker.class,ReferenceResolver.class,TypeResolver.class,
		ValueSpecificationTypeResolver.class},before = NakedParsedOclStringResolver.class)
public class SourcePopulationResolver extends AbstractModelElementLinker{
	private Map<INakedClassifier,List<IClassifier>> hierarchicalSubClasses = new HashMap<INakedClassifier,List<IClassifier>>();
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedEntity c){
		if(c.getStereotype(HIERARCHY) != null){
			List<IClassifier> subClasses = new ArrayList<IClassifier>();
			hierarchicalSubClasses.put(c.getEndToComposite().getNakedBaseType(), subClasses);
			Collection<IClassifier> classifiers = c.getEndToComposite().getNakedBaseType().getSubClasses();
			for(IClassifier iClassifier:classifiers){
				if(iClassifier.equals(c)){
					continue;
				}
				getSubClasses(subClasses, iClassifier);
			}
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
	private void getSubClasses(List<IClassifier> subClasses,IClassifier iClassifier){
		subClasses.add(iClassifier);
		for(IClassifier classifier:iClassifier.getSubClasses()){
			getSubClasses(subClasses, classifier);
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
					List<IClassifier> subClasses = hierarchicalSubClasses.get(e);
					for(IClassifier iClassifier:subClasses){
						if(!iClassifier.getIsAbstract()){
							e = (ICompositionParticipant) iClassifier;
							constr = buildOclConstraint(p, constr, e);
							ensureSetsAndRemoveUsedOneToOnes(p, constr);
						}
					}
					constr = null;
				}else{
					constr = buildOclConstraint(p, constr, owner);
				}
			}
			if(constr != null){
				ensureSetsAndRemoveUsedOneToOnes(p, constr);
			}
		}
	}
	private void ensureSetsAndRemoveUsedOneToOnes(INakedProperty p,INakedConstraint constr){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
		if(map.isOneToOne() && p.isNavigable() && p.getOtherEnd() != null && p.getOtherEnd().isNavigable()){
			ParsedOclString pcs = (ParsedOclString) constr.getSpecification().getOclValue();
			String other = p.getOtherEnd().getName();
			pcs.setExpressionString(pcs.getExpressionString() + "->select(o|o." + other + ".oclIsUndefined() or o." + other + "=self)");
			// System.out.println("oneToOne:" + pcs.getExpressionString());
		}else{
			if(!(constr.getSpecification().getOclValue() instanceof ParsedOclString)){
				System.out.println();
			}
			ParsedOclString pcs = (ParsedOclString) constr.getSpecification().getOclValue();
			pcs.setExpressionString(pcs.getExpressionString() + "->asSet()");
			// System.out.println("toMany:" + pcs.getExpressionString());
		}
	}
	private INakedConstraint buildOclConstraint(INakedProperty p,INakedConstraint constr,ICompositionParticipant e){
		String ocl = buildOcl(e, p);
		if(ocl != null){
			NakedConstraintImpl constraint = ConstraintUtil.buildArtificialConstraint(e, p, ocl, "SourcePopulation");
			constraint.setConstrainedElement(p);
			constr = constraint;
		}
		return constr;
	}
	private String buildOcl(ICompositionParticipant owner,INakedProperty p){
		String ocl = null;
		if(p.getNakedBaseType() instanceof ICompositionParticipant){
			if(InterfaceUtil.getImplementationsOf(p.getNakedBaseType()).isEmpty()){
				ICompositionParticipant baseType = (ICompositionParticipant) p.getNakedBaseType();
				ocl = buildOcl(owner, p, baseType);
			}else{
				StringBuilder union = new StringBuilder();
				for(INakedEntity c:InterfaceUtil.getImplementationsOf(p.getNakedBaseType())){
					String builtOcl = buildOcl(owner, p, c);
					if(union.length() == 0){
						union.append(builtOcl);
						union.append("->collect(g|g.oclAsType(");
						union.append(getPathNameInModel(p.getNakedBaseType()));
						union.append("))->asSet()");
					}else if(OJUtil.buildStructuralFeatureMap(p).isMany() && p.isUnique()){
						union.append("->union(").append(builtOcl).append(")");
					}else{
						union.append("->union(").append(builtOcl).append("->asSet())");
					}
				}
				ocl = union.toString();
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
		String ocl;
		StringBuilder pathToCommonComposite = new StringBuilder("self");
		ICompositionParticipant commonComposite = p.getTaggedValue("SourcePopulationCommonComposite", "commonComposite");
		;
		if(commonComposite != null){
			if(!CompositionSiblingsFinder.isCompositionAncestorOf(commonComposite, baseType)){
				throw new IllegalStateException("The stereotype for the common composition ancestor on " + p.getName()
						+ " is not a compositional ancestor!, Fix the model.");
			}
			calculatePathFromOwnerToCommonCompositionsAncestorForAncestor(commonComposite, owner, baseType, pathToCommonComposite);
		}else{
			commonComposite = calculatePathFromOwnerToCommonCompositionsAncestor(owner, baseType, pathToCommonComposite);
		}
		if(commonComposite != null){
			StringBuilder pathFromCommonComposite = new StringBuilder();
			calculatePathFromCommonCompositionsAncestorToBaseType(commonComposite, baseType, pathFromCommonComposite);
			ocl = pathToCommonComposite.toString() + pathFromCommonComposite;
		}else{
			throw new IllegalStateException("No compositional ancestor found between " + getPathNameInModel(owner) + " and " + getPathNameInModel(baseType));
		}
		return ocl;
	}
	private boolean shouldResolve(INakedTypedElement p,INakedClassifier owner){
		return p.getNakedBaseType().getStereotype(StereotypeNames.HELPER) == null
				&& ((p.getNakedBaseType() instanceof ICompositionParticipant || (p.getNakedBaseType() instanceof INakedEnumeration)
						&& owner instanceof ICompositionParticipant));
	}
	private INakedConstraint getSourcePopulationConstraint(INakedTypedElement t,INakedClassifier owner){
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
	private ICompositionParticipant calculatePathFromOwnerToCommonCompositionsAncestor(ICompositionParticipant owner,ICompositionParticipant type,StringBuilder expression){
		if(CompositionSiblingsFinder.isCompositionAncestorOf(owner, type)){
			return owner;
		}else{
			INakedProperty endToComposite = owner.getEndToComposite();
			if(endToComposite == null){
				return null;
			}else{
				expression.append(".");
				expression.append(endToComposite.getName());
				return calculatePathFromOwnerToCommonCompositionsAncestor((ICompositionParticipant) endToComposite.getNakedBaseType(), type, expression);
			}
		}
	}
	private void calculatePathFromCommonCompositionsAncestorToBaseType(ICompositionParticipant ancestor,ICompositionParticipant baseType,StringBuilder expression){
		INakedProperty endToComposite = baseType.getEndToComposite();
		if(endToComposite == null){
			return;
		}else if(endToComposite.getNakedBaseType().conformsTo(ancestor)){
			expression.append(".");
			expression.append(endToComposite.getOtherEnd().getName());
		}else{
			calculatePathFromCommonCompositionsAncestorToBaseType(ancestor, (ICompositionParticipant) endToComposite.getNakedBaseType(), expression);
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
