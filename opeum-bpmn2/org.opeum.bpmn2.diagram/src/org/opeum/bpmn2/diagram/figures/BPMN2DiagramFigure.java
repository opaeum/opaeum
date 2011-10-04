/*******************************************************************************
 * No CopyrightText Defined in the configurator file.
 ******************************************************************************/
package org.opeum.bpmn2.diagram.figures;

import java.util.ArrayList;
import java.util.List;

import org.opeum.bpmn2.diagram.figure.BoundaryEventFigure;
import org.topcased.modeler.figures.DiagramFigure;

/**
 * The figure to display a BPMN2 Diagram.
 * 
 * @generated
 */
public class BPMN2DiagramFigure extends DiagramFigure{
	@SuppressWarnings({
			"unchecked","rawtypes"
	})
	@Override
	public List getChildren(){
		List children = super.getChildren();
		List result = new ArrayList();
		for(Object object:children){
			if(object instanceof BoundaryEventFigure){
				result.add(object);
			}
		}
		for(Object object:children){
			if(!(object instanceof BoundaryEventFigure)){
				result.add(object);
			}
		}
		return result;
	}
	@Override
	protected void layout(){
		super.layout();
	}
}
