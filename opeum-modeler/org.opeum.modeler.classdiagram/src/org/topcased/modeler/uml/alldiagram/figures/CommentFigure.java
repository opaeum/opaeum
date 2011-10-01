/*******************************************************************************
 * Copyright (c) 2006 AIRBUS FRANCE. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package org.topcased.modeler.uml.alldiagram.figures;

import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.ParagraphTextLayout;
import org.topcased.draw2d.figures.EllipsisFlowPage;
import org.topcased.draw2d.figures.ILabel;
import org.topcased.draw2d.figures.Label;
import org.topcased.draw2d.figures.TextFlow;

/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class CommentFigure extends org.topcased.draw2d.figures.CommentFigure
{
    private ILabel label;
    
    /**
     * Creates a new CommentFigure with a MarginBorder that is the given
     * size and a FlowPage containing a TextFlow with the style WORD_WRAP_SOFT.
     */
    @Override
    protected void drawFigure()
    {
        
        setBorder(new MarginBorder(getBorderInset(), getBorderInset(), getBorderInset(), getBorderInset() + getDefaultCornerSize()));
        setLayoutManager(getNewLayoutManager());
        
        // Add the label for the stereotype
        label = new Label();
		add(label);
        
        setTextFlow(new TextFlow());
        getTextFlow().setLayoutManager(new ParagraphTextLayout(getTextFlow(), ParagraphTextLayout.WORD_WRAP_TRUNCATE));

        FlowPage flowPage = new EllipsisFlowPage();
        flowPage.add(getTextFlow());

        add(flowPage);
    }

    /**
     * This method is defined in order to be redefined in
     * inherited methods in order to specialize the layout
     * @return the new LayoutManager
     */
	protected LayoutManager getNewLayoutManager() {
		return new ToolbarLayout();
	}

	public void setStereotypeLabel(String text) {
		label.setText(text);
	}


}