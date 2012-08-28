package org.opaeum.eclipse.uml.propertysections.core;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.context.EObjectSelectorUI;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.topcased.tabbedproperties.sections.AbstractStringPropertySection;
import org.topcased.tabbedproperties.sections.widgets.IText;

public class NamedElementNameSection extends AbstractStringPropertySection{
	public static class OpaeumTextViewer extends TextViewer implements IText{

		public OpaeumTextViewer() {
			super();
		}

		public OpaeumTextViewer(Composite parent, int styles) {
			super(parent, styles|SWT.BORDER|SWT.FLAT);
		}

		@Override
		public void setEnabled(boolean enabled) {
			getTextWidget().setEnabled(enabled);
			
		}

		@Override
		public void setLayoutData(Object layoutData) {
			getTextWidget().setLayoutData(layoutData);
		}

		@Override
		public void setText(String string) {
			getTextWidget().setText(string);
		}

		@Override
		public String getText() {
			return getTextWidget().getText();
		}

		@Override
		public void setBackground(Color color) {
			getTextWidget().setBackground(color);
			
		}

		@Override
		public void setForeground(Color color) {
			getTextWidget().setForeground(color);
			
		}

		@Override
		public Control getTextControl() {
			return getTextWidget();
		}
		
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		EObject eObject = getEObject();
		super.setInput(part, selection);
		if(eObject != getEObject() && OpaeumEclipseContext.getCurrentContext()!=null){
			OpaeumEclipseContext ct = OpaeumEclipseContext.getCurrentContext();
			EObjectSelectorUI selector = ct.geteObjectSelectorUI();
			if(selector != null){
				selector.pushSelection(getEObject());
			}
		}
	}
    public IText getTextWidget(Composite parent, int style) {
    	return new OpaeumTextViewer(parent, style);
	}

	@Override
	public void makeContributions(IMenuManager menuManager,IToolBarManager toolBarManager,IStatusLineManager statLineManager){
		toolBarManager.removeAll();
		if(toolBarManager.find("asdfasdfa.back") == null){
			final IWorkbenchPage activePage = getActivePage();
			Action action = new Action("Back"){
				@Override
				public void run(){
					NavigationDecorator.goToPreviousEObject(activePage);
				}
			};
			ISharedImages images = PlatformUI.getWorkbench().getSharedImages();
			action.setImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_BACK));
			action.setDisabledImageDescriptor(images.getImageDescriptor(ISharedImages.IMG_TOOL_BACK_DISABLED));
			ActionContributionItem item = new ActionContributionItem(action);
			item.setVisible(true);
			item.setId("asdfasdfa.back");
			toolBarManager.add(item);
			toolBarManager.update(false);
		}
		super.makeContributions(menuManager, toolBarManager, statLineManager);
	}
	protected String getLabelText(){
		return "Name:";
	}
	/**
	 * @see org.topcased.tabbedproperties.sections.AbstractTextPropertySection#getFeature()
	 */
	protected EStructuralFeature getFeature(){
		return UMLPackage.eINSTANCE.getNamedElement_Name();
	}
}
