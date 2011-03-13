package net.sf.nakeduml.javageneration.composition;

import java.util.Iterator;
import java.util.List;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import nl.klasse.octopus.model.IModelElement;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJWhileStatement;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

/**
 * This class implements the 'createComponents()' method. This method takes the
 * semantics of compositional relationships one step further: if the entity has
 * compositional children that are required, it creates them and adds their
 * initialization to the init method
 */
public class ComponentInitializer extends AbstractJavaProducingVisitor {
	@VisitAfter(matchSubclasses = true)
	public void visitClassifier(INakedEntity entity) {
		if (OJUtil.hasOJClass(entity)) {
			OJAnnotatedClass ojClass = findJavaClass(entity);
			OJOperation init = OJUtil.findOperation(ojClass, "init");
			List<? extends INakedProperty> aws = entity.getOwnedAttributes();
			init.getBody().addToStatements("createComponents()");
			OJOperation createComponents = new OJAnnotatedOperation();
			createComponents.setName("createComponents");
			init.getOwner().addToOperations(createComponents);
			createComponents.setBody(new OJBlock());
			if (entity.hasSupertype()) {
				createComponents.getBody().addToStatements("super.createComponents()");
			}
			for (int i = 0; i < aws.size(); i++) {
				IModelElement a = (IModelElement) aws.get(i);
				if (a instanceof INakedProperty) {
					INakedProperty np = (INakedProperty) a;
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(np);
					if (!np.isDerived()
							&& (np.getNakedBaseType() instanceof INakedEntity || np.getNakedBaseType() instanceof INakedStructuredDataType)) {
						INakedClassifier type = np.getNakedBaseType();
						if (np.hasQualifiers() && np.getNakedMultiplicity().getLower() == 1 && np.getQualifiers().size() == 1
								&& (np.getQualifiers().get(0)).getNakedBaseType() instanceof INakedEnumeration) {
							INakedProperty qualifier = np.getQualifiers().get(0);
							INakedEnumeration en = (INakedEnumeration) qualifier.getNakedBaseType();
							Iterator iter = en.getLiterals().iterator();
							OJIfStatement ifEmpty = new OJIfStatement();
							ifEmpty.setCondition("get" + np.getMappingInfo().getJavaName().getCapped() + "().isEmpty()");
							ifEmpty.setThenPart(new OJBlock());
							ifEmpty.getThenPart().addToStatements(
									type.getMappingInfo().getJavaName() + " new" + np.getMappingInfo().getJavaName());
							while (iter.hasNext()) {
								INakedEnumerationLiteral l = (INakedEnumerationLiteral) iter.next();
								ifEmpty.getThenPart().addToStatements(
										"new" + np.getMappingInfo().getJavaName() + "= new " + type.getMappingInfo().getJavaName() + "()");
								ifEmpty.getThenPart().addToStatements(
										"addTo" + np.getMappingInfo().getJavaName().getCapped() + "(" + "new"
												+ np.getMappingInfo().getJavaName() + ")");
								ifEmpty.getThenPart().addToStatements(
										"new" + np.getMappingInfo().getJavaName() + ".set"
												+ qualifier.getMappingInfo().getJavaName().getCapped() + "("
												+ en.getMappingInfo().getQualifiedJavaName() + "."
												+ l.getMappingInfo().getJavaName().getUpperCase() + ")");
							}
							createComponents.getBody().addToStatements(ifEmpty);
							if (np.getNakedBaseType() instanceof INakedEntity) {
								init.getBody().addToStatements(
										"java.util.Iterator iter" + np.getMappingInfo().getJavaName() + "=get"
												+ np.getMappingInfo().getJavaName().getCapped() + "().iterator()");
								OJWhileStatement whileIter = new OJWhileStatement();
								whileIter.setCondition("iter" + np.getMappingInfo().getJavaName() + ".hasNext()");
								whileIter.setBody(new OJBlock());
								whileIter.getBody().addToStatements(
										"((AbstractEntity)iter" + np.getMappingInfo().getJavaName() + ".next()).init(this)");
								init.getBody().addToStatements(whileIter);
							}
						} else if (map.isOne() && (np.isComposite() && np.getNakedMultiplicity().getLower() == 1)) {
							OJIfStatement ifNull = new OJIfStatement("get" + np.getMappingInfo().getJavaName().getCapped() + "()==null",
									"set" + np.getMappingInfo().getJavaName().getCapped() + "(new " + type.getMappingInfo().getJavaName()
											+ "())");
							createComponents.getBody().addToStatements(ifNull);
							if (np.getOtherEnd() != null && np.getOtherEnd().isNavigable()) {
								init.getBody().addToStatements("get" + np.getMappingInfo().getJavaName().getCapped() + "().init(this)");
							}
						}
					}
				}
			}
		}
	}
}
