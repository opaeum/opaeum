package org.opaeum.javageneration.composition;

import java.util.ArrayList;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
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
import org.opaeum.javageneration.persistence.PersistentObjectImplementor;
import org.opaeum.name.NameConverter;

@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class},after = {OperationAnnotator.class})
public class CopyMethodImplementor extends AbstractStructureVisitor{
	protected void visitComplexStructure(Classifier c){
		OJPathName path = ojUtil.classifierPathname(c);
		OJClassifier myOwner = this.javaModel.findClass(path);
		// NakedModelElement mew = (NakedModelElement)
		// nakedModel.lookup(c.getPathName());
		if(myOwner instanceof OJClass && (c instanceof Class || EmfClassifierUtil.isStructuredDataType(c))){
			Classifier nc = (Classifier) c;
			OJClass ojClass = (OJClass) myOwner;
			implementCopyMethod(ojClass, nc);
			addCopyStateMethod(nc, ojClass);
			addShallowMakeCopyMethod(ojClass, nc);
			addShallowCopyStateMethod(nc, ojClass);
		}
	}
	private void addShallowMakeCopyMethod(OJClass owner,Classifier classifier){
		OJAnnotatedOperation oper = new OJAnnotatedOperation("makeShallowCopy");
		oper.setReturnType(ojUtil.classifierPathname(classifier));
		owner.addToOperations(oper);
		if(classifier.isAbstract() || owner.isAbstract()){
			// Can NEVER instantiate abstract objects
			oper.setAbstract(true);
		}else{
			OJBlock body = oper.getBody();
			oper.initializeResultVariable("new " + owner.getName() + "()");
			oper.getResultVariable().setType(owner.getPathName());
			body.addToStatements("copyShallowState((" + classifier.getName() + ")this,result)");
			if(super.transformationContext.isFeatureSelected(PersistentObjectImplementor.class)){
				body.addToStatements(new OJSimpleStatement("result.setId(this.getId())"));
			}
		}
	}
	private void implementCopyMethod(OJClass owner,Classifier classifier){
		OJAnnotatedOperation oper = (OJAnnotatedOperation) owner.getUniqueOperation("makeCopy");
		if(oper == null){
			oper = new OJAnnotatedOperation("makeCopy");
			owner.addToOperations(oper);
		}else{
			oper.setBody(new OJBlock());
		}
		oper.setReturnType(ojUtil.classifierPathname(classifier));
		if(classifier.isAbstract() || owner.isAbstract()){
			// Can NEVER instantiate abstract objects
			oper.setAbstract(true);
		}else{
			oper.initializeResultVariable("new " + owner.getName() + "()");
			oper.getResultVariable().setType(owner.getPathName());
			OJBlock body = oper.getBody();
			body.addToStatements("copyState((" + classifier.getName() + ")this,result)");
		}
	}
	private void addCopyStateMethod(Classifier classifier,OJClass owner){
		OJOperation oper = new OJAnnotatedOperation("copyState");
		oper.setVisibility(OJVisibilityKindGEN.PUBLIC);
		oper.addParam("from", owner.getPathName());
		oper.addParam("to", owner.getPathName());
		addCopyStatements(classifier, owner, oper.getBody(), true, false);
		owner.addToOperations(oper);
	}
	private void addShallowCopyStateMethod(Classifier classifier,OJClass owner){
		OJOperation oper = new OJAnnotatedOperation("copyShallowState");
		oper.setVisibility(OJVisibilityKindGEN.PUBLIC);
		oper.addParam("from", owner.getPathName());
		oper.addParam("to", owner.getPathName());
		addCopyStatements(classifier, owner, oper.getBody(), false, true);
		owner.addToOperations(oper);
	}
	private void addCopyStatements(Classifier classifier,OJClass owner,OJBlock body,boolean deep,boolean shallowCopy){
		String copyMethodName = shallowCopy ? "makeShallowCopy" : "makeCopy";
		List<? extends Property> properties = getLibrary().getEffectiveAttributes(classifier);
		// TODO implement containment by value (composition) vs containment
		// by reference logic
		// might be helpful for web service, for instance
		// give some more thought
		// maybe consider an input parameter specifying the number of levels to
		// include in a deep copy
		for(Property np:properties){
			PropertyMap map = ojUtil.buildStructuralFeatureMap(np);
			if(!(EmfPropertyUtil.isDerived( np) || (np.getOtherEnd() != null && np.getOtherEnd().isComposite()))){
				if(EmfClassifierUtil.isSimpleType(map.getBaseType()) || map.getBaseType() instanceof Enumeration){
					if(map.isMany()){
						body.addToStatements("to." + map.getter() + "().addAll(from." + map.getter() + "())");
					}else{
						body.addToStatements("to." + map.setter() + "(from." + map.getter() + "())");
					}
				}else if(map.getBaseType() instanceof Class || EmfClassifierUtil.isStructuredDataType(map.getBaseType())){
					OJBlock forBlock = new OJBlock();
					if(EmfClassifierUtil.isStructuredDataType(map.getBaseType()) || (deep && np.isComposite())){
						if(map.isMany()){
							OJForStatement ws = new OJForStatement("child", map.javaBaseTypePath(),"from." + map.getter() + "()");
							OJBlock whileBody = forBlock;
							ws.setBody(whileBody);
							if(isMap(map.getProperty())){
								StringBuilder sb = new StringBuilder();
								List<Property> qualifiers = map.getProperty().getQualifiers();
								// Assume qualifiers are back by attributes as we are doing composition here
								for(Property q:qualifiers){
									PropertyMap qMap = ojUtil.buildStructuralFeatureMap(q);
									sb.append("child.");
									sb.append(qMap.getter());
									sb.append("(),");
								}
								whileBody.addToStatements("to." + map.adder() + "(" + sb.toString() + "child." + copyMethodName + "())");
							}else{
								whileBody.addToStatements("to." + map.adder() + "(child." + copyMethodName + "())");
							}
							body.addToStatements(ws);
						}else{
							OJIfStatement ifNotNull = new OJIfStatement("from." + map.getter() + "()!=null", "to." + map.setter() + "(from."
									+ map.getter() + "()." + copyMethodName + "())");
							body.addToStatements(ifNotNull);
						}
					}else if(map.isOne() && EmfPropertyUtil.isInverse(np)){
						OJIfStatement ifNotNull = new OJIfStatement("from." + map.getter() + "()!=null", "to." + map.setter() + "(from." + map.getter()
								+ "()." + copyMethodName + "())");
						body.addToStatements(ifNotNull);
					}else if(map.isManyToMany() && EmfPropertyUtil.isInverse(np)){
						String localCopyMethodName;
						if(shallowCopy){
							localCopyMethodName = "copyShallowState";
						}else{
							localCopyMethodName = "copyState";
						}
						body.addToStatements("to." + map.allAdder() + "(" + localCopyMethodName + NameConverter.capitalize(map.fieldname()) + "(from."
								+ map.getter() + "()))");
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
							// copyMany.getResultVariable().setType(owner.getPathName());
							OJForStatement forS = new OJForStatement("entity", map.javaBaseTypePath(),"from");
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
	@Override
	protected void visitProperty(Classifier owner,PropertyMap buildStructuralFeatureMap){
	}
}
