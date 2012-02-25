package org.opaeum.papyrus.usecasediagram.providers;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.AbstractionEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.AbstractionNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorAppliedStereotypeEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorAsRectangleEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorAsRectangleNameEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorInComponentAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorInComponentEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorInComponentNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorInPackageAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorInPackageEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorInPackageNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ActorNameEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.AppliedStereotypeAbstractionEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.AppliedStereotypePackageMergeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.AppliedStereotypeUsageEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.AssociationAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.AssociationEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.AssociationNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.CommentAnnotatedElementEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.CommentBodyEditPartCN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.CommentBodyEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.CommentEditPartCN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.CommentEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ComponentEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ComponentInComponentEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ComponentInComponentNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ComponentInPackageEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ComponentInPackageNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ComponentNameEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ComponentUsecases2EditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ComponentUsecases3EditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ComponentUsecasesEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ConstraintConstrainedElementEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ConstraintEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ConstraintInComponentEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ConstraintInComponentNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ConstraintInPackageEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ConstraintInPackageNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ConstraintNameEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.DependencyAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.DependencyEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.DependencyNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.DiagramNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ExtendAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ExtendEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ExtendsLink_fixedEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ExtensionPointEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ExtensionPointInRectangleEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.GeneralizationAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.GeneralizationEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.IncludeAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.IncludeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.IncludeLink_fixedEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.PackageEditPartCN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.PackageEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.PackageImportAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.PackageImportEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.PackageMergeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.PackageNameEditPartCN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.PackageNameEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.PackagePackageableElementCompartment2EditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.PackagePackageableElementCompartmentEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.RealizationAppliedStereotypeEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.RealizationEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.RealizationNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.ShortCutDiagramEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UMLEditPartFactory;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UsageEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UsageNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCaseAsRectangleEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCaseAsRectangleNameEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCaseDiagramEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCaseEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCaseInComponentEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCaseInComponentNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCaseInPackageEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCaseInPackageNameEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCaseNameEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCasePointsEditPartTN;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCasePointsInComponentEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCasePointsInPackageEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.edit.parts.UseCasePointsInRectangleEditPart;
import org.eclipse.papyrus.uml.diagram.usecase.part.UMLDiagramEditorPlugin;
import org.eclipse.papyrus.uml.diagram.usecase.part.UMLVisualIDRegistry;

public class UMLEditPartProvider extends org.eclipse.papyrus.uml.diagram.usecase.providers.UMLEditPartProvider{
	public UMLEditPartProvider(){
		setFactory(new UMLEditPartFactory(){
			@Override
			public EditPart createEditPart(EditPart context,Object model){
				if(model instanceof View){
					View view = (View) model;
					switch(UMLVisualIDRegistry.getVisualID(view)){
					case UseCaseDiagramEditPart.VISUAL_ID:
						return new UseCaseDiagramEditPart(view);
					case ActorEditPartTN.VISUAL_ID:
						return new ActorEditPartTN(view){
							@Override
							protected void refreshVisuals(){
								super.refreshVisuals();
							}
							@Override
							protected IFigure createNodeShape(){
								return primaryShape = new ImageFigure(UMLDiagramEditorPlugin.getInstance().getBundledImage("images/BusinessActor.jpg"));
							}
						};
					case ActorNameEditPartTN.VISUAL_ID:
						return new ActorNameEditPartTN(view){
							@Override
							protected void refreshVisuals(){
								super.refreshVisuals();
							}
						};
					case ActorAppliedStereotypeEditPartTN.VISUAL_ID:
						return new ActorAppliedStereotypeEditPartTN(view){
							@Override
							protected void refreshVisuals(){
								super.refreshVisuals();
								getFigure().setVisible(true);
								setLabelTextHelper(getFigure(), "<<Business Actor>>");
							}
						};
					case ActorAsRectangleEditPartTN.VISUAL_ID:
						return new ActorAsRectangleEditPartTN(view);
					case ActorAsRectangleNameEditPartTN.VISUAL_ID:
						return new ActorAsRectangleNameEditPartTN(view);
					case UseCaseEditPartTN.VISUAL_ID:
						return new UseCaseEditPartTN(view);
					case UseCaseNameEditPartTN.VISUAL_ID:
						return new UseCaseNameEditPartTN(view);
					case UseCaseAsRectangleEditPartTN.VISUAL_ID:
						return new UseCaseAsRectangleEditPartTN(view);
					case UseCaseAsRectangleNameEditPartTN.VISUAL_ID:
						return new UseCaseAsRectangleNameEditPartTN(view);
					case ComponentEditPartTN.VISUAL_ID:
						return new ComponentEditPartTN(view);
					case ComponentNameEditPartTN.VISUAL_ID:
						return new ComponentNameEditPartTN(view);
					case PackageEditPartTN.VISUAL_ID:
						return new PackageEditPartTN(view);
					case PackageNameEditPartTN.VISUAL_ID:
						return new PackageNameEditPartTN(view);
					case ConstraintEditPartTN.VISUAL_ID:
						return new ConstraintEditPartTN(view);
					case ConstraintNameEditPartTN.VISUAL_ID:
						return new ConstraintNameEditPartTN(view);
					case CommentEditPartTN.VISUAL_ID:
						return new CommentEditPartTN(view);
					case CommentBodyEditPartTN.VISUAL_ID:
						return new CommentBodyEditPartTN(view);
					case ShortCutDiagramEditPart.VISUAL_ID:
						return new ShortCutDiagramEditPart(view);
					case DiagramNameEditPart.VISUAL_ID:
						return new DiagramNameEditPart(view);
					case ExtensionPointEditPart.VISUAL_ID:
						return new ExtensionPointEditPart(view);
					case ExtensionPointInRectangleEditPart.VISUAL_ID:
						return new ExtensionPointInRectangleEditPart(view);
					case UseCaseInComponentEditPart.VISUAL_ID:
						return new UseCaseInComponentEditPart(view);
					case UseCaseInComponentNameEditPart.VISUAL_ID:
						return new UseCaseInComponentNameEditPart(view);
					case ComponentInComponentEditPart.VISUAL_ID:
						return new ComponentInComponentEditPart(view);
					case ComponentInComponentNameEditPart.VISUAL_ID:
						return new ComponentInComponentNameEditPart(view);
					case CommentEditPartCN.VISUAL_ID:
						return new CommentEditPartCN(view);
					case CommentBodyEditPartCN.VISUAL_ID:
						return new CommentBodyEditPartCN(view);
					case ConstraintInComponentEditPart.VISUAL_ID:
						return new ConstraintInComponentEditPart(view);
					case ConstraintInComponentNameEditPart.VISUAL_ID:
						return new ConstraintInComponentNameEditPart(view);
					case ActorInComponentEditPart.VISUAL_ID:
						return new ActorInComponentEditPart(view);
					case ActorInComponentNameEditPart.VISUAL_ID:
						return new ActorInComponentNameEditPart(view);
					case ActorInComponentAppliedStereotypeEditPart.VISUAL_ID:
						return new ActorInComponentAppliedStereotypeEditPart(view);
					case ConstraintInPackageEditPart.VISUAL_ID:
						return new ConstraintInPackageEditPart(view);
					case ConstraintInPackageNameEditPart.VISUAL_ID:
						return new ConstraintInPackageNameEditPart(view);
					case ActorInPackageEditPart.VISUAL_ID:
						return new ActorInPackageEditPart(view);
					case ActorInPackageNameEditPart.VISUAL_ID:
						return new ActorInPackageNameEditPart(view);
					case ActorInPackageAppliedStereotypeEditPart.VISUAL_ID:
						return new ActorInPackageAppliedStereotypeEditPart(view);
					case UseCaseInPackageEditPart.VISUAL_ID:
						return new UseCaseInPackageEditPart(view);
					case UseCaseInPackageNameEditPart.VISUAL_ID:
						return new UseCaseInPackageNameEditPart(view);
					case ComponentInPackageEditPart.VISUAL_ID:
						return new ComponentInPackageEditPart(view);
					case ComponentInPackageNameEditPart.VISUAL_ID:
						return new ComponentInPackageNameEditPart(view);
					case PackageEditPartCN.VISUAL_ID:
						return new PackageEditPartCN(view);
					case PackageNameEditPartCN.VISUAL_ID:
						return new PackageNameEditPartCN(view);
					case UseCasePointsEditPartTN.VISUAL_ID:
						return new UseCasePointsEditPartTN(view);
					case UseCasePointsInRectangleEditPart.VISUAL_ID:
						return new UseCasePointsInRectangleEditPart(view);
					case ComponentUsecasesEditPart.VISUAL_ID:
						return new ComponentUsecasesEditPart(view);
					case UseCasePointsInComponentEditPart.VISUAL_ID:
						return new UseCasePointsInComponentEditPart(view);
					case ComponentUsecases2EditPart.VISUAL_ID:
						return new ComponentUsecases2EditPart(view);
					case UseCasePointsInPackageEditPart.VISUAL_ID:
						return new UseCasePointsInPackageEditPart(view);
					case ComponentUsecases3EditPart.VISUAL_ID:
						return new ComponentUsecases3EditPart(view);
					case PackagePackageableElementCompartment2EditPart.VISUAL_ID:
						return new PackagePackageableElementCompartment2EditPart(view);
					case PackagePackageableElementCompartmentEditPart.VISUAL_ID:
						return new PackagePackageableElementCompartmentEditPart(view);
					case IncludeEditPart.VISUAL_ID:
						return new IncludeEditPart(view);
					case IncludeLink_fixedEditPart.VISUAL_ID:
						return new IncludeLink_fixedEditPart(view);
					case IncludeAppliedStereotypeEditPart.VISUAL_ID:
						return new IncludeAppliedStereotypeEditPart(view);
					case ExtendEditPart.VISUAL_ID:
						return new ExtendEditPart(view);
					case ExtendsLink_fixedEditPart.VISUAL_ID:
						return new ExtendsLink_fixedEditPart(view);
					case ExtendAppliedStereotypeEditPart.VISUAL_ID:
						return new ExtendAppliedStereotypeEditPart(view);
					case GeneralizationEditPart.VISUAL_ID:
						return new GeneralizationEditPart(view);
					case GeneralizationAppliedStereotypeEditPart.VISUAL_ID:
						return new GeneralizationAppliedStereotypeEditPart(view);
					case AssociationEditPart.VISUAL_ID:
						return new AssociationEditPart(view);
					case AssociationNameEditPart.VISUAL_ID:
						return new AssociationNameEditPart(view);
					case AssociationAppliedStereotypeEditPart.VISUAL_ID:
						return new AssociationAppliedStereotypeEditPart(view);
					case ConstraintConstrainedElementEditPart.VISUAL_ID:
						return new ConstraintConstrainedElementEditPart(view);
					case DependencyEditPart.VISUAL_ID:
						return new DependencyEditPart(view);
					case DependencyNameEditPart.VISUAL_ID:
						return new DependencyNameEditPart(view);
					case DependencyAppliedStereotypeEditPart.VISUAL_ID:
						return new DependencyAppliedStereotypeEditPart(view);
					case CommentAnnotatedElementEditPart.VISUAL_ID:
						return new CommentAnnotatedElementEditPart(view);
					case AbstractionEditPart.VISUAL_ID:
						return new AbstractionEditPart(view);
					case AbstractionNameEditPart.VISUAL_ID:
						return new AbstractionNameEditPart(view);
					case AppliedStereotypeAbstractionEditPart.VISUAL_ID:
						return new AppliedStereotypeAbstractionEditPart(view);
					case UsageEditPart.VISUAL_ID:
						return new UsageEditPart(view);
					case UsageNameEditPart.VISUAL_ID:
						return new UsageNameEditPart(view);
					case AppliedStereotypeUsageEditPart.VISUAL_ID:
						return new AppliedStereotypeUsageEditPart(view);
					case RealizationEditPart.VISUAL_ID:
						return new RealizationEditPart(view);
					case RealizationNameEditPart.VISUAL_ID:
						return new RealizationNameEditPart(view);
					case RealizationAppliedStereotypeEditPart.VISUAL_ID:
						return new RealizationAppliedStereotypeEditPart(view);
					case PackageMergeEditPart.VISUAL_ID:
						return new PackageMergeEditPart(view);
					case AppliedStereotypePackageMergeEditPart.VISUAL_ID:
						return new AppliedStereotypePackageMergeEditPart(view);
					case PackageImportEditPart.VISUAL_ID:
						return new PackageImportEditPart(view);
					case PackageImportAppliedStereotypeEditPart.VISUAL_ID:
						return new PackageImportAppliedStereotypeEditPart(view);
					}
				}
				return super.createEditPart(context, model);
			}
		});
	}
}
