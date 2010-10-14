package net.sf.nakeduml.javageneration.basicjava.simpleactions;

import java.util.Collection;
import java.util.Iterator;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.basicjava.AbstractActionBuilder;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import nl.klasse.octopus.oclengine.IOclEngine;

public abstract class SimpleActionBuilder<E extends INakedActivityNode> extends AbstractActionBuilder{
	protected E node;
	public SimpleActionBuilder(IOclEngine oclEngine,E action){
		super(oclEngine);
		this.node = action;
	}
	public abstract void implementActionOn(OJAnnotatedOperation operation,OJBlock block);
	@Override
	protected String expressInputPinOrOutParam(OJBlock block,INakedObjectNode pin){
		// Either an outputpin or parameterNode
		INakedObjectFlow edge = (INakedObjectFlow) pin.getIncoming().iterator().next();
		return surroundWithSelectionAndTransformation(pin.getFeedingNode().getName(), edge);
	}
	protected NakedStructuralFeatureMap buildResultVariable(OJAnnotatedOperation operation,INakedPin returnPin){
		NakedStructuralFeatureMap map = null;
		map = OJUtil.buildStructuralFeatureMap(returnPin.getActivity(), returnPin);
		OJField field = new OJField();
		field.setName(map.umlName());
		field.setType(map.javaTypePath());
		field.setInitExp(map.javaBaseDefaultValue());
		operation.getBody().addToLocals(field);
		operation.getOwner().addToImports(map.javaBaseTypePath());
		operation.getOwner().addToImports(map.javaDefaultTypePath());
		return map;
	}
	protected <E extends INakedInputPin>StringBuilder populateArguments(OJOperation operation,Collection<E> input){
		StringBuilder arguments = new StringBuilder();
		Iterator<? extends INakedInputPin> args = input.iterator();
		while(args.hasNext()){
			INakedObjectNode pin = args.next();
			arguments.append(buildPinExpression(operation, operation.getBody(), pin));
			if(args.hasNext()){
				arguments.append(",");
			}
		}
		return arguments;
	}

}
