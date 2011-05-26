package org.nakeduml.topcased.propertysections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecoretools.tabbedproperties.sections.AbstractEnumerationPropertySection;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain.EditingDomainProvider;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.activities.WorkbenchActivityHelper;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.topcased.modeler.uml.UMLPlugin;
import org.topcased.modeler.uml.editor.UMLEditor;

public class TimeUnitSection extends AbstractEnumerationPropertySection{
	Stereotype stereotype;
	Enumeration timeUnit;
	CCombo combo;
	@Override
	protected void createWidgets(Composite composite){
		super.createWidgets(composite);
	}
	private Profile getNumlProfile(){
		Profile p = null;
		IEditorReference[] ers = getActivePage().getEditorReferences();
		for(IEditorReference er:ers){
			IEditorPart e = er.getEditor(false);
			if(e instanceof UMLEditor){
				UMLEditor ue=(UMLEditor) e;
				EList<Resource> resources =  ue.getResourceSet().getResources();
				for(Resource r:resources){
					if(r.getURI().toString().contains("NakedUMLProfile")){
						p = (Profile) r.getContents().get(0);
						break;
					}
				}
			}
		}
		return p;
	}
	public Enumeration getTimeUnitEnum(){
		if(this.timeUnit == null){
			this.timeUnit = (Enumeration) getNumlProfile().getOwnedType("TimeUnit");
		}
		return this.timeUnit;
	}
	@Override
	protected void setSectionData(Composite composite){
		super.setSectionData(composite);
	}
	@Override
	protected void handleModelChanged(Notification msg){
		refresh();
		TimeEvent te = (TimeEvent) getEObject();
	}
	@Override
	protected void createCommand(final Object oldValue,final Object newValue){
		boolean equals = oldValue == null ? false : oldValue.equals(newValue);
		if(!equals){
			EditingDomain editingDomain = getEditingDomain();
			Object value = newValue;
			CompoundCommand compoundCommand = new CompoundCommand("Set TimeUnit");
			// apply the property change to all selected elements
			for(EObject nextObject:getEObjectList()){
				compoundCommand.append(new Command(){
					@Override
					public boolean canExecute(){
						return true;
					}
					@Override
					public void execute(){
						((TimeEvent) getEObject()).setValue(stereotype, "timeUnit", newValue);
					}
					@Override
					public boolean canUndo(){
						return true;
					}
					@Override
					public void undo(){
						((TimeEvent) getEObject()).setValue(stereotype, "timeUnit", oldValue);
					}
					@Override
					public void redo(){
						execute();
					}
					@Override
					public Collection<?> getResult(){
						return Collections.singleton(newValue);
					}
					@Override
					public Collection<?> getAffectedObjects(){
						return Collections.singleton(getEObject());
					}
					@Override
					public String getLabel(){
						return "Set Timeunit";
					}
					@Override
					public String getDescription(){
						return "";
					}
					@Override
					public void dispose(){
					}
					@Override
					public Command chain(Command command){
						return null;
					}
				});
			}
			editingDomain.getCommandStack().execute(compoundCommand);
		}
	}
	@Override
	protected EStructuralFeature getFeature(){
		return null;
	}
	@Override
	protected String getLabelText(){
		return "TimeUnit";
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		if(!getTimeEvent().isStereotypeApplied(getStereotype())){
			getTimeEvent().applyStereotype(getStereotype());
		}
	}
	private Stereotype getStereotype(){
		return getNumlProfile().getOwnedStereotype("RelativeTimeEvent");
	}
	@Override
	protected String[] getEnumerationFeatureValues(){
		List<String> result = new ArrayList<String>();
		Enumeration tu = getTimeUnitEnum();
		for(EnumerationLiteral l:tu.getOwnedLiterals()){
			result.add(l.getName());
		}
		return (String[]) result.toArray(new String[result.size()]);
	}
	@Override
	protected String getFeatureAsText(){
		EnumerationLiteral l = (EnumerationLiteral) getOldFeatureValue();
		if(l != null){
			return l.getName();
		}else{
			return "";
		}
	}
	@Override
	protected Object getFeatureValue(int index){
		return getTimeUnitEnum().getOwnedLiterals().get(index);
	}
	@Override
	protected Object getOldFeatureValue(){
		return getTimeEvent().getValue(getStereotype(), "timeUnit");
	}
	private TimeEvent getTimeEvent(){
		return (TimeEvent) getEObject();
	}
}
