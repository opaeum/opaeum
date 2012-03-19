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
			case SimulationPackage.VALUE_SIMULATION: {
				ValueSimulation valueSimulation = (ValueSimulation)theEObject;
				T result = caseValueSimulation(valueSimulation);
				if (result == null) result = caseValueSpecification(valueSimulation);
				if (result == null) result = casePackageableElement(valueSimulation);
				if (result == null) result = caseTypedElement(valueSimulation);
				if (result == null) result = caseNamedElement(valueSimulation);
				if (result == null) result = caseParameterableElement(valueSimulation);
				if (result == null) result = caseElement(valueSimulation);
				if (result == null) result = caseEModelElement(valueSimulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.NORMAL_DISTRIBUTION: {
				NormalDistribution normalDistribution = (NormalDistribution)theEObject;
				T result = caseNormalDistribution(normalDistribution);
				if (result == null) result = caseNumericValueDistribution(normalDistribution);
				if (result == null) result = caseValueSimulation(normalDistribution);
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
				if (result == null) result = caseValueSimulation(uniformDistribution);
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
				if (result == null) result = caseValueSimulation(exponentialDistribution);
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
			case SimulationPackage.SIMULATED_SLOT: {
				SimulatedSlot simulatedSlot = (SimulatedSlot)theEObject;
				T result = caseSimulatedSlot(simulatedSlot);
				if (result == null) result = caseSlot(simulatedSlot);
				if (result == null) result = caseElement(simulatedSlot);
				if (result == null) result = caseEModelElement(simulatedSlot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.NUMERIC_VALUE_DISTRIBUTION: {
				NumericValueDistribution numericValueDistribution = (NumericValueDistribution)theEObject;
				T result = caseNumericValueDistribution(numericValueDistribution);
				if (result == null) result = caseValueSimulation(numericValueDistribution);
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
			case SimulationPackage.ENUM_LITERAL_SIMULATION: {
				EnumLiteralSimulation enumLiteralSimulation = (EnumLiteralSimulation)theEObject;
				T result = caseEnumLiteralSimulation(enumLiteralSimulation);
				if (result == null) result = caseValueSimulation(enumLiteralSimulation);
				if (result == null) result = caseValueSpecification(enumLiteralSimulation);
				if (result == null) result = casePackageableElement(enumLiteralSimulation);
				if (result == null) result = caseTypedElement(enumLiteralSimulation);
				if (result == null) result = caseNamedElement(enumLiteralSimulation);
				if (result == null) result = caseParameterableElement(enumLiteralSimulation);
				if (result == null) result = caseElement(enumLiteralSimulation);
				if (result == null) result = caseEModelElement(enumLiteralSimulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.CONTAINED_INSTANCE_VALUE_SIMULATION: {
				ContainedInstanceValueSimulation containedInstanceValueSimulation = (ContainedInstanceValueSimulation)theEObject;
				T result = caseContainedInstanceValueSimulation(containedInstanceValueSimulation);
				if (result == null) result = caseValueSimulation(containedInstanceValueSimulation);
				if (result == null) result = caseValueSpecification(containedInstanceValueSimulation);
				if (result == null) result = casePackageableElement(containedInstanceValueSimulation);
				if (result == null) result = caseTypedElement(containedInstanceValueSimulation);
				if (result == null) result = caseNamedElement(containedInstanceValueSimulation);
				if (result == null) result = caseParameterableElement(containedInstanceValueSimulation);
				if (result == null) result = caseElement(containedInstanceValueSimulation);
				if (result == null) result = caseEModelElement(containedInstanceValueSimulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.BOOLEAN_VALUE_SIMULATION: {
				BooleanValueSimulation booleanValueSimulation = (BooleanValueSimulation)theEObject;
				T result = caseBooleanValueSimulation(booleanValueSimulation);
				if (result == null) result = caseValueSimulation(booleanValueSimulation);
				if (result == null) result = caseValueSpecification(booleanValueSimulation);
				if (result == null) result = casePackageableElement(booleanValueSimulation);
				if (result == null) result = caseTypedElement(booleanValueSimulation);
				if (result == null) result = caseNamedElement(booleanValueSimulation);
				if (result == null) result = caseParameterableElement(booleanValueSimulation);
				if (result == null) result = caseElement(booleanValueSimulation);
				if (result == null) result = caseEModelElement(booleanValueSimulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.NUMBER_RANGE_DISTRIBUTION: {
				NumberRangeDistribution numberRangeDistribution = (NumberRangeDistribution)theEObject;
				T result = caseNumberRangeDistribution(numberRangeDistribution);
				if (result == null) result = caseNumericValueDistribution(numberRangeDistribution);
				if (result == null) result = caseValueSimulation(numberRangeDistribution);
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
			case SimulationPackage.REFERENCED_INSTANCE_SIMULATION: {
				ReferencedInstanceSimulation referencedInstanceSimulation = (ReferencedInstanceSimulation)theEObject;
				T result = caseReferencedInstanceSimulation(referencedInstanceSimulation);
				if (result == null) result = caseValueSimulation(referencedInstanceSimulation);
				if (result == null) result = caseValueSpecification(referencedInstanceSimulation);
				if (result == null) result = casePackageableElement(referencedInstanceSimulation);
				if (result == null) result = caseTypedElement(referencedInstanceSimulation);
				if (result == null) result = caseNamedElement(referencedInstanceSimulation);
				if (result == null) result = caseParameterableElement(referencedInstanceSimulation);
				if (result == null) result = caseElement(referencedInstanceSimulation);
				if (result == null) result = caseEModelElement(referencedInstanceSimulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.STRING_VALUE_SIMULATION: {
				StringValueSimulation stringValueSimulation = (StringValueSimulation)theEObject;
				T result = caseStringValueSimulation(stringValueSimulation);
				if (result == null) result = caseValueSimulation(stringValueSimulation);
				if (result == null) result = caseValueSpecification(stringValueSimulation);
				if (result == null) result = casePackageableElement(stringValueSimulation);
				if (result == null) result = caseTypedElement(stringValueSimulation);
				if (result == null) result = caseNamedElement(stringValueSimulation);
				if (result == null) result = caseParameterableElement(stringValueSimulation);
				if (result == null) result = caseElement(stringValueSimulation);
				if (result == null) result = caseEModelElement(stringValueSimulation);
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
			case SimulationPackage.ALL_INSTANCE_SIMULATION: {
				AllInstanceSimulation allInstanceSimulation = (AllInstanceSimulation)theEObject;
				T result = caseAllInstanceSimulation(allInstanceSimulation);
				if (result == null) result = caseInstanceSpecification(allInstanceSimulation);
				if (result == null) result = caseDeploymentTarget(allInstanceSimulation);
				if (result == null) result = casePackageableElement(allInstanceSimulation);
				if (result == null) result = caseDeployedArtifact(allInstanceSimulation);
				if (result == null) result = caseNamedElement(allInstanceSimulation);
				if (result == null) result = caseParameterableElement(allInstanceSimulation);
				if (result == null) result = caseElement(allInstanceSimulation);
				if (result == null) result = caseEModelElement(allInstanceSimulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SimulationPackage.ACTUAL_INSTANCE_SIMULATION: {
				ActualInstanceSimulation actualInstanceSimulation = (ActualInstanceSimulation)theEObject;
				T result = caseActualInstanceSimulation(actualInstanceSimulation);
				if (result == null) result = caseInstanceSpecification(actualInstanceSimulation);
				if (result == null) result = caseDeploymentTarget(actualInstanceSimulation);
				if (result == null) result = casePackageableElement(actualInstanceSimulation);
				if (result == null) result = caseDeployedArtifact(actualInstanceSimulation);
				if (result == null) result = caseNamedElement(actualInstanceSimulation);
				if (result == null) result = caseParameterableElement(actualInstanceSimulation);
				if (result == null) result = caseElement(actualInstanceSimulation);
				if (result == null) result = caseEModelElement(actualInstanceSimulation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Value Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Value Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseValueSimulation(ValueSimulation object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Simulated Slot</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Simulated Slot</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSimulatedSlot(SimulatedSlot object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Enum Literal Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enum Literal Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumLiteralSimulation(EnumLiteralSimulation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Contained Instance Value Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Contained Instance Value Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainedInstanceValueSimulation(ContainedInstanceValueSimulation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Value Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Value Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanValueSimulation(BooleanValueSimulation object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Referenced Instance Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Referenced Instance Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferencedInstanceSimulation(ReferencedInstanceSimulation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Value Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Value Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStringValueSimulation(StringValueSimulation object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>All Instance Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>All Instance Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAllInstanceSimulation(AllInstanceSimulation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Actual Instance Simulation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Actual Instance Simulation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActualInstanceSimulation(ActualInstanceSimulation object) {
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
