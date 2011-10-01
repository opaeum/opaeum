package org.opeum.javageneration.basicjava;

import java.io.Serializable;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

import org.opeum.feature.OpeumConfig;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJPackage;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.OJVisibilityKind;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.java.metamodel.annotation.OJEnum;
import org.opeum.java.metamodel.annotation.OJEnumLiteral;
import org.opeum.javageneration.JavaSourceKind;
import org.opeum.javageneration.JavaTextSource;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.maps.NakedClassifierMap;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.maps.SignalMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.metamodel.commonbehaviors.INakedBehavior;
import org.opeum.metamodel.commonbehaviors.INakedSignal;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedDataType;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedEnumerationLiteral;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedMessageStructure;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedPackage;
import org.opeum.metamodel.core.INakedSimpleType;
import org.opeum.metamodel.models.INakedModel;
import org.opeum.runtime.domain.IEnum;
import org.opeum.runtime.domain.ISignal;
import org.opeum.strategies.DateTimeStrategyFactory;
import org.opeum.strategies.TextStrategyFactory;
import org.opeum.textmetamodel.ISourceFolderIdentifier;
import org.opeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opeum.textmetamodel.SourceFolder;
import org.opeum.textmetamodel.SourceFolderDefinition;
import org.opeum.textmetamodel.TextFile;
import org.opeum.textmetamodel.TextSource;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class Java6ModelGenerator extends AbstractStructureVisitor{
	static{
		// Because of eclipse classloading issues
		OpeumConfig.registerClass(DateTimeStrategyFactory.class);
		OpeumConfig.registerClass(TextStrategyFactory.class);
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
				seri.setInitExp(c.getMappingInfo().getOpeumId() + "");
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
			if(c.getCodeGenerationStrategy().isAbstractSupertypeOnly()){
				createTextPath(JavaSourceKind.ABSTRACT_SUPERCLASS, myClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
				TextFile impl = createTextPath(JavaSourceKind.CONCRETE_IMPLEMENTATION, myClass, JavaSourceFolderIdentifier.DOMAIN_SRC);
				if(c.getRootObject() instanceof INakedModel && ((INakedModel) c.getRootObject()).isRegeneratingLibrary()){
					INakedModel m = (INakedModel) c.getRootObject();
					String artifactName = impl.getParent().getRelativePath().substring(impl.getParent().getSourceFolder().getRelativePath().length()+1) + "/" + impl.getName();
					final String implementationCodeFor = m.getImplementationCodeFor(artifactName);
					if(implementationCodeFor != null){
						impl.setTextSource(new TextSource(){
							@Override
							public char[] toCharArray(){
								return implementationCodeFor.toCharArray();
							}
							@Override
							public boolean hasContent(){
								return true;
							}
						});
					}
				}
			}else{
				super.createTextPath(myClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
			}
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
		TextFile file = or.findOrCreateTextFile(names, jts, outputRoot.overwriteFiles());
		file.setTextSource(jts);
		this.textFiles.add(file);
		return file;
	}
}
