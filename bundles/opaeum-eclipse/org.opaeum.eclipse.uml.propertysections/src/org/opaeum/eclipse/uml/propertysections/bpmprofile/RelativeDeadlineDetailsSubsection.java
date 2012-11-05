package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.TimeEvent;
import org.eclipse.uml2.uml.UMLPackage;
import org.opaeum.eclipse.commands.StereotypeValueInformation;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMultiFeaturePropertySection;
import org.opaeum.eclipse.uml.propertysections.base.AbstractOpaeumPropertySection;
import org.opaeum.eclipse.uml.propertysections.base.AbstractTabbedPropertySubsection;
import org.opaeum.eclipse.uml.propertysections.event.AbsoluteTimeEventDetailsComposite;
import org.opaeum.eclipse.uml.propertysections.event.AbsoluteTimeEventDetailsComposite.WhenExpressionSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.AbstractDetailsSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.ComboOnStereotypeSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.OpaqueExpressionOnStereotypeSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.StringSubsection;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;

public class RelativeDeadlineDetailsSubsection extends AbstractDetailsSubsection<TimeEvent>{
	int labelWidth=AbstractOpaeumPropertySection.STANDARD_LABEL_WIDTH;
	private ComboOnStereotypeSubsection timeUnitCombo;
	private OpaqueExpressionOnStereotypeSubsection businessCalendarToUseComposite;
	private StringSubsection nameTxt;
	private WhenExpressionSubsection expressionComposite;
	private ComboOnStereotypeSubsection deadlineKindCombo;

	public RelativeDeadlineDetailsSubsection(TabbedPropertySheetWidgetFactory widgetFactory,Composite parent){
		super(parent, SWT.NONE, widgetFactory);
	}
	@Override
	protected void addSubsections(){
		labelWidth=AbstractOpaeumPropertySection.STANDARD_LABEL_WIDTH;//CAlled before this object's init is invoked
		nameTxt = createString(UMLPackage.eINSTANCE.getNamedElement_Name(), "Timer Name", labelWidth, AbstractTabbedPropertySubsection.FILL);
		deadlineKindCombo=new ComboOnStereotypeSubsection(this,getDeadlineKindInfo());
		AbstractMultiFeaturePropertySection.populateSubsection(deadlineKindCombo, null, "Deadline Kind", labelWidth, AbstractTabbedPropertySubsection.FILL);
		timeUnitCombo=new ComboOnStereotypeSubsection(this,getTimeUnitMetaInfo());
		AbstractMultiFeaturePropertySection.populateSubsection(timeUnitCombo, null, "Time Unit", labelWidth, AbstractTabbedPropertySubsection.FILL);
		expressionComposite = new AbsoluteTimeEventDetailsComposite.WhenExpressionSubsection(this);
		AbstractMultiFeaturePropertySection.populateSubsection(expressionComposite, UMLPackage.eINSTANCE.getTimeExpression_Expr(), "Exact time of event", labelWidth, AbstractTabbedPropertySubsection.FILL);
		expressionComposite.setRowSpan(3);
		businessCalendarToUseComposite=new OpaqueExpressionOnStereotypeSubsection(this, getBusinessCalendarToUseMetaInfo());
		AbstractMultiFeaturePropertySection.populateSubsection(businessCalendarToUseComposite, null, "Business Calendar to use", labelWidth, AbstractTabbedPropertySubsection.FILL);
	}
	private StereotypeValueInformation getDeadlineKindInfo(){
		return new StereotypeValueInformation(StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.DEADLINE, TagNames.DEADLINE_KIND);
	}

	public void setLabelWidth(int labelWidth){
		this.labelWidth = labelWidth;
		nameTxt.setLabelWidth(labelWidth);
		expressionComposite.setLabelWidth(labelWidth);
		timeUnitCombo.setLabelWidth(labelWidth);
		businessCalendarToUseComposite.setLabelWidth(labelWidth);
		deadlineKindCombo.setLabelWidth(labelWidth);
	}
	protected StereotypeValueInformation getBusinessCalendarToUseMetaInfo(){
		return new StereotypeValueInformation(StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.DEADLINE, TagNames.BUSINESS_CALENDAR_TO_USE);
	}
	protected StereotypeValueInformation getTimeUnitMetaInfo(){
		return new StereotypeValueInformation(StereotypeNames.OPAEUM_BPM_PROFILE, StereotypeNames.DEADLINE, TagNames.TIME_UNIT);
	}
}
