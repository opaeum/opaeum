/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.nakeduml.uim.classform;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.nakeduml.uim.classform.edit.BuiltInActionEditPart;
import org.nakeduml.uim.classform.edit.ClassFormDiagramEditPart;
import org.nakeduml.uim.classform.edit.DetailPanelEditPart;
import org.nakeduml.uim.classform.edit.FormPanelEditPart;
import org.nakeduml.uim.classform.edit.NavigationToEntityEditPart;
import org.nakeduml.uim.classform.edit.NavigationToOperationEditPart;
import org.nakeduml.uim.classform.edit.OperationActionEditPart;
import org.nakeduml.uim.classform.edit.TransitionActionEditPart;
import org.nakeduml.uim.classform.edit.UIMBorderLayoutEditPart;
import org.nakeduml.uim.classform.edit.UIMDataColumnEditPart;
import org.nakeduml.uim.classform.edit.UIMDataTableEditPart;
import org.nakeduml.uim.classform.edit.UIMFieldEditPart;
import org.nakeduml.uim.classform.edit.UIMGridLayoutEditPart;
import org.nakeduml.uim.classform.edit.UIMPanelEditPart;
import org.nakeduml.uim.classform.edit.UIMTabEditPart;
import org.nakeduml.uim.classform.edit.UIMTabPanelEditPart;
import org.nakeduml.uim.classform.edit.UIMToolbarLayoutEditPart;
import org.nakeduml.uim.classform.edit.UIMXYLayoutEditPart;
import org.nakeduml.uim.util.UIMSwitch;
import org.topcased.modeler.ModelerPropertyConstants;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.SimpleSemanticModelElement;
import org.topcased.modeler.di.model.util.DIUtils;
import org.topcased.modeler.edit.EListEditPart;
import org.topcased.modeler.edit.EMFGraphEdgeEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.editor.ModelerEditPartFactory;
import org.topcased.modeler.utils.Utils;

/**
 * Part Factory : associates a model object to its controller. <br>
 *
 * @generated
 */
public class ClassFormEditPartFactory extends ModelerEditPartFactory {
	/**
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,java.lang.Object)
	 * @generated
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof Diagram) {
			return new ClassFormDiagramEditPart((Diagram) model);
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
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMField(org.nakeduml.uim.UIMField)
		 * @generated
		 */
		public Object caseUIMField(org.nakeduml.uim.UIMField object) {
			return new UIMFieldEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseNavigationToEntity(org.nakeduml.uim.NavigationToEntity)
		 * @generated
		 */
		public Object caseNavigationToEntity(
				org.nakeduml.uim.NavigationToEntity object) {
			return new NavigationToEntityEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseNavigationToOperation(org.nakeduml.uim.NavigationToOperation)
		 * @generated
		 */
		public Object caseNavigationToOperation(
				org.nakeduml.uim.NavigationToOperation object) {
			return new NavigationToOperationEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseBuiltInAction(org.nakeduml.uim.BuiltInAction)
		 * @generated
		 */
		public Object caseBuiltInAction(org.nakeduml.uim.BuiltInAction object) {
			return new BuiltInActionEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseOperationAction(org.nakeduml.uim.OperationAction)
		 * @generated
		 */
		public Object caseOperationAction(
				org.nakeduml.uim.OperationAction object) {
			return new OperationActionEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseFormPanel(org.nakeduml.uim.FormPanel)
		 * @generated
		 */
		public Object caseFormPanel(org.nakeduml.uim.FormPanel object) {
			return new FormPanelEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMPanel(org.nakeduml.uim.UIMPanel)
		 * @generated
		 */
		public Object caseUIMPanel(org.nakeduml.uim.UIMPanel object) {
			return new UIMPanelEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMTabPanel(org.nakeduml.uim.UIMTabPanel)
		 * @generated
		 */
		public Object caseUIMTabPanel(org.nakeduml.uim.UIMTabPanel object) {
			return new UIMTabPanelEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMTab(org.nakeduml.uim.UIMTab)
		 * @generated
		 */
		public Object caseUIMTab(org.nakeduml.uim.UIMTab object) {
			return new UIMTabEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMDataTable(org.nakeduml.uim.UIMDataTable)
		 * @generated
		 */
		public Object caseUIMDataTable(org.nakeduml.uim.UIMDataTable object) {
			return new UIMDataTableEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMDataColumn(org.nakeduml.uim.UIMDataColumn)
		 * @generated
		 */
		public Object caseUIMDataColumn(org.nakeduml.uim.UIMDataColumn object) {
			return new UIMDataColumnEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseDetailPanel(org.nakeduml.uim.DetailPanel)
		 * @generated
		 */
		public Object caseDetailPanel(org.nakeduml.uim.DetailPanel object) {
			return new DetailPanelEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMXYLayout(org.nakeduml.uim.UIMXYLayout)
		 * @generated
		 */
		public Object caseUIMXYLayout(org.nakeduml.uim.UIMXYLayout object) {
			return new UIMXYLayoutEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMGridLayout(org.nakeduml.uim.UIMGridLayout)
		 * @generated
		 */
		public Object caseUIMGridLayout(org.nakeduml.uim.UIMGridLayout object) {
			return new UIMGridLayoutEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMToolbarLayout(org.nakeduml.uim.UIMToolbarLayout)
		 * @generated
		 */
		public Object caseUIMToolbarLayout(
				org.nakeduml.uim.UIMToolbarLayout object) {
			return new UIMToolbarLayoutEditPart(node);
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseUIMBorderLayout(org.nakeduml.uim.UIMBorderLayout)
		 * @generated
		 */
		public Object caseUIMBorderLayout(
				org.nakeduml.uim.UIMBorderLayout object) {
			String feature = DIUtils.getPropertyValue(node,
					ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if (!"".equals(feature)) {
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass()
						.getEStructuralFeature(featureID));
			} else {
				return new UIMBorderLayoutEditPart(node);
			}
		}

		/**
		 * @see org.nakeduml.uim.util.UIMSwitch#caseTransitionAction(org.nakeduml.uim.TransitionAction)
		 * @generated
		 */
		public Object caseTransitionAction(
				org.nakeduml.uim.TransitionAction object) {
			return new TransitionActionEditPart(node);
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