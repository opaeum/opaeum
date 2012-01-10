package org.opaeum.emf.extraction;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
import org.eclipse.uml2.uml.Extension;
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
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.activities.ActivityKind;
import org.opaeum.metamodel.activities.internal.NakedActivityImpl;
import org.opaeum.metamodel.bpm.internal.NakedBusinessServiceImpl;
import org.opaeum.metamodel.bpm.internal.NakedUserInRoleImpl;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.internal.NakedOpaqueBehaviorImpl;
import org.opaeum.metamodel.commonbehaviors.internal.NakedSignalImpl;
import org.opaeum.metamodel.components.internal.NakedComponentImpl;
import org.opaeum.metamodel.compositestructures.internal.NakedCollaborationImpl;
import org.opaeum.metamodel.core.CodeGenerationStrategy;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedElement;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.NakedAssociationImpl;
import org.opaeum.metamodel.core.internal.NakedElementImpl;
import org.opaeum.metamodel.core.internal.NakedEntityImpl;
import org.opaeum.metamodel.core.internal.NakedEnumerationImpl;
import org.opaeum.metamodel.core.internal.NakedHelperImpl;
import org.opaeum.metamodel.core.internal.NakedInterfaceImpl;
import org.opaeum.metamodel.core.internal.NakedPackageImpl;
import org.opaeum.metamodel.core.internal.NakedPowerTypeImpl;
import org.opaeum.metamodel.core.internal.NakedPrimitiveTypeImpl;
import org.opaeum.metamodel.core.internal.NakedRootObjectImpl;
import org.opaeum.metamodel.core.internal.NakedStructuredDataType;
import org.opaeum.metamodel.core.internal.NakedValueTypeImpl;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.models.internal.NakedModelImpl;
import org.opaeum.metamodel.profiles.internal.NakedProfileImpl;
import org.opaeum.metamodel.profiles.internal.NakedStereotypeImpl;
import org.opaeum.metamodel.statemachines.StateMachineKind;
import org.opaeum.metamodel.statemachines.internal.NakedStateMachineImpl;
import org.opaeum.metamodel.usecases.internal.NakedActorImpl;
import org.opaeum.metamodel.usecases.internal.NakedUseCaseImpl;
import org.opaeum.metamodel.workspace.MappedType;

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
		populateRootObject(p, np);
	}
	private void populateRootObject(Package p,NakedRootObjectImpl nakedPeer){
		if(emfWorkspace.getPotentialGeneratingModels().contains(p)){
			nakedWorkspace.addPrimaryModel(nakedPeer);
		}
		nakedWorkspace.addOwnedElement(nakedPeer);// NB!!!!
		nakedPeer.setFileName(p.eResource().getURI().lastSegment());
		URI mappedTypesUri = p.eResource().getURI().trimFileExtension().appendFileExtension(MAPPINGS_EXTENSION);
		try{
			InputStream inStream = p.eResource().getResourceSet().getURIConverter().createInputStream(mappedTypesUri);
			Properties props = new Properties();
			props.load(inStream);
			Set<Entry<Object,Object>> entrySet = props.entrySet();
			for(Entry<Object,Object> entry:entrySet){
				super.nakedWorkspace.getOpaeumLibrary().getTypeMap().put((String) entry.getKey(), new MappedType((String) entry.getValue()));
			}
			System.out.println("Loaded mappings: " + mappedTypesUri);
		}catch(IOException e1){
		}
	}
	@VisitBefore
	public void visitStereotype(Stereotype c,NakedStereotypeImpl ns){
		initializeClassifier(ns, c);
	}
	@VisitBefore
	public void visitModel(Model p,NakedModelImpl nm){
		nm.setIdentifier(p.eResource().getURI().trimFileExtension().lastSegment());
		nm.setLibrary(emfWorkspace.isLibrary(p) || StereotypesHelper.hasStereotype(p, StereotypeNames.MODEL_LIBRARY));
		this.populateRootObject(p, nm);
		extractImplementationCodeFor(p, nm);
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
	public void visitPrimitiveType(PrimitiveType p,NakedPrimitiveTypeImpl npt){

		initializeClassifier(npt, p);
	}
	public NakedElementImpl createElementFor(Element e,java.lang.Class<?> peerClass){
		if(e instanceof Association){
			if(e instanceof Extension){
				return null;
			}
			if(((Association) e).getMemberEnds().size() < 2){
				getErrorMap().putError(getId(e), EmfValidationRule.BROKEN_ASSOCIATION, e);
				return null;
			}
			for(Property property:((Association) e).getMemberEnds()){
				if(property.getType() == null || property.getOtherEnd() == null){
					getErrorMap().putError(getId(e), EmfValidationRule.BROKEN_ASSOCIATION, e);
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
				if(i.getName().equals("IUserWorkspaceHelper")){
					System.out.println();
				}

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
	public void visitClass(Class c,INakedClassifier ne){
		initializeClassifier(ne, c);
		final Stereotype entity = StereotypesHelper.getStereotype(c, StereotypeNames.ENTITY);
		if(entity != null){
			if(Boolean.TRUE.equals(c.getValue(entity, "generateAbstractSupertype"))){
				ne.setCodeGenerationStrategy(CodeGenerationStrategy.ABSTRACT_SUPERTYPE_ONLY);
			}
		}
	}
	private void extractImplementationCodeFor(Model m,NakedModelImpl model){
		try{
			URI uri = m.eResource().getURI().trimFileExtension().appendFileExtension("zip");
			uri = m.eResource().getResourceSet().getURIConverter().normalize(uri);
			File file = emfWorkspace.getUriToFileConverter().resolveUri(uri);
			if(file != null){
				ZipFile zipFile = new ZipFile(file);
				java.util.Enumeration<? extends ZipEntry> entries = zipFile.entries();
				while(entries.hasMoreElements()){
					ZipEntry zipEntry = entries.nextElement();
					if(!zipEntry.isDirectory()){
						BufferedReader reader = new BufferedReader(new InputStreamReader(zipFile.getInputStream(zipEntry)));
						StringBuilder sb = new StringBuilder();
						String line;
						while((line = reader.readLine()) != null){
							sb.append(line);
							sb.append("\n");
						}
						model.putImplementationCode(zipEntry.getName(), sb.toString());
					}
				}
			}
		}catch(IOException e){
			System.out.println(e.toString());
		}
	}
	private boolean isBusinessService(Classifier c){
		boolean representsUser = StereotypesHelper.hasStereotype(c, new String[]{
				"businessworker","caseworker","worker","user","userrole","businessService","businessrole"
		});
		if(!representsUser){
			for(Dependency o:c.getClientDependencies()){
				if(o.getSuppliers().size() == 1 && o.getSuppliers().get(0) instanceof Actor){
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
	public void visitDataType(DataType dt,INakedClassifier nsdt){
		initializeClassifier(nsdt, dt);
	}
	@VisitBefore(matchSubclasses = true)
	public void visitAssociation(Association a,NakedAssociationImpl na){
		na.setDerived(a.isDerived());
		initializeClassifier(na, a);
		EList<Property> memberEnds = a.getMemberEnds();
		if(a.getName() == null){
			// HACK!!! to avoid nullpointerexceptiosn in
			// NAkedParsedOclStringResolver
			// Something wrong with the phases
			na.setName(memberEnds.get(0).getName() + "To" + memberEnds.get(1).getName());
		}
		na.setDerived(a.isDerived());
		initializeClassifier(na, a);
		if(a instanceof AssociationClass){
			na.setClass(true);
		}else{
			for(Property property:memberEnds){
				boolean isDerived = property.isDerived() || property.isDerivedUnion() || property.getOtherEnd().isDerived() || property.getAssociation().isDerived();
				if(!isDerived && property.getType() instanceof Interface && EmfPropertyUtil.isMany(property)){
					na.setClass(true);
					break;
				}
			}
		}
		if(na.isMarkedForDeletion()){
			for(INakedProperty e:na.getEnds()){
				e.markForDeletion();
				nakedWorkspace.removeModelElement(e);
				if(e.getOwnerElement() != null){
					e.getOwnerElement().removeOwnedElement(e, true);
				}
			}
		}
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
