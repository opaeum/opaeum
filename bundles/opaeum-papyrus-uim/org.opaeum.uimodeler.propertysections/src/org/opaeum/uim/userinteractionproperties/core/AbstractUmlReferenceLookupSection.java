package org.opaeum.uim.userinteractionproperties.core;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Control;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.edit.providers.UMLItemProviderAdapterFactory;
import org.opaeum.eclipse.uml.propertysections.base.AbstractReferencePropertySection;
import org.opaeum.eclipse.uml.propertysections.common.DefaultFeatureInfo;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.util.UmlUimLinks;

public abstract class AbstractUmlReferenceLookupSection extends AbstractReferencePropertySection{
	public AbstractUmlReferenceLookupSection(){
		super();
	}
	protected abstract List<? extends EObject> getAvailableChoices();
	@Override
	public Object[] getChoices(){
		return getAvailableChoices().toArray();
	}
	@Override
	protected abstract EObject getFeatureOwner(EObject nextObject);
	protected abstract UmlReference createNewReference();
	protected abstract List<? extends UmlReference> getCurrentUmlReferences();
	@Override
	public Control getPrimaryInput(){
		throw new IllegalStateException();
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	@Override
	protected void maybeAppendCommand(EditingDomain editingDomain,CompoundCommand cmd,Object selectedObject,EObject featureOwner,EStructuralFeature f,
			Object oldValue,Object newValue){
		List<?> newElements = (List<?>) newValue;
		if(oldValue instanceof List && ((List<?>) oldValue).size() > 0){
			cmd.append(RemoveCommand.create(getEditingDomain(), featureOwner, getFeature(), oldValue));
		}
		if(newElements.size() > 0){
			if(featureOwner == null){
				featureOwner = createFeatureOwner((EObject) selectedObject, cmd);
			}
			for(Object element:newElements){
				UmlReference ref = createNewReference();
				ref.setUmlElementUid(EmfWorkspace.getId((EObject) element));
				cmd.append(AddCommand.create(getEditingDomain(), featureOwner, getFeature(), ref));
			}
		}
	}
	@Override
	protected final ILabelProvider getLabelProvider(){
		List<AdapterFactory> f = new ArrayList<AdapterFactory>();
		// TODO change back to UMLItem..
		f.add(new UMLItemProviderAdapterFactory());
		f.addAll(getPrincipalAdapterFactories());
		return new AdapterFactoryLabelProvider(new ComposedAdapterFactory(f)){
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
	@Override
	protected DefaultFeatureInfo buildTableInput(){
		return new DefaultFeatureInfo(getFeatureOwner(getSelectedObject()), getFeature()){
			@Override
			@SuppressWarnings({"rawtypes","unchecked"})
			public List<?> getCurrentValues(){
				List<? extends UmlReference> requiredRoles = (List) super.getCurrentValues();
				UmlUimLinks links = UmlUimLinks.getCurrentUmlLinks((UserInteractionElement) getFeatureOwner(getSelectedObject()));
				List<EObject> result = new ArrayList<EObject>();
				for(UmlReference requiredRole:requiredRoles){
					Element umlElement = links.getUmlElement(requiredRole);
					if(umlElement != null){
						result.add(umlElement);
					}
				}
				return result;
			}
		};
	}
	protected abstract EObject createFeatureOwner(EObject currentObject,CompoundCommand cc);
}