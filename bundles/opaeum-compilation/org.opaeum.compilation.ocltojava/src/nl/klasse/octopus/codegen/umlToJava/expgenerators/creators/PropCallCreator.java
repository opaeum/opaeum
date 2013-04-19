package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.helpers.EmfPropertyCallHelper;
import nl.klasse.octopus.codegen.umlToJava.common.ExpGeneratorHelper;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.NavToAssocClassMap;
import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StdlibMap;
import nl.klasse.tools.common.StringHelpers;

import org.eclipse.ocl.uml.AssociationClassCallExp;
import org.eclipse.ocl.uml.CallExp;
import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.ocl.uml.FeatureCallExp;
import org.eclipse.ocl.uml.IterateExp;
import org.eclipse.ocl.uml.IteratorExp;
import org.eclipse.ocl.uml.LoopExp;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.uml.OperationCallExp;
import org.eclipse.ocl.uml.PropertyCallExp;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.ocl.uml.AbstractOclContext;

@SuppressWarnings("rawtypes")
public class PropCallCreator{
	private OJClass myClass = null;
	ExpGeneratorHelper expGeneratorHelper;
	private OJUtil ojUtil;
	private AbstractOclContext context;
	public PropCallCreator(ExpGeneratorHelper e,OJClass myClass,AbstractOclContext context){
		super();
		expGeneratorHelper = e;
		this.myClass = myClass;
		this.ojUtil = e.ojUtil;
		this.context = context;
	}
	public StringBuilder makeExpression(CallExp in,StringBuilder source,boolean isStatic,List<OJParameter> params){
		StringBuilder thisNode = new StringBuilder();
		StringBuilder newSource = privMakeExpNode(in, source, isStatic, params);
		thisNode.append(newSource);
		return thisNode;
	}
	/**
	 * Is called only when in is a reference to a class property.
	 */
	public StringBuilder makeExpressionNode(CallExp in,boolean isStatic,List<OJParameter> params){
		return privMakeExpNode(in, null, isStatic, params);
	}
	private StringBuilder privMakeExpNode(CallExp in,StringBuilder source,boolean isStatic,List<OJParameter> params){
		StringBuilder newSource = new StringBuilder();
		if(in instanceof LoopExp){
			if(in instanceof IterateExp){
				LoopExpCreator maker = new LoopExpCreator(expGeneratorHelper, myClass, context);
				newSource.append(maker.iterateExp((IterateExp) in, source, isStatic, params));
			}else if(in instanceof IteratorExp){
				LoopExpCreator maker = new LoopExpCreator(expGeneratorHelper, myClass, context);
				newSource.append(maker.iteratorExp((IteratorExp) in, source, isStatic, params));
			}
		}else if(in instanceof FeatureCallExp){
			if(in instanceof PropertyCallExp){
				newSource.append(attributeCallExp((PropertyCallExp) in, source, params));
			}else if(in instanceof AssociationClassCallExp){
				newSource.append(associationClassCallExp((AssociationClassCallExp) in, source));
			}else if(in instanceof OperationCallExp){
				OperationCallCreator maker = new OperationCallCreator(expGeneratorHelper, myClass, context);
				newSource.append(maker.operationCallExp((OperationCallExp) in, source, isStatic, params));
			}
		}
		return newSource;
	}
	private String attributeCallExp(PropertyCallExp exp,StringBuilder source,List<OJParameter> params){
		Property prop = exp.getReferredProperty();
		PropertyMap mapper = ojUtil.buildStructuralFeatureMap(prop);
		String getterName = mapper.getter();
		if(prop.isStatic() || source == null){
			if(prop.getName().equals("values") && prop.getOwner() == null && mapper.getBaseType() instanceof Enumeration){
				myClass.addToImports(mapper.javaBaseTypePath());
				return mapper.javaBaseType() + "." + getterName + "()";
			}else{
				ClassifierMap classmap = ojUtil.buildClassifierMap(EmfPropertyUtil.getOwningClassifier(prop));
				String classname = classmap.javaType();
				myClass.addToImports(classmap.javaTypePath());
				return classname + "." + getterName + "()";
			}
		}else{
			String qArgs = "";
			if(exp.getQualifier().size() > 0){
				ExpressionCreator myExpMaker = new ExpressionCreator(ojUtil, myClass, context);
				StringBuilder args = new StringBuilder();
				Iterator it = exp.getQualifier().iterator();
				while(it.hasNext()){
					OCLExpression arg = (OCLExpression) it.next();
					args.append(myExpMaker.makeExpression(arg, false, params));
					if(it.hasNext()){
						args.append(",");
					}
				}
				qArgs = args.toString();
			}
			String sourceStr = StringHelpers.addBrackets(source.toString());
			if(!(exp.getSource().getType() instanceof CollectionType) &&  EmfPropertyCallHelper.resultsInMany((OCLExpression) exp.getSource())){
//				Property sourceProp = ((PropertyCallExp) exp.getSource()).getReferredProperty();
//				PropertyMap sourceMap = ojUtil.buildStructuralFeatureMap(sourceProp);
				OJPathName bag = StdlibMap.javaBagType.getCopy();
				bag.addToElementTypes(mapper.javaBaseTypePath());
				OJAnnotatedOperation oper = new OJAnnotatedOperation("collect" + myClass.getUniqueNumber(), bag);
				myClass.addToOperations(oper);
				oper.setVisibility(OJVisibilityKind.PRIVATE);
				OJPathName paramType = StdlibMap.javaBagType.getCopy();
				OJPathName sourceType = ojUtil.classifierPathname(exp.getSource().getType());
				paramType.addToElementTypes(sourceType);
				oper.addParam("source", paramType);
				oper.initializeResultVariable("new " + StdlibMap.javaBagImplType.getCopy().getLast() + "<" + mapper.javaBaseType() + ">()");
				OJForStatement foreach = new OJForStatement("el", sourceType, "source");
				oper.getBody().addToStatements(foreach);
				String addStatement = null;
				if(prop.getQualifiers().size()>0 &&exp.getQualifier().isEmpty() ||prop.isMultivalued()){
					addStatement = "result.addAll(el." + mapper.getter() + "(" + qArgs + "))";
				}else{
					addStatement = "result.add(el." + mapper.getter() + "(" + qArgs + "))";
				}
				foreach.getBody().addToStatements(new OJIfStatement("el."+mapper.getter() + "(" + qArgs + ")!=null", addStatement));
				return oper.getName() + "(" + source + ")";
			}
			return sourceStr + "." + mapper.getter() + "(" + qArgs + ")";
		}
	}
	private String associationClassCallExp(AssociationClassCallExp exp,StringBuilder source){
		Property navSource = exp.getNavigationSource();
		NavToAssocClassMap mapper = new NavToAssocClassMap(navSource);
		String sourceStr = StringHelpers.addBrackets(source.toString());
		return sourceStr + "." + mapper.getter() + "()";
	}
}
