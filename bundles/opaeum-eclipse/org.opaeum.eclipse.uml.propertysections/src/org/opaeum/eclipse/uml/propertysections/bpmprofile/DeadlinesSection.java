package org.opaeum.eclipse.uml.propertysections.bpmprofile;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeEvent;
import org.opaeum.eclipse.uml.propertysections.base.AbstractMasterDetailSection;
import org.opaeum.eclipse.uml.propertysections.core.AbstractTableComposite;
import org.opaeum.eclipse.uml.propertysections.event.AbsoluteTimeEventDetailsComposite;
import org.opaeum.eclipse.uml.propertysections.subsections.IDetailsSubsection;
import org.opaeum.eclipse.uml.propertysections.subsections.StackDetailsSubsection;
import org.opaeum.metamodel.core.internal.TagNames;

public class DeadlinesSection extends AbstractMasterDetailSection<TimeEvent>{
	public DeadlinesSection(){
		super("Details of Deadline");
	}
	protected static final String RELATIVE = "R";
	protected static final String ABSOLUTE = "A";
	private DeadlinesTableComposite table;
	private AbsoluteDeadlineDetailsComposite absoluteComposite;
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
				addLayer(ABSOLUTE, absoluteComposite = new AbsoluteDeadlineDetailsComposite(factory, composite));
			}
			@Override
			protected String getKeyFor(List<TimeEvent> eObjectList){
				if(eObjectList.isEmpty()){
					return ABSOLUTE;
				}
				return eObjectList.get(0).isRelative() ? RELATIVE : ABSOLUTE;
			}
		};
	}
	@Override
	protected EObject getFeatureOwner(EObject e){
		for(EObject eObject:((Element) e).getStereotypeApplications()){
			if(eObject.eClass().getEStructuralFeature(TagNames.DEADLINES) != null){
				return eObject;
			}
		}
		return super.getFeatureOwner(e);
	}
	@Override
	protected AbstractTableComposite<TimeEvent> createTable(Composite composite){
		return table = new DeadlinesTableComposite(composite, SWT.NONE, getWidgetFactory());
	}
	protected EStructuralFeature getFeature(EObject e){
		return e.eClass().getEStructuralFeature(TagNames.DEADLINES);
	}
	protected EStructuralFeature getFeature(){
		return getFeature(getFeatureOwner(getSelectedObject()));
	}
	public String getLabelText(){
		return null;
	}
}