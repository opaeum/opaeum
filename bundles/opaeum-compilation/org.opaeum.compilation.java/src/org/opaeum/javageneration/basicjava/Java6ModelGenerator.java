package org.opaeum.javageneration.basicjava;

import java.io.Serializable;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Signal;
import org.opaeum.annotation.NumlMetaInfo;
import org.opaeum.eclipse.CodeGenerationStrategy;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfElementUtil;
import org.opaeum.eclipse.EmfPackageUtil;
import org.opaeum.eclipse.emulated.OperationMessageType;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.StepDependency.StrategyRequirement;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.java.metamodel.annotation.OJEnumLiteral;
import org.opaeum.javageneration.JavaSourceKind;
import org.opaeum.javageneration.JavaTextSource;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.SignalMap;
import org.opaeum.javageneration.util.JpaPropertyStrategy;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.javageneration.util.PojoPropertyStrategy;
import org.opaeum.javageneration.util.PropertyStrategy;
import org.opaeum.runtime.domain.IEnum;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.strategies.BlobStrategyFactory;
import org.opaeum.strategies.DateTimeStrategyFactory;
import org.opaeum.strategies.TextStrategyFactory;
import org.opaeum.textmetamodel.ISourceFolderIdentifier;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.SourceFolderDefinition;
import org.opaeum.textmetamodel.TextFile;

@StepDependency(phase = JavaTransformationPhase.class,requires = {UmlToJavaMapInitialiser.class},after = {UmlToJavaMapInitialiser.class},strategyRequirement = {@StrategyRequirement(strategyContract=PropertyStrategy.class,requires = PojoPropertyStrategy.class)})
public class Java6ModelGenerator extends AbstractStructureVisitor{
	static{
		// Because of eclipse classloading issues
		OpaeumConfig.registerClass(DateTimeStrategyFactory.class);
		OpaeumConfig.registerClass(TextStrategyFactory.class);
		OpaeumConfig.registerClass(BlobStrategyFactory.class);
		StdlibMap.javaRealType.replaceTail("double");
		StdlibMap.javaRealObjectType.replaceTail("Double");
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;// adds too many entries to shared non-synchronized collections;
	}
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass oc,Classifier umlOwner){
		visitClass(umlOwner);
		return false;
	}
	protected boolean ignoreDeletedElements(){
		return false;
	}
	@SuppressWarnings({"unchecked","rawtypes"})
	@VisitAfter(matchSubclasses = true,match = {Interface.class,Enumeration.class})
	public void visitClass(final Classifier c){
		// We do not generate simple data types. They can't participate in
		// two-way associations and should be built-in or pre-implemented
		if(EmfElementUtil.isMarkedForDeletion(c)){
			deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.classifierPathname(c));
			deletePackage(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.packagePathname(c));
		}else if(ojUtil.hasOJClass(c)){
			if(ojUtil.requiresJavaRename(c)){
				deleteClass(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.getOldClassifierPathname(c));
				deletePackage(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.getOldPackagePathname(c));
			}
			OJPathName path = ojUtil.classifierPathname(c);
			OJPackage pack = findOrCreatePackage(path.getHead());
			ClassifierMap classifierMap = ojUtil.buildClassifierMap(c, (CollectionKind) null);
			OJAnnotatedClass myClass;
			if(c instanceof Enumeration){
				myClass = new OJEnum(path.getLast());
				// In case it needs to be sent by jms or serialized as session state
				myClass.addToImplementedInterfaces(new OJPathName(Serializable.class.getName()));
				myClass.addToImplementedInterfaces(new OJPathName(IEnum.class.getName()));
				OJUtil.addMetaInfo(myClass, c);
			}else if(c instanceof Interface){
				myClass = new OJAnnotatedInterface(path.getLast());
				// In case it needs to be sent by jms or serialized as session state
				((OJAnnotatedInterface) myClass).addToSuperInterfaces(new OJPathName(Serializable.class.getName()));
				OJUtil.addMetaInfo(myClass, c);
			}else{
				myClass = new OJAnnotatedClass(path.getLast());
				// Create default constructor for inits
				myClass.getDefaultConstructor();
				// In case it needs to be sent by jms or serialized as session state
				myClass.addToImplementedInterfaces(new OJPathName(Serializable.class.getName()));
				OJUtil.addMetaInfo(myClass, c);
				OJAnnotatedField seri = new OJAnnotatedField("serialVersionUID", new OJPathName("long"));
				seri.setStatic(true);
				seri.setFinal(true);
				seri.setVisibility(OJVisibilityKind.PRIVATE);
				seri.setInitExp(EmfWorkspace.getOpaeumId(c) + "l");
				myClass.addToFields(seri);
			}
			OJAnnotationValue ann = myClass.findAnnotation(new OJPathName(NumlMetaInfo.class.getName()));
			if(ann != null){
				ann.putAttribute("applicationIdentifier", workspace.getIdentifier());
			}
			// TODO find another place
			if(c instanceof Signal){
				SignalMap signalMap = ojUtil.buildSignalMap((Signal) c);
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
			}else if(c instanceof OperationMessageType){
				Operation oper = ((OperationMessageType) c).getOperation();
				if(EmfBehaviorUtil.isResponsibility(oper)){
					myClass.setAbstract(true);
				}
			}
			myClass.addToImports(new OJPathName("java.util.ArrayList"));// Octopus bug
			pack.addToClasses(myClass);
			myClass.setVisibility(classifierMap.javaVisibility());
			myClass.setAbstract(c.isAbstract());
			myClass.setComment(EmfElementUtil.getDocumentation(c));
			Package rootObject = EmfElementFinder.getRootObject(c);
			if(ojUtil.getCodeGenerationStrategy(c) == CodeGenerationStrategy.ABSTRACT_SUPERTYPE_ONLY){
				createTextPath(JavaSourceKind.ABSTRACT_SUPERCLASS, myClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC).setDependsOnVersion(true);
				TextFile impl = createTextPath(JavaSourceKind.CONCRETE_IMPLEMENTATION, myClass, JavaSourceFolderIdentifier.DOMAIN_SRC);
				if(rootObject instanceof Model && EmfPackageUtil.isRegeneratingLibrary(((Model) rootObject))){
					Model m = (Model) rootObject;
					String artifactName = impl.getParent().getRelativePath().substring(impl.getParent().getSourceFolder().getRelativePath().length() + 1) + "/"
							+ impl.getName();
					final String implementationCodeFor = getLibrary().getImplementationCodeFor(m, artifactName);
					impl.setDependsOnVersion(true);
					if(implementationCodeFor != null){
						impl.setTextSource(new JavaStringTextSource(implementationCodeFor));
					}
				}
			}else{
				super.createTextPath(myClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC).setDependsOnVersion(true);
			}
			if(c instanceof Enumeration){
				OJEnum oje = (OJEnum) myClass;
				Enumeration e = (Enumeration) c;
				List<EnumerationLiteral> literals = (List) e.getOwnedLiterals();
				for(EnumerationLiteral l:literals){
					OJEnumLiteral ojLiteral = new OJEnumLiteral(OJUtil.toJavaLiteral(l));
					ojLiteral.setComment(EmfElementUtil.getDocumentation(l));
					applyStereotypesAsAnnotations((l), ojLiteral);
					oje.addToLiterals(ojLiteral);
				}
			}else if(c instanceof DataType){
				// Create default constructor for inits
				myClass.getDefaultConstructor();
				// Signals and StructuredDataTypes
				// TODO implement hashCode and Equals methods
			}else if(c instanceof Behavior){
				Operation specification = (Operation) ((Behavior) c).getSpecification();
				if(specification != null){
					ClassifierMap map = ojUtil.buildClassifierMap(getLibrary().getMessageStructure(specification), (CollectionKind) null);
					myClass.setSuperclass(map.javaTypePath());
				}
			}
			applyStereotypesAsAnnotations((c), myClass);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitPackage(Package p){
		if(ojUtil.requiresJavaRename(p)){
			deletePackage(JavaSourceFolderIdentifier.DOMAIN_GEN_SRC, ojUtil.getOldPackagePathname(p));
		}
		OJPackage currentPack = findOrCreatePackage(ojUtil.packagePathname(p));
		if(EmfElementUtil.getDocumentation(p) != null){
			currentPack.setComment(EmfElementUtil.getDocumentation(p));
		}
		super.applyStereotypesAsAnnotations(p, findOrCreatePackageInfo(currentPack.getPathName(), JavaSourceFolderIdentifier.DOMAIN_GEN_SRC));
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(Operation no){
		if(EmfBehaviorUtil.hasExecutionInstance(no)){
			this.visitClass(getLibrary().getMessageStructure(no));
		}
	}
	@Override
	protected void visitProperty(OJAnnotatedClass c,Classifier owner,PropertyMap buildStructuralFeatureMap){
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
