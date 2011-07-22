package net.sf.nakeduml.metamodel.commonbehaviors.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.metamodel.activities.ActivityKind;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavioredClassifier;
import net.sf.nakeduml.metamodel.core.CodeGenerationStrategy;
import net.sf.nakeduml.metamodel.core.INakedConstraint;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedMultiplicityElement;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.ParameterUtil;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.profiles.INakedProfile;
import net.sf.nakeduml.metamodel.statemachines.INakedState;
import net.sf.nakeduml.metamodel.statemachines.INakedTransition;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.oclengine.IOclContext;

/**
 * Defines a common superclass for elements that: 1. Can be called as a
 * method/operation/behavior 2. Has pre and post conditions expressed in OCL 3.
 * Could have a body expressed in OCL 4. Has Parameters and exceptions
 * 
 * @author ampie
 * @s
 * 
 */
public abstract class NakedBehaviorImpl extends NakedBehavioredClassifierImpl implements INakedBehavior {
	private static final long serialVersionUID = 2245169607437688948L;
	private Collection<IOclContext> preConditions = new ArrayList<IOclContext>();
	private Collection<IOclContext> postConditions = new ArrayList<IOclContext>();
	private INakedParameter returnParameter;
	private INakedOperation specification;
	private List<INakedParameter> argumentParameters = new ArrayList<INakedParameter>();
	private List<INakedParameter> resultParameters = new ArrayList<INakedParameter>();
	private List<INakedParameter> exceptionParameters = new ArrayList<INakedParameter>();

	public NakedBehaviorImpl() {
		super();
	}

	public List<IParameter> getParameters() {
		return (List) getArgumentParameters();
	}

	public boolean isPersistent() {
		return isProcess();
	}

	public INakedConstraint getBodyCondition() {
		return null;
	}

	@Override
	public List<INakedProperty> getEffectiveAttributes() {
		List<INakedProperty> result = super.getEffectiveAttributes();
		return result;
	}

	public List<INakedParameter> getOwnedParameters() {
		List<INakedParameter> results = new ArrayList<INakedParameter>(getArgumentParameters());
		for (INakedParameter p : getResultParameters()) {
			if (!getArgumentParameters().contains(p)) {
				results.add(p);
			}
		}
		return results;
	}

	public final INakedOperation getSpecification() {
		return this.specification;
	}

	public void setSpecification(INakedOperation specification) {
		this.specification = specification;
		if (specification != null && !specification.getMethods().contains(this)) {
			specification.addMethod(this);
		}
	}

	public boolean hasMultipleConcurrentResults() {
		if (getOwnedAttributes().size() > 0) {
			return true;
		} else {
			int i = 0;
			for (INakedParameter p : this.resultParameters) {
				if (!p.isException()) {
					i++;
				}
			}
			return i > 1;
		}
	}

	public List<INakedParameter> getResultParameters() {
		return this.resultParameters;
	}

	public List<INakedParameter> getArgumentParameters() {
		return this.argumentParameters;
	}

	public List<INakedParameter> getExceptionParameters() {
		return this.exceptionParameters;
	}

	public void addPostCondition(IOclContext post) {
		this.postConditions.add(post);
	}

	public void addPreCondition(IOclContext pre) {
		this.preConditions.add(pre);
	}

	public IClassifier getReturnType() {
		if (getReturnParameter() == null) {
			return null;
		} else {
			return getReturnParameter().getType();
		}
	}

	public Collection<IOclContext> getPostConditions() {
		return this.postConditions;
	}

	public void setPostConditions(Collection<IOclContext> postConditions) {
		this.postConditions = postConditions;
	}

	public Collection<IOclContext> getPreConditions() {
		return this.preConditions;
	}

	public void setPreConditions(Collection<IOclContext> preConditions) {
		this.preConditions = preConditions;
	}

	@Override
	public void addOwnedElement(INakedElement element) {
		super.addOwnedElement(element);
		if (element instanceof INakedParameter) {
			INakedParameter p = (INakedParameter) element;
			if (p.isReturn()) {
				this.returnParameter = p;
			}
			ParameterUtil.addParameterTolist(p, p.getArgumentIndex(), this.argumentParameters);
			ParameterUtil.addParameterTolist(p, p.getResultIndex(), this.resultParameters);
			ParameterUtil.addParameterTolist(p, p.getExceptionIndex(), this.exceptionParameters);
		}
	}

	@Override
	public CodeGenerationStrategy getCodeGenerationStrategy() {
		if (super.getCodeGenerationStrategy() == null) {
			if (getOwnerElement() instanceof INakedTransition) {
				return ((INakedTransition) getOwnerElement()).getStateMachine().getCodeGenerationStrategy();
			} else if (getOwnerElement() instanceof INakedState) {
				return ((INakedState) getOwnerElement()).getStateMachine().getCodeGenerationStrategy();
			} else {
				return CodeGenerationStrategy.all;
			}
		}
		return super.getCodeGenerationStrategy();
	}

	public INakedParameter getReturnParameter() {
		if(returnParameter==null){
			for (INakedParameter parm : getOwnedParameters()) {
				if(!parm.isException() && parm.isResult()){
					return parm;
				}
				
			}
		}
		return this.returnParameter;
	}

	public INakedBehavioredClassifier getContext() {
		// NB!! Behaviours can be owned by transitions or directly by packages
		INakedElementOwner owner = getOwnerElement();
		if (owner instanceof INakedActivity && ((INakedActivity) owner).getActivityKind() == ActivityKind.SIMPLE_SYNCHRONOUS_METHOD) {
			return ((INakedActivity) owner).getContext();
		} else if (owner instanceof INakedBehavioredClassifier) {
			return (INakedBehavioredClassifier) owner;
		} else if ((owner instanceof INakedTransition)) {
			return ((INakedTransition) owner).getStateMachine().getContext();
		} else if (owner instanceof INakedState) {
			return ((INakedState) owner).getStateMachine().getContext();
		} else {
			while (true) {
				if (owner instanceof INakedBehavioredClassifier) {
					return (INakedBehavioredClassifier) owner;
				} else if (owner instanceof INakedElement) {
					owner = ((INakedElement) owner).getOwnerElement();
				} else {
					return null;
				}
			}
		}
	}

	@Override
	public INakedBehavioredClassifier getOwner() {
		// Deprecated, implemented only for Octopus;
		if (getContext() != null) {
			return getContext();
		} else {
			INakedElement element = (INakedElement) getOwnerElement();
			while (!(element instanceof INakedBehavioredClassifier || element instanceof INakedModel || element instanceof INakedProfile)) {
				element = (INakedElement) element.getOwnerElement();
			}
			if (element instanceof INakedBehavioredClassifier) {
				return (INakedBehavioredClassifier) element;
			}
			return null;
		}
	}

	@Override
	public INakedNameSpace getNameSpace() {
		// NB!! Behaviours can be owned by transitions that are not namespaces
		INakedElementOwner owner = getOwnerElement();
		while (!(owner instanceof INakedNameSpace) && owner instanceof INakedElement) {
			owner = ((INakedElement) owner).getOwnerElement();
		}
		return (INakedNameSpace) owner;
	}

	@Override
	protected boolean isNamedMember(INakedElement e) {
		return super.isNamedMember(e) || e instanceof INakedMultiplicityElement;
	}

	public boolean isClassifierBehavior() {
		// TODO this assumes that the Behavior is always nested inside the
		// classifier it is behavior for
		if (getSpecification() == null && getContext() instanceof INakedEntity) {
			return equals(((INakedEntity) getContext()).getClassifierBehavior());
		}
		return false;
	}
	/*
	 * @Override public List<IClassifier> getParamTypes() { return
	 * ParameterUtil.parameterTypes(getArgumentParameters()); }
	 * 
	 * @Override public String getSignature() { return
	 * ParameterUtil.signature(this); }
	 * 
	 * @Override public boolean hasClassScope() { return false; }
	 * 
	 * @Override public boolean isAbstract() { return false; }
	 * 
	 * @Override public boolean isInfix() { return false; }
	 * 
	 * @Override public boolean isOclDef() { return false; }
	 * 
	 * @Override public boolean isPrefix() { return false; }
	 */
}
