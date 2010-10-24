/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import java.util.Collection;

import org.drools.drools._5._0.process.ActionNodeType;
import org.drools.drools._5._0.process.CompositeType;
import org.drools.drools._5._0.process.DynamicType;
import org.drools.drools._5._0.process.EndType;
import org.drools.drools._5._0.process.EventNodeType;
import org.drools.drools._5._0.process.FaultType;
import org.drools.drools._5._0.process.ForEachType;
import org.drools.drools._5._0.process.HumanTaskType;
import org.drools.drools._5._0.process.JoinType;
import org.drools.drools._5._0.process.MilestoneType;
import org.drools.drools._5._0.process.NodesType;
import org.drools.drools._5._0.process.ProcessPackage;
import org.drools.drools._5._0.process.RuleSetType;
import org.drools.drools._5._0.process.SplitType;
import org.drools.drools._5._0.process.StartType;
import org.drools.drools._5._0.process.StateType;
import org.drools.drools._5._0.process.SubProcessType;
import org.drools.drools._5._0.process.TimerNodeType;
import org.drools.drools._5._0.process.WorkItemType;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Nodes Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getStart <em>Start</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getEnd <em>End</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getActionNode <em>Action Node</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getRuleSet <em>Rule Set</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getSplit <em>Split</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getJoin <em>Join</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getMilestone <em>Milestone</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getSubProcess <em>Sub Process</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getWorkItem <em>Work Item</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getTimerNode <em>Timer Node</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getHumanTask <em>Human Task</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getComposite <em>Composite</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getForEach <em>For Each</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getEventNode <em>Event Node</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getFault <em>Fault</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getState <em>State</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.NodesTypeImpl#getDynamic <em>Dynamic</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodesTypeImpl extends EObjectImpl implements NodesType {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodesTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.NODES_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, ProcessPackage.NODES_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StartType> getStart() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__START);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EndType> getEnd() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__END);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActionNodeType> getActionNode() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__ACTION_NODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RuleSetType> getRuleSet() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__RULE_SET);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SplitType> getSplit() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__SPLIT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<JoinType> getJoin() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__JOIN);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MilestoneType> getMilestone() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__MILESTONE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SubProcessType> getSubProcess() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__SUB_PROCESS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<WorkItemType> getWorkItem() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__WORK_ITEM);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TimerNodeType> getTimerNode() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__TIMER_NODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<HumanTaskType> getHumanTask() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__HUMAN_TASK);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CompositeType> getComposite() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__COMPOSITE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ForEachType> getForEach() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__FOR_EACH);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventNodeType> getEventNode() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__EVENT_NODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FaultType> getFault() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__FAULT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StateType> getState() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__STATE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DynamicType> getDynamic() {
		return getGroup().list(ProcessPackage.Literals.NODES_TYPE__DYNAMIC);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.NODES_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__START:
				return ((InternalEList<?>)getStart()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__END:
				return ((InternalEList<?>)getEnd()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__ACTION_NODE:
				return ((InternalEList<?>)getActionNode()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__RULE_SET:
				return ((InternalEList<?>)getRuleSet()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__SPLIT:
				return ((InternalEList<?>)getSplit()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__JOIN:
				return ((InternalEList<?>)getJoin()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__MILESTONE:
				return ((InternalEList<?>)getMilestone()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__SUB_PROCESS:
				return ((InternalEList<?>)getSubProcess()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__WORK_ITEM:
				return ((InternalEList<?>)getWorkItem()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__TIMER_NODE:
				return ((InternalEList<?>)getTimerNode()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__HUMAN_TASK:
				return ((InternalEList<?>)getHumanTask()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__COMPOSITE:
				return ((InternalEList<?>)getComposite()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__FOR_EACH:
				return ((InternalEList<?>)getForEach()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__EVENT_NODE:
				return ((InternalEList<?>)getEventNode()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__FAULT:
				return ((InternalEList<?>)getFault()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__STATE:
				return ((InternalEList<?>)getState()).basicRemove(otherEnd, msgs);
			case ProcessPackage.NODES_TYPE__DYNAMIC:
				return ((InternalEList<?>)getDynamic()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ProcessPackage.NODES_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case ProcessPackage.NODES_TYPE__START:
				return getStart();
			case ProcessPackage.NODES_TYPE__END:
				return getEnd();
			case ProcessPackage.NODES_TYPE__ACTION_NODE:
				return getActionNode();
			case ProcessPackage.NODES_TYPE__RULE_SET:
				return getRuleSet();
			case ProcessPackage.NODES_TYPE__SPLIT:
				return getSplit();
			case ProcessPackage.NODES_TYPE__JOIN:
				return getJoin();
			case ProcessPackage.NODES_TYPE__MILESTONE:
				return getMilestone();
			case ProcessPackage.NODES_TYPE__SUB_PROCESS:
				return getSubProcess();
			case ProcessPackage.NODES_TYPE__WORK_ITEM:
				return getWorkItem();
			case ProcessPackage.NODES_TYPE__TIMER_NODE:
				return getTimerNode();
			case ProcessPackage.NODES_TYPE__HUMAN_TASK:
				return getHumanTask();
			case ProcessPackage.NODES_TYPE__COMPOSITE:
				return getComposite();
			case ProcessPackage.NODES_TYPE__FOR_EACH:
				return getForEach();
			case ProcessPackage.NODES_TYPE__EVENT_NODE:
				return getEventNode();
			case ProcessPackage.NODES_TYPE__FAULT:
				return getFault();
			case ProcessPackage.NODES_TYPE__STATE:
				return getState();
			case ProcessPackage.NODES_TYPE__DYNAMIC:
				return getDynamic();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ProcessPackage.NODES_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case ProcessPackage.NODES_TYPE__START:
				getStart().clear();
				getStart().addAll((Collection<? extends StartType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__END:
				getEnd().clear();
				getEnd().addAll((Collection<? extends EndType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__ACTION_NODE:
				getActionNode().clear();
				getActionNode().addAll((Collection<? extends ActionNodeType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__RULE_SET:
				getRuleSet().clear();
				getRuleSet().addAll((Collection<? extends RuleSetType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__SPLIT:
				getSplit().clear();
				getSplit().addAll((Collection<? extends SplitType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__JOIN:
				getJoin().clear();
				getJoin().addAll((Collection<? extends JoinType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__MILESTONE:
				getMilestone().clear();
				getMilestone().addAll((Collection<? extends MilestoneType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__SUB_PROCESS:
				getSubProcess().clear();
				getSubProcess().addAll((Collection<? extends SubProcessType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__WORK_ITEM:
				getWorkItem().clear();
				getWorkItem().addAll((Collection<? extends WorkItemType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__TIMER_NODE:
				getTimerNode().clear();
				getTimerNode().addAll((Collection<? extends TimerNodeType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__HUMAN_TASK:
				getHumanTask().clear();
				getHumanTask().addAll((Collection<? extends HumanTaskType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__COMPOSITE:
				getComposite().clear();
				getComposite().addAll((Collection<? extends CompositeType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__FOR_EACH:
				getForEach().clear();
				getForEach().addAll((Collection<? extends ForEachType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__EVENT_NODE:
				getEventNode().clear();
				getEventNode().addAll((Collection<? extends EventNodeType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__FAULT:
				getFault().clear();
				getFault().addAll((Collection<? extends FaultType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__STATE:
				getState().clear();
				getState().addAll((Collection<? extends StateType>)newValue);
				return;
			case ProcessPackage.NODES_TYPE__DYNAMIC:
				getDynamic().clear();
				getDynamic().addAll((Collection<? extends DynamicType>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ProcessPackage.NODES_TYPE__GROUP:
				getGroup().clear();
				return;
			case ProcessPackage.NODES_TYPE__START:
				getStart().clear();
				return;
			case ProcessPackage.NODES_TYPE__END:
				getEnd().clear();
				return;
			case ProcessPackage.NODES_TYPE__ACTION_NODE:
				getActionNode().clear();
				return;
			case ProcessPackage.NODES_TYPE__RULE_SET:
				getRuleSet().clear();
				return;
			case ProcessPackage.NODES_TYPE__SPLIT:
				getSplit().clear();
				return;
			case ProcessPackage.NODES_TYPE__JOIN:
				getJoin().clear();
				return;
			case ProcessPackage.NODES_TYPE__MILESTONE:
				getMilestone().clear();
				return;
			case ProcessPackage.NODES_TYPE__SUB_PROCESS:
				getSubProcess().clear();
				return;
			case ProcessPackage.NODES_TYPE__WORK_ITEM:
				getWorkItem().clear();
				return;
			case ProcessPackage.NODES_TYPE__TIMER_NODE:
				getTimerNode().clear();
				return;
			case ProcessPackage.NODES_TYPE__HUMAN_TASK:
				getHumanTask().clear();
				return;
			case ProcessPackage.NODES_TYPE__COMPOSITE:
				getComposite().clear();
				return;
			case ProcessPackage.NODES_TYPE__FOR_EACH:
				getForEach().clear();
				return;
			case ProcessPackage.NODES_TYPE__EVENT_NODE:
				getEventNode().clear();
				return;
			case ProcessPackage.NODES_TYPE__FAULT:
				getFault().clear();
				return;
			case ProcessPackage.NODES_TYPE__STATE:
				getState().clear();
				return;
			case ProcessPackage.NODES_TYPE__DYNAMIC:
				getDynamic().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ProcessPackage.NODES_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case ProcessPackage.NODES_TYPE__START:
				return !getStart().isEmpty();
			case ProcessPackage.NODES_TYPE__END:
				return !getEnd().isEmpty();
			case ProcessPackage.NODES_TYPE__ACTION_NODE:
				return !getActionNode().isEmpty();
			case ProcessPackage.NODES_TYPE__RULE_SET:
				return !getRuleSet().isEmpty();
			case ProcessPackage.NODES_TYPE__SPLIT:
				return !getSplit().isEmpty();
			case ProcessPackage.NODES_TYPE__JOIN:
				return !getJoin().isEmpty();
			case ProcessPackage.NODES_TYPE__MILESTONE:
				return !getMilestone().isEmpty();
			case ProcessPackage.NODES_TYPE__SUB_PROCESS:
				return !getSubProcess().isEmpty();
			case ProcessPackage.NODES_TYPE__WORK_ITEM:
				return !getWorkItem().isEmpty();
			case ProcessPackage.NODES_TYPE__TIMER_NODE:
				return !getTimerNode().isEmpty();
			case ProcessPackage.NODES_TYPE__HUMAN_TASK:
				return !getHumanTask().isEmpty();
			case ProcessPackage.NODES_TYPE__COMPOSITE:
				return !getComposite().isEmpty();
			case ProcessPackage.NODES_TYPE__FOR_EACH:
				return !getForEach().isEmpty();
			case ProcessPackage.NODES_TYPE__EVENT_NODE:
				return !getEventNode().isEmpty();
			case ProcessPackage.NODES_TYPE__FAULT:
				return !getFault().isEmpty();
			case ProcessPackage.NODES_TYPE__STATE:
				return !getState().isEmpty();
			case ProcessPackage.NODES_TYPE__DYNAMIC:
				return !getDynamic().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (group: ");
		result.append(group);
		result.append(')');
		return result.toString();
	}

} //NodesTypeImpl
