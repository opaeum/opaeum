/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DeployedArtifact;
import org.eclipse.uml2.uml.Deployment;
import org.eclipse.uml2.uml.DeploymentTarget;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;

import org.eclipse.uml2.uml.util.UMLValidator;

import org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contained Instance Value Simulation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedInstanceValueSimulationImpl#getDeployedElements <em>Deployed Element</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedInstanceValueSimulationImpl#getDeployments <em>Deployment</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedInstanceValueSimulationImpl#getClassifiers <em>Classifier</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedInstanceValueSimulationImpl#getSlots <em>Slot</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedInstanceValueSimulationImpl#getSpecification <em>Specification</em>}</li>
 *   <li>{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedInstanceValueSimulationImpl#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainedInstanceValueSimulationImpl extends ValueSimulationImpl implements ContainedInstanceValueSimulation {
	/**
	 * The cached value of the '{@link #getDeployments() <em>Deployment</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeployments()
	 * @generated
	 * @ordered
	 */
	protected EList<Deployment> deployments;

	/**
	 * The cached value of the '{@link #getClassifiers() <em>Classifier</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassifiers()
	 * @generated
	 * @ordered
	 */
	protected EList<Classifier> classifiers;

	/**
	 * The cached value of the '{@link #getSlots() <em>Slot</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSlots()
	 * @generated
	 * @ordered
	 */
	protected EList<Slot> slots;

	/**
	 * The cached value of the '{@link #getSpecification() <em>Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecification()
	 * @generated
	 * @ordered
	 */
	protected ValueSpecification specification;

	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<InstanceSpecification> values;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainedInstanceValueSimulationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimulationPackage.Literals.CONTAINED_INSTANCE_VALUE_SIMULATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PackageableElement> getDeployedElements() {
		// TODO: implement this method to return the 'Deployed Element' reference list
		// Ensure that you remove @generated or mark it @generated NOT
		// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
		// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Deployment> getDeployments() {
		if (deployments == null) {
			deployments = new EObjectContainmentWithInverseEList.Resolving<Deployment>(Deployment.class, this, SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYMENT, UMLPackage.DEPLOYMENT__LOCATION);
		}
		return deployments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Classifier> getClassifiers() {
		if (classifiers == null) {
			classifiers = new EObjectResolvingEList<Classifier>(Classifier.class, this, SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CLASSIFIER);
		}
		return classifiers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Slot> getSlots() {
		if (slots == null) {
			slots = new EObjectContainmentWithInverseEList.Resolving<Slot>(Slot.class, this, SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SLOT, UMLPackage.SLOT__OWNING_INSTANCE);
		}
		return slots;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSpecification getSpecification() {
		if (specification != null && specification.eIsProxy()) {
			InternalEObject oldSpecification = (InternalEObject)specification;
			specification = (ValueSpecification)eResolveProxy(oldSpecification);
			if (specification != oldSpecification) {
				InternalEObject newSpecification = (InternalEObject)specification;
				NotificationChain msgs = oldSpecification.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION, null, null);
				if (newSpecification.eInternalContainer() == null) {
					msgs = newSpecification.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION, oldSpecification, specification));
			}
		}
		return specification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValueSpecification basicGetSpecification() {
		return specification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpecification(ValueSpecification newSpecification, NotificationChain msgs) {
		ValueSpecification oldSpecification = specification;
		specification = newSpecification;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION, oldSpecification, newSpecification);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpecification(ValueSpecification newSpecification) {
		if (newSpecification != specification) {
			NotificationChain msgs = null;
			if (specification != null)
				msgs = ((InternalEObject)specification).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION, null, msgs);
			if (newSpecification != null)
				msgs = ((InternalEObject)newSpecification).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION, null, msgs);
			msgs = basicSetSpecification(newSpecification, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION, newSpecification, newSpecification));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InstanceSpecification> getValues() {
		if (values == null) {
			values = new EObjectContainmentEList<InstanceSpecification>(InstanceSpecification.class, this, SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStructuralFeature(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 UMLValidator.DIAGNOSTIC_SOURCE,
						 UMLValidator.INSTANCE_SPECIFICATION__STRUCTURAL_FEATURE,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "validateStructuralFeature", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDefiningFeature(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 UMLValidator.DIAGNOSTIC_SOURCE,
						 UMLValidator.INSTANCE_SPECIFICATION__DEFINING_FEATURE,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "validateDefiningFeature", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDeploymentTarget(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 UMLValidator.DIAGNOSTIC_SOURCE,
						 UMLValidator.INSTANCE_SPECIFICATION__DEPLOYMENT_TARGET,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "validateDeploymentTarget", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDeploymentArtifact(DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO: implement this method
		// -> specify the condition that violates the invariant
		// -> verify the details of the diagnostic, including severity and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(new BasicDiagnostic
						(Diagnostic.ERROR,
						 UMLValidator.DIAGNOSTIC_SOURCE,
						 UMLValidator.INSTANCE_SPECIFICATION__DEPLOYMENT_ARTIFACT,
						 EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "validateDeploymentArtifact", EObjectValidator.getObjectLabel(this, context) }),
						 new Object [] { this }));
			}
			return false;
		}
		return true;
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
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDeployments()).basicAdd(otherEnd, msgs);
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SLOT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSlots()).basicAdd(otherEnd, msgs);
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
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYMENT:
				return ((InternalEList<?>)getDeployments()).basicRemove(otherEnd, msgs);
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SLOT:
				return ((InternalEList<?>)getSlots()).basicRemove(otherEnd, msgs);
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION:
				return basicSetSpecification(null, msgs);
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES:
				return ((InternalEList<?>)getValues()).basicRemove(otherEnd, msgs);
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
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYED_ELEMENT:
				return getDeployedElements();
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYMENT:
				return getDeployments();
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CLASSIFIER:
				return getClassifiers();
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SLOT:
				return getSlots();
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION:
				if (resolve) return getSpecification();
				return basicGetSpecification();
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES:
				return getValues();
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
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYMENT:
				getDeployments().clear();
				getDeployments().addAll((Collection<? extends Deployment>)newValue);
				return;
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CLASSIFIER:
				getClassifiers().clear();
				getClassifiers().addAll((Collection<? extends Classifier>)newValue);
				return;
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SLOT:
				getSlots().clear();
				getSlots().addAll((Collection<? extends Slot>)newValue);
				return;
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION:
				setSpecification((ValueSpecification)newValue);
				return;
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES:
				getValues().clear();
				getValues().addAll((Collection<? extends InstanceSpecification>)newValue);
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
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYMENT:
				getDeployments().clear();
				return;
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CLASSIFIER:
				getClassifiers().clear();
				return;
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SLOT:
				getSlots().clear();
				return;
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION:
				setSpecification((ValueSpecification)null);
				return;
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES:
				getValues().clear();
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
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYED_ELEMENT:
				return !getDeployedElements().isEmpty();
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYMENT:
				return deployments != null && !deployments.isEmpty();
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CLASSIFIER:
				return classifiers != null && !classifiers.isEmpty();
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SLOT:
				return slots != null && !slots.isEmpty();
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION:
				return specification != null;
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES:
				return values != null && !values.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == DeploymentTarget.class) {
			switch (derivedFeatureID) {
				case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYED_ELEMENT: return UMLPackage.DEPLOYMENT_TARGET__DEPLOYED_ELEMENT;
				case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYMENT: return UMLPackage.DEPLOYMENT_TARGET__DEPLOYMENT;
				default: return -1;
			}
		}
		if (baseClass == DeployedArtifact.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == InstanceSpecification.class) {
			switch (derivedFeatureID) {
				case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CLASSIFIER: return UMLPackage.INSTANCE_SPECIFICATION__CLASSIFIER;
				case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SLOT: return UMLPackage.INSTANCE_SPECIFICATION__SLOT;
				case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION: return UMLPackage.INSTANCE_SPECIFICATION__SPECIFICATION;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == DeploymentTarget.class) {
			switch (baseFeatureID) {
				case UMLPackage.DEPLOYMENT_TARGET__DEPLOYED_ELEMENT: return SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYED_ELEMENT;
				case UMLPackage.DEPLOYMENT_TARGET__DEPLOYMENT: return SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYMENT;
				default: return -1;
			}
		}
		if (baseClass == DeployedArtifact.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == InstanceSpecification.class) {
			switch (baseFeatureID) {
				case UMLPackage.INSTANCE_SPECIFICATION__CLASSIFIER: return SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__CLASSIFIER;
				case UMLPackage.INSTANCE_SPECIFICATION__SLOT: return SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SLOT;
				case UMLPackage.INSTANCE_SPECIFICATION__SPECIFICATION: return SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	@Override
	public Classifier getClassifier(String name){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Classifier getClassifier(String name,boolean ignoreCase,EClass eClass){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ValueSpecification createSpecification(String name,Type type,EClass eClass){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Slot createSlot(){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deployment createDeployment(String name){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deployment getDeployment(String name){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Deployment getDeployment(String name,boolean ignoreCase,boolean createOnDemand){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PackageableElement getDeployedElement(String name){
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PackageableElement getDeployedElement(String name,boolean ignoreCase,EClass eClass){
		// TODO Auto-generated method stub
		return null;
	}

} //ContainedInstanceValueSimulationImpl
