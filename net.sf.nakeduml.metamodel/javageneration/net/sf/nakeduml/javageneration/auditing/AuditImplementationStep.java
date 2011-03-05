package net.sf.nakeduml.javageneration.auditing;

import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

@StepDependency(phase = AuditGenerationPhase.class, requires = {})
public class AuditImplementationStep extends AbstractJavaTransformationStep {
	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		TimestampAdder timestampAdder = new TimestampAdder();
		timestampAdder.initialize(javaModel, config, textWorkspace, context);
		timestampAdder.startVisiting(workspace);
		// Make copies of the root packages just below the model package
		OJAnnotatedPackage newRoot = new OJAnnotatedPackage();
		Set<OJPackage> packages = this.javaModel.getSubpackages();
		for (OJPackage ojPackage : packages) {
			OJPackage deepCopy = ojPackage.getDeepCopy(null);
			deepCopy.setParent(newRoot);
		}
		// This next section must be here in the middle.
		// It adds in a makeAuditCopy method. This method must only be on the
		// original class
		AuditEntryMassageOriginalClasses anotherAuditEntryOriginalClassesGenerator = new AuditEntryMassageOriginalClasses();
		anotherAuditEntryOriginalClassesGenerator.initialize(this.javaModel, config, textWorkspace, context);
		anotherAuditEntryOriginalClassesGenerator.startVisiting(workspace);
		// Visit copy classes
		AuditEntryMassage aeg = new AuditEntryMassage();
		aeg.initialize(newRoot, config, textWorkspace, context);
		aeg.startVisiting(workspace);
		AuditFixAnnotations auditFixAnnotations = new AuditFixAnnotations();
		auditFixAnnotations.initialize(newRoot, config, textWorkspace, context);
		auditFixAnnotations.startVisiting(workspace);
		mergePackages(newRoot.getSubpackages());
	}

	private void mergePackages(Set<OJPackage> packages) {
		for (OJPackage pkg : packages) {
			Set<OJClass> auditClasses = new HashSet<OJClass>(pkg.getClasses());
			OJPackage newPkg=(OJPackage) javaModel.findPackage(pkg.getPathName());
			for (OJClass ojClass : auditClasses) {
				if (ojClass.getName().endsWith("_Audit")) {
					newPkg.addToClasses(ojClass);
				}
			}
			mergePackages(pkg.getSubpackages());
		}
	}
}
