package org.opaeum.metamodel.core.internal.emulated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IAssociationClass;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IImportedElement;
import nl.klasse.octopus.model.IInterface;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.IState;
import nl.klasse.octopus.model.VisibilityKind;
import nl.klasse.octopus.oclengine.IOclContext;

import org.opaeum.feature.MappingInfo;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedEvent;
import org.opaeum.metamodel.commonbehaviors.INakedReception;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.core.CodeGenerationStrategy;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedConstraint;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedGeneralization;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedInterfaceRealization;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedPackage;
import org.opaeum.metamodel.core.INakedPowerType;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedClassifierImpl;
import org.opaeum.metamodel.core.internal.NakedGeneralizationImpl;
import org.opaeum.metamodel.core.internal.NakedInterfaceRealizationImpl;
import org.opaeum.name.NameConverter;

public abstract class EmulatedCompositionMessageStructure extends EmulatingElement implements INakedMessageStructure,INakedBehavioredClassifier{
	@Override
	public boolean isImported(IClassifier cls){
		return owner.isImported(cls);
	}
	@Override
	public Collection<? extends INakedEvent> getEventsInScopeForClassAsContext(){
		return Collections.emptySet();
	}
	@Override
	public void reorderSequences(){
	}
	public INakedProperty getNameProperty(){
		return null;
	}
	private static final long serialVersionUID = -3198245957575601442L;
	protected INakedElement element;
	protected INakedClassifier owner;
	private Collection<INakedInterfaceRealization> interfaceRealizations = new HashSet<INakedInterfaceRealization>();
	private List<INakedGeneralization> generalizations = new ArrayList<INakedGeneralization>();
	protected List<INakedProperty> attributes = new ArrayList<INakedProperty>();
	protected MappingInfo mappingInfo;
	private String implementationCode;
	private INakedProperty endToComposite;
	protected String id;
	protected String name;
	protected EmulatedCompositionMessageStructure(INakedClassifier owner,INakedElement element){
		super(element);
		this.name = NameConverter.toJavaVariableName(element.getName());
		this.element = element;
		this.owner = owner;
		this.mappingInfo = element.getMappingInfo().getCopy();
		this.id = element.getId() + getClass().getSimpleName();
		this.mappingInfo.setIdInModel(id);
	}
	@Override
	public boolean isPersistent(){
		return true;
	}
	public final List<INakedProperty> getOwnedAttributes(){
		return attributes;
	}
	public void addInterface(INakedInterface in){
		INakedInterfaceRealization t = new NakedInterfaceRealizationImpl();
		t.setOwnerElement(this);
		t.setContract(in);
		t.setMappingInfo(in.getMappingInfo().getCopy());
		t.getMappingInfo().setIdInModel(getId() + in.getId());
		t.initialize(getId() + in.getId(), "artificalRealization", false);
		addInterfaceRealization(t);
	}
	protected void addSuperclass(INakedClassifier in){
		generalizations.clear();// Only one superclass
		NakedGeneralizationImpl t = new NakedGeneralizationImpl();
		t.setOwnerElement(this);
		t.setGeneral(in);
		t.setMappingInfo(in.getMappingInfo().getCopy());
		t.getMappingInfo().setIdInModel(getId() + in.getId());
		t.initialize(getId() + in.getId(), "artificalGeneralization", false);
		generalizations.add(t);
		addOwnedElement(t);
	}
	public List<IClassifier> getGeneralizations(){
		List<IClassifier> results = new ArrayList<IClassifier>();
		for(INakedGeneralization g:generalizations){
			results.add(g.getGeneral());
		}
		return results;
	}
	public INakedClassifier getSupertype(){
		return this.generalizations.isEmpty() ? null : this.generalizations.get(0).getGeneral();
	}
	@Override
	public INakedClassifier getNestingClassifier(){
		return owner;
	}
	// TODO make configurable
	public CodeGenerationStrategy getCodeGenerationStrategy(){
		return CodeGenerationStrategy.ALL;
	}
	@Override
	public String getName(){
		return this.name;
	}
	public void addOclDefAttribute(IAttribute attr){
	}
	public void addOclDefOperation(IOperation oper){
	}
	public boolean conformsTo(IClassifier c){
		return c.equals(this);
	}
	public IAssociationClass findAssociationClass(String name){
		return owner.findAssociationClass(name);
	}
	public IAssociationEnd findAssociationEnd(String assName){
		return owner.findAssociationEnd(assName);
	}
	public IAttribute findAttribute(String attName){
		for(INakedProperty a:getEffectiveAttributes()){
			if(a.getName().equals(attName)){
				return a;
			}
		}
		return null;
	}
	public IAttribute findClassAttribute(String attName){
		return owner.findClassAttribute(attName);
	}
	public IOperation findClassOperation(String opName,List<IClassifier> parameters){
		return owner.findClassOperation(opName, parameters);
	}
	public IClassifier findCommonSuperType(IClassifier otherType){
		return null;
	}
	public IOperation findOperation(String opName,List<IClassifier> parameters){
		return owner.findOperation(opName, parameters);
	}
	public IState findState(PathName path){
		return owner.findState(path);
	}
	public List<IAttribute> getAllAttributes(){
		return getAttributes();
	}
	public List<IAssociationEnd> getAllNavigations(){
		return Collections.emptyList();
	}
	public List<IAttribute> getAttributes(){
		return new ArrayList<IAttribute>(getOwnedAttributes());
	}
	public List<IAttribute> getClassAttributes(){
		return Collections.emptyList();
	}
	public List<IOperation> getClassOperations(){
		return Collections.emptyList();
	}
	public List<IInterface> getInterfaces(){
		List<IInterface> result = new ArrayList<IInterface>();
		for(INakedInterfaceRealization r:this.interfaceRealizations){
			result.add(r.getContract());
		}
		return result;
	}
	public boolean getIsAbstract(){
		return false;
	}
	public List<IAssociationEnd> getNavigations(){
		return Collections.emptyList();
	}
	public List<IOperation> getOperations(){
		return Collections.emptyList();
	}
	public List<IState> getStates(){
		return owner.getStates();
	}
	public String getStereotype(){
		return null;
	}
	public Collection<IClassifier> getSubClasses(){
		return Collections.emptySet();
	}
	public boolean isCollectionKind(){
		return false;
	}
	public void removeOclDefAttribute(IAttribute attr){
	}
	public void removeOclDefOperation(IOperation oper){
	}
	public void setIsAbstract(boolean isAbstract){
	}
	public void setVisibility(VisibilityKind public1){
	}
	public VisibilityKind getVisibility(){
		return VisibilityKind.PUBLIC;
	}
	public IModelElement lookup(PathName path){
		return owner.lookup(path);
	}
	public IOperation lookupOperation(PathName path,List<IClassifier> types){
		return owner.lookupOperation(path, types);
	}
	@SuppressWarnings("deprecation")
	@Override
	public PathName getPathName(){
		PathName p = owner.getPathName();
		p.addString(getName());
		return p;
	}
	public void addInterfaceRealization(INakedInterfaceRealization in){
		interfaceRealizations.add(in);
		addOwnedElement(in);
	}
	public void addInvariant(IOclContext string){
	}
	public void addSubClass(INakedClassifier c){
	}
	public INakedProperty findEffectiveAttribute(String name){
		return null;
	}
	public List<INakedOperation> getEffectiveOperations(){
		ArrayList<INakedOperation> result = new ArrayList<INakedOperation>();
		for(INakedInterfaceRealization ir:getInterfaceRealizations()){
			result.addAll(ir.getContract().getEffectiveOperations());
		}
		return result;
	}
	public INakedPackage getAsPackage(){
		return null;
	}
	public List<INakedProperty> getEffectiveAttributes(){
		ArrayList<INakedProperty> result = new ArrayList<INakedProperty>();
		for(INakedInterfaceRealization ir:getInterfaceRealizations()){
			NakedClassifierImpl.addEffectiveAttributes(result, ir.getContract().getEffectiveAttributes());
		}
		for(INakedGeneralization g:getNakedGeneralizations()){
			NakedClassifierImpl.addEffectiveAttributes(result, g.getGeneral().getEffectiveAttributes());
		}
		NakedClassifierImpl.addEffectiveAttributes(result, getOwnedAttributes());
		return result;
	}
	public Collection<IImportedElement> getImports(){
		return owner.getImports();
	}
	public String getMappedImplementationType(){
		return null;
	}
	public Collection<INakedClassifier> getNestedClassifiers(){
		return Collections.emptySet();
	}
	public Collection<INakedBehavior> getOwnedBehaviors(){
		return Collections.emptySet();
	}
	public INakedPowerType getPowerType(){
		return null;
	}
	public boolean hasPowerType(){
		return false;
	}
	public boolean hasSubtypes(){
		return false;
	}
	public boolean hasSupertype(){
		return false;
	}
	public boolean isPowerTypeInstance(){
		return false;
	}
	public void removeSubClass(INakedClassifier specific){
	}
	public void setCodeGenerationStrategy(CodeGenerationStrategy none){
	}
	public Collection<INakedGeneralization> getNakedGeneralizations(){
		return this.generalizations;
	}
	public void setMappedImplementationType(String class1){
	}
	public void setPowerType(INakedPowerType impl){
	}
	@Override
	public INakedNameSpace getOwner(){
		return (INakedNameSpace) owner;
	}
	@Override
	public Collection<INakedInterfaceRealization> getInterfaceRealizations(){
		return this.interfaceRealizations;
	}
	@Override
	public Collection<IClassifier> getClassifiers(){
		return Collections.emptySet();
	}
	@Override
	public Collection<IPackage> getSubpackages(){
		return Collections.emptySet();
	}
	@Override
	public void setOwnedRules(List<INakedConstraint> loopResults){
	}
	public INakedNameSpace getParent(){
		return getNameSpace();
	}
	@Override
	public INakedBehavior getClassifierBehavior(){
		return null;
	}
	@Override
	public void setClassifierBehavior(INakedBehavior behavior){
	}
	@Override
	public Collection<INakedReception> getOwnedReceptions(){
		return Collections.emptySet();
	}
	@Override
	public Collection<? extends INakedReception> getEffectiveReceptions(){
		ArrayList<INakedReception> result = new ArrayList<INakedReception>();
		for(INakedInterfaceRealization ir:getInterfaceRealizations()){
			result.addAll(ir.getContract().getEffectiveReceptions());
		}
		return result;
	}
	@Override
	public Collection<? extends INakedBehavior> getEffectiveBehaviors(){
		return Collections.emptySet();
	}
	@Override
	public boolean hasReceptionOrTriggerFor(INakedSignal signal){
		return false;
	}
	@Override
	public Set<INakedProperty> getDirectlyImplementedAttributes(){
		return new HashSet<INakedProperty>(getEffectiveAttributes());
	}
	@Override
	public List<? extends INakedConstraint> getOwnedRules(){
		return Collections.emptyList();
	}
	@Override
	public Set<INakedOperation> getDirectlyImplementedOperations(){
		return new HashSet<INakedOperation>(getEffectiveOperations());
	}
	@Override
	public Collection<? extends INakedReception> getDirectlyImplementedReceptions(){
		return Collections.emptySet();
	}
	@Override
	public String getId(){
		return id;
	}
	@Override
	public MappingInfo getMappingInfo(){
		return mappingInfo;
	}
	@Override
	public boolean hasComposite(){
		return true;
	}
	@Override
	public INakedProperty getEndToComposite(){
		return this.endToComposite;
	}
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof AbstractEmulatedProperty){
			this.attributes.add((INakedProperty) element);
		}
	}
	@Override
	public void removeOwnedElement(INakedElement element,boolean recursively){
		super.removeOwnedElement(element, recursively);
		if(element instanceof AbstractEmulatedProperty){
			attributes.remove(element);
		}
	}
	@Override
	public void setEndToComposite(INakedProperty artificialProperty){
		this.endToComposite = artificialProperty;
		addOwnedElement(artificialProperty);
	}
	public void reinitialize(){
		for(INakedProperty a:this.attributes){
			getOwnedElementMap().remove(a.getId());// Leave other owned elements there
		}
		this.attributes.clear();
		if(endToComposite != null){
			endToComposite.getNakedBaseType().removeOwnedElement(endToComposite.getOtherEnd(), true);
			endToComposite = null;
		}
	}
	@Override
	public Collection<INakedElement> getOwnedElements(){
		HashSet<INakedElement> hashSet = new HashSet<INakedElement>(super.getOwnedElements());
		hashSet.addAll(getOwnedAttributes());
		hashSet.addAll(getNakedGeneralizations());
		hashSet.addAll(getInterfaceRealizations());
		if(endToComposite != null){
			hashSet.add(endToComposite);
		}
		return hashSet;
	}
	@Override
	public void removeObsoleteArtificialProperties(){
	}
	public String getImplementationCode(){
		return implementationCode;
	}
	public void setImplementationCode(String implementationCode){
		this.implementationCode = implementationCode;
	}
	@Override
	public boolean isFact(){
		for(INakedProperty p:getEffectiveAttributes()){
			if(p.isMeasure()){
				for(INakedProperty d:getOwnedAttributes()){
					if(d.isDimension()){
						return true;
					}
				}
			}
		}
		return false;
	}
}
