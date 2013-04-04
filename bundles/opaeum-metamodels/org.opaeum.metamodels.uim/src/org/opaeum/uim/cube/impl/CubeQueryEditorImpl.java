/**
 */
package org.opaeum.uim.cube.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.IgnoredElement;
import org.opaeum.uim.LabelContainer;
import org.opaeum.uim.LabeledElement;
import org.opaeum.uim.Labels;
import org.opaeum.uim.Page;
import org.opaeum.uim.PageOrdering;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.UserInterfaceRoot;
import org.opaeum.uim.constraint.RootUserInteractionConstraint;
import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.CubeQuery;
import org.opaeum.uim.cube.CubeQueryEditor;
import org.opaeum.uim.impl.UmlReferenceImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query Editor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryEditorImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryEditorImpl#isUnderUserControl <em>Under User Control</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryEditorImpl#getLabelOverride <em>Label Override</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryEditorImpl#getEditability <em>Editability</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryEditorImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryEditorImpl#getIgnoredElements <em>Ignored Elements</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryEditorImpl#getSuperUserInterfaces <em>Super User Interfaces</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryEditorImpl#getPageOrdering <em>Page Ordering</em>}</li>
 *   <li>{@link org.opaeum.uim.cube.impl.CubeQueryEditorImpl#getQueries <em>Queries</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CubeQueryEditorImpl extends UmlReferenceImpl implements CubeQueryEditor {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #isUnderUserControl() <em>Under User Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnderUserControl()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNDER_USER_CONTROL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnderUserControl() <em>Under User Control</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnderUserControl()
	 * @generated
	 * @ordered
	 */
	protected boolean underUserControl = UNDER_USER_CONTROL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getLabelOverride() <em>Label Override</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelOverride()
	 * @generated
	 * @ordered
	 */
	protected Labels labelOverride;

	/**
	 * The cached value of the '{@link #getEditability() <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditability()
	 * @generated
	 * @ordered
	 */
	protected RootUserInteractionConstraint editability;

	/**
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected RootUserInteractionConstraint visibility;

	/**
	 * The cached value of the '{@link #getIgnoredElements() <em>Ignored Elements</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIgnoredElements()
	 * @generated
	 * @ordered
	 */
	protected EList<IgnoredElement> ignoredElements;

	/**
	 * The cached value of the '{@link #getSuperUserInterfaces() <em>Super User Interfaces</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperUserInterfaces()
	 * @generated
	 * @ordered
	 */
	protected EList<UserInterfaceRoot> superUserInterfaces;

	/**
	 * The cached value of the '{@link #getPageOrdering() <em>Page Ordering</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageOrdering()
	 * @generated
	 * @ordered
	 */
	protected EList<PageOrdering> pageOrdering;

	/**
	 * The cached value of the '{@link #getQueries() <em>Queries</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueries()
	 * @generated
	 * @ordered
	 */
	protected EList<CubeQuery> queries;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CubeQueryEditorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CubePackage.Literals.CUBE_QUERY_EDITOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CubePackage.CUBE_QUERY_EDITOR__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnderUserControl() {
		return underUserControl;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnderUserControl(boolean newUnderUserControl) {
		boolean oldUnderUserControl = underUserControl;
		underUserControl = newUnderUserControl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CubePackage.CUBE_QUERY_EDITOR__UNDER_USER_CONTROL, oldUnderUserControl, underUserControl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Labels getLabelOverride() {
		return labelOverride;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabelOverride(Labels newLabelOverride, NotificationChain msgs) {
		Labels oldLabelOverride = labelOverride;
		labelOverride = newLabelOverride;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CubePackage.CUBE_QUERY_EDITOR__LABEL_OVERRIDE, oldLabelOverride, newLabelOverride);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelOverride(Labels newLabelOverride) {
		if (newLabelOverride != labelOverride) {
			NotificationChain msgs = null;
			if (labelOverride != null)
				msgs = ((InternalEObject)labelOverride).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CubePackage.CUBE_QUERY_EDITOR__LABEL_OVERRIDE, null, msgs);
			if (newLabelOverride != null)
				msgs = ((InternalEObject)newLabelOverride).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CubePackage.CUBE_QUERY_EDITOR__LABEL_OVERRIDE, null, msgs);
			msgs = basicSetLabelOverride(newLabelOverride, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CubePackage.CUBE_QUERY_EDITOR__LABEL_OVERRIDE, newLabelOverride, newLabelOverride));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RootUserInteractionConstraint getEditability() {
		return editability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditability(RootUserInteractionConstraint newEditability, NotificationChain msgs) {
		RootUserInteractionConstraint oldEditability = editability;
		editability = newEditability;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CubePackage.CUBE_QUERY_EDITOR__EDITABILITY, oldEditability, newEditability);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditability(RootUserInteractionConstraint newEditability) {
		if (newEditability != editability) {
			NotificationChain msgs = null;
			if (editability != null)
				msgs = ((InternalEObject)editability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CubePackage.CUBE_QUERY_EDITOR__EDITABILITY, null, msgs);
			if (newEditability != null)
				msgs = ((InternalEObject)newEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CubePackage.CUBE_QUERY_EDITOR__EDITABILITY, null, msgs);
			msgs = basicSetEditability(newEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CubePackage.CUBE_QUERY_EDITOR__EDITABILITY, newEditability, newEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RootUserInteractionConstraint getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVisibility(RootUserInteractionConstraint newVisibility, NotificationChain msgs) {
		RootUserInteractionConstraint oldVisibility = visibility;
		visibility = newVisibility;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CubePackage.CUBE_QUERY_EDITOR__VISIBILITY, oldVisibility, newVisibility);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(RootUserInteractionConstraint newVisibility) {
		if (newVisibility != visibility) {
			NotificationChain msgs = null;
			if (visibility != null)
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CubePackage.CUBE_QUERY_EDITOR__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CubePackage.CUBE_QUERY_EDITOR__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CubePackage.CUBE_QUERY_EDITOR__VISIBILITY, newVisibility, newVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IgnoredElement> getIgnoredElements() {
		if (ignoredElements == null) {
			ignoredElements = new EObjectContainmentWithInverseEList<IgnoredElement>(IgnoredElement.class, this, CubePackage.CUBE_QUERY_EDITOR__IGNORED_ELEMENTS, UimPackage.IGNORED_ELEMENT__USER_INTERFACE_ROOT);
		}
		return ignoredElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UserInterfaceRoot> getSuperUserInterfaces() {
		if (superUserInterfaces == null) {
			superUserInterfaces = new EObjectResolvingEList<UserInterfaceRoot>(UserInterfaceRoot.class, this, CubePackage.CUBE_QUERY_EDITOR__SUPER_USER_INTERFACES);
		}
		return superUserInterfaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PageOrdering> getPageOrdering() {
		if (pageOrdering == null) {
			pageOrdering = new EObjectContainmentEList<PageOrdering>(PageOrdering.class, this, CubePackage.CUBE_QUERY_EDITOR__PAGE_ORDERING);
		}
		return pageOrdering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generate
	 */
	public String getUmlElementUid() {
		return umlElementUid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CubeQuery> getQueries() {
		if (queries == null) {
			queries = new EObjectContainmentEList<CubeQuery>(CubeQuery.class, this, CubePackage.CUBE_QUERY_EDITOR__QUERIES);
		}
		return queries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Page> getPages() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CubePackage.CUBE_QUERY_EDITOR__IGNORED_ELEMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIgnoredElements()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CubePackage.CUBE_QUERY_EDITOR__LABEL_OVERRIDE:
				return basicSetLabelOverride(null, msgs);
			case CubePackage.CUBE_QUERY_EDITOR__EDITABILITY:
				return basicSetEditability(null, msgs);
			case CubePackage.CUBE_QUERY_EDITOR__VISIBILITY:
				return basicSetVisibility(null, msgs);
			case CubePackage.CUBE_QUERY_EDITOR__IGNORED_ELEMENTS:
				return ((InternalEList<?>)getIgnoredElements()).basicRemove(otherEnd, msgs);
			case CubePackage.CUBE_QUERY_EDITOR__PAGE_ORDERING:
				return ((InternalEList<?>)getPageOrdering()).basicRemove(otherEnd, msgs);
			case CubePackage.CUBE_QUERY_EDITOR__QUERIES:
				return ((InternalEList<?>)getQueries()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CubePackage.CUBE_QUERY_EDITOR__NAME:
				return getName();
			case CubePackage.CUBE_QUERY_EDITOR__UNDER_USER_CONTROL:
				return isUnderUserControl();
			case CubePackage.CUBE_QUERY_EDITOR__LABEL_OVERRIDE:
				return getLabelOverride();
			case CubePackage.CUBE_QUERY_EDITOR__EDITABILITY:
				return getEditability();
			case CubePackage.CUBE_QUERY_EDITOR__VISIBILITY:
				return getVisibility();
			case CubePackage.CUBE_QUERY_EDITOR__IGNORED_ELEMENTS:
				return getIgnoredElements();
			case CubePackage.CUBE_QUERY_EDITOR__SUPER_USER_INTERFACES:
				return getSuperUserInterfaces();
			case CubePackage.CUBE_QUERY_EDITOR__PAGE_ORDERING:
				return getPageOrdering();
			case CubePackage.CUBE_QUERY_EDITOR__QUERIES:
				return getQueries();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CubePackage.CUBE_QUERY_EDITOR__NAME:
				setName((String)newValue);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__UNDER_USER_CONTROL:
				setUnderUserControl((Boolean)newValue);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__LABEL_OVERRIDE:
				setLabelOverride((Labels)newValue);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__EDITABILITY:
				setEditability((RootUserInteractionConstraint)newValue);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__VISIBILITY:
				setVisibility((RootUserInteractionConstraint)newValue);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__IGNORED_ELEMENTS:
				getIgnoredElements().clear();
				getIgnoredElements().addAll((Collection<? extends IgnoredElement>)newValue);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__SUPER_USER_INTERFACES:
				getSuperUserInterfaces().clear();
				getSuperUserInterfaces().addAll((Collection<? extends UserInterfaceRoot>)newValue);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__PAGE_ORDERING:
				getPageOrdering().clear();
				getPageOrdering().addAll((Collection<? extends PageOrdering>)newValue);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__QUERIES:
				getQueries().clear();
				getQueries().addAll((Collection<? extends CubeQuery>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CubePackage.CUBE_QUERY_EDITOR__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__UNDER_USER_CONTROL:
				setUnderUserControl(UNDER_USER_CONTROL_EDEFAULT);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__LABEL_OVERRIDE:
				setLabelOverride((Labels)null);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__EDITABILITY:
				setEditability((RootUserInteractionConstraint)null);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__VISIBILITY:
				setVisibility((RootUserInteractionConstraint)null);
				return;
			case CubePackage.CUBE_QUERY_EDITOR__IGNORED_ELEMENTS:
				getIgnoredElements().clear();
				return;
			case CubePackage.CUBE_QUERY_EDITOR__SUPER_USER_INTERFACES:
				getSuperUserInterfaces().clear();
				return;
			case CubePackage.CUBE_QUERY_EDITOR__PAGE_ORDERING:
				getPageOrdering().clear();
				return;
			case CubePackage.CUBE_QUERY_EDITOR__QUERIES:
				getQueries().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CubePackage.CUBE_QUERY_EDITOR__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CubePackage.CUBE_QUERY_EDITOR__UNDER_USER_CONTROL:
				return underUserControl != UNDER_USER_CONTROL_EDEFAULT;
			case CubePackage.CUBE_QUERY_EDITOR__LABEL_OVERRIDE:
				return labelOverride != null;
			case CubePackage.CUBE_QUERY_EDITOR__EDITABILITY:
				return editability != null;
			case CubePackage.CUBE_QUERY_EDITOR__VISIBILITY:
				return visibility != null;
			case CubePackage.CUBE_QUERY_EDITOR__IGNORED_ELEMENTS:
				return ignoredElements != null && !ignoredElements.isEmpty();
			case CubePackage.CUBE_QUERY_EDITOR__SUPER_USER_INTERFACES:
				return superUserInterfaces != null && !superUserInterfaces.isEmpty();
			case CubePackage.CUBE_QUERY_EDITOR__PAGE_ORDERING:
				return pageOrdering != null && !pageOrdering.isEmpty();
			case CubePackage.CUBE_QUERY_EDITOR__QUERIES:
				return queries != null && !queries.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == UserInteractionElement.class) {
			switch (derivedFeatureID) {
				case CubePackage.CUBE_QUERY_EDITOR__NAME: return UimPackage.USER_INTERACTION_ELEMENT__NAME;
				case CubePackage.CUBE_QUERY_EDITOR__UNDER_USER_CONTROL: return UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL;
				default: return -1;
			}
		}
		if (baseClass == LabelContainer.class) {
			switch (derivedFeatureID) {
				case CubePackage.CUBE_QUERY_EDITOR__LABEL_OVERRIDE: return UimPackage.LABEL_CONTAINER__LABEL_OVERRIDE;
				default: return -1;
			}
		}
		if (baseClass == LabeledElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == UserInterfaceRoot.class) {
			switch (derivedFeatureID) {
				case CubePackage.CUBE_QUERY_EDITOR__EDITABILITY: return UimPackage.USER_INTERFACE_ROOT__EDITABILITY;
				case CubePackage.CUBE_QUERY_EDITOR__VISIBILITY: return UimPackage.USER_INTERFACE_ROOT__VISIBILITY;
				case CubePackage.CUBE_QUERY_EDITOR__IGNORED_ELEMENTS: return UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS;
				case CubePackage.CUBE_QUERY_EDITOR__SUPER_USER_INTERFACES: return UimPackage.USER_INTERFACE_ROOT__SUPER_USER_INTERFACES;
				case CubePackage.CUBE_QUERY_EDITOR__PAGE_ORDERING: return UimPackage.USER_INTERFACE_ROOT__PAGE_ORDERING;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == UserInteractionElement.class) {
			switch (baseFeatureID) {
				case UimPackage.USER_INTERACTION_ELEMENT__NAME: return CubePackage.CUBE_QUERY_EDITOR__NAME;
				case UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL: return CubePackage.CUBE_QUERY_EDITOR__UNDER_USER_CONTROL;
				default: return -1;
			}
		}
		if (baseClass == LabelContainer.class) {
			switch (baseFeatureID) {
				case UimPackage.LABEL_CONTAINER__LABEL_OVERRIDE: return CubePackage.CUBE_QUERY_EDITOR__LABEL_OVERRIDE;
				default: return -1;
			}
		}
		if (baseClass == LabeledElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == UserInterfaceRoot.class) {
			switch (baseFeatureID) {
				case UimPackage.USER_INTERFACE_ROOT__EDITABILITY: return CubePackage.CUBE_QUERY_EDITOR__EDITABILITY;
				case UimPackage.USER_INTERFACE_ROOT__VISIBILITY: return CubePackage.CUBE_QUERY_EDITOR__VISIBILITY;
				case UimPackage.USER_INTERFACE_ROOT__IGNORED_ELEMENTS: return CubePackage.CUBE_QUERY_EDITOR__IGNORED_ELEMENTS;
				case UimPackage.USER_INTERFACE_ROOT__SUPER_USER_INTERFACES: return CubePackage.CUBE_QUERY_EDITOR__SUPER_USER_INTERFACES;
				case UimPackage.USER_INTERFACE_ROOT__PAGE_ORDERING: return CubePackage.CUBE_QUERY_EDITOR__PAGE_ORDERING;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", underUserControl: ");
		result.append(underUserControl);
		result.append(')');
		return result.toString();
	}

} //CubeQueryEditorImpl
