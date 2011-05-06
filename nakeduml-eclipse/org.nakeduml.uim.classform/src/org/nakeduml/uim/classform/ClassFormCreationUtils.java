/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.classform;

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
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.editor.AbstractCreationUtils;
import org.topcased.modeler.graphconf.DiagramGraphConf;

/**
 * This utility class allows to create a GraphElement associated with a Model Object
 *
 * @generated
 */
public class ClassFormCreationUtils extends AbstractCreationUtils{
	/**
	 * Constructor
	 *
	 * @param diagramConf the Diagram Graphical Configuration
	 * @generated
	 */
	public ClassFormCreationUtils(DiagramGraphConf diagramConf){
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
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMField(org.nakeduml.uim.UIMField)
		 * @generated
		 */
		public Object caseUIMField(org.nakeduml.uim.UIMField object){
			if("default".equals(presentation)){
				return createGraphElementUIMField(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseNavigationToEntity(org.nakeduml.uim.NavigationToEntity)
		 * @generated
		 */
		public Object caseNavigationToEntity(org.nakeduml.uim.NavigationToEntity object){
			if("default".equals(presentation)){
				return createGraphElementNavigationToEntity(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseNavigationToOperation(org.nakeduml.uim.NavigationToOperation)
		 * @generated
		 */
		public Object caseNavigationToOperation(org.nakeduml.uim.NavigationToOperation object){
			if("default".equals(presentation)){
				return createGraphElementNavigationToOperation(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseOperationAction(org.nakeduml.uim.OperationAction)
		 * @generated
		 */
		public Object caseOperationAction(org.nakeduml.uim.OperationAction object){
			if("default".equals(presentation)){
				return createGraphElementOperationAction(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseBuiltInAction(org.nakeduml.uim.BuiltInAction)
		 * @generated
		 */
		public Object caseBuiltInAction(org.nakeduml.uim.BuiltInAction object){
			if("default".equals(presentation)){
				return createGraphElementBuiltInAction(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseTransitionAction(org.nakeduml.uim.TransitionAction)
		 * @generated
		 */
		public Object caseTransitionAction(org.nakeduml.uim.TransitionAction object){
			if("default".equals(presentation)){
				return createGraphElementTransitionAction(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseFormPanel(org.nakeduml.uim.FormPanel)
		 * @generated
		 */
		public Object caseFormPanel(org.nakeduml.uim.FormPanel object){
			if("default".equals(presentation)){
				return createGraphElementFormPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMPanel(org.nakeduml.uim.UIMPanel)
		 * @generated
		 */
		public Object caseUIMPanel(org.nakeduml.uim.UIMPanel object){
			if("default".equals(presentation)){
				return createGraphElementUIMPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMTabPanel(org.nakeduml.uim.UIMTabPanel)
		 * @generated
		 */
		public Object caseUIMTabPanel(org.nakeduml.uim.UIMTabPanel object){
			if("default".equals(presentation)){
				return createGraphElementUIMTabPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMTab(org.nakeduml.uim.UIMTab)
		 * @generated
		 */
		public Object caseUIMTab(org.nakeduml.uim.UIMTab object){
			if("default".equals(presentation)){
				return createGraphElementUIMTab(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMDataTable(org.nakeduml.uim.UIMDataTable)
		 * @generated
		 */
		public Object caseUIMDataTable(org.nakeduml.uim.UIMDataTable object){
			if("default".equals(presentation)){
				return createGraphElementUIMDataTable(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMDataColumn(org.nakeduml.uim.UIMDataColumn)
		 * @generated
		 */
		public Object caseUIMDataColumn(org.nakeduml.uim.UIMDataColumn object){
			if("default".equals(presentation)){
				return createGraphElementUIMDataColumn(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseDetailPanel(org.nakeduml.uim.DetailPanel)
		 * @generated
		 */
		public Object caseDetailPanel(org.nakeduml.uim.DetailPanel object){
			if("default".equals(presentation)){
				return createGraphElementDetailPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMXYLayout(org.nakeduml.uim.UIMXYLayout)
		 * @generated
		 */
		public Object caseUIMXYLayout(org.nakeduml.uim.UIMXYLayout object){
			if("default".equals(presentation)){
				return createGraphElementUIMXYLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMGridLayout(org.nakeduml.uim.UIMGridLayout)
		 * @generated
		 */
		public Object caseUIMGridLayout(org.nakeduml.uim.UIMGridLayout object){
			if("default".equals(presentation)){
				return createGraphElementUIMGridLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMToolbarLayout(org.nakeduml.uim.UIMToolbarLayout)
		 * @generated
		 */
		public Object caseUIMToolbarLayout(org.nakeduml.uim.UIMToolbarLayout object){
			if("default".equals(presentation)){
				return createGraphElementUIMToolbarLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMBorderLayout(org.nakeduml.uim.UIMBorderLayout)
		 * @generated
		 */
		public Object caseUIMBorderLayout(org.nakeduml.uim.UIMBorderLayout object){
			if("default".equals(presentation)){
				return createGraphElementUIMBorderLayout(object, presentation);
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
	protected GraphElement createGraphElementUIMField(org.nakeduml.uim.UIMField element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementNavigationToEntity(org.nakeduml.uim.NavigationToEntity element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementNavigationToOperation(org.nakeduml.uim.NavigationToOperation element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementOperationAction(org.nakeduml.uim.OperationAction element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementBuiltInAction(org.nakeduml.uim.BuiltInAction element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementTransitionAction(org.nakeduml.uim.TransitionAction element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementFormPanel(org.nakeduml.uim.FormPanel element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUIMPanel(org.nakeduml.uim.UIMPanel element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUIMTabPanel(org.nakeduml.uim.UIMTabPanel element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUIMTab(org.nakeduml.uim.UIMTab element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUIMDataTable(org.nakeduml.uim.UIMDataTable element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUIMDataColumn(org.nakeduml.uim.UIMDataColumn element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementDetailPanel(org.nakeduml.uim.DetailPanel element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUIMXYLayout(org.nakeduml.uim.UIMXYLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUIMGridLayout(org.nakeduml.uim.UIMGridLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUIMToolbarLayout(org.nakeduml.uim.UIMToolbarLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated NOT
	 */
	protected GraphElement createGraphElementUIMBorderLayout(org.nakeduml.uim.UIMBorderLayout element,String presentation){
		// TODO this snippet of code should be customized if it is not well generated
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
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
		IEditorInput editorInput = ClassFormPlugin.getActivePage().getActiveEditor().getEditorInput();
		if(editorInput instanceof IFileEditorInput){
			IProject project = ((IFileEditorInput) editorInput).getFile().getProject();
			Preferences root = Platform.getPreferencesService().getRootNode();
			try{
				if(root.node(ProjectScope.SCOPE).node(project.getName()).nodeExists(ClassFormPlugin.getId())){
					return new ScopedPreferenceStore(new ProjectScope(project), ClassFormPlugin.getId());
				}
			}catch(BackingStoreException e){
				e.printStackTrace();
			}
		}
		return ClassFormPlugin.getDefault().getPreferenceStore();
	}
}
