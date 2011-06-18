package org.nakeduml.topcased.uml.editor;



import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;

public class UMLEditor extends org.topcased.modeler.uml.editor.UMLEditor{
	@Override
	protected EObject openFile(IFile file,boolean resolve){
		EObject openFile = super.openFile(file, resolve);
		getResourceSet().eAdapters().add(new NakedUmlContentAdaptor());
		return openFile;
	}
}
