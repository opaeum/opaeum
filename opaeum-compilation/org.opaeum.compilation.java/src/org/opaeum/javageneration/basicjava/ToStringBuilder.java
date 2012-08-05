package org.opaeum.javageneration.basicjava;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.StereotypeAnnotator;
import org.opaeum.javageneration.util.OJUtil;

@StepDependency(phase = JavaTransformationPhase.class,requires = {Java6ModelGenerator.class,AttributeImplementor.class},after = {Java6ModelGenerator.class})
public class ToStringBuilder extends StereotypeAnnotator{
	public static String HASHCODE_IN_TO_STRING = "HASHCODE_IN_TO_STRING";
	@VisitAfter(matchSubclasses = true)
	public void visitClass(Classifier c){
		if(OJUtil.hasOJClass(c)){
			OJAnnotatedClass ojClass = findJavaClass(c);
			this.buildToString(ojClass, c);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(Operation no){
		if(EmfBehaviorUtil.hasExecutionInstance(no)){
			this.visitClass(getLibrary().getMessageStructure(no));
		}
	}
	@VisitBefore()
	public void visitOpaqueAction(OpaqueAction oa){
		if(EmfActionUtil.isSingleScreenTask(oa)){
			this.visitClass(getLibrary().getMessageStructure( oa));
		}
	}
	private void buildToString(OJAnnotatedClass owner,Classifier umlClass){
		OJOperation toString = new OJAnnotatedOperation("toString");
		toString.setReturnType(new OJPathName("String"));
		OJAnnotatedField sb = new OJAnnotatedField("sb", new OJPathName("StringBuilder"));
		sb.setInitExp("new StringBuilder()");
		toString.getBody().addToLocals(sb);
		toString.getBody().addToStatements("sb.append(super.toString())");
		for(Property f:getLibrary().getEffectiveAttributes(umlClass)){
			if(!OJUtil.isBuiltIn(f)){
				StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(f);
				if(map.isOne() && !EmfPropertyUtil.isInverse(f)){
					if(map.getProperty().getType() instanceof Class || map.getProperty().getType() instanceof Interface){
						OJIfStatement ifNull = new OJIfStatement(map.getter() + "()==null", "sb.append(\"" + map.fieldname() + "=null;\")");
						ifNull.setElsePart(new OJBlock());
						OJSimpleStatement b = null;
						ifNull.getElsePart().addToStatements(
								"sb.append(\"" + map.fieldname() + "=\"+" + map.getter() + "().getClass().getSimpleName()+\"[\")");
						if(getLibrary().findEffectiveAttribute( (Classifier) f.getType(),"name") != null){
							b = new OJSimpleStatement("sb.append(" + map.getter() + "().getName())");
						}else{
							b = new OJSimpleStatement("sb.append(" + map.getter() + "().hashCode())");
						}
						b.setName(HASHCODE_IN_TO_STRING);
						ifNull.getElsePart().addToStatements(b);
						ifNull.getElsePart().addToStatements("sb.append(\"];\")");
						toString.getBody().addToStatements(ifNull);
					}else{
						toString.getBody().addToStatements("sb.append(\"" + map.fieldname() + "=\")");
						toString.getBody().addToStatements("sb.append(" + map.getter() + "())");
						toString.getBody().addToStatements("sb.append(\";\")");
					}
				}
			}
		}
		toString.getBody().addToStatements("return sb.toString()");
		owner.addToOperations(toString);
	}
}
