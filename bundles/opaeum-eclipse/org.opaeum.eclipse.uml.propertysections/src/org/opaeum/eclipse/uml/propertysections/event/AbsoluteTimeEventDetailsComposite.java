package org.opaeum.eclipse.uml.propertysections.event;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.OpaqueExpressionSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.StringSubsection;

public class AbsoluteTimeEventDetailsComposite extends AbstractDetailsSubsection<TimeEvent>{
	public static final class WhenExpressionSubsection extends OpaqueExpressionSubsection{
		//Remember OpaeumElementLinker will create all the expressions
		public WhenExpressionSubsection(IMultiPropertySection section){
			super(section);
		}
		@Override
		protected EObject getFeatureOwner(EObject eObject){
			return ((TimeEvent)eObject).getWhen();
		}
	}
	private OpaqueExpressionSubsection expressionComposite;
	protected StringSubsection nameTxt;
	protected EditingDomain editingDomain;
	private int labelWidth=AbstractOpaeumPropertySection.STANDARD_LABEL_WIDTH;
	public AbsoluteTimeEventDetailsComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent){
		super(parent, SWT.NONE, toolkit);
	}
	@Override
	protected void addSubsections(){
		nameTxt = createString(UMLPackage.eINSTANCE.getNamedElement_Name(), "Timer Name", labelWidth, AbstractTabbedPropertySubsection.FILL);
		expressionComposite = new WhenExpressionSubsection(this);
		AbstractMultiFeaturePropertySection.populateSubsection(expressionComposite, UMLPackage.eINSTANCE.getTimeExpression_Expr(), "Exact time of event", labelWidth, AbstractTabbedPropertySubsection.FILL);
		expressionComposite.setRowSpan(3);
	}
	public void setLabelWidth(int labelWidth){
		this.labelWidth = labelWidth;
		nameTxt.setLabelWidth(labelWidth);
		expressionComposite.setLabelWidth(labelWidth);
	}
	@Override
	protected int getNumberOfColumns(){
		return 1;
	}


}