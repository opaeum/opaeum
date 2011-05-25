package org.nakeduml.tinker.basicjava.tinker;

import java.util.Arrays;

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

import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;

public class TinkerFieldRemoverImplementor extends AbstractJavaProducingVisitor {

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
			removeField(owner, map);
			if(map.isOne() && isPersistent(p.getNakedBaseType()) || p.getBaseType() instanceof INakedInterface){
				removeInternalAdder(owner, map);
				removeInternalRemover(owner, map);
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

	private void removeInternalRemover(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation remover = owner.findOperation(map.internalRemover(), Arrays.asList(map.javaBaseTypePath()));
		owner.removeFromOperations(remover);
	}

	private void removeInternalAdder(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJOperation adder = owner.findOperation(map.internalAdder(), Arrays.asList(map.javaBaseTypePath()));
		owner.removeFromOperations(adder);
	}
	
	private void removeField(OJAnnotatedClass owner, NakedStructuralFeatureMap map) {
		OJField field = owner.findField(map.umlName());
		if (field!=null) {
			owner.removeFromFields(field);
		}
	}
	
}
