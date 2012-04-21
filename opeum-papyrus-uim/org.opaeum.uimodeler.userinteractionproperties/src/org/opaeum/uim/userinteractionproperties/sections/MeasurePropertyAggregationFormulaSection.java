package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.ClassUserInteractionModel;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.cube.AggregationFormula;
import org.opaeum.uim.cube.AxisEntry;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.cube.DimensionBinding;
import org.opaeum.uim.cube.MeasureProperty;
import org.opaeum.uim.provider.UimItemProviderAdapterFactory;
import org.opaeum.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.internal.utils.Messages;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;
import org.topcased.tabbedproperties.sections.AbstractChooserPropertySection;

public class MeasurePropertyAggregationFormulaSection extends AbstractChooserPropertySection{
	Map<DimensionBinding,DimensionNode> nodes = new HashMap<DimensionBinding,DimensionNode>();
	public MeasureProperty getMeasureProperty(){
		return (MeasureProperty) getEObject();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return CubePackage.eINSTANCE.getMeasureProperty_AggregationFormula();
	}
	@Override
	protected String getLabelText(){
		return "Aggregator";
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	protected Object getFeatureValue(){
		return getMeasureProperty().getAggregationFormula();
	}
	protected Object[] getComboFeatureValues(){
		Collection<Object> results = new ArrayList<Object>();
		results.add("");
		Property p = (Property) UmlUimLinks.getCurrentUmlLinks(getEObject()).getUmlElement(getMeasureProperty());
		for(EObject eo:p.getStereotypeApplications()){
			EStructuralFeature sf = eo.eClass().getEStructuralFeature("aggregationFormulas");
			if(sf != null){
				Collection<? extends EEnumLiteral> avFormulas = (Collection<? extends EEnumLiteral>) eo.eGet(sf);
				for(EEnumLiteral l:avFormulas){
					results.add(AggregationFormula.getByName(l.getName()));
				}
				break;
			}
		}
		return results.toArray();
	}
	protected ILabelProvider getLabelProvider(){
		return new LabelProvider(){
			@Override
			public String getText(Object element){
				if(element instanceof AggregationFormula){
					return ((AggregationFormula) element).getName();
				}else{
					return "None";
				}
			}
		};
	}
	protected void createCommand(Object oldValue,Object newValue){
		boolean equals = oldValue == null ? false : oldValue.equals(newValue);
		if(!equals){
			EditingDomain editingDomain = getEditingDomain();
			CompoundCommand compoundCommand = new CompoundCommand(Messages.AbstractTabbedPropertySection_CommandName);
			// apply the property change to all selected elements
			for(EObject nextObject:getEObjectList()){
				compoundCommand.append(SetCommand.create(editingDomain, nextObject, getFeature(), newValue));
			}
			editingDomain.getCommandStack().execute(compoundCommand);
		}
	}
}
