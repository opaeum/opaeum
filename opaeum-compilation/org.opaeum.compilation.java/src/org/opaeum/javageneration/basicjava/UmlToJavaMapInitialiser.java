package org.opaeum.javageneration.basicjava;

import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;

import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.uml2.uml.Behavior;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.ObjectNode;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Signal;
import org.eclipse.uml2.uml.StructuredActivityNode;
import org.eclipse.uml2.uml.Variable;
import org.opaeum.eclipse.EmfActivityUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
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
	public void visitAssociationClassProperty(Classifier c,AssociationClassEndMap map){
	}
	@Override
	protected int getThreadPoolSize(){
		return 1;// adds too many entries to shared non-synchronized collections;
	}
	@Override
	public Collection<Element> getChildren(Element root){
		return root.getOwnedElements();
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
		if(umlOwner instanceof EmulatingElement){
			visitClass(umlOwner);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitObjectNode(ObjectNode on){
		OJUtil.buildStructuralFeatureMap(EmfActivityUtil.getContainingActivity( on), on, false);
		OJUtil.buildStructuralFeatureMap(EmfActivityUtil.getContainingActivity(on), on, true);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitVariable(Variable v){
		OJUtil.buildStructuralFeatureMap(EmfActivityUtil.getContainingActivity(v), v);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitParameterOwner(Behavior p){
		OJUtil.buildOperationMap(p);
		List<Parameter> ownedParameters = p.getOwnedParameters();
		for(Parameter parm:ownedParameters){
			OJUtil.buildStructuralFeatureMap( p, parm);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitParameterOwner(Operation p){
		OJUtil.buildOperationMap(p);
		List<Parameter> ownedParameters = p.getOwnedParameters();
		for(Parameter parm:ownedParameters){
			OJUtil.buildStructuralFeatureMap((Classifier) p.getOwner(),parm);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void workspaceAfter(EmfWorkspace a){
		OJUtil.lock();
	}
	@VisitAfter(matchSubclasses = true)
	public void visitStructuredActivityNode(final StructuredActivityNode c){
		OJUtil.statePathname(c);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClass(final Classifier c){
		OJUtil.buildClassifierMap(c,(CollectionKind)null);
		if(c instanceof Signal){
			OJUtil.buildSignalMap((Signal) c);
		}
		OJUtil.classifierPathname(c);
		OJUtil.packagePathname(c);
		if(c instanceof Behavior ){
			OJUtil.statePathname(c);
		}
		List<? extends Property> ownedAttributes = c.getAttributes();
		for(Property iNakedProperty:ownedAttributes){
			OJUtil.buildStructuralFeatureMap(iNakedProperty);// for interfaces and stereotypes
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitPackage(Package p){
		OJUtil.packagePathname(p);
	}
	@Override
	protected void visitProperty(Classifier owner,NakedStructuralFeatureMap map){
		Property p = map.getProperty();
		if(p.getOtherEnd() != null){
			OJUtil.buildStructuralFeatureMap(p.getOtherEnd());
		}
		// NB!! This will initialise NakedStructuralFeatureMaps
	}
}
