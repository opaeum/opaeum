package org.opaeum.eclipse.uml.propertysections.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.uml2.uml.Class;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extension;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.common.DefaultFeatureInfo;
import org.opaeum.emf.extraction.StereotypesHelper;

public class StereotypeExtendedMetaclassesSection extends AbstractReferencePropertySection{
	public StereotypeExtendedMetaclassesSection(){
		super();
	}
	@Override
	public boolean shouldUseExtraSpace(){
		return true;
	}
	Stereotype getStereotype(){
		return (Stereotype) getSelectedObject();
	}
	public Object[] getChoices(){
		List<Object> result = new ArrayList<Object>();
		TreeIterator<Notifier> allContents = getSelectedObject().eResource().getResourceSet().getAllContents();
		while(allContents.hasNext()){
			Notifier notifier = (Notifier) allContents.next();
			if(notifier instanceof Classifier && StereotypesHelper.hasStereotype((Element) notifier, "Metaclass")){
				result.add(notifier);
			}
		}
		return result.toArray();
	}
	@Override
	protected void maybeAppendCommand(EditingDomain editingDomain,CompoundCommand cmd,Object selectedObject,EObject featureOwner,
			EStructuralFeature f,Object oldValue,Object newValue){
		Collection<?> oldValues = (Collection<?>) oldValue;
		if(oldValues.size() > 0){
			for(Extension extension:getStereotype().getProfile().getOwnedExtensions(false)){
				if(extension.getStereotype() == getStereotype() && oldValues.contains(extension.getMetaclass())){
					cmd.append(RemoveCommand.create(editingDomain, getStereotype().getProfile(), UMLPackage.eINSTANCE.getPackage_PackagedElement(),
							extension));
					cmd.append(RemoveCommand.create(editingDomain, getStereotype(), UMLPackage.eINSTANCE.getStructuredClassifier_OwnedAttribute(),
							extension.getStereotypeEnd()));
				}
			}
		}
		Collection<?> newValues = (Collection<?>) newValue;
		if(newValues.size() > 0){
			for(Object object:newValues){
				Class metaClass = (Class) object;
				Extension e = UMLFactory.eINSTANCE.createExtension();
				e.getOwnedEnd("extension_" + metaClass.getName(), metaClass, false, UMLPackage.eINSTANCE.getExtensionEnd(), true);
				cmd.append(AddCommand.create(editingDomain, getStereotype().getProfile(), UMLPackage.eINSTANCE.getPackage_PackagedElement(),
						e));
				Property p  = UMLFactory.eINSTANCE.createProperty();
				p.setType(metaClass);
				p.setName("base_"+metaClass.getName());
				cmd.append(AddCommand.create(editingDomain, getStereotype(), UMLPackage.eINSTANCE.getStructuredClassifier_OwnedAttribute(),
						p));
				cmd.append(AddCommand.create(editingDomain, e, UMLPackage.eINSTANCE.getAssociation_MemberEnd(),
						p));
			}
		}
	}
	protected Object getExistingFeatureValue(EObject owner,EStructuralFeature f){
		return getStereotype().getExtendedMetaclasses();
	}

	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	public String getLabelText(){
		return "Extended Metaclasses";
	}
	protected DefaultFeatureInfo buildTableInput(){
		EObject featureOwner = getFeatureOwner(getSelectedObject());
		return new DefaultFeatureInfo(featureOwner, null){
			@Override
			public EClassifier getType(){
				return UMLPackage.eINSTANCE.getClass_();
			}
			@Override
			public List<?> getCurrentValues(){
				return getStereotype().getExtendedMetaclasses();
			}
		};
	}
}