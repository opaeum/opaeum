package org.opaeum.uim.userinteractionproperties.sections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Operation;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.topcased.propertysections.OpaeumChooserPropertySection;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UmlReference;
import org.opaeum.uim.action.LinkToQuery;
import org.opaeum.uim.action.OperationButton;
import org.opaeum.uim.provider.UimItemProviderAdapterFactory;
import org.opaeum.uim.util.UmlUimLinks;
import org.topcased.tabbedproperties.AbstractTabbedPropertySheetPage;
import org.topcased.tabbedproperties.internal.utils.Messages;
import org.topcased.tabbedproperties.providers.TabbedPropertiesLabelProvider;

public class LinkToQueryOperationSection extends OpaeumChooserPropertySection{
	protected String getLabelText(){
		return "Operation:";
	}
	protected EStructuralFeature getFeature(){
		return UimPackage.eINSTANCE.getUmlReference_UmlElementUid();
	}
	protected Object getFeatureValue(){
		return UmlUimLinks.getCurrentUmlLinks(getOperationAction()).getOperation(getOperationAction());
	}
	private LinkToQuery getOperationAction(){
		return (LinkToQuery) getEObject();
	}
	protected Object[] getComboFeatureValues(){
		Collection<Operation> results = new ArrayList<Operation>();
		if(getEObject() instanceof LinkToQuery){
			LinkToQuery oa = getOperationAction();
			results.addAll(UmlUimLinks.getCurrentUmlLinks(oa).getNearestClass(oa).getAllOperations());
		}
		return getQueryOperations(results);
	}
	private Object[] getQueryOperations(Collection<Operation> opers){
		List<Operation> results = new ArrayList<Operation>();
		for(Operation operation:opers){
			if(operation.getReturnResult() != null && operation.getReturnResult().getType() != null && operation.isQuery()
					&& !StereotypesHelper.hasStereotype(operation, "Responsibility")){
				results.add(operation);
			}
		}
		return (Operation[]) results.toArray(new Operation[results.size()]);
	}
	protected ILabelProvider getLabelProvider(){
		List f = new ArrayList();
		f.add(new UimItemProviderAdapterFactory());
		f.addAll(AbstractTabbedPropertySheetPage.getPrincipalAdapterFactories());
		return new TabbedPropertiesLabelProvider(new ComposedAdapterFactory(f));
	}
	protected void createCommand(Object oldValue,Object newValue){
		boolean equals = oldValue == null ? false : oldValue.equals(newValue);
		if(!equals){
			EditingDomain editingDomain = getEditingDomain();
			Element value = (Element) newValue;
			String uuid = EmfWorkspace.getId(value);
			CompoundCommand compoundCommand = new CompoundCommand(Messages.AbstractTabbedPropertySection_CommandName);
			// apply the property change to all selected elements
			for(EObject nextObject:getEObjectList()){
				compoundCommand.append(SetCommand.create(editingDomain, nextObject, getFeature(), uuid));
			}
			editingDomain.getCommandStack().execute(compoundCommand);
		}
	}
	UmlReference getReference(){
		return getOperationAction();
	}
}
