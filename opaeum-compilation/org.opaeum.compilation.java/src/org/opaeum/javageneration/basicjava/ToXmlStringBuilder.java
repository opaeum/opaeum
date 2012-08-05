package org.opaeum.javageneration.basicjava;




import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.workspace.EmfWorkspace;
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
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		Java6ModelGenerator.class,HashcodeBuilder.class,FormatterBuilder.class
},after = {
	Java6ModelGenerator.class
})
public class ToXmlStringBuilder extends AbstractStructureVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitInterface(Interface i){
		if(OJUtil.hasOJClass(i) && !(EmfClassifierUtil.isHelper(i ))){
			OJAnnotatedClass ojClass = findJavaClass(i);
			this.buildToXmlString(ojClass, i);
			this.buildToXmlReferenceString(ojClass, i);
		}
	}
	private void visitClass(Classifier c){
		if(OJUtil.hasOJClass(c) && !(c instanceof Enumeration) && EmfClassifierUtil.isHelper(c)){
			OJAnnotatedClass ojClass = findJavaClass(c);
			this.buildToXmlString(ojClass, c);
			this.buildToXmlReferenceString(ojClass, c);
		}
	}
	protected void buildToXmlReferenceString(OJAnnotatedClass owner,Classifier umlClass){
		OJOperation toString = new OJAnnotatedOperation("toXmlReferenceString");
		toString.setReturnType(new OJPathName("String"));
		if(!(owner instanceof OJAnnotatedInterface)){
			toString.getBody().addToStatements(
					"return \"<" + umlClass.getName() + " uid=\\\"\"+getUid() + \"\\\"/>\"");
		}
		owner.addToOperations(toString);
	}
	protected void buildToXmlString(OJAnnotatedClass owner,Classifier umlClass){
		String rootObjectName = NameConverter.capitalize(EmfElementFinder.getRootObject( umlClass).getName());
		owner.addToImports(UtilityCreator.getUtilPathName() + "." + rootObjectName + "Formatter");
		OJOperation toString = new OJAnnotatedOperation("toXmlString");
		toString.setReturnType(new OJPathName("String"));
		owner.addToOperations(toString);
		if(!(owner instanceof OJAnnotatedInterface)){
			OJAnnotatedField sb = new OJAnnotatedField("sb", new OJPathName("StringBuilder"));
			sb.setInitExp("new StringBuilder()");
			toString.getBody().addToLocals(sb);
			toString.getBody().addToStatements("sb.append(\"<" + umlClass.getName() + " \")");
			toString.getBody().addToStatements("sb.append(\"classUuid=\\\"" + EmfWorkspace.getId( umlClass) + "\\\" \")");
			toString.getBody().addToStatements("sb.append(\"className=\\\"" + ojUtil.classifierPathname(umlClass) + "\\\" \")");
			toString.getBody().addToStatements("sb.append(\"uid=\\\"\" + this.getUid() + \"\\\" \")");
			for(Property f:getLibrary().getEffectiveAttributes( umlClass)){
				if(XmlUtil.isXmlAttribute(f)){
					StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(f);
					owner.addToImports(map.javaBaseTypePath());
					if(map.isOne()){
						OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null");
						toString.getBody().addToStatements(ifNotNull);
						if(EmfClassifierUtil.isSimpleType(f.getType() )){
							ifNotNull.getThenPart().addToStatements(
									"sb.append(\"" + map.fieldname() + "=\\\"\"+ " + rootObjectName + "Formatter.getInstance().format" + f.getType().getName()
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
						if(EmfClassifierUtil.isSimpleType(f.getType() )){
							forEach.getBody().addToStatements(
									"sb.append(" + rootObjectName + "Formatter.getInstance().format" + f.getType().getName() + "(val) + \";\")");
						}else{
							// ENumeration
							forEach.getBody().addToStatements("sb.append(val.name() + \";\")");
						}
					}
				}
			}
			toString.getBody().addToStatements("sb.append(\">\")");
			for(Property f:getLibrary().getEffectiveAttributes( umlClass)){
				if(XmlUtil.isXmlReference(f) || XmlUtil.isXmlSubElement(f)){
					StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(f);
					owner.addToImports(map.javaBaseTypePath());
					String openProperty = "sb.append(\"\\n<" + map.fieldname() + " propertyId=\\\""+ EmfWorkspace.getOpaeumId( map.getProperty()) + "\\\">\")";
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
			toString.getBody().addToStatements("sb.append(\"\\n</" + umlClass.getName() + ">\")");
			toString.getBody().addToStatements("return sb.toString()");
		}
	}
	@Override
	protected void visitProperty(Classifier owner,StructuralFeatureMap buildStructuralFeatureMap){
		// TODO Auto-generated method stub
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
		visitClass(umlOwner);
	}
}
