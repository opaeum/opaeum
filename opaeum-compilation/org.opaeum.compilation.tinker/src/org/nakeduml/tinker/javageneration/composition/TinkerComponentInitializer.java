package org.nakeduml.tinker.javageneration.composition;

import java.util.Arrays;
import java.util.List;

import nl.klasse.octopus.model.IModelElement;

import org.nakeduml.tinker.generator.TinkerGenerationUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.composition.ComponentInitializer;
import org.opaeum.javageneration.composition.CompositionNodeImplementor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.CompositionEmulator;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedStructuredDataType;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	CompositionEmulator.class,CompositionNodeImplementor.class
},after = {
CompositionNodeImplementor.class
}, replaces=ComponentInitializer.class)
public class TinkerComponentInitializer extends ComponentInitializer {

	@VisitAfter(matchSubclasses = true)
	public void visitClassifier(INakedEntity entity){
		if(OJUtil.hasOJClass(entity)){
			OJAnnotatedClass ojClass = findJavaClass(entity);
			OJOperation init = ojClass.findOperation("init", Arrays.asList(new OJPathName[]{new OJPathName("org.opaeum.runtime.domain.CompositionNode")}));
			List<? extends INakedProperty> aws = entity.getOwnedAttributes();
//			init.getBody().addToStatements("createComponents()");
			OJOperation createComponents = new OJAnnotatedOperation("createComponents");
			init.getOwner().addToOperations(createComponents);
			createComponents.setBody(new OJBlock());
			if(entity.hasSupertype()){
				createComponents.getBody().addToStatements("super.createComponents()");
			}
			initChildren(init, aws, createComponents);
		}
	}
	
	protected void initChildren(OJOperation init, List<? extends INakedProperty> aws, OJOperation createComponents) {
		for(int i = 0;i < aws.size();i++){
			IModelElement a = (IModelElement) aws.get(i);
			if(a instanceof INakedProperty){
				INakedProperty np = (INakedProperty) a;
				NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(np);
				if(!np.isDerived() && (np.getNakedBaseType() instanceof INakedEntity || np.getNakedBaseType() instanceof INakedStructuredDataType)){
					INakedClassifier type = np.getNakedBaseType();
					if(np.hasQualifiers() && np.getNakedMultiplicity().getLower() == 1 && np.getQualifiers().size() == 1
							&& (np.getQualifiers().get(0)).getNakedBaseType() instanceof INakedEnumeration){
						INakedProperty qualifier = np.getQualifiers().get(0);
						INakedEnumeration en = (INakedEnumeration) qualifier.getNakedBaseType();
						OJIfStatement ifEmpty = new OJIfStatement();
						ifEmpty.setCondition("get" + np.getMappingInfo().getJavaName().getCapped() + "().isEmpty()");
						ifEmpty.setThenPart(new OJBlock());
						ifEmpty.getThenPart().addToStatements(type.getMappingInfo().getJavaName() + " new" + np.getMappingInfo().getJavaName());
						List<INakedEnumerationLiteral> ownedLiterals = en.getOwnedLiterals();
						for(INakedEnumerationLiteral l:ownedLiterals){
							ifEmpty.getThenPart().addToStatements("new" + np.getMappingInfo().getJavaName() + "= new " + type.getMappingInfo().getJavaName() + "()");
							ifEmpty.getThenPart().addToStatements(
									"addTo" + np.getMappingInfo().getJavaName().getCapped() + "(" + "new" + np.getMappingInfo().getJavaName() + ")");
							ifEmpty.getThenPart().addToStatements(
									"new" + np.getMappingInfo().getJavaName() + ".set" + qualifier.getMappingInfo().getJavaName().getCapped() + "("
											+ en.getMappingInfo().getQualifiedJavaName() + "." + l.getMappingInfo().getJavaName().getUpperCase() + ")");
						}
						createComponents.getBody().addToStatements(ifEmpty);
						if(np.getNakedBaseType() instanceof ICompositionParticipant){
							OJForStatement whileIter = new OJForStatement("c", map.javaBaseTypePath(), map.getter() + "()");
							whileIter.setBody(new OJBlock());
							whileIter.getBody().addToStatements("c.init(this)");
							init.getBody().addToStatements(whileIter);
						}
					}else if(map.isOne() && (np.isComposite() && np.getNakedMultiplicity().getLower() == 1)){
						OJIfStatement ifNull = new OJIfStatement("get" + np.getMappingInfo().getJavaName().getCapped() + "()==null", "set"
								+ np.getMappingInfo().getJavaName().getCapped() + "(new " + type.getMappingInfo().getJavaName() + "(true))");
						createComponents.getBody().addToStatements(ifNull);
						if(np.getNakedBaseType() instanceof ICompositionParticipant){
							init.getBody().addToStatements("get" + np.getMappingInfo().getJavaName().getCapped() + "().init(this)");
						}
					}
				}
			}
		}
	}


}
