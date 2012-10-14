package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;
import org.opaeum.uim.cube.AxisEntry;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.cube.DimensionBinding;
import org.opaeum.uim.util.UmlUimLinks;

public class AxisEntryDimensionBindingSection extends AbstractChooserPropertySection{
	Map<DimensionBinding,DimensionNode> nodes = new HashMap<DimensionBinding,DimensionNode>();
	public AxisEntry getAxisEntry(){
		return (AxisEntry) getEObject();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return CubePackage.eINSTANCE.getAxisEntry_DimensionBinding();
	}
	@Override
	public String getLabelText(){
		return "Dimension";
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	private boolean addDimensions(Classifier cp,DimensionNode detail,Set<DimensionNode> leaves){
		boolean hasParent = false;
		List<Property> propertiesInScope = EmfPropertyUtil.getEffectiveProperties(cp);
		for(Property p:propertiesInScope){
			if(EmfElementFinder.isDimension(p)){
				hasParent = true;
				DimensionNode master = new DimensionNode(cp, p);
				if(detail != null){
					detail = detail.getCopy();
					master.detail = detail;
				}
				boolean masterHasMaster = false;
				if(p.getType() instanceof Class || p.getType() instanceof Enumeration){
					masterHasMaster = addDimensions((Classifier) p.getType(), master, leaves);
				}
				if(!masterHasMaster){
					leaves.add(master.linkToInnermostDetail());
				}
			}
		}
		return hasParent;
	}
	@Override
	protected ILabelProvider getLabelProvider(){
		return new AdapterFactoryLabelProvider(new EcoreItemProviderAdapterFactory()){
			@Override
			public String getText(Object object){
				DimensionNode dimensionNode = nodes.get(object);
				if(dimensionNode == null){
					return "None";
				}else{
					return dimensionNode.toString();
				}
			}
		};
	}
	@Override
	protected Object getFeatureValue(){
		return getAxisEntry().getDimensionBinding();
	}
	@Override
	protected Object[] getComboFeatureValues(){
		nodes.clear();
		Set<DimensionNode> input = new HashSet<DimensionNode>();
		CubeQuery c = (CubeQuery) getAxisEntry().eContainer();
		if(c != null){// during deletion
			UmlUimLinks currentUmlLinks = UmlUimLinks.getCurrentUmlLinks(getAxisEntry());
			Classifier clazz = currentUmlLinks.getRepresentedClass(c);
			if(clazz != null){
				addDimensions(clazz, null, input);
			}
			for(DimensionNode dimensionNode:input){
				if(!(isInUse(dimensionNode, c.getColumnAxis()) || isInUse(dimensionNode, c.getRowAxis()))){
					DimensionBinding dimensionBinding = dimensionNode.linkToInnermostDetail().toDimensionBinding();
					nodes.put(dimensionBinding, dimensionNode);
				}
			}
		}
		ArrayList<Object> result = new ArrayList<Object>(nodes.keySet());
		result.add("");
		return result.toArray();
	}
	public boolean isInUse(DimensionNode dimensionNode,EList<? extends AxisEntry> columnAxis){
		boolean found = false;
		for(AxisEntry entry:columnAxis){
			if(!entry.equals(getAxisEntry()) && dimensionNode.toDimensionBinding().equals(entry.getDimensionBinding())){
				found = true;
			}
		}
		return found;
	}
}
