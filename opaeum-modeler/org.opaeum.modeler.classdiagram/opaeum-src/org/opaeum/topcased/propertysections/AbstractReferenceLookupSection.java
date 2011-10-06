package org.opaeum.topcased.propertysections;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.internal.sections.TableObjectManager;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.sections.AbstractReferencePropertySection;
import org.topcased.tabbedproperties.sections.widgets.ReferenceViewerComposite;

public abstract class AbstractReferenceLookupSection extends AbstractReferencePropertySection{
	final class SpecialObjectManager extends TableObjectManager{
		private SpecialObjectManager(EObject eObject,EStructuralFeature structuralFeature){
			super(eObject, structuralFeature);
			setLabelProvider((ILabelProvider) AbstractReferenceLookupSection.this.getLabelProvider());
		}
		public List<?> chooseObjectsFromDialog(){
			List<? extends EObject> choiceOfValues = getAvailableChoices();
			Shell shell = Display.getDefault().getActiveShell();
			String displayName = "Choose the objects to add";
			FeatureEditorDialog dialog = new FeatureEditorDialog(shell, (ILabelProvider) getLabelProvider(), getFeatureOwner(), getFeature(), displayName, choiceOfValues);
			dialog.open();
			return dialog.getResult();
		}
	}
	private TableObjectManager objectManager;
	public AbstractReferenceLookupSection(){
		super();
	}
	protected abstract EObject getFeatureOwner();
	protected abstract List<? extends EObject> getAvailableChoices();
	protected final IBaseLabelProvider getLabelProvider(){
		List<AdapterFactory> f = new ArrayList<AdapterFactory>();
		f.add(new UMLItemProviderAdapterFactory());
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
	public void refresh(){
		getTable().setInput(getFeatureOwner(), getFeature());
		getTable().setEditingDomain(getEditingDomain());
		getTable().refresh();
	}
	protected void createWidgets(Composite composite){
		setTable(new ReferenceViewerComposite(composite, new String[]{
			getLabelText()
		}, getWidgetFactory()){
			public void updateSelectedItem(Object data){
				updateSelection(data);
			}
			@Override
			public TableObjectManager getObjectManager(){
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
}