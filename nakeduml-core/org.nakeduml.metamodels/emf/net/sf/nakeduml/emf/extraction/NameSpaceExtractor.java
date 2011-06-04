package net.sf.nakeduml.emf.extraction;

import java.io.File;

import net.sf.nakeduml.feature.StepDependency;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.internal.NakedActivityImpl;
import net.sf.nakeduml.metamodel.bpm.internal.NakedBusinessServiceImpl;
import net.sf.nakeduml.metamodel.bpm.internal.NakedUserInRoleImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedOpaqueBehaviorImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.internal.NakedSignalImpl;
import net.sf.nakeduml.metamodel.components.internal.NakedComponentImpl;
import net.sf.nakeduml.metamodel.compositestructures.internal.NakedCollaborationImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedPowerType;
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.INakedValueType;
import net.sf.nakeduml.metamodel.core.internal.NakedAssociationClassImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedAssociationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedEntityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedEnumerationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedHelperClassImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedInterfaceImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPackageImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPowerTypeImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.internal.NakedStructuredDataType;
import net.sf.nakeduml.metamodel.core.internal.NakedValueTypeImpl;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
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

import org.eclipse.emf.common.util.EList;
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
import org.eclipse.uml2.uml.InterfaceRealization;
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
public class NameSpaceExtractor extends AbstractExtractorFromEmf{
	/**
	 * For imported profiles. Put them at the top level of the workspace
	 */
	@VisitBefore
	public void visitProfile(Profile p){
		//Different versions of the same profile may occur
		NakedProfileImpl np = new NakedProfileImpl();
		np.initialize(getId(p), p.getName(), true);
		if(p.eResource().getURI().isFile()){
			np.setModelFile(new File(p.eResource().getURI().toFileString()));
		}
		this.workspace.putModelElement(np);
	}
	@VisitBefore
	public void visitStereotype(Stereotype c){
		if(getNakedPeer(c) == null){
			INakedStereotype ns = new NakedStereotypeImpl();
			initialize(ns, c, c.getNamespace());
		}
	}
	@VisitBefore
	public void visitModel(Model p){
		NakedModelImpl nm = new NakedModelImpl();
		nm.initialize(getId(p), p.getName(), true);
		if(p.eResource().getURI().isFile()){
			nm.setModelFile(new File(p.eResource().getURI().toFileString()));
		}
		this.workspace.putModelElement(nm);
	}
	@VisitBefore
	public void visitPackage(Package p,NakedPackageImpl np){
	}
	@VisitBefore
	public void visitComponent(Component c,NakedComponentImpl nc){
		initializeClassifier(nc, c);
	}
	@VisitBefore
	public void visitActor(Actor a,NakedActorImpl na){
		initializeClassifier(na, a);
	}
	@VisitBefore
	public void visitUseCase(UseCase uc,NakedUseCaseImpl nuc){
		initializeClassifier(nuc, uc);
	}
	@VisitBefore
	public void visitCollaboration(Collaboration c,NakedCollaborationImpl nc){
		initializeClassifier(nc, c);
	}
	@VisitBefore
	public void visitPrimitiveType(PrimitiveType p){
		INakedPrimitiveType npt = new NakedPrimitiveType();
		initialize(npt, p, p.getNamespace());
		initializeClassifier(npt, p);
	}
	@VisitBefore
	public void visitClass(Class c){
		if(StereotypesHelper.hasStereotype(c, "Helper")){
			NakedHelperClassImpl ne = new NakedHelperClassImpl();
			initialize(ne, c, c.getNamespace());
			initializeClassifier(ne, c);
		}else if(isBusinessService(c)){
			NakedUserInRoleImpl ne = new NakedUserInRoleImpl();
			initialize(ne, c, c.getNamespace());
			initializeClassifier(ne, c);
		}else{
			INakedEntity ne = new NakedEntityImpl();
			initialize(ne, c, c.getNamespace());
			initializeClassifier(ne, c);
		}
	}
	private boolean isBusinessService(Classifier c){
		boolean representsUser = StereotypesHelper.hasStereotype(c, new String[]{
				"businessworker","caseworker","worker","user","userrole","businessService"
		});
		if(!representsUser){
			for(Dependency o:c.getClientDependencies()){
				if(o.getSuppliers().get(0) instanceof Actor){
					representsUser = true;
				}
			}
		}
		EList<Classifier> generals = c.getGenerals();
		for(Classifier classifier:generals){
			if(isBusinessService(classifier)){
				return true;
			}
		}
		if(c instanceof Class){
			for(InterfaceRealization ir:((Class) c).getInterfaceRealizations()){
				if(isBusinessService(c)){
					return true;
				}
			}
		}
		return representsUser;
	}
	@VisitBefore
	public void visitInterface(Interface i){
		NakedInterfaceImpl ni;
		if(isBusinessService(i)){
			ni=new NakedBusinessServiceImpl();
		}else{
			ni=new NakedInterfaceImpl();
		}
		initialize(ni, i, i.getNamespace());
		initializeClassifier(ni, i);
	}
	@VisitBefore
	public void visitEnumeration(Enumeration e){
		if(StereotypesHelper.hasStereotype(e, "powertype") || e.getPowertypeExtents().size() > 0){
			INakedPowerType npt = new NakedPowerTypeImpl();
			initialize(npt, e, e.getNamespace());
			initializeClassifier(npt, e);
		}else{
			INakedEnumeration ne = new NakedEnumerationImpl();
			initialize(ne, e, e.getNamespace());
			initializeClassifier(ne, e);
		}
	}
	@VisitBefore
	public void visitActivity(Activity a,NakedActivityImpl na){
		ActivityKind kind = null;
		if(StereotypesHelper.hasStereotype(a, new String[]{
				"process","businessprocess"
		})){
			kind = ActivityKind.PROCESS;
		}else if(StereotypesHelper.hasStereotype(a, new String[]{
			"simpleMethod"
		})){
			kind = ActivityKind.SIMPLE_SYNCHRONOUS_METHOD;
		}else{
			kind = ActivityKind.COMPLEX_SYNCHRONOUS_METHOD;
		}
		na.setActivityKind(kind);
		addConstraints(na, a.getPreconditions(), a.getPostconditions());
		initializeClassifier(na, a);
	}
	@VisitBefore
	public void visitStateMachine(StateMachine sm,NakedStateMachineImpl nsm){
		StateMachineKind kind = StateMachineKind.LONG_LIVED;
		if(StereotypesHelper.hasStereotype(sm, new String[]{
			"screenFlow"
		})){
			kind = StateMachineKind.SCREEN_FLOW;
		}
		nsm.setStateMachineKind(kind);
		addConstraints(nsm, sm.getPreconditions(), sm.getPostconditions());
		initializeClassifier(nsm, sm);
	}
	@VisitBefore
	public void visitOpaqueBehavior(OpaqueBehavior ob,NakedOpaqueBehaviorImpl nob){
		nob.setBodyExpression(buildParsedOclString(ob, OclUsageType.BODY, ob.getLanguages(), ob.getBodies()));
		addConstraints(nob, ob.getPreconditions(), ob.getPostconditions());
		initializeClassifier(nob, ob);
	}
	@VisitBefore
	public void visitSignal(Signal s,NakedSignalImpl ns){
		initializeClassifier(ns, s);
	}
	@VisitBefore
	public void visitDataType(DataType dt){
		if(StereotypesHelper.hasStereotype(dt, StereotypeNames.VALUE_TYPE)){
			INakedValueType nvt = new NakedValueTypeImpl();
			initialize(nvt, dt, dt.getNamespace());
			initializeClassifier(nvt, dt);
		}else{
			INakedStructuredDataType nsdt = new NakedStructuredDataType();
			initialize(nsdt, dt, dt.getNamespace());
			initializeClassifier(nsdt, dt);
		}
	}
	@VisitBefore
	public void visitAssociation(Association a,NakedAssociationImpl na){
		na.setDerived(a.isDerived());
		initializeClassifier(na, a);
		if(a.getName() == null){
			// HACK!!! to avoid nullpointerexceptiosn in
			// NAkedParsedOclStringResolver
			// Something wrong with the phases
			na.setName(a.getMemberEnds().get(0).getName() + "To" + a.getMemberEnds().get(1).getName());
		}
	}
	@VisitBefore
	public void visitAssociationClass(AssociationClass a,NakedAssociationClassImpl na){
		na.setDerived(a.isDerived());
		initializeClassifier(na, a);
	}
	protected void initializeClassifier(INakedClassifier classifier,Classifier emfClassifier){
		if(classifier instanceof INakedBehavior && classifier.getOwnerElement() == null){
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
