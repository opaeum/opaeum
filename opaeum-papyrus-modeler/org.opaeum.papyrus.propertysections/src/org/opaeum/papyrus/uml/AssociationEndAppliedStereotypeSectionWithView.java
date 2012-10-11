package org.opaeum.papyrus.uml;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.papyrus.uml.profile.tree.objects.StereotypedElementTreeObject;
import org.eclipse.papyrus.uml.properties.profile.ui.compositeforview.AppliedStereotypeCompositeWithView;
import org.eclipse.papyrus.uml.properties.profile.ui.compositeforview.AppliedStereotypePropertyCompositeWithView;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Element;
//import org.eclipse.papyrus.uml.profile.ui.compositeforview.AppliedStereotypeCompositeWithView;
//import org.eclipse.papyrus.uml.profile.ui.compositeforview.AppliedStereotypePropertyCompositeWithView;

public abstract class AssociationEndAppliedStereotypeSectionWithView extends AbstractPropertySection{
	private AppliedStereotypeCompositeWithView appliedStereotypeComposite;
	private AppliedStereotypePropertyCompositeWithView propertyComposite;
	public AssociationEndAppliedStereotypeSectionWithView(){
		super();
	}
	@Override
	public void createControls(Composite parent,TabbedPropertySheetPage tabbedPropertySheetPage){
		super.createControls(parent, tabbedPropertySheetPage);
		appliedStereotypeComposite = new AppliedStereotypeCompositeWithView(parent);
		appliedStereotypeComposite.createContent(parent, getWidgetFactory());
		propertyComposite = new AppliedStereotypePropertyCompositeWithView(parent, appliedStereotypeComposite);
		propertyComposite.createContent(parent, getWidgetFactory());
	}
	@Override
	public void refresh(){
		appliedStereotypeComposite.refresh();
		propertyComposite.refresh();
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		if(selection instanceof IStructuredSelection){
			Object input = ((IStructuredSelection) selection).getFirstElement();
			if(input instanceof GraphicalEditPart && ((GraphicalEditPart) input).getModel() instanceof View){
				GraphicalEditPart graphicalEditPart = (GraphicalEditPart) input;
				View view = (View) graphicalEditPart.getModel();
				Association association = (Association) view.getElement();
				if(association != null){
					appliedStereotypeComposite.setDiagramElement(view);
					propertyComposite.setDiagramElement(view);
					appliedStereotypeComposite.setElement(getEnd(association));
					appliedStereotypeComposite.setInput(new StereotypedElementTreeObject(getEnd(association)));
				}
			}else{
				EObject eobject = resolveSemanticObject(input);
				if(eobject instanceof Association){
					Association association = (Association) eobject;
					appliedStereotypeComposite.setDiagramElement(null);
					appliedStereotypeComposite.setElement(getEnd(association));
					appliedStereotypeComposite.setInput(new StereotypedElementTreeObject(getEnd(association)));
				}
			}
		}
	}
	protected abstract Element getEnd(Association association);
	private EObject resolveSemanticObject(Object object){
		if(object instanceof EObject){
			return (EObject) object;
		}else if(object instanceof IAdaptable){
			IAdaptable adaptable = (IAdaptable) object;
			if(adaptable.getAdapter(EObject.class) != null){
				return (EObject) adaptable.getAdapter(EObject.class);
			}
		}
		return null;
	}
	public void dispose(){
		super.dispose();
		// if(appliedStereotypeComposite != null)
		// appliedStereotypeComposite.disposeListeners();
		// if(propertyComposite != null)
		// propertyComposite.disposeListeners();
	}
}