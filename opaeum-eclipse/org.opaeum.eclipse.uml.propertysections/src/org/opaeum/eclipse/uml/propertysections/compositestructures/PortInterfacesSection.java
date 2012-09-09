package org.opaeum.eclipse.uml.propertysections.compositestructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.internal.sections.TableObjectManager;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.sections.AbstractReferencePropertySection;
import org.topcased.tabbedproperties.sections.widgets.ReferenceViewerComposite;
import org.topcased.tabbedproperties.utils.ITypeCacheAdapter;
import org.topcased.tabbedproperties.utils.TypeCacheAdapter;

@SuppressWarnings("restriction")
public abstract class PortInterfacesSection extends AbstractReferencePropertySection{
	final class SpecialObjectManager extends TableObjectManager{
		private SpecialObjectManager(){
			super(null, UMLPackage.eINSTANCE.getPort_Provided());// Dummy feature to allow the type to be determined
			setLabelProvider((ILabelProvider) PortInterfacesSection.this.getLabelProvider());
		}
		public List<?> chooseObjectsFromDialog(){
			List<? extends EObject> choiceOfValues = new ArrayList<EObject>(getAvailableChoices());
			Shell shell = Display.getDefault().getActiveShell();
			String displayName = "Choose the objects to add";
			FeatureEditorDialog dialog = new FeatureEditorDialog(shell, (ILabelProvider) getLabelProvider(), getProperty(), UMLPackage.eINSTANCE.getInterface(),
					(List<?>) PortInterfacesSection.this.getListValues(), displayName, choiceOfValues, true, true, true);
			dialog.open();
			return dialog.getResult();
		}
		@Override
		public List<?> eGet(){
			return (List<?>) PortInterfacesSection.this.getListValues();
		}
		@SuppressWarnings("unchecked")
		public void updateElement(Object newValue){
			if(newValue == null){
				return;
			}
			List<Interface> oldValues = (List<Interface>) PortInterfacesSection.this.getListValues();
			if(newValue instanceof List<?>){
				List<Interface> newElements = (List<Interface>) newValue;
				CompoundCommand cpcmd = new CompoundCommand();
				for(Interface element:oldValues){
					if(!newElements.contains(element)){
						cpcmd.append(getRemoveCommand(element));
					}
				}
				for(Interface element:newElements){
					if(!oldValues.contains(element)){
						cpcmd.append(getCreateCommand(element));
					}
				}
				getEditingDomain().getCommandStack().execute(cpcmd);
			}
			refresh();
		}
	}
	private SpecialObjectManager objectManager;
	public PortInterfacesSection(){
		super();
	}
	protected abstract Command getRemoveCommand(Interface element);
	protected abstract Command getCreateCommand(Interface element);
	protected BehavioredClassifier getType(){
		Property p = getProperty();
		if(!(p.getType() instanceof BehavioredClassifier)){
			Package portTypes;
			if(p.getOwner() instanceof Component){
				Component component = (Component) p.getOwner();
				portTypes = (Package) component.getPackagedElement("porttypes");
				if(portTypes == null){
					portTypes = UMLFactory.eINSTANCE.createPackage();
					portTypes.setName("porttypes");
					getEditingDomain().getCommandStack().execute(
							AddCommand.create(getEditingDomain(), component, UMLPackage.eINSTANCE.getComponent_PackagedElement(), portTypes));
				}
			}else{
				Package pkg = ((Classifier) p.getOwner()).getNearestPackage();
				portTypes = (Package) pkg.getPackagedElement("porttypes");
				if(portTypes == null){
					portTypes = UMLFactory.eINSTANCE.createPackage();
					portTypes.setName("porttypes");
					getEditingDomain().getCommandStack().execute(AddCommand.create(getEditingDomain(), pkg, UMLPackage.eINSTANCE.getPackage_NestedPackage(), portTypes));
				}
			}
			String name = NameConverter.capitalize(p.getName()) + "Type";
			Type portType = portTypes.getOwnedType(name);
			if(!(portType instanceof BehavioredClassifier)){
				
				portType = UMLFactory.eINSTANCE.createClass();
				StereotypesHelper.getNumlAnnotation(portType).getDetails().put(StereotypeNames.PORT_TYPE, "");
				portType.setName(portType == null ? name : name + "1");
				getEditingDomain().getCommandStack().execute(AddCommand.create(getEditingDomain(), portTypes, UMLPackage.eINSTANCE.getPackage_OwnedType(), portType));
			}
			getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), p, UMLPackage.eINSTANCE.getTypedElement_Type(), portType));
		}
		return (BehavioredClassifier) p.getType();
	}
	private Property getProperty(){
		return (Property) getEObject();
	}
	protected final IBaseLabelProvider getLabelProvider(){
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
				return super.getText(object);
			}
		};
	}
	public void refresh(){
		getTable().setInput(getProperty(), getFeature());
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
			protected void createTableViewer(){
				super.createTableViewer();
				getTableViewer().setContentProvider(new IStructuredContentProvider(){
					@Override
					public void inputChanged(Viewer viewer,Object oldInput,Object newInput){
					}
					@Override
					public void dispose(){
					}
					@Override
					public Object[] getElements(Object inputElement){
						// TODO Auto-generated method stub
						return ((List<?>)getListValues()).toArray();
					}
				});
			}			
			@Override
			public TableObjectManager getObjectManager(){
				return objectManager;
			}
			public void setInput(EObject eObject,EStructuralFeature feature){
				super.setInput(eObject, feature);
				if(objectManager == null || objectManager.getInputEObject()!=eObject){
					objectManager = new SpecialObjectManager();
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
			getTable().setEnabled(true);
		}
	}
	protected Collection<? extends EObject> getAvailableChoices(){
		ITypeCacheAdapter typeCacheAdapter = TypeCacheAdapter.getExistingTypeCacheAdapter(getEObject());
		Collection<EObject> types = typeCacheAdapter.getReachableObjectsOfType(getEObject(), UMLPackage.eINSTANCE.getInterface());
		return types;
	}
}
