package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Property;
import org.opaeum.uim.cube.AggregationFormula;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.DimensionBinding;
import org.opaeum.uim.cube.MeasureProperty;
import org.opaeum.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.internal.utils.Messages;
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
	@Override
	protected void handleModelChanged(Notification msg){
		// TODO Auto-generated method stub
		super.handleModelChanged(msg);
		if(msg.getNotifier()==getEObject()){
			refresh();
		}
	}
	protected Object[] getComboFeatureValues(){
		Collection<Object> results = new ArrayList<Object>();
		results.add("");
		Property p = (Property) UmlUimLinks.getCurrentUmlLinks(getEObject()).getUmlElement(getMeasureProperty());
		if(p != null){
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
