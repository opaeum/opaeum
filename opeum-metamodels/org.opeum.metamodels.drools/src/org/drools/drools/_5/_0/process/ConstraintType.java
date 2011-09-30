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
 * A representation of the model object '<em><b>Constraint Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.ConstraintType#getValue <em>Value</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ConstraintType#getDialect <em>Dialect</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ConstraintType#getName <em>Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ConstraintType#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ConstraintType#getToNodeId <em>To Node Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ConstraintType#getToType <em>To Type</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ConstraintType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.drools.drools._5._0.process.ProcessPackage#getConstraintType()
 * @model extendedMetaData="name='constraint_._type' kind='simple'"
 * @generated
 */
public interface ConstraintType extends EObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getConstraintType_Value()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="name=':0' kind='simple'"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ConstraintType#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Dialect</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dialect</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dialect</em>' attribute.
	 * @see #setDialect(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getConstraintType_Dialect()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='dialect'"
	 * @generated
	 */
	String getDialect();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ConstraintType#getDialect <em>Dialect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dialect</em>' attribute.
	 * @see #getDialect()
	 * @generated
	 */
	void setDialect(String value);

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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getConstraintType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ConstraintType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see #setPriority(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getConstraintType_Priority()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='priority'"
	 * @generated
	 */
	String getPriority();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ConstraintType#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(String value);

	/**
	 * Returns the value of the '<em><b>To Node Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>To Node Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>To Node Id</em>' attribute.
	 * @see #setToNodeId(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getConstraintType_ToNodeId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='toNodeId'"
	 * @generated
	 */
	String getToNodeId();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ConstraintType#getToNodeId <em>To Node Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Node Id</em>' attribute.
	 * @see #getToNodeId()
	 * @generated
	 */
	void setToNodeId(String value);

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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getConstraintType_ToType()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='toType'"
	 * @generated
	 */
	String getToType();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ConstraintType#getToType <em>To Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>To Type</em>' attribute.
	 * @see #getToType()
	 * @generated
	 */
	void setToType(String value);

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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getConstraintType_Type()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='type'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ConstraintType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

} // ConstraintType
