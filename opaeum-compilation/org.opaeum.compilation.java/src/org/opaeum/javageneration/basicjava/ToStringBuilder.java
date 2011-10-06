package org.opaeum.javageneration.basicjava;

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
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedProperty;
@StepDependency(phase = JavaTransformationPhase.class,requires = {
	Java6ModelGenerator.class,AttributeImplementor.class
},after = {
	Java6ModelGenerator.class
})

public class ToStringBuilder extends StereotypeAnnotator{
	public static String HASHCODE_IN_TO_STRING = "HASHCODE_IN_TO_STRING";
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c){
		if(OJUtil.hasOJClass(c)){
			OJAnnotatedClass ojClass = findJavaClass(c);
			this.buildToString(ojClass, c);
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation no){
		if(BehaviorUtil.hasExecutionInstance(no)){
			this.visitClass(no.getMessageStructure());
		}
	}
	@VisitBefore()
	public void visitOpaqueAction(INakedEmbeddedSingleScreenTask oa){
		this.visitClass(oa.getMessageStructure());
	}
	private void buildToString(OJAnnotatedClass owner,INakedClassifier umlClass){
		OJOperation toString = new OJAnnotatedOperation("toString");
		toString.setReturnType(new OJPathName("String"));
		OJAnnotatedField sb = new OJAnnotatedField("sb",new OJPathName("StringBuilder"));
		sb.setInitExp("new StringBuilder()");
		toString.getBody().addToLocals(sb);
		toString.getBody().addToStatements("sb.append(super.toString())");
		for(INakedProperty f:umlClass.getEffectiveAttributes()){
			if(!OJUtil.isBuiltIn(f)){
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(f);
				if(map.isOne() && !f.isInverse()){
					if(map.getProperty().getNakedBaseType() instanceof INakedEntity || map.getProperty().getNakedBaseType() instanceof INakedInterface){
						OJIfStatement ifNull = new OJIfStatement(map.getter() + "()==null", "sb.append(\"" + map.umlName() + "=null;\")");
						ifNull.setElsePart(new OJBlock());
						OJSimpleStatement b = null;
						ifNull.getElsePart().addToStatements("sb.append(\"" + map.umlName() + "=\"+" + map.getter() + "().getClass().getSimpleName()+\"[\")");
						if(f.getNakedBaseType().findEffectiveAttribute("name") != null){
							b = new OJSimpleStatement("sb.append(" + map.getter() + "().getName())");
						}else{
							b = new OJSimpleStatement("sb.append(" + map.getter() + "().hashCode())");
						}
						b.setName(HASHCODE_IN_TO_STRING);
						ifNull.getElsePart().addToStatements(b);
						ifNull.getElsePart().addToStatements("sb.append(\"];\")");
						toString.getBody().addToStatements(ifNull);
					}else{
						toString.getBody().addToStatements("sb.append(\"" + map.umlName() + "=\")");
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
