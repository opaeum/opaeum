package org.nakeduml.tinker.auditing.tinker;

import java.io.Serializable;
import java.util.List;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedDataType;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.validation.namegeneration.AbstractJavaNameGenerator;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.nakeduml.java.metamodel.OJPackage;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedInterface;
import org.nakeduml.java.metamodel.annotation.OJEnum;
import org.nakeduml.java.metamodel.annotation.OJEnumLiteral;
import org.nakeduml.runtime.domain.AbstractSignal;

public class TinkerAuditCreator extends AbstractJavaProducingVisitor {

	public static final String AUDIT = "Audit";
	
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			ClassifierMap classifierMap = new NakedClassifierMap(c);
			OJAnnotatedClass myClass;
			if (c instanceof INakedEnumeration) {
				return;
			} else if (c instanceof INakedInterface) {
				myClass = new OJAnnotatedInterface();
				// In case it needs to be sent by jms or serialized as session
				// state
				((OJAnnotatedInterface) myClass).addToSuperInterfaces(new OJPathName(Serializable.class.getName()));

			} else {
				myClass = new OJAnnotatedClass();
				// Create default constructor for inits
				myClass.getDefaultConstructor();
				// In case it needs to be sent by jms or serialized as session
				// state
				myClass.addToImplementedInterfaces(new OJPathName(Serializable.class.getName()));
			}
			// TODO find another place
			if (c instanceof INakedSignal) {
				myClass.setSuperclass(new OJPathName(AbstractSignal.class.getName()));
			}
			myClass.setName(c.getName()+AUDIT);
			myClass.setVisibility(classifierMap.javaVisibility());
			myClass.setAbstract(c.getIsAbstract());
			myClass.setComment(c.getDocumentation());
			if (c.getSupertype()!=null) {
				OJAnnotatedClass superClass = findJavaClass(c.getSupertype());
				OJPathName superTypePathName = superClass.getPathName();
				String className = superTypePathName.getLast();
				superTypePathName.replaceTail(className+TinkerAuditCreator.AUDIT);
				myClass.setSuperclass(superTypePathName);
			}
			OJPathName path = OJUtil.packagePathname(c.getNameSpace());
			OJPackage pack = findOrCreatePackage(path);
			myClass.setMyPackage(pack);
			super.createTextPath(myClass, JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
			if (c instanceof INakedEnumeration) {
				OJEnum oje = (OJEnum) myClass;
				INakedEnumeration e = (INakedEnumeration) c;
				List<INakedEnumerationLiteral> literals = (List) e.getLiterals();
				for (INakedEnumerationLiteral l : literals) {
					OJEnumLiteral ojLiteral = new OJEnumLiteral(l.getName().toUpperCase());
					ojLiteral.setComment(l.getDocumentation());
					oje.addToLiterals(ojLiteral);
				}
			} else if (c instanceof INakedDataType) {
				// Create default constructor for inits
				myClass.getDefaultConstructor();
				// Signals and StructuredDataTypes
				// TODO implement hashCode and Equals methods
			} else if (c instanceof INakedBehavior) {
				INakedOperation specification = ((INakedBehavior) c).getSpecification();
				if (specification != null) {
					NakedClassifierMap map = new NakedClassifierMap(specification.getMessageStructure(getLibrary()));
					myClass.setSuperclass(map.javaTypePath());
				}
			}
		}
	}
}
