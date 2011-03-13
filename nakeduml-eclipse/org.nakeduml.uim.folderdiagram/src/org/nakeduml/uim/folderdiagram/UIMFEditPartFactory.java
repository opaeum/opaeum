/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.folderdiagram;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.nakeduml.uim.folderdiagram.edit.ActionTaskFormEditPart;
import org.nakeduml.uim.folderdiagram.edit.ActivityFolderEditPart;
import org.nakeduml.uim.folderdiagram.edit.ClassFormEditPart;
import org.nakeduml.uim.folderdiagram.edit.EntityFolderEditPart;
import org.nakeduml.uim.folderdiagram.edit.OperationInvocationFormEditPart;
import org.nakeduml.uim.folderdiagram.edit.OperationTaskFormEditPart;
import org.nakeduml.uim.folderdiagram.edit.PackageFolderEditPart;
import org.nakeduml.uim.folderdiagram.edit.StateFormEditPart;
import org.nakeduml.uim.folderdiagram.edit.StateMachineFolderEditPart;
import org.nakeduml.uim.folderdiagram.edit.UIMFDiagramEditPart;
import org.nakeduml.uim.util.UIMSwitch;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SimpleSemanticModelElement;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.editor.ModelerEditPartFactory;
import org.topcased.modeler.utils.Utils;

/**
 * Part Factory : associates a model object to its controller. <br>
 *
 * @generated
 */
public class UIMFEditPartFactory extends ModelerEditPartFactory {
	/**
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,java.lang.Object)
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof Diagram) {
			return new UIMFDiagramEditPart((Diagram) model);
		} else if (model instanceof GraphNode) {
			final GraphNode node = (GraphNode) model;
			EObject element = Utils.getElement(node);
			if (element != null) {
				if ("http://nakeduml.sf.net/uimetamodel/1.0".equals(element
						.eClass().getEPackage().getNsURI())) {
					return (EditPart) new NodeUIMSwitch(node).doSwitch(element);
				}
			}

			if (node.getSemanticModel() instanceof SimpleSemanticModelElement) {
				// Manage the Element that are not associated with a model object
			}
		} else if (model instanceof GraphEdge) {
			final GraphEdge edge = (GraphEdge) model;
			EObject element = Utils.getElement(edge);
			if (element != null) {
				if ("http://nakeduml.sf.net/uimetamodel/1.0".equals(element
						.eClass().getEPackage().getNsURI())) {
					return (EditPart) new EdgeUIMSwitch(edge).doSwitch(element);
				}
			}

			if (edge.getSemanticModel() instanceof SimpleSemanticModelElement) {
				// Manage the Element that are not associated with a model object                    
			}
		}
		return super.createEditPart(context, model);
	}

	/**
	 * @generated
	 */
	private class NodeUIMSwitch extends UIMSwitch {
		/**
		 * The graphical node
		 * @generated
		 */
		private GraphNode node;

		/**
		 * Constructor
		 * 
		 * @param node the graphical node
		 * @generated
		 */
		public NodeUIMSwitch(GraphNode node) {
			this.node = node;
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#casePackageFolder(org.nakeduml.uim.PackageFolder)
		 * @generated
		 */
		public Object casePackageFolder(org.nakeduml.uim.PackageFolder object) {
			return new PackageFolderEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseEntityFolder(org.nakeduml.uim.EntityFolder)
		 * @generated
		 */
		public Object caseEntityFolder(org.nakeduml.uim.EntityFolder object) {
			return new EntityFolderEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseStateMachineFolder(org.nakeduml.uim.StateMachineFolder)
		 * @generated
		 */
		public Object caseStateMachineFolder(
				org.nakeduml.uim.StateMachineFolder object) {
			return new StateMachineFolderEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseActivityFolder(org.nakeduml.uim.ActivityFolder)
		 * @generated
		 */
		public Object caseActivityFolder(
				org.nakeduml.uim.ActivityFolder object) {
			return new ActivityFolderEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseClassForm(org.nakeduml.uim.ClassForm)
		 * @generated
		 */
		public Object caseClassForm(org.nakeduml.uim.ClassForm object) {
			return new ClassFormEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseStateForm(org.nakeduml.uim.StateForm)
		 * @generated
		 */
		public Object caseStateForm(org.nakeduml.uim.StateForm object) {
			return new StateFormEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseOperationTaskForm(org.nakeduml.uim.OperationTaskForm)
		 * @generated
		 */
		public Object caseOperationTaskForm(
				org.nakeduml.uim.OperationTaskForm object) {
			return new OperationTaskFormEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseActionTaskForm(org.nakeduml.uim.ActionTaskForm)
		 * @generated
		 */
		public Object caseActionTaskForm(
				org.nakeduml.uim.ActionTaskForm object) {
			return new ActionTaskFormEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseOperationInvocationForm(org.nakeduml.uim.OperationInvocationForm)
		 * @generated
		 */
		public Object caseOperationInvocationForm(
				org.nakeduml.uim.OperationInvocationForm object) {
			return new OperationInvocationFormEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object) {
			return new EMFGraphNodeEditPart(node);
		}
	}

	/**
	 * @generated
	 */
	private class EdgeUIMSwitch extends UIMSwitch {
		/**
		 * The graphical edge
		 * @generated
		 */
		private GraphEdge edge;

		/**
		 * Constructor
		 * 
		 * @param edge the graphical edge
		 * @generated
		 */
		public EdgeUIMSwitch(GraphEdge edge) {
			this.edge = edge;
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object) {
			return new EMFGraphEdgeEditPart(edge);
		}
	}

}