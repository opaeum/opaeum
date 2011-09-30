package org.opeum.javageneration.composition;

import java.util.ArrayList;
import java.util.List;

import org.opeum.feature.StepDependency;
import org.opeum.feature.visit.VisitAfter;
import org.opeum.javageneration.AbstractJavaProducingVisitor;
import org.opeum.javageneration.JavaTransformationPhase;
import org.opeum.javageneration.basicjava.OperationAnnotator;
import org.opeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opeum.javageneration.persistence.PersistentObjectImplementor;
import org.opeum.javageneration.util.OJUtil;
import org.opeum.linkage.CompositionEmulator;
import org.opeum.metamodel.core.INakedClassifier;
import org.opeum.metamodel.core.INakedComplexStructure;
import org.opeum.metamodel.core.INakedEntity;
import org.opeum.metamodel.core.INakedEnumeration;
import org.opeum.metamodel.core.INakedProperty;
import org.opeum.metamodel.core.INakedSimpleType;
import org.opeum.metamodel.core.INakedStructuredDataType;
import nl.klasse.octopus.model.IModelElement;

import org.opeum.java.metamodel.OJBlock;
import org.opeum.java.metamodel.OJClass;
import org.opeum.java.metamodel.OJClassifier;
import org.opeum.java.metamodel.OJField;
import org.opeum.java.metamodel.OJForStatement;
import org.opeum.java.metamodel.OJIfStatement;
import org.opeum.java.metamodel.OJOperation;
import org.opeum.java.metamodel.OJPathName;
import org.opeum.java.metamodel.OJSimpleStatement;
import org.opeum.java.metamodel.annotation.OJAnnotatedField;
import org.opeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opeum.java.metamodel.generated.OJVisibilityKindGEN;
import org.opeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		CompositionEmulator.class,OperationAnnotator.class
},after = {
	OperationAnnotator.class
})
public class CopyMethodImplementor extends AbstractJavaProducingVisitor{
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c){
		OJPathName path = OJUtil.classifierPathname(c);
		OJClassifier myOwner = this.javaModel.findIntfOrCls(path);
		// NakedModelElement mew = (NakedModelElement)
		// nakedModel.lookup(c.getPathName());
		if(myOwner instanceof OJClass && (c instanceof INakedEntity || c instanceof INakedStructuredDataType)){
			INakedComplexStructure nc = (INakedComplexStructure) c;
			OJClass ojClass = (OJClass) myOwner;
			implementCopyMethod(ojClass, nc);
			addCopyStateMethod(nc, ojClass);
			addShallowMakeCopyMethod(ojClass, nc);
			addShallowCopyStateMethod(nc, ojClass);
		}
	}
	private void addShallowMakeCopyMethod(OJClass owner,INakedComplexStructure classifier){
		OJAnnotatedOperation oper = new OJAnnotatedOperation("makeShallowCopy");
		oper.setReturnType(OJUtil.classifierPathname(classifier));
		owner.addToOperations(oper);
		if(classifier.getIsAbstract() || owner.isAbstract()){
			// Can NEVER instantiate abstract objects
			oper.setAbstract(true);
		}else{
			OJBlock body = oper.getBody();
			OJAnnotatedField result = new OJAnnotatedField("result", owner.getPathName());
			result.setInitExp("new " + owner.getName() + "()");
			body.addToLocals(result);
			body.addToStatements("copyShallowState((" + classifier.getMappingInfo().getJavaName() + ")this,result)");
			if(super.transformationContext.isFeatureSelected(PersistentObjectImplementor.class)){
				body.addToStatements(new OJSimpleStatement("result.setId(this.getId())"));
			}
			body.addToStatements("return result");
		}
	}
	private void implementCopyMethod(OJClass owner,INakedClassifier classifier){
		OJOperation oper = OJUtil.findOperation(owner, "makeCopy");
		if(oper == null){
			oper = new OJAnnotatedOperation("makeCopy");
			oper.setReturnType(OJUtil.classifierPathname(classifier));
			owner.addToOperations(oper);
		}else{
			oper.setBody(new OJBlock());
		}
		if(classifier.getIsAbstract() || owner.isAbstract()){
			// Can NEVER instantiate abstract objects
			oper.setAbstract(true);
		}else{
			OJBlock body = oper.getBody();
			OJAnnotatedField result = new OJAnnotatedField("result", owner.getPathName());
			result.setInitExp("new " + owner.getName() + "()");
			body.addToLocals(result);
			body.addToStatements("copyState((" + classifier.getMappingInfo().getJavaName() + ")this,result)");
			body.addToStatements("return result");
		}
	}
	private void addCopyStateMethod(INakedClassifier classifier,OJClass owner){
		OJOperation oper = new OJAnnotatedOperation("copyState");
		oper.setVisibility(OJVisibilityKindGEN.PUBLIC);
		oper.addParam("from", owner.getPathName());
		oper.addParam("to", owner.getPathName());
		addCopyStatements(classifier, owner, oper.getBody(), true, false);
		owner.addToOperations(oper);
	}
	private void addShallowCopyStateMethod(INakedClassifier classifier,OJClass owner){
		OJOperation oper = new OJAnnotatedOperation("copyShallowState");
		oper.setVisibility(OJVisibilityKindGEN.PUBLIC);
		oper.addParam("from", owner.getPathName());
		oper.addParam("to", owner.getPathName());
		addCopyStatements(classifier, owner, oper.getBody(), false, true);
		owner.addToOperations(oper);
	}
	private void addCopyStatements(INakedClassifier classifier,OJClass owner,OJBlock body,boolean deep,boolean shallowCopy){
		String copyMethodName = shallowCopy ? "makeShallowCopy" : "makeCopy";
		List<? extends INakedProperty> properties = classifier.getEffectiveAttributes();
		// TODO implement containment by value (composition) vs containment
		// by reference logic
		// might be helpful for web service, for instance
		// give some more thought
		// maybe consider an input parameter specifying the number of levels to
		// include in a deep copy
		for(int i = 0;i < properties.size();i++){
			IModelElement a = (IModelElement) properties.get(i);
			if(a instanceof INakedProperty){
				INakedProperty np = (INakedProperty) a;
				NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(np);
				if(!(np.isDerived() || (np.getOtherEnd() != null && np.getOtherEnd().isComposite()))){
					if(np.getNakedBaseType() instanceof INakedSimpleType || np.getNakedBaseType() instanceof INakedEnumeration){
						if(map.isMany()){
							body.addToStatements("to." + map.getter() + "().addAll(from." + map.getter() + "())");
						}else{
							body.addToStatements("to." + map.setter() + "(from." + map.getter() + "())");
						}
					}else if(np.getNakedBaseType() instanceof INakedEntity || np.getNakedBaseType() instanceof INakedStructuredDataType){
						OJBlock forBlock = new OJBlock();
						if(np.getNakedBaseType() instanceof INakedStructuredDataType || (deep && np.isComposite())){
							if(map.isMany()){
								OJForStatement ws = new OJForStatement("", "", "child", "from." + map.getter() + "()");
								OJBlock whileBody = forBlock;
								ws.setBody(whileBody);
								ws.setElemType(map.javaBaseTypePath());
								whileBody.addToStatements("to." + map.adder() + "(child." + copyMethodName + "())");
								body.addToStatements(ws);
							}else{
								OJIfStatement ifNotNull = new OJIfStatement("from." + map.getter() + "()!=null", "to." + map.setter() + "(from." + map.getter() + "()."
										+ copyMethodName + "())");
								body.addToStatements(ifNotNull);
							}
						}else if(map.isOne() && np.isInverse()){
							OJIfStatement ifNotNull = new OJIfStatement("from." + map.getter() + "()!=null", "to." + map.setter() + "(from." + map.getter() + "()."
									+ copyMethodName + "())");
							body.addToStatements(ifNotNull);
						}else if(map.isManyToMany() && np.isInverse()){
							String localCopyMethodName;
							if(shallowCopy){
								localCopyMethodName = "copyShallowState";
							}else{
								localCopyMethodName = "copyState";
							}
							body.addToStatements("to." + map.allAdder() + "(" + localCopyMethodName + NameConverter.capitalize(map.umlName()) + "(from." + map.getter()
									+ "()))");
							String operName = localCopyMethodName + NameConverter.capitalize(map.umlName());
							List<OJPathName> params = new ArrayList<OJPathName>();
							params.add(map.javaTypePath());
							OJOperation oper = owner.findOperation(operName, params);
							if(oper == null){
								OJAnnotatedOperation copyMany = new OJAnnotatedOperation(operName);
								copyMany.setReturnType(map.javaTypePath());
								owner.addToOperations(copyMany);
								copyMany.addParam("from", map.javaTypePath());
								OJField result = new OJField();
								result.setName("result");
								result.setType(map.javaTypePath());
								result.setInitExp("new HashSet<" + map.javaBaseType() + ">()");
								copyMany.getBody().addToLocals(result);
								OJForStatement forS = new OJForStatement("", "", "entity", "from");
								forS.setElemType(map.javaBaseTypePath());
								forS.setBody(forBlock);
								forBlock.addToStatements(new OJSimpleStatement("result.add(entity." + copyMethodName + "())"));
								copyMany.getBody().addToStatements(forS);
								copyMany.getBody().addToStatements("return result");
							}
						}
					}
				}
			}
		}
	}
}