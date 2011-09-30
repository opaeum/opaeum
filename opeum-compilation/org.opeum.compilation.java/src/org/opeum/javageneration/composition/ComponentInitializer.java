package org.opeum.javageneration.composition;

import java.util.List;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.CompositionEmulator;
import org.opeum.metamodel.core.ICompositionParticipant;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedEntity;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedEnumerationLiteral;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedStructuredDataType;
import nl.klasse.octopus.model.IModelElement;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJForStatement;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;

/**
 * This class implements the 'createComponents()' method. This method takes the semantics of compositional relationships one step further:
 * if the entity has compositional children that are required, it creates them and adds their initialization to the init method
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {
		CompositionEmulator.class,CompositionNodeImplementor.class
},after = {
	CompositionNodeImplementor.class
})
public class ComponentInitializer extends AbstractJavaProducingVisitor{
	@VisitAfter(matchSubclasses = true)
	public void visitClassifier(INakedEntity entity){
		if(OJUtil.hasOJClass(entity)){
			OJAnnotatedClass ojClass = findJavaClass(entity);
			OJOperation init = OJUtil.findOperation(ojClass, "init");
			List<? extends INakedProperty> aws = entity.getOwnedAttributes();
			init.getBody().addToStatements("createComponents()");
			OJOperation createComponents = new OJAnnotatedOperation("createComponents");
			init.getOwner().addToOperations(createComponents);
			createComponents.setBody(new OJBlock());
			if(entity.hasSupertype()){
				createComponents.getBody().addToStatements("super.createComponents()");
			}
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
									+ np.getMappingInfo().getJavaName().getCapped() + "(new " + type.getMappingInfo().getJavaName() + "())");
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
}
