package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.helpers.EmfPropertyCallHelper;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.TupleTypeMap;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.StringHelpers;

import org.eclipse.ocl.uml.BooleanLiteralExp;
import org.eclipse.ocl.uml.CollectionItem;
import org.eclipse.ocl.uml.CollectionLiteralExp;
import org.eclipse.ocl.uml.CollectionLiteralPart;
import org.eclipse.ocl.uml.CollectionRange;
import org.eclipse.ocl.uml.EnumLiteralExp;
import org.eclipse.ocl.uml.IntegerLiteralExp;
import org.eclipse.ocl.uml.LiteralExp;
import org.eclipse.ocl.uml.NullLiteralExp;
import org.eclipse.ocl.uml.NumericLiteralExp;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.uml.RealLiteralExp;
import org.eclipse.ocl.uml.StringLiteralExp;
import org.eclipse.ocl.uml.TupleLiteralExp;
import org.eclipse.ocl.uml.TupleType;
import org.eclipse.ocl.uml.Variable;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.ocl.uml.AbstractOclContext;

public class LiteralExpCreator{
	private OJClass myClass = null;
	OJUtil ojUtil;
	private AbstractOclContext context;
	public LiteralExpCreator(OJUtil ojUtil,OJClass myClass,AbstractOclContext context){
		this.myClass = myClass;
		this.ojUtil = ojUtil;
		this.context = context;
	}
	public String makeExpression(LiteralExp in,boolean isStatic,List<OJParameter> params){
		String result = "";
		if(in instanceof CollectionLiteralExp){
			result = makeCollectionLiteralExp((CollectionLiteralExp) in, isStatic, params);
		}else if(in instanceof EnumLiteralExp){
			EnumerationLiteral lit = ((EnumLiteralExp) in).getReferredEnumLiteral();
			ClassifierMap mapper = ojUtil.buildClassifierMap(lit.getEnumeration());
			result = mapper.javaType() + "." + OJUtil.toJavaLiteral(lit);
			myClass.addToImports(mapper.javaTypePath());
		}else if(in instanceof NullLiteralExp){
			result = "null";
		}else if(in instanceof BooleanLiteralExp){
			result = ((BooleanLiteralExp) in).toString();
		}else if(in instanceof NumericLiteralExp){
			if(in instanceof IntegerLiteralExp){
				result = ((IntegerLiteralExp) in).toString();
			}else if(in instanceof RealLiteralExp){
				result = "(Double)" + ((RealLiteralExp) in).toString();
			}
		}else if(in instanceof StringLiteralExp){
			result = StringHelpers.replaceAllSubstrings(((StringLiteralExp) in).toString(), "'", "\"");
		}else if(in instanceof TupleLiteralExp){
			result = makeTupleLiteral(in, isStatic, params);
		}else{
			System.err.println("unspecified option in LiteralExpGenerator.makeExpression:");
			System.err.println("\t" + in.getClass().getName());
		}
		return result;
	}
	private String makeTupleLiteral(LiteralExp in,boolean isStatic,List<OJParameter> params){
		String result = "";
		StringBuilder localPars = new StringBuilder();
		boolean first = true;
		TupleTypeMap TUPLE = ojUtil.buildTupleTypeMap((TupleType) in.getType());
		Collection<?> sortedParts = TUPLE.sort_literal_parts((TupleLiteralExp) in);
		Iterator<?> it = sortedParts.iterator();
		while(it.hasNext()){
			Variable decl = (Variable) it.next();
			if(!first){
				localPars.append(", ");
			}else{
				first = false;
			}
			ExpressionCreator myExpMaker = new ExpressionCreator(ojUtil, myClass, context);
			// TODO find out whether the parts of the tuple type should be
			// included in params
			String par = myExpMaker.makeExpression((OCLExpression) decl.getInitExpression(), isStatic, params);
			localPars.append(par);
		}
		result = "new " + TUPLE.getClassName() + "(" + localPars + ")";
		OJPathName tuplePath = OclUtilityCreator.getTuplesPath();
		tuplePath.addToNames(TUPLE.getClassName());
		myClass.addToImports(tuplePath);
		return result;
	}
	private String makeCollectionLiteralExp(CollectionLiteralExp exp,boolean isStatic,List<OJParameter> params){
		if(exp.getPart().size() == 1 && exp.getPart().get(0) instanceof CollectionItem
				&& EmfPropertyCallHelper.resultsInMany( (OCLExpression) ((CollectionItem) exp.getPart().get(0)).getItem())){
			ExpressionCreator myExpMaker = new ExpressionCreator(ojUtil, myClass, context);
			OCLExpression item = (OCLExpression) ((CollectionItem) exp.getPart().get(0)).getItem();
			return myExpMaker.makeExpression(item, isStatic, params);
		}else if(exp.getPart().size()==0){
			myClass.addToImports("java.util.Collections");
			switch(exp.getKind()){
			case BAG_LITERAL:
			case COLLECTION_LITERAL:
			case SEQUENCE_LITERAL:
			case ORDERED_SET_LITERAL:
				return "Collections.EMPTY_LIST";
			case SET_LITERAL:
				return "Collections.EMPTY_SET";
			}
			return "Collections.EMPTY_LIST";
		}else{
			// generate a separate operation
			ClassifierMap mapper = ojUtil.buildClassifierMap(exp.getType());
			String myType = mapper.javaType();
			myClass.addToImports(mapper.javaTypePath());
			String operName = "collectionLiteral" + myClass.getUniqueNumber();
			OJOperation oper = null;
			oper = new OJOperation(operName);
			myClass.addToOperations(oper);
			oper.setReturnType(mapper.javaTypePath());
			
			oper.setStatic(isStatic);
			oper.setVisibility(OJVisibilityKind.PRIVATE);
			oper.setComment("implements " + exp.toString());
			oper.setParameters(params);
			createCollectionBody(oper, exp, myType, isStatic, params);
			// generate the call to the created operation
			return operName + "(" + OJOperation.paramsToActuals(oper) + ")";
		}
	}
	private void createCollectionBody(OJOperation oper,CollectionLiteralExp exp,String myType,boolean isStatic,List<OJParameter> params){
		OJBlock body = oper.getBody();
		ClassifierMap mapper = ojUtil.buildClassifierMap(exp.getType());
		String myDefault = mapper.javaDefaultValue();
		myClass.addToImports(mapper.javaDefaultTypePath());
		// NB!! Eclipse qualified associations still see multiplicity[0..1] as a single object, and not a set, therefore
		// an expression invoking the qualified property as a collection will be interpreted as a collection item
		String collectionVarName = "myList";
		OJSimpleStatement exp1 = new OJSimpleStatement(myType + " " + collectionVarName + " = " + myDefault);
		body.addToStatements(exp1);
		ExpressionCreator myExpMaker = new ExpressionCreator(ojUtil, myClass, context);
		Iterator<?> partsIter = exp.getPart().iterator();
		while(partsIter.hasNext()){
			CollectionLiteralPart part = (CollectionLiteralPart) partsIter.next();
			if(part instanceof CollectionItem){
				OCLExpression item = (OCLExpression) ((CollectionItem) part).getItem();
				String str = myExpMaker.makeExpression(item, isStatic, params);
				OJStatement addStatement = null;
				if(EmfClassifierUtil.comformsToLibraryType(item.getType(), IOclLibrary.IntegerTypeName)){
					addStatement = new OJSimpleStatement(collectionVarName + ".add( new Integer(" + str + ") )");
				}else if(EmfClassifierUtil.comformsToLibraryType(item.getType(), IOclLibrary.RealTypeName)){
					addStatement = new OJSimpleStatement(collectionVarName + ".add(  new Double(" + str + ") )");
				}else{
					// TODO may have to optimise by storing the value in a variable to eliminate multiple invocations
					addStatement = new OJIfStatement(str + " != null", collectionVarName + ".add( " + str + " )");
				}
				body.addToStatements(addStatement);
			}
			if(part instanceof CollectionRange){
				String first = myExpMaker.makeExpression((OCLExpression) ((CollectionRange) part).getFirst(), isStatic, params);
				String last = myExpMaker.makeExpression((OCLExpression) ((CollectionRange) part).getLast(), isStatic, params);
				OJSimpleStatement exp3 = new OJSimpleStatement("for(int i=" + first + "; i<=" + last + "; i++) " + collectionVarName
						+ ".add(new Integer(i))");
				body.addToStatements(exp3);
			}
		}
		OJSimpleStatement exp4 = new OJSimpleStatement("return " + collectionVarName);
		body.addToStatements(exp4);
	}
}
