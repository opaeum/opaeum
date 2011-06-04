package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedSingleScreenTask;
import net.sf.nakeduml.metamodel.bpm.internal.EmbeddedSingleScreenTaskMessageStructureImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJSimpleStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

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
		if(no.shouldEmulateClass() || BehaviorUtil.hasMethodsWithStructure(no)){
			this.visitClass(no.getMessageStructure(getOclEngine().getOclLibrary()));
		}
	}
	@VisitBefore()
	public void visitOpaqueAction(INakedEmbeddedSingleScreenTask oa){
		this.visitClass(oa.getMessageStructure(getOclEngine().getOclLibrary()));
	}
	private void buildToString(OJAnnotatedClass owner,INakedClassifier umlClass){
		OJOperation toString = new OJAnnotatedOperation();
		toString.setReturnType(new OJPathName("String"));
		toString.setName("toString");
		OJAnnotatedField sb = new OJAnnotatedField();
		sb.setName("sb");
		sb.setType(new OJPathName("StringBuilder"));
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
