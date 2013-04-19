/*
 * Created on Jan 8, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;
/**<octel>
<java>
	%import org.opaeum.javametamodel.OJSimpleStatement%;
	%inet.sfsf.opaeum.javametamodelametamodel.model.OJVisibilityKind%;
</java>
</octel>*/

/* <octel> */ 

/* <java> */ 

/* <inline> */ 
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StateMap;
import nl.klasse.octopus.codegen.umlToJava.maps.TupleTypeMap;
import nl.klasse.octopus.expressions.IBooleanLiteralExp;
import nl.klasse.octopus.expressions.ICollectionItem;
import nl.klasse.octopus.expressions.ICollectionLiteralExp;
import nl.klasse.octopus.expressions.ICollectionLiteralPart;
import nl.klasse.octopus.expressions.ICollectionRange;
import nl.klasse.octopus.expressions.IEnumLiteralExp;
import nl.klasse.octopus.expressions.IIntegerLiteralExp;
import nl.klasse.octopus.expressions.ILiteralExp;
import nl.klasse.octopus.expressions.INumericLiteralExp;
import nl.klasse.octopus.expressions.IOclStateLiteralExp;
import nl.klasse.octopus.expressions.IOclTypeLiteralExp;
import nl.klasse.octopus.expressions.IOclUndefinedLiteralExp;
import nl.klasse.octopus.expressions.IRealLiteralExp;
import nl.klasse.octopus.expressions.IStringLiteralExp;
import nl.klasse.octopus.expressions.ITupleLiteralExp;
import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.internal.types.BooleanLiteralExp;
import nl.klasse.octopus.expressions.internal.types.CollectionLiteralExp;
import nl.klasse.octopus.expressions.internal.types.IntegerLiteralExp;
import nl.klasse.octopus.expressions.internal.types.OclExpression;
import nl.klasse.octopus.expressions.internal.types.RealLiteralExp;
import nl.klasse.octopus.expressions.internal.types.StringLiteralExp;
import nl.klasse.octopus.model.IEnumLiteral;
import nl.klasse.octopus.model.ITupleType;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.StringHelpers;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;

/**
 * LiteralExpTransformer : 
 */
public class LiteralExpCreator {
	private OJClass myClass 	= null;

	/**
	 * @param myClass
	 */
	public LiteralExpCreator(OJClass myClass) {
		super();
		this.myClass = myClass;
	}

	/**
	 * @param exp
	 * @return
	 */
	public String makeExpression(ILiteralExp in, boolean isStatic, List<OJParameter> params) {
		String result = "";
		if (in instanceof ICollectionLiteralExp) {
			result = makeCollectionLiteralExp((CollectionLiteralExp)in, isStatic, params);
		} else if (in instanceof IEnumLiteralExp) {
			IEnumLiteral lit = ((IEnumLiteralExp)in).getReferredEnumLiteral();
			ClassifierMap mapper = new ClassifierMap(lit.getEnumeration());
			result = mapper.javaType() + "." + lit.getName().toUpperCase();
			myClass.addToImports(mapper.javaTypePath());
		} else if (in instanceof IOclStateLiteralExp) {		
			StateMap stateMap = new StateMap(((IOclStateLiteralExp) in).getReferredState());
			result = stateMap.getter();
		} else if (in instanceof IOclTypeLiteralExp) {
			ClassifierMap mapper = new ClassifierMap(((IOclTypeLiteralExp)in).getReferredClassifier());
			myClass.addToImports(mapper.javaTypePath());
			result = mapper.javaType();
		} else if (in instanceof IBooleanLiteralExp ) {
			result = ((BooleanLiteralExp)in).toString();				
		} else if (in instanceof INumericLiteralExp) {
			if (in instanceof IIntegerLiteralExp) {
				result = ((IntegerLiteralExp)in).toString();
			} else if (in instanceof IRealLiteralExp) {
				result = "(float)" + ((RealLiteralExp)in).toString();
			}
		} else if (in instanceof IStringLiteralExp) {
			result = StringHelpers.replaceAllSubstrings(((StringLiteralExp)in).toString(), "'", "\"");
		} else if (in instanceof ITupleLiteralExp) {
			result = makeTupleLiteral(in, isStatic, params);
		} else if (in instanceof IOclUndefinedLiteralExp) {
			result = "null";
		} else {
			System.err.println("unspecified option in LiteralExpGenerator.makeExpression:");
			System.err.println("\t" + in.getClass().getName());
		}
		return result;
	}

	private String makeTupleLiteral(ILiteralExp in, boolean isStatic, List<OJParameter> params) {
		String result = "";
		StringBuffer localPars = new StringBuffer();
		boolean first = true;
		TupleTypeMap TUPLE = new TupleTypeMap((ITupleType)in.getNodeType());
		Collection<?> sortedParts = TUPLE.sort_literal_parts((ITupleLiteralExp)in);
		Iterator<?> it = sortedParts.iterator();
		while (it.hasNext()){
			IVariableDeclaration decl = (IVariableDeclaration) it.next();
			if (!first ) {
				localPars.append(", ");
			} else {
				first = false;
			}
			ExpressionCreator myExpMaker = new ExpressionCreator(myClass);
			// TODO find out whether the parts of the tuple type should be included in params
			String par = myExpMaker.makeExpression(decl.getInitExpression(), isStatic, params);
			localPars.append(par);
		}			
		result = "new " + TUPLE.getClassName() + "(" + localPars + ")";
		OJPathName tuplePath = OclUtilityCreator.getTuplesPath();
		tuplePath.addToNames(TUPLE.getClassName());
		myClass.addToImports(tuplePath);
		return result;
	}

	/**
	 * @param in
	 * @return
	 */
	private String makeCollectionLiteralExp(CollectionLiteralExp exp, boolean isStatic, List<OJParameter> params) {
		// generate a separate operation
		ClassifierMap mapper = new ClassifierMap(exp.getNodeType());
		String myType = mapper.javaType();
		myClass.addToImports(mapper.javaTypePath());
		String operName = "collectionLiteral" + myClass.getUniqueNumber();
		OJOperation oper = null;
		
		/**<octel var="myClass">
		 	<method type="%mapper.javaTypePath()%" name="%operName%" var="oper" static="%isStatic%" visibility="%OJVisibilityKind.PRIVATE%">
		 	<comment> implements %exp.toString()% </comment>
		 	</method> 
		 </octel>*/

/* <octel> */ 

/* <method> */ 
oper = new OJOperation();
myClass.addToOperations(oper);

/* <type> */ 
oper.setReturnType(mapper.javaTypePath());

/* <type/> */ 

/* <name> */ 
oper.setName(operName);

/* <name/> */ 

/* <static> */ 
oper.setStatic(isStatic);

/* <static/> */ 

/* <visibility> */ 
oper.setVisibility(OJVisibilityKind.PRIVATE);

/* <visibility/> */ 

/* <comment> */ 
oper.setComment("implements " + exp.toString());

/* <comment/> */ 

/* <method/> */ 

/* <octel/> */ 


		
		oper.setParameters(params);
		createCollectionBody(oper, exp, myType, isStatic, params);
		
		// generate the call to the created operation
		return operName + "(" + OJOperation.paramsToActuals(oper)+ ")";
	}

	/**
	 * @param exp
	 * @param myType
	 * @param myDefault
	 * @return
	 */
	private void createCollectionBody(OJOperation oper, ICollectionLiteralExp exp, String myType, boolean isStatic, List<OJParameter> params) {
		OJBlock body = oper.getBody();
		ClassifierMap mapper = new ClassifierMap(exp.getNodeType());
		String myDefault = mapper.javaDefaultValue();
		myClass.addToImports(mapper.javaDefaultTypePath());
		String collectionVarName = "myList";
		/**<octel var="body">
			<exp>
				%myType% %collectionVarName% = %myDefault%
			</exp>
		</octel>*/

/* <octel> */ 

/* <exp> */ 
OJSimpleStatement exp1 = new OJSimpleStatement(myType + " " + collectionVarName + " = " + myDefault);
body.addToStatements( exp1 );

/* <exp/> */ 

/* <octel/> */ 


		ExpressionCreator myExpMaker = new ExpressionCreator(myClass);
		Iterator<?> partsIter = exp.getParts().iterator();
		while (partsIter.hasNext()) {
			ICollectionLiteralPart part = (ICollectionLiteralPart) partsIter.next();
			String elemToAdd = "";
			if (part instanceof ICollectionItem) {
				OclExpression item = ((ICollectionItem)part).getItem();
				String str = myExpMaker.makeExpression(item, isStatic, params);
				if (item.getExpressionType() == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.IntegerTypeName)){
					elemToAdd = "new Integer(" + str + ")";
				} else if (item.getExpressionType() == OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.RealTypeName)) {
					elemToAdd = "new Float(" + str + ")";
				} else {
					elemToAdd = str;
				}
				/**<octel var="body">
					<exp>
						%collectionVarName%.add( %elemToAdd% )
					</exp>
				</octel>*/

/* <octel> */ 

/* <exp> */ 
OJSimpleStatement exp2 = new OJSimpleStatement(collectionVarName + ".add( " + elemToAdd + " )");
body.addToStatements( exp2 );

/* <exp/> */ 

/* <octel/> */ 


			}
			if (part instanceof ICollectionRange) {
				String first = myExpMaker.makeExpression(((ICollectionRange)part).getFirst(), isStatic, params);
				String last = myExpMaker.makeExpression(((ICollectionRange)part).getLast(), isStatic, params);
				// TODO add for-loop to java meta model
				/**<octel var="body">
					<exp>
						for(int i=%first%; i&lt;=%last%; i++) %collectionVarName%.add(new Integer(i))
					</exp>
				</octel>*/

/* <octel> */ 

/* <exp> */ 
OJSimpleStatement exp3 = new OJSimpleStatement("for(int i=" + first + "; i<=" + last + "; i++) " + collectionVarName + ".add(new Integer(i))");
body.addToStatements( exp3 );

/* <exp/> */ 

/* <octel/> */ 


			}
		}
		/**<octel var="body">
			<exp>
				return %collectionVarName%
			</exp>
		</octel>*/

/* <octel> */ 

/* <exp> */ 
OJSimpleStatement exp4 = new OJSimpleStatement("return " + collectionVarName);
body.addToStatements( exp4 );

/* <exp/> */ 

/* <octel/> */ 


	}
}
