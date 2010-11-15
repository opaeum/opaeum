package net.sf.nakeduml.metamodel.core.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.metamodel.activities.INakedInputPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.VisibilityKind;
import nl.klasse.octopus.oclengine.IOclContext;

public class NakedOperationImpl extends NakedNameSpaceImpl implements INakedOperation{
	private static final long serialVersionUID = 6979135768898034683L;
	public static final String META_CLASS = "operation";
	@Override
	public void setVisibility(VisibilityKind visibility){
		this.visibility = visibility;
	}
	private VisibilityKind visibility;
	private boolean isQuery;
	private Set<INakedBehavior> method = new HashSet<INakedBehavior>();
	private List<INakedParameter> argumentParameters = new ArrayList<INakedParameter>();
	private List<INakedParameter> resultParameters = new ArrayList<INakedParameter>();
	private List<INakedParameter> exceptionParameters = new ArrayList<INakedParameter>();
	private Collection<IOclContext> preConditions = new ArrayList<IOclContext>();
	private Collection<IOclContext> postConditions = new ArrayList<IOclContext>();
	private INakedParameter returnParameter;
	private boolean userResponsibility;
	private INakedConstraint bodyCondition;
	private boolean hasClassScope;
	private boolean isAbstract;
	private boolean isOclDef;
	public static IClassifier VOID_TYPE;
	public boolean isStatic(){
		return hasClassScope;
	}
	public NakedOperationImpl(){
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
			//Interfaces and DataTypes
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
	public boolean isProcess(){
		for(INakedBehavior b:getMethods()){
			if(b.isProcess()){
				return true;
			}
		}
		return false;
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
	// Method would usually carry the state. only emulate class if there is no
	// method
	public boolean shouldEmulateClass(){
		return (hasMultipleConcurrentResults() || isUserResponsibility()) && getMethods().isEmpty();
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
	public void setIsUserResponsibility(boolean b){
		this.userResponsibility = b;
	}
	// precondition: the various indices of a parameter have been set
	@Override
	public void addOwnedElement(INakedElement element){
		super.addOwnedElement(element);
		if(element instanceof INakedParameter){
			INakedParameter p = (INakedParameter) element;
			if(p.isReturn()){
				this.returnParameter = p;
			}
			ParameterUtil.addParameterTolist(p, p.getArgumentIndex(), this.argumentParameters);
			ParameterUtil.addParameterTolist(p, p.getExceptionIndex(), this.exceptionParameters);
			ParameterUtil.addParameterTolist(p, p.getResultIndex(), this.resultParameters);
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
	public boolean hasReturnParameter(){
		return getReturnParameter() != null;
	}
	public boolean isUserResponsibility(){
		//All operations on an interface representing a user are userResponsibilities
		return this.userResponsibility || getOwner() instanceof INakedInterface && ((INakedInterface)getOwner()).representsUser();
	}
	@Override
	public VisibilityKind getVisibility(){
		return visibility;
	}
	public void setQuery(boolean b){
		this.isQuery = b;
	}
	public Collection<IOclContext> getPostConditions(){
		return postConditions;
	}
	public void setPostConditions(Collection<IOclContext> posConditions){
		this.postConditions = posConditions;
	}
	public Collection<IOclContext> getPreConditions(){
		return preConditions;
	}
	public void setPreConditions(Collection<IOclContext> preConditions){
		this.preConditions = preConditions;
	}
	public IOclContext getBodyExpression(){
		return this.getBodyCondition().getSpecification().getOclValue();
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
		return ParameterUtil.signature(this);
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
	public void addPostCondition(IOclContext pc){
		this.getPostConditions().add(pc);
	}
	public void addPreCondition(IOclContext pc){
		this.getPreConditions().add(pc);
	}
	public void setIsOclDef(boolean b){
		this.isOclDef = b;
	}
	public INakedConstraint getBodyCondition(){
		return bodyCondition;
	}
	public void setBodyCondition(INakedConstraint bodyCondition){
		this.bodyCondition = bodyCondition;
		super.addOwnedElement(bodyCondition);
	}
	@Override
	protected boolean isNamedMember(INakedElement e){
		return super.isNamedMember(e) || e instanceof INakedTypedElement;
	}
}
