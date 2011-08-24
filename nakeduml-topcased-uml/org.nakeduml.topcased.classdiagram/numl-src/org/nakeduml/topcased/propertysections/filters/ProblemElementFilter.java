package org.nakeduml.topcased.propertysections.filters;

import javax.smartcardio.ATR;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Element;
import org.nakeduml.topcased.uml.editor.NakedUmlErrorMarker;

public class ProblemElementFilter extends AbstractFilter{
	@Override
	public boolean select(Element e){
		if(e.eResource() != null){
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(e.eResource().getURI().toPlatformString(false)));
			try{
				for(IMarker m:file.findMarkers(EValidator.MARKER, true, 0)){
					String attribute = (String) m.getAttribute(EValidator.URI_ATTRIBUTE);
					URI createURI = URI.createURI(attribute, false);
					
					EObject eo = e.eResource().getEObject(createURI.fragment());
					while(eo != null){
						if(eo == e){
							return true;
						}else{
							eo = eo.eContainer();
						}
					}
				}
			}catch(CoreException e1){
				e1.printStackTrace();
			}
		}
		return false;
	}
}
