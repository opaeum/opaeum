package org.opaeum.javageneration.basicjava;

import java.util.List;
import java.util.Map;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;
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
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.java.metamodel.OJWhileStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.domain.IntrospectionUtil;
import org.opaeum.runtime.environment.Environment;
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
	public void visitInterface(Interface i){
		if(ojUtil.hasOJClass(i) && !(EmfClassifierUtil.isHelper(i ))){
			OJAnnotatedClass ojClass = findJavaClass(i);
			this.buildBuildTreeFromXml(ojClass, i);
			this.buildPopulateReferencesFromXml(ojClass, i);
		}
	}
	private void visitClass(Classifier c){
		if(ojUtil.hasOJClass(c) && !(c instanceof Enumeration) && !EmfClassifierUtil.isHelper(c)){
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
	protected void buildPopulateReferencesFromXml(OJAnnotatedClass owner,Classifier umlClass){
		OJOperation toString = new OJAnnotatedOperation("populateReferencesFromXml");
		owner.addToOperations(toString);
		addParameters(toString);
		if(!(owner instanceof OJAnnotatedInterface)){
			OJWhileStatement whileItems = iterateThroughProperties(toString);
			//TODO rather leverage AbstracStructureVisitor.visitProperty
			for(Property f:getLibrary().getEffectiveAttributes(umlClass)){
				PropertyMap map = ojUtil.buildStructuralFeatureMap(f);
				if(XmlUtil.isXmlReference(map)){
					owner.addToImports(map.javaBaseTypePath());
					OJBlock then = iterateThroughPropertyValues(map, whileItems);
					then.addToStatements((map.isMany() ? map.adder() : map.setter()) + "((" + map.javaBaseType()
							+ ")map.get(((Element)currentPropertyValueNode).getAttribute(\"uid\")))");
				}else if(XmlUtil.isXmlSubElement(map)){
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
	protected void buildBuildTreeFromXml(OJAnnotatedClass owner,Classifier umlClass){
		String rootObjectName = NameConverter.capitalize(EmfElementFinder.getRootObject(umlClass).getName());
		owner.addToImports(UtilityCreator.getUtilPathName() + "." + rootObjectName + "Formatter");
		OJOperation toString = new OJAnnotatedOperation("buildTreeFromXml");
		addParameters(toString);
		owner.addToOperations(toString);
		if(!(owner instanceof OJAnnotatedInterface)){
			toString.getBody().addToStatements("setUid(xml.getAttribute(\"uid\"))");
			for(Property f:getLibrary().getEffectiveAttributes(umlClass)){
				PropertyMap map = ojUtil.buildStructuralFeatureMap(f);
				if(XmlUtil.isXmlAttribute(map)){
					populateAttributes(owner, rootObjectName, toString, f);
				}
			}
			OJWhileStatement whileItems = iterateThroughProperties(toString);
			for(Property f:getLibrary().getEffectiveAttributes(umlClass)){
				PropertyMap map = ojUtil.buildStructuralFeatureMap(f);
				if(XmlUtil.isXmlSubElement(map)){
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
	protected void populateAttributes(OJAnnotatedClass owner,String rootObjectName,OJOperation toString,Property f){
		PropertyMap map = ojUtil.buildStructuralFeatureMap(f);
		owner.addToImports(map.javaBaseTypePath());
		OJIfStatement ifNotNull = new OJIfStatement("xml.getAttribute(\"" + map.fieldname() + "\").length()>0");
		toString.getBody().addToStatements(ifNotNull);
		if(map.isOne()){
			if(EmfClassifierUtil.isSimpleType(map.getBaseType())){
				ifNotNull.getThenPart().addToStatements(
						map.setter() + "(" + rootObjectName + "Formatter.getInstance().parse" + map.getBaseType().getName() + "(xml.getAttribute(\"" + map.fieldname()
								+ "\")))");
			}else{
				ifNotNull.getThenPart().addToStatements(map.setter() + "(" + map.javaType() + ".valueOf(" + "xml.getAttribute(\"" + map.fieldname() + "\")))");
			}
		}else{
			OJForStatement forEach = new OJForStatement("val", new OJPathName("String"), "xml.getAttribute(\"" + map.fieldname() + "\").split(\";\")");
			ifNotNull.getThenPart().addToStatements(forEach);
			if(EmfClassifierUtil.isSimpleType(map.getBaseType())){
				forEach.getBody().addToStatements(map.adder() + "(" + rootObjectName + "Formatter.getInstance().parse" + map.getBaseType().getName() + "(val))");
			}else{
				forEach.getBody().addToStatements(map.adder() + "(" + map.javaBaseType() + ".valueOf(val))");
			}
		}
	}
	private void populatePropertyValues(PropertyMap map,OJWhileStatement w){
		OJBlock thenPart = iterateThroughPropertyValues(map, w);
		OJAnnotatedField curVal = new OJAnnotatedField("curVal", map.javaBaseTypePath());
		thenPart.addToLocals(curVal);
		OJTryStatement tryNewInstance = new OJTryStatement();
		thenPart.addToStatements(tryNewInstance);
		tryNewInstance.getTryPart().addToStatements("curVal=IntrospectionUtil.newInstance(((Element)currentPropertyValueNode).getAttribute(\"className\"))");
		tryNewInstance.setCatchParam(new OJParameter("e", new OJPathName("Exception")));
		tryNewInstance.getCatchPart().addToStatements("curVal=Environment.getInstance().getMetaInfoMap().newInstance(((Element)currentPropertyValueNode).getAttribute(\"classUuid\"))");
		thenPart.addToStatements("curVal.buildTreeFromXml((Element)currentPropertyValueNode,map)");
		if(isMap(map.getProperty())){
			List<Property> qualifiers = map.getProperty().getQualifiers();
			String varName = "curVal";
			String string = ojUtil.addQualifierArguments(qualifiers, varName);
			thenPart.addToStatements("this." + map.adder() + "(" + string + "curVal)");
		}else{
			String writer = map.isMany() ? map.adder() : map.setter();
			thenPart.addToStatements("this." + writer + "(curVal)");
		}
		thenPart.addToStatements("map.put(curVal.getUid(), curVal)");
	}
	protected OJBlock iterateThroughPropertyValues(PropertyMap map,OJWhileStatement w){
		OJIfStatement ifInstance = new OJIfStatement("currentPropertyNode instanceof Element && (currentPropertyNode.getNodeName().equals(\"" + map.fieldname()
				+ "\") || ((Element)currentPropertyNode).getAttribute(\"propertyId\").equals(\"" + EmfWorkspace.getOpaeumId(map.getProperty()) + "\"))");
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
	protected void visitProperty(Classifier owner,PropertyMap buildStructuralFeatureMap){
		//TODO find fromXml method
		//
		// TODO Auto-generated method stub
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
		visitClass(umlOwner);
	}
}
