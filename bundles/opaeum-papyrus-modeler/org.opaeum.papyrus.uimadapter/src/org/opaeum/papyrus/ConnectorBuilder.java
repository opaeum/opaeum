package org.opaeum.papyrus;

import java.util.Map;

import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AppliedStereotypeAssociationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AppliedStereotypePackageImportEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AssociationEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AssociationMultiplicitySourceEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AssociationMultiplicityTargetEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AssociationNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AssociationSourceNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.AssociationTargetNameEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.ModelEditPart;
import org.eclipse.papyrus.uml.diagram.clazz.edit.parts.PackageImportEditPart;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.util.UMLSwitch;

public class ConnectorBuilder extends UMLSwitch<Connector>{
	private String diagramId;
	private Map<Element,Shape> shapes;
	public ConnectorBuilder(String diagramId,Map<Element,Shape> shapes){
		this.diagramId = diagramId;
		this.shapes = shapes;
	}
	@Override
	public Connector caseAssociation(Association ass){
		if(diagramId.equals(ModelEditPart.MODEL_ID)){
			if(ass.getEndTypes().size() == 2){
				Shape shape0 = shapes.get(ass.getEndTypes().get(0));
				Shape shape1 = shapes.get(ass.getEndTypes().get(1));
				if(shape0 != null && shape1 != null){
					Connector associationConnector = NotationFactory.eINSTANCE.createConnector();
					associationConnector.setType(AssociationEditPart.VISUAL_ID + "");
					associationConnector.setSource(shape0);
					associationConnector.setTarget(shape1);
					addDecoration(associationConnector, AppliedStereotypeAssociationEditPart.VISUAL_ID + "");
					addDecoration(associationConnector, AssociationNameEditPart.VISUAL_ID + "");
					addDecoration(associationConnector, AssociationTargetNameEditPart.VISUAL_ID + "",20, 20);
					addDecoration(associationConnector, AssociationSourceNameEditPart.VISUAL_ID + "",-20, -20);
					addDecoration(associationConnector, AssociationMultiplicityTargetEditPart.VISUAL_ID + "", 20, 20);
					addDecoration(associationConnector, AssociationMultiplicitySourceEditPart.VISUAL_ID + "", -20, -20);
					associationConnector.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
					associationConnector.setElement(ass);
					associationConnector.setBendpoints(NotationFactory.eINSTANCE.createRelativeBendpoints());
					return associationConnector;
				}
			}else{
				System.out.println(ass.getQualifiedName());
			}
		}
		return super.caseAssociation(ass);
	}
	@Override
	public Connector casePackageImport(PackageImport pi){
		if(diagramId.equals(ModelEditPart.MODEL_ID)){
			if(pi.getImportedPackage() != null && pi.getImportingNamespace() != null){
				Shape shape0 = shapes.get(pi.getImportingNamespace());
				Shape shape1 = shapes.get(pi.getImportedPackage());
				if(shape0 != null && shape1 != null){
					Connector connector = NotationFactory.eINSTANCE.createConnector();
					connector.setType(PackageImportEditPart.VISUAL_ID + "");
					connector.setSource(shape0);
					connector.setTarget(shape1);
					addDecoration(connector, AppliedStereotypePackageImportEditPart.VISUAL_ID + "");
					// addDecoration(connector, PackageImportNameEditPart.VISUAL_ID + "");
					// addDecoration(connector, AssociationTargetNameEditPart.VISUAL_ID + "");
					// addDecoration(connector, AssociationSourceNameEditPart.VISUAL_ID + "");
					// addDecoration(connector, AssociationMultiplicityTargetEditPart.VISUAL_ID + "", 20, 20);
					// addDecoration(connector, AssociationMultiplicitySourceEditPart.VISUAL_ID + "", -20, -20);
					connector.getStyles().add(NotationFactory.eINSTANCE.createFontStyle());
					connector.setElement(pi);
					connector.setBendpoints(NotationFactory.eINSTANCE.createRelativeBendpoints());
					return connector;
				}
			}
		}
		return super.casePackageImport(pi);
	}
	private void addDecoration(Connector associationConnector,String d1Id){
		DecorationNode d1 = NotationFactory.eINSTANCE.createDecorationNode();
		d1.setType(d1Id);
		d1.setVisible(false);
		associationConnector.getPersistedChildren().add(d1);
		Bounds bnds = NotationFactory.eINSTANCE.createBounds();
		bnds.setY(20);
		d1.setLayoutConstraint(bnds);
	}
	private void addDecoration(Connector associationConnector,String d1Id,int x,int y){
		DecorationNode d1 = NotationFactory.eINSTANCE.createDecorationNode();
		d1.setType(d1Id);
		d1.setVisible(true);
		associationConnector.getPersistedChildren().add(d1);
		Bounds bnds = NotationFactory.eINSTANCE.createBounds();
		bnds.setY(y);
		bnds.setX(x);
		d1.setLayoutConstraint(bnds);
	}
}
