package org.opaeum.eclipse.uml.propertysections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyComposite;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabSelectionListener;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabContents;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;

@SuppressWarnings("restriction")
public class AbstractOpaeumTabbedPropertySheetPage extends TabbedPropertySheetPage{
	private OpaeumSectionActionProvider actionProvider;
	private HashMap<ITabDescriptor,TabContents> tabs = new HashMap<ITabDescriptor,TabContents>();
	private ITabbedPropertySheetPageContributor contributor;
	public AbstractOpaeumTabbedPropertySheetPage(ITabbedPropertySheetPageContributor tabbedPropertySheetPageContributor){
		super(tabbedPropertySheetPageContributor);
		this.contributor = tabbedPropertySheetPageContributor;
		addListener();
	}
	protected void addListener(){
		super.addTabSelectionListener(new ITabSelectionListener(){
			public void tabSelected(ITabDescriptor tabDescriptor){
				applyOptimalBounds();
			}
		});
	}
	public AbstractOpaeumTabbedPropertySheetPage(ITabbedPropertySheetPageContributor tabbedPropertySheetPageContributor,boolean showTitleBar){
		super(tabbedPropertySheetPageContributor, showTitleBar);
		addListener();
	}
	@Override
	public void dispose(){
		super.dispose();
		contributor = null;
		actionProvider = null;
	}
	@Override
	public void selectionChanged(IWorkbenchPart part,ISelection selection){
		if(selection instanceof IStructuredSelection && ((IStructuredSelection) selection).getFirstElement() != null){
			IStructuredSelection ss = ((IStructuredSelection) selection);
//			List<Object> list = new ArrayList<Object>();
//			Object[] array = ss.toArray();
//			for(int i = 0;i < array.length;i++){
//				Object object = array[i];
//				EObject resolveEObject = EmfElementFinder.adaptObject(object);
//				if(resolveEObject != null && resolveEObject.eResource() != null && !list.contains(resolveEObject)){
//					list.add(resolveEObject);
//				}
//				selection = new StructuredSelection(list);
//			}
			if(ss.size() > 0){
				EObject eObject = (EObject) EmfElementFinder.adaptObject(ss.getFirstElement());
				geActionProvider().setCurrentSelection(eObject);
				OpaeumEclipseContext.findOpenUmlFileFor(eObject).geteObjectSelectorUI().pushSelection(eObject);
			}
			geActionProvider().setActionBars(contributor, getSite().getActionBars());
			getSite().getActionBars().updateActionBars();
		}
		super.selectionChanged(part, selection);
		applyOptimalBounds();
	}
	protected void applyOptimalBounds(){
		TabContents currentTab = tabs.get(getSelectedTab());
		if(currentTab != null){
			int maxHeight = 30;
			int maxLabelWidth = 60;
			TabbedPropertyComposite tbc = (TabbedPropertyComposite) this.getControl();
			if(!tbc.isDisposed()){
				Composite tabComposite = (Composite) tbc.getTabComposite().getChildren()[0];
				Composite pageComposite = (Composite) tabComposite.getChildren()[0];
				Control[] children = pageComposite.getChildren();
				ISection[] sections = currentTab.getSections();
				for(int i = 0;i < sections.length;i++){
					ISection s = sections[i];
					if(s instanceof AbstractOpaeumPropertySection){
						AbstractOpaeumPropertySection os = (AbstractOpaeumPropertySection) s;
						if(os.getLabelText() != null){
							maxLabelWidth = Math.max(maxLabelWidth, os.getMinimumLabelWidth());
						}
						if(!s.shouldUseExtraSpace()){
							maxHeight = Math.max(maxHeight, children[i].getSize().y);
						}
					}
				}
				for(int i = 0;i < sections.length;i++){
					ISection s = sections[i];
					if(s instanceof AbstractOpaeumPropertySection){
						AbstractOpaeumPropertySection os = (AbstractOpaeumPropertySection) s;
						if(!os.shouldUseExtraSpace()){
							GridData gd = (GridData) children[i].getLayoutData();
							gd.minimumHeight = maxHeight;
							gd.heightHint = maxHeight;
						}
						os.setLabelWidth(maxLabelWidth);
						((Composite) children[i]).layout();
					}
				}
				tabComposite.layout();
			}
		}
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
	protected Object resolveEObject(Object object){
		return EmfElementFinder.adaptObject(object);
	}
}