/**
 * 
 */
package org.opaeum.linkage;

import java.util.HashSet;
import java.util.Set;

import nl.klasse.octopus.model.IPrimitiveType;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.CodeGenerationStrategy;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedInstanceSpecification;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedPrimitiveType;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.statemachines.INakedStateMachine;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.metamodel.workspace.MappedType;
import org.opaeum.runtime.domain.IntrospectionUtil;

/**
 * Identifies the SimpleTypes that may be required by an application, such as EMailAddresses, CellPhoneNumbers and Dates Also identifies the
 * primitive types from the UMLPrimitiveTypesPackage
 */
@StepDependency(phase = LinkagePhase.class,after = {},requires = {})
public final class MappedTypeLinker extends AbstractModelElementLinker{
	static Set<AbstractStrategyFactory> strategyFactories = new HashSet<AbstractStrategyFactory>();
	@VisitBefore
	public void visitStatemachine(INakedStateMachine m){
		if(m.getName().equals("TaskRequest")){
			getLibrary().setTaskRequest(m);
		}else if(m.getName().equals("ProcessRequest")){
			getLibrary().setProcessRequest(m);
		}else if(m.getName().equals("AbstractRequest")){
			getLibrary().setAbstractRequest(m);
			
		}
	}
	@VisitBefore
	public void visitDataType(INakedStructuredDataType m){
		if(m.getName().equals("Duration")){
			getLibrary().setDurationType(m);
		}
	}
	@VisitBefore
	public void visitInterface(INakedInterface m){
		if(m.getName().equals("IBusinessRole")){
			getLibrary().setBusinessRole(m);
		}else if(m.getName().equals("ITaskObject")){
			getLibrary().setTaskObject(m);
		}else if(m.getName().equals("IProcessObject")){
			getLibrary().setProcessObject(m);
		}else if(m.getName().equals("ITaskResponsibilityObject")){
			getLibrary().setTaskResponsibilityObject(m);
		}else if(m.getName().equals("IProcessResponsibilityObject")){
			getLibrary().setProcessResponsibilityObject(m);
		}else if(m.getName().equals("IBusinessCollaboration")){
			getLibrary().setBusinessCollaboration(m);
		}else if(m.getName().equals("IBusiness")){
			getLibrary().setBusiness(m);
		}else if(m.getName().equals("IBusinessActor")){
			getLibrary().setBusinessActor(m);
		}
	}
	@VisitBefore
	public void visitEntity(INakedEntity e){
		if(e.getName().equals("OpaeumPerson")){
			getLibrary().setOpaeumPerson(e);
		}else if(e.getName().equals("BusinessNetwork")){
			getLibrary().setBusinessNetwork(e);
		}
	}
	@SuppressWarnings("deprecation")
	@VisitBefore(matchSubclasses = true)
	public void setBuiltInType(INakedSimpleType simpleType){
		String name = simpleType.getName();
		// TODO get rid of this....
		if(name.equalsIgnoreCase(config.getEMailAddressType())){
			getLibrary().setEmailAddressType(simpleType);
			updateDefaultType(simpleType, "java.lang.String");
		}else if(name.equalsIgnoreCase(config.getDateType())){
			getLibrary().setDateType(simpleType);
			updateDefaultType(simpleType, "java.util.Date");
		}else if(name.equalsIgnoreCase(config.getRealType())){
			updateDefaultType(simpleType, "java.lang.Double");
			getLibrary().setRealType(simpleType);
			((INakedPrimitiveType) simpleType).setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary()
					.lookupStandardType(IOclLibrary.RealTypeName));
		}else if(simpleType.getNameSpace().getName().equalsIgnoreCase("PrimitiveTypes") ||simpleType.getNameSpace().getName().equalsIgnoreCase("UMLPrimitiveTypes")
				|| simpleType.getNameSpace().getName().equalsIgnoreCase("uml")) {
			INakedPrimitiveType primitiveType = (INakedPrimitiveType) simpleType;
			boolean isStandardPrimitive = simpleType.getNameSpace().getName().equalsIgnoreCase("PrimitiveTypes")||simpleType.getNameSpace().getName().equalsIgnoreCase("UMLPrimitiveTypes");
			if(primitiveType.getName().equals("String")){
				if(isStandardPrimitive){
					getLibrary().setDefaultType(primitiveType);
					getLibrary().setStringType(primitiveType);
				}
				updateDefaultType(primitiveType, "java.lang.String");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.StringTypeName));
			}else if(primitiveType.getName().equalsIgnoreCase("Boolean")){
				if(isStandardPrimitive){
					getLibrary().setBooleanType(primitiveType);
				}
				updateDefaultType(primitiveType, "java.lang.Boolean");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName));
			}else if(primitiveType.getName().equalsIgnoreCase("Integer")){
				if(isStandardPrimitive){
					getLibrary().setIntegerType(primitiveType);
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
				getLibrary().setRealType(primitiveType);
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
			}else if(primitiveType.getName().equalsIgnoreCase("float")){
				updateDefaultType(primitiveType, "java.lang.Double");
			}else if(primitiveType.getName().equalsIgnoreCase("double")){
				updateDefaultType(primitiveType, "java.lang.Double");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.RealTypeName));
			}else if(primitiveType.getName().equalsIgnoreCase("long")){
				updateDefaultType(primitiveType, "java.lang.Integer");
				primitiveType.setOclType((IPrimitiveType) workspace.getOclEngine().getOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName));
			}else if(primitiveType.getName().equalsIgnoreCase("int")){
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
				getLibrary().setDateType(simpleType);
			}
		}else if(simpleType.hasStereotype(StereotypeNames.PRIMITIVE_TYPE)){
			loadStrategies(simpleType, simpleType.getStereotype(StereotypeNames.PRIMITIVE_TYPE), simpleType.getMappedImplementationType());
		}
		if(simpleType instanceof INakedPrimitiveType && ((INakedPrimitiveType) simpleType).getOclType() == null){
			System.out.println(simpleType.getPathName() + " has no oclType!!");
		}
	}
	private void loadStrategies(INakedSimpleType simpleType,INakedInstanceSpecification stereotype,String javaType){
		getLibrary().getTypeMap().put(getPathNameInModel(simpleType).toString(), new MappedType(javaType));
		for(AbstractStrategyFactory sf:strategyFactories){
			if(sf.appliesTo(simpleType)){
				simpleType.setStrategyFactory(sf);
			}
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void setCodeGenerationStrategy(INakedClassifier classifier){
		MappedType mappedType = getLibrary().getTypeMap().get(getPathNameInModel(classifier).toString());
		if(mappedType != null){
			classifier.setCodeGenerationStrategy(CodeGenerationStrategy.NO_CODE);
			classifier.setMappedImplementationType(mappedType.getQualifiedJavaName());
		}else{
			// classifier.setCodeGenerationStrategy(CodeGenerationStrategy.all);
		}
	}
	private void updateDefaultType(INakedSimpleType javaType,String qualifiedJavaReal){
		javaType.setMappedImplementationType(qualifiedJavaReal);
		javaType.setCodeGenerationStrategy(CodeGenerationStrategy.NO_CODE);
	}
	@Override
	public void initialize(INakedModelWorkspace workspace,OpaeumConfig config){
		super.initialize(workspace, config);
		for(Class<? extends AbstractStrategyFactory> class1:config.getStrategyFactories()){
			strategyFactories.add(IntrospectionUtil.newInstance(class1));
		}
	}
}