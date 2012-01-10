package org.opaeum.javageneration.basicjava;

import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.activities.INakedActivityVariable;
import org.opaeum.metamodel.activities.INakedObjectNode;
import org.opaeum.metamodel.activities.INakedPin;
import org.opaeum.metamodel.activities.internal.StructuredActivityNodeClassifier;
import org.opaeum.metamodel.commonbehaviors.INakedBehavior;
import org.opaeum.metamodel.commonbehaviors.INakedSignal;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedElementOwner;
import org.opaeum.metamodel.core.INakedOperation;
import org.opaeum.metamodel.core.INakedPackage;
import org.opaeum.metamodel.core.INakedParameter;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.IParameterOwner;
import org.opaeum.metamodel.core.internal.emulated.EmulatingElement;
import org.opaeum.metamodel.workspace.INakedModelWorkspace;
import org.opaeum.strategies.BlobStrategyFactory;
import org.opaeum.strategies.DateTimeStrategyFactory;
import org.opaeum.strategies.TextStrategyFactory;

@StepDependency(phase = JavaTransformationPhase.class,requires = {},after = {})
public class UmlToJavaMapInitialiser extends AbstractStructureVisitor{
	static{
		// Because of eclipse classloading issues
		OpaeumConfig.registerClass(DateTimeStrategyFactory.class);
		OpaeumConfig.registerClass(TextStrategyFactory.class);
		OpaeumConfig.registerClass(BlobStrategyFactory.class);
		StdlibMap.javaRealType.replaceTail("double");
		StdlibMap.javaRealObjectType.replaceTail("Double");
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;// adds too many entries to shared non-synchronized collections;
	}
	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root){
		return root.getOwnedElements();
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
		if(umlOwner instanceof EmulatingElement){
			visitClass(umlOwner);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitObjectNode(INakedObjectNode on){
		OJUtil.buildStructuralFeatureMap(on.getActivity(), on, false);
		OJUtil.buildStructuralFeatureMap(on.getActivity(), on, true);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitVariable(INakedActivityVariable v){
		OJUtil.buildStructuralFeatureMap(v.getActivity(), v);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitParameterOwner(IParameterOwner p){
		OJUtil.buildOperationMap(p);
		List<INakedParameter> ownedParameters = p.getOwnedParameters();
		for(INakedParameter parm:ownedParameters){
			OJUtil.buildStructuralFeatureMap(p instanceof INakedOperation ? ((INakedOperation) p).getOwner() : (INakedBehavior) p, parm);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void workspaceAfter(INakedModelWorkspace a){
		OJUtil.lock();
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClass(final INakedClassifier c){
		OJUtil.buildClassifierMap(c);
		if(c instanceof INakedSignal){
			OJUtil.buildSignalMap((INakedSignal) c);
		}
		OJUtil.classifierPathname(c);
		OJUtil.packagePathname(c);
		if(c instanceof INakedBehavior || c instanceof StructuredActivityNodeClassifier){
			OJUtil.statePathname(c);
		}
		List<? extends INakedProperty> ownedAttributes = c.getOwnedAttributes();
		for(INakedProperty iNakedProperty:ownedAttributes){
			OJUtil.buildStructuralFeatureMap(iNakedProperty);// for interfaces and stereotypes
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitPackage(INakedPackage p){
		OJUtil.packagePathname(p);
	}
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap map){
		if(map.getProperty().getOtherEnd()!=null){
			OJUtil.buildStructuralFeatureMap(map.getProperty().getOtherEnd());
		}
		// NB!! This will initialise NakedStructuralFeatureMaps
	}
}
