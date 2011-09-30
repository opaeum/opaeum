/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.apache.maven.pom;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Notifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 4.0.0
 * 
 *         Configures one method for notifying users/developers when a build breaks.
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.apache.maven.pom.Notifier#getType <em>Type</em>}</li>
 *   <li>{@link org.apache.maven.pom.Notifier#isSendOnError <em>Send On Error</em>}</li>
 *   <li>{@link org.apache.maven.pom.Notifier#isSendOnFailure <em>Send On Failure</em>}</li>
 *   <li>{@link org.apache.maven.pom.Notifier#isSendOnSuccess <em>Send On Success</em>}</li>
 *   <li>{@link org.apache.maven.pom.Notifier#isSendOnWarning <em>Send On Warning</em>}</li>
 *   <li>{@link org.apache.maven.pom.Notifier#getAddress <em>Address</em>}</li>
 *   <li>{@link org.apache.maven.pom.Notifier#getConfiguration <em>Configuration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.apache.maven.pom.POMPackage#getNotifier()
 * @model extendedMetaData="name='Notifier' kind='elementOnly'"
 * @generated
 */
public interface Notifier extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"mail"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * The mechanism used to deliver notifications.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(String)
	 * @see org.apache.maven.pom.POMPackage#getNotifier_Type()
	 * @model default="mail" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='type' namespace='##targetNamespace'"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Notifier#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Unsets the value of the '{@link org.apache.maven.pom.Notifier#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(String)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link org.apache.maven.pom.Notifier#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(String)
	 * @generated
	 */
	boolean isSetType();

	/**
	 * Returns the value of the '<em><b>Send On Error</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * Whether to send notifications on error.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Send On Error</em>' attribute.
	 * @see #isSetSendOnError()
	 * @see #unsetSendOnError()
	 * @see #setSendOnError(boolean)
	 * @see org.apache.maven.pom.POMPackage#getNotifier_SendOnError()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='sendOnError' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSendOnError();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Notifier#isSendOnError <em>Send On Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Send On Error</em>' attribute.
	 * @see #isSetSendOnError()
	 * @see #unsetSendOnError()
	 * @see #isSendOnError()
	 * @generated
	 */
	void setSendOnError(boolean value);

	/**
	 * Unsets the value of the '{@link org.apache.maven.pom.Notifier#isSendOnError <em>Send On Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSendOnError()
	 * @see #isSendOnError()
	 * @see #setSendOnError(boolean)
	 * @generated
	 */
	void unsetSendOnError();

	/**
	 * Returns whether the value of the '{@link org.apache.maven.pom.Notifier#isSendOnError <em>Send On Error</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Send On Error</em>' attribute is set.
	 * @see #unsetSendOnError()
	 * @see #isSendOnError()
	 * @see #setSendOnError(boolean)
	 * @generated
	 */
	boolean isSetSendOnError();

	/**
	 * Returns the value of the '<em><b>Send On Failure</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * Whether to send notifications on failure.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Send On Failure</em>' attribute.
	 * @see #isSetSendOnFailure()
	 * @see #unsetSendOnFailure()
	 * @see #setSendOnFailure(boolean)
	 * @see org.apache.maven.pom.POMPackage#getNotifier_SendOnFailure()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='sendOnFailure' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSendOnFailure();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Notifier#isSendOnFailure <em>Send On Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Send On Failure</em>' attribute.
	 * @see #isSetSendOnFailure()
	 * @see #unsetSendOnFailure()
	 * @see #isSendOnFailure()
	 * @generated
	 */
	void setSendOnFailure(boolean value);

	/**
	 * Unsets the value of the '{@link org.apache.maven.pom.Notifier#isSendOnFailure <em>Send On Failure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSendOnFailure()
	 * @see #isSendOnFailure()
	 * @see #setSendOnFailure(boolean)
	 * @generated
	 */
	void unsetSendOnFailure();

	/**
	 * Returns whether the value of the '{@link org.apache.maven.pom.Notifier#isSendOnFailure <em>Send On Failure</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Send On Failure</em>' attribute is set.
	 * @see #unsetSendOnFailure()
	 * @see #isSendOnFailure()
	 * @see #setSendOnFailure(boolean)
	 * @generated
	 */
	boolean isSetSendOnFailure();

	/**
	 * Returns the value of the '<em><b>Send On Success</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * Whether to send notifications on success.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Send On Success</em>' attribute.
	 * @see #isSetSendOnSuccess()
	 * @see #unsetSendOnSuccess()
	 * @see #setSendOnSuccess(boolean)
	 * @see org.apache.maven.pom.POMPackage#getNotifier_SendOnSuccess()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='sendOnSuccess' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSendOnSuccess();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Notifier#isSendOnSuccess <em>Send On Success</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Send On Success</em>' attribute.
	 * @see #isSetSendOnSuccess()
	 * @see #unsetSendOnSuccess()
	 * @see #isSendOnSuccess()
	 * @generated
	 */
	void setSendOnSuccess(boolean value);

	/**
	 * Unsets the value of the '{@link org.apache.maven.pom.Notifier#isSendOnSuccess <em>Send On Success</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSendOnSuccess()
	 * @see #isSendOnSuccess()
	 * @see #setSendOnSuccess(boolean)
	 * @generated
	 */
	void unsetSendOnSuccess();

	/**
	 * Returns whether the value of the '{@link org.apache.maven.pom.Notifier#isSendOnSuccess <em>Send On Success</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Send On Success</em>' attribute is set.
	 * @see #unsetSendOnSuccess()
	 * @see #isSendOnSuccess()
	 * @see #setSendOnSuccess(boolean)
	 * @generated
	 */
	boolean isSetSendOnSuccess();

	/**
	 * Returns the value of the '<em><b>Send On Warning</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * Whether to send notifications on warning.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Send On Warning</em>' attribute.
	 * @see #isSetSendOnWarning()
	 * @see #unsetSendOnWarning()
	 * @see #setSendOnWarning(boolean)
	 * @see org.apache.maven.pom.POMPackage#getNotifier_SendOnWarning()
	 * @model default="true" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='element' name='sendOnWarning' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isSendOnWarning();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Notifier#isSendOnWarning <em>Send On Warning</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Send On Warning</em>' attribute.
	 * @see #isSetSendOnWarning()
	 * @see #unsetSendOnWarning()
	 * @see #isSendOnWarning()
	 * @generated
	 */
	void setSendOnWarning(boolean value);

	/**
	 * Unsets the value of the '{@link org.apache.maven.pom.Notifier#isSendOnWarning <em>Send On Warning</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSendOnWarning()
	 * @see #isSendOnWarning()
	 * @see #setSendOnWarning(boolean)
	 * @generated
	 */
	void unsetSendOnWarning();

	/**
	 * Returns whether the value of the '{@link org.apache.maven.pom.Notifier#isSendOnWarning <em>Send On Warning</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Send On Warning</em>' attribute is set.
	 * @see #unsetSendOnWarning()
	 * @see #isSendOnWarning()
	 * @see #setSendOnWarning(boolean)
	 * @generated
	 */
	boolean isSetSendOnWarning();

	/**
	 * Returns the value of the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 4.0.0
	 * 
	 *             <b>Deprecated</b>. Where to send the notification to - eg email address.
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Address</em>' attribute.
	 * @see #setAddress(String)
	 * @see org.apache.maven.pom.POMPackage#getNotifier_Address()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='address' namespace='##targetNamespace'"
	 * @generated
	 */
	String getAddress();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Notifier#getAddress <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' attribute.
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(String value);

	/**
	 * Returns the value of the '<em><b>Configuration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 0.0.0+
	 * Extended configuration specific to this notifier goes here.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Configuration</em>' containment reference.
	 * @see #setConfiguration(ConfigurationType4)
	 * @see org.apache.maven.pom.POMPackage#getNotifier_Configuration()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='configuration' namespace='##targetNamespace'"
	 * @generated
	 */
	ConfigurationType4 getConfiguration();

	/**
	 * Sets the value of the '{@link org.apache.maven.pom.Notifier#getConfiguration <em>Configuration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Configuration</em>' containment reference.
	 * @see #getConfiguration()
	 * @generated
	 */
	void setConfiguration(ConfigurationType4 value);

} // Notifier
