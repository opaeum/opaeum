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
 * A representation of the model object '<em><b>For Each Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getMetaData <em>Meta Data</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getNodes <em>Nodes</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getConnections <em>Connections</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getInPorts <em>In Ports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getOutPorts <em>Out Ports</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getCollectionExpression <em>Collection Expression</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getHeight <em>Height</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getId <em>Id</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getName <em>Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getVariableName <em>Variable Name</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getWaitForCompletion <em>Wait For Completion</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getWidth <em>Width</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getX <em>X</em>}</li>
 *   <li>{@link org.drools.drools._5._0.process.ForEachType#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType()
 * @model extendedMetaData="name='forEach_._type' kind='elementOnly'"
 * @generated
 */
public interface ForEachType extends EObject {
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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_Group()
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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_MetaData()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='metaData' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<MetaDataType> getMetaData();

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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_Nodes()
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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_Connections()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='connections' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ConnectionsType> getConnections();

	/**
	 * Returns the value of the '<em><b>In Ports</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.InPortsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Ports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Ports</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_InPorts()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='in-ports' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<InPortsType> getInPorts();

	/**
	 * Returns the value of the '<em><b>Out Ports</b></em>' containment reference list.
	 * The list contents are of type {@link org.drools.drools._5._0.process.OutPortsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Out Ports</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Ports</em>' containment reference list.
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_OutPorts()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='out-ports' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<OutPortsType> getOutPorts();

	/**
	 * Returns the value of the '<em><b>Collection Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collection Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collection Expression</em>' attribute.
	 * @see #setCollectionExpression(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_CollectionExpression()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='collectionExpression'"
	 * @generated
	 */
	String getCollectionExpression();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ForEachType#getCollectionExpression <em>Collection Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collection Expression</em>' attribute.
	 * @see #getCollectionExpression()
	 * @generated
	 */
	void setCollectionExpression(String value);

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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_Height()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='height'"
	 * @generated
	 */
	String getHeight();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ForEachType#getHeight <em>Height</em>}' attribute.
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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ForEachType#getId <em>Id</em>}' attribute.
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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ForEachType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Variable Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Name</em>' attribute.
	 * @see #setVariableName(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_VariableName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='variableName'"
	 * @generated
	 */
	String getVariableName();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ForEachType#getVariableName <em>Variable Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Name</em>' attribute.
	 * @see #getVariableName()
	 * @generated
	 */
	void setVariableName(String value);

	/**
	 * Returns the value of the '<em><b>Wait For Completion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Wait For Completion</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Wait For Completion</em>' attribute.
	 * @see #setWaitForCompletion(String)
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_WaitForCompletion()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='waitForCompletion'"
	 * @generated
	 */
	String getWaitForCompletion();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ForEachType#getWaitForCompletion <em>Wait For Completion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wait For Completion</em>' attribute.
	 * @see #getWaitForCompletion()
	 * @generated
	 */
	void setWaitForCompletion(String value);

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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_Width()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='width'"
	 * @generated
	 */
	String getWidth();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ForEachType#getWidth <em>Width</em>}' attribute.
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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_X()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='x'"
	 * @generated
	 */
	String getX();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ForEachType#getX <em>X</em>}' attribute.
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
	 * @see org.drools.drools._5._0.process.ProcessPackage#getForEachType_Y()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='y'"
	 * @generated
	 */
	String getY();

	/**
	 * Sets the value of the '{@link org.drools.drools._5._0.process.ForEachType#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
	void setY(String value);

} // ForEachType
