/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.ProcessType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ProcessType#getHeader <em>Header</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ProcessType#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ProcessType#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ProcessType#getId <em>Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ProcessType#getName <em>Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ProcessType#getPackageName <em>Package Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ProcessType#getRouterLayout <em>Router Layout</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ProcessType#getType <em>Type</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ProcessType#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.drools.drools._5._0.process.ProcessPackage#getProcessType()
 * @model extendedMetaData="name='process_._type' kind='elementOnly'"
 * @generated
 */
public interface ProcessType extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getProcessType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Header</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.HeaderType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Header</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Header</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getProcessType_Header()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='header' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<HeaderType> getHeader();

	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.NodesType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getProcessType_Nodes()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='nodes' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<NodesType> getNodes();

	/**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.ConnectionsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getProcessType_Connections()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='connections' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ConnectionsType> getConnections();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getProcessType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ProcessType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getProcessType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ProcessType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Package Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Package Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Package Name</em>' attribute.
	 * @see #setPackageName(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getProcessType_PackageName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='package-name'"
	 * @generated
	 */
	String getPackageName();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ProcessType#getPackageName <em>Package Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Name</em>' attribute.
	 * @see #getPackageName()
	 * @generated
	 */
	void setPackageName(String value);

	/**
	 * Returns the value of the '<em><b>Router Layout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Router Layout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Router Layout</em>' attribute.
	 * @see #setRouterLayout(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getProcessType_RouterLayout()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='routerLayout'"
	 * @generated
	 */
	String getRouterLayout();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ProcessType#getRouterLayout <em>Router Layout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Router Layout</em>' attribute.
	 * @see #getRouterLayout()
	 * @generated
	 */
	void setRouterLayout(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getProcessType_Type()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='type'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ProcessType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getProcessType_Version()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='version'"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ProcessType#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

} // ProcessType
