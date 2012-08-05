package org.opaeum.eclipse.emulated;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class OperationMessageType extends AbstractEmulatedMessageType{
	Operation operation;
	public OperationMessageType(Operation node,IPropertyEmulation e){
		super(node, e, node.getOwnedParameters());
		this.operation=node;
	}
	public Operation getOperation(){
		return operation;
	}
	protected boolean couldBeEmulated(Object o){
		return o instanceof Parameter;
	}

	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
	}
	@Override
	public String getId(){
		return EmfWorkspace.getId(operation) +"@MT";
	}
	@Override
	public boolean shouldEmulate(){
		return EmfBehaviorUtil.hasExecutionInstance(operation);
	}
}
