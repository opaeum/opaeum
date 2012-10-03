/**
 */
package org.opaeum.uim.model;

import org.opaeum.uim.editor.AbstractEditor;
import org.opaeum.uim.editor.QueryResultPage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Query Invoker</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.opaeum.uim.model.QueryInvoker#getResultPage <em>Result Page</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.opaeum.uim.model.ModelPackage#getQueryInvoker()
 * @model
 * @generated
 */
public interface QueryInvoker extends AbstractEditor, AbstractUserInteractionModel {
	/**
	 * Returns the value of the '<em><b>Result Page</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Page</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Page</em>' containment reference.
	 * @see #setResultPage(QueryResultPage)
	 * @see org.opaeum.uim.model.ModelPackage#getQueryInvoker_ResultPage()
	 * @model containment="true"
	 * @generated
	 */
	QueryResultPage getResultPage();

	/**
	 * Sets the value of the '{@link org.opaeum.uim.model.QueryInvoker#getResultPage <em>Result Page</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Page</em>' containment reference.
	 * @see #getResultPage()
	 * @generated
	 */
	void setResultPage(QueryResultPage value);

} // QueryInvoker
