package net.sf.nakeduml.javageneration.basicjava;

import java.util.UUID;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.StereotypeAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.metamodel.actions.INakedOpaqueAction;
import net.sf.nakeduml.metamodel.actions.internal.OpaqueActionMessageStructureImpl;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedDataType;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;

import org.nakeduml.java.metamodel.OJBlock;
import org.nakeduml.java.metamodel.OJField;
import org.nakeduml.java.metamodel.OJIfStatement;
import org.nakeduml.java.metamodel.OJOperation;
import org.nakeduml.java.metamodel.OJPathName;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedClass;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedField;
import org.nakeduml.java.metamodel.annotation.OJAnnotatedOperation;

public class HashcodeBuilder extends StereotypeAnnotator {
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c) {
		if (OJUtil.hasOJClass(c) && !(c instanceof INakedInterface)) {
			OJAnnotatedClass ojClass = findJavaClass(c);
			this.buildHashcode(ojClass, c);
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitOperation(INakedOperation no) {
		if (no.shouldEmulateClass() || BehaviorUtil.hasMethodsWithStructure(no)) {
			this.visitClass(new OperationMessageStructureImpl(no.getOwner(), no));
		}
	}

	@VisitBefore()
	public void visitOpaqueAction(INakedOpaqueAction oa) {
		if (oa.isTask()) {
			this.visitClass(new OpaqueActionMessageStructureImpl(oa));
		}
	}

	private void buildHashcode(OJAnnotatedClass owner, INakedClassifier umlClass) {
		OJField uid = owner.findField("uid");
		if (umlClass.getGeneralizations().isEmpty()) {
			if (uid == null) {
				owner.addToFields(new OJAnnotatedField("uid", new OJPathName("String")));
				// TODO build validation that a derived or read only uuid
				// property is not allowed
				OJOperation setUid = OJUtil.findOperation(owner, "setUid");
				if (setUid == null) {
					setUid = new OJAnnotatedOperation("setUid");
					setUid.addParam("newUid", new OJPathName("java.lang.String"));
					owner.addToOperations(setUid);
				}
				setUid.setBody(new OJBlock());
				setUid.getBody().addToStatements("this.uid=newUid");
				OJOperation getUid = OJUtil.findOperation(owner, "getUid");
				if (getUid == null) {
					getUid = new OJAnnotatedOperation("getUid", new OJPathName("java.lang.String"));
					owner.addToOperations(getUid);
				}
				getUid.setBody(new OJBlock());
				owner.addToImports(new OJPathName(UUID.class.getName()));
				getUid.getBody().addToStatements(new OJIfStatement("this.uid==null || this.uid.trim().length()==0", "uid=UUID.randomUUID().toString()"));
				getUid.getBody().addToStatements("return this.uid");
			}
		}
		if (!(umlClass instanceof INakedDataType)) {
			OJOperation equals = new OJAnnotatedOperation("equals", new OJPathName("boolean"));
			equals.addParam("other", new OJPathName("Object"));
			equals.getBody().addToStatements(
					new OJIfStatement("other instanceof " + owner.getName(), "return other==this || ((" + owner.getName()
							+ ")other).getUid().equals(this.getUid())"));
			equals.getBody().addToStatements("return false");
			owner.addToOperations(equals);
			OJOperation hashCode = new OJAnnotatedOperation("hashCode", new OJPathName("int"));
			hashCode.getBody().addToStatements("return getUid().hashCode()");
			owner.addToOperations(hashCode);
		}
		// TODO DataTypes!!!!
	}
}
