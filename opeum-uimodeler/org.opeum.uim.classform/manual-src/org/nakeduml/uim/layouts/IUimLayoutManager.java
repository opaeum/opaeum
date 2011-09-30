package org.opeum.uim.layouts;

import java.util.Collection;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;

public interface IUimLayoutManager{
	public void layout(Collection<Rectangle> values, Dimension parentSize);

}
