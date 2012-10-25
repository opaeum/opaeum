package org.opaeum.modeler.compositestructurediagram;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditDomain;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Port;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.StructuredClassifier;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.compositestructuresdiagram.commands.InterfaceRealizationEdgeCreationCommand;
import org.topcased.modeler.uml.compositestructuresdiagram.policies.InterfaceRealizationEdgeCreationEditPolicy;
import org.topcased.modeler.utils.SourceTargetData;
import org.topcased.modeler.utils.Utils;

public class BusinessStructureInterfaceRealizationEdgeCreationPolicy extends InterfaceRealizationEdgeCreationEditPolicy{
	@Override
	protected boolean checkTargetForSource(GraphElement source,GraphElement target){
		EObject sourceObject = Utils.getElement(source);
		EObject targetObject = Utils.getElement(target);
		if(sourceObject instanceof Interface && targetObject instanceof Property && ((Property) targetObject).getType() instanceof BehavioredClassifier){
			return true;
		}
		return false;
	}
	@Override
	protected SourceTargetData getSourceTargetData(GraphElement source,GraphElement target){
		EObject sourceObject = Utils.getElement(source);
		EObject targetObject = Utils.getElement(target);
		
		if (sourceObject instanceof Interface && targetObject instanceof Property)
		{
			return new SourceTargetData(false, false, SourceTargetData.TARGET, "org.eclipse.uml2.uml.Class", "interfaceRealization",
					"contract", null, null, null, null, null);
		}
		return null;
	}
	@Override
	protected CreateTypedEdgeCommand createCommand(EditDomain domain,GraphEdge edge,GraphElement source){
		return new InterfaceRealizationEdgeCreationCommand(domain, edge, source){
			@Override
			protected EObject getContainerEObject(){
				EObject object = Utils.getElement(getTarget());
				if(object instanceof Port){
					EObject container = Utils.getElement(getTarget().getContainer());
					if(container instanceof Property){
						return ((Property) container).getType();
					}
					if(container instanceof StructuredClassifier){
						return container;
					}
				}else if(object instanceof Property){
					return ((Property) object).getType();
				}
				return super.getContainerEObject();
			}
			@Override
			protected void redoModel(){
				super.redoModel();
				EObject targetObject = Utils.getElement(getTarget());
				BehavioredClassifier bc = null;
				if(targetObject instanceof Property && ((Property) targetObject).getType() instanceof BehavioredClassifier){
					bc = (BehavioredClassifier) ((Property) targetObject).getType();
				}else if(targetObject instanceof BehavioredClassifier){
					bc = (BehavioredClassifier) targetObject;
				}
				InterfaceRealization ir = (InterfaceRealization) Utils.getElement(getEdge());
				Interface intf = (Interface) Utils.getElement(getSource());
				ir.setContract(intf);
				bc.getInterfaceRealization(intf.getName(), ir.getContract(), false, true);
			}
		};
	}
}