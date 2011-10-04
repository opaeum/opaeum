/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opeum.bpmn2.diagram;

import org.eclipse.bpmn2.di.util.BpmnDiSwitch;
import org.eclipse.bpmn2.util.Bpmn2Switch;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dd.dc.util.DcSwitch;
import org.eclipse.dd.di.util.DiSwitch;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.editor.AbstractCreationUtils;
import org.topcased.modeler.graphconf.DiagramGraphConf;

/**
 * This utility class allows to create a GraphElement associated with a Model Object
 *
 * @generated
 */
public class BPMN2CreationUtils extends AbstractCreationUtils{
	/**
	 * Constructor
	 *
	 * @param diagramConf the Diagram Graphical Configuration
	 * @generated
	 */
	public BPMN2CreationUtils(DiagramGraphConf diagramConf){
		super(diagramConf);
	}
	/**
	 * @generated
	 */
	private class GraphicBpmn2Switch extends Bpmn2Switch{
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
		public GraphicBpmn2Switch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseUserTask(org.eclipse.bpmn2.UserTask)
		 * @generated
		 */
		public Object caseUserTask(org.eclipse.bpmn2.UserTask object){
			if("default".equals(presentation)){
				return createGraphElementUserTask(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseSequenceFlow(org.eclipse.bpmn2.SequenceFlow)
		 * @generated
		 */
		public Object caseSequenceFlow(org.eclipse.bpmn2.SequenceFlow object){
			if("default".equals(presentation)){
				return createGraphElementSequenceFlow(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#caseBoundaryEvent(org.eclipse.bpmn2.BoundaryEvent)
		 * @generated
		 */
		public Object caseBoundaryEvent(org.eclipse.bpmn2.BoundaryEvent object){
			if("default".equals(presentation)){
				return createGraphElementBoundaryEvent(object, presentation);
			}
			return null;
		}
		/**
		 * @see org.eclipse.bpmn2.util.Bpmn2Switch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicBpmnDiSwitch extends BpmnDiSwitch{
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
		public GraphicBpmnDiSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.eclipse.bpmn2.di.util.BpmnDiSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicDiSwitch extends DiSwitch{
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
		public GraphicDiSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.eclipse.dd.di.util.DiSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return null;
		}
	}
	/**
	 * @generated
	 */
	private class GraphicDcSwitch extends DcSwitch{
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
		public GraphicDcSwitch(String presentation){
			this.presentation = presentation;
		}
		/**
		 * @see org.eclipse.dd.dc.util.DcSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
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
		if("http://www.omg.org/spec/BPMN/20100524/MODEL-XMI".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicBpmn2Switch(presentation).doSwitch(obj);
		}
		if("http://www.omg.org/spec/BPMN/20100524/DI-XMI".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicBpmnDiSwitch(presentation).doSwitch(obj);
		}
		if("http://www.omg.org/spec/DD/20100524/DI-XMI".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicDiSwitch(presentation).doSwitch(obj);
		}
		if("http://www.omg.org/spec/DD/20100524/DC-XMI".equals(obj.eClass().getEPackage().getNsURI())){
			graphElt = new GraphicDcSwitch(presentation).doSwitch(obj);
		}
		return (GraphElement) graphElt;
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementUserTask(org.eclipse.bpmn2.UserTask element,String presentation){
		return createGraphNode(element, presentation);
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementSequenceFlow(org.eclipse.bpmn2.SequenceFlow element,String presentation){
		GraphEdge graphEdge = createGraphEdge(element, presentation);
		return graphEdge;
	}
	/**
	 * @param element the model element
	 * @param presentation the presentation of the graphical element
	 * @return the complete GraphElement
	 * @generated
	 */
	protected GraphElement createGraphElementBoundaryEvent(org.eclipse.bpmn2.BoundaryEvent element,String presentation){
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
		IEditorInput editorInput = BPMN2Plugin.getActivePage().getActiveEditor().getEditorInput();
		if(editorInput instanceof IFileEditorInput){
			IProject project = ((IFileEditorInput) editorInput).getFile().getProject();
			Preferences root = Platform.getPreferencesService().getRootNode();
			try{
				if(root.node(ProjectScope.SCOPE).node(project.getName()).nodeExists(BPMN2Plugin.getId())){
					return new ScopedPreferenceStore(new ProjectScope(project), BPMN2Plugin.getId());
				}
			}catch(BackingStoreException e){
				e.printStackTrace();
			}
		}
		return BPMN2Plugin.getDefault().getPreferenceStore();
	}
}
