package org.opaeum.papyrus.businessprocessdiagram.editparts;

import org.eclipse.draw2d.Graphics;
import org.eclipse.papyrus.uml.diagram.common.figure.node.PapyrusRoundedNodeFigure;
import org.opaeum.papyrus.common.ImageUtil;

public class OpaeumRoundedNodeFigure extends PapyrusRoundedNodeFigure{
	private String imageLocation;
	public String getImageLocation(){
		return imageLocation;
	}
	public void setImageLocation(String imageLocation){
		this.imageLocation = imageLocation;
	}
	@Override
	public void paint(Graphics graphics){
		super.paint(graphics);
		if(imageLocation != null){
			ImageUtil.paintBackgroundSvgImage(graphics, this, imageLocation);
		}
	}
}
