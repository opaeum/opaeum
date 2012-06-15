package org.opaeum.topcased.classdiagram.edit;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gef.EditDomain;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.topcased.modeler.commands.CreateTypedEdgeCommand;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.uml.classdiagram.commands.AssociationEdgeCreationCommand;
import org.topcased.modeler.uml.classdiagram.policies.AssociationEdgeCreationEditPolicy;
import org.topcased.modeler.uml.classdiagram.util.AssociationHelper;
import org.topcased.modeler.utils.LabelHelper;
import org.topcased.modeler.utils.Utils;

public final class FixedAssociationEdgeCreationEditPolicy extends AssociationEdgeCreationEditPolicy{
	protected CreateTypedEdgeCommand createCommand(EditDomain domain,GraphEdge edge,GraphElement source){
		return new AssociationEdgeCreationCommand(domain, edge, source){
			@SuppressWarnings("rawtypes")
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
	    protected void redoModel()
	    {
	        // Add the Association to the current package
	        getAssociationContainerList().add(association);
	        
	        // init the name of the association contained by the package
	        String curName = LabelHelper.INSTANCE.getName(getEditDomain(), association);
	        if (curName == null || curName.length() == 0)
	        {
	            LabelHelper.INSTANCE.initName(getEditDomain(), Utils.getElement(getEdge()).eContainer(), association);
	        }
	    }

			@Override
			public void execute(){
				// TODO Auto-generated method stub
				// Create the association and add it to the current package
				association = (Association) Utils.getElement(getEdge());
				associationHelper = new AssociationHelper(association);
				// Retrieve source object and target object
				sourceObject = (Type) Utils.getElement(getSource());
				targetObject = (Type) Utils.getElement(getTarget());
				
				//To fix #1781 #1782
				if(isUpdateModel())
				{       	       
				  String lowSourceName = toLowcase(sourceObject.getName());
				  if( associationHelper.getAssociationFirstMemberEnd()!=null)
				  {
				      associationHelper.getAssociationFirstMemberEnd().setType(sourceObject);
				      associationHelper.getAssociationFirstMemberEnd().setName(lowSourceName);         
				  }
				  
				  String lowTargetName = toLowcase(targetObject.getName());
				  if(associationHelper.getAssociationSecondMemberEnd()!=null)
				  {
				      manageSecondmemberEnd(lowTargetName);
				  }
				
				  int cpt = associationHelper.findName(getEditDomain(), getContainerEObject(), association);
				  
				  association.setName(associationHelper.getAssociationName(cpt));
				  
				  // If the association is composite, then move the corresponding property to its classifier.
				}

        redo();
			}
		};
	}
}