package org.opaeum.ocl.uml;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.ocl.helper.OCLHelper;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfValueSpecificationUtil;

public class OpaqueBehaviorContext extends AbstractOclContext{
	private OpaqueBehavior opaqueBehavior;
	public OpaqueBehaviorContext(OpaqueBehavior oe,OCLHelper<Classifier,Operation,Property,Constraint> helper){
		super(oe);
		this.opaqueBehavior=oe;
		oe.eAdapters().add(this);
		this.helper = helper;
		getExpression();
	}

	protected String retrieveBody(){
		return EmfValueSpecificationUtil.getOclBody(opaqueBehavior);
	}
	@Override
	public void notifyChanged(Notification notification){
		if(notification.getNotifier() instanceof OpaqueBehavior){
			switch(notification.getFeatureID(OpaqueBehavior.class)){
			case UMLPackage.OPAQUE_BEHAVIOR__BODY:
				reset();
				break;
			default:
				break;
			}
		}
	}

}
