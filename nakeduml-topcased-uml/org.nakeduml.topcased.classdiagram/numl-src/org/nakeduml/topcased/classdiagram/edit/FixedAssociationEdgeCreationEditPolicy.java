package org.nakeduml.topcased.classdiagram.edit;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditDomain;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.classdiagram.commands.AssociationEdgeCreationCommand;
import org.topcased.modeler.uml.classdiagram.policies.AssociationEdgeCreationEditPolicy;

public final class FixedAssociationEdgeCreationEditPolicy extends AssociationEdgeCreationEditPolicy{
	protected CreateTypedEdgeCommand createCommand(EditDomain domain,GraphEdge edge,GraphElement source){
		return new AssociationEdgeCreationCommand(domain, edge, source){
			protected EList getAssociationContainerList(){
				if(getContainerEObject() instanceof Package){
					Package pack = (Package) getContainerEObject();
					return pack.getPackagedElements();
				}else{
					Namespace ns = sourceObject.getNamespace();
					while(true){
						if(ns instanceof Component){
							return ((Component) ns).getPackagedElements();
						}else if(ns instanceof Package){
							return ((Package) ns).getPackagedElements();
						}else{
							ns = ns.getNamespace();
						}
					}
				}
			}
		};
	}
}