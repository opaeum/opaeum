package org.nakeduml.topcased.statemachinediagram;

import net.sf.nakeduml.emf.extraction.StereotypesHelper;
import net.sf.nakeduml.emf.workspace.UmlElementCache;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.nakeduml.topcased.classdiagram.figure.Gradient;
import org.nakeduml.topcased.uml.NakedUmlPlugin;
import org.nakeduml.topcased.uml.editor.NakedUmlEditor;
import org.nakeduml.uml2uim.AbstractUimGenerationAction;
import org.nakeduml.uml2uim.SynchronizeAction;
import org.topcased.modeler.ModelerPropertyConstants;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.di.model.util.DIUtils;
import org.topcased.modeler.edit.EListEditPart;
import org.topcased.modeler.edit.EMFGraphNodeEditPart;
import org.topcased.modeler.evaluator.EvaluatorException;
import org.topcased.modeler.evaluator.extension.EvaluatorsManager;
import org.topcased.modeler.uml.alldiagram.edit.CommentEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintEditPart;
import org.topcased.modeler.uml.classdiagram.figures.ClassFigure;
import org.topcased.modeler.uml.statemachinediagram.STMEditPartFactory;
import org.topcased.modeler.uml.statemachinediagram.STMPlugin;
import org.topcased.modeler.uml.statemachinediagram.edit.CompositeStateEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.ConnectionPointReferenceEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.FinalStateEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.PseudostateChoiceEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.PseudostateDeepHistoryEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.PseudostateEntryPointEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.PseudostateExitPointEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.PseudostateForkEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.PseudostateInitialEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.PseudostateJoinEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.PseudostateJunctionEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.PseudostateShallowHistoryEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.PseudostateTerminateEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.RegionEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.StateEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.SubmachineStateEditPart;
import org.topcased.modeler.uml.statemachinediagram.edit.VertexEditPart;
import org.topcased.modeler.uml.statemachinediagram.figures.StateFigure;
import org.topcased.modeler.utils.Utils;

public class ScreenFlowEditPartFactory extends STMEditPartFactory{
	public EditPart createEditPart(EditPart context,Object model){
		if(model instanceof Diagram){
			return super.createEditPart(context, model);
		}else if(model instanceof GraphNode){
			final GraphNode node = (GraphNode) model;
			EObject element = Utils.getElement(node);
			if(element != null){
				return (EditPart) new NodeUMLSwitch(node).doSwitch(element);
			}
		}
		return super.createEditPart(context, model);
	}
	private class NodeUMLSwitch extends UMLSwitch<Object>{
		private GraphNode node;
		public NodeUMLSwitch(GraphNode node){
			this.node = node;
		}
		public Object caseRegion(org.eclipse.uml2.uml.Region object){
			return new RegionEditPart(node);
		}
		public Object caseVertex(org.eclipse.uml2.uml.Vertex object){
			return new VertexEditPart(node);
		}
		public EditPart caseConstraint(org.eclipse.uml2.uml.Constraint object){
			String feature = DIUtils.getPropertyValue(node, ModelerPropertyConstants.ESTRUCTURAL_FEATURE_ID);
			if(!"".equals(feature)){
				int featureID = Integer.parseInt(feature);
				return new EListEditPart(node, object.eClass().getEStructuralFeature(featureID));
			}else{
				return new ConstraintEditPart(node);
			}
		}
		public Object caseState(org.eclipse.uml2.uml.State object){
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.isSimple=true", "ocl")
						&& EvaluatorsManager.getInstance().evaluate(object, "self.isSubmachineState=false", "ocl")){
					return new StateEditPart(node){
						@Override
						public void performRequest(Request request){
							if(request.getType() == RequestConstants.REQ_OPEN){
								NamedElement e = (NamedElement) getEObject();
								String uuid = NakedUmlEditor.getCurrentContext().getId(e);
								URI uri = AbstractUimGenerationAction.getFileUri(e, uuid);
								if(!AbstractUimGenerationAction.getFile(uri).exists()){
									SynchronizeAction.doSynchronize(e);
								}
								AbstractUimGenerationAction.openEditor(e, uuid);
							}else{
								super.performRequest(request);
							}
						}
						@Override
						protected IFigure createFigure(){
							return new StateFigure(){
								@Override
								public void fillShape(Graphics graphics){
									Rectangle originalBounds = figure.getBounds();
									Rectangle r = originalBounds.getCopy();
									r.x=(int) Math.round(r.x*graphics.getAbsoluteScale());
									r.y=(int) Math.round(r.y*graphics.getAbsoluteScale());
									r.height=(int) Math.round(r.height*graphics.getAbsoluteScale());
									r.width=(int) Math.round(r.width*graphics.getAbsoluteScale());
									Pattern bcPattern = new Pattern(Display.getCurrent(), r.x, r.y, r.x, r.y+r.height, figure.getBackgroundColor(), ColorConstants.white);
									graphics.setBackgroundPattern(bcPattern);
									graphics.setFillRule(SWT.FILL_EVEN_ODD);
									super.fillShape(graphics);
									graphics.setBackgroundPattern(null);
									bcPattern.dispose();
								}
							};
						}
					};
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.isComposite=true", "ocl")){
					return new CompositeStateEditPart(node);
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.isSubmachineState=true", "ocl")){
					return new SubmachineStateEditPart(node);
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			return new EMFGraphNodeEditPart(node);
		}
		public Object caseFinalState(org.eclipse.uml2.uml.FinalState object){
			return new FinalStateEditPart(node);
		}
		public Object casePseudostate(org.eclipse.uml2.uml.Pseudostate object){
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.kind=PseudostateKind::initial", "ocl")){
					return new PseudostateInitialEditPart(node);
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.kind=PseudostateKind::deepHistory", "ocl")){
					return new PseudostateDeepHistoryEditPart(node);
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.kind=PseudostateKind::shallowHistory", "ocl")){
					return new PseudostateShallowHistoryEditPart(node);
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.kind=PseudostateKind::join", "ocl")){
					return new PseudostateJoinEditPart(node);
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.kind=PseudostateKind::fork", "ocl")){
					return new PseudostateForkEditPart(node);
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.kind=PseudostateKind::junction", "ocl")){
					return new PseudostateJunctionEditPart(node);
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.kind=PseudostateKind::choice", "ocl")){
					return new PseudostateChoiceEditPart(node);
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.kind=PseudostateKind::entryPoint", "ocl")){
					return new PseudostateEntryPointEditPart(node);
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.kind=PseudostateKind::exitPoint", "ocl")){
					return new PseudostateExitPointEditPart(node);
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			try{
				if(EvaluatorsManager.getInstance().evaluate(object, "self.kind=PseudostateKind::terminate", "ocl")){
					return new PseudostateTerminateEditPart(node);
				}
			}catch(EvaluatorException ee){
				STMPlugin.log(ee);
			}
			return new EMFGraphNodeEditPart(node);
		}
		public Object caseConnectionPointReference(org.eclipse.uml2.uml.ConnectionPointReference object){
			return new ConnectionPointReferenceEditPart(node);
		}
		public Object caseComment(org.eclipse.uml2.uml.Comment object){
			return new CommentEditPart(node);
		}
		public Object defaultCase(EObject object){
			return new EMFGraphNodeEditPart(node);
		}
	}
}