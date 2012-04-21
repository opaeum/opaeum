package org.opaeum.uimodeler.cubequery.diagram.part;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.gmf.runtime.notation.View;
import org.opaeum.uim.cube.ColumnAxisEntry;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.cube.MeasureProperty;
import org.opaeum.uim.cube.RowAxisEntry;
import org.opaeum.uimodeler.cubequery.diagram.edit.parts.ColumnAxisEntryEditPart;
import org.opaeum.uimodeler.cubequery.diagram.edit.parts.CubeQueryEditPart;
import org.opaeum.uimodeler.cubequery.diagram.edit.parts.MeasurePropertyEditPart;
import org.opaeum.uimodeler.cubequery.diagram.edit.parts.RowAxisEntryEditPart;

/**
 * @generated
 */
public class UimDiagramUpdater{
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getSemanticChildren(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case CubeQueryEditPart.VISUAL_ID:
			return getCubeQuery_1000SemanticChildren(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimNodeDescriptor> getCubeQuery_1000SemanticChildren(View view){
		if(!view.isSetElement()){
			return Collections.EMPTY_LIST;
		}
		CubeQuery modelElement = (CubeQuery) view.getElement();
		LinkedList<UimNodeDescriptor> result = new LinkedList<UimNodeDescriptor>();
		for(Iterator<?> it = modelElement.getColumnAxis().iterator();it.hasNext();){
			ColumnAxisEntry childElement = (ColumnAxisEntry) it.next();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == ColumnAxisEntryEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for(Iterator<?> it = modelElement.getRowAxis().iterator();it.hasNext();){
			RowAxisEntry childElement = (RowAxisEntry) it.next();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == RowAxisEntryEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		for(Iterator<?> it = modelElement.getMeasures().iterator();it.hasNext();){
			MeasureProperty childElement = (MeasureProperty) it.next();
			int visualID = UimVisualIDRegistry.getNodeVisualID(view, childElement);
			if(visualID == MeasurePropertyEditPart.VISUAL_ID){
				result.add(new UimNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getContainedLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case CubeQueryEditPart.VISUAL_ID:
			return getCubeQuery_1000ContainedLinks(view);
		case ColumnAxisEntryEditPart.VISUAL_ID:
			return getColumnAxisEntry_2001ContainedLinks(view);
		case RowAxisEntryEditPart.VISUAL_ID:
			return getRowAxisEntry_2002ContainedLinks(view);
		case MeasurePropertyEditPart.VISUAL_ID:
			return getMeasureProperty_2003ContainedLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getIncomingLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case ColumnAxisEntryEditPart.VISUAL_ID:
			return getColumnAxisEntry_2001IncomingLinks(view);
		case RowAxisEntryEditPart.VISUAL_ID:
			return getRowAxisEntry_2002IncomingLinks(view);
		case MeasurePropertyEditPart.VISUAL_ID:
			return getMeasureProperty_2003IncomingLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getOutgoingLinks(View view){
		switch(UimVisualIDRegistry.getVisualID(view)){
		case ColumnAxisEntryEditPart.VISUAL_ID:
			return getColumnAxisEntry_2001OutgoingLinks(view);
		case RowAxisEntryEditPart.VISUAL_ID:
			return getRowAxisEntry_2002OutgoingLinks(view);
		case MeasurePropertyEditPart.VISUAL_ID:
			return getMeasureProperty_2003OutgoingLinks(view);
		}
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getCubeQuery_1000ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getColumnAxisEntry_2001ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getRowAxisEntry_2002ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getMeasureProperty_2003ContainedLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getColumnAxisEntry_2001IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getRowAxisEntry_2002IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getMeasureProperty_2003IncomingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getColumnAxisEntry_2001OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getRowAxisEntry_2002OutgoingLinks(View view){
		return Collections.emptyList();
	}
	/**
	 * @generated
	 */
	public static List<UimLinkDescriptor> getMeasureProperty_2003OutgoingLinks(View view){
		return Collections.emptyList();
	}
}
