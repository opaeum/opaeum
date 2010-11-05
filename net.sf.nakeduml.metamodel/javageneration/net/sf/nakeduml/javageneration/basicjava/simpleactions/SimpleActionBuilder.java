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
import net.sf.nakeduml.metamodel.activities.INakedAction;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.activities.INakedObjectFlow;
import net.sf.nakeduml.metamodel.activities.INakedObjectNode;
import net.sf.nakeduml.metamodel.activities.INakedPin;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.oclengine.IOclEngine;

public abstract class SimpleActionBuilder<E extends INakedActivityNode> extends AbstractActionBuilder{
	protected E node;
	public SimpleActionBuilder(IOclEngine oclEngine,E action, ObjectNodeExpressor expressor){
		super(oclEngine, expressor);
		this.node = action;
	}
	public abstract void implementActionOn(OJAnnotatedOperation operation,OJBlock block);


}
