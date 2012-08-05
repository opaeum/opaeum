/*
 * Created on Jun 19, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;
/**<octel>
<java>
	%import org.opaeum.javametamodel.OJParameter%;
	%inet.sfsf.opaeum.javametamodelametamodel.model.OJFieldnet.sf.sf.opaeum.javametamodell.javametamodel.model.OJSimpleStanet.sft.sf.opaeum.javametamodelkeduml.javametamodel.model.Onet.sfet.sf.opaeum.javametamodelsf.opaeum.javametamnet.sforg.opaeum.javametamnet.sf org.opaeum.javametnet.sfl.org.opaeum.javametamodenet.sfrt org.opaeum.javametamodel.utilities.JavaPathNames%;
</java>
</octel>*/

 

 

 
import nl.klasse.octopus.codegen.helpers.CommonNames;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.utilities.JavaPathNames;


/**
 * InvariantHelpersGenerator : 
 */
public class InvHelpersCreator {

	/**
	 * 
	 */
	public InvHelpersCreator() {
		super();
	}

	/**
	 * @return
	 */
	public OJClass makeInvariantError() {
		OJClass result = new OJClass();
		result.setComment(CommonNames.standardClassComment);
		result.setName("InvariantError");
//		OJPathName IModelObject = UtilityCreator.getModelObjectPath();

		/**<octel var="result">
		<field type="%JavaPathNames.Object%" name="instance" init="null"/>
		<field type="%JavaPathNames.String%" name="message" init="&quot;&quot;"/>

		<constructor> 
		<param type="%JavaPathNames.Object%" name="instance"/>
		<param type="%JavaPathNames.String%" name="message"/>
		<body>
			this.message = message;
			this.instance = instance;
		</body>
		</constructor>
		
		<method type="%JavaPathNames.String%" name="getMessage">
		<comment> returns the reason why this error has occurred. </comment>
		<body>
			return message;
		</body>
		</method>

		<method type="%JavaPathNames.Void%" name ="setMessage">
		<comment> set the reason why this error has occurred. </comment>
		<param type="%JavaPathNames.String%" name="string"/>
		<body>
			message = string;
		</body>
		</method>

		<method type="%JavaPathNames.Object%" name="getInstance">
		<comment> returns the instance in which this error has occurred. </comment>
		<body>
			return instance;
		</body>
		</method>		

		<method type="%JavaPathNames.Void%" name="setInstance">
		<comment> sets the instance in which this error has occurred. </comment>
		<param type="%JavaPathNames.Object%" name="object"/>
		<body>
			instance = object;
		</body>
		</method>
		
		<method type="%JavaPathNames.String%" name="toString">
		<comment> returns a printable version of this error. </comment>
		<body>
			return getMessage();
		</body>
		</method>

		</octel>*/

 

/* <field> */ 
OJField field1 = new OJAnnotatedField("instance",JavaPathNames.Object);
result.addToFields(field1);

 
//field1.setType(JavaPathNames.Object);

 

 
//field1.setName("instance");

 

/* <init> */ 
field1.setInitExp("null");

/* <init/> */ 

/* <field/> */ 

/* <field> */ 
OJField field2 = new OJAnnotatedField("message",JavaPathNames.String);
result.addToFields(field2);

 
//field2.setType(JavaPathNames.String);

 

 
//field2.setName("message");

 

/* <init> */ 
field2.setInitExp("\"\"");

/* <init/> */ 

/* <field/> */ 

/* <constructor> */ 
OJConstructor constructor1 = new OJConstructor();
result.addToConstructors(constructor1);

/* <param> */ 
OJParameter param1 = new OJParameter();
constructor1.addToParameters(param1);

 
param1.setType(JavaPathNames.Object);

 

 
param1.setName("instance");

 

/* <param/> */ 

/* <param> */ 
OJParameter param2 = new OJParameter();
constructor1.addToParameters(param2);

 
param2.setType(JavaPathNames.String);

 

 
param2.setName("message");

 

/* <param/> */ 

 
OJBlock body1 = new OJBlock();
constructor1.setBody(body1);

 
OJSimpleStatement exp1 = new OJSimpleStatement("this.message = message");
body1.addToStatements( exp1 );

 

 
OJSimpleStatement exp2 = new OJSimpleStatement("this.instance = instance");
body1.addToStatements( exp2 );

 

 

/* <constructor/> */ 

/* <method> */ 
OJOperation method1 = new OJOperation();
result.addToOperations(method1);

 
method1.setReturnType(JavaPathNames.String);

 

 
method1.setName("getMessage");

 

 
method1.setComment("returns the reason why this error has occurred.");

 

 
OJBlock body2 = new OJBlock();
method1.setBody(body2);

 
OJSimpleStatement exp3 = new OJSimpleStatement("return message");
body2.addToStatements( exp3 );

 

 

 

/* <method> */ 
OJOperation method2 = new OJOperation();
result.addToOperations(method2);

 
method2.setReturnType(JavaPathNames.Void);

 

 
method2.setName("setMessage");

 

 
method2.setComment("set the reason why this error has occurred.");

 

/* <param> */ 
OJParameter param3 = new OJParameter();
method2.addToParameters(param3);

 
param3.setType(JavaPathNames.String);

 

 
param3.setName("string");

 

/* <param/> */ 

 
OJBlock body3 = new OJBlock();
method2.setBody(body3);

 
OJSimpleStatement exp4 = new OJSimpleStatement("message = string");
body3.addToStatements( exp4 );

 

 

 

/* <method> */ 
OJOperation method3 = new OJOperation();
result.addToOperations(method3);

 
method3.setReturnType(JavaPathNames.Object);

 

 
method3.setName("getInstance");

 

 
method3.setComment("returns the instance in which this error has occurred.");

 

 
OJBlock body4 = new OJBlock();
method3.setBody(body4);

 
OJSimpleStatement exp5 = new OJSimpleStatement("return instance");
body4.addToStatements( exp5 );

 

 

 

/* <method> */ 
OJOperation method4 = new OJOperation();
result.addToOperations(method4);

 
method4.setReturnType(JavaPathNames.Void);

 

 
method4.setName("setInstance");

 

 
method4.setComment("sets the instance in which this error has occurred.");

 

/* <param> */ 
OJParameter param4 = new OJParameter();
method4.addToParameters(param4);

 
param4.setType(JavaPathNames.Object);

 

 
param4.setName("object");

 

/* <param/> */ 

 
OJBlock body5 = new OJBlock();
method4.setBody(body5);

 
OJSimpleStatement exp6 = new OJSimpleStatement("instance = object");
body5.addToStatements( exp6 );

 

 

 

/* <method> */ 
OJOperation method5 = new OJOperation();
result.addToOperations(method5);

 
method5.setReturnType(JavaPathNames.String);

 

 
method5.setName("toString");

 

 
method5.setComment("returns a printable version of this error.");

 

 
OJBlock body6 = new OJBlock();
method5.setBody(body6);

 
OJSimpleStatement exp7 = new OJSimpleStatement("return getMessage()");
body6.addToStatements( exp7 );

 

 

 

 


		return result;
	}

	/**
	 * @return
	 */
	public OJClass makeInvariantException() {
		OJClass result = new OJClass();
		result.setComment(CommonNames.standardClassComment + "InvariantException: instances are thrown when an invariant is broken.\n" + 
				"This class cannot be implemented without the compiler warning about 'static final serialVersionUID'\n" +
				"The best option is to ignore this warning.");
		result.setName("InvariantException");
		// this class cannot be implemented without the compiler warning about 'static final serialVersionUID'
		// best option is to ignore this warning
		result.setNeedsSuppress(true);
		
		/**<octel var="result">
		<superclass>%JavaPathNames.Exception%</superclass>
		<field type="%JavaPathNames.Object%" name="instance" init="null"/>

		<constructor> 
		<param type="%JavaPathNames.Object%" name="instance"/>
		<param type="%JavaPathNames.String%" name="message"/>
		<body>
			super(message);
			this.instance = instance;
		</body>
		</constructor>

		<method type="%JavaPathNames.Object%" name="getInstance">
		<body>
			return instance;
		</body>
		</method>		

		</octel>*/

 

/* <superclass> */ 
result.setSuperclass(JavaPathNames.Exception);

/* <superclass/> */ 

/* <field> */ 
OJField field3 = new OJField();
result.addToFields(field3);

 
field3.setType(JavaPathNames.Object);

 

 
field3.setName("instance");

 

/* <init> */ 
field3.setInitExp("null");

/* <init/> */ 

/* <field/> */ 

/* <constructor> */ 
OJConstructor constructor2 = new OJConstructor();
result.addToConstructors(constructor2);

/* <param> */ 
OJParameter param5 = new OJParameter();
constructor2.addToParameters(param5);

 
param5.setType(JavaPathNames.Object);

 

 
param5.setName("instance");

 

/* <param/> */ 

/* <param> */ 
OJParameter param6 = new OJParameter();
constructor2.addToParameters(param6);

 
param6.setType(JavaPathNames.String);

 

 
param6.setName("message");

 

/* <param/> */ 

 
OJBlock body7 = new OJBlock();
constructor2.setBody(body7);

 
OJSimpleStatement exp8 = new OJSimpleStatement("super(message)");
body7.addToStatements( exp8 );

 

 
OJSimpleStatement exp9 = new OJSimpleStatement("this.instance = instance");
body7.addToStatements( exp9 );

 

 

/* <constructor/> */ 

/* <method> */ 
OJOperation method6 = new OJOperation();
result.addToOperations(method6);

 
method6.setReturnType(JavaPathNames.Object);

 

 
method6.setName("getInstance");

 

 
OJBlock body8 = new OJBlock();
method6.setBody(body8);

 
OJSimpleStatement exp10 = new OJSimpleStatement("return instance");
body8.addToStatements( exp10 );

 

 

 

 


		return result;
	}

}
