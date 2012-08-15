/**
 */
package org.opaeum.uim.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.opaeum.uim.UimComponent;
import org.opaeum.uim.UimContainer;
import org.opaeum.uim.UimDataTable;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.action.UimAction;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.TableBinding;
import org.opaeum.uim.constraint.ConstrainedObject;
import org.opaeum.uim.constraint.ConstraintPackage;
import org.opaeum.uim.constraint.EditableConstrainedObject;
import org.opaeum.uim.constraint.UserInteractionConstraint;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.panel.PanelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Table</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#isUnderUserControl <em>Under User Control</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getVisibility <em>Visibility</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getEditability <em>Editability</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getPreferredWidth <em>Preferred Width</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getPreferredHeight <em>Preferred Height</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getFillHorizontally <em>Fill Horizontally</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getFillVertically <em>Fill Vertically</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.opaeum.uim.impl.UimDataTableImpl#getActionsOnMultipleSelection <em>Actions On Multiple Selection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimDataTableImpl extends MasterComponentImpl implements UimDataTable {
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
	 * The cached value of the '{@link #getVisibility() <em>Visibility</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVisibility()
	 * @generated
	 * @ordered
	 */
	protected UserInteractionConstraint visibility;

	/**
	 * The cached value of the '{@link #getEditability() <em>Editability</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditability()
	 * @generated
	 * @ordered
	 */
	protected UserInteractionConstraint editability;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<UimComponent> children;

	/**
	 * The default value of the '{@link #getPreferredWidth() <em>Preferred Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredWidth()
	 * @generated
	 * @ordered
	 */
	protected static final Integer PREFERRED_WIDTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPreferredWidth() <em>Preferred Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredWidth()
	 * @generated
	 * @ordered
	 */
	protected Integer preferredWidth = PREFERRED_WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreferredHeight() <em>Preferred Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredHeight()
	 * @generated
	 * @ordered
	 */
	protected static final Integer PREFERRED_HEIGHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPreferredHeight() <em>Preferred Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreferredHeight()
	 * @generated
	 * @ordered
	 */
	protected Integer preferredHeight = PREFERRED_HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getFillHorizontally() <em>Fill Horizontally</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillHorizontally()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FILL_HORIZONTALLY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFillHorizontally() <em>Fill Horizontally</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillHorizontally()
	 * @generated
	 * @ordered
	 */
	protected Boolean fillHorizontally = FILL_HORIZONTALLY_EDEFAULT;

	/**
	 * The default value of the '{@link #getFillVertically() <em>Fill Vertically</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillVertically()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FILL_VERTICALLY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFillVertically() <em>Fill Vertically</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFillVertically()
	 * @generated
	 * @ordered
	 */
	protected Boolean fillVertically = FILL_VERTICALLY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected TableBinding binding;

	/**
	 * The cached value of the '{@link #getActionsOnMultipleSelection() <em>Actions On Multiple Selection</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActionsOnMultipleSelection()
	 * @generated
	 * @ordered
	 */
	protected EList<UimAction> actionsOnMultipleSelection;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UimDataTableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UimPackage.Literals.UIM_DATA_TABLE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__UNDER_USER_CONTROL, oldUnderUserControl, underUserControl));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInteractionConstraint getVisibility() {
		return visibility;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVisibility(UserInteractionConstraint newVisibility, NotificationChain msgs) {
		UserInteractionConstraint oldVisibility = visibility;
		visibility = newVisibility;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__VISIBILITY, oldVisibility, newVisibility);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVisibility(UserInteractionConstraint newVisibility) {
		if (newVisibility != visibility) {
			NotificationChain msgs = null;
			if (visibility != null)
				msgs = ((InternalEObject)visibility).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__VISIBILITY, null, msgs);
			if (newVisibility != null)
				msgs = ((InternalEObject)newVisibility).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__VISIBILITY, null, msgs);
			msgs = basicSetVisibility(newVisibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__VISIBILITY, newVisibility, newVisibility));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserInteractionConstraint getEditability() {
		return editability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEditability(UserInteractionConstraint newEditability, NotificationChain msgs) {
		UserInteractionConstraint oldEditability = editability;
		editability = newEditability;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__EDITABILITY, oldEditability, newEditability);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditability(UserInteractionConstraint newEditability) {
		if (newEditability != editability) {
			NotificationChain msgs = null;
			if (editability != null)
				msgs = ((InternalEObject)editability).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__EDITABILITY, null, msgs);
			if (newEditability != null)
				msgs = ((InternalEObject)newEditability).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__EDITABILITY, null, msgs);
			msgs = basicSetEditability(newEditability, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__EDITABILITY, newEditability, newEditability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UimComponent> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<UimComponent>(UimComponent.class, this, UimPackage.UIM_DATA_TABLE__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getPreferredWidth() {
		return preferredWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferredWidth(Integer newPreferredWidth) {
		Integer oldPreferredWidth = preferredWidth;
		preferredWidth = newPreferredWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__PREFERRED_WIDTH, oldPreferredWidth, preferredWidth));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getPreferredHeight() {
		return preferredHeight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferredHeight(Integer newPreferredHeight) {
		Integer oldPreferredHeight = preferredHeight;
		preferredHeight = newPreferredHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__PREFERRED_HEIGHT, oldPreferredHeight, preferredHeight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFillHorizontally() {
		return fillHorizontally;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillHorizontally(Boolean newFillHorizontally) {
		Boolean oldFillHorizontally = fillHorizontally;
		fillHorizontally = newFillHorizontally;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__FILL_HORIZONTALLY, oldFillHorizontally, fillHorizontally));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFillVertically() {
		return fillVertically;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillVertically(Boolean newFillVertically) {
		Boolean oldFillVertically = fillVertically;
		fillVertically = newFillVertically;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__FILL_VERTICALLY, oldFillVertically, fillVertically));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableBinding getBinding() {
		return binding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBinding(TableBinding newBinding, NotificationChain msgs) {
		TableBinding oldBinding = binding;
		binding = newBinding;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__BINDING, oldBinding, newBinding);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(TableBinding newBinding) {
		if (newBinding != binding) {
			NotificationChain msgs = null;
			if (binding != null)
				msgs = ((InternalEObject)binding).eInverseRemove(this, BindingPackage.TABLE_BINDING__TABLE, TableBinding.class, msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, BindingPackage.TABLE_BINDING__TABLE, TableBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UimPackage.UIM_DATA_TABLE__BINDING, newBinding, newBinding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UimAction> getActionsOnMultipleSelection() {
		if (actionsOnMultipleSelection == null) {
			actionsOnMultipleSelection = new EObjectContainmentEList<UimAction>(UimAction.class, this, UimPackage.UIM_DATA_TABLE__ACTIONS_ON_MULTIPLE_SELECTION);
		}
		return actionsOnMultipleSelection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimContainer getParent() {
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
			case UimPackage.UIM_DATA_TABLE__BINDING:
				if (binding != null)
					msgs = ((InternalEObject)binding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UimPackage.UIM_DATA_TABLE__BINDING, null, msgs);
				return basicSetBinding((TableBinding)otherEnd, msgs);
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
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				return basicSetVisibility(null, msgs);
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				return basicSetEditability(null, msgs);
			case UimPackage.UIM_DATA_TABLE__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case UimPackage.UIM_DATA_TABLE__BINDING:
				return basicSetBinding(null, msgs);
			case UimPackage.UIM_DATA_TABLE__ACTIONS_ON_MULTIPLE_SELECTION:
				return ((InternalEList<?>)getActionsOnMultipleSelection()).basicRemove(otherEnd, msgs);
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
			case UimPackage.UIM_DATA_TABLE__NAME:
				return getName();
			case UimPackage.UIM_DATA_TABLE__UNDER_USER_CONTROL:
				return isUnderUserControl();
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				return getVisibility();
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				return getEditability();
			case UimPackage.UIM_DATA_TABLE__CHILDREN:
				return getChildren();
			case UimPackage.UIM_DATA_TABLE__PREFERRED_WIDTH:
				return getPreferredWidth();
			case UimPackage.UIM_DATA_TABLE__PREFERRED_HEIGHT:
				return getPreferredHeight();
			case UimPackage.UIM_DATA_TABLE__FILL_HORIZONTALLY:
				return getFillHorizontally();
			case UimPackage.UIM_DATA_TABLE__FILL_VERTICALLY:
				return getFillVertically();
			case UimPackage.UIM_DATA_TABLE__BINDING:
				return getBinding();
			case UimPackage.UIM_DATA_TABLE__ACTIONS_ON_MULTIPLE_SELECTION:
				return getActionsOnMultipleSelection();
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
			case UimPackage.UIM_DATA_TABLE__NAME:
				setName((String)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__UNDER_USER_CONTROL:
				setUnderUserControl((Boolean)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				setVisibility((UserInteractionConstraint)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				setEditability((UserInteractionConstraint)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends UimComponent>)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__PREFERRED_WIDTH:
				setPreferredWidth((Integer)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__PREFERRED_HEIGHT:
				setPreferredHeight((Integer)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__FILL_HORIZONTALLY:
				setFillHorizontally((Boolean)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__FILL_VERTICALLY:
				setFillVertically((Boolean)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__BINDING:
				setBinding((TableBinding)newValue);
				return;
			case UimPackage.UIM_DATA_TABLE__ACTIONS_ON_MULTIPLE_SELECTION:
				getActionsOnMultipleSelection().clear();
				getActionsOnMultipleSelection().addAll((Collection<? extends UimAction>)newValue);
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
			case UimPackage.UIM_DATA_TABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case UimPackage.UIM_DATA_TABLE__UNDER_USER_CONTROL:
				setUnderUserControl(UNDER_USER_CONTROL_EDEFAULT);
				return;
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				setVisibility((UserInteractionConstraint)null);
				return;
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				setEditability((UserInteractionConstraint)null);
				return;
			case UimPackage.UIM_DATA_TABLE__CHILDREN:
				getChildren().clear();
				return;
			case UimPackage.UIM_DATA_TABLE__PREFERRED_WIDTH:
				setPreferredWidth(PREFERRED_WIDTH_EDEFAULT);
				return;
			case UimPackage.UIM_DATA_TABLE__PREFERRED_HEIGHT:
				setPreferredHeight(PREFERRED_HEIGHT_EDEFAULT);
				return;
			case UimPackage.UIM_DATA_TABLE__FILL_HORIZONTALLY:
				setFillHorizontally(FILL_HORIZONTALLY_EDEFAULT);
				return;
			case UimPackage.UIM_DATA_TABLE__FILL_VERTICALLY:
				setFillVertically(FILL_VERTICALLY_EDEFAULT);
				return;
			case UimPackage.UIM_DATA_TABLE__BINDING:
				setBinding((TableBinding)null);
				return;
			case UimPackage.UIM_DATA_TABLE__ACTIONS_ON_MULTIPLE_SELECTION:
				getActionsOnMultipleSelection().clear();
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
			case UimPackage.UIM_DATA_TABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case UimPackage.UIM_DATA_TABLE__UNDER_USER_CONTROL:
				return underUserControl != UNDER_USER_CONTROL_EDEFAULT;
			case UimPackage.UIM_DATA_TABLE__VISIBILITY:
				return visibility != null;
			case UimPackage.UIM_DATA_TABLE__EDITABILITY:
				return editability != null;
			case UimPackage.UIM_DATA_TABLE__CHILDREN:
				return children != null && !children.isEmpty();
			case UimPackage.UIM_DATA_TABLE__PREFERRED_WIDTH:
				return PREFERRED_WIDTH_EDEFAULT == null ? preferredWidth != null : !PREFERRED_WIDTH_EDEFAULT.equals(preferredWidth);
			case UimPackage.UIM_DATA_TABLE__PREFERRED_HEIGHT:
				return PREFERRED_HEIGHT_EDEFAULT == null ? preferredHeight != null : !PREFERRED_HEIGHT_EDEFAULT.equals(preferredHeight);
			case UimPackage.UIM_DATA_TABLE__FILL_HORIZONTALLY:
				return FILL_HORIZONTALLY_EDEFAULT == null ? fillHorizontally != null : !FILL_HORIZONTALLY_EDEFAULT.equals(fillHorizontally);
			case UimPackage.UIM_DATA_TABLE__FILL_VERTICALLY:
				return FILL_VERTICALLY_EDEFAULT == null ? fillVertically != null : !FILL_VERTICALLY_EDEFAULT.equals(fillVertically);
			case UimPackage.UIM_DATA_TABLE__BINDING:
				return binding != null;
			case UimPackage.UIM_DATA_TABLE__ACTIONS_ON_MULTIPLE_SELECTION:
				return actionsOnMultipleSelection != null && !actionsOnMultipleSelection.isEmpty();
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
				case UimPackage.UIM_DATA_TABLE__NAME: return UimPackage.USER_INTERACTION_ELEMENT__NAME;
				case UimPackage.UIM_DATA_TABLE__UNDER_USER_CONTROL: return UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL;
				default: return -1;
			}
		}
		if (baseClass == ConstrainedObject.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_DATA_TABLE__VISIBILITY: return ConstraintPackage.CONSTRAINED_OBJECT__VISIBILITY;
				default: return -1;
			}
		}
		if (baseClass == UimComponent.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == EditableConstrainedObject.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_DATA_TABLE__EDITABILITY: return ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT__EDITABILITY;
				default: return -1;
			}
		}
		if (baseClass == UimContainer.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_DATA_TABLE__CHILDREN: return UimPackage.UIM_CONTAINER__CHILDREN;
				default: return -1;
			}
		}
		if (baseClass == Outlayable.class) {
			switch (derivedFeatureID) {
				case UimPackage.UIM_DATA_TABLE__PREFERRED_WIDTH: return PanelPackage.OUTLAYABLE__PREFERRED_WIDTH;
				case UimPackage.UIM_DATA_TABLE__PREFERRED_HEIGHT: return PanelPackage.OUTLAYABLE__PREFERRED_HEIGHT;
				case UimPackage.UIM_DATA_TABLE__FILL_HORIZONTALLY: return PanelPackage.OUTLAYABLE__FILL_HORIZONTALLY;
				case UimPackage.UIM_DATA_TABLE__FILL_VERTICALLY: return PanelPackage.OUTLAYABLE__FILL_VERTICALLY;
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
				case UimPackage.USER_INTERACTION_ELEMENT__NAME: return UimPackage.UIM_DATA_TABLE__NAME;
				case UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL: return UimPackage.UIM_DATA_TABLE__UNDER_USER_CONTROL;
				default: return -1;
			}
		}
		if (baseClass == ConstrainedObject.class) {
			switch (baseFeatureID) {
				case ConstraintPackage.CONSTRAINED_OBJECT__VISIBILITY: return UimPackage.UIM_DATA_TABLE__VISIBILITY;
				default: return -1;
			}
		}
		if (baseClass == UimComponent.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == EditableConstrainedObject.class) {
			switch (baseFeatureID) {
				case ConstraintPackage.EDITABLE_CONSTRAINED_OBJECT__EDITABILITY: return UimPackage.UIM_DATA_TABLE__EDITABILITY;
				default: return -1;
			}
		}
		if (baseClass == UimContainer.class) {
			switch (baseFeatureID) {
				case UimPackage.UIM_CONTAINER__CHILDREN: return UimPackage.UIM_DATA_TABLE__CHILDREN;
				default: return -1;
			}
		}
		if (baseClass == Outlayable.class) {
			switch (baseFeatureID) {
				case PanelPackage.OUTLAYABLE__PREFERRED_WIDTH: return UimPackage.UIM_DATA_TABLE__PREFERRED_WIDTH;
				case PanelPackage.OUTLAYABLE__PREFERRED_HEIGHT: return UimPackage.UIM_DATA_TABLE__PREFERRED_HEIGHT;
				case PanelPackage.OUTLAYABLE__FILL_HORIZONTALLY: return UimPackage.UIM_DATA_TABLE__FILL_HORIZONTALLY;
				case PanelPackage.OUTLAYABLE__FILL_VERTICALLY: return UimPackage.UIM_DATA_TABLE__FILL_VERTICALLY;
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

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", underUserControl: ");
		result.append(underUserControl);
		result.append(", preferredWidth: ");
		result.append(preferredWidth);
		result.append(", preferredHeight: ");
		result.append(preferredHeight);
		result.append(", fillHorizontally: ");
		result.append(fillHorizontally);
		result.append(", fillVertically: ");
		result.append(fillVertically);
		result.append(')');
		return result.toString();
	}

} //UimDataTableImpl
