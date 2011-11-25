package org.opaeum.javageneration.basicjava.simpleactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJStatement;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.javageneration.maps.NakedOperationMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.BehaviorUtil;
import org.opaeum.metamodel.actions.INakedCallAction;
import org.opaeum.metamodel.activities.INakedInputPin;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.workspace.OpaeumLibrary;
import org.opaeum.runtime.domain.ExceptionHolder;

public abstract class AbstractCaller<T extends INakedCallAction> extends SimpleNodeBuilder<T>{
	protected NakedStructuralFeatureMap callMap;
	protected NakedOperationMap operationMap;
	public AbstractCaller(OpaeumLibrary oclEngine,T action,AbstractObjectNodeExpressor objectNodeExpressor){
		super(oclEngine, action, objectNodeExpressor);
		if(BehaviorUtil.hasMessageStructure(node)){
			callMap = OJUtil.buildStructuralFeatureMap(node, getLibrary());
		}
		if(node.getCalledElement()!=null){
			operationMap=OJUtil.buildOperationMap(node.getCalledElement());
		}

	}
	protected <E extends INakedInputPin>StringBuilder populateArgumentPinsAndBuildArgumentString(OJOperation operation,boolean includeReturnInfo,Collection<E> input){
		StringBuilder arguments = new StringBuilder();
		Iterator<? extends INakedInputPin> args = input.iterator();
		if(includeReturnInfo){
			if(node.getActivity().getActivityKind().isSimpleSynchronousMethod()){
				arguments.append("null");
			}else{
				arguments.append("context");
			}
			if(args.hasNext()){
				arguments.append(",");
			}
		}
		while(args.hasNext()){
			INakedObjectNode pin = args.next();
			arguments.append(readPin(operation, operation.getBody(), pin));
			if(args.hasNext()){
				arguments.append(",");
			}
		}
		return arguments;
	}
	public OJTryStatement surroundWithCatchIfNecessary(OJOperation operationContext,OJBlock originalBlock){
		boolean shouldSurround = BehaviorUtil.shouldSurrounWithTry(node);
		if(shouldSurround){
			// List<OJField> locals = new
			// ArrayList<OJField>(originalBlock.getLocals());
			List<OJStatement> statements = new ArrayList<OJStatement>(originalBlock.getStatements());
			// originalBlock.removeAllFromLocals();
			originalBlock.removeAllFromStatements();
			OJTryStatement tryStatement = new OJTryStatement();
			tryStatement.setCatchPart(new OJBlock());
			tryStatement.setTryPart(new OJBlock());
			// tryStatement.getTryPart().addToLocals(locals);
			tryStatement.getTryPart().addToStatements(statements);
			operationContext.getOwner().addToImports(ExceptionHolder.class.getName());
			tryStatement.setCatchParam(new OJParameter());
			tryStatement.getCatchParam().setType(new OJPathName("ExceptionHolder"));
			tryStatement.getCatchParam().setName("e");
			originalBlock.addToStatements(tryStatement);
			return tryStatement;
		}else{
			return null;
		}
	}
}