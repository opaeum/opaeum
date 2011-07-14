package org.nakeduml.tinker.passbyvalue;

import java.util.Collection;
import java.util.Set;

import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import nl.klasse.octopus.model.IClassifier;

import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;

public abstract class AbstractPassByValueImplementor extends StereotypeAnnotator {

	protected void findAllImplementingClassifiers(Set<INakedClassifier> implementingClassifiers, INakedClassifier c) {
		if (c instanceof INakedInterface) {
			INakedInterface i = (INakedInterface) c;
			Collection<INakedClassifier> classifiers = i.getImplementingClassifiers();
			for (INakedClassifier classifier : classifiers) {
				if (!implementingClassifiers.contains(classifier)) {
					implementingClassifiers.add(classifier);
					findAllImplementingClassifiers(implementingClassifiers, classifier);
				}
			}
		} else if (c instanceof INakedEntity) {
			INakedEntity e = (INakedEntity) c;
			Collection<IClassifier> classifiers = e.getSubClasses();
			for (IClassifier classifier : classifiers) {
				if (!implementingClassifiers.contains(classifier)) {
					implementingClassifiers.add((INakedClassifier) classifier);
					findAllImplementingClassifiers(implementingClassifiers, (INakedClassifier) classifier);
				}
			}
		} else {
			throw new RuntimeException("only INakedInterface and INakedEntity catered for, not " + c.getClass().getName());
		}
	}
	
	protected OJAnnotatedClass findDtoJavaClass(INakedClassifier classifier){
		OJPathName path = PassByValueUtil.classifierDtoPathname(classifier);
		OJAnnotatedClass owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		if(owner == null){
			owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		}
		return owner;
	}
	protected OJAnnotatedClass findAssemblerJavaClass(INakedClassifier classifier){
		OJPathName path = PassByValueUtil.classifierAssemblerPathname(classifier);
		OJAnnotatedClass owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		if(owner == null){
			owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		}
		return owner;
	}
	protected OJAnnotatedClass findControlerJavaClass(INakedClassifier classifier){
		OJPathName path = PassByValueUtil.classifierControllerPathname(classifier);
		OJAnnotatedClass owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		if(owner == null){
			owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		}
		return owner;
	}

	protected OJAnnotatedClass findWsInterfaceJavaClass(INakedClassifier classifier){
		OJPathName path = PassByValueUtil.classifierWsInterfacePathname(classifier);
		OJAnnotatedClass owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		if(owner == null){
			owner = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		}
		return owner;
	}	
}
