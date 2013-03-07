package org.opaeum.eclipse.uml.propertysections.constraints;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.uml.propertysections.common.IChoiceProvider;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.ChooserSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.InstanceValueSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.OclExpressionSubsection;

public class OclConstraintDetailsComposite extends AbstractDetailsSubsection<Constraint>{
	public OclConstraintDetailsComposite(TabbedPropertySheetWidgetFactory factory,Composite parent){
		super(parent, SWT.NONE, factory);
	}
	@Override
	protected void addSubsections(){
		createString(UMLPackage.eINSTANCE.getNamedElement_Name(), "Name", 120, 280);
		ChooserSubsection chooser = createChooser(UMLPackage.eINSTANCE.getConstraint_ConstrainedElement(), "Constrained Elements", 120, 280, new IChoiceProvider(){
			@Override
			public Object[] getChoices(){
				List<Object> result = new ArrayList<Object>();
				result.add("");
				EObject context = getSelectedObject().eContainer();
				if(context instanceof Operation){
					result.addAll(((Operation) context).getOwnedParameters());
				}
				if(context instanceof Behavior){
					result.addAll(((Behavior) context).getOwnedParameters());
				}
				if(context instanceof Classifier){
					result.addAll(EmfPropertyUtil.getEffectiveProperties((Classifier) context));
				}
				return result.toArray();
			}
		});
		chooser.setSingle(false);
		OclExpressionSubsection ocl = createOpaqueExpression(UMLPackage.eINSTANCE.getConstraint_Specification(),"Expression", 120,300);
		ocl.setRowSpan(2);
		ocl.setColumnSpan(2);
	}
	
}
