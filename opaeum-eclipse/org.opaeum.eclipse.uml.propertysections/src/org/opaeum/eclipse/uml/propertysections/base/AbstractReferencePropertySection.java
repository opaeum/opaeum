package org.opaeum.eclipse.uml.propertysections.base;

import java.util.Collection;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;
import org.opaeum.eclipse.uml.propertysections.common.DefaultFeatureInfo;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.common.ReferenceViewer;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public abstract class AbstractReferencePropertySection extends AbstractOpaeumPropertySection implements ISelectionChangedListener,IChoiceProvider{
	private ReferenceViewer table;
	@Override
	protected void setSectionData(Composite composite){
		FormData data;
		data = new FormData();
		data.left = new FormAttachment(labelCombo);
		data.right = new FormAttachment(100, 0);
		data.bottom = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, 0);
		table.setLayoutData(data);
	}
	@Override
	public Control getPrimaryInput(){
		return null;
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	@Override
	public void selectionChanged(SelectionChangedEvent event){
		updateModel(((IStructuredSelection) event.getSelection()).toList());
	}
	@Override
	protected void maybeAppendCommand(EditingDomain editingDomain,CompoundCommand cmd,Object selectedObject,EObject featureOwner,EStructuralFeature f,
			Object oldValue,Object newValue){
		if(((Collection<?>) oldValue).size() > 0){
			cmd.append(RemoveCommand.create(editingDomain, featureOwner, getFeature(), (Collection<?>)oldValue));
		}
		if(((Collection<?>) newValue).size() > 0){
			cmd.append(AddCommand.create(editingDomain, featureOwner, getFeature(), (Collection<?>) newValue));
		}
	}
	@Override
	protected void hookListeners(){
		table.addSelectionChangedListener(this);
		table.getTable().addMouseListener(new MouseListener(){
			@Override
			public void mouseUp(MouseEvent e){
			}
			@Override
			public void mouseDown(MouseEvent e){
			}
			@Override
			public void mouseDoubleClick(MouseEvent e){
				TableItem item = table.getTable().getItem(new Point(e.x, e.y));
				if(item.getData() instanceof EObject){
					getOpenUmlFile().geteObjectSelectorUI().gotoEObject((EObject) item.getData());
				}
			}
		});
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		table.setInput(buildTableInput());
	}
	protected DefaultFeatureInfo buildTableInput(){
		//TODO calculate common subset
		EObject featureOwner = getFeatureOwner(getSelectedObject());
		return new DefaultFeatureInfo(featureOwner, getFeature(featureOwner));
	}
	@Override
	public void populateControls(){
		table.refresh();
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		if(table != null){
			table.setEnabled(enabled);
		}
	}
	protected void createWidgets(Composite composite){
		this.table = new ReferenceViewer(composite, new String[]{"name"}, getWidgetFactory(), this);
		if(getLabelProvider() != null){
			this.table.setLabelProvider(getLabelProvider());
		}
	}
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new OpaeumItemProviderAdapterFactory());
	}
}
