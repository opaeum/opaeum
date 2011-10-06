package org.opeum.linkage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.model.IClassifier;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedNameSpace;
import org.opeum.metamodel.core.INakedPrimitiveType;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedValueSpecification;
import org.opeum.metamodel.core.internal.NakedImportedElementImpl;

@StepDependency(phase = LinkagePhase.class)
public class ReferenceResolver extends AbstractModelElementLinker {
	@VisitBefore(matchSubclasses = true)
	public void linkReference(INakedValueSpecification nvs) {
		if (nvs.isElementReference()) {
			nvs.setValue(this.workspace.getModelElement(nvs.getValue()));
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void linkQualifiers(INakedProperty property) {
		String[] qualifierNames = property.getQualifierNames();
		List<INakedProperty> qualifiers = new ArrayList<INakedProperty>();
		INakedClassifier baseType = property.getNakedBaseType();
		for (String q : qualifierNames) {
			INakedProperty backingAttribute = baseType.findEffectiveAttribute(q);
			if (backingAttribute == null) {
				getErrorMap().putError(property, CoreValidationRule.QUALIFIER_NEEDS_BACKING_ATTRIBUTE,
						"Qualifier '" + q + "' has no backing attribute on " + baseType.getName());
			} else {
				qualifiers.add(backingAttribute);
				if (!backingAttribute.getOwner().equals(property.getNakedBaseType())) {
					getErrorMap().putError(property, CoreValidationRule.BACKING_ATTRIBUTE_NO_ON_TYPE,
							"The backing attribute for qualifier '" + q + "' does not belong to the type " + baseType.getName());
				}
			}
		}
		property.setQualifiers(qualifiers);
		Boolean isQualifier = null;
		Boolean isQualifierForComposite = null;
		INakedClassifier owner = property.getOwner();
		if (owner == null) {
			if (!(property.getOwnerElement() instanceof INakedProperty)) {
				getErrorMap().putError(property, CoreValidationRule.OWNER_FOR_PROPERTY,
						"Property  " + property.getName() + " has no known owner");
			}
		} else {
			List<? extends INakedProperty> peers = owner.getOwnedAttributes();
			for (int i = 0; i < peers.size(); i++) {
				INakedProperty peer = (INakedProperty) peers.get(i);
				if (peer.getOtherEnd() != null && peer.getOtherEnd().hasQualifier(property.getName())) {
					isQualifier = Boolean.TRUE;
					if (peer.getOtherEnd().isComposite()) {
						isQualifierForComposite = Boolean.TRUE;
					}
					break;
				}
			}
		}
		if (isQualifier == null) {
			isQualifier = Boolean.FALSE;
		}
		if (isQualifierForComposite == null) {
			isQualifierForComposite = Boolean.FALSE;
		}
		property.setIsQualifier(isQualifier.booleanValue());
		property.setIsQualifierForComposite(isQualifierForComposite.booleanValue());
	}

	@VisitBefore
	public void applyImports(NakedImportedElementImpl ie) {
		// TODO implement merges
		INakedNameSpace importingPackage = ie.getNameSpace();
		INakedNameSpace p = null;
		if (ie.getElement() instanceof INakedNameSpace) {
			p = (INakedNameSpace) ie.getElement();
		}
		if (p != null) {
			Iterator<IClassifier> importIter = p.getClassifiers().iterator();
			while (importIter.hasNext()) {
				INakedClassifier c = (INakedClassifier) importIter.next();
				NakedImportedElementImpl importedElement = new NakedImportedElementImpl();
				importedElement.initialize(p.getId() + c.getId(), c.getName(),false);
				importedElement.setOwnerElement(ie.getNameSpace());
				importedElement.setMappingInfo(ie.getMappingInfo());
				if (c instanceof INakedPrimitiveType) {
					if (((INakedPrimitiveType) c).getOclType() != null) {
						// get original OCL type
						importedElement.setElement(((INakedPrimitiveType) c).getOclType());
						importingPackage.addOwnedElement(importedElement);
					}else{
						//don't import PrimitiveTypes without 
					}
				} else {
					importedElement.setElement(c);
					importingPackage.addOwnedElement(importedElement);
				}
			}
		} else {
			importingPackage.addOwnedElement(ie);
		}
	}
}
