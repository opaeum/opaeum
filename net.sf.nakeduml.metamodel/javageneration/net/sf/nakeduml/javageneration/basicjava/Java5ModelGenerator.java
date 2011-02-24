package net.sf.nakeduml.javageneration.basicjava;

import java.util.List;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.NakedOperationMap;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedInterface;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.javametamodel.annotation.OJEnum;
import net.sf.nakeduml.javametamodel.annotation.OJEnumLiteral;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.actions.internal.OpaqueActionMessageStructureImpl;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedSignal;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedDataType;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedEnumerationLiteral;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import net.sf.nakeduml.util.AbstractSignal;
import nl.klasse.octopus.OctopusConstants;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;

public class Java5ModelGenerator extends StereotypeAnnotator {
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		// We do not generate simple data types. They can't participate in
		// two-way
		// associations
		// and should be built-in or pre-implemented
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedSimpleType)) {
			ClassifierMap classifierMap = new NakedClassifierMap(c);
			OJAnnotatedClass myClass;
			if (c instanceof INakedEnumeration) {
				myClass = new OJEnum();
			} else if (c instanceof INakedInterface) {
				myClass = new OJAnnotatedInterface();
			} else {
				myClass = new OJAnnotatedClass();
				// Create default constructor for inits
				myClass.getDefaultConstructor();
			}
			// TODO find another place
			if (c instanceof INakedSignal) {
				myClass.setSuperclass(new OJPathName(AbstractSignal.class.getName()));
			}
			myClass.setName(c.getName());
			myClass.setVisibility(classifierMap.javaVisibility());
			myClass.setAbstract(c.getIsAbstract());
			myClass.setComment(c.getDocumentation());
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
					applyStereotypesAsAnnotations((l), ojLiteral);
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
			applyStereotypesAsAnnotations((c), myClass);
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitPackage(INakedPackage p) {
		OJAnnotatedPackage currentPack = new OJAnnotatedPackage();
		currentPack.setName(p.getName().toLowerCase());
		if (p.getDocumentation() != null) {
			currentPack.setComment(p.getDocumentation());
		}
		super.applyStereotypesAsAnnotations(p, currentPack);
		if (p.getParent() == null || p.getParent().getName().equals(OctopusConstants.OCTOPUS_INVISIBLE_PACK_NAME)) {
			currentPack.setParent(this.javaModel);
		} else {
			OJPathName path = OJUtil.packagePathname(p.getParent());
			currentPack.setParent(this.javaModel.findPackage(path));
		}
		super.createTextPathIfRequired(currentPack, JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
	}

	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation no) {
		if (no.shouldEmulateClass() ||  BehaviorUtil. hasMethodsWithStructure(no)) {
			OperationMessageStructureImpl message = new OperationMessageStructureImpl(no.getOwner(), no);
			this.visitClass(message);
			if(no.isUserResponsibility()){
				NakedOperationMap map = new NakedOperationMap(no);
				OJAnnotatedInterface listener = new OJAnnotatedInterface(map.callbackListener());
				OJPackage pack = findOrCreatePackage(map.callbackListenerPath().getHead());
				listener.setMyPackage(pack);
				OJAnnotatedOperation callBackOper = new OJAnnotatedOperation(map.callbackOperName());
				callBackOper.addParam("completedTask", new NakedClassifierMap(message).javaTypePath());
				listener.addToOperations( callBackOper);
				
				super.createTextPath(listener, JavaTextSource.OutputRootId.DOMAIN_GEN_SRC);
			}
		}
	}

	

	@VisitBefore(matchSubclasses = true)
	public void visitOpaqueAction(INakedOpaqueAction oa) {
		if (oa.isTask()) {
			this.visitClass(new OpaqueActionMessageStructureImpl(oa));
		}
	}
}
