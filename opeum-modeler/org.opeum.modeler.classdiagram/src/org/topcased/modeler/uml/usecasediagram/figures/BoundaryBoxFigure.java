/*****************************************************************************
 * 
 * BoundaryBoxFigure.java
 * 
 * Copyright (c) 2008 Atos Origin
 *
 * Contributors:
 *  Maxime Nauleau (Atos Origin) maxime.nauleau@atosorigin.com - initial API and implementation
 *    
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
  *****************************************************************************/

package org.topcased.modeler.uml.usecasediagram.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;

/**
 * Class which draw a BoundaryBox Figure (based on PackageFigure without the header)
 * @author maxime.nauleau
 *
 */
public class BoundaryBoxFigure extends Figure
{   
    private IFigure contentPane;
    
    /**
     * Constructor
     */
    public BoundaryBoxFigure()
    {
        drawFigure();
    }
    
    /**
     * Draw a Rectangle
     */
    protected void drawFigure()
    {
        GridLayout gridLayout = new GridLayout();
        gridLayout.verticalSpacing = 0;
        gridLayout.horizontalSpacing = 0;
        gridLayout.marginWidth = 0;
        gridLayout.marginHeight = 0;
        setLayoutManager(gridLayout);
        
        setContentPane(createContainer());
        add(contentPane, new GridData(GridData.FILL_BOTH));   
    }
    
    /**
     * Creates the container figure
     * @return the container figure
     */
    protected IFigure createContainer()
    {        
        Figure container = new Figure();
        container.setLayoutManager(new XYLayout());
        container.setOpaque(true);
        container.setBorder(new LineBorder(2));
        return container;
    }
    
    /**
     * Set the container. This function will only be used by extending classes.
     * 
     * @param c the new container figure
     */
    protected void setContentPane(IFigure c)
    {
        contentPane = c;
    }

  
    
}

