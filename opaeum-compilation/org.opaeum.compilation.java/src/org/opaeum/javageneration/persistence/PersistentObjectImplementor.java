package org.opaeum.javageneration.persistence;

import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedGeneralization;
import org.opaeum.metamodel.core.INakedHelper;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedPowerType;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.models.INakedModel;
import org.opaeum.validation.namegeneration.PersistentNameGenerator;
import org.opeum.runtime.domain.IPersistentObject;

/**
 * This class builds all the operations specified by the AbstractEntity interface. It also provides an implementation for the equals method
 * that uses the id of the instance involved
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {
		AttributeImplementor.class,PersistentNameGenerator.class
},after = {
	AttributeImplementor.class
})
public class PersistentObjectImplementor extends AbstractStructureVisitor{
	private static final OJPathName ABSTRACT_ENTITY = new OJPathName(IPersistentObject.class.getName());
	@VisitBefore
	public void visitModel(INakedModel p){
	}
	@VisitBefore(matchSubclasses = true)
	public void visitInterface(INakedInterface a){
		if(OJUtil.hasOJClass(a) && !(a instanceof INakedHelper || a.hasStereotype(StereotypeNames.HELPER))){
			OJAnnotatedInterface asdf = (OJAnnotatedInterface) findJavaClass(a);
			asdf.addToSuperInterfaces(new OJPathName(IPersistentObject.class.getName()));
		}
	}
	private void visitClass(INakedClassifier c){
		OJClassifier ojClassifier = super.findJavaClass(c);
		if(ojClassifier instanceof OJAnnotatedInterface){
			if(c.getStereotype(StereotypeNames.HELPER) == null){
				((OJAnnotatedInterface) ojClassifier).addToSuperInterfaces(ABSTRACT_ENTITY);
				ojClassifier.addToImports(ABSTRACT_ENTITY);
			}
		}else if(ojClassifier instanceof OJClass){
			OJClass ojClass = (OJClass) ojClassifier;
			if(isPersistent(c)){
				INakedComplexStructure entity = (INakedComplexStructure) c;
				ojClass.addToImports(ABSTRACT_ENTITY);
				if(entity.findAttribute("name") == null){
					addGetName(entity, ojClass);
				}
				ojClass.addToImplementedInterfaces(ABSTRACT_ENTITY);
				if(entity instanceof INakedEntity){
					addDiscriminatorInitialization((INakedEntity) entity, ojClass);
				}
			}
		}
	}
	private void addDiscriminatorInitialization(INakedEntity entity,OJClass ojClass){
		OJBlock dcBody = new OJBlock();
		for(INakedProperty attr:entity.getEffectiveAttributes()){
			if(attr.isDiscriminator()){
				INakedPowerType powerType = (INakedPowerType) attr.getNakedBaseType();
				if(entity.isPowerTypeInstance()){
					INakedGeneralization generalization = entity.getNakedGeneralizations().iterator().next();
					String literal = powerType.getMappingInfo().getQualifiedJavaName() + "."
							+ generalization.getPowerTypeLiteral().getMappingInfo().getJavaName().getUpperCase();
					dcBody.addToStatements("set" + attr.getMappingInfo().getJavaName().getCapped() + "(" + literal + ")");
				}
			}
		}
		ojClass.getDefaultConstructor().setBody(dcBody);
	}
	private void addGetName(INakedComplexStructure entity,OJClass ojClass){
		OJOperation getName = new OJAnnotatedOperation("getName");
		getName.setReturnType(new OJPathName("String"));
		getName.setBody(new OJBlock());
		getName.getBody().addToStatements("return \"" + entity.getMappingInfo().getJavaName() + "[\"+getId()+\"]\"");
		ojClass.addToOperations(getName);
	}
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
	}
	@Override
	protected void visitComplexStructure(INakedComplexStructure umlOwner){
		visitClass(umlOwner);
	}
}
