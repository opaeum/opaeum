package net.sf.nakeduml.linkage;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.NakedPropertyImpl;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.validation.CoreValidationRule;

@StepDependency(phase = LinkagePhase.class, requires = {TypeResolver.class}, after = {TypeResolver.class})
public class InverseCalculator extends AbstractModelElementLinker {
	@VisitAfter(matchSubclasses = true)
	public void calculateInverse(INakedProperty ae) {
		//TODO remove dependency on NakedStructuralFeatureMap here
		NakedStructuralFeatureMap map =new NakedStructuralFeatureMap(ae);
		Boolean inverse = false;
		if (ae.getOtherEnd() == null) {
			inverse = false;
		} else {
			if (map.isManyToOne()) {
				inverse = !ae.isNavigable();
			} else if (map.isOneToMany()) {
				inverse = ae.getOtherEnd().isNavigable();
			} else if (map.isOneToOne()) {
				if (ae.getNakedBaseType() instanceof INakedEntity || ae.getNakedBaseType() instanceof INakedInterface) {
						inverse = true;
						if (!ae.isNavigable() && ae.getOtherEnd().isNavigable()) {
					} else if (ae.isNavigable() && !ae.getOtherEnd().isNavigable()) {
						inverse = false;
					} else if (ae.getOtherEnd().isComposite()) {
						inverse = false;
					} else if (ae.isComposite()) {
						inverse = true;
					} else if (!ae.isRequired() && ae.getOtherEnd().isRequired()) {
						inverse = true;
					} else if (ae.isRequired() && !ae.getOtherEnd().isRequired()) {
						inverse = false;
					} else if (ae.hasStereotype("inverse")) {
						inverse = true;
					} else if (ae.getOtherEnd().hasStereotype("inverse")) {
						inverse = false;
					} else if (ae.hasTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE)) {
						inverse = ae.getTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE);
					} else if (ae.getOtherEnd().hasTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE)) {
						inverse = ae.getTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE);
						inverse=!inverse;
					} else {
						getErrorMap().putError(ae, CoreValidationRule.INVERSE, "Could not determine if " + ae.getPathName() + "::" + " is inverse");
						inverse = ae.getAssociation().getEnd2().equals(ae);
					}
				} else {
					inverse = false;
				}
			} else if (map.isManyToMany()) {
				if (ae.getNakedBaseType() instanceof INakedEntity || ae.getNakedBaseType() instanceof INakedInterface) {
					if (!ae.isNavigable() && ae.getOtherEnd().isNavigable()) {
						inverse = true;
					} else if (ae.isNavigable() && !ae.getOtherEnd().isNavigable()) {
						inverse = false;
					} else if (!ae.isRequired() && ae.getOtherEnd().isRequired()) {
						inverse = true;
					} else if (ae.isRequired() && !ae.getOtherEnd().isRequired()) {
						inverse = false;
					} else if (ae.hasStereotype("inverse")) {
						inverse = true;
					} else if (ae.getOtherEnd().hasStereotype("inverse")) {
						inverse = false;
					} else if (ae.hasTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE)) {
						inverse = ae.getTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE);
					} else if (ae.getOtherEnd().hasTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE)) {
						inverse = ae.getTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE);
						inverse=!inverse;
					} else {
						getErrorMap().putError(ae, CoreValidationRule.INVERSE, "Could not determine if " + ae.getPathName() + "::" + " is inverse");
						inverse = ae.getAssociation().getEnd2().equals(ae);
					}
				} else {
					inverse = false;
				}
			}
		}
		ae.setInverse(inverse);
	}
}
