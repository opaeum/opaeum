package net.sf.nakeduml.uigeneration;

import static net.sf.nakeduml.uigeneration.StereotypeNames.CLASSIFIER_SPECIFICATION;
import static net.sf.nakeduml.uigeneration.StereotypeNames.HUMAN_NAME;
import static net.sf.nakeduml.uigeneration.StereotypeNames.SECURITY_ON_CREATE;
import static net.sf.nakeduml.uigeneration.StereotypeNames.SECURITY_ON_DELETE;
import static net.sf.nakeduml.uigeneration.StereotypeNames.SECURITY_ON_EDIT;
import static net.sf.nakeduml.uigeneration.StereotypeNames.SECURITY_ON_VIEW;
import static net.sf.nakeduml.uigeneration.StereotypeNames.getTag;
import net.sf.nakeduml.domainmetamodel.ClassifierKind;
import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.domainmetamodel.DomainEntity;
import net.sf.nakeduml.domainmetamodel.DomainInterface;
import net.sf.nakeduml.domainmetamodel.DomainPackage;
import net.sf.nakeduml.domainmetamodel.NodeDomainPackage;
import net.sf.nakeduml.domainmetamodel.RootDomainPackage;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedPowerType;
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.INakedValueType;
import net.sf.nakeduml.validation.namegeneration.HumanNameGenerator;

@StepDependency(phase = UserInteractionTransformationPhase.class, requires = HumanNameGenerator.class)
public class ClassifierBuilder extends AbstractUserInteractionTransformationStep {
	@VisitBefore
	public void buildEntity(INakedEntity c) {
		DomainClassifier dc = new DomainEntity();
		dc.setClassifierKind(ClassifierKind.ENTITY);
		populateClassifier(c, dc);
	}

	@VisitBefore
	public void buildInterface(INakedInterface c) {
		DomainInterface dc = new DomainInterface();
		dc.setClassifierKind(ClassifierKind.INTERFACE);
		populateClassifier(c, dc);
	}

	@VisitBefore(match = { INakedValueType.class, INakedStructuredDataType.class, INakedEnumeration.class, INakedPrimitiveType.class,
			INakedPowerType.class })
	public void visitOtherClassifier(INakedClassifier c) {
		ClassifierKind supportedClassifierKind = resolveClassifierKind(c);
		DomainClassifier dc = new DomainClassifier();
		dc.setClassifierKind(supportedClassifierKind);
		populateClassifier(c, dc);
	}

	private void populateClassifier(INakedClassifier c, DomainClassifier dc) {
		dc.setDomainPackage(findDomainPackageFor(c.getNameSpace()));
		dc.setName(getDomainNameOf(c));
		String humanName = getTag(c, CLASSIFIER_SPECIFICATION, HUMAN_NAME);
		if (humanName == null) {
			humanName = c.getMappingInfo().getHumanName().toString();
		}
		dc.setHumanName(humanName);
		NakedClassifierMap map = new NakedClassifierMap(c);
		dc.setQualifiedImplementationType(map.javaTypePath().toJavaString());
		dc.setSecurityOnAdd(super.createSecureUserAction(c, SECURITY_ON_CREATE));
		dc.setSecurityOnDelete(super.createSecureUserAction(c, SECURITY_ON_DELETE));
		dc.setSecurityOnEdit(super.createSecureUserAction(c, SECURITY_ON_EDIT));
		dc.setSecurityOnView(super.createSecureUserAction(c, SECURITY_ON_VIEW));
		// TODO consolidate security - edit requires view, etc.
	}

	private ClassifierKind resolveClassifierKind(INakedClassifier c) {
		if (c instanceof INakedEnumeration) {
			return ClassifierKind.ENUMERATION;
		}
		if (c instanceof INakedSimpleType) {
			return ClassifierKind.SIMPLETYPE;
		}
		if (c instanceof INakedInterface) {
			return ClassifierKind.INTERFACE;
		}
		if (c instanceof INakedStructuredDataType) {
			return ClassifierKind.STRUCTUREDDATATYPE;
		}
		return ClassifierKind.ENTITY;
	}

	@VisitBefore(matchSubclasses = true)
	public void visitPackage(INakedPackage p) {
		if (p.getParent() == null) {
			RootDomainPackage ownedElement = new RootDomainPackage();
			ownedElement.setName(getDomainNameOfPackage(p));
			uiModel.addToDomainPackage(ownedElement);
		} else {
			NodeDomainPackage ownedElement = new NodeDomainPackage();
			DomainPackage parent = findDomainPackageFor(p.getParent());
			parent.addToChildPackage(ownedElement);
			ownedElement.setName(getDomainNameOfPackage(p));
		}
	}
}
