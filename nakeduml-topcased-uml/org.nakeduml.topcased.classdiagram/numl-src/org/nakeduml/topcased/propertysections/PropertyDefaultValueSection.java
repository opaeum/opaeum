package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

public class PropertyDefaultValueSection extends AbstractOpaqueExpressionSection{
	protected void handleModelChanged(Notification msg){
		Object notifier = msg.getNotifier();
		if(notifier.equals(getProperty())){
			oclComposite.setAutoDeleteOpaqueExpression(!getProperty().isDerived() || getProperty().isDerivedUnion());
			if(msg.getFeatureID(Property.class) == UMLPackage.eINSTANCE.getProperty_IsDerived().getFeatureID()){
				label.setText(getAppropriateLabelText());
				refresh();
			}
			if(getProperty().isDerived() && !(getProperty().isDerivedUnion() || getProperty().getDefaultValue() instanceof OpaqueExpression)){
				createOpaqueExpression();
				//Give  the UmlCache time to sync
				Display.getDefault().timerExec(500, new Runnable(){
					@Override
					public void run(){
						oclComposite.highlightError();
					}
				});
			}

		}
		super.handleModelChanged(msg);
	}
	private void createOpaqueExpression(){
		OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
		oe.setName("abc");
		oe.getLanguages().add("OCL");
		oe.getBodies().add("Derivation Rule Required");
		getEditingDomain().getCommandStack().execute(SetCommand.create(getEditingDomain(), getProperty(), getValueSpecificationFeature(), oe));
	}
	@Override
	protected NamedElement beforeOclChanged(String text){
		return super.beforeOclChanged(text);
	}
	@Override
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
	}
	public String getAppropriateLabelText(){
		return getProperty().isDerived() ? "Derived Value" : "Initial Value";
	}
	@Override
	public void refresh(){
		super.refresh();
		label.setText(getAppropriateLabelText());
	}
	protected String getLabelText(){
		return "Default Value";
	}
	public Property getProperty(){
		return getProperty(getEObject());
	}
	protected Property getProperty(EObject e){
		return (Property) e;
	}
	@Override
	protected NamedElement getValueSpecificationOwner(){
		return getProperty();
	}
	@Override
	protected EReference getValueSpecificationFeature(){
		return UMLPackage.eINSTANCE.getProperty_DefaultValue();
	}
}
