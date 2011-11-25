package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedHelper;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		Java6ModelGenerator.class,HashcodeBuilder.class,FormatterBuilder.class
},after = {
	Java6ModelGenerator.class
})
public class ToXmlStringBuilder extends AbstractStructureVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitInterface(INakedInterface i){
		if(OJUtil.hasOJClass(i) && !(i instanceof INakedHelper)){
			OJAnnotatedClass ojClass = findJavaClass(i);
			this.buildToXmlString(ojClass, i);
			this.buildToXmlReferenceString(ojClass, i);
		}
	}
	private void visitClass(INakedClassifier c){
		if(OJUtil.hasOJClass(c) && !(c instanceof INakedEnumeration) && c.getStereotype(StereotypeNames.HELPER) == null){
			OJAnnotatedClass ojClass = findJavaClass(c);
			this.buildToXmlString(ojClass, c);
			this.buildToXmlReferenceString(ojClass, c);
		}
	}
	protected void buildToXmlReferenceString(OJAnnotatedClass owner,INakedClassifier umlClass){
		OJOperation toString = new OJAnnotatedOperation("toXmlReferenceString");
		toString.setReturnType(new OJPathName("String"));
		if(!(owner instanceof OJAnnotatedInterface)){
			toString.getBody().addToStatements(
					"return \"<" + umlClass.getMappingInfo().getJavaName() + " uid=\\\"\"+getUid() + \"\\\"/>\"");
		}
		owner.addToOperations(toString);
	}
	protected void buildToXmlString(OJAnnotatedClass owner,INakedClassifier umlClass){
		String rootObjectName = NameConverter.capitalize(umlClass.getRootObject().getName());
		owner.addToImports(UtilityCreator.getUtilPathName() + "." + rootObjectName + "Formatter");
		OJOperation toString = new OJAnnotatedOperation("toXmlString");
		toString.setReturnType(new OJPathName("String"));
		owner.addToOperations(toString);
		if(!(owner instanceof OJAnnotatedInterface)){
			OJAnnotatedField sb = new OJAnnotatedField("sb", new OJPathName("StringBuilder"));
			sb.setInitExp("new StringBuilder()");
			toString.getBody().addToLocals(sb);
			toString.getBody().addToStatements("sb.append(\"<" + umlClass.getMappingInfo().getJavaName() + " \")");
			toString.getBody().addToStatements("sb.append(\"classUuid=\\\"" + umlClass.getId() + "\\\" \")");
			toString.getBody().addToStatements("sb.append(\"className=\\\"" + OJUtil.classifierPathname(umlClass) + "\\\" \")");
			toString.getBody().addToStatements("sb.append(\"uid=\\\"\" + this.getUid() + \"\\\" \")");
			for(INakedProperty f:umlClass.getEffectiveAttributes()){
				if(XmlUtil.isXmlAttribute(f)){
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
					owner.addToImports(map.javaBaseTypePath());
					if(map.isOne()){
						OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null");
						toString.getBody().addToStatements(ifNotNull);
						if(f.getNakedBaseType() instanceof INakedSimpleType){
							ifNotNull.getThenPart().addToStatements(
									"sb.append(\"" + map.fieldname() + "=\\\"\"+ " + rootObjectName + "Formatter.getInstance().format" + f.getNakedBaseType().getName()
											+ "(" + map.getter() + "())+\"\\\" \")");
						}else{
							// ENumeration
							ifNotNull.getThenPart().addToStatements("sb.append(\"" + map.fieldname() + "=\\\"\"+ " + map.getter() + "().name() + \"\\\" \")");
						}
					}else{
						toString.getBody().addToStatements("sb.append(\"" + map.fieldname() + "=\\\"\")");
						OJForStatement forEach = new OJForStatement("val", map.javaBaseTypePath(), map.getter() + "()");
						toString.getBody().addToStatements(forEach);
						toString.getBody().addToStatements("sb.append(\"\\\" \")");
						if(f.getNakedBaseType() instanceof INakedSimpleType){
							forEach.getBody().addToStatements(
									"sb.append(" + rootObjectName + "Formatter.getInstance().format" + f.getNakedBaseType().getName() + "(val) + \";\")");
						}else{
							// ENumeration
							forEach.getBody().addToStatements("sb.append(val.name() + \";\")");
						}
					}
				}
			}
			toString.getBody().addToStatements("sb.append(\">\")");
			for(INakedProperty f:umlClass.getEffectiveAttributes()){
				if(XmlUtil.isXmlReference(f) || XmlUtil.isXmlSubElement(f)){
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
					owner.addToImports(map.javaBaseTypePath());
					String openProperty = "sb.append(\"\\n<" + map.fieldname() + " propertyId=\\\""+ map.getProperty().getMappingInfo().getOpaeumId() + "\\\">\")";
					String closeProperty = "sb.append(\"\\n</" + map.fieldname() + ">\")";
					if(map.isOne()){
						OJIfStatement ifNull = new OJIfStatement(map.getter() + "()==null", "sb.append(\"\\n<" + map.fieldname() + "/>\")");
						toString.getBody().addToStatements(ifNull);
						ifNull.setElsePart(new OJBlock());
						ifNull.getElsePart().addToStatements(openProperty);
						if(XmlUtil.isXmlSubElement(f)){
							ifNull.getElsePart().addToStatements("sb.append(\"\\n\" + " + map.getter() + "().toXmlString())");
						}else{
							ifNull.getElsePart().addToStatements("sb.append(\"\\n\" + " + map.getter() + "().toXmlReferenceString())");
						}
						ifNull.getElsePart().addToStatements(closeProperty);
					}else{
						toString.getBody().addToStatements(openProperty);
						OJForStatement forEach = new OJForStatement();
						toString.getBody().addToStatements(forEach);
						forEach.setElemType(map.javaBaseTypePath());
						forEach.setElemName(map.fieldname());
						forEach.setCollection(map.getter() + "()");
						forEach.setBody(new OJBlock());
						if(XmlUtil.isXmlSubElement(f)){
							forEach.getBody().addToStatements("sb.append(\"\\n\" + " + map.fieldname() + ".toXmlString())");
						}else{
							forEach.getBody().addToStatements("sb.append(\"\\n\" + " + map.fieldname() + ".toXmlReferenceString())");
						}
						toString.getBody().addToStatements(closeProperty);
					}
				}
			}
			toString.getBody().addToStatements("sb.append(\"\\n</" + umlClass.getMappingInfo().getJavaName() + ">\")");
			toString.getBody().addToStatements("return sb.toString()");
		}
	}
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
		// TODO Auto-generated method stub
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
		visitClass(umlOwner);
	}
}
