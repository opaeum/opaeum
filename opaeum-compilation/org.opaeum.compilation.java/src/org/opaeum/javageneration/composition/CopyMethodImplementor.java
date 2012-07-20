package org.opaeum.javageneration.composition;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.model.IModelElement;

import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.generated.OJVisibilityKindGEN;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.persistence.PersistentObjectImplementor;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.linkage.CompositionEmulator;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedEntity;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class,requires = {
		CompositionEmulator.class,OperationAnnotator.class
},after = {
	OperationAnnotator.class
})
public class CopyMethodImplementor extends AbstractStructureVisitor{
	protected void visitComplexStructure(INakedComplexStructure c){
		OJPathName path = OJUtil.classifierPathname(c);
		OJClassifier myOwner = this.javaModel.findClass(path);
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
		if(classifier.isAbstract() || owner.isAbstract()){
			// Can NEVER instantiate abstract objects
			oper.setAbstract(true);
		}else{
			OJBlock body = oper.getBody();
			oper.initializeResultVariable("new " + owner.getName() + "()");
			oper.getResultVariable().setType(owner.getPathName());
			body.addToStatements("copyShallowState((" + classifier.getMappingInfo().getJavaName() + ")this,result)");
			if(super.transformationContext.isFeatureSelected(PersistentObjectImplementor.class)){
				body.addToStatements(new OJSimpleStatement("result.setId(this.getId())"));
			}
		}
	}
	private void implementCopyMethod(OJClass owner,INakedClassifier classifier){
		OJAnnotatedOperation oper = (OJAnnotatedOperation) owner.getUniqueOperation("makeCopy");
		if(oper == null){
			oper = new OJAnnotatedOperation("makeCopy");
			owner.addToOperations(oper);
		}else{
			oper.setBody(new OJBlock());
		}
		oper.setReturnType(OJUtil.classifierPathname(classifier));
		if(classifier.isAbstract() || owner.isAbstract()){
			// Can NEVER instantiate abstract objects
			oper.setAbstract(true);
		}else{
			oper.initializeResultVariable("new " + owner.getName() + "()");
			oper.getResultVariable().setType(owner.getPathName());

			OJBlock body = oper.getBody();
			body.addToStatements("copyState((" + classifier.getMappingInfo().getJavaName() + ")this,result)");
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
								if(isMap(map.getProperty())){
									StringBuilder sb = new StringBuilder();
									List<INakedProperty> qualifiers = map.getProperty().getQualifiers();
									//Assume qualifiers are back by attributes as we are doing composition here
									for(INakedProperty q:qualifiers){
										NakedStructuralFeatureMap qMap = OJUtil.buildStructuralFeatureMap(q);
										sb.append("child.");
										sb.append(qMap.getter());
										sb.append("(),");
									}
									whileBody.addToStatements("to." + map.adder() + "("+sb.toString()+"child." + copyMethodName + "())");
								}else{
									whileBody.addToStatements("to." + map.adder() + "(child." + copyMethodName + "())");
								}
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
							body.addToStatements("to." + map.allAdder() + "(" + localCopyMethodName + NameConverter.capitalize(map.fieldname()) + "(from." + map.getter()
									+ "()))");
							String operName = localCopyMethodName + NameConverter.capitalize(map.fieldname());
							List<OJPathName> params = new ArrayList<OJPathName>();
							params.add(map.javaTypePath());
							OJOperation oper = owner.findOperation(operName, params);
							if(oper == null){
								OJAnnotatedOperation copyMany = new OJAnnotatedOperation(operName);
								copyMany.setReturnType(map.javaTypePath());
								owner.addToOperations(copyMany);
								copyMany.addParam("from", map.javaTypePath());
								owner.addToImports(map.javaDefaultTypePath());
								copyMany.initializeResultVariable("new " + map.javaDefaultTypePath().getLast() + "<" + map.javaBaseType() + ">()");
//								copyMany.getResultVariable().setType(owner.getPathName());
								OJForStatement forS = new OJForStatement("", "", "entity", "from");
								forS.setElemType(map.javaBaseTypePath());
								forS.setBody(forBlock);
								forBlock.addToStatements(new OJSimpleStatement("result.add(entity." + copyMethodName + "())"));
								copyMany.getBody().addToStatements(forS);
							}
						}
					}
				}
			}
		}
	}
	@Override
	protected void visitProperty(INakedClassifier owner,NakedStructuralFeatureMap buildStructuralFeatureMap){
		
	}
}
