/*
 * Created on Jul 11, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;
/**<octel>
<java>
	%import net.sf.nakeduml.javametamodel.OJParameter%;
	%inet.sfsf.nakeduml.javametamodelametamodel.model.OJSimpleStatementnet.sf.sf.nakeduml.javametamodell.javametamodel.model.OJIfStanet.sft.sf.nakeduml.javametamodelkeduml.javametamodel.model.OJOperation%;
</java>
</octel>*/

/* <octel> */ 

/* <java> */ 

/* <inline> */ 
import java.util.ArrayList;
import java.util.List;

import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.OJVisibilityKind;
import net.sf.nakeduml.javametamodel.utilities.JavaPathNames;
import nl.klasse.octopus.codegen.helpers.CommonNames;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IOperation;

/**
 * ComparatorGenerator : 
 */
public class ComparatorCreator {

	/**
	 * 
	 */
	public ComparatorCreator() {
		super();
	}

	static private String buildComparatorName(IClassifier elemType, IClassifier exprType){
		String elemTypeName = new ClassifierMap(elemType).javaType();
		String exprTypeName = new ClassifierMap(exprType).javaType();
		return "Comp" + elemTypeName + "On" + exprTypeName;		
	}


	/** Creates a comparator and returns the path name for use as import.
	 * 
	 * @param string
	 * @param expStr
	 * @return 
	 */
	static public OJPathName makeComparator(IClassifier elementType, IClassifier exprType, String expStr, String iterVarName) {
		OJClass created = new OJClass();
		OJPackage utilPack = UtilityCreator.getUtilPack();
		if (utilPack != null) utilPack.addToClasses(created);
		created.setComment(CommonNames.standardClassComment);
		created.setName(buildComparatorName(elementType, exprType));
		created.addToImplementedInterfaces(JavaPathNames.Comparator);
		created.setVisibility(OJVisibilityKind.PUBLIC);
		makeCompareOp(created,elementType, exprType, expStr, iterVarName);
		created.addToImports(JavaPathNames.Comparator);
		created.addToImports(new ClassifierMap(elementType).javaTypePath());
		OJPathName result = created.getPathName();
		return result;
	}
	

	/**
	 * Creates an operation of more or less the following form:
	 * 
	 * 	public int compare(Object arg0, Object arg1) {
	 *		TYPENAME var1 = null;
	 *		TYPENAME var2 = null;
	 *		if (arg0 instanceof TYPENAME) var1 = (TYPENAME) arg0;
	 *		if (arg1 instanceof TYPENAME) var2 = (TYPENAME) arg1;
	 *		if (var1 != null && var2 != null) {
	 *			return var1.EXPSTRING.compareTo(var2.EXPSTRING) ;
	 *		}
	 *		return 0;
	 *  }
	 *
	 * Where TYPENAME is <arg>elemType<\arg>.getName(), and EXPSTRING is <arg>expString<\arg>.
	 * 
	 * @param typer
	 * @param type
	 * @param expString
	 * @return
	 */
	private static void makeCompareOp(OJClass owner, IClassifier elemType, IClassifier exprType, String expString, String iterVarName) {
		ClassifierMap elemTypeMap = new ClassifierMap(elemType);
		ClassifierMap exprTypeMap = new ClassifierMap(exprType);
		String elemTypeName = elemTypeMap.javaType();
		String exprTypeName = exprTypeMap.javaType();
		String exprDefault  = exprTypeMap.javaDefaultValue();
		List<IClassifier> paramTypes = new ArrayList<IClassifier>();
		paramTypes.add(exprType);
		owner.addToImports(elemTypeMap.javaTypePath());
		owner.addToImports(exprTypeMap.javaTypePath());
		
		IOperation lessOper = exprType.findOperation("<", paramTypes);
		IOperation moreOper = exprType.findOperation(">", paramTypes);
		String lessOperName = "<";
		String moreOperName = ">";
		if (lessOper != null && moreOper != null) {
			if (lessOper.getReturnType().getName().equals("Boolean") &&
				moreOper.getReturnType().getName().equals("Boolean") )  
			{
				OperationMap mapper = new OperationMap(lessOper);
				lessOperName = mapper.javaOperName();
				mapper = new OperationMap(moreOper);
				moreOperName = mapper.javaOperName();
			}
		}

		//
		OJBlock body= new OJBlock(); 
		/**<octel var="owner">
			<method type="%JavaPathNames.Int%" name="compare" visibility="%OJVisibilityKind.PUBLIC%">
			<param type="%JavaPathNames.Object%" name="arg0"/>
			<param type="%JavaPathNames.Object%" name="arg1"/>
			<body var="body">
				<if> arg0 == null || arg1 == null <then>
					return 0;
				</then></if>
			
				int result = 0;
				%exprTypeName% value0 = %exprDefault%;
				%exprTypeName% value1 = %exprDefault%;
				%elemTypeName% %iterVarName% = null;
				<if> arg0 instanceof %elemTypeName% <then>
					%iterVarName% = (%elemTypeName%) arg0;
					value0 = %expString%;
				</then></if>
				<if> arg1 instanceof %elemTypeName% <then>
					%iterVarName% = (%elemTypeName%) arg1;
					value1 = %expString%;
				</then></if>
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
method1.setName("compare");

/* <name/> */ 

/* <visibility> */ 
method1.setVisibility(OJVisibilityKind.PUBLIC);

/* <visibility/> */ 

/* <param> */ 
OJParameter param1 = new OJParameter();
method1.addToParameters(param1);

/* <type> */ 
param1.setType(JavaPathNames.Object);

/* <type/> */ 

/* <name> */ 
param1.setName("arg0");

/* <name/> */ 

/* <param/> */ 

/* <param> */ 
OJParameter param2 = new OJParameter();
method1.addToParameters(param2);

/* <type> */ 
param2.setType(JavaPathNames.Object);

/* <type/> */ 

/* <name> */ 
param2.setName("arg1");

/* <name/> */ 

/* <param/> */ 

/* <body> */ 
body = new OJBlock();
method1.setBody(body);

/* <if> */ 
OJIfStatement if1 = new OJIfStatement();
if1.setCondition("arg0 == null || arg1 == null");
body.addToStatements(if1);

/* <then> */ 
OJBlock then1 = new OJBlock();
if1.setThenPart(then1);

/* <exp> */ 
OJSimpleStatement exp1 = new OJSimpleStatement("return 0");
then1.addToStatements( exp1 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp2 = new OJSimpleStatement("int result = 0");
body.addToStatements( exp2 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp3 = new OJSimpleStatement(exprTypeName + " value0 = " + exprDefault);
body.addToStatements( exp3 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp4 = new OJSimpleStatement(exprTypeName + " value1 = " + exprDefault);
body.addToStatements( exp4 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp5 = new OJSimpleStatement(elemTypeName + " " + iterVarName + " = null");
body.addToStatements( exp5 );

/* <exp/> */ 

/* <if> */ 
OJIfStatement if2 = new OJIfStatement();
if2.setCondition("arg0 instanceof " + elemTypeName);
body.addToStatements(if2);

/* <then> */ 
OJBlock then2 = new OJBlock();
if2.setThenPart(then2);

/* <exp> */ 
OJSimpleStatement exp6 = new OJSimpleStatement(iterVarName + " = (" + elemTypeName + ") arg0");
then2.addToStatements( exp6 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp7 = new OJSimpleStatement("value0 = " + expString);
then2.addToStatements( exp7 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <if> */ 
OJIfStatement if3 = new OJIfStatement();
if3.setCondition("arg1 instanceof " + elemTypeName);
body.addToStatements(if3);

/* <then> */ 
OJBlock then3 = new OJBlock();
if3.setThenPart(then3);

/* <exp> */ 
OJSimpleStatement exp8 = new OJSimpleStatement(iterVarName + " = (" + elemTypeName + ") arg1");
then3.addToStatements( exp8 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp9 = new OJSimpleStatement("value1 = " + expString);
then3.addToStatements( exp9 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		
		if (exprTypeName.equals("boolean")) { 
			/**<octel var="body">
				<if> value0 == value1 <then>
					return  0;
				</then></if>
				<if> value0 == true &amp;&amp; value1 == false <then>
					return  -1;
				</then></if>
				<if> value0 == false &amp;&amp; value1 == true <then>
					return  1;
				</then></if>
				<exp>return result</exp>
			</octel>*/

/* <octel> */ 

/* <if> */ 
OJIfStatement if4 = new OJIfStatement();
if4.setCondition("value0 == value1");
body.addToStatements(if4);

/* <then> */ 
OJBlock then4 = new OJBlock();
if4.setThenPart(then4);

/* <exp> */ 
OJSimpleStatement exp10 = new OJSimpleStatement("return  0");
then4.addToStatements( exp10 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <if> */ 
OJIfStatement if5 = new OJIfStatement();
if5.setCondition("value0 == true && value1 == false");
body.addToStatements(if5);

/* <then> */ 
OJBlock then5 = new OJBlock();
if5.setThenPart(then5);

/* <exp> */ 
OJSimpleStatement exp11 = new OJSimpleStatement("return  -1");
then5.addToStatements( exp11 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <if> */ 
OJIfStatement if6 = new OJIfStatement();
if6.setCondition("value0 == false && value1 == true");
body.addToStatements(if6);

/* <then> */ 
OJBlock then6 = new OJBlock();
if6.setThenPart(then6);

/* <exp> */ 
OJSimpleStatement exp12 = new OJSimpleStatement("return  1");
then6.addToStatements( exp12 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp13 = new OJSimpleStatement("return result");
body.addToStatements( exp13 );

/* <exp/> */ 

/* <octel/> */ 


		} else if (exprTypeName.equals("String")) {
			/**<octel var="body">
				<exp>return value0.compareTo(value1)</exp>
			</octel>*/

/* <octel> */ 

/* <exp> */ 
OJSimpleStatement exp14 = new OJSimpleStatement("return value0.compareTo(value1)");
body.addToStatements( exp14 );

/* <exp/> */ 

/* <octel/> */ 


		} else if (exprTypeName.equals("int") || exprTypeName.equals("float")) {
			/**<octel var="body">
				<if> value0 &lt; value1 <then>
					result = -1;
				</then></if>
				<if> value0 == value1 <then>
					result = 0;
				</then></if>
				<if> value0 > value1 <then>
					result = 1;
				</then></if>
				<exp>return result</exp>
			</octel>*/

/* <octel> */ 

/* <if> */ 
OJIfStatement if7 = new OJIfStatement();
if7.setCondition("value0 < value1");
body.addToStatements(if7);

/* <then> */ 
OJBlock then7 = new OJBlock();
if7.setThenPart(then7);

/* <exp> */ 
OJSimpleStatement exp15 = new OJSimpleStatement("result = -1");
then7.addToStatements( exp15 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <if> */ 
OJIfStatement if8 = new OJIfStatement();
if8.setCondition("value0 == value1");
body.addToStatements(if8);

/* <then> */ 
OJBlock then8 = new OJBlock();
if8.setThenPart(then8);

/* <exp> */ 
OJSimpleStatement exp16 = new OJSimpleStatement("result = 0");
then8.addToStatements( exp16 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <if> */ 
OJIfStatement if9 = new OJIfStatement();
if9.setCondition("value0 > value1");
body.addToStatements(if9);

/* <then> */ 
OJBlock then9 = new OJBlock();
if9.setThenPart(then9);

/* <exp> */ 
OJSimpleStatement exp17 = new OJSimpleStatement("result = 1");
then9.addToStatements( exp17 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp18 = new OJSimpleStatement("return result");
body.addToStatements( exp18 );

/* <exp/> */ 

/* <octel/> */ 


		} else {
			/**<octel var="body">
				<if> value0.%moreOperName%( value1 )<then>
					result = 1;
				</then></if>
				<if> value0 == value1 <then>
					result = 0;
				</then></if>
				<if> value0.%lessOperName%( value1 )<then>
					result = -1;
				</then></if>
				<exp>return result</exp>
			</octel>*/

/* <octel> */ 

/* <if> */ 
OJIfStatement if10 = new OJIfStatement();
if10.setCondition("value0." + moreOperName + "( value1 )");
body.addToStatements(if10);

/* <then> */ 
OJBlock then10 = new OJBlock();
if10.setThenPart(then10);

/* <exp> */ 
OJSimpleStatement exp19 = new OJSimpleStatement("result = 1");
then10.addToStatements( exp19 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <if> */ 
OJIfStatement if11 = new OJIfStatement();
if11.setCondition("value0 == value1");
body.addToStatements(if11);

/* <then> */ 
OJBlock then11 = new OJBlock();
if11.setThenPart(then11);

/* <exp> */ 
OJSimpleStatement exp20 = new OJSimpleStatement("result = 0");
then11.addToStatements( exp20 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <if> */ 
OJIfStatement if12 = new OJIfStatement();
if12.setCondition("value0." + lessOperName + "( value1 )");
body.addToStatements(if12);

/* <then> */ 
OJBlock then12 = new OJBlock();
if12.setThenPart(then12);

/* <exp> */ 
OJSimpleStatement exp21 = new OJSimpleStatement("result = -1");
then12.addToStatements( exp21 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp22 = new OJSimpleStatement("return result");
body.addToStatements( exp22 );

/* <exp/> */ 

/* <octel/> */ 


		}
	}
}
