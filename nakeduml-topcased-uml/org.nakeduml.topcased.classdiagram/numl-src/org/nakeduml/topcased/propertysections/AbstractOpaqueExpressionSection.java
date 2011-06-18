package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.OpaqueExpression;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public abstract class AbstractOpaqueExpressionSection extends AbstractTabbedPropertySection{
	protected static final String DEFAULT_TEXT = "Type expression here";
	private TextChangeListener listener;
	protected OclValueComposite oclComposite;
	protected CLabel label;
	public AbstractOpaqueExpressionSection(){
		super();
	}
	protected abstract OpaqueExpression getExpression(EObject e);
	protected abstract NamedElement getOwner();
	protected abstract ValueSpecification getValueSpecification();
	public void setInput(IWorkbenchPart part,ISelection selection){
		super.setInput(part, selection);
		forceOpaqueExpression();
		oclComposite.setValueElement(getOclContext());
	}
	protected Element getOclContext(){
		return getOwner();
	}
	protected void forceOpaqueExpression(){
		if(!(getValueSpecification() instanceof OpaqueExpression)){
			OpaqueExpression oe = UMLFactory.eINSTANCE.createOpaqueExpression();
			oe.setName(getOwner().getName() + "Expression");
			Command cmd = SetCommand.create(getEditingDomain(), getOwner(), getFeature(), oe);
			if(oe.getBodies().isEmpty()){
				oe.getBodies().add(DEFAULT_TEXT);
				oe.getLanguages().add("OCL");
			}
			getEditingDomain().getCommandStack().execute(cmd);
		}else if(getExpression(getEObject()).getBodies().size()==0){
			OpaqueExpression oe = getExpression(getEObject());
			if(oe.getBodies().isEmpty()){
				oe.getBodies().add(DEFAULT_TEXT);
				oe.getLanguages().add("OCL");
			}
		}
	}
	protected String getFeatureAsString(){
		return getExpression(getEObject()).getBodies().get(0);
	}
	protected Object getNewFeatureValue(String newText){
		return newText;
	}
	protected Object getOldFeatureValue(){
		if(getValueSpecification() instanceof OpaqueExpression){
			return getExpression(getEObject()).getBodies().get(0);
		}else{
			return "";
		}
	}
	protected void createWidgets(Composite composite){
		label = getWidgetFactory().createCLabel(composite, getLabelText());
		oclComposite = new OclValueComposite(composite, getWidgetFactory());
		oclComposite.setBackground(composite.getBackground());
	}
	protected String getExpressionLabel(){
		return "Value expression";
	}
	protected void setSectionData(Composite composite){
		FormData labelFd = new FormData();
		labelFd.left = new FormAttachment(0, 0);
		
		this.label.setLayoutData(labelFd);
		FormData fd = new FormData(400, 400);
		fd.right = new FormAttachment(100, 0);
		fd.left = new FormAttachment(0,getStandardLabelWidth(composite, new String[]{getLabelText()}));
		fd.height = 50;
		this.oclComposite.setLayoutData(fd);
	}
	protected void hookListeners(){
		listener = new TextChangeListener(){
			public void textChanged(Control control){
				handleTextModified();
			}
			public void focusIn(Control control){
			}
			public void focusOut(Control control){
			}
		};
		listener.startListeningTo(oclComposite.getTextControl());
	}
	public void refresh(){
		super.refresh();
		if(getValueSpecification() instanceof OpaqueExpression){
			oclComposite.getTextControl().setText(getFeatureAsString());
		}
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		if(oclComposite.getTextControl() != null){
			oclComposite.getTextControl().setEnabled(enabled);
		}
	}
	protected void handleTextModified(){
		Command cmd = SetCommand.create(getEditingDomain(), getExpression(getEObject()), UMLPackage.eINSTANCE.getOpaqueExpression_Body(), this.oclComposite
				.getTextControl().getText(), 0);
		getEditingDomain().getCommandStack().execute(cmd);
	}
	protected TextChangeListener getListener(){
		return listener;
	}
}