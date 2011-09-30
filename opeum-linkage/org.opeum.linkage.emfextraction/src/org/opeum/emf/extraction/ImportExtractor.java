package org.opeum.emf.extraction;

import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ElementImport;
import org.eclipse.uml2.uml.PackageImport;
import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.metamodel.core.INakedElement;
import org.opeum.metamodel.core.internal.NakedElementImpl;
import org.opeum.metamodel.core.internal.NakedImportedElementImpl;

@StepDependency(phase = EmfExtractionPhase.class,requires = {
	NameSpaceExtractor.class
},after = {
	NameSpaceExtractor.class
})
public class ImportExtractor extends AbstractExtractorFromEmf{
	@Override
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		if(e instanceof ElementImport){
			ElementImport dependency = (ElementImport) e;
			if(this.nakedWorkspace.getModelElement(getId(dependency.getImportedElement())) == null){
				if(dependency.eResource().getURI().toString().contains("UML_METAMODELS")){
					System.out.println("dependency.getImportedElement() not in nakedWorkspace: " + dependency.getImportedElement().getQualifiedName());
				}
			}else{
				return new NakedImportedElementImpl();
			}
		}else if(e instanceof PackageImport){
			PackageImport dependency = (PackageImport) e;
			if(dependency.getImportedPackage() != null){
				INakedElement referenced = this.nakedWorkspace.getModelElement(getId((dependency.getImportedPackage())));
				if(referenced == null){
					if(dependency.eResource().getURI().toString().contains("UML_METAMODELS")){
						System.out.println("dependency.getImportedPackage() not in nakedWorkspace: " + dependency.getImportedPackage().getQualifiedName());
					}
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
