/**
 */
package org.opaeum.uim;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Master Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.MasterComponent#getDetailPanels <em>Detail Panels</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.UimPackage#getMasterComponent()
 * @model abstract="true"
 * @generated
 */
public interface MasterComponent extends EObject {
	/**
	 * Returns the value of the '<em><b>Detail Panels</b></em>' reference list.
	 * The list contents are of type {@link org.opaeum.uim.DetailComponent}.
	 * It is bidirectional and its opposite is '{@link org.opaeum.uim.DetailComponent#getMasterComponent <em>Master Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Detail Panels</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Detail Panels</em>' reference list.
	 * @see org.opaeum.uim.UimPackage#getMasterComponent_DetailPanels()
	 * @see org.opaeum.uim.DetailComponent#getMasterComponent
	 * @model opposite="masterComponent"
	 * @generated
	 */
	EList<DetailComponent> getDetailPanels();

} // MasterComponent
