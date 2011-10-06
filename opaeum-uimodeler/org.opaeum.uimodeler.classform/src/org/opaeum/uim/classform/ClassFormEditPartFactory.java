/*******************************************************************************
 * 
 ******************************************************************************/
package org.opaeum.uim.classform;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.opaeum.uim.action.util.ActionSwitch;
import org.opaeum.uim.binding.util.BindingSwitch;
import org.opaeum.uim.classform.edit.BuiltInActionEditPart;
import org.opaeum.uim.classform.edit.ClassFormDiagramEditPart;
import org.opaeum.uim.classform.edit.DetailPanelEditPart;
import org.opaeum.uim.classform.edit.FormPanelEditPart;
import org.opaeum.uim.classform.edit.NavigationToEntityEditPart;
import org.opaeum.uim.classform.edit.NavigationToOperationEditPart;
import org.opaeum.uim.classform.edit.OperationActionEditPart;
import org.opaeum.uim.classform.edit.TransitionActionEditPart;
import org.opaeum.uim.classform.edit.UimBorderLayoutEditPart;
import org.opaeum.uim.classform.edit.UimColumnLayoutEditPart;
import org.opaeum.uim.classform.edit.UimDataTableEditPart;
import org.opaeum.uim.classform.edit.UimFieldEditPart;
import org.opaeum.uim.classform.edit.UimFullLayoutEditPart;
import org.opaeum.uim.classform.edit.UimGridLayoutEditPart;
import org.opaeum.uim.classform.edit.UimPanelEditPart;
import org.opaeum.uim.classform.edit.UimTabEditPart;
import org.opaeum.uim.classform.edit.UimTabPanelEditPart;
import org.opaeum.uim.classform.edit.UimToolbarLayoutEditPart;
import org.opaeum.uim.classform.edit.UimXYLayoutEditPart;
import org.opaeum.uim.control.util.ControlSwitch;
import org.opaeum.uim.folder.util.FolderSwitch;
import org.opaeum.uim.form.util.FormSwitch;
import org.opaeum.uim.layout.util.LayoutSwitch;
import org.opaeum.uim.security.util.SecuritySwitch;
import org.opaeum.uim.util.UimSwitch;
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
				if("http://opaeum.org/uimetamodel/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeUimSwitch(node).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/layout/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeLayoutSwitch(node).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/control/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeControlSwitch(node).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/folder/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeFolderSwitch(node).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/form/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeFormSwitch(node).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/binding/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeBindingSwitch(node).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/security/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeSecuritySwitch(node).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/action/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new NodeActionSwitch(node).doSwitch(element);
				}
			}
			if(node.getSemanticModel() instanceof SimpleSemanticModelElement){
				// Manage the Element that are not associated with a model object
			}
		}else if(model instanceof GraphEdge){
			final GraphEdge edge = (GraphEdge) model;
			EObject element = Utils.getElement(edge);
			if(element != null){
				if("http://opaeum.org/uimetamodel/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeUimSwitch(edge).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/layout/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeLayoutSwitch(edge).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/control/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeControlSwitch(edge).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/folder/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeFolderSwitch(edge).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/form/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeFormSwitch(edge).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/binding/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeBindingSwitch(edge).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/security/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeSecuritySwitch(edge).doSwitch(element);
				}
				if("http://opaeum.org/uimetamodel/action/1.0".equals(element.eClass().getEPackage().getNsURI())){
					return (EditPart) new EdgeActionSwitch(edge).doSwitch(element);
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
	private class NodeUimSwitch extends UimSwitch<Object>{
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
		 * @see org.opaeum.uim.util.UimSwitch#caseUimField(org.opaeum.uim.UimField)
		 * @generated
		 */
		public Object caseUimField(org.opaeum.uim.UimField object){
			return new UimFieldEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.util.UimSwitch#caseUimPanel(org.opaeum.uim.UimPanel)
		 * @generated
		 */
		public Object caseUimPanel(org.opaeum.uim.UimPanel object){
			return new UimPanelEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.util.UimSwitch#caseUimTabPanel(org.opaeum.uim.UimTabPanel)
		 * @generated
		 */
		public Object caseUimTabPanel(org.opaeum.uim.UimTabPanel object){
			return new UimTabPanelEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.util.UimSwitch#caseUimTab(org.opaeum.uim.UimTab)
		 * @generated
		 */
		public Object caseUimTab(org.opaeum.uim.UimTab object){
			return new UimTabEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.util.UimSwitch#caseUimDataTable(org.opaeum.uim.UimDataTable)
		 * @generated
		 */
		public Object caseUimDataTable(org.opaeum.uim.UimDataTable object){
			return new UimDataTableEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.util.UimSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class NodeLayoutSwitch extends LayoutSwitch<Object>{
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
		public NodeLayoutSwitch(GraphNode node){
			this.node = node;
		}
		/**
		 * @see org.opaeum.uim.layout.util.LayoutSwitch#caseUimXYLayout(org.opaeum.uim.layout.UimXYLayout)
		 * @generated
		 */
		public Object caseUimXYLayout(org.opaeum.uim.layout.UimXYLayout object){
			return new UimXYLayoutEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.layout.util.LayoutSwitch#caseUimGridLayout(org.opaeum.uim.layout.UimGridLayout)
		 * @generated
		 */
		public Object caseUimGridLayout(org.opaeum.uim.layout.UimGridLayout object){
			return new UimGridLayoutEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.layout.util.LayoutSwitch#caseUimToolbarLayout(org.opaeum.uim.layout.UimToolbarLayout)
		 * @generated
		 */
		public Object caseUimToolbarLayout(org.opaeum.uim.layout.UimToolbarLayout object){
			return new UimToolbarLayoutEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.layout.util.LayoutSwitch#caseUimBorderLayout(org.opaeum.uim.layout.UimBorderLayout)
		 * @generated
		 */
		public Object caseUimBorderLayout(org.opaeum.uim.layout.UimBorderLayout object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new UimBorderLayoutEditPart(node);
			}
		}
		/**
		 * @see org.opaeum.uim.layout.util.LayoutSwitch#caseUimFullLayout(org.opaeum.uim.layout.UimFullLayout)
		 * @generated
		 */
		public Object caseUimFullLayout(org.opaeum.uim.layout.UimFullLayout object){
			return new UimFullLayoutEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.layout.util.LayoutSwitch#caseUimColumnLayout(org.opaeum.uim.layout.UimColumnLayout)
		 * @generated
		 */
		public Object caseUimColumnLayout(org.opaeum.uim.layout.UimColumnLayout object){
			return new UimColumnLayoutEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.layout.util.LayoutSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class NodeControlSwitch extends ControlSwitch<Object>{
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
		public NodeControlSwitch(GraphNode node){
			this.node = node;
		}
		/**
		 * @see org.opaeum.uim.control.util.ControlSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class NodeFolderSwitch extends FolderSwitch<Object>{
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
		public NodeFolderSwitch(GraphNode node){
			this.node = node;
		}
		/**
		 * @see org.opaeum.uim.folder.util.FolderSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class NodeFormSwitch extends FormSwitch<Object>{
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
		public NodeFormSwitch(GraphNode node){
			this.node = node;
		}
		/**
		 * @see org.opaeum.uim.form.util.FormSwitch#caseFormPanel(org.opaeum.uim.form.FormPanel)
		 * @generated
		 */
		public Object caseFormPanel(org.opaeum.uim.form.FormPanel object){
			return new FormPanelEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.form.util.FormSwitch#caseDetailPanel(org.opaeum.uim.form.DetailPanel)
		 * @generated
		 */
		public Object caseDetailPanel(org.opaeum.uim.form.DetailPanel object){
			return new DetailPanelEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.form.util.FormSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class NodeBindingSwitch extends BindingSwitch<Object>{
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
		public NodeBindingSwitch(GraphNode node){
			this.node = node;
		}
		/**
		 * @see org.opaeum.uim.binding.util.BindingSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class NodeSecuritySwitch extends SecuritySwitch<Object>{
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
		public NodeSecuritySwitch(GraphNode node){
			this.node = node;
		}
		/**
		 * @see org.opaeum.uim.security.util.SecuritySwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class NodeActionSwitch extends ActionSwitch<Object>{
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
		public NodeActionSwitch(GraphNode node){
			this.node = node;
		}
		/**
		 * @see org.opaeum.uim.action.util.ActionSwitch#caseNavigationToEntity(org.opaeum.uim.action.NavigationToEntity)
		 * @generated
		 */
		public Object caseNavigationToEntity(org.opaeum.uim.action.NavigationToEntity object){
			return new NavigationToEntityEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.action.util.ActionSwitch#caseNavigationToOperation(org.opaeum.uim.action.NavigationToOperation)
		 * @generated
		 */
		public Object caseNavigationToOperation(org.opaeum.uim.action.NavigationToOperation object){
			return new NavigationToOperationEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.action.util.ActionSwitch#caseBuiltInAction(org.opaeum.uim.action.BuiltInAction)
		 * @generated
		 */
		public Object caseBuiltInAction(org.opaeum.uim.action.BuiltInAction object){
			return new BuiltInActionEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.action.util.ActionSwitch#caseOperationAction(org.opaeum.uim.action.OperationAction)
		 * @generated
		 */
		public Object caseOperationAction(org.opaeum.uim.action.OperationAction object){
			return new OperationActionEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.action.util.ActionSwitch#caseTransitionAction(org.opaeum.uim.action.TransitionAction)
		 * @generated
		 */
		public Object caseTransitionAction(org.opaeum.uim.action.TransitionAction object){
			return new TransitionActionEditPart(node);
		}
		/**
		 * @see org.opaeum.uim.action.util.ActionSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeUimSwitch extends UimSwitch<Object>{
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
		 * @see org.opaeum.uim.util.UimSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeLayoutSwitch extends LayoutSwitch<Object>{
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
		public EdgeLayoutSwitch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * @see org.opaeum.uim.layout.util.LayoutSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeControlSwitch extends ControlSwitch<Object>{
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
		public EdgeControlSwitch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * @see org.opaeum.uim.control.util.ControlSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeFolderSwitch extends FolderSwitch<Object>{
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
		public EdgeFolderSwitch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * @see org.opaeum.uim.folder.util.FolderSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeFormSwitch extends FormSwitch<Object>{
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
		public EdgeFormSwitch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * @see org.opaeum.uim.form.util.FormSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeBindingSwitch extends BindingSwitch<Object>{
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
		public EdgeBindingSwitch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * @see org.opaeum.uim.binding.util.BindingSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeSecuritySwitch extends SecuritySwitch<Object>{
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
		public EdgeSecuritySwitch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * @see org.opaeum.uim.security.util.SecuritySwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
	/**
	 * @generated
	 */
	private class EdgeActionSwitch extends ActionSwitch<Object>{
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
		public EdgeActionSwitch(GraphEdge edge){
			this.edge = edge;
		}
		/**
		 * @see org.opaeum.uim.action.util.ActionSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
		 * @generated
		 */
		public Object defaultCase(EObject object){
			return new EMFGraphEdgeEditPart(edge);
		}
	}
}