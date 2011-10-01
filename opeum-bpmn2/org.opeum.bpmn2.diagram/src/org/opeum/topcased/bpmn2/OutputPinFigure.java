package org.opeum.topcased.bpmn2;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public class OutputPinFigure extends org.topcased.draw2d.figures.PortFigure
{
    private static final int WIDTH_SPACE = 2;

    private static final int HEIGHT_SPACE = 4;

    /**
     * Constructor
     * 
     * @generated NOT
     */
    public OutputPinFigure()
    {
        super();
    }

    /**
     * @see org.eclipse.draw2d.IFigure#getPreferredSize(int, int)
     */
    public Dimension getPreferredSize(int wHint, int hHint)
    {
        return new Dimension(32, 32);
    }

    /**
     * @see org.topcased.draw2d.figures.PortFigure#outlineShape(org.eclipse.draw2d.Graphics)
     */
    protected void outlineShape(Graphics graphics)
    {
        Rectangle r = getBounds();
        int x = r.x + lineWidth / 2;
        int y = r.y + lineWidth / 2;
        int w = r.width - Math.max(1, lineWidth);
        int h = r.height - Math.max(1, lineWidth);
//        graphics.drawRectangle(x, y, w, h);
        graphics.drawOval(r);

        switch (getPosition())
        {
            case PositionConstants.RIGHT:
                graphics.drawLine(r.getRight().getTranslated(-WIDTH_SPACE, 0),
                        r.getLeft().getTranslated(WIDTH_SPACE, 0));
                graphics.drawLine(r.getRight().getTranslated(-WIDTH_SPACE, 0), r.getBottom().getTranslated(0,
                        -HEIGHT_SPACE));
                graphics.drawLine(r.getRight().getTranslated(-WIDTH_SPACE, 0),
                        r.getTop().getTranslated(0, HEIGHT_SPACE));
                break;
            case PositionConstants.BOTTOM:
                graphics.drawLine(r.getBottom().getTranslated(0, -WIDTH_SPACE),
                        r.getTop().getTranslated(0, WIDTH_SPACE));
                graphics.drawLine(r.getBottom().getTranslated(0, -WIDTH_SPACE), r.getLeft().getTranslated(HEIGHT_SPACE,
                        0));
                graphics.drawLine(r.getBottom().getTranslated(0, -WIDTH_SPACE), r.getRight().getTranslated(
                        -HEIGHT_SPACE, 0));
                break;
            case PositionConstants.TOP:
                graphics.drawLine(r.getTop().getTranslated(0, WIDTH_SPACE),
                        r.getBottom().getTranslated(0, -WIDTH_SPACE));
                graphics.drawLine(r.getTop().getTranslated(0, WIDTH_SPACE),
                        r.getRight().getTranslated(-HEIGHT_SPACE, 0));
                graphics.drawLine(r.getTop().getTranslated(0, WIDTH_SPACE), r.getLeft().getTranslated(HEIGHT_SPACE, 0));
                break;
            default:
                graphics.drawLine(r.getLeft().getTranslated(WIDTH_SPACE, 0),
                        r.getRight().getTranslated(-WIDTH_SPACE, 0));
                graphics.drawLine(r.getLeft().getTranslated(WIDTH_SPACE, 0), r.getTop().getTranslated(0, HEIGHT_SPACE));
                graphics.drawLine(r.getLeft().getTranslated(WIDTH_SPACE, 0), r.getBottom().getTranslated(0,
                        -HEIGHT_SPACE));
                break;
        }
    }
}