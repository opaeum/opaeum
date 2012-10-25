package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.uml.propertysections.base.AbstractChooserPropertySection;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.cube.DimensionBinding;
import org.opaeum.uim.cube.MeasureProperty;
import org.opaeum.uim.provider.UimItemProviderAdapterFactory;
import org.opaeum.uim.util.UmlUimLinks;

public class MeasurePropertyPropertySection extends AbstractChooserPropertySection{
	Map<DimensionBinding,DimensionNode> nodes = new HashMap<DimensionBinding,DimensionNode>();
	public MeasureProperty getMeasureProperty(){
		return (MeasureProperty) getEObject();
	}
	@Override
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUmlReference_UmlElementUid();
	}
	@Override
	public String getLabelText(){
		return "Measure";
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	protected Object getFeatureValue(){
		return UmlUimLinks.getCurrentUmlLinks(getEObject()).getUmlElement(getMeasureProperty());
	}
	protected Object[] getComboFeatureValues(){
		Collection<Object> results = new ArrayList<Object>();
		results.add("");
		CubeQuery cq = (CubeQuery) getMeasureProperty().eContainer();
		Class clzz = (Class) UmlUimLinks.getCurrentUmlLinks(getEObject()).getUmlElement(cq);
		for(Property p:EmfPropertyUtil.getEffectiveProperties(clzz)){
			if(isMeasure(p)){
				results.add(p);
			}
		}
		return results.toArray();
	}
	private boolean isMeasure(Property p){
		EList<EObject> stereotypeApplications = p.getStereotypeApplications();
		for(EObject eObject:stereotypeApplications){
			if(eObject.eClass().getEStructuralFeature("roleInCube") != null){
				EEnumLiteral l = (EEnumLiteral) eObject.eGet(eObject.eClass().getEStructuralFeature("roleInCube"));
				return l.getName().equals("MEASURE");
			}
		}
		return false;
	}
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new UimItemProviderAdapterFactory());
		f.addAll(getPrincipalAdapterFactories());
		return new AdapterFactoryLabelProvider(new ComposedAdapterFactory(f));
	}
	protected void createCommand(Object oldValue,Object newValue){
		boolean equals = oldValue == null ? false : oldValue.equals(newValue);
		if(!equals){
			EditingDomain editingDomain = getEditingDomain();
			Element value = (Element) newValue;
			String uuid = EmfWorkspace.getId(value);
			CompoundCommand compoundCommand = new CompoundCommand(COMMAND_NAME);
			// apply the property change to all selected elements
			for(EObject nextObject:getEObjectList()){
				compoundCommand.append(SetCommand.create(editingDomain, nextObject, getFeature(), uuid));
			}
			editingDomain.getCommandStack().execute(compoundCommand);
		}
	}
}
