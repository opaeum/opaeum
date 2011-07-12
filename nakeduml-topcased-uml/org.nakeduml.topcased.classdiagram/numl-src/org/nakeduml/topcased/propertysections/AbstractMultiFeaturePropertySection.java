package org.nakeduml.topcased.propertysections;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.ecore.UnlimitedNaturalLiteralExp;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
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
			createCommand(getEObject().eGet(myFeature), Boolean.valueOf(((Button) e.getSource()).getSelection()));
		}
		public void widgetDefaultSelected(SelectionEvent e){
		}
	}
	public class LiteralIntegerTextChangeListener extends TextChangeHelper{
		private EAttribute myFeature;
		public LiteralIntegerTextChangeListener(EAttribute feature_IsStatic){
			this.myFeature = feature_IsStatic;
		}
		public void textChanged(Control control){
			feature = myFeature;
			try{
				int parseInt = Integer.parseInt(((Text) control).getText());
				createCommand(getEObject().eGet(myFeature), parseInt);
			}catch(Exception e){
				createCommand(getEObject().eGet(myFeature), 0);
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
}
