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
import net.sf.nakeduml.javageneration.AbstractJavaTransformationStep;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJElement;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedInterface;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.textmetamodel.TextWorkspace;

@StepDependency(phase = AuditGenerationPhase.class, requires = {})
public class AuditImplementationStep extends AbstractJavaTransformationStep {
	protected OJPackage newRoot;

	public void initialize(OJPackage pac, NakedUmlConfig config, TextWorkspace textWorkspace, OJPackage newRoot) {
		super.initialize(pac, config, textWorkspace);
		this.newRoot=newRoot;
	}

	public static final class OJVisitor extends VisitorAdapter<OJElement, OJPackage> {
		private final HashSet<OJClass> persistentClasses;

		private OJVisitor(HashSet<OJClass> classes) {
			this.persistentClasses = classes;
		}

		@Override
		public Collection<? extends OJElement> getChildren(OJElement root) {
			if (root instanceof OJPackage) {
				Set<OJElement> results = new HashSet<OJElement>();
				OJPackage ojp = (OJPackage) root;
				results.addAll(ojp.getSubpackages());
				return results;
			}
			return Collections.emptySet();
		}

		@VisitBefore(matchSubclasses = true)
		public void visitPackage(OJPackage p) {
			for (OJClass c : p.getClasses()) {
				if ((c instanceof OJAnnotatedInterface && !((OJAnnotatedInterface) c).getPathName().getFirst().startsWith("util"))
						|| c instanceof OJAnnotatedClass && ((OJAnnotatedClass) c).hasAnnotation(new OJPathName("javax.persistence.Table"))) {
					persistentClasses.add(c);
				}
			}
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
		Set<OJClass> classes = getClassesRecursively(newRoot);
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
	
	private Set<OJClass> getClassesRecursively(OJPackage copy) {
		final HashSet<OJClass> classes = new HashSet<OJClass>();
		new OJVisitor(classes).startVisiting(copy);
		return classes;
	}
	
}
