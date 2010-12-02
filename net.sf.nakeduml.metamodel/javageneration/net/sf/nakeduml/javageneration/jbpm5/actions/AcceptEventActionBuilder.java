package net.sf.nakeduml.javageneration.jbpm5.actions;

import net.sf.nakeduml.javageneration.basicjava.simpleactions.ActionMap;
import net.sf.nakeduml.javageneration.jbpm5.BpmUtil;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.actions.INakedAcceptEventAction;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedTimeEvent;
import nl.klasse.octopus.oclengine.IOclEngine;

public class AcceptEventActionBuilder extends Jbpm5ActionBuilder<INakedAcceptEventAction> {
	public AcceptEventActionBuilder(IOclEngine oclEngine, INakedAcceptEventAction node) {
		super(oclEngine, node);
	}

	@Override
	public void implementActionOn(OJAnnotatedOperation operation) {
		if (node.getActionType().isAcceptTimeEventAction()) {
			BpmUtil.implementTimeEvent(operation, (INakedTimeEvent) node.getTrigger().getEvent(), node, node.getAllEffectiveOutgoing());
			OJOperation cancel = new OJAnnotatedOperation();
			ActionMap map = new ActionMap(node);
			cancel.setName(map.getCancelTimersMethod());
			operation.getOwner().addToOperations(cancel);
			BpmUtil.cancelTimer(cancel, (INakedTimeEvent) node.getTrigger().getEvent());
		}
	}

	@Override
	public boolean waitsForEvent() {
		return true;
	}
}
