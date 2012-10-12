/**
 */
package org.opaeum.metamodels.simulation.simulation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.SimulatedValueImpl <em>Simulated Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulatedValueImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getSimulatedValue()
	 * @generated
	 */
	int SIMULATED_VALUE = 0;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__EANNOTATIONS = UMLPackage.VALUE_SPECIFICATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__OWNED_COMMENT = UMLPackage.VALUE_SPECIFICATION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__OWNED_ELEMENT = UMLPackage.VALUE_SPECIFICATION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__OWNER = UMLPackage.VALUE_SPECIFICATION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__CLIENT_DEPENDENCY = UMLPackage.VALUE_SPECIFICATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__NAME = UMLPackage.VALUE_SPECIFICATION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__NAME_EXPRESSION = UMLPackage.VALUE_SPECIFICATION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__NAMESPACE = UMLPackage.VALUE_SPECIFICATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__QUALIFIED_NAME = UMLPackage.VALUE_SPECIFICATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__VISIBILITY = UMLPackage.VALUE_SPECIFICATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__OWNING_TEMPLATE_PARAMETER = UMLPackage.VALUE_SPECIFICATION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__TEMPLATE_PARAMETER = UMLPackage.VALUE_SPECIFICATION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE__TYPE = UMLPackage.VALUE_SPECIFICATION__TYPE;

	/**
	 * The number of structural features of the '<em>Simulated Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATED_VALUE_FEATURE_COUNT = UMLPackage.VALUE_SPECIFICATION_FEATURE_COUNT + 0;

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
	int NUMERIC_VALUE_DISTRIBUTION__EANNOTATIONS = SIMULATED_VALUE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__OWNED_COMMENT = SIMULATED_VALUE__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__OWNED_ELEMENT = SIMULATED_VALUE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__OWNER = SIMULATED_VALUE__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__CLIENT_DEPENDENCY = SIMULATED_VALUE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__NAME = SIMULATED_VALUE__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__NAME_EXPRESSION = SIMULATED_VALUE__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__NAMESPACE = SIMULATED_VALUE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__QUALIFIED_NAME = SIMULATED_VALUE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__VISIBILITY = SIMULATED_VALUE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__OWNING_TEMPLATE_PARAMETER = SIMULATED_VALUE__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__TEMPLATE_PARAMETER = SIMULATED_VALUE__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION__TYPE = SIMULATED_VALUE__TYPE;

	/**
	 * The number of structural features of the '<em>Numeric Value Distribution</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_VALUE_DISTRIBUTION_FEATURE_COUNT = SIMULATED_VALUE_FEATURE_COUNT + 0;

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
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.SimulatingSlotImpl <em>Simulating Slot</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulatingSlotImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getSimulatingSlot()
	 * @generated
	 */
	int SIMULATING_SLOT = 4;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATING_SLOT__EANNOTATIONS = UMLPackage.SLOT__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATING_SLOT__OWNED_COMMENT = UMLPackage.SLOT__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATING_SLOT__OWNED_ELEMENT = UMLPackage.SLOT__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATING_SLOT__OWNER = UMLPackage.SLOT__OWNER;

	/**
	 * The feature id for the '<em><b>Defining Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATING_SLOT__DEFINING_FEATURE = UMLPackage.SLOT__DEFINING_FEATURE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATING_SLOT__VALUE = UMLPackage.SLOT__VALUE;

	/**
	 * The feature id for the '<em><b>Owning Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATING_SLOT__OWNING_INSTANCE = UMLPackage.SLOT__OWNING_INSTANCE;

	/**
	 * The feature id for the '<em><b>Size Distribution</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATING_SLOT__SIZE_DISTRIBUTION = UMLPackage.SLOT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Simulation Strategy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATING_SLOT__SIMULATION_STRATEGY = UMLPackage.SLOT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Simulating Slot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATING_SLOT_FEATURE_COUNT = UMLPackage.SLOT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.WeightedEnumLiteralValueImpl <em>Weighted Enum Literal Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.WeightedEnumLiteralValueImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getWeightedEnumLiteralValue()
	 * @generated
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE = 6;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__EANNOTATIONS = SIMULATED_VALUE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__OWNED_COMMENT = SIMULATED_VALUE__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__OWNED_ELEMENT = SIMULATED_VALUE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__OWNER = SIMULATED_VALUE__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__CLIENT_DEPENDENCY = SIMULATED_VALUE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__NAME = SIMULATED_VALUE__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__NAME_EXPRESSION = SIMULATED_VALUE__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__NAMESPACE = SIMULATED_VALUE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__QUALIFIED_NAME = SIMULATED_VALUE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__VISIBILITY = SIMULATED_VALUE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__OWNING_TEMPLATE_PARAMETER = SIMULATED_VALUE__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__TEMPLATE_PARAMETER = SIMULATED_VALUE__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__TYPE = SIMULATED_VALUE__TYPE;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__WEIGHT = SIMULATED_VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Literal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE__LITERAL = SIMULATED_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Weighted Enum Literal Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_ENUM_LITERAL_VALUE_FEATURE_COUNT = SIMULATED_VALUE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedActualInstanceImpl <em>Contained Actual Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.ContainedActualInstanceImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getContainedActualInstance()
	 * @generated
	 */
	int CONTAINED_ACTUAL_INSTANCE = 7;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__EANNOTATIONS = SIMULATED_VALUE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__OWNED_COMMENT = SIMULATED_VALUE__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__OWNED_ELEMENT = SIMULATED_VALUE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__OWNER = SIMULATED_VALUE__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__CLIENT_DEPENDENCY = SIMULATED_VALUE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__NAME = SIMULATED_VALUE__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__NAME_EXPRESSION = SIMULATED_VALUE__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__NAMESPACE = SIMULATED_VALUE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__QUALIFIED_NAME = SIMULATED_VALUE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__VISIBILITY = SIMULATED_VALUE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__OWNING_TEMPLATE_PARAMETER = SIMULATED_VALUE__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__TEMPLATE_PARAMETER = SIMULATED_VALUE__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__TYPE = SIMULATED_VALUE__TYPE;

	/**
	 * The feature id for the '<em><b>Values</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__VALUES = SIMULATED_VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contained Instance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE__CONTAINED_INSTANCE = SIMULATED_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Contained Actual Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINED_ACTUAL_INSTANCE_FEATURE_COUNT = SIMULATED_VALUE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.WeightedBooleanValueImpl <em>Weighted Boolean Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.WeightedBooleanValueImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getWeightedBooleanValue()
	 * @generated
	 */
	int WEIGHTED_BOOLEAN_VALUE = 8;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__EANNOTATIONS = SIMULATED_VALUE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__OWNED_COMMENT = SIMULATED_VALUE__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__OWNED_ELEMENT = SIMULATED_VALUE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__OWNER = SIMULATED_VALUE__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__CLIENT_DEPENDENCY = SIMULATED_VALUE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__NAME = SIMULATED_VALUE__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__NAME_EXPRESSION = SIMULATED_VALUE__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__NAMESPACE = SIMULATED_VALUE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__QUALIFIED_NAME = SIMULATED_VALUE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__VISIBILITY = SIMULATED_VALUE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__OWNING_TEMPLATE_PARAMETER = SIMULATED_VALUE__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__TEMPLATE_PARAMETER = SIMULATED_VALUE__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__TYPE = SIMULATED_VALUE__TYPE;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__WEIGHT = SIMULATED_VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE__VALUE = SIMULATED_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Weighted Boolean Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_BOOLEAN_VALUE_FEATURE_COUNT = SIMULATED_VALUE_FEATURE_COUNT + 2;

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
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.WeightedStringValueImpl <em>Weighted String Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.WeightedStringValueImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getWeightedStringValue()
	 * @generated
	 */
	int WEIGHTED_STRING_VALUE = 10;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__EANNOTATIONS = SIMULATED_VALUE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__OWNED_COMMENT = SIMULATED_VALUE__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__OWNED_ELEMENT = SIMULATED_VALUE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__OWNER = SIMULATED_VALUE__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__CLIENT_DEPENDENCY = SIMULATED_VALUE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__NAME = SIMULATED_VALUE__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__NAME_EXPRESSION = SIMULATED_VALUE__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__NAMESPACE = SIMULATED_VALUE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__QUALIFIED_NAME = SIMULATED_VALUE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__VISIBILITY = SIMULATED_VALUE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__OWNING_TEMPLATE_PARAMETER = SIMULATED_VALUE__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__TEMPLATE_PARAMETER = SIMULATED_VALUE__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__TYPE = SIMULATED_VALUE__TYPE;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__WEIGHT = SIMULATED_VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE__VALUE = SIMULATED_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Weighted String Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_STRING_VALUE_FEATURE_COUNT = SIMULATED_VALUE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.SimulationModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationModelImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getSimulationModel()
	 * @generated
	 */
	int SIMULATION_MODEL = 11;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__EANNOTATIONS = UMLPackage.PACKAGE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__OWNED_COMMENT = UMLPackage.PACKAGE__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__OWNED_ELEMENT = UMLPackage.PACKAGE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__OWNER = UMLPackage.PACKAGE__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__CLIENT_DEPENDENCY = UMLPackage.PACKAGE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__NAME = UMLPackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__NAME_EXPRESSION = UMLPackage.PACKAGE__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__NAMESPACE = UMLPackage.PACKAGE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__QUALIFIED_NAME = UMLPackage.PACKAGE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__VISIBILITY = UMLPackage.PACKAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Element Import</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__ELEMENT_IMPORT = UMLPackage.PACKAGE__ELEMENT_IMPORT;

	/**
	 * The feature id for the '<em><b>Package Import</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__PACKAGE_IMPORT = UMLPackage.PACKAGE__PACKAGE_IMPORT;

	/**
	 * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__OWNED_RULE = UMLPackage.PACKAGE__OWNED_RULE;

	/**
	 * The feature id for the '<em><b>Owned Member</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__OWNED_MEMBER = UMLPackage.PACKAGE__OWNED_MEMBER;

	/**
	 * The feature id for the '<em><b>Imported Member</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__IMPORTED_MEMBER = UMLPackage.PACKAGE__IMPORTED_MEMBER;

	/**
	 * The feature id for the '<em><b>Member</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__MEMBER = UMLPackage.PACKAGE__MEMBER;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__OWNING_TEMPLATE_PARAMETER = UMLPackage.PACKAGE__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__TEMPLATE_PARAMETER = UMLPackage.PACKAGE__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Owned Template Signature</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__OWNED_TEMPLATE_SIGNATURE = UMLPackage.PACKAGE__OWNED_TEMPLATE_SIGNATURE;

	/**
	 * The feature id for the '<em><b>Template Binding</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__TEMPLATE_BINDING = UMLPackage.PACKAGE__TEMPLATE_BINDING;

	/**
	 * The feature id for the '<em><b>URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__URI = UMLPackage.PACKAGE__URI;

	/**
	 * The feature id for the '<em><b>URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */

	/**
	 * The feature id for the '<em><b>Nested Package</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__NESTED_PACKAGE = UMLPackage.PACKAGE__NESTED_PACKAGE;

	/**
	 * The feature id for the '<em><b>Nesting Package</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__NESTING_PACKAGE = UMLPackage.PACKAGE__NESTING_PACKAGE;

	/**
	 * The feature id for the '<em><b>Owned Stereotype</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__OWNED_STEREOTYPE = UMLPackage.PACKAGE__OWNED_STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Owned Stereotype</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */

	/**
	 * The feature id for the '<em><b>Package Merge</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__PACKAGE_MERGE = UMLPackage.PACKAGE__PACKAGE_MERGE;

	/**
	 * The feature id for the '<em><b>Packaged Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__PACKAGED_ELEMENT = UMLPackage.PACKAGE__PACKAGED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Profile Application</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__PROFILE_APPLICATION = UMLPackage.PACKAGE__PROFILE_APPLICATION;

	/**
	 * The feature id for the '<em><b>Owned Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL__OWNED_TYPE = UMLPackage.PACKAGE__OWNED_TYPE;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIMULATION_MODEL_FEATURE_COUNT = UMLPackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.InstanceSimulationImpl <em>Instance Simulation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.InstanceSimulationImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getInstanceSimulation()
	 * @generated
	 */
	int INSTANCE_SIMULATION = 12;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__EANNOTATIONS = UMLPackage.INSTANCE_SPECIFICATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__OWNED_COMMENT = UMLPackage.INSTANCE_SPECIFICATION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__OWNED_ELEMENT = UMLPackage.INSTANCE_SPECIFICATION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__OWNER = UMLPackage.INSTANCE_SPECIFICATION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__CLIENT_DEPENDENCY = UMLPackage.INSTANCE_SPECIFICATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__NAME = UMLPackage.INSTANCE_SPECIFICATION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__NAME_EXPRESSION = UMLPackage.INSTANCE_SPECIFICATION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__NAMESPACE = UMLPackage.INSTANCE_SPECIFICATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__QUALIFIED_NAME = UMLPackage.INSTANCE_SPECIFICATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__VISIBILITY = UMLPackage.INSTANCE_SPECIFICATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Deployed Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__DEPLOYED_ELEMENT = UMLPackage.INSTANCE_SPECIFICATION__DEPLOYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__DEPLOYMENT = UMLPackage.INSTANCE_SPECIFICATION__DEPLOYMENT;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__OWNING_TEMPLATE_PARAMETER = UMLPackage.INSTANCE_SPECIFICATION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__TEMPLATE_PARAMETER = UMLPackage.INSTANCE_SPECIFICATION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Classifier</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__CLASSIFIER = UMLPackage.INSTANCE_SPECIFICATION__CLASSIFIER;

	/**
	 * The feature id for the '<em><b>Slot</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__SLOT = UMLPackage.INSTANCE_SPECIFICATION__SLOT;

	/**
	 * The feature id for the '<em><b>Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION__SPECIFICATION = UMLPackage.INSTANCE_SPECIFICATION__SPECIFICATION;

	/**
	 * The number of structural features of the '<em>Instance Simulation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INSTANCE_SIMULATION_FEATURE_COUNT = UMLPackage.INSTANCE_SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.ActualInstanceImpl <em>Actual Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.ActualInstanceImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getActualInstance()
	 * @generated
	 */
	int ACTUAL_INSTANCE = 13;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__EANNOTATIONS = UMLPackage.INSTANCE_SPECIFICATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__OWNED_COMMENT = UMLPackage.INSTANCE_SPECIFICATION__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__OWNED_ELEMENT = UMLPackage.INSTANCE_SPECIFICATION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__OWNER = UMLPackage.INSTANCE_SPECIFICATION__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__CLIENT_DEPENDENCY = UMLPackage.INSTANCE_SPECIFICATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__NAME = UMLPackage.INSTANCE_SPECIFICATION__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__NAME_EXPRESSION = UMLPackage.INSTANCE_SPECIFICATION__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__NAMESPACE = UMLPackage.INSTANCE_SPECIFICATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__QUALIFIED_NAME = UMLPackage.INSTANCE_SPECIFICATION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__VISIBILITY = UMLPackage.INSTANCE_SPECIFICATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Deployed Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__DEPLOYED_ELEMENT = UMLPackage.INSTANCE_SPECIFICATION__DEPLOYED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__DEPLOYMENT = UMLPackage.INSTANCE_SPECIFICATION__DEPLOYMENT;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__OWNING_TEMPLATE_PARAMETER = UMLPackage.INSTANCE_SPECIFICATION__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__TEMPLATE_PARAMETER = UMLPackage.INSTANCE_SPECIFICATION__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Classifier</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__CLASSIFIER = UMLPackage.INSTANCE_SPECIFICATION__CLASSIFIER;

	/**
	 * The feature id for the '<em><b>Slot</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__SLOT = UMLPackage.INSTANCE_SPECIFICATION__SLOT;

	/**
	 * The feature id for the '<em><b>Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE__SPECIFICATION = UMLPackage.INSTANCE_SPECIFICATION__SPECIFICATION;

	/**
	 * The number of structural features of the '<em>Actual Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTUAL_INSTANCE_FEATURE_COUNT = UMLPackage.INSTANCE_SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.WeightedInstanceValueImpl <em>Weighted Instance Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.WeightedInstanceValueImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getWeightedInstanceValue()
	 * @generated
	 */
	int WEIGHTED_INSTANCE_VALUE = 14;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__EANNOTATIONS = SIMULATED_VALUE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__OWNED_COMMENT = SIMULATED_VALUE__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__OWNED_ELEMENT = SIMULATED_VALUE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__OWNER = SIMULATED_VALUE__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__CLIENT_DEPENDENCY = SIMULATED_VALUE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__NAME = SIMULATED_VALUE__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__NAME_EXPRESSION = SIMULATED_VALUE__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__NAMESPACE = SIMULATED_VALUE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__QUALIFIED_NAME = SIMULATED_VALUE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__VISIBILITY = SIMULATED_VALUE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__OWNING_TEMPLATE_PARAMETER = SIMULATED_VALUE__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__TEMPLATE_PARAMETER = SIMULATED_VALUE__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__TYPE = SIMULATED_VALUE__TYPE;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__WEIGHT = SIMULATED_VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE__INSTANCE = SIMULATED_VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Weighted Instance Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_INSTANCE_VALUE_FEATURE_COUNT = SIMULATED_VALUE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.WeightedSimpleTypeValueImpl <em>Weighted Simple Type Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.WeightedSimpleTypeValueImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getWeightedSimpleTypeValue()
	 * @generated
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE = 15;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__EANNOTATIONS = SIMULATED_VALUE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__OWNED_COMMENT = SIMULATED_VALUE__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__OWNED_ELEMENT = SIMULATED_VALUE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__OWNER = SIMULATED_VALUE__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__CLIENT_DEPENDENCY = SIMULATED_VALUE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__NAME = SIMULATED_VALUE__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__NAME_EXPRESSION = SIMULATED_VALUE__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__NAMESPACE = SIMULATED_VALUE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__QUALIFIED_NAME = SIMULATED_VALUE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__VISIBILITY = SIMULATED_VALUE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__OWNING_TEMPLATE_PARAMETER = SIMULATED_VALUE__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__TEMPLATE_PARAMETER = SIMULATED_VALUE__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__TYPE = SIMULATED_VALUE__TYPE;

	/**
	 * The feature id for the '<em><b>String Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__STRING_VALUE = SIMULATED_VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__WEIGHT = SIMULATED_VALUE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Runtime Strategy Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE__RUNTIME_STRATEGY_FACTORY = SIMULATED_VALUE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Weighted Simple Type Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEIGHTED_SIMPLE_TYPE_VALUE_FEATURE_COUNT = SIMULATED_VALUE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.impl.LiteralSimpleTypeImpl <em>Literal Simple Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.impl.LiteralSimpleTypeImpl
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getLiteralSimpleType()
	 * @generated
	 */
	int LITERAL_SIMPLE_TYPE = 16;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__EANNOTATIONS = UMLPackage.LITERAL_STRING__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__OWNED_COMMENT = UMLPackage.LITERAL_STRING__OWNED_COMMENT;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__OWNED_ELEMENT = UMLPackage.LITERAL_STRING__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__OWNER = UMLPackage.LITERAL_STRING__OWNER;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__CLIENT_DEPENDENCY = UMLPackage.LITERAL_STRING__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__NAME = UMLPackage.LITERAL_STRING__NAME;

	/**
	 * The feature id for the '<em><b>Name Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__NAME_EXPRESSION = UMLPackage.LITERAL_STRING__NAME_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__NAMESPACE = UMLPackage.LITERAL_STRING__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__QUALIFIED_NAME = UMLPackage.LITERAL_STRING__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__VISIBILITY = UMLPackage.LITERAL_STRING__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Owning Template Parameter</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__OWNING_TEMPLATE_PARAMETER = UMLPackage.LITERAL_STRING__OWNING_TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Template Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__TEMPLATE_PARAMETER = UMLPackage.LITERAL_STRING__TEMPLATE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__TYPE = UMLPackage.LITERAL_STRING__TYPE;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__VALUE = UMLPackage.LITERAL_STRING__VALUE;

	/**
	 * The feature id for the '<em><b>String Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__STRING_VALUE = UMLPackage.LITERAL_STRING_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Runtime Strategy Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE__RUNTIME_STRATEGY_FACTORY = UMLPackage.LITERAL_STRING_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Literal Simple Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LITERAL_SIMPLE_TYPE_FEATURE_COUNT = UMLPackage.LITERAL_STRING_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.opaeum.metamodels.simulation.simulation.SimulationStrategy <em>Strategy</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationStrategy
	 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getSimulationStrategy()
	 * @generated
	 */
	int SIMULATION_STRATEGY = 17;


	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.SimulatedValue <em>Simulated Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simulated Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulatedValue
	 * @generated
	 */
	EClass getSimulatedValue();

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
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.SimulatingSlot <em>Simulating Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Simulating Slot</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulatingSlot
	 * @generated
	 */
	EClass getSimulatingSlot();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.metamodels.simulation.simulation.SimulatingSlot#getSizeDistribution <em>Size Distribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Size Distribution</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulatingSlot#getSizeDistribution()
	 * @see #getSimulatingSlot()
	 * @generated
	 */
	EReference getSimulatingSlot_SizeDistribution();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.SimulatingSlot#getSimulationStrategy <em>Simulation Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Simulation Strategy</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulatingSlot#getSimulationStrategy()
	 * @see #getSimulatingSlot()
	 * @generated
	 */
	EAttribute getSimulatingSlot_SimulationStrategy();

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
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.WeightedEnumLiteralValue <em>Weighted Enum Literal Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Weighted Enum Literal Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedEnumLiteralValue
	 * @generated
	 */
	EClass getWeightedEnumLiteralValue();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.WeightedEnumLiteralValue#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedEnumLiteralValue#getWeight()
	 * @see #getWeightedEnumLiteralValue()
	 * @generated
	 */
	EAttribute getWeightedEnumLiteralValue_Weight();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.metamodels.simulation.simulation.WeightedEnumLiteralValue#getLiteral <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Literal</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedEnumLiteralValue#getLiteral()
	 * @see #getWeightedEnumLiteralValue()
	 * @generated
	 */
	EReference getWeightedEnumLiteralValue_Literal();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.ContainedActualInstance <em>Contained Actual Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contained Actual Instance</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.ContainedActualInstance
	 * @generated
	 */
	EClass getContainedActualInstance();

	/**
	 * Returns the meta object for the containment reference list '{@link org.opaeum.metamodels.simulation.simulation.ContainedActualInstance#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Values</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.ContainedActualInstance#getValues()
	 * @see #getContainedActualInstance()
	 * @generated
	 */
	EReference getContainedActualInstance_Values();

	/**
	 * Returns the meta object for the containment reference '{@link org.opaeum.metamodels.simulation.simulation.ContainedActualInstance#getContainedInstance <em>Contained Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Contained Instance</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.ContainedActualInstance#getContainedInstance()
	 * @see #getContainedActualInstance()
	 * @generated
	 */
	EReference getContainedActualInstance_ContainedInstance();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue <em>Weighted Boolean Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Weighted Boolean Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue
	 * @generated
	 */
	EClass getWeightedBooleanValue();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue#getWeight()
	 * @see #getWeightedBooleanValue()
	 * @generated
	 */
	EAttribute getWeightedBooleanValue_Weight();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue#isValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedBooleanValue#isValue()
	 * @see #getWeightedBooleanValue()
	 * @generated
	 */
	EAttribute getWeightedBooleanValue_Value();

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
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.WeightedStringValue <em>Weighted String Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Weighted String Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedStringValue
	 * @generated
	 */
	EClass getWeightedStringValue();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.WeightedStringValue#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedStringValue#getWeight()
	 * @see #getWeightedStringValue()
	 * @generated
	 */
	EAttribute getWeightedStringValue_Weight();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.WeightedStringValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedStringValue#getValue()
	 * @see #getWeightedStringValue()
	 * @generated
	 */
	EAttribute getWeightedStringValue_Value();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.SimulationModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationModel
	 * @generated
	 */
	EClass getSimulationModel();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.InstanceSimulation <em>Instance Simulation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instance Simulation</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.InstanceSimulation
	 * @generated
	 */
	EClass getInstanceSimulation();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.ActualInstance <em>Actual Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actual Instance</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.ActualInstance
	 * @generated
	 */
	EClass getActualInstance();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue <em>Weighted Instance Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Weighted Instance Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue
	 * @generated
	 */
	EClass getWeightedInstanceValue();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue#getWeight()
	 * @see #getWeightedInstanceValue()
	 * @generated
	 */
	EAttribute getWeightedInstanceValue_Weight();

	/**
	 * Returns the meta object for the reference '{@link org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue#getInstance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Instance</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedInstanceValue#getInstance()
	 * @see #getWeightedInstanceValue()
	 * @generated
	 */
	EReference getWeightedInstanceValue_Instance();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue <em>Weighted Simple Type Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Weighted Simple Type Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue
	 * @generated
	 */
	EClass getWeightedSimpleTypeValue();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue#getStringValue <em>String Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue#getStringValue()
	 * @see #getWeightedSimpleTypeValue()
	 * @generated
	 */
	EAttribute getWeightedSimpleTypeValue_StringValue();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue#getWeight <em>Weight</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Weight</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue#getWeight()
	 * @see #getWeightedSimpleTypeValue()
	 * @generated
	 */
	EAttribute getWeightedSimpleTypeValue_Weight();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue#getRuntimeStrategyFactory <em>Runtime Strategy Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Runtime Strategy Factory</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.WeightedSimpleTypeValue#getRuntimeStrategyFactory()
	 * @see #getWeightedSimpleTypeValue()
	 * @generated
	 */
	EAttribute getWeightedSimpleTypeValue_RuntimeStrategyFactory();

	/**
	 * Returns the meta object for class '{@link org.opaeum.metamodels.simulation.simulation.LiteralSimpleType <em>Literal Simple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Literal Simple Type</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.LiteralSimpleType
	 * @generated
	 */
	EClass getLiteralSimpleType();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.LiteralSimpleType#getStringValue <em>String Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Value</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.LiteralSimpleType#getStringValue()
	 * @see #getLiteralSimpleType()
	 * @generated
	 */
	EAttribute getLiteralSimpleType_StringValue();

	/**
	 * Returns the meta object for the attribute '{@link org.opaeum.metamodels.simulation.simulation.LiteralSimpleType#getRuntimeStrategyFactory <em>Runtime Strategy Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Runtime Strategy Factory</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.LiteralSimpleType#getRuntimeStrategyFactory()
	 * @see #getLiteralSimpleType()
	 * @generated
	 */
	EAttribute getLiteralSimpleType_RuntimeStrategyFactory();

	/**
	 * Returns the meta object for enum '{@link org.opaeum.metamodels.simulation.simulation.SimulationStrategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Strategy</em>'.
	 * @see org.opaeum.metamodels.simulation.simulation.SimulationStrategy
	 * @generated
	 */
	EEnum getSimulationStrategy();

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
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.SimulatedValueImpl <em>Simulated Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulatedValueImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getSimulatedValue()
		 * @generated
		 */
		EClass SIMULATED_VALUE = eINSTANCE.getSimulatedValue();

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
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.SimulatingSlotImpl <em>Simulating Slot</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulatingSlotImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getSimulatingSlot()
		 * @generated
		 */
		EClass SIMULATING_SLOT = eINSTANCE.getSimulatingSlot();

		/**
		 * The meta object literal for the '<em><b>Size Distribution</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIMULATING_SLOT__SIZE_DISTRIBUTION = eINSTANCE.getSimulatingSlot_SizeDistribution();

		/**
		 * The meta object literal for the '<em><b>Simulation Strategy</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIMULATING_SLOT__SIMULATION_STRATEGY = eINSTANCE.getSimulatingSlot_SimulationStrategy();

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
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.WeightedEnumLiteralValueImpl <em>Weighted Enum Literal Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.WeightedEnumLiteralValueImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getWeightedEnumLiteralValue()
		 * @generated
		 */
		EClass WEIGHTED_ENUM_LITERAL_VALUE = eINSTANCE.getWeightedEnumLiteralValue();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEIGHTED_ENUM_LITERAL_VALUE__WEIGHT = eINSTANCE.getWeightedEnumLiteralValue_Weight();

		/**
		 * The meta object literal for the '<em><b>Literal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEIGHTED_ENUM_LITERAL_VALUE__LITERAL = eINSTANCE.getWeightedEnumLiteralValue_Literal();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.ContainedActualInstanceImpl <em>Contained Actual Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.ContainedActualInstanceImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getContainedActualInstance()
		 * @generated
		 */
		EClass CONTAINED_ACTUAL_INSTANCE = eINSTANCE.getContainedActualInstance();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINED_ACTUAL_INSTANCE__VALUES = eINSTANCE.getContainedActualInstance_Values();

		/**
		 * The meta object literal for the '<em><b>Contained Instance</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINED_ACTUAL_INSTANCE__CONTAINED_INSTANCE = eINSTANCE.getContainedActualInstance_ContainedInstance();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.WeightedBooleanValueImpl <em>Weighted Boolean Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.WeightedBooleanValueImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getWeightedBooleanValue()
		 * @generated
		 */
		EClass WEIGHTED_BOOLEAN_VALUE = eINSTANCE.getWeightedBooleanValue();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEIGHTED_BOOLEAN_VALUE__WEIGHT = eINSTANCE.getWeightedBooleanValue_Weight();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEIGHTED_BOOLEAN_VALUE__VALUE = eINSTANCE.getWeightedBooleanValue_Value();

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
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.WeightedStringValueImpl <em>Weighted String Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.WeightedStringValueImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getWeightedStringValue()
		 * @generated
		 */
		EClass WEIGHTED_STRING_VALUE = eINSTANCE.getWeightedStringValue();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEIGHTED_STRING_VALUE__WEIGHT = eINSTANCE.getWeightedStringValue_Weight();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEIGHTED_STRING_VALUE__VALUE = eINSTANCE.getWeightedStringValue_Value();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.SimulationModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationModelImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getSimulationModel()
		 * @generated
		 */
		EClass SIMULATION_MODEL = eINSTANCE.getSimulationModel();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.InstanceSimulationImpl <em>Instance Simulation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.InstanceSimulationImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getInstanceSimulation()
		 * @generated
		 */
		EClass INSTANCE_SIMULATION = eINSTANCE.getInstanceSimulation();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.ActualInstanceImpl <em>Actual Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.ActualInstanceImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getActualInstance()
		 * @generated
		 */
		EClass ACTUAL_INSTANCE = eINSTANCE.getActualInstance();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.WeightedInstanceValueImpl <em>Weighted Instance Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.WeightedInstanceValueImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getWeightedInstanceValue()
		 * @generated
		 */
		EClass WEIGHTED_INSTANCE_VALUE = eINSTANCE.getWeightedInstanceValue();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEIGHTED_INSTANCE_VALUE__WEIGHT = eINSTANCE.getWeightedInstanceValue_Weight();

		/**
		 * The meta object literal for the '<em><b>Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WEIGHTED_INSTANCE_VALUE__INSTANCE = eINSTANCE.getWeightedInstanceValue_Instance();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.WeightedSimpleTypeValueImpl <em>Weighted Simple Type Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.WeightedSimpleTypeValueImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getWeightedSimpleTypeValue()
		 * @generated
		 */
		EClass WEIGHTED_SIMPLE_TYPE_VALUE = eINSTANCE.getWeightedSimpleTypeValue();

		/**
		 * The meta object literal for the '<em><b>String Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEIGHTED_SIMPLE_TYPE_VALUE__STRING_VALUE = eINSTANCE.getWeightedSimpleTypeValue_StringValue();

		/**
		 * The meta object literal for the '<em><b>Weight</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEIGHTED_SIMPLE_TYPE_VALUE__WEIGHT = eINSTANCE.getWeightedSimpleTypeValue_Weight();

		/**
		 * The meta object literal for the '<em><b>Runtime Strategy Factory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEIGHTED_SIMPLE_TYPE_VALUE__RUNTIME_STRATEGY_FACTORY = eINSTANCE.getWeightedSimpleTypeValue_RuntimeStrategyFactory();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.impl.LiteralSimpleTypeImpl <em>Literal Simple Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.impl.LiteralSimpleTypeImpl
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getLiteralSimpleType()
		 * @generated
		 */
		EClass LITERAL_SIMPLE_TYPE = eINSTANCE.getLiteralSimpleType();

		/**
		 * The meta object literal for the '<em><b>String Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LITERAL_SIMPLE_TYPE__STRING_VALUE = eINSTANCE.getLiteralSimpleType_StringValue();

		/**
		 * The meta object literal for the '<em><b>Runtime Strategy Factory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LITERAL_SIMPLE_TYPE__RUNTIME_STRATEGY_FACTORY = eINSTANCE.getLiteralSimpleType_RuntimeStrategyFactory();

		/**
		 * The meta object literal for the '{@link org.opaeum.metamodels.simulation.simulation.SimulationStrategy <em>Strategy</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.opaeum.metamodels.simulation.simulation.SimulationStrategy
		 * @see org.opaeum.metamodels.simulation.simulation.impl.SimulationPackageImpl#getSimulationStrategy()
		 * @generated
		 */
		EEnum SIMULATION_STRATEGY = eINSTANCE.getSimulationStrategy();

	}

} //SimulationPackage
