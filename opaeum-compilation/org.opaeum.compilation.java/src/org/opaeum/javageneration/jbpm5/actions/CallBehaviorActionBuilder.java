package org.opaeum.javageneration.jbpm5.actions;


import org.opaeum.javageneration.basicjava.simpleactions.BehaviorCaller;
import org.opaeum.metamodel.actions.INakedCallBehaviorAction;
import org.opaeum.metamodel.workspace.OpaeumLibrary;


public class CallBehaviorActionBuilder extends AbstractCallActionBuilder<INakedCallBehaviorAction>{
	public CallBehaviorActionBuilder(OpaeumLibrary engine,INakedCallBehaviorAction node){
		super(engine, node,new BehaviorCaller(engine, node, new Jbpm5ObjectNodeExpressor(engine)));
	}
}
