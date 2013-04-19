package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.commands.StereotypeValueInformation;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.base.IMultiPropertySection;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.ComboOnStereotypeSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.OclExpressionSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.StringSubsection;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class AbsoluteDeadlineDetailsComposite extends AbstractDetailsSubsection<TimeEvent>{
	public static final class WhenExpressionSubsection extends OclExpressionSubsection{
		//Remember OpaeumElementLinker will create all the expressions
		public WhenExpressionSubsection(IMultiPropertySection section){
			super(section);
		}
		@Override
		protected EObject getFeatureOwner(EObject eObject){
			return ((TimeEvent)eObject).getWhen();
		}
	}
	private OclExpressionSubsection expressionComposite;
	protected StringSubsection nameTxt;
	protected EditingDomain editingDomain;
	private int labelWidth=AbstractOpaeumPropertySection.STANDARD_LABEL_WIDTH;
	private ComboOnStereotypeSubsection deadlineKindCombo;
	public AbsoluteDeadlineDetailsComposite(TabbedPropertySheetWidgetFactory toolkit,Composite parent){
		super(parent, SWT.NONE, toolkit);
	}
	@Override
	protected void addSubsections(){
		labelWidth=AbstractOpaeumPropertySection.STANDARD_LABEL_WIDTH;//CAlled before this object's init is invoked
		
		nameTxt = createString(UMLPackage.eINSTANCE.getNamedElement_Name(), "Deadline Name", labelWidth, AbstractTabbedPropertySubsection.FILL);
		deadlineKindCombo=new ComboOnStereotypeSubsection(this,getDeadlineKindInfo());
		AbstractMultiFeaturePropertySection.populateSubsection(deadlineKindCombo, null, "Deadline Kind", labelWidth, AbstractTabbedPropertySubsection.FILL);
		expressionComposite = new WhenExpressionSubsection(this);
		AbstractMultiFeaturePropertySection.populateSubsection(expressionComposite, UMLPackage.eINSTANCE.getTimeExpression_Expr(), "Exact time of deadline", labelWidth, AbstractTabbedPropertySubsection.FILL);
		expressionComposite.setRowSpan(3);
	}
	private StereotypeValueInformation getDeadlineKindInfo(){
		return new StereotypeValueInformation(StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.DEADLINE, TagNames.DEADLINE_KIND);
	}

	public void setLabelWidth(int labelWidth){
		this.labelWidth = labelWidth;
		nameTxt.setLabelWidth(labelWidth);
		deadlineKindCombo.setLabelWidth(labelWidth);
		expressionComposite.setLabelWidth(labelWidth);
		
	}

}