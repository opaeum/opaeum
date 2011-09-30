/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opeum.uim;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.opeum.uim.form.DetailPanel;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Master Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opeum.uim.MasterComponent#getDetailPanels <em>Detail Panels</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opeum.uim.UimPackage#getMasterComponent()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface MasterComponent extends EObject {
	/**
	 * Returns the value of the '<em><b>Detail Panels</b></em>' reference list.
	 * The list contents are of type {@link org.opeum.uim.form.DetailPanel}.
	 * It is bidirectional and its opposite is '{@link org.opeum.uim.form.DetailPanel#getMasterComponent <em>Master Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Detail Panels</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Detail Panels</em>' reference list.
	 * @see org.opeum.uim.UimPackage#getMasterComponent_DetailPanels()
	 * @see org.opeum.uim.form.DetailPanel#getMasterComponent
	 * @model opposite="masterComponent"
	 * @generated
	 */
	EList<DetailPanel> getDetailPanels();

} // MasterComponent
