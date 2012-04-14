package org.opaeum.uim.userinteractionproperties.sections;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Stereotype;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.cube.AxisEntry;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public class AxisEntryDimensionBindingSection extends AbstractTabbedPropertySection{
	private CCombo dimensions;
	private ComboViewer viewer;
	public AxisEntry getAxisEntry(){
		return (AxisEntry) getEObject();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return CubePackage.eINSTANCE.getAxisEntry_DimensionBinding();
	}
	@Override
	protected String getLabelText(){
		return "Dimension";
	}
	@Override
	protected void createWidgets(Composite composite){
		getWidgetFactory().createCLabel(composite, getLabelText());
		this.dimensions = getWidgetFactory().createCCombo(composite, SWT.READ_ONLY | SWT.DROP_DOWN);
		Set<DimensionNode> input = new HashSet<DimensionNode>();
		CubeQuery classModel = (CubeQuery) getAxisEntry().eContainer();
		Class clazz = UmlUimLinks.getCurrentUmlLinks(getAxisEntry()).getRepresentedClass(classModel);
		addDimensions(clazz, null, input);
		this.viewer = new ComboViewer(dimensions);
		viewer.setLabelProvider(new LabelProvider());
		viewer.setInput(input);
	}
	private boolean addDimensions(Class cp,DimensionNode detail,Set<DimensionNode> leaves){
		boolean hasParent = false;
		for(Property p:EmfElementFinder.getPropertiesInScope(cp)){
			if(EmfElementFinder.isDimension(p)){
				hasParent = true;
				DimensionNode master = new DimensionNode(cp, p);
				if(detail != null){
					detail = detail.getCopy();
					master.detail = detail;
				}
				boolean masterHasMaster = false;
				if(p.getType() instanceof Class){
					masterHasMaster = addDimensions((Class) p.getType(), master, leaves);
				}
				if(!masterHasMaster){
					leaves.add(master.linkToInnermostDetail());
					if(detail != null){
						System.out.println("Master=" + detail.master + " Detail=" + detail);
					}
				}
			}
		}
		return hasParent;
	}
}
