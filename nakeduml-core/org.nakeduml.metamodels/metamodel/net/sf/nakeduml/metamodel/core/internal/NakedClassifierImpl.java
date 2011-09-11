package net.sf.nakeduml.metamodel.core.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedReception;
import net.sf.nakeduml.metamodel.core.CodeGenerationStrategy;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComment;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedPowerType;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.expressions.internal.analysis.Conformance;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IAssociationClass;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IState;
import nl.klasse.octopus.model.IStructuralFeature;
import nl.klasse.octopus.model.VisibilityKind;
import nl.klasse.octopus.oclengine.IOclContext;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.library.StdlibBasic;

/**
 * Common superclass for all types that have ownedAttributes and generalizations. THis class deviates from UML2 in that it also supports
 * ownedOperations as required by Octopus. As a result, Signals therefore incorrectly have operations
 */
public abstract class NakedClassifierImpl extends NakedNameSpaceImpl implements INakedClassifier{
	private static final long serialVersionUID = -9194358342840031394L;
	protected List<INakedProperty> ownedAttributes = new ArrayList<INakedProperty>();
	private List<INakedOperation> ownedOperations = new ArrayList<INakedOperation>();
	private Set<INakedReception> ownedReceptions = new HashSet<INakedReception>();
	protected List<INakedGeneralization> generalisations = new ArrayList<INakedGeneralization>();
	private INakedPowerType powerType;
	private CodeGenerationStrategy codeGenerationStrategy;
	private String mappedImplementationType;
	private Collection<INakedClassifier> subClasses = new HashSet<INakedClassifier>();
	private List<INakedComment> comments = new ArrayList<INakedComment>();
	private List<INakedConstraint> ownedRules = new ArrayList<INakedConstraint>();
	private List<IOclContext> definitions = new ArrayList<IOclContext>();
	private List<IAttribute> oclDefAttributes = new ArrayList<IAttribute>();
	private boolean isAbstract;
	private VisibilityKind visibility;
	private INakedProperty endToComposite;
	public List<INakedGeneralization> getNakedGeneralizations(){
		return generalisations;
	}
	@Override
	public INakedClassifier getNestingClassifier(){
		return super.getNearestClassifier();
	}
	@Override
	public void removeObsoleteArtificialProperties(){
		Collection<ArtificialProperty> aps = new HashSet<ArtificialProperty>();
		ArrayList<INakedProperty> oas = new ArrayList<INakedProperty>(ownedAttributes);
		for(INakedProperty p:oas){
			if(p instanceof ArtificialProperty && p.getOtherEnd() != null){
				aps.add((ArtificialProperty) p);
			}
		}
		for(ArtificialProperty ap:aps){
			for(INakedProperty p:oas){
				if(!(p instanceof ArtificialProperty)){
					boolean compositionSame = p.isComposite() == ap.isComposite() && p.getOtherEnd() != null
							&& p.getOtherEnd().isComposite() == ap.getOtherEnd().isComposite();
					if(compositionSame && p.getMultiplicity().getUpper() == ap.getMultiplicity().getUpper() && p.getBaseType().equals(ap.getBaseType())){
						removeOwnedElement(ap);
						if(ap == endToComposite){
							endToComposite = p;
						}
						break;
					}
				}
			}
		}
	}
	@Override
	public VisibilityKind getVisibility(){
		return visibility;
	}
	@Override
	public void setVisibility(VisibilityKind visibility){
		this.visibility = visibility;
	}
	public List<INakedConstraint> getOwnedRules(){
		return ownedRules;
	}
	public void setOwnedRules(List<INakedConstraint> ownedRules){
		this.ownedRules = ownedRules;
	}
	@SuppressWarnings("unchecked")
	public List<IAttribute> getAttributes(){
		return Collections.unmodifiableList(getAttributes(false, false, true));
	}
	@SuppressWarnings("unchecked")
	public List<IAttribute> getClassAttributes(){
		return Collections.unmodifiableList(getAttributes(false, true, true));
	}
	@SuppressWarnings("unchecked")
	public List<IAttribute> getAllAttributes(){
		return Collections.unmodifiableList(getAttributes(false, false, false));
	}
	@SuppressWarnings("unchecked")
	public List<IAssociationEnd> getAllNavigations(){
		return Collections.unmodifiableList(getAttributes(true, false, false));
	}
	@SuppressWarnings("unchecked")
	public List<IAssociationEnd> getNavigations(){
		return Collections.unmodifiableList(getAttributes(true, false, true));
	}
	public List<INakedProperty> getOwnedAttributes(){
		return this.ownedAttributes;
	}
	public INakedProperty findEffectiveAttribute(String name){
		return findAttributeFrom(name, getEffectiveAttributes());
	}
	/**
	 * Not to be confused with the Octopus getAllAttributes property - includes navigations. Used primarily in NakedUml.
	 */
	public List<INakedProperty> getEffectiveAttributes(){
		List<INakedProperty> results = new ArrayList<INakedProperty>();
		for(INakedGeneralization s:getNakedGeneralizations()){
			List<? extends INakedProperty> superAttributes = s.getGeneral().getEffectiveAttributes();
			addEffectiveAttributes(results, superAttributes);
		}
		for(IInterface i:getInterfaces()){
			List<? extends INakedProperty> interfaceAttributes = ((INakedClassifier) i).getEffectiveAttributes();
			addEffectiveAttributes(results, interfaceAttributes);
		}
		Collections.sort(ownedAttributes, new Comparator<INakedProperty>(){
			@Override
			public int compare(INakedProperty o1,INakedProperty o2){
				return o1.getOwnedAttributeIndex() - o2.getOwnedAttributeIndex();
			}
		});
		addEffectiveAttributes(results, ownedAttributes);
		return results;
	}
	/**
	 * Allows properties lower down in the inheritance tree to replace properties higher up in the tree by name
	 * 
	 * @param results
	 * @param attributes
	 */
	public static void addEffectiveAttributes(List<INakedProperty> results,List<? extends INakedProperty> attributes){
		Iterator<INakedProperty> iter = results.iterator();
		while(iter.hasNext()){
			INakedProperty presentProperty = (INakedProperty) iter.next();
			if(presentProperty.getName() == null){
				iter.remove();
			}else{
				for(INakedProperty newProperty:attributes){
					if(presentProperty.getName().equals(newProperty.getName())){
						iter.remove();
					}
				}
			}
		}
		results.addAll(attributes);
	}
	/**
	 * NOt exposed - to be exposed by subclasses
	 */
	public INakedProperty getEndToComposite(){
		if(this.endToComposite == null){
			for(INakedProperty np:getEffectiveAttributes()){
				if(np.getOtherEnd() != null && np.getOtherEnd().isComposite()){
					// Give preference to non-derived or local endsToComposite
					if(endToComposite == null){
						this.endToComposite = np;
					}else{
						boolean notDerived = endToComposite.isDerived() && !np.isDerived();
						boolean local = np.getOwner() == this && this.endToComposite.getOwner() != this;
						if(this.endToComposite == null || notDerived || local){
							this.endToComposite = np;
						}
					}
				}
			}
		}
		return this.endToComposite;
	}
	public boolean hasComposite(){
		return getEndToComposite() != null;
	}
	public final void setEndToComposite(INakedProperty endToComposite){
		if(this.endToComposite == null && !getOwnedAttributes().contains(endToComposite)){
			addOwnedElement(endToComposite);
		}
		this.endToComposite = endToComposite;
	}
	@SuppressWarnings("unchecked")
	protected List<IAttribute> getAllAttributesForOcl(boolean classScope){
		return getAttributes(false, classScope, false);
	}
	/**
	 * Used primairly in for Octopus to resolve different types of attributes
	 */
	@SuppressWarnings({
			"unchecked","rawtypes"
	})
	protected List getAttributes(boolean associationEnds,boolean classScope,boolean ownedOnly){
		List results = new ArrayList();
		List<IStructuralFeature> source = new ArrayList<IStructuralFeature>(ownedOnly ? getOwnedAttributes() : getEffectiveAttributes());
		if(!associationEnds){
			source.addAll(oclDefAttributes);
		}
		for(IStructuralFeature f:source){
			if(f.hasClassScope() == classScope){
				boolean isNavigation = f instanceof INakedProperty && ((INakedProperty) f).getAssociation() != null;
				if(isNavigation == associationEnds){
					results.add(f);
				}
			}
		}
		return results;
	}
	protected <E extends IStructuralFeature>E findAttributeFrom(String name,Collection<? extends E> source){
		for(E attribute:source){
			if(name.equals(attribute.getName())){
				return attribute;
			}
		}
		return null;
	}
	public IAttribute findAttribute(String attName){
		return findAttributeFrom(attName, getAllAttributesForOcl(false));
	}
	public IAttribute findClassAttribute(String attName){
		return findAttributeFrom(attName, getAllAttributesForOcl(true));
	}
	public IAssociationEnd findAssociationEnd(String assName){
		return findAttributeFrom(assName, getAllNavigations());
	}
	public IOperation findClassOperation(String opName,List<IClassifier> paramTypes){
		return findOperationInScope(opName, paramTypes, false);
	}
	public IOperation findOperation(String opName,List<IClassifier> paramTypes){
		return findOperationInScope(opName, paramTypes, false);
	}
	public List<IOperation> getClassOperations(){
		return Collections.unmodifiableList(getOperationsInScope(true, true));
	}
	public List<IOperation> getOperations(){
		return Collections.unmodifiableList(getOperationsInScope(false, true));
	}
	protected List<IOperation> getAllOperationsForOcl(boolean classScope){
		return getOperationsInScope(classScope, false);
	}
	private List<IOperation> getOperationsInScope(boolean classScope,boolean ownedOnly){
		List<IOperation> results = new ArrayList<IOperation>();
		List<IOperation> source = new ArrayList<IOperation>(ownedOnly ? ownedOperations : getEffectiveOperations());
		for(IOperation o:source){
			if(o.hasClassScope() == classScope){
				results.add(o);
			}
		}
		return results;
	}
	protected IOperation findOperationInScope(String opName,List<IClassifier> paramTypes,boolean classScope){
		for(IOperation op:getAllOperationsForOcl(classScope)){
			if(op.getName().equals(opName) && Conformance.argumentsConformTo(paramTypes, op)){
				return op;
			}
		}
		return StdlibBasic.getBasicType(IOclLibrary.OclAnyTypeName).findOperation(opName, paramTypes);
	}
	public Collection<INakedOperation> getEffectiveOperations(){
		HashSet<INakedOperation> results = new HashSet<INakedOperation>(ownedOperations);
		for(INakedGeneralization g:this.generalisations){
			if(g.getGeneral() instanceof NakedClassifierImpl){
				results.addAll(((INakedClassifier) g.getGeneral()).getEffectiveOperations());
			}
		}
		return results;
	}
	public IClassifier findCommonSuperType(IClassifier otherType){
		IClassifier result = null;
		if(this.conformsTo(otherType)){
			result = otherType;
		}else if(otherType.conformsTo(this)){
			result = this;
		}
		if(result == null){
			for(INakedGeneralization supr:getNakedGeneralizations()){
				result = supr.getGeneral().findCommonSuperType(otherType);
			}
		}
		return result;
	}
	@Override
	public List<INakedComment> getComments(){
		return comments;
	}
	public void setPowerType(INakedPowerType powerType){
		this.powerType = powerType;
	}
	@Override
	protected boolean isNamedMember(INakedElement e){
		return super.isNamedMember(e) || e instanceof IStructuralFeature || e instanceof IOperation || e instanceof IState;
	}
	@Override
	public PathName getPathName(){
		if(getMappedImplementationType() == null){
			PathName pathName = super.getPathName();
			return pathName;
		}else{
			// TODO refactor to ClassifierMap
			// Still required by OCL2Java generation to resolve
			StringTokenizer st = new StringTokenizer(getMappedImplementationType(), ".");
			PathName result = new PathName();
			while(st.hasMoreTokens()){
				result.addString(st.nextToken());
			}
			return result;
		}
	}
	public CodeGenerationStrategy getCodeGenerationStrategy(){
		if(this.codeGenerationStrategy == null){
			if(getNameSpace() instanceof INakedPackage){
				return ((INakedPackage) getNameSpace()).getCodeGenerationStrategy();
			}else if(getNameSpace() instanceof INakedClassifier){
				return ((INakedClassifier) getNameSpace()).getCodeGenerationStrategy();
			}else{
				// Owner probably null;
				return CodeGenerationStrategy.all;
			}
		}
		return this.codeGenerationStrategy;
	}
	public void setCodeGenerationStrategy(CodeGenerationStrategy codeGenerationStrategy){
		this.codeGenerationStrategy = codeGenerationStrategy;
	}
	public String getMappedImplementationType(){
		return this.mappedImplementationType;
	}
	public void setMappedImplementationType(String mappedImplementationType){
		this.mappedImplementationType = mappedImplementationType;
	}
	public void setDefinitions(List<IOclContext> loopResults){
		this.definitions = new ArrayList<IOclContext>(loopResults);
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasValueForFeature(TagNames.MAPPED_IMPLEMENTATION_TYPE)){
			this.mappedImplementationType = stereotype.getFirstValueFor(TagNames.MAPPED_IMPLEMENTATION_TYPE).stringValue();
			this.codeGenerationStrategy = CodeGenerationStrategy.none;
		}
		if(stereotype.hasValueForFeature(TagNames.CODE_GENERATION_STRATEGY)){
			String s = stereotype.getFirstValueFor(TagNames.CODE_GENERATION_STRATEGY).stringValue();
			this.codeGenerationStrategy = Enum.valueOf(CodeGenerationStrategy.class, s);
		}
	}
	public boolean hasPowerType(){
		return getPowerType() != null;
	}
	public INakedPowerType getPowerType(){
		return this.powerType;
	}
	public boolean isPowerTypeInstance(){
		// TODO move related logic to INakedGeneralization
		for(INakedGeneralization g:generalisations){
			if(g.getPowerTypeLiteral() != null){
				return true;
			}
		}
		return false;
	}
	@Override
	public void removeOwnedElement(INakedElement element){
		super.removeOwnedElement(element);
		if(element instanceof INakedProperty){
			INakedProperty p = (INakedProperty) element;
			this.ownedAttributes.remove(p);
			if(p == endToComposite){
				endToComposite = null;
			}
		}else if(element instanceof INakedOperation){
			INakedOperation oper = (INakedOperation) element;
			this.ownedOperations.remove(oper);
		}else if(element instanceof INakedComment){
			comments.remove(element);
		}else if(element instanceof INakedConstraint){
			ownedRules.remove(element);
		}else if(element instanceof INakedReception){
			this.ownedReceptions.remove(element);
		}else if(element instanceof INakedGeneralization){
			INakedGeneralization generalization = (INakedGeneralization) element;
			this.generalisations.remove(generalization);
			generalization.getGeneral().removeSubClass(this);
		}
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedProperty){
			INakedProperty p = (INakedProperty) element;
			this.ownedAttributes.add(p);
		}else if(element instanceof INakedOperation){
			INakedOperation oper = (INakedOperation) element;
			this.ownedOperations.add(oper);
		}else if(element instanceof INakedComment){
			comments.add((INakedComment) element);
		}else if(element instanceof INakedConstraint){
			ownedRules.remove((INakedConstraint) element);
			ownedRules.add((INakedConstraint) element);
		}else if(element instanceof INakedGeneralization){
			INakedGeneralization generalization = (INakedGeneralization) element;
			this.generalisations.add(generalization);
		}else if(element instanceof INakedReception){
			this.ownedReceptions.add((INakedReception) element);
		}
	}
	public INakedNameSpace getOwner(){
		return (INakedNameSpace) getOwnerElement();
	}
	public INakedClassifier getSupertype(){
		return this.generalisations.isEmpty() ? null : this.generalisations.get(0).getGeneral();
	}
	public boolean hasSupertype(){
		return this.generalisations.size() == 1;
	}
	public void removeSubClass(INakedClassifier specific){
		this.subClasses.remove(specific);
	}
	public void addSubClass(INakedClassifier c){
		subClasses.add(c);
	}
	public Collection<IClassifier> getSubClasses(){
		return new ArrayList<IClassifier>(subClasses);
	}
	public List<IClassifier> getGeneralizations(){
		List<IClassifier> results = new ArrayList<IClassifier>();
		for(INakedGeneralization g:generalisations){
			results.add(g.getGeneral());
		}
		return results;
	}
	public String getStereotype(){
		if(!this.getStereotypes().isEmpty()){
			return this.getStereotypes().iterator().next().getClassifier().getName();
		}else{
			return null;
		}
	}
	public IState findState(PathName path){
		for(IState state:getStates()){
			if(state.getStatePath().equals(path)){
				return state;
			}
		}
		return null;
	}
	public IAssociationClass findAssociationClass(String assName){
		for(INakedProperty f:getEffectiveAttributes()){
			if(f instanceof INakedProperty){
				INakedProperty p = f;
				if(p.getAssociation() != null && p.getAssociation().isClass() && p.getAssociation().getName().equals(assName)){
					return (IAssociationClass) p.getAssociation();
				}
			}
		}
		return null;
	}
	@Override
	public boolean conformsTo(IClassifier c){
		if(this.equals(c)){
			return true;
		}
		for(INakedGeneralization i:getNakedGeneralizations()){
			if(i.getGeneral().conformsTo(c)){
				return true;
			}
		}
		for(IInterface i:getInterfaces()){
			if(i.conformsTo(c)){
				return true;
			}
		}
		return false;
	}
	public boolean getIsAbstract(){
		return isAbstract;
	}
	public boolean isCollectionKind(){
		return false;
	}
	public void removeOclDefAttribute(IAttribute attr){
	}
	public void removeOclDefOperation(IOperation oper){
	}
	public void setIsAbstract(boolean isAbstract){
		this.isAbstract = isAbstract;
	}
	public List<IOclContext> getDefinitions(){
		return this.definitions;
	}
	public void addOclDefAttribute(IAttribute attr){
		this.oclDefAttributes.add(attr);
	}
	public void addOclDefOperation(IOperation oper){
	}
	public List<IState> getStates(){
		return Collections.emptyList();
	}
	// This methods is masked from INakedClassifier. Republished by INakedInterface and INakedBehavioredClassifier
	public Collection<INakedReception> getOwnedReceptions(){
		return ownedReceptions;
	}
	// This methods is masked from INakedClassifier. Republished by INakedInterface and INakedBehavioredClassifier
	public Collection<INakedReception> getEffectiveReceptions(){
		HashSet<INakedReception> results = new HashSet<INakedReception>(ownedReceptions);
		for(INakedGeneralization g:this.generalisations){
			if(g.getGeneral() instanceof NakedClassifierImpl){
				results.addAll(((NakedClassifierImpl) g.getGeneral()).getEffectiveReceptions());
			}
		}
		return results;
	}
	@Override
	@Deprecated
	public List<IInterface> getInterfaces(){
		return Collections.emptyList();
	}
}