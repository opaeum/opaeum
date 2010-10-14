/*
 * Created on Jun 7, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.modelgenerators.creators;
/**<octel>
<java>
	%import net.sf.nakeduml.javametamodel.OJParameter%;
	%inet.sfsf.nakeduml.javametamodelametamodel.model.OJSimpleStatement%;
</java>
</octel>*/

/* <octel> */ 

/* <java> */ 

/* <inline> */ 
import java.util.Iterator;

import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.OJVisibilityKind;
import net.sf.nakeduml.javametamodel.utilities.JavaPathNames;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IDataType;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.StringHelpers;


/**
 * DataTypeGenerator : 
 */
public class DataTypeCreator {
	protected OJClass	currentClass = null;

	/**
	 * 
	 */
	public DataTypeCreator() {
		super();
	}
	// TODO use template!!

	/**
	 * @param myClass
	 * @param type
	 */
	public void createDataType(OJClass myClass, IDataType in) {
		currentClass = myClass;
		currentClass.addToOperations(equal_op(in));
		hashcode_op(in, myClass);
		currentClass.addToFields(hashcode_field(in));
	}
	
	/**
	 * Creates the operation 'equals' for <arg>in<\arg>.
	 * See ITEM 7 in Effective Java.
	 * The operation compares all non-derived attributes.
	 * 
	 * @param in
	 * @return
	 */
	protected OJOperation equal_op(IDataType in){
		String param = StringHelpers.firstCharToLower(in.getName());
		String TYPE = in.getName();

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
		param = "par";
		Iterator it = in.getAttributes().iterator();
		while( it.hasNext()){
			IAttribute elem = (IAttribute) it.next();
			StructuralFeatureMap mapper = new StructuralFeatureMap(elem);
			if (!elem.isDerived()) { // only compare the non redundant fields
				IClassifier type = elem.getType();
				String name = mapper.getter() + "()";
				oper.getBody().addToStatements(compareInnerElements(param, type, name));		
			}
		}
		oper.getBody().addToStatements("return true");
		return oper;
	}
	
	// TODO replace Float.floatToIntBits by something from 'typer'
	protected OJIfStatement compareInnerElements(String param, IClassifier type, String name) {
		OJIfStatement stat = new OJIfStatement();
		if (type == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName)
		 || type == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName)){
			stat.setCondition("this." + name + " != " + param + "." + name);
		} else if (type == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.RealTypeName) ) {
			stat.setCondition("Float.floatToIntBits(this." + name + ") != " + 
							  "Float.floatToIntBits( " + param + "." + name + ")");
		} else {
			StringBuffer bodyStr = new StringBuffer();
			bodyStr.append("!(this." + name + " == null ? ");
			bodyStr.append(param + "." + name + " == null : ");
			bodyStr.append("this." + name + ".equals( ");
			bodyStr.append(param + "." + name + " ))");
			stat.setCondition(bodyStr.toString());
		}
		stat.addToThenPart("return false");
		return stat;	
	}	
	

	
	protected OJField hashcode_field(IDataType c){
		OJField field = new OJField();
		field.setName("hashCode");
		field.setType(JavaPathNames.Int);
		field.setVisibility(OJVisibilityKind.PRIVATE);
		field.setVolatile(true);
		field.setInitExp("0");
		field.setComment("See Item 8 from \'Effective Java\'");
		return field;
	}

	private void hashcode_op(IDataType in, OJClass owner){
		OJIfStatement ifStat = null;
		/**<octel var="owner">
			<method type="%JavaPathNames.Int%" name="hashCode">
			 	<comment>See Item 8 from 'Effective Java'
				</comment>
				<body>
				<if var="ifStat"> hashCode == 0 <then>
					int result = 17;
				</then></if>
				return hashCode;
				</body>
			</method> 		 
		 </octel>*/

/* <octel> */ 

/* <method> */ 
OJOperation method1 = new OJOperation();
owner.addToOperations(method1);

/* <type> */ 
method1.setReturnType(JavaPathNames.Int);

/* <type/> */ 

/* <name> */ 
method1.setName("hashCode");

/* <name/> */ 

/* <comment> */ 
method1.setComment("See Item 8 from 'Effective Java'");

/* <comment/> */ 

/* <body> */ 
OJBlock body2 = new OJBlock();
method1.setBody(body2);

/* <if> */ 
ifStat = new OJIfStatement();
ifStat.setCondition("hashCode == 0");
body2.addToStatements(ifStat);

/* <then> */ 
OJBlock then2 = new OJBlock();
ifStat.setThenPart(then2);

/* <exp> */ 
OJSimpleStatement exp3 = new OJSimpleStatement("int result = 17");
then2.addToStatements( exp3 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp4 = new OJSimpleStatement("return hashCode");
body2.addToStatements( exp4 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		
		OJBlock thenPart = ifStat.getThenPart();
		Iterator it = in.getAttributes().iterator();
		while( it.hasNext()){
			IAttribute elem = (IAttribute) it.next();
			StructuralFeatureMap mapper = new StructuralFeatureMap(elem);
			String fieldname = mapper.fieldname();
			if (!elem.isDerived()) { // only use the non redundant fields
				if (elem.getType() == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName) ) {
					thenPart.addToStatements("result = 37*result + " + fieldname);
				} else if (elem.getType() == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.BooleanTypeName) ) {
					thenPart.addToStatements("result = 37*result + (new Boolean(" + fieldname + ")).hashCode()");	 					
				} else if (elem.getType() == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.RealTypeName) ) {
					thenPart.addToStatements("result = 37*result + Float.floatToIntBits(" + fieldname + ")");					 
				} else {
					thenPart.addToStatements("result = 37*result + " + fieldname + ".hashCode()");
				}
			}
		}
		thenPart.addToStatements("hashCode = result");
	}
}
