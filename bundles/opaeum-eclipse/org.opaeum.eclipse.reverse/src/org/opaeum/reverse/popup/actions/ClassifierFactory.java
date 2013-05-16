package org.opaeum.reverse.popup.actions;

import java.beans.IntrospectionException;
import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.dom.IAnnotationBinding;
import org.eclipse.jdt.core.dom.IMemberValuePairBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.VisibilityKind;
import org.eclipse.uml2.uml.resource.UMLResource;

public class ClassifierFactory{
	private Map<String,org.eclipse.uml2.uml.Classifier> classMap = new HashMap<String,org.eclipse.uml2.uml.Classifier>();
	private Stereotype helperStereotype;
	private Package model;
	private Properties mappedTypes = new Properties();
	private Model umlMetaModel;
	private Class operationMetaClass;
	private Class propertyMetaClass;
	private Class classMetaClass;
	private Set<Classifier> newClassifiers = new HashSet<Classifier>();
	public ClassifierFactory(Package model){
		// TODO got through all libraries and read previously mapped types as
		// well, and load them into classMap
		this.model = model;
		this.helperStereotype = findInProfiles(model, "Helper");
		importPrimitiveTypes(model);
		if(model instanceof Profile){
			umlMetaModel = (Model) model.eResource().getResourceSet().getResource(URI.createURI(UMLResource.UML_METAMODEL_URI), true)
					.getContents().get(0);
			classMetaClass = (org.eclipse.uml2.uml.Class) umlMetaModel.getOwnedType("Class");
			propertyMetaClass = (org.eclipse.uml2.uml.Class) umlMetaModel.getOwnedType("Property");
			operationMetaClass = (org.eclipse.uml2.uml.Class) umlMetaModel.getOwnedType("Operation");
			Profile profile = (Profile) model;
			profile.getMetaclassReference(classMetaClass);
			profile.getMetaclassReference(operationMetaClass);
			profile.getMetaclassReference(propertyMetaClass);
			profile.getMetamodelReference(umlMetaModel, true);
		}
	}
	public Classifier getClassifierFor(ITypeBinding returnType){
		try{
			//TODO cleanup sometimes forces creates, sometimes returns null
			if(returnType == null){
				return null;
			}
			Classifier classifier=null;
			if(isCollection(returnType) && returnType.getTypeArguments().length == 1){
				returnType = returnType.getTypeArguments()[0];
			}else{
				while(returnType.isArray()){
					returnType = returnType.getComponentType();
				}
			}
			if(classMap.containsKey(returnType.getQualifiedName())){
				classifier = classMap.get(returnType.getQualifiedName());
			}else{
				if(returnType.getTypeArguments().length > 0){
					if(returnType.getBinaryName().equals("java.lang.Class")){
						for(ITypeBinding arg:returnType.getTypeArguments()){
							if(arg.isInterface()){
								return (Classifier) umlMetaModel.getOwnedType("Interface");
							}
						}
						return (Classifier) umlMetaModel.getOwnedType("Class");
					}
				}
				Namespace pkg = getPackageFor(returnType, model);
				if(pkg != null){
					classifier = (Classifier) pkg.getOwnedMember(returnType.getName());
					if(model instanceof Model && returnType.isAnnotation()){
						return classifier;// no lazy creation
					}else if(classifier == null){
						classifier = createClassifier(returnType);
						mappedTypes.put(classifier.getQualifiedName(), returnType.getQualifiedName());
					}
					classMap.put(returnType.getQualifiedName(), classifier);
				}
			}
			return classifier;
		}catch(IntrospectionException e){
			throw new RuntimeException(e);
		}
	}
	public boolean isCollection(ITypeBinding returnType){
		return (returnType.isParameterizedType() && returnType.getQualifiedName().contains("java.util.Collection")) || isSet(returnType)
				|| isList(returnType);
	}
	private Classifier createClassifier(ITypeBinding returnType) throws IntrospectionException{
		Classifier classifier = null;
		if(returnType.isAnnotation()){
			classifier = createStereotype(returnType);
		}else if(returnType.isEnum()){
			classifier = createEnum(returnType);
		}else if(returnType.isInterface()){
			classifier = createInterface(returnType);
		}else{
			IAnnotationBinding[] anns = returnType.getAnnotations();
			IAnnotationBinding entity = Annotations.getAnnotation("javax.persistence.Entity", anns);
			if(entity == null){
				IAnnotationBinding name = Annotations.getAnnotation("org.jboss.seam.annotations.Name", anns);
				if(name == null){
					String intfName = "java.io.Serializable";
					if(implementsInterface(returnType, intfName)){
						classifier = createDataType(returnType);
					}else{
						classifier = createHelper(returnType);
					}
				}else{
					classifier = createSeamComponent(returnType, name);
				}
			}else{
				classifier = createEntity(returnType);
			}
		}
		if(!(returnType.getSuperclass() == null || returnType.getSuperclass().getQualifiedName().equals("java.lang.Object")
				|| returnType.isEnum() || returnType.isAnnotation())){
			classifier.createGeneralization(getClassifierFor(returnType.getSuperclass()));
		}
		newClassifiers.add(classifier);
		return classifier;
	}
	private Classifier createStereotype(ITypeBinding type){
		Stereotype st = (Stereotype) createType(type, UMLPackage.eINSTANCE.getStereotype());
		// TODO move this section to createSTereotype
		for(IAnnotationBinding ab:type.getAnnotations()){
			if(ab.getAnnotationType().getBinaryName().equals(Target.class.getName())){
				for(IMemberValuePairBinding mvp:ab.getDeclaredMemberValuePairs()){
					Object val = mvp.getValue();
					if(mvp.getName().equals("value") && val instanceof Object[]){
						for(Object o:(Object[]) val){
							IVariableBinding elementType = (IVariableBinding) o;
							if(elementType.getName().equals("TYPE") && !st.getAllExtendedMetaclasses().contains(classMetaClass)){
								st.createExtension(classMetaClass, false);
							}else if(elementType.getName().equals("FIELD") && !st.getAllExtendedMetaclasses().contains(propertyMetaClass)){
								st.createExtension(propertyMetaClass, false);
							}else if(elementType.getName().equals("METHOD") && !st.getAllExtendedMetaclasses().contains(operationMetaClass)){
								st.createExtension(operationMetaClass, false);
							}
						}
						Type pt = umlMetaModel.getOwnedType("PrimitiveType");
						Type dt = umlMetaModel.getOwnedType("DataType");
						if(!(st.getAllExtendedMetaclasses().contains(pt) || st.getAllExtendedMetaclasses().contains(dt))){
							st.createExtension((Class) dt, false);
							st.createExtension((Class) pt, false);
						}
					}
				}
			}
		}
		return st;
	}
	public boolean implementsInterface(ITypeBinding returnType,String intfName){
		ITypeBinding[] interfaces = returnType.getInterfaces();
		for(ITypeBinding inf:interfaces){
			if(inf.getQualifiedName().equals(intfName)){
				return true;
			}
		}
		return false;
	}
	private org.eclipse.uml2.uml.DataType createDataType(ITypeBinding returnType){
		// TODO make a good guess as to whether it is a ValueType or not by
		// looking for the absence setters
		org.eclipse.uml2.uml.DataType umlDataType = (DataType) createType(returnType, UMLPackage.eINSTANCE.getDataType());
		return umlDataType;
	}
	private org.eclipse.uml2.uml.Class createHelper(ITypeBinding returnType){
		org.eclipse.uml2.uml.Class umlClass = (Class) createType(returnType, UMLPackage.eINSTANCE.getClass_());
		umlClass.applyStereotype(helperStereotype);
		return umlClass;
	}
	private org.eclipse.uml2.uml.Class createSeamComponent(ITypeBinding returnType,IAnnotationBinding name){
		org.eclipse.uml2.uml.Class umlClass = createHelper(returnType);
		umlClass.setValue(helperStereotype, "name", Annotations.getAnnotationAttribute(name, "value").getValue());
		return umlClass;
	}
	private org.eclipse.uml2.uml.Class createEntity(ITypeBinding returnType){
		org.eclipse.uml2.uml.Class umlClass = (Class) createType(returnType, UMLPackage.eINSTANCE.getClass_());
		ITypeBinding[] interfaces = returnType.getInterfaces();
		for(ITypeBinding intfce:interfaces){
			if(!intfce.getQualifiedName().startsWith("org.opaeum")){
				umlClass.createInterfaceRealization(intfce.getName(), (Interface) getClassifierFor(intfce));
			}
		}
		return umlClass;
	}
	private Interface createInterface(ITypeBinding returnType){
		Interface umlInterface = (Interface) createType(returnType, UMLPackage.eINSTANCE.getInterface());
		ITypeBinding[] interfaces = returnType.getInterfaces();
		for(ITypeBinding intfce:interfaces){
			if(!intfce.getQualifiedName().startsWith("org.opaeum")){
				umlInterface.createGeneralization(getClassifierFor(intfce));
			}
		}
		IAnnotationBinding ann = Annotations.getAnnotation("org.opaeum.annotation.HelperInterface", returnType.getAnnotations());
		if(ann != null){
			umlInterface.applyStereotype(helperStereotype);
			umlInterface.setValue(helperStereotype, "name", Annotations.getAnnotationAttribute(ann, "name").getValue());
		}
		return umlInterface;
	}
	protected Type createType(ITypeBinding returnType,EClass eTYpe){
		Namespace ns = getPackageFor(returnType, model);
		if(ns instanceof Package){
			return ((Package) ns).createOwnedType(returnType.getName(), eTYpe);
		}else{
			return ((Class) ns).createNestedClassifier(returnType.getName(), eTYpe);
		}
	}
	private Classifier createEnum(ITypeBinding en) throws IntrospectionException{
		Enumeration enumeration = (Enumeration) createType(en, UMLPackage.eINSTANCE.getEnumeration());
		IVariableBinding[] enumConstants = en.getDeclaredFields();
		for(IVariableBinding e:enumConstants){
			if(e.isEnumConstant()){
				if(enumeration.getMember(e.getName()) == null){
					((Enumeration) enumeration).createOwnedLiteral(e.getName());
				}
			}
		}
		return enumeration;
	}
	private Stereotype findInProfiles(Package model,String name){
		EList<Profile> pkgs = model.getAppliedProfiles();
		return (Stereotype) findClassifier(name, pkgs);
	}
	public Classifier findClassifier(String name,EList<? extends Package> pkgs){
		for(Package pkg:pkgs){
			EList<Type> types = pkg.getOwnedTypes();
			for(Type type:types){
				if(type.getName().equals(name)){
					return (Classifier) type;
				}
			}
		}
		return null;
	}
	private Namespace getPackageFor(ITypeBinding javaClass,Namespace ecoreModelOrProfile){
		if(javaClass.isAnnotation()){
			if(ecoreModelOrProfile instanceof Profile){
				return ecoreModelOrProfile;// Probably to be created
			}else{
				// to be looked up
				// TODO clean this up it is unnecessary
				// Do this lookup in getClassifierFor insteads
				EList<Resource> resources = ecoreModelOrProfile.eResource().getResourceSet().getResources();
				for(Resource resource:resources){
					if(resource.getContents().size() > 0 && resource.getContents().get(0) instanceof Profile){
						Profile p = (Profile) resource.getContents().get(0);
						if(p.getOwnedStereotype(javaClass.getName()) != null){
							return p;
						}
					}
				}
				return null;
			}
		}else{
			StringTokenizer st = new StringTokenizer(javaClass.getPackage().getName(), ".");
			Namespace currentPackage = null;
			String name = st.nextToken();
			if(name.equalsIgnoreCase(ecoreModelOrProfile.getName())){
				currentPackage = ecoreModelOrProfile;
			}else{
				if(ecoreModelOrProfile instanceof Profile && !javaClass.isAnnotation()){
					Resource res = ecoreModelOrProfile.eResource();
					EList<Resource> resources = res.getResourceSet().getResources();
					outer:for(Resource resource:resources){
						for(EObject eObject:resource.getContents()){
							if(eObject instanceof Model && ((Model) eObject).getName().equals(name)){
								currentPackage = model;
								break outer;
							}
						}
					}
					if(currentPackage == null){
						URI uri = res.getURI().trimFileExtension().trimSegments(1).appendSegment(name).appendFileExtension("uml");
						currentPackage = UMLFactory.eINSTANCE.createModel();
						currentPackage.setName(name);
						Resource newRes = res.getResourceSet().createResource(uri);
						res.getResourceSet().getResources().add(newRes);
						newRes.getContents().add(currentPackage);
						ecoreModelOrProfile.createPackageImport((Package) currentPackage, VisibilityKind.PUBLIC_LITERAL);
					}
				}else{
					// just create it in the current model
					// TODOmaybe revisit in future
					currentPackage = findOrCreateChild(ecoreModelOrProfile, name);
				}
			}
			while(st.hasMoreTokens()){
				name = st.nextToken();
				currentPackage = findOrCreateChild(currentPackage, name);
			}
			return currentPackage;
		}
	}
	private Namespace findOrCreateChild(Namespace ecoreModelOrProfile,String name){
		Namespace childPackage = null;
		EList<NamedElement> members = ecoreModelOrProfile.getMembers();
		for(NamedElement member:members){
			if((member instanceof Class || member instanceof Package) && member.getName().equalsIgnoreCase(name)){
				childPackage = (Namespace) member;
			}
		}
		if(childPackage == null){
			if(ecoreModelOrProfile instanceof Package){
				childPackage = ((Package) ecoreModelOrProfile).createNestedPackage(name);
			}else{
				childPackage = ((Class) ecoreModelOrProfile).createNestedClassifier(name, UMLPackage.eINSTANCE.getClass_());
			}
		}
		return childPackage;
	}
	protected void importPrimitiveTypes(Package model){
		Package umlLibrary = getImportedPackage(model, UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
		PrimitiveType booleanPrimitiveType = (PrimitiveType) umlLibrary.getOwnedType("Boolean");
		this.classMap.put(Boolean.class.getName(), booleanPrimitiveType);
		this.classMap.put("boolean", booleanPrimitiveType);
		PrimitiveType stringPrimitiveType = (PrimitiveType) umlLibrary.getOwnedType("String");
		this.classMap.put(String.class.getName(), stringPrimitiveType);
		PrimitiveType integerPrimitiveType = (PrimitiveType) umlLibrary.getOwnedType("Integer");
		this.classMap.put(Integer.class.getName(), integerPrimitiveType);
		this.classMap.put("int", integerPrimitiveType);
		this.classMap.put(BigInteger.class.getName(), integerPrimitiveType);
		this.classMap.put(Long.class.getName(), integerPrimitiveType);
		this.classMap.put("long", integerPrimitiveType);
		this.classMap.put(Short.class.getName(), integerPrimitiveType);
		this.classMap.put("short", integerPrimitiveType);
		this.classMap.put(Byte.class.getName(), integerPrimitiveType);
		this.classMap.put("byte", integerPrimitiveType);
		Package javaLibrary = getImportedPackage(model, UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI);
		this.classMap.put(BigDecimal.class.getName(), (Classifier) javaLibrary.getOwnedMember("double"));
		this.classMap.put(Double.class.getName(), (Classifier) javaLibrary.getOwnedMember("double"));
		this.classMap.put("double", (Classifier) javaLibrary.getOwnedMember("double"));
		this.classMap.put(Float.class.getName(), (Classifier) javaLibrary.getOwnedMember("double"));
		this.classMap.put("float", (Classifier) javaLibrary.getOwnedMember("double"));
		this.classMap.put(Character.class.getName(), (Classifier) javaLibrary.getOwnedMember("char"));
		this.classMap.put("char", (Classifier) javaLibrary.getOwnedMember("char"));
		DataType dateTimeType = (DataType) findInImports(model, "DateTime");
		if(dateTimeType != null){
			this.classMap.put("java.util.Date", dateTimeType);
			this.classMap.put("java.sql.Timestamp", dateTimeType);
		}
	}
	private Classifier findInImports(Package model,String name){
		EList<Package> pkgs = model.getImportedPackages();
		return findClassifier(name, pkgs);
	}
	private Model getImportedPackage(Package ecoreProfile,String uriString){
		URI uri = URI.createURI(uriString);
		for(PackageImport pi:ecoreProfile.getPackageImports()){
			if(pi.getImportedPackage().eResource().getURI().equals(uri)){
				return (Model) pi.getImportedPackage();
			}
		}
		EList<EObject> contents = ecoreProfile.eResource().getResourceSet().getResource(uri, true).getContents();
		if(contents.size() == 0){
			return null;
		}
		Model umlLibrary = (Model) contents.get(0);
		ecoreProfile.getPackageImport(umlLibrary, true);
		return umlLibrary;
	}
	private boolean isSet(ITypeBinding returnType){
		return returnType.isParameterizedType() && (returnType.getQualifiedName().contains("java.util.Set"));
	}
	private boolean isList(ITypeBinding returnType){
		return returnType.isParameterizedType() && (returnType.getQualifiedName().contains("java.util.List"));
	}
	public Properties getMappedTypes(){
		return mappedTypes;
	}
	public Set<Classifier> getNewClassifiers(){
		return newClassifiers;
	}
}
