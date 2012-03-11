package org.opaeum.uimodeler.modelexplorer;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimField;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.action.OperationAction;
import org.opaeum.uim.binding.UimBinding;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.util.ControlUtil;
import org.opaeum.uim.util.UmlUimLinks;

public class UimContentAdapter extends EContentAdapter{
	@Override
	public void notifyChanged(Notification notification){
		super.notifyChanged(notification);
		if(notification.getNewValue() instanceof OperationAction && notification.getEventType() == Notification.ADD){
			OperationAction a=(OperationAction) notification.getNewValue() ;
			a.setPopup(org.opaeum.uim.action.ActionFactory.eINSTANCE.createOperationActionPopup());
		}
		if(notification.getNotifier() instanceof UimField && notification.getEventType() == Notification.SET){
			UimField field = (UimField) notification.getNotifier();
			switch(notification.getFeatureID(UimField.class)){
			case UimPackage.UIM_FIELD__CONTROL_KIND:
				field.setControl(ControlUtil.instantiate((ControlKind) notification.getNewValue()));
				break;
			case UimPackage.UIM_FIELD__BINDING:
				if(notification.getNewValue() != null){
					TypedElement type = UmlUimLinks.getCurrentUmlLinks(field).getResultingType((UimBinding) notification.getNewValue());
					ControlKind[] cks = ControlUtil.getAllowedControlKinds(UmlUimLinks.getCurrentUmlLinks(field).getNearestForm(field), type,
							field.eContainer() instanceof UimDataTable);
					UimField uimField = (UimField) field;
					if(cks[0] != uimField.getControlKind()){
						uimField.setControlKind(cks[0]);
						uimField.setControl(ControlUtil.instantiate(cks[0]));
					}
				}
			default:
				break;
			}
		}
	}
	public static boolean isListeningTo(ResourceSet rs){
		for(Adapter adapter:rs.eAdapters()){
			if(adapter instanceof UimContentAdapter){
				return true;
			}
		}
		return false;
	}
}
