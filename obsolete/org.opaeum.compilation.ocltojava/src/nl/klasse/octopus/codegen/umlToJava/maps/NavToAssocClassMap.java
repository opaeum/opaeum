 /*
 * Created on Mar 24, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.maps;

import nl.klasse.octopus.model.IAssociationClass;
import nl.klasse.octopus.model.IAssociationEnd;
import nl.klasse.tools.common.Check;
import nl.klasse.tools.common.StringHelpers;

import org.opaeum.java.metamodel.OJPathName;

/** NavToAssocClassMap : This class holds all the information on how a UML association end
 *  that is part of an association class is transformed.
 *  
 *  An association end that is part of an association class is like a 'normal' association end transformed 
 *  into a field, getter, setter, adder, remover, etc. The difference is that the type of the field is the
 *  Java class generated from its association class. Likewise, the types of the operations are changed.
 * 
 *  This class is able to give information on the following items.
 *  	- 
 */
public class NavToAssocClassMap  {

	protected IAssociationClass 			assocClass  		= null;
	protected IAssociationEnd				assocEnd			= null;
	protected StructuralFeatureMap 	assocEndMap 		= null;
	protected ClassifierMap				assocClassMap 	= null;
	
	public NavToAssocClassMap(IAssociationEnd ae) {
		Check.pre("AssociationClassMap created for non AssociationClass", ae.getAssociation().isClass() );
		assocEnd = ae;
		assocClass  = (IAssociationClass)ae.getAssociation();
		
		assocEndMap   = new StructuralFeatureMap(ae);
		assocClassMap = new ClassifierMap(assocClass);
	}

	/* OPERATIONS THAT RETURN SIMPLE INFO ON THIS ASSOCIATION END */

	public String umlName() {
		return assocClass.getName();
	}

	public boolean isSingleObject() {
		return !assocEnd.getType().isCollectionKind();
	}
	
	public String javaFieldName() {
		String name = assocClass.getName();
		IAssociationEnd target = assocClass.getOtherEnd(assocEnd);
		boolean useNameExtension = assocEnd.getBaseType() == target.getBaseType();
		if (useNameExtension){
			name = assocClass.getName() + "_" + target.getName();
		}
		return "f_" + StringHelpers.firstCharToLower(name);
	}

	public String getter() {
		String name = buildAssocEndName(assocClass, assocEnd);
		return "get" + StringHelpers.firstCharToUpper(name);
	}

	public String setter() {
		String name = buildAssocEndName(assocClass, assocEnd);
		return "set" + StringHelpers.firstCharToUpper(name);
	}

	public String adder() {
		String name = buildAssocEndName(assocClass, assocEnd);
		return "z_internalAddTo" + StringHelpers.firstCharToUpper(name);
	}

	public String remover() {
		String name = buildAssocEndName(assocClass, assocEnd);
		return "z_internalRemoveFrom" + StringHelpers.firstCharToUpper(name);
	}

	/* OPERATIONS THAT RETURN TYPE INFO ON THIS ASSOCIATION END */

	/** Results in either (1) the type that is generated form the association class, if the end has multiplicity maximum 1,
	 *  or (2) the collection type corresponding with the association end, with the type parameter changed to the
	 *  type that is generated form the association class, if the end has multiplicity maximum larger than 1.
	 *  
	 *  E.g for the end <code>singleEnd</code> in the association class <code>Coupling: + singleEnd [0..1] <-> + multEnd [0..*]</code>
	 *  this method returns <code>Coupling</code>. For the end <code>multEnd</code> this method returns <code>Set/<Coupling/></code>.
	 * @return
	 */
	public OJPathName javaTypePath() {
		OJPathName result = null;
		if (assocEnd.getType().isCollectionKind()) {
			// TODO check Java 5.0 collection types
			// get the correct collection type
			result = assocEndMap.javaTypePath();
			// now change the element type of this collection to the assoc class
			result.removeAllFromElementTypes();
			result.addToElementTypes(assocClassMap.javaTypePath());
		} else {
			result = assocClassMap.javaTypePath();
		}
		return result;
	}
		
	public OJPathName javaFacadeTypePath() {
		OJPathName result = null;
		if (assocEnd.getType().isCollectionKind()) {
			// TODO check Java 5.0 collection types
			// get the correct collection type
			result = assocEndMap.javaFacadeTypePath();
			// now change the element type of this collection to the assoc class
			result.removeAllFromElementTypes();
			result.addToElementTypes(assocClassMap.javaTypePath());
		} else {
			result = assocClassMap.javaTypePath();
		}
		return result;
	}

	public OJPathName javaDefaultTypePath() {
		OJPathName result = null;
		if (assocEnd.getType().isCollectionKind()) {
			// TODO check Java 5.0 collection types
			result = assocEndMap.javaDefaultTypePath();
			// now change the element type of this collection to the assoc class
			result.removeAllFromElementTypes();
			result.addToElementTypes(assocClassMap.javaTypePath());
		} else {
			result = assocClassMap.javaTypePath();
		}
		return result;
	}
	
	public String javaDefaultValue() {
		String result = null;
		if (assocEnd.getType().isCollectionKind()) {
			// TODO check Java 5.0 collection types
			OJPathName path = assocEndMap.javaDefaultTypePath();
			// now change the element type of this collection to the assoc class
			path.removeAllFromElementTypes();
			path.addToElementTypes(assocClassMap.javaTypePath());
			//
			result = "new " + path.getCollectionTypeName() + "()";
		} else {
			result = assocClassMap.javaDefaultValue();
		}
		return result;
	}
		
	public OJPathName javaBaseTypePath() {
		return assocClassMap.javaTypePath();
	}

	public OJPathName javaBaseFacadeTypePath() {
		return assocClassMap.javaTypePath();
	}

	public String buildAssocEndName(IAssociationClass assoc, IAssociationEnd end) {
		Check.pre("StructuralFeatureTransformer: 'end' must be an end of assoc",
				  (assoc.getEnd1() == end) || (assoc.getEnd2() == end) );
		String name = assoc.getName();
		IAssociationEnd otherEnd = assoc.getOtherEnd(end);
		boolean useNameExtension = (end.getBaseType() == otherEnd.getBaseType());
		if (useNameExtension){
			name = assoc.getName() + "_" + otherEnd.getName();
		}
		return name;
	}

	
	/* A SET OF CONVIENCE OPERATIONS THAT RETURN A STRING REPRESENTATION OF 
	 * THE CORRESPONDING OJPATHNAME
	 */

	public String javaType() {
		return javaTypePath().getCollectionTypeName();
	}
	
	public String javaFacadeType() {
		return javaFacadeTypePath().getCollectionTypeName();
	}

	public String javaBaseType() {
		return javaBaseTypePath().getCollectionTypeName();
	}
	
	/* PRIVATE OPERATIONS THAT DO SOME OF THE WORK
	 */

}
