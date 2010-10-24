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
 * A representation of the model object '<em><b>Action Node Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.ActionNodeType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ActionNodeType#getMetaData <em>Meta Data</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ActionNodeType#getAction <em>Action</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ActionNodeType#getHeight <em>Height</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ActionNodeType#getId <em>Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ActionNodeType#getName <em>Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ActionNodeType#getWidth <em>Width</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ActionNodeType#getX <em>X</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ActionNodeType#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.drools.drools._5._0.process.ProcessPackage#getActionNodeType()
 * @model extendedMetaData="name='actionNode_._type' kind='elementOnly'"
 * @generated
 */
public interface ActionNodeType extends EObject {
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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getActionNodeType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Meta Data</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.MetaDataType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta Data</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Meta Data</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getActionNodeType_MetaData()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='metaData' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<MetaDataType> getMetaData();

	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.ActionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getActionNodeType_Action()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='action' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ActionType> getAction();

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getActionNodeType_Height()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='height'"
	 * @generated
	 */
	String getHeight();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ActionNodeType#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(String value);

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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getActionNodeType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ActionNodeType#getId <em>Id</em>}' attribute.
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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getActionNodeType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ActionNodeType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getActionNodeType_Width()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='width'"
	 * @generated
	 */
	String getWidth();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ActionNodeType#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(String value);

	/**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getActionNodeType_X()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='x'"
	 * @generated
	 */
	String getX();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ActionNodeType#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
	void setX(String value);

	/**
	 * Returns the value of the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Y</em>' attribute.
	 * @see #setY(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getActionNodeType_Y()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='y'"
	 * @generated
	 */
	String getY();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ActionNodeType#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
	void setY(String value);

} // ActionNodeType
