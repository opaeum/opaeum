/*
 * Created on Jun 19, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;
/**<octel>
<java>
	%import org.opeum.javametamodel.OJParameter%;
	%inet.sfsf.opeum.javametamodelametamodel.model.OJFieldnet.sf.sf.opeum.javametamodell.javametamodel.model.OJSimpleStanet.sft.sf.opeum.javametamodelkeduml.javametamodel.model.Onet.sfet.sf.opeum.javametamodelsf.opeum.javametamnet.sforg.opeum.javametamnet.sf org.opeum.javametnet.sfl.org.opeum.javametamodenet.sfrt org.opeum.javametamodel.utilities.JavaPathNames%;
</java>
</octel>*/

/* <octel> */ 

/* <java> */ 

/* <inline> */ 
import nl.klasse.octopus.codegen.helpers.CommonNames;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJConstructor;
import org.opeum.java.metamodel.OJField;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJParameter;
import org.opeum.java.metamodel.OJSimpleStatement;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.utilities.JavaPathNames;


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

/* <octel> */ 

/* <field> */ 
OJField field1 = new OJAnnotatedField("instance",JavaPathNames.Object);
result.addToFields(field1);

/* <type> */ 
//field1.setType(JavaPathNames.Object);

/* <type/> */ 

/* <name> */ 
//field1.setName("instance");

/* <name/> */ 

/* <init> */ 
field1.setInitExp("null");

/* <init/> */ 

/* <field/> */ 

/* <field> */ 
OJField field2 = new OJAnnotatedField("message",JavaPathNames.String);
result.addToFields(field2);

/* <type> */ 
//field2.setType(JavaPathNames.String);

/* <type/> */ 

/* <name> */ 
//field2.setName("message");

/* <name/> */ 

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

/* <type> */ 
param1.setType(JavaPathNames.Object);

/* <type/> */ 

/* <name> */ 
param1.setName("instance");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param2 = new OJParameter();
constructor1.addToParameters(param2);

/* <type> */ 
param2.setType(JavaPathNames.String);

/* <type/> */ 

/* <name> */ 
param2.setName("message");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body1 = new OJBlock();
constructor1.setBody(body1);

/* <exp> */ 
OJSimpleStatement exp1 = new OJSimpleStatement("this.message = message");
body1.addToStatements( exp1 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp2 = new OJSimpleStatement("this.instance = instance");
body1.addToStatements( exp2 );

/* <exp/> */ 

/* <body/> */ 

/* <constructor/> */ 

/* <method> */ 
OJOperation method1 = new OJOperation();
result.addToOperations(method1);

/* <type> */ 
method1.setReturnType(JavaPathNames.String);

/* <type/> */ 

/* <name> */ 
method1.setName("getMessage");

/* <name/> */ 

/* <comment> */ 
method1.setComment("returns the reason why this error has occurred.");

/* <comment/> */ 

/* <body> */ 
OJBlock body2 = new OJBlock();
method1.setBody(body2);

/* <exp> */ 
OJSimpleStatement exp3 = new OJSimpleStatement("return message");
body2.addToStatements( exp3 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <method> */ 
OJOperation method2 = new OJOperation();
result.addToOperations(method2);

/* <type> */ 
method2.setReturnType(JavaPathNames.Void);

/* <type/> */ 

/* <name> */ 
method2.setName("setMessage");

/* <name/> */ 

/* <comment> */ 
method2.setComment("set the reason why this error has occurred.");

/* <comment/> */ 

/* <param> */ 
OJParameter param3 = new OJParameter();
method2.addToParameters(param3);

/* <type> */ 
param3.setType(JavaPathNames.String);

/* <type/> */ 

/* <name> */ 
param3.setName("string");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body3 = new OJBlock();
method2.setBody(body3);

/* <exp> */ 
OJSimpleStatement exp4 = new OJSimpleStatement("message = string");
body3.addToStatements( exp4 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <method> */ 
OJOperation method3 = new OJOperation();
result.addToOperations(method3);

/* <type> */ 
method3.setReturnType(JavaPathNames.Object);

/* <type/> */ 

/* <name> */ 
method3.setName("getInstance");

/* <name/> */ 

/* <comment> */ 
method3.setComment("returns the instance in which this error has occurred.");

/* <comment/> */ 

/* <body> */ 
OJBlock body4 = new OJBlock();
method3.setBody(body4);

/* <exp> */ 
OJSimpleStatement exp5 = new OJSimpleStatement("return instance");
body4.addToStatements( exp5 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <method> */ 
OJOperation method4 = new OJOperation();
result.addToOperations(method4);

/* <type> */ 
method4.setReturnType(JavaPathNames.Void);

/* <type/> */ 

/* <name> */ 
method4.setName("setInstance");

/* <name/> */ 

/* <comment> */ 
method4.setComment("sets the instance in which this error has occurred.");

/* <comment/> */ 

/* <param> */ 
OJParameter param4 = new OJParameter();
method4.addToParameters(param4);

/* <type> */ 
param4.setType(JavaPathNames.Object);

/* <type/> */ 

/* <name> */ 
param4.setName("object");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body5 = new OJBlock();
method4.setBody(body5);

/* <exp> */ 
OJSimpleStatement exp6 = new OJSimpleStatement("instance = object");
body5.addToStatements( exp6 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <method> */ 
OJOperation method5 = new OJOperation();
result.addToOperations(method5);

/* <type> */ 
method5.setReturnType(JavaPathNames.String);

/* <type/> */ 

/* <name> */ 
method5.setName("toString");

/* <name/> */ 

/* <comment> */ 
method5.setComment("returns a printable version of this error.");

/* <comment/> */ 

/* <body> */ 
OJBlock body6 = new OJBlock();
method5.setBody(body6);

/* <exp> */ 
OJSimpleStatement exp7 = new OJSimpleStatement("return getMessage()");
body6.addToStatements( exp7 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


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

/* <octel> */ 

/* <superclass> */ 
result.setSuperclass(JavaPathNames.Exception);

/* <superclass/> */ 

/* <field> */ 
OJField field3 = new OJField();
result.addToFields(field3);

/* <type> */ 
field3.setType(JavaPathNames.Object);

/* <type/> */ 

/* <name> */ 
field3.setName("instance");

/* <name/> */ 

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

/* <type> */ 
param5.setType(JavaPathNames.Object);

/* <type/> */ 

/* <name> */ 
param5.setName("instance");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param6 = new OJParameter();
constructor2.addToParameters(param6);

/* <type> */ 
param6.setType(JavaPathNames.String);

/* <type/> */ 

/* <name> */ 
param6.setName("message");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
OJBlock body7 = new OJBlock();
constructor2.setBody(body7);

/* <exp> */ 
OJSimpleStatement exp8 = new OJSimpleStatement("super(message)");
body7.addToStatements( exp8 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp9 = new OJSimpleStatement("this.instance = instance");
body7.addToStatements( exp9 );

/* <exp/> */ 

/* <body/> */ 

/* <constructor/> */ 

/* <method> */ 
OJOperation method6 = new OJOperation();
result.addToOperations(method6);

/* <type> */ 
method6.setReturnType(JavaPathNames.Object);

/* <type/> */ 

/* <name> */ 
method6.setName("getInstance");

/* <name/> */ 

/* <body> */ 
OJBlock body8 = new OJBlock();
method6.setBody(body8);

/* <exp> */ 
OJSimpleStatement exp10 = new OJSimpleStatement("return instance");
body8.addToStatements( exp10 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		return result;
	}

}
