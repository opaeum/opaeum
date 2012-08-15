package org.opaeum.javageneration.basicjava.simpleactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.OperationMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.CallAction;
import org.eclipse.uml2.uml.InputPin;
import org.eclipse.uml2.uml.ObjectNode;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJStatement;
import org.opaeum.java.metamodel.OJTryStatement;
import org.opaeum.javageneration.basicjava.AbstractObjectNodeExpressor;
import org.opaeum.runtime.domain.ExceptionHolder;

public abstract class AbstractCaller<T extends CallAction> extends SimpleNodeBuilder<T>{
	protected PropertyMap callMap;
	protected OperationMap operationMap;
	public AbstractCaller(T action,AbstractObjectNodeExpressor objectNodeExpressor){
		super(action, objectNodeExpressor);
		if(EmfBehaviorUtil.hasMessageStructure(node)){
			callMap = ojUtil.buildStructuralFeatureMap(node);
		}

	}
	protected <E extends InputPin>StringBuilder populateArgumentPinsAndBuildArgumentString(OJOperation operation,boolean includeReturnInfo,Collection<E> input){
		StringBuilder arguments = new StringBuilder();
		Iterator<? extends InputPin> args = input.iterator();
		if(includeReturnInfo){
			if(EmfActivityUtil.isSimpleSynchronousMethod(EmfActivityUtil.getContainingActivity(node))){
				arguments.append("null");
			}else{
				arguments.append("context");
			}
			if(args.hasNext()){
				arguments.append(",");
			}
		}
		while(args.hasNext()){
			ObjectNode pin = args.next();
			arguments.append(readPin(operation, operation.getBody(), pin));
			if(args.hasNext()){
				arguments.append(",");
			}
		}
		return arguments;
	}
	public OJTryStatement surroundWithCatchIfNecessary(OJOperation operationContext,OJBlock originalBlock){
		boolean shouldSurround = EmfBehaviorUtil.shouldSurrounWithTry(node);
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