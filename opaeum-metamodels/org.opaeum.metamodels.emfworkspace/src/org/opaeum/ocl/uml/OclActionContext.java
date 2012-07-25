package org.opaeum.ocl.uml;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.OpaqueAction;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfValueSpecificationUtil;

public class OclActionContext extends AbstractOclContext{
	private OpaqueAction opaqueAction;
	public OclActionContext(OpaqueAction oe,OCLHelper<Classifier,Operation,Property,Constraint> helper){
		super();
		this.opaqueAction=oe;
		oe.eAdapters().add(this);
		this.helper = helper;
		reParse();
	}

	protected String retrieveBody(){
		return EmfValueSpecificationUtil.getOclBody(opaqueAction);
	}
	@Override
	public void notifyChanged(Notification notification){
		if(notification.getNotifier() instanceof OpaqueExpression){
			switch(notification.getFeatureID(OpaqueExpression.class)){
			case UMLPackage.OPAQUE_ACTION__BODY:
				reParse();
				break;
			default:
				break;
			}
		}
	}

}
