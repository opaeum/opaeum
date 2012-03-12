/**
 */
package org.opaeum.uim;

import java.util.List;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page Container</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.opaeum.uim.UimPackage#getPageContainer()
 * @model
 * @generated
 */
public interface PageContainer extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated NOT
	 */

	List<? extends Page> getPages();
} // PageContainer
