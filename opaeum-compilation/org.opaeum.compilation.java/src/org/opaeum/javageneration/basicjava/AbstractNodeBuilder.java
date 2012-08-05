package org.opaeum.javageneration.basicjava;

import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ValuePin;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.javageneration.maps.ActionMap;
import org.opaeum.javageneration.oclexpressions.ValueSpecificationUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public abstract class AbstractNodeBuilder {
	protected OpaeumLibrary library;
	protected AbstractObjectNodeExpressor expressor;
	protected ValueSpecificationUtil valueSpecificationUtil;
	protected OJUtil ojUtil;
	protected AbstractNodeBuilder(AbstractObjectNodeExpressor expressor) {
		this.library=expressor.ojUtil.getLibrary();
		this.ojUtil=expressor.ojUtil;
		this.expressor=expressor;
		valueSpecificationUtil=new ValueSpecificationUtil(ojUtil);
	}
	public OJUtil getOjUtil(){
		return ojUtil;
	}
	public OJBlock buildLoopThroughTarget(OJOperation operationContext, OJBlock block, ActionMap actionMap) {
		if (actionMap.targetIsImplicitObject()) {
			return block;
		} else {
			Action action = actionMap.getAction();
			String expression = null;
			if (action.getInPartitions().size()==1) {
				// TODO is this wise? letting the partition override all pins
				if (action.getInPartitions().get(0).getRepresents() instanceof Property) {
					expression = actionMap.targetMap().getter() + "()";
				} else if (action.getInPartitions().get(0).getRepresents() instanceof Classifier) {
					
					expression = "NOT IMPLEMENTED";
					// TODO use group assignment here
				}
			} else if(EmfActionUtil.getLogicalTarget(action)!=null){
				expression = readPin(operationContext, block, EmfActionUtil.getLogicalTarget(action));
			}
			if (actionMap.targetMap().isOne()) {
				OJPathName targetPath = actionMap.targetMap().javaBaseTypePath();
				String targetVar = targetPath.getLast() + " " + actionMap.targetName() + "=" + expression;
				block.addToStatements(targetVar);
				if (actionMap.targetMap().isOptional()) {
					OJIfStatement fs = new OJIfStatement();
					fs.setCondition(actionMap.targetName() + "!=null");
					block.addToStatements(fs);
					operationContext.getOwner().addToImports(targetPath);
					return fs.getThenPart();
				} else {
					return block;
				}
			} else {
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
	protected final String readPin(OJOperation operationContext, OJBlock block, ObjectNode pin) {
		if(expressor.pinsAvailableAsVariables()){
			return  ojUtil.buildStructuralFeatureMap(pin).fieldname();
		}else{
			return expressPin(operationContext, block, pin);
		}
	}

	protected final String expressPin(OJOperation operationContext, OJBlock block, ObjectNode pin) {
		String expression = null;
		if (pin instanceof ValuePin) {
			expression = valueSpecificationUtil.expressValue(operationContext, ((ValuePin) pin).getValue(), EmfActivityUtil.getContainingActivity( pin),
					 getLibrary().getActualType( (ValuePin)pin));
		}else if(EmfActivityUtil.getIncomingExceptionHandlers( pin).size()>0){
			expression=this.expressor.expressExceptionInput(block,pin);
		} else if (pin.getIncomings().size() == 0) {
			expression = valueSpecificationUtil.expressDefaultOrImplicitObject(EmfActivityUtil.getContainingActivity(pin), (Classifier)pin.getType());
		} else if (pin.getIncomings().size() == 1) {
			expression = this.expressor.expressInputPinOrOutParamOrExpansionNode(block, pin);
		} else {
			throw new IllegalStateException("Multiple flows are not supported into ObjectNodes");
		}
		return expression;
	}

	protected final OpaeumLibrary getLibrary() {
		return library;
	}
}
