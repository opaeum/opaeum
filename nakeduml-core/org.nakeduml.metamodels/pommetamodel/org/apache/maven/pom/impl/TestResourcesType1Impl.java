/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.maven.pom.impl;

import java.util.Collection;

import org.apache.maven.pom.POMPackage;
import org.apache.maven.pom.Resource;
import org.apache.maven.pom.TestResourcesType1;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Resources Type1</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.apache.maven.pom.impl.TestResourcesType1Impl#getTestResource <em>Test Resource</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestResourcesType1Impl extends EObjectImpl implements TestResourcesType1 {
	/**
	 * The cached value of the '{@link #getTestResource() <em>Test Resource</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestResource()
	 * @generated
	 * @ordered
	 */
	protected EList<Resource> testResource;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestResourcesType1Impl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return POMPackage.Literals.TEST_RESOURCES_TYPE1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Resource> getTestResource() {
		if (testResource == null) {
			testResource = new EObjectContainmentEList<Resource>(Resource.class, this, POMPackage.TEST_RESOURCES_TYPE1__TEST_RESOURCE);
		}
		return testResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case POMPackage.TEST_RESOURCES_TYPE1__TEST_RESOURCE:
				return ((InternalEList<?>)getTestResource()).basicRemove(otherEnd, msgs);
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
			case POMPackage.TEST_RESOURCES_TYPE1__TEST_RESOURCE:
				return getTestResource();
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
			case POMPackage.TEST_RESOURCES_TYPE1__TEST_RESOURCE:
				getTestResource().clear();
				getTestResource().addAll((Collection<? extends Resource>)newValue);
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
			case POMPackage.TEST_RESOURCES_TYPE1__TEST_RESOURCE:
				getTestResource().clear();
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
			case POMPackage.TEST_RESOURCES_TYPE1__TEST_RESOURCE:
				return testResource != null && !testResource.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestResourcesType1Impl
