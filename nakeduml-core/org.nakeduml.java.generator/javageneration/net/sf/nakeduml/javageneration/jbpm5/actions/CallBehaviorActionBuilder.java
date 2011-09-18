package net.sf.nakeduml.javageneration.jbpm5.actions;


import net.sf.nakeduml.javageneration.basicjava.simpleactions.BehaviorCaller;
import net.sf.nakeduml.metamodel.actions.INakedCallBehaviorAction;
import net.sf.nakeduml.metamodel.workspace.NakedUmlLibrary;


public class CallBehaviorActionBuilder extends AbstractCallActionBuilder<INakedCallBehaviorAction>{
	public CallBehaviorActionBuilder(NakedUmlLibrary engine,INakedCallBehaviorAction node){
		super(engine, node,new BehaviorCaller(engine, node, new Jbpm5ObjectNodeExpressor(engine)));
	}
}
