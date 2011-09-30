package org.opeum.javageneration.basicjava;

import java.util.Map;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedHelper;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedSimpleType;
import org.opeum.metamodel.core.internal.StereotypeNames;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJForStatement;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJParameter;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.OJTryStatement;
import org.opeum.java.metamodel.OJWhileStatement;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.name.NameConverter;
import org.opeum.runtime.domain.IntrospectionUtil;
import org.opeum.runtime.environment.Environment;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		Java6ModelGenerator.class,ToXmlStringBuilder.class
},after = {
	Java6ModelGenerator.class
})
public class FromXmlBuilder extends AbstractStructureVisitor{
	@VisitBefore(matchSubclasses = true)
	public void visitInterface(INakedInterface i){
		if(OJUtil.hasOJClass(i) && !(i instanceof INakedHelper)){
			OJAnnotatedClass ojClass = findJavaClass(i);
			this.buildBuildTreeFromXml(ojClass, i);
			this.buildPopulateReferencesFromXml(ojClass, i);
		}
	}
	private void visitClass(INakedClassifier c){
		if(OJUtil.hasOJClass(c) && !(c instanceof INakedEnumeration) && c.getStereotype(StereotypeNames.HELPER) == null){
			OJAnnotatedClass ojClass = findJavaClass(c);
			ojClass.addToImports(Environment.class.getName());
			ojClass.addToImports(Node.class.getName());
			ojClass.addToImports(NodeList.class.getName());
			ojClass.addToImports(Element.class.getName());
			ojClass.addToImports(IntrospectionUtil.class.getName());
			this.buildBuildTreeFromXml(ojClass, c);
			this.buildPopulateReferencesFromXml(ojClass, c);
		}
	}
	protected void buildPopulateReferencesFromXml(OJAnnotatedClass owner,INakedClassifier umlClass){
		OJOperation toString = new OJAnnotatedOperation("populateReferencesFromXml");
		owner.addToOperations(toString);
		addParameters(toString);
		if(!(owner instanceof OJAnnotatedInterface)){
			OJWhileStatement whileItems = iterateThroughProperties(toString);
			for(INakedProperty f:umlClass.getEffectiveAttributes()){
				if(XmlUtil.isXmlReference(f)){
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
					owner.addToImports(map.javaBaseTypePath());
					OJBlock then = iterateThroughPropertyValues(map, whileItems);
					then.addToStatements((map.isMany() ? map.adder() : map.setter()) + "((" + map.javaBaseType()
							+ ")map.get(((Element)currentPropertyValueNode).getAttribute(\"uid\")))");
				}else if(XmlUtil.isXmlSubElement(f)){
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
					OJBlock then = iterateThroughPropertyValues(map, whileItems);
					then.addToStatements("((" + map.javaBaseType()
							+ ")map.get(((Element)currentPropertyValueNode).getAttribute(\"uid\"))).populateReferencesFromXml((Element)currentPropertyValueNode, map)");
				}
			}
		}
	}
	protected void addParameters(OJOperation toString){
		toString.addParam("xml", new OJPathName(Element.class.getName()));
		OJPathName type = new OJPathName(Map.class.getName());
		type.addToElementTypes(new OJPathName("String"));
		type.addToElementTypes(new OJPathName("Object"));
		toString.addParam("map", type);
	}
	protected void buildBuildTreeFromXml(OJAnnotatedClass owner,INakedClassifier umlClass){
		String rootObjectName = NameConverter.capitalize(umlClass.getRootObject().getName());
		owner.addToImports(UtilityCreator.getUtilPathName() + "." + rootObjectName + "Formatter");
		OJOperation toString = new OJAnnotatedOperation("buildTreeFromXml");
		addParameters(toString);
		owner.addToOperations(toString);
		if(!(owner instanceof OJAnnotatedInterface)){
			toString.getBody().addToStatements("setUid(xml.getAttribute(\"uid\"))");
			for(INakedProperty f:umlClass.getEffectiveAttributes()){
				if(XmlUtil.isXmlAttribute(f)){
					populateAttributes(owner, rootObjectName, toString, f);
				}
			}
			OJWhileStatement whileItems = iterateThroughProperties(toString);
			for(INakedProperty f:umlClass.getEffectiveAttributes()){
				if(XmlUtil.isXmlSubElement(f)){
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
					owner.addToImports(map.javaBaseTypePath());
					populatePropertyValues(map, whileItems);
				}
			}
		}
	}
	protected OJWhileStatement iterateThroughProperties(OJOperation toString){
		OJBlock block = new OJBlock();
		OJAnnotatedField propertyNodes = new OJAnnotatedField("propertyNodes", new OJPathName(NodeList.class.getName()));
		block.addToLocals(propertyNodes);
		propertyNodes.setInitExp("xml.getChildNodes()");
		OJAnnotatedField i = new OJAnnotatedField("i", new OJPathName("int"));
		block.addToLocals(i);
		i.setInitExp("0");
		OJWhileStatement whileItems = new OJWhileStatement();
		block.addToStatements(whileItems);
		whileItems.setCondition("i<propertyNodes.getLength()");
		OJAnnotatedField currentPropertyNode = new OJAnnotatedField("currentPropertyNode", new OJPathName(Node.class.getName()));
		whileItems.getBody().addToLocals(currentPropertyNode);
		currentPropertyNode.setInitExp("propertyNodes.item(i++)");
		toString.getBody().addToStatements(block);
		return whileItems;
	}
	protected void populateAttributes(OJAnnotatedClass owner,String rootObjectName,OJOperation toString,INakedProperty f){
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
		owner.addToImports(map.javaBaseTypePath());
		OJIfStatement ifNotNull = new OJIfStatement("xml.getAttribute(\"" + map.umlName() + "\").length()>0");
		toString.getBody().addToStatements(ifNotNull);
		if(map.isOne()){
			if(f.getNakedBaseType() instanceof INakedSimpleType){
				ifNotNull.getThenPart().addToStatements(
						map.setter() + "(" + rootObjectName + "Formatter.getInstance().parse" + f.getNakedBaseType().getName() + "(xml.getAttribute(\"" + map.umlName()
								+ "\")))");
			}else{
				ifNotNull.getThenPart().addToStatements(map.setter() + "(" + map.javaType() + ".valueOf(" + "xml.getAttribute(\"" + map.umlName() + "\")))");
			}
		}else{
			OJForStatement forEach = new OJForStatement("val", new OJPathName("String"), "xml.getAttribute(\"" + map.umlName() + "\").split(\";\")");
			ifNotNull.getThenPart().addToStatements(forEach);
			if(f.getNakedBaseType() instanceof INakedSimpleType){
				forEach.getBody().addToStatements(map.adder() + "(" + rootObjectName + "Formatter.getInstance().parse" + f.getNakedBaseType().getName() + "(val))");
			}else{
				forEach.getBody().addToStatements(map.adder() + "(" + map.javaBaseType() + ".valueOf(val))");
			}
		}
	}
	private void populatePropertyValues(NakedStructuralFeatureMap map,OJWhileStatement w){
		OJBlock thenPart = iterateThroughPropertyValues(map, w);
		OJAnnotatedField curVal = new OJAnnotatedField("curVal", map.javaBaseTypePath());
		thenPart.addToLocals(curVal);
		OJTryStatement tryNewInstance = new OJTryStatement();
		thenPart.addToStatements(tryNewInstance);
		tryNewInstance.getTryPart().addToStatements("curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute(\"className\"))");
		tryNewInstance.setCatchParam(new OJParameter("e", new OJPathName("Exception")));
		tryNewInstance.getCatchPart().addToStatements(
				"curVal=Environment.getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute(\"classUuid\"))");
		thenPart.addToStatements("curVal.buildTreeFromXml((Element)currentPropertyValueNode,map)");
		String writer = map.isMany() ? map.adder() : map.setter();
		thenPart.addToStatements("this." + writer + "(curVal)");
		thenPart.addToStatements("map.put(curVal.getUid(), curVal)");
	}
	protected OJBlock iterateThroughPropertyValues(NakedStructuralFeatureMap map,OJWhileStatement w){
		OJIfStatement ifInstance = new OJIfStatement("currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals(\"" + map.umlName()
				+ "\") || ((Element)currentPropertyNode).getAttribute(\"propertyId\").equals(\"" + map.getProperty().getMappingInfo().getOpeumId() + "\"))");
		OJAnnotatedField propertyValueNodes = new OJAnnotatedField("propertyValueNodes", new OJPathName(NodeList.class.getName()));
		ifInstance.getThenPart().addToLocals(propertyValueNodes);
		propertyValueNodes.setInitExp("currentPropertyNode.getChildNodes()");
		OJAnnotatedField j = new OJAnnotatedField("j", new OJPathName("int"));
		ifInstance.getThenPart().addToLocals(j);
		j.setInitExp("0");
		OJWhileStatement whileValueItems = new OJWhileStatement();
		ifInstance.getThenPart().addToStatements(whileValueItems);
		whileValueItems.setCondition("j<propertyValueNodes.getLength()");
		OJAnnotatedField currentPropertyValueNode = new OJAnnotatedField("currentPropertyValueNode", new OJPathName(Node.class.getName()));
		whileValueItems.getBody().addToLocals(currentPropertyValueNode);
		currentPropertyValueNode.setInitExp("propertyValueNodes.item(j++)");
		OJIfStatement ifInstance2 = new OJIfStatement("currentPropertyValueNode instanceof Element");
		whileValueItems.getBody().addToStatements(ifInstance2);
		w.getBody().addToStatements(ifInstance);
		OJBlock thenPart = ifInstance2.getThenPart();
		return thenPart;
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
