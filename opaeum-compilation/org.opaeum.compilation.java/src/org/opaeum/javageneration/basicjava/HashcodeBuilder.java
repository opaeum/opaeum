package org.opaeum.javageneration.basicjava;

import java.util.UUID;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Interface;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.feature.visit.VisitBefore;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJField;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
	Java6ModelGenerator.class
},after = {
	Java6ModelGenerator.class
})
public class HashcodeBuilder extends AbstractStructureVisitor{
	@VisitAfter(matchSubclasses = true)
	protected void visitComplexStructure(Classifier c){
		if(OJUtil.hasOJClass(c) && !(c instanceof Interface)){
			OJAnnotatedClass ojClass = findJavaClass(c);
			this.buildHashcode(ojClass, c);
		}
	}
	@VisitBefore(matchSubclasses=true)
	public void visitInterface(Interface i){
		if(OJUtil.hasOJClass(i) && !(EmfClassifierUtil.isHelper(i))){
			OJAnnotatedClass ojClass = findJavaClass(i);
			ojClass.addToOperations(new OJAnnotatedOperation("getUid", new OJPathName("String")));
		}
	}
	private void buildHashcode(OJAnnotatedClass owner,Classifier umlClass){
		OJField uid = owner.findField("uid");
		if(umlClass.getGeneralizations().isEmpty() && !(umlClass instanceof Enumeration)){
			if(uid == null){
				owner.addToFields(new OJAnnotatedField("uid", new OJPathName("String")));
				// TODO build validation that a derived or read only uuid
				// property is not allowed
				OJOperation setUid = owner.getUniqueOperation("setUid");
				if(setUid == null){
					setUid = new OJAnnotatedOperation("setUid");
					setUid.addParam("newUid", new OJPathName("java.lang.String"));
					owner.addToOperations(setUid);
				}
				setUid.setBody(new OJBlock());
				setUid.getBody().addToStatements("this.uid=newUid");
				OJOperation getUid = owner.getUniqueOperation("getUid");
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
		if(!(umlClass instanceof DataType)){
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
	protected void visitProperty(Classifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
		
	}
}
