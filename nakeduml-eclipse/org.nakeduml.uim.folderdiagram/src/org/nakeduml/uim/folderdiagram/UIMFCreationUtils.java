/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.folderdiagram;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.nakeduml.uim.util.UIMSwitch;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.AbstractCreationUtils;
import org.topcased.modeler.graphconf.DiagramGraphConf;

/**
 * This utility class allows to create a GraphElement associated with a Model Object
 *
 * @generated
 */
public class UIMFCreationUtils extends AbstractCreationUtils{
	/**
	 * Constructor
	 *
	 * @param diagramConf the Diagram Graphical Configuration
	 * @generated
	 */
	public UIMFCreationUtils(DiagramGraphConf diagramConf){
		super(diagramConf);
	}
	/**
	 * @generated
	 */
	private class GraphicUIMSwitch extends UIMSwitch{
		/**
		 * The presentation of the graphical element
		 *
		 * @generated
		 */
		private String presentation;
		/**
		 * Constructor
		 *
		 * @param presentation the presentation of the graphical element
		 * @generated
		 */
		public GraphicUIMSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#casePackageFolder(org.nakeduml.uim.PackageFolder)
		 * @generated
		 */
		public Object casePackageFolder(org.nakeduml.uim.PackageFolder object){
			if("default".equals(presentation)){
				return createGraphElementPackageFolder(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseEntityFolder(org.nakeduml.uim.EntityFolder)
		 * @generated
		 */
		public Object caseEntityFolder(org.nakeduml.uim.EntityFolder object){
			if("default".equals(presentation)){
				return createGraphElementEntityFolder(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseStateMachineFolder(org.nakeduml.uim.StateMachineFolder)
		 * @generated
		 */
		public Object caseStateMachineFolder(org.nakeduml.uim.StateMachineFolder object){
			if("default".equals(presentation)){
				return createGraphElementStateMachineFolder(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseActivityFolder(org.nakeduml.uim.ActivityFolder)
		 * @generated
		 */
		public Object caseActivityFolder(org.nakeduml.uim.ActivityFolder object){
			if("default".equals(presentation)){
				return createGraphElementActivityFolder(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseClassForm(org.nakeduml.uim.ClassForm)
		 * @generated
		 */
		public Object caseClassForm(org.nakeduml.uim.ClassForm object){
			if("default".equals(presentation)){
				return createGraphElementClassForm(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseStateForm(org.nakeduml.uim.StateForm)
		 * @generated
		 */
		public Object caseStateForm(org.nakeduml.uim.StateForm object){
			if("default".equals(presentation)){
				return createGraphElementStateForm(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseOperationInvocationForm(org.nakeduml.uim.OperationInvocationForm)
		 * @generated
		 */
		public Object caseOperationInvocationForm(org.nakeduml.uim.OperationInvocationForm object){
			if("default".equals(presentation)){
				return createGraphElementOperationInvocationForm(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseActionTaskForm(org.nakeduml.uim.ActionTaskForm)
		 * @generated
		 */
		public Object caseActionTaskForm(org.nakeduml.uim.ActionTaskForm object){
			if("default".equals(presentation)){
				return createGraphElementActionTaskForm(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseOperationTaskForm(org.nakeduml.uim.OperationTaskForm)
		 * @generated
		 */
		public Object caseOperationTaskForm(org.nakeduml.uim.OperationTaskForm object){
			if("default".equals(presentation)){
				return createGraphElementOperationTaskForm(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @see org.topcased.modeler.editor.ICreationUtils#createGraphElement(org.eclipse.emf.ecore.EObject, java.lang.String)
	 * @generated
	 */
	public GraphElement createGraphElement(EObject obj,String presentation){
		Object graphElt = null;
		if("http://nakeduml.org/uimetamodel/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicUIMSwitch(presentation).doSwitch(obj);
		}
		return (GraphElement) graphElt;
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementPackageFolder(org.nakeduml.uim.PackageFolder element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementEntityFolder(org.nakeduml.uim.EntityFolder element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementStateMachineFolder(org.nakeduml.uim.StateMachineFolder element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementActivityFolder(org.nakeduml.uim.ActivityFolder element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementClassForm(org.nakeduml.uim.ClassForm element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementStateForm(org.nakeduml.uim.StateForm element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementOperationInvocationForm(org.nakeduml.uim.OperationInvocationForm element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementActionTaskForm(org.nakeduml.uim.ActionTaskForm element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementOperationTaskForm(org.nakeduml.uim.OperationTaskForm element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * Create the ModelObject with its initial children
	 * 
	 * @param obj the model object
	 * @return the model object with its children
	 * @generated
	 */
	public EObject createModelObject(EObject obj){
		return obj;
	}
	/**
	 * Get the preference store associated with the current editor.
	 * 
	 * @return IPreferenceStore
	 * @generated
	 */
	private IPreferenceStore getPreferenceStore(){
		IEditorInput editorInput = UIMFPlugin.getActivePage().getActiveEditor().getEditorInput();
		if(editorInput instanceof IFileEditorInput){
			IProject project = ((IFileEditorInput) editorInput).getFile().getProject();
			Preferences root = Platform.getPreferencesService().getRootNode();
			try{
				if(root.node(ProjectScope.SCOPE).node(project.getName()).nodeExists(UIMFPlugin.getId())){
					return new ScopedPreferenceStore(new ProjectScope(project), UIMFPlugin.getId());
				}
			}catch(BackingStoreException e){
				e.printStackTrace();
			}
		}
		return UIMFPlugin.getDefault().getPreferenceStore();
	}
}
