package org.opaeum.ocl.uml;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfValueSpecificationUtil;

public class OpaqueActionActionContext extends AbstractOclContext{
	private OpaqueAction opaqueAction;
	public OpaqueActionActionContext(OpaqueAction oe,OCLHelper<Classifier,Operation,Property,Constraint> helper){
		super(oe,helper);
		this.opaqueAction=oe;
		oe.eAdapters().add(this);
		getExpression();
	}
	public OpaqueAction getOpaqueAction(){
		return opaqueAction;
	}
	protected String retrieveBody(){
		return EmfValueSpecificationUtil.getOclBody(opaqueAction);
	}
	@Override
	public void notifyChanged(Notification notification){
		if(notification.getNotifier() instanceof OpaqueAction){
			switch(notification.getFeatureID(OpaqueAction.class)){
			case UMLPackage.OPAQUE_ACTION__BODY:
				reset();
				break;
			default:
				break;
			}
		}
	}

}
