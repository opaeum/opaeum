/*****************************************************************************
 * Copyright (c) 2008 Atos Origin.
 *
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Thomas Szadel (Atos Origin) - Initial API and implementation
 *  Jeremie Belmudes (Atos Origin) - Fix Bug #3069
 *
 *****************************************************************************/

package org.topcased.modeler.uml.internal.decorators;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.IMapMode;
import org.eclipse.gmf.runtime.draw2d.ui.mapmode.MapModeUtil;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Image;
import org.eclipse.uml2.uml.Stereotype;
import org.topcased.modeler.editor.ModelerGraphicalViewer;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.alldiagram.preferences.AllDiagramPreferenceConstants;

/**
 * This class decorates the graphical objects with the stereotype icon if available.
 * 
 * @author <a href="mailto:thomas.szadel@atosorigin.com">Thomas Szadel</a>
 */
public class StereotypeDecorator implements IDecorator
{

    private static final String IMAGE_PAPYRUS = "image_papyrus";

    /** The maximum width and/or height for an icon. */
    private static final int MAX_ICON_SIZE = 20;

    /** The object to be decorated. */
    private IDecoratorTarget decoratorTarget;

    /** The decoration being displayed. */
    private IDecoration decoration;

    /** The loaded images. */
    private Set<org.eclipse.swt.graphics.Image> loadedImages;

    /**
     * Creates a new <code>Decorator</code> for the decorator target passed in.
     * 
     * @param target the object to be decorated
     */
    public StereotypeDecorator(IDecoratorTarget target)
    {
        this.decoratorTarget = target;
        loadedImages = new HashSet<org.eclipse.swt.graphics.Image>();
    }

    /**
     * Returns the decoration.
     * 
     * @return The decoration.
     */
    public IDecoration getDecoration()
    {
        return decoration;
    }

    /**
     * Sets the decoration.
     * 
     * @param decoration The decoration.
     */
    public void setDecoration(IDecoration decoration)
    {
        this.decoration = decoration;
    }

    /**
     * Activate.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator#activate()
     */
    public void activate()
    {
        // Nothing
    }

    /**
     * Deactivate.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator#deactivate()
     */
    public void deactivate()
    {
        removeDecoration();
    }

    /**
     * Gets the object to be decorated.
     * 
     * @return Returns the object to be decorated
     */
    protected IDecoratorTarget getDecoratorTarget()
    {
        return decoratorTarget;
    }

    /**
     * Refresh.
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator#refresh()
     */
    public void refresh()
    {
        removeDecoration();

        // The icon can be disabled in the preference
        if (!"icon".equals(getPreferenceStore().getString(AllDiagramPreferenceConstants.STEREOTYPE_DEFAULT_DISPLAY)))
        {
            return;
        }

        EObject object = getEObjectDecoratorTarget(getDecoratorTarget());

        if (object != null && object instanceof Element)
        {
            Element e = (Element) object;
            EList<Stereotype> stereotypes = e.getAppliedStereotypes();
            if (!stereotypes.isEmpty())
            {
                GraphicalEditPart editPart = (GraphicalEditPart) getDecoratorTarget().getAdapter(GraphicalEditPart.class);
                IMapMode mm = MapModeUtil.getMapMode(editPart.getFigure());
                IFigure figure = getImageFigure(stereotypes, mm);
                if (editPart instanceof ConnectionEditPart)
                {
                    setDecoration(getDecoratorTarget().addConnectionDecoration(figure, 50, false));
                }
                else
                {

                    setDecoration(getDecoratorTarget().addShapeDecoration(figure, getDirection(getDecoratorTarget()), -1, false));
                }
            }
        }
    }

    /**
     * Get the preference store of the graphical edge
     * 
     * @return the preference store
     */
    protected IPreferenceStore getPreferenceStore()
    {
        GraphicalEditPart editPart = (GraphicalEditPart) getDecoratorTarget().getAdapter(GraphicalEditPart.class);
        if (editPart.getViewer() instanceof ModelerGraphicalViewer)
        {
            return ((ModelerGraphicalViewer) editPart.getViewer()).getModelerEditor().getPreferenceStore();
        }
        return UMLPlugin.getDefault().getPreferenceStore();
    }

    /**
     * Compute the direction using the kind of graphical representation is annotated.
     * 
     * @param target The target
     * 
     * @return the position where the decorator is displayed
     */
    private IDecoratorTarget.Direction getDirection(IDecoratorTarget target)
    {
        GraphicalEditPart editPart = (GraphicalEditPart) target.getAdapter(GraphicalEditPart.class);
        if (editPart != null)
        {
            if (editPart instanceof ConnectionEditPart)
            {
                return IDecoratorTarget.Direction.CENTER;
            }
        }

        // By default
        return IDecoratorTarget.Direction.NORTH_EAST;
    }

    /**
     * Removes the decoration if it exists and sets it to null.
     */
    protected void removeDecoration()
    {
        if (decoration != null)
        {
            decoratorTarget.removeDecoration(decoration);
            decoration = null;
            // Dispose all the loaded images in order to avoid memory consumption
            for (Iterator<org.eclipse.swt.graphics.Image> iter = loadedImages.iterator(); iter.hasNext();)
            {
                iter.next().dispose();
                iter.remove();
            }
        }
    }

    /**
     * Method to determine if the decoratorTarget is a supported type for this decorator and return the associated
     * Classifier element.
     * 
     * @param decoratorTarget IDecoratorTarget to check and return valid Classifier target.
     * 
     * @return node Node if IDecoratorTarget can be supported, null otherwise.
     */
    public static EObject getEObjectDecoratorTarget(IDecoratorTarget decoratorTarget)
    {
        return (EObject) decoratorTarget.getAdapter(EObject.class);

    }

    /**
     * Returns the figure.
     * 
     * @param stereotypes The stereotypes.
     * @param mm The map mode.
     * 
     * @return The figure
     */
    private IFigure getImageFigure(EList<Stereotype> stereotypes, IMapMode mm)
    {
        IFigure stereotypeFigure = new Figure();
        stereotypeFigure.setLayoutManager(new ToolbarLayout(true));
        stereotypeFigure.setOpaque(false);

        for (Stereotype stereo : stereotypes)
        {
            for (Image imageModel : stereo.getIcons())
            {
                // check if papyrus image
                if (imageModel.getContent() != null && imageModel.getLocation() == null && imageModel.getEAnnotation(getImagePapyrus()) != null)
                {
                    try
                    {
                        manageImagePapyrus(mm, stereotypeFigure, imageModel);
                    }
                    // we catch exception because it's not defined 
                    catch (Exception e)
                    {
                        ResourcesPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, UMLPlugin.getId(), "The content of the image is incorrect:" + imageModel.getContent()));
                    }
                }
                else
                {
                    // check path image
                    if (imageModel.getLocation() != null)
                    {
                        try
                        {
                            manageLocationDifferentOfNull(mm, stereotypeFigure, imageModel);
                        }
                        catch (IllegalArgumentException e)
                        {
                            // The path is not correct
                            ResourcesPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, UMLPlugin.getId(), "The path of the image is incorrect:" + imageModel.getLocation()));
                        }
                        catch (SWTException e)
                        {
                            // Error loading the image
                            ResourcesPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, UMLPlugin.getId(), "The format of the image is incorrect:" + imageModel.getLocation()));
                        }
                    }
                    else
                    {
                        ResourcesPlugin.getPlugin().getLog().log(
                                new Status(IStatus.ERROR, UMLPlugin.getId(), "The format of the image is incorrect: location=" + imageModel.getLocation() + " ; content=" + imageModel.getContent()));
                    }
                }
            }
        }

        // Sets the preferred size as its actual size is (0,0)
        stereotypeFigure.setSize(stereotypeFigure.getPreferredSize());
        return stereotypeFigure;
    }

    /**
     * Manage icons coming from papyrus v1
     * @param mm The map mode
     * @param stereotypeFigure The stereotype figure
     * @param image The image
     */
    private void manageImagePapyrus(IMapMode mm, IFigure stereotypeFigure, Image image)
    {
        ImageFigure imageFig = new ImageFigure();

        org.eclipse.swt.graphics.Image imageFinal = getContent(image);
        manageImage(mm, stereotypeFigure, imageFig, imageFinal);
    }

    /**
     * Return the decoded image from the serialized image
     * 
     * @param image The serialized image
     * @return The decoded image
     */
    public static org.eclipse.swt.graphics.Image getContent(Image image)
    {

        if (image == null)
        {
            // null parameter
            return null;
        }

        if (image.getContent() == null)
        {
            // null image
            return null;
        }

        // else
        String rawData = image.getContent();
        StringTokenizer strToken = new StringTokenizer(rawData, "%");
        byte[] target = new byte[strToken.countTokens()];

        // decoding image
        int j = 0;
        while (strToken.hasMoreTokens())
        {
            target[j] = new Byte(strToken.nextToken()).byteValue();
            j++;
        }

        org.eclipse.swt.graphics.Image decodedImage = new org.eclipse.swt.graphics.Image(null, new ByteArrayInputStream(target));

        return decodedImage;
    }

    /**
     * Manage the stereotype image from location attribute
     * 
     * @param mm The map mode
     * @param stereotypeFigure The stereotype firgure
     * @param imageModel The image model
     */
    private void manageLocationDifferentOfNull(IMapMode mm, IFigure stereotypeFigure, Image imageModel)
    {
        URI uri = URI.createURI(imageModel.getLocation());
        String location;
        if (uri.isPlatformResource())
        {
            location = ResourcesPlugin.getWorkspace().getRoot().getLocation().append(uri.toPlatformString(true)).toOSString();
        }
        else
        {
            location = uri.toString();
        }
        File imageFile = new File(location);
        // Avoid load error
        if (imageFile.exists())
        {
            ImageFigure imageFig = new ImageFigure();
            // Start scaling big images
            org.eclipse.swt.graphics.Image image = new org.eclipse.swt.graphics.Image(Display.getCurrent(), imageFile.getAbsolutePath());

            manageImage(mm, stereotypeFigure, imageFig, image);
        }
        else
        {
            ResourcesPlugin.getPlugin().getLog().log(new Status(IStatus.ERROR, UMLPlugin.getId(), "The path of the image is incorrect:" + imageModel.getLocation()));
        }
    }

    /**
     * Manage to disply the given image
     * 
     * @param mm The map mode
     * @param stereotypeFigure The stereotype figure
     * @param imageFig The image figure
     * @param image The image
     */
    private void manageImage(IMapMode mm, IFigure stereotypeFigure, ImageFigure imageFig, org.eclipse.swt.graphics.Image image)
    {
        int width = mm.DPtoLP(image.getBounds().width);
        int height = mm.DPtoLP(image.getBounds().height);
        if (width > MAX_ICON_SIZE || height > MAX_ICON_SIZE)
        {
            // The scale to apply (as we reduce, takes the lowest)
            float scale = Math.min((float) MAX_ICON_SIZE / (float) width, (float) MAX_ICON_SIZE / (float) height);

            int newWidth = Math.round(width * scale);
            int newHeight = Math.round(height * scale);

            // Scale the image
            org.eclipse.swt.graphics.Image scaledImage = new org.eclipse.swt.graphics.Image(Display.getDefault(), newWidth, newHeight);
            GC gc = new GC(scaledImage);
            // Use antialias to improve display
            gc.setAntialias(SWT.ON);
            gc.drawImage(image, 0, 0, width, height, 0, 0, newWidth, newHeight);
            // Release the temporary resources
            gc.dispose();
            image.dispose();

            image = scaledImage;
            width = newWidth;
            height = newHeight;
        }

        loadedImages.add(image);
        imageFig.setImage(image);
        imageFig.setSize(width, height);
        stereotypeFigure.add(imageFig);
    }

    /**
     * Return IMAGE_PAPYRUS value
     * 
     * @return The IMAGE_PAPYRUS value
     */
    public static String getImagePapyrus()
    {
        return IMAGE_PAPYRUS;
    }
}
