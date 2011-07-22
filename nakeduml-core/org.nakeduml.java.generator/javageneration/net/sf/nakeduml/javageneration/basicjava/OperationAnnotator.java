package net.sf.nakeduml.javageneration.basicjava;

import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.bpm.INakedEmbeddedTask;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedInterfaceRealization;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.IParameterOwner;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IParameter;

import org.nakeduml.annotation.NumlMetaInfo;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotationValue;

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
	}
	@VisitBefore
	public void visitOperationMessage(INakedOperation a){
		if(BehaviorUtil.hasExecutionInstance(a)){
			visitClass(a.getMessageStructure(getLibrary()));
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitTask(INakedEmbeddedTask t){
		visitClass(t.getMessageStructure(getLibrary()));
	}
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedClassifier c){
		if(OJUtil.hasOJClass(c)){
			for(INakedOperation o:c.getEffectiveOperations()){
				if(o.getOwner() == c || o.getOwner() instanceof INakedInterface){
					createOperation(o,findJavaClass(c));
				}
			}
		}
	}
	private void visitOperation(INakedOperation o){
		INakedClassifier umlOwner = o.getOwner();
		OJAnnotatedClass myOwner = findJavaClass(umlOwner);
		OJAnnotatedOperation oper = createOperation(o, myOwner);
		if(o.hasClassScope()){
			oper.setStatic(true);
		}
		oper.setAbstract(o.isAbstract());
	}
	private OJAnnotatedOperation createOperation(IParameterOwner o,OJAnnotatedClass owner){
		NakedOperationMap operationMap = new NakedOperationMap(o);
		OJAnnotatedOperation oper = (OJAnnotatedOperation) owner.findOperation(operationMap.javaOperName(), operationMap.javaParamTypePaths());
		if(oper == null){
			oper = new OJAnnotatedOperation();
			if(o.getReturnParameter() != null){
				oper.setReturnType(operationMap.javaReturnTypePath());
				owner.addToImports(operationMap.javaReturnTypePath());
			}
			oper.setName(operationMap.javaOperName());
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
