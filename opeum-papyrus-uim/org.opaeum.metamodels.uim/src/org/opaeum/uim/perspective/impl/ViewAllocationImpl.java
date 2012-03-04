/**
 */
package org.opaeum.uim.perspective.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PositionInPerspective;
import org.opaeum.uim.perspective.UimPerspective;
import org.opaeum.uim.perspective.ViewAllocation;
import org.opaeum.uim.perspective.ViewKind;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>View Allocation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.perspective.impl.ViewAllocationImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ViewAllocationImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ViewAllocationImpl#getPerspective <em>Perspective</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ViewAllocationImpl#getViewKind <em>View Kind</em>}</li>
 *   <li>{@link org.opaeum.uim.perspective.impl.ViewAllocationImpl#getPosition <em>Position</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViewAllocationImpl extends EObjectImpl implements ViewAllocation {
	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final Integer WIDTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected Integer width = WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected static final Integer HEIGHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected Integer height = HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #getViewKind() <em>View Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewKind()
	 * @generated
	 * @ordered
	 */
	protected static final ViewKind VIEW_KIND_EDEFAULT = ViewKind.EXPLORER;

	/**
	 * The cached value of the '{@link #getViewKind() <em>View Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViewKind()
	 * @generated
	 * @ordered
	 */
	protected ViewKind viewKind = VIEW_KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected static final PositionInPerspective POSITION_EDEFAULT = PositionInPerspective.LEFT;

	/**
	 * The cached value of the '{@link #getPosition() <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosition()
	 * @generated
	 * @ordered
	 */
	protected PositionInPerspective position = POSITION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViewAllocationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PerspectivePackage.Literals.VIEW_ALLOCATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidth(Integer newWidth) {
		Integer oldWidth = width;
		width = newWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.VIEW_ALLOCATION__WIDTH, oldWidth, width));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeight(Integer newHeight) {
		Integer oldHeight = height;
		height = newHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.VIEW_ALLOCATION__HEIGHT, oldHeight, height));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimPerspective getPerspective() {
		if (eContainerFeatureID() != PerspectivePackage.VIEW_ALLOCATION__PERSPECTIVE) return null;
		return (UimPerspective)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPerspective(UimPerspective newPerspective, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newPerspective, PerspectivePackage.VIEW_ALLOCATION__PERSPECTIVE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerspective(UimPerspective newPerspective) {
		if (newPerspective != eInternalContainer() || (eContainerFeatureID() != PerspectivePackage.VIEW_ALLOCATION__PERSPECTIVE && newPerspective != null)) {
			if (EcoreUtil.isAncestor(this, newPerspective))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPerspective != null)
				msgs = ((InternalEObject)newPerspective).eInverseAdd(this, PerspectivePackage.UIM_PERSPECTIVE__VIEW_ALLOCATIONS, UimPerspective.class, msgs);
			msgs = basicSetPerspective(newPerspective, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.VIEW_ALLOCATION__PERSPECTIVE, newPerspective, newPerspective));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewKind getViewKind() {
		return viewKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViewKind(ViewKind newViewKind) {
		ViewKind oldViewKind = viewKind;
		viewKind = newViewKind == null ? VIEW_KIND_EDEFAULT : newViewKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.VIEW_ALLOCATION__VIEW_KIND, oldViewKind, viewKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PositionInPerspective getPosition() {
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPosition(PositionInPerspective newPosition) {
		PositionInPerspective oldPosition = position;
		position = newPosition == null ? POSITION_EDEFAULT : newPosition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerspectivePackage.VIEW_ALLOCATION__POSITION, oldPosition, position));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerspectivePackage.VIEW_ALLOCATION__PERSPECTIVE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetPerspective((UimPerspective)otherEnd, msgs);
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
			case PerspectivePackage.VIEW_ALLOCATION__PERSPECTIVE:
				return basicSetPerspective(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case PerspectivePackage.VIEW_ALLOCATION__PERSPECTIVE:
				return eInternalContainer().eInverseRemove(this, PerspectivePackage.UIM_PERSPECTIVE__VIEW_ALLOCATIONS, UimPerspective.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PerspectivePackage.VIEW_ALLOCATION__WIDTH:
				return getWidth();
			case PerspectivePackage.VIEW_ALLOCATION__HEIGHT:
				return getHeight();
			case PerspectivePackage.VIEW_ALLOCATION__PERSPECTIVE:
				return getPerspective();
			case PerspectivePackage.VIEW_ALLOCATION__VIEW_KIND:
				return getViewKind();
			case PerspectivePackage.VIEW_ALLOCATION__POSITION:
				return getPosition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PerspectivePackage.VIEW_ALLOCATION__WIDTH:
				setWidth((Integer)newValue);
				return;
			case PerspectivePackage.VIEW_ALLOCATION__HEIGHT:
				setHeight((Integer)newValue);
				return;
			case PerspectivePackage.VIEW_ALLOCATION__PERSPECTIVE:
				setPerspective((UimPerspective)newValue);
				return;
			case PerspectivePackage.VIEW_ALLOCATION__VIEW_KIND:
				setViewKind((ViewKind)newValue);
				return;
			case PerspectivePackage.VIEW_ALLOCATION__POSITION:
				setPosition((PositionInPerspective)newValue);
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
			case PerspectivePackage.VIEW_ALLOCATION__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case PerspectivePackage.VIEW_ALLOCATION__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case PerspectivePackage.VIEW_ALLOCATION__PERSPECTIVE:
				setPerspective((UimPerspective)null);
				return;
			case PerspectivePackage.VIEW_ALLOCATION__VIEW_KIND:
				setViewKind(VIEW_KIND_EDEFAULT);
				return;
			case PerspectivePackage.VIEW_ALLOCATION__POSITION:
				setPosition(POSITION_EDEFAULT);
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
			case PerspectivePackage.VIEW_ALLOCATION__WIDTH:
				return WIDTH_EDEFAULT == null ? width != null : !WIDTH_EDEFAULT.equals(width);
			case PerspectivePackage.VIEW_ALLOCATION__HEIGHT:
				return HEIGHT_EDEFAULT == null ? height != null : !HEIGHT_EDEFAULT.equals(height);
			case PerspectivePackage.VIEW_ALLOCATION__PERSPECTIVE:
				return getPerspective() != null;
			case PerspectivePackage.VIEW_ALLOCATION__VIEW_KIND:
				return viewKind != VIEW_KIND_EDEFAULT;
			case PerspectivePackage.VIEW_ALLOCATION__POSITION:
				return position != POSITION_EDEFAULT;
		}
		return super.eIsSet(featureID);
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
		result.append(" (width: ");
		result.append(width);
		result.append(", height: ");
		result.append(height);
		result.append(", viewKind: ");
		result.append(viewKind);
		result.append(", position: ");
		result.append(position);
		result.append(')');
		return result.toString();
	}

} //ViewAllocationImpl
