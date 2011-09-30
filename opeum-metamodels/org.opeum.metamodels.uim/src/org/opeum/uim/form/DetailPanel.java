/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim.form;

import org.opeum.uim.MasterComponent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Detail Panel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.form.DetailPanel#getMasterComponent <em>Master Component</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.form.FormPackage#getDetailPanel()
 * @model
 * @generated
 */
public interface DetailPanel extends UimForm {
	/**
	 * Returns the value of the '<em><b>Master Component</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.MasterComponent#getDetailPanels <em>Detail Panels</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Master Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Master Component</em>' reference.
	 * @see #setMasterComponent(MasterComponent)
	 * @see org.opeum.uim.form.FormPackage#getDetailPanel_MasterComponent()
	 * @see org.opeum.uim.MasterComponent#getDetailPanels
	 * @model opposite="detailPanels"
	 * @generated
	 */
	MasterComponent getMasterComponent();

	/**
	 * Sets the value of the '{@link org.opeum.uim.form.DetailPanel#getMasterComponent <em>Master Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Master Component</em>' reference.
	 * @see #getMasterComponent()
	 * @generated
	 */
	void setMasterComponent(MasterComponent value);

} // DetailPanel
