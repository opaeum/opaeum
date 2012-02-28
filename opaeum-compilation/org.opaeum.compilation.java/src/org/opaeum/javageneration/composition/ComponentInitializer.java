package org.opaeum.javageneration.composition;

import java.util.List;

import nl.klasse.octopus.model.IModelElement;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.CompositionEmulator;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedStructuredDataType;

/**
 * This class implements the 'createComponents()' method. This method takes the semantics of compositional relationships one step further:
 * if the entity has compositional children that are required, it creates them and adds their initialization to the init method
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {CompositionEmulator.class,CompositionNodeImplementor.class},after = {CompositionNodeImplementor.class})
public class ComponentInitializer extends AbstractStructureVisitor{
	@Override
	protected void visitComplexStructure(INakedComplexStructure entity){
		if(OJUtil.hasOJClass(entity)){
			if(entity instanceof INakedEntity){
				OJAnnotatedClass ojClass = findJavaClass(entity);
				OJOperation init = ojClass.getUniqueOperation("init");
				List<? extends INakedProperty> aws = entity.getOwnedAttributes();
				init.getBody().addToStatements("createComponents()");
				OJOperation createComponents = new OJAnnotatedOperation("createComponents");
				init.getOwner().addToOperations(createComponents);
				createComponents.setBody(new OJBlock());
				if(entity.hasSupertype()){
					createComponents.getBody().addToStatements("super.createComponents()");
				}
				for(INakedProperty np:aws){
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(np);
					if(!np.isDerived()
							&& (np.getNakedBaseType() instanceof INakedEntity || np.getNakedBaseType() instanceof INakedStructuredDataType)){
						INakedClassifier type = np.getNakedBaseType();
						if(isMap(np) && np.getNakedMultiplicity().getLower() == 1 && np.getQualifiers().size() == 1
								&& (np.getQualifiers().get(0)).getNakedBaseType() instanceof INakedEnumeration){
							INakedProperty qualifier = np.getQualifiers().get(0);
							INakedEnumeration en = (INakedEnumeration) qualifier.getNakedBaseType();
							OJIfStatement ifEmpty = new OJIfStatement();
							ifEmpty.setCondition("get" + np.getMappingInfo().getJavaName().getCapped() + "().isEmpty()");
							ifEmpty.setThenPart(new OJBlock());
							ifEmpty.getThenPart().addToStatements(type.getMappingInfo().getJavaName() + " new" + np.getMappingInfo().getJavaName());
							List<INakedEnumerationLiteral> ownedLiterals = en.getOwnedLiterals();
							for(INakedEnumerationLiteral l:ownedLiterals){
								ifEmpty.getThenPart().addToStatements(
										"new" + np.getMappingInfo().getJavaName() + "= new " + type.getMappingInfo().getJavaName() + "()");
								ifEmpty.getThenPart().addToStatements(
										map.adder() + "(" + en.getMappingInfo().getQualifiedJavaName() + "." + l.getMappingInfo().getJavaName().getUpperCase()
												+ ",new" + np.getMappingInfo().getJavaName() + ")");
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
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
		// TODO Auto-generated method stub
	}
}
