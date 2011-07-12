/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalevée (Anyware Technologies) - initial API and implementation 
 ******************************************************************************/

package org.topcased.modeler.uml.usecasediagram;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.PackageMerge;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EdgeObjectOffset;
import org.topcased.modeler.di.model.EdgeObjectUV;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.edit.DynamicInstanceEditPartController;
import org.topcased.modeler.editor.AbstractCreationUtils;
import org.topcased.modeler.graphconf.DiagramGraphConf;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.alldiagram.AllEdgeObjectConstants;
import org.topcased.modeler.uml.alldiagram.ExactUMLSwitch;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.uml.classdiagram.ClassEdgeObjectConstants;
import org.topcased.modeler.uml.usecasediagram.preferences.UseCaseDiagramPreferenceConstants;

/**
 * This utility class allows to create a GraphElement associated with a Model Object <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class UseCaseCreationUtils extends AbstractCreationUtils
{

    private static final int LABEL_OFFSET = 10;

    /**
     * Constructor
     * 
     * @param diagramConf the Diagram Graphical Configuration
     */
    public UseCaseCreationUtils(DiagramGraphConf diagramConf)
    {
        super(diagramConf);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private class GraphicSwitch extends ExactUMLSwitch
    {
        /** The presentation of the graphical element */
        private String presentation;

        /**
         * Constructor
         * 
         * @param presentation the presentation of the graphical element
         */
        public GraphicSwitch(String presentation)
        {
            this.presentation = presentation;
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#casePackage(org.eclipse.uml2.uml.Package)
         */
        public Object casePackage(Package object)
        {
            return createGraphElementPackage(object, presentation);
        }
        
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#casePackageMerge(org.eclipse.uml2.uml.PackageMerge)
         */
        public Object casePackageMerge(PackageMerge object)
        {
            return createGraphElementPackageMerge(object, presentation);
        }
        
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#casePackageImport(org.eclipse.uml2.uml.PackageImport)
         */
        public Object casePackageImport(PackageImport object)
        {
            return createGraphElementPackageImport(object, presentation);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseActor(org.eclipse.uml2.uml.Actor)
         */
        public Object caseActor(Actor object)
        {
            return createGraphElementActor(object, presentation);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseUseCase(org.eclipse.uml2.uml.UseCase)
         */
        public Object caseUseCase(UseCase object)
        {
            return createGraphElementUseCase(object, presentation);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseAssociation(org.eclipse.uml2.uml.Association)
         */
        public Object caseAssociation(Association object)
        {
            return createGraphElementAssociation(object, presentation);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseGeneralization(org.eclipse.uml2.uml.Generalization)
         */
        public Object caseGeneralization(Generalization object)
        {
            return createGraphElementGeneralization(object, presentation);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseInclude(org.eclipse.uml2.uml.Include)
         */
        public Object caseInclude(Include object)
        {
            return createGraphElementInclude(object, presentation);
        }

        
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseExtend(org.eclipse.uml2.uml.Extend)
         */
        public Object caseExtend(Extend object)
        {
            return createGraphElementExtend(object, presentation);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseComment(org.eclipse.uml2.uml.Comment)
         */
        public Object caseComment(Comment object)
        {
            return createGraphElementComment(object, presentation);
        }
        
        /**
         * Add Constraint to requirement diagram
         */
        @Override
        public Object caseConstraint(Constraint object)
        {
            return createGraphElementConstraint(object, presentation);
        }
        
        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseDependency(org.eclipse.uml2.uml.Dependency)
         * @generated
         */
        @Override
        public Object caseDependency(Dependency object)
        {
           return createGraphElementDependency(object, presentation);
        }
        
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
         */
        public Object defaultCase(EObject object)
        {
            return null;
        }
        
        
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.ICreationUtils#createGraphElement(org.eclipse.emf.ecore.EObject,
     *      java.lang.String)
     * @generated
     */
    public GraphElement createGraphElement(EObject obj, String presentation)
    {
        Object graphElt = new GraphicSwitch(presentation).doSwitch(obj);
        if (graphElt == null || graphElt == obj)
        {
        	// Use to dran'n drop custom element defined in 
        	// the extension point org.topcased.modeler.customEditPart 
        	graphElt = DynamicInstanceEditPartController.instance.createGraphElement(obj, presentation);
        }
        if (graphElt instanceof GraphElement)
        {
            return (GraphElement) graphElt;
        }

        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementPackage(Package element, String presentation)
    {
        return createGraphNode(element, presentation);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementActor(Actor element, String presentation)
    {
        return createGraphNode(element, presentation);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementUseCase(UseCase element, String presentation)
    {
        return createGraphNode(element, presentation);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated NOT
     */
    protected GraphElement createGraphElementAssociation(Association element, String presentation)
    {
        GraphEdge graphEdge = createGraphEdge(element, presentation);

        IPreferenceStore preferenceStore = getPreferenceStore();

        EdgeObjectUV srccountEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
        srccountEdgeObjectUV.setId(ClassEdgeObjectConstants.SRCCOUNT_EDGE_OBJECT_ID);
        srccountEdgeObjectUV.setUDistance(LABEL_OFFSET);
        srccountEdgeObjectUV.setVDistance(-LABEL_OFFSET);
        srccountEdgeObjectUV.setVisible(preferenceStore.getBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_SRCCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(srccountEdgeObjectUV);

        EdgeObjectUV targetcountEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
        targetcountEdgeObjectUV.setId(ClassEdgeObjectConstants.TARGETCOUNT_EDGE_OBJECT_ID);
        targetcountEdgeObjectUV.setUDistance(LABEL_OFFSET);
        targetcountEdgeObjectUV.setVDistance(-LABEL_OFFSET);
        targetcountEdgeObjectUV.setVisible(preferenceStore.getBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_TARGETCOUNT_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(targetcountEdgeObjectUV);

        EdgeObjectOffset nameEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
        nameEdgeObjectOffset.setId(UseCaseEdgeObjectConstants.NAME_EDGE_OBJECT_ID);
        nameEdgeObjectOffset.setOffset(new Dimension(0, 10));
        nameEdgeObjectOffset.setVisible(false);
        nameEdgeObjectOffset.setVisible(preferenceStore.getBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(nameEdgeObjectOffset);

        EdgeObjectOffset stereotypeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
        stereotypeEdgeObjectOffset.setId(UseCaseEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
        stereotypeEdgeObjectOffset.setOffset(new Dimension(0, -10));
        stereotypeEdgeObjectOffset.setVisible(preferenceStore.getBoolean(UseCaseDiagramPreferenceConstants.ASSOCIATION_USECASE_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(stereotypeEdgeObjectOffset);

        return graphEdge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementGeneralization(Generalization element, String presentation)
    {
        GraphEdge graphEdge = createGraphEdge(element, presentation);

        IPreferenceStore preferenceStore = getPreferenceStore();

        EdgeObjectOffset stereotypeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
        stereotypeEdgeObjectOffset.setId(UseCaseEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
        stereotypeEdgeObjectOffset.setOffset(new Dimension(0, 0));
        stereotypeEdgeObjectOffset.setVisible(preferenceStore.getBoolean(UseCaseDiagramPreferenceConstants.GENERALIZATION_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(stereotypeEdgeObjectOffset);
        return graphEdge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementInclude(Include element, String presentation)
    {
        GraphEdge graphEdge = createGraphEdge(element, presentation);

        IPreferenceStore preferenceStore = getPreferenceStore();

        EdgeObjectOffset nameEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
        nameEdgeObjectOffset.setId(UseCaseEdgeObjectConstants.NAME_EDGE_OBJECT_ID);
        nameEdgeObjectOffset.setOffset(new Dimension(0, 0));
        nameEdgeObjectOffset.setVisible(preferenceStore.getBoolean(UseCaseDiagramPreferenceConstants.INCLUDE_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(nameEdgeObjectOffset);
        return graphEdge;
    }
    
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated NOT
     */
    protected GraphElement createGraphElementDependency(org.eclipse.uml2.uml.Dependency element, String presentation)
    {
        GraphEdge graphEdge = createGraphEdge(element, presentation);
        EdgeObjectOffset stereotypeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
        stereotypeEdgeObjectOffset.setId(AllEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
        stereotypeEdgeObjectOffset.setOffset(new Dimension(0, 0));
        stereotypeEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(AllDiagramPreferenceConstants.DEPENDENCY_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(stereotypeEdgeObjectOffset);

        EdgeObjectOffset dependencynameEdgeObjectOffset =
        DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
        dependencynameEdgeObjectOffset.setId(AllEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID);
        dependencynameEdgeObjectOffset.setOffset(new Dimension(0, 0));
        dependencynameEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(AllDiagramPreferenceConstants.DEPENDENCY_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(dependencynameEdgeObjectOffset);
        return graphEdge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementExtend(Extend element, String presentation)
    {
        GraphEdge graphEdge = createGraphEdge(element, presentation);

        IPreferenceStore preferenceStore = getPreferenceStore();

        EdgeObjectOffset nameEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
        nameEdgeObjectOffset.setId(UseCaseEdgeObjectConstants.NAME_EDGE_OBJECT_ID);
        nameEdgeObjectOffset.setOffset(new Dimension(0, 0));
        nameEdgeObjectOffset.setVisible(preferenceStore.getBoolean(UseCaseDiagramPreferenceConstants.EXTEND_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(nameEdgeObjectOffset);
        EdgeObjectUV extensionEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
        extensionEdgeObjectUV.setId(UseCaseEdgeObjectConstants.EXTENSION_EDGE_OBJECT_ID);
        extensionEdgeObjectUV.setUDistance(0);
        extensionEdgeObjectUV.setVDistance(0);
        extensionEdgeObjectUV.setVisible(preferenceStore.getBoolean(UseCaseDiagramPreferenceConstants.EXTEND_EXTENSION_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(extensionEdgeObjectUV);
        return graphEdge;
    }
    
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementPackageImport(org.eclipse.uml2.uml.PackageImport element, String presentation)
    {
        GraphEdge graphEdge = createGraphEdge(element, presentation);
        EdgeObjectOffset visibilityEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
        visibilityEdgeObjectOffset.setId(UseCaseEdgeObjectConstants.VISIBILITY_EDGE_OBJECT_ID);
        visibilityEdgeObjectOffset.setOffset(new Dimension(0, 0));
        visibilityEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(AllDiagramPreferenceConstants.PACKAGEIMPORT_VISIBILITY_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(visibilityEdgeObjectOffset);
        return graphEdge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementPackageMerge(org.eclipse.uml2.uml.PackageMerge element, String presentation)
    {
        GraphEdge graphEdge = createGraphEdge(element, presentation);
        EdgeObjectOffset mergeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
        mergeEdgeObjectOffset.setId(UseCaseEdgeObjectConstants.MERGE_EDGE_OBJECT_ID);
        mergeEdgeObjectOffset.setOffset(new Dimension(0, 0));
        mergeEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(AllDiagramPreferenceConstants.PACKAGEMERGE_MERGE_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(mergeEdgeObjectOffset);
        return graphEdge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementComment(Comment element, String presentation)
    {
        return createGraphNode(element, presentation);
    }
    
    /**
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementConstraint(Constraint element, String presentation)
    {
        return createGraphNode(element, presentation);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private class ModelSwitch extends UMLSwitch
    {
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#casePackage(org.eclipse.uml2.uml.Package)
         */
        public Object casePackage(Package object)
        {
            return createModelObjectPackage(object);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseActor(org.eclipse.uml2.uml.Actor)
         */
        public Object caseActor(Actor object)
        {
            return createModelObjectActor(object);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseUseCase(org.eclipse.uml2.uml.UseCase)
         */
        public Object caseUseCase(UseCase object)
        {
            return createModelObjectUseCase(object);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseAssociation(org.eclipse.uml2.uml.Association)
         */
        public Object caseAssociation(Association object)
        {
            return createModelObjectAssociation(object);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseGeneralization(org.eclipse.uml2.uml.Generalization)
         */
        public Object caseGeneralization(Generalization object)
        {
            return createModelObjectGeneralization(object);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseInclude(org.eclipse.uml2.uml.Include)
         */
        public Object caseInclude(Include object)
        {
            return createModelObjectInclude(object);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseExtend(org.eclipse.uml2.uml.Extend)
         */
        public Object caseExtend(Extend object)
        {
            return createModelObjectExtend(object);
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseComment(org.eclipse.uml2.uml.Comment)
         */
        public Object caseComment(Comment object)
        {
            return createModelObjectComment(object);
        }

        /**
         * Add Constraint to requirement diagram
         */
        @Override
        public Object caseConstraint(Constraint object)
        {
            return createModelObjectConstraint(object);
        }
        
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#casePackageImport(org.eclipse.uml2.uml.PackageImport)
         */
        @Override
        public Object casePackageImport(PackageImport object)
        {
            return createModelObjectPackageImport(object);
        }
        
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#casePackageMerge(org.eclipse.uml2.uml.PackageMerge)
         */
        @Override
        public Object casePackageMerge(PackageMerge object)
        {
            return createModelObjectPackageMerge(object);
        }
        
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseDependency(Dependency)
         */
        @Override
        public Object caseDependency(Dependency object)
        {
        	return createModelObjectDependency(object);
        }
        
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
         */
        public Object defaultCase(EObject object)
        {
            return null;
        }
    }

    /**
     * Create the ModelObject with its initial children <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param obj the model object
     * @return the model object with its children
     * @generated
     */
    public EObject createModelObject(EObject obj)
    {
        Object eObject = new ModelSwitch().doSwitch(obj);
        return (EObject) eObject;
    }
    
    

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the original model element
     * @return the complete Model Object
     * @generated
     */
    protected Package createModelObjectPackage(Package element)
    {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the original model element
     * @return the complete Model Object
     * @generated
     */
    protected Actor createModelObjectActor(Actor element)
    {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the original model element
     * @return the complete Model Object
     * @generated
     */
    protected UseCase createModelObjectUseCase(UseCase element)
    {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the original model element
     * @return the complete Model Object
     * @generated
     */
    protected Association createModelObjectAssociation(Association element)
    {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the original model element
     * @return the complete Model Object
     * @generated
     */
    protected Generalization createModelObjectGeneralization(Generalization element)
    {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the original model element
     * @return the complete Model Object
     * @generated
     */
    protected Include createModelObjectInclude(Include element)
    {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the original model element
     * @return the complete Model Object
     * @generated
     */
    protected Extend createModelObjectExtend(Extend element)
    {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the original model element
     * @return the complete Model Object
     * @generated
     */
    protected Comment createModelObjectComment(Comment element)
    {
        return element;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the original model element
     * @return the complete Model Object
     * @generated
     */
    protected Constraint createModelObjectConstraint(Constraint element)
    {
        return element;
    }
    
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the original model element
     * @return the complete Model Object
     * @generated
     */
    protected PackageMerge createModelObjectPackageMerge(PackageMerge element)
    {
        return element;
    }
    
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the original model element
     * @return the complete Model Object
     * @generated
     */
    protected PackageImport createModelObjectPackageImport(PackageImport element)
    {
        return element;
    }
    
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the original model element
     * @return the complete Model Object
     * @generated
     */
    protected Dependency createModelObjectDependency(Dependency element)
    {
    	return element;
    }
    
    /**
     * Get the preference store associated with the current editor.
     * 
     * @return IPreferenceStore
     * @generated
     */
    private IPreferenceStore getPreferenceStore()
    {
        IEditorInput editorInput = UMLPlugin.getActivePage().getActiveEditor().getEditorInput();
        if (editorInput instanceof IFileEditorInput)
        {
            IProject project = ((IFileEditorInput) editorInput).getFile().getProject();
            Preferences root = Platform.getPreferencesService().getRootNode();
            try
            {
                if (root.node(ProjectScope.SCOPE).node(project.getName()).nodeExists(UMLPlugin.getId()))
                {
                    return new ScopedPreferenceStore(new ProjectScope(project), UMLPlugin.getId());
                }
            }
            catch (BackingStoreException e)
            {
                e.printStackTrace();
            }
        }
        return UMLPlugin.getDefault().getPreferenceStore();
    }
}
