package net.sf.nakeduml.javageneration.auditing;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.TransformationContext;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJElement;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedInterface;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.util.AbstractSignal;

@StepDependency(phase = AuditGenerationPhase.class, requires = {})
public class AuditImplementationStep extends AbstractJavaTransformationStep {
	protected OJPackage newRoot;

	public void initialize(OJPackage pac, NakedUmlConfig config, TextWorkspace textWorkspace, OJPackage newRoot) {
		super.initialize(pac, config, textWorkspace);
		this.newRoot = newRoot;
	}

	public static final class NakedElementVisitor extends AbstractJavaProducingVisitor {
		private final HashSet<OJPathName> persistentClasses;

		@Override
		public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root) {
			return root.getOwnedElements();
		}

		private NakedElementVisitor(HashSet<OJPathName> classes) {
			this.persistentClasses = classes;
		}

		@VisitBefore(matchSubclasses = true)
		public void visitClass(INakedClassifier p) {
			if ((isPersistent(p) || isPersistentInterface(p)) && !isInNakedUmlUtil(p)) {
				persistentClasses.add(new OJPathName(p.getMappingInfo().getQualifiedJavaName()));
			}
		}

		private boolean isPersistentInterface(INakedClassifier p) {
			return (p instanceof INakedInterface && p.getStereotype(StereotypeNames.HELPER) == null);
		}

		private boolean isInNakedUmlUtil(INakedClassifier p) {
			return (p.getMappingInfo().getQualifiedJavaName().startsWith(AbstractSignal.class.getPackage().getName()));
		}
	}

	@Override
	public void generate(INakedModelWorkspace workspace, TransformationContext context) {
		TimestampAdder timestampAdder = new TimestampAdder();
		timestampAdder.initialize(workspace, javaModel, config, textWorkspace);
		timestampAdder.startVisiting(workspace);
		
		// Make copies of the root packages just below the model package
		Set<OJPackage> packages = this.javaModel.getSubpackages();
		for (OJPackage ojPackage : packages) {
			OJPackage deepCopy = ojPackage.getDeepCopy(null);
			deepCopy.setParent(newRoot);
		}
		// This next section must be here in the middle.
		// It adds in a makeAuditCopy method. This method must only be on the
		// original class
		AuditEntryMassageOriginalClasses anotherAuditEntryOriginalClassesGenerator = new AuditEntryMassageOriginalClasses();
		anotherAuditEntryOriginalClassesGenerator.initialize(workspace, this.javaModel, config, textWorkspace);
		anotherAuditEntryOriginalClassesGenerator.startVisiting(workspace);
		// Visit copy classes
		Set<OJPathName> classes = getClassesRecursively(workspace);
		AuditEntryMassage aeg = new AuditEntryMassage();
		aeg.initialize(workspace, newRoot, config, textWorkspace, classes);
		aeg.startVisiting(workspace);
		AuditFixAnnotations auditFixAnnotations = new AuditFixAnnotations();
		auditFixAnnotations.initialize(workspace, newRoot, config, textWorkspace, classes);
		auditFixAnnotations.startVisiting(workspace);
		
		for (OJClass auditClass : classes) {
			OJPackage owner = this.javaModel.findPackage(auditClass.getMyPackage().getPathName());
			owner.addToClasses(auditClass);
		}
	}

	private Set<OJPathName> getClassesRecursively(INakedModelWorkspace workspace) {
		final HashSet<OJPathName> classes = new HashSet<OJPathName>();
		new NakedElementVisitor(classes).startVisiting(workspace);
		return classes;
	}
	
}
