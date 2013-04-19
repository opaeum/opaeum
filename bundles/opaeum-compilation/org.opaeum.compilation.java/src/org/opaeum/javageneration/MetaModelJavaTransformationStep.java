package org.opaeum.javageneration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.opaeum.feature.ITransformationStep;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.feature.visit.VisitorAdapter;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.OJWhileStatement;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.java.metamodel.annotation.OJEnumLiteral;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.javageneration.util.PojoPropertyStrategy;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.textmetamodel.SourceFolder;
import org.opaeum.textmetamodel.TextFile;
import org.opaeum.textmetamodel.TextOutputNode;
import org.opaeum.textmetamodel.TextWorkspace;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MetaModelJavaTransformationStep extends VisitorAdapter<EModelElement,EPackage> implements ITransformationStep{
	protected TextWorkspace textWorkspace;
	protected Set<TextOutputNode> textFiles;
	private EPackage workspace;
	private OJWorkspace javaModel;
	private String projectName;
	private OJPathName prefix;
	private OJAnnotatedClass instantiator;
	private OJUtil ojUtil=new OJUtil();
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
		pkg.addToClasses(itf);
		createTextPath(itf);
		OJAnnotatedOperation getByName = new OJAnnotatedOperation("getByName", itf.getPathName());
		itf.addToOperations(getByName);
		getByName.addParam("name", "String");
		getByName.initializeResultVariable("null");
		getByName.setStatic(true);
		EList<EEnumLiteral> eLiterals = e.getELiterals();
		for(EEnumLiteral el:eLiterals){
			OJEnumLiteral ojLit = new OJEnumLiteral(NameConverter.toUnderscoreStyle(el.getName()).toUpperCase());
			itf.addToLiterals(ojLit);
			OJIfStatement ifName = new OJIfStatement("\"" + el.getName() + "\".equals(name)");
			getByName.getBody().addToStatements(ifName);
			ifName.getThenPart().addToStatements("return " + ojLit.getName());
		}
	}
	private String qualifiedName(EPackage e){
		if(e.getESuperPackage() == null){
			return "";
		}else{
			return qualifiedName(e.getESuperPackage()) + e.getNsPrefix() + ":";
		}
	}
	private String qualifiedName(EClassifier e){
		return qualifiedName(e.getEPackage()) + e.getName();
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClassifier(EClass e){
		OJPackage pkg = javaModel.findOrCreatePackage(pathName(e.getEPackage()));
		OJAnnotatedInterface itf = new OJAnnotatedInterface(e.getName());
		OJPathName eObject = new OJPathName("org.opaeum.ecore.EObject");
		itf.addToSuperInterfaces(eObject);
		pkg.addToClasses(itf);
		ojUtil.addPersistentProperty(itf, "uid", new OJPathName("String"), false);
		createTextPath(itf);
		doXmlDeserialization(e, itf);
		if(!e.isInterface()){
			OJAnnotatedClass cls = new OJAnnotatedClass(e.getName() + "Impl");
			pkg.addToClasses(cls);
			createTextPath(cls);
			if(!e.isAbstract()){
				instantiator.getDefaultConstructor().getBody().addToStatements("classes.put(\"" + qualifiedName(e) + "\"," + cls.getPathName() + ".class)");
			}
			cls.addToImports(instantiator.getPathName());
			doXmlDeserialization(e, cls);
			for(EClass eClass:e.getESuperTypes()){
				itf.addToSuperInterfaces(pathName(eClass));
				if(eClass.getEPackage().getName().equalsIgnoreCase("ecore")){
					cls.setSuperclass(pathName(eClass.getEPackage()).append(eClass.getName() + "Impl"));
				}
			}
			if(cls.getSuperclass() == null){
				cls.setSuperclass(new OJPathName("org.opaeum.ecore.EObjectImpl"));
			}
			cls.addToImplementedInterfaces(itf.getPathName());
			for(EStructuralFeature ea1:e.getEAllStructuralFeatures()){
				if(!ea1.getEContainingClass().getEPackage().getName().equalsIgnoreCase("ecore")){
					addProperty(cls, ea1);
				}
			}
			EList<EOperation> eAllOperations = e.getEAllOperations();
			for(EOperation eOperation:eAllOperations){
				if(!eOperation.getEContainingClass().getEPackage().getName().equalsIgnoreCase("ecore")){
					addOperation(cls, eOperation);
				}
			}
		}
		for(EStructuralFeature ea:e.getEStructuralFeatures()){
			addProperty(itf, ea);
		}
		EList<EOperation> eAllOperations = e.getEOperations();
		for(EOperation eOperation:eAllOperations){
			addOperation(itf, eOperation);
		}
	}
	protected void doXmlDeserialization(EClass e,OJAnnotatedClass itf2){
		itf2.addToImports("org.opaeum.runtime.domain.EcoreDataTypeParser");
		itf2.addToImports(Environment.class.getName());
		itf2.addToImports(Node.class.getName());
		itf2.addToImports(NodeList.class.getName());
		itf2.addToImports(Element.class.getName());
		this.buildBuildTreeFromXml(itf2, e);
		this.buildPopulateReferencesFromXml(itf2, e);
	}
	protected void addProperty(OJAnnotatedClass itf2,EStructuralFeature ea){
		boolean many = ea.isMany();
		EClassifier eType = ea.getEType();
		OJPathName pathName = pathName(many, eType);
		OJAnnotatedField fld = ojUtil.addPersistentProperty(itf2, ea.getName(), pathName, !(itf2 instanceof OJAnnotatedInterface));
		if(fld != null && ea.isMany()){
			itf2.addToImports("java.util.ArrayList");
			fld.setInitExp("new ArrayList<" + ea.getEType().getName() + ">()");
		}
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
		if(cls.findOperation(eOperation.getName(), Collections.<OJPathName>emptyList()) == null
				&& !(eOperation.getName().equals("hashCode") || eOperation.getName().equals("equals"))){
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
	private boolean isPrimitive(EClassifier e){
		if(e instanceof EDataType && !(e instanceof EEnum) && e.getInstanceClassName() != null){
			if(e.getInstanceClassName().indexOf('.') == -1){
				return true;
			}else{
				try{
					Class.forName(e.getInstanceClassName());
					return true;
				}catch(ClassNotFoundException ex){
					// Not a standard JDK class
				}
			}
		}
		return false;
	}
	private OJPathName pathName(EClassifier e){
		if(e instanceof EDataType && !(e instanceof EEnum) && e.getInstanceClassName() != null){
			if(e.getInstanceClassName().indexOf('.') == -1){
				// primitive
				return new OJPathName(e.getInstanceClassName());
			}
			try{
				return new OJPathName(Class.forName(e.getInstanceClassName()).getName());
			}catch(ClassNotFoundException ex){
				// Not a standard JDK class
			}
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
		ojUtil.initialise(null, new PojoPropertyStrategy());
		textFiles = new HashSet<TextOutputNode>();
		this.textWorkspace = textWorkspace;
		this.javaModel = javaMode;
		this.projectName = projectName;
		this.prefix = new OJPathName(prefix);
		this.instantiator = new OJAnnotatedClass(NameConverter.capitalize(workspace.getName() + "Instantiator"));
		OJPackage rootPkg = javaModel.findOrCreatePackage(new OJPathName(prefix + "." + projectName.toLowerCase()));
		rootPkg.addToClasses(instantiator);
		instantiator.setSuperclass(new OJPathName("org.opaeum.runtime.domain.AbstractEcoreInstantiator"));
		OJAnnotatedField inst = new OJAnnotatedField("INSTANCE", instantiator.getPathName());
		inst.setInitExp("new " + instantiator.getName() + "()");
		instantiator.addToFields(inst);
		inst.setVisibility(OJVisibilityKind.PUBLIC);
		inst.setStatic(true);
		instantiator.getDefaultConstructor().getBody()
				.addToStatements("classes.put(\"EStringToStringMapEntry\",org.opaeum.ecore.EStringToStringMapEntryImpl.class)");
		instantiator.getDefaultConstructor().getBody().addToStatements("classes.put(\"EAnnotation\",org.opaeum.ecore.EAnnotationImpl.class)");
		createTextPath(instantiator);
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
	protected void buildBuildTreeFromXml(OJAnnotatedClass owner,EClass e){
		OJOperation toString = new OJAnnotatedOperation("buildTreeFromXml");
		addParameters(toString);
		owner.addToOperations(toString);
		if(!(owner instanceof OJAnnotatedInterface)){
			for(EStructuralFeature f:e.getEAllStructuralFeatures()){
				if(isXmlAttribute(f)){
					populateAttribute(owner, toString, (EAttribute) f);
				}
			}
			OJWhileStatement whileItems = iterateThroughProperties(toString);
			for(EStructuralFeature f:e.getEAllStructuralFeatures()){
				if(isXmlSubElement(f)){
					owner.addToImports(pathName(f.getEType()));
					populatePropertyValues(f, whileItems);
				}
			}
		}
	}
	private boolean isXmlAttribute(EStructuralFeature f){
		return f instanceof EAttribute;
	}
	protected void buildPopulateReferencesFromXml(OJAnnotatedClass owner,EClass e){
		OJOperation toString = new OJAnnotatedOperation("populateReferencesFromXml");
		owner.addToOperations(toString);
		addParameters(toString);
		if(!(owner instanceof OJAnnotatedInterface)){
			OJWhileStatement whileItems = iterateThroughProperties(toString);
			for(EStructuralFeature f:e.getEAllStructuralFeatures()){
				if(isXmlReference(f)){
					owner.addToImports(this.pathName(f.getEType()));
					OJBlock then = iterateThroughPropertyValues(f, whileItems);
					String modifier = setter(f);
					String retriever = f.isMany() ? "getReferences" : "getReference";
					then.addToStatements(modifier + "((" + this.pathName(f.isMany(), f.getEType()) + ")this.eResource().getResourceSet()." + retriever
							+ "((Element)currentPropertyNode))");
				}else if(isXmlSubElement(f)){
					OJBlock then = iterateThroughPropertyValues(f, whileItems);
					then.addToStatements("((" + this.pathName(f.getEType())
							+ ")this.eResource().getElement((Element)currentPropertyNode)).populateReferencesFromXml((Element)currentPropertyNode)");
				}
			}
		}
	}
	protected String writer(EStructuralFeature f){
		return f.isMany() ? "get" + NameConverter.capitalize(f.getName()) + "().add" : setter(f);
	}
	private boolean isXmlSubElement(EStructuralFeature f){
		return f instanceof EReference && ((EReference) f).isContainment();
	}
	private boolean isXmlReference(EStructuralFeature f){
		return f instanceof EReference && !((EReference) f).isContainment() && !((EReference) f).isContainer();
	}
	protected void addParameters(OJOperation toString){
		toString.addParam("xml", new OJPathName(Element.class.getName()));
	}
	protected OJWhileStatement iterateThroughProperties(OJOperation toString){
		OJBlock block = new OJBlock();
		OJAnnotatedField propertyNodes = new OJAnnotatedField("propertyNodes", new OJPathName(NodeList.class.getName()));
		block.addToLocals(propertyNodes);
		propertyNodes.setInitExp("xml.getChildNodes()");
		OJAnnotatedField i = new OJAnnotatedField("i", new OJPathName("int"));
		block.addToLocals(i);
		i.setInitExp("0");
		OJWhileStatement whileItems = new OJWhileStatement();
		block.addToStatements(whileItems);
		whileItems.setCondition("i<propertyNodes.getLength()");
		OJAnnotatedField currentPropertyNode = new OJAnnotatedField("currentPropertyNode", new OJPathName(Node.class.getName()));
		whileItems.getBody().addToLocals(currentPropertyNode);
		currentPropertyNode.setInitExp("propertyNodes.item(i++)");
		toString.getBody().addToStatements(block);
		return whileItems;
	}
	protected void populateAttribute(OJAnnotatedClass owner,OJOperation toString,EAttribute f){
		owner.addToImports(pathName(f.getEType()));
		if(isPrimitive(f.getEType())){
			OJIfStatement ifNotNull = new OJIfStatement("xml.getAttribute(\"" + f.getName() + "\").length()>0");
			toString.getBody().addToStatements(ifNotNull);
			ifNotNull.getThenPart().addToStatements(
					setter(f) + "(EcoreDataTypeParser.getInstance().parse" + f.getEType().getName() + "(xml.getAttribute(\"" + f.getName() + "\")))");
		}else if(f.getEType() instanceof EEnum){
			OJIfStatement ifNull = new OJIfStatement("xml.getAttribute(\"" + f.getName() + "\").length()==0");
			toString.getBody().addToStatements(ifNull);
			ifNull.getThenPart().addToStatements(setter(f) + "(" + f.getEType().getName() + ".values()[0])");// !!!!!NB ECORE STANDARD!!!!
			ifNull.setElsePart(new OJBlock());
			ifNull.getElsePart().addToStatements(setter(f) + "(" + f.getEType().getName() + ".getByName(" + "xml.getAttribute(\"" + f.getName() + "\")))");
		}else{
			throw new IllegalStateException("Unknown DataType:" + f.getEType().getName());
		}
	}
	protected String setter(EStructuralFeature f){
		return "set" + NameConverter.capitalize(f.getName());
	}
	private void populatePropertyValues(EStructuralFeature f,OJWhileStatement w){
		OJBlock thenPart = iterateThroughPropertyValues(f, w);
		OJAnnotatedField typeString = new OJAnnotatedField("typeString", new OJPathName("String"));
		thenPart.addToLocals(typeString);
		typeString.setInitExp("((Element)currentPropertyNode).getAttribute(\"xsi:type\")");
		OJIfStatement ifTypeNull = new OJIfStatement("typeString==null ||typeString.trim().length()==0");
		thenPart.addToStatements(ifTypeNull);
		ifTypeNull.getThenPart().addToStatements("typeString=\"" + qualifiedName(f.getEType()) + "\"");
		OJAnnotatedField curVal = new OJAnnotatedField("curVal", pathName(f.getEType()));
		thenPart.addToLocals(curVal);
		thenPart.addToStatements("curVal=" + instantiator.getName() + ".INSTANCE.newInstance(typeString)");
		thenPart.addToStatements("this." + writer(f) + "(curVal)");
		thenPart.addToStatements("curVal.init(this,eResource(),(Element)currentPropertyNode)");
		thenPart.addToStatements("curVal.buildTreeFromXml((Element)currentPropertyNode)");
		if(f instanceof EReference){
			EReference eOpposite = ((EReference) f).getEOpposite();
			if(eOpposite != null){
				thenPart.addToStatements("curVal." + setter(eOpposite) + "(this)");
			}
		}
	}
	protected OJBlock iterateThroughPropertyValues(EStructuralFeature f,OJWhileStatement w){
		OJIfStatement ifInstance = new OJIfStatement("currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals(\"" + f.getName() + "\")");
		// OJAnnotatedField propertyValueNodes = new OJAnnotatedField("propertyValueNodes", new OJPathName(NodeList.class.getName()));
		// ifInstance.getThenPart().addToLocals(propertyValueNodes);
		// propertyValueNodes.setInitExp("currentPropertyNode.getChildNodes()");
		// OJAnnotatedField j = new OJAnnotatedField("j", new OJPathName("int"));
		// ifInstance.getThenPart().addToLocals(j);
		// j.setInitExp("0");
		// OJWhileStatement whileValueItems = new OJWhileStatement();
		// ifInstance.getThenPart().addToStatements(whileValueItems);
		// whileValueItems.setCondition("j<propertyValueNodes.getLength()");
		// OJAnnotatedField currentPropertyNode = new OJAnnotatedField("currentPropertyNode", new OJPathName(Node.class.getName()));
		// whileValueItems.getBody().addToLocals(currentPropertyNode);
		// currentPropertyNode.setInitExp("propertyValueNodes.item(j++)");
		// OJIfStatement ifInstance2 = new OJIfStatement("currentPropertyNode instanceof Element");
		// whileValueItems.getBody().addToStatements(ifInstance2);
		w.getBody().addToStatements(ifInstance);
		OJBlock thenPart = ifInstance.getThenPart();
		return thenPart;
	}
}
