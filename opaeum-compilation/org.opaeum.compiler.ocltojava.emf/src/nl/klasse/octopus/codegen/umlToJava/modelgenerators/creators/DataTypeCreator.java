package nl.klasse.octopus.codegen.umlToJava.modelgenerators.creators;

import java.util.Iterator;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.StringHelpers;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.utilities.JavaPathNames;
import org.opaeum.javageneration.util.OJUtil;

public class DataTypeCreator{
	protected OJClass currentClass = null;
	protected OJUtil ojUtil;
	public DataTypeCreator(OJUtil ojUtil){
		this.ojUtil=ojUtil;
	}
	// TODO use template!!
	public void createDataType(OJClass myClass,DataType in){
		currentClass = myClass;
		currentClass.addToOperations(equal_op(in));
		hashcode_op(in, myClass);
		currentClass.addToFields(hashcode_field(in));
	}
	protected OJOperation equal_op(DataType in){
		String param = StringHelpers.firstCharToLower(in.getName());
		String TYPE = in.getName();
		OJOperation oper = null;
		oper = new OJOperation();
		currentClass.addToOperations(oper);
		oper.setReturnType(JavaPathNames.Bool);
		oper.setName("equals");
		OJParameter param1 = new OJParameter();
		oper.addToParameters(param1);
		param1.setType(JavaPathNames.Object);
		param1.setName(param);
		OJBlock body1 = new OJBlock();
		oper.setBody(body1);
		OJIfStatement if1 = new OJIfStatement();
		if1.setCondition("!(" + param + " instanceof " + TYPE + ")");
		body1.addToStatements(if1);
		OJBlock then1 = new OJBlock();
		if1.setThenPart(then1);
		OJSimpleStatement exp1 = new OJSimpleStatement("return false");
		then1.addToStatements(exp1);
		OJSimpleStatement exp2 = new OJSimpleStatement(TYPE + " par = (" + TYPE + ") " + param);
		body1.addToStatements(exp2);
		param = "par";
		Iterator<?> it = in.getAttributes().iterator();
		while(it.hasNext()){
			Property elem = (Property) it.next();
			StructuralFeatureMap mapper = ojUtil.buildStructuralFeatureMap(elem);
			if(!elem.isDerived()){ // only compare the non redundant fields
				Classifier type = (Classifier) elem.getType();
				String name = mapper.getter() + "()";
				oper.getBody().addToStatements(compareInnerElements(param, type, name));
			}
		}
		oper.getBody().addToStatements("return true");
		return oper;
	}
	protected OJIfStatement compareInnerElements(String param,Classifier type,String name){
		OJIfStatement stat = new OJIfStatement();
		if(EmfClassifierUtil.comformsToLibraryType(type, IOclLibrary.IntegerTypeName)
				|| EmfClassifierUtil.comformsToLibraryType(type, IOclLibrary.BooleanTypeName)){
			stat.setCondition("this." + name + " != " + param + "." + name);
		}else if(EmfClassifierUtil.comformsToLibraryType(type, IOclLibrary.RealTypeName)){
			stat.setCondition("Float.floatToIntBits(this." + name + ".floatValue()) != " + "Float.floatToIntBits( " + param + "." + name
					+ ".floatValue())");
		}else{
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
	protected OJField hashcode_field(DataType c){
		OJField field = new OJField();
		field.setName("hashCode");
		field.setType(JavaPathNames.Int);
		field.setVisibility(OJVisibilityKind.PRIVATE);
		field.setVolatile(true);
		field.setInitExp("0");
		field.setComment("See Item 8 from \'Effective Java\'");
		return field;
	}
	private void hashcode_op(DataType in,OJClass owner){
		OJIfStatement ifStat = null;
		/**
		 * <octel var="owner"> <method type="%JavaPathNames.Int%" name="hashCode"> <comment>See Item 8 from 'Effective Java' </comment> <body>
		 * <if var="ifStat"> hashCode == 0 <then> int result = 17; </then></if> return hashCode; </body> </method> </octel>
		 */
		
		/* <method> */
		OJOperation method1 = new OJOperation();
		owner.addToOperations(method1);
		method1.setReturnType(JavaPathNames.Int);
		method1.setName("hashCode");
		method1.setComment("See Item 8 from 'Effective Java'");
		OJBlock body2 = new OJBlock();
		method1.setBody(body2);
		ifStat = new OJIfStatement();
		ifStat.setCondition("hashCode == 0");
		body2.addToStatements(ifStat);
		OJBlock then2 = new OJBlock();
		ifStat.setThenPart(then2);
		OJSimpleStatement exp3 = new OJSimpleStatement("int result = 17");
		then2.addToStatements(exp3);
		/* <then/> */
		/* <if/> */
		OJSimpleStatement exp4 = new OJSimpleStatement("return hashCode");
		body2.addToStatements(exp4);
		OJBlock thenPart = ifStat.getThenPart();
		Iterator<?> it = in.getAttributes().iterator();
		while(it.hasNext()){
			Property elem = (Property) it.next();
			StructuralFeatureMap mapper = ojUtil.buildStructuralFeatureMap(elem);
			String fieldname = mapper.fieldname();
			if(!elem.isDerived()){ // only use the non redundant fields
				if(EmfClassifierUtil.comformsToLibraryType(elem.getType(), IOclLibrary.IntegerTypeName)){
					thenPart.addToStatements("result = 37*result + " + fieldname);
				}else if(EmfClassifierUtil.comformsToLibraryType(elem.getType(), IOclLibrary.BooleanTypeName)){
					thenPart.addToStatements("result = 37*result + (new Boolean(" + fieldname + ")).hashCode()");
				}else if(EmfClassifierUtil.comformsToLibraryType(elem.getType(), IOclLibrary.RealTypeName)){
					thenPart.addToStatements("result = 37*result + Float.floatToIntBits(" + fieldname + ")");
				}else{
					thenPart.addToStatements("result = 37*result + " + fieldname + ".hashCode()");
				}
			}
		}
		thenPart.addToStatements("hashCode = result");
	}
}
