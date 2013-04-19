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
 * A representation of the model object '<em><b>Connection Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.ConnectionType#getBendpoints <em>Bendpoints</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ConnectionType#getFrom <em>From</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ConnectionType#getFromType <em>From Type</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ConnectionType#getTo <em>To</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ConnectionType#getToType <em>To Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.drools.drools._5._0.process.ProcessPackage#getConnectionType()
 * @model extendedMetaData="name='connection_._type' kind='empty'"
 * @generated
 */
public interface ConnectionType extends EObject {
	/**
	 * Returns the value of the '<em><b>Bendpoints</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bendpoints</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bendpoints</em>' attribute.
	 * @see #setBendpoints(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getConnectionType_Bendpoints()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='bendpoints'"
	 * @generated
	 */
	String getBendpoints();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ConnectionType#getBendpoints <em>Bendpoints</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bendpoints</em>' attribute.
	 * @see #getBendpoints()
	 * @generated
	 */
	void setBendpoints(String value);

	/**
	 * Returns the value of the '<em><b>From</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From</em>' attribute.
	 * @see #setFrom(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getConnectionType_From()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='from'"
	 * @generated
	 */
	String getFrom();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ConnectionType#getFrom <em>From</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From</em>' attribute.
	 * @see #getFrom()
	 * @generated
	 */
	void setFrom(String value);

	/**
	 * Returns the value of the '<em><b>From Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>From Type</em>' attribute.
	 * @see #setFromType(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getConnectionType_FromType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='fromType'"
	 * @generated
	 */
	String getFromType();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ConnectionType#getFromType <em>From Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>From Type</em>' attribute.
	 * @see #getFromType()
	 * @generated
	 */
	void setFromType(String value);

	/**
	 * Returns the value of the '<em><b>To</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To</em>' attribute.
	 * @see #setTo(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getConnectionType_To()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='to'"
	 * @generated
	 */
	String getTo();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ConnectionType#getTo <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To</em>' attribute.
	 * @see #getTo()
	 * @generated
	 */
	void setTo(String value);

	/**
	 * Returns the value of the '<em><b>To Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Type</em>' attribute.
	 * @see #setToType(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getConnectionType_ToType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='toType'"
	 * @generated
	 */
	String getToType();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ConnectionType#getToType <em>To Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Type</em>' attribute.
	 * @see #getToType()
	 * @generated
	 */
	void setToType(String value);

} // ConnectionType
