package org.opaeum.eclipse.emulated;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Pin;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class OpaqueActionMessageType extends AbstractEmulatedMessageType{
	private OpaqueAction node;
	@SuppressWarnings("unchecked")
	public OpaqueActionMessageType(OpaqueAction node,IPropertyEmulation e){
		super(node, e, node.getInputValues(), node.getOutputValues());
		this.node=node;
		if(node.getRedefinedNodes().size() > 0 && node.getRedefinedNodes().get(0) instanceof OpaqueAction){
			addGeneral(e.getMessageStructure((OpaqueAction) node.getRedefinedNodes().get(0)));
		}
	}
	protected boolean couldBeEmulated(Object o){
		return o instanceof Pin;
	}

	@Override
	public void notifyChanged(Notification notification){
		super.notifyChanged(notification);
	}
	@Override
	public String getId(){
		return EmfWorkspace.getId(node) + "@MT";
	}
	@Override
	public boolean shouldEmulate(){
		return EmfActionUtil.isSingleScreenTask(node);
	}

}
