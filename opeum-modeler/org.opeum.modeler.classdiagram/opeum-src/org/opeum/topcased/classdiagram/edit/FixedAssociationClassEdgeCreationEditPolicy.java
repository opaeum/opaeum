package org.opeum.topcased.classdiagram.edit;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.EditDomain;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Type;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.classdiagram.commands.AssociationClassEdgeCreationCommand;
import org.topcased.modeler.uml.classdiagram.policies.AssociationClassEdgeCreationEditPolicy;
import org.topcased.modeler.utils.LabelHelper;
import org.topcased.modeler.utils.Utils;

public final class FixedAssociationClassEdgeCreationEditPolicy extends AssociationClassEdgeCreationEditPolicy{
	protected CreateTypedEdgeCommand createCommand(EditDomain domain,GraphEdge edge,GraphElement source){
		return new AssociationClassEdgeCreationCommand(domain, edge, source){
			protected void redoModel(){
				Type sourceObject = getSourceObject();
				Namespace ns = sourceObject.getNamespace();
				EList<PackageableElement> pe = getPackagedElements(ns);
				AssociationClass associationClass = getAssociationClass();
				pe.add(associationClass);
				// init the name of the association contained by the package
				String curName = LabelHelper.INSTANCE.getName(getEditDomain(), associationClass);
				if(curName == null || curName.length() == 0){
					LabelHelper.INSTANCE.initName(getEditDomain(), Utils.getElement(getEdge()).eContainer(), associationClass);
				}
			}
			protected AssociationClass getAssociationClass(){
				AssociationClass associationClass = (AssociationClass) Utils.getElement(getEdge());
				return associationClass;
			}
			protected EList<PackageableElement> getPackagedElements(Namespace ns){
				EList<PackageableElement> pe=null;
				while(ns != null){
					if(ns instanceof Component){
						pe = ((Component) ns).getPackagedElements();
						break;
					}else if(ns instanceof Package){
						pe = ((Package) ns).getPackagedElements();
						break;
					}else{
						ns = ns.getNamespace();
					}
				}
				return pe;
			}
			protected Type getSourceObject(){
				Type sourceObject = (Type) Utils.getElement(getSource());
				return sourceObject;
			}
			/**
			 * @generated
			 */
			protected void undoModel(){
				// Remove the association from the package
				getPackagedElements(getSourceObject().getNamespace()).remove(getAssociationClass());
			}
		};
	}
}