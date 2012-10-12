package org.opaeum.papyrus.editor;

import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.LinkItem;
import org.eclipse.emf.facet.infra.browser.uicore.internal.model.ModelElementItem;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.papyrus.infra.core.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.utils.EditorUtils;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyComposite;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabContents;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;
import org.opaeum.eclipse.uml.propertysections.base.IOpaeumTabbedPropertySheetPage;
import org.opaeum.papyrus.uml.modelexplorer.OpaeumModelExplorerPageBookView;
import org.opaeum.propertysections.OpaeumSectionActionProvider;

@SuppressWarnings("restriction")
public final class OpaeumTabbedPropertySheetPage extends TabbedPropertySheetPage implements IOpaeumTabbedPropertySheetPage{
	Integer labelWidth;
	private ITabbedPropertySheetPageContributor contributor;
	boolean activated = false;
	private OpaeumSectionActionProvider actionProvider;
	private HashMap<ITabDescriptor,TabContents> tabs = new HashMap<ITabDescriptor,TabContents>();
	public OpaeumTabbedPropertySheetPage(ITabbedPropertySheetPageContributor tabbedPropertySheetPageContributor){
		super(tabbedPropertySheetPageContributor);
		this.contributor = tabbedPropertySheetPageContributor;
	}
	@Override
	protected void handlePartActivated(IWorkbenchPart part){
		if(part instanceof OpaeumModelExplorerPageBookView){
			// substitute for the Editor to:
			// 1. avoid unnecessary calculations
			// 2. avoid incorrect refreshing of Sections
			IMultiDiagramEditor multiDiagramEditor = EditorUtils.getMultiDiagramEditor();
			if(multiDiagramEditor instanceof OpaeumMultiDiagramEditor){
				super.handlePartActivated(multiDiagramEditor);
			}else{
				super.handlePartActivated(part);
			}
		}else{
			super.handlePartActivated(part);
		}
	}
	@Override
	public void selectionChanged(IWorkbenchPart part,ISelection selection){
		if(selection instanceof IStructuredSelection && ((IStructuredSelection) selection).getFirstElement() != null){
			IStructuredSelection ss = ((IStructuredSelection) selection);
			Object[] array = ss.toArray();
			for(int i = 0;i < array.length;i++){
				Object object = array[i];
				array[i] = resolveEObject(object);
				selection = new StructuredSelection(array);
			}
			geActionProvider().setActionBars(contributor, getSite().getActionBars());
			if(array.length > 0){
				EObject eObject = (EObject) array[0];
				OpaeumEclipseContext.getContextFor(eObject).geteObjectSelectorUI().pushSelection(eObject);
			}
		}
		getSite().getActionBars().updateActionBars();
		labelWidth = null;
		super.selectionChanged(part, selection);
		TabContents currentTab = tabs.get(getSelectedTab());
		int max = 40;
		if(currentTab != null){
			TabbedPropertyComposite tbc = (TabbedPropertyComposite) super.getControl();
			Composite tabComposite = (Composite) tbc.getTabComposite().getChildren()[0];
			Composite pageComposite = (Composite) tabComposite.getChildren()[0];
			Control[] children = pageComposite.getChildren();
			ISection[] sections = currentTab.getSections();
			for(int i = 0;i < sections.length;i++){
				ISection s = sections[i];
				if(s instanceof AbstractOpaeumPropertySection && !s.shouldUseExtraSpace()){
					max = Math.max(max, children[i].getSize().y);
				}
			}
			for(int i = 0;i < sections.length;i++){
				ISection s = sections[i];
				if(s instanceof AbstractOpaeumPropertySection && !s.shouldUseExtraSpace()){
					GridData gd = (GridData) children[i].getLayoutData();
					gd.minimumHeight = max;
					gd.heightHint = max;
				}
			}
			tbc.getTabComposite().layout();
		}
	}
	private Object resolveEObject(Object object){
		if(object instanceof ModelElementItem){
			return ((ModelElementItem) object).getEObject();
		}else if(object instanceof LinkItem){
			return ((LinkItem) object).getParent();
		}else if(object instanceof EditPart){
			Object model = ((EditPart) object).getModel();
			if(model instanceof View){
				return ((View) model).getElement();
			}
		}
		return object;
	}
	protected OpaeumSectionActionProvider geActionProvider(){
		if(this.actionProvider == null){
			actionProvider = new OpaeumSectionActionProvider();
		}
		return actionProvider;
	}
	@Override
	protected TabContents createTab(ITabDescriptor tabDescriptor){
		TabContents createTab = super.createTab(tabDescriptor);
		tabs.put(tabDescriptor, createTab);
		return createTab;
	}
	public Integer getLabelWidth(){
		if(labelWidth == null){
			TabContents currentTab = tabs.get(getSelectedTab());
			if(currentTab != null){
				int max = AbstractPropertySection.STANDARD_LABEL_WIDTH + 65;
				GC gc = new GC(Display.getDefault());
				for(ISection s:currentTab.getSections()){
					if(s instanceof AbstractOpaeumPropertySection){
						AbstractOpaeumPropertySection os = (AbstractOpaeumPropertySection) s;
						if(os.getLabelText() != null){
							max = Math.max(max, gc.textExtent(os.getLabelText()).x);
						}
					}
				}
				labelWidth = max;
			}
		}
		return labelWidth;
	}
	public Integer getSectionHeight(){
		// TODO Auto-generated method stub
		return null;
	}
}