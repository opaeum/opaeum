package nl.klasse.octopus.codegen.umlToJava.maps;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.Check;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.ocl.uml.TupleType;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.PrimitiveType;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.utilities.JavaPathNames;
import org.opaeum.javageneration.util.OJUtil;

public class ClassifierMap extends PackageableElementMap{
	protected Classifier elementType;
	protected CollectionKind collectionKind;
	public ClassifierMap(OJUtil ojUtil,Classifier modelClass){
		super(ojUtil, modelClass);
		if(modelClass instanceof CollectionType){
			CollectionType ct = (CollectionType) modelClass;
			this.collectionKind = ct.getKind();
			this.elementType = ct.getElementType();
		}else{
			this.elementType = modelClass;
		}
		Check.pre("ClassifierMap constructor: modelClass is null", modelClass != null);
	}
	public boolean isAbstract(){
		return elementType.isAbstract();
	}
	public boolean isCollection(){
		return collectionKind != null;
	}
	public boolean isUmlPrimitive(){
		return elementType instanceof PrimitiveType;
	}
	public boolean isJavaPrimitive(){
		String javatype = javaType();
		return javatype.equals("int") || javatype.equals("double") || javatype.equals("float") || javatype.equals("long")
				|| javatype.equals("boolean");
	}
	public OJPathName javaTypePath(){
		return getJavaType(elementType, collectionKind);
	}
	public OJPathName javaDefaultTypePath(){
		return getJavaImplType(elementType, collectionKind);
	}
	public String javaDefaultValue(){
			return getJavaDefault(elementType, collectionKind);
	}
	public OJPathName javaElementTypePath(){
		if(collectionKind == null){
			return null;
		}else{
			return ojUtil.classifierPathname(elementType);
		}
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
		PrimitiveType type = (PrimitiveType) elementType;
		return getJavaObjectType(type);
	}
	/* SET OF OPERATIONS THAT DO THE ACTUAL WORK */
	private OJPathName getJavaType(Classifier t,CollectionKind collectionKind){
		OJPathName result = null;
		if(t == null){
			result = JavaPathNames.Void;
		}else if(collectionKind != null){
			result = getJavaColectionType(t, collectionKind);
		}else if(t instanceof Enumeration){
			result = getJavaType((Enumeration) t);
		}else if(t instanceof PrimitiveType){
			result = getJavaType((PrimitiveType) t);
		}else if(t instanceof TupleType){
			result = getJavaType((TupleType) t);
		}else if(t instanceof DataType){
			result = getJavaType((DataType) t);
		}else{
			result = pathname(t);
		}
		return result;
	}
	private OJPathName getJavaColectionType(Classifier type,CollectionKind k){
		OJPathName result = getCollectionKind(k).getCopy();
		addElementType(type, result);
		return result;
	}
	private OJPathName getJavaType(DataType t){
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
	private OJPathName getJavaType(Enumeration t){
		OJPathName result = null;
		result = pathname(t);
		return result;
	}
	private OJPathName getJavaType(PrimitiveType t){
		OJPathName result = null;
		result = ojUtil.classifierPathname(t);
		if(EmfClassifierUtil.comformsToLibraryType(t, IOclLibrary.StringTypeName)){
			result = StdlibMap.javaStringType;
		}else if(EmfClassifierUtil.comformsToLibraryType(t,IOclLibrary.RealTypeName)){
			result = StdlibMap.javaRealObjectType;
		}else if(EmfClassifierUtil.comformsToLibraryType(t,IOclLibrary.IntegerTypeName)){
			result = StdlibMap.javaIntegerObjectType;
		}else if(EmfClassifierUtil.comformsToLibraryType(t,IOclLibrary.BooleanTypeName)){
			result = StdlibMap.javaBooleanObjectType;
		}else{
			result = pathname(t);
		}
		return result;
	}
	private OJPathName getJavaType(TupleType t){
		OJPathName result = null;
		TupleTypeMap innerMap = ojUtil.buildTupleTypeMap(t);
		String name = innerMap.getClassName();
		result = OclUtilityCreator.getTuplesPath().getCopy();
		result.addToNames(name);
		return result;
	}
	private String getJavaDefault(Classifier t,CollectionKind k){
		String result = "null";
		if(k != null){
			result = getJavaDefaultCollection(t, k);
		}else if(t instanceof Enumeration){
			result = getJavaDefault((Enumeration) t);
		}else if(t instanceof PrimitiveType){
			result = getJavaDefault((PrimitiveType) t);
		}else if(t instanceof TupleType){
			result = getJavaDefault((TupleType) t);
		}else if(t instanceof DataType){
			result = getJavaDefault((DataType) t);
		}
		return result;
	}
	private String getJavaDefaultCollection(Classifier t,CollectionKind k){
		OJPathName path = getJavaImplType(t, k);
		path = path.getCopy();
		addElementType(t, path);
		return "new " + path.getTypeNameWithTypeArguments() + "()";
	}
	private String getJavaDefault(DataType t){
		String result = "null";
		if(t.getName().equals(IOclLibrary.OclAnyTypeName))
			result = "null";
		if(t.getName().equals(IOclLibrary.OclTypeTypeName))
			result = "null";
		if(t.getName().equals(IOclLibrary.OclVoidTypeName))
			result = "null";
		return result;
	}
	private String getJavaDefault(Enumeration t){
		return t.getName() + ".lookup(0)";
	}
	private String getJavaDefault(PrimitiveType t){
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
	private String getJavaDefault(TupleType t){
		String result = "";
		result = "null";
		return result;
	}
	private OJPathName getJavaImplType(Classifier type,CollectionKind t){
		OJPathName path = null;
		if(t != null){
			if(t == CollectionKind.SET_LITERAL){
				path = StdlibMap.javaSetImplType;
			}else if(t == CollectionKind.SEQUENCE_LITERAL){
				path = StdlibMap.javaSequenceImplType;
			}else if(t == CollectionKind.BAG_LITERAL){
				path = StdlibMap.javaBagImplType;
			}else if(t == CollectionKind.ORDERED_SET_LITERAL){
				path = StdlibMap.javaOrderedSetImplType;
			}
			path = path.getCopy();
			addElementType(type, path);
		}else if(type instanceof PrimitiveType){
			path = getJavaObjectType((PrimitiveType) type);
		}else if(type instanceof TupleType){
			//
		}else{
			path = getJavaType(type, null);
		}
		return path;
	}
	private OJPathName getJavaObjectType(PrimitiveType t){
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
	private OJPathName getCollectionKind(CollectionKind t){
		OJPathName result = null;
		if(t == CollectionKind.SET_LITERAL){
			result = StdlibMap.javaSetType;
		}else if(t == CollectionKind.SEQUENCE_LITERAL){
			result = StdlibMap.javaSequenceType;
		}else if(t == CollectionKind.BAG_LITERAL){
			result = StdlibMap.javaBagType;
		}else if(t == CollectionKind.ORDERED_SET_LITERAL){
			result = StdlibMap.javaOrderedSetType;
		}else if(t == CollectionKind.COLLECTION_LITERAL){
			result = StdlibMap.javaCollectionType;
		}
		return result;
	}
	private void addElementType(Classifier t,OJPathName path){
		OJPathName innerPath = null;
		if(t instanceof PrimitiveType){
			innerPath = getJavaObjectType((PrimitiveType) t);
		}else{
			innerPath = ojUtil.classifierPathname(t);
		}
		if(innerPath != null)
			path.addToElementTypes(innerPath);
	}
	protected OJPathName pathname(NamedElement t){
		if(t instanceof Classifier){
			return ojUtil.classifierPathname((Classifier) t);
		}else{
			OJPathName result = new OJPathName();
			while(t != null){
				result.addToNames(t.getName());
				if(EmfElementFinder.getContainer(t) instanceof NamedElement){
					t = (NamedElement) EmfElementFinder.getContainer(t);
				}else{
					t = null;
				}
			}
			return result;
		}
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
		return javaTypePath().getTypeNameWithTypeArguments();
	}
	/**
	 * If this classifier has a facade interface, this method returns the name of the java type of that facade interface. If the classifier
	 * does not have a facade interface, the name of the java type for the classifier is returned.
	 * 
	 * @return
	 */
	public String javaFacadeType(){
		return javaTypePath().getTypeNameWithTypeArguments();
	}
	/**
	 * If this classifier represents a collection, this method returns the java type of the elements in the collection. E.g. the java element
	 * type of Sequence{1,2,3} is <code>int</code>.
	 * 
	 * @return
	 */
	public String javaElementType(){
		return javaElementTypePath().getTypeNameWithTypeArguments();
	}
	/**
	 * @return
	 */
	public String javaObjectType(){
		return javaObjectTypePath().getTypeName();
	}
}
