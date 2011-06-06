package org.nakeduml.tinker.basicjava.tinker;

import java.util.ArrayList;
import java.util.List;

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
import org.nakeduml.java.metamodel.OJVisibilityKind;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class TinkerFieldAdderImplementor extends AbstractJavaProducingVisitor {

	@VisitAfter(matchSubclasses = true,match = {INakedEntity.class,INakedStructuredDataType.class,INakedAssociationClass.class})
	public void visitFeature(INakedClassifier entity){
		List<INakedProperty> properties = new ArrayList<INakedProperty>();
		if (entity.getGeneralizations().isEmpty()) {
			properties.addAll(entity.getEffectiveAttributes());
		} else {
			properties.addAll(entity.getOwnedAttributes());
		}
		for(INakedProperty p:properties){
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
	
	protected void visitProperty(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		INakedProperty p = map.getProperty();
		if(!OJUtil.isBuiltIn(p) && !isCompositeOwner(umlOwner, map) && !p.isDerived()) {
			OJAnnotatedClass owner = findJavaClass(umlOwner);
			buildField(owner, map);
			if (map.isMany()) {
				buildInternalManyToManyAdder(owner, map);
				buildInternalManyToManyRemover(owner, map);
			} else {
				buildInternalAdder(owner, map);
				buildInternalRemover(owner, map);
			}			
			
		}
	}

	private void buildInternalManyToManyRemover(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation remover = new OJAnnotatedOperation();
		remover.setName(map.internalRemover());
		remover.addParam(map.umlName(), map.javaBaseTypePath());
		remover.getBody().addToStatements("this." + map.umlName() + ".tinkerRemove(" + map.umlName() + ")");
		owner.addToOperations(remover);	
	}

	private void buildInternalManyToManyAdder(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.internalAdder());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		adder.getBody().addToStatements("this." + map.umlName() + ".tinkerAdd(" + map.umlName() + ")");
		owner.addToOperations(adder);	
	}

	protected void buildInternalManyRemover(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation remover = new OJAnnotatedOperation();
		remover.setName(map.internalRemover());
		remover.addParam(map.umlName(), map.javaBaseTypePath());
		remover.getBody().addToStatements("this." + map.umlName() + ".tinkerRemove(" + map.umlName() + ")");
		owner.addToOperations(remover);	
	}

	protected void buildInternalManyAdder(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.internalAdder());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		adder.getBody().addToStatements("this." + map.umlName() + ".tinkerAdd(" + map.umlName() + ")");
		owner.addToOperations(adder);	
	}

	protected boolean isCompositeOwner(INakedClassifier umlOwner, NakedStructuralFeatureMap map) {
		if (umlOwner instanceof ICompositionParticipant) {
			ICompositionParticipant entity = (ICompositionParticipant)umlOwner;
			return entity.getEndToComposite()!=null && entity.getEndToComposite().equals(map.getProperty());
		} else {
			return false;
		}
	}

	protected void buildInternalAdder(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJOperation adder = new OJAnnotatedOperation();
		adder.setName(map.internalAdder());
		adder.addParam(map.umlName(), map.javaBaseTypePath());
		adder.setStatic(map.isStatic());
		adder.getBody().addToStatements("this." + map.umlName() + "=" + map.umlName());
		owner.addToOperations(adder);
	}
	protected void buildInternalRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
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
	
	protected OJAnnotatedField buildField(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		OJAnnotatedField field = new OJAnnotatedField();
		field.setType(map.javaTypePath());
		field.setName(map.umlName());
		field.setVisibility(OJVisibilityKind.PROTECTED);
		if(map.isJavaPrimitive()){
			field.setInitExp(map.javaDefaultValue());
		}
		owner.addToFields(field);
		return field;
	}
	
	
}
