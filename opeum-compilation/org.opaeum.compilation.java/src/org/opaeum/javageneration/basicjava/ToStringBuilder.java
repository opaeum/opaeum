package org.opeum.javageneration.basicjava;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.OJSimpleStatement;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.StereotypeAnnotator;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.BehaviorUtil;
import org.opeum.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedEntity;
import org.opeum.metamodel.core.INakedInterface;
import org.opeum.metamodel.core.INakedOperation;
import org.opeum.metamodel.core.INakedProperty;
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
