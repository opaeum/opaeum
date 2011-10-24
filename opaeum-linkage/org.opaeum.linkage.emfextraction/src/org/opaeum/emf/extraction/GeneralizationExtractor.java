package org.opaeum.emf.extraction;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Generalization;
import org.eclipse.uml2.uml.GeneralizationSet;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedPowerType;
import org.opaeum.metamodel.core.INakedPowerTypeInstance;
import org.opaeum.metamodel.core.internal.NakedElementImpl;
import org.opaeum.metamodel.core.internal.NakedGeneralizationImpl;
import org.opaeum.metamodel.core.internal.NakedInterfaceRealizationImpl;
import org.opaeum.metamodel.core.internal.NakedPowerTypeInstanceImpl;
import org.opaeum.metamodel.name.SingularNameWrapper;

@StepDependency(phase = EmfExtractionPhase.class,requires = NameSpaceExtractor.class,after = NameSpaceExtractor.class)
public class GeneralizationExtractor extends AbstractExtractorFromEmf{
	@VisitBefore
	public void visitInterfaceRealization(InterfaceRealization r,NakedInterfaceRealizationImpl impl){
		Classifier parent = r.getContract();
		Classifier child = r.getImplementingClassifier();
		INakedInterface nakedParent = (INakedInterface) getNakedPeer(parent);
		INakedBehavioredClassifier nakedChild = (INakedBehavioredClassifier) getNakedPeer(child);
		if(!impl.isMarkedForDeletion()){
			if(nakedParent == null){
				System.out.println("Contract is not in model:" + parent.getName());
				return;
			}
			if(nakedChild == null){
				System.out.println("Implementation is not in model:" + child.getName());
				return;
			}
			impl.setContract(nakedParent);
		}
	}
	@Override
	protected NakedElementImpl createElementFor(Element e,Class<?> peerClass){
		// ignore generalizations/realizations to proxies
		if(e instanceof InterfaceRealization && !((InterfaceRealization) e).getContract().eIsProxy()){
			return super.createElementFor(e, peerClass);
		}else if(e instanceof Generalization && !((Generalization) e).getGeneral().eIsProxy()){
			return super.createElementFor(e, peerClass);
		}else{
			return null;
		}
	}
	@VisitBefore
	public void visitGeneralization(Generalization g,NakedGeneralizationImpl ng){
		Classifier parent = g.getGeneral();
		Classifier child = g.getSpecific();
		INakedClassifier nakedParent = (INakedClassifier) getNakedPeer(parent);
		INakedClassifier nakedChild = (INakedClassifier) getNakedPeer(child);
		if(nakedParent == null){
			System.out.println("General is not in model:" + parent);
			return;
		}
		if(nakedChild == null){
			System.out.println("Specific is not in model:" + child);
			return;
		}
		ng.setGeneral(nakedParent);
		if(!g.getGeneralizationSets().isEmpty()){
			GeneralizationSet generalizationSet = g.getGeneralizationSets().get(0);
			if(generalizationSet.getPowertype() instanceof Enumeration){
				// TODO do this during linkage
				Enumeration powerType = (Enumeration) generalizationSet.getPowertype();
				INakedPowerTypeInstance ptl = new NakedPowerTypeInstanceImpl();
				SingularNameWrapper nameWrapper = new SingularNameWrapper(child.getName(), null);
				ptl.initialize(getId(g) + "EnumerationLiteral", nameWrapper.getDecapped().toString(), false);
				this.nakedWorkspace.putModelElement(ptl);
				ng.setPowerTypeLiteral(ptl);
				INakedPowerType ptw = (INakedPowerType) getNakedPeer(powerType);
				ptl.setOwnerElement(ptw);
				INakedClassifier representedSupertype = (INakedClassifier) getNakedPeer(parent);
				ptw.setRepresentedSupertype(representedSupertype);
			}
		}
	}
}
