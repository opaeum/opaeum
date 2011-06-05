package org.nakeduml.tinker.basicjava.tinker;

import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedProperty;

import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;


public class TinkerFieldCompositeOwnerAdderImplementor extends TinkerFieldAdderImplementor {

	protected void visitProperty(INakedClassifier umlOwner,NakedStructuralFeatureMap map){
		INakedProperty p = map.getProperty();
		if(!OJUtil.isBuiltIn(p) && isCompositeOwner(umlOwner, map)) {
			OJAnnotatedClass owner = findJavaClass(umlOwner);
			buildField(owner, map);
			if(map.isMany() && !map.getProperty().isDerived()){
				buildInternalManyAdder(owner, map);
				buildInternalManyRemover(owner, map);
			}else if(map.isOne() && (isPersistent(p.getNakedBaseType()) || p.getBaseType() instanceof INakedInterface)) {
				buildInternalAdder(owner, map);
				buildInternalRemover(owner, map);
			}
		}
	}	
}
