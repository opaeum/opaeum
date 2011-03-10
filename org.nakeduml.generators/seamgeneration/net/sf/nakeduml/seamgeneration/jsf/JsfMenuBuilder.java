package net.sf.nakeduml.seamgeneration.jsf;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.component.UIViewRoot;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.seamgeneration.SeamTransformationPhase;
import net.sf.nakeduml.seamgeneration.jsf.component.JsfAccordianPanelBuilder;
import net.sf.nakeduml.seamgeneration.jsf.component.JsfFactoryAccordion;
import net.sf.nakeduml.seamgeneration.jsf.component.JsfFactoryHierarchy;
import net.sf.nakeduml.seamgeneration.jsf.component.JsfFactoryNavigation;
import net.sf.nakeduml.seamgeneration.jsf.source.JsfMenuSource;
import net.sf.nakeduml.textmetamodel.TextOutputRoot;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.OperationNavigation;
import net.sf.nakeduml.userinteractionmetamodel.OperationParticipationKind;
import net.sf.nakeduml.userinteractionmetamodel.ParticipationGroup;
import net.sf.nakeduml.userinteractionmetamodel.PropertyNavigation;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;

import org.jboss.seam.ui.component.html.HtmlFragment;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.tabview.Tab;

@StepDependency(phase = SeamTransformationPhase.class, after = JsfCreateBuilder.class)
public class JsfMenuBuilder extends AbstractJsfBuilder {

	private UIViewRoot jsfMenu;
	private boolean forCreate;
	private Map<ParticipationGroup, Tab> tabs;
	private AccordionPanel accordion;

	@VisitBefore
	public void populateJSFBody(ClassifierUserInteraction ui) {
		jsfMenu = new UIViewRoot();
		tabs = new TreeMap<ParticipationGroup, Tab>(new Comparator<ParticipationGroup>() {
			@Override
			public int compare(ParticipationGroup o1, ParticipationGroup o2) {
				return o1.getDisplayIndex().compareTo(o2.getDisplayIndex());
			}
		});

		JsfAccordianPanelBuilder jsfAccordianPanelBuilder = JsfFactoryAccordion.instance().getJsfAccordianPanelBuilder(jsfMenu);
		accordion = jsfAccordianPanelBuilder.getAccordion();
		accordion.setMultiple(true);
		accordion.setSpeed(0.2D);
		accordion.setActiveIndex("0");
		setSettedAttributes(accordion, "multiple", "speed", "activeIndex");
		jsfMenu.getChildren().add(accordion);

		if (ui.getUserInteractionKind() != UserInteractionKind.LIST) {
			for (PropertyNavigation pn : ui.getPropertyNavigation()) {
				forCreate = false;

				Tab tab = null;
				 if (pn.getParticipationGroup()!=null) {
					 tab = jsfAccordianPanelBuilder.getAccordionTab(tabs, pn.getParticipationGroup());
				 } else {
					 System.out.println("null");
				 }

				HtmlFragment htmlFragment = null;
				if (pn.getResultingUserInteraction().isTooMany()) {
					htmlFragment = JsfFactoryNavigation.instance().getJsfMenuTooManyLinkBuilder(ui.getClassifier(), pn).createUILink();
				} else {
					htmlFragment = JsfFactoryNavigation.instance().getJsfMenuLinkBuilder(ui.getClassifier(), pn, forCreate).createUILink();
				}

				tab.getChildren().add(htmlFragment);
				if (pn.getResultingUserInteraction().getUserInteractionKind() == UserInteractionKind.EDIT) {
					forCreate = true;
					htmlFragment = JsfFactoryNavigation.instance().getJsfMenuLinkBuilder(ui.getClassifier(), pn, forCreate).createUILink();
					tab.getChildren().add(htmlFragment);
				}

			}

			if (ui.getUserInteractionKind() != UserInteractionKind.CREATE) {
				List<OperationNavigation> operationNavigations = ui.getOperationNavigation();
				for (OperationNavigation operationNavigation : operationNavigations) {
					if (operationNavigation.getParticipationKind() == OperationParticipationKind.VISIBLE) {
						Tab tab = jsfAccordianPanelBuilder.getAccordionTab(tabs, operationNavigation.getParticipationGroup());
						HtmlFragment htmlFragment = JsfFactoryNavigation.instance().getJsfOperationMenuLinkBuilder(ui.getClassifier(), operationNavigation)
						.createUILink();
						tab.getChildren().add(htmlFragment);
					}
				}
			}
			
			if (ui.getInHierarchy()) {
				HtmlFragment htmlFragment = JsfFactoryHierarchy.instance().getJsfTreeViewBuilder().createUIComponent();
				jsfMenu.getChildren().add(htmlFragment);
			}
			

		} else {
			List<ClassifierUserInteraction> listParentUIList = findClassifierUserInteractionOfKind(workspace, ui.getOriginatingPropertyNavigation()
					.getClassifierUserInteraction(), UserInteractionKind.EDIT);
			for (PropertyNavigation pn : listParentUIList.get(0).getPropertyNavigation()) {
				forCreate = false;

				Tab tab = jsfAccordianPanelBuilder.getAccordionTab(tabs, pn.getParticipationGroup());

				HtmlFragment htmlFragment = null;
				if (pn.getResultingUserInteraction().isTooMany()) {
					htmlFragment = JsfFactoryNavigation.instance().getJsfMenuTooManyLinkBuilder(listParentUIList.get(0).getClassifier(), pn).createUILink();
				} else {
					htmlFragment = JsfFactoryNavigation.instance().getJsfMenuLinkBuilder(listParentUIList.get(0).getClassifier(), pn, forCreate).createUILink();
				}

				tab.getChildren().add(htmlFragment);
				if (pn.getResultingUserInteraction().getUserInteractionKind() == UserInteractionKind.EDIT) {
					forCreate = true;
					htmlFragment = JsfFactoryNavigation.instance().getJsfMenuLinkBuilder(listParentUIList.get(0).getClassifier(), pn, forCreate).createUILink();
					tab.getChildren().add(htmlFragment);
				}
			}
		}
	}

	@VisitAfter
	public void generateEntityXhtml(ClassifierUserInteraction ui) {

		for (Tab tab : tabs.values()) {
			accordion.getChildren().add(tab);
		}

		TextOutputRoot outputRoot = textWorkspace.findOrCreateTextOutputRoot(VIEW_DIR);
		List<String> path = resolveViewId(ui, ".menu.xhtml");
		outputRoot.findOrCreateTextFile(path, new JsfMenuSource(jsfMenu, path.size() - 1, namespaceProperties));
	}

	@Override
	protected String getEditRenderedRoot(DomainClassifier dc) {
		return NameConverter.decapitalize(dc.getName());
	}

}
