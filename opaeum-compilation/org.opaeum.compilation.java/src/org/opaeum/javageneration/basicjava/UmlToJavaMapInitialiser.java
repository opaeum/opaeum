package org.opaeum.javageneration.basicjava;

import java.util.Collection;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;
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
import org.opaeum.eclipse.emulated.IEmulatedElement;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
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
		return super.getChildren(root);
	}
	@Override
	protected void visitComplexStructure(Classifier umlOwner){
		if(umlOwner instanceof IEmulatedElement){
			visitClass(umlOwner);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitObjectNode(ObjectNode on){
		ojUtil.buildStructuralFeatureMap(on);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitVariable(Variable v){
		ojUtil.buildStructuralFeatureMap(v);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitParameterOwner(Behavior p){
		ojUtil.buildOperationMap(p);
		List<Parameter> ownedParameters = p.getOwnedParameters();
		for(Parameter parm:ownedParameters){
			ojUtil.buildStructuralFeatureMap(parm);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitParameterOwner(Operation p){
		ojUtil.buildOperationMap(p);
		List<Parameter> ownedParameters = p.getOwnedParameters();
		for(Parameter parm:ownedParameters){
			ojUtil.buildStructuralFeatureMap(parm);
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void workspaceAfter(EmfWorkspace a){
		ojUtil.lock();
	}
	@VisitAfter(matchSubclasses = true)
	public void visitStructuredActivityNode(final StructuredActivityNode c){
		ojUtil.statePathname(c);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClass(final Classifier c){
		ojUtil.buildClassifierMap(c, (CollectionKind) null);
		if(c instanceof Signal){
			ojUtil.buildSignalMap((Signal) c);
		}
		ojUtil.classifierPathname(c);
		ojUtil.packagePathname(c);
		if(c instanceof Behavior){
			ojUtil.statePathname(c);
		}
		List<? extends Property> ownedAttributes = c.getAttributes();
		for(Property p:ownedAttributes){
			if(p.getType() != null){
				ojUtil.buildStructuralFeatureMap(p);// for interfaces and stereotypes
			}
		}
	}
	@VisitBefore(matchSubclasses = true)
	public void visitPackage(Package p){
		ojUtil.packagePathname(p);
	}
	@Override
	protected void visitProperty(Classifier owner,PropertyMap map){
		Property p = map.getProperty();
		if(p.getOtherEnd() != null){
			ojUtil.buildStructuralFeatureMap(p.getOtherEnd());
		}
		// NB!! This will initialise NakedStructuralFeatureMaps
	}
}
