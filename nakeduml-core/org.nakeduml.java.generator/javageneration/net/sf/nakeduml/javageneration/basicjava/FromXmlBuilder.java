package net.sf.nakeduml.javageneration.basicjava;

import java.util.Map;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
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
import org.nakeduml.java.metamodel.OJWhileStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.name.NameConverter;
import org.nakeduml.runtime.domain.IPersistentObject;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	Java6ModelGenerator.class,ToXmlStringBuilder.class
},after = {
	Java6ModelGenerator.class
})
public class FromXmlBuilder extends AbstractStructureVisitor{
	@VisitBefore(matchSubclasses=true)
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
			ojClass.addToImports(Node.class.getName());
			ojClass.addToImports(NodeList.class.getName());
			ojClass.addToImports(Element.class.getName());
			ojClass.addToImports(IntrospectionUtil.class.getName());
			this.buildBuildTreeFromXml(ojClass, c);
			this.buildPopulateReferencesFromXml(ojClass, c);
		}
	}
	private void buildPopulateReferencesFromXml(OJAnnotatedClass owner,INakedClassifier umlClass){
		OJOperation toString = new OJAnnotatedOperation("populateReferencesFromXml");
		owner.addToOperations(toString);
		addParameters(toString);
		if(!(owner instanceof OJAnnotatedInterface)){
			OJWhileStatement whileItems = iterateThroughProperties(toString);
			for(INakedProperty f:umlClass.getEffectiveAttributes()){
				if(!(f.getNakedBaseType() instanceof INakedSimpleType || f.getNakedBaseType() instanceof INakedEnumeration
						|| f.getNakedBaseType() instanceof INakedHelper || f.isDerived())){
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
					owner.addToImports(map.javaBaseTypePath());
					if(!f.isInverse()){
						if(OJUtil.hasOJClass((INakedClassifier) f.getAssociation())){
							OJBlock then = iterateThroughPropertyValues(map, whileItems);
							// TODO instantiate the assocation object and populate it
							then.addToStatements((map.isMany() ? map.adder() : map.setter()) + "((" + map.javaBaseType()
									+ ")map.get(((Element)xml).getAttribute(\"uid\")))");
						}else if(!(f.isComposite() || (f.getOtherEnd() != null && f.getOtherEnd().isComposite()))){
							OJBlock then = iterateThroughPropertyValues(map, whileItems);
							then.addToStatements((map.isMany() ? map.adder() : map.setter()) + "((" + map.javaBaseType()
									+ ")map.get(((Element)xml).getAttribute(\"uid\")))");
						}
					}else if(f.isComposite()){
						OJBlock then = iterateThroughPropertyValues(map, whileItems);
						then.addToStatements("((" + map.javaBaseType()
								+ ")map.get(((Element)xml).getAttribute(\"uid\"))).populateReferencesFromXml((Element)currentPropertyValueNode, map)");
					}
				}
			}
		}
	}
	protected void addParameters(OJOperation toString){
		toString.addParam("xml", new OJPathName(Element.class.getName()));
		OJPathName type = new OJPathName(Map.class.getName());
		type.addToElementTypes(new OJPathName("String"));
		type.addToElementTypes(new OJPathName(IPersistentObject.class.getName()));
		toString.addParam("map", type);
	}
	private void buildBuildTreeFromXml(OJAnnotatedClass owner,INakedClassifier umlClass){
		String rootObjectName = NameConverter.capitalize(umlClass.getRootObject().getName());
		owner.addToImports(UtilityCreator.getUtilPathName() + "." + rootObjectName + "Formatter");
		OJOperation toString = new OJAnnotatedOperation("buildTreeFromXml");
		addParameters(toString);
		owner.addToOperations(toString);
		if(!(owner instanceof OJAnnotatedInterface)){
			toString.getBody().addToStatements("setUid(xml.getAttribute(\"uid\"))");
			for(INakedProperty f:umlClass.getEffectiveAttributes()){
				if((f.getNakedBaseType() instanceof INakedSimpleType || f.getNakedBaseType() instanceof INakedEnumeration) && !(OJUtil.isBuiltIn(f) || f.isDerived())){
					populateAttributes(owner, rootObjectName, toString, f);
				}
			}
			OJWhileStatement whileItems = iterateThroughProperties(toString);
			for(INakedProperty f:umlClass.getEffectiveAttributes()){
				if(!(f.getNakedBaseType() instanceof INakedSimpleType || f.getNakedBaseType() instanceof INakedEnumeration
						|| f.getNakedBaseType() instanceof INakedHelper || f.isDerived())){
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
					owner.addToImports(map.javaBaseTypePath());
					if(f.isComposite() && !OJUtil.hasOJClass((INakedClassifier) f.getAssociation()) &&isPersistent(f.getNakedBaseType()) || f.getNakedBaseType() instanceof INakedInterface){
						populatePropertyValues(map, whileItems);
					}
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
		OJIfStatement ifNotNull = new OJIfStatement("xml.getAttribute(\"" + map.umlName() + "\")!=null");
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
				forEach.getBody().addToStatements(map.adder() + "(" + map.javaType() + ".valueOf(" + "xml.getAttribute(\"" + map.umlName() + "\")))");
			}
		}
	}
	private void populatePropertyValues(NakedStructuralFeatureMap map,OJWhileStatement w){
		OJBlock thenPart = iterateThroughPropertyValues(map, w);
		OJAnnotatedField curVal = new OJAnnotatedField("curVal", map.javaBaseTypePath());
		thenPart.addToLocals(curVal);
		curVal.setInitExp("(" + map.javaBaseType()
				+ ")IntrospectionUtil.newInstance(IntrospectionUtil.classForName(((Element)currentPropertyNode).getAttribute(\"className\")))");
		String writer = map.isMany() ? map.adder() : map.setter();
		thenPart.addToStatements("this." + writer + "(curVal)");
		thenPart.addToStatements("curVal.buildTreeFromXml((Element)currentPropertyValueNode,map)");
		thenPart.addToStatements("map.put(curVal.getUid(), curVal)");
	}
	protected OJBlock iterateThroughPropertyValues(NakedStructuralFeatureMap map,OJWhileStatement w){
		OJIfStatement ifInstance = new OJIfStatement("currentPropertyNode instanceof Element && currentPropertyNode.getNodeName().equals(\"" + map.umlName() + "\")");
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
