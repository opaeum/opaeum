package org.opaeum.linkage;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedPropertyImpl;
import org.opaeum.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = LinkagePhase.class, requires = {TypeResolver.class}, after = {TypeResolver.class})
public class InverseCalculator extends AbstractModelElementLinker {
	@SuppressWarnings("deprecation")
	@VisitAfter(matchSubclasses = true)
	public void calculateInverse(INakedProperty p) {
		//TODO remove dependency on NakedStructuralFeatureMap here
		Boolean inverse = false;
		if (p.getOtherEnd() == null || !p.getOtherEnd().isNavigable()) {
			inverse = false;
		} else {
			if (PropertyUtil.isManyToOne(p)) {
				inverse = !p.isNavigable();
			} else if (PropertyUtil.isOneToMany(p)) {
				inverse = p.getOtherEnd().isNavigable();
			} else if (PropertyUtil.isOneToOne(p)) {
				if (p.getNakedBaseType() instanceof INakedEntity || p.getNakedBaseType() instanceof INakedInterface) {
						inverse = true;
						if (!p.isNavigable() && p.getOtherEnd().isNavigable()) {
					} else if (p.isNavigable() && !p.getOtherEnd().isNavigable()) {
						inverse = false;
					} else if (p.getOtherEnd().isComposite()) {
						inverse = false;
					} else if (p.isComposite()) {
						inverse = true;
					} else if (!p.isRequired() && p.getOtherEnd().isRequired()) {
						inverse = true;
					} else if (p.isRequired() && !p.getOtherEnd().isRequired()) {
						inverse = false;
					} else if (p.hasStereotype("inverse")) {
						inverse = true;
					} else if (p.getOtherEnd().hasStereotype("inverse")) {
						inverse = false;
					} else if (p.hasTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE)) {
						inverse = p.getTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE);
					} else if (p.getOtherEnd().hasTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE)) {
						inverse = p.getTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE);
						inverse=!inverse;
					} else {
						getErrorMap().putError(p, CoreValidationRule.INVERSE, "Could not determine if " + p.getPathName() + "::" + " is inverse");
						inverse = p.getAssociation().getEnd2().equals(p);
					}
				} else {
					inverse = false;
				}
			} else if (PropertyUtil.isManyToMany(p)) {
				if (p.getNakedBaseType() instanceof INakedEntity || p.getNakedBaseType() instanceof INakedInterface) {
					if (!p.isNavigable() && p.getOtherEnd().isNavigable()) {
						inverse = true;
					} else if (p.isNavigable() && !p.getOtherEnd().isNavigable()) {
						inverse = false;
					} else if (!p.isRequired() && p.getOtherEnd().isRequired()) {
						inverse = true;
					} else if (p.isRequired() && !p.getOtherEnd().isRequired()) {
						inverse = false;
					} else if (p.hasStereotype("inverse")) {
						inverse = true;
					} else if (p.getOtherEnd().hasStereotype("inverse")) {
						inverse = false;
					} else if (p.hasTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE)) {
						inverse = p.getTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE);
					} else if (p.getOtherEnd().hasTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE)) {
						inverse = p.getTaggedValue(StereotypeNames.PROPERTY, NakedPropertyImpl.INVERSE);
						inverse=!inverse;
					} else {
//						getErrorMap().putError(p, CoreValidationRule.INVERSE, "Could not determine if " + p.getPathName() + "::" + " is inverse");
						inverse = p.getAssociation().getEnd2().equals(p);
					}
				} else {
					inverse = false;
				}
			}
		}
		p.setInverse(inverse);
	}
}
