package org.opaeum.eclipse.uml.propertysections.base;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.internal.sections.TableObjectManager;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.sections.AbstractReferencePropertySection;
import org.topcased.tabbedproperties.sections.widgets.ReferenceViewerComposite;

public abstract class AbstractReferenceLookupSection extends AbstractReferencePropertySection{
	final class SpecialObjectManager extends TableObjectManager{
		EStructuralFeature feature;
		private SpecialObjectManager(EObject eObject,EStructuralFeature structuralFeature){
			super(eObject, structuralFeature);
			feature = structuralFeature;
			setLabelProvider((ILabelProvider) AbstractReferenceLookupSection.this.getLabelProvider());
		}
		@Override
		public void updateElement(Object newValue){
			if(newValue == null){
				return;
			}
			Command command = null;
			CompoundCommand cpcmd = new CompoundCommand();
			for(EObject eObject:getEObjectList()){
				EObject inputEObject = getFeatureOwner(eObject);
				if(inputEObject != null){
					List<?> oldValues = (List<?>) inputEObject.eGet(feature);
					if(newValue instanceof List<?>){
						List<?> newElements = (List<?>) newValue;
						// Search for deleted items
						for(Object element:oldValues){
							if(!newElements.contains(element)){
								cpcmd.append(RemoveCommand.create(getEditingDomain(), inputEObject, feature, element));
							}
						}
						// Search for added items
						for(Object element:newElements){
							if(!oldValues.contains(element)){
								cpcmd.append(AddCommand.create(getEditingDomain(), inputEObject, feature, element));
							}
						}
						command = cpcmd;
					}else{
						command = AddCommand.create(getEditingDomain(), inputEObject, feature, newValue, oldValues.size());
					}
				}
			}
			getEditingDomain().getCommandStack().execute(command);
		}
		public List<?> chooseObjectsFromDialog(){
			List<? extends EObject> choiceOfValues = getAvailableChoices();
			Shell shell = Display.getDefault().getActiveShell();
			String displayName = "Choose the objects to add";
			FeatureEditorDialog dialog = new FeatureEditorDialog(shell, (ILabelProvider) getLabelProvider(), getFeatureOwner(), getFeature(),
					displayName, choiceOfValues);
			dialog.open();
			return dialog.getResult();
		}
	}
	private TableObjectManager objectManager;
	public AbstractReferenceLookupSection(){
		super();
	}
	protected abstract EObject getFeatureOwner(EObject e);
	private  final EObject getFeatureOwner(){
		return getFeatureOwner(getEObject());
	}
	protected abstract List<? extends EObject> getAvailableChoices();
	protected void removeListener(){
		super.removeListener();
		if(getEObject() != null && getFeatureOwner() != null){
			getFeatureOwner().eAdapters().remove(getModelListener());
		}
	}
	protected void handleModelChanged(Notification msg){
		Object notifier = msg.getNotifier();
		if(notifier instanceof EObject && ((EObject) notifier).eResource() == null){
			// deleted
			((Notifier) notifier).eAdapters().remove(getModelListener());
		}else if(notifier.equals(getFeatureOwner()) && getFeature() != null){
			Object msgFeature = msg.getFeature();
			if(msg.getFeatureID(getEObject().getClass()) == getFeature().getFeatureID() || getFeature().equals(msgFeature)){
				if(getTable().isDisposed()){
					((Notifier) notifier).eAdapters().remove(getModelListener());
				}else{
					refresh();
				}
			}
		}
	}
	protected void addListener(){
		super.addListener();
		if(getEObject() != null && getFeatureOwner() != null){
			getFeatureOwner().eAdapters().add(getModelListener());
		}
	}
	protected IBaseLabelProvider getLabelProvider(){
		List<AdapterFactory> f = new ArrayList<AdapterFactory>();
		f.add(new OpaeumItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f)){
			@Override
			public String getColumnText(Object object,int columnIndex){
				return getText(object);
			}
			@Override
			public String getText(Object object){
				if(object instanceof Property){
					Property p = (Property) object;
					String typeName = p.getType() == null ? "untyped" : p.getType().getName();
					if(p.eContainer() instanceof Classifier && p.getAssociation() != p.eContainer()){
						return ((Classifier) p.eContainer()).getName() + "::" + p.getName() + ":" + typeName;
					}else if(p.getAssociation() != null){
						return p.getOtherEnd().getType().getName() + "::" + p.getName() + ":" + typeName;
					}
				}
				return super.getText(object);
			}
		};
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		getTable().setInput(getFeatureOwner(), getFeature());
		getTable().setEditingDomain(getEditingDomain());
	}
	public void refresh(){
		getTable().refresh();
		getTable().getParent().getParent().getParent().layout();
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	protected void createWidgets(Composite composite){
		setTable(new ReferenceViewerComposite(composite, new String[]{getLabelText()}, getWidgetFactory()){
			public void updateSelectedItem(Object data){
				updateSelection(data);
			}
			@Override
			protected void createButtons(Composite parent){
				super.createButtons(parent);
				super.getTable().addMouseListener(new MouseListener(){
					@Override
					public void mouseUp(MouseEvent e){
					}
					@Override
					public void mouseDown(MouseEvent e){
					}
					@Override
					public void mouseDoubleClick(MouseEvent e){
						TableItem item = getTable().getItem(new Point(e.x, e.y));
						if(item.getData() instanceof EObject){
							OpaeumEclipseContext.getCurrentContext().geteObjectSelectorUI().gotoEObject((EObject) item.getData());
						}
					}
				});
			}
			@Override
			public TableObjectManager getObjectManager(){
				return objectManager;
			}
			public void setInput(EObject eObject,EStructuralFeature feature){
				if(objectManager == null || objectManager.getInputEObject() == null || !objectManager.getInputEObject().equals(eObject)){
					super.setInput(eObject, feature);
					objectManager = new SpecialObjectManager(eObject, feature);
					objectManager.setLabelProvider((ILabelProvider) getLabelProvider());
					getTableViewer().setInput(objectManager);
				}
			}
			@Override
			public void setEditingDomain(EditingDomain editingDomain){
				objectManager.setEditingDomain(editingDomain);
			}
		});
		getTable().setLabelProvider(getLabelProvider());
	}
	@Override
	protected final Object getListValues(){
		return null;
	}
}