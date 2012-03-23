/**
 */
package org.opaeum.metamodels.simulation.simulation.util;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.uml2.uml.DeployedArtifact;
import org.eclipse.uml2.uml.DeploymentTarget;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.InstanceSpecification;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Namespace;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.ParameterableElement;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.TemplateableElement;
import org.eclipse.uml2.uml.TypedElement;
import org.eclipse.uml2.uml.ValueSpecification;

import org.opaeum.metamodels.simulation.simulation.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.opaeum.metamodels.simulation.simulation.SimulationPackage
 * @generated
 */
public class SimulationSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SimulationPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationSwitch() {
		if (modelPackage == null) {
			modelPackage = SimulationPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case SimulationPackage.SIMULATED_VALUE: {
				SimulatedValue simulatedValue = (SimulatedValue)theEObject;
				T result = caseSimulatedValue(simulatedValue);
				if (result == null) result = caseValueSpecification(simulatedValue);
				if (result == null) result = casePackageableElement(simulatedValue);
				if (result == null) result = caseTypedElement(simulatedValue);
				if (result == null) result = caseNamedElement(simulatedValue);
				if (result == null) result = caseParameterableElement(simulatedValue);
				if (result == null) result = caseElement(simulatedValue);
				if (result == null) result = caseEModelElement(simulatedValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.NORMAL_DISTRIBUTION: {
				NormalDistribution normalDistribution = (NormalDistribution)theEObject;
				T result = caseNormalDistribution(normalDistribution);
				if (result == null) result = caseNumericValueDistribution(normalDistribution);
				if (result == null) result = caseSimulatedValue(normalDistribution);
				if (result == null) result = caseValueSpecification(normalDistribution);
				if (result == null) result = casePackageableElement(normalDistribution);
				if (result == null) result = caseTypedElement(normalDistribution);
				if (result == null) result = caseNamedElement(normalDistribution);
				if (result == null) result = caseParameterableElement(normalDistribution);
				if (result == null) result = caseElement(normalDistribution);
				if (result == null) result = caseEModelElement(normalDistribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.UNIFORM_DISTRIBUTION: {
				UniformDistribution uniformDistribution = (UniformDistribution)theEObject;
				T result = caseUniformDistribution(uniformDistribution);
				if (result == null) result = caseNumericValueDistribution(uniformDistribution);
				if (result == null) result = caseSimulatedValue(uniformDistribution);
				if (result == null) result = caseValueSpecification(uniformDistribution);
				if (result == null) result = casePackageableElement(uniformDistribution);
				if (result == null) result = caseTypedElement(uniformDistribution);
				if (result == null) result = caseNamedElement(uniformDistribution);
				if (result == null) result = caseParameterableElement(uniformDistribution);
				if (result == null) result = caseElement(uniformDistribution);
				if (result == null) result = caseEModelElement(uniformDistribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.EXPONENTIAL_DISTRIBUTION: {
				ExponentialDistribution exponentialDistribution = (ExponentialDistribution)theEObject;
				T result = caseExponentialDistribution(exponentialDistribution);
				if (result == null) result = caseNumericValueDistribution(exponentialDistribution);
				if (result == null) result = caseSimulatedValue(exponentialDistribution);
				if (result == null) result = caseValueSpecification(exponentialDistribution);
				if (result == null) result = casePackageableElement(exponentialDistribution);
				if (result == null) result = caseTypedElement(exponentialDistribution);
				if (result == null) result = caseNamedElement(exponentialDistribution);
				if (result == null) result = caseParameterableElement(exponentialDistribution);
				if (result == null) result = caseElement(exponentialDistribution);
				if (result == null) result = caseEModelElement(exponentialDistribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.SIMULATING_SLOT: {
				SimulatingSlot simulatingSlot = (SimulatingSlot)theEObject;
				T result = caseSimulatingSlot(simulatingSlot);
				if (result == null) result = caseSlot(simulatingSlot);
				if (result == null) result = caseElement(simulatingSlot);
				if (result == null) result = caseEModelElement(simulatingSlot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.NUMERIC_VALUE_DISTRIBUTION: {
				NumericValueDistribution numericValueDistribution = (NumericValueDistribution)theEObject;
				T result = caseNumericValueDistribution(numericValueDistribution);
				if (result == null) result = caseSimulatedValue(numericValueDistribution);
				if (result == null) result = caseValueSpecification(numericValueDistribution);
				if (result == null) result = casePackageableElement(numericValueDistribution);
				if (result == null) result = caseTypedElement(numericValueDistribution);
				if (result == null) result = caseNamedElement(numericValueDistribution);
				if (result == null) result = caseParameterableElement(numericValueDistribution);
				if (result == null) result = caseElement(numericValueDistribution);
				if (result == null) result = caseEModelElement(numericValueDistribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.WEIGHTED_ENUM_LITERAL_VALUE: {
				WeightedEnumLiteralValue weightedEnumLiteralValue = (WeightedEnumLiteralValue)theEObject;
				T result = caseWeightedEnumLiteralValue(weightedEnumLiteralValue);
				if (result == null) result = caseSimulatedValue(weightedEnumLiteralValue);
				if (result == null) result = caseValueSpecification(weightedEnumLiteralValue);
				if (result == null) result = casePackageableElement(weightedEnumLiteralValue);
				if (result == null) result = caseTypedElement(weightedEnumLiteralValue);
				if (result == null) result = caseNamedElement(weightedEnumLiteralValue);
				if (result == null) result = caseParameterableElement(weightedEnumLiteralValue);
				if (result == null) result = caseElement(weightedEnumLiteralValue);
				if (result == null) result = caseEModelElement(weightedEnumLiteralValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.CONTAINED_ACTUAL_INSTANCE: {
				ContainedActualInstance containedActualInstance = (ContainedActualInstance)theEObject;
				T result = caseContainedActualInstance(containedActualInstance);
				if (result == null) result = caseSimulatedValue(containedActualInstance);
				if (result == null) result = caseValueSpecification(containedActualInstance);
				if (result == null) result = casePackageableElement(containedActualInstance);
				if (result == null) result = caseTypedElement(containedActualInstance);
				if (result == null) result = caseNamedElement(containedActualInstance);
				if (result == null) result = caseParameterableElement(containedActualInstance);
				if (result == null) result = caseElement(containedActualInstance);
				if (result == null) result = caseEModelElement(containedActualInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.WEIGHTED_BOOLEAN_VALUE: {
				WeightedBooleanValue weightedBooleanValue = (WeightedBooleanValue)theEObject;
				T result = caseWeightedBooleanValue(weightedBooleanValue);
				if (result == null) result = caseSimulatedValue(weightedBooleanValue);
				if (result == null) result = caseValueSpecification(weightedBooleanValue);
				if (result == null) result = casePackageableElement(weightedBooleanValue);
				if (result == null) result = caseTypedElement(weightedBooleanValue);
				if (result == null) result = caseNamedElement(weightedBooleanValue);
				if (result == null) result = caseParameterableElement(weightedBooleanValue);
				if (result == null) result = caseElement(weightedBooleanValue);
				if (result == null) result = caseEModelElement(weightedBooleanValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION: {
				NumberRangeDistribution numberRangeDistribution = (NumberRangeDistribution)theEObject;
				T result = caseNumberRangeDistribution(numberRangeDistribution);
				if (result == null) result = caseNumericValueDistribution(numberRangeDistribution);
				if (result == null) result = caseSimulatedValue(numberRangeDistribution);
				if (result == null) result = caseValueSpecification(numberRangeDistribution);
				if (result == null) result = casePackageableElement(numberRangeDistribution);
				if (result == null) result = caseTypedElement(numberRangeDistribution);
				if (result == null) result = caseNamedElement(numberRangeDistribution);
				if (result == null) result = caseParameterableElement(numberRangeDistribution);
				if (result == null) result = caseElement(numberRangeDistribution);
				if (result == null) result = caseEModelElement(numberRangeDistribution);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.WEIGHTED_STRING_VALUE: {
				WeightedStringValue weightedStringValue = (WeightedStringValue)theEObject;
				T result = caseWeightedStringValue(weightedStringValue);
				if (result == null) result = caseSimulatedValue(weightedStringValue);
				if (result == null) result = caseValueSpecification(weightedStringValue);
				if (result == null) result = casePackageableElement(weightedStringValue);
				if (result == null) result = caseTypedElement(weightedStringValue);
				if (result == null) result = caseNamedElement(weightedStringValue);
				if (result == null) result = caseParameterableElement(weightedStringValue);
				if (result == null) result = caseElement(weightedStringValue);
				if (result == null) result = caseEModelElement(weightedStringValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.SIMULATION_MODEL: {
				SimulationModel simulationModel = (SimulationModel)theEObject;
				T result = caseSimulationModel(simulationModel);
				if (result == null) result = casePackage(simulationModel);
				if (result == null) result = caseNamespace(simulationModel);
				if (result == null) result = casePackageableElement(simulationModel);
				if (result == null) result = caseTemplateableElement(simulationModel);
				if (result == null) result = caseNamedElement(simulationModel);
				if (result == null) result = caseParameterableElement(simulationModel);
				if (result == null) result = caseElement(simulationModel);
				if (result == null) result = caseEModelElement(simulationModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.INSTANCE_SIMULATION: {
				InstanceSimulation instanceSimulation = (InstanceSimulation)theEObject;
				T result = caseInstanceSimulation(instanceSimulation);
				if (result == null) result = caseInstanceSpecification(instanceSimulation);
				if (result == null) result = caseDeploymentTarget(instanceSimulation);
				if (result == null) result = casePackageableElement(instanceSimulation);
				if (result == null) result = caseDeployedArtifact(instanceSimulation);
				if (result == null) result = caseNamedElement(instanceSimulation);
				if (result == null) result = caseParameterableElement(instanceSimulation);
				if (result == null) result = caseElement(instanceSimulation);
				if (result == null) result = caseEModelElement(instanceSimulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.ACTUAL_INSTANCE: {
				ActualInstance actualInstance = (ActualInstance)theEObject;
				T result = caseActualInstance(actualInstance);
				if (result == null) result = caseInstanceSpecification(actualInstance);
				if (result == null) result = caseDeploymentTarget(actualInstance);
				if (result == null) result = casePackageableElement(actualInstance);
				if (result == null) result = caseDeployedArtifact(actualInstance);
				if (result == null) result = caseNamedElement(actualInstance);
				if (result == null) result = caseParameterableElement(actualInstance);
				if (result == null) result = caseElement(actualInstance);
				if (result == null) result = caseEModelElement(actualInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.WEIGHTED_INSTANCE_VALUE: {
				WeightedInstanceValue weightedInstanceValue = (WeightedInstanceValue)theEObject;
				T result = caseWeightedInstanceValue(weightedInstanceValue);
				if (result == null) result = caseSimulatedValue(weightedInstanceValue);
				if (result == null) result = caseValueSpecification(weightedInstanceValue);
				if (result == null) result = casePackageableElement(weightedInstanceValue);
				if (result == null) result = caseTypedElement(weightedInstanceValue);
				if (result == null) result = caseNamedElement(weightedInstanceValue);
				if (result == null) result = caseParameterableElement(weightedInstanceValue);
				if (result == null) result = caseElement(weightedInstanceValue);
				if (result == null) result = caseEModelElement(weightedInstanceValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simulated Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simulated Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimulatedValue(SimulatedValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Normal Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Normal Distribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNormalDistribution(NormalDistribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Uniform Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Uniform Distribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUniformDistribution(UniformDistribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exponential Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exponential Distribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExponentialDistribution(ExponentialDistribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Simulating Slot</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simulating Slot</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimulatingSlot(SimulatingSlot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Numeric Value Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Numeric Value Distribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumericValueDistribution(NumericValueDistribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Weighted Enum Literal Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Weighted Enum Literal Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWeightedEnumLiteralValue(WeightedEnumLiteralValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contained Actual Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contained Actual Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainedActualInstance(ContainedActualInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Weighted Boolean Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Weighted Boolean Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWeightedBooleanValue(WeightedBooleanValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Number Range Distribution</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Number Range Distribution</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumberRangeDistribution(NumberRangeDistribution object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Weighted String Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Weighted String Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWeightedStringValue(WeightedStringValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimulationModel(SimulationModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instance Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instance Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstanceSimulation(InstanceSimulation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Actual Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Actual Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActualInstance(ActualInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Weighted Instance Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Weighted Instance Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWeightedInstanceValue(WeightedInstanceValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EModel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EModel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEModelElement(EModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElement(Element object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameterable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameterable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterableElement(ParameterableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Packageable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Packageable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageableElement(PackageableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypedElement(TypedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueSpecification(ValueSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Slot</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Slot</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSlot(Slot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Namespace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamespace(Namespace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Templateable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Templateable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplateableElement(TemplateableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackage(org.eclipse.uml2.uml.Package object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployment Target</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployment Target</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeploymentTarget(DeploymentTarget object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deployed Artifact</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deployed Artifact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDeployedArtifact(DeployedArtifact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Instance Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Instance Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInstanceSpecification(InstanceSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //SimulationSwitch
