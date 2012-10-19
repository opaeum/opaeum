package org.opaeum.linkage;

import static org.opaeum.metamodel.core.internal.StereotypeNames.HIERARCHY;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.uml.OCL;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.OutputPin;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.OpaqueActionMessageType;
import org.opaeum.eclipse.emulated.TypedElementPropertyBridge;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.name.NameConverter;
import org.opaeum.validation.AbstractValidator;
import org.opaeum.validation.OpaeumValidationPlugin;
import org.opaeum.validation.ValidationPhase;

@StepDependency(phase = ValidationPhase.class,after = {},requires = {},before = {})
public class SourcePopulationResolver extends AbstractValidator{
	@Deprecated
	private Map<Classifier,Collection<Classifier>> hierarchicalSubClasses = new HashMap<Classifier,Collection<Classifier>>();
	@VisitBefore(matchSubclasses = true)
	public void visitClassBefore(Class c){
		if(StereotypesHelper.hasStereotype(c, HIERARCHY)){
			Classifier baseType = (Classifier) getLibrary().getEndToComposite(c).getType();
			hierarchicalSubClasses.put(baseType, EmfClassifierUtil.getAllSubClassifiers(baseType, getModelInScope()));
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClassAfter(Class e){
		List<Property> effectiveAttributes = getLibrary().getEffectiveAttributes(e);
		for(Property p:effectiveAttributes){
			if(p.getOwner() instanceof Interface){
				buildSourcePopulationConstraint(e, p, "SourcePopulationFor" + NameConverter.capitalize(p.getName()));
			}
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitProperty(Property p){
		if(p.getOwner() instanceof Class){
			buildSourcePopulationConstraint((Classifier) p.getOwner(), p, "SourcePopulationFor" + NameConverter.capitalize(p.getName()));
		}
	}
	private void buildSourcePopulationConstraint(Classifier owner,Property p,String name){
		Constraint constr = null;
		boolean isComposition = p.isComposite() || (p.getOtherEnd() != null && p.getOtherEnd().isComposite());
		constr = getSourcePopulationConstraint(p, owner);
		if(!isComposition && shouldResolve(p, owner) && !EmfPropertyUtil.isDerived( p) && !p.isReadOnly()){
			String expression = null;
			if(constr == null){
				if(StereotypesHelper.hasStereotype(owner, HIERARCHY)){
					// Find concrete owners of root of hierarchy
					Classifier e = (Classifier) getLibrary().getEndToComposite(owner).getType();
					Collection<Classifier> subClasses = hierarchicalSubClasses.get(e);
					for(Classifier iClassifier:subClasses){
						if(!iClassifier.isAbstract()){
							e = (Classifier) iClassifier;
							expression = buildOcl(owner, p, e);
						}
					}
				}else{
					expression = buildOcl(owner, p);
				}
			}
			if(expression != null){
				expression = ensureSetsAndRemoveUsedOneToOnes(p, expression);
				OCL ocl = getLibrary().createOcl(owner, Collections.<String,Classifier>emptyMap());
				try{
					OCLExpression query = ocl.createOCLHelper().createQuery(expression);
					getLibrary().getEmulatedPropertyHolder(owner).putQuery(p, query);
				}catch(ParserException e){
					e.printStackTrace();
				}
			}
		}
	}
	private String ensureSetsAndRemoveUsedOneToOnes(Property p,String constr){
		if(EmfPropertyUtil.isOneToOne(p) && p.isNavigable() && p.getOtherEnd() != null && p.getOtherEnd().isNavigable()){
			String other = p.getOtherEnd().getName();
			return constr + "->select(o|o." + other + ".oclIsUndefined() or o." + other + "=self)";
		}else{
			return constr + "->asSet()";
		}
	}
	private String buildOcl(Classifier owner,Property p){
		String ocl = null;
		if(EmfClassifierUtil.isCompositionParticipant(p.getType())){
			Collection<Classifier> subClassifiers = EmfClassifierUtil.getAllSubClassifiers((Classifier) p.getType(), getModelInScope());
			if(subClassifiers.isEmpty()){
				Classifier baseType = (Classifier) p.getType();
				ocl = buildOcl(owner, p, baseType);
			}else{
				StringBuilder union = new StringBuilder();
				for(Classifier c:subClassifiers){
					String builtOcl = buildOcl(owner, p, (Classifier) c);
					if(builtOcl != null){
						if(union.length() == 0){
							union.append(builtOcl);
							union.append("->collect(g|g.oclAsType(");
							union.append(getPathNameInModel((Classifier) p.getType()));
							union.append("))->asSet()");
						}else if(EmfPropertyUtil.isMany(p) && p.isUnique()){
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
		}else if(p.getType() instanceof Enumeration){
			Enumeration en = (Enumeration) p.getType();
			if(en.getAttribute("values", null) != null){
				ocl = getPathNameInModel(en).toString() + "::values";
			}
		}
		return ocl;
	}
	private String buildOcl(Classifier owner,Property p,Classifier baseType){
		String ocl = null;
		StringBuilder pathToCommonComposite = new StringBuilder("self");
		Stereotype st = StereotypesHelper.getStereotype(p, "SourcePopulationCommonComposite");
		Classifier commonComposite = null;
		if(st != null){
			commonComposite = (Classifier) p.getValue(st, "commonComposite");
			if(commonComposite != null){
				if(!isCompositionAncestorOf(commonComposite, baseType)){
					throw new IllegalStateException("The stereotype for the common composition ancestor on " + p.getName()
							+ " is not a compositional ancestor!, Fix the model.");
				}
				calculatePathFromOwnerToCommonCompositionsAncestorForAncestor(commonComposite, owner, baseType, pathToCommonComposite);
			}
		}else{
			commonComposite = calculatePathFromOwnerToCommonCompositionsAncestor(owner, baseType, pathToCommonComposite, 30);
		}
		if(commonComposite != null){
			StringBuilder pathFromCommonComposite = new StringBuilder();
			calculatePathFromCommonCompositionsAncestorToBaseType(commonComposite, baseType, pathFromCommonComposite, 30);
			ocl = pathToCommonComposite.toString() + pathFromCommonComposite;
		}else{
			OpaeumValidationPlugin.logWarning("No compositional ancestor found between " + getPathNameInModel(owner) + " and " + getPathNameInModel(baseType));
		}
		return ocl;
	}
	private boolean shouldResolve(TypedElement p,Classifier owner){
		Classifier baseType = (Classifier) p.getType();
		return (EmfClassifierUtil.isCompositionParticipant(baseType) || (baseType instanceof Enumeration))
				&& EmfClassifierUtil.isCompositionParticipant(owner);
	}
	private Constraint getSourcePopulationConstraint(MultiplicityElement t,Classifier owner){
		for(Constraint n:owner.getOwnedRules()){
			if(n.getConstrainedElements().contains(t)){
				return n;
			}
		}
		return null;
	}
	private Classifier calculatePathFromOwnerToCommonCompositionsAncestorForAncestor(Classifier ancestor,Classifier owner,Classifier type,
			StringBuilder expression){
		if(owner.equals(ancestor)){
			return owner;
		}else{
			Property endToComposite = getLibrary().getEndToComposite(owner);
			if(endToComposite == null){
				return null;
			}else{
				expression.append(".");
				expression.append(endToComposite.getName());
				return calculatePathFromOwnerToCommonCompositionsAncestorForAncestor(ancestor, (Class) endToComposite.getType(), type, expression);
			}
		}
	}
	private Classifier calculatePathFromOwnerToCommonCompositionsAncestor(Classifier owner,Classifier type,StringBuilder expression,int depth){
		if(isCompositionAncestorOf(owner, type) || depth == 0){
			return owner;
		}else{
			Property endToComposite = getLibrary().getEndToComposite(owner);
			if(endToComposite == null || endToComposite.getType().equals(owner)){
				return null;
			}else{
				expression.append(".");
				expression.append(endToComposite.getName());
				return calculatePathFromOwnerToCommonCompositionsAncestor((Classifier) endToComposite.getType(), type, expression, --depth);
			}
		}
	}
	private void calculatePathFromCommonCompositionsAncestorToBaseType(Classifier ancestor,Classifier baseType,StringBuilder expression,
			int depth){
		Property endToComposite = getLibrary().getEndToComposite(baseType);
		if(endToComposite == null || depth == 0){
			return;
		}else if(endToComposite.getType().conformsTo(ancestor)){
			expression.append(".");
			expression.append(endToComposite.getOtherEnd().getName());
		}else{
			calculatePathFromCommonCompositionsAncestorToBaseType(ancestor, (Classifier) endToComposite.getType(), expression, --depth);
			expression.append("->collect(c|c.");
			expression.append(endToComposite.getOtherEnd().getName());
			expression.append(")");
		}
		if(!baseType.equals(endToComposite.getOtherEnd().getType())){
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
	public void visitParameter(Parameter p){
		NamedElement owner = (NamedElement) p.getOwner();
		if(owner instanceof Operation && EmfClassifierUtil.isCompositionParticipant((Type) owner.getOwner())){
			Classifier compositionParticipant = (Classifier) owner.getOwner();
			TypedElementPropertyBridge tepb = new TypedElementPropertyBridge(compositionParticipant, p,getLibrary());
			String name = "SourcePopulationFor" + NameConverter.capitalize(owner.getName()) + NameConverter.capitalize(p.getName());
			buildSourcePopulationConstraint(compositionParticipant, tepb, name);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitParameter(OutputPin p){
		Action a = (Action) p.getOwner();
		String name = "SourcePopulationFor" + NameConverter.capitalize(a.getName()) + NameConverter.capitalize(p.getName());
		if(a instanceof OpaqueAction){
			OpaqueActionMessageType classifier = (OpaqueActionMessageType) getLibrary().getMessageStructure((OpaqueAction) a);
			buildSourcePopulationConstraint(p.getActivity(), classifier.getEmulatedAttribute(p), name);
		}
	}
	public boolean isCompositionAncestorOf(Classifier self,Classifier other){
		boolean hasEncounteredMany = false;
		return isCompositionAncestorOf(self, other, other, hasEncounteredMany, 10);
	}
	private boolean isCompositionAncestorOf(Classifier self,Classifier other,Classifier startingPoint,boolean hasEncounteredMany,int count){
		Property othersEndToComposite = getLibrary().getEndToComposite(other);
		if(othersEndToComposite != null && count > 0){
			Classifier othersComposite = (Classifier) othersEndToComposite.getType();
			if(othersComposite.equals(startingPoint) || othersComposite.equals(other)){
				// TODO
				// circularity - modelling error
				return false;
			}
			// Test for multiplicity of more than one TODO verify this logic,
			// its a hack to ensure sourcePopulation comes back with more than
			// one
			hasEncounteredMany = hasEncounteredMany || EmfPropertyUtil.isMany(othersEndToComposite.getOtherEnd());
			boolean foundRootClass = getLibrary().getEndToComposite(othersComposite) == null;
			if(self.equals(othersComposite) && (hasEncounteredMany || foundRootClass)){
				// Try best to find a many, but still end with the root object
				return true;
			}else if(isCompositionAncestorOf(self, othersComposite, startingPoint, hasEncounteredMany, --count)){
				return true;
			}
			return false;
		}else{
			return false;
		}
	}
}
