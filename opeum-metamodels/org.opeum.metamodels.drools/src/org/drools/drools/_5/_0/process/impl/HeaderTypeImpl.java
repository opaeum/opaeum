/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process.impl;

import java.util.Collection;

import org.drools.drools._5._0.process.ExceptionHandlersType;
import org.drools.drools._5._0.process.FunctionImportsType;
import org.drools.drools._5._0.process.GlobalsType;
import org.drools.drools._5._0.process.HeaderType;
import org.drools.drools._5._0.process.ImportsType;
import org.drools.drools._5._0.process.ProcessPackage;
import org.drools.drools._5._0.process.SwimlanesType;
import org.drools.drools._5._0.process.VariablesType;
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
 * An implementation of the model object '<em><b>Header Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.impl.HeaderTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.HeaderTypeImpl#getImports <em>Imports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.HeaderTypeImpl#getFunctionImports <em>Function Imports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.HeaderTypeImpl#getGlobals <em>Globals</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.HeaderTypeImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.HeaderTypeImpl#getSwimlanes <em>Swimlanes</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.impl.HeaderTypeImpl#getExceptionHandlers <em>Exception Handlers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HeaderTypeImpl extends EObjectImpl implements HeaderType {
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
	protected HeaderTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.HEADER_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, ProcessPackage.HEADER_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ImportsType> getImports() {
		return getGroup().list(ProcessPackage.Literals.HEADER_TYPE__IMPORTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FunctionImportsType> getFunctionImports() {
		return getGroup().list(ProcessPackage.Literals.HEADER_TYPE__FUNCTION_IMPORTS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<GlobalsType> getGlobals() {
		return getGroup().list(ProcessPackage.Literals.HEADER_TYPE__GLOBALS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariablesType> getVariables() {
		return getGroup().list(ProcessPackage.Literals.HEADER_TYPE__VARIABLES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SwimlanesType> getSwimlanes() {
		return getGroup().list(ProcessPackage.Literals.HEADER_TYPE__SWIMLANES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExceptionHandlersType> getExceptionHandlers() {
		return getGroup().list(ProcessPackage.Literals.HEADER_TYPE__EXCEPTION_HANDLERS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.HEADER_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case ProcessPackage.HEADER_TYPE__IMPORTS:
				return ((InternalEList<?>)getImports()).basicRemove(otherEnd, msgs);
			case ProcessPackage.HEADER_TYPE__FUNCTION_IMPORTS:
				return ((InternalEList<?>)getFunctionImports()).basicRemove(otherEnd, msgs);
			case ProcessPackage.HEADER_TYPE__GLOBALS:
				return ((InternalEList<?>)getGlobals()).basicRemove(otherEnd, msgs);
			case ProcessPackage.HEADER_TYPE__VARIABLES:
				return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
			case ProcessPackage.HEADER_TYPE__SWIMLANES:
				return ((InternalEList<?>)getSwimlanes()).basicRemove(otherEnd, msgs);
			case ProcessPackage.HEADER_TYPE__EXCEPTION_HANDLERS:
				return ((InternalEList<?>)getExceptionHandlers()).basicRemove(otherEnd, msgs);
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
			case ProcessPackage.HEADER_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case ProcessPackage.HEADER_TYPE__IMPORTS:
				return getImports();
			case ProcessPackage.HEADER_TYPE__FUNCTION_IMPORTS:
				return getFunctionImports();
			case ProcessPackage.HEADER_TYPE__GLOBALS:
				return getGlobals();
			case ProcessPackage.HEADER_TYPE__VARIABLES:
				return getVariables();
			case ProcessPackage.HEADER_TYPE__SWIMLANES:
				return getSwimlanes();
			case ProcessPackage.HEADER_TYPE__EXCEPTION_HANDLERS:
				return getExceptionHandlers();
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
			case ProcessPackage.HEADER_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case ProcessPackage.HEADER_TYPE__IMPORTS:
				getImports().clear();
				getImports().addAll((Collection<? extends ImportsType>)newValue);
				return;
			case ProcessPackage.HEADER_TYPE__FUNCTION_IMPORTS:
				getFunctionImports().clear();
				getFunctionImports().addAll((Collection<? extends FunctionImportsType>)newValue);
				return;
			case ProcessPackage.HEADER_TYPE__GLOBALS:
				getGlobals().clear();
				getGlobals().addAll((Collection<? extends GlobalsType>)newValue);
				return;
			case ProcessPackage.HEADER_TYPE__VARIABLES:
				getVariables().clear();
				getVariables().addAll((Collection<? extends VariablesType>)newValue);
				return;
			case ProcessPackage.HEADER_TYPE__SWIMLANES:
				getSwimlanes().clear();
				getSwimlanes().addAll((Collection<? extends SwimlanesType>)newValue);
				return;
			case ProcessPackage.HEADER_TYPE__EXCEPTION_HANDLERS:
				getExceptionHandlers().clear();
				getExceptionHandlers().addAll((Collection<? extends ExceptionHandlersType>)newValue);
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
			case ProcessPackage.HEADER_TYPE__GROUP:
				getGroup().clear();
				return;
			case ProcessPackage.HEADER_TYPE__IMPORTS:
				getImports().clear();
				return;
			case ProcessPackage.HEADER_TYPE__FUNCTION_IMPORTS:
				getFunctionImports().clear();
				return;
			case ProcessPackage.HEADER_TYPE__GLOBALS:
				getGlobals().clear();
				return;
			case ProcessPackage.HEADER_TYPE__VARIABLES:
				getVariables().clear();
				return;
			case ProcessPackage.HEADER_TYPE__SWIMLANES:
				getSwimlanes().clear();
				return;
			case ProcessPackage.HEADER_TYPE__EXCEPTION_HANDLERS:
				getExceptionHandlers().clear();
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
			case ProcessPackage.HEADER_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case ProcessPackage.HEADER_TYPE__IMPORTS:
				return !getImports().isEmpty();
			case ProcessPackage.HEADER_TYPE__FUNCTION_IMPORTS:
				return !getFunctionImports().isEmpty();
			case ProcessPackage.HEADER_TYPE__GLOBALS:
				return !getGlobals().isEmpty();
			case ProcessPackage.HEADER_TYPE__VARIABLES:
				return !getVariables().isEmpty();
			case ProcessPackage.HEADER_TYPE__SWIMLANES:
				return !getSwimlanes().isEmpty();
			case ProcessPackage.HEADER_TYPE__EXCEPTION_HANDLERS:
				return !getExceptionHandlers().isEmpty();
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

} //HeaderTypeImpl
