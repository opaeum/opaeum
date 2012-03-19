/**
 */
package org.opaeum.metamodels.simulation.simulation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.uml2.uml.UMLPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.opaeum.metamodels.simulation.simulation.SimulationFactory
 * @model kind="package"
 * @generated
 */
public interface SimulationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "simulation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://simulation/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "simulation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SimulationPackage eINSTANCE = org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.ValueSimulationImpl <em>Value Simulation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.ValueSimulationImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getValueSimulation()
	 * @generated
	 */
	int VALUE_SIMULATION = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__EANNOTATIONS = UMLPackage.VALUE_SPECIFICATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__OWNED_COMMENT = UMLPackage.VALUE_SPECIFICATION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__OWNED_ELEMENT = UMLPackage.VALUE_SPECIFICATION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__OWNER = UMLPackage.VALUE_SPECIFICATION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__CLIENT_DEPENDENCY = UMLPackage.VALUE_SPECIFICATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__NAME = UMLPackage.VALUE_SPECIFICATION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__NAME_EXPRESSION = UMLPackage.VALUE_SPECIFICATION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__NAMESPACE = UMLPackage.VALUE_SPECIFICATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__QUALIFIED_NAME = UMLPackage.VALUE_SPECIFICATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__VISIBILITY = UMLPackage.VALUE_SPECIFICATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__OWNING_TEMPLATE_PARAMETER = UMLPackage.VALUE_SPECIFICATION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__TEMPLATE_PARAMETER = UMLPackage.VALUE_SPECIFICATION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION__TYPE = UMLPackage.VALUE_SPECIFICATION__TYPE;

	/**
	 * The number of structural features of the '<em>Value Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_SIMULATION_FEATURE_COUNT = UMLPackage.VALUE_SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.NumericValueDistributionImpl <em>Numeric Value Distribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.NumericValueDistributionImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getNumericValueDistribution()
	 * @generated
	 */
	int NUMERIC_VALUE_DISTRIBUTION = 5;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__EANNOTATIONS = VALUE_SIMULATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__OWNED_COMMENT = VALUE_SIMULATION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__OWNED_ELEMENT = VALUE_SIMULATION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__OWNER = VALUE_SIMULATION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__CLIENT_DEPENDENCY = VALUE_SIMULATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__NAME = VALUE_SIMULATION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__NAME_EXPRESSION = VALUE_SIMULATION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__NAMESPACE = VALUE_SIMULATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__QUALIFIED_NAME = VALUE_SIMULATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__VISIBILITY = VALUE_SIMULATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__OWNING_TEMPLATE_PARAMETER = VALUE_SIMULATION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__TEMPLATE_PARAMETER = VALUE_SIMULATION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__TYPE = VALUE_SIMULATION__TYPE;

	/**
	 * The number of structural features of the '<em>Numeric Value Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT = VALUE_SIMULATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.NormalDistributionImpl <em>Normal Distribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.NormalDistributionImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getNormalDistribution()
	 * @generated
	 */
	int NORMAL_DISTRIBUTION = 1;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__EANNOTATIONS = NUMERIC_VALUE_DISTRIBUTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__OWNED_COMMENT = NUMERIC_VALUE_DISTRIBUTION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__OWNED_ELEMENT = NUMERIC_VALUE_DISTRIBUTION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__OWNER = NUMERIC_VALUE_DISTRIBUTION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__CLIENT_DEPENDENCY = NUMERIC_VALUE_DISTRIBUTION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__NAME = NUMERIC_VALUE_DISTRIBUTION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__NAME_EXPRESSION = NUMERIC_VALUE_DISTRIBUTION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__NAMESPACE = NUMERIC_VALUE_DISTRIBUTION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__QUALIFIED_NAME = NUMERIC_VALUE_DISTRIBUTION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__VISIBILITY = NUMERIC_VALUE_DISTRIBUTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__OWNING_TEMPLATE_PARAMETER = NUMERIC_VALUE_DISTRIBUTION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__TEMPLATE_PARAMETER = NUMERIC_VALUE_DISTRIBUTION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__TYPE = NUMERIC_VALUE_DISTRIBUTION__TYPE;

	/**
	 * The feature id for the '<em><b>Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__MEAN = NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Standard Deviation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION__STANDARD_DEVIATION = NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Normal Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NORMAL_DISTRIBUTION_FEATURE_COUNT = NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.UniformDistributionImpl <em>Uniform Distribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.UniformDistributionImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getUniformDistribution()
	 * @generated
	 */
	int UNIFORM_DISTRIBUTION = 2;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__EANNOTATIONS = NUMERIC_VALUE_DISTRIBUTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__OWNED_COMMENT = NUMERIC_VALUE_DISTRIBUTION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__OWNED_ELEMENT = NUMERIC_VALUE_DISTRIBUTION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__OWNER = NUMERIC_VALUE_DISTRIBUTION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__CLIENT_DEPENDENCY = NUMERIC_VALUE_DISTRIBUTION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__NAME = NUMERIC_VALUE_DISTRIBUTION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__NAME_EXPRESSION = NUMERIC_VALUE_DISTRIBUTION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__NAMESPACE = NUMERIC_VALUE_DISTRIBUTION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__QUALIFIED_NAME = NUMERIC_VALUE_DISTRIBUTION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__VISIBILITY = NUMERIC_VALUE_DISTRIBUTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__OWNING_TEMPLATE_PARAMETER = NUMERIC_VALUE_DISTRIBUTION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__TEMPLATE_PARAMETER = NUMERIC_VALUE_DISTRIBUTION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__TYPE = NUMERIC_VALUE_DISTRIBUTION__TYPE;

	/**
	 * The feature id for the '<em><b>Lower Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__LOWER_LIMIT = NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Upper Limit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION__UPPER_LIMIT = NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Uniform Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNIFORM_DISTRIBUTION_FEATURE_COUNT = NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.ExponentialDistributionImpl <em>Exponential Distribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.ExponentialDistributionImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getExponentialDistribution()
	 * @generated
	 */
	int EXPONENTIAL_DISTRIBUTION = 3;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__EANNOTATIONS = NUMERIC_VALUE_DISTRIBUTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__OWNED_COMMENT = NUMERIC_VALUE_DISTRIBUTION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__OWNED_ELEMENT = NUMERIC_VALUE_DISTRIBUTION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__OWNER = NUMERIC_VALUE_DISTRIBUTION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__CLIENT_DEPENDENCY = NUMERIC_VALUE_DISTRIBUTION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__NAME = NUMERIC_VALUE_DISTRIBUTION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__NAME_EXPRESSION = NUMERIC_VALUE_DISTRIBUTION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__NAMESPACE = NUMERIC_VALUE_DISTRIBUTION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__QUALIFIED_NAME = NUMERIC_VALUE_DISTRIBUTION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__VISIBILITY = NUMERIC_VALUE_DISTRIBUTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__OWNING_TEMPLATE_PARAMETER = NUMERIC_VALUE_DISTRIBUTION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__TEMPLATE_PARAMETER = NUMERIC_VALUE_DISTRIBUTION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__TYPE = NUMERIC_VALUE_DISTRIBUTION__TYPE;

	/**
	 * The feature id for the '<em><b>Mean</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION__MEAN = NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Exponential Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIAL_DISTRIBUTION_FEATURE_COUNT = NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.SimulatedSlotImpl <em>Simulated Slot</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulatedSlotImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getSimulatedSlot()
	 * @generated
	 */
	int SIMULATED_SLOT = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_SLOT__EANNOTATIONS = UMLPackage.SLOT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_SLOT__OWNED_COMMENT = UMLPackage.SLOT__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_SLOT__OWNED_ELEMENT = UMLPackage.SLOT__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_SLOT__OWNER = UMLPackage.SLOT__OWNER;

	/**
	 * The feature id for the '<em><b>Defining Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_SLOT__DEFINING_FEATURE = UMLPackage.SLOT__DEFINING_FEATURE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_SLOT__VALUE = UMLPackage.SLOT__VALUE;

	/**
	 * The feature id for the '<em><b>Owning Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_SLOT__OWNING_INSTANCE = UMLPackage.SLOT__OWNING_INSTANCE;

	/**
	 * The feature id for the '<em><b>Size Distribution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_SLOT__SIZE_DISTRIBUTION = UMLPackage.SLOT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_SLOT__PROPERTY = UMLPackage.SLOT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Simulated Slot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_SLOT_FEATURE_COUNT = UMLPackage.SLOT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.EnumLiteralSimulationImpl <em>Enum Literal Simulation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.EnumLiteralSimulationImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getEnumLiteralSimulation()
	 * @generated
	 */
	int ENUM_LITERAL_SIMULATION = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__EANNOTATIONS = VALUE_SIMULATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__OWNED_COMMENT = VALUE_SIMULATION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__OWNED_ELEMENT = VALUE_SIMULATION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__OWNER = VALUE_SIMULATION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__CLIENT_DEPENDENCY = VALUE_SIMULATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__NAME = VALUE_SIMULATION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__NAME_EXPRESSION = VALUE_SIMULATION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__NAMESPACE = VALUE_SIMULATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__QUALIFIED_NAME = VALUE_SIMULATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__VISIBILITY = VALUE_SIMULATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__OWNING_TEMPLATE_PARAMETER = VALUE_SIMULATION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__TEMPLATE_PARAMETER = VALUE_SIMULATION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__TYPE = VALUE_SIMULATION__TYPE;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION__WEIGHT = VALUE_SIMULATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Enum Literal Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_LITERAL_SIMULATION_FEATURE_COUNT = VALUE_SIMULATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedInstanceValueSimulationImpl <em>Contained Instance Value Simulation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.ContainedInstanceValueSimulationImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getContainedInstanceValueSimulation()
	 * @generated
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION = 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__EANNOTATIONS = VALUE_SIMULATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__OWNED_COMMENT = VALUE_SIMULATION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__OWNED_ELEMENT = VALUE_SIMULATION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__OWNER = VALUE_SIMULATION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__CLIENT_DEPENDENCY = VALUE_SIMULATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__NAME = VALUE_SIMULATION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__NAME_EXPRESSION = VALUE_SIMULATION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__NAMESPACE = VALUE_SIMULATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__QUALIFIED_NAME = VALUE_SIMULATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__VISIBILITY = VALUE_SIMULATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__OWNING_TEMPLATE_PARAMETER = VALUE_SIMULATION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__TEMPLATE_PARAMETER = VALUE_SIMULATION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__TYPE = VALUE_SIMULATION__TYPE;

	/**
	 * The feature id for the '<em><b>Deployed Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYED_ELEMENT = VALUE_SIMULATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__DEPLOYMENT = VALUE_SIMULATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Classifier</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__CLASSIFIER = VALUE_SIMULATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Slot</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__SLOT = VALUE_SIMULATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__SPECIFICATION = VALUE_SIMULATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES = VALUE_SIMULATION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Contained Instance Value Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_INSTANCE_VALUE_SIMULATION_FEATURE_COUNT = VALUE_SIMULATION_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.BooleanValueSimulationImpl <em>Boolean Value Simulation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.BooleanValueSimulationImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getBooleanValueSimulation()
	 * @generated
	 */
	int BOOLEAN_VALUE_SIMULATION = 8;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__EANNOTATIONS = VALUE_SIMULATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__OWNED_COMMENT = VALUE_SIMULATION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__OWNED_ELEMENT = VALUE_SIMULATION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__OWNER = VALUE_SIMULATION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__CLIENT_DEPENDENCY = VALUE_SIMULATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__NAME = VALUE_SIMULATION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__NAME_EXPRESSION = VALUE_SIMULATION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__NAMESPACE = VALUE_SIMULATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__QUALIFIED_NAME = VALUE_SIMULATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__VISIBILITY = VALUE_SIMULATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__OWNING_TEMPLATE_PARAMETER = VALUE_SIMULATION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__TEMPLATE_PARAMETER = VALUE_SIMULATION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__TYPE = VALUE_SIMULATION__TYPE;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__WEIGHT = VALUE_SIMULATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION__VALUE = VALUE_SIMULATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Boolean Value Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_VALUE_SIMULATION_FEATURE_COUNT = VALUE_SIMULATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.NumberRangeDistributionImpl <em>Number Range Distribution</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.NumberRangeDistributionImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getNumberRangeDistribution()
	 * @generated
	 */
	int NUMBER_RANGE_DISTRIBUTION = 9;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__EANNOTATIONS = NUMERIC_VALUE_DISTRIBUTION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__OWNED_COMMENT = NUMERIC_VALUE_DISTRIBUTION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__OWNED_ELEMENT = NUMERIC_VALUE_DISTRIBUTION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__OWNER = NUMERIC_VALUE_DISTRIBUTION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__CLIENT_DEPENDENCY = NUMERIC_VALUE_DISTRIBUTION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__NAME = NUMERIC_VALUE_DISTRIBUTION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__NAME_EXPRESSION = NUMERIC_VALUE_DISTRIBUTION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__NAMESPACE = NUMERIC_VALUE_DISTRIBUTION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__QUALIFIED_NAME = NUMERIC_VALUE_DISTRIBUTION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__VISIBILITY = NUMERIC_VALUE_DISTRIBUTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__OWNING_TEMPLATE_PARAMETER = NUMERIC_VALUE_DISTRIBUTION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__TEMPLATE_PARAMETER = NUMERIC_VALUE_DISTRIBUTION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__TYPE = NUMERIC_VALUE_DISTRIBUTION__TYPE;

	/**
	 * The feature id for the '<em><b>Upper Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__UPPER_VALUE = NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Lower Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__LOWER_VALUE = NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION__WEIGHT = NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Number Range Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_RANGE_DISTRIBUTION_FEATURE_COUNT = NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.ReferencedInstanceSimulationImpl <em>Referenced Instance Simulation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.ReferencedInstanceSimulationImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getReferencedInstanceSimulation()
	 * @generated
	 */
	int REFERENCED_INSTANCE_SIMULATION = 10;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__EANNOTATIONS = VALUE_SIMULATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__OWNED_COMMENT = VALUE_SIMULATION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__OWNED_ELEMENT = VALUE_SIMULATION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__OWNER = VALUE_SIMULATION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__CLIENT_DEPENDENCY = VALUE_SIMULATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__NAME = VALUE_SIMULATION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__NAME_EXPRESSION = VALUE_SIMULATION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__NAMESPACE = VALUE_SIMULATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__QUALIFIED_NAME = VALUE_SIMULATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__VISIBILITY = VALUE_SIMULATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__OWNING_TEMPLATE_PARAMETER = VALUE_SIMULATION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__TEMPLATE_PARAMETER = VALUE_SIMULATION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__TYPE = VALUE_SIMULATION__TYPE;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__WEIGHT = VALUE_SIMULATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION__INSTANCE = VALUE_SIMULATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Referenced Instance Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCED_INSTANCE_SIMULATION_FEATURE_COUNT = VALUE_SIMULATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.StringValueSimulationImpl <em>String Value Simulation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.StringValueSimulationImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getStringValueSimulation()
	 * @generated
	 */
	int STRING_VALUE_SIMULATION = 11;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__EANNOTATIONS = VALUE_SIMULATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__OWNED_COMMENT = VALUE_SIMULATION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__OWNED_ELEMENT = VALUE_SIMULATION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__OWNER = VALUE_SIMULATION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__CLIENT_DEPENDENCY = VALUE_SIMULATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__NAME = VALUE_SIMULATION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__NAME_EXPRESSION = VALUE_SIMULATION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__NAMESPACE = VALUE_SIMULATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__QUALIFIED_NAME = VALUE_SIMULATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__VISIBILITY = VALUE_SIMULATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__OWNING_TEMPLATE_PARAMETER = VALUE_SIMULATION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__TEMPLATE_PARAMETER = VALUE_SIMULATION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__TYPE = VALUE_SIMULATION__TYPE;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__WEIGHT = VALUE_SIMULATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION__VALUE = VALUE_SIMULATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>String Value Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_VALUE_SIMULATION_FEATURE_COUNT = VALUE_SIMULATION_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.ValueSimulation <em>Value Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Simulation</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.ValueSimulation
	 * @generated
	 */
	EClass getValueSimulation();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.NormalDistribution <em>Normal Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Normal Distribution</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.NormalDistribution
	 * @generated
	 */
	EClass getNormalDistribution();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.NormalDistribution#getMean <em>Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mean</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.NormalDistribution#getMean()
	 * @see #getNormalDistribution()
	 * @generated
	 */
	EAttribute getNormalDistribution_Mean();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.NormalDistribution#getStandardDeviation <em>Standard Deviation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Standard Deviation</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.NormalDistribution#getStandardDeviation()
	 * @see #getNormalDistribution()
	 * @generated
	 */
	EAttribute getNormalDistribution_StandardDeviation();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.UniformDistribution <em>Uniform Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Uniform Distribution</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.UniformDistribution
	 * @generated
	 */
	EClass getUniformDistribution();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.UniformDistribution#getLowerLimit <em>Lower Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Limit</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.UniformDistribution#getLowerLimit()
	 * @see #getUniformDistribution()
	 * @generated
	 */
	EAttribute getUniformDistribution_LowerLimit();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.UniformDistribution#getUpperLimit <em>Upper Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Limit</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.UniformDistribution#getUpperLimit()
	 * @see #getUniformDistribution()
	 * @generated
	 */
	EAttribute getUniformDistribution_UpperLimit();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.ExponentialDistribution <em>Exponential Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exponential Distribution</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.ExponentialDistribution
	 * @generated
	 */
	EClass getExponentialDistribution();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.ExponentialDistribution#getMean <em>Mean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mean</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.ExponentialDistribution#getMean()
	 * @see #getExponentialDistribution()
	 * @generated
	 */
	EAttribute getExponentialDistribution_Mean();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.SimulatedSlot <em>Simulated Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simulated Slot</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulatedSlot
	 * @generated
	 */
	EClass getSimulatedSlot();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.metamodels.simulation.simulation.SimulatedSlot#getSizeDistribution <em>Size Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Size Distribution</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulatedSlot#getSizeDistribution()
	 * @see #getSimulatedSlot()
	 * @generated
	 */
	EReference getSimulatedSlot_SizeDistribution();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.metamodels.simulation.simulation.SimulatedSlot#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Property</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulatedSlot#getProperty()
	 * @see #getSimulatedSlot()
	 * @generated
	 */
	EReference getSimulatedSlot_Property();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.NumericValueDistribution <em>Numeric Value Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Numeric Value Distribution</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.NumericValueDistribution
	 * @generated
	 */
	EClass getNumericValueDistribution();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.EnumLiteralSimulation <em>Enum Literal Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Literal Simulation</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.EnumLiteralSimulation
	 * @generated
	 */
	EClass getEnumLiteralSimulation();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.EnumLiteralSimulation#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.EnumLiteralSimulation#getWeight()
	 * @see #getEnumLiteralSimulation()
	 * @generated
	 */
	EAttribute getEnumLiteralSimulation_Weight();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation <em>Contained Instance Value Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contained Instance Value Simulation</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation
	 * @generated
	 */
	EClass getContainedInstanceValueSimulation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.ContainedInstanceValueSimulation#getValues()
	 * @see #getContainedInstanceValueSimulation()
	 * @generated
	 */
	EReference getContainedInstanceValueSimulation_Values();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.BooleanValueSimulation <em>Boolean Value Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Value Simulation</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.BooleanValueSimulation
	 * @generated
	 */
	EClass getBooleanValueSimulation();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.BooleanValueSimulation#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.BooleanValueSimulation#getWeight()
	 * @see #getBooleanValueSimulation()
	 * @generated
	 */
	EAttribute getBooleanValueSimulation_Weight();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.BooleanValueSimulation#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.BooleanValueSimulation#isValue()
	 * @see #getBooleanValueSimulation()
	 * @generated
	 */
	EAttribute getBooleanValueSimulation_Value();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution <em>Number Range Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Number Range Distribution</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution
	 * @generated
	 */
	EClass getNumberRangeDistribution();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution#getUpperValue <em>Upper Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution#getUpperValue()
	 * @see #getNumberRangeDistribution()
	 * @generated
	 */
	EAttribute getNumberRangeDistribution_UpperValue();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution#getLowerValue <em>Lower Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution#getLowerValue()
	 * @see #getNumberRangeDistribution()
	 * @generated
	 */
	EAttribute getNumberRangeDistribution_LowerValue();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.NumberRangeDistribution#getWeight()
	 * @see #getNumberRangeDistribution()
	 * @generated
	 */
	EAttribute getNumberRangeDistribution_Weight();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.ReferencedInstanceSimulation <em>Referenced Instance Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Referenced Instance Simulation</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.ReferencedInstanceSimulation
	 * @generated
	 */
	EClass getReferencedInstanceSimulation();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.ReferencedInstanceSimulation#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.ReferencedInstanceSimulation#getWeight()
	 * @see #getReferencedInstanceSimulation()
	 * @generated
	 */
	EAttribute getReferencedInstanceSimulation_Weight();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.metamodels.simulation.simulation.ReferencedInstanceSimulation#getInstance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Instance</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.ReferencedInstanceSimulation#getInstance()
	 * @see #getReferencedInstanceSimulation()
	 * @generated
	 */
	EReference getReferencedInstanceSimulation_Instance();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.StringValueSimulation <em>String Value Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Value Simulation</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.StringValueSimulation
	 * @generated
	 */
	EClass getStringValueSimulation();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.StringValueSimulation#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.StringValueSimulation#getWeight()
	 * @see #getStringValueSimulation()
	 * @generated
	 */
	EAttribute getStringValueSimulation_Weight();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.StringValueSimulation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.StringValueSimulation#getValue()
	 * @see #getStringValueSimulation()
	 * @generated
	 */
	EAttribute getStringValueSimulation_Value();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SimulationFactory getSimulationFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.ValueSimulationImpl <em>Value Simulation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.ValueSimulationImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getValueSimulation()
		 * @generated
		 */
		EClass VALUE_SIMULATION = eINSTANCE.getValueSimulation();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.NormalDistributionImpl <em>Normal Distribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.NormalDistributionImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getNormalDistribution()
		 * @generated
		 */
		EClass NORMAL_DISTRIBUTION = eINSTANCE.getNormalDistribution();

		/**
		 * The meta object literal for the '<em><b>Mean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NORMAL_DISTRIBUTION__MEAN = eINSTANCE.getNormalDistribution_Mean();

		/**
		 * The meta object literal for the '<em><b>Standard Deviation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NORMAL_DISTRIBUTION__STANDARD_DEVIATION = eINSTANCE.getNormalDistribution_StandardDeviation();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.UniformDistributionImpl <em>Uniform Distribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.UniformDistributionImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getUniformDistribution()
		 * @generated
		 */
		EClass UNIFORM_DISTRIBUTION = eINSTANCE.getUniformDistribution();

		/**
		 * The meta object literal for the '<em><b>Lower Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIFORM_DISTRIBUTION__LOWER_LIMIT = eINSTANCE.getUniformDistribution_LowerLimit();

		/**
		 * The meta object literal for the '<em><b>Upper Limit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNIFORM_DISTRIBUTION__UPPER_LIMIT = eINSTANCE.getUniformDistribution_UpperLimit();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.ExponentialDistributionImpl <em>Exponential Distribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.ExponentialDistributionImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getExponentialDistribution()
		 * @generated
		 */
		EClass EXPONENTIAL_DISTRIBUTION = eINSTANCE.getExponentialDistribution();

		/**
		 * The meta object literal for the '<em><b>Mean</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXPONENTIAL_DISTRIBUTION__MEAN = eINSTANCE.getExponentialDistribution_Mean();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.SimulatedSlotImpl <em>Simulated Slot</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulatedSlotImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getSimulatedSlot()
		 * @generated
		 */
		EClass SIMULATED_SLOT = eINSTANCE.getSimulatedSlot();

		/**
		 * The meta object literal for the '<em><b>Size Distribution</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMULATED_SLOT__SIZE_DISTRIBUTION = eINSTANCE.getSimulatedSlot_SizeDistribution();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMULATED_SLOT__PROPERTY = eINSTANCE.getSimulatedSlot_Property();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.NumericValueDistributionImpl <em>Numeric Value Distribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.NumericValueDistributionImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getNumericValueDistribution()
		 * @generated
		 */
		EClass NUMERIC_VALUE_DISTRIBUTION = eINSTANCE.getNumericValueDistribution();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.EnumLiteralSimulationImpl <em>Enum Literal Simulation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.EnumLiteralSimulationImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getEnumLiteralSimulation()
		 * @generated
		 */
		EClass ENUM_LITERAL_SIMULATION = eINSTANCE.getEnumLiteralSimulation();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUM_LITERAL_SIMULATION__WEIGHT = eINSTANCE.getEnumLiteralSimulation_Weight();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedInstanceValueSimulationImpl <em>Contained Instance Value Simulation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.ContainedInstanceValueSimulationImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getContainedInstanceValueSimulation()
		 * @generated
		 */
		EClass CONTAINED_INSTANCE_VALUE_SIMULATION = eINSTANCE.getContainedInstanceValueSimulation();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINED_INSTANCE_VALUE_SIMULATION__VALUES = eINSTANCE.getContainedInstanceValueSimulation_Values();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.BooleanValueSimulationImpl <em>Boolean Value Simulation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.BooleanValueSimulationImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getBooleanValueSimulation()
		 * @generated
		 */
		EClass BOOLEAN_VALUE_SIMULATION = eINSTANCE.getBooleanValueSimulation();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_VALUE_SIMULATION__WEIGHT = eINSTANCE.getBooleanValueSimulation_Weight();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_VALUE_SIMULATION__VALUE = eINSTANCE.getBooleanValueSimulation_Value();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.NumberRangeDistributionImpl <em>Number Range Distribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.NumberRangeDistributionImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getNumberRangeDistribution()
		 * @generated
		 */
		EClass NUMBER_RANGE_DISTRIBUTION = eINSTANCE.getNumberRangeDistribution();

		/**
		 * The meta object literal for the '<em><b>Upper Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMBER_RANGE_DISTRIBUTION__UPPER_VALUE = eINSTANCE.getNumberRangeDistribution_UpperValue();

		/**
		 * The meta object literal for the '<em><b>Lower Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMBER_RANGE_DISTRIBUTION__LOWER_VALUE = eINSTANCE.getNumberRangeDistribution_LowerValue();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMBER_RANGE_DISTRIBUTION__WEIGHT = eINSTANCE.getNumberRangeDistribution_Weight();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.ReferencedInstanceSimulationImpl <em>Referenced Instance Simulation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.ReferencedInstanceSimulationImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getReferencedInstanceSimulation()
		 * @generated
		 */
		EClass REFERENCED_INSTANCE_SIMULATION = eINSTANCE.getReferencedInstanceSimulation();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REFERENCED_INSTANCE_SIMULATION__WEIGHT = eINSTANCE.getReferencedInstanceSimulation_Weight();

		/**
		 * The meta object literal for the '<em><b>Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCED_INSTANCE_SIMULATION__INSTANCE = eINSTANCE.getReferencedInstanceSimulation_Instance();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.StringValueSimulationImpl <em>String Value Simulation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.StringValueSimulationImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getStringValueSimulation()
		 * @generated
		 */
		EClass STRING_VALUE_SIMULATION = eINSTANCE.getStringValueSimulation();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_VALUE_SIMULATION__WEIGHT = eINSTANCE.getStringValueSimulation_Weight();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_VALUE_SIMULATION__VALUE = eINSTANCE.getStringValueSimulation_Value();

	}

} //SimulationPackage
