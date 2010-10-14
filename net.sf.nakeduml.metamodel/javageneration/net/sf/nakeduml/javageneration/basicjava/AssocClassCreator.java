package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJConstructor;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.OJVisibilityKind;
import net.sf.nakeduml.javametamodel.OJWhileStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.utilities.JavaPathNames;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import nl.klasse.octopus.codegen.umlToJava.maps.NavToAssocClassMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.IAssociationClass;

public class AssocClassCreator {
	private NakedStructuralFeatureMap end1 = null;
	private NakedStructuralFeatureMap end2 = null;
	private NavToAssocClassMap mapToEnd1 = null;
	private NavToAssocClassMap mapToEnd2 = null;
	private String ASSCLS_NAME;
	private OJPathName end1BaseTypePath;
	private OJPathName end2BaseTypePath;
	private String end1GetterName;
	private String end2GetterName;
	private String end1DefaultValue;
	private String end2DefaultValue;
	private String ASSCLS_ADDER1_NAME;
	private String ASSCLS_ADDER2_NAME;
	private String ASSCLS_REMOVER1_NAME;
	private String ASSCLS_REMOVER2_NAME;
	private String PARAMS_END1_END2 = "this, par";
	private String PARAMS_END2_END1 = "par, this";

	public AssocClassCreator() {
		super();
	}

	public void generateOneToOne(INakedAssociationClass asscls, OJClass owner, OJClass end1Cls, OJClass end2Cls) {
		commonWithoutFacade(asscls, owner);
		addToBaseType(end1Cls, end2, mapToEnd2, mapToEnd1, PARAMS_END1_END2);
		addToBaseType(end2Cls, end1, mapToEnd1, mapToEnd2, PARAMS_END2_END1);
	}

	public void generateOneToMany(INakedAssociationClass asscls, OJClass owner, OJClass end1Cls, OJClass end2Cls) {
		commonWithoutFacade(asscls, owner);
		addMultToBaseType(end1Cls, end2, mapToEnd2, mapToEnd1, PARAMS_END1_END2);
		addToBaseType(end2Cls, end1, mapToEnd1, mapToEnd2, PARAMS_END2_END1);
	}

	public void generateManyToOne(INakedAssociationClass asscls, OJClass owner, OJClass end1Cls, OJClass end2Cls) {
		commonWithoutFacade(asscls, owner);
		addToBaseType(end1Cls, end2, mapToEnd2, mapToEnd1, PARAMS_END1_END2);
		addMultToBaseType(end2Cls, end1, mapToEnd1, mapToEnd2, PARAMS_END2_END1);
	}

	public void generateManyToMany(INakedAssociationClass asscls, OJClass owner, OJClass end1Cls, OJClass end2Cls) {
		commonWithoutFacade(asscls, owner);
		addMultMultToBaseType(end1Cls, end2, mapToEnd2, mapToEnd1, PARAMS_END1_END2);
		addMultMultToBaseType(end2Cls, end1, mapToEnd1, mapToEnd2, PARAMS_END2_END1);
	}

	private void commonWithoutFacade(INakedAssociationClass asscls, OJClass owner) {
		getVariables(asscls);
		buildAssociationClass(owner, asscls);
		owner.addToImports(end1BaseTypePath);
		owner.addToImports(end2BaseTypePath);
		OJConstructor constructor = owner.getDefaultConstructor();
		buildConstructor(constructor);
	}

	private void buildConstructor(OJConstructor constructor) {
		constructor.setComment("Constructor for " + ASSCLS_NAME + ". \n"
				+ "			Always use this constructor, do NOT use the one without parameters.");
		OJParameter param1 = new OJParameter();
		constructor.addToParameters(param1);
		param1.setType(end1BaseTypePath);
		param1.setName("a");
		OJParameter param2 = new OJParameter();
		constructor.addToParameters(param2);
		param2.setType(end2BaseTypePath);
		param2.setName("b");
		OJBlock body1 = new OJBlock();
		constructor.setBody(body1);
		OJIfStatement if1 = new OJIfStatement();
		if1.setCondition("a != null && b != null");
		body1.addToStatements(if1);
		OJBlock then1 = new OJBlock();
		if1.setThenPart(then1);
		OJSimpleStatement exp1 = new OJSimpleStatement("this." + end1.umlName() + " = a");
		then1.addToStatements(exp1);
		OJSimpleStatement exp2 = new OJSimpleStatement("a." + ASSCLS_ADDER1_NAME + "(this)");
		then1.addToStatements(exp2);
		OJSimpleStatement exp3 = new OJSimpleStatement("this." + end2.umlName() + " = b");
		then1.addToStatements(exp3);
		OJSimpleStatement exp4 = new OJSimpleStatement("b." + ASSCLS_ADDER2_NAME + "(this)");
		then1.addToStatements(exp4);
	}

	private void addToBaseType(OJClass endCls, StructuralFeatureMap END, NavToAssocClassMap NAV, NavToAssocClassMap NAV_OTHER, String PARAMS) {
		OJPathName END_INF_PATH = END.javaBaseFacadeTypePath();
		OJAnnotatedField field1 = new OJAnnotatedField();
		endCls.addToFields(field1);
		field1.setType(NAV.javaFacadeTypePath());
		field1.setName(NAV_OTHER.umlName());
		field1.setInitExp(NAV.javaDefaultValue());
		OJAnnotatedOperation method2 = new OJAnnotatedOperation();
		endCls.addToOperations(method2);
		method2.setReturnType(NAV.javaFacadeTypePath());
		method2.setName(NAV_OTHER.getter());
		method2.setComment("implements the getter for the association class '" + ASSCLS_NAME + "'.");
		OJBlock body4 = new OJBlock();
		method2.setBody(body4);
		OJSimpleStatement exp15 = new OJSimpleStatement("return " + NAV_OTHER.umlName());
		body4.addToStatements(exp15);
		OJAnnotatedOperation method3 = new OJAnnotatedOperation();
		endCls.addToOperations(method3);
		method3.setReturnType(JavaPathNames.Void);
		method3.setName(NAV_OTHER.adder());
		method3.setComment("Should be used from " + ASSCLS_NAME + " only! \n"
				+ "			Implements setting the link between this object and the association class.");
		OJParameter param6 = new OJParameter();
		method3.addToParameters(param6);
		param6.setType(NAV.javaFacadeTypePath());
		param6.setName("assocClass");
		OJBlock body5 = new OJBlock();
		method3.setBody(body5);
		OJSimpleStatement exp16 = new OJSimpleStatement("this." + NAV_OTHER.umlName() + " = assocClass");
		body5.addToStatements(exp16);
		OJAnnotatedOperation method4 = new OJAnnotatedOperation();
		endCls.addToOperations(method4);
		method4.setReturnType(JavaPathNames.Void);
		method4.setName(NAV_OTHER.remover());
		method4.setComment("Should be used from " + ASSCLS_NAME + " only! \n"
				+ "			Implements removal of the link between this object and the association class.");
		OJParameter param7 = new OJParameter();
		method4.addToParameters(param7);
		param7.setType(NAV.javaFacadeTypePath());
		param7.setName("assocClass");
		OJBlock body6 = new OJBlock();
		method4.setBody(body6);
		OJSimpleStatement exp17 = new OJSimpleStatement("this." + NAV_OTHER.umlName() + " = null");
		body6.addToStatements(exp17);
		OJAnnotatedOperation method5 = new OJAnnotatedOperation();
		endCls.addToOperations(method5);
		method5.setReturnType(END_INF_PATH);
		method5.setName(END.getter());
		method5.setComment("Implements the getter for association end '" + END.umlName() + "'");
		OJBlock body7 = new OJBlock();
		method5.setBody(body7);
		OJIfStatement if4 = new OJIfStatement();
		if4.setCondition("this." + NAV_OTHER.umlName() + " != null");
		body7.addToStatements(if4);
		OJBlock then4 = new OJBlock();
		if4.setThenPart(then4);
		OJSimpleStatement exp18 = new OJSimpleStatement("return this." + NAV_OTHER.umlName() + "." + END.getter() + "()");
		then4.addToStatements(exp18);
		OJBlock else2 = new OJBlock();
		if4.setElsePart(else2);
		OJSimpleStatement exp19 = new OJSimpleStatement("return null");
		else2.addToStatements(exp19);
		OJAnnotatedOperation method6 = new OJAnnotatedOperation();
		endCls.addToOperations(method6);
		method6.setReturnType(JavaPathNames.Void);
		method6.setName(END.setter());
		method6.setComment("Implements the setter for association end '" + END.umlName() + "'");
		OJParameter param8 = new OJParameter();
		method6.addToParameters(param8);
		param8.setType(END_INF_PATH);
		param8.setName("par");
		OJBlock body8 = new OJBlock();
		method6.setBody(body8);
		OJIfStatement if5 = new OJIfStatement();
		if5.setCondition("this." + NAV_OTHER.umlName() + " != null");
		body8.addToStatements(if5);
		OJBlock then5 = new OJBlock();
		if5.setThenPart(then5);
		OJSimpleStatement exp21 = new OJSimpleStatement("this." + NAV_OTHER.umlName() + ".clean()");
		then5.addToStatements(exp21);
		OJIfStatement if6 = new OJIfStatement();
		if6.setCondition("par != null");
		body8.addToStatements(if6);
		OJBlock then6 = new OJBlock();
		if6.setThenPart(then6);
		if (NAV_OTHER.isSingleObject()) {
			OJIfStatement if7 = new OJIfStatement();
			if7.setCondition("par." + NAV.getter() + "() != null");
			then6.addToStatements(if7);
			OJBlock then7 = new OJBlock();
			if7.setThenPart(then7);
			OJSimpleStatement exp23 = new OJSimpleStatement("par." + NAV.getter() + "().clean()");
			then7.addToStatements(exp23);
		}
		OJSimpleStatement exp24 = new OJSimpleStatement("this." + NAV_OTHER.umlName() + " = new " + ASSCLS_NAME + "(" + PARAMS + ")");
		then6.addToStatements(exp24);
		OJBlock else3 = new OJBlock();
		if6.setElsePart(else3);
		OJSimpleStatement exp25 = new OJSimpleStatement("this." + NAV_OTHER.umlName() + " = null");
		else3.addToStatements(exp25);
	}

	private void addMultToBaseType(OJClass endCls, NakedStructuralFeatureMap END, NavToAssocClassMap NAV, NavToAssocClassMap NAV_OTHER,
			String PARAMS) {
		endCls.addToImports(NAV.javaDefaultTypePath());
		OJAnnotatedField field2 = new OJAnnotatedField();
		endCls.addToFields(field2);
		field2.setType(NAV.javaFacadeTypePath());
		field2.setName(NAV_OTHER.umlName());
		field2.setInitExp(NAV.javaDefaultValue());
		OJAnnotatedOperation method7 = new OJAnnotatedOperation();
		endCls.addToOperations(method7);
		method7.setReturnType(NAV.javaFacadeTypePath());
		method7.setName(NAV_OTHER.getter());
		method7.setComment("implements the getter for the association class in association '" + NAV_OTHER.umlName() + "'.");
		OJBlock body9 = new OJBlock();
		method7.setBody(body9);
		OJSimpleStatement exp26 = new OJSimpleStatement("return " + NAV_OTHER.umlName());
		body9.addToStatements(exp26);
		OJAnnotatedOperation method8 = new OJAnnotatedOperation();
		endCls.addToOperations(method8);
		method8.setReturnType(JavaPathNames.Void);
		method8.setName(NAV_OTHER.setter());
		method8.setComment("Should be used from " + NAV_OTHER.umlName() + " only! \n"
				+ "			Implements the setting of the link between this object and its association classes.");
		OJParameter param9 = new OJParameter();
		method8.addToParameters(param9);
		param9.setType(NAV.javaFacadeTypePath());
		param9.setName("val");
		param9.setComment("the elements to be added");
		OJBlock body10 = new OJBlock();
		method8.setBody(body10);
		OJSimpleStatement exp27 = new OJSimpleStatement("this." + NAV_OTHER.umlName() + " = val");
		body10.addToStatements(exp27);
		OJAnnotatedOperation method9 = new OJAnnotatedOperation();
		endCls.addToOperations(method9);
		method9.setReturnType(JavaPathNames.Void);
		method9.setName(NAV_OTHER.adder());
		method9.setComment("Should be used from " + NAV_OTHER.umlName() + " only! \n"
				+ "			Implements the adding to the link between this object and its association classes.");
		OJParameter param10 = new OJParameter();
		method9.addToParameters(param10);
		param10.setType(NAV_OTHER.javaBaseFacadeTypePath());
		param10.setName("assocClass");
		param10.setComment("the element to be added");
		OJBlock body11 = new OJBlock();
		method9.setBody(body11);
		OJSimpleStatement exp28 = new OJSimpleStatement("this." + NAV_OTHER.umlName() + ".add(assocClass)");
		body11.addToStatements(exp28);
		OJAnnotatedOperation method10 = new OJAnnotatedOperation();
		endCls.addToOperations(method10);
		method10.setReturnType(JavaPathNames.Void);
		method10.setName(NAV_OTHER.remover());
		method10.setComment("Should be used from " + NAV_OTHER.umlName() + " only! \n"
				+ "			Implements the removal of the link between this object and its association classes.");
		OJParameter param11 = new OJParameter();
		method10.addToParameters(param11);
		param11.setType(NAV_OTHER.javaBaseFacadeTypePath());
		param11.setName("assocClass");
		param11.setComment("the element to be removed");
		OJBlock body12 = new OJBlock();
		method10.setBody(body12);
		OJSimpleStatement exp29 = new OJSimpleStatement("this." + NAV_OTHER.umlName() + ".remove(assocClass)");
		body12.addToStatements(exp29);
		OJAnnotatedOperation method11 = new OJAnnotatedOperation();
		endCls.addToOperations(method11);
		method11.setReturnType(END.javaTypePath());
		method11.setName(END.getter());
		method11.setComment("implements the getter for association end '" + END.umlName() + "'");
		OJBlock body13 = new OJBlock();
		method11.setBody(body13);
		OJSimpleStatement exp30 = new OJSimpleStatement(END.javaType() + " result = " + END.javaDefaultValue());
		body13.addToStatements(exp30);
		OJForStatement for1 = new OJForStatement();
		body13.addToStatements(for1);
		for1.setElemType(NAV_OTHER.javaBaseFacadeTypePath());
		for1.setElemName("elem");
		for1.setCollection("this." + NAV_OTHER.umlName());
		OJBlock body14 = new OJBlock();
		for1.setBody(body14);
		OJSimpleStatement exp31 = new OJSimpleStatement("result.add( elem." + END.getter() + "() )");
		body14.addToStatements(exp31);
		OJSimpleStatement exp32 = new OJSimpleStatement("return result");
		body13.addToStatements(exp32);
		OJAnnotatedOperation method12 = new OJAnnotatedOperation();
		endCls.addToOperations(method12);
		method12.setReturnType(JavaPathNames.Void);
		method12.setName(END.setter());
		method12.setComment("implements the setter method for '" + END.umlName() + "'");
		OJParameter param12 = new OJParameter();
		method12.addToParameters(param12);
		param12.setType(END.javaTypePath());
		param12.setName("par");
		param12.setComment("the elements to be added");
		OJBlock body15 = new OJBlock();
		method12.setBody(body15);
		OJSimpleStatement exp33 = new OJSimpleStatement("this." + NAV_OTHER.umlName() + ".clear()");
		body15.addToStatements(exp33);
		OJIfStatement if8 = new OJIfStatement();
		if8.setCondition("par != null");
		body15.addToStatements(if8);
		OJBlock then8 = new OJBlock();
		if8.setThenPart(then8);
		OJForStatement for2 = new OJForStatement();
		then8.addToStatements(for2);
		for2.setElemType(END.javaBaseFacadeTypePath());
		for2.setElemName("elem");
		for2.setCollection("par");
		OJBlock body16 = new OJBlock();
		for2.setBody(body16);
		OJSimpleStatement exp34 = new OJSimpleStatement("this." + END.adder() + "(elem)");
		body16.addToStatements(exp34);
		OJAnnotatedOperation method13 = new OJAnnotatedOperation();
		endCls.addToOperations(method13);
		method13.setReturnType(JavaPathNames.Void);
		method13.setName(END.adder());
		method13.setComment("implements the add element method for '" + END.umlName() + "'");
		OJParameter param13 = new OJParameter();
		method13.addToParameters(param13);
		param13.setType(END.javaBaseFacadeTypePath());
		param13.setName("par");
		OJBlock body17 = new OJBlock();
		method13.setBody(body17);
		OJIfStatement if9 = new OJIfStatement();
		if9.setCondition("par != null");
		body17.addToStatements(if9);
		OJBlock then9 = new OJBlock();
		if9.setThenPart(then9);
		OJIfStatement if10 = new OJIfStatement();
		if10.setCondition("par." + NAV.getter() + "() != null");
		then9.addToStatements(if10);
		OJBlock then10 = new OJBlock();
		if10.setThenPart(then10);
		OJSimpleStatement exp36 = new OJSimpleStatement("par." + NAV.getter() + "().clean()");
		then10.addToStatements(exp36);
		OJSimpleStatement exp37 = new OJSimpleStatement("new " + NAV_OTHER.umlName() + "(" + PARAMS + ")");
		then9.addToStatements(exp37);
		OJAnnotatedOperation method14 = new OJAnnotatedOperation();
		endCls.addToOperations(method14);
		method14.setReturnType(JavaPathNames.Void);
		method14.setName(END.remover());
		method14.setComment("implements the remove element method for '" + END.umlName() + "'");
		OJParameter param14 = new OJParameter();
		method14.addToParameters(param14);
		param14.setType(END.javaBaseFacadeTypePath());
		param14.setName("element");
		OJBlock body18 = new OJBlock();
		method14.setBody(body18);
		OJSimpleStatement exp38 = new OJSimpleStatement("this." + NAV_OTHER.umlName() + ".remove(element)");
		body18.addToStatements(exp38);
		OJIfStatement if11 = new OJIfStatement();
		if11.setCondition("element != null");
		body18.addToStatements(if11);
		OJBlock then11 = new OJBlock();
		if11.setThenPart(then11);
		OJIfStatement if12 = new OJIfStatement();
		if12.setCondition("element." + NAV.getter() + "() != null");
		then11.addToStatements(if12);
		OJBlock then12 = new OJBlock();
		if12.setThenPart(then12);
		OJSimpleStatement exp40 = new OJSimpleStatement("element." + NAV.getter() + "().clean()");
		then12.addToStatements(exp40);
		generateExtraCollOpers(endCls, END, END.javaBaseFacadeTypePath());
	}

	private void addMultMultToBaseType(OJClass endCls, NakedStructuralFeatureMap featureMapToThisEnd, NavToAssocClassMap mapToThisEnd,
			NavToAssocClassMap mapToOtherEnd, String PARAMS) {
		endCls.addToImports(JavaPathNames.Iterator);
		endCls.addToImports(mapToThisEnd.javaDefaultTypePath());
		buildProperty(endCls, mapToThisEnd, mapToOtherEnd);
		OJAnnotatedOperation otherAdder = new OJAnnotatedOperation();
		endCls.addToOperations(otherAdder);
		otherAdder.setReturnType(JavaPathNames.Void);
		otherAdder.setName(mapToOtherEnd.adder());
		otherAdder.setComment("Should be used from " + mapToOtherEnd.umlName() + " only! Implements \n" + "			the addition of an "
				+ mapToOtherEnd.umlName() + " instance because an element\n" + "			was added to the association.");
		otherAdder.addParam("assocClass", mapToOtherEnd.javaBaseFacadeTypePath());
		otherAdder.getBody().addToStatements("this." + mapToThisEnd.umlName() + ".add(assocClass)");
		OJAnnotatedOperation otherRemover = new OJAnnotatedOperation();
		endCls.addToOperations(otherRemover);
		otherRemover.setReturnType(JavaPathNames.Void);
		otherRemover.setName(mapToOtherEnd.remover());
		otherRemover.setComment("Should be used from " + mapToOtherEnd.umlName() + " only! Implements \n" + "			the removal of an "
				+ mapToOtherEnd.umlName() + " instance because an element\n" + "			was removed from the association.");
		otherRemover.addParam("assocClass", mapToOtherEnd.javaBaseFacadeTypePath());
		otherRemover.getBody().addToStatements("this." + mapToThisEnd.umlName() + ".remove(assocClass)");
		OJAnnotatedOperation thisGetter = new OJAnnotatedOperation();
		endCls.addToOperations(thisGetter);
		thisGetter.setReturnType(featureMapToThisEnd.javaTypePath());
		thisGetter.setName(featureMapToThisEnd.getter());
		thisGetter.setComment("Implements the getter for '" + featureMapToThisEnd.umlName() + "'");
		thisGetter.getBody().addToStatements(
				new OJSimpleStatement(featureMapToThisEnd.javaType() + " result = " + featureMapToThisEnd.javaDefaultValue()));
		OJForStatement forEachInThisEnd = new OJForStatement();
		thisGetter.getBody().addToStatements(forEachInThisEnd);
		forEachInThisEnd.setElemType(mapToOtherEnd.javaBaseFacadeTypePath());
		forEachInThisEnd.setElemName("elem");
		forEachInThisEnd.setCollection("this." + mapToThisEnd.umlName());
		OJBlock body24 = new OJBlock();
		forEachInThisEnd.setBody(body24);
		body24.addToStatements(new OJSimpleStatement("result.add( elem." + featureMapToThisEnd.getter() + "() )"));
		thisGetter.getBody().addToStatements(new OJSimpleStatement("return result"));
		OJAnnotatedOperation thisSetter = new OJAnnotatedOperation();
		endCls.addToOperations(thisSetter);
		thisSetter.setReturnType(JavaPathNames.Void);
		thisSetter.setName(featureMapToThisEnd.setter());
		thisSetter.setComment("Implements the setter for '" + featureMapToThisEnd.umlName() + "'");
		OJParameter param18 = new OJParameter();
		thisSetter.addToParameters(param18);
		param18.setType(featureMapToThisEnd.javaTypePath());
		param18.setName("par");
		OJBlock body25 = new OJBlock();
		thisSetter.setBody(body25);
		body25.setComment("make copy to avoid a ConcurrentModificationException");
		OJSimpleStatement exp48 = new OJSimpleStatement(mapToThisEnd.javaFacadeTypePath().getCollectionTypeName() + " _internal = new "
				+ mapToThisEnd.javaDefaultTypePath().getCollectionTypeName() + "(this." + mapToThisEnd.umlName() + ")");
		body25.addToStatements(exp48);
		OJForStatement for4 = new OJForStatement();
		body25.addToStatements(for4);
		for4.setElemType(mapToOtherEnd.javaBaseFacadeTypePath());
		for4.setElemName("elem");
		for4.setCollection("_internal");
		OJBlock body26 = new OJBlock();
		for4.setBody(body26);
		OJSimpleStatement exp50 = new OJSimpleStatement("elem.clean()");
		body26.addToStatements(exp50);
		OJIfStatement if13 = new OJIfStatement();
		if13.setCondition("par != null");
		body25.addToStatements(if13);
		OJBlock then13 = new OJBlock();
		if13.setThenPart(then13);
		OJForStatement for5 = new OJForStatement();
		then13.addToStatements(for5);
		for5.setElemType(featureMapToThisEnd.javaBaseFacadeTypePath());
		for5.setElemName("elem");
		for5.setCollection("par");
		OJBlock body27 = new OJBlock();
		for5.setBody(body27);
		OJSimpleStatement exp51 = new OJSimpleStatement("this." + featureMapToThisEnd.adder() + "(elem)");
		body27.addToStatements(exp51);
		OJAnnotatedOperation thisAdder = new OJAnnotatedOperation();
		endCls.addToOperations(thisAdder);
		thisAdder.setReturnType(JavaPathNames.Void);
		thisAdder.setName(featureMapToThisEnd.adder());
		thisAdder.setComment("Implements the addition of an element to '" + featureMapToThisEnd.umlName() + "'");
		OJParameter param19 = new OJParameter();
		thisAdder.addToParameters(param19);
		param19.setType(featureMapToThisEnd.javaBaseFacadeTypePath());
		param19.setName("par");
		OJSimpleStatement exp52 = new OJSimpleStatement("boolean isPresent = false");
		thisAdder.getBody().addToStatements(exp52);
		OJSimpleStatement exp53 = new OJSimpleStatement("Iterator it = " + mapToThisEnd.umlName() + ".iterator()");
		thisAdder.getBody().addToStatements(exp53);
		OJWhileStatement whileNextAndNotPresent = new OJWhileStatement();
		thisAdder.getBody().addToStatements(whileNextAndNotPresent);
		whileNextAndNotPresent.setCondition("it.hasNext() && !isPresent");
		whileNextAndNotPresent.getBody().addToStatements(mapToOtherEnd.umlName() + " elem = (" + mapToOtherEnd.umlName() + ") it.next()");
		OJIfStatement if14 = new OJIfStatement();
		if14.setCondition("elem." + featureMapToThisEnd.getter() + "() == par");
		whileNextAndNotPresent.getBody().addToStatements(if14);
		OJBlock then14 = new OJBlock();
		if14.setThenPart(then14);
		OJSimpleStatement exp55 = new OJSimpleStatement("isPresent = true");
		then14.addToStatements(exp55);
		OJIfStatement if15 = new OJIfStatement();
		if15.setCondition("!isPresent");
		thisAdder.getBody().addToStatements(if15);
		OJBlock then15 = new OJBlock();
		if15.setThenPart(then15);
		then15.addToStatements(mapToThisEnd.umlName() + ".add(new " + mapToOtherEnd.umlName() + "(" + PARAMS + "))");
		OJAnnotatedOperation thisRemover = new OJAnnotatedOperation();
		endCls.addToOperations(thisRemover);
		thisRemover.setReturnType(JavaPathNames.Void);
		thisRemover.setName(featureMapToThisEnd.remover());
		thisRemover.setComment("Implements the removal of an element from '" + featureMapToThisEnd.umlName() + "'");
		OJParameter param20 = new OJParameter();
		thisRemover.addToParameters(param20);
		param20.setType(featureMapToThisEnd.javaBaseFacadeTypePath());
		param20.setName("par");
		thisRemover.getBody().addToStatements(mapToOtherEnd.javaBaseFacadeTypePath().getCollectionTypeName() + " foundElem = null");
		OJForStatement for6 = new OJForStatement();
		thisRemover.getBody().addToStatements(for6);
		for6.setElemType(mapToOtherEnd.javaBaseFacadeTypePath());
		for6.setElemName("elem");
		for6.setCollection(mapToThisEnd.umlName());
		OJBlock body31 = new OJBlock();
		for6.setBody(body31);
		OJIfStatement if16 = new OJIfStatement();
		if16.setCondition("elem." + featureMapToThisEnd.getter() + "() == par");
		body31.addToStatements(if16);
		OJBlock then16 = new OJBlock();
		if16.setThenPart(then16);
		then16.addToStatements(new OJSimpleStatement("foundElem = elem"));
		OJIfStatement if17 = new OJIfStatement();
		if17.setCondition("foundElem != null");
		thisRemover.getBody().addToStatements(if17);
		OJBlock then17 = new OJBlock();
		if17.setThenPart(then17);
		then17.addToStatements("foundElem.clean()");
		then17.addToStatements("this." + mapToThisEnd.umlName() + ".remove(foundElem)");
		generateExtraCollOpers(endCls, featureMapToThisEnd, featureMapToThisEnd.javaBaseFacadeTypePath());
	}

	public void buildProperty(OJClass endCls, NavToAssocClassMap mapToThisEnd, NavToAssocClassMap mapToOtherEnd) {
		//Add AssociationClass property
		OJAnnotatedField thisField = new OJAnnotatedField();
		endCls.addToFields(thisField);
		thisField.setType(mapToThisEnd.javaTypePath());
		thisField.setName(mapToThisEnd.umlName());
		thisField.setInitExp(mapToThisEnd.javaDefaultValue());
		OJAnnotatedOperation otherGetter = new OJAnnotatedOperation();
		endCls.addToOperations(otherGetter);
		otherGetter.setReturnType(mapToThisEnd.javaTypePath());
		otherGetter.setName(mapToOtherEnd.getter());
		otherGetter.setComment("Implements the getter for '" + mapToOtherEnd.umlName() + "'");
		otherGetter.getBody().addToStatements(new OJSimpleStatement("return " + mapToThisEnd.umlName()));
		OJAnnotatedOperation otherSetter = new OJAnnotatedOperation();
		endCls.addToOperations(otherSetter);
		otherSetter.setReturnType(JavaPathNames.Void);
		otherSetter.setName(mapToOtherEnd.setter());
		otherSetter.setComment("Implements the setter for '" + mapToOtherEnd.umlName() + "'");
		otherSetter.addParam("val",mapToThisEnd.javaTypePath());
		OJBlock body20 = new OJBlock();
		otherSetter.setBody(body20);
		OJSimpleStatement exp42 = new OJSimpleStatement("this." + mapToThisEnd.umlName() + " = val");
		body20.addToStatements(exp42);
	}

	private void buildAssociationClass(OJClass owner, IAssociationClass asscls) {
		OJAnnotatedField end1Field = new OJAnnotatedField(end1.umlName(),end1BaseTypePath);
		owner.addToFields(end1Field);
		end1Field.setVisibility(OJVisibilityKind.PRIVATE);
		end1Field.setInitExp(end1DefaultValue);
		OJAnnotatedField end2Field = new OJAnnotatedField(end2.umlName(), end2BaseTypePath);
		owner.addToFields(end2Field);
		end2Field.setVisibility(OJVisibilityKind.PRIVATE);
		end2Field.setInitExp(end2DefaultValue);
		OJAnnotatedOperation end1Getter = new OJAnnotatedOperation();
		owner.addToOperations(end1Getter);
		end1Getter.setReturnType(end1BaseTypePath);
		end1Getter.setName(end1GetterName);
		end1Getter.setComment("Implements the getter for " + asscls.getEnd1().getName());
		end1Getter.getBody().addToStatements(new OJSimpleStatement("return " + end1.umlName()));
		OJAnnotatedOperation end2Getter = new OJAnnotatedOperation();
		owner.addToOperations(end2Getter);
		end2Getter.setReturnType(end2BaseTypePath);
		end2Getter.setName(end2GetterName);
		end2Getter.setComment("Implements the getter for " + asscls.getEnd2().getName());
		end2Getter.getBody().addToStatements(new OJSimpleStatement("return " + end2.umlName()));
		OJAnnotatedOperation clean = new OJAnnotatedOperation();
		owner.addToOperations(clean);
		clean.setReturnType(JavaPathNames.Void);
		clean.setName("clean");
		clean.setComment("Implements the removal of both ends of  '" + asscls.getName() + "'");
		clean.getBody().addToStatements(new OJSimpleStatement(end1.umlName() + "." + ASSCLS_REMOVER1_NAME + "(this)"));
		clean.getBody().addToStatements(new OJSimpleStatement(end1.umlName() + " = null"));
		clean.getBody().addToStatements(new OJSimpleStatement(end2.umlName() + "." + ASSCLS_REMOVER2_NAME + "(this)"));
		clean.getBody().addToStatements(new OJSimpleStatement(end2.umlName() + " = null"));
	}

	// copied from AttributeGenerator
	// TODO find better place for this op
	private void generateExtraCollOpers(OJClass owner, NakedStructuralFeatureMap featureMap, OJPathName elementType) {
		OJPathName paramPath = buildCollParam(owner, featureMap);
		OJAnnotatedOperation allAdder = new OJAnnotatedOperation();
		owner.addToOperations(allAdder);
		allAdder.setReturnType(JavaPathNames.Void);
		allAdder.setName(featureMap.allAdder());
		allAdder.setStatic(featureMap.isStatic());
		allAdder.setComment("implements the addition of a number of elements to feature '" + featureMap.umlName() + "'");
		OJParameter param21 = new OJParameter();
		allAdder.addToParameters(param21);
		param21.setType(paramPath);
		param21.setName("newElems");
		OJForStatement forNewElems = new OJForStatement();
		allAdder.getBody().addToStatements(forNewElems);
		forNewElems.setElemType(elementType);
		forNewElems.setElemName("item");
		forNewElems.setCollection("newElems");
		forNewElems.setBody(new OJBlock());
		forNewElems.getBody().addToStatements(featureMap.adder() + "(item)");
		OJAnnotatedOperation remover = new OJAnnotatedOperation();
		owner.addToOperations(remover);
		remover.setReturnType(JavaPathNames.Void);
		remover.setName(featureMap.removeAll());
		remover.setStatic(featureMap.isStatic());
		remover.setComment("implements the removal of a number of elements from feature '" + featureMap.umlName() + "'");
		OJParameter param22 = new OJParameter();
		remover.addToParameters(param22);
		param22.setType(paramPath);
		param22.setName("oldElems");
		OJBlock body37 = new OJBlock();
		remover.setBody(body37);
		OJForStatement for8 = new OJForStatement();
		body37.addToStatements(for8);
		for8.setElemType(elementType);
		for8.setElemName("item");
		for8.setCollection("oldElems");
		OJBlock body38 = new OJBlock();
		for8.setBody(body38);
		OJSimpleStatement exp69 = new OJSimpleStatement(featureMap.remover() + "((" + elementType + ")item)");
		body38.addToStatements(exp69);
//		OJAnnotatedOperation removeAll = new OJAnnotatedOperation();
//		owner.addToOperations(removeAll);
//		removeAll.setReturnType(JavaPathNames.Void);
//		removeAll.setName(featureMap.removeAll());
//		removeAll.setStatic(featureMap.isStatic());
//		removeAll.setComment("implements the removal of all elements from feature '" + featureMap.umlName() + "'");
//		OJBlock body39 = new OJBlock();
//		removeAll.setBody(body39);
//		body39.setComment("make a copy of the collection in order to avoid a ConcurrentModificationException");
//		OJForStatement for9 = new OJForStatement();
//		body39.addToStatements(for9);
//		for9.setElemType(elementType);
//		for9.setElemName("item");
//		for9.setCollection("new " + featureMap.javaDefaultTypePath().getCollectionTypeName() + "(" + featureMap.getter() + "())");
//		OJBlock body40 = new OJBlock();
//		for9.setBody(body40);
//		OJSimpleStatement exp70 = new OJSimpleStatement(featureMap.remover() + "((" + elementType + ")item)");
//		body40.addToStatements(exp70);
	}

	private OJPathName buildCollParam(OJClassifier owner, StructuralFeatureMap FEATURE) {
		OJPathName paramPath = JavaPathNames.Collection.getCopy();
		paramPath.addToElementTypes(FEATURE.javaBaseTypePath());
		owner.addToImports(paramPath);
		return paramPath;
	}

	private void getVariables(INakedAssociationClass asscls) {
		end1 = new NakedStructuralFeatureMap(asscls.getEnd1());
		end2 = new NakedStructuralFeatureMap(asscls.getEnd2());
		mapToEnd1 = new NavToAssocClassMap(asscls.getEnd1());
		mapToEnd2 = new NavToAssocClassMap(asscls.getEnd2());
		ASSCLS_NAME = mapToEnd1.javaBaseType();
		end1BaseTypePath = end1.javaBaseTypePath();
		end2BaseTypePath = end2.javaBaseTypePath();
		end1GetterName = end1.getter();
		end2GetterName = end2.getter();
		end1DefaultValue = end1.javaBaseDefaultValue();
		end2DefaultValue = end2.javaBaseDefaultValue();
		ASSCLS_ADDER1_NAME = mapToEnd1.adder();
		ASSCLS_ADDER2_NAME = mapToEnd2.adder();
		ASSCLS_REMOVER1_NAME = mapToEnd1.remover();
		ASSCLS_REMOVER2_NAME = mapToEnd2.remover();
	}
}
