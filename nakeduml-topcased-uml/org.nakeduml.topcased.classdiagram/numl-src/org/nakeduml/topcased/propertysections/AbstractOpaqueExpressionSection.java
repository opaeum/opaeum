package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.nakeduml.topcased.commands.SetOclExpressionCommand;
import org.nakeduml.topcased.propertysections.OclValueComposite.OclChangeListener;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;
import org.topcased.tabbedproperties.utils.TextChangeListener;

public abstract class AbstractOpaqueExpressionSection extends AbstractTabbedPropertySection{
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
		oclComposite.setValueElement(getOclContext());
	}
	protected Element getOclContext(){
		return getOwner();
	}
	protected String getFeatureAsString(){
		OpaqueExpression expression = getExpression(getEObject());
		if(expression == null || expression.getBodies().size() == 0){
			return "Type expression here";
		}else{
			return expression.getBodies().get(0);
		}
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
		oclComposite = new OclValueComposite(composite, getWidgetFactory(), new OclChangeListener(){
			@Override
			public void oclChanged(String oclText){
				handleOclChanged(oclText);
			}

		});
		oclComposite.setBackground(composite.getBackground());
	}
	protected void handleOclChanged(String oclText){
		if(oclText.trim().length() > 0){
			Command cmd = SetOclExpressionCommand.create(getEditingDomain(), getOwner(), UMLPackage.eINSTANCE.getProperty_DefaultValue(), oclText);
			getEditingDomain().getCommandStack().execute(cmd);
		}
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
		fd.left = new FormAttachment(0, getStandardLabelWidth(composite, new String[]{
			getLabelText()
		}));
		fd.height = 50;
		this.oclComposite.setLayoutData(fd);
	}
	public void refresh(){
		super.refresh();
		if(getValueSpecification() instanceof OpaqueExpression){
			oclComposite.getTextControl().setText(getFeatureAsString());
		}else{
			oclComposite.getTextControl().setText(OclValueComposite.DEFAULT_TEXT);
		}
	}
	@Override
	protected void setEnabled(boolean enabled){
		super.setEnabled(enabled);
		if(oclComposite.getTextControl() != null){
			oclComposite.getTextControl().setEnabled(enabled);
		}
	}
}