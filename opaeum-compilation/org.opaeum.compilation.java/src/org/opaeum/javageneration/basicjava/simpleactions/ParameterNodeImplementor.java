package org.opaeum.javageneration.basicjava.simpleactions;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityParameterNode;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.ParameterDirectionKind;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Transition;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.jbpm5.Jbpm5Util;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.workspace.OpaeumLibrary;

public class ParameterNodeImplementor extends SimpleNodeBuilder<ActivityParameterNode>{
	public ParameterNodeImplementor(OpaeumLibrary oclEngine,ActivityParameterNode action,AbstractObjectNodeExpressor objectNodeExpressor){
		super(oclEngine, action, objectNodeExpressor);
	}
	@Override
	public void implementActionOn(OJAnnotatedOperation operation,OJBlock block){
		if(node.getParameter().getDirection()!=ParameterDirectionKind.IN_LITERAL && node.getIncomings().size() > 0){
			// consume input token where necessary
			String call = super.expressor.expressInputPinOrOutParamOrExpansionNode(block, node);
			String pathToActivity = getPathToActivity();
			if(node.getParameter().getDirection()==ParameterDirectionKind.RETURN_LITERAL){
				NakedStructuralFeatureMap resultMap;
				if(EmfBehaviorUtil.getLinkedParameter( node.getParameter()) == null){
					resultMap = OJUtil.buildStructuralFeatureMap(getActivity(), node.getParameter());
				}else{
					resultMap = OJUtil.buildStructuralFeatureMap(getActivity(), EmfBehaviorUtil.getLinkedParameter( node.getParameter()));
				}
				ObjectNode feedingNode = EmfActivityUtil.getFeedingNode(node);
				if(node.getParameter().isException()){
					// TODO JBPM exception handling
					// oper.getBody().addToStatements("processInstance.getRootToken().end()");
					OJPathName pathName = OJUtil.classifierPathname((Classifier) node.getType());
					operation.getOwner().addToImports(pathName);
					operation.getOwner().addToImports(Jbpm5Util.getExceptionHolder());
					if(EmfBehaviorUtil.isLongRunning( getActivity()) ||  getActivity().getOwner() instanceof Transition
							|| getActivity().getOwner() instanceof State){
						block.addToStatements(expressor.storeResults(resultMap, call, EmfActivityUtil.isMultivalued( feedingNode)));
						NakedOperationMap map = OJUtil.buildOperationMap(getActivity());
						OJAnnotatedField callBackListener = new OJAnnotatedField("callbackListener", map.callbackListenerPath());
						block.addToLocals(callBackListener);
						callBackListener.setInitExp(pathToActivity + "getCallingProcessObject()");
						OJIfStatement ifNotNull = new OJIfStatement("callbackListener!=null");
						block.addToStatements(ifNotNull);
						ifNotNull.getThenPart().addToStatements(
								"callbackListener." + map.exceptionOperName(node.getParameter()) + "(" + pathToActivity + "getCallingNodeInstanceUniqueId()," + call
										+ ",this)");
					}else if(EmfBehaviorUtil.hasExecutionInstance(getActivity())){
						block.addToStatements(expressor.storeResults(resultMap, call, EmfActivityUtil.isMultivalued(feedingNode)));
					}else{
						block.addToStatements("throw new ExceptionHolder(this,\"" + node.getParameter().getName() + "\"," + call + ")");
					}
				}else{
					if(EmfBehaviorUtil.hasExecutionInstance(getActivity()) || getActivity().getOwner() instanceof Transition
							|| getActivity().getOwner() instanceof State){
						block.addToStatements(pathToActivity + expressor.storeResults(resultMap, call, EmfActivityUtil.isMultivalued( feedingNode)));
					}else{
						block.addToStatements("result= " + call);
					}
				}
			}
		}
	}
	protected Activity getActivity(){
		return EmfActivityUtil.getContainingActivity(node);
	}
}
