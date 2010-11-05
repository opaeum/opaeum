package net.sf.nakeduml.javageneration.jbpm5.actions;


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
			BpmUtil.implementTimeEvent(operation, (INakedTimeEvent) node.getEvent(), node, node.getAllEffectiveOutgoing());
			OJOperation cancel = new OJAnnotatedOperation();
			cancel.setName("cancel" + node.getMappingInfo().getJavaName().getCapped());
			operation.getOwner().addToOperations(cancel);
			BpmUtil.cancelTimer(cancel, (INakedTimeEvent) node.getEvent());
		}
	}

	@Override
	public boolean waitsForEvent() {
		return true;
	}

}
