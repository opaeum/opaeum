package org.opaeum.emf.reverse;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.resource.UMLResource;

public class AbstractUmlGenerator{
	protected static PrimitiveType booleanPrimitiveType;
	protected static PrimitiveType stringPrimitiveType;
	protected static PrimitiveType integerPrimitiveType;
	protected static Properties mappedTypes = new Properties();
	private static Map<Class<?>,org.eclipse.uml2.uml.Classifier> primitiveTypes = new HashMap<Class<?>,org.eclipse.uml2.uml.Classifier>();
	private static Map<Class<?>,org.eclipse.uml2.uml.Classifier> classMap = new HashMap<Class<?>,org.eclipse.uml2.uml.Classifier>();
	private static Set<Class<?>> selectedClasses = new HashSet<Class<?>>();
	protected static void selectClasses(JarFile jarFile){
		java.util.Enumeration<JarEntry> entries = jarFile.entries();
		while(entries.hasMoreElements()){
			JarEntry entry = entries.nextElement();
			String cn = entry.getName();
			cn = cn.replaceAll("/", ".");
			if(cn.endsWith(".class")){
				Class<?> c = null;
				try{
					c = Thread.currentThread().getContextClassLoader().loadClass(cn.substring(0, cn.length() - 6));
				}catch(ClassNotFoundException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				selectedClasses.add(c);
			}
		}
	}
	protected static void importPrimitiveTypes(Package modelOrProfile){
		Package umlLibrary = getImportedPackage(modelOrProfile, UMLResource.UML_PRIMITIVE_TYPES_LIBRARY_URI);
		booleanPrimitiveType = (PrimitiveType) umlLibrary.getOwnedType("Boolean");
		primitiveTypes.put(Boolean.class, booleanPrimitiveType);
		primitiveTypes.put(boolean.class, booleanPrimitiveType);
		stringPrimitiveType = (PrimitiveType) umlLibrary.getOwnedType("String");
		primitiveTypes.put(String.class, stringPrimitiveType);
		integerPrimitiveType = (PrimitiveType) umlLibrary.getOwnedType("Integer");
		primitiveTypes.put(Integer.class, integerPrimitiveType);
		primitiveTypes.put(int.class, integerPrimitiveType);
		primitiveTypes.put(BigInteger.class, integerPrimitiveType);
		primitiveTypes.put(Long.class, integerPrimitiveType);
		primitiveTypes.put(long.class, integerPrimitiveType);
		primitiveTypes.put(Short.class, integerPrimitiveType);
		primitiveTypes.put(short.class, integerPrimitiveType);
		primitiveTypes.put(Byte.class, integerPrimitiveType);
		primitiveTypes.put(byte.class, integerPrimitiveType);
		Package ecoreLibrary = getImportedPackage(modelOrProfile, UMLResource.JAVA_PRIMITIVE_TYPES_LIBRARY_URI);
		primitiveTypes.put(BigDecimal.class, (Classifier) ecoreLibrary.getOwnedMember("double"));
		primitiveTypes.put(Double.class, (Classifier) ecoreLibrary.getOwnedMember("double"));
		primitiveTypes.put(double.class, (Classifier) ecoreLibrary.getOwnedMember("double"));
		primitiveTypes.put(Float.class, (Classifier) ecoreLibrary.getOwnedMember("double"));
		primitiveTypes.put(float.class, (Classifier) ecoreLibrary.getOwnedMember("double"));
		classMap.putAll(primitiveTypes);
	}
	public static Model getImportedPackage(Package ecoreProfile,String uriString){
		URI uri = URI.createURI(uriString);
		for(PackageImport pi:ecoreProfile.getPackageImports()){
			if(pi.getImportedPackage().eResource().getURI().equals(uri)){
				return (Model) pi.getImportedPackage();
			}
		}
		Model umlLibrary = (Model) ecoreProfile.eResource().getResourceSet().getResource(uri, true).getContents().get(0);
		ecoreProfile.createPackageImport(umlLibrary);
		return umlLibrary;
	}
	protected static Classifier getClassifierFor(Package modelOrProfile,Class<?> returnType){
		try{
			if(returnType == null)
				return null;
			Classifier classifier;
			if(Collection.class.isAssignableFrom(returnType) && returnType.getTypeParameters().length == 1){
				returnType = returnType.getTypeParameters()[0].getGenericDeclaration();
			}else{
				while(returnType.isArray()){
					returnType = returnType.getComponentType();
				}
			}
			if(classMap.containsKey(returnType)){
				classifier = classMap.get(returnType);
			}else{
				// create enum
				classifier = (Classifier) getPackageFor(returnType, modelOrProfile).getMember(returnType.getSimpleName());
				if(classifier == null){
					classifier = createClassifier(modelOrProfile, returnType);
				}
				classMap.put(returnType, classifier);
				mappedTypes.put(classifier.getQualifiedName(), returnType.getName());
				populateClassifier(modelOrProfile, returnType, classifier);
			}
			return classifier;
		}catch(IntrospectionException e){
			throw new RuntimeException(e);
		}
	}
	@SuppressWarnings("unchecked")
	public static Classifier createClassifier(Package modelOrProfile,Class<?> returnType) throws IntrospectionException{
		Classifier classifier = null;
		if(returnType.isEnum()){
			classifier = createEnum(modelOrProfile, (Class<Enum<?>>) returnType);
		}else if(returnType.isInterface()){
			Interface umlInterface = getPackageFor(returnType, modelOrProfile).createOwnedInterface(returnType.getSimpleName());
			Class<?>[] interfaces = returnType.getInterfaces();
			for(Class<?> intfce:interfaces){
				umlInterface.createGeneralization(getClassifierFor(modelOrProfile, intfce));
			}
			classifier = umlInterface;
		}else if(primitiveTypes.containsKey(returnType)){
			classifier = getPackageFor(returnType, modelOrProfile).createOwnedPrimitiveType(returnType.getSimpleName());
		}else{
			org.eclipse.uml2.uml.Class umlClass = getPackageFor(returnType, modelOrProfile).createOwnedClass(returnType.getSimpleName(), false);
			Class<?>[] interfaces = returnType.getInterfaces();
			for(Class<?> intfce:interfaces){
				umlClass.createInterfaceRealization(intfce.getSimpleName(), (Interface) getClassifierFor(modelOrProfile, intfce));
			}
			classifier = umlClass;
		}
		if(returnType.getSuperclass() != Object.class){
			classifier.createGeneralization(getClassifierFor(modelOrProfile, returnType.getSuperclass()));
		}
		return classifier;
	}
	private static void populateClassifier(Package modelOrProfile,Class<?> en,Classifier intfce) throws IntrospectionException{
		if(selectedClasses.contains(en)){
			BeanInfo beanInfo = Introspector.getBeanInfo(en, en.getSuperclass());
			populateAttributes(modelOrProfile, intfce, beanInfo);
			populateOperations(modelOrProfile, intfce, beanInfo);
		}
	}
	private static void populateOperations(Package modelOrProfile,Classifier intfce,BeanInfo beanInfo){
		MethodDescriptor[] mds = beanInfo.getMethodDescriptors();
		for(MethodDescriptor md:mds){
			if(!hasOperation(modelOrProfile, intfce, md) && !isAccessor(md)){
				Operation oper = createOperation(modelOrProfile, intfce, md);
				oper.setIsStatic(Modifier.isStatic(md.getMethod().getModifiers()));
				Class<?> returnType = md.getMethod().getReturnType();
				if(returnType != void.class){
					Parameter result = oper.createOwnedParameter("result", getClassifierFor(modelOrProfile, returnType));
					result.setDirection(ParameterDirectionKind.RETURN_LITERAL);
					if(returnType.isArray() || Collection.class.isAssignableFrom(returnType)){
						result.setUpper(LiteralUnlimitedNatural.UNLIMITED);
					}
				}
				Class<?>[] parameterTypes = md.getMethod().getParameterTypes();
				for(int i = 0;i < parameterTypes.length;i++){
					if(parameterTypes[0].isArray() || Collection.class.isAssignableFrom(parameterTypes[0])){
						Parameter umlParameter = oper.getOwnedParameters().get(i);
						umlParameter.setUpper(LiteralUnlimitedNatural.UNLIMITED);
						umlParameter.setName("parm" + i);
					}
				}
			}
		}
	}
	private static boolean isAccessor(MethodDescriptor md){
		// TODO Auto-generated method stub
		boolean returnsVoid = md.getMethod().getReturnType() == null || md.getMethod().getReturnType() == void.class;
		boolean setter = md.getName().startsWith("set") && md.getMethod().getParameterTypes().length == 1 && returnsVoid;
		boolean getter = md.getName().startsWith("get") && !returnsVoid;
		return setter || getter;
	}
	public static boolean hasOperation(Package modelOrProfile,Classifier intfce,MethodDescriptor md){
		for(Operation oper:intfce.getOperations()){
			if(oper.getName().equals(md.getName())){
				boolean paramsMatch = true;
				for(int i = 0;i < md.getMethod().getParameterTypes().length;i++){
					Parameter parm = oper.getOwnedParameters().get(i);
					if(getClassifierFor(modelOrProfile, md.getMethod().getParameterTypes()[i]) != parm.getType()){
						paramsMatch = false;
						break;
					}
				}
				if(paramsMatch){
					return true;
				}
			}
		}
		return false;
	}
	private static void populateAttributes(Package modelOrProfile,Classifier intfce,BeanInfo beanInfo){
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor pd:pds){
			Class<?> propertyType = pd.getPropertyType();
			if(propertyType == null){
				System.out.println("Property type null:" + pd.getReadMethod());
			}else{
				if(intfce.getAttribute(pd.getName(), getClassifierFor(modelOrProfile, pd.getPropertyType())) == null){
					Property attr = createAttribute(modelOrProfile, intfce, pd);
					attr.setIsReadOnly(pd.getWriteMethod() == null);
					attr.setIsDerived(pd.getWriteMethod() == null);
					if(propertyType.isArray()){
						attr.setUpper(-1);
					}
				}
			}
		}
	}
	private static Operation createOperation(Package modelOrProfile,Classifier intfce,MethodDescriptor md){
		Operation oper = null;
		if(intfce instanceof Interface){
			oper = ((Interface) intfce).createOwnedOperation(md.getName(), getArgumentNames(md), getArgumentTypes(modelOrProfile, md));
		}else if(intfce instanceof DataType){
			oper = ((DataType) intfce).createOwnedOperation(md.getName(), getArgumentNames(md), getArgumentTypes(modelOrProfile, md));
		}else if(intfce instanceof org.eclipse.uml2.uml.Class){
			oper = ((org.eclipse.uml2.uml.Class) intfce).createOwnedOperation(md.getName(), getArgumentNames(md), getArgumentTypes(modelOrProfile, md));
		}
		return oper;
	}
	private static Property createAttribute(Package modelOrProfile,Classifier intfce,PropertyDescriptor pd){
		Property attr = null;
		if(intfce instanceof Interface){
			attr = ((Interface) intfce).createOwnedAttribute(pd.getName(), getClassifierFor(modelOrProfile, pd.getPropertyType()));
		}else if(intfce instanceof org.eclipse.uml2.uml.Class){
			attr = ((org.eclipse.uml2.uml.Class) intfce).createOwnedAttribute(pd.getName(), getClassifierFor(modelOrProfile, pd.getPropertyType()));
		}else if(intfce instanceof DataType){
			attr = ((DataType) intfce).createOwnedAttribute(pd.getName(), getClassifierFor(modelOrProfile, pd.getPropertyType()));
		}
		return attr;
	}
	private static EList<String> getArgumentNames(MethodDescriptor md){
		BasicEList<String> results = new BasicEList<String>();
		for(int i = 0;i < md.getMethod().getParameterTypes().length;i++){
			results.add("parm" + i);
		}
		return results;
	}
	private static EList<Type> getArgumentTypes(Package modelOrProfile,MethodDescriptor md){
		BasicEList<Type> results = new BasicEList<Type>();
		Class<?>[] pds = md.getMethod().getParameterTypes();
		for(@SuppressWarnings("rawtypes")
		Class pd:pds){
			results.add(getClassifierFor(modelOrProfile, pd));
		}
		return results;
	}
	@SuppressWarnings("rawtypes")
	private static Classifier createEnum(Package modelOrProfile,Class<Enum<?>> en) throws IntrospectionException{
		Enumeration enumeration = getPackageFor(en, modelOrProfile).createOwnedEnumeration(en.getSimpleName());
		Enum[] enumConstants = en.getEnumConstants();
		for(Enum e:enumConstants){
			if(enumeration.getMember(e.name()) == null){
				((Enumeration) enumeration).createOwnedLiteral(e.name());
			}
		}
		return enumeration;
	}
	private static Package getPackageFor(@SuppressWarnings("rawtypes") Class javaClass,Package ecoreModelOrProfile){
		StringTokenizer st = new StringTokenizer(javaClass.getPackage().getName(), ".");
		Package currentPackage = ecoreModelOrProfile;
		while(st.hasMoreTokens()){
			String name = st.nextToken();
			Package childPackage = (Package) currentPackage.getMember(name);
			if(childPackage == null){
				childPackage = currentPackage.createNestedPackage(name);
			}
			currentPackage = childPackage;
		}
		return currentPackage;
	}
	protected static String findUml2ResourceJar() throws Exception{
		URLClassLoader s = (URLClassLoader) Thread.currentThread().getContextClassLoader();
		URL[] urls = s.getURLs();
		String UML2JAR = null;
		for(URL url:urls){
			if(url.getFile().contains("org.eclipse.uml2.uml.resources")){
				File file = new File(url.getFile());
				UML2JAR = "jar:file:///" + file.getAbsolutePath().replace('\\', '/') + "!/";
				System.out.println(UML2JAR);
				break;
			}
		}
		return UML2JAR;
	}
}
