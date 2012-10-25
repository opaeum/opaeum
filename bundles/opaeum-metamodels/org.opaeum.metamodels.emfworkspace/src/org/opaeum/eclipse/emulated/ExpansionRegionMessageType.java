package org.opaeum.eclipse.emulated;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.uml2.uml.ExpansionNode;
import org.eclipse.uml2.uml.ExpansionRegion;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class ExpansionRegionMessageType extends StructuredActivityNodeMessageType{
	ExpansionRegion node;
	@SuppressWarnings("unchecked")
	public ExpansionRegionMessageType(ExpansionRegion node,IPropertyEmulation e){
		super(node, e,node.getVariables(),node.getInputElements(),node.getOutputElements());
		this.node=node;
	}
	@Override
	public void notifyChanged(Notification msg){
		super.notifyChanged(msg);
	}
	protected boolean couldBeEmulated(Object o){
		return o instanceof Variable || o instanceof ExpansionNode;
	}

}
