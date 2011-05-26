/*******************************************************************************
 * 
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
import org.nakeduml.uim.classform.edit.UimBorderLayoutEditPart;
import org.nakeduml.uim.classform.edit.UimDataColumnEditPart;
import org.nakeduml.uim.classform.edit.UimDataTableEditPart;
import org.nakeduml.uim.classform.edit.UimFieldEditPart;
import org.nakeduml.uim.classform.edit.UimFullLayoutEditPart;
import org.nakeduml.uim.classform.edit.UimGridLayoutEditPart;
import org.nakeduml.uim.classform.edit.UimPanelEditPart;
import org.nakeduml.uim.classform.edit.UimTabEditPart;
import org.nakeduml.uim.classform.edit.UimTabPanelEditPart;
import org.nakeduml.uim.classform.edit.UimToolbarLayoutEditPart;
import org.nakeduml.uim.classform.edit.UimXYLayoutEditPart;
import org.nakeduml.uim.util.UimSwitch;
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
public class ClassFormEditPartFactory extends ModelerEditPartFactory{
	/**
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,java.lang.Object)
	 * @generated
	 */
	public EditPart createEditPart(EditPart context,Object model){
		if(model instanceof Diagram){
			return new ClassFormDiagramEditPart((Diagram) model);
		}else if(model instanceof GraphNode){
			final GraphNode node = (GraphNode) model;
			EObject element = Utils.getElement(node);
			if(element != null){
				if("http://nakeduml.org/uimetamodel/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeUimSwitch(node).doSwitch(element);
				}
			}
			if(node.getSemanticModel() instanceof SimpleSemanticModelElement){
				// Manage the Element that are not associated with a model object
			}
		}else if(model instanceof GraphEdge){
			final GraphEdge edge = (GraphEdge) model;
			EObject element = Utils.getElement(edge);
			if(element != null){
				if("http://nakeduml.org/uimetamodel/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeUimSwitch(edge).doSwitch(element);
				}
			}
			if(edge.getSemanticModel() instanceof SimpleSemanticModelElement){
				// Manage the Element that are not associated with a model object                    
			}
		}
		return super.createEditPart(context, model);
	}
	/**
	 * @generated
	 */
	private class NodeUimSwitch extends UimSwitch{
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
		public NodeUimSwitch(GraphNode node){
			this.node = node;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimField(org.nakeduml.uim.UimField)
		 * @generated
		 */
		public Object caseUimField(org.nakeduml.uim.UimField object){
			return new UimFieldEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseNavigationToEntity(org.nakeduml.uim.NavigationToEntity)
		 * @generated
		 */
		public Object caseNavigationToEntity(org.nakeduml.uim.NavigationToEntity object){
			return new NavigationToEntityEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseNavigationToOperation(org.nakeduml.uim.NavigationToOperation)
		 * @generated
		 */
		public Object caseNavigationToOperation(org.nakeduml.uim.NavigationToOperation object){
			return new NavigationToOperationEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseBuiltInAction(org.nakeduml.uim.BuiltInAction)
		 * @generated
		 */
		public Object caseBuiltInAction(org.nakeduml.uim.BuiltInAction object){
			return new BuiltInActionEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseOperationAction(org.nakeduml.uim.OperationAction)
		 * @generated
		 */
		public Object caseOperationAction(org.nakeduml.uim.OperationAction object){
			return new OperationActionEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseTransitionAction(org.nakeduml.uim.TransitionAction)
		 * @generated
		 */
		public Object caseTransitionAction(org.nakeduml.uim.TransitionAction object){
			return new TransitionActionEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseFormPanel(org.nakeduml.uim.FormPanel)
		 * @generated
		 */
		public Object caseFormPanel(org.nakeduml.uim.FormPanel object){
			return new FormPanelEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimPanel(org.nakeduml.uim.UimPanel)
		 * @generated
		 */
		public Object caseUimPanel(org.nakeduml.uim.UimPanel object){
			return new UimPanelEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimTabPanel(org.nakeduml.uim.UimTabPanel)
		 * @generated
		 */
		public Object caseUimTabPanel(org.nakeduml.uim.UimTabPanel object){
			return new UimTabPanelEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimTab(org.nakeduml.uim.UimTab)
		 * @generated
		 */
		public Object caseUimTab(org.nakeduml.uim.UimTab object){
			return new UimTabEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimDataTable(org.nakeduml.uim.UimDataTable)
		 * @generated
		 */
		public Object caseUimDataTable(org.nakeduml.uim.UimDataTable object){
			return new UimDataTableEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimDataColumn(org.nakeduml.uim.UimDataColumn)
		 * @generated
		 */
		public Object caseUimDataColumn(org.nakeduml.uim.UimDataColumn object){
			return new UimDataColumnEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseDetailPanel(org.nakeduml.uim.DetailPanel)
		 * @generated
		 */
		public Object caseDetailPanel(org.nakeduml.uim.DetailPanel object){
			return new DetailPanelEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimXYLayout(org.nakeduml.uim.UimXYLayout)
		 * @generated
		 */
		public Object caseUimXYLayout(org.nakeduml.uim.UimXYLayout object){
			return new UimXYLayoutEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimGridLayout(org.nakeduml.uim.UimGridLayout)
		 * @generated
		 */
		public Object caseUimGridLayout(org.nakeduml.uim.UimGridLayout object){
			return new UimGridLayoutEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimToolbarLayout(org.nakeduml.uim.UimToolbarLayout)
		 * @generated
		 */
		public Object caseUimToolbarLayout(org.nakeduml.uim.UimToolbarLayout object){
			return new UimToolbarLayoutEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimBorderLayout(org.nakeduml.uim.UimBorderLayout)
		 * @generated
		 */
		public Object caseUimBorderLayout(org.nakeduml.uim.UimBorderLayout object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new UimBorderLayoutEditPart(node);
			}
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#caseUimFullLayout(org.nakeduml.uim.UimFullLayout)
		 * @generated
		 */
		public Object caseUimFullLayout(org.nakeduml.uim.UimFullLayout object){
			return new UimFullLayoutEditPart(node);
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeUimSwitch extends UimSwitch{
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
		public EdgeUimSwitch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * @see org.nakeduml.uim.util.UimSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
}