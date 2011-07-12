/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.classdiagram;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gef.EditPartFactory;
import org.topcased.modeler.editor.IConfiguration;
import org.topcased.modeler.editor.ICreationUtils;
import org.topcased.modeler.editor.IPaletteManager;
import org.topcased.modeler.graphconf.DiagramGraphConf;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.alldiagram.edit.CommentEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ConstraintEditPart;
import org.topcased.modeler.uml.alldiagram.edit.DependencyEditPart;
import org.topcased.modeler.uml.alldiagram.edit.ElementEditPart;
import org.topcased.modeler.uml.alldiagram.edit.NamedElementEditPart;
import org.topcased.modeler.uml.alldiagram.edit.PackageEditPart;
import org.topcased.modeler.uml.alldiagram.edit.PackageImportEditPart;
import org.topcased.modeler.uml.alldiagram.edit.PackageMergeEditPart;
import org.topcased.modeler.uml.alldiagram.edit.UsageEditPart;
import org.topcased.modeler.uml.classdiagram.edit.AnyReceiveEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.AssociationClassEditPart;
import org.topcased.modeler.uml.classdiagram.edit.AssociationEditPart;
import org.topcased.modeler.uml.classdiagram.edit.BehavioredClassifierEditPart;
import org.topcased.modeler.uml.classdiagram.edit.CallEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ChangeEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ClassEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ClassifierEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ClassifierTemplateParameterEditPart;
import org.topcased.modeler.uml.classdiagram.edit.CreationEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.DataTypeEditPart;
import org.topcased.modeler.uml.classdiagram.edit.DestructionEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.EnumerationEditPart;
import org.topcased.modeler.uml.classdiagram.edit.EnumerationLiteralEditPart;
import org.topcased.modeler.uml.classdiagram.edit.GeneralizationEditPart;
import org.topcased.modeler.uml.classdiagram.edit.InstanceSpecificationEditPart;
import org.topcased.modeler.uml.classdiagram.edit.InterfaceEditPart;
import org.topcased.modeler.uml.classdiagram.edit.InterfaceRealizationEditPart;
import org.topcased.modeler.uml.classdiagram.edit.OperationEditPart;
import org.topcased.modeler.uml.classdiagram.edit.PrimitiveTypeEditPart;
import org.topcased.modeler.uml.classdiagram.edit.PropertyEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ReceiveOperationEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ReceiveSignalEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.ReceptionEditPart;
import org.topcased.modeler.uml.classdiagram.edit.RedefinableTemplateSignatureEditPart;
import org.topcased.modeler.uml.classdiagram.edit.SendOperationEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.SendSignalEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.SignalEditPart;
import org.topcased.modeler.uml.classdiagram.edit.SignalEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.SlotEditPart;
import org.topcased.modeler.uml.classdiagram.edit.TemplateBindingEditPart;
import org.topcased.modeler.uml.classdiagram.edit.TemplateParameterEditPart;
import org.topcased.modeler.uml.classdiagram.edit.TemplateSignatureEditPart;
import org.topcased.modeler.uml.classdiagram.edit.TimeEventEditPart;
import org.topcased.modeler.uml.classdiagram.edit.TypeEditPart;

/**
 * A diagram configuration : manages Palette, EditPartFactory for this diagram. <br>
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class ClassConfiguration implements IConfiguration
{
    private ClassPaletteManager paletteManager;

    private ClassEditPartFactory editPartFactory;

    private ClassCreationUtils creationUtils;

    /**
     * The DiagramGraphConf that contains graphical informations on the configuration
     */
    private DiagramGraphConf diagramGraphConf;

    /**
     * Constructor. Initialize Adapter factories.
     * 
     * @generated
     */
    public ClassConfiguration()
    {
        registerAdapters();
    }

    /**
     * Registers the Adapter Factories for all the EditParts
     * 
     * @generated
     */
    private void registerAdapters()
    {
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ElementEditPart.class, org.eclipse.uml2.uml.Element.class), ElementEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(NamedElementEditPart.class, org.eclipse.uml2.uml.NamedElement.class), NamedElementEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(TypeEditPart.class, org.eclipse.uml2.uml.Type.class), TypeEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ClassifierEditPart.class, org.eclipse.uml2.uml.Classifier.class), ClassifierEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(BehavioredClassifierEditPart.class, org.eclipse.uml2.uml.BehavioredClassifier.class),
                BehavioredClassifierEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PackageEditPart.class, org.eclipse.uml2.uml.Package.class), PackageEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ClassEditPart.class, org.eclipse.uml2.uml.Class.class), ClassEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(InterfaceEditPart.class, org.eclipse.uml2.uml.Interface.class), InterfaceEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(DataTypeEditPart.class, org.eclipse.uml2.uml.DataType.class), DataTypeEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(OperationEditPart.class, org.eclipse.uml2.uml.Operation.class), OperationEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PropertyEditPart.class, org.eclipse.uml2.uml.Property.class), PropertyEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(InstanceSpecificationEditPart.class, org.eclipse.uml2.uml.InstanceSpecification.class),
                InstanceSpecificationEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(DependencyEditPart.class, org.eclipse.uml2.uml.Dependency.class), DependencyEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(UsageEditPart.class, org.eclipse.uml2.uml.Usage.class), UsageEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(AssociationEditPart.class, org.eclipse.uml2.uml.Association.class), AssociationEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(GeneralizationEditPart.class, org.eclipse.uml2.uml.Generalization.class), GeneralizationEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(InterfaceRealizationEditPart.class, org.eclipse.uml2.uml.InterfaceRealization.class),
                InterfaceRealizationEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(TemplateSignatureEditPart.class, org.eclipse.uml2.uml.TemplateSignature.class), TemplateSignatureEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(TemplateParameterEditPart.class, org.eclipse.uml2.uml.TemplateParameter.class), TemplateParameterEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(RedefinableTemplateSignatureEditPart.class, org.eclipse.uml2.uml.RedefinableTemplateSignature.class),
                RedefinableTemplateSignatureEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ClassifierTemplateParameterEditPart.class, org.eclipse.uml2.uml.ClassifierTemplateParameter.class),
                ClassifierTemplateParameterEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(TemplateBindingEditPart.class, org.eclipse.uml2.uml.TemplateBinding.class),
                TemplateBindingEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(CommentEditPart.class, org.eclipse.uml2.uml.Comment.class), CommentEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(SlotEditPart.class, org.eclipse.uml2.uml.Slot.class), SlotEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(EnumerationEditPart.class, org.eclipse.uml2.uml.Enumeration.class), EnumerationEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(EnumerationLiteralEditPart.class, org.eclipse.uml2.uml.EnumerationLiteral.class),
                EnumerationLiteralEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PrimitiveTypeEditPart.class, org.eclipse.uml2.uml.PrimitiveType.class), PrimitiveTypeEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PackageImportEditPart.class, org.eclipse.uml2.uml.PackageImport.class), PackageImportEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(PackageMergeEditPart.class, org.eclipse.uml2.uml.PackageMerge.class), PackageMergeEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(AssociationClassEditPart.class, org.eclipse.uml2.uml.AssociationClass.class), AssociationClassEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ConstraintEditPart.class, org.eclipse.uml2.uml.Constraint.class), ConstraintEditPart.class);
        Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(SignalEditPart.class, org.eclipse.uml2.uml.Signal.class), SignalEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ReceptionEditPart.class, org.eclipse.uml2.uml.Reception.class), ReceptionEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(CreationEventEditPart.class,org.eclipse.uml2.uml.CreationEvent.class),CreationEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(DestructionEventEditPart.class,org.eclipse.uml2.uml.DestructionEvent.class),DestructionEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(SendOperationEventEditPart.class,org.eclipse.uml2.uml.SendOperationEvent.class),SendOperationEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(SendSignalEventEditPart.class,org.eclipse.uml2.uml.SendSignalEvent.class),SendSignalEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ReceiveOperationEventEditPart.class,org.eclipse.uml2.uml.ReceiveOperationEvent.class),ReceiveOperationEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ReceiveSignalEventEditPart.class,org.eclipse.uml2.uml.ReceiveSignalEvent.class),ReceiveSignalEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(CallEventEditPart.class,org.eclipse.uml2.uml.CallEvent.class),CallEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(AnyReceiveEventEditPart.class,org.eclipse.uml2.uml.AnyReceiveEvent.class),AnyReceiveEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(ChangeEventEditPart.class,org.eclipse.uml2.uml.ChangeEvent.class),ChangeEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(SignalEventEditPart.class,org.eclipse.uml2.uml.SignalEvent.class),SignalEventEditPart.class);
		Platform.getAdapterManager().registerAdapters(new EditPart2ModelAdapterFactory(TimeEventEditPart.class,org.eclipse.uml2.uml.TimeEvent.class),TimeEventEditPart.class);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getId()
     * @generated
     */
    public String getId()
    {
        return new String("org.topcased.modeler.uml.classdiagram");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getName()
     * @generated
     */
    public String getName()
    {
        return new String("Class Diagram");
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getEditPartFactory()
     * @generated
     */
    public EditPartFactory getEditPartFactory()
    {
        if (editPartFactory == null)
        {
            editPartFactory = new ClassEditPartFactory();
        }

        return editPartFactory;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getPaletteManager()
     * @generated
     */
    public IPaletteManager getPaletteManager()
    {
        if (paletteManager == null)
        {
            paletteManager = new ClassPaletteManager(getCreationUtils());
        }

        return paletteManager;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.IConfiguration#getCreationUtils()
     * @generated
     */
    public ICreationUtils getCreationUtils()
    {
        if (creationUtils == null)
        {
            creationUtils = new ClassCreationUtils(getDiagramGraphConf());
        }

        return creationUtils;
    }

    /**
     * @see org.topcased.modeler.editor.IConfiguration#getDiagramGraphConf()
     */
    public DiagramGraphConf getDiagramGraphConf()
    {
        if (diagramGraphConf == null)
        {
            URI fileURI = URI.createURI(UMLPlugin.getDefault().getBundle().getResource("org/topcased/modeler/uml/classdiagram/diagram.graphconf").toString());
            ResourceSet resourceSet = new ResourceSetImpl();
            Resource resource = resourceSet.getResource(fileURI, true);
            if (resource != null && resource.getContents().get(0) instanceof DiagramGraphConf)
            {
                diagramGraphConf = (DiagramGraphConf) resource.getContents().get(0);
            }
        }

        return diagramGraphConf;
    }

}