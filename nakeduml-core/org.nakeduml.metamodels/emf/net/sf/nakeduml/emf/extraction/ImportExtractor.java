package net.sf.nakeduml.emf.extraction;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedImportedElementImpl;

import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.PackageImport;

@StepDependency(phase = EmfExtractionPhase.class,requires = {
		TransitionExtractor.class,ActivityEdgeExtractor.class
},after = {
		ActivityEdgeExtractor.class,TransitionExtractor.class
})
public class ImportExtractor extends AbstractExtractorFromEmf{
	@Override
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		if(e instanceof ElementImport){
			ElementImport dependency = (ElementImport) e;
			if(this.nakedWorkspace.getModelElement(getId(dependency.getImportedElement())) == null){
				System.out.println("dependency.getImportedElement() not in nakedWorkspace: " + dependency.getImportedElement().getQualifiedName());
			}else{
				return new NakedImportedElementImpl();
			}
		}else if(e instanceof PackageImport){
			PackageImport dependency = (PackageImport) e;
			if(dependency.getImportedPackage() != null){
				INakedElement referenced = this.nakedWorkspace.getModelElement(getId((dependency.getImportedPackage())));
				if(referenced == null){
					System.out.println("dependency.getImportedPackage() not in nakedWorkspace: " + dependency.getImportedPackage().getQualifiedName());
				}else{
					return new NakedImportedElementImpl();
				}
			}
		}
		return null;
	}
	@VisitBefore
	public void visitElementImport(ElementImport dependency,NakedImportedElementImpl importedElementImpl){
		INakedElement referenced = this.nakedWorkspace.getModelElement(getId(dependency.getImportedElement()));
		importedElementImpl.setElement(referenced);
		importedElementImpl.setName(referenced.getName());
	}
	@VisitBefore
	public void visitPackageImport(PackageImport dependency,NakedImportedElementImpl importedElementImpl){
		INakedElement referenced = this.nakedWorkspace.getModelElement(getId((dependency.getImportedPackage())));
		importedElementImpl.setElement(referenced);
		importedElementImpl.setName(referenced.getName());
	}
}
