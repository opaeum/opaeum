package net.sf.nakeduml.javageneration.basicjava;

import java.io.Serializable;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaSourceFolderIdentifier;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.linkage.MappedTypeLinker;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedDataType;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.strategies.DateTimeStrategyFactory;
import net.sf.nakeduml.strategies.IdStrategyFactory;
import net.sf.nakeduml.strategies.TextStrategyFactory;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedPackage;
import org.nakeduml.java.metamodel.annotation.OJEnum;
import org.nakeduml.java.metamodel.annotation.OJEnumLiteral;
import org.nakeduml.runtime.domain.AbstractSignal;
import org.nakeduml.runtime.domain.IEnum;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class Java6ModelGenerator extends AbstractStructureVisitor{
	static{
		// Because of eclipse classloading issues
		MappedTypeLinker.registerStrategyFactory(new DateTimeStrategyFactory());
		MappedTypeLinker.registerStrategyFactory(new TextStrategyFactory());
		MappedTypeLinker.registerStrategyFactory(new IdStrategyFactory());
		StdlibMap.javaRealType.replaceTail("double");
		StdlibMap.javaRealObjectType.replaceTail("Double");
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
		visitClass(umlOwner);
	}
	@VisitAfter(matchSubclasses = true,match = {
			INakedInterface.class,INakedEnumeration.class
	})
	public void visitClass(INakedClassifier c){
		// We do not generate simple data types. They can't participate in
		// two-way associations and should be built-in or pre-implemented
		if(OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)){
			if(c.isMarkedForDeletion()){
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(c.getMappingInfo().getOldQualifiedJavaName()));
				deletePackage(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(c.getMappingInfo().getOldQualifiedJavaName().toLowerCase()));
			}else{
				if(c.getMappingInfo().requiresJavaRename()){
					deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(c.getMappingInfo().getOldQualifiedJavaName()));
					deletePackage(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(c.getMappingInfo().getOldQualifiedJavaName().toLowerCase()));
				}
				ClassifierMap classifierMap = new NakedClassifierMap(c);
				OJAnnotatedClass myClass;
				if(c instanceof INakedEnumeration){
					myClass = new OJEnum(c.getName());
					// In case it needs to be sent by jms or serialized as session state
					myClass.addToImplementedInterfaces(new OJPathName(Serializable.class.getName()));
					myClass.addToImplementedInterfaces(new OJPathName(IEnum.class.getName()));
					OJUtil.addMetaInfo(myClass, c);
				}else if(c instanceof INakedInterface){
					myClass = new OJAnnotatedInterface(c.getName());
					// In case it needs to be sent by jms or serialized as session state
					((OJAnnotatedInterface) myClass).addToSuperInterfaces(new OJPathName(Serializable.class.getName()));
					OJUtil.addMetaInfo(myClass, c);
				}else{
					myClass = new OJAnnotatedClass(c.getName());
					// Create default constructor for inits
					myClass.getDefaultConstructor();
					// In case it needs to be sent by jms or serialized as session state
					myClass.addToImplementedInterfaces(new OJPathName(Serializable.class.getName()));
					OJUtil.addMetaInfo(myClass, c);
					OJAnnotatedField seri = new OJAnnotatedField("serialVersionUID", new OJPathName("long"));
					seri.setStatic(true);
					seri.setFinal(true);
					seri.setVisibility(OJVisibilityKind.PRIVATE);
					seri.setInitExp(c.getMappingInfo().getNakedUmlId() + "");
					myClass.addToFields(seri);
				}
				// TODO find another place
				if(c instanceof INakedSignal){
					myClass.setSuperclass(new OJPathName(AbstractSignal.class.getName()));
				}
				myClass.setVisibility(classifierMap.javaVisibility());
				myClass.setAbstract(c.getIsAbstract());
				myClass.setComment(c.getDocumentation());
				OJPathName path = OJUtil.packagePathname(c.getNameSpace());
				OJPackage pack = findOrCreatePackage(path);
				pack.addToClasses(myClass);
				super.createTextPath(myClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
				if(c instanceof INakedEnumeration){
					OJEnum oje = (OJEnum) myClass;
					INakedEnumeration e = (INakedEnumeration) c;
					List<INakedEnumerationLiteral> literals = (List) e.getLiterals();
					for(INakedEnumerationLiteral l:literals){
						OJEnumLiteral ojLiteral = new OJEnumLiteral(l.getName().toUpperCase());
						ojLiteral.setComment(l.getDocumentation());
						applyStereotypesAsAnnotations((l), ojLiteral);
						oje.addToLiterals(ojLiteral);
					}
				}else if(c instanceof INakedDataType){
					// Create default constructor for inits
					myClass.getDefaultConstructor();
					// Signals and StructuredDataTypes
					// TODO implement hashCode and Equals methods
				}else if(c instanceof INakedBehavior){
					INakedOperation specification = ((INakedBehavior) c).getSpecification();
					if(specification != null){
						NakedClassifierMap map = new NakedClassifierMap(specification.getMessageStructure());
						myClass.setSuperclass(map.javaTypePath());
					}
				}
				applyStereotypesAsAnnotations((c), myClass);
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitPackage(INakedPackage p){
		if(p.getMappingInfo().requiresJavaRename()){
			deletePackage(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(p.getMappingInfo().getOldQualifiedJavaName()));
		}
		OJAnnotatedPackage currentPack = findOrCreatePackage(OJUtil.packagePathname(p));
		currentPack.setName(p.getName().toLowerCase());
		if(p.getDocumentation() != null){
			currentPack.setComment(p.getDocumentation());
		}
		super.applyStereotypesAsAnnotations(p, currentPack);
		super.createTextPathIfRequired(currentPack, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation no){
		if(no.shouldEmulateClass() || BehaviorUtil.hasMethodsWithStructure(no)){
			INakedMessageStructure message = no.getMessageStructure();
			this.visitClass(message);
			if(no.isLongRunning()){
				NakedOperationMap map = new NakedOperationMap(no);
				OJAnnotatedInterface listener = new OJAnnotatedInterface(map.callbackListener());
				OJPackage pack = findOrCreatePackage(map.callbackListenerPath().getHead());
				listener.setMyPackage(pack);
				OJAnnotatedOperation callBackOper = new OJAnnotatedOperation(map.callbackOperName());
				callBackOper.addParam("nodeInstance", new OJPathName("int"));
				callBackOper.addParam("completedOperation", new NakedClassifierMap(message).javaTypePath());
				listener.addToOperations(callBackOper);
				super.createTextPath(listener, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			}
		}
	}
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
	}
}