package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.context.EObjectSelectorUI;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.common.OpaeumTextViewer;
import org.opaeum.eclipse.uml.propertysections.base.AbstractStringPropertySection;
import org.topcased.tabbedproperties.sections.widgets.IText;

public class NamedElementNameSection extends AbstractStringPropertySection{
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		EObject eObject = getEObject();
		super.setInput(part, selection);
		if(eObject != getEObject() && OpaeumEclipseContext.getCurrentContext() != null){
			OpaeumEclipseContext ct = OpaeumEclipseContext.getCurrentContext();
			EObjectSelectorUI selector = ct.geteObjectSelectorUI();
			if(selector != null){
				selector.pushSelection(getEObject());
			}
		}
	}
	public IText getTextWidget(Composite parent,int style){
		return new OpaeumTextViewer(parent, style);
	}
	@Override
	public void makeContributions(IMenuManager menuManager,IToolBarManager toolBarManager,IStatusLineManager statLineManager){

		super.makeContributions(menuManager, toolBarManager, statLineManager);
	}
	public String getLabelText(){
		return "Name:";
	}
	/**
	 * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getFeature()
	 */
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getNamedElement_Name();
	}
}
