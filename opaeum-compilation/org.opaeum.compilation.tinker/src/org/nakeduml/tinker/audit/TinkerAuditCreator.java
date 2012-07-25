package org.nakeduml.tinker.audit;

import java.io.Serializable;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

import org.eclipse.uml2.uml.INakedBehavior;
import org.eclipse.uml2.uml.INakedClassifier;
import org.eclipse.uml2.uml.INakedDataType;
import org.eclipse.uml2.uml.INakedEnumeration;
import org.eclipse.uml2.uml.INakedEnumerationLiteral;
import org.eclipse.uml2.uml.INakedInterface;
import org.eclipse.uml2.uml.INakedOperation;
import org.eclipse.uml2.uml.INakedSignal;
import org.eclipse.uml2.uml.INakedSimpleType;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJEnum;
import org.opaeum.java.metamodel.annotation.OJEnumLiteral;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.maps.NakedClassifierMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = TinkerAuditPhase.class)
public class TinkerAuditCreator extends AbstractJavaProducingVisitor {

	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			ClassifierMap classifierMap = new NakedClassifierMap(c);
			OJAnnotatedClass myClass;
			if (c instanceof INakedEnumeration) {
				return;
			} else if (c instanceof INakedInterface) {
				myClass = new OJAnnotatedInterface(c.getName()+TinkerAuditGenerationUtil.AUDIT);
				// In case it needs to be sent by jms or serialized as session
				// state
				((OJAnnotatedInterface) myClass).addToSuperInterfaces(new OJPathName(Serializable.class.getName()));

			} else {
				myClass = new OJAnnotatedClass(c.getName()+TinkerAuditGenerationUtil.AUDIT);
				// Create default constructor for inits
				myClass.getDefaultConstructor();
				// In case it needs to be sent by jms or serialized as session
				// state
				myClass.addToImplementedInterfaces(new OJPathName(Serializable.class.getName()));
			}
			// TODO find another place
			if (c instanceof INakedSignal) {
//				myClass.setSuperclass(new OJPathName(AbstractSignal.class.getName()));
			}
			myClass.setName(c.getName()+TinkerAuditGenerationUtil.AUDIT);
			myClass.setVisibility(classifierMap.javaVisibility());
			myClass.setAbstract(c.isAbstract());
			myClass.setComment(c.getDocumentation());
			if (c.getSupertype()!=null) {
				OJAnnotatedClass superClass = findJavaClass(c.getSupertype());
				OJPathName superTypePathName = superClass.getPathName();
				String className = superTypePathName.getLast();
				superTypePathName.replaceTail(className+TinkerAuditGenerationUtil.AUDIT);
				myClass.setSuperclass(superTypePathName);
			}
			OJPathName path = OJUtil.packagePathname(c.getNameSpace());
			OJPackage pack = findOrCreatePackage(path);
			myClass.setMyPackage(pack);
			super.createTextPath(myClass, JavaSourceFolderIdentifier.DOMAIN_GEN_SRC);
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
					NakedClassifierMap map = new NakedClassifierMap(new OperationMessageStructureImpl(specification));
					myClass.setSuperclass(map.javaTypePath());
				}
			}
		}
	}

	
}
