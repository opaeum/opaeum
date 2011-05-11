/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.nakeduml.uim.impl;

import java.util.Collection;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.uml2.uml.StateMachine;
import org.nakeduml.uim.StateForm;
import org.nakeduml.uim.StateMachineFolder;
import org.nakeduml.uim.UIMPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>State Machine Folder</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.nakeduml.uim.impl.StateMachineFolderImpl#getStateForms <em>State Forms</em>}</li>
 *   <li>{@link org.nakeduml.uim.impl.StateMachineFolderImpl#getStateMachine <em>State Machine</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StateMachineFolderImpl extends OperationContainingFolderImpl implements StateMachineFolder {
	/**
	 * The cached value of the '{@link #getStateForms() <em>State Forms</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateForms()
	 * @generated
	 * @ordered
	 */
	protected EList<StateForm> stateForms;

	/**
	 * The cached value of the '{@link #getStateMachine() <em>State Machine</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStateMachine()
	 * @generated
	 * @ordered
	 */
	protected StateMachine stateMachine;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StateMachineFolderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UIMPackage.Literals.STATE_MACHINE_FOLDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StateForm> getStateForms() {
		if (stateForms == null) {
			stateForms = new EObjectContainmentWithInverseEList<StateForm>(StateForm.class, this, UIMPackage.STATE_MACHINE_FOLDER__STATE_FORMS, UIMPackage.STATE_FORM__FOLDER);
		}
		return stateForms;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateMachine getStateMachine() {
		if (stateMachine != null && stateMachine.eIsProxy()) {
			InternalEObject oldStateMachine = (InternalEObject)stateMachine;
			stateMachine = (StateMachine)eResolveProxy(oldStateMachine);
			if (stateMachine != oldStateMachine) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UIMPackage.STATE_MACHINE_FOLDER__STATE_MACHINE, oldStateMachine, stateMachine));
			}
		}
		return stateMachine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StateMachine basicGetStateMachine() {
		return stateMachine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStateMachine(StateMachine newStateMachine) {
		StateMachine oldStateMachine = stateMachine;
		stateMachine = newStateMachine;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UIMPackage.STATE_MACHINE_FOLDER__STATE_MACHINE, oldStateMachine, stateMachine));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIMPackage.STATE_MACHINE_FOLDER__STATE_FORMS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getStateForms()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UIMPackage.STATE_MACHINE_FOLDER__STATE_FORMS:
				return ((InternalEList<?>)getStateForms()).basicRemove(otherEnd, msgs);
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
			case UIMPackage.STATE_MACHINE_FOLDER__STATE_FORMS:
				return getStateForms();
			case UIMPackage.STATE_MACHINE_FOLDER__STATE_MACHINE:
				if (resolve) return getStateMachine();
				return basicGetStateMachine();
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
			case UIMPackage.STATE_MACHINE_FOLDER__STATE_FORMS:
				getStateForms().clear();
				getStateForms().addAll((Collection<? extends StateForm>)newValue);
				return;
			case UIMPackage.STATE_MACHINE_FOLDER__STATE_MACHINE:
				setStateMachine((StateMachine)newValue);
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
			case UIMPackage.STATE_MACHINE_FOLDER__STATE_FORMS:
				getStateForms().clear();
				return;
			case UIMPackage.STATE_MACHINE_FOLDER__STATE_MACHINE:
				setStateMachine((StateMachine)null);
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
			case UIMPackage.STATE_MACHINE_FOLDER__STATE_FORMS:
				return stateForms != null && !stateForms.isEmpty();
			case UIMPackage.STATE_MACHINE_FOLDER__STATE_MACHINE:
				return stateMachine != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public org.eclipse.uml2.uml.Class getRepresentedClass() {
		return getStateMachine();
	}
} //StateMachineFolderImpl
