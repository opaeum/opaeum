package org.opaeum.papyrus.businessprocessdiagram.editparts;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.papyrus.uml.diagram.common.figure.node.PapyrusRoundedNodeFigure;
import org.opaeum.papyrus.common.ImageUtil;
/**
 * just for those simple nodes that don't have their own custom figure
 * @author ampie
 *
 */
public class OpaeumRoundedNodeFigure extends PapyrusRoundedNodeFigure{
	private String imageLocation;
	public OpaeumRoundedNodeFigure(){
		super();
	}
	public String getImageLocation(){
		return imageLocation;
	}
	public void setImageLocation(String imageLocation){
		this.imageLocation = imageLocation;
	}
	@Override
	public Label getTaggedLabel(){
		if(super.getTaggedLabel()==null){
			initTagLabel("tag");
		}
		return super.getTaggedLabel();
	}
	@Override
	public void paint(Graphics graphics){
		super.paint(graphics);
		if(imageLocation != null){
			ImageUtil.paintBackgroundSvgImage(graphics, this, imageLocation);
		}
	}
}
