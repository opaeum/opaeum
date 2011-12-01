package org.opaeum.linkage;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedNameSpace;
import org.opaeum.metamodel.core.INakedPrimitiveType;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedValueSpecification;
import org.opaeum.metamodel.core.internal.NakedImportedElementImpl;

@StepDependency(phase = LinkagePhase.class,requires = {
	MappedTypeLinker.class
},after = {
	MappedTypeLinker.class
})
public class ReferenceResolver extends AbstractModelElementLinker{
	@VisitBefore(matchSubclasses = true)
	public void linkReference(INakedValueSpecification nvs){
		if(nvs.isElementReference()){
			nvs.setValue(this.workspace.getModelElement(nvs.getValue()));
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void linkQualifiers(INakedProperty property){
		String[] qualifierNames = property.getQualifierNames();
		List<INakedProperty> qualifiers = new ArrayList<INakedProperty>();
		INakedClassifier baseType = property.getNakedBaseType();
		for(String q:qualifierNames){
			INakedProperty backingAttribute = baseType.findEffectiveAttribute(q);
			if(backingAttribute == null){
				getErrorMap().putError(property, CoreValidationRule.QUALIFIER_NEEDS_BACKING_ATTRIBUTE,
						"Qualifier '" + q + "' has no backing attribute on " + baseType.getName());
			}else{
				qualifiers.add(backingAttribute);
				if(!backingAttribute.getOwner().equals(property.getNakedBaseType())){
					getErrorMap().putError(property, CoreValidationRule.BACKING_ATTRIBUTE_NO_ON_TYPE,
							"The backing attribute for qualifier '" + q + "' does not belong to the type " + baseType.getName());
				}
			}
		}
		property.setQualifiers(qualifiers);
		Boolean isQualifier = null;
		Boolean isQualifierForComposite = null;
		INakedClassifier owner = property.getOwner();
		if(owner == null){
			if(!(property.getOwnerElement() instanceof INakedProperty)){
				getErrorMap().putError(property, CoreValidationRule.OWNER_FOR_PROPERTY, "Property  " + property.getName() + " has no known owner");
			}
		}else{
			List<? extends INakedProperty> peers = owner.getOwnedAttributes();
			for(int i = 0;i < peers.size();i++){
				INakedProperty peer = (INakedProperty) peers.get(i);
				if(peer.getOtherEnd() != null && peer.getOtherEnd().hasQualifier(property.getName())){
					isQualifier = Boolean.TRUE;
					property.getPropertiesQualified().add(peer.getOtherEnd());
					if(peer.getOtherEnd().isComposite()){
						isQualifierForComposite = Boolean.TRUE;
					}
					break;
				}
			}
		}
		if(isQualifier == null){
			isQualifier = Boolean.FALSE;
		}
		if(isQualifierForComposite == null){
			isQualifierForComposite = Boolean.FALSE;
		}
		property.setIsQualifier(isQualifier.booleanValue());
		property.setIsQualifierForComposite(isQualifierForComposite.booleanValue());
	}
	@VisitBefore
	public void applyImports(NakedImportedElementImpl ie){
		// TODO implement merges
		INakedNameSpace importingPackage = ie.getNameSpace();
		INakedNameSpace p = null;
		if(ie.getElement() instanceof INakedNameSpace){
			p = (INakedNameSpace) ie.getElement();
		}
		if(p != null){
			Iterator<IClassifier> importIter = p.getClassifiers().iterator();
			while(importIter.hasNext()){
				INakedClassifier c = (INakedClassifier) importIter.next();
				IClassifier cls = c;
				if(c instanceof INakedPrimitiveType){
					if(((INakedPrimitiveType) c).getOclType() != null){
						// get original OCL type
						cls = ((INakedPrimitiveType) c).getOclType();
					}else{
						// TODO create validation error
						// getErrorMap().putError(holder, rule, objects)
						cls = getBuiltInTypes().getOclLibrary().lookupStandardType(IOclLibrary.StringTypeName);
					}
				}
				boolean isImported=false;
				try{
					isImported=p.isImported(cls);
				}catch(ConcurrentModificationException e){
					// HACK!! intermittent exception. Should not happen as namespaces and their imports are done in a single thread
				}
				if(!isImported){
					NakedImportedElementImpl importedElement = new NakedImportedElementImpl();
					importedElement.initialize(p.getId() + c.getId(), c.getName(), false);
					importedElement.setOwnerElement(ie.getNameSpace());
					importedElement.setMappingInfo(ie.getMappingInfo());
					importedElement.setElement(cls);
					importingPackage.addOwnedElement(importedElement);
				}
			}
		}else{
			importingPackage.addOwnedElement(ie);
		}
	}
}
