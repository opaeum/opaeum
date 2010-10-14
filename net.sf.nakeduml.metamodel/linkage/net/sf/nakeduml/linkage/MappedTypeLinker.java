/**
 * 
 */
package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.CodeGenerationStrategy;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.workspace.AbstractStrategyFactory;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.MappedType;
import nl.klasse.octopus.model.IPrimitiveType;
import nl.klasse.octopus.stdlib.IOclLibrary;

/**
 * Identifies the SimpleTypes that may be required by an application, such as
 * EMailAddresses, CellPhoneNumbers and Dates Also identifies the primitive
 * types from the UMLPrimitiveTypesPackage
 */
@StepDependency(phase = LinkagePhase.class, after = {}, requires = {})
public final class MappedTypeLinker extends AbstractModelElementLinker {
	@VisitBefore(matchSubclasses = true)
	public void setBuiltInType(INakedSimpleType simpleType) {
		String name = simpleType.getName();
		// TODO get rid of this....
		if (simpleType.getNameSpace().getName().equalsIgnoreCase(config.getMappedTypesPackage())) {
			if (name.equalsIgnoreCase(config.getEMailAddressType())) {
				getBuiltInTypes().setEmailAddressType(simpleType);
				updateDefaultType(simpleType, "java.lang.String");
			} else if (name.equalsIgnoreCase(config.getDateType())) {
				getBuiltInTypes().setDateType(simpleType);
				updateDefaultType(simpleType, "java.util.Date");
			} else if (name.equalsIgnoreCase(config.getRealType())) {
				updateDefaultType(simpleType, "java.lang.Double");
				getBuiltInTypes().setRealType(simpleType);
				((INakedPrimitiveType) simpleType).setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary()
						.lookupStandardType(IOclLibrary.RealTypeName));
			}
			// .... up to here
		} else if (simpleType.getNameSpace().getName().equalsIgnoreCase("UMLPrimitiveTypes")
				|| simpleType.getNameSpace().getName().equalsIgnoreCase("uml")) {
			INakedPrimitiveType primitiveType = (INakedPrimitiveType) simpleType;
			boolean isStandardPrimitive = simpleType.getNameSpace().getName().equalsIgnoreCase("UMLPrimitiveTypes");
			if (primitiveType.getName().equals("String")) {
				if (isStandardPrimitive) {
					getBuiltInTypes().setDefaultType(primitiveType);
					getBuiltInTypes().setStringType(primitiveType);
				}
				updateDefaultType(primitiveType, "java.lang.String");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary()
						.lookupStandardType(IOclLibrary.StringTypeName));
			} else if (primitiveType.getName().equalsIgnoreCase("Boolean")) {
				if (isStandardPrimitive) {
					getBuiltInTypes().setBooleanType(primitiveType);
				}
				updateDefaultType(primitiveType, "java.lang.Boolean");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary()
						.lookupStandardType(IOclLibrary.BooleanTypeName));
			} else if (primitiveType.getName().equalsIgnoreCase("Integer")) {
				if (isStandardPrimitive) {
					getBuiltInTypes().setIntegerType(primitiveType);
				}
				updateDefaultType(primitiveType, "java.lang.Integer");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary()
						.lookupStandardType(IOclLibrary.IntegerTypeName));
			} else if (primitiveType.getName().equalsIgnoreCase("UnlimitedNatural")) {
				updateDefaultType(primitiveType, "java.lang.Integer");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary()
						.lookupStandardType(IOclLibrary.IntegerTypeName));
			} else {
				System.out.println("WARNING: No semantics declared for PrimitiveType:" + simpleType.getPathName());
			}
		} else if (simpleType.getNameSpace().getName().equalsIgnoreCase("ecore")) {
			INakedPrimitiveType primitiveType = (INakedPrimitiveType) simpleType;
			if (primitiveType.getName().equals("EDouble")) {
				getBuiltInTypes().setRealType(primitiveType);
				updateDefaultType(primitiveType, "java.lang.Double");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary()
						.lookupStandardType(IOclLibrary.RealTypeName));
			}
		}
		// delete this ....
		if (isInMappedTypesPackage(simpleType)) {
			MappedType mappedType = getBuiltInTypes().getTypeMap().get(simpleType.getName());
			if (mappedType != null) {
				// put under correct path
				// TODO getPathNameInModel may not be necessary anymore
				getBuiltInTypes().getTypeMap().put(super.getPathNameInModel(simpleType).toString(), mappedType);
			}
		}// ... to here

		// Check for steretypes with strategyFactory properties
		if (simpleType.hasStereotype(StereotypeNames.VALUE_TYPE)) {
			INakedInstanceSpecification stereotype = simpleType.getStereotype(StereotypeNames.VALUE_TYPE);
			if (stereotype.hasValueForFeature("mappedImplementationType")) {
				INakedValueSpecification firstValueFor = stereotype.getFirstValueFor("mappedImplementationType");
				String javaType = firstValueFor.stringValue();
				loadStrategies(simpleType, stereotype, javaType);
			}
			if(simpleType.getName().equals("DateTime")){
				getBuiltInTypes().setDateType(simpleType);
			}
		} else if (simpleType.hasStereotype(StereotypeNames.PRIMITIVE_TYPE)) {
			loadStrategies(simpleType, simpleType.getStereotype(StereotypeNames.PRIMITIVE_TYPE), simpleType.getMappedImplementationType());
		}
	}

	public void loadStrategies(INakedSimpleType simpleType, INakedInstanceSpecification stereotype, String javaType) {
		getBuiltInTypes().getTypeMap().put(getPathNameInModel(simpleType).toString(), new MappedType(javaType));
		if (stereotype.hasValueForFeature("strategyFactory")) {
			INakedValueSpecification strategyFactory = stereotype.getFirstValueFor("strategyFactory");
			try {
				Object newInstance = Thread.currentThread().getContextClassLoader().loadClass(strategyFactory.stringValue()).newInstance();
				simpleType.setStrategyFactory((AbstractStrategyFactory) newInstance);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@VisitAfter(matchSubclasses = true)
	public void setCodeGenerationStrategy(INakedClassifier classifier) {
		boolean isMapped = getBuiltInTypes().getTypeMap().containsKey(getPathNameInModel(classifier).toString());
		if (isInMappedTypesPackage(classifier) || isMapped) {
			classifier.setCodeGenerationStrategy(CodeGenerationStrategy.none);
			MappedType mappedType = getBuiltInTypes().getTypeMap().get(super.getPathNameInModel(classifier).toString());
			classifier.setMappedImplementationType(mappedType.getQualifiedJavaName());
		}
	}

	// TODO get rid of this
	private boolean isInMappedTypesPackage(INakedClassifier classifier) {
		return (classifier.getNameSpace().getName().equalsIgnoreCase(config.getMappedTypesPackage()) && getBuiltInTypes().getTypeMap()
				.containsKey(classifier.getName()));
	}

	private void updateDefaultType(INakedSimpleType javaType, String qualifiedJavaReal) {
		javaType.setMappedImplementationType(qualifiedJavaReal);
		javaType.setCodeGenerationStrategy(CodeGenerationStrategy.none);
	}

	@Override
	public void initialize(INakedModelWorkspace workspace, NakedUmlConfig config) {
		super.initialize(workspace, config);
	}
}