package org.nakeduml.tinker.audit;

import org.eclipse.uml2.uml.INakedBehavioredClassifier;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedGeneralization;
import org.eclipse.uml2.uml.INakedInterface;
import org.eclipse.uml2.uml.INakedInterfaceRealization;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.util.OJUtil;

@StepDependency(phase = TinkerAuditPhase.class, requires = {
		TinkerAuditCreator.class, TinkerAuditAttributeImplementor.class }, after = { TinkerAuditCreator.class }, before = { TinkerAuditAttributeImplementor.class })
public class TinkerAuditSuperTypeGenerator extends
		AbstractAuditJavaProducingVisitor {
	@VisitBefore(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		if (c.getGeneralizations().size() == 1) {
			OJAnnotatedClass myClass = findAuditJavaClass(c);
			if (myClass != null) {
				for (INakedGeneralization g : c.getNakedGeneralizations()) {
					NakedClassifierMap map = new NakedClassifierMap(
							g.getGeneral());
					OJPathName javaTypePath = map.javaTypePath();
					OJPathName auditCopy = javaTypePath.getDeepCopy();
					auditCopy.replaceTail(auditCopy.getLast()
							+ TinkerAuditGenerationUtil.AUDIT);
					myClass.setSuperclass(auditCopy);
					myClass.addToImports(auditCopy);
					OJConstructor constructor = myClass.getDefaultConstructor();
					constructor.getBody().addToStatements("super()");
				}
			}
		} else if (c.getNakedGeneralizations().size() > 1) {
			// TODO implement as validation rule
			System.out.println(c + " has more than one generalization");
		}
		if (c instanceof INakedBehavioredClassifier) {
			for (INakedInterfaceRealization ir : ((INakedBehavioredClassifier) c)
					.getInterfaceRealizations()) {
				OJAnnotatedClass myClass = findAuditJavaClass(c);
				OJPathName classifierPathname = OJUtil.classifierPathname(ir
						.getContract());
				OJPathName auditCopy = classifierPathname.getDeepCopy();
				auditCopy.replaceTail(auditCopy.getLast()
						+ TinkerAuditGenerationUtil.AUDIT);
				myClass.addToImplementedInterfaces(auditCopy);
			}
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitInterface(INakedInterface c) {
		if (c.getGeneralizations().isEmpty())
			return;
		OJAnnotatedInterface myIntf = (OJAnnotatedInterface) findAuditJavaClass(c);
		if (myIntf != null) {
			for (INakedGeneralization g : c.getNakedGeneralizations()) {
				OJPathName pathname = OJUtil.classifierPathname(g.getGeneral());
				OJPathName auditCopy = pathname.getDeepCopy();
				auditCopy.replaceTail(auditCopy.getLast()
						+ TinkerAuditGenerationUtil.AUDIT);
				myIntf.getSuperInterfaces().add(auditCopy);
				myIntf.addToImports(auditCopy);
			}
		}
	}

}
