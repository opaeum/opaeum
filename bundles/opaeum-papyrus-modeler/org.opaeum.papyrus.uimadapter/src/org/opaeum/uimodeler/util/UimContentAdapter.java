package org.opaeum.uimodeler.util;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.uml2.uml.TypedElement;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageOrdering;
import org.opaeum.uim.UimFactory;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.action.InvocationButton;
import org.opaeum.uim.binding.UimBinding;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.UimDataTable;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.perspective.NavigationConstraint;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.resources.UimModelSet;
import org.opaeum.uim.uml2uim.FormSynchronizer2;
import org.opaeum.uim.uml2uim.PerspectiveCreator;
import org.opaeum.uim.util.ControlUtil;
import org.opaeum.uim.util.UmlUimLinks;

public class UimContentAdapter extends EContentAdapter{
	private UimModelSet modelSet;
	private FormSynchronizer2 formSynchronizer;
	private PerspectiveCreator perspectiveCreator;
	private UmlToUimSwitch umlSwitch;
	public UimContentAdapter(final UimModelSet modelSet){
		super();
		this.modelSet = modelSet;
		URI dirUri = modelSet.getOpenUmlFile().getEmfWorkspace().getDirectoryUri();
		perspectiveCreator = new PerspectiveCreator(dirUri, modelSet, false,modelSet.getOpenUmlFile().getEmfWorkspace());
		formSynchronizer = new FormSynchronizer2(dirUri, modelSet, false);
		umlSwitch = new UmlToUimSwitch(perspectiveCreator, formSynchronizer);
		modelSet.registerUimResources(perspectiveCreator.getNewResources());
	}
	@Override
	public void notifyChanged(Notification notification){
		umlSwitch.goForIt(notification);
		if(notification.getNotifier() instanceof UserInteractionElement){
			UmlUimLinks linkd = UmlUimLinks.getCurrentUmlLinks((EObject) notification.getNotifier());
			if(linkd != null){
				if(notification.getNewValue() instanceof Page){
					Page page = (Page) notification.getNewValue();
					PageOrdering po = UimFactory.eINSTANCE.createPageOrdering();
					UserInterfaceRoot pc = (UserInterfaceRoot) page.eContainer();
					po.setPage(page);
					po.setPosition(pc.getPageOrdering().size());
					pc.getPageOrdering().add(po);
					modelSet.getInMemoryNotationResource().getDiagram(page);
				}else if(notification.getOldValue() instanceof Page){
					Page page = (Page) notification.getOldValue();
					UserInterfaceRoot pc = (UserInterfaceRoot) notification.getNotifier();
					Iterator<PageOrdering> iterator = pc.getPageOrdering().iterator();
					while(iterator.hasNext()){
						PageOrdering po = (PageOrdering) iterator.next();
						if(po.getPage() == page || po.getPage() == null){
							iterator.remove();
						}
					}
				}
				if(notification.getNotifier() instanceof UserInteractionConstraint){
					UserInteractionConstraint uic=(UserInteractionConstraint) notification.getNotifier();
					if(notification.getFeatureID(UserInteractionConstraint.class)==ConstraintPackage.USER_INTERACTION_CONSTRAINT__INHERIT_FROM_PARENT){
						uic.setRequiresOwnership(false);
						if(notification.getNewBooleanValue()){
							uic.setRequiresGroupOwnership(false);
						}else{
							uic.setRequiresGroupOwnership(true);
						}
					}
					if(notification.getFeatureID(NavigationConstraint.class)==PerspectivePackage.NAVIGATION_CONSTRAINT__HIDDEN){
						uic.setInheritFromParent(false);
						uic.setRequiresOwnership(false);
						if(notification.getNewBooleanValue()){
							uic.setRequiresGroupOwnership(false);
						}else{
							uic.setRequiresGroupOwnership(true);
						}
					}

				}
				if(notification.getEventType()==Notification.ADD && notification.getNewValue() instanceof LabeledElement){
					LabeledElement le = (LabeledElement) notification.getNewValue();
					if(le.getLabelOverride()==null){
						le.setLabelOverride(UimFactory.eINSTANCE.createLabels());
					}
				}
				if(notification.getNewValue() instanceof InvocationButton && notification.getEventType() == Notification.ADD){
					// InvocationButton a = (InvocationButton) notification.getNewValue();
					// a.setPopup(this.modelSet.getUiResourceFor(e));
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
							ControlKind[] cks = ControlUtil.getAllowedControlKinds(UmlUimLinks.getNearestUserInterfaceRoot(field), type,
									field.eContainer() instanceof UimDataTable);
							UimField uimField = (UimField) field;
							if(cks.length > 0 && cks[0] != uimField.getControlKind()){
								uimField.setControlKind(cks[0]);
								uimField.setControl(ControlUtil.instantiate(cks[0]));
							}
						}
					default:
						break;
					}
				}
			}
		}else{
			umlSwitch.goForIt(notification);
		}
	}
	public PerspectiveCreator getPerspectiveCreator(){
		// TODO Auto-generated method stub
		return perspectiveCreator;
	}
}
