package net.sf.nakeduml.metamodel.core.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.core.CodeGenerationStrategy;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.internal.types.AttributeImpl;
import nl.klasse.octopus.oclengine.IOclContext;

/**
 * @author Ampie Barnard
 */
public class NakedPackageImpl extends NakedNameSpaceImpl implements INakedPackage {
	private static final long serialVersionUID = 4916604812046576007L;
	public static final String META_CLASS = "package";
	private String mappedImplementationPackage;
	private CodeGenerationStrategy codeGenerationStrategy = CodeGenerationStrategy.all;
	private boolean isRootPackage;
	private List<INakedPackage> subpackages = new ArrayList<INakedPackage>();
	private boolean isSchema;
	private boolean isLinked;

	public NakedPackageImpl() {
		super();
	}

	/**
	 * Override to allow packages at arbitrary levels to function as top level
	 * packages. Also allows classifiers to function as packages, as is the case
	 * with UML2 components that can contain nested packages Used primarily by
	 * Octopus
	 */
	public INakedNameSpace getParent() {
		if (isRootPackage()) {
			return null;
		} else {
			return (INakedNameSpace) getOwnerElement();
		}
	}
	public void setLinked(boolean isLinked) {
		this.isLinked = isLinked;
	}
	public boolean isLinked() {
		return isLinked;
	}

	/**
	 * Override to allow packages at arbitrary levels to function as top level
	 * packages
	 */
	@Override
	public PathName getPathName() {
		if (isRootPackage()) {
			return new PathName(getName());
		} else {
			return super.getPathName();
		}
	}

	@Override
	public void addStereotype(INakedInstanceSpecification stereotype) {
		if (stereotype.hasValueForFeature(TagNames.MAPPED_IMPLEMENTATION_PACKAGE)) {
			this.mappedImplementationPackage = stereotype.getFirstValueFor(TagNames.MAPPED_IMPLEMENTATION_PACKAGE).stringValue();
		}
		if (stereotype.hasValueForFeature(TagNames.IS_SCHEMA)) {
			this.isSchema= stereotype.getFirstValueFor(TagNames.IS_SCHEMA).booleanValue();
		}
		if (stereotype.hasValueForFeature(TagNames.CODE_GENERATION_STRATEGY)) {
			String s = stereotype.getFirstValueFor(TagNames.CODE_GENERATION_STRATEGY).stringValue();
			this.codeGenerationStrategy = Enum.valueOf(CodeGenerationStrategy.class, s);
		}
		if (stereotype.hasValueForFeature(TagNames.IS_ROOT_PACKAGE)) {
			this.isRootPackage = stereotype.getFirstValueFor(TagNames.IS_ROOT_PACKAGE).booleanValue();
		}
		super.addStereotype(stereotype);
	}
	public boolean isSchema(){
		return isSchema;
	}
	public CodeGenerationStrategy getCodeGenerationStrategy() {
		if (this.codeGenerationStrategy == null) {
			if (getOwnerElement() instanceof INakedPackage && !isRootPackage()) {
				return ((INakedPackage) getOwnerElement()).getCodeGenerationStrategy();
			} else {
				return CodeGenerationStrategy.all;
			}
		} else {
			return this.codeGenerationStrategy;
		}
	}

	public String getMappedImplementationPackage() {
		return this.mappedImplementationPackage;
	}

	public void setMappedImplementationPackage(String generatedName) {
		this.mappedImplementationPackage = generatedName;
	}

	public void setCodeGenerationStrategy(CodeGenerationStrategy codeGenerationStrategy) {
		this.codeGenerationStrategy = codeGenerationStrategy;
	}

	@Override
	public void addOwnedElement(INakedElement element) {
		super.addOwnedElement(element);
		element.setOwnerElement(this);
		if (element instanceof INakedPackage) {
			INakedPackage thePackage = (INakedPackage) element;
			this.subpackages.add(thePackage);
		}
	}

	@Override
	public String getMetaClass() {
		return META_CLASS;
	}

	public boolean isRootPackage() {
		return this.isRootPackage;
	}

	public IPackage getRoot() {
		return isRootPackage() || getParent() == null ? this : super.getNakedRoot();
	}

	public Collection getSubpackages() {
		return this.subpackages;
	}


	/**
	 * Method mergeExpressions.
	 * 
	 * @param contexts
	 *            : a list of IOclContext instances
	 */
	// Move to linkage
	public void mergeExpressions(List<IOclContext> contexts) {
		for (IOclContext cont : contexts) {
			if (cont != null) {
				IClassifier owner = cont.getOwningModelElement().getSurroundingClassifier();
				IModelElement elem = cont.getOwningModelElement().getModelElement();
				if (elem != null) {
					OclUsageType type = cont.getType();
					if (type == OclUsageType.DEF) {
						// TODO addd operation or attribute
						// ((INakedClassifier) elem).addDefinition(cont);
					} else if (type == OclUsageType.INV) {
						// TODO add artificial constraint to ownedRules
						// ((INakedClassifier) elem).addInvariant(cont);
					} else {
						if (type == OclUsageType.PRE) {
							((INakedOperation) elem).addPreCondition(cont);
						} else if (type == OclUsageType.POST) {
							((INakedOperation) elem).addPostCondition(cont);
						} else if (type == OclUsageType.BODY) {
							// TODO set bodyExpression with artificial
							// constraint
							// ((INakedOperation) elem).setBodyExpression(cont);
						} else if (type == OclUsageType.DERIVE) {
							if (elem instanceof INakedProperty) {
								// TODO wrap in InstanceSpecification and set
								// init
								// ((INakedProperty)
								// elem).setDerivationRule(cont);
							}
						} else if (type == OclUsageType.INIT) {
							if (elem instanceof INakedProperty)
								((AttributeImpl) elem).setInit(cont);
						}
					}
				}
			}
		}
	}
}
