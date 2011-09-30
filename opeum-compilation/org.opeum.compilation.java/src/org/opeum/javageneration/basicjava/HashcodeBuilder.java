package org.opeum.javageneration.basicjava;

import java.util.UUID;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.feature.visit.VisitBefore;
import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJField;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedDataType;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedHelper;
import org.opeum.metamodel.core.INakedInterface;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	Java6ModelGenerator.class
},after = {
	Java6ModelGenerator.class
})
public class HashcodeBuilder extends AbstractStructureVisitor{
	@VisitAfter(matchSubclasses = true)
	protected void visitComplexStructure(INakedComplexStructure c){
		if(OJUtil.hasOJClass(c) && !(c instanceof INakedInterface)){
			OJAnnotatedClass ojClass = findJavaClass(c);
			this.buildHashcode(ojClass, c);
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitInterface(INakedInterface i){
		if(OJUtil.hasOJClass(i) && !(i instanceof INakedHelper)){
			OJAnnotatedClass ojClass = findJavaClass(i);
			ojClass.addToOperations(new OJAnnotatedOperation("getUid", new OJPathName("String")));
		}
	}
	private void buildHashcode(OJAnnotatedClass owner,INakedClassifier umlClass){
		OJField uid = owner.findField("uid");
		if(umlClass.getGeneralizations().isEmpty() && !(umlClass instanceof INakedEnumeration)){
			if(uid == null){
				owner.addToFields(new OJAnnotatedField("uid", new OJPathName("String")));
				// TODO build validation that a derived or read only uuid
				// property is not allowed
				OJOperation setUid = OJUtil.findOperation(owner, "setUid");
				if(setUid == null){
					setUid = new OJAnnotatedOperation("setUid");
					setUid.addParam("newUid", new OJPathName("java.lang.String"));
					owner.addToOperations(setUid);
				}
				setUid.setBody(new OJBlock());
				setUid.getBody().addToStatements("this.uid=newUid");
				OJOperation getUid = OJUtil.findOperation(owner, "getUid");
				if(getUid == null){
					getUid = new OJAnnotatedOperation("getUid", new OJPathName("java.lang.String"));
					owner.addToOperations(getUid);
				}
				getUid.setBody(new OJBlock());
				owner.addToImports(new OJPathName(UUID.class.getName()));
				getUid.getBody().addToStatements(new OJIfStatement("this.uid==null || this.uid.trim().length()==0", "uid=UUID.randomUUID().toString()"));
				getUid.getBody().addToStatements("return this.uid");
			}
		}
		if(!(umlClass instanceof INakedDataType)){
			OJOperation equals = new OJAnnotatedOperation("equals", new OJPathName("boolean"));
			equals.addParam("other", new OJPathName("Object"));
			equals.getBody().addToStatements(
					new OJIfStatement("other instanceof " + owner.getName(), "return other==this || ((" + owner.getName() + ")other).getUid().equals(this.getUid())"));
			equals.getBody().addToStatements("return false");
			owner.addToOperations(equals);
			OJOperation hashCode = new OJAnnotatedOperation("hashCode", new OJPathName("int"));
			hashCode.getBody().addToStatements("return getUid().hashCode()");
			owner.addToOperations(hashCode);
		}
		// TODO DataTypes!!!!
	}
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
		
	}
}
