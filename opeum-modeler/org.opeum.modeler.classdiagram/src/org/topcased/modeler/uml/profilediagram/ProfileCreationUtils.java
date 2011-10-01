/*******************************************************************************
 * Copyright (c) 2006, 2008 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.profilediagram;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.util.UMLSwitch;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.topcased.modeler.di.model.DiagramInterchangeFactory;
import org.topcased.modeler.di.model.EdgeObjectOffset;
import org.topcased.modeler.di.model.EdgeObjectUV;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.GraphNode;
import org.topcased.modeler.editor.AbstractCreationUtils;
import org.topcased.modeler.graphconf.DiagramGraphConf;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.alldiagram.AllEdgeObjectConstants;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.uml.profilediagram.preferences.ProfileDiagramPreferenceConstants;

/**
 * This utility class allows to create a GraphElement associated with a Model Object <!-- begin-user-doc --> <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ProfileCreationUtils extends AbstractCreationUtils
{

    /**
     * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param diagramConf the Diagram Graphical Configuration
     * @generated
     */
    public ProfileCreationUtils(DiagramGraphConf diagramConf)
    {
        super(diagramConf);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private class GraphicUMLSwitch extends UMLSwitch<GraphElement>
    {
        /** The presentation of the graphical element */
        private String presentation;

        /**
         * Constructor <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @param presentation the presentation of the graphical element
         * @generated
         */
        public GraphicUMLSwitch(String presentation)
        {
            this.presentation = presentation;
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseStereotype(org.eclipse.uml2.uml.Stereotype)
         * @generated
         */
        public GraphElement caseStereotype(org.eclipse.uml2.uml.Stereotype object)
        {
            if ("default".equals(presentation))
            {
                return createGraphElementStereotype(object, presentation);
            }
            return null;
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseProperty(org.eclipse.uml2.uml.Property)
         * @generated
         */
        public GraphElement caseProperty(org.eclipse.uml2.uml.Property object)
        {
            if ("default".equals(presentation))
            {
                return createGraphElementProperty(object, presentation);
            }
            return null;
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseElementImport(org.eclipse.uml2.uml.ElementImport)
         * @generated
         */
        public GraphElement caseElementImport(org.eclipse.uml2.uml.ElementImport object)
        {
            if ("default".equals(presentation))
            {
                return createGraphElementElementImport(object, presentation);
            }
            return null;
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseDependency(org.eclipse.uml2.uml.Dependency)
         * @generated
         */
        public GraphElement caseDependency(org.eclipse.uml2.uml.Dependency object)
        {
            if ("default".equals(presentation))
            {
                return createGraphElementDependency(object, presentation);
            }
            return null;
        }
        
        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseGeneralization(org.eclipse.uml2.uml.Generalization)
         * @generated
         */
        public GraphElement caseGeneralization(org.eclipse.uml2.uml.Generalization object)
        {
            if ("default".equals(presentation))
            {
                return createGraphElementGeneralization(object, presentation);
            }
            return null;
        }

        /**
         * @see org.eclipse.uml2.uml.util.UMLSwitch#caseExtension(org.eclipse.uml2.uml.Extension)
         * @generated
         */
        public GraphElement caseExtension(org.eclipse.uml2.uml.Extension object)
        {
            if ("default".equals(presentation))
            {
                return createGraphElementExtension(object, presentation);
            }
            return null;
        }

        /**
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.eclipse.uml2.uml.util.UMLSwitch#defaultCase(org.eclipse.emf.ecore.EObject)
         * @generated
         */
        public GraphElement defaultCase(EObject object)
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
        GraphElement graphElt = null;

        if (UMLPlugin.UML_URI.equals(obj.eClass().getEPackage().getNsURI()))
        {
            graphElt = new GraphicUMLSwitch(presentation).doSwitch(obj);
        }

        return graphElt;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated NOT
     */
    protected GraphElement createGraphElementStereotype(org.eclipse.uml2.uml.Stereotype element, String presentation)
    {
        GraphNode nodeParent = createGraphNode(element, presentation);

        GraphNode property = createGraphNode(element, UMLPackage.STEREOTYPE__OWNED_ATTRIBUTE, presentation);
        property.setContainer(nodeParent);

        return nodeParent;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementProperty(org.eclipse.uml2.uml.Property element, String presentation)
    {
        return createGraphNode(element, presentation);
    }

    /**
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementElementImport(org.eclipse.uml2.uml.ElementImport element, String presentation)
    {
        return createGraphNode(element, presentation);
    }

    /**
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementDependency(org.eclipse.uml2.uml.Dependency element, String presentation)
    {
        GraphEdge graphEdge = createGraphEdge(element, presentation);

        EdgeObjectOffset stereotypeEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
        stereotypeEdgeObjectOffset.setId(AllEdgeObjectConstants.STEREOTYPE_EDGE_OBJECT_ID);
        stereotypeEdgeObjectOffset.setOffset(new Dimension(0, 0));
        stereotypeEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(AllDiagramPreferenceConstants.DEPENDENCY_STEREOTYPE_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(stereotypeEdgeObjectOffset);

        EdgeObjectOffset nameEdgeObjectOffset = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectOffset();
        nameEdgeObjectOffset.setId(AllEdgeObjectConstants.MIDDLENAME_EDGE_OBJECT_ID);
        nameEdgeObjectOffset.setOffset(new Dimension(0, 20));
        nameEdgeObjectOffset.setVisible(getPreferenceStore().getBoolean(AllDiagramPreferenceConstants.DEPENDENCY_NAME_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(nameEdgeObjectOffset);

        return graphEdge;
    }
    
    /**
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementGeneralization(org.eclipse.uml2.uml.Generalization element, String presentation)
    {
        GraphEdge graphEdge = createGraphEdge(element, presentation);
        return graphEdge;
    }

    /**
     * @param element the model element
     * @param presentation the presentation of the graphical element
     * @return the complete GraphElement
     * @generated
     */
    protected GraphElement createGraphElementExtension(org.eclipse.uml2.uml.Extension element, String presentation)
    {
        GraphEdge graphEdge = createGraphEdge(element, presentation);
        EdgeObjectUV requiredfieldEdgeObjectUV = DiagramInterchangeFactory.eINSTANCE.createEdgeObjectUV();
        requiredfieldEdgeObjectUV.setId(ProfileEdgeObjectConstants.REQUIREDFIELD_EDGE_OBJECT_ID);
        requiredfieldEdgeObjectUV.setUDistance(0);
        requiredfieldEdgeObjectUV.setVDistance(0);
        requiredfieldEdgeObjectUV.setVisible(getPreferenceStore().getBoolean(ProfileDiagramPreferenceConstants.EXTENSION_REQUIREDFIELD_EDGE_OBJECT_DEFAULT_VISIBILITY));
        graphEdge.getContained().add(requiredfieldEdgeObjectUV);
        return graphEdge;
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
        return obj;
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
