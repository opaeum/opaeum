package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.ocl.ecore.UnlimitedNaturalLiteralExp;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;
import org.topcased.modeler.editor.properties.TextChangeHelper;
import org.topcased.tabbedproperties.sections.AbstractTabbedPropertySection;

public abstract class AbstractMultiFeaturePropertySection extends AbstractTabbedPropertySection{
	private EStructuralFeature feature;
	public class BooleanSelectionListener implements SelectionListener{
		private EAttribute myFeature;
		public BooleanSelectionListener(EAttribute feature_IsStatic){
			this.myFeature = feature_IsStatic;
		}
		public void widgetSelected(SelectionEvent e){
			feature = myFeature;
			Command cmd = SetCommand.create(getEditingDomain(), getFeatureOwner(), getFeature(), ((Button) e.getSource()).getSelection());
			getEditingDomain().getCommandStack().execute(cmd);
		}
		public void widgetDefaultSelected(SelectionEvent e){
		}
	}
	public class LiteralIntegerTextChangeListener extends TextChangeHelper{
		private EAttribute myFeature;
		private Text control;
		public LiteralIntegerTextChangeListener(EAttribute feature_IsStatic){
			this.myFeature = feature_IsStatic;
		}
		
		@Override
		public void finishNonUserChange(){
			super.finishNonUserChange();
		}
		public void startListeningTo(Control control) {
			super.startListeningForEnter(control);
			this.control=(Text)control;
		}


		public void textChanged(Control control){
			feature = myFeature;
			try{
				String text = ((Text) control).getText();
				int parseInt;
				if(text.contains("*")){
					parseInt = -1;
				}else{
					parseInt = Integer.parseInt(text);
				}
				Command cmd = SetCommand.create(getEditingDomain(), getFeatureOwner(), getFeature(), parseInt);
				getEditingDomain().getCommandStack().execute(cmd);
			}catch(Exception e){
				Command cmd = SetCommand.create(getEditingDomain(), getFeatureOwner(), getFeature(), 0);
				getEditingDomain().getCommandStack().execute(cmd);
			}
		}
	}
	public abstract void refresh();
	@Override
	protected EStructuralFeature getFeature(){
		return feature;
	}
	protected void layout(Control prev,Control cur,int width){
		FormData readOnlyData = new FormData();
		if(prev == null){
			readOnlyData.left = new FormAttachment(0, 0);
		}else{
			readOnlyData.left = new FormAttachment(prev, 0);
		}
		readOnlyData.width = width;
		readOnlyData.top = new FormAttachment(0, 0);
		cur.setLayoutData(readOnlyData);
	}
	protected Element getFeatureOwner(){
		return (Element) getEObject();
	}
}
