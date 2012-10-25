package org.opaeum.javageneration.composition;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.runtime.domain.CompositionNode;

/**
 * This class implements the 'createComponents()' method. This method takes the semantics of compositional relationships one step further:
 * if the entity has compositional children that are required, it creates them and adds their initialization to the init method
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {CompositionNodeImplementor.class},after = {CompositionNodeImplementor.class})
public class ComponentInitializer extends AbstractStructureVisitor{
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass ojClass, Classifier entity){
			if(EmfClassifierUtil.isCompositionParticipant(entity) && !(entity instanceof Interface)){
				OJOperation init = ojClass.findOperation("init", Arrays.asList(new OJPathName(CompositionNode.class.getName())));
				Set<? extends Property> aws = getLibrary().getDirectlyImplementedAttributes(entity);

				init.getBody().addToStatements("createComponents()");
				OJOperation createComponents = new OJAnnotatedOperation("createComponents");
				init.getOwner().addToOperations(createComponents);
				createComponents.setBody(new OJBlock());
				if(entity.getGenerals().size() >= 1){
					createComponents.getBody().addToStatements("super.createComponents()");
				}
				for(Property np:aws){
					PropertyMap map = ojUtil.buildStructuralFeatureMap(np);
					if(!EmfPropertyUtil.isDerived(np)
							&& !map.getBaseType().isAbstract() && !(map.getBaseType() instanceof Interface)
							&& (EmfClassifierUtil.isCompositionParticipant(map.getBaseType()) || EmfClassifierUtil
									.isStructuredDataType(map.getBaseType()))){
						Classifier type = (Classifier) map.getBaseType();
						if(isMap(np) && np.getLower() == 1 && np.getQualifiers().size() == 1){
							Property q = np.getQualifiers().get(0);
							Property bp = EmfPropertyUtil.getBackingPropertyForQualifier(q);
							Classifier qtype = (Classifier) (q.getType() != null ? q.getType() : bp == null ? null : bp.getType());
							if(qtype instanceof Enumeration){
								Enumeration en = (Enumeration) qtype;
								OJIfStatement ifEmpty = new OJIfStatement();
								ifEmpty.setCondition(map.getter() + "().isEmpty()");
								ifEmpty.setThenPart(new OJBlock());
								ifEmpty.getThenPart().addToStatements(type.getName() + " new" + map.fieldname());
								List<EnumerationLiteral> ownedLiterals = en.getOwnedLiterals();
								for(EnumerationLiteral l:ownedLiterals){
									ifEmpty.getThenPart().addToStatements("new" + np.getName() + "= new " + map.javaBaseType() + "()");
									ifEmpty.getThenPart().addToStatements(
											map.adder() + "(" + ojUtil.classifierPathname(en) + "." + OJUtil.toJavaLiteral(l) + ",new" + map.fieldname() + ")");
								}
								createComponents.getBody().addToStatements(ifEmpty);
								if(EmfClassifierUtil.isCompositionParticipant((Classifier) map.getBaseType())){
									OJForStatement whileIter = new OJForStatement("c", map.javaBaseTypePath(), map.getter() + "()");
									whileIter.setBody(new OJBlock());
									whileIter.getBody().addToStatements("c.init(this)");
									init.getBody().addToStatements(whileIter);
								}
							}
						}else if(map.isOne() && (np.isComposite() && np.getLower() == 1)){
							OJIfStatement ifNull = new OJIfStatement(map.getter() + "()==null", map.setter() + "(new " + map.javaBaseType() + "())");
							createComponents.getBody().addToStatements(ifNull);
							if(EmfClassifierUtil.isCompositionParticipant((Classifier) map.getBaseType())){
								init.getBody().addToStatements(map.getter() + "().init(this)");
							}
						}
					}
				}
			}
		return false;
	}
	@Override
	protected void visitProperty(OJAnnotatedClass c, Classifier owner,PropertyMap buildStructuralFeatureMap){
	}
}
