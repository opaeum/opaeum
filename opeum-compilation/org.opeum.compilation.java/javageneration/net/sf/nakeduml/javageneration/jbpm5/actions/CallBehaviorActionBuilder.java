package org.opeum.javageneration.jbpm5.actions;


import org.opeum.javageneration.basicjava.simpleactions.BehaviorCaller;
import org.opeum.metamodel.actions.INakedCallBehaviorAction;
import org.opeum.metamodel.workspace.OpeumLibrary;


public class CallBehaviorActionBuilder extends AbstractCallActionBuilder<INakedCallBehaviorAction>{
	public CallBehaviorActionBuilder(OpeumLibrary engine,INakedCallBehaviorAction node){
		super(engine, node,new BehaviorCaller(engine, node, new Jbpm5ObjectNodeExpressor(engine)));
	}
}
