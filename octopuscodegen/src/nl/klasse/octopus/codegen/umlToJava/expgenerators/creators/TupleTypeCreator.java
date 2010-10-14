/*
 * Created on Jan 6, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.HashMap;
import java.util.Iterator;

import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.OJVisibilityKind;
import net.sf.nakeduml.javametamodel.utilities.JavaPathNames;
import nl.klasse.octopus.codegen.helpers.CommonNames;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.TupleTypeMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.creators.DataTypeCreator;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.internal.types.VariableDeclaration;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IDataType;
import nl.klasse.octopus.model.ITupleType;
import nl.klasse.tools.common.StringHelpers;

/* <octel> */ 

/* <java> */ 

/* <inline> */ 

/* <inline/> */ 

/* <inline> */ 

/* <inline/> */ 

/* <inline> */ 

/* <inline/> */ 

/* <inline> */ 

/* <inline/> */ 

/* <inline> */ 

/* <inline/> */ 

/* <java/> */ 

/* <octel/> */ 



/**
 * DataTypeTransformer : 
 */
public class TupleTypeCreator extends DataTypeCreator {
	private String standardClassComment = "TupleType implementation generated by Octopus";
	static private HashMap<String, OJClass> tupleTypes 	= null;

	/**
	 * 
	 */
	public TupleTypeCreator() {
		super();
		tupleTypes = new HashMap<String, OJClass>();
	}
	
	public OJClass make(ITupleType in, OJPackage tuplePack) {
		String key = "";
		//build key from the types of the parts, in alphabetical order
		TupleTypeMap TUPLE = new TupleTypeMap((ITupleType)in);
		String[] typeNames = TUPLE.get_typenames();
		for(int i=0; i<typeNames.length; i++) {
			key = key + "#" + typeNames[i];
		}
        
		OJClass result = (OJClass)tupleTypes.get(key);
		if( result != null ){ // found the type; it already exists.
			return result; 
		}
		result = priv_make(in, tuplePack);
		tupleTypes.put(key, result);
		return result;
	}

	private OJClass priv_make(ITupleType in, OJPackage tuplePack) {
		OJClass created = new OJClass();
		created.setComment(CommonNames.standardClassComment);
		tuplePack.addToClasses(created);
		TupleTypeMap TUPLE = new TupleTypeMap((ITupleType)in);
		String TYPE = TUPLE.getClassName();
		created.setName(TYPE);
		created.setVisibility(new ClassifierMap(in).javaVisibility());
		created.setComment(standardClassComment);
		this.createDataType(created, in);
		OJConstructor constr = created.getDefaultConstructor();
		//
		Iterator it = TUPLE.sort_parts().iterator();
		while (it.hasNext()){
			VariableDeclaration decl = (VariableDeclaration) it.next();
			ClassifierMap mapper = new ClassifierMap(decl.getType());
			created.addToFields(make_attribute(decl, mapper));
			constr.getBody().addToStatements("this." + getFieldName(decl) + " = " + decl.getName());
			if (mapper.hasFacade()) {
				constr.addParam(decl.getName(), mapper.javaFacadeTypePath());
				created.addToImports(mapper.javaFacadeTypePath());
			} else {
				constr.addParam(decl.getName(), mapper.javaTypePath());
			}
			created.addToOperations(createGetter(decl, mapper));
			created.addToImports(mapper.javaTypePath());
		}
		return created;
	}

	/**
	 * @param decl
	 * @return
	 */
	private OJOperation createGetter(VariableDeclaration decl, ClassifierMap mapper) {
		OJOperation oper = new OJOperation();
		oper.setName(getGetterName(decl));
		if (mapper.hasFacade()) {
			oper.setReturnType(mapper.javaFacadeTypePath());
		} else {
			oper.setReturnType(mapper.javaTypePath());
		}
		oper.setVisibility(OJVisibilityKind.PUBLIC);
		oper.getBody().addToStatements("return " + getFieldName(decl));
		return oper;
	}

	private OJField make_attribute(VariableDeclaration decl, ClassifierMap mapper) {
		OJField field = new OJField();
		field.setName(getFieldName(decl));
		field.setVisibility(OJVisibilityKind.PRIVATE);
		if (mapper.hasFacade()) {
			field.setType(mapper.javaFacadeTypePath());
		} else {
			field.setType(mapper.javaTypePath());
		}
		return field;
	}
	
	/**
	 * @param decl
	 * @return
	 */
	private String getFieldName(VariableDeclaration decl) {
		return decl.getName();
	}
	
	private String getGetterName(IVariableDeclaration decl) {
		return "get" + StringHelpers.firstCharToUpper(decl.getName());
	}
	
	/**
	 * Creates the operation 'equals' for <arg>in<\arg>.
	 * See ITEM 7 in Effective Java.
	 * The operation compares all non-derived attributes.
	 * 
	 * @param in
	 * @return
	 */
	// we need to overwrite the equal_op in DataTypeGenerator
	protected OJOperation equal_op(IDataType in){
		String param = "tuple";
		TupleTypeMap TUPLE = new TupleTypeMap((ITupleType)in);
		String TYPE = TUPLE.getClassName();

		OJOperation oper = null;
		
		/**<octel var="currentClass">
			<method type="%JavaPathNames.Bool%" name="equals" var="oper">
				<param type="%JavaPathNames.Object%" name="%param%"/>
				<body>		  		
					<if> !(%param% instanceof %TYPE%) <then>
						return false;
					</then></if>
					%TYPE% par = (%TYPE%) %param%;
				</body>
			</method>
		  
		 </octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
currentClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(JavaPathNames.Bool);

/* <type/> */ 

/* <name> */ 
oper.setName("equals");

/* <name/> */ 

/* <param> */ 
OJParameter param1 = new OJParameter();
oper.addToParameters(param1);

/* <type> */ 
param1.setType(JavaPathNames.Object);

/* <type/> */ 

/* <name> */ 
param1.setName(param);

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body1 = new OJBlock();
oper.setBody(body1);

/* <if> */ 
OJIfStatement if1 = new OJIfStatement();
if1.setCondition("!(" + param + " instanceof " + TYPE + ")");
body1.addToStatements(if1);

/* <then> */ 
OJBlock then1 = new OJBlock();
if1.setThenPart(then1);

/* <exp> */ 
OJSimpleStatement exp1 = new OJSimpleStatement("return false");
then1.addToStatements( exp1 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp2 = new OJSimpleStatement(TYPE + " par = (" + TYPE + ") " + param);
body1.addToStatements( exp2 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		
		// compare fields
		param = "((" + TYPE + ")" + param + ")";
		Iterator it = ((ITupleType)in).getParts().iterator();
		while( it.hasNext()){
			IVariableDeclaration elem = (IVariableDeclaration) it.next();
			IClassifier type = elem.getType();
			String name = getGetterName(elem) + "()";
			oper.getBody().addToStatements(compareInnerElements(param, type, name));		
		}
		oper.getBody().addToStatements("return true");
		return oper;
	}
	


}
