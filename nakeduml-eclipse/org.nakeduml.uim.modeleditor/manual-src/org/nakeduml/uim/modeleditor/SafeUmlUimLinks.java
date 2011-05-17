package org.nakeduml.uim.modeleditor;

import java.io.File;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.uml2.uml.UMLPlugin;
import org.nakeduml.uim.util.UmlUimLinks;
import org.topcased.modeler.uml.editor.UMLEditor;

public class SafeUmlUimLinks{
	public static UmlUimLinks getInstance(EObject e){
		UmlUimLinks instance = UmlUimLinks.getInstance(e);
		if(!instance.umlLoaded()){
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			URI dirUri = e.eResource().getURI().trimFileExtension().trimSegments(2);
			IFolder folder = root.getFolder(new Path(dirUri.toPlatformString(true)));
			if(folder.exists()){
				File dirFile = folder.getLocation().toFile();
				for(File file:dirFile.listFiles()){
					if(file.getName().endsWith(".umldi")){
						IEditorReference[] ers = UimPlugin.getActivePage().getEditorReferences();
						for(IEditorReference er:ers){
							if(file.getName().endsWith(er.getPartName())){
								UmlUimLinks.init(((UMLEditor) er.getEditor(false)).getResourceSet());
							}
						}
						// UMLEditor editorPart;
						// try{
						// editorPart = (UMLEditor) IDE.openEditor(UimPlugin.getActivePage(), folder.getFile(file.getName()), true);
						// editorPart.getResourceSet());
						// }catch(PartInitException e1){
						// // TODO Auto-generated catch block
						// e1.printStackTrace();
						// }
					}
				}
			}
		}
		return instance;
	}
}
