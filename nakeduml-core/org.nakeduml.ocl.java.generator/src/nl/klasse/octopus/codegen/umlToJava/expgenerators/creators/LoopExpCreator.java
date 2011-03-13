/*
 * Created on Jan 8, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;
/**<octel>
<java>
	%import net.sf.nakeduml.javametamodel.OJWhileStatement%;
	%inet.sfsf.nakeduml.javametamodelametamodel.model.OJForStatementnet.sf.sf.nakeduml.javametamodell.javametamodel.model.OJSimpleStanet.sft.sf.nakeduml.javametamodelkeduml.javametamodel.model.Onet.sfet.sf.nakeduml.javametamodelsf.nakeduml.javametamodel.model.OJBlock%;
</java>
</octel>*/

/* <octel> */ 

/* <java> */ 

/* <inline> */ 
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.common.ExpGeneratorHelper;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.internal.types.IterateExp;
import nl.klasse.octopus.expressions.internal.types.IteratorExp;
import nl.klasse.octopus.expressions.internal.types.LoopExp;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.ICollectionType;
import nl.klasse.octopus.stdlib.internal.types.StdlibCollectionType;
import nl.klasse.tools.common.StringHelpers;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJClass;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.OJWhileStatement;
import org.nakeduml.java.metamodel.utilities.JavaPathNames;

/**
 * PropCallTransformer : 
 */
public class LoopExpCreator {
	private OJClass			myClass				= null;
//	private String				resultName  		= "result";
	private OJVisibilityKind 	priv 					= OJVisibilityKind.PRIVATE;
	private String[] 			iterVarNames 		= {"it"};
	private String 			resultType 			= "";
	private OJPathName 	resultTypePath 	= null;
	private String 			resultDefault 		= "";
	private String 			myListType 		= "";
	private OJPathName 	myListTypePath 	= null;
	private String 			myListDefault		= "";
	private String 			expStr				= "";
	private String 			expType				= "";
	private String 			operName			= "";
	private IClassifier 		elementType 		= null;

	/**
	 * 
	 */
	public LoopExpCreator(OJClass myClass) {
		super();
		this.myClass = myClass;
	}

	/**
	 * @param exp
	 * @param source
	 * @return
	 */
	public String iteratorExp(IteratorExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		String result = "";
		setVariables(exp, isStatic, params);
		String iteratorName = exp.getName();
		if (iteratorName.equals("forAll")){
			result = createForAll(exp, source, isStatic, params);
		} else if (iteratorName.equals("exists")){
			result = createExists(exp, source, isStatic, params);
		} else if (iteratorName.equals("isUnique")){
			result = createIsUnique(exp, source, isStatic, params);
		} else if (iteratorName.equals("one")){
			result = createOne(exp, source, isStatic, params);
		} else if (iteratorName.equals("any")) {
			result = createAny(exp, source, isStatic, params);
		} else if (iteratorName.equals("reject")){
			result = createReject(exp, source, isStatic, params);
		} else if (iteratorName.equals("select")) {
			result = createSelect(exp, source, isStatic, params);
		} else if (iteratorName.equals("sortedBy")) {
			result = createSortedBy(exp, source, isStatic, params);
		} else if (iteratorName.equals("collectNested")){
			result = createCollectNested(exp, source, isStatic, params);
		} else if (iteratorName.equals("collect")) {
			result = createCollect(exp, source, isStatic, params);
		}
		// add myListTypePath to the imports, except for the sortedBy which does not use it.
		if (!iteratorName.equals("sortedBy")) {
			myClass.addToImports(myListTypePath);
		}
		return result;
	}
		
	/**
	 * @param object
	 * @return
	 */
	public String iterateExp(IterateExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		// get the result variable of the expression
		ExpressionCreator maker = new ExpressionCreator(myClass);
		String varDeclStr = maker.makeVarDecl(exp.getResult(), isStatic, params);
		List<OJParameter> bodyParams = ExpGeneratorHelper.addVarToParams(exp.getResult(), params);
		String resultName = exp.getResult().getName();
		// get info
		setVariables(exp, isStatic, bodyParams);	

		// generate a separate operation
		OJOperation oper = null;
		if (exp.getIterators().size() == 2) {
			//TODO test this implementation, it seems wrong
			/**<octel var="myClass">
				<method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
				<comment> implements %exp.toString()% </comment>
				<paramlist> %params% </paramlist>
				<body>
					%varDeclStr%;
					Iterator it = %source%.iterator();
					<while> it.hasNext() <body>
						%myListType% %iterVarNames[0]% = (%myListType%) it.next();
						<while> it.hasNext() <body>
						%myListType% %iterVarNames[1]% = (%myListType%) it.next();
						%resultName% = %expStr%;
						</body></while>
					</body></while>
					return %resultName%;
				</body>
				</method>
			</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(resultTypePath);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body1 = new OJBlock();
oper.setBody(body1);

/* <exp> */ 
OJSimpleStatement exp1 = new OJSimpleStatement(varDeclStr);
body1.addToStatements( exp1 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp2 = new OJSimpleStatement("Iterator it = " + source + ".iterator()");
body1.addToStatements( exp2 );

/* <exp/> */ 

/* <while> */ 
OJWhileStatement while1 = new OJWhileStatement();
body1.addToStatements(while1);
while1.setCondition("it.hasNext()");

/* <body> */ 
OJBlock body2 = new OJBlock();
while1.setBody(body2);

/* <exp> */ 
OJSimpleStatement exp3 = new OJSimpleStatement(myListType + " " + iterVarNames[0] + " = (" + myListType + ") it.next()");
body2.addToStatements( exp3 );

/* <exp/> */ 

/* <while> */ 
OJWhileStatement while2 = new OJWhileStatement();
body2.addToStatements(while2);
while2.setCondition("it.hasNext()");

/* <body> */ 
OJBlock body3 = new OJBlock();
while2.setBody(body3);

/* <exp> */ 
OJSimpleStatement exp4 = new OJSimpleStatement(myListType + " " + iterVarNames[1] + " = (" + myListType + ") it.next()");
body3.addToStatements( exp4 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp5 = new OJSimpleStatement(resultName + " = " + expStr);
body3.addToStatements( exp5 );

/* <exp/> */ 

/* <body/> */ 

/* <while/> */ 

/* <body/> */ 

/* <while/> */ 

/* <exp> */ 
OJSimpleStatement exp6 = new OJSimpleStatement("return " + resultName);
body1.addToStatements( exp6 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


			myClass.addToImports(JavaPathNames.Iterator);
		} else if (exp.getIterators().size() == 1) {
			/**<octel var="myClass">
				<method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
				<comment> implements %exp.toString()% </comment>
				<paramlist> %params% </paramlist>
				<body>
					%varDeclStr%;
					<for name="%iterVarNames[0]%" type="%myListTypePath%">
					<collection>%source.toString()%</collection>
					<body>
						%resultName% = %expStr%;
					</body></for>
					return %resultName%;
				</body>
				</method>
			</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(resultTypePath);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body4 = new OJBlock();
oper.setBody(body4);

/* <exp> */ 
OJSimpleStatement exp7 = new OJSimpleStatement(varDeclStr);
body4.addToStatements( exp7 );

/* <exp/> */ 

/* <for> */ 
OJForStatement for1 = new OJForStatement();
body4.addToStatements(for1);

/* <name> */ 
for1.setElemName(iterVarNames[0]);

/* <name/> */ 

/* <type> */ 
for1.setElemType(myListTypePath);

/* <type/> */ 

/* <collection> */ 
for1.setCollection(source.toString());

/* <collection/> */ 

/* <body> */ 
OJBlock body5 = new OJBlock();
for1.setBody(body5);

/* <exp> */ 
OJSimpleStatement exp8 = new OJSimpleStatement(resultName + " = " + expStr);
body5.addToStatements( exp8 );

/* <exp/> */ 

/* <body/> */ 

/* <for/> */ 

/* <exp> */ 
OJSimpleStatement exp9 = new OJSimpleStatement("return " + resultName);
body4.addToStatements( exp9 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		}
		
		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}

	private String createCollect(IteratorExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		// make sure expressions of primitive type can be added to a collection
		expStr = ExpGeneratorHelper.makeListElem(myClass, exp.getBody().getExpressionType(), expStr);
		//
		IClassifier argType = exp.getBody().getExpressionType();
		// flatten 
		String myBodyExp = "";
		if (argType instanceof ICollectionType) {
			myBodyExp = "result.addAll( bodyExpResult )";	
		} else {
			myBodyExp = "if ( bodyExpResult != null ) result.add( bodyExpResult )";	
		}
		
		// generate operation
		OJOperation oper = null;
		/**<octel var="myClass">
			<method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
			<comment> implements %exp.toString()% </comment>
			<paramlist> %params% </paramlist>
			<body>
				%resultType% result = %resultDefault%;
				<for name="%iterVarNames[0]%" type="%myListTypePath%">
				<collection>%source.toString()%</collection>
				<body>
				%expType% bodyExpResult = %expStr%;
				%myBodyExp%;
				</body></for>
				return result;
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(resultTypePath);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body6 = new OJBlock();
oper.setBody(body6);

/* <exp> */ 
OJSimpleStatement exp10 = new OJSimpleStatement(resultType + " result = " + resultDefault);
body6.addToStatements( exp10 );

/* <exp/> */ 

/* <for> */ 
OJForStatement for2 = new OJForStatement();
body6.addToStatements(for2);

/* <name> */ 
for2.setElemName(iterVarNames[0]);

/* <name/> */ 

/* <type> */ 
for2.setElemType(myListTypePath);

/* <type/> */ 

/* <collection> */ 
for2.setCollection(source.toString());

/* <collection/> */ 

/* <body> */ 
OJBlock body7 = new OJBlock();
for2.setBody(body7);

/* <exp> */ 
OJSimpleStatement exp11 = new OJSimpleStatement(expType + " bodyExpResult = " + expStr);
body7.addToStatements( exp11 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp12 = new OJSimpleStatement(myBodyExp);
body7.addToStatements( exp12 );

/* <exp/> */ 

/* <body/> */ 

/* <for/> */ 

/* <exp> */ 
OJSimpleStatement exp13 = new OJSimpleStatement("return result");
body6.addToStatements( exp13 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


	
		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}

	private String createCollectNested(IteratorExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		// make sure expressions of primitive type can be added to a collection
		expStr = ExpGeneratorHelper.makeListElem(myClass, exp.getBody().getExpressionType(), expStr);
		// generate operation
		OJOperation oper = null;
		/**<octel var="myClass">
			<method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
			<comment> implements %exp.toString()% </comment>
			<paramlist> %params% </paramlist>
			<body>
				%resultType% result = %resultDefault%;
				<for name="%iterVarNames[0]%" type="%myListTypePath%">
				<collection>%source.toString()%</collection>
				<body>
				%expType% bodyExpResult = %expStr%;
				if ( bodyExpResult != null ) result.add( bodyExpResult );
				</body></for>
				return result;
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(resultTypePath);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body8 = new OJBlock();
oper.setBody(body8);

/* <exp> */ 
OJSimpleStatement exp14 = new OJSimpleStatement(resultType + " result = " + resultDefault);
body8.addToStatements( exp14 );

/* <exp/> */ 

/* <for> */ 
OJForStatement for3 = new OJForStatement();
body8.addToStatements(for3);

/* <name> */ 
for3.setElemName(iterVarNames[0]);

/* <name/> */ 

/* <type> */ 
for3.setElemType(myListTypePath);

/* <type/> */ 

/* <collection> */ 
for3.setCollection(source.toString());

/* <collection/> */ 

/* <body> */ 
OJBlock body9 = new OJBlock();
for3.setBody(body9);

/* <exp> */ 
OJSimpleStatement exp15 = new OJSimpleStatement(expType + " bodyExpResult = " + expStr);
body9.addToStatements( exp15 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp16 = new OJSimpleStatement("if ( bodyExpResult != null ) result.add( bodyExpResult )");
body9.addToStatements( exp16 );

/* <exp/> */ 

/* <body/> */ 

/* <for/> */ 

/* <exp> */ 
OJSimpleStatement exp17 = new OJSimpleStatement("return result");
body8.addToStatements( exp17 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 



		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	
	private String createSortedBy(IteratorExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		// TODO: Iterator not needed in this implementation but cannot remove in case others are using it
//		myClass.removeFromImports(%JavaPathNames.Iterator%);
		//
		String iterVarName = iterVarNames[0];
		OJPathName comparatorClassName = 
			ComparatorCreator.makeComparator(elementType,
										  exp.getBody().getExpressionType(),
										  expStr,
										  iterVarName);
		myClass.addToImports(JavaPathNames.Comparator);
		myClass.addToImports(JavaPathNames.Collections);
//		myClass.addToImports("utils.Util");
		myClass.addToImports(comparatorClassName);
		// generate operation
		OJOperation oper = null;
		/**<octel var="myClass">
			<method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
			<comment> implements %exp.toString()% </comment>
			<paramlist> %params% </paramlist>
			<body>
				%resultType% result = %resultDefault%;
				result.addAll(%source%);
				// System.out.println("BEFORE SORTING ON %expStr%");
				// System.out.println(Util.collectionToString(result, "\\n"));
				// create a new Comparator instance;
				Comparator comp = new %comparatorClassName.getLast()%();
				// sort the result collection;
				Collections.sort(result, comp);
				// System.out.println("AFTER SORTING ON %expStr%");
				// System.out.println(Util.collectionToString(result, "\\n"));
				return result;
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(resultTypePath);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body10 = new OJBlock();
oper.setBody(body10);

/* <exp> */ 
OJSimpleStatement exp18 = new OJSimpleStatement(resultType + " result = " + resultDefault);
body10.addToStatements( exp18 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp19 = new OJSimpleStatement("result.addAll(" + source + ")");
body10.addToStatements( exp19 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp20 = new OJSimpleStatement("// System.out.println(\"BEFORE SORTING ON " + expStr + "\")");
body10.addToStatements( exp20 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp21 = new OJSimpleStatement("// System.out.println(Util.collectionToString(result, \"\\n\"))");
body10.addToStatements( exp21 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp22 = new OJSimpleStatement("// create a new Comparator instance");
body10.addToStatements( exp22 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp23 = new OJSimpleStatement("Comparator comp = new " + comparatorClassName.getLast() + "()");
body10.addToStatements( exp23 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp24 = new OJSimpleStatement("// sort the result collection");
body10.addToStatements( exp24 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp25 = new OJSimpleStatement("Collections.sort(result, comp)");
body10.addToStatements( exp25 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp26 = new OJSimpleStatement("// System.out.println(\"AFTER SORTING ON " + expStr + "\")");
body10.addToStatements( exp26 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp27 = new OJSimpleStatement("// System.out.println(Util.collectionToString(result, \"\\n\"))");
body10.addToStatements( exp27 );

/* <exp/> */ 

/* <exp> */ 
OJSimpleStatement exp28 = new OJSimpleStatement("return result");
body10.addToStatements( exp28 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 



		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	
	private String createSelect(IteratorExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		// generate operation
		OJOperation oper = null;
		/**<octel var="myClass">
			<method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
			<comment> implements %exp.toString()% </comment>
			<paramlist> %params% </paramlist>
			<body>
				%resultType% result = %resultDefault%;
				<for name="%iterVarNames[0]%" type="%myListTypePath%">
				<collection>%source.toString()%</collection>
				<body>
				<if> %expStr% <then>
					result.add( %iterVarNames[0]% );
				</then></if>
				</body></for>
				return result;
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(resultTypePath);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body11 = new OJBlock();
oper.setBody(body11);

/* <exp> */ 
OJSimpleStatement exp29 = new OJSimpleStatement(resultType + " result = " + resultDefault);
body11.addToStatements( exp29 );

/* <exp/> */ 

/* <for> */ 
OJForStatement for4 = new OJForStatement();
body11.addToStatements(for4);

/* <name> */ 
for4.setElemName(iterVarNames[0]);

/* <name/> */ 

/* <type> */ 
for4.setElemType(myListTypePath);

/* <type/> */ 

/* <collection> */ 
for4.setCollection(source.toString());

/* <collection/> */ 

/* <body> */ 
OJBlock body12 = new OJBlock();
for4.setBody(body12);

/* <if> */ 
OJIfStatement if1 = new OJIfStatement();
if1.setCondition(expStr);
body12.addToStatements(if1);

/* <then> */ 
OJBlock then1 = new OJBlock();
if1.setThenPart(then1);

/* <exp> */ 
OJSimpleStatement exp30 = new OJSimpleStatement("result.add( " + iterVarNames[0] + " )");
then1.addToStatements( exp30 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <body/> */ 

/* <for/> */ 

/* <exp> */ 
OJSimpleStatement exp31 = new OJSimpleStatement("return result");
body11.addToStatements( exp31 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 



		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}

	private String createReject(IteratorExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		expStr = StringHelpers.addBrackets(expStr);
		// generate operation
		OJOperation oper = null;
		/**<octel var="myClass">
			<method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
			<comment> implements %exp.toString()% </comment>
			<paramlist> %params% </paramlist>
			<body>
				%resultType% result = %resultDefault%;
				<for name="%iterVarNames[0]%" type="%myListTypePath%">
				<collection>%source.toString()%</collection>
				<body>
				<if> !%expStr% <then>
					result.add( %iterVarNames[0]% );
				</then></if>
				</body></for>
				return result;
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(resultTypePath);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body13 = new OJBlock();
oper.setBody(body13);

/* <exp> */ 
OJSimpleStatement exp32 = new OJSimpleStatement(resultType + " result = " + resultDefault);
body13.addToStatements( exp32 );

/* <exp/> */ 

/* <for> */ 
OJForStatement for5 = new OJForStatement();
body13.addToStatements(for5);

/* <name> */ 
for5.setElemName(iterVarNames[0]);

/* <name/> */ 

/* <type> */ 
for5.setElemType(myListTypePath);

/* <type/> */ 

/* <collection> */ 
for5.setCollection(source.toString());

/* <collection/> */ 

/* <body> */ 
OJBlock body14 = new OJBlock();
for5.setBody(body14);

/* <if> */ 
OJIfStatement if2 = new OJIfStatement();
if2.setCondition("!" + expStr);
body14.addToStatements(if2);

/* <then> */ 
OJBlock then2 = new OJBlock();
if2.setThenPart(then2);

/* <exp> */ 
OJSimpleStatement exp33 = new OJSimpleStatement("result.add( " + iterVarNames[0] + " )");
then2.addToStatements( exp33 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <body/> */ 

/* <for/> */ 

/* <exp> */ 
OJSimpleStatement exp34 = new OJSimpleStatement("return result");
body13.addToStatements( exp34 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 



		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}

	private String createAny(IteratorExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		// generate operation
		OJOperation oper = null;
		/**<octel var="myClass">
			<method type="%myListTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
			<comment> implements %exp.toString()% </comment>
			<paramlist> %params% </paramlist>
			<body>
				%myListType% result = %myListDefault%;
				<for name="%iterVarNames[0]%" type="%myListTypePath%">
				<collection>%source.toString()%</collection>
				<body>
				<if> %expStr% <then>
					return %iterVarNames[0]%;
				</then></if>
				</body></for>
				return result;
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(myListTypePath);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body15 = new OJBlock();
oper.setBody(body15);

/* <exp> */ 
OJSimpleStatement exp35 = new OJSimpleStatement(myListType + " result = " + myListDefault);
body15.addToStatements( exp35 );

/* <exp/> */ 

/* <for> */ 
OJForStatement for6 = new OJForStatement();
body15.addToStatements(for6);

/* <name> */ 
for6.setElemName(iterVarNames[0]);

/* <name/> */ 

/* <type> */ 
for6.setElemType(myListTypePath);

/* <type/> */ 

/* <collection> */ 
for6.setCollection(source.toString());

/* <collection/> */ 

/* <body> */ 
OJBlock body16 = new OJBlock();
for6.setBody(body16);

/* <if> */ 
OJIfStatement if3 = new OJIfStatement();
if3.setCondition(expStr);
body16.addToStatements(if3);

/* <then> */ 
OJBlock then3 = new OJBlock();
if3.setThenPart(then3);

/* <exp> */ 
OJSimpleStatement exp36 = new OJSimpleStatement("return " + iterVarNames[0]);
then3.addToStatements( exp36 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <body/> */ 

/* <for/> */ 

/* <exp> */ 
OJSimpleStatement exp37 = new OJSimpleStatement("return result");
body15.addToStatements( exp37 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 



		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	private String createOne(IteratorExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		// generate operation
		OJOperation oper = null;
		/**<octel var="myClass">
			<method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
			<comment> implements %exp.toString()% </comment>
			<paramlist> %params% </paramlist>
			<body>
				int _genNr = 0;
				<for name="%iterVarNames[0]%" type="%myListTypePath%">
				<collection>%source.toString()%</collection>
				<body>
				<if> %expStr% <then>
					_genNr++;
				</then></if>
				</body></for>
				<if> _genNr != 1 <then>
					return false;
				</then></if>
				return true;
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(resultTypePath);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body17 = new OJBlock();
oper.setBody(body17);

/* <exp> */ 
OJSimpleStatement exp38 = new OJSimpleStatement("int _genNr = 0");
body17.addToStatements( exp38 );

/* <exp/> */ 

/* <for> */ 
OJForStatement for7 = new OJForStatement();
body17.addToStatements(for7);

/* <name> */ 
for7.setElemName(iterVarNames[0]);

/* <name/> */ 

/* <type> */ 
for7.setElemType(myListTypePath);

/* <type/> */ 

/* <collection> */ 
for7.setCollection(source.toString());

/* <collection/> */ 

/* <body> */ 
OJBlock body18 = new OJBlock();
for7.setBody(body18);

/* <if> */ 
OJIfStatement if4 = new OJIfStatement();
if4.setCondition(expStr);
body18.addToStatements(if4);

/* <then> */ 
OJBlock then4 = new OJBlock();
if4.setThenPart(then4);

/* <exp> */ 
OJSimpleStatement exp39 = new OJSimpleStatement("_genNr++");
then4.addToStatements( exp39 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <body/> */ 

/* <for/> */ 

/* <if> */ 
OJIfStatement if5 = new OJIfStatement();
if5.setCondition("_genNr != 1");
body17.addToStatements(if5);

/* <then> */ 
OJBlock then5 = new OJBlock();
if5.setThenPart(then5);

/* <exp> */ 
OJSimpleStatement exp40 = new OJSimpleStatement("return false");
then5.addToStatements( exp40 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp41 = new OJSimpleStatement("return true");
body17.addToStatements( exp41 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 



		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}

	private String createIsUnique(IteratorExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		expStr = ExpGeneratorHelper.makeListElem(myClass, exp.getBody().getExpressionType(), expStr);
		ClassifierMap map = new ClassifierMap(exp.getBody().getExpressionType());
		OJPathName valueType = JavaPathNames.List.getCopy();
		valueType.addToElementTypes(map.javaDefaultTypePath());
		OJPathName valueImplType= JavaPathNames.ArrayList.getCopy();
		valueImplType.addToElementTypes(map.javaDefaultTypePath());
		// generate operation
		OJOperation oper = null;
		/**<octel var="myClass">
			<method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
			<comment> implements %exp.toString()% </comment>
			<paramlist> %params% </paramlist>
			<body>
				%valueType.getCollectionTypeName()% _valuesInSource = new %valueImplType.getCollectionTypeName()%();
				<for name="%iterVarNames[0]%" type="%myListTypePath%">
				<collection>%source.toString()%</collection>
				<body>
				<if> _valuesInSource.contains(%expStr%) <then>
					return false;
				</then></if>
				_valuesInSource.add(%expStr%);
				</body></for>
				return true;
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(resultTypePath);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body19 = new OJBlock();
oper.setBody(body19);

/* <exp> */ 
OJSimpleStatement exp42 = new OJSimpleStatement(valueType.getCollectionTypeName() + " _valuesInSource = new " + valueImplType.getCollectionTypeName() + "()");
body19.addToStatements( exp42 );

/* <exp/> */ 

/* <for> */ 
OJForStatement for8 = new OJForStatement();
body19.addToStatements(for8);

/* <name> */ 
for8.setElemName(iterVarNames[0]);

/* <name/> */ 

/* <type> */ 
for8.setElemType(myListTypePath);

/* <type/> */ 

/* <collection> */ 
for8.setCollection(source.toString());

/* <collection/> */ 

/* <body> */ 
OJBlock body20 = new OJBlock();
for8.setBody(body20);

/* <if> */ 
OJIfStatement if6 = new OJIfStatement();
if6.setCondition("_valuesInSource.contains(" + expStr + ")");
body20.addToStatements(if6);

/* <then> */ 
OJBlock then6 = new OJBlock();
if6.setThenPart(then6);

/* <exp> */ 
OJSimpleStatement exp43 = new OJSimpleStatement("return false");
then6.addToStatements( exp43 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <exp> */ 
OJSimpleStatement exp44 = new OJSimpleStatement("_valuesInSource.add(" + expStr + ")");
body20.addToStatements( exp44 );

/* <exp/> */ 

/* <body/> */ 

/* <for/> */ 

/* <exp> */ 
OJSimpleStatement exp45 = new OJSimpleStatement("return true");
body19.addToStatements( exp45 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 



		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	
	private String createExists(IteratorExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		// generate operation
		OJOperation oper = null;
		/**<octel var="myClass">
			<method type="%resultTypePath%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
			<comment> implements %exp.toString()% </comment>
			<paramlist> %params% </paramlist>
			<body>
				<for name="%iterVarNames[0]%" type="%myListTypePath%">
				<collection>%source.toString()%</collection>
				<body>
				<if> %expStr% <then>
					return true;
				</then></if>
				</body></for>
				return false;
			</body>
			</method>
		</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(resultTypePath);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body21 = new OJBlock();
oper.setBody(body21);

/* <for> */ 
OJForStatement for9 = new OJForStatement();
body21.addToStatements(for9);

/* <name> */ 
for9.setElemName(iterVarNames[0]);

/* <name/> */ 

/* <type> */ 
for9.setElemType(myListTypePath);

/* <type/> */ 

/* <collection> */ 
for9.setCollection(source.toString());

/* <collection/> */ 

/* <body> */ 
OJBlock body22 = new OJBlock();
for9.setBody(body22);

/* <if> */ 
OJIfStatement if7 = new OJIfStatement();
if7.setCondition(expStr);
body22.addToStatements(if7);

/* <then> */ 
OJBlock then7 = new OJBlock();
if7.setThenPart(then7);

/* <exp> */ 
OJSimpleStatement exp46 = new OJSimpleStatement("return true");
then7.addToStatements( exp46 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <body/> */ 

/* <for/> */ 

/* <exp> */ 
OJSimpleStatement exp47 = new OJSimpleStatement("return false");
body21.addToStatements( exp47 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 



		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	
	private String createForAll(IteratorExp exp, StringBuffer source, boolean isStatic, List<OJParameter> params) {
		expStr = StringHelpers.addBrackets(expStr);
		OJOperation oper = null;
		if (iterVarNames.length == 1) {
			// generate operation
			/**<octel var="myClass">
				<method type="%JavaPathNames.Bool%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
				<comment> implements %exp.toString()% </comment>
				<paramlist> %params% </paramlist>
				<body>
					<for name="%iterVarNames[0]%" type="%myListTypePath%">
					<collection>%source.toString()%</collection>
					<body>
					<if> !%expStr% <then>
						return false;
					</then></if>
					</body></for>
					return true;
				</body>
				</method>
			</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(JavaPathNames.Bool);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body23 = new OJBlock();
oper.setBody(body23);

/* <for> */ 
OJForStatement for10 = new OJForStatement();
body23.addToStatements(for10);

/* <name> */ 
for10.setElemName(iterVarNames[0]);

/* <name/> */ 

/* <type> */ 
for10.setElemType(myListTypePath);

/* <type/> */ 

/* <collection> */ 
for10.setCollection(source.toString());

/* <collection/> */ 

/* <body> */ 
OJBlock body24 = new OJBlock();
for10.setBody(body24);

/* <if> */ 
OJIfStatement if8 = new OJIfStatement();
if8.setCondition("!" + expStr);
body24.addToStatements(if8);

/* <then> */ 
OJBlock then8 = new OJBlock();
if8.setThenPart(then8);

/* <exp> */ 
OJSimpleStatement exp48 = new OJSimpleStatement("return false");
then8.addToStatements( exp48 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <body/> */ 

/* <for/> */ 

/* <exp> */ 
OJSimpleStatement exp49 = new OJSimpleStatement("return true");
body23.addToStatements( exp49 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		} else if (iterVarNames.length == 2) {
			// generate operation
			/**<octel var="myClass">
				<method type="%JavaPathNames.Bool%" name="%operName%" visibility="%priv%" static="%isStatic%" var="oper">
				<comment> implements %exp.toString()% </comment>
				<paramlist> %params% </paramlist>
				<body>
					<for name="%iterVarNames[0]%" type="%myListTypePath%">
					<collection>%source.toString()%</collection>
					<body>
						<for name="%iterVarNames[1]%" type="%myListTypePath%">
						<collection>%source.toString()%</collection>
						<body>
							<if> !%expStr% <then>
								return false;
							</then></if>
						</body></for>
					</body></for>
					return true;
				</body>
				</method>
			</octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(JavaPathNames.Bool);

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <visibility> */ 
oper.setVisibility(priv);

/* <visibility/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <paramlist> */ 
oper.setParameters(params);

/* <paramlist/> */ 

/* <body> */ 
OJBlock body25 = new OJBlock();
oper.setBody(body25);

/* <for> */ 
OJForStatement for11 = new OJForStatement();
body25.addToStatements(for11);

/* <name> */ 
for11.setElemName(iterVarNames[0]);

/* <name/> */ 

/* <type> */ 
for11.setElemType(myListTypePath);

/* <type/> */ 

/* <collection> */ 
for11.setCollection(source.toString());

/* <collection/> */ 

/* <body> */ 
OJBlock body26 = new OJBlock();
for11.setBody(body26);

/* <for> */ 
OJForStatement for12 = new OJForStatement();
body26.addToStatements(for12);

/* <name> */ 
for12.setElemName(iterVarNames[1]);

/* <name/> */ 

/* <type> */ 
for12.setElemType(myListTypePath);

/* <type/> */ 

/* <collection> */ 
for12.setCollection(source.toString());

/* <collection/> */ 

/* <body> */ 
OJBlock body27 = new OJBlock();
for12.setBody(body27);

/* <if> */ 
OJIfStatement if9 = new OJIfStatement();
if9.setCondition("!" + expStr);
body27.addToStatements(if9);

/* <then> */ 
OJBlock then9 = new OJBlock();
if9.setThenPart(then9);

/* <exp> */ 
OJSimpleStatement exp50 = new OJSimpleStatement("return false");
then9.addToStatements( exp50 );

/* <exp/> */ 

/* <then/> */ 

/* <if/> */ 

/* <body/> */ 

/* <for/> */ 

/* <body/> */ 

/* <for/> */ 

/* <exp> */ 
OJSimpleStatement exp51 = new OJSimpleStatement("return true");
body25.addToStatements( exp51 );

/* <exp/> */ 

/* <body/> */ 

/* <method/> */ 

/* <octel/> */ 


		}

		// generate the call to the created operation
		return oper.getName() + "(" + OJOperation.paramsToActuals(oper) + ")";
	}
	
	/**
	 * @param exp
	 * @param isStatic
	 * @param params
	 */
	private void setVariables(LoopExp exp, boolean isStatic, List<OJParameter> params) {
		ClassifierMap nodeMap = new ClassifierMap(exp.getNodeType());
		// the return type of the loop operation
		resultType = nodeMap.javaFacadeType();
		resultTypePath = nodeMap.javaFacadeTypePath();
		// the default value that will be returned if all else fails
		resultDefault = nodeMap.javaDefaultValue();
		// the type of the elements in source
		StdlibCollectionType sourceType = (StdlibCollectionType) exp.getSource().getNodeType();
		elementType = sourceType.getElementType(); // in UML
		ClassifierMap elementMap = new ClassifierMap(elementType);
		myListTypePath = ExpGeneratorHelper.makeListType(elementType); // in Java
		myListType = myListTypePath.getTypeName(); // in Java
		myListDefault = elementMap.javaDefaultValue();
		// TODO do we still need to import the types for these references
//		myClass.addToImports(nodeMap.javaFacadeTypePath());
//		myClass.addToImports(nodeMap.javaDefaultTypePath());
//		myClass.addToImports(elementMap.javaDefaultTypePath());
		// import the type for the Iterator
		// the names of the iterator variables
		iterVarNames = getIterVarNames(exp);
		// the string that holds the body of the loop exp
		ExpressionCreator myExpMaker = new ExpressionCreator(myClass);
		List<OJParameter> loopParams = addItersToParams(exp.getIterators(), params);
		expStr = myExpMaker.makeExpression(exp.getBody(), isStatic, loopParams);
		ClassifierMap map = new ClassifierMap(exp.getBody().getExpressionType());
		if (map.isJavaPrimitive()) {
			expType = map.javaObjectType();
		} else {
			expType = map.javaFacadeType();
		}
		// the name of the loop operation
		operName = exp.getName() + myClass.getUniqueNumber();
	}

	/** The iterator variable(s) needs to be added to any generated operation implementing
	 * the body expression. This method add the iterator variable(s) and replaces any variables
	 * that will be eclipsed by another name in the scope of the body expression.
	 * @param params
	 * @return
	 */
	// TODO find out how the operation ExpGenerationHelper.addVarToParams can be used
	private List<OJParameter> addItersToParams(Collection<IVariableDeclaration> iterVars, List<OJParameter> params) {
		List<OJParameter> result = new ArrayList<OJParameter>(params);
		Iterator outerIt = iterVars.iterator(); 
		while (outerIt.hasNext()){
			IVariableDeclaration elem = (IVariableDeclaration) outerIt.next();			
			Iterator innerIt = params.iterator();
			while (innerIt.hasNext()){
				OJParameter par = (OJParameter) innerIt.next();
				if ( elem.getName().equals(par.getName())) {
					result.remove(par);
				}
			}
			result.add(ExpGeneratorHelper.varDeclToOJPar(elem));
		}
		return result;
	}

	private String[] getIterVarNames(LoopExp exp) {
		String[] iterVarNames = new String[ exp.getIterators().size() ];
		Iterator it = exp.getIterators().iterator();
		int i=0;
		while (it.hasNext()) {
			IVariableDeclaration iterVar = (IVariableDeclaration) it.next();
			iterVarNames[i] = iterVar.getName();
			i++;
		}
		return iterVarNames;
	}
}
