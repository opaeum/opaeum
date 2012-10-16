package org.opaeum.uimodeler.util;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.uml2.uml.Action;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.MultiplicityElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Pin;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
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
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.resources.UimModelSet;
import org.opaeum.uim.uml2uim.FormSynchronizer2;
import org.opaeum.uim.util.ControlUtil;
import org.opaeum.uim.util.UmlUimLinks;

public class UimContentAdapter extends EContentAdapter{
	private UimModelSet modelSet;
	private FormSynchronizer2 formSynchronizer;
	public UimContentAdapter(final UimModelSet modelSet){
		super();
		this.modelSet = modelSet;
		formSynchronizer = new FormSynchronizer2(modelSet.getRootObject().eResource().getURI().trimSegments(1), modelSet, false);
	}
	@Override
	public void notifyChanged(Notification notification){
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
				if(notification.getNewValue() instanceof InvocationButton && notification.getEventType() == Notification.ADD){
					InvocationButton a = (InvocationButton) notification.getNewValue();
//					a.setPopup(this.modelSet.getUiResourceFor(e));
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
							ControlKind[] cks = ControlUtil.getAllowedControlKinds(UmlUimLinks.getNearestForm(field), type, field.eContainer() instanceof UimDataTable);
							UimField uimField = (UimField) field;
							if(cks.length>0 &&  cks[0] != uimField.getControlKind()){
								uimField.setControlKind(cks[0]);
								uimField.setControl(ControlUtil.instantiate(cks[0]));
							}
						}
					default:
						break;
					}
				}
			}
		}else if(notification.getNotifier() instanceof Package && notification.getFeatureID(Package.class) == UMLPackage.PACKAGE__OWNED_TYPE
				&& notification.getNewValue() instanceof Classifier){
			Classifier classifier = (Classifier) notification.getNewValue();
			if(EmfClassifierUtil.isPersistentComplexStructure(classifier) && !(classifier instanceof DataType)){
				formSynchronizer.beforeClass(classifier);
			}
		}else if(notification.getNotifier() instanceof Classifier){
			Classifier classifier = (Classifier) notification.getNotifier();
			if(notification.getNewValue() instanceof Operation){
				formSynchronizer.beforeOperation((Operation) notification.getNewValue());
			}else if(notification.getNewValue() instanceof Action){
				formSynchronizer.beforeAction((Action) notification.getNewValue());
			}else if(notification.getNewValue() instanceof TypedElement){
				TypedElement typedElement = (TypedElement) notification.getNewValue();
				refreshDataElements(typedElement);
			}
		}else if(notification.getNotifier() instanceof TypedElement
				&& (notification.getFeatureID(TypedElement.class) == UMLPackage.TYPED_ELEMENT__TYPE || notification.getFeatureID(MultiplicityElement.class) == UMLPackage.MULTIPLICITY_ELEMENT__UPPER_VALUE)){
			refreshDataElements((TypedElement) notification.getNotifier());
		}
	}
	protected void refreshDataElements(TypedElement typedElement){
		Element container = EmfElementFinder.getContainer(typedElement);
		if(typedElement instanceof Property && ((Property) typedElement).getAssociationEnd()==null){
			formSynchronizer.beforeClass((Classifier) container);
			// TODO refresh all the pages (Tables,Panels) that may refer to this classifier,i.e. containment properties, generalizations,
			// interfacerealizations, paramaters,pins
		}else if(typedElement instanceof Parameter){
			if(container instanceof Operation){
				formSynchronizer.beforeOperation((Operation) container);
			}else{
				formSynchronizer.beforeClass((Classifier) container);
			}
		}else if(typedElement instanceof Pin){
			formSynchronizer.beforeAction((Action) container);
		}
	}
}
