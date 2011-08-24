/**
 * 
 */
package net.sf.nakeduml.linkage;

import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.CodeGenerationStrategy;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.AbstractStrategyFactory;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.metamodel.workspace.MappedType;
import nl.klasse.octopus.model.IPrimitiveType;
import nl.klasse.octopus.stdlib.IOclLibrary;

/**
 * Identifies the SimpleTypes that may be required by an application, such as EMailAddresses, CellPhoneNumbers and Dates Also identifies the
 * primitive types from the UMLPrimitiveTypesPackage
 */
@StepDependency(phase = LinkagePhase.class,after = {},requires = {})
public final class MappedTypeLinker extends AbstractModelElementLinker{
	static Map<String,AbstractStrategyFactory> strategyFactories = new HashMap<String,AbstractStrategyFactory>();
	public static void registerStrategyFactory(AbstractStrategyFactory a){
		strategyFactories.put(a.getClass().getName(), a);
	}
	@VisitBefore
	public void visitInterface(INakedInterface m){
		if( m.getName().equals("BusinessRole")){
			getBuiltInTypes().setBusinessRole(m);
		}else if(m.getName().equals("TaskObject")){
			getBuiltInTypes().setTaskObject(m);
		}
			
	}
	@VisitBefore(matchSubclasses = true)
	public void setBuiltInType(INakedSimpleType simpleType){
		String name = simpleType.getName();
		// TODO get rid of this....
		if(name.equalsIgnoreCase(config.getEMailAddressType())){
			getBuiltInTypes().setEmailAddressType(simpleType);
			updateDefaultType(simpleType, "java.lang.String");
		}else if(name.equalsIgnoreCase(config.getDateType())){
			getBuiltInTypes().setDateType(simpleType);
			updateDefaultType(simpleType, "java.util.Date");
		}else if(name.equalsIgnoreCase(config.getRealType())){
			updateDefaultType(simpleType, "java.lang.Double");
			getBuiltInTypes().setRealType(simpleType);
			((INakedPrimitiveType) simpleType).setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.RealTypeName));
		}else if(simpleType.getNameSpace().getName().equalsIgnoreCase("UMLPrimitiveTypes") || simpleType.getNameSpace().getName().equalsIgnoreCase("uml")){
			INakedPrimitiveType primitiveType = (INakedPrimitiveType) simpleType;
			boolean isStandardPrimitive = simpleType.getNameSpace().getName().equalsIgnoreCase("UMLPrimitiveTypes");
			if(primitiveType.getName().equals("String")){
				if(isStandardPrimitive){
					getBuiltInTypes().setDefaultType(primitiveType);
					getBuiltInTypes().setStringType(primitiveType);
				}
				updateDefaultType(primitiveType, "java.lang.String");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.StringTypeName));
			}else if(primitiveType.getName().equalsIgnoreCase("Boolean")){
				if(isStandardPrimitive){
					getBuiltInTypes().setBooleanType(primitiveType);
				}
				updateDefaultType(primitiveType, "java.lang.Boolean");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
			}else if(primitiveType.getName().equalsIgnoreCase("Integer")){
				if(isStandardPrimitive){
					getBuiltInTypes().setIntegerType(primitiveType);
				}
				updateDefaultType(primitiveType, "java.lang.Integer");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
			}else if(primitiveType.getName().equalsIgnoreCase("UnlimitedNatural")){
				updateDefaultType(primitiveType, "java.lang.Integer");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
			}else{
				System.out.println("WARNING: No semantics declared for PrimitiveType:" + simpleType.getPathName());
			}
		}else if(simpleType.getNameSpace().getName().equalsIgnoreCase("ecore")){
			INakedPrimitiveType primitiveType = (INakedPrimitiveType) simpleType;
			if(primitiveType.getName().equals("EDouble")){
				getBuiltInTypes().setRealType(primitiveType);
				updateDefaultType(primitiveType, "java.lang.Double");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.RealTypeName));
			}
		}else if(simpleType.getNameSpace().getName().equalsIgnoreCase("javaprimitivetypes")){
			INakedPrimitiveType primitiveType = (INakedPrimitiveType) simpleType;
			if(primitiveType.getName().equals("char")){
				updateDefaultType(primitiveType, "java.lang.String");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.StringTypeName));
			}else if(primitiveType.getName().equalsIgnoreCase("boolean")){
				updateDefaultType(primitiveType, "java.lang.Boolean");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
			}else if(primitiveType.getName().equalsIgnoreCase("double")){
				updateDefaultType(primitiveType, "java.lang.Double");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.RealTypeName));
			}else if(primitiveType.getName().equalsIgnoreCase("long")){
				updateDefaultType(primitiveType, "java.lang.Integer");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
			}else if(primitiveType.getName().equalsIgnoreCase("short")){
				updateDefaultType(primitiveType, "java.lang.Integer");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
			}else if(primitiveType.getName().equalsIgnoreCase("byte")){
				updateDefaultType(primitiveType, "java.lang.Integer");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
			}else{
				System.out.println("WARNING: No semantics declared for PrimitiveType:" + simpleType.getPathName());
			}
		}
		// Check for steretypes with strategyFactory properties
		if(simpleType.hasStereotype(StereotypeNames.VALUE_TYPE)){
			INakedInstanceSpecification stereotype = simpleType.getStereotype(StereotypeNames.VALUE_TYPE);
			if(stereotype.hasValueForFeature("mappedImplementationType")){
				INakedValueSpecification firstValueFor = stereotype.getFirstValueFor("mappedImplementationType");
				String javaType = firstValueFor.stringValue();
				loadStrategies(simpleType, stereotype, javaType);
			}
			if(simpleType.getName().equals("DateTime")){
				getBuiltInTypes().setDateType(simpleType);
			}
		}else if(simpleType.hasStereotype(StereotypeNames.PRIMITIVE_TYPE)){
			loadStrategies(simpleType, simpleType.getStereotype(StereotypeNames.PRIMITIVE_TYPE), simpleType.getMappedImplementationType());
		}
		if(simpleType instanceof INakedPrimitiveType && ((INakedPrimitiveType) simpleType).getOclType() == null){
			System.out.println(simpleType.getPathName() + " has no oclType!!");
		}
	}
	private void loadStrategies(INakedSimpleType simpleType,INakedInstanceSpecification stereotype,String javaType){
		getBuiltInTypes().getTypeMap().put(getPathNameInModel(simpleType).toString(), new MappedType(javaType));
		if(stereotype.hasValueForFeature("strategyFactory")){
			INakedValueSpecification strategyFactory = stereotype.getFirstValueFor("strategyFactory");
			String factoryName = strategyFactory.stringValue();
			try{
				Object newInstance = Thread.currentThread().getContextClassLoader().loadClass(factoryName).newInstance();
				simpleType.setStrategyFactory((AbstractStrategyFactory) newInstance);
			}catch(ClassNotFoundException e){
				if(strategyFactories.containsKey(factoryName)){
					simpleType.setStrategyFactory(strategyFactories.get(factoryName));
				}else{
					
//					throw new RuntimeException(e);
				}
			}catch(InstantiationException e){
				throw new RuntimeException(e);
			}catch(IllegalAccessException e){
				throw new RuntimeException(e);
			}
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void setCodeGenerationStrategy(INakedClassifier classifier){
		boolean isMapped = getBuiltInTypes().getTypeMap().containsKey(getPathNameInModel(classifier).toString());
		if(isMapped){
			classifier.setCodeGenerationStrategy(CodeGenerationStrategy.none);
			MappedType mappedType = getBuiltInTypes().getTypeMap().get(super.getPathNameInModel(classifier).toString());
			classifier.setMappedImplementationType(mappedType.getQualifiedJavaName());
		}else{
			// classifier.setCodeGenerationStrategy(CodeGenerationStrategy.all);
		}
	}
	private void updateDefaultType(INakedSimpleType javaType,String qualifiedJavaReal){
		javaType.setMappedImplementationType(qualifiedJavaReal);
		javaType.setCodeGenerationStrategy(CodeGenerationStrategy.none);
	}
	@Override
	public void initialize(INakedModelWorkspace workspace,NakedUmlConfig config){
		super.initialize(workspace, config);
	}
}