/*****************************************************************************
 * Copyright (c) 2009 ATOS ORIGIN INTEGRATION.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Tristan FAURE (ATOS ORIGIN INTEGRATION) tristan.faure@atosorigin.com - Initial API and implementation
 *
 *****************************************************************************/
package org.topcased.modeler.uml.editor.outline;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.uml2.uml.Package;
import org.topcased.modeler.di.model.Diagram;
import org.topcased.modeler.di.model.DiagramInterchangePackage;
import org.topcased.modeler.di.model.EMFSemanticModelBridge;
import org.topcased.modeler.di.model.GraphElement;
import org.topcased.modeler.di.model.SemanticModelBridge;
import org.topcased.modeler.diagrams.model.Diagrams;
import org.topcased.modeler.diagrams.model.DiagramsFactory;
import org.topcased.modeler.diagrams.model.DiagramsPackage;
import org.topcased.modeler.diagrams.model.util.DiagramsUtils;
import org.topcased.modeler.editor.Modeler;
import org.topcased.modeler.editor.outline.OutlineToOutlineDropAdapter;

/**
 * The Class UMLOutlineToOutlineDropAdapter.
 */
public class UMLOutlineToOutlineDropAdapter extends OutlineToOutlineDropAdapter
{

    /**
     * Instantiates a new uML outline to outline drop adapter.
     * 
     * @param modeler the modeler
     * @param viewer the viewer
     */
    public UMLOutlineToOutlineDropAdapter(Modeler modeler, Viewer viewer)
    {
        super(modeler, viewer);
    }

    @Override
    public void drop(DropTargetEvent event)
    {
        super.drop(event);
    }

    @Override
    protected void helper(DropTargetEvent event)
    {
        // If we can't do anything else, we'll provide the default select feedback
        // and enable auto-scroll and auto-expand effects.
        event.feedback = DND.FEEDBACK_SELECT | getAutoFeedback();

        // If we don't already have it, try to get the source early. We can't give
        // feedback if it's not available yet (this is platform-dependent).
        //
        if (source == null)
        {
            source = getDragSource(event);
            if (source == null)
            {
                // Clear out any old information from a previous drag.
                //
                dragAndDropCommandInformation = null;
                return;
            }
        }

        // Get the target object from the item widget and the mouse location in it.
        //
        Object target = extractDropTarget(event.item);
        if (target instanceof Package)
        {
            Collection<Object> sources = null;
            if (source instanceof Collection)
            {
                sources = (Collection<Object>) source;
            }
            else
            {
                sources = new ArrayList<Object>();
                sources.add(source);
            }
            boolean isGraphElement = false;
            for (Object o : sources)
            {
                if (o instanceof GraphElement)
                {
                    isGraphElement = true;
                    break;
                }
            }
            if (isGraphElement)
            {
                // nothing but it's good
                event.detail = DND.DROP_MOVE;
            }
            else
            {
                super.helper(event);
            }
        }
        else
        {
            super.helper(event);
        }
    }

    @Override
    protected void handleDrop(DropTargetEvent event)
    {
        // If we can't do anything else, we'll provide the default select feedback
        // and enable auto-scroll and auto-expand effects.
        event.feedback = DND.FEEDBACK_SELECT | getAutoFeedback();

        // If we don't already have it, try to get the source early. We can't give
        // feedback if it's not available yet (this is platform-dependent).
        //
        if (source == null)
        {
            source = getDragSource(event);
            if (source == null)
            {
                // Clear out any old information from a previous drag.
                //
                dragAndDropCommandInformation = null;
                return;
            }
        }

        // Get the target object from the item widget and the mouse location in it.
        //
        Object target = extractDropTarget(event.item);
        if (target instanceof Package)
        {
            Diagrams owner = null;
            Collection<Object> sources = null;
            if (source instanceof Collection)
            {
                sources = (Collection<Object>) source;
            }
            else
            {
                sources = new ArrayList<Object>();
                sources.add(source);
            }
            boolean isGraphElement = false;
            for (Object o : sources)
            {
                if (o instanceof GraphElement)
                {
                    isGraphElement = true;
                    break;
                }
            }
            if (isGraphElement && target instanceof EObject)
            {
                createCommandAndExecute((EObject) target, sources);
            }
            else
            {
                super.handleDrop(event);
            }
        }
        else
        {
            super.handleDrop(event);
        }
    }

    private void createCommandAndExecute(EObject target, Collection<Object> sources)
    {
        CompoundCommand command = new CompoundCommand();
        Diagrams rootDi = getTargetDiagrams(target);
        Diagrams targetDi = DiagramsUtils.findNearestContainerDiagrams(rootDi, target);
        if (targetDi == null)
        {
            targetDi = rootDi;
        }
        if (targetDi != null && targetDi.getModel() != target)
        {
            Diagrams ownerDi = DiagramsFactory.eINSTANCE.createDiagrams();
            ownerDi.setModel(target);
            command.append(AddCommand.create(domain, targetDi, DiagramsPackage.Literals.DIAGRAMS__SUBDIAGRAMS, ownerDi));
            targetDi = ownerDi;
        }
        Collection<EObject> toRefresh = new ArrayList<EObject>(sources.size() + 1);
        toRefresh.add(target);
        if (targetDi != null)
        {
            command.append(new UpdateDiagramAndViewerCommand(toRefresh,true));
            for (Object o : sources)
            {
                if (o instanceof Diagram)
                {
                    Diagram di = (Diagram) o;
                    command.append(RemoveCommand.create(domain, di.eContainer(), DiagramsPackage.Literals.DIAGRAMS__DIAGRAMS, di));
                    command.append(AddCommand.create(domain, targetDi, DiagramsPackage.Literals.DIAGRAMS__DIAGRAMS, di));
                    SemanticModelBridge bridge = di.getSemanticModel();
                    if (bridge instanceof EMFSemanticModelBridge)
                    {
                        EMFSemanticModelBridge emfBridge = (EMFSemanticModelBridge) bridge;
                        toRefresh.add(((EMFSemanticModelBridge) bridge).getElement());
                        command.append(SetCommand.create(domain, emfBridge, DiagramInterchangePackage.Literals.EMF_SEMANTIC_MODEL_BRIDGE__ELEMENT, target));
                    }
                }
            }
            command.append(new UpdateDiagramAndViewerCommand(toRefresh,false));
            domain.getCommandStack().execute(command);
        }
    }

    private Diagrams getTargetDiagrams(EObject target)
    {
        Resource diResource = target.eResource().getResourceSet().getResource(getDiURI(target), true);
        if (diResource != null && !diResource.getContents().isEmpty())
        {
            return (Diagrams) diResource.getContents().get(0);
        }
        return null;
    }

    /**
     * get the uri of di file
     * 
     * @param eobject
     * @return
     */
    protected URI getDiURI(EObject eobject)
    {
        return URI.createURI(eobject.eResource().getURI().toString() + "di");
    }

    /**
     * A class refreshing the treeviewer and diagram in a command
     * @author tfaure
     *
     */
    protected class UpdateDiagramAndViewerCommand extends AbstractCommand
    {
        private final Collection<EObject> toRefresh;
        private final boolean undo;

        /**
         * Refresh tree viewer and diagram
         * @param toRefresh
         * @param undo, specifies if the code must be call at undo or at redo
         */
        public UpdateDiagramAndViewerCommand(Collection<EObject> toRefresh,boolean undo)
        {
            this.toRefresh = toRefresh;
            this.undo = undo;
        }

        @Override
        public boolean canExecute()
        {
            return true;
        }

        public void execute()
        {
            redo();
        }

        @Override
        public boolean canUndo()
        {
            return true;
        }

        @Override
        public void undo()
        {
            if (undo)
            {
                doRedo();
            }
        }

        public void redo()
        {
            if (!undo)
            {
                doRedo();
            }
        }

        private void doRedo()
        {
            if (viewer instanceof TreeViewer)
            {
                TreeViewer tree = (TreeViewer) viewer;
                for  (EObject e : toRefresh)
                {
                    tree.refresh(e);
                }
            }
            if (getModeler() != null)
            {
                getModeler().refreshActiveDiagram();
            }
        }
    }

}
