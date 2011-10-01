/*******************************************************************************
 * 
 ******************************************************************************/
package org.opeum.uim.classform;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.opeum.uim.action.util.ActionSwitch;
import org.opeum.uim.binding.util.BindingSwitch;
import org.opeum.uim.control.util.ControlSwitch;
import org.opeum.uim.folder.util.FolderSwitch;
import org.opeum.uim.form.util.FormSwitch;
import org.opeum.uim.layout.util.LayoutSwitch;
import org.opeum.uim.security.util.SecuritySwitch;
import org.opeum.uim.util.UimSwitch;
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
	private class GraphicUimSwitch extends UimSwitch<Object>{
		/**
		 * The presentation of the graphical originalElement
		 *
		 * @generated
		 */
		private String presentation;
		/**
		 * Constructor
		 *
		 * @param presentation the presentation of the graphical originalElement
		 * @generated
		 */
		public GraphicUimSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.opeum.uim.util.UimSwitch#caseUimField(org.opeum.uim.UimField)
		 * @generated
		 */
		public Object caseUimField(org.opeum.uim.UimField object){
			if("default".equals(presentation)){
				return createGraphElementUimField(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.util.UimSwitch#caseUimPanel(org.opeum.uim.UimPanel)
		 * @generated
		 */
		public Object caseUimPanel(org.opeum.uim.UimPanel object){
			if("default".equals(presentation)){
				return createGraphElementUimPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.util.UimSwitch#caseUimTabPanel(org.opeum.uim.UimTabPanel)
		 * @generated
		 */
		public Object caseUimTabPanel(org.opeum.uim.UimTabPanel object){
			if("default".equals(presentation)){
				return createGraphElementUimTabPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.util.UimSwitch#caseUimTab(org.opeum.uim.UimTab)
		 * @generated
		 */
		public Object caseUimTab(org.opeum.uim.UimTab object){
			if("default".equals(presentation)){
				return createGraphElementUimTab(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.util.UimSwitch#caseUimDataTable(org.opeum.uim.UimDataTable)
		 * @generated
		 */
		public Object caseUimDataTable(org.opeum.uim.UimDataTable object){
			if("default".equals(presentation)){
				return createGraphElementUimDataTable(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.util.UimSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicLayoutSwitch extends LayoutSwitch<Object>{
		/**
		 * The presentation of the graphical originalElement
		 *
		 * @generated
		 */
		private String presentation;
		/**
		 * Constructor
		 *
		 * @param presentation the presentation of the graphical originalElement
		 * @generated
		 */
		public GraphicLayoutSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.opeum.uim.layout.util.LayoutSwitch#caseUimXYLayout(org.opeum.uim.layout.UimXYLayout)
		 * @generated
		 */
		public Object caseUimXYLayout(org.opeum.uim.layout.UimXYLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimXYLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.layout.util.LayoutSwitch#caseUimGridLayout(org.opeum.uim.layout.UimGridLayout)
		 * @generated
		 */
		public Object caseUimGridLayout(org.opeum.uim.layout.UimGridLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimGridLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.layout.util.LayoutSwitch#caseUimToolbarLayout(org.opeum.uim.layout.UimToolbarLayout)
		 * @generated
		 */
		public Object caseUimToolbarLayout(org.opeum.uim.layout.UimToolbarLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimToolbarLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.layout.util.LayoutSwitch#caseUimBorderLayout(org.opeum.uim.layout.UimBorderLayout)
		 * @generated
		 */
		public Object caseUimBorderLayout(org.opeum.uim.layout.UimBorderLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimBorderLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.layout.util.LayoutSwitch#caseUimFullLayout(org.opeum.uim.layout.UimFullLayout)
		 * @generated
		 */
		public Object caseUimFullLayout(org.opeum.uim.layout.UimFullLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimFullLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.layout.util.LayoutSwitch#caseUimColumnLayout(org.opeum.uim.layout.UimColumnLayout)
		 * @generated
		 */
		public Object caseUimColumnLayout(org.opeum.uim.layout.UimColumnLayout object){
			if("default".equals(presentation)){
				return createGraphElementUimColumnLayout(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.layout.util.LayoutSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicControlSwitch extends ControlSwitch<Object>{
		/**
		 * The presentation of the graphical originalElement
		 *
		 * @generated
		 */
		private String presentation;
		/**
		 * Constructor
		 *
		 * @param presentation the presentation of the graphical originalElement
		 * @generated
		 */
		public GraphicControlSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.opeum.uim.control.util.ControlSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicFolderSwitch extends FolderSwitch<Object>{
		/**
		 * The presentation of the graphical originalElement
		 *
		 * @generated
		 */
		@SuppressWarnings("unused")
		private String presentation;
		/**
		 * Constructor
		 *
		 * @param presentation the presentation of the graphical originalElement
		 * @generated
		 */
		public GraphicFolderSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.opeum.uim.folder.util.FolderSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicFormSwitch extends FormSwitch<Object>{
		/**
		 * The presentation of the graphical originalElement
		 *
		 * @generated
		 */
		private String presentation;
		/**
		 * Constructor
		 *
		 * @param presentation the presentation of the graphical originalElement
		 * @generated
		 */
		public GraphicFormSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.opeum.uim.form.util.FormSwitch#caseFormPanel(org.opeum.uim.form.FormPanel)
		 * @generated
		 */
		public Object caseFormPanel(org.opeum.uim.form.FormPanel object){
			if("default".equals(presentation)){
				return createGraphElementFormPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.form.util.FormSwitch#caseDetailPanel(org.opeum.uim.form.DetailPanel)
		 * @generated
		 */
		public Object caseDetailPanel(org.opeum.uim.form.DetailPanel object){
			if("default".equals(presentation)){
				return createGraphElementDetailPanel(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.form.util.FormSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicBindingSwitch extends BindingSwitch<Object>{
		/**
		 * The presentation of the graphical originalElement
		 *
		 * @generated
		 */
		@SuppressWarnings("unused")
		private String presentation;
		/**
		 * Constructor
		 *
		 * @param presentation the presentation of the graphical originalElement
		 * @generated
		 */
		public GraphicBindingSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.opeum.uim.binding.util.BindingSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicSecuritySwitch extends SecuritySwitch<Object>{
		/**
		 * The presentation of the graphical originalElement
		 *
		 * @generated
		 */
		@SuppressWarnings("unused")
		private String presentation;
		/**
		 * Constructor
		 *
		 * @param presentation the presentation of the graphical originalElement
		 * @generated
		 */
		public GraphicSecuritySwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.opeum.uim.security.util.SecuritySwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicActionSwitch extends ActionSwitch<Object>{
		/**
		 * The presentation of the graphical originalElement
		 *
		 * @generated
		 */
		private String presentation;
		/**
		 * Constructor
		 *
		 * @param presentation the presentation of the graphical originalElement
		 * @generated
		 */
		public GraphicActionSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.opeum.uim.action.util.ActionSwitch#caseNavigationToEntity(org.opeum.uim.action.NavigationToEntity)
		 * @generated
		 */
		public Object caseNavigationToEntity(org.opeum.uim.action.NavigationToEntity object){
			if("default".equals(presentation)){
				return createGraphElementNavigationToEntity(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.action.util.ActionSwitch#caseNavigationToOperation(org.opeum.uim.action.NavigationToOperation)
		 * @generated
		 */
		public Object caseNavigationToOperation(org.opeum.uim.action.NavigationToOperation object){
			if("default".equals(presentation)){
				return createGraphElementNavigationToOperation(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.action.util.ActionSwitch#caseOperationAction(org.opeum.uim.action.OperationAction)
		 * @generated
		 */
		public Object caseOperationAction(org.opeum.uim.action.OperationAction object){
			if("default".equals(presentation)){
				return createGraphElementOperationAction(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.action.util.ActionSwitch#caseBuiltInAction(org.opeum.uim.action.BuiltInAction)
		 * @generated
		 */
		public Object caseBuiltInAction(org.opeum.uim.action.BuiltInAction object){
			if("default".equals(presentation)){
				return createGraphElementBuiltInAction(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.action.util.ActionSwitch#caseTransitionAction(org.opeum.uim.action.TransitionAction)
		 * @generated
		 */
		public Object caseTransitionAction(org.opeum.uim.action.TransitionAction object){
			if("default".equals(presentation)){
				return createGraphElementTransitionAction(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.opeum.uim.action.util.ActionSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
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
		if("http://opeum.org/uimetamodel/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicUimSwitch(presentation).doSwitch(obj);
		}
		if("http://opeum.org/uimetamodel/layout/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicLayoutSwitch(presentation).doSwitch(obj);
		}
		if("http://opeum.org/uimetamodel/control/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicControlSwitch(presentation).doSwitch(obj);
		}
		if("http://opeum.org/uimetamodel/folder/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicFolderSwitch(presentation).doSwitch(obj);
		}
		if("http://opeum.org/uimetamodel/form/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicFormSwitch(presentation).doSwitch(obj);
		}
		if("http://opeum.org/uimetamodel/binding/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicBindingSwitch(presentation).doSwitch(obj);
		}
		if("http://opeum.org/uimetamodel/security/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicSecuritySwitch(presentation).doSwitch(obj);
		}
		if("http://opeum.org/uimetamodel/action/1.0".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicActionSwitch(presentation).doSwitch(obj);
		}
		return (GraphElement) graphElt;
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimField(org.opeum.uim.UimField element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementNavigationToEntity(org.opeum.uim.action.NavigationToEntity element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementNavigationToOperation(org.opeum.uim.action.NavigationToOperation element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementOperationAction(org.opeum.uim.action.OperationAction element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementBuiltInAction(org.opeum.uim.action.BuiltInAction element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementTransitionAction(org.opeum.uim.action.TransitionAction element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementFormPanel(org.opeum.uim.form.FormPanel element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimPanel(org.opeum.uim.UimPanel element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimTabPanel(org.opeum.uim.UimTabPanel element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimTab(org.opeum.uim.UimTab element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimDataTable(org.opeum.uim.UimDataTable element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementDetailPanel(org.opeum.uim.form.DetailPanel element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimXYLayout(org.opeum.uim.layout.UimXYLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimGridLayout(org.opeum.uim.layout.UimGridLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimToolbarLayout(org.opeum.uim.layout.UimToolbarLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimBorderLayout(org.opeum.uim.layout.UimBorderLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimFullLayout(org.opeum.uim.layout.UimFullLayout element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param originalElement the model originalElement
	 * @param presentation the presentation of the graphical originalElement
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUimColumnLayout(org.opeum.uim.layout.UimColumnLayout element,String presentation){
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
