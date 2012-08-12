package nl.klasse.octopus.codegen.umlToJava.expgenerators.creators;

import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.codegen.umlToJava.common.ExpGeneratorHelper;
import nl.klasse.octopus.codegen.umlToJava.maps.ClassifierMap;
import nl.klasse.octopus.codegen.umlToJava.maps.NavToAssocClassMap;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.tools.common.StringHelpers;

import org.eclipse.ocl.uml.AssociationClassCallExp;
import org.eclipse.ocl.uml.CallExp;
import org.eclipse.ocl.uml.FeatureCallExp;
import org.eclipse.ocl.uml.IterateExp;
import org.eclipse.ocl.uml.IteratorExp;
import org.eclipse.ocl.uml.LoopExp;
import org.eclipse.ocl.uml.OCLExpression;
import org.eclipse.ocl.uml.OperationCallExp;
import org.eclipse.ocl.uml.PropertyCallExp;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Property;
import org.opaeum.java.metamodel.OJClass;
import org.opaeum.java.metamodel.OJParameter;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.ocl.uml.AbstractOclContext;

@SuppressWarnings("rawtypes")
public class PropCallCreator{
	private OJClass myClass = null;
	ExpGeneratorHelper expGeneratorHelper;
	private OJUtil ojUtil;
	private AbstractOclContext context;
	public PropCallCreator(ExpGeneratorHelper e,OJClass myClass, AbstractOclContext context){
		super();
		expGeneratorHelper = e;
		this.myClass = myClass;
		this.ojUtil = e.ojUtil;
		this.context=context;
	}
	public StringBuffer makeExpression(CallExp in,StringBuffer source,boolean isStatic,List<OJParameter> params){
		StringBuffer thisNode = new StringBuffer();
		StringBuffer newSource = privMakeExpNode(in, source, isStatic, params);
		thisNode.append(newSource);
		return thisNode;
	}
	/**
	 * Is called only when in is a reference to a class property.
	 */
	public StringBuffer makeExpressionNode(CallExp in,boolean isStatic,List<OJParameter> params){
		return privMakeExpNode(in, null, isStatic, params);
	}
	private StringBuffer privMakeExpNode(CallExp in,StringBuffer source,boolean isStatic,List<OJParameter> params){
		StringBuffer newSource = new StringBuffer();
		if(in instanceof LoopExp){
			if(in instanceof IterateExp){
				LoopExpCreator maker = new LoopExpCreator(expGeneratorHelper, myClass,context);
				newSource.append(maker.iterateExp((IterateExp) in, source, isStatic, params));
			}else if(in instanceof IteratorExp){
				LoopExpCreator maker = new LoopExpCreator(expGeneratorHelper, myClass,context);
				newSource.append(maker.iteratorExp((IteratorExp) in, source, isStatic, params));
			}
		}else if(in instanceof FeatureCallExp){
			if(in instanceof PropertyCallExp){
				newSource.append(attributeCallExp((PropertyCallExp) in, source,params));
			}else if(in instanceof AssociationClassCallExp){
				newSource.append(associationClassCallExp((AssociationClassCallExp) in, source));
			}else if(in instanceof OperationCallExp){
				OperationCallCreator maker = new OperationCallCreator(expGeneratorHelper, myClass,context);
				newSource.append(maker.operationCallExp((OperationCallExp) in, source, isStatic, params));
			}
		}
		return newSource;
	}
	private String attributeCallExp(PropertyCallExp exp,StringBuffer source, List<OJParameter> params){
		StructuralFeatureMap mapper = ojUtil.buildStructuralFeatureMap(exp.getReferredProperty());
		String getterName = mapper.getter();
		if(exp.getReferredProperty().isStatic() || source == null){
			ClassifierMap classmap = ojUtil.buildClassifierMap((Classifier) exp.getReferredProperty().getOwner());
			String classname = classmap.javaType();
			myClass.addToImports(classmap.javaTypePath());
			return classname + "." + getterName + "()";
		}else{
			String sourceStr = StringHelpers.addBrackets(source.toString());
			if(exp.getQualifier().size() > 0){
				ExpressionCreator myExpMaker = new ExpressionCreator(ojUtil, myClass,context);

				StringBuilder args = new StringBuilder();
				Iterator it = exp.getQualifier().iterator();
				while(it.hasNext()){
					OCLExpression arg = (OCLExpression) it.next();
					args.append(myExpMaker.makeExpression(arg, false, params));
					if(it.hasNext()){
						args.append(",");
					}
				}
				
				return sourceStr + "." + getterName + "("+args.toString()+")";
			}else{
				return sourceStr + "." + getterName + "()";
			}
		}
	}
	private String associationClassCallExp(AssociationClassCallExp exp,StringBuffer source){
		Property navSource = exp.getNavigationSource();
		NavToAssocClassMap mapper = new NavToAssocClassMap(navSource);
		String sourceStr = StringHelpers.addBrackets(source.toString());
		return sourceStr + "." + mapper.getter() + "()";
	}
}
