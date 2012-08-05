package org.opaeum.eclipse.emulated;

import org.eclipse.uml2.uml.AcceptCallAction;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Type;
import org.opaeum.eclipse.EmfActionUtil;
import org.opaeum.eclipse.EmfBehaviorUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.metamodel.workspace.IPropertyEmulation;

public class ActionFeatureBridge extends AbstractEmulatedProperty{
	private Action action;
	private IPropertyEmulation emulation;
	public ActionFeatureBridge(Classifier owner, Action action,IPropertyEmulation lib){
		super(owner, action);
		this.action = action;
		this.emulation=lib;
	}
	@Override
	public Type getType(){
		if(action instanceof AcceptCallAction){
			Operation operation = EmfActionUtil.getOperation((AcceptCallAction) action);
			return emulation.getMessageStructure(operation);
		}else if(action instanceof CallBehaviorAction){
			return ((CallBehaviorAction) action).getBehavior();
		}else if(action instanceof CallOperationAction){
			Operation operation =((CallOperationAction) action).getOperation();
			return emulation.getMessageStructure(operation);
		}else{
			return null;
		}
	}
	@Override
	public int getUpper(){
		return LiteralUnlimitedNatural.UNLIMITED;
	}
	public boolean isComposite(){
		return false;
	}
	@Override
	public AggregationKind getAggregation(){
		return AggregationKind.NONE_LITERAL;
	}
	@Override
	public String getId(){
		return EmfWorkspace.getId(action)+"@AFB";
	}
	@Override
	public boolean shouldEmulate(){
		Type type2 = getType();
		if(type2==null){
			return false;
		}else if(type2 instanceof Behavior){
			return EmfBehaviorUtil.isProcess((Behavior) type2);
		}else if(type2 instanceof IEmulatedElement){
			return ((IEmulatedElement) type2).shouldEmulate();
		}
		return false;
	}
}
