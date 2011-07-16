package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.internal.NakedImportedElementImpl;

import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.PackageImport;

@StepDependency(phase = EmfExtractionPhase.class, requires = { TransitionExtractor.class, ActivityEdgeExtractor.class }, after = {
		ActivityEdgeExtractor.class, TransitionExtractor.class })
public class ImportExtractor extends AbstractExtractorFromEmf {
	@VisitBefore
	public void visitElementImport(ElementImport dependency) {
		INakedElement referenced = this.nakedWorkspace.getModelElement(getId((dependency.getImportedElement())));
		if (referenced == null) {
			System.out.println("dependency.getImportedElement() not in nakedWorkspace: " + dependency.getImportedElement().getQualifiedName());
		} else {
			NakedImportedElementImpl importedElementImpl = new NakedImportedElementImpl();
			importedElementImpl.setElement(referenced);
			importedElementImpl.setName(referenced.getName());
			initialize(importedElementImpl, dependency, dependency.getImportingNamespace());
		}
	}

	@VisitBefore
	public void visitPackageImport(PackageImport dependency) {
		if (dependency.getImportedPackage()!=null) {
			INakedElement referenced = this.nakedWorkspace.getModelElement(getId((dependency.getImportedPackage())));
			if (referenced == null) {
				System.out.println("dependency.getImportedPackage() not in nakedWorkspace: "
						+ dependency.getImportedPackage().getQualifiedName());
			} else {
				NakedImportedElementImpl importedElementImpl = new NakedImportedElementImpl();
				importedElementImpl.setElement(referenced);
				importedElementImpl.setName(referenced.getName());
				initialize(importedElementImpl, dependency, dependency.getImportingNamespace());
			}
		}
	}
}
