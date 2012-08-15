package nl.klasse.octopus.codegen.umlToJava.othergenerators.creators;

import nl.klasse.octopus.codegen.helpers.CommonNames;
import nl.klasse.octopus.codegen.umlToJava.expgenerators.visitors.OclUtilityCreator;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.utilities.JavaPathNames;
import org.opaeum.javageneration.util.OJUtil;

public class MultCheckCreator{
	private PropertyMap FEATURE = null;
	private OJClassifier owner = null;
	private OJBlock body = null;
	private OJPathName ERROR_PATH = null;
	private String getIdStrName = CommonNames.getIdStrName;
	OJUtil ojUtil;
	public MultCheckCreator(OJUtil ojUtil){
		this.ojUtil = ojUtil;
	}
	public void createCheckOper(OJClassifier owner){
		this.owner = owner;
		String OPER_NAME = "checkMultiplicities";
		// get the error path
		ERROR_PATH = OclUtilityCreator.getInvErrorPath();
		OJPathName myTypePath = JavaPathNames.List.getCopy();
		myTypePath.addToElementTypes(OclUtilityCreator.getInvErrorPath());
		String myType = myTypePath.getTypeNameWithTypeArguments();
		String myDefault = "ArrayList<" + OclUtilityCreator.getInvErrorPath().getLast() + ">";
		OJOperation myOper = null;
		myOper = new OJOperation();
		owner.addToOperations(myOper);
		myOper.setReturnType(myTypePath);
		myOper.setName(OPER_NAME);
		myOper.setComment("implements a check on the multiplicities of all attributes and association ends");
		OJBlock body1 = new OJBlock();
		myOper.setBody(body1);
		OJSimpleStatement exp1 = new OJSimpleStatement(myType + " result = new " + myDefault + "()");
		body1.addToStatements(exp1);
		body = myOper.getBody();
	}
	public void finishCheckOper(){
		OJSimpleStatement exp2 = new OJSimpleStatement("return result");
		body.addToStatements(exp2);
	}
	public void structuralfeature(Property feat){
		int upper = feat.getUpper();
		int lower = feat.getLower();
		if(lower == 1 && upper == 1){
			generateNonOptional(feat);
		}else if(upper > 1 || upper == -1){
			if(upper != -1){
				generateUpper(feat, upper);
			}
			if(lower > 0){
				generateLower(feat, lower);
			}
		}
	}
	private void generateNonOptional(Property att){
		FEATURE = ojUtil.buildStructuralFeatureMap(att);
		if(FEATURE.isJavaPrimitive())
			return;
		owner.addToImports(ERROR_PATH);
		OJIfStatement if1 = new OJIfStatement();
		if1.setCondition(FEATURE.getter() + "() == null");
		body.addToStatements(if1);
		OJBlock then1 = new OJBlock();
		if1.setThenPart(then1);
		OJSimpleStatement exp3 = new OJSimpleStatement("String message = \"Mandatory feature '" + att.getName() + "' in object '\"");
		then1.addToStatements(exp3);
		OJSimpleStatement exp4 = new OJSimpleStatement("message = message + this." + getIdStrName + "()");
		then1.addToStatements(exp4);
		OJSimpleStatement exp5 = new OJSimpleStatement("message = message + \"' of type '\" + this.getClass().getName() + \"' has no value.\"");
		then1.addToStatements(exp5);
		OJSimpleStatement exp6 = new OJSimpleStatement("result.add(new " + ERROR_PATH.getLast() + "(this, message))");
		then1.addToStatements(exp6);

	}
	private void generateUpper(Property att,int upper){
		FEATURE = ojUtil.buildStructuralFeatureMap(att);
		owner.addToImports(ERROR_PATH);

		OJIfStatement if2 = new OJIfStatement();
		if2.setCondition(FEATURE.getter() + "().size() > " + upper);
		body.addToStatements(if2);
		OJBlock then2 = new OJBlock();
		if2.setThenPart(then2);
		OJSimpleStatement exp7 = new OJSimpleStatement("String message = \"Upper bound of feature '" + att.getName() + "' in object '\"");
		then2.addToStatements(exp7);
		OJSimpleStatement exp8 = new OJSimpleStatement("message = message + this." + getIdStrName + "()");
		then2.addToStatements(exp8);
		OJSimpleStatement exp9 = new OJSimpleStatement("message = message + \"' of type '\" + this.getClass().getName() + \"' is " + upper
				+ "\" +\n" + "  			\", yet it has size \" + " + FEATURE.getter() + "().size() + \".\"");
		then2.addToStatements(exp9);
		OJSimpleStatement exp10 = new OJSimpleStatement("result.add(new " + ERROR_PATH.getLast() + "(this, message))");
		then2.addToStatements(exp10);
	}
	private void generateLower(Property att,int lower){
		FEATURE = ojUtil.buildStructuralFeatureMap(att);
		owner.addToImports(ERROR_PATH);
		OJIfStatement if3 = new OJIfStatement();
		if3.setCondition(FEATURE.getter() + "().size() < " + lower);
		body.addToStatements(if3);
		OJBlock then3 = new OJBlock();
		if3.setThenPart(then3);
		OJSimpleStatement exp11 = new OJSimpleStatement("String message = \"Lower bound of feature '" + att.getName() + "' in object '\"");
		then3.addToStatements(exp11);
		OJSimpleStatement exp12 = new OJSimpleStatement("message = message + this." + getIdStrName + "()");
		then3.addToStatements(exp12);
		OJSimpleStatement exp13 = new OJSimpleStatement("message = message + \"' of type '\" + this.getClass().getName() + \"' is " + lower
				+ "\" +\n" + "  			\", yet it has size \" + " + FEATURE.getter() + "().size() + \".\"");
		then3.addToStatements(exp13);
		OJSimpleStatement exp14 = new OJSimpleStatement("result.add(new " + ERROR_PATH.getLast() + "(this, message))");
		then3.addToStatements(exp14);
	}
}
