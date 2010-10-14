package net.sf.nakeduml.javageneration.basicjava;

import net.sf.nakeduml.javageneration.bpm.actions.ActionMap;
import net.sf.nakeduml.javageneration.oclexpressions.ValueSpecificationUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.metamodel.actions.IActionWithTarget;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedValuePin;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.oclengine.IOclEngine;

public abstract class AbstractActionBuilder{
	protected IOclEngine oclEngine;
	protected AbstractActionBuilder(IOclEngine oclEngine){
		this.oclEngine = oclEngine;
	}
	protected abstract String expressInputPinOrOutParam(OJBlock block,INakedObjectNode pin);
	public OJBlock buildLoopThroughTarget(OJOperation operationContext,OJBlock block,ActionMap actionMap){
		if(actionMap.targetIsImplicitObject()){
			return block;
		}else{
			IActionWithTarget action = actionMap.getAction();
			String expression = null;
			if(action.getInPartition()!=null){
				//TODO is this wise? letting the partition override all pins
				if(action.getInPartition().getRepresents() instanceof INakedProperty){
					expression = actionMap.targetMap().getter() + "()";
				}else if(action.getInPartition().getRepresents() instanceof INakedClassifier){
					expression = "NOT IMPLEMENTED";
					//TODO use group assignment here
				}
			}else{
				expression = buildPinExpression(operationContext, block, action.getTarget());
			}
			if(actionMap.targetMap().isOne()){
				OJPathName targetPath = actionMap.targetMap().javaBaseTypePath();
				block.addToStatements(targetPath.getLast() + " " + actionMap.targetName() + "=" + expression);
				OJIfStatement fs = new OJIfStatement();
				fs.setCondition(actionMap.targetName() + "!=null");
				block.addToStatements(fs);
				operationContext.getOwner().addToImports(targetPath);
				return fs.getThenPart();
			}else{
				OJPathName targetPath = actionMap.targetMap().javaBaseTypePath();
				operationContext.getOwner().addToImports("java.util.Collection");
				expression = "(Collection<" + targetPath.getLast() + ">)" + expression;
				OJForStatement fs = new OJForStatement();
				fs.setCollection(expression);
				fs.setElemName(actionMap.targetName());
				fs.setElemType(targetPath);
				fs.setBody(new OJBlock());
				block.addToStatements(fs);
				operationContext.getOwner().addToImports(targetPath);
				return fs.getBody();
			}
		}
	}
	protected final String buildPinExpression(OJOperation operationContext,OJBlock block,INakedObjectNode pin){
		String expression = null;
		if(pin instanceof INakedValuePin){
			expression = ValueSpecificationUtil.expressValue(operationContext, ((INakedValuePin) pin).getValue(), pin.getActivity(), pin
					.getType());
		}else if(pin.getIncoming().size() == 0){
			expression = ValueSpecificationUtil.expressDefaultOrImplicitObject(pin.getActivity(), pin.getType());
		}else if(pin.getIncoming().size() == 1){
			expression = expressInputPinOrOutParam(block, pin);
		}else{
			throw new IllegalStateException("Multiple flows are not supported into ObjectNodes");
		}
		return expression;
	}
	protected String surroundWithSelectionAndTransformation(String expression,INakedObjectFlow edge){
		if(edge.getSelection() != null){
			expression = edge.getSelection().getMappingInfo().getJavaName() + "(" + expression + ")";
		}
		if(edge.getTransformation() != null){
			expression = edge.getTransformation().getMappingInfo().getJavaName() + "(" + expression + ")";
		}
		return expression;
	}
	protected final IOclEngine getOclEngine(){
		return oclEngine;
	}
}
