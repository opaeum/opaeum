/*******************************************************************************
 * 
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
import org.nakeduml.uim.util.UimSwitch;
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
	private class GraphicUimSwitch extends UimSwitch{
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
		public GraphicUimSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimField(org.nakeduml.uim.UimField)
		 * @generated
		 */
		public Object caseUimField(org.nakeduml.uim.UimField object){
			if("default".equals(presentation)){
				return createGraphElementUimField(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseNavigationToEntity(org.nakeduml.uim.NavigationToEntity)
		 * @generated
		 */
		public Object caseNavigationToEntity(org.nakeduml.uim.NavigationToEntity object){
			if("default".equals(presentation)){
				return createGraphElementNavigationToEntity(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseNavigationToOperation(org.nakeduml.uim.NavigationToOperation)
		 * @generated
		 */
		public Object caseNavigationToOperation(org.nakeduml.uim.NavigationToOperation object){
			if("default".equals(presentation)){
				return createGraphElementNavigationToOperation(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseOperationAction(org.nakeduml.uim.OperationAction)
		 * @generated
		 */
		public Object caseOperationAction(org.nakeduml.uim.OperationAction object){
			if("default".equals(presentation)){
				return createGraphElementOperationAction(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseBuiltInAction(org.nakeduml.uim.BuiltInAction)
		 * @generated
		 */
		public Object caseBuiltInAction(org.nakeduml.uim.BuiltInAction object){
			if("default".equals(presentation)){
				return createGraphElementBuiltInAction(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseTransitionAction(org.nakeduml.uim.TransitionAction)
		 * @generated
		 */
		public Object caseTransitionAction(org.nakeduml.uim.TransitionAction object){
			if("default".equals(presentation)){
				return createGraphElementTransitionAction(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseFormPanel(org.nakeduml.uim.FormPanel)
		 * @generated
		 */
		public Object caseFormPanel(org.nakeduml.uim.FormPanel object){
			if("default".equals(presentation)){
				return createGraphElementFormPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimPanel(org.nakeduml.uim.UimPanel)
		 * @generated
		 */
		public Object caseUimPanel(org.nakeduml.uim.UimPanel object){
			if("default".equals(presentation)){
				return createGraphElementUimPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimTabPanel(org.nakeduml.uim.UimTabPanel)
		 * @generated
		 */
		public Object caseUimTabPanel(org.nakeduml.uim.UimTabPanel object){
			if("default".equals(presentation)){
				return createGraphElementUimTabPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimTab(org.nakeduml.uim.UimTab)
		 * @generated
		 */
		public Object caseUimTab(org.nakeduml.uim.UimTab object){
			if("default".equals(presentation)){
				return createGraphElementUimTab(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimDataTable(org.nakeduml.uim.UimDataTable)
		 * @generated
		 */
		public Object caseUimDataTable(org.nakeduml.uim.UimDataTable object){
			if("default".equals(presentation)){
				return createGraphElementUimDataTable(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimDataColumn(org.nakeduml.uim.UimDataColumn)
		 * @generated
		 */
		public Object caseUimDataColumn(org.nakeduml.uim.UimDataColumn object){
			if("default".equals(presentation)){
				return createGraphElementUimDataColumn(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseDetailPanel(org.nakeduml.uim.DetailPanel)
		 * @generated
		 */
		public Object caseDetailPanel(org.nakeduml.uim.DetailPanel object){
			if("default".equals(presentation)){
				return createGraphElementDetailPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimXYLayout(org.nakeduml.uim.UimXYLayout)
		 * @generated
		 */
		public Object caseUimXYLayout(org.nakeduml.uim.UimXYLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimXYLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimGridLayout(org.nakeduml.uim.UimGridLayout)
		 * @generated
		 */
		public Object caseUimGridLayout(org.nakeduml.uim.UimGridLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimGridLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimToolbarLayout(org.nakeduml.uim.UimToolbarLayout)
		 * @generated
		 */
		public Object caseUimToolbarLayout(org.nakeduml.uim.UimToolbarLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimToolbarLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimBorderLayout(org.nakeduml.uim.UimBorderLayout)
		 * @generated
		 */
		public Object caseUimBorderLayout(org.nakeduml.uim.UimBorderLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimBorderLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimFullLayout(org.nakeduml.uim.UimFullLayout)
		 * @generated
		 */
		public Object caseUimFullLayout(org.nakeduml.uim.UimFullLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimFullLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
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
			graphElt = new GraphicUimSwitch(presentation).doSwitch(obj);
		}
		return (GraphElement) graphElt;
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimField(org.nakeduml.uim.UimField element,String presentation){
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
	protected GraphElement createGraphElementUimPanel(org.nakeduml.uim.UimPanel element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimTabPanel(org.nakeduml.uim.UimTabPanel element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimTab(org.nakeduml.uim.UimTab element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimDataTable(org.nakeduml.uim.UimDataTable element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimDataColumn(org.nakeduml.uim.UimDataColumn element,String presentation){
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
	protected GraphElement createGraphElementUimXYLayout(org.nakeduml.uim.UimXYLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimGridLayout(org.nakeduml.uim.UimGridLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimToolbarLayout(org.nakeduml.uim.UimToolbarLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimBorderLayout(org.nakeduml.uim.UimBorderLayout element,String presentation){
		// TODO this snippet of code should be customized if it is not well generated
		GraphNode nodeParent = createGraphNode(element, presentation);
		return nodeParent;
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimFullLayout(org.nakeduml.uim.UimFullLayout element,String presentation){
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
