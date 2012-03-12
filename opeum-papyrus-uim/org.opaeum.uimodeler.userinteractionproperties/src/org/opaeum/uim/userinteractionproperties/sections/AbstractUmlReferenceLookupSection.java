package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.internal.sections.TableObjectManager;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.sections.AbstractReferencePropertySection;
import org.topcased.tabbedproperties.sections.widgets.ReferenceViewerComposite;

//TODO rewrite all of the superclasses SOlution should be much simpler for references
public abstract class AbstractUmlReferenceLookupSection extends AbstractReferencePropertySection{
	@SuppressWarnings("restriction")
	final class SpecialObjectManager extends TableObjectManager{
		private SpecialObjectManager(EObject eObject,EStructuralFeature structuralFeature){
			super(eObject, structuralFeature);
			setLabelProvider((ILabelProvider) AbstractUmlReferenceLookupSection.this.getLabelProvider());
		}
		public List<?> chooseObjectsFromDialog(){
			List<? extends EObject> choiceOfValues = getAvailableChoices();
			Shell shell = Display.getDefault().getActiveShell();
			String displayName = "Choose the objects to add";
			FeatureEditorDialog dialog = new FeatureEditorDialog(shell, (ILabelProvider) getLabelProvider(), getFeatureOwnerOnLookup(),
					getFeature().getEType(), eGet(), displayName, choiceOfValues, true, true);
			dialog.open();
			return dialog.getResult();
		}
		@Override
		public List<?> eGet(){
			return (List<?>) getListValues();
		}
		@Override
		public void addElement(Object newElement){
			System.out.println(newElement);
		}
		@Override
		public void removeElement(Object element){
			System.out.println(element);
		}
		@Override
		public void updateElement(Object newValue){
			Command command;
			List<? extends UmlReference> oldValues = (List<? extends UmlReference>) getFeatureOwner().eGet(getFeature());
			if(newValue instanceof List<?>){
				List<?> newElements = (List<?>) newValue;
				CompoundCommand cpcmd = new CompoundCommand();
				cpcmd.append(RemoveCommand.create(getEditingDomain(), getFeatureOwner(), getFeature(), oldValues));
				for(Object element:newElements){
					UmlReference ref = createNewReference();
					ref.setUmlElementUid(EmfWorkspace.getId((EObject) element));
					cpcmd.append(AddCommand.create(getEditingDomain(), getFeatureOwner(), getFeature(), ref));
				}
				command = cpcmd;
				getEditingDomain().getCommandStack().execute(command);
				refresh();
			}
		}
	}
	private TableObjectManager objectManager;
	public AbstractUmlReferenceLookupSection(){
		super();
	}
	protected abstract EObject getFeatureOwner();
	protected abstract List<? extends EObject> getAvailableChoices();
	protected abstract UmlReference createNewReference();
	protected abstract List<? extends UmlReference> getCurrentUmlReferences();
	protected EObject getFeatureOwnerOnLookup(){
		return getFeatureOwner();
	}
	protected final IBaseLabelProvider getLabelProvider(){
		List<AdapterFactory> f = new ArrayList<AdapterFactory>();
		//TODO change back to UMLItem..
		f.add(new UMLItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f)){
			@Override
			public String getColumnText(Object object,int columnIndex){
				return getText(object);
			}
			@Override
			public String getText(Object object){
				if(object instanceof UserInteractionElement){
					UserInteractionElement p = (UserInteractionElement) object;
					return p.eClass().getName() + "::" + p.getName();
				}
				return super.getText(object);
			}
		};
	}
	public void refresh(){
		if(getFeatureOwner() != null){
			getTable().setInput(getFeatureOwner(), getFeature());
			getTable().setEditingDomain(getEditingDomain());
		}
		getTable().refresh();
		getTable().setSize(800, 100);
		getTable().getParent().pack();
		FormData fd = (FormData) getTable().getLayoutData();
		fd.right = new FormAttachment(100);
		fd.bottom = new FormAttachment(100);
		fd.height=100;
		getTable().getParent().getParent().layout();
	}
	protected void createWidgets(Composite composite){
		setTable(new ReferenceViewerComposite(composite, new String[]{getLabelText()}, getWidgetFactory()){
			public void updateSelectedItem(Object data){
				updateSelection(data);
			}
			@Override
			protected void createTableViewer(){
				super.createTableViewer();
				getTableViewer().setContentProvider(new ArrayContentProvider(){
					@Override
					public Object[] getElements(Object inputElement){
						return getObjectManager().eGet().toArray();
					}
				});
			}
			@Override
			public TableObjectManager getObjectManager(){
				if(objectManager == null){
					objectManager = new SpecialObjectManager(getFeatureOwnerOnLookup(), getFeature());
				}
				return objectManager;
			}
			public void setInput(EObject eObject,EStructuralFeature feature){
				super.setInput(eObject, feature);
				if(objectManager == null || !objectManager.getInputEObject().equals(eObject)){
					objectManager = new SpecialObjectManager(eObject, feature);
					objectManager.setLabelProvider((ILabelProvider) getLabelProvider());
					getTableViewer().setInput(objectManager);
				}
			}
			@Override
			public void setEditingDomain(EditingDomain editingDomain){
				super.setEditingDomain(editingDomain);
				objectManager.setEditingDomain(editingDomain);
			}
		});
		getTable().setLabelProvider(getLabelProvider());
		if(getFeature() != null){
			getTable().setEnabled(getFeature().isChangeable());
		}
	}
	@Override
	protected Object getListValues(){
		List<? extends UmlReference> requiredRoles = getCurrentUmlReferences();
		UmlUimLinks links = UmlUimLinks.getCurrentUmlLinks((UserInteractionElement) getEObject());
		List<EObject> result = new ArrayList<EObject>();
		for(UmlReference requiredRole:requiredRoles){
			Element umlElement = links.getUmlElement(requiredRole);
			if(umlElement != null){
				result.add(umlElement);
			}
		}
		return result;
	}
}