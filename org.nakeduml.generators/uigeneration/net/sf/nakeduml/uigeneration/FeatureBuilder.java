package net.sf.nakeduml.uigeneration;

import static net.sf.nakeduml.uigeneration.StereotypeNames.HUMAN_NAME;
import static net.sf.nakeduml.uigeneration.StereotypeNames.OPERATION_SPECIFICATION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.PARAMETER_SPECIFICATION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.PROPERTY_SPECIFICATION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.SECURITY_ON_CREATE;
import static net.sf.nakeduml.uigeneration.StereotypeNames.SECURITY_ON_EDIT;
import static net.sf.nakeduml.uigeneration.StereotypeNames.SECURITY_ON_VIEW;
import static net.sf.nakeduml.uigeneration.StereotypeNames.getTag;

import java.util.List;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.domainmetamodel.DomainEntity;
import net.sf.nakeduml.domainmetamodel.DomainOperation;
import net.sf.nakeduml.domainmetamodel.DomainParameter;
import net.sf.nakeduml.domainmetamodel.DomainProperty;
import net.sf.nakeduml.domainmetamodel.ParameterDirection;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import nl.klasse.octopus.model.ParameterDirectionKind;

@StepDependency(phase = UserInteractionTransformationPhase.class, after = ClassifierBuilder.class, requires = ClassifierBuilder.class)
public class FeatureBuilder extends AbstractUserInteractionTransformationStep {
	@VisitBefore
	public void visitProperty(INakedEntity c) {
		if (hasUserInteractions(c)) {
			for (INakedProperty property : c.getEffectiveAttributes()) {
				DomainProperty dp = new DomainProperty();
				dp.setName(getDomainNameOf(property));
				dp.setType(findDomainClassifierFor(property.getNakedBaseType()));
				DomainClassifier domainClassifier = super.findDomainClassifierFor(c);
				// TODO merge and intersect the securityOnEdit from the baseType

				dp.setAdditionalSecurityOnEdit(super.createSecureUserAction(property, SECURITY_ON_EDIT));
				dp.setAdditionalSecurityOnView(super.createSecureUserAction(property, SECURITY_ON_VIEW));
				dp.setAdditionalSecurityOnAdd(super.createSecureUserAction(property, SECURITY_ON_CREATE));

				if (!property.isComposite() && property.getOtherEnd() == null && property.getStereotype(SECURITY_ON_EDIT) == null) {
					// Normal properties edit
					dp.setAdditionalSecurityOnEdit(super.createSecureUserAction(c, SECURITY_ON_EDIT));
					if (property.getStereotype(SECURITY_ON_VIEW) == null) {
						dp.setAdditionalSecurityOnView(super.createSecureUserAction(c, SECURITY_ON_VIEW));
					}
				} else if (!property.isComposite() && property.getOtherEnd() == null && property.getStereotype(SECURITY_ON_VIEW) == null) {
					// Normal properties view
					dp.setAdditionalSecurityOnView(super.createSecureUserAction(c, SECURITY_ON_VIEW));
				}

				if (!property.isComposite() && property.getOtherEnd() != null && property.getNakedMultiplicity().getUpper() == 1 && property.isRequired()
						&& property.getStereotype(SECURITY_ON_EDIT) == null) {

					dp.setAdditionalSecurityOnEdit(super.createSecureUserAction(c, SECURITY_ON_EDIT));
					if (property.getStereotype(SECURITY_ON_VIEW) == null) {
						dp.setAdditionalSecurityOnView(super.createSecureUserAction(c, SECURITY_ON_VIEW));
					}					
				} else if (!property.isComposite() && property.getOtherEnd() != null && property.getNakedMultiplicity().getUpper() == 1 && property.isRequired()
						&& property.getStereotype(SECURITY_ON_VIEW) == null) {
					dp.setAdditionalSecurityOnView(super.createSecureUserAction(c, SECURITY_ON_VIEW));
				}

				domainClassifier.addToProperty(dp);
				dp.setComposite(property.isComposite());
				String humanName = getTag(property, PROPERTY_SPECIFICATION, HUMAN_NAME);
				if (humanName == null) {
					humanName = property.getMappingInfo().getHumanName().toString();
				}
				dp.setHumanName(humanName);
				dp.setLowerLimit(property.getNakedMultiplicity().getLower());
				List<INakedProperty> qualifiers = property.getQualifiers();
				if (qualifiers.isEmpty() || property.getNakedMultiplicity().getUpper() > 1) {
					dp.setUpperLimit(property.getNakedMultiplicity().getUpper());
				} else if (qualifiers.size() == 1 && qualifiers.get(0).getNakedBaseType() instanceof INakedEnumeration) {
					INakedEnumeration en = (INakedEnumeration) qualifiers.get(0).getNakedBaseType();
					dp.setUpperLimit(en.getLiterals().size());
				} else {
					dp.setUpperLimit(Integer.MAX_VALUE);
				}
			}
		}
	}

	@VisitBefore
	public void visitOperation(INakedOperation operation) {
		if (hasUserInteractions(operation.getOwner())) {
			DomainEntity entity = (DomainEntity) super.findDomainClassifierFor(operation.getOwner());
			DomainOperation domainOperation = new DomainOperation();
			domainOperation.setName(getDomainNameOf(operation));
			domainOperation.setQuery(operation.isQuery());
			String humanName = getTag(operation, OPERATION_SPECIFICATION, HUMAN_NAME);
			if (humanName == null) {
				humanName = operation.getMappingInfo().getHumanName().toString();
			}
			domainOperation.setHumanName(humanName);
			entity.addToOperation(domainOperation);
			for (INakedParameter p : operation.getOwnedParameters()) {
				DomainParameter dp = buildParameter(p);
				domainOperation.addToParameter(dp);
				dp.setLowerLimit(p.getNakedMultiplicity().getLower());
				dp.setUpperLimit(p.getNakedMultiplicity().getUpper());
			}
			domainOperation.setAdditionalSecurityOnInvoke(super.createSecureUserAction(operation, SECURITY_ON_VIEW));
		}
	}

	private DomainParameter buildParameter(INakedParameter np) {
		DomainParameter dp = new DomainParameter();
		dp.setName(getDomainNameOf(np));
		dp.setType(findDomainClassifierFor(np.getNakedBaseType()));
		dp.setDirection(resolveDirection(np.getDirection(), np.isReturn()));
		String humanName = getTag(np, PARAMETER_SPECIFICATION, HUMAN_NAME);
		if (humanName == null) {
			humanName = np.getMappingInfo().getHumanName().toString();
		}
		dp.setHumanName(humanName);
		return dp;
	}

	private ParameterDirection resolveDirection(ParameterDirectionKind direction, boolean isReturn) {
		if (isReturn) {
			return ParameterDirection.RETURN;
		}
		if (direction.equals(ParameterDirectionKind.IN)) {
			return ParameterDirection.IN;
		}
		if (direction.equals(ParameterDirectionKind.INOUT)) {
			return ParameterDirection.INOUT;
		}
		if (direction.equals(ParameterDirectionKind.OUT)) {
			return ParameterDirection.OUT;
		}
		return null;
	}
}
