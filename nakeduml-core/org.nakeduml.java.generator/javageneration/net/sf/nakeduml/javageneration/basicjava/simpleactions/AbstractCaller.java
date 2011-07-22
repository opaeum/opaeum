package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractObjectNodeExpressor;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJParameter;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.OJStatement;
import org.nakeduml.java.metamodel.OJTryStatement;
import org.nakeduml.runtime.domain.ExceptionHolder;

public abstract class AbstractCaller<T extends INakedCallAction> extends SimpleNodeBuilder<T>{
	protected NakedStructuralFeatureMap callMap;

	public AbstractCaller(NakedUmlLibrary oclEngine,T action,AbstractObjectNodeExpressor objectNodeExpressor){
		super(oclEngine, action, objectNodeExpressor);
		if(BehaviorUtil.hasMessageStructure(node)){
			callMap = OJUtil.buildStructuralFeatureMap(node, getLibrary());
		}
	}

	protected <E extends INakedInputPin>StringBuilder populateArgumentPinsAndBuildArgumentString(OJOperation operation,Collection<E> input){
		StringBuilder arguments = new StringBuilder();
		Iterator<? extends INakedInputPin> args = input.iterator();
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