package net.sf.nakeduml.emf.extraction;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

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
import net.sf.nakeduml.metamodel.core.INakedPrimitiveType;
import net.sf.nakeduml.metamodel.core.internal.NakedAssociationClassImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedAssociationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedClassifierImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedElementImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedEntityImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedEnumerationImpl;
import net.sf.nakeduml.metamodel.core.internal.NakedHelperImpl;
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
import net.sf.nakeduml.metamodel.validation.BrokenElement;
import net.sf.nakeduml.metamodel.workspace.MappedType;
import net.sf.nakeduml.validation.CoreValidationRule;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.VisibilityKind;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
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
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.OpaqueBehavior;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StateMachine;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UseCase;

/**
 * Builds all classifier and the namespaces required to hostr them
 */
@StepDependency(phase = EmfExtractionPhase.class)
public class NameSpaceExtractor extends AbstractExtractorFromEmf{
	public static final String MAPPINGS_EXTENSION = "mappings";
	/**
	 * For imported profiles. Put them at the top level of the nakedWorkspace
	 */
	@VisitBefore
	public void visitProfile(Profile p,NakedProfileImpl np){
		p.getName();
		// Different versions of the same profile may occur
		np.setIdentifier(p.eResource().getURI().trimFileExtension().lastSegment());
		populateTypesMappedIn(p);
	}
	private void populateTypesMappedIn(Package p){
		URI mappedTypesUri = p.eResource().getURI().trimFileExtension().appendFileExtension(MAPPINGS_EXTENSION);
		try{
			InputStream inStream = p.eResource().getResourceSet().getURIConverter().createInputStream(mappedTypesUri);
			Properties props = new Properties();
			props.load(inStream);
			Set<Entry<Object,Object>> entrySet = props.entrySet();
			for(Entry<Object,Object> entry:entrySet){
				super.nakedWorkspace.getNakedUmlLibrary().getTypeMap().put((String) entry.getKey(), new MappedType((String) entry.getValue()));
			}
			System.out.println("Loaded mappings: " + mappedTypesUri);
		}catch(IOException e1){
			// System.out.println("Could not load mappedTypes in " + mappedTypesUri);
			// System.out.println(e);
		}
	}
	@VisitBefore
	public void visitStereotype(Stereotype c,NakedStereotypeImpl ns){
		initializeClassifier(ns, c);
	}
	@VisitBefore
	public void visitModel(Model p,NakedModelImpl nm){
		nm.setIdentifier(p.eResource().getURI().trimFileExtension().lastSegment());
		nm.setLibrary(emfWorkspace.isLibrary(p));
		this.populateTypesMappedIn(p);
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
	public void visitPrimitiveType(PrimitiveType p,NakedPrimitiveType npt){
		initializeClassifier(npt, p);
	}
	public NakedElementImpl createElementFor(Element e,java.lang.Class<?> peerClass){
		if(e instanceof Association){
			for(Property property:((Association) e).getMemberEnds()){
				if(property.getType() == null){
					BrokenElement be = new BrokenElement(getId(property));
					be.addMessage(CoreValidationRule.INVERSE);
					getErrorMap().getErrors().put(getId(property),be);
					// broken association a'la topcased
					return null;
				}
			}
			return super.createElementFor(e, peerClass);
		}else if(e instanceof Stereotype || e instanceof AssociationClass || e instanceof Component || e instanceof Behavior || e instanceof Collaboration
				|| e instanceof PrimitiveType){
			return super.createElementFor(e, peerClass);
		}else if(e instanceof Class){
			Class c = (Class) e;
			if(StereotypesHelper.hasStereotype(c, "Helper")){
				return new NakedHelperImpl();
			}else if(isBusinessService(c)){
				return new NakedUserInRoleImpl();
			}else{
				return new NakedEntityImpl();
			}
		}else if(e instanceof Interface){
			Interface i = (Interface) e;
			if(isBusinessService(i)){
				return new NakedBusinessServiceImpl();
			}else if(StereotypesHelper.hasStereotype(i, "Helper")){
				return new NakedHelperImpl();
			}else{
				return new NakedInterfaceImpl();
			}
		}else if(e instanceof Enumeration){
			Enumeration en = (Enumeration) e;
			if(StereotypesHelper.hasStereotype(en, "powertype") || en.getPowertypeExtents().size() > 0){
				return new NakedPowerTypeImpl();
			}else{
				return new NakedEnumerationImpl();
			}
		}else if(e instanceof DataType){
			DataType dt = (DataType) e;
			if(StereotypesHelper.hasStereotype(dt, StereotypeNames.VALUE_TYPE)){
				return new NakedValueTypeImpl();
			}else{
				return new NakedStructuredDataType();
			}
		}else{
			return super.createElementFor(e, peerClass);
		}
	}
	@VisitBefore
	public void visitClass(Class c,NakedClassifierImpl ne){
		initializeClassifier(ne, c);
	}
	private boolean isBusinessService(Classifier c){
		boolean representsUser = StereotypesHelper.hasStereotype(c, new String[]{
				"businessworker","caseworker","worker","user","userrole","businessService","businessrole"
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
				if(isBusinessService(ir.getContract())){
					return true;
				}
			}
		}
		return representsUser;
	}
	@VisitBefore
	public void visitInterface(Interface i,NakedInterfaceImpl ni){
		initializeClassifier(ni, i);
	}
	@VisitBefore
	public void visitEnumeration(Enumeration e,NakedEnumerationImpl npt){
		initializeClassifier(npt, e);
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
		initializeClassifier(nsm, sm);
	}
	@VisitBefore
	public void visitOpaqueBehavior(OpaqueBehavior ob,NakedOpaqueBehaviorImpl nob){
		nob.setBodyExpression(buildParsedOclString(ob, ob.getLanguages(), ob.getBodies(), OclUsageType.DEF));
		initializeClassifier(nob, ob);
	}
	@VisitBefore
	public void visitSignal(Signal s,NakedSignalImpl ns){
		initializeClassifier(ns, s);
	}
	@VisitBefore
	public void visitDataType(DataType dt,NakedClassifierImpl nsdt){
		initializeClassifier(nsdt, dt);
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
