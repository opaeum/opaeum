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
import org.nakeduml.uim.UimPackage;
import org.nakeduml.uim.action.ActionPackage;
import org.nakeduml.uim.action.util.ActionSwitch;
import org.nakeduml.uim.binding.util.BindingSwitch;
import org.nakeduml.uim.control.util.ControlSwitch;
import org.nakeduml.uim.folder.util.FolderSwitch;
import org.nakeduml.uim.form.FormPackage;
import org.nakeduml.uim.form.util.FormSwitch;
import org.nakeduml.uim.layout.util.LayoutSwitch;
import org.nakeduml.uim.security.util.SecuritySwitch;
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
		 * @see org.nakeduml.uim.util.UimSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicLayoutSwitch extends LayoutSwitch{
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
		public GraphicLayoutSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.nakeduml.uim.layout.util.LayoutSwitch#caseUimXYLayout(org.nakeduml.uim.layout.UimXYLayout)
		 * @generated
		 */
		public Object caseUimXYLayout(org.nakeduml.uim.layout.UimXYLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimXYLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.layout.util.LayoutSwitch#caseUimGridLayout(org.nakeduml.uim.layout.UimGridLayout)
		 * @generated
		 */
		public Object caseUimGridLayout(org.nakeduml.uim.layout.UimGridLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimGridLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.layout.util.LayoutSwitch#caseUimToolbarLayout(org.nakeduml.uim.layout.UimToolbarLayout)
		 * @generated
		 */
		public Object caseUimToolbarLayout(org.nakeduml.uim.layout.UimToolbarLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimToolbarLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.layout.util.LayoutSwitch#caseUimBorderLayout(org.nakeduml.uim.layout.UimBorderLayout)
		 * @generated
		 */
		public Object caseUimBorderLayout(org.nakeduml.uim.layout.UimBorderLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimBorderLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.layout.util.LayoutSwitch#caseUimFullLayout(org.nakeduml.uim.layout.UimFullLayout)
		 * @generated
		 */
		public Object caseUimFullLayout(org.nakeduml.uim.layout.UimFullLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimFullLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.layout.util.LayoutSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicControlSwitch extends ControlSwitch{
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
		public GraphicControlSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.nakeduml.uim.control.util.ControlSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicFolderSwitch extends FolderSwitch{
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
		public GraphicFolderSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.nakeduml.uim.folder.util.FolderSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicFormSwitch extends FormSwitch{
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
		public GraphicFormSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.nakeduml.uim.form.util.FormSwitch#caseFormPanel(org.nakeduml.uim.form.FormPanel)
		 * @generated
		 */
		public Object caseFormPanel(org.nakeduml.uim.form.FormPanel object){
			if("default".equals(presentation)){
				return createGraphElementFormPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.form.util.FormSwitch#caseDetailPanel(org.nakeduml.uim.form.DetailPanel)
		 * @generated
		 */
		public Object caseDetailPanel(org.nakeduml.uim.form.DetailPanel object){
			if("default".equals(presentation)){
				return createGraphElementDetailPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.form.util.FormSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicBindingSwitch extends BindingSwitch{
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
		public GraphicBindingSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.nakeduml.uim.binding.util.BindingSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicSecuritySwitch extends SecuritySwitch{
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
		public GraphicSecuritySwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.nakeduml.uim.security.util.SecuritySwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicActionSwitch extends ActionSwitch{
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
		public GraphicActionSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.nakeduml.uim.action.util.ActionSwitch#caseNavigationToEntity(org.nakeduml.uim.action.NavigationToEntity)
		 * @generated
		 */
		public Object caseNavigationToEntity(org.nakeduml.uim.action.NavigationToEntity object){
			if("default".equals(presentation)){
				return createGraphElementNavigationToEntity(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.action.util.ActionSwitch#caseNavigationToOperation(org.nakeduml.uim.action.NavigationToOperation)
		 * @generated
		 */
		public Object caseNavigationToOperation(org.nakeduml.uim.action.NavigationToOperation object){
			if("default".equals(presentation)){
				return createGraphElementNavigationToOperation(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.action.util.ActionSwitch#caseOperationAction(org.nakeduml.uim.action.OperationAction)
		 * @generated
		 */
		public Object caseOperationAction(org.nakeduml.uim.action.OperationAction object){
			if("default".equals(presentation)){
				return createGraphElementOperationAction(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.action.util.ActionSwitch#caseBuiltInAction(org.nakeduml.uim.action.BuiltInAction)
		 * @generated
		 */
		public Object caseBuiltInAction(org.nakeduml.uim.action.BuiltInAction object){
			if("default".equals(presentation)){
				return createGraphElementBuiltInAction(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.action.util.ActionSwitch#caseTransitionAction(org.nakeduml.uim.action.TransitionAction)
		 * @generated
		 */
		public Object caseTransitionAction(org.nakeduml.uim.action.TransitionAction object){
			if("default".equals(presentation)){
				return createGraphElementTransitionAction(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.nakeduml.uim.action.util.ActionSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
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
		if("http://nakeduml.org/uimetamodel/layout/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicLayoutSwitch(presentation).doSwitch(obj);
		}
		if("http://nakeduml.org/uimetamodel/control/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicControlSwitch(presentation).doSwitch(obj);
		}
		if("http://nakeduml.org/uimetamodel/folder/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicFolderSwitch(presentation).doSwitch(obj);
		}
		if("http://nakeduml.org/uimetamodel/form/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicFormSwitch(presentation).doSwitch(obj);
		}
		if("http://nakeduml.org/uimetamodel/binding/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicBindingSwitch(presentation).doSwitch(obj);
		}
		if("http://nakeduml.org/uimetamodel/security/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicSecuritySwitch(presentation).doSwitch(obj);
		}
		if("http://nakeduml.org/uimetamodel/action/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicActionSwitch(presentation).doSwitch(obj);
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
	protected GraphElement createGraphElementNavigationToEntity(org.nakeduml.uim.action.NavigationToEntity element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementNavigationToOperation(org.nakeduml.uim.action.NavigationToOperation element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementOperationAction(org.nakeduml.uim.action.OperationAction element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementBuiltInAction(org.nakeduml.uim.action.BuiltInAction element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementTransitionAction(org.nakeduml.uim.action.TransitionAction element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementFormPanel(org.nakeduml.uim.form.FormPanel element,String presentation){
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
	protected GraphElement createGraphElementDetailPanel(org.nakeduml.uim.form.DetailPanel element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimXYLayout(org.nakeduml.uim.layout.UimXYLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimGridLayout(org.nakeduml.uim.layout.UimGridLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimToolbarLayout(org.nakeduml.uim.layout.UimToolbarLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimBorderLayout(org.nakeduml.uim.layout.UimBorderLayout element,String presentation){
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
	protected GraphElement createGraphElementUimFullLayout(org.nakeduml.uim.layout.UimFullLayout element,String presentation){
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
