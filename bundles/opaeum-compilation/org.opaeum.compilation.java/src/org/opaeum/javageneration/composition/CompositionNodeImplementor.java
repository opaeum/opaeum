package org.opaeum.javageneration.composition;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.ocl.uml.MessageType;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfAssociationUtil;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.eclipse.emulated.AssociationClassToEnd;
import org.opaeum.feature.StepDependency;
import org.opaeum.feature.visit.VisitAfter;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJClassifier;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJOperation;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSimpleStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AbstractStructureVisitor;
import org.opaeum.javageneration.basicjava.OperationAnnotator;
import org.opaeum.javageneration.hibernate.HibernateAnnotator;
import org.opaeum.javageneration.maps.AssociationClassEndMap;
import org.opaeum.javageneration.oclexpressions.AttributeExpressionGenerator;
import org.opaeum.runtime.domain.CompositionNode;

/**
 * This class implements the CompositionNode semantics which enriches the Java model with ideas on how compositions should ideally be
 * implemented.
 */
@StepDependency(phase = JavaTransformationPhase.class,requires = {OperationAnnotator.class},after = {OperationAnnotator.class,
		AttributeExpressionGenerator.class})
public class CompositionNodeImplementor extends AbstractStructureVisitor{
	protected static OJPathName COMPOSITION_NODE = new OJPathName(CompositionNode.class.getName());
	public static final String GET_OWNING_OBJECT = "getOwningObject";
	protected void visitClass(OJAnnotatedClass ojClass,Classifier c){
		boolean isTransientMessageStructure = c instanceof MessageType && !(EmfClassifierUtil.isPersistent(c));
		if(!isTransientMessageStructure){
			ojClass.addToImplementedInterfaces(COMPOSITION_NODE);
			addGetOwningObject(c, ojClass);
			addRemoveFromOwner(ojClass);
			addMarkDeleted(ojClass, c);
		}
		addAddToOwningObject(ojClass, c);
		addInit(c, ojClass);
		addConstructorForTests(c, ojClass);
	}
	public void addConstructorForTests(Classifier entity,OJAnnotatedClass ojClass){
		if(!isInterfaceOrAssociationClass(entity)){
			Property endToComposite = getLibrary().getEndToComposite(entity);
			if(endToComposite != null){
				Classifier owningType = (Classifier) endToComposite.getType();
				OJPathName paramPath = ojUtil.classifierPathname(owningType);
				OJConstructor testConstructor = ojClass.findConstructor(paramPath);
				if(testConstructor == null){
					testConstructor = new OJConstructor();
					ojClass.addToConstructors(testConstructor);
					testConstructor.addParam("owningObject", ojUtil.classifierPathname(owningType));
					if(isMap(endToComposite.getOtherEnd())){
						for(Property p:endToComposite.getOtherEnd().getQualifiers()){
							PropertyMap qMap = ojUtil.buildStructuralFeatureMap(p);
							testConstructor.addParam(qMap.fieldname(), qMap.javaTypePath());
							testConstructor.getBody().addToStatements(qMap.setter() + "(" + qMap.fieldname() + ")");
						}
					}
					testConstructor.getBody().addToStatements("init(owningObject)");
				}else{
				}
				testConstructor.setComment("This constructor is intended for easy initialization in unit tests");
				testConstructor.getBody().addToStatements("addToOwningObject()");
			}
		}
	}
	public void addAddToOwningObject(OJAnnotatedClass ojClass,Classifier entity){
		OJOperation addToOwningObject = new OJAnnotatedOperation("addToOwningObject");
		addToOwningObject.setComment("Call this method when you want to attach this object to the containment tree. Useful with transitive persistence");
		if(!isInterfaceOrAssociationClass(entity)){
			Property endToComposite = getLibrary().getEndToComposite(entity);
			if(isModifiable(endToComposite)){
				PropertyMap map = ojUtil.buildStructuralFeatureMap(endToComposite);
				if(EmfAssociationUtil.isClass(endToComposite.getAssociation())){
					AssociationClassEndMap aMap = new AssociationClassEndMap(ojUtil, endToComposite);
					String targetExpression = aMap.getEndToAssocationClassMap().getter() + "()";
					if(isMap(endToComposite.getOtherEnd())){
						super.addCheckForNullQualifiers(map, endToComposite.getOtherEnd(), addToOwningObject.getBody());
						targetExpression = ojUtil.addQualifierArguments(endToComposite.getOtherEnd().getQualifiers(), "this") + targetExpression;
					}
					addToOwningObject.getBody().addToStatements(
							aMap.getMap().getter() + "()." + aMap.getOtherEndToAssocationClassMap().internalAdder() + "(" + targetExpression + ")");
				}else{
					PropertyMap featureMap = ojUtil.buildStructuralFeatureMap(endToComposite);
					PropertyMap otherFeatureMap = ojUtil.buildStructuralFeatureMap(endToComposite.getOtherEnd());
					String args = "(" + ojClass.getName() + ")this";
					if(isMap(otherFeatureMap.getProperty())){
						super.addCheckForNullQualifiers(map, endToComposite.getOtherEnd(), addToOwningObject.getBody());
						args = ojUtil.addQualifierArguments(otherFeatureMap.getProperty().getQualifiers(), "this") + "(" + ojClass.getName() + ")this";
					}
					addToOwningObject.getBody().addToStatements(featureMap.getter() + "()." + otherFeatureMap.internalAdder() + "(" + args + ")");
				}
			}
		}
		if(entity instanceof BehavioredClassifier && ((BehavioredClassifier) entity).getClassifierBehavior() != null){
			addToOwningObject.getBody().addToStatements("startClassifierBehavior()");
		}
		ojClass.addToOperations(addToOwningObject);
	}
	public boolean isInterfaceOrAssociationClass(Classifier c){
		return c instanceof Interface || c instanceof Association;
	}
	public void addMarkDeleted(OJAnnotatedClass ojClass,Classifier sc){
		OJAnnotatedOperation markDeleted = new OJAnnotatedOperation("markDeleted");
		ojClass.addToOperations(markDeleted);
		if(sc.getGenerals().size() >= 1){
			markDeleted.getBody().addToStatements("super.markDeleted()");
		}else if(AbstractJavaProducingVisitor.isPersistent(sc)){
			if(transformationContext.isFeatureSelected(HibernateAnnotator.class)){
				ojClass.addToImports("java.util.Date");
				markDeleted.getBody().addToStatements("setDeletedOn(new Date(System.currentTimeMillis()))");
			}else{
				// is deletion relevant?
			}
		}
		removeFromReferences(sc, ojClass, markDeleted);
		invokeOperationRecursively(sc, markDeleted, "markDeleted()");
	}
	protected void removeFromReferences(Classifier sc,OJClass ojClass,OJAnnotatedOperation markDeleted){
		for(Property np:getLibrary().getEffectiveAttributes(sc)){
			if(!(np instanceof AssociationClassToEnd) && !np.isComposite() && isOtherEndModifiable(np) && isModifiable(np)
					&& (isPersistentClassOrInterface(np.getType()) && !(np.getType() instanceof DataType))){
				PropertyMap map = ojUtil.buildStructuralFeatureMap(np);
				if(!map.isEndToAssociationClass()){
					PropertyMap otherMap = ojUtil.buildStructuralFeatureMap(np.getOtherEnd());
					if(map.isMany()){
						if(EmfAssociationUtil.isClass(np.getAssociation())){
							AssociationClassEndMap aMap = map.getAssocationClassMap();
							PropertyMap mapToAssociation = aMap.getEndToAssocationClassMap();
							OJForStatement forEachLink = new OJForStatement("link", mapToAssociation.javaBaseTypePath(), "new HashSet<" + mapToAssociation.javaBaseType()
									+ ">(" + mapToAssociation.getter() + "())");
							String args = "link";
							if(isMap(otherMap.getProperty())){
								args = ojUtil.addQualifierArguments(otherMap.getProperty().getQualifiers(), "this") + "link";
							}
							forEachLink.getBody().addToStatements(
									"link." + aMap.getAssocationClassToOtherEndMap().getter() + "()." + aMap.getOtherEndToAssocationClassMap().internalRemover() + "(" + args
											+ ")");
							markDeleted.getBody().addToStatements(forEachLink);
						}else if(!map.isEndToAssociationClass()){
							// Nothing we can do - data about this relationship will be lost
							markDeleted.getBody().addToStatements(map.removeAll() + "(" + map.getter() + "())");
						}
					}else if(map.isManyToOne()){
						OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null");
						if(EmfAssociationUtil.isClass(np.getAssociation())){
							AssociationClassEndMap aMap = ojUtil.buildAssociationClassEndMap(np);
							OJAnnotatedField link = new OJAnnotatedField("link", aMap.getEndToAssocationClassMap().javaBaseDefaultTypePath());
							ifNotNull.getThenPart().addToLocals(link);
							link.setInitExp(aMap.getEndToAssocationClassMap().getter() + "()");
							String args = "link";
							if(isMap(np.getOtherEnd())){
								args = ojUtil.addQualifierArguments(np.getOtherEnd().getQualifiers(), "this") + "link";
							}
							ifNotNull.getThenPart().addToStatements(
									"link." + aMap.getAssocationClassToOtherEndMap().getter() + "()." + aMap.getOtherEndToAssocationClassMap().internalRemover() + "(" + args
											+ ")");
							ifNotNull.getThenPart().addToStatements("link.markDeleted()");
						}else{
							String args = "this";
							if(isMap(np.getOtherEnd())){
								args = ojUtil.addQualifierArguments(np.getOtherEnd().getQualifiers(), "this") + "this";
							}
							ifNotNull.getThenPart().addToStatements(map.getter() + "()." + otherMap.internalRemover() + "(" + args + ")");
						}
						markDeleted.getBody().addToStatements(ifNotNull);
					}else if(map.isOneToOne()){
						OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null");
						if(EmfAssociationUtil.isClass(map.getProperty().getAssociation())){
							AssociationClassEndMap aMap = map.getAssocationClassMap();
							ifNotNull.getThenPart().addToStatements(
									map.getter() + "()." + aMap.getOtherEndToAssocationClassMap().internalRemover() + "(" + aMap.getEndToAssocationClassMap().getter() + "())");
						}else{
							ifNotNull.getThenPart().addToStatements(map.getter() + "()." + otherMap.internalRemover() + "(this)");
						}
						markDeleted.getBody().addToStatements(ifNotNull);
					}
				}
			}
		}
	}
	@VisitAfter(matchSubclasses = true)
	public void visitInterface(Interface i){
		if(!EmfClassifierUtil.isHelper(i) && ojUtil.hasOJClass(i)){
			OJPathName path = ojUtil.classifierPathname(i);
			OJClassifier ojClassifier = this.javaModel.findClass(path);
			((OJAnnotatedInterface) ojClassifier).addToSuperInterfaces(COMPOSITION_NODE);
			ojClassifier.addToImports(COMPOSITION_NODE);
		}
	}
	protected void addRemoveFromOwner(OJClass ojClass){
		OJAnnotatedOperation remove = new OJAnnotatedOperation("removeFromOwningObject");
		remove.getBody().addToStatements("this.markDeleted()");
		ojClass.addToOperations(remove);
	}
	/**
	 * Removes initialization logic from the default constructor and adds it to the init method which takes the
	 */
	protected void addInit(Classifier c,OJClass ojClass){
		OJOperation init = new OJAnnotatedOperation("init");
		if(isPersistent(c)){
			init.addParam("owner", COMPOSITION_NODE);
		}else{
			Property etc = getLibrary().getEndToComposite(c);
			if(etc != null){
				init.addParam("owner", ojUtil.classifierPathname(etc.getType()));
			}
		}
		init.setBody(ojClass.getDefaultConstructor().getBody());
		ojClass.getDefaultConstructor().setBody(new OJBlock());
		int start = 0;
		if(c.getGenerals().size() >= 1){
			OJSimpleStatement simpleStatement = new OJSimpleStatement("super.init(owner)");
			if(init.getBody().getStatements().isEmpty()){
				init.getBody().getStatements().add(simpleStatement);
			}else{
				init.getBody().getStatements().set(0, simpleStatement);
			}
			start++;
		}
		Property etc = getLibrary().getEndToComposite(c);
		if(etc != null && !EmfPropertyUtil.isDerived(etc)){
			PropertyMap mapToComposite = ojUtil.buildStructuralFeatureMap(etc);
			ojClass.addToImports(mapToComposite.javaBaseTypePath());
			String add = "this." + mapToComposite.internalAdder() + "((" + mapToComposite.javaBaseType() + ")owner)";
			if(!mapToComposite.isAssociationClassToEnd() && isInvolvedInAnAssociationClass(mapToComposite)){
				AssociationClassEndMap aMap = mapToComposite.getAssocationClassMap();
				add = "this." + aMap.getEndToAssocationClassMap().internalAdder() + "(new " + aMap.getEndToAssocationClassMap().javaBaseType() + "(("
						+ aMap.getAssociationClassToThisEndMap().javaType() + ")this,(" + mapToComposite.javaBaseType() + ")owner))";
			}
			init.getBody().getStatements().add(start, new OJSimpleStatement(add));
		}
		// init.getBody().addToStatements(0, new OJIfStatement("getOwningObject()!=null && !getOwningObject().equals(owner)",
		ojClass.addToOperations(init);
	}
	protected void addGetOwningObject(Classifier c,OJClass ojClass){
		OJOperation getOwner = new OJAnnotatedOperation(GET_OWNING_OBJECT);
		if(isPersistent(c)){
			getOwner.setReturnType(COMPOSITION_NODE);
		}else{
			Property endToComposite = getLibrary().getEndToComposite(c);
			if(endToComposite != null){
				getOwner.setReturnType(ojUtil.classifierPathname(endToComposite.getType()));
			}
		}
		getOwner.setBody(new OJBlock());
		Property endToComposite = getLibrary().getEndToComposite(c);
		if(endToComposite != null){
			PropertyMap map = ojUtil.buildStructuralFeatureMap(endToComposite);
			getOwner.getBody().addToStatements("return " + map.getter() + "()");
		}else{
			getOwner.getBody().addToStatements("return null");
		}
		ojClass.addToOperations(getOwner);
	}
	@Override
	protected void visitProperty(OJAnnotatedClass ojOwner,Classifier owner,PropertyMap buildStructuralFeatureMap){
	}
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass ojOwner,Classifier umlOwner){
		if(EmfClassifierUtil.isCompositionParticipant(umlOwner)){
			visitClass(ojOwner, (Classifier) umlOwner);
		}else if(EmfClassifierUtil.isStructuredDataType(umlOwner)){
			addMarkDeleted(ojOwner, umlOwner);
		}
		return false;
	}
	public void invokeOperationRecursively(Classifier ew,OJOperation markDeleted,String operationName){
		for(Property np:getLibrary().getEffectiveAttributes(ew)){
			PropertyMap map = ojUtil.buildStructuralFeatureMap(np);
			if(np.isComposite() && (isPersistent(np.getType()) || np.getType() instanceof Interface) && !EmfPropertyUtil.isDerived(np)
					&& !(np.getType() instanceof DataType) && !map.isAssociationClassToEnd()){
				Classifier type = (Classifier) np.getType();
				if(map.isMany()){
					markDeleted.getOwner().addToImports("java.util.ArrayList");
					OJForStatement forEach = new OJForStatement();
					forEach.setCollectionExpression("new ArrayList<" + map.javaBaseDefaultType() + ">(" + map.getter() + "())");
					forEach.setElemType(ojUtil.classifierPathname(type));
					forEach.setElemName("child");
					forEach.setBody(new OJBlock());
					forEach.getBody().addToStatements("child." + operationName);
					markDeleted.getBody().addToStatements(forEach);
				}else if(map.isOne()){
					OJIfStatement ifNotNull = new OJIfStatement(map.getter() + "()!=null", map.getter() + "()." + operationName);
					markDeleted.getBody().addToStatements(ifNotNull);
				}
			}
		}
	}
}