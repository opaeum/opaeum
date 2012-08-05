package org.opaeum.javageneration.composition;

import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.util.OJUtil;

/**
 * This class implements the 'createComponents()' method. This method takes the semantics of compositional relationships one step further:
 * if the entity has compositional children that are required, it creates them and adds their initialization to the init method
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {CompositionNodeImplementor.class},after = {CompositionNodeImplementor.class})
public class ComponentInitializer extends AbstractStructureVisitor{
	@Override
	protected void visitComplexStructure(Classifier entity){
		if(OJUtil.hasOJClass(entity)){
			if(entity instanceof Class){
				OJAnnotatedClass ojClass = findJavaClass(entity);
				OJOperation init = ojClass.getUniqueOperation("init");
				List<? extends Property> aws = entity.getAttributes();
				init.getBody().addToStatements("createComponents()");
				OJOperation createComponents = new OJAnnotatedOperation("createComponents");
				init.getOwner().addToOperations(createComponents);
				createComponents.setBody(new OJBlock());
				if(entity.getGenerals().size()>=1){
					createComponents.getBody().addToStatements("super.createComponents()");
				}
				for(Property np:aws){
					StructuralFeatureMap map = ojUtil.buildStructuralFeatureMap(np);
					if(!np.isDerived() && (np.getType() instanceof Class || EmfClassifierUtil.isStructuredDataType(np.getType()))){
						Classifier type = (Classifier) np.getType();
						if(isMap(np) && np.getLower() == 1 && np.getQualifiers().size() == 1
								&& (np.getQualifiers().get(0)).getType() instanceof Enumeration){
							Property qualifier = np.getQualifiers().get(0);
							Enumeration en = (Enumeration) qualifier.getType();
							OJIfStatement ifEmpty = new OJIfStatement();
							ifEmpty.setCondition(map.getter() + "().isEmpty()");
							ifEmpty.setThenPart(new OJBlock());
							ifEmpty.getThenPart().addToStatements(type.getName() + " new" + np.getName());
							List<EnumerationLiteral> ownedLiterals = en.getOwnedLiterals();
							for(EnumerationLiteral l:ownedLiterals){
								ifEmpty.getThenPart().addToStatements("new" + np.getName() + "= new " + type.getName() + "()");
								ifEmpty.getThenPart().addToStatements(
										map.adder() + "(" +  ojUtil.classifierPathname(en) + "." + l.getName().toUpperCase()+ ",new"
												+ np.getName() + ")");
							}
							createComponents.getBody().addToStatements(ifEmpty);
							if(EmfClassifierUtil.isCompositionParticipant((Classifier) np.getType())){
								OJForStatement whileIter = new OJForStatement("c", map.javaBaseTypePath(), map.getter() + "()");
								whileIter.setBody(new OJBlock());
								whileIter.getBody().addToStatements("c.init(this)");
								init.getBody().addToStatements(whileIter);
							}
						}else if(map.isOne() && (np.isComposite() && np.getLower() == 1)){
							OJIfStatement ifNull = new OJIfStatement(map.getter() + "()==null", map.setter()
									+ "(new " + type.getName() + "())");
							createComponents.getBody().addToStatements(ifNull);
							if(EmfClassifierUtil.isCompositionParticipant((Classifier) np.getType())){
								init.getBody().addToStatements(map.getter() + "().init(this)");
							}
						}
					}
				}
			}
		}
	}
	@Override
	protected void visitProperty(Classifier owner,StructuralFeatureMap buildStructuralFeatureMap){
		// TODO Auto-generated method stub
	}
}
