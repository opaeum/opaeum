package net.sf.nakeduml.emf.extraction;

import java.util.List;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedOpaqueBehaviorImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedSignalImpl;
import net.sf.nakeduml.metamodel.components.internal.NakedComponentImpl;
import net.sf.nakeduml.metamodel.compositestructures.internal.NakedCollaborationImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedPowerType;
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.INakedValueType;
import net.sf.nakeduml.metamodel.core.internal.NakedAssociationClassImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedAssociationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedEntityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedEnumerationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedInterfaceImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPackageImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPowerTypeImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.internal.NakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.NakedValueSpecificationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedValueTypeImpl;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.models.internal.NakedModelImpl;
import net.sf.nakeduml.metamodel.profiles.INakedStereotype;
import net.sf.nakeduml.metamodel.profiles.internal.NakedProfileImpl;
import net.sf.nakeduml.metamodel.profiles.internal.NakedStereotypeImpl;
import net.sf.nakeduml.metamodel.statemachines.StateMachineKind;
import net.sf.nakeduml.metamodel.statemachines.internal.NakedStateMachineImpl;
import net.sf.nakeduml.metamodel.usecases.internal.NakedActorImpl;
import net.sf.nakeduml.metamodel.usecases.internal.NakedUseCaseImpl;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.VisibilityKind;
import nl.klasse.octopus.model.internal.parser.parsetree.ParsedOclString;

import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.AssociationClass;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Component;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UseCase;

/**
 * Builds all classifier and the namespaces required to hostr them
 */
@StepDependency(phase = EmfExtractionPhase.class)
public class NameSpaceExtractor extends AbstractExtractorFromEmf {
	/**
	 * For imported profiles. Put them at the top level of the workspace
	 */
	@VisitBefore
	public void visitProfile(Profile p) {
		INakedPackage pw = new NakedProfileImpl();
		pw.initialize(getId(p), p.getName());
		this.workspace.putModelElement(pw);
	}

	@VisitBefore
	public void visitStereotype(Stereotype c) {
		if (getNakedPeer(c) == null) {
			INakedStereotype ns = new NakedStereotypeImpl();
			initialize(ns, c, c.getNamespace());
		}
	}

	@VisitBefore
	public void visitModel(Model p) {
		INakedModel pw = new NakedModelImpl();
		pw.initialize(getId(p), p.getName());
		this.workspace.putModelElement(pw);
	}

	@VisitBefore
	public void visitPackage(Package p, NakedPackageImpl np) {
	}

	@VisitBefore
	public void visitComponent(Component c, NakedComponentImpl nc) {
		initializeClassifier(nc, c);
	}

	@VisitBefore
	public void visitActor(Actor a, NakedActorImpl na) {
		initializeClassifier(na, a);
	}

	@VisitBefore
	public void visitUseCase(UseCase uc, NakedUseCaseImpl nuc) {
		initializeClassifier(nuc, uc);
	}

	@VisitBefore
	public void visitCollaboration(Collaboration c, NakedCollaborationImpl nc) {
		initializeClassifier(nc, c);
	}

	@VisitBefore
	public void visitPrimitiveType(PrimitiveType p) {
		INakedPrimitiveType npt = new NakedPrimitiveType();
		initialize(npt, p, p.getNamespace());
		initializeClassifier(npt, p);
	}

	@VisitBefore
	public void visitClass(Class c) {
		INakedEntity ne = new NakedEntityImpl();
		initialize(ne, c, c.getNamespace());
		boolean representsUser = representsUser(c);
		ne.setRepresentsUser(representsUser);
		initializeClassifier(ne, c);
	}

	private boolean representsUser(Classifier c) {
		boolean representsUser = StereotypesHelper.hasStereotype(c, new String[] { "businessworker", "caseworker", "worker", "user",
				"userrole" });
		if (!representsUser) {
			for (Dependency o : c.getClientDependencies()) {
				if (o.getSuppliers().get(0) instanceof Actor) {
					representsUser = true;
				}
			}
		}
		return representsUser;
	}

	@VisitBefore
	public void visitInterface(Interface i, NakedInterfaceImpl ni) {
		initializeClassifier(ni, i);
		ni.setRepresentsUser(representsUser(i));
	}

	@VisitBefore
	public void visitEnumeration(Enumeration e) {
		if (StereotypesHelper.hasStereotype(e, "powertype") || e.getPowertypeExtents().size() > 0) {
			INakedPowerType npt = new NakedPowerTypeImpl();
			initialize(npt, e, e.getNamespace());
			initializeClassifier(npt, e);
		} else {
			INakedEnumeration ne = new NakedEnumerationImpl();
			initialize(ne, e, e.getNamespace());
			initializeClassifier(ne, e);
		}
	}

	@VisitBefore
	public void visitActivity(Activity a, NakedActivityImpl na) {
		ActivityKind kind = null;
		if (StereotypesHelper.hasStereotype(a, new String[] { "process", "businessprocess" })) {
			kind = ActivityKind.PROCESS;
		} else if (StereotypesHelper.hasStereotype(a, new String[] { "simpleMethod" })) {
			kind = ActivityKind.SIMPLE_SYNCHRONOUS_METHOD;
		} else {
			kind = ActivityKind.COMPLEX_SYNCHRONOUS_METHOD;
		}
		na.setActivityKind(kind);
		addConstraints(na, a.getPreconditions(), a.getPostconditions());
		initializeClassifier(na, a);
	}

	@VisitBefore
	public void visitStateMachine(StateMachine sm, NakedStateMachineImpl nsm) {
		StateMachineKind kind = StateMachineKind.LONG_LIVED;
		if (StereotypesHelper.hasStereotype(sm, new String[] { "screenFlow" })) {
			kind = StateMachineKind.SCREEN_FLOW;
		}
		nsm.setStateMachineKind(kind);
		addConstraints(nsm, sm.getPreconditions(), sm.getPostconditions());
		initializeClassifier(nsm, sm);
	}

	@VisitBefore
	public void visitOpaqueBehavior(OpaqueBehavior a, NakedOpaqueBehaviorImpl nob) {
		String body = findOclBody(a);
		if (body != null && body.length() > 0) {
			ParsedOclString string = new ParsedOclString(a.getName() + "BODY", OclUsageType.BODY);
			string.setExpressionString(body);
			string.setContext(nob.getContext(), nob);
			getErrorMap().linkElement(string, a);
			NakedValueSpecificationImpl nvs = new NakedValueSpecificationImpl();
			nvs.initialize(getId(a) + "BODY", a.getName());
			nvs.setOwnerElement(nob);
			workspace.putModelElement(nvs);
			nvs.setValue(string);
			nob.setBody(nvs);
			getErrorMap().linkElement(nvs, a);
		}
		addConstraints(nob, a.getPreconditions(), a.getPostconditions());
		// addParametersAndConstraints(b, nakedOpaqueBehavior);
		initializeClassifier(nob, a);
	}

	private String findOclBody(OpaqueBehavior a) {
		if (a.getBodies().size() == 1 && a.getLanguages().size() == 0) {
			return a.getBodies().get(0);
		}
		List languages = a.getLanguages();
		// find the OCL body
		int i = 0;
		for (i = 0; i < languages.size(); i++) {
			if ("OCL".equalsIgnoreCase(languages.get(i).toString())) {
				if (a.getBodies().size() > i) {
					return a.getBodies().get(i);
				}
			}
		}
		return null;
	}

	@VisitBefore
	public void visitSignal(Signal s, NakedSignalImpl ns) {
		initializeClassifier(ns, s);
	}

	@VisitBefore
	public void visitDataType(DataType dt) {
		if (StereotypesHelper.hasStereotype(dt, StereotypeNames.VALUE_TYPE)) {
			INakedValueType nvt = new NakedValueTypeImpl();
			initialize(nvt, dt, dt.getNamespace());
			initializeClassifier(nvt, dt);
		} else {
			INakedStructuredDataType nsdt = new NakedStructuredDataType();
			initialize(nsdt, dt, dt.getNamespace());
			initializeClassifier(nsdt, dt);
		}
	}

	@VisitBefore
	public void visitAssociation(Association a, NakedAssociationImpl na) {
		na.setDerived(a.isDerived());
		initializeClassifier(na, a);
	}

	@VisitBefore
	public void visitAssociationClass(AssociationClass a, NakedAssociationClassImpl na) {
		na.setDerived(a.isDerived());
		initializeClassifier(na, a);
	}

	protected void initializeClassifier(INakedClassifier classifier, Classifier emfClassifier) {
		if (classifier instanceof INakedBehavior && classifier.getOwnerElement() == null) {
			// try our best to get the containment tree in place - might be
			// changed
			// later
			Behavior b = (Behavior) emfClassifier;
			classifier.setOwnerElement(getNakedPeer(b.getContext()));
		}
		classifier.setVisibility(VisibilityKind.PUBLIC);
		classifier.setIsAbstract(emfClassifier.isAbstract());
	}
}