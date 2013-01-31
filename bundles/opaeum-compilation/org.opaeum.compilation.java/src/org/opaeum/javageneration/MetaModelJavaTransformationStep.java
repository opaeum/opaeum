package org.opaeum.javageneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.feature.visit.VisitorAdapter;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.java.metamodel.annotation.OJEnumLiteral;
import org.opaeum.javageneration.util.OJUtill;
import org.opaeum.name.NameConverter;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;

public class MetaModelJavaTransformationStep extends VisitorAdapter<EModelElement,EPackage> implements ITransformationStep{
	protected TextWorkspace textWorkspace;
	protected Set<TextOutputNode> textFiles;
	private EPackage workspace;
	private OJWorkspace javaModel;
	private String projectName;
	private OJPathName prefix;
	static private Map<String,OJPathName> mapping = new HashMap<String,OJPathName>();
	static{
		mapping.put("String", new OJPathName("String"));
		mapping.put("Integer", new OJPathName("Integer"));
		mapping.put("int", new OJPathName("Integer"));
		mapping.put("Double", new OJPathName("Double"));
		mapping.put("double", new OJPathName("Double"));
		mapping.put("float", new OJPathName("Double"));
		mapping.put("float", new OJPathName("Double"));
		mapping.put("Boolean", new OJPathName("Boolean"));
		mapping.put("boolean", new OJPathName("Boolean"));
	}
	@Override
	public Collection<? extends EModelElement> getChildren(EModelElement parent){
		Collection<EModelElement> result = new ArrayList<EModelElement>();
		EList<EObject> eContents = parent.eContents();
		for(EObject eObject:eContents){
			if(eObject instanceof EModelElement){
				result.add((EModelElement) eObject);
			}
		}
		return result;
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;
	}
	@VisitBefore(matchSubclasses = true)
	public void visitEnum(EEnum e){
		OJPackage pkg = javaModel.findOrCreatePackage(pathName(e.getEPackage()));
		OJEnum itf = new OJEnum(e.getName());
		EList<EEnumLiteral> eLiterals = e.getELiterals();
		for(EEnumLiteral el:eLiterals){
			itf.addToLiterals(new OJEnumLiteral(NameConverter.toUnderscoreStyle(el.getName()).toUpperCase()));
		}
		pkg.addToClasses(itf);
		createTextPath(itf);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(EClass e){
		OJPackage pkg = javaModel.findOrCreatePackage(pathName(e.getEPackage()));
		OJAnnotatedInterface itf = new OJAnnotatedInterface(e.getName());
		OJAnnotatedClass itf2 = itf;
		OJPathName eObject = new OJPathName("org.opaeum.ecore.EObject");
		itf.addToSuperInterfaces(eObject);
		pkg.addToClasses(itf2);
		createTextPath(itf2);
		if(!e.isInterface()){
			OJAnnotatedClass cls = new OJAnnotatedClass(e.getName() + "Impl");
			pkg.addToClasses(cls);
			createTextPath(cls);
			for(EClass eClass:e.getESuperTypes()){
				itf.addToSuperInterfaces(pathName(eClass));
				if(eClass.getEPackage().getName().equalsIgnoreCase("ecore")){
					cls.setSuperclass(pathName(eClass.getEPackage()).append(eClass.getName() + "Impl"));
				}
			}
			OJAnnotatedOperation eContainer = new OJAnnotatedOperation("eContainer", eObject);
			eContainer.initializeResultVariable("null");
			cls.addToOperations(eContainer);
			cls.addToImplementedInterfaces(itf2.getPathName());
			for(EStructuralFeature ea1:e.getEAllStructuralFeatures()){
				if(!ea1.getEContainingClass().getEPackage().getName().equalsIgnoreCase("ecore")){
					addProperty(cls, ea1);
				}
			}
			EList<EOperation> eAllOperations = e.getEAllOperations();
			for(EOperation eOperation:eAllOperations){
				addOperation(cls, eOperation);
			}
		}
		for(EStructuralFeature ea:e.getEStructuralFeatures()){
			addProperty(itf2, ea);
		}
		EList<EOperation> eAllOperations = e.getEOperations();
		for(EOperation eOperation:eAllOperations){
			addOperation(itf, eOperation);
		}
	}
	protected void addProperty(OJAnnotatedClass itf2,EStructuralFeature ea){
		boolean many = ea.isMany();
		EClassifier eType = ea.getEType();
		OJPathName pathName = pathName(many, eType);
		OJUtill.addPersistentProperty(itf2, ea.getName(), pathName, !(itf2 instanceof OJAnnotatedInterface));
	}
	protected OJPathName pathName(boolean many,EClassifier eType){
		OJPathName pathName = pathName(eType);
		if(many){
			OJPathName list = new OJPathName("java.util.List");
			list.addToElementTypes(pathName);
			pathName = list;
		}
		return pathName;
	}
	protected void addStructuralFeatures(EClass e,OJAnnotatedClass itf){
		for(EStructuralFeature ea:e.getEAllStructuralFeatures()){
			if(!ea.getEContainingClass().getEPackage().getName().equalsIgnoreCase("ecore")){
				addProperty(itf, ea);
			}
		}
		EList<EOperation> eAllOperations = e.getEAllOperations();
		for(EOperation eOperation:eAllOperations){
			addOperation(itf, eOperation);
		}
	}
	protected void addOperation(OJAnnotatedClass cls,EOperation eOperation){
		if(cls.findOperation(eOperation.getName(), Collections.<OJPathName>emptyList()) == null && !(eOperation.getName().equals("hashCode") || eOperation.getName().equals("equals"))){
			OJAnnotatedOperation oper = new OJAnnotatedOperation(eOperation.getName());
			if(eOperation.getEType() != null){
				oper.setReturnType(pathName(eOperation.isMany(), eOperation.getEType()));
				oper.initializeResultVariable("null");
			}
			cls.addToOperations(oper);
			EList<EParameter> eParameters = eOperation.getEParameters();
			for(EParameter eParameter:eParameters){
				oper.addParam(eParameter.getName(), pathName(eParameter.isMany(), eParameter.getEType()));
			}
		}
	}
	private OJPathName pathName(EClassifier e){
		mapping.put("EString", new OJPathName("String"));
		mapping.put("EIntegerObject", new OJPathName("Integer"));
		mapping.put("EInteger", new OJPathName("Integer"));
		mapping.put("EInt", new OJPathName("Integer"));
		mapping.put("EBoolean", new OJPathName("boolean"));
		mapping.put("EBooleanObject", new OJPathName("Boolean"));
		mapping.put("EDoubleObject", new OJPathName("Double"));
		mapping.put("EDouble", new OJPathName("Double"));
		mapping.put("EJavaObject", new OJPathName("Object"));
		mapping.put("EObject", new OJPathName("Object"));
		OJPathName pn = mapping.get(e.getName());
		if(pn != null){
			return pn;
		}
		OJPathName pathName = pathName(e.getEPackage());
		pathName.append(e.getName());
		return pathName;
	}
	private OJPathName pathName(EPackage e){
		if(e.getESuperPackage() == null){
			return prefix.getDeepCopy().append(e.getName());
		}else{
			OJPathName pathName = pathName(e.getESuperPackage());
			pathName.append(e.getName());
			return pathName;
		}
	}
	public void initialize(TextWorkspace textWorkspace,EPackage workspace,OJWorkspace javaMode,String projectName,String prefix){
		this.workspace = workspace;
		textFiles = new HashSet<TextOutputNode>();
		this.textWorkspace = textWorkspace;
		this.javaModel = javaMode;
		this.projectName = projectName;
		this.prefix = new OJPathName(prefix);
	}
	public synchronized TextFile createTextPath(OJClassifier c){
		List<String> names = c.getPathName().getHead().getNames();
		names.add(c.getName() + ".java");
		TextFile file = createTextPath(names);
		JavaTextSource jts = new JavaTextSource(c);
		file.setTextSource(jts);
		return file;
	}
	protected TextFile createTextPath(List<String> names){
		SourceFolder or = textWorkspace.findOrCreateTextProject(projectName).findOrCreateSourceFolder("src", true);
		TextFile file = or.findOrCreateTextFile(names, true);
		this.textFiles.add(file);
		return file;
	}
}
