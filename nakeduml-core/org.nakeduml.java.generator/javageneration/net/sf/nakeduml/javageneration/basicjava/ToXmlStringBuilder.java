package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.maps.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedComplexStructure;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedHelper;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJForStatement;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.name.NameConverter;

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
	private void buildToXmlReferenceString(OJAnnotatedClass owner,INakedClassifier umlClass){
		OJOperation toString = new OJAnnotatedOperation("toXmlReferenceString");
		toString.setReturnType(new OJPathName("String"));
		if(!(owner instanceof OJAnnotatedInterface)){
			toString.getBody().addToStatements(
					"return \"<" + umlClass.getMappingInfo().getJavaName() + " uid=\\\"\"+getUid() + \"\\\"/>\"");
		}
		owner.addToOperations(toString);
	}
	private void buildToXmlString(OJAnnotatedClass owner,INakedClassifier umlClass){
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
									"sb.append(\"" + map.umlName() + "=\\\"\"+ " + rootObjectName + "Formatter.getInstance().format" + f.getNakedBaseType().getName()
											+ "(" + map.getter() + "())+\"\\\" \")");
						}else{
							// ENumeration
							ifNotNull.getThenPart().addToStatements("sb.append(\"" + map.umlName() + "=\\\"\"+ " + map.getter() + "().name() + \"\\\" \")");
						}
					}else{
						toString.getBody().addToStatements("sb.append(\"" + map.umlName() + "=\\\"\")");
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
					String openProperty = "sb.append(\"\\n<" + map.umlName() + " propertyId=\\\""+ map.getProperty().getMappingInfo().getNakedUmlId() + "\\\">\")";
					String closeProperty = "sb.append(\"\\n</" + map.umlName() + ">\")";
					if(map.isOne()){
						OJIfStatement ifNull = new OJIfStatement(map.getter() + "()==null", "sb.append(\"\\n<" + map.umlName() + "/>\")");
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
						forEach.setElemName(map.umlName());
						forEach.setCollection(map.getter() + "()");
						forEach.setBody(new OJBlock());
						if(XmlUtil.isXmlSubElement(f)){
							forEach.getBody().addToStatements("sb.append(\"\\n\" + " + map.umlName() + ".toXmlString())");
						}else{
							forEach.getBody().addToStatements("sb.append(\"\\n\" + " + map.umlName() + ".toXmlReferenceString())");
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
