/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.drools.drools._5._0.process;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>In Port Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.InPortType#getNodeId <em>Node Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.InPortType#getNodeInType <em>Node In Type</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.InPortType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.drools.drools._5._0.process.ProcessPackage#getInPortType()
 * @model extendedMetaData="name='in-port_._type' kind='empty'"
 * @generated
 */
public interface InPortType extends EObject {
	/**
	 * Returns the value of the '<em><b>Node Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node Id</em>' attribute.
	 * @see #setNodeId(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getInPortType_NodeId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='nodeId'"
	 * @generated
	 */
	String getNodeId();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.InPortType#getNodeId <em>Node Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node Id</em>' attribute.
	 * @see #getNodeId()
	 * @generated
	 */
	void setNodeId(String value);

	/**
	 * Returns the value of the '<em><b>Node In Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node In Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node In Type</em>' attribute.
	 * @see #setNodeInType(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getInPortType_NodeInType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='nodeInType'"
	 * @generated
	 */
	String getNodeInType();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.InPortType#getNodeInType <em>Node In Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Node In Type</em>' attribute.
	 * @see #getNodeInType()
	 * @generated
	 */
	void setNodeInType(String value);

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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getInPortType_Type()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='type'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.InPortType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // InPortType
