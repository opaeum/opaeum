package org.opaeum.uimodeler.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.binding.UimBinding;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.util.ControlUtil;
import org.opaeum.uim.util.UmlUimLinks;

public class UimContentAdapter extends EContentAdapter{
	@Override
	public void notifyChanged(Notification notification){
		super.notifyChanged(notification);
		if(notification.getNotifier() instanceof UserInteractionElement){
			UmlUimLinks linkd = UmlUimLinks.getCurrentUmlLinks((EObject) notification.getNotifier());
			if(linkd != null){
				if(notification.getNewValue() instanceof InvocationButton && notification.getEventType() == Notification.ADD){
					InvocationButton a = (InvocationButton) notification.getNewValue();
					// TODO
					throw new RuntimeException();
					// a.setPopup(org.opaeum.uim.action.ActionFactory.eINSTANCE.createOperationPopup());
				}
				if(notification.getNotifier() instanceof UimField && notification.getEventType() == Notification.SET){
					UimField field = (UimField) notification.getNotifier();
					switch(notification.getFeatureID(UimField.class)){
					case ComponentPackage.UIM_FIELD__CONTROL_KIND:
						field.setControl(ControlUtil.instantiate((ControlKind) notification.getNewValue()));
						break;
					case ComponentPackage.UIM_FIELD__BINDING:
						if(notification.getNewValue() != null){
							TypedElement type = linkd.getResultingType((UimBinding) notification.getNewValue());
							ControlKind[] cks = ControlUtil.getAllowedControlKinds(linkd.getNearestForm(field), type, field.eContainer() instanceof UimDataTable);
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
