/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see org.drools.drools._5._0.process.ProcessFactory
 * @model kind="package"
 * @generated
 */
public interface ProcessPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "process";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://drools.org/drools-5.0/process";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "process";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ProcessPackage eINSTANCE = org.drools.drools._5._0.process.impl.ProcessPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ActionNodeTypeImpl <em>Action Node Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ActionNodeTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getActionNodeType()
	 * @generated
	 */
	int ACTION_NODE_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE_TYPE__ACTION = 2;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE_TYPE__HEIGHT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE_TYPE__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE_TYPE__NAME = 5;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE_TYPE__WIDTH = 6;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE_TYPE__X = 7;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE_TYPE__Y = 8;

	/**
	 * The number of structural features of the '<em>Action Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_NODE_TYPE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ActionTypeImpl <em>Action Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ActionTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getActionType()
	 * @generated
	 */
	int ACTION_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Dialect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__DIALECT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__NAME = 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE__TYPE = 3;

	/**
	 * The number of structural features of the '<em>Action Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.CompositeTypeImpl <em>Composite Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.CompositeTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getCompositeType()
	 * @generated
	 */
	int COMPOSITE_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Timers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__TIMERS = 2;

	/**
	 * The feature id for the '<em><b>On Entry</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__ON_ENTRY = 3;

	/**
	 * The feature id for the '<em><b>On Exit</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__ON_EXIT = 4;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__VARIABLES = 5;

	/**
	 * The feature id for the '<em><b>Exception Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__EXCEPTION_HANDLERS = 6;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__NODES = 7;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__CONNECTIONS = 8;

	/**
	 * The feature id for the '<em><b>In Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__IN_PORTS = 9;

	/**
	 * The feature id for the '<em><b>Out Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__OUT_PORTS = 10;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__HEIGHT = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__ID = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__NAME = 13;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__WIDTH = 14;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__X = 15;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE__Y = 16;

	/**
	 * The number of structural features of the '<em>Composite Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_TYPE_FEATURE_COUNT = 17;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ConnectionsTypeImpl <em>Connections Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ConnectionsTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getConnectionsType()
	 * @generated
	 */
	int CONNECTIONS_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTIONS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTIONS_TYPE__CONNECTION = 1;

	/**
	 * The number of structural features of the '<em>Connections Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTIONS_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ConnectionTypeImpl <em>Connection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ConnectionTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getConnectionType()
	 * @generated
	 */
	int CONNECTION_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Bendpoints</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_TYPE__BENDPOINTS = 0;

	/**
	 * The feature id for the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_TYPE__FROM = 1;

	/**
	 * The feature id for the '<em><b>From Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_TYPE__FROM_TYPE = 2;

	/**
	 * The feature id for the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_TYPE__TO = 3;

	/**
	 * The feature id for the '<em><b>To Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_TYPE__TO_TYPE = 4;

	/**
	 * The number of structural features of the '<em>Connection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_TYPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ConstraintsTypeImpl <em>Constraints Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ConstraintsTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getConstraintsType()
	 * @generated
	 */
	int CONSTRAINTS_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINTS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINTS_TYPE__CONSTRAINT = 1;

	/**
	 * The number of structural features of the '<em>Constraints Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINTS_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ConstraintTypeImpl <em>Constraint Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ConstraintTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getConstraintType()
	 * @generated
	 */
	int CONSTRAINT_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_TYPE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Dialect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_TYPE__DIALECT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_TYPE__NAME = 2;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_TYPE__PRIORITY = 3;

	/**
	 * The feature id for the '<em><b>To Node Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_TYPE__TO_NODE_ID = 4;

	/**
	 * The feature id for the '<em><b>To Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_TYPE__TO_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_TYPE__TYPE = 6;

	/**
	 * The number of structural features of the '<em>Constraint Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_TYPE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.DocumentRootImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 7;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ACTION = 3;

	/**
	 * The feature id for the '<em><b>Action Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ACTION_NODE = 4;

	/**
	 * The feature id for the '<em><b>Composite</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__COMPOSITE = 5;

	/**
	 * The feature id for the '<em><b>Connection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONNECTION = 6;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONNECTIONS = 7;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONSTRAINT = 8;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONSTRAINTS = 9;

	/**
	 * The feature id for the '<em><b>Dynamic</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__DYNAMIC = 10;

	/**
	 * The feature id for the '<em><b>End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__END = 11;

	/**
	 * The feature id for the '<em><b>Event Filter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EVENT_FILTER = 12;

	/**
	 * The feature id for the '<em><b>Event Filters</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EVENT_FILTERS = 13;

	/**
	 * The feature id for the '<em><b>Event Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EVENT_NODE = 14;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXCEPTION_HANDLER = 15;

	/**
	 * The feature id for the '<em><b>Exception Handlers</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__EXCEPTION_HANDLERS = 16;

	/**
	 * The feature id for the '<em><b>Fault</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FAULT = 17;

	/**
	 * The feature id for the '<em><b>For Each</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FOR_EACH = 18;

	/**
	 * The feature id for the '<em><b>Function Import</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FUNCTION_IMPORT = 19;

	/**
	 * The feature id for the '<em><b>Function Imports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FUNCTION_IMPORTS = 20;

	/**
	 * The feature id for the '<em><b>Global</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GLOBAL = 21;

	/**
	 * The feature id for the '<em><b>Globals</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GLOBALS = 22;

	/**
	 * The feature id for the '<em><b>Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__HEADER = 23;

	/**
	 * The feature id for the '<em><b>Human Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__HUMAN_TASK = 24;

	/**
	 * The feature id for the '<em><b>Import</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IMPORT = 25;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IMPORTS = 26;

	/**
	 * The feature id for the '<em><b>In Port</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IN_PORT = 27;

	/**
	 * The feature id for the '<em><b>In Ports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__IN_PORTS = 28;

	/**
	 * The feature id for the '<em><b>Join</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__JOIN = 29;

	/**
	 * The feature id for the '<em><b>Mapping</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MAPPING = 30;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__META_DATA = 31;

	/**
	 * The feature id for the '<em><b>Milestone</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MILESTONE = 32;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NODES = 33;

	/**
	 * The feature id for the '<em><b>On Entry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ON_ENTRY = 34;

	/**
	 * The feature id for the '<em><b>On Exit</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ON_EXIT = 35;

	/**
	 * The feature id for the '<em><b>Out Port</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OUT_PORT = 36;

	/**
	 * The feature id for the '<em><b>Out Ports</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OUT_PORTS = 37;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PARAMETER = 38;

	/**
	 * The feature id for the '<em><b>Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROCESS = 39;

	/**
	 * The feature id for the '<em><b>Rule Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RULE_SET = 40;

	/**
	 * The feature id for the '<em><b>Split</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SPLIT = 41;

	/**
	 * The feature id for the '<em><b>Start</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__START = 42;

	/**
	 * The feature id for the '<em><b>State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__STATE = 43;

	/**
	 * The feature id for the '<em><b>Sub Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SUB_PROCESS = 44;

	/**
	 * The feature id for the '<em><b>Swimlane</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SWIMLANE = 45;

	/**
	 * The feature id for the '<em><b>Swimlanes</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SWIMLANES = 46;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TIMER = 47;

	/**
	 * The feature id for the '<em><b>Timer Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TIMER_NODE = 48;

	/**
	 * The feature id for the '<em><b>Timers</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TIMERS = 49;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TRIGGER = 50;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TRIGGERS = 51;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TYPE = 52;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VALUE = 53;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VARIABLE = 54;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VARIABLES = 55;

	/**
	 * The feature id for the '<em><b>Work</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WORK = 56;

	/**
	 * The feature id for the '<em><b>Work Item</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WORK_ITEM = 57;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 58;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.DynamicTypeImpl <em>Dynamic Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.DynamicTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getDynamicType()
	 * @generated
	 */
	int DYNAMIC_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__VARIABLES = 2;

	/**
	 * The feature id for the '<em><b>Exception Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__EXCEPTION_HANDLERS = 3;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__NODES = 4;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__CONNECTIONS = 5;

	/**
	 * The feature id for the '<em><b>In Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__IN_PORTS = 6;

	/**
	 * The feature id for the '<em><b>Out Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__OUT_PORTS = 7;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__HEIGHT = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__ID = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__NAME = 10;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__WIDTH = 11;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__X = 12;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE__Y = 13;

	/**
	 * The number of structural features of the '<em>Dynamic Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_TYPE_FEATURE_COUNT = 14;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.EndTypeImpl <em>End Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.EndTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getEndType()
	 * @generated
	 */
	int END_TYPE = 9;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_TYPE__HEIGHT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_TYPE__ID = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_TYPE__NAME = 4;

	/**
	 * The feature id for the '<em><b>Terminate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_TYPE__TERMINATE = 5;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_TYPE__WIDTH = 6;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_TYPE__X = 7;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_TYPE__Y = 8;

	/**
	 * The number of structural features of the '<em>End Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_TYPE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.EventFiltersTypeImpl <em>Event Filters Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.EventFiltersTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getEventFiltersType()
	 * @generated
	 */
	int EVENT_FILTERS_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FILTERS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Event Filter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FILTERS_TYPE__EVENT_FILTER = 1;

	/**
	 * The number of structural features of the '<em>Event Filters Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FILTERS_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.EventFilterTypeImpl <em>Event Filter Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.EventFilterTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getEventFilterType()
	 * @generated
	 */
	int EVENT_FILTER_TYPE = 11;

	/**
	 * The feature id for the '<em><b>Event Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FILTER_TYPE__EVENT_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FILTER_TYPE__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Event Filter Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FILTER_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl <em>Event Node Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.EventNodeTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getEventNodeType()
	 * @generated
	 */
	int EVENT_NODE_TYPE = 12;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_NODE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_NODE_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Event Filters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_NODE_TYPE__EVENT_FILTERS = 2;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_NODE_TYPE__HEIGHT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_NODE_TYPE__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_NODE_TYPE__NAME = 5;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_NODE_TYPE__SCOPE = 6;

	/**
	 * The feature id for the '<em><b>Variable Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_NODE_TYPE__VARIABLE_NAME = 7;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_NODE_TYPE__WIDTH = 8;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_NODE_TYPE__X = 9;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_NODE_TYPE__Y = 10;

	/**
	 * The number of structural features of the '<em>Event Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_NODE_TYPE_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ExceptionHandlersTypeImpl <em>Exception Handlers Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ExceptionHandlersTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getExceptionHandlersType()
	 * @generated
	 */
	int EXCEPTION_HANDLERS_TYPE = 13;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLERS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Exception Handler</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLERS_TYPE__EXCEPTION_HANDLER = 1;

	/**
	 * The number of structural features of the '<em>Exception Handlers Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLERS_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ExceptionHandlerTypeImpl <em>Exception Handler Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ExceptionHandlerTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getExceptionHandlerType()
	 * @generated
	 */
	int EXCEPTION_HANDLER_TYPE = 14;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_TYPE__ACTION = 1;

	/**
	 * The feature id for the '<em><b>Fault Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_TYPE__FAULT_NAME = 2;

	/**
	 * The feature id for the '<em><b>Fault Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_TYPE__FAULT_VARIABLE = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_TYPE__TYPE = 4;

	/**
	 * The number of structural features of the '<em>Exception Handler Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTION_HANDLER_TYPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.FaultTypeImpl <em>Fault Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.FaultTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getFaultType()
	 * @generated
	 */
	int FAULT_TYPE = 15;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Fault Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT_TYPE__FAULT_NAME = 2;

	/**
	 * The feature id for the '<em><b>Fault Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT_TYPE__FAULT_VARIABLE = 3;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT_TYPE__HEIGHT = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT_TYPE__ID = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT_TYPE__NAME = 6;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT_TYPE__WIDTH = 7;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT_TYPE__X = 8;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT_TYPE__Y = 9;

	/**
	 * The number of structural features of the '<em>Fault Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAULT_TYPE_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl <em>For Each Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ForEachTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getForEachType()
	 * @generated
	 */
	int FOR_EACH_TYPE = 16;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__NODES = 2;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__CONNECTIONS = 3;

	/**
	 * The feature id for the '<em><b>In Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__IN_PORTS = 4;

	/**
	 * The feature id for the '<em><b>Out Ports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__OUT_PORTS = 5;

	/**
	 * The feature id for the '<em><b>Collection Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__COLLECTION_EXPRESSION = 6;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__HEIGHT = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__ID = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__NAME = 9;

	/**
	 * The feature id for the '<em><b>Variable Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__VARIABLE_NAME = 10;

	/**
	 * The feature id for the '<em><b>Wait For Completion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__WAIT_FOR_COMPLETION = 11;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__WIDTH = 12;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__X = 13;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE__Y = 14;

	/**
	 * The number of structural features of the '<em>For Each Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOR_EACH_TYPE_FEATURE_COUNT = 15;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.FunctionImportsTypeImpl <em>Function Imports Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.FunctionImportsTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getFunctionImportsType()
	 * @generated
	 */
	int FUNCTION_IMPORTS_TYPE = 17;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_IMPORTS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Function Import</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_IMPORTS_TYPE__FUNCTION_IMPORT = 1;

	/**
	 * The number of structural features of the '<em>Function Imports Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_IMPORTS_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.FunctionImportTypeImpl <em>Function Import Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.FunctionImportTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getFunctionImportType()
	 * @generated
	 */
	int FUNCTION_IMPORT_TYPE = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_IMPORT_TYPE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Function Import Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_IMPORT_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.GlobalsTypeImpl <em>Globals Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.GlobalsTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getGlobalsType()
	 * @generated
	 */
	int GLOBALS_TYPE = 19;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBALS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Global</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBALS_TYPE__GLOBAL = 1;

	/**
	 * The number of structural features of the '<em>Globals Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBALS_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.GlobalTypeImpl <em>Global Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.GlobalTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getGlobalType()
	 * @generated
	 */
	int GLOBAL_TYPE = 20;

	/**
	 * The feature id for the '<em><b>Identifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBAL_TYPE__IDENTIFIER = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBAL_TYPE__TYPE = 1;

	/**
	 * The number of structural features of the '<em>Global Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GLOBAL_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.HeaderTypeImpl <em>Header Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.HeaderTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getHeaderType()
	 * @generated
	 */
	int HEADER_TYPE = 21;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER_TYPE__IMPORTS = 1;

	/**
	 * The feature id for the '<em><b>Function Imports</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER_TYPE__FUNCTION_IMPORTS = 2;

	/**
	 * The feature id for the '<em><b>Globals</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER_TYPE__GLOBALS = 3;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER_TYPE__VARIABLES = 4;

	/**
	 * The feature id for the '<em><b>Swimlanes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER_TYPE__SWIMLANES = 5;

	/**
	 * The feature id for the '<em><b>Exception Handlers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER_TYPE__EXCEPTION_HANDLERS = 6;

	/**
	 * The number of structural features of the '<em>Header Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER_TYPE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.HumanTaskTypeImpl <em>Human Task Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.HumanTaskTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getHumanTaskType()
	 * @generated
	 */
	int HUMAN_TASK_TYPE = 22;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Work</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__WORK = 2;

	/**
	 * The feature id for the '<em><b>Mapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__MAPPING = 3;

	/**
	 * The feature id for the '<em><b>Timers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__TIMERS = 4;

	/**
	 * The feature id for the '<em><b>On Entry</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__ON_ENTRY = 5;

	/**
	 * The feature id for the '<em><b>On Exit</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__ON_EXIT = 6;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__HEIGHT = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__ID = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__NAME = 9;

	/**
	 * The feature id for the '<em><b>Swimlane</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__SWIMLANE = 10;

	/**
	 * The feature id for the '<em><b>Wait For Completion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__WAIT_FOR_COMPLETION = 11;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__WIDTH = 12;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__X = 13;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE__Y = 14;

	/**
	 * The number of structural features of the '<em>Human Task Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HUMAN_TASK_TYPE_FEATURE_COUNT = 15;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ImportsTypeImpl <em>Imports Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ImportsTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getImportsType()
	 * @generated
	 */
	int IMPORTS_TYPE = 23;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Import</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTS_TYPE__IMPORT = 1;

	/**
	 * The number of structural features of the '<em>Imports Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORTS_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ImportTypeImpl <em>Import Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ImportTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getImportType()
	 * @generated
	 */
	int IMPORT_TYPE = 24;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TYPE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Import Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPORT_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.InPortsTypeImpl <em>In Ports Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.InPortsTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getInPortsType()
	 * @generated
	 */
	int IN_PORTS_TYPE = 25;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORTS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>In Port</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORTS_TYPE__IN_PORT = 1;

	/**
	 * The number of structural features of the '<em>In Ports Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORTS_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.InPortTypeImpl <em>In Port Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.InPortTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getInPortType()
	 * @generated
	 */
	int IN_PORT_TYPE = 26;

	/**
	 * The feature id for the '<em><b>Node Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT_TYPE__NODE_ID = 0;

	/**
	 * The feature id for the '<em><b>Node In Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT_TYPE__NODE_IN_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT_TYPE__TYPE = 2;

	/**
	 * The number of structural features of the '<em>In Port Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IN_PORT_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.JoinTypeImpl <em>Join Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.JoinTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getJoinType()
	 * @generated
	 */
	int JOIN_TYPE = 27;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__HEIGHT = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__ID = 3;

	/**
	 * The feature id for the '<em><b>N</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__N = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__NAME = 5;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__TYPE = 6;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__WIDTH = 7;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__X = 8;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE__Y = 9;

	/**
	 * The number of structural features of the '<em>Join Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_TYPE_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.MappingTypeImpl <em>Mapping Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.MappingTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getMappingType()
	 * @generated
	 */
	int MAPPING_TYPE = 28;

	/**
	 * The feature id for the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_TYPE__FROM = 0;

	/**
	 * The feature id for the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_TYPE__TO = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_TYPE__TYPE = 2;

	/**
	 * The number of structural features of the '<em>Mapping Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPING_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.MetaDataTypeImpl <em>Meta Data Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.MetaDataTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getMetaDataType()
	 * @generated
	 */
	int META_DATA_TYPE = 29;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int META_DATA_TYPE__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int META_DATA_TYPE__NAME = 1;

	/**
	 * The number of structural features of the '<em>Meta Data Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int META_DATA_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.MilestoneTypeImpl <em>Milestone Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.MilestoneTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getMilestoneType()
	 * @generated
	 */
	int MILESTONE_TYPE = 30;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE__CONSTRAINT = 2;

	/**
	 * The feature id for the '<em><b>Timers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE__TIMERS = 3;

	/**
	 * The feature id for the '<em><b>On Entry</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE__ON_ENTRY = 4;

	/**
	 * The feature id for the '<em><b>On Exit</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE__ON_EXIT = 5;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE__HEIGHT = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE__ID = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE__NAME = 8;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE__WIDTH = 9;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE__X = 10;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE__Y = 11;

	/**
	 * The number of structural features of the '<em>Milestone Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MILESTONE_TYPE_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.NodesTypeImpl <em>Nodes Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.NodesTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getNodesType()
	 * @generated
	 */
	int NODES_TYPE = 31;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Start</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__START = 1;

	/**
	 * The feature id for the '<em><b>End</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__END = 2;

	/**
	 * The feature id for the '<em><b>Action Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__ACTION_NODE = 3;

	/**
	 * The feature id for the '<em><b>Rule Set</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__RULE_SET = 4;

	/**
	 * The feature id for the '<em><b>Split</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__SPLIT = 5;

	/**
	 * The feature id for the '<em><b>Join</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__JOIN = 6;

	/**
	 * The feature id for the '<em><b>Milestone</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__MILESTONE = 7;

	/**
	 * The feature id for the '<em><b>Sub Process</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__SUB_PROCESS = 8;

	/**
	 * The feature id for the '<em><b>Work Item</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__WORK_ITEM = 9;

	/**
	 * The feature id for the '<em><b>Timer Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__TIMER_NODE = 10;

	/**
	 * The feature id for the '<em><b>Human Task</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__HUMAN_TASK = 11;

	/**
	 * The feature id for the '<em><b>Composite</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__COMPOSITE = 12;

	/**
	 * The feature id for the '<em><b>For Each</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__FOR_EACH = 13;

	/**
	 * The feature id for the '<em><b>Event Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__EVENT_NODE = 14;

	/**
	 * The feature id for the '<em><b>Fault</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__FAULT = 15;

	/**
	 * The feature id for the '<em><b>State</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__STATE = 16;

	/**
	 * The feature id for the '<em><b>Dynamic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE__DYNAMIC = 17;

	/**
	 * The number of structural features of the '<em>Nodes Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODES_TYPE_FEATURE_COUNT = 18;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.OnEntryTypeImpl <em>On Entry Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.OnEntryTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getOnEntryType()
	 * @generated
	 */
	int ON_ENTRY_TYPE = 32;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_ENTRY_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_ENTRY_TYPE__ACTION = 1;

	/**
	 * The number of structural features of the '<em>On Entry Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_ENTRY_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.OnExitTypeImpl <em>On Exit Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.OnExitTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getOnExitType()
	 * @generated
	 */
	int ON_EXIT_TYPE = 33;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_EXIT_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_EXIT_TYPE__ACTION = 1;

	/**
	 * The number of structural features of the '<em>On Exit Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ON_EXIT_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.OutPortsTypeImpl <em>Out Ports Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.OutPortsTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getOutPortsType()
	 * @generated
	 */
	int OUT_PORTS_TYPE = 34;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORTS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Out Port</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORTS_TYPE__OUT_PORT = 1;

	/**
	 * The number of structural features of the '<em>Out Ports Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORTS_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.OutPortTypeImpl <em>Out Port Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.OutPortTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getOutPortType()
	 * @generated
	 */
	int OUT_PORT_TYPE = 35;

	/**
	 * The feature id for the '<em><b>Node Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT_TYPE__NODE_ID = 0;

	/**
	 * The feature id for the '<em><b>Node Out Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT_TYPE__NODE_OUT_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT_TYPE__TYPE = 2;

	/**
	 * The number of structural features of the '<em>Out Port Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUT_PORT_TYPE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ParameterTypeImpl <em>Parameter Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ParameterTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getParameterType()
	 * @generated
	 */
	int PARAMETER_TYPE = 36;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_TYPE__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_TYPE__VALUE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_TYPE__NAME = 3;

	/**
	 * The number of structural features of the '<em>Parameter Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAMETER_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ProcessTypeImpl <em>Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ProcessTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getProcessType()
	 * @generated
	 */
	int PROCESS_TYPE = 37;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Header</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TYPE__HEADER = 1;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TYPE__NODES = 2;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TYPE__CONNECTIONS = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TYPE__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TYPE__NAME = 5;

	/**
	 * The feature id for the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TYPE__PACKAGE_NAME = 6;

	/**
	 * The feature id for the '<em><b>Router Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TYPE__ROUTER_LAYOUT = 7;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TYPE__TYPE = 8;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TYPE__VERSION = 9;

	/**
	 * The number of structural features of the '<em>Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_TYPE_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.RuleSetTypeImpl <em>Rule Set Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.RuleSetTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getRuleSetType()
	 * @generated
	 */
	int RULE_SET_TYPE = 38;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Timers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_TYPE__TIMERS = 2;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_TYPE__HEIGHT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_TYPE__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_TYPE__NAME = 5;

	/**
	 * The feature id for the '<em><b>Rule Flow Group</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_TYPE__RULE_FLOW_GROUP = 6;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_TYPE__WIDTH = 7;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_TYPE__X = 8;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_TYPE__Y = 9;

	/**
	 * The number of structural features of the '<em>Rule Set Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_SET_TYPE_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.SplitTypeImpl <em>Split Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.SplitTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getSplitType()
	 * @generated
	 */
	int SPLIT_TYPE = 39;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE__CONSTRAINTS = 2;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE__HEIGHT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE__NAME = 5;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE__TYPE = 6;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE__WIDTH = 7;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE__X = 8;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE__Y = 9;

	/**
	 * The number of structural features of the '<em>Split Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SPLIT_TYPE_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.StartTypeImpl <em>Start Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.StartTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getStartType()
	 * @generated
	 */
	int START_TYPE = 40;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Triggers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TYPE__TRIGGERS = 2;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TYPE__HEIGHT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TYPE__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TYPE__NAME = 5;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TYPE__WIDTH = 6;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TYPE__X = 7;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TYPE__Y = 8;

	/**
	 * The number of structural features of the '<em>Start Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_TYPE_FEATURE_COUNT = 9;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.StateTypeImpl <em>State Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.StateTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getStateType()
	 * @generated
	 */
	int STATE_TYPE = 41;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Timers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__TIMERS = 2;

	/**
	 * The feature id for the '<em><b>On Entry</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__ON_ENTRY = 3;

	/**
	 * The feature id for the '<em><b>On Exit</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__ON_EXIT = 4;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__CONSTRAINTS = 5;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__HEIGHT = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__ID = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__NAME = 8;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__WIDTH = 9;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__X = 10;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE__Y = 11;

	/**
	 * The number of structural features of the '<em>State Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_TYPE_FEATURE_COUNT = 12;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl <em>Sub Process Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.SubProcessTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getSubProcessType()
	 * @generated
	 */
	int SUB_PROCESS_TYPE = 42;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Mapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__MAPPING = 2;

	/**
	 * The feature id for the '<em><b>Timers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__TIMERS = 3;

	/**
	 * The feature id for the '<em><b>On Entry</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__ON_ENTRY = 4;

	/**
	 * The feature id for the '<em><b>On Exit</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__ON_EXIT = 5;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__HEIGHT = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__ID = 7;

	/**
	 * The feature id for the '<em><b>Independent</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__INDEPENDENT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__NAME = 9;

	/**
	 * The feature id for the '<em><b>Process Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__PROCESS_ID = 10;

	/**
	 * The feature id for the '<em><b>Wait For Completion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__WAIT_FOR_COMPLETION = 11;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__WIDTH = 12;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__X = 13;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE__Y = 14;

	/**
	 * The number of structural features of the '<em>Sub Process Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_PROCESS_TYPE_FEATURE_COUNT = 15;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.SwimlanesTypeImpl <em>Swimlanes Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.SwimlanesTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getSwimlanesType()
	 * @generated
	 */
	int SWIMLANES_TYPE = 43;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANES_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Swimlane</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANES_TYPE__SWIMLANE = 1;

	/**
	 * The number of structural features of the '<em>Swimlanes Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANES_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.SwimlaneTypeImpl <em>Swimlane Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.SwimlaneTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getSwimlaneType()
	 * @generated
	 */
	int SWIMLANE_TYPE = 44;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE_TYPE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Swimlane Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SWIMLANE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.TimerNodeTypeImpl <em>Timer Node Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.TimerNodeTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getTimerNodeType()
	 * @generated
	 */
	int TIMER_NODE_TYPE = 45;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_NODE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_NODE_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_NODE_TYPE__DELAY = 2;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_NODE_TYPE__HEIGHT = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_NODE_TYPE__ID = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_NODE_TYPE__NAME = 5;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_NODE_TYPE__PERIOD = 6;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_NODE_TYPE__WIDTH = 7;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_NODE_TYPE__X = 8;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_NODE_TYPE__Y = 9;

	/**
	 * The number of structural features of the '<em>Timer Node Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_NODE_TYPE_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.TimersTypeImpl <em>Timers Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.TimersTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getTimersType()
	 * @generated
	 */
	int TIMERS_TYPE = 46;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMERS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMERS_TYPE__TIMER = 1;

	/**
	 * The number of structural features of the '<em>Timers Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMERS_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.TimerTypeImpl <em>Timer Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.TimerTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getTimerType()
	 * @generated
	 */
	int TIMER_TYPE = 47;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__ACTION = 0;

	/**
	 * The feature id for the '<em><b>Delay</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__DELAY = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__ID = 2;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE__PERIOD = 3;

	/**
	 * The number of structural features of the '<em>Timer Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.TriggersTypeImpl <em>Triggers Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.TriggersTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getTriggersType()
	 * @generated
	 */
	int TRIGGERS_TYPE = 48;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_TYPE__TRIGGER = 1;

	/**
	 * The number of structural features of the '<em>Triggers Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGERS_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.TriggerTypeImpl <em>Trigger Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.TriggerTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getTriggerType()
	 * @generated
	 */
	int TRIGGER_TYPE = 49;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__CONSTRAINT = 1;

	/**
	 * The feature id for the '<em><b>Event Filters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__EVENT_FILTERS = 2;

	/**
	 * The feature id for the '<em><b>Mapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__MAPPING = 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE__TYPE = 4;

	/**
	 * The number of structural features of the '<em>Trigger Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_TYPE_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.TypeTypeImpl <em>Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.TypeTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getTypeType()
	 * @generated
	 */
	int TYPE_TYPE = 50;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_TYPE__CLASS_NAME = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_TYPE__NAME = 1;

	/**
	 * The number of structural features of the '<em>Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.ValueTypeImpl <em>Value Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.ValueTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getValueType()
	 * @generated
	 */
	int VALUE_TYPE = 51;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Value Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_TYPE_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.VariablesTypeImpl <em>Variables Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.VariablesTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getVariablesType()
	 * @generated
	 */
	int VARIABLES_TYPE = 52;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLES_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLES_TYPE__VARIABLE = 1;

	/**
	 * The number of structural features of the '<em>Variables Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLES_TYPE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.VariableTypeImpl <em>Variable Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.VariableTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getVariableType()
	 * @generated
	 */
	int VARIABLE_TYPE = 53;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TYPE__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TYPE__VALUE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TYPE__NAME = 3;

	/**
	 * The number of structural features of the '<em>Variable Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE_TYPE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.WorkItemTypeImpl <em>Work Item Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.WorkItemTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getWorkItemType()
	 * @generated
	 */
	int WORK_ITEM_TYPE = 54;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Meta Data</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__META_DATA = 1;

	/**
	 * The feature id for the '<em><b>Timers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__TIMERS = 2;

	/**
	 * The feature id for the '<em><b>Work</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__WORK = 3;

	/**
	 * The feature id for the '<em><b>Mapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__MAPPING = 4;

	/**
	 * The feature id for the '<em><b>On Entry</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__ON_ENTRY = 5;

	/**
	 * The feature id for the '<em><b>On Exit</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__ON_EXIT = 6;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__HEIGHT = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__ID = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__NAME = 9;

	/**
	 * The feature id for the '<em><b>Wait For Completion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__WAIT_FOR_COMPLETION = 10;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__WIDTH = 11;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__X = 12;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE__Y = 13;

	/**
	 * The number of structural features of the '<em>Work Item Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_ITEM_TYPE_FEATURE_COUNT = 14;

	/**
	 * The meta object id for the '{@link org.drools.drools._5._0.process.impl.WorkTypeImpl <em>Work Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.drools.drools._5._0.process.impl.WorkTypeImpl
	 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getWorkType()
	 * @generated
	 */
	int WORK_TYPE = 55;

	/**
	 * The feature id for the '<em><b>Group</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_TYPE__GROUP = 0;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_TYPE__PARAMETER = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_TYPE__NAME = 2;

	/**
	 * The number of structural features of the '<em>Work Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORK_TYPE_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ActionNodeType <em>Action Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Node Type</em>'.
	 * @see org.drools.drools._5._0.process.ActionNodeType
	 * @generated
	 */
	EClass getActionNodeType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.ActionNodeType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.ActionNodeType#getGroup()
	 * @see #getActionNodeType()
	 * @generated
	 */
	EAttribute getActionNodeType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ActionNodeType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.ActionNodeType#getMetaData()
	 * @see #getActionNodeType()
	 * @generated
	 */
	EReference getActionNodeType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ActionNodeType#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action</em>'.
	 * @see org.drools.drools._5._0.process.ActionNodeType#getAction()
	 * @see #getActionNodeType()
	 * @generated
	 */
	EReference getActionNodeType_Action();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ActionNodeType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.ActionNodeType#getHeight()
	 * @see #getActionNodeType()
	 * @generated
	 */
	EAttribute getActionNodeType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ActionNodeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.ActionNodeType#getId()
	 * @see #getActionNodeType()
	 * @generated
	 */
	EAttribute getActionNodeType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ActionNodeType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.ActionNodeType#getName()
	 * @see #getActionNodeType()
	 * @generated
	 */
	EAttribute getActionNodeType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ActionNodeType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.ActionNodeType#getWidth()
	 * @see #getActionNodeType()
	 * @generated
	 */
	EAttribute getActionNodeType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ActionNodeType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.ActionNodeType#getX()
	 * @see #getActionNodeType()
	 * @generated
	 */
	EAttribute getActionNodeType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ActionNodeType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.ActionNodeType#getY()
	 * @see #getActionNodeType()
	 * @generated
	 */
	EAttribute getActionNodeType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ActionType <em>Action Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Type</em>'.
	 * @see org.drools.drools._5._0.process.ActionType
	 * @generated
	 */
	EClass getActionType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ActionType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.drools.drools._5._0.process.ActionType#getValue()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ActionType#getDialect <em>Dialect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dialect</em>'.
	 * @see org.drools.drools._5._0.process.ActionType#getDialect()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_Dialect();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ActionType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.ActionType#getName()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ActionType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.ActionType#getType()
	 * @see #getActionType()
	 * @generated
	 */
	EAttribute getActionType_Type();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.CompositeType <em>Composite Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Type</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType
	 * @generated
	 */
	EClass getCompositeType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.CompositeType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getGroup()
	 * @see #getCompositeType()
	 * @generated
	 */
	EAttribute getCompositeType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.CompositeType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getMetaData()
	 * @see #getCompositeType()
	 * @generated
	 */
	EReference getCompositeType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.CompositeType#getTimers <em>Timers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timers</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getTimers()
	 * @see #getCompositeType()
	 * @generated
	 */
	EReference getCompositeType_Timers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.CompositeType#getOnEntry <em>On Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Entry</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getOnEntry()
	 * @see #getCompositeType()
	 * @generated
	 */
	EReference getCompositeType_OnEntry();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.CompositeType#getOnExit <em>On Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Exit</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getOnExit()
	 * @see #getCompositeType()
	 * @generated
	 */
	EReference getCompositeType_OnExit();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.CompositeType#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getVariables()
	 * @see #getCompositeType()
	 * @generated
	 */
	EReference getCompositeType_Variables();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.CompositeType#getExceptionHandlers <em>Exception Handlers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handlers</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getExceptionHandlers()
	 * @see #getCompositeType()
	 * @generated
	 */
	EReference getCompositeType_ExceptionHandlers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.CompositeType#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getNodes()
	 * @see #getCompositeType()
	 * @generated
	 */
	EReference getCompositeType_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.CompositeType#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getConnections()
	 * @see #getCompositeType()
	 * @generated
	 */
	EReference getCompositeType_Connections();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.CompositeType#getInPorts <em>In Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>In Ports</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getInPorts()
	 * @see #getCompositeType()
	 * @generated
	 */
	EReference getCompositeType_InPorts();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.CompositeType#getOutPorts <em>Out Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Out Ports</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getOutPorts()
	 * @see #getCompositeType()
	 * @generated
	 */
	EReference getCompositeType_OutPorts();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.CompositeType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getHeight()
	 * @see #getCompositeType()
	 * @generated
	 */
	EAttribute getCompositeType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.CompositeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getId()
	 * @see #getCompositeType()
	 * @generated
	 */
	EAttribute getCompositeType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.CompositeType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getName()
	 * @see #getCompositeType()
	 * @generated
	 */
	EAttribute getCompositeType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.CompositeType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getWidth()
	 * @see #getCompositeType()
	 * @generated
	 */
	EAttribute getCompositeType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.CompositeType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getX()
	 * @see #getCompositeType()
	 * @generated
	 */
	EAttribute getCompositeType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.CompositeType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.CompositeType#getY()
	 * @see #getCompositeType()
	 * @generated
	 */
	EAttribute getCompositeType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ConnectionsType <em>Connections Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connections Type</em>'.
	 * @see org.drools.drools._5._0.process.ConnectionsType
	 * @generated
	 */
	EClass getConnectionsType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.ConnectionsType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.ConnectionsType#getGroup()
	 * @see #getConnectionsType()
	 * @generated
	 */
	EAttribute getConnectionsType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ConnectionsType#getConnection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection</em>'.
	 * @see org.drools.drools._5._0.process.ConnectionsType#getConnection()
	 * @see #getConnectionsType()
	 * @generated
	 */
	EReference getConnectionsType_Connection();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ConnectionType <em>Connection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Type</em>'.
	 * @see org.drools.drools._5._0.process.ConnectionType
	 * @generated
	 */
	EClass getConnectionType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ConnectionType#getBendpoints <em>Bendpoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bendpoints</em>'.
	 * @see org.drools.drools._5._0.process.ConnectionType#getBendpoints()
	 * @see #getConnectionType()
	 * @generated
	 */
	EAttribute getConnectionType_Bendpoints();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ConnectionType#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From</em>'.
	 * @see org.drools.drools._5._0.process.ConnectionType#getFrom()
	 * @see #getConnectionType()
	 * @generated
	 */
	EAttribute getConnectionType_From();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ConnectionType#getFromType <em>From Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From Type</em>'.
	 * @see org.drools.drools._5._0.process.ConnectionType#getFromType()
	 * @see #getConnectionType()
	 * @generated
	 */
	EAttribute getConnectionType_FromType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ConnectionType#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To</em>'.
	 * @see org.drools.drools._5._0.process.ConnectionType#getTo()
	 * @see #getConnectionType()
	 * @generated
	 */
	EAttribute getConnectionType_To();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ConnectionType#getToType <em>To Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To Type</em>'.
	 * @see org.drools.drools._5._0.process.ConnectionType#getToType()
	 * @see #getConnectionType()
	 * @generated
	 */
	EAttribute getConnectionType_ToType();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ConstraintsType <em>Constraints Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraints Type</em>'.
	 * @see org.drools.drools._5._0.process.ConstraintsType
	 * @generated
	 */
	EClass getConstraintsType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.ConstraintsType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.ConstraintsType#getGroup()
	 * @see #getConstraintsType()
	 * @generated
	 */
	EAttribute getConstraintsType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ConstraintsType#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraint</em>'.
	 * @see org.drools.drools._5._0.process.ConstraintsType#getConstraint()
	 * @see #getConstraintsType()
	 * @generated
	 */
	EReference getConstraintsType_Constraint();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ConstraintType <em>Constraint Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint Type</em>'.
	 * @see org.drools.drools._5._0.process.ConstraintType
	 * @generated
	 */
	EClass getConstraintType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ConstraintType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.drools.drools._5._0.process.ConstraintType#getValue()
	 * @see #getConstraintType()
	 * @generated
	 */
	EAttribute getConstraintType_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ConstraintType#getDialect <em>Dialect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dialect</em>'.
	 * @see org.drools.drools._5._0.process.ConstraintType#getDialect()
	 * @see #getConstraintType()
	 * @generated
	 */
	EAttribute getConstraintType_Dialect();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ConstraintType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.ConstraintType#getName()
	 * @see #getConstraintType()
	 * @generated
	 */
	EAttribute getConstraintType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ConstraintType#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see org.drools.drools._5._0.process.ConstraintType#getPriority()
	 * @see #getConstraintType()
	 * @generated
	 */
	EAttribute getConstraintType_Priority();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ConstraintType#getToNodeId <em>To Node Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To Node Id</em>'.
	 * @see org.drools.drools._5._0.process.ConstraintType#getToNodeId()
	 * @see #getConstraintType()
	 * @generated
	 */
	EAttribute getConstraintType_ToNodeId();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ConstraintType#getToType <em>To Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To Type</em>'.
	 * @see org.drools.drools._5._0.process.ConstraintType#getToType()
	 * @see #getConstraintType()
	 * @generated
	 */
	EAttribute getConstraintType_ToType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ConstraintType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.ConstraintType#getType()
	 * @see #getConstraintType()
	 * @generated
	 */
	EAttribute getConstraintType_Type();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the map '{@link org.drools.drools._5._0.process.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.drools.drools._5._0.process.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getAction()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Action();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getActionNode <em>Action Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action Node</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getActionNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ActionNode();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getComposite <em>Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Composite</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getComposite()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Composite();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getConnection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Connection</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getConnection()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Connection();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Connections</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getConnections()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Connections();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Constraint</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getConstraint()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Constraint();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Constraints</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getConstraints()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Constraints();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getDynamic <em>Dynamic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Dynamic</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getDynamic()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Dynamic();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>End</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getEnd()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_End();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getEventFilter <em>Event Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event Filter</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getEventFilter()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_EventFilter();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getEventFilters <em>Event Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event Filters</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getEventFilters()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_EventFilters();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getEventNode <em>Event Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Event Node</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getEventNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_EventNode();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exception Handler</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getExceptionHandler()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExceptionHandler();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getExceptionHandlers <em>Exception Handlers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Exception Handlers</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getExceptionHandlers()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ExceptionHandlers();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getFault <em>Fault</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Fault</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getFault()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Fault();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getForEach <em>For Each</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>For Each</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getForEach()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_ForEach();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getFunctionImport <em>Function Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function Import</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getFunctionImport()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FunctionImport();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getFunctionImports <em>Function Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function Imports</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getFunctionImports()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_FunctionImports();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getGlobal <em>Global</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Global</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getGlobal()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Global();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getGlobals <em>Globals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Globals</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getGlobals()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Globals();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getHeader <em>Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Header</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getHeader()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Header();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getHumanTask <em>Human Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Human Task</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getHumanTask()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_HumanTask();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getImport <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Import</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getImport()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Import();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getImports <em>Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Imports</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getImports()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Imports();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getInPort <em>In Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>In Port</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getInPort()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_InPort();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getInPorts <em>In Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>In Ports</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getInPorts()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_InPorts();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getJoin <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Join</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getJoin()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Join();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getMapping <em>Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Mapping</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getMapping()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Mapping();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getMetaData()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_MetaData();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getMilestone <em>Milestone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Milestone</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getMilestone()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Milestone();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Nodes</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getNodes()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Nodes();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getOnEntry <em>On Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>On Entry</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getOnEntry()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_OnEntry();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getOnExit <em>On Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>On Exit</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getOnExit()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_OnExit();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getOutPort <em>Out Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Out Port</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getOutPort()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_OutPort();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getOutPorts <em>Out Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Out Ports</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getOutPorts()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_OutPorts();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Parameter</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getParameter()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Parameter();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getProcess <em>Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Process</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getProcess()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Process();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getRuleSet <em>Rule Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Rule Set</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getRuleSet()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_RuleSet();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getSplit <em>Split</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Split</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getSplit()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Split();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Start</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getStart()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Start();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>State</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getState()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_State();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getSubProcess <em>Sub Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Sub Process</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getSubProcess()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_SubProcess();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getSwimlane <em>Swimlane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Swimlane</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getSwimlane()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Swimlane();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getSwimlanes <em>Swimlanes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Swimlanes</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getSwimlanes()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Swimlanes();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Timer</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getTimer()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Timer();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getTimerNode <em>Timer Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Timer Node</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getTimerNode()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_TimerNode();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getTimers <em>Timers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Timers</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getTimers()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Timers();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Trigger</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getTrigger()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Trigger();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Triggers</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getTriggers()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Triggers();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getType()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Type();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getValue()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Value();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variable</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getVariable()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Variable();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variables</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getVariables()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Variables();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getWork <em>Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Work</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getWork()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Work();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.DocumentRoot#getWorkItem <em>Work Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Work Item</em>'.
	 * @see org.drools.drools._5._0.process.DocumentRoot#getWorkItem()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_WorkItem();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.DynamicType <em>Dynamic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Type</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType
	 * @generated
	 */
	EClass getDynamicType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.DynamicType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getGroup()
	 * @see #getDynamicType()
	 * @generated
	 */
	EAttribute getDynamicType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.DynamicType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getMetaData()
	 * @see #getDynamicType()
	 * @generated
	 */
	EReference getDynamicType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.DynamicType#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getVariables()
	 * @see #getDynamicType()
	 * @generated
	 */
	EReference getDynamicType_Variables();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.DynamicType#getExceptionHandlers <em>Exception Handlers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handlers</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getExceptionHandlers()
	 * @see #getDynamicType()
	 * @generated
	 */
	EReference getDynamicType_ExceptionHandlers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.DynamicType#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getNodes()
	 * @see #getDynamicType()
	 * @generated
	 */
	EReference getDynamicType_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.DynamicType#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getConnections()
	 * @see #getDynamicType()
	 * @generated
	 */
	EReference getDynamicType_Connections();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.DynamicType#getInPorts <em>In Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>In Ports</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getInPorts()
	 * @see #getDynamicType()
	 * @generated
	 */
	EReference getDynamicType_InPorts();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.DynamicType#getOutPorts <em>Out Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Out Ports</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getOutPorts()
	 * @see #getDynamicType()
	 * @generated
	 */
	EReference getDynamicType_OutPorts();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.DynamicType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getHeight()
	 * @see #getDynamicType()
	 * @generated
	 */
	EAttribute getDynamicType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.DynamicType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getId()
	 * @see #getDynamicType()
	 * @generated
	 */
	EAttribute getDynamicType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.DynamicType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getName()
	 * @see #getDynamicType()
	 * @generated
	 */
	EAttribute getDynamicType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.DynamicType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getWidth()
	 * @see #getDynamicType()
	 * @generated
	 */
	EAttribute getDynamicType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.DynamicType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getX()
	 * @see #getDynamicType()
	 * @generated
	 */
	EAttribute getDynamicType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.DynamicType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.DynamicType#getY()
	 * @see #getDynamicType()
	 * @generated
	 */
	EAttribute getDynamicType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.EndType <em>End Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>End Type</em>'.
	 * @see org.drools.drools._5._0.process.EndType
	 * @generated
	 */
	EClass getEndType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.EndType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.EndType#getGroup()
	 * @see #getEndType()
	 * @generated
	 */
	EAttribute getEndType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.EndType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.EndType#getMetaData()
	 * @see #getEndType()
	 * @generated
	 */
	EReference getEndType_MetaData();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EndType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.EndType#getHeight()
	 * @see #getEndType()
	 * @generated
	 */
	EAttribute getEndType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EndType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.EndType#getId()
	 * @see #getEndType()
	 * @generated
	 */
	EAttribute getEndType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EndType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.EndType#getName()
	 * @see #getEndType()
	 * @generated
	 */
	EAttribute getEndType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EndType#getTerminate <em>Terminate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Terminate</em>'.
	 * @see org.drools.drools._5._0.process.EndType#getTerminate()
	 * @see #getEndType()
	 * @generated
	 */
	EAttribute getEndType_Terminate();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EndType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.EndType#getWidth()
	 * @see #getEndType()
	 * @generated
	 */
	EAttribute getEndType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EndType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.EndType#getX()
	 * @see #getEndType()
	 * @generated
	 */
	EAttribute getEndType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EndType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.EndType#getY()
	 * @see #getEndType()
	 * @generated
	 */
	EAttribute getEndType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.EventFiltersType <em>Event Filters Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Filters Type</em>'.
	 * @see org.drools.drools._5._0.process.EventFiltersType
	 * @generated
	 */
	EClass getEventFiltersType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.EventFiltersType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.EventFiltersType#getGroup()
	 * @see #getEventFiltersType()
	 * @generated
	 */
	EAttribute getEventFiltersType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.EventFiltersType#getEventFilter <em>Event Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Filter</em>'.
	 * @see org.drools.drools._5._0.process.EventFiltersType#getEventFilter()
	 * @see #getEventFiltersType()
	 * @generated
	 */
	EReference getEventFiltersType_EventFilter();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.EventFilterType <em>Event Filter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Filter Type</em>'.
	 * @see org.drools.drools._5._0.process.EventFilterType
	 * @generated
	 */
	EClass getEventFilterType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EventFilterType#getEventType <em>Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Event Type</em>'.
	 * @see org.drools.drools._5._0.process.EventFilterType#getEventType()
	 * @see #getEventFilterType()
	 * @generated
	 */
	EAttribute getEventFilterType_EventType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EventFilterType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.EventFilterType#getType()
	 * @see #getEventFilterType()
	 * @generated
	 */
	EAttribute getEventFilterType_Type();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.EventNodeType <em>Event Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Node Type</em>'.
	 * @see org.drools.drools._5._0.process.EventNodeType
	 * @generated
	 */
	EClass getEventNodeType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.EventNodeType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.EventNodeType#getGroup()
	 * @see #getEventNodeType()
	 * @generated
	 */
	EAttribute getEventNodeType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.EventNodeType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.EventNodeType#getMetaData()
	 * @see #getEventNodeType()
	 * @generated
	 */
	EReference getEventNodeType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.EventNodeType#getEventFilters <em>Event Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Filters</em>'.
	 * @see org.drools.drools._5._0.process.EventNodeType#getEventFilters()
	 * @see #getEventNodeType()
	 * @generated
	 */
	EReference getEventNodeType_EventFilters();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EventNodeType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.EventNodeType#getHeight()
	 * @see #getEventNodeType()
	 * @generated
	 */
	EAttribute getEventNodeType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EventNodeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.EventNodeType#getId()
	 * @see #getEventNodeType()
	 * @generated
	 */
	EAttribute getEventNodeType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EventNodeType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.EventNodeType#getName()
	 * @see #getEventNodeType()
	 * @generated
	 */
	EAttribute getEventNodeType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EventNodeType#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope</em>'.
	 * @see org.drools.drools._5._0.process.EventNodeType#getScope()
	 * @see #getEventNodeType()
	 * @generated
	 */
	EAttribute getEventNodeType_Scope();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EventNodeType#getVariableName <em>Variable Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variable Name</em>'.
	 * @see org.drools.drools._5._0.process.EventNodeType#getVariableName()
	 * @see #getEventNodeType()
	 * @generated
	 */
	EAttribute getEventNodeType_VariableName();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EventNodeType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.EventNodeType#getWidth()
	 * @see #getEventNodeType()
	 * @generated
	 */
	EAttribute getEventNodeType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EventNodeType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.EventNodeType#getX()
	 * @see #getEventNodeType()
	 * @generated
	 */
	EAttribute getEventNodeType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.EventNodeType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.EventNodeType#getY()
	 * @see #getEventNodeType()
	 * @generated
	 */
	EAttribute getEventNodeType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ExceptionHandlersType <em>Exception Handlers Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exception Handlers Type</em>'.
	 * @see org.drools.drools._5._0.process.ExceptionHandlersType
	 * @generated
	 */
	EClass getExceptionHandlersType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.ExceptionHandlersType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.ExceptionHandlersType#getGroup()
	 * @see #getExceptionHandlersType()
	 * @generated
	 */
	EAttribute getExceptionHandlersType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ExceptionHandlersType#getExceptionHandler <em>Exception Handler</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handler</em>'.
	 * @see org.drools.drools._5._0.process.ExceptionHandlersType#getExceptionHandler()
	 * @see #getExceptionHandlersType()
	 * @generated
	 */
	EReference getExceptionHandlersType_ExceptionHandler();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ExceptionHandlerType <em>Exception Handler Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exception Handler Type</em>'.
	 * @see org.drools.drools._5._0.process.ExceptionHandlerType
	 * @generated
	 */
	EClass getExceptionHandlerType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.ExceptionHandlerType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.ExceptionHandlerType#getGroup()
	 * @see #getExceptionHandlerType()
	 * @generated
	 */
	EAttribute getExceptionHandlerType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ExceptionHandlerType#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action</em>'.
	 * @see org.drools.drools._5._0.process.ExceptionHandlerType#getAction()
	 * @see #getExceptionHandlerType()
	 * @generated
	 */
	EReference getExceptionHandlerType_Action();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ExceptionHandlerType#getFaultName <em>Fault Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fault Name</em>'.
	 * @see org.drools.drools._5._0.process.ExceptionHandlerType#getFaultName()
	 * @see #getExceptionHandlerType()
	 * @generated
	 */
	EAttribute getExceptionHandlerType_FaultName();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ExceptionHandlerType#getFaultVariable <em>Fault Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fault Variable</em>'.
	 * @see org.drools.drools._5._0.process.ExceptionHandlerType#getFaultVariable()
	 * @see #getExceptionHandlerType()
	 * @generated
	 */
	EAttribute getExceptionHandlerType_FaultVariable();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ExceptionHandlerType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.ExceptionHandlerType#getType()
	 * @see #getExceptionHandlerType()
	 * @generated
	 */
	EAttribute getExceptionHandlerType_Type();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.FaultType <em>Fault Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fault Type</em>'.
	 * @see org.drools.drools._5._0.process.FaultType
	 * @generated
	 */
	EClass getFaultType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.FaultType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.FaultType#getGroup()
	 * @see #getFaultType()
	 * @generated
	 */
	EAttribute getFaultType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.FaultType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.FaultType#getMetaData()
	 * @see #getFaultType()
	 * @generated
	 */
	EReference getFaultType_MetaData();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.FaultType#getFaultName <em>Fault Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fault Name</em>'.
	 * @see org.drools.drools._5._0.process.FaultType#getFaultName()
	 * @see #getFaultType()
	 * @generated
	 */
	EAttribute getFaultType_FaultName();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.FaultType#getFaultVariable <em>Fault Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fault Variable</em>'.
	 * @see org.drools.drools._5._0.process.FaultType#getFaultVariable()
	 * @see #getFaultType()
	 * @generated
	 */
	EAttribute getFaultType_FaultVariable();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.FaultType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.FaultType#getHeight()
	 * @see #getFaultType()
	 * @generated
	 */
	EAttribute getFaultType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.FaultType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.FaultType#getId()
	 * @see #getFaultType()
	 * @generated
	 */
	EAttribute getFaultType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.FaultType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.FaultType#getName()
	 * @see #getFaultType()
	 * @generated
	 */
	EAttribute getFaultType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.FaultType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.FaultType#getWidth()
	 * @see #getFaultType()
	 * @generated
	 */
	EAttribute getFaultType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.FaultType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.FaultType#getX()
	 * @see #getFaultType()
	 * @generated
	 */
	EAttribute getFaultType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.FaultType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.FaultType#getY()
	 * @see #getFaultType()
	 * @generated
	 */
	EAttribute getFaultType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ForEachType <em>For Each Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>For Each Type</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType
	 * @generated
	 */
	EClass getForEachType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.ForEachType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getGroup()
	 * @see #getForEachType()
	 * @generated
	 */
	EAttribute getForEachType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ForEachType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getMetaData()
	 * @see #getForEachType()
	 * @generated
	 */
	EReference getForEachType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ForEachType#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getNodes()
	 * @see #getForEachType()
	 * @generated
	 */
	EReference getForEachType_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ForEachType#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getConnections()
	 * @see #getForEachType()
	 * @generated
	 */
	EReference getForEachType_Connections();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ForEachType#getInPorts <em>In Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>In Ports</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getInPorts()
	 * @see #getForEachType()
	 * @generated
	 */
	EReference getForEachType_InPorts();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ForEachType#getOutPorts <em>Out Ports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Out Ports</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getOutPorts()
	 * @see #getForEachType()
	 * @generated
	 */
	EReference getForEachType_OutPorts();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ForEachType#getCollectionExpression <em>Collection Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Collection Expression</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getCollectionExpression()
	 * @see #getForEachType()
	 * @generated
	 */
	EAttribute getForEachType_CollectionExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ForEachType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getHeight()
	 * @see #getForEachType()
	 * @generated
	 */
	EAttribute getForEachType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ForEachType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getId()
	 * @see #getForEachType()
	 * @generated
	 */
	EAttribute getForEachType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ForEachType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getName()
	 * @see #getForEachType()
	 * @generated
	 */
	EAttribute getForEachType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ForEachType#getVariableName <em>Variable Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variable Name</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getVariableName()
	 * @see #getForEachType()
	 * @generated
	 */
	EAttribute getForEachType_VariableName();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ForEachType#getWaitForCompletion <em>Wait For Completion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wait For Completion</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getWaitForCompletion()
	 * @see #getForEachType()
	 * @generated
	 */
	EAttribute getForEachType_WaitForCompletion();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ForEachType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getWidth()
	 * @see #getForEachType()
	 * @generated
	 */
	EAttribute getForEachType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ForEachType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getX()
	 * @see #getForEachType()
	 * @generated
	 */
	EAttribute getForEachType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ForEachType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.ForEachType#getY()
	 * @see #getForEachType()
	 * @generated
	 */
	EAttribute getForEachType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.FunctionImportsType <em>Function Imports Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Imports Type</em>'.
	 * @see org.drools.drools._5._0.process.FunctionImportsType
	 * @generated
	 */
	EClass getFunctionImportsType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.FunctionImportsType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.FunctionImportsType#getGroup()
	 * @see #getFunctionImportsType()
	 * @generated
	 */
	EAttribute getFunctionImportsType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.FunctionImportsType#getFunctionImport <em>Function Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Function Import</em>'.
	 * @see org.drools.drools._5._0.process.FunctionImportsType#getFunctionImport()
	 * @see #getFunctionImportsType()
	 * @generated
	 */
	EReference getFunctionImportsType_FunctionImport();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.FunctionImportType <em>Function Import Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function Import Type</em>'.
	 * @see org.drools.drools._5._0.process.FunctionImportType
	 * @generated
	 */
	EClass getFunctionImportType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.FunctionImportType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.FunctionImportType#getName()
	 * @see #getFunctionImportType()
	 * @generated
	 */
	EAttribute getFunctionImportType_Name();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.GlobalsType <em>Globals Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Globals Type</em>'.
	 * @see org.drools.drools._5._0.process.GlobalsType
	 * @generated
	 */
	EClass getGlobalsType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.GlobalsType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.GlobalsType#getGroup()
	 * @see #getGlobalsType()
	 * @generated
	 */
	EAttribute getGlobalsType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.GlobalsType#getGlobal <em>Global</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Global</em>'.
	 * @see org.drools.drools._5._0.process.GlobalsType#getGlobal()
	 * @see #getGlobalsType()
	 * @generated
	 */
	EReference getGlobalsType_Global();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.GlobalType <em>Global Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Global Type</em>'.
	 * @see org.drools.drools._5._0.process.GlobalType
	 * @generated
	 */
	EClass getGlobalType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.GlobalType#getIdentifier <em>Identifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Identifier</em>'.
	 * @see org.drools.drools._5._0.process.GlobalType#getIdentifier()
	 * @see #getGlobalType()
	 * @generated
	 */
	EAttribute getGlobalType_Identifier();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.GlobalType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.GlobalType#getType()
	 * @see #getGlobalType()
	 * @generated
	 */
	EAttribute getGlobalType_Type();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.HeaderType <em>Header Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Header Type</em>'.
	 * @see org.drools.drools._5._0.process.HeaderType
	 * @generated
	 */
	EClass getHeaderType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.HeaderType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.HeaderType#getGroup()
	 * @see #getHeaderType()
	 * @generated
	 */
	EAttribute getHeaderType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.HeaderType#getImports <em>Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Imports</em>'.
	 * @see org.drools.drools._5._0.process.HeaderType#getImports()
	 * @see #getHeaderType()
	 * @generated
	 */
	EReference getHeaderType_Imports();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.HeaderType#getFunctionImports <em>Function Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Function Imports</em>'.
	 * @see org.drools.drools._5._0.process.HeaderType#getFunctionImports()
	 * @see #getHeaderType()
	 * @generated
	 */
	EReference getHeaderType_FunctionImports();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.HeaderType#getGlobals <em>Globals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Globals</em>'.
	 * @see org.drools.drools._5._0.process.HeaderType#getGlobals()
	 * @see #getHeaderType()
	 * @generated
	 */
	EReference getHeaderType_Globals();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.HeaderType#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see org.drools.drools._5._0.process.HeaderType#getVariables()
	 * @see #getHeaderType()
	 * @generated
	 */
	EReference getHeaderType_Variables();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.HeaderType#getSwimlanes <em>Swimlanes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Swimlanes</em>'.
	 * @see org.drools.drools._5._0.process.HeaderType#getSwimlanes()
	 * @see #getHeaderType()
	 * @generated
	 */
	EReference getHeaderType_Swimlanes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.HeaderType#getExceptionHandlers <em>Exception Handlers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exception Handlers</em>'.
	 * @see org.drools.drools._5._0.process.HeaderType#getExceptionHandlers()
	 * @see #getHeaderType()
	 * @generated
	 */
	EReference getHeaderType_ExceptionHandlers();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.HumanTaskType <em>Human Task Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Human Task Type</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType
	 * @generated
	 */
	EClass getHumanTaskType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.HumanTaskType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getGroup()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EAttribute getHumanTaskType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.HumanTaskType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getMetaData()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EReference getHumanTaskType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.HumanTaskType#getWork <em>Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Work</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getWork()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EReference getHumanTaskType_Work();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.HumanTaskType#getMapping <em>Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mapping</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getMapping()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EReference getHumanTaskType_Mapping();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.HumanTaskType#getTimers <em>Timers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timers</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getTimers()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EReference getHumanTaskType_Timers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.HumanTaskType#getOnEntry <em>On Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Entry</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getOnEntry()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EReference getHumanTaskType_OnEntry();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.HumanTaskType#getOnExit <em>On Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Exit</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getOnExit()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EReference getHumanTaskType_OnExit();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.HumanTaskType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getHeight()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EAttribute getHumanTaskType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.HumanTaskType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getId()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EAttribute getHumanTaskType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.HumanTaskType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getName()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EAttribute getHumanTaskType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.HumanTaskType#getSwimlane <em>Swimlane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Swimlane</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getSwimlane()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EAttribute getHumanTaskType_Swimlane();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.HumanTaskType#getWaitForCompletion <em>Wait For Completion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wait For Completion</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getWaitForCompletion()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EAttribute getHumanTaskType_WaitForCompletion();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.HumanTaskType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getWidth()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EAttribute getHumanTaskType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.HumanTaskType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getX()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EAttribute getHumanTaskType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.HumanTaskType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.HumanTaskType#getY()
	 * @see #getHumanTaskType()
	 * @generated
	 */
	EAttribute getHumanTaskType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ImportsType <em>Imports Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Imports Type</em>'.
	 * @see org.drools.drools._5._0.process.ImportsType
	 * @generated
	 */
	EClass getImportsType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.ImportsType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.ImportsType#getGroup()
	 * @see #getImportsType()
	 * @generated
	 */
	EAttribute getImportsType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ImportsType#getImport <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Import</em>'.
	 * @see org.drools.drools._5._0.process.ImportsType#getImport()
	 * @see #getImportsType()
	 * @generated
	 */
	EReference getImportsType_Import();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ImportType <em>Import Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import Type</em>'.
	 * @see org.drools.drools._5._0.process.ImportType
	 * @generated
	 */
	EClass getImportType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ImportType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.ImportType#getName()
	 * @see #getImportType()
	 * @generated
	 */
	EAttribute getImportType_Name();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.InPortsType <em>In Ports Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>In Ports Type</em>'.
	 * @see org.drools.drools._5._0.process.InPortsType
	 * @generated
	 */
	EClass getInPortsType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.InPortsType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.InPortsType#getGroup()
	 * @see #getInPortsType()
	 * @generated
	 */
	EAttribute getInPortsType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.InPortsType#getInPort <em>In Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>In Port</em>'.
	 * @see org.drools.drools._5._0.process.InPortsType#getInPort()
	 * @see #getInPortsType()
	 * @generated
	 */
	EReference getInPortsType_InPort();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.InPortType <em>In Port Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>In Port Type</em>'.
	 * @see org.drools.drools._5._0.process.InPortType
	 * @generated
	 */
	EClass getInPortType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.InPortType#getNodeId <em>Node Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Id</em>'.
	 * @see org.drools.drools._5._0.process.InPortType#getNodeId()
	 * @see #getInPortType()
	 * @generated
	 */
	EAttribute getInPortType_NodeId();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.InPortType#getNodeInType <em>Node In Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node In Type</em>'.
	 * @see org.drools.drools._5._0.process.InPortType#getNodeInType()
	 * @see #getInPortType()
	 * @generated
	 */
	EAttribute getInPortType_NodeInType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.InPortType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.InPortType#getType()
	 * @see #getInPortType()
	 * @generated
	 */
	EAttribute getInPortType_Type();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.JoinType <em>Join Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join Type</em>'.
	 * @see org.drools.drools._5._0.process.JoinType
	 * @generated
	 */
	EClass getJoinType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.JoinType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.JoinType#getGroup()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.JoinType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.JoinType#getMetaData()
	 * @see #getJoinType()
	 * @generated
	 */
	EReference getJoinType_MetaData();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.JoinType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.JoinType#getHeight()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.JoinType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.JoinType#getId()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.JoinType#getN <em>N</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>N</em>'.
	 * @see org.drools.drools._5._0.process.JoinType#getN()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_N();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.JoinType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.JoinType#getName()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.JoinType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.JoinType#getType()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.JoinType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.JoinType#getWidth()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.JoinType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.JoinType#getX()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.JoinType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.JoinType#getY()
	 * @see #getJoinType()
	 * @generated
	 */
	EAttribute getJoinType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.MappingType <em>Mapping Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mapping Type</em>'.
	 * @see org.drools.drools._5._0.process.MappingType
	 * @generated
	 */
	EClass getMappingType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.MappingType#getFrom <em>From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>From</em>'.
	 * @see org.drools.drools._5._0.process.MappingType#getFrom()
	 * @see #getMappingType()
	 * @generated
	 */
	EAttribute getMappingType_From();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.MappingType#getTo <em>To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>To</em>'.
	 * @see org.drools.drools._5._0.process.MappingType#getTo()
	 * @see #getMappingType()
	 * @generated
	 */
	EAttribute getMappingType_To();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.MappingType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.MappingType#getType()
	 * @see #getMappingType()
	 * @generated
	 */
	EAttribute getMappingType_Type();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.MetaDataType <em>Meta Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Meta Data Type</em>'.
	 * @see org.drools.drools._5._0.process.MetaDataType
	 * @generated
	 */
	EClass getMetaDataType();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.MetaDataType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.drools.drools._5._0.process.MetaDataType#getValue()
	 * @see #getMetaDataType()
	 * @generated
	 */
	EReference getMetaDataType_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.MetaDataType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.MetaDataType#getName()
	 * @see #getMetaDataType()
	 * @generated
	 */
	EAttribute getMetaDataType_Name();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.MilestoneType <em>Milestone Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Milestone Type</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType
	 * @generated
	 */
	EClass getMilestoneType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.MilestoneType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType#getGroup()
	 * @see #getMilestoneType()
	 * @generated
	 */
	EAttribute getMilestoneType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.MilestoneType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType#getMetaData()
	 * @see #getMilestoneType()
	 * @generated
	 */
	EReference getMilestoneType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.MilestoneType#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraint</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType#getConstraint()
	 * @see #getMilestoneType()
	 * @generated
	 */
	EReference getMilestoneType_Constraint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.MilestoneType#getTimers <em>Timers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timers</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType#getTimers()
	 * @see #getMilestoneType()
	 * @generated
	 */
	EReference getMilestoneType_Timers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.MilestoneType#getOnEntry <em>On Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Entry</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType#getOnEntry()
	 * @see #getMilestoneType()
	 * @generated
	 */
	EReference getMilestoneType_OnEntry();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.MilestoneType#getOnExit <em>On Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Exit</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType#getOnExit()
	 * @see #getMilestoneType()
	 * @generated
	 */
	EReference getMilestoneType_OnExit();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.MilestoneType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType#getHeight()
	 * @see #getMilestoneType()
	 * @generated
	 */
	EAttribute getMilestoneType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.MilestoneType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType#getId()
	 * @see #getMilestoneType()
	 * @generated
	 */
	EAttribute getMilestoneType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.MilestoneType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType#getName()
	 * @see #getMilestoneType()
	 * @generated
	 */
	EAttribute getMilestoneType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.MilestoneType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType#getWidth()
	 * @see #getMilestoneType()
	 * @generated
	 */
	EAttribute getMilestoneType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.MilestoneType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType#getX()
	 * @see #getMilestoneType()
	 * @generated
	 */
	EAttribute getMilestoneType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.MilestoneType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.MilestoneType#getY()
	 * @see #getMilestoneType()
	 * @generated
	 */
	EAttribute getMilestoneType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.NodesType <em>Nodes Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nodes Type</em>'.
	 * @see org.drools.drools._5._0.process.NodesType
	 * @generated
	 */
	EClass getNodesType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.NodesType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getGroup()
	 * @see #getNodesType()
	 * @generated
	 */
	EAttribute getNodesType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Start</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getStart()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_Start();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>End</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getEnd()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_End();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getActionNode <em>Action Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action Node</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getActionNode()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_ActionNode();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getRuleSet <em>Rule Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rule Set</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getRuleSet()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_RuleSet();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getSplit <em>Split</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Split</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getSplit()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_Split();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getJoin <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Join</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getJoin()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_Join();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getMilestone <em>Milestone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Milestone</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getMilestone()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_Milestone();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getSubProcess <em>Sub Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Process</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getSubProcess()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_SubProcess();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getWorkItem <em>Work Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Work Item</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getWorkItem()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_WorkItem();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getTimerNode <em>Timer Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer Node</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getTimerNode()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_TimerNode();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getHumanTask <em>Human Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Human Task</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getHumanTask()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_HumanTask();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getComposite <em>Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Composite</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getComposite()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_Composite();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getForEach <em>For Each</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>For Each</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getForEach()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_ForEach();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getEventNode <em>Event Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Node</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getEventNode()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_EventNode();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getFault <em>Fault</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fault</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getFault()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_Fault();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>State</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getState()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_State();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.NodesType#getDynamic <em>Dynamic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dynamic</em>'.
	 * @see org.drools.drools._5._0.process.NodesType#getDynamic()
	 * @see #getNodesType()
	 * @generated
	 */
	EReference getNodesType_Dynamic();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.OnEntryType <em>On Entry Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>On Entry Type</em>'.
	 * @see org.drools.drools._5._0.process.OnEntryType
	 * @generated
	 */
	EClass getOnEntryType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.OnEntryType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.OnEntryType#getGroup()
	 * @see #getOnEntryType()
	 * @generated
	 */
	EAttribute getOnEntryType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.OnEntryType#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action</em>'.
	 * @see org.drools.drools._5._0.process.OnEntryType#getAction()
	 * @see #getOnEntryType()
	 * @generated
	 */
	EReference getOnEntryType_Action();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.OnExitType <em>On Exit Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>On Exit Type</em>'.
	 * @see org.drools.drools._5._0.process.OnExitType
	 * @generated
	 */
	EClass getOnExitType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.OnExitType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.OnExitType#getGroup()
	 * @see #getOnExitType()
	 * @generated
	 */
	EAttribute getOnExitType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.OnExitType#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action</em>'.
	 * @see org.drools.drools._5._0.process.OnExitType#getAction()
	 * @see #getOnExitType()
	 * @generated
	 */
	EReference getOnExitType_Action();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.OutPortsType <em>Out Ports Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Out Ports Type</em>'.
	 * @see org.drools.drools._5._0.process.OutPortsType
	 * @generated
	 */
	EClass getOutPortsType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.OutPortsType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.OutPortsType#getGroup()
	 * @see #getOutPortsType()
	 * @generated
	 */
	EAttribute getOutPortsType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.OutPortsType#getOutPort <em>Out Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Out Port</em>'.
	 * @see org.drools.drools._5._0.process.OutPortsType#getOutPort()
	 * @see #getOutPortsType()
	 * @generated
	 */
	EReference getOutPortsType_OutPort();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.OutPortType <em>Out Port Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Out Port Type</em>'.
	 * @see org.drools.drools._5._0.process.OutPortType
	 * @generated
	 */
	EClass getOutPortType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.OutPortType#getNodeId <em>Node Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Id</em>'.
	 * @see org.drools.drools._5._0.process.OutPortType#getNodeId()
	 * @see #getOutPortType()
	 * @generated
	 */
	EAttribute getOutPortType_NodeId();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.OutPortType#getNodeOutType <em>Node Out Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Node Out Type</em>'.
	 * @see org.drools.drools._5._0.process.OutPortType#getNodeOutType()
	 * @see #getOutPortType()
	 * @generated
	 */
	EAttribute getOutPortType_NodeOutType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.OutPortType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.OutPortType#getType()
	 * @see #getOutPortType()
	 * @generated
	 */
	EAttribute getOutPortType_Type();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ParameterType <em>Parameter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Type</em>'.
	 * @see org.drools.drools._5._0.process.ParameterType
	 * @generated
	 */
	EClass getParameterType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.ParameterType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.ParameterType#getGroup()
	 * @see #getParameterType()
	 * @generated
	 */
	EAttribute getParameterType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ParameterType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.ParameterType#getType()
	 * @see #getParameterType()
	 * @generated
	 */
	EReference getParameterType_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ParameterType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Value</em>'.
	 * @see org.drools.drools._5._0.process.ParameterType#getValue()
	 * @see #getParameterType()
	 * @generated
	 */
	EReference getParameterType_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ParameterType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.ParameterType#getName()
	 * @see #getParameterType()
	 * @generated
	 */
	EAttribute getParameterType_Name();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ProcessType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.ProcessType
	 * @generated
	 */
	EClass getProcessType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.ProcessType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.ProcessType#getGroup()
	 * @see #getProcessType()
	 * @generated
	 */
	EAttribute getProcessType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ProcessType#getHeader <em>Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Header</em>'.
	 * @see org.drools.drools._5._0.process.ProcessType#getHeader()
	 * @see #getProcessType()
	 * @generated
	 */
	EReference getProcessType_Header();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ProcessType#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see org.drools.drools._5._0.process.ProcessType#getNodes()
	 * @see #getProcessType()
	 * @generated
	 */
	EReference getProcessType_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.ProcessType#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see org.drools.drools._5._0.process.ProcessType#getConnections()
	 * @see #getProcessType()
	 * @generated
	 */
	EReference getProcessType_Connections();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ProcessType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.ProcessType#getId()
	 * @see #getProcessType()
	 * @generated
	 */
	EAttribute getProcessType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ProcessType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.ProcessType#getName()
	 * @see #getProcessType()
	 * @generated
	 */
	EAttribute getProcessType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ProcessType#getPackageName <em>Package Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Name</em>'.
	 * @see org.drools.drools._5._0.process.ProcessType#getPackageName()
	 * @see #getProcessType()
	 * @generated
	 */
	EAttribute getProcessType_PackageName();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ProcessType#getRouterLayout <em>Router Layout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Router Layout</em>'.
	 * @see org.drools.drools._5._0.process.ProcessType#getRouterLayout()
	 * @see #getProcessType()
	 * @generated
	 */
	EAttribute getProcessType_RouterLayout();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ProcessType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.ProcessType#getType()
	 * @see #getProcessType()
	 * @generated
	 */
	EAttribute getProcessType_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ProcessType#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.drools.drools._5._0.process.ProcessType#getVersion()
	 * @see #getProcessType()
	 * @generated
	 */
	EAttribute getProcessType_Version();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.RuleSetType <em>Rule Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule Set Type</em>'.
	 * @see org.drools.drools._5._0.process.RuleSetType
	 * @generated
	 */
	EClass getRuleSetType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.RuleSetType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.RuleSetType#getGroup()
	 * @see #getRuleSetType()
	 * @generated
	 */
	EAttribute getRuleSetType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.RuleSetType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.RuleSetType#getMetaData()
	 * @see #getRuleSetType()
	 * @generated
	 */
	EReference getRuleSetType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.RuleSetType#getTimers <em>Timers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timers</em>'.
	 * @see org.drools.drools._5._0.process.RuleSetType#getTimers()
	 * @see #getRuleSetType()
	 * @generated
	 */
	EReference getRuleSetType_Timers();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.RuleSetType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.RuleSetType#getHeight()
	 * @see #getRuleSetType()
	 * @generated
	 */
	EAttribute getRuleSetType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.RuleSetType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.RuleSetType#getId()
	 * @see #getRuleSetType()
	 * @generated
	 */
	EAttribute getRuleSetType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.RuleSetType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.RuleSetType#getName()
	 * @see #getRuleSetType()
	 * @generated
	 */
	EAttribute getRuleSetType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.RuleSetType#getRuleFlowGroup <em>Rule Flow Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rule Flow Group</em>'.
	 * @see org.drools.drools._5._0.process.RuleSetType#getRuleFlowGroup()
	 * @see #getRuleSetType()
	 * @generated
	 */
	EAttribute getRuleSetType_RuleFlowGroup();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.RuleSetType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.RuleSetType#getWidth()
	 * @see #getRuleSetType()
	 * @generated
	 */
	EAttribute getRuleSetType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.RuleSetType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.RuleSetType#getX()
	 * @see #getRuleSetType()
	 * @generated
	 */
	EAttribute getRuleSetType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.RuleSetType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.RuleSetType#getY()
	 * @see #getRuleSetType()
	 * @generated
	 */
	EAttribute getRuleSetType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.SplitType <em>Split Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Split Type</em>'.
	 * @see org.drools.drools._5._0.process.SplitType
	 * @generated
	 */
	EClass getSplitType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.SplitType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.SplitType#getGroup()
	 * @see #getSplitType()
	 * @generated
	 */
	EAttribute getSplitType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.SplitType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.SplitType#getMetaData()
	 * @see #getSplitType()
	 * @generated
	 */
	EReference getSplitType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.SplitType#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraints</em>'.
	 * @see org.drools.drools._5._0.process.SplitType#getConstraints()
	 * @see #getSplitType()
	 * @generated
	 */
	EReference getSplitType_Constraints();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SplitType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.SplitType#getHeight()
	 * @see #getSplitType()
	 * @generated
	 */
	EAttribute getSplitType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SplitType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.SplitType#getId()
	 * @see #getSplitType()
	 * @generated
	 */
	EAttribute getSplitType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SplitType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.SplitType#getName()
	 * @see #getSplitType()
	 * @generated
	 */
	EAttribute getSplitType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SplitType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.SplitType#getType()
	 * @see #getSplitType()
	 * @generated
	 */
	EAttribute getSplitType_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SplitType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.SplitType#getWidth()
	 * @see #getSplitType()
	 * @generated
	 */
	EAttribute getSplitType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SplitType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.SplitType#getX()
	 * @see #getSplitType()
	 * @generated
	 */
	EAttribute getSplitType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SplitType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.SplitType#getY()
	 * @see #getSplitType()
	 * @generated
	 */
	EAttribute getSplitType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.StartType <em>Start Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start Type</em>'.
	 * @see org.drools.drools._5._0.process.StartType
	 * @generated
	 */
	EClass getStartType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.StartType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.StartType#getGroup()
	 * @see #getStartType()
	 * @generated
	 */
	EAttribute getStartType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.StartType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.StartType#getMetaData()
	 * @see #getStartType()
	 * @generated
	 */
	EReference getStartType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.StartType#getTriggers <em>Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Triggers</em>'.
	 * @see org.drools.drools._5._0.process.StartType#getTriggers()
	 * @see #getStartType()
	 * @generated
	 */
	EReference getStartType_Triggers();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.StartType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.StartType#getHeight()
	 * @see #getStartType()
	 * @generated
	 */
	EAttribute getStartType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.StartType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.StartType#getId()
	 * @see #getStartType()
	 * @generated
	 */
	EAttribute getStartType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.StartType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.StartType#getName()
	 * @see #getStartType()
	 * @generated
	 */
	EAttribute getStartType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.StartType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.StartType#getWidth()
	 * @see #getStartType()
	 * @generated
	 */
	EAttribute getStartType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.StartType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.StartType#getX()
	 * @see #getStartType()
	 * @generated
	 */
	EAttribute getStartType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.StartType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.StartType#getY()
	 * @see #getStartType()
	 * @generated
	 */
	EAttribute getStartType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.StateType <em>State Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Type</em>'.
	 * @see org.drools.drools._5._0.process.StateType
	 * @generated
	 */
	EClass getStateType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.StateType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.StateType#getGroup()
	 * @see #getStateType()
	 * @generated
	 */
	EAttribute getStateType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.StateType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.StateType#getMetaData()
	 * @see #getStateType()
	 * @generated
	 */
	EReference getStateType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.StateType#getTimers <em>Timers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timers</em>'.
	 * @see org.drools.drools._5._0.process.StateType#getTimers()
	 * @see #getStateType()
	 * @generated
	 */
	EReference getStateType_Timers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.StateType#getOnEntry <em>On Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Entry</em>'.
	 * @see org.drools.drools._5._0.process.StateType#getOnEntry()
	 * @see #getStateType()
	 * @generated
	 */
	EReference getStateType_OnEntry();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.StateType#getOnExit <em>On Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Exit</em>'.
	 * @see org.drools.drools._5._0.process.StateType#getOnExit()
	 * @see #getStateType()
	 * @generated
	 */
	EReference getStateType_OnExit();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.StateType#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraints</em>'.
	 * @see org.drools.drools._5._0.process.StateType#getConstraints()
	 * @see #getStateType()
	 * @generated
	 */
	EReference getStateType_Constraints();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.StateType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.StateType#getHeight()
	 * @see #getStateType()
	 * @generated
	 */
	EAttribute getStateType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.StateType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.StateType#getId()
	 * @see #getStateType()
	 * @generated
	 */
	EAttribute getStateType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.StateType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.StateType#getName()
	 * @see #getStateType()
	 * @generated
	 */
	EAttribute getStateType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.StateType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.StateType#getWidth()
	 * @see #getStateType()
	 * @generated
	 */
	EAttribute getStateType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.StateType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.StateType#getX()
	 * @see #getStateType()
	 * @generated
	 */
	EAttribute getStateType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.StateType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.StateType#getY()
	 * @see #getStateType()
	 * @generated
	 */
	EAttribute getStateType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.SubProcessType <em>Sub Process Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sub Process Type</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType
	 * @generated
	 */
	EClass getSubProcessType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.SubProcessType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getGroup()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.SubProcessType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getMetaData()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EReference getSubProcessType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.SubProcessType#getMapping <em>Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mapping</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getMapping()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EReference getSubProcessType_Mapping();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.SubProcessType#getTimers <em>Timers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timers</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getTimers()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EReference getSubProcessType_Timers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.SubProcessType#getOnEntry <em>On Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Entry</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getOnEntry()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EReference getSubProcessType_OnEntry();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.SubProcessType#getOnExit <em>On Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Exit</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getOnExit()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EReference getSubProcessType_OnExit();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SubProcessType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getHeight()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SubProcessType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getId()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SubProcessType#getIndependent <em>Independent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Independent</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getIndependent()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_Independent();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SubProcessType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getName()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SubProcessType#getProcessId <em>Process Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Process Id</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getProcessId()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_ProcessId();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SubProcessType#getWaitForCompletion <em>Wait For Completion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wait For Completion</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getWaitForCompletion()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_WaitForCompletion();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SubProcessType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getWidth()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SubProcessType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getX()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SubProcessType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.SubProcessType#getY()
	 * @see #getSubProcessType()
	 * @generated
	 */
	EAttribute getSubProcessType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.SwimlanesType <em>Swimlanes Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Swimlanes Type</em>'.
	 * @see org.drools.drools._5._0.process.SwimlanesType
	 * @generated
	 */
	EClass getSwimlanesType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.SwimlanesType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.SwimlanesType#getGroup()
	 * @see #getSwimlanesType()
	 * @generated
	 */
	EAttribute getSwimlanesType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.SwimlanesType#getSwimlane <em>Swimlane</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Swimlane</em>'.
	 * @see org.drools.drools._5._0.process.SwimlanesType#getSwimlane()
	 * @see #getSwimlanesType()
	 * @generated
	 */
	EReference getSwimlanesType_Swimlane();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.SwimlaneType <em>Swimlane Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Swimlane Type</em>'.
	 * @see org.drools.drools._5._0.process.SwimlaneType
	 * @generated
	 */
	EClass getSwimlaneType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.SwimlaneType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.SwimlaneType#getName()
	 * @see #getSwimlaneType()
	 * @generated
	 */
	EAttribute getSwimlaneType_Name();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.TimerNodeType <em>Timer Node Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timer Node Type</em>'.
	 * @see org.drools.drools._5._0.process.TimerNodeType
	 * @generated
	 */
	EClass getTimerNodeType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.TimerNodeType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.TimerNodeType#getGroup()
	 * @see #getTimerNodeType()
	 * @generated
	 */
	EAttribute getTimerNodeType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.TimerNodeType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.TimerNodeType#getMetaData()
	 * @see #getTimerNodeType()
	 * @generated
	 */
	EReference getTimerNodeType_MetaData();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TimerNodeType#getDelay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delay</em>'.
	 * @see org.drools.drools._5._0.process.TimerNodeType#getDelay()
	 * @see #getTimerNodeType()
	 * @generated
	 */
	EAttribute getTimerNodeType_Delay();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TimerNodeType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.TimerNodeType#getHeight()
	 * @see #getTimerNodeType()
	 * @generated
	 */
	EAttribute getTimerNodeType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TimerNodeType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.TimerNodeType#getId()
	 * @see #getTimerNodeType()
	 * @generated
	 */
	EAttribute getTimerNodeType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TimerNodeType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.TimerNodeType#getName()
	 * @see #getTimerNodeType()
	 * @generated
	 */
	EAttribute getTimerNodeType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TimerNodeType#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see org.drools.drools._5._0.process.TimerNodeType#getPeriod()
	 * @see #getTimerNodeType()
	 * @generated
	 */
	EAttribute getTimerNodeType_Period();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TimerNodeType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.TimerNodeType#getWidth()
	 * @see #getTimerNodeType()
	 * @generated
	 */
	EAttribute getTimerNodeType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TimerNodeType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.TimerNodeType#getX()
	 * @see #getTimerNodeType()
	 * @generated
	 */
	EAttribute getTimerNodeType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TimerNodeType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.TimerNodeType#getY()
	 * @see #getTimerNodeType()
	 * @generated
	 */
	EAttribute getTimerNodeType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.TimersType <em>Timers Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timers Type</em>'.
	 * @see org.drools.drools._5._0.process.TimersType
	 * @generated
	 */
	EClass getTimersType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.TimersType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.TimersType#getGroup()
	 * @see #getTimersType()
	 * @generated
	 */
	EAttribute getTimersType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.TimersType#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see org.drools.drools._5._0.process.TimersType#getTimer()
	 * @see #getTimersType()
	 * @generated
	 */
	EReference getTimersType_Timer();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.TimerType <em>Timer Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timer Type</em>'.
	 * @see org.drools.drools._5._0.process.TimerType
	 * @generated
	 */
	EClass getTimerType();

	/**
	 * Returns the meta object for the containment reference '{@link org.drools.drools._5._0.process.TimerType#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Action</em>'.
	 * @see org.drools.drools._5._0.process.TimerType#getAction()
	 * @see #getTimerType()
	 * @generated
	 */
	EReference getTimerType_Action();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TimerType#getDelay <em>Delay</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delay</em>'.
	 * @see org.drools.drools._5._0.process.TimerType#getDelay()
	 * @see #getTimerType()
	 * @generated
	 */
	EAttribute getTimerType_Delay();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TimerType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.TimerType#getId()
	 * @see #getTimerType()
	 * @generated
	 */
	EAttribute getTimerType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TimerType#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see org.drools.drools._5._0.process.TimerType#getPeriod()
	 * @see #getTimerType()
	 * @generated
	 */
	EAttribute getTimerType_Period();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.TriggersType <em>Triggers Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Triggers Type</em>'.
	 * @see org.drools.drools._5._0.process.TriggersType
	 * @generated
	 */
	EClass getTriggersType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.TriggersType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.TriggersType#getGroup()
	 * @see #getTriggersType()
	 * @generated
	 */
	EAttribute getTriggersType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.TriggersType#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Trigger</em>'.
	 * @see org.drools.drools._5._0.process.TriggersType#getTrigger()
	 * @see #getTriggersType()
	 * @generated
	 */
	EReference getTriggersType_Trigger();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.TriggerType <em>Trigger Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger Type</em>'.
	 * @see org.drools.drools._5._0.process.TriggerType
	 * @generated
	 */
	EClass getTriggerType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.TriggerType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.TriggerType#getGroup()
	 * @see #getTriggerType()
	 * @generated
	 */
	EAttribute getTriggerType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.TriggerType#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Constraint</em>'.
	 * @see org.drools.drools._5._0.process.TriggerType#getConstraint()
	 * @see #getTriggerType()
	 * @generated
	 */
	EReference getTriggerType_Constraint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.TriggerType#getEventFilters <em>Event Filters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Event Filters</em>'.
	 * @see org.drools.drools._5._0.process.TriggerType#getEventFilters()
	 * @see #getTriggerType()
	 * @generated
	 */
	EReference getTriggerType_EventFilters();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.TriggerType#getMapping <em>Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mapping</em>'.
	 * @see org.drools.drools._5._0.process.TriggerType#getMapping()
	 * @see #getTriggerType()
	 * @generated
	 */
	EReference getTriggerType_Mapping();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TriggerType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.TriggerType#getType()
	 * @see #getTriggerType()
	 * @generated
	 */
	EAttribute getTriggerType_Type();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.TypeType <em>Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Type</em>'.
	 * @see org.drools.drools._5._0.process.TypeType
	 * @generated
	 */
	EClass getTypeType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TypeType#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see org.drools.drools._5._0.process.TypeType#getClassName()
	 * @see #getTypeType()
	 * @generated
	 */
	EAttribute getTypeType_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.TypeType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.TypeType#getName()
	 * @see #getTypeType()
	 * @generated
	 */
	EAttribute getTypeType_Name();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.ValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Type</em>'.
	 * @see org.drools.drools._5._0.process.ValueType
	 * @generated
	 */
	EClass getValueType();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.ValueType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.drools.drools._5._0.process.ValueType#getValue()
	 * @see #getValueType()
	 * @generated
	 */
	EAttribute getValueType_Value();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.VariablesType <em>Variables Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variables Type</em>'.
	 * @see org.drools.drools._5._0.process.VariablesType
	 * @generated
	 */
	EClass getVariablesType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.VariablesType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.VariablesType#getGroup()
	 * @see #getVariablesType()
	 * @generated
	 */
	EAttribute getVariablesType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.VariablesType#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variable</em>'.
	 * @see org.drools.drools._5._0.process.VariablesType#getVariable()
	 * @see #getVariablesType()
	 * @generated
	 */
	EReference getVariablesType_Variable();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.VariableType <em>Variable Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Type</em>'.
	 * @see org.drools.drools._5._0.process.VariableType
	 * @generated
	 */
	EClass getVariableType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.VariableType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.VariableType#getGroup()
	 * @see #getVariableType()
	 * @generated
	 */
	EAttribute getVariableType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.VariableType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Type</em>'.
	 * @see org.drools.drools._5._0.process.VariableType#getType()
	 * @see #getVariableType()
	 * @generated
	 */
	EReference getVariableType_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.VariableType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Value</em>'.
	 * @see org.drools.drools._5._0.process.VariableType#getValue()
	 * @see #getVariableType()
	 * @generated
	 */
	EReference getVariableType_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.VariableType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.VariableType#getName()
	 * @see #getVariableType()
	 * @generated
	 */
	EAttribute getVariableType_Name();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.WorkItemType <em>Work Item Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Item Type</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType
	 * @generated
	 */
	EClass getWorkItemType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.WorkItemType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getGroup()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EAttribute getWorkItemType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.WorkItemType#getMetaData <em>Meta Data</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Meta Data</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getMetaData()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EReference getWorkItemType_MetaData();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.WorkItemType#getTimers <em>Timers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timers</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getTimers()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EReference getWorkItemType_Timers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.WorkItemType#getWork <em>Work</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Work</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getWork()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EReference getWorkItemType_Work();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.WorkItemType#getMapping <em>Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mapping</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getMapping()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EReference getWorkItemType_Mapping();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.WorkItemType#getOnEntry <em>On Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Entry</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getOnEntry()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EReference getWorkItemType_OnEntry();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.WorkItemType#getOnExit <em>On Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>On Exit</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getOnExit()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EReference getWorkItemType_OnExit();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.WorkItemType#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getHeight()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EAttribute getWorkItemType_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.WorkItemType#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getId()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EAttribute getWorkItemType_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.WorkItemType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getName()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EAttribute getWorkItemType_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.WorkItemType#getWaitForCompletion <em>Wait For Completion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wait For Completion</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getWaitForCompletion()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EAttribute getWorkItemType_WaitForCompletion();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.WorkItemType#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getWidth()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EAttribute getWorkItemType_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.WorkItemType#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getX()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EAttribute getWorkItemType_X();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.WorkItemType#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.drools.drools._5._0.process.WorkItemType#getY()
	 * @see #getWorkItemType()
	 * @generated
	 */
	EAttribute getWorkItemType_Y();

	/**
	 * Returns the meta object for class '{@link org.drools.drools._5._0.process.WorkType <em>Work Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Work Type</em>'.
	 * @see org.drools.drools._5._0.process.WorkType
	 * @generated
	 */
	EClass getWorkType();

	/**
	 * Returns the meta object for the attribute list '{@link org.drools.drools._5._0.process.WorkType#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Group</em>'.
	 * @see org.drools.drools._5._0.process.WorkType#getGroup()
	 * @see #getWorkType()
	 * @generated
	 */
	EAttribute getWorkType_Group();

	/**
	 * Returns the meta object for the containment reference list '{@link org.drools.drools._5._0.process.WorkType#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter</em>'.
	 * @see org.drools.drools._5._0.process.WorkType#getParameter()
	 * @see #getWorkType()
	 * @generated
	 */
	EReference getWorkType_Parameter();

	/**
	 * Returns the meta object for the attribute '{@link org.drools.drools._5._0.process.WorkType#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.drools.drools._5._0.process.WorkType#getName()
	 * @see #getWorkType()
	 * @generated
	 */
	EAttribute getWorkType_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ProcessFactory getProcessFactory();

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
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ActionNodeTypeImpl <em>Action Node Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ActionNodeTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getActionNodeType()
		 * @generated
		 */
		EClass ACTION_NODE_TYPE = eINSTANCE.getActionNodeType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_NODE_TYPE__GROUP = eINSTANCE.getActionNodeType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_NODE_TYPE__META_DATA = eINSTANCE.getActionNodeType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_NODE_TYPE__ACTION = eINSTANCE.getActionNodeType_Action();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_NODE_TYPE__HEIGHT = eINSTANCE.getActionNodeType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_NODE_TYPE__ID = eINSTANCE.getActionNodeType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_NODE_TYPE__NAME = eINSTANCE.getActionNodeType_Name();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_NODE_TYPE__WIDTH = eINSTANCE.getActionNodeType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_NODE_TYPE__X = eINSTANCE.getActionNodeType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_NODE_TYPE__Y = eINSTANCE.getActionNodeType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ActionTypeImpl <em>Action Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ActionTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getActionType()
		 * @generated
		 */
		EClass ACTION_TYPE = eINSTANCE.getActionType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__VALUE = eINSTANCE.getActionType_Value();

		/**
		 * The meta object literal for the '<em><b>Dialect</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__DIALECT = eINSTANCE.getActionType_Dialect();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__NAME = eINSTANCE.getActionType_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION_TYPE__TYPE = eINSTANCE.getActionType_Type();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.CompositeTypeImpl <em>Composite Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.CompositeTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getCompositeType()
		 * @generated
		 */
		EClass COMPOSITE_TYPE = eINSTANCE.getCompositeType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPOSITE_TYPE__GROUP = eINSTANCE.getCompositeType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_TYPE__META_DATA = eINSTANCE.getCompositeType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Timers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_TYPE__TIMERS = eINSTANCE.getCompositeType_Timers();

		/**
		 * The meta object literal for the '<em><b>On Entry</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_TYPE__ON_ENTRY = eINSTANCE.getCompositeType_OnEntry();

		/**
		 * The meta object literal for the '<em><b>On Exit</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_TYPE__ON_EXIT = eINSTANCE.getCompositeType_OnExit();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_TYPE__VARIABLES = eINSTANCE.getCompositeType_Variables();

		/**
		 * The meta object literal for the '<em><b>Exception Handlers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_TYPE__EXCEPTION_HANDLERS = eINSTANCE.getCompositeType_ExceptionHandlers();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_TYPE__NODES = eINSTANCE.getCompositeType_Nodes();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_TYPE__CONNECTIONS = eINSTANCE.getCompositeType_Connections();

		/**
		 * The meta object literal for the '<em><b>In Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_TYPE__IN_PORTS = eINSTANCE.getCompositeType_InPorts();

		/**
		 * The meta object literal for the '<em><b>Out Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE_TYPE__OUT_PORTS = eINSTANCE.getCompositeType_OutPorts();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPOSITE_TYPE__HEIGHT = eINSTANCE.getCompositeType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPOSITE_TYPE__ID = eINSTANCE.getCompositeType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPOSITE_TYPE__NAME = eINSTANCE.getCompositeType_Name();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPOSITE_TYPE__WIDTH = eINSTANCE.getCompositeType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPOSITE_TYPE__X = eINSTANCE.getCompositeType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPOSITE_TYPE__Y = eINSTANCE.getCompositeType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ConnectionsTypeImpl <em>Connections Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ConnectionsTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getConnectionsType()
		 * @generated
		 */
		EClass CONNECTIONS_TYPE = eINSTANCE.getConnectionsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTIONS_TYPE__GROUP = eINSTANCE.getConnectionsType_Group();

		/**
		 * The meta object literal for the '<em><b>Connection</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTIONS_TYPE__CONNECTION = eINSTANCE.getConnectionsType_Connection();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ConnectionTypeImpl <em>Connection Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ConnectionTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getConnectionType()
		 * @generated
		 */
		EClass CONNECTION_TYPE = eINSTANCE.getConnectionType();

		/**
		 * The meta object literal for the '<em><b>Bendpoints</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_TYPE__BENDPOINTS = eINSTANCE.getConnectionType_Bendpoints();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_TYPE__FROM = eINSTANCE.getConnectionType_From();

		/**
		 * The meta object literal for the '<em><b>From Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_TYPE__FROM_TYPE = eINSTANCE.getConnectionType_FromType();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_TYPE__TO = eINSTANCE.getConnectionType_To();

		/**
		 * The meta object literal for the '<em><b>To Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION_TYPE__TO_TYPE = eINSTANCE.getConnectionType_ToType();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ConstraintsTypeImpl <em>Constraints Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ConstraintsTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getConstraintsType()
		 * @generated
		 */
		EClass CONSTRAINTS_TYPE = eINSTANCE.getConstraintsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINTS_TYPE__GROUP = eINSTANCE.getConstraintsType_Group();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINTS_TYPE__CONSTRAINT = eINSTANCE.getConstraintsType_Constraint();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ConstraintTypeImpl <em>Constraint Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ConstraintTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getConstraintType()
		 * @generated
		 */
		EClass CONSTRAINT_TYPE = eINSTANCE.getConstraintType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_TYPE__VALUE = eINSTANCE.getConstraintType_Value();

		/**
		 * The meta object literal for the '<em><b>Dialect</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_TYPE__DIALECT = eINSTANCE.getConstraintType_Dialect();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_TYPE__NAME = eINSTANCE.getConstraintType_Name();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_TYPE__PRIORITY = eINSTANCE.getConstraintType_Priority();

		/**
		 * The meta object literal for the '<em><b>To Node Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_TYPE__TO_NODE_ID = eINSTANCE.getConstraintType_ToNodeId();

		/**
		 * The meta object literal for the '<em><b>To Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_TYPE__TO_TYPE = eINSTANCE.getConstraintType_ToType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT_TYPE__TYPE = eINSTANCE.getConstraintType_Type();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.DocumentRootImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ACTION = eINSTANCE.getDocumentRoot_Action();

		/**
		 * The meta object literal for the '<em><b>Action Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ACTION_NODE = eINSTANCE.getDocumentRoot_ActionNode();

		/**
		 * The meta object literal for the '<em><b>Composite</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__COMPOSITE = eINSTANCE.getDocumentRoot_Composite();

		/**
		 * The meta object literal for the '<em><b>Connection</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONNECTION = eINSTANCE.getDocumentRoot_Connection();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONNECTIONS = eINSTANCE.getDocumentRoot_Connections();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONSTRAINT = eINSTANCE.getDocumentRoot_Constraint();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__CONSTRAINTS = eINSTANCE.getDocumentRoot_Constraints();

		/**
		 * The meta object literal for the '<em><b>Dynamic</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__DYNAMIC = eINSTANCE.getDocumentRoot_Dynamic();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__END = eINSTANCE.getDocumentRoot_End();

		/**
		 * The meta object literal for the '<em><b>Event Filter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EVENT_FILTER = eINSTANCE.getDocumentRoot_EventFilter();

		/**
		 * The meta object literal for the '<em><b>Event Filters</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EVENT_FILTERS = eINSTANCE.getDocumentRoot_EventFilters();

		/**
		 * The meta object literal for the '<em><b>Event Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EVENT_NODE = eINSTANCE.getDocumentRoot_EventNode();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXCEPTION_HANDLER = eINSTANCE.getDocumentRoot_ExceptionHandler();

		/**
		 * The meta object literal for the '<em><b>Exception Handlers</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__EXCEPTION_HANDLERS = eINSTANCE.getDocumentRoot_ExceptionHandlers();

		/**
		 * The meta object literal for the '<em><b>Fault</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FAULT = eINSTANCE.getDocumentRoot_Fault();

		/**
		 * The meta object literal for the '<em><b>For Each</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FOR_EACH = eINSTANCE.getDocumentRoot_ForEach();

		/**
		 * The meta object literal for the '<em><b>Function Import</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FUNCTION_IMPORT = eINSTANCE.getDocumentRoot_FunctionImport();

		/**
		 * The meta object literal for the '<em><b>Function Imports</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__FUNCTION_IMPORTS = eINSTANCE.getDocumentRoot_FunctionImports();

		/**
		 * The meta object literal for the '<em><b>Global</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GLOBAL = eINSTANCE.getDocumentRoot_Global();

		/**
		 * The meta object literal for the '<em><b>Globals</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GLOBALS = eINSTANCE.getDocumentRoot_Globals();

		/**
		 * The meta object literal for the '<em><b>Header</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__HEADER = eINSTANCE.getDocumentRoot_Header();

		/**
		 * The meta object literal for the '<em><b>Human Task</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__HUMAN_TASK = eINSTANCE.getDocumentRoot_HumanTask();

		/**
		 * The meta object literal for the '<em><b>Import</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__IMPORT = eINSTANCE.getDocumentRoot_Import();

		/**
		 * The meta object literal for the '<em><b>Imports</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__IMPORTS = eINSTANCE.getDocumentRoot_Imports();

		/**
		 * The meta object literal for the '<em><b>In Port</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__IN_PORT = eINSTANCE.getDocumentRoot_InPort();

		/**
		 * The meta object literal for the '<em><b>In Ports</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__IN_PORTS = eINSTANCE.getDocumentRoot_InPorts();

		/**
		 * The meta object literal for the '<em><b>Join</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__JOIN = eINSTANCE.getDocumentRoot_Join();

		/**
		 * The meta object literal for the '<em><b>Mapping</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MAPPING = eINSTANCE.getDocumentRoot_Mapping();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__META_DATA = eINSTANCE.getDocumentRoot_MetaData();

		/**
		 * The meta object literal for the '<em><b>Milestone</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MILESTONE = eINSTANCE.getDocumentRoot_Milestone();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__NODES = eINSTANCE.getDocumentRoot_Nodes();

		/**
		 * The meta object literal for the '<em><b>On Entry</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ON_ENTRY = eINSTANCE.getDocumentRoot_OnEntry();

		/**
		 * The meta object literal for the '<em><b>On Exit</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ON_EXIT = eINSTANCE.getDocumentRoot_OnExit();

		/**
		 * The meta object literal for the '<em><b>Out Port</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__OUT_PORT = eINSTANCE.getDocumentRoot_OutPort();

		/**
		 * The meta object literal for the '<em><b>Out Ports</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__OUT_PORTS = eINSTANCE.getDocumentRoot_OutPorts();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PARAMETER = eINSTANCE.getDocumentRoot_Parameter();

		/**
		 * The meta object literal for the '<em><b>Process</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PROCESS = eINSTANCE.getDocumentRoot_Process();

		/**
		 * The meta object literal for the '<em><b>Rule Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RULE_SET = eINSTANCE.getDocumentRoot_RuleSet();

		/**
		 * The meta object literal for the '<em><b>Split</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SPLIT = eINSTANCE.getDocumentRoot_Split();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__START = eINSTANCE.getDocumentRoot_Start();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__STATE = eINSTANCE.getDocumentRoot_State();

		/**
		 * The meta object literal for the '<em><b>Sub Process</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SUB_PROCESS = eINSTANCE.getDocumentRoot_SubProcess();

		/**
		 * The meta object literal for the '<em><b>Swimlane</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SWIMLANE = eINSTANCE.getDocumentRoot_Swimlane();

		/**
		 * The meta object literal for the '<em><b>Swimlanes</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__SWIMLANES = eINSTANCE.getDocumentRoot_Swimlanes();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TIMER = eINSTANCE.getDocumentRoot_Timer();

		/**
		 * The meta object literal for the '<em><b>Timer Node</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TIMER_NODE = eINSTANCE.getDocumentRoot_TimerNode();

		/**
		 * The meta object literal for the '<em><b>Timers</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TIMERS = eINSTANCE.getDocumentRoot_Timers();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TRIGGER = eINSTANCE.getDocumentRoot_Trigger();

		/**
		 * The meta object literal for the '<em><b>Triggers</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TRIGGERS = eINSTANCE.getDocumentRoot_Triggers();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TYPE = eINSTANCE.getDocumentRoot_Type();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__VALUE = eINSTANCE.getDocumentRoot_Value();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__VARIABLE = eINSTANCE.getDocumentRoot_Variable();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__VARIABLES = eINSTANCE.getDocumentRoot_Variables();

		/**
		 * The meta object literal for the '<em><b>Work</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WORK = eINSTANCE.getDocumentRoot_Work();

		/**
		 * The meta object literal for the '<em><b>Work Item</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WORK_ITEM = eINSTANCE.getDocumentRoot_WorkItem();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.DynamicTypeImpl <em>Dynamic Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.DynamicTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getDynamicType()
		 * @generated
		 */
		EClass DYNAMIC_TYPE = eINSTANCE.getDynamicType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_TYPE__GROUP = eINSTANCE.getDynamicType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_TYPE__META_DATA = eINSTANCE.getDynamicType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_TYPE__VARIABLES = eINSTANCE.getDynamicType_Variables();

		/**
		 * The meta object literal for the '<em><b>Exception Handlers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_TYPE__EXCEPTION_HANDLERS = eINSTANCE.getDynamicType_ExceptionHandlers();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_TYPE__NODES = eINSTANCE.getDynamicType_Nodes();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_TYPE__CONNECTIONS = eINSTANCE.getDynamicType_Connections();

		/**
		 * The meta object literal for the '<em><b>In Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_TYPE__IN_PORTS = eINSTANCE.getDynamicType_InPorts();

		/**
		 * The meta object literal for the '<em><b>Out Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_TYPE__OUT_PORTS = eINSTANCE.getDynamicType_OutPorts();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_TYPE__HEIGHT = eINSTANCE.getDynamicType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_TYPE__ID = eINSTANCE.getDynamicType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_TYPE__NAME = eINSTANCE.getDynamicType_Name();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_TYPE__WIDTH = eINSTANCE.getDynamicType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_TYPE__X = eINSTANCE.getDynamicType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_TYPE__Y = eINSTANCE.getDynamicType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.EndTypeImpl <em>End Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.EndTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getEndType()
		 * @generated
		 */
		EClass END_TYPE = eINSTANCE.getEndType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_TYPE__GROUP = eINSTANCE.getEndType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference END_TYPE__META_DATA = eINSTANCE.getEndType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_TYPE__HEIGHT = eINSTANCE.getEndType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_TYPE__ID = eINSTANCE.getEndType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_TYPE__NAME = eINSTANCE.getEndType_Name();

		/**
		 * The meta object literal for the '<em><b>Terminate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_TYPE__TERMINATE = eINSTANCE.getEndType_Terminate();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_TYPE__WIDTH = eINSTANCE.getEndType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_TYPE__X = eINSTANCE.getEndType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute END_TYPE__Y = eINSTANCE.getEndType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.EventFiltersTypeImpl <em>Event Filters Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.EventFiltersTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getEventFiltersType()
		 * @generated
		 */
		EClass EVENT_FILTERS_TYPE = eINSTANCE.getEventFiltersType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_FILTERS_TYPE__GROUP = eINSTANCE.getEventFiltersType_Group();

		/**
		 * The meta object literal for the '<em><b>Event Filter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_FILTERS_TYPE__EVENT_FILTER = eINSTANCE.getEventFiltersType_EventFilter();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.EventFilterTypeImpl <em>Event Filter Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.EventFilterTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getEventFilterType()
		 * @generated
		 */
		EClass EVENT_FILTER_TYPE = eINSTANCE.getEventFilterType();

		/**
		 * The meta object literal for the '<em><b>Event Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_FILTER_TYPE__EVENT_TYPE = eINSTANCE.getEventFilterType_EventType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_FILTER_TYPE__TYPE = eINSTANCE.getEventFilterType_Type();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.EventNodeTypeImpl <em>Event Node Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.EventNodeTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getEventNodeType()
		 * @generated
		 */
		EClass EVENT_NODE_TYPE = eINSTANCE.getEventNodeType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_NODE_TYPE__GROUP = eINSTANCE.getEventNodeType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_NODE_TYPE__META_DATA = eINSTANCE.getEventNodeType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Event Filters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_NODE_TYPE__EVENT_FILTERS = eINSTANCE.getEventNodeType_EventFilters();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_NODE_TYPE__HEIGHT = eINSTANCE.getEventNodeType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_NODE_TYPE__ID = eINSTANCE.getEventNodeType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_NODE_TYPE__NAME = eINSTANCE.getEventNodeType_Name();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_NODE_TYPE__SCOPE = eINSTANCE.getEventNodeType_Scope();

		/**
		 * The meta object literal for the '<em><b>Variable Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_NODE_TYPE__VARIABLE_NAME = eINSTANCE.getEventNodeType_VariableName();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_NODE_TYPE__WIDTH = eINSTANCE.getEventNodeType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_NODE_TYPE__X = eINSTANCE.getEventNodeType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_NODE_TYPE__Y = eINSTANCE.getEventNodeType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ExceptionHandlersTypeImpl <em>Exception Handlers Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ExceptionHandlersTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getExceptionHandlersType()
		 * @generated
		 */
		EClass EXCEPTION_HANDLERS_TYPE = eINSTANCE.getExceptionHandlersType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCEPTION_HANDLERS_TYPE__GROUP = eINSTANCE.getExceptionHandlersType_Group();

		/**
		 * The meta object literal for the '<em><b>Exception Handler</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXCEPTION_HANDLERS_TYPE__EXCEPTION_HANDLER = eINSTANCE.getExceptionHandlersType_ExceptionHandler();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ExceptionHandlerTypeImpl <em>Exception Handler Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ExceptionHandlerTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getExceptionHandlerType()
		 * @generated
		 */
		EClass EXCEPTION_HANDLER_TYPE = eINSTANCE.getExceptionHandlerType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCEPTION_HANDLER_TYPE__GROUP = eINSTANCE.getExceptionHandlerType_Group();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXCEPTION_HANDLER_TYPE__ACTION = eINSTANCE.getExceptionHandlerType_Action();

		/**
		 * The meta object literal for the '<em><b>Fault Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCEPTION_HANDLER_TYPE__FAULT_NAME = eINSTANCE.getExceptionHandlerType_FaultName();

		/**
		 * The meta object literal for the '<em><b>Fault Variable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCEPTION_HANDLER_TYPE__FAULT_VARIABLE = eINSTANCE.getExceptionHandlerType_FaultVariable();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXCEPTION_HANDLER_TYPE__TYPE = eINSTANCE.getExceptionHandlerType_Type();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.FaultTypeImpl <em>Fault Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.FaultTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getFaultType()
		 * @generated
		 */
		EClass FAULT_TYPE = eINSTANCE.getFaultType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT_TYPE__GROUP = eINSTANCE.getFaultType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FAULT_TYPE__META_DATA = eINSTANCE.getFaultType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Fault Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT_TYPE__FAULT_NAME = eINSTANCE.getFaultType_FaultName();

		/**
		 * The meta object literal for the '<em><b>Fault Variable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT_TYPE__FAULT_VARIABLE = eINSTANCE.getFaultType_FaultVariable();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT_TYPE__HEIGHT = eINSTANCE.getFaultType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT_TYPE__ID = eINSTANCE.getFaultType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT_TYPE__NAME = eINSTANCE.getFaultType_Name();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT_TYPE__WIDTH = eINSTANCE.getFaultType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT_TYPE__X = eINSTANCE.getFaultType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FAULT_TYPE__Y = eINSTANCE.getFaultType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ForEachTypeImpl <em>For Each Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ForEachTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getForEachType()
		 * @generated
		 */
		EClass FOR_EACH_TYPE = eINSTANCE.getForEachType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOR_EACH_TYPE__GROUP = eINSTANCE.getForEachType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_EACH_TYPE__META_DATA = eINSTANCE.getForEachType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_EACH_TYPE__NODES = eINSTANCE.getForEachType_Nodes();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_EACH_TYPE__CONNECTIONS = eINSTANCE.getForEachType_Connections();

		/**
		 * The meta object literal for the '<em><b>In Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_EACH_TYPE__IN_PORTS = eINSTANCE.getForEachType_InPorts();

		/**
		 * The meta object literal for the '<em><b>Out Ports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FOR_EACH_TYPE__OUT_PORTS = eINSTANCE.getForEachType_OutPorts();

		/**
		 * The meta object literal for the '<em><b>Collection Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOR_EACH_TYPE__COLLECTION_EXPRESSION = eINSTANCE.getForEachType_CollectionExpression();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOR_EACH_TYPE__HEIGHT = eINSTANCE.getForEachType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOR_EACH_TYPE__ID = eINSTANCE.getForEachType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOR_EACH_TYPE__NAME = eINSTANCE.getForEachType_Name();

		/**
		 * The meta object literal for the '<em><b>Variable Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOR_EACH_TYPE__VARIABLE_NAME = eINSTANCE.getForEachType_VariableName();

		/**
		 * The meta object literal for the '<em><b>Wait For Completion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOR_EACH_TYPE__WAIT_FOR_COMPLETION = eINSTANCE.getForEachType_WaitForCompletion();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOR_EACH_TYPE__WIDTH = eINSTANCE.getForEachType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOR_EACH_TYPE__X = eINSTANCE.getForEachType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOR_EACH_TYPE__Y = eINSTANCE.getForEachType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.FunctionImportsTypeImpl <em>Function Imports Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.FunctionImportsTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getFunctionImportsType()
		 * @generated
		 */
		EClass FUNCTION_IMPORTS_TYPE = eINSTANCE.getFunctionImportsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION_IMPORTS_TYPE__GROUP = eINSTANCE.getFunctionImportsType_Group();

		/**
		 * The meta object literal for the '<em><b>Function Import</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION_IMPORTS_TYPE__FUNCTION_IMPORT = eINSTANCE.getFunctionImportsType_FunctionImport();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.FunctionImportTypeImpl <em>Function Import Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.FunctionImportTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getFunctionImportType()
		 * @generated
		 */
		EClass FUNCTION_IMPORT_TYPE = eINSTANCE.getFunctionImportType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION_IMPORT_TYPE__NAME = eINSTANCE.getFunctionImportType_Name();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.GlobalsTypeImpl <em>Globals Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.GlobalsTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getGlobalsType()
		 * @generated
		 */
		EClass GLOBALS_TYPE = eINSTANCE.getGlobalsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLOBALS_TYPE__GROUP = eINSTANCE.getGlobalsType_Group();

		/**
		 * The meta object literal for the '<em><b>Global</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GLOBALS_TYPE__GLOBAL = eINSTANCE.getGlobalsType_Global();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.GlobalTypeImpl <em>Global Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.GlobalTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getGlobalType()
		 * @generated
		 */
		EClass GLOBAL_TYPE = eINSTANCE.getGlobalType();

		/**
		 * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLOBAL_TYPE__IDENTIFIER = eINSTANCE.getGlobalType_Identifier();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GLOBAL_TYPE__TYPE = eINSTANCE.getGlobalType_Type();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.HeaderTypeImpl <em>Header Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.HeaderTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getHeaderType()
		 * @generated
		 */
		EClass HEADER_TYPE = eINSTANCE.getHeaderType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HEADER_TYPE__GROUP = eINSTANCE.getHeaderType_Group();

		/**
		 * The meta object literal for the '<em><b>Imports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HEADER_TYPE__IMPORTS = eINSTANCE.getHeaderType_Imports();

		/**
		 * The meta object literal for the '<em><b>Function Imports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HEADER_TYPE__FUNCTION_IMPORTS = eINSTANCE.getHeaderType_FunctionImports();

		/**
		 * The meta object literal for the '<em><b>Globals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HEADER_TYPE__GLOBALS = eINSTANCE.getHeaderType_Globals();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HEADER_TYPE__VARIABLES = eINSTANCE.getHeaderType_Variables();

		/**
		 * The meta object literal for the '<em><b>Swimlanes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HEADER_TYPE__SWIMLANES = eINSTANCE.getHeaderType_Swimlanes();

		/**
		 * The meta object literal for the '<em><b>Exception Handlers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HEADER_TYPE__EXCEPTION_HANDLERS = eINSTANCE.getHeaderType_ExceptionHandlers();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.HumanTaskTypeImpl <em>Human Task Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.HumanTaskTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getHumanTaskType()
		 * @generated
		 */
		EClass HUMAN_TASK_TYPE = eINSTANCE.getHumanTaskType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HUMAN_TASK_TYPE__GROUP = eINSTANCE.getHumanTaskType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HUMAN_TASK_TYPE__META_DATA = eINSTANCE.getHumanTaskType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Work</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HUMAN_TASK_TYPE__WORK = eINSTANCE.getHumanTaskType_Work();

		/**
		 * The meta object literal for the '<em><b>Mapping</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HUMAN_TASK_TYPE__MAPPING = eINSTANCE.getHumanTaskType_Mapping();

		/**
		 * The meta object literal for the '<em><b>Timers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HUMAN_TASK_TYPE__TIMERS = eINSTANCE.getHumanTaskType_Timers();

		/**
		 * The meta object literal for the '<em><b>On Entry</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HUMAN_TASK_TYPE__ON_ENTRY = eINSTANCE.getHumanTaskType_OnEntry();

		/**
		 * The meta object literal for the '<em><b>On Exit</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HUMAN_TASK_TYPE__ON_EXIT = eINSTANCE.getHumanTaskType_OnExit();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HUMAN_TASK_TYPE__HEIGHT = eINSTANCE.getHumanTaskType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HUMAN_TASK_TYPE__ID = eINSTANCE.getHumanTaskType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HUMAN_TASK_TYPE__NAME = eINSTANCE.getHumanTaskType_Name();

		/**
		 * The meta object literal for the '<em><b>Swimlane</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HUMAN_TASK_TYPE__SWIMLANE = eINSTANCE.getHumanTaskType_Swimlane();

		/**
		 * The meta object literal for the '<em><b>Wait For Completion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HUMAN_TASK_TYPE__WAIT_FOR_COMPLETION = eINSTANCE.getHumanTaskType_WaitForCompletion();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HUMAN_TASK_TYPE__WIDTH = eINSTANCE.getHumanTaskType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HUMAN_TASK_TYPE__X = eINSTANCE.getHumanTaskType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HUMAN_TASK_TYPE__Y = eINSTANCE.getHumanTaskType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ImportsTypeImpl <em>Imports Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ImportsTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getImportsType()
		 * @generated
		 */
		EClass IMPORTS_TYPE = eINSTANCE.getImportsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPORTS_TYPE__GROUP = eINSTANCE.getImportsType_Group();

		/**
		 * The meta object literal for the '<em><b>Import</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORTS_TYPE__IMPORT = eINSTANCE.getImportsType_Import();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ImportTypeImpl <em>Import Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ImportTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getImportType()
		 * @generated
		 */
		EClass IMPORT_TYPE = eINSTANCE.getImportType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPORT_TYPE__NAME = eINSTANCE.getImportType_Name();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.InPortsTypeImpl <em>In Ports Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.InPortsTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getInPortsType()
		 * @generated
		 */
		EClass IN_PORTS_TYPE = eINSTANCE.getInPortsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IN_PORTS_TYPE__GROUP = eINSTANCE.getInPortsType_Group();

		/**
		 * The meta object literal for the '<em><b>In Port</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IN_PORTS_TYPE__IN_PORT = eINSTANCE.getInPortsType_InPort();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.InPortTypeImpl <em>In Port Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.InPortTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getInPortType()
		 * @generated
		 */
		EClass IN_PORT_TYPE = eINSTANCE.getInPortType();

		/**
		 * The meta object literal for the '<em><b>Node Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IN_PORT_TYPE__NODE_ID = eINSTANCE.getInPortType_NodeId();

		/**
		 * The meta object literal for the '<em><b>Node In Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IN_PORT_TYPE__NODE_IN_TYPE = eINSTANCE.getInPortType_NodeInType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IN_PORT_TYPE__TYPE = eINSTANCE.getInPortType_Type();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.JoinTypeImpl <em>Join Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.JoinTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getJoinType()
		 * @generated
		 */
		EClass JOIN_TYPE = eINSTANCE.getJoinType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__GROUP = eINSTANCE.getJoinType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JOIN_TYPE__META_DATA = eINSTANCE.getJoinType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__HEIGHT = eINSTANCE.getJoinType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__ID = eINSTANCE.getJoinType_Id();

		/**
		 * The meta object literal for the '<em><b>N</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__N = eINSTANCE.getJoinType_N();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__NAME = eINSTANCE.getJoinType_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__TYPE = eINSTANCE.getJoinType_Type();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__WIDTH = eINSTANCE.getJoinType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__X = eINSTANCE.getJoinType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JOIN_TYPE__Y = eINSTANCE.getJoinType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.MappingTypeImpl <em>Mapping Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.MappingTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getMappingType()
		 * @generated
		 */
		EClass MAPPING_TYPE = eINSTANCE.getMappingType();

		/**
		 * The meta object literal for the '<em><b>From</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAPPING_TYPE__FROM = eINSTANCE.getMappingType_From();

		/**
		 * The meta object literal for the '<em><b>To</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAPPING_TYPE__TO = eINSTANCE.getMappingType_To();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAPPING_TYPE__TYPE = eINSTANCE.getMappingType_Type();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.MetaDataTypeImpl <em>Meta Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.MetaDataTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getMetaDataType()
		 * @generated
		 */
		EClass META_DATA_TYPE = eINSTANCE.getMetaDataType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference META_DATA_TYPE__VALUE = eINSTANCE.getMetaDataType_Value();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute META_DATA_TYPE__NAME = eINSTANCE.getMetaDataType_Name();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.MilestoneTypeImpl <em>Milestone Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.MilestoneTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getMilestoneType()
		 * @generated
		 */
		EClass MILESTONE_TYPE = eINSTANCE.getMilestoneType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MILESTONE_TYPE__GROUP = eINSTANCE.getMilestoneType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MILESTONE_TYPE__META_DATA = eINSTANCE.getMilestoneType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MILESTONE_TYPE__CONSTRAINT = eINSTANCE.getMilestoneType_Constraint();

		/**
		 * The meta object literal for the '<em><b>Timers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MILESTONE_TYPE__TIMERS = eINSTANCE.getMilestoneType_Timers();

		/**
		 * The meta object literal for the '<em><b>On Entry</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MILESTONE_TYPE__ON_ENTRY = eINSTANCE.getMilestoneType_OnEntry();

		/**
		 * The meta object literal for the '<em><b>On Exit</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MILESTONE_TYPE__ON_EXIT = eINSTANCE.getMilestoneType_OnExit();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MILESTONE_TYPE__HEIGHT = eINSTANCE.getMilestoneType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MILESTONE_TYPE__ID = eINSTANCE.getMilestoneType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MILESTONE_TYPE__NAME = eINSTANCE.getMilestoneType_Name();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MILESTONE_TYPE__WIDTH = eINSTANCE.getMilestoneType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MILESTONE_TYPE__X = eINSTANCE.getMilestoneType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MILESTONE_TYPE__Y = eINSTANCE.getMilestoneType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.NodesTypeImpl <em>Nodes Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.NodesTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getNodesType()
		 * @generated
		 */
		EClass NODES_TYPE = eINSTANCE.getNodesType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODES_TYPE__GROUP = eINSTANCE.getNodesType_Group();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__START = eINSTANCE.getNodesType_Start();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__END = eINSTANCE.getNodesType_End();

		/**
		 * The meta object literal for the '<em><b>Action Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__ACTION_NODE = eINSTANCE.getNodesType_ActionNode();

		/**
		 * The meta object literal for the '<em><b>Rule Set</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__RULE_SET = eINSTANCE.getNodesType_RuleSet();

		/**
		 * The meta object literal for the '<em><b>Split</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__SPLIT = eINSTANCE.getNodesType_Split();

		/**
		 * The meta object literal for the '<em><b>Join</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__JOIN = eINSTANCE.getNodesType_Join();

		/**
		 * The meta object literal for the '<em><b>Milestone</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__MILESTONE = eINSTANCE.getNodesType_Milestone();

		/**
		 * The meta object literal for the '<em><b>Sub Process</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__SUB_PROCESS = eINSTANCE.getNodesType_SubProcess();

		/**
		 * The meta object literal for the '<em><b>Work Item</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__WORK_ITEM = eINSTANCE.getNodesType_WorkItem();

		/**
		 * The meta object literal for the '<em><b>Timer Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__TIMER_NODE = eINSTANCE.getNodesType_TimerNode();

		/**
		 * The meta object literal for the '<em><b>Human Task</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__HUMAN_TASK = eINSTANCE.getNodesType_HumanTask();

		/**
		 * The meta object literal for the '<em><b>Composite</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__COMPOSITE = eINSTANCE.getNodesType_Composite();

		/**
		 * The meta object literal for the '<em><b>For Each</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__FOR_EACH = eINSTANCE.getNodesType_ForEach();

		/**
		 * The meta object literal for the '<em><b>Event Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__EVENT_NODE = eINSTANCE.getNodesType_EventNode();

		/**
		 * The meta object literal for the '<em><b>Fault</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__FAULT = eINSTANCE.getNodesType_Fault();

		/**
		 * The meta object literal for the '<em><b>State</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__STATE = eINSTANCE.getNodesType_State();

		/**
		 * The meta object literal for the '<em><b>Dynamic</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODES_TYPE__DYNAMIC = eINSTANCE.getNodesType_Dynamic();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.OnEntryTypeImpl <em>On Entry Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.OnEntryTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getOnEntryType()
		 * @generated
		 */
		EClass ON_ENTRY_TYPE = eINSTANCE.getOnEntryType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ON_ENTRY_TYPE__GROUP = eINSTANCE.getOnEntryType_Group();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ON_ENTRY_TYPE__ACTION = eINSTANCE.getOnEntryType_Action();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.OnExitTypeImpl <em>On Exit Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.OnExitTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getOnExitType()
		 * @generated
		 */
		EClass ON_EXIT_TYPE = eINSTANCE.getOnExitType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ON_EXIT_TYPE__GROUP = eINSTANCE.getOnExitType_Group();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ON_EXIT_TYPE__ACTION = eINSTANCE.getOnExitType_Action();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.OutPortsTypeImpl <em>Out Ports Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.OutPortsTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getOutPortsType()
		 * @generated
		 */
		EClass OUT_PORTS_TYPE = eINSTANCE.getOutPortsType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUT_PORTS_TYPE__GROUP = eINSTANCE.getOutPortsType_Group();

		/**
		 * The meta object literal for the '<em><b>Out Port</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUT_PORTS_TYPE__OUT_PORT = eINSTANCE.getOutPortsType_OutPort();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.OutPortTypeImpl <em>Out Port Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.OutPortTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getOutPortType()
		 * @generated
		 */
		EClass OUT_PORT_TYPE = eINSTANCE.getOutPortType();

		/**
		 * The meta object literal for the '<em><b>Node Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUT_PORT_TYPE__NODE_ID = eINSTANCE.getOutPortType_NodeId();

		/**
		 * The meta object literal for the '<em><b>Node Out Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUT_PORT_TYPE__NODE_OUT_TYPE = eINSTANCE.getOutPortType_NodeOutType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OUT_PORT_TYPE__TYPE = eINSTANCE.getOutPortType_Type();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ParameterTypeImpl <em>Parameter Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ParameterTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getParameterType()
		 * @generated
		 */
		EClass PARAMETER_TYPE = eINSTANCE.getParameterType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER_TYPE__GROUP = eINSTANCE.getParameterType_Group();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_TYPE__TYPE = eINSTANCE.getParameterType_Type();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER_TYPE__VALUE = eINSTANCE.getParameterType_Value();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER_TYPE__NAME = eINSTANCE.getParameterType_Name();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ProcessTypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ProcessTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getProcessType()
		 * @generated
		 */
		EClass PROCESS_TYPE = eINSTANCE.getProcessType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_TYPE__GROUP = eINSTANCE.getProcessType_Group();

		/**
		 * The meta object literal for the '<em><b>Header</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_TYPE__HEADER = eINSTANCE.getProcessType_Header();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_TYPE__NODES = eINSTANCE.getProcessType_Nodes();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROCESS_TYPE__CONNECTIONS = eINSTANCE.getProcessType_Connections();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_TYPE__ID = eINSTANCE.getProcessType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_TYPE__NAME = eINSTANCE.getProcessType_Name();

		/**
		 * The meta object literal for the '<em><b>Package Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_TYPE__PACKAGE_NAME = eINSTANCE.getProcessType_PackageName();

		/**
		 * The meta object literal for the '<em><b>Router Layout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_TYPE__ROUTER_LAYOUT = eINSTANCE.getProcessType_RouterLayout();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_TYPE__TYPE = eINSTANCE.getProcessType_Type();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROCESS_TYPE__VERSION = eINSTANCE.getProcessType_Version();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.RuleSetTypeImpl <em>Rule Set Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.RuleSetTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getRuleSetType()
		 * @generated
		 */
		EClass RULE_SET_TYPE = eINSTANCE.getRuleSetType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_SET_TYPE__GROUP = eINSTANCE.getRuleSetType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_SET_TYPE__META_DATA = eINSTANCE.getRuleSetType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Timers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE_SET_TYPE__TIMERS = eINSTANCE.getRuleSetType_Timers();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_SET_TYPE__HEIGHT = eINSTANCE.getRuleSetType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_SET_TYPE__ID = eINSTANCE.getRuleSetType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_SET_TYPE__NAME = eINSTANCE.getRuleSetType_Name();

		/**
		 * The meta object literal for the '<em><b>Rule Flow Group</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_SET_TYPE__RULE_FLOW_GROUP = eINSTANCE.getRuleSetType_RuleFlowGroup();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_SET_TYPE__WIDTH = eINSTANCE.getRuleSetType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_SET_TYPE__X = eINSTANCE.getRuleSetType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RULE_SET_TYPE__Y = eINSTANCE.getRuleSetType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.SplitTypeImpl <em>Split Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.SplitTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getSplitType()
		 * @generated
		 */
		EClass SPLIT_TYPE = eINSTANCE.getSplitType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPLIT_TYPE__GROUP = eINSTANCE.getSplitType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPLIT_TYPE__META_DATA = eINSTANCE.getSplitType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SPLIT_TYPE__CONSTRAINTS = eINSTANCE.getSplitType_Constraints();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPLIT_TYPE__HEIGHT = eINSTANCE.getSplitType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPLIT_TYPE__ID = eINSTANCE.getSplitType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPLIT_TYPE__NAME = eINSTANCE.getSplitType_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPLIT_TYPE__TYPE = eINSTANCE.getSplitType_Type();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPLIT_TYPE__WIDTH = eINSTANCE.getSplitType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPLIT_TYPE__X = eINSTANCE.getSplitType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SPLIT_TYPE__Y = eINSTANCE.getSplitType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.StartTypeImpl <em>Start Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.StartTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getStartType()
		 * @generated
		 */
		EClass START_TYPE = eINSTANCE.getStartType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_TYPE__GROUP = eINSTANCE.getStartType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_TYPE__META_DATA = eINSTANCE.getStartType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference START_TYPE__TRIGGERS = eINSTANCE.getStartType_Triggers();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_TYPE__HEIGHT = eINSTANCE.getStartType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_TYPE__ID = eINSTANCE.getStartType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_TYPE__NAME = eINSTANCE.getStartType_Name();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_TYPE__WIDTH = eINSTANCE.getStartType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_TYPE__X = eINSTANCE.getStartType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute START_TYPE__Y = eINSTANCE.getStartType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.StateTypeImpl <em>State Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.StateTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getStateType()
		 * @generated
		 */
		EClass STATE_TYPE = eINSTANCE.getStateType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_TYPE__GROUP = eINSTANCE.getStateType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_TYPE__META_DATA = eINSTANCE.getStateType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Timers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_TYPE__TIMERS = eINSTANCE.getStateType_Timers();

		/**
		 * The meta object literal for the '<em><b>On Entry</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_TYPE__ON_ENTRY = eINSTANCE.getStateType_OnEntry();

		/**
		 * The meta object literal for the '<em><b>On Exit</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_TYPE__ON_EXIT = eINSTANCE.getStateType_OnExit();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_TYPE__CONSTRAINTS = eINSTANCE.getStateType_Constraints();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_TYPE__HEIGHT = eINSTANCE.getStateType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_TYPE__ID = eINSTANCE.getStateType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_TYPE__NAME = eINSTANCE.getStateType_Name();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_TYPE__WIDTH = eINSTANCE.getStateType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_TYPE__X = eINSTANCE.getStateType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE_TYPE__Y = eINSTANCE.getStateType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.SubProcessTypeImpl <em>Sub Process Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.SubProcessTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getSubProcessType()
		 * @generated
		 */
		EClass SUB_PROCESS_TYPE = eINSTANCE.getSubProcessType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__GROUP = eINSTANCE.getSubProcessType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_PROCESS_TYPE__META_DATA = eINSTANCE.getSubProcessType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Mapping</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_PROCESS_TYPE__MAPPING = eINSTANCE.getSubProcessType_Mapping();

		/**
		 * The meta object literal for the '<em><b>Timers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_PROCESS_TYPE__TIMERS = eINSTANCE.getSubProcessType_Timers();

		/**
		 * The meta object literal for the '<em><b>On Entry</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_PROCESS_TYPE__ON_ENTRY = eINSTANCE.getSubProcessType_OnEntry();

		/**
		 * The meta object literal for the '<em><b>On Exit</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUB_PROCESS_TYPE__ON_EXIT = eINSTANCE.getSubProcessType_OnExit();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__HEIGHT = eINSTANCE.getSubProcessType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__ID = eINSTANCE.getSubProcessType_Id();

		/**
		 * The meta object literal for the '<em><b>Independent</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__INDEPENDENT = eINSTANCE.getSubProcessType_Independent();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__NAME = eINSTANCE.getSubProcessType_Name();

		/**
		 * The meta object literal for the '<em><b>Process Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__PROCESS_ID = eINSTANCE.getSubProcessType_ProcessId();

		/**
		 * The meta object literal for the '<em><b>Wait For Completion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__WAIT_FOR_COMPLETION = eINSTANCE.getSubProcessType_WaitForCompletion();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__WIDTH = eINSTANCE.getSubProcessType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__X = eINSTANCE.getSubProcessType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUB_PROCESS_TYPE__Y = eINSTANCE.getSubProcessType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.SwimlanesTypeImpl <em>Swimlanes Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.SwimlanesTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getSwimlanesType()
		 * @generated
		 */
		EClass SWIMLANES_TYPE = eINSTANCE.getSwimlanesType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWIMLANES_TYPE__GROUP = eINSTANCE.getSwimlanesType_Group();

		/**
		 * The meta object literal for the '<em><b>Swimlane</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SWIMLANES_TYPE__SWIMLANE = eINSTANCE.getSwimlanesType_Swimlane();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.SwimlaneTypeImpl <em>Swimlane Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.SwimlaneTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getSwimlaneType()
		 * @generated
		 */
		EClass SWIMLANE_TYPE = eINSTANCE.getSwimlaneType();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SWIMLANE_TYPE__NAME = eINSTANCE.getSwimlaneType_Name();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.TimerNodeTypeImpl <em>Timer Node Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.TimerNodeTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getTimerNodeType()
		 * @generated
		 */
		EClass TIMER_NODE_TYPE = eINSTANCE.getTimerNodeType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_NODE_TYPE__GROUP = eINSTANCE.getTimerNodeType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMER_NODE_TYPE__META_DATA = eINSTANCE.getTimerNodeType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_NODE_TYPE__DELAY = eINSTANCE.getTimerNodeType_Delay();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_NODE_TYPE__HEIGHT = eINSTANCE.getTimerNodeType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_NODE_TYPE__ID = eINSTANCE.getTimerNodeType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_NODE_TYPE__NAME = eINSTANCE.getTimerNodeType_Name();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_NODE_TYPE__PERIOD = eINSTANCE.getTimerNodeType_Period();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_NODE_TYPE__WIDTH = eINSTANCE.getTimerNodeType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_NODE_TYPE__X = eINSTANCE.getTimerNodeType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_NODE_TYPE__Y = eINSTANCE.getTimerNodeType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.TimersTypeImpl <em>Timers Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.TimersTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getTimersType()
		 * @generated
		 */
		EClass TIMERS_TYPE = eINSTANCE.getTimersType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMERS_TYPE__GROUP = eINSTANCE.getTimersType_Group();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMERS_TYPE__TIMER = eINSTANCE.getTimersType_Timer();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.TimerTypeImpl <em>Timer Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.TimerTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getTimerType()
		 * @generated
		 */
		EClass TIMER_TYPE = eINSTANCE.getTimerType();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMER_TYPE__ACTION = eINSTANCE.getTimerType_Action();

		/**
		 * The meta object literal for the '<em><b>Delay</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_TYPE__DELAY = eINSTANCE.getTimerType_Delay();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_TYPE__ID = eINSTANCE.getTimerType_Id();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMER_TYPE__PERIOD = eINSTANCE.getTimerType_Period();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.TriggersTypeImpl <em>Triggers Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.TriggersTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getTriggersType()
		 * @generated
		 */
		EClass TRIGGERS_TYPE = eINSTANCE.getTriggersType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGERS_TYPE__GROUP = eINSTANCE.getTriggersType_Group();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGERS_TYPE__TRIGGER = eINSTANCE.getTriggersType_Trigger();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.TriggerTypeImpl <em>Trigger Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.TriggerTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getTriggerType()
		 * @generated
		 */
		EClass TRIGGER_TYPE = eINSTANCE.getTriggerType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_TYPE__GROUP = eINSTANCE.getTriggerType_Group();

		/**
		 * The meta object literal for the '<em><b>Constraint</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER_TYPE__CONSTRAINT = eINSTANCE.getTriggerType_Constraint();

		/**
		 * The meta object literal for the '<em><b>Event Filters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER_TYPE__EVENT_FILTERS = eINSTANCE.getTriggerType_EventFilters();

		/**
		 * The meta object literal for the '<em><b>Mapping</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER_TYPE__MAPPING = eINSTANCE.getTriggerType_Mapping();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER_TYPE__TYPE = eINSTANCE.getTriggerType_Type();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.TypeTypeImpl <em>Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.TypeTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getTypeType()
		 * @generated
		 */
		EClass TYPE_TYPE = eINSTANCE.getTypeType();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_TYPE__CLASS_NAME = eINSTANCE.getTypeType_ClassName();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_TYPE__NAME = eINSTANCE.getTypeType_Name();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.ValueTypeImpl <em>Value Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.ValueTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getValueType()
		 * @generated
		 */
		EClass VALUE_TYPE = eINSTANCE.getValueType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_TYPE__VALUE = eINSTANCE.getValueType_Value();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.VariablesTypeImpl <em>Variables Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.VariablesTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getVariablesType()
		 * @generated
		 */
		EClass VARIABLES_TYPE = eINSTANCE.getVariablesType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLES_TYPE__GROUP = eINSTANCE.getVariablesType_Group();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLES_TYPE__VARIABLE = eINSTANCE.getVariablesType_Variable();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.VariableTypeImpl <em>Variable Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.VariableTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getVariableType()
		 * @generated
		 */
		EClass VARIABLE_TYPE = eINSTANCE.getVariableType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_TYPE__GROUP = eINSTANCE.getVariableType_Group();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_TYPE__TYPE = eINSTANCE.getVariableType_Type();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_TYPE__VALUE = eINSTANCE.getVariableType_Value();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_TYPE__NAME = eINSTANCE.getVariableType_Name();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.WorkItemTypeImpl <em>Work Item Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.WorkItemTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getWorkItemType()
		 * @generated
		 */
		EClass WORK_ITEM_TYPE = eINSTANCE.getWorkItemType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ITEM_TYPE__GROUP = eINSTANCE.getWorkItemType_Group();

		/**
		 * The meta object literal for the '<em><b>Meta Data</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_ITEM_TYPE__META_DATA = eINSTANCE.getWorkItemType_MetaData();

		/**
		 * The meta object literal for the '<em><b>Timers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_ITEM_TYPE__TIMERS = eINSTANCE.getWorkItemType_Timers();

		/**
		 * The meta object literal for the '<em><b>Work</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_ITEM_TYPE__WORK = eINSTANCE.getWorkItemType_Work();

		/**
		 * The meta object literal for the '<em><b>Mapping</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_ITEM_TYPE__MAPPING = eINSTANCE.getWorkItemType_Mapping();

		/**
		 * The meta object literal for the '<em><b>On Entry</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_ITEM_TYPE__ON_ENTRY = eINSTANCE.getWorkItemType_OnEntry();

		/**
		 * The meta object literal for the '<em><b>On Exit</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_ITEM_TYPE__ON_EXIT = eINSTANCE.getWorkItemType_OnExit();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ITEM_TYPE__HEIGHT = eINSTANCE.getWorkItemType_Height();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ITEM_TYPE__ID = eINSTANCE.getWorkItemType_Id();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ITEM_TYPE__NAME = eINSTANCE.getWorkItemType_Name();

		/**
		 * The meta object literal for the '<em><b>Wait For Completion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ITEM_TYPE__WAIT_FOR_COMPLETION = eINSTANCE.getWorkItemType_WaitForCompletion();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ITEM_TYPE__WIDTH = eINSTANCE.getWorkItemType_Width();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ITEM_TYPE__X = eINSTANCE.getWorkItemType_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_ITEM_TYPE__Y = eINSTANCE.getWorkItemType_Y();

		/**
		 * The meta object literal for the '{@link org.drools.drools._5._0.process.impl.WorkTypeImpl <em>Work Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.drools.drools._5._0.process.impl.WorkTypeImpl
		 * @see org.drools.drools._5._0.process.impl.ProcessPackageImpl#getWorkType()
		 * @generated
		 */
		EClass WORK_TYPE = eINSTANCE.getWorkType();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_TYPE__GROUP = eINSTANCE.getWorkType_Group();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORK_TYPE__PARAMETER = eINSTANCE.getWorkType_Parameter();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORK_TYPE__NAME = eINSTANCE.getWorkType_Name();

	}

} //ProcessPackage
