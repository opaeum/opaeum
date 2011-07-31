package net.sf.nakeduml.javageneration.basicjava;

import java.util.Iterator;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTransformationPhase;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import nl.klasse.octopus.model.IParameter;

import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;


@StepDependency(phase = JavaTransformationPhase.class, requires = { AttributeImplementor.class,SuperTypeGenerator.class }, after = { AttributeImplementor.class,SuperTypeGenerator.class })
public class OperationAnnotator extends StereotypeAnnotator{
	@VisitBefore(matchSubclasses = true)
	public void visitBehavior(INakedBehavior o){
		if(OJUtil.hasOJClass(o.getContext()) && !o.isClassifierBehavior() && o.getOwnerElement() instanceof INakedClassifier){
			// DO not do effects, state actions or classifier behavior - will be
			// invoked elsewhere
			if(o.getSpecification() == null || !o.getSpecification().getOwner().equals(o.getContext())){
				// if there is no specification or the specificatoin is in a
				// superclass/interface,
				// we need to create a matching OJOperation
				createOperation(o, findJavaClass(o.getContext()));
			}
		}
		if(o instanceof INakedActivity){
			INakedActivity a=(INakedActivity) o;
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
				if(o.getOwner() == c ){
					if(BehaviorUtil.hasExecutionInstance(o)){
						visitClass(o.getMessageStructure());
					}
				}
				if(o.getOwner() == c || o.getOwner() instanceof INakedInterface){
					createOperation(o,findJavaClass(c));
				}
			}
		}
	}
	private OJAnnotatedOperation createOperation(IParameterOwner o,OJAnnotatedClass owner){
		NakedOperationMap operationMap = new NakedOperationMap(o);
		OJAnnotatedOperation oper = (OJAnnotatedOperation) owner.findOperation(operationMap.javaOperName(), operationMap.javaParamTypePaths());
		if(oper == null){
			oper = new OJAnnotatedOperation(operationMap.javaOperName());
			if(o.getReturnParameter() != null){
				oper.setReturnType(operationMap.javaReturnTypePath());
				owner.addToImports(operationMap.javaReturnTypePath());
			}
			Iterator<IParameter> it = o.getParameters().iterator();
			while(it.hasNext()){
				IParameter elem = it.next();
				OJParameter param = new OJParameter();
				param.setName(elem.getName());
				param.setType(operationMap.javaParamTypePath(elem));
				oper.addToParameters(param);
				applyStereotypesAsAnnotations(((INakedElement) elem), param);
				owner.addToImports(operationMap.javaParamTypePath(elem));
			}
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
			owner.addToOperations(oper);
			applyStereotypesAsAnnotations((o), oper);
			OJUtil.addMetaInfo(oper, o);
		}
		return oper;
	}
}
