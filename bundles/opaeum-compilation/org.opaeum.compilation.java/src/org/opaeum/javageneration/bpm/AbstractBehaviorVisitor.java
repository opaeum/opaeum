package org.opaeum.javageneration.bpm;

import java.util.Collection;
import java.util.Date;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.ocl.uml.CollectionType;
import org.eclipse.uml2.uml.DurationObservation;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Observation;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.TimeObservation;
import org.opaeum.eclipse.EmfTimeUtil;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.emf.workspace.EmfWorkspace;
import org.opaeum.feature.OpaeumConfig;
import org.opaeum.java.metamodel.OJAnnonymousInnerClass;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJWorkspace;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.AbstractJavaProducingVisitor;
import org.opaeum.javageneration.basicjava.JavaMetaInfoMapGenerator;
import org.opaeum.javageneration.hibernate.HibernateUtil;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;
import org.opaeum.metamodel.core.internal.TagNames;
import org.opaeum.metamodel.name.SingularNameWrapper;
import org.opaeum.ocl.uml.OpaqueExpressionContext;
import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.textmetamodel.TextWorkspace;

/**
 * Provides the behavior related logic common to statemachines and activities:
 * 
 * 
 */
public abstract class AbstractBehaviorVisitor extends AbstractJavaProducingVisitor{
	protected TaskUtil taskUtil;
	@Override
	public void initialize(OJWorkspace pac,OpaeumConfig config,TextWorkspace textWorkspace,EmfWorkspace workspace,OJUtil ojUtil){
		super.initialize(pac, config, textWorkspace, workspace, ojUtil);
		taskUtil = new TaskUtil(ojUtil);
	}
	public void addReturnInfo(OJAnnotatedClass ojOperationClass){
		OJAnnotatedField returnInfo = new OJAnnotatedField("returnInfo", HibernateUtil.RETURN_INFO);
		HibernateUtil.overrideInterfaceValueAtributes(returnInfo, new SingularNameWrapper("return_info", null));
		ojOperationClass.addToFields(returnInfo);
		returnInfo.setInitExp("new ReturnInfo()");
		OJAnnotatedOperation setReturnInfo = new OJAnnotatedOperation("setReturnInfo");
		ojOperationClass.addToOperations(setReturnInfo);
		setReturnInfo.addParam("token", BpmUtil.ITOKEN);
		setReturnInfo.getBody().addToStatements(new OJIfStatement("this.returnInfo==null", "this.returnInfo=new ReturnInfo()"));
		setReturnInfo.getBody().addToStatements("this.returnInfo.setValue(token)");
		OJPathName itoken = BpmUtil.ITOKEN.getCopy();
		itoken.addToElementTypes(new OJPathName("?"));
		OJAnnotatedOperation getReturnInfo = new OJAnnotatedOperation("getReturnInfo", itoken);
		ojOperationClass.addToOperations(getReturnInfo);
		getReturnInfo.initializeResultVariable("null");
		getReturnInfo.getBody().addToStatements(new OJIfStatement("this.returnInfo==null", "this.returnInfo=new ReturnInfo()"));
		getReturnInfo.getBody().addToStatements("result=this.returnInfo.getValue(persistence)");
	}
	protected OJAnnotatedOperation implementExecute(OJAnnotatedClass ojClass,boolean isPersistent,boolean hasSuperClass){
		OJAnnotatedOperation execute = new OJAnnotatedOperation("execute");
		ojClass.addToOperations(execute);
		if(hasSuperClass){
			execute.getBody().addToStatements("super.execute()");
		}else{
			execute.getBody().addToStatements("evaluatePreconditions()");
			OJUtil.addFailedConstraints(execute);
			// add executedOn property for sorting purposes ONLY - all important dates should be observed manually
			OJAnnotatedField f = ojUtil.addPersistentProperty(ojClass, "executedOn", new OJPathName(Date.class.getName()), true);
			if(isPersistent){
				OJAnnotationValue column = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
				column.putAttribute(new OJAnnotationAttributeValue("name", "executed_on"));
				f.putAnnotation(column);
				OJAnnotationValue temporal = new OJAnnotationValue(new OJPathName("javax.persistence.Temporal"), new OJEnumValue(new OJPathName(
						"javax.persistence.TemporalType"), "TIMESTAMP"));
				f.putAnnotation(temporal);
			}
			execute.getBody().addToStatements("setExecutedOn(new Date())");
		}
		return execute;
	}
	protected void implementIObserver(OJAnnotatedClass context,Collection<TimeObservation> tos,Collection<DurationObservation> dos){
		if(tos.size() + dos.size() > 0){
			OJAnnotatedOperation register = new OJAnnotatedOperation("registerObservations");
			register.addParam("elements", mapOfExecutionElements());
			context.addToOperations(register);
			for(DurationObservation dob:dos){
				if(dob.getEvents().size() > 0){
					createIObserver(register, dob, dob.getEvents().get(0));
				}
				if(dob.getEvents().size() > 1){
					if(dob.getEvents().get(0) == dob.getEvents().get(1)){
						//IGnore, already done 
					}else{
						createIObserver(register, dob, dob.getEvents().get(1));
					}
				}
			}
			for(TimeObservation to:tos){
				createIObserver(register, to, to.getEvent());
			}
			if(dos.size() > 0){
				context.addToImports(ojUtil.classifierPathname(ojUtil.getLibrary().getBusinessCalendar()));
				context.addToImports(ojUtil.classifierPathname(ojUtil.getLibrary().getDurationType()));
				context.addToImports(ojUtil.classifierPathname(ojUtil.getLibrary().getCumulativeDurationType()));
				context.addToImports(new OJPathName(BusinessTimeUnit.class.getName()));
			}
			context.addToImplementedInterfaces(BpmUtil.IOBSERVER);
		}
	}
	protected void createIObserver(OJAnnotatedOperation register,Observation dob,NamedElement vertex){
		OJPathName pn = ojUtil.classifierPathname(vertex);
		OJIfStatement ifContains = new OJIfStatement("elements.containsKey(" + pn.toJavaString() + ".ID)");
		register.getBody().addToStatements(ifContains);
		OJAnnonymousInnerClass iobservation = new OJAnnonymousInnerClass((OJAnnotatedClass) register.getOwner(), "observation",
				BpmUtil.IOBSERVATION);
		ifContains.getThenPart().addToLocals(iobservation);
		ifContains.getThenPart().addToStatements("elements.get(" + pn.toJavaString() + ".ID).registerObservation(observation)");
		OJAnnotatedOperation onEntry = new OJAnnotatedOperation("onEntry");
		iobservation.getClassDeclaration().addToOperations(onEntry);
		implementObservationCallback(dob, vertex, onEntry, true);
		OJAnnotatedOperation onExit = new OJAnnotatedOperation("onExit");
		OJPathName executionElementPathName = BpmUtil.IEXECUTION_ELEMENT.getCopy();
		executionElementPathName.addToElementTypes(new OJPathName("?"));
		onExit.addParam("element", executionElementPathName);
		onEntry.addParam("element", executionElementPathName);
		iobservation.getClassDeclaration().addToOperations(onExit);
		implementObservationCallback(dob, vertex, onExit, false);
	}
	protected void implementObservationCallback(Observation ob,NamedElement event,OJAnnotatedOperation oper,boolean isOnEntryMethod){
		PropertyMap map = ojUtil.buildStructuralFeatureMap(ob);
		if(ob instanceof DurationObservation){
			DurationObservation dob = (DurationObservation) ob;
			boolean startEventFiresOnEntry = dob.getFirstEvents().size() > 0 ? dob.getFirstEvents().get(0) : true;
			boolean endEventFiresOnEntry = dob.getFirstEvents().size() > 1 ? dob.getFirstEvents().get(1) : true;
			boolean shouldFireFromEvent = dob.getEvents().size() > 0 && dob.getEvents().get(0) == event
					&& startEventFiresOnEntry == isOnEntryMethod;
			boolean shouldFireToEvent = dob.getEvents().size() > 1 && dob.getEvents().get(1) == event && endEventFiresOnEntry == isOnEntryMethod;
			if(EmfTimeUtil.isDurationBasedCostObservation(dob)){
				if(shouldFireFromEvent){
					oper.getBody().addToStatements(map.fieldname() + "FromEventOccurred(" + startEventFiresOnEntry + ")");
				}
				if(shouldFireToEvent){
					OJPathName setOfResources = new OJPathName("java.util.Set");
					setOfResources.addToElementTypes(ojUtil.classifierPathname(getLibrary().getTimedResource()));
					OJAnnotatedField resources = new OJAnnotatedField("resources", setOfResources);
					oper.getBody().addToLocals(resources);
					resources.setInitExp("new HashSet<ITimedResource>()");
					oper.getOwner().addToImports(new OJPathName("java.util.HashSet"));
					Collection<OpaqueExpressionContext> exps = ojUtil.getLibrary().getArtificialExpressions(dob, TagNames.RESOURCES_PAID_FOR);
					if(exps.isEmpty()){
						oper.getBody().addToStatements("resources.add((ITimedResource)getTaskRequest().getOwner())");
					}
					for(OpaqueExpressionContext bctu:exps){
						if(!bctu.hasErrors()){
							String expression = valueSpecificationUtil.expressOcl(bctu, oper, null);
							if(bctu.getExpression().getType() instanceof CollectionType){
								oper.getBody().addToStatements("resources.addAll(" + expression + ")");
							}else{
								oper.getBody().addToStatements("resources.add(" + expression + ")");
							}
						}
					}
					oper.getBody().addToStatements(map.fieldname() + "ToEventOccurred(resources," + endEventFiresOnEntry + ")");
				}
			}else{
				Stereotype st = StereotypesHelper.getStereotype(dob, StereotypeNames.BUSINESS_DURATION_OBSERVATION);
				EnumerationLiteral l = st == null ? null : (EnumerationLiteral) dob.getValue(st, TagNames.TIME_UNIT);
				BusinessTimeUnit btu = BusinessTimeUnit.BUSINESSDAY;
				if(l != null){
					btu = BusinessTimeUnit.valueOf(OJUtil.toJavaLiteral(l));
				}
				if(shouldFireFromEvent){
					String newValue = EmfTimeUtil.isCumulative(dob) ? "(new CumulativeDuration(" : "(new Duration(";
					OJIfStatement ifNull = new OJIfStatement(map.getter() + "()==null", map.setter() + newValue + "BusinessTimeUnit." + btu.name()
							+ "))");
					oper.getBody().addToStatements(ifNull);
					oper.getBody().addToStatements(map.getter() + "().fromEventOccurred(" + startEventFiresOnEntry + ")");
				}
				if(shouldFireToEvent){
					String expression = "BusinessCalendar.getInstance()";
					OpaqueExpressionContext bctu = ojUtil.getLibrary().getArtificationExpression(dob, TagNames.BUSINESS_CALENDAR_TO_USE);
					if(bctu != null && !bctu.hasErrors()){
						expression = valueSpecificationUtil.expressOcl(bctu, oper, null);
					}
					oper.getBody().addToStatements(
							map.getter() + "().toEventOccurred(" + expression + ",BusinessTimeUnit." + btu.name() + "," + endEventFiresOnEntry + ")");
				}
			}
		}else{
			TimeObservation tob = (TimeObservation) ob;
			if(tob.getEvent() != null && tob.isFirstEvent() == isOnEntryMethod){
				Stereotype st = StereotypesHelper.getStereotype(tob, StereotypeNames.QUANTITY_BASED_COST_OBSERVATION);
				if(st == null){
					oper.getBody().addToStatements(map.setter() + "(new Date())");
				}else{
					OJPathName setOfResources = new OJPathName("java.util.Set");
					setOfResources.addToElementTypes(ojUtil.classifierPathname(getLibrary().getQuantifiedResource()));
					OpaqueExpressionContext resourceExpression = getLibrary().getArtificationExpression(tob, TagNames.RESOURCES_PAID_FOR);
					OpaqueExpressionContext quantityExpression = getLibrary().getArtificationExpression(tob, TagNames.QUANTITY_EXPRESSION);
					if(resourceExpression != null && !resourceExpression.hasErrors()){
						String quantity;
						if(quantityExpression == null || quantityExpression.hasErrors()){
							quantity = "1d";
						}else{
							quantity = valueSpecificationUtil.expressOcl(quantityExpression, oper, null);
						}
						String resource = valueSpecificationUtil.expressOcl(resourceExpression, oper, null);
						OJIfStatement ifNull = new OJIfStatement(map.getter() + "()==null", map.setter() + "(new QuantityBasedCost())");
						oper.getBody().addToStatements(ifNull);
						oper.getBody().addToStatements(map.getter() + "().eventOccurred(" + resource + "," + isOnEntryMethod + "," + quantity + ")");
					}
				}
			}
		}
	}
	protected OJPathName mapOfExecutionElements(){
		OJPathName map = new OJPathName("java.util.Map");
		map.addToElementTypes(new OJPathName("String"));
		map.addToElementTypes(BpmUtil.IEXECUTION_ELEMENT);
		return map;
	}
}
