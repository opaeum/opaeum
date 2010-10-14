package net.sf.nakeduml.metamodel.core.internal.emulated;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.CodeGenerationStrategy;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedGeneralization;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedPowerType;
import net.sf.nakeduml.metamodel.core.INakedProperty;
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

public abstract class MessageStructureImpl extends EmulatingElement implements INakedMessageStructure {
	INakedElement element;
	INakedClassifier owner;

	protected MessageStructureImpl(INakedClassifier owner, INakedElement element) {
		super(element);
		this.element = element;
		this.owner = owner;
	}

	public abstract List<INakedProperty> getOwnedAttributes();

	// TODO make configurable
	public CodeGenerationStrategy getCodeGenerationStrategy() {
		return CodeGenerationStrategy.all;
	}

	@Override
	public String getName() {
		return element.getName();// Don't capp unless we override the
									// qualifiedJavaName too
	}

	public void addOclDefAttribute(IAttribute attr) {
	}

	public void addOclDefOperation(IOperation oper) {
	}

	public boolean conformsTo(IClassifier c) {
		return c.equals(this);
	}

	public IAssociationClass findAssociationClass(String name) {
		return owner.findAssociationClass(name);
	}

	public IAssociationEnd findAssociationEnd(String assName) {
		return owner.findAssociationEnd(assName);
	}

	public IAttribute findAttribute(String attName) {
		for (INakedProperty a : getOwnedAttributes()) {
			if (a.getName().equals(attName)) {
				return a;
			}
		}
		return owner.findAttribute(attName);
	}

	public IAttribute findClassAttribute(String attName) {
		return owner.findClassAttribute(attName);
	}

	public IOperation findClassOperation(String opName, List<IClassifier> parameters) {
		return owner.findClassOperation(opName, parameters);
	}

	public IClassifier findCommonSuperType(IClassifier otherType) {
		return null;
	}

	public IOperation findOperation(String opName, List<IClassifier> parameters) {
		return owner.findOperation(opName, parameters);
	}

	public IState findState(PathName path) {
		return owner.findState(path);
	}

	public List<IAttribute> getAllAttributes() {
		return getAttributes();
	}

	public List<IAssociationEnd> getAllNavigations() {
		return Collections.emptyList();
	}

	public List<IAttribute> getAttributes() {
		return new ArrayList<IAttribute>(getOwnedAttributes());
	}

	public List<IAttribute> getClassAttributes() {
		return Collections.emptyList();
	}

	public List<IOperation> getClassOperations() {
		return Collections.emptyList();
	}

	public List<IClassifier> getGeneralizations() {
		return Collections.emptyList();
	}

	public List<IInterface> getInterfaces() {
		return Collections.emptyList();
	}

	public boolean getIsAbstract() {
		return false;
	}

	public List<IAssociationEnd> getNavigations() {
		return Collections.emptyList();
	}

	public List<IOperation> getOperations() {
		return Collections.emptyList();
	}

	public List<IState> getStates() {
		return owner.getStates();
	}

	public String getStereotype() {
		return null;
	}

	public Collection<IClassifier> getSubClasses() {
		return Collections.emptySet();
	}

	public boolean isCollectionKind() {
		return false;
	}

	public void removeOclDefAttribute(IAttribute attr) {
	}

	public void removeOclDefOperation(IOperation oper) {
	}

	public void setIsAbstract(boolean isAbstract) {
	}

	public void setVisibility(VisibilityKind public1) {
	}

	public VisibilityKind getVisibility() {
		return VisibilityKind.PUBLIC;
	}

	public IModelElement lookup(PathName path) {
		return owner.lookup(path);
	}

	public IOperation lookupOperation(PathName path, List<IClassifier> types) {
		return owner.lookupOperation(path, types);
	}

	@Override
	public PathName getPathName() {
		PathName p = owner.getPathName();
		p.addString(getName());
		return p;
	}

	public void addInterface(INakedInterfaceRealization in) {
	}

	public void addInvariant(IOclContext string) {
	}

	public void addSubClass(INakedClassifier c) {
	}

	public INakedProperty findEffectiveAttribute(String name) {
		return null;
	}

	public List<INakedOperation> getEffectiveOperations() {
		return null;
	}

	public INakedPackage getAsPackage() {
		return null;
	}


	public List<INakedProperty> getEffectiveAttributes() {
		return getOwnedAttributes();
	}

	public Collection<IImportedElement> getImports() {
		return Collections.emptySet();
	}

	public String getMappedImplementationType() {
		return null;
	}

	public Collection<INakedClassifier> getNestedClassifiers() {
		return Collections.emptySet();
	}

	public Collection<INakedBehavior> getOwnedBehaviors() {
		return Collections.emptySet();
	}

	public INakedPowerType getPowerType() {
		return null;
	}

	public INakedClassifier getSupertype() {
		return null;
	}

	public boolean hasPowerType() {
		return false;
	}

	public boolean hasSubtypes() {
		return false;
	}

	public boolean hasSupertype() {
		return false;
	}

	public boolean isPowerTypeInstance() {
		return false;
	}

	public void removeSubClass(INakedClassifier specific) {
	}

	public void setCodeGenerationStrategy(CodeGenerationStrategy none) {
	}

	public Collection<INakedGeneralization> getNakedGeneralizations() {
		return Collections.emptySet();
	}

	public void setMappedImplementationType(String class1) {
	}

	public void setPowerType(INakedPowerType impl) {
	}


	@Override
	public INakedNameSpace getOwner() {
		return (INakedNameSpace) owner;
	}

	@Override
	public Collection<INakedInterfaceRealization> getInterfaceRealizations() {
		return Collections.emptySet();
	}

	@Override
	public Collection<IClassifier> getClassifiers() {
		return Collections.emptySet();
	}

	@Override
	public Collection<IPackage> getSubpackages() {
		return Collections.emptySet();
	}

	@Override
	public void setDefinitions(List<IOclContext> loopResults) {
	}

	@Override
	public void setOwnedRules(List<INakedConstraint> loopResults) {
	}

	public INakedNameSpace getParent(){
		return getNameSpace();
	}
}