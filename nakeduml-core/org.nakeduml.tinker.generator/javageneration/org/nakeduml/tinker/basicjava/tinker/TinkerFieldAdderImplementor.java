package org.nakeduml.tinker.basicjava.tinker;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.ICompositionParticipant;
import net.sf.nakeduml.metamodel.core.INakedAssociationClass;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;

import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class TinkerFieldAdderImplementor extends AbstractJavaProducingVisitor {

	@VisitAfter(matchSubclasses = true,match = {INakedEntity.class,INakedStructuredDataType.class,INakedAssociationClass.class})
	public void visitFeature(INakedClassifier entity){
		for(INakedProperty p:entity.getEffectiveAttributes()){
			if(p.getOwner() instanceof INakedInterface && OJUtil.hasOJClass(entity)){
				if(!(p.getAssociation() instanceof INakedAssociationClass)){
					visitProperty(entity, OJUtil.buildStructuralFeatureMap(p));
				}
			}
		}
	}
	
	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedProperty p){
		if(OJUtil.hasOJClass(p.getOwner())){
			if(!(p.getAssociation() instanceof INakedAssociationClass)){
				visitProperty(p.getOwner(), OJUtil.buildStructuralFeatureMap(p));
			}
		}
	}
	
	private void visitProperty(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		INakedProperty p = map.getProperty();
		if(!OJUtil.isBuiltIn(p) && !isCompositeOwner(umlOwner, map)) {
			OJAnnotatedClass owner = findJavaClass(umlOwner);
			buildField(owner, map);
			if(map.isMany()){
			}else if(map.isOne() && isPersistent(p.getNakedBaseType()) || p.getBaseType() instanceof INakedInterface){
				buildInternalAdder(owner, map);
				buildInternalRemover(owner, map);
			}
		}
	}

	private boolean isCompositeOwner(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		if (umlOwner instanceof ICompositionParticipant) {
			ICompositionParticipant entity = (ICompositionParticipant)umlOwner;
			return entity.getEndToComposite()!=null && entity.getEndToComposite().equals(map.getProperty());
		} else {
			return false;
		}
	}

	private void buildInternalAdder(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.internalAdder());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		adder.getBody().addToStatements("this." + map.umlName() + "=" + map.umlName());
		owner.addToOperations(adder);
	}
	private void buildInternalRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.internalRemover());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		String remove;
		remove = "this." + map.umlName() + "=null";
		OJIfStatement ifEquals = new OJIfStatement(map.getter() + "()!=null && " + map.getter() + "().equals(" + map.umlName() + ")", remove);
		adder.getBody().addToStatements(ifEquals);
		owner.addToOperations(adder);
	}
	
	private OJAnnotatedField buildField(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJAnnotatedField field = new OJAnnotatedField();
		field.setType(map.javaTypePath());
		field.setName(map.umlName());
		if(map.isJavaPrimitive()){
			field.setInitExp(map.javaDefaultValue());
		}
		owner.addToFields(field);
		return field;
	}
	
	
}
