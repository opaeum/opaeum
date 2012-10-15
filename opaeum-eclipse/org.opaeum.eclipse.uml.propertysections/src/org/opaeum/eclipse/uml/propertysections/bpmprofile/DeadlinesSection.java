package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMasterDetailSection;
import org.opaeum.eclipse.uml.propertysections.core.AbstractTableComposite;
import org.opaeum.eclipse.uml.propertysections.event.AbsoluteTimeEventDetailsComposite;
import org.opaeum.eclipse.uml.propertysections.event.RelativeTimeEventDetailsComposite;
import org.opaeum.eclipse.uml.propertysections.subsections.IDetailsSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.StackDetailsSubsection;

public class DeadlinesSection extends AbstractMasterDetailSection<TimeEvent>{
	public DeadlinesSection(){
		super("Details of Deadline");
	}
	protected static final String RELATIVE = "R";
	protected static final String ABSOLUTE = "A";
	private DeadlinesTableComposite table;
	private AbsoluteTimeEventDetailsComposite absoluteComposite;
	private RelativeDeadlineDetailsSubsection relativeComposite;
	private Group groupDetails;
	private EList<EEnumLiteral> deadlineKinds;
	private Stereotype deadlineStereotype;
	private Stereotype stereotype;
	@Override
	protected IDetailsSubsection<TimeEvent> createDetails(Group group){
		return new StackDetailsSubsection<TimeEvent>(getWidgetFactory(), group, "Deadline Details"){
			@Override
			protected void addLayers(Composite composite,TabbedPropertySheetWidgetFactory factory){
				addLayer(RELATIVE, relativeComposite = new RelativeDeadlineDetailsSubsection(factory, composite));
				addLayer(ABSOLUTE, absoluteComposite = new AbsoluteTimeEventDetailsComposite(factory, composite));
			}
			@Override
			protected String getKeyFor(List<TimeEvent> eObjectList){
				return eObjectList.get(0).isRelative() ? RELATIVE : ABSOLUTE;
			}
		};
	}
	@Override
	protected AbstractTableComposite<TimeEvent> createTable(Composite composite){
		return table = new DeadlinesTableComposite(composite, SWT.NONE, getWidgetFactory());
	}
	protected EStructuralFeature getFeature(){
		return null;
	}
	public String getLabelText(){
		return null;
	}
}