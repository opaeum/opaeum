/*******************************************************************************
 * Copyright (c) 2005 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   David Sciamma (Anyware Technologies), Mathieu Garcia (Anyware Technologies),
 *   Jacques Lescot (Anyware Technologies), Thomas Friol (Anyware Technologies),
 *   Nicolas Lalev�e (Anyware Technologies) - initial API and implementation 
 *   Maxime Nauleau (Atos Origin) - Fix #1557
 ******************************************************************************/

package org.topcased.modeler.uml.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.uml2.uml.Profile;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.topcased.modeler.di.model.GraphEdge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.diagrams.model.DiagramsPackage;
import org.topcased.modeler.dialogs.ConfirmationDialog;
import org.topcased.modeler.documentation.EAnnotationDocPage;
import org.topcased.modeler.documentation.IDocPage;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.actions.DefineProfileAction;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;
import org.topcased.modeler.uml.editor.outline.CustomUMLItemProviderAdapterFactory;
import org.topcased.modeler.uml.editor.outline.UMLOutlinePage;
import org.topcased.modeler.uml.profilediagram.preferences.ProfileDiagramPreferenceConstants;
import org.topcased.modeler.utils.Utils;

/**
 * Generated Model editor <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class UMLEditor extends Modeler
{

    public static final String EDITOR_ID = "org.topcased.modeler.uml.editor.UMLEditor";

    /** The instance store (related to the workbench instance). */
    private IPreferenceStore instanceStore = null;

    /** The project store. */
    private IPreferenceStore projectStore = null;

    /**
     * @see org.topcased.modeler.editor.Modeler#getAdapterFactories() <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected List<AdapterFactory> getAdapterFactories()
    {
        List<AdapterFactory> factories = new ArrayList<AdapterFactory>();
        factories.add(new CustomUMLItemProviderAdapterFactory());
        factories.add(new org.topcased.modeler.uml.providers.UMLModelerProviderAdapterFactory());
        factories.addAll(super.getAdapterFactories());
        return factories;
    }

    /**
     * This resolve method calls a resolveAll method but start by resolving the top level diagram model element. This
     * algorithm implies the global graph of emf objects will be created before any process. During the resolve
     * operation all the eadapters are disabled. They are restored to their previous state at the end
     * 
     * @param resource the resource supposed to contain the diagrams
     * @param file
     */
    protected void resolveUsingDiagrams(Resource resource, IFile file)
    {
        ResourceSet resourceSet = resource.getResourceSet();
        if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof Diagrams)
        {
            // get the top level diagrams
            Diagrams diagrams = (Diagrams) resource.getContents().get(0);
            Object eGet = diagrams.eGet(DiagramsPackage.Literals.DIAGRAMS__PARENT, false);
            while (eGet != null)
            {
                diagrams = (Diagrams) eGet;
                if (diagrams.eIsProxy())
                {
                    diagrams = (Diagrams) resourceSet.getEObject(EcoreUtil.getURI(diagrams), true);
                }
                eGet = diagrams.eGet(DiagramsPackage.Literals.DIAGRAMS__PARENT, false);
            }
            manageResolveAll(diagrams, resourceSet, file);
        }
    }

    private void manageResolveAll(Diagrams diagrams, ResourceSet resourceSet, IFile file)
    {
        if (getPreferenceStore(file).getBoolean(AllDiagramPreferenceConstants.LOAD_CUSTOM_RESOLVE_ALL))
        {
            // resolve all for the diagrams
            Utils.customResolveAll(resourceSet);
        }
        else
        {
            // resolve all for the diagrams
            // EcoreUtil.resolveAll(diagrams);
            // for each resource, resolve all
            Resource rDi = diagrams.eResource();
            boolean oldDeliverRDi = rDi.eDeliver(); 
            rDi.eSetDeliver(false);
            Resource r = resourceSet.getResource(diagrams.getModel().eResource().getURI(), true);
            boolean oldResDeliver = r.eDeliver();
            r.eSetDeliver(false);
            /////// Model
            EcoreUtil.resolveAll(r);
            r.eSetDeliver(oldResDeliver);
            ///////// DI
            LinkedList<Diagrams> queue = new LinkedList<Diagrams>();
            queue.add(diagrams);
            while (!queue.isEmpty())
            {
                Diagrams d = queue.poll();
                for (TreeIterator<EObject> i = EcoreUtil.getAllProperContents(d, false) ; i.hasNext() ;)
                {
                    EObject next = i.next();
                    if (next instanceof GraphElement)
                    {
                        GraphElement graph = (GraphElement) next;
                        Utils.getElement(graph);
                    }
                    if (next instanceof GraphEdge)
                    {
                        i.prune();
                    }
                }
                queue.addAll(d.getSubdiagrams());
            }
            rDi.eSetDeliver(oldDeliverRDi);
            // resolve notification for adapters
            // it is possible now because elements are all resolved maybe more notification could be add
        }
    }

    protected void initLoadOptions(ResourceSet resourceSet, IFile file)
    {
        resourceSet.getLoadOptions().put(XMLResource.OPTION_DISABLE_NOTIFY, getPreferenceStore(file).getBoolean(AllDiagramPreferenceConstants.LOAD_NOTIFY));
        resourceSet.getLoadOptions().put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, getPreferenceStore(file).getBoolean(AllDiagramPreferenceConstants.LOAD_DEFER_IDREF));
        resourceSet.getLoadOptions().put(XMLResource.OPTION_DEFER_ATTACHMENT, getPreferenceStore(file).getBoolean(AllDiagramPreferenceConstants.LOAD_DEFER_ATTACHMENT));
    }

    /**
     * Override the method to prevent stereotypes problems
     * 
     * @Override
     */
    protected EObject openFile(IFile file, boolean resolve)
    {
        // Fix #2985 : resource path must be encoded to avoid further problems
        ResourceSet resourceSet = getResourceSet();
        boolean oldDeliver = resourceSet.eDeliver();
        resourceSet.eSetDeliver(false);
        Map<Object, Object> defaultOptions = new HashMap<Object, Object>();
        defaultOptions.putAll(resourceSet.getLoadOptions());
        initLoadOptions(resourceSet, file);
        // adapters are disabled
        URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
        
        Resource resource = resourceSet.getResource(uri, true);
        if (resolve)
        {
            try
            {
                resolveUsingDiagrams(resource, file);
            }
            catch (Throwable e)
            {
               UMLPlugin.log("error during resolve", IStatus.ERROR);
            }
        }
        // eadapters are restored to their previous state
        resourceSet.getLoadOptions().putAll(defaultOptions);
        resourceSet.eSetDeliver(oldDeliver);
        return resource.getContents().get(0);
    }

    /**
     * @see org.topcased.modeler.editor.Modeler#getId()
     * @generated
     */
    @Override
    public String getId()
    {
        return EDITOR_ID;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.Modeler#getAdapter(java.lang.Class)
     * @generated
     */
    @Override
    public Object getAdapter(Class type)
    {
        if (type == IDocPage.class)
        {
            return new EAnnotationDocPage(true);
        }
        return super.getAdapter(type);
    }

    public IPreferenceStore getPreferenceStore(IFile file)
    {
        if (instanceStore == null)
        {
            instanceStore = UMLPlugin.getDefault().getPreferenceStore();
        }
        if (file != null)
        {
            IProject project = file.getProject();
            try
            {
                Preferences root = Platform.getPreferencesService().getRootNode();
                if (root.node(ProjectScope.SCOPE).node(project.getName()).nodeExists(UMLPlugin.getId()))
                {
                    if (projectStore == null)
                    {
                        projectStore = new ScopedPreferenceStore(new ProjectScope(project), UMLPlugin.getId());

                        // Notify old listeners that the preference store has been modified
                        instanceStore.firePropertyChangeEvent(UMLPlugin.PREFERENCE_STORE_PROPERTY, instanceStore, projectStore);
                    }
                    return projectStore;
                }
            }
            catch (BackingStoreException e)
            {
                UMLPlugin.log(e);
            }
        }
        return instanceStore;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.topcased.modeler.editor.Modeler#getPreferenceStore()
     * @generated NOT
     */
    @Override
    public IPreferenceStore getPreferenceStore()
    {
        return getPreferenceStore(((IFileEditorInput) getEditorInput()).getFile());

    }

    /**
     * Customize the Outline
     * 
     * @see org.topcased.modeler.editor.Modeler#createOutlinePage()
     */
    @Override
    protected IContentOutlinePage createOutlinePage()
    {
        return new UMLOutlinePage(this);
    }

    /**
     * @see org.eclipse.gef.ui.parts.GraphicalEditorWithPalette#getInitialPaletteSize()
     */
    @Override
    protected int getInitialPaletteSize()
    {
        return 200;
    }

    @Override
    public void doSave(IProgressMonitor monitor)
    {
        // When saving a Profile diagram
        if (getDiagrams().getModel().eResource().getContents().get(0) instanceof Profile)
        {
            // Warn the user about the need to call "Define Profile" before saving
            ConfirmationDialog dialog = new ConfirmationDialog(UMLPlugin.getActiveWorkbenchShell(), "Define Profile before saving",
                    "Do you want to run the 'Define Profile' action before saving?\nIf you do not perform this, you won't be able to apply this new (or updated) profile in a UML model.",
                    getPreferenceStore(), ProfileDiagramPreferenceConstants.ASK_DEFINE_PROFILE_ON_SAVING);
            if (dialog.open() == Window.OK)
            {
                DefineProfileAction action = new DefineProfileAction(this, (Profile) getDiagrams().getModel().eResource().getContents().get(0));
                action.run();
            }
        }
        super.doSave(monitor);
    }

}
