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
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.context.OpaeumEclipseContext;
import org.opaeum.eclipse.uml.propertysections.base.AbstractReferencePropertySection;
import org.opaeum.eclipse.uml.propertysections.common.DefaultFeatureInfo;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.name.NameConverter;
import org.opaeum.topcased.uml.editor.OpaeumItemProviderAdapterFactory;

public abstract class PortInterfacesSection extends AbstractReferencePropertySection{
	public PortInterfacesSection(){
		super();
	}
	protected abstract Command getRemoveCommand(BehavioredClassifier bc,Interface element);
	protected abstract Command getCreateCommand(BehavioredClassifier bc,Interface element);
	@Override
	@SuppressWarnings("unchecked")
	protected void maybeAppendCommand(EditingDomain editingDomain,CompoundCommand cpcmd,Object selectedObject,EObject featureOwner,EStructuralFeature f,Object oldValue, Object newValue){
		List<Interface> oldValues = (List<Interface>) oldValue;
		List<Interface> newElements = (List<Interface>) newValue;
		BehavioredClassifier bc = getType(featureOwner);
		for(Interface element:oldValues){
			if(!newElements.contains(element)){
				cpcmd.append(getRemoveCommand(bc, element));
			}
		}
		for(Interface element:newElements){
			if(!oldValues.contains(element)){
				cpcmd.append(getCreateCommand(bc, element));
			}
		}
		getEditingDomain().getCommandStack().execute(cpcmd);
	}
	protected BehavioredClassifier getType(EObject owner){
		Property p = (Property) owner;
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
	public final ILabelProvider getLabelProvider(){
		List<AdapterFactory> f = new ArrayList<AdapterFactory>();
		f.add(new OpaeumItemProviderAdapterFactory());
		return new AdapterFactoryLabelProvider(new ComposedAdapterFactory(f));
	}
	@Override
	protected DefaultFeatureInfo buildTableInput(){
		return new DefaultFeatureInfo(getSelectedObject(), getFeature()){
			@Override
			public List<?> getCurrentValues(){
				return getListValues();
			}
		};
	}
	public Object[] getChoices(){
		Collection<EObject> types = OpaeumEclipseContext.getReachableObjectsOfType(getSelectedObject(), UMLPackage.eINSTANCE.getInterface());
		return types.toArray();
	}
	protected abstract List<?> getListValues();
}
