package org.nakeduml.topcased.propertysections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.nakeduml.eclipse.ElementFinder;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.internal.sections.TableObjectManager;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.sections.AbstractReferencePropertySection;
import org.topcased.tabbedproperties.sections.widgets.ReferenceViewerComposite;

public abstract class AbstractPropertyLookupSection extends AbstractReferencePropertySection{
	final class SpecialObjectManager extends TableObjectManager{
		private SpecialObjectManager(EObject eObject,EStructuralFeature structuralFeature){
			super(eObject, structuralFeature);
		}
		public List<?> chooseObjectsFromDialog(){
			Property p = (Property) getEObject();
			List<EObject> choiceOfValues = new ArrayList<EObject>();
			if(p.getClass_() != null){
				choiceOfValues.addAll(ElementFinder.getPropertiesInScope(p.getClass_()));
			}else if(p.getAssociation() != null){
				choiceOfValues.addAll(ElementFinder.getPropertiesInScope(p.getAssociation()));
			}
			if(p.getType() != null){
				Iterator<EObject> iter = choiceOfValues.iterator();
				while(iter.hasNext()){
					Property rsp = (Property) iter.next();
					if(rsp==p || (rsp.getType() != null && !p.getType().conformsTo(rsp.getType()))){
						iter.remove();
					}
				}
			}
			Shell shell = Display.getDefault().getActiveShell();
			String displayName = "Choose the objects to add";
			FeatureEditorDialog dialog = new FeatureEditorDialog(shell, (ILabelProvider) getLabelProvider(), getEObject(), getFeature(), displayName, choiceOfValues);
			dialog.open();
			return dialog.getResult();
		}
	}
	private TableObjectManager objectManager;
	public AbstractPropertyLookupSection(){
		super();
	}
	protected IBaseLabelProvider getLabelProvider(){
		if(getFeature() instanceof EReference){
			List<AdapterFactory> f = new ArrayList<AdapterFactory>();
			f.add(new UMLItemProviderAdapterFactory());
			f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
			return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
		}
		return null;
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
				if(objectManager==null ||objectManager.getInputEObject().equals(eObject)){
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