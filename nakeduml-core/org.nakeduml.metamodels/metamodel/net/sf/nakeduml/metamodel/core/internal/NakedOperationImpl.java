package net.sf.nakeduml.metamodel.core.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedMultiplicityElement;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.internal.emulated.OperationMessageStructureImpl;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.VisibilityKind;
import nl.klasse.octopus.oclengine.IOclContext;

public class NakedOperationImpl extends NakedNameSpaceImpl implements INakedOperation{
	private static final long serialVersionUID = 6979135768898034683L;
	public static final String META_CLASS = "operation";
	private VisibilityKind visibility;
	private boolean isQuery;
	private Set<INakedBehavior> method = new HashSet<INakedBehavior>();
	private List<INakedParameter> argumentParameters = new ArrayList<INakedParameter>();
	private List<INakedParameter> resultParameters = new ArrayList<INakedParameter>();
	private List<INakedParameter> exceptionParameters = new ArrayList<INakedParameter>();
	private Collection<INakedConstraint> preConditions = new HashSet<INakedConstraint>();
	private Collection<INakedConstraint> postConditions = new HashSet<INakedConstraint>();
	private Collection<INakedClassifier> raisedExceptions = new HashSet<INakedClassifier>();
	private Collection<INakedOperation> redefinedOperations= new HashSet<INakedOperation>();
	private INakedParameter returnParameter;
	private INakedConstraint bodyCondition;
	private boolean hasClassScope;
	private boolean isAbstract;
	private boolean isOclDef;
	private boolean longRunning;
	public static IClassifier VOID_TYPE;
	private OperationMessageStructureImpl messageStructure;
	public boolean isStatic(){
		return hasClassScope;
	}
	public NakedOperationImpl(){
	}
	@Override
	public void setVisibility(VisibilityKind visibility){
		this.visibility = visibility;
	}
	public List<INakedParameter> getResultParameters(){
		return this.resultParameters;
	}
	public List<INakedParameter> getArgumentParameters(){
		return this.argumentParameters;
	}
	public List<INakedParameter> getExceptionParameters(){
		return this.exceptionParameters;
	}
	public INakedBehavioredClassifier getContext(){
		if(getOwner() instanceof INakedBehavioredClassifier){
			return (INakedBehavioredClassifier) getOwner();
		}else{
			// Interfaces and DataTypes
			return null;
		}
	}
	public IClassifier getReturnType(){
		if(getReturnParameter() == null){
			return VOID_TYPE;
		}else{
			return getReturnParameter().getType();
		}
	}
	public boolean hasMultipleConcurrentResults(){
		int i = 0;
		for(INakedParameter p:this.resultParameters){
			if(!p.isException()){
				i++;
			}
		}
		return i > 1;
	}
	public boolean shouldEmulateClass(){
		return hasMultipleConcurrentResults() || isLongRunning();
	}
	public List<INakedParameter> getOwnedParameters(){
		List<INakedParameter> results = new ArrayList<INakedParameter>(getArgumentParameters());
		for(INakedParameter p:getResultParameters()){
			if(!getArgumentParameters().contains(p)){
				results.add(p);
			}
		}
		return results;
	}
	// precondition: the various indices of a parameter have been set
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedConstraint && ((INakedConstraint) element).getSpecification()!=null){
			INakedConstraint cnstr = (INakedConstraint) element;
			IOclContext oc=cnstr.getSpecification().getOclValue();
			if(oc.getType().equals(OclUsageType.PRE)){
				preConditions.remove(cnstr);
				preConditions.add(cnstr);
			}else if(oc.getType().equals(OclUsageType.POST)){
				postConditions.remove(cnstr);
				postConditions.add(cnstr);
			}else{
				bodyCondition=cnstr;
			}
		}
	}
	public INakedParameter getReturnParameter(){
		return this.returnParameter;
	}
	public INakedClassifier getOwner(){
		if(getOwnerElement() instanceof INakedClassifier){
			return (INakedClassifier) getOwnerElement();
		}else{
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<IParameter> getParameters(){
		return (List) getArgumentParameters();
	}
	public List<IClassifier> getParamTypes(){
		return ParameterUtil.parameterTypes(getArgumentParameters());
	}
	public Set<INakedBehavior> getMethods(){
		return this.method;
	}
	public void addMethod(INakedBehavior method){
		this.method.add(method);
		if(method.getSpecification() != this){
			method.setSpecification(this);
		}
	}
	@Override
	public String getMetaClass(){
		return "operation";
	}
	public boolean isQuery(){
		return this.isQuery;
	}
	@Override
	public boolean hasReturnParameter(){
		return getReturnParameter() != null;
	}
	@Override
	public boolean isLongRunning(){
		return this.longRunning;
	}
	@Override
	public void setIsLongRunning(boolean b){
		this.longRunning = b;
	}
	@Override
	public VisibilityKind getVisibility(){
		return visibility;
	}
	public void setQuery(boolean b){
		this.isQuery = b;
	}
	public Collection<INakedConstraint> getPostConditions(){
		return postConditions;
	}
	public void setPostConditions(Collection<INakedConstraint> posConditions){
		this.postConditions = posConditions;
	}
	public Collection<INakedConstraint> getPreConditions(){
		return preConditions;
	}
	public void setPreConditions(Collection<INakedConstraint> preConditions){
		this.preConditions = preConditions;
	}
	public void setStatic(boolean b){
		this.hasClassScope = b;
	}
	public boolean hasClassScope(){
		return hasClassScope;
	}
	public boolean isAbstract(){
		return this.isAbstract;
	}
	public String getSignature(){
		return ParameterUtil.fullSignature(this);
	}
	/** like a+b */
	public boolean isInfix(){
		return false;
	}
	/** like a++ */
	public boolean isPrefix(){
		return false;
	}
	public boolean isOclDef(){
		return this.isOclDef;
	}
	public void setIsOclDef(boolean b){
		this.isOclDef = b;
	}
	public INakedConstraint getBodyCondition(){
		return bodyCondition;
	}
	@Override
	protected boolean isNamedMember(INakedElement e){
		return super.isNamedMember(e) || e instanceof INakedMultiplicityElement;
	}
	@Override
	public INakedMessageStructure getMessageStructure(){
		return messageStructure;
	}
	public void initMessageStructure(){
		if(this.messageStructure == null){
			this.messageStructure = new OperationMessageStructureImpl(this);
		}
	}
	@Override
	public void recalculateParameterPositions(){
		this.argumentParameters.clear();
		this.exceptionParameters.clear();
		this.resultParameters.clear();
		for(INakedElement e:getOwnedElements()){
			if(e instanceof INakedParameter){
				INakedParameter p = (INakedParameter) e;
				if(p.isReturn()){
					this.returnParameter = p;
				}
				ParameterUtil.addParameterTolist(p, p.getArgumentIndex(), this.argumentParameters);
				ParameterUtil.addParameterTolist(p, p.getExceptionIndex(), this.exceptionParameters);
				ParameterUtil.addParameterTolist(p, p.getResultIndex(), this.resultParameters);
			}
		}
	}
	@Override
	public void addStereotype(INakedInstanceSpecification stereotype){
		super.addStereotype(stereotype);
		if(stereotype.hasValueForFeature("isLongRunning")){
			setIsLongRunning(Boolean.TRUE.equals(stereotype.getFirstValueFor("isLongRunning")));
		}
	}
	public Collection<INakedClassifier> getRaisedExceptions(){
		return raisedExceptions;
	}
	public void setRaisedExceptions(Collection<INakedClassifier> raisedExceptions){
		this.raisedExceptions = raisedExceptions;
	}
	@Override
	public Collection<INakedOperation> getRedefinedOperations(){
		return redefinedOperations;
	}
	public void setRedefinedOperations(Collection<INakedOperation> redefinedOperations){
		this.redefinedOperations = redefinedOperations;
	}
}
