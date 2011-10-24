package org.opaeum.javageneration.basicjava;

import java.io.Serializable;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.java.metamodel.annotation.OJEnumLiteral;
import org.opaeum.javageneration.JavaSourceKind;
import org.opaeum.javageneration.JavaTextSource;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedDataType;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedMessageStructure;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedPackage;
import org.opaeum.metamodel.core.INakedRootObject;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.strategies.DateTimeStrategyFactory;
import org.opaeum.strategies.TextStrategyFactory;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextFile;
import org.opeum.runtime.domain.IEnum;
import org.opeum.runtime.domain.ISignal;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class Java6ModelGenerator extends AbstractStructureVisitor{
	static{
		// Because of eclipse classloading issues
		OpaeumConfig.registerClass(DateTimeStrategyFactory.class);
		OpaeumConfig.registerClass(TextStrategyFactory.class);
		StdlibMap.javaRealType.replaceTail("double");
		StdlibMap.javaRealObjectType.replaceTail("Double");
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;// adds too many entries to shared non-synchronized collections;
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
		visitClass(umlOwner);
	}
	@SuppressWarnings({
			"unchecked","rawtypes"
	})
	@VisitAfter(matchSubclasses = true,match = {
			INakedInterface.class,INakedEnumeration.class
	})
	public void visitClass(final INakedClassifier c){
		// We do not generate simple data types. They can't participate in
		// two-way associations and should be built-in or pre-implemented
		if(c.isMarkedForDeletion()){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(c.getMappingInfo().getQualifiedJavaName()));
			deletePackage(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(c.getMappingInfo().getQualifiedJavaName().toLowerCase()));
		}else if(OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)){
			if(c.getMappingInfo().requiresJavaRename()){
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(c.getMappingInfo().getOldQualifiedJavaName()));
				deletePackage(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(c.getMappingInfo().getOldQualifiedJavaName().toLowerCase()));
			}
			OJPathName path = OJUtil.packagePathname(c.getNameSpace());
			OJPackage pack = findOrCreatePackage(path);
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
				seri.setInitExp(c.getMappingInfo().getOpaeumId() + "");
				myClass.addToFields(seri);
			}
			// TODO find another place
			if(c instanceof INakedSignal){
				SignalMap signalMap = new SignalMap((INakedSignal) c);
				OJAnnotatedInterface receiver = new OJAnnotatedInterface(signalMap.receiverContractTypePath().getLast());
				pack.addToClasses(receiver);
				OJAnnotatedOperation consumeMethod = new OJAnnotatedOperation(signalMap.eventConsumerMethodName(), new OJPathName("boolean"));
				consumeMethod.addParam("signal", signalMap.javaTypePath());
				receiver.addToOperations(consumeMethod);
				OJAnnotatedOperation receiverMethod = new OJAnnotatedOperation(signalMap.receiveMethodName());
				receiverMethod.addParam("signal", signalMap.javaTypePath());
				receiver.addToOperations(receiverMethod);
				myClass.addToImplementedInterfaces(new OJPathName(ISignal.class.getName()));
				createTextPath(receiver, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			}
			myClass.addToImports(new OJPathName("java.util.ArrayList"));// Octopus bug
			pack.addToClasses(myClass);
			myClass.setVisibility(classifierMap.javaVisibility());
			myClass.setAbstract(c.getIsAbstract());
			myClass.setComment(c.getDocumentation());
			INakedRootObject ro = c.getRootObject();
			INakedRootObject rootObject = ro;
			if(c.getCodeGenerationStrategy().isAbstractSupertypeOnly()){
				createTextPath(JavaSourceKind.ABSTRACT_SUPERCLASS, myClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC).setDependsOnVersion(true);
				TextFile impl = createTextPath(JavaSourceKind.CONCRETE_IMPLEMENTATION, myClass, JavaSourceFolderIdentifier.DOMAIN_SRC);
				if(rootObject instanceof INakedModel && ((INakedModel) rootObject).isRegeneratingLibrary()){
					INakedModel m = (INakedModel) rootObject;
					String artifactName = impl.getParent().getRelativePath().substring(impl.getParent().getSourceFolder().getRelativePath().length() + 1) + "/"
							+ impl.getName();
					final String implementationCodeFor = m.getImplementationCodeFor(artifactName);
					impl.setDependsOnVersion(true);
					if(implementationCodeFor != null){
						impl.setTextSource(new JavaStringTextSource(implementationCodeFor));
					}
				}
			}else{
				super.createTextPath(myClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC).setDependsOnVersion(true);
			}
			if(c instanceof INakedEnumeration){
				OJEnum oje = (OJEnum) myClass;
				INakedEnumeration e = (INakedEnumeration) c;
				List<INakedEnumerationLiteral> literals = (List) e.getLiterals();
				for(INakedEnumerationLiteral l:literals){
					OJEnumLiteral ojLiteral = new OJEnumLiteral( OJUtil.toJavaLiteral(l));
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
	@VisitBefore(matchSubclasses = true)
	public void visitPackage(INakedPackage p){
		if(p.getMappingInfo().requiresJavaRename()){
			deletePackage(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, new OJPathName(p.getMappingInfo().getOldQualifiedJavaName()));
		}
		OJPackage currentPack = findOrCreatePackage(OJUtil.packagePathname(p));
		if(p.getDocumentation() != null){
			currentPack.setComment(p.getDocumentation());
		}
		super.applyStereotypesAsAnnotations(p, findOrCreatePackageInfo(currentPack.getPathName(), JavaSourceFolderIdentifier.DOMAIN_GEN_SRC));
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation no){
		if(BehaviorUtil.hasExecutionInstance(no)){
			INakedMessageStructure message = no.getMessageStructure();
			this.visitClass(message);
		}
	}
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
	}
	private synchronized TextFile createTextPath(JavaSourceKind kind,OJAnnotatedClass c,ISourceFolderIdentifier id){
		SourceFolderDefinition outputRoot = config.getSourceFolderDefinition(id);
		SourceFolder or = getSourceFolder(outputRoot);
		List<String> names = c.getPathName().getHead().getNames();
		names.add(c.getName() + kind.getSuffix() + ".java");
		JavaTextSource jts = new JavaTextSource(kind, c);
		TextFile file = or.findOrCreateTextFile(names, outputRoot.overwriteFiles());
		file.setTextSource(jts);
		this.textFiles.add(file);
		return file;
	}
}
