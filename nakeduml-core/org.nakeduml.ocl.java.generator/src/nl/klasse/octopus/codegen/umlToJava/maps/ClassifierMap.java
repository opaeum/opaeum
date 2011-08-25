/*
 * Created on Mar 24, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.maps;

import java.util.Iterator;

import nl.klasse.octopus.OctopusConstants;
import nl.klasse.octopus.codegen.helpers.GenerationHelpers;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.model.CollectionMetaType;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.model.IDataType;
import nl.klasse.octopus.model.IEnumerationType;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IPrimitiveType;
import nl.klasse.octopus.model.ITupleType;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;
import nl.klasse.octopus.stdlib.internal.types.StdlibTupleType;
import nl.klasse.tools.common.Check;

import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.utilities.JavaPathNames;

/**
 * ClassifierMap : This class holds all the information on how a UML classifier is transformed into the corresponding Java class.
 * 
 * It is able to give information on the following items. - the path in the java model of the corresponding Java class/type, e.g. for UML
 * classifier<code>novels::Book</code>the result is<code>novels.Book</code>, or - case of a facade -<code>novels.internal.Book</code>; e.g.
 * for UML classifier <code>Integer</code> the result is<code>java.util.int</code>; e.g. for UML classifier <code>Set(novels::Book)</code>
 * the result is<code>java.util.Set<novels.Book></code>, or - case of a facade -<code>java.util.Set<novels.internal.Book></code>; - the path
 * in the java model of the facade interface, e.g. for UML classifier<code>novels::Book</code>the result is<code>novels.IBook</code>; e.g.
 * for UML classifier <code>Integer</code> the result is<code>java.util.int</code>; e.g. for UML classifier <code>Set(novels::Book)</code>
 * the result is<code>java.util.Set<novels.IBook></code>; - the path in the java model of the Java type that is used to give an element of
 * this UML type an initial value, e.g. for UML classifier<code>novels::Book</code>the result is<code>novels.Book</code>; e.g. for UML
 * classifier <code>Integer</code> the result is<code>java.util.int</code>; e.g. for UML classifier <code>Set(novels::Book)</code> the
 * result is<code>java.util.HashSet<novels.Book></code>; - a string that is used to give an element of this UML type an initial value, e.g.
 * for UML classifier<code>novels::Book</code>the result is<code>new Book()</code>; e.g. for UML classifier <code>Integer</code> the result
 * is<code>0</code>; e.g. for UML classifier <code>Set(novels::Book)</code> the result is<code>new HashSet<novels.Book>()</code>;
 * 
 * If the UML classifier is a collection type, then the same information can be obtained about the element type of the collection.
 * 
 * If the UML classifier is a primitive type, then this class is also able to return the Java object type for that primitive type. E.g. the
 * Java object type for the UML type <code>Integer</code> is <code>java.util.Object</code>.
 * 
 */
public class ClassifierMap extends PackageableElementMap{
	protected IClassifier modelClass;
	private OJPathName myTypePath = null; // holds result of javaTypePath
	private OJPathName myFacadeTypePath = null; // holds result of javaFacadeTypePath
	private OJPathName myDefaultTypePath = null; // holds result of javaDefaultTypePath
	private String myDefaultValue = null; // holds result of javaDefaultValue
	private ClassifierMap elementMap = null; // used to get the results on the element type if 'modelClass' is a collection
	/**
	 * 
	 */
	public ClassifierMap(IClassifier modelClass){
		super(modelClass);
		Check.pre("ClassifierMap constructor: modelClass is null", modelClass != null);
		this.modelClass = modelClass;
		if(this.modelClass.isCollectionKind()){
			ICollectionType type = (ICollectionType) modelClass;
			elementMap = newClassifierMap(type.getElementType());
		}
	}
	/* SET OF OPERATIONS THAT RETURN SIMPLE INFO ON THIS CLASSIFIER */
	/**
	 * The UML class name without any prefix
	 * 
	 * @return String
	 */
	public String umlType(){
		return modelClass.getName();
	}
	public boolean isAbstract(){
		return modelClass.getIsAbstract();
	}
	public boolean isCollection(){
		return modelClass.isCollectionKind();
	}
	public boolean isNestedCollection(){
		return modelClass.isCollectionKind() && (new ClassifierMap(((ICollectionType) modelClass).getElementType()).isCollection());
	}
	public boolean isUmlPrimitive(){
		return modelClass instanceof IPrimitiveType;
	}
	public boolean isJavaPrimitive(){
		String javatype = javaType();
		return javatype.equals("int") || javatype.equals("float") || javatype.equals("boolean");
	}
	/**
	 * @return
	 */
	public boolean hasFacade(){
		return GenerationHelpers.hasFacade(modelClass);
	}
	/* SET OF OPERATIONS THAT RETURN JAVA TYPE INFO ON THIS CLASSIFIER */
	/**
	 * The java path for this class
	 * 
	 * @return
	 */
	public OJPathName javaTypePath(){
		if(myTypePath == null){
			myTypePath = getJavaType(modelClass);
		}
		return myTypePath;
	}
	/**
	 * If this classifier has a facade interface, this method returns the path of the java type of that facade interface. If the classifier
	 * does not have a facade interface, the path of the java type for the classifier is returned, i.e. this method gives the same result as
	 * this.javaTypePath().
	 * 
	 * @return
	 */
	public OJPathName javaFacadeTypePath(){
		if(myFacadeTypePath == null){
			IClassifier facade = GenerationHelpers.getFacade(modelClass);
			myFacadeTypePath = getJavaType(facade);
		}
		return myFacadeTypePath;
	}
	/**
	 * The java type of the default value for this class. Note that this type may be different form the java type for this class. E.g. the
	 * java type for the default value of an OCL sequence is <code>ArrayList</code>, whereas the java type is <code>List</code>.
	 * 
	 * @return
	 */
	public OJPathName javaDefaultTypePath(){
		if(myDefaultTypePath == null){
			myDefaultTypePath = getJavaImplType(modelClass);
		}
		return myDefaultTypePath;
	}
	/**
	 * The java default initial value for this class
	 * 
	 * @return
	 */
	public String javaDefaultValue(){
		if(myDefaultValue == null){
			myDefaultValue = getJavaDefault(modelClass);
		}
		return myDefaultValue;
	}
	/*
	 * SET OF OPERATIONS THAT ONLY DO SOMETHING WHEN 'modelClass' IS A COLLECTION.
	 */
	/**
	 * If this classifier represents a collection, this method returns the java type of the elements in the collection. E.g. the java
	 * element type of Sequence{1,2,3} is <code>java.util.int</code>.
	 * 
	 * @return
	 */
	public OJPathName javaElementTypePath(){
		Check.pre("ClassMap.javaElementType called for non-collection attribute", isCollection());
		if(elementMap != null){
			return elementMap.javaTypePath();
		}
		return null;
	}
	/**
	 * If this classifier represents a collection and it has a facade interface, this method returns the path of the java type of the facade
	 * of the elements in the collection. E.g. the java element facade type of a Sequence of <code>pack1.SomeClass</code> objects is
	 * <code>pack1.ISomeClass</code>.
	 * 
	 * @return
	 */
	public OJPathName javaElementFacadeTypePath(){
		Check.pre("ClassMap.elementFacadeTypePath called for non-collection attribute", isCollection());
		if(elementMap != null){
			return elementMap.javaFacadeTypePath();
		}
		return null;
	}
	/**
	 * If this classifier represents a collection, this method returns the path of the java type of the default initial value of the
	 * elements in the collection. E.g. the java element type of Sequence{Set{1,2,3}} is <code>HashSet</code>.
	 * 
	 * @return
	 */
	public OJPathName javaElementDefaultTypePath(){
		Check.pre("ClassMap.javaElementDefaultValue called for non-collection attribute", isCollection());
		if(elementMap != null){
			return elementMap.javaDefaultTypePath();
		}
		return null;
	}
	/**
	 * If this classifier represents a collection, this method returns the java default initial value of the elements in the collection.
	 * E.g. the java default initial value of Sequence{1,2,3} is <code>0</code>.
	 * 
	 * @return
	 */
	public String javaElementDefaultValue(){
		Check.pre("ClassMap.javaElementDefaultValue called for non-collection attribute", isCollection());
		if(elementMap != null){
			return elementMap.javaDefaultValue();
		}
		return null;
	}
	public OJPathName javaElementObjectTypePath(){
		Check.pre("ClassMap.javaElementObjectTypePath called for non-collection attribute", isCollection());
		Check.pre("ClassMap.javaElementObjectTypePath called for non-primitive element type", elementMap.isJavaPrimitive());
		if(elementMap != null){
			return elementMap.javaObjectTypePath();
		}
		return null;
	}
	/**
	 * If this classifier has a facade interface, this method returns the default initial value of that facade interface. If the classifier
	 * does not have a facade interface, the default initial value for the classifier is returned.
	 * 
	 * @return
	 */
	// TODO can we remove this op??
	public String javaElementFacadeDefaultValue(){
		Check.pre("ClassMap.facadeDefaultValue called for non-collection attribute", isCollection());
		ICollectionType type = (ICollectionType) modelClass;
		IClassifier elemType = type.getElementType();
		IClassifier elemFacade = elemType;
		return getJavaDefault(elemFacade);
	}
	/**
	 * If this classifier represents a collection, this method returns the name of the method that copies an instance of the collection into
	 * an unmodifiable instance. E.g. for a Sequence this method returns <code>Collections.unmodifiableList</code>.
	 * 
	 * @return
	 */
	public String javaUnmodifiableCollectionOper(){
		Check.pre("ClassMap.unmodifiableType called for non-collection attribute", isCollection());
		ICollectionType type = (ICollectionType) modelClass;
		if(type.getMetaType() == CollectionMetaType.SET){
			return "Collections.unmodifiableSet";
		}else if(type.getMetaType() == CollectionMetaType.SEQUENCE){
			return "Collections.unmodifiableList";
		}else if(type.getMetaType() == CollectionMetaType.BAG){
			return "Collections.unmodifiableList";
		}else if(type.getMetaType() == CollectionMetaType.ORDEREDSET){
			return "Collections.unmodifiableList";
		}
		return "";
	}
	public OJPathName javaUnmodifiableCollectionOperType(){
		return JavaPathNames.Collections;
	}
	/* OPERATION THAT ONLY WORKS WHEN 'modelClass' IS A PRIMITIVE TYPE */
	/**
	 * @return
	 */
	public OJPathName javaObjectTypePath(){
		Check.pre("javaObjectType called for non primitive type", isUmlPrimitive());
		IPrimitiveType type = (IPrimitiveType) modelClass;
		return getJavaObjectType(type);
	}
	/* SET OF OPERATIONS THAT DO THE ACTUAL WORK */
	private OJPathName getJavaType(IClassifier t){
		OJPathName result = null;
		if(t == null){
			result = JavaPathNames.Void;
		}else if(t instanceof ICollectionType){
			result = getJavaType((ICollectionType) t);
		}else if(t instanceof IEnumerationType){
			result = getJavaType((IEnumerationType) t);
		}else if(t instanceof IPrimitiveType){
			result = getJavaType((IPrimitiveType) t);
		}else if(t instanceof ITupleType){
			result = getJavaType((ITupleType) t);
		}else if(t instanceof IDataType){
			result = getJavaType((IDataType) t);
		}else{
			result = pathname(t);
		}
		return result;
	}
	private OJPathName getJavaType(ICollectionType t){
		// TODO check use of JAVA 5.0 collection types here !!!!
		OJPathName result = getCollectionMetaType(t).getCopy();
		addElementType(t, result);
		return result;
	}
	private OJPathName getJavaType(IDataType t){
		OJPathName result = null;
		if(t.getName().equals(IOclLibrary.OclAnyTypeName)){
			result = JavaPathNames.Object;
		}else if(t.getName().equals(IOclLibrary.OclVoidTypeName)){
			result = JavaPathNames.Void;
		}else{
			result = pathname(t);
		}
		return result;
	}
	private OJPathName getJavaType(IEnumerationType t){
		OJPathName result = null;
		result = pathname(t);
		return result;
	}
	private OJPathName getJavaType(IPrimitiveType t){
		OJPathName result = null;
		if(t.getName().equals(IOclLibrary.StringTypeName)){
			result = StdlibMap.javaStringType;
		}else if(t.getName().equals(IOclLibrary.RealTypeName)){
			result = StdlibMap.javaRealObjectType;
		}else if(t.getName().equals(IOclLibrary.IntegerTypeName)){
			result = StdlibMap.javaIntegerObjectType;
		}else if(t.getName().equals(IOclLibrary.BooleanTypeName)){
			result = StdlibMap.javaBooleanObjectType;
		}else{
			result = pathname(t);
		}
		return result;
	}
	private OJPathName getJavaType(ITupleType t){
		OJPathName result = null;
		TupleTypeMap innerMap = new TupleTypeMap(t);
		String name = innerMap.getClassName();
		result = OclUtilityCreator.getTuplesPath().getCopy();
		result.addToNames(name);
		return result;
	}
	private String getJavaDefault(IClassifier t){
		String result = "null";
		if(t instanceof ICollectionType){
			result = getJavaDefault((ICollectionType) t);
		}else if(t instanceof IEnumerationType){
			result = getJavaDefault((IEnumerationType) t);
		}else if(t instanceof IPrimitiveType){
			result = getJavaDefault((IPrimitiveType) t);
		}else if(t instanceof ITupleType){
			result = getJavaDefault((ITupleType) t);
		}else if(t instanceof IDataType){
			result = getJavaDefault((IDataType) t);
		}
		return result;
	}
	private String getJavaDefault(ICollectionType t){
		// TODO check creation of JAVA 5.0 types here !!!!
		OJPathName path = getJavaImplType(t);
		path = path.getCopy();
		addElementType(t, path);
		return "new " + path.getCollectionTypeName() + "()";
	}
	private String getJavaDefault(IDataType t){
		String result = "null";
		if(t.getName().equals(IOclLibrary.OclAnyTypeName))
			result = "null";
		if(t.getName().equals(IOclLibrary.OclTypeTypeName))
			result = "null";
		if(t.getName().equals(IOclLibrary.OclVoidTypeName))
			result = "null";
		return result;
	}
	private String getJavaDefault(IEnumerationType t){
		return t.getName() + ".lookup(0)";
	}
	private String getJavaDefault(IPrimitiveType t){
		String result = t.getName();
		if(t.getName().equals(IOclLibrary.StringTypeName))
			result = "\"\"";
		if(t.getName().equals(IOclLibrary.RealTypeName))
			result = "0";
		if(t.getName().equals(IOclLibrary.IntegerTypeName))
			result = "0";
		if(t.getName().equals(IOclLibrary.BooleanTypeName))
			result = "false";
		return result;
	}
	private String getJavaDefault(ITupleType t){
		String result = "";
		result = "null";
		return result;
	}
	private OJPathName getJavaImplType(IClassifier type){
		// TODO check creation of JAVA 5.0 types here !!!!
		OJPathName path = getJavaType(type);
		if(type instanceof ICollectionType){
			ICollectionType t = (ICollectionType) type;
			if(t.getMetaType() == CollectionMetaType.SET){
				path = StdlibMap.javaSetImplType;
			}else if(t.getMetaType() == CollectionMetaType.SEQUENCE){
				path = StdlibMap.javaSequenceImplType;
			}else if(t.getMetaType() == CollectionMetaType.BAG){
				path = StdlibMap.javaBagImplType;
			}else if(t.getMetaType() == CollectionMetaType.ORDEREDSET){
				path = StdlibMap.javaOrderedSetImplType;
			}else{
				System.err.println("unidentified collection type in getJavaImplType");
			}
			path = path.getCopy();
			addElementType(t, path);
		}else if(type instanceof StdlibPrimitiveType){
			path = getJavaObjectType((StdlibPrimitiveType) type);
		}else if(type instanceof StdlibTupleType){
			//
		}else{
//			System.err.println("unidentified classifier in getJavaImplType: type is " + type.getClass().getName());
		}
		return path;
	}
	private OJPathName getJavaObjectType(IPrimitiveType t){
		OJPathName result = null;
		if(t.getName().equals(IOclLibrary.StringTypeName)){
			result = StdlibMap.javaStringType;
		}else if(t.getName().equals(IOclLibrary.RealTypeName)){
			result = StdlibMap.javaRealObjectType;
		}else if(t.getName().equals(IOclLibrary.IntegerTypeName)){
			result = StdlibMap.javaIntegerObjectType;
		}else if(t.getName().equals(IOclLibrary.BooleanTypeName)){
			result = StdlibMap.javaBooleanObjectType;
		}else{
			result = pathname(t);
		}
		return result;
	}
	private OJPathName getCollectionMetaType(ICollectionType t){
		OJPathName result = null;
		if(t.getMetaType() == CollectionMetaType.SET){
			result = StdlibMap.javaSetType;
		}else if(t.getMetaType() == CollectionMetaType.SEQUENCE){
			result = StdlibMap.javaSequenceType;
		}else if(t.getMetaType() == CollectionMetaType.BAG){
			result = StdlibMap.javaBagType;
		}else if(t.getMetaType() == CollectionMetaType.ORDEREDSET){
			result = StdlibMap.javaOrderedSetType;
		}else if(t.getMetaType() == CollectionMetaType.COLLECTION){
			result = StdlibMap.javaCollectionType;
		}
		return result;
	}
	private void addElementType(ICollectionType t,OJPathName path){
		IClassifier elementType = t.getElementType();
		IClassifier elemType = elementType;
		OJPathName innerPath = null;
		if(elemType instanceof StdlibPrimitiveType){
			innerPath = getJavaObjectType((StdlibPrimitiveType) elemType);
		}else{
			ClassifierMap innerMap = newClassifierMap(elementType);
			innerPath = innerMap.javaFacadeTypePath(); // if no facade present, this will give the same result as javaTypePath()
		}
		if(innerPath != null)
			path.addToElementTypes(innerPath);
	}
	protected ClassifierMap newClassifierMap(IClassifier elementType){
		return new ClassifierMap(elementType);
	}
	protected OJPathName pathname(IModelElement t){
		OJPathName result = new OJPathName();
		Iterator it = t.getPathName().getNames().iterator();
		while(it.hasNext()){
			String n = (String) it.next();
			if(!n.equals(OctopusConstants.OCTOPUS_INVISIBLE_PACK_NAME)){
				result.addToNames(n);
			}
		}
		return result;
	}
	/*
	 * A SET OF CONVIENCE OPERATIONS THAT RETURN A STRING REPRESENTATION OF THE CORRESPONDING OJPATHNAME
	 */
	/**
	 * The Java class name without any prefix, but with element types if the UML classifier is a collection. E.g. Set<Book>, Chapter.
	 * 
	 * @return String
	 */
	public String javaType(){
		return javaTypePath().getCollectionTypeName();
	}
	/**
	 * If this classifier has a facade interface, this method returns the name of the java type of that facade interface. If the classifier
	 * does not have a facade interface, the name of the java type for the classifier is returned.
	 * 
	 * @return
	 */
	public String javaFacadeType(){
		return javaFacadeTypePath().getCollectionTypeName();
	}
	/**
	 * If this classifier represents a collection, this method returns the java type of the elements in the collection. E.g. the java
	 * element type of Sequence{1,2,3} is <code>int</code>.
	 * 
	 * @return
	 */
	public String javaElementType(){
		return javaElementTypePath().getCollectionTypeName();
	}
	/**
	 * If this classifier represents a collection, this method returns the name of the java type of the default initial value of the
	 * elements in the collection. E.g. the java element type of Sequence{Set{1,2,3}} is <code>HashSet</code>.
	 * 
	 * @return
	 */
	public String javaElementDefaultType(){
		return javaElementDefaultTypePath().getCollectionTypeName();
	}
	/**
	 * @return
	 */
	public String javaObjectType(){
		return javaObjectTypePath().getTypeName();
	}
}
