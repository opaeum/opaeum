package net.sf.nakeduml.javageneration.basicjava;

import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.internal.NakedClearVariableActionImpl;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import nl.klasse.octopus.model.IParameter;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		AttributeImplementor.class,SuperTypeGenerator.class
},after = {
		AttributeImplementor.class,SuperTypeGenerator.class
})
public class OperationAnnotator extends StereotypeAnnotator{
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior o){
		if(OJUtil.hasOJClass(o.getContext()) && !o.isClassifierBehavior() && o.getOwnerElement() instanceof INakedClassifier){
			// DO not do effects, state actions or classifier behavior - will be
			// invoked elsewhere
			if(o.getSpecification() == null || !o.getName().equals(o.getSpecification().getName()) || !o.getContext().equals(o.getSpecification().getContext())){
				// if the specification has a different name to to behavior or the specificatoin is in a
				// superclass/interface,
				// we need to create a matching OJOperation
				OJAnnotatedOperation oper = createOperation(o.getContext(), o, findJavaClass(o.getContext()));
				if(BehaviorUtil.hasExecutionInstance(o)){
					NakedClassifierMap cmap = new NakedClassifierMap(o);
					oper.setReturnType(cmap.javaTypePath());
				}
			}
		}
		if(o instanceof INakedActivity){
			INakedActivity a = (INakedActivity) o;
			for(INakedActivityNode n:a.getActivityNodesRecursively()){
				if(n instanceof INakedEmbeddedTask){
					visitClass(((INakedEmbeddedTask) n).getMessageStructure());
				}
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedClassifier c){
		if(OJUtil.hasOJClass(c)){
			for(INakedOperation o:c.getEffectiveOperations()){
				if(o.getOwner() == c){
					if(BehaviorUtil.hasExecutionInstance(o)){
						visitClass(o.getMessageStructure());
					}
				}
				if(o.getOwner() == c || o.getOwner() instanceof INakedInterface){
					OJAnnotatedClass ojClass = findJavaClass(c);
					createOperation(c, o, ojClass);
					OJAnnotatedOperation oper = new OJAnnotatedOperation(new NakedOperationMap(o).eventOperName(), new OJPathName("boolean"));
					OJAnnotatedField consumed = new OJAnnotatedField("consumed", new OJPathName("boolean"));
					oper.getBody().addToLocals(consumed);
					consumed.setInitExp("false");
					ojClass.addToOperations(oper);
					addParameters(c, oper, o.getArgumentParameters());
					oper.getBody().addToStatements("return consumed");
				}
			}
		}
	}
	private OJAnnotatedOperation createOperation(INakedClassifier context,IParameterOwner o,OJAnnotatedClass owner){
		NakedOperationMap operationMap = new NakedOperationMap(o);
		OJAnnotatedOperation oper = (OJAnnotatedOperation) owner.findOperation(operationMap.javaOperName(), operationMap.javaParamTypePaths());
		if(oper == null){
			oper = new OJAnnotatedOperation(operationMap.javaOperName());
			owner.addToOperations(oper);
			if(o.getReturnParameter() != null){
				oper.setReturnType(operationMap.javaReturnTypePath());
				owner.addToImports(operationMap.javaReturnTypePath());
			}
			List<? extends INakedParameter> argumentParameters = o.getArgumentParameters();
			addParameters(context, oper, argumentParameters);
			if(!(owner instanceof OJAnnotatedInterface)){
				if(o.getReturnParameter() != null){
					String resultName = "result";
					String resultDefaultValue = operationMap.javaReturnDefaultValue();
					owner.addToImports(operationMap.javaReturnDefaultTypePath());
					oper.getBody().addToStatements(oper.getReturnType().getLast() + " " + resultName + " = " + resultDefaultValue);
					oper.getBody().addToStatements("return " + resultName);
				}
			}
			oper.setVisibility(operationMap.javaVisibility());
			applyStereotypesAsAnnotations((o), oper);
			OJUtil.addMetaInfo(oper, o);
		}
		return oper;
	}
	public void addParameters(INakedClassifier context,OJAnnotatedOperation oper,List<? extends INakedParameter> argumentParameters){
		for(INakedParameter elem:argumentParameters){
			OJParameter param = new OJParameter();
			NakedStructuralFeatureMap pMap = OJUtil.buildStructuralFeatureMap(context, elem);
			param.setName(pMap.umlName());
			param.setType(pMap.javaTypePath());
			oper.addToParameters(param);
			applyStereotypesAsAnnotations(((INakedElement) elem), param);
			oper.getOwner().addToImports(pMap.javaTypePath());
		}
	}
}
