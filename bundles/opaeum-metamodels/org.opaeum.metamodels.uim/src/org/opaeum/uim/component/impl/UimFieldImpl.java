/**
 */
package org.opaeum.uim.component.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.opaeum.uim.LabelContainer;
import org.opaeum.uim.Labels;
import org.opaeum.uim.UimPackage;
import org.opaeum.uim.UserInteractionElement;
import org.opaeum.uim.binding.BindingPackage;
import org.opaeum.uim.binding.FieldBinding;
import org.opaeum.uim.component.ComponentPackage;
import org.opaeum.uim.component.UimComponent;
import org.opaeum.uim.component.UimContainer;
import org.opaeum.uim.component.UimField;
import org.opaeum.uim.constraint.impl.EditableConstrainedObjectImpl;
import org.opaeum.uim.control.ControlKind;
import org.opaeum.uim.control.ControlPackage;
import org.opaeum.uim.control.UimControl;
import org.opaeum.uim.panel.Orientation;
import org.opaeum.uim.panel.Outlayable;
import org.opaeum.uim.panel.PanelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Uim Field</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.opaeum.uim.component.impl.UimFieldImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.UimFieldImpl#isUnderUserControl <em>Under User Control</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.UimFieldImpl#getLabelOverride <em>Label Override</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.UimFieldImpl#getPreferredWidth <em>Preferred Width</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.UimFieldImpl#getPreferredHeight <em>Preferred Height</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.UimFieldImpl#getFillHorizontally <em>Fill Horizontally</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.UimFieldImpl#getFillVertically <em>Fill Vertically</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.UimFieldImpl#getControl <em>Control</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.UimFieldImpl#getControlKind <em>Control Kind</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.UimFieldImpl#getMinimumLabelWidth <em>Minimum Label Width</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.UimFieldImpl#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.opaeum.uim.component.impl.UimFieldImpl#getOrientation <em>Orientation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UimFieldImpl extends EditableConstrainedObjectImpl implements UimField{
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #isUnderUserControl() <em>Under User Control</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #isUnderUserControl()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNDER_USER_CONTROL_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isUnderUserControl() <em>Under User Control</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #isUnderUserControl()
	 * @generated
	 * @ordered
	 */
	protected boolean underUserControl = UNDER_USER_CONTROL_EDEFAULT;
	/**
	 * The cached value of the '{@link #getLabelOverride() <em>Label Override</em>}' containment reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getLabelOverride()
	 * @generated
	 * @ordered
	 */
	protected Labels labelOverride;
	/**
	 * The default value of the '{@link #getPreferredWidth() <em>Preferred Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getPreferredWidth()
	 * @generated
	 * @ordered
	 */
	protected static final Integer PREFERRED_WIDTH_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getPreferredWidth() <em>Preferred Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getPreferredWidth()
	 * @generated
	 * @ordered
	 */
	protected Integer preferredWidth = PREFERRED_WIDTH_EDEFAULT;
	/**
	 * The default value of the '{@link #getPreferredHeight() <em>Preferred Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getPreferredHeight()
	 * @generated
	 * @ordered
	 */
	protected static final Integer PREFERRED_HEIGHT_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getPreferredHeight() <em>Preferred Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getPreferredHeight()
	 * @generated
	 * @ordered
	 */
	protected Integer preferredHeight = PREFERRED_HEIGHT_EDEFAULT;
	/**
	 * The default value of the '{@link #getFillHorizontally() <em>Fill Horizontally</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getFillHorizontally()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FILL_HORIZONTALLY_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getFillHorizontally() <em>Fill Horizontally</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getFillHorizontally()
	 * @generated
	 * @ordered
	 */
	protected Boolean fillHorizontally = FILL_HORIZONTALLY_EDEFAULT;
	/**
	 * The default value of the '{@link #getFillVertically() <em>Fill Vertically</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getFillVertically()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean FILL_VERTICALLY_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getFillVertically() <em>Fill Vertically</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getFillVertically()
	 * @generated
	 * @ordered
	 */
	protected Boolean fillVertically = FILL_VERTICALLY_EDEFAULT;
	/**
	 * The cached value of the '{@link #getControl() <em>Control</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getControl()
	 * @generated
	 * @ordered
	 */
	protected UimControl control;
	/**
	 * The default value of the '{@link #getControlKind() <em>Control Kind</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getControlKind()
	 * @generated
	 * @ordered
	 */
	protected static final ControlKind CONTROL_KIND_EDEFAULT = ControlKind.DATE_POPUP;
	/**
	 * The cached value of the '{@link #getControlKind() <em>Control Kind</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getControlKind()
	 * @generated
	 * @ordered
	 */
	protected ControlKind controlKind = CONTROL_KIND_EDEFAULT;
	/**
	 * The default value of the '{@link #getMinimumLabelWidth() <em>Minimum Label Width</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getMinimumLabelWidth()
	 * @generated
	 * @ordered
	 */
	protected static final Integer MINIMUM_LABEL_WIDTH_EDEFAULT = new Integer(200);
	/**
	 * The cached value of the '{@link #getMinimumLabelWidth() <em>Minimum Label Width</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getMinimumLabelWidth()
	 * @generated
	 * @ordered
	 */
	protected Integer minimumLabelWidth = MINIMUM_LABEL_WIDTH_EDEFAULT;
	/**
	 * The cached value of the '{@link #getBinding() <em>Binding</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected FieldBinding binding;
	/**
	 * The default value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOrientation()
	 * @generated
	 * @ordered
	 */
	protected static final Orientation ORIENTATION_EDEFAULT = Orientation.HORIZONTAL;
	/**
	 * The cached value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOrientation()
	 * @generated
	 * @ordered
	 */
	protected Orientation orientation = ORIENTATION_EDEFAULT;
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected UimFieldImpl(){
		super();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass(){
		return ComponentPackage.Literals.UIM_FIELD;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getName(){
		return name;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName){
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__NAME, oldName, name));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnderUserControl(){
		return underUserControl;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnderUserControl(boolean newUnderUserControl){
		boolean oldUnderUserControl = underUserControl;
		underUserControl = newUnderUserControl;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__UNDER_USER_CONTROL, oldUnderUserControl, underUserControl));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getPreferredWidth(){
		return preferredWidth;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferredWidth(Integer newPreferredWidth){
		Integer oldPreferredWidth = preferredWidth;
		preferredWidth = newPreferredWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__PREFERRED_WIDTH, oldPreferredWidth, preferredWidth));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getPreferredHeight(){
		return preferredHeight;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setPreferredHeight(Integer newPreferredHeight){
		Integer oldPreferredHeight = preferredHeight;
		preferredHeight = newPreferredHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__PREFERRED_HEIGHT, oldPreferredHeight, preferredHeight));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFillHorizontally(){
		return fillHorizontally;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillHorizontally(Boolean newFillHorizontally){
		Boolean oldFillHorizontally = fillHorizontally;
		fillHorizontally = newFillHorizontally;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__FILL_HORIZONTALLY, oldFillHorizontally, fillHorizontally));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Boolean getFillVertically(){
		return fillVertically;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setFillVertically(Boolean newFillVertically){
		Boolean oldFillVertically = fillVertically;
		fillVertically = newFillVertically;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__FILL_VERTICALLY, oldFillVertically, fillVertically));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public UimControl getControl(){
		return control;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetControl(UimControl newControl,NotificationChain msgs){
		UimControl oldControl = control;
		control = newControl;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__CONTROL, oldControl, newControl);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setControl(UimControl newControl){
		if (newControl != control) {
			NotificationChain msgs = null;
			if (control != null)
				msgs = ((InternalEObject)control).eInverseRemove(this, ControlPackage.UIM_CONTROL__FIELD, UimControl.class, msgs);
			if (newControl != null)
				msgs = ((InternalEObject)newControl).eInverseAdd(this, ControlPackage.UIM_CONTROL__FIELD, UimControl.class, msgs);
			msgs = basicSetControl(newControl, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__CONTROL, newControl, newControl));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ControlKind getControlKind(){
		return controlKind;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setControlKind(ControlKind newControlKind){
		ControlKind oldControlKind = controlKind;
		controlKind = newControlKind == null ? CONTROL_KIND_EDEFAULT : newControlKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__CONTROL_KIND, oldControlKind, controlKind));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getMinimumLabelWidth(){
		return minimumLabelWidth;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setMinimumLabelWidth(Integer newMinimumLabelWidth){
		Integer oldMinimumLabelWidth = minimumLabelWidth;
		minimumLabelWidth = newMinimumLabelWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__MINIMUM_LABEL_WIDTH, oldMinimumLabelWidth, minimumLabelWidth));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FieldBinding getBinding(){
		return binding;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBinding(FieldBinding newBinding,NotificationChain msgs){
		FieldBinding oldBinding = binding;
		binding = newBinding;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__BINDING, oldBinding, newBinding);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setBinding(FieldBinding newBinding){
		if (newBinding != binding) {
			NotificationChain msgs = null;
			if (binding != null)
				msgs = ((InternalEObject)binding).eInverseRemove(this, BindingPackage.FIELD_BINDING__FIELD, FieldBinding.class, msgs);
			if (newBinding != null)
				msgs = ((InternalEObject)newBinding).eInverseAdd(this, BindingPackage.FIELD_BINDING__FIELD, FieldBinding.class, msgs);
			msgs = basicSetBinding(newBinding, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__BINDING, newBinding, newBinding));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Orientation getOrientation(){
		return orientation;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrientation(Orientation newOrientation){
		Orientation oldOrientation = orientation;
		orientation = newOrientation == null ? ORIENTATION_EDEFAULT : newOrientation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__ORIENTATION, oldOrientation, orientation));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Labels getLabelOverride(){
		return labelOverride;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabelOverride(Labels newLabelOverride,NotificationChain msgs){
		Labels oldLabelOverride = labelOverride;
		labelOverride = newLabelOverride;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__LABEL_OVERRIDE, oldLabelOverride, newLabelOverride);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabelOverride(Labels newLabelOverride){
		if (newLabelOverride != labelOverride) {
			NotificationChain msgs = null;
			if (labelOverride != null)
				msgs = ((InternalEObject)labelOverride).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.UIM_FIELD__LABEL_OVERRIDE, null, msgs);
			if (newLabelOverride != null)
				msgs = ((InternalEObject)newLabelOverride).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.UIM_FIELD__LABEL_OVERRIDE, null, msgs);
			msgs = basicSetLabelOverride(newLabelOverride, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ComponentPackage.UIM_FIELD__LABEL_OVERRIDE, newLabelOverride, newLabelOverride));
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public UimContainer getParent(){
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case ComponentPackage.UIM_FIELD__CONTROL:
				if (control != null)
					msgs = ((InternalEObject)control).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.UIM_FIELD__CONTROL, null, msgs);
				return basicSetControl((UimControl)otherEnd, msgs);
			case ComponentPackage.UIM_FIELD__BINDING:
				if (binding != null)
					msgs = ((InternalEObject)binding).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ComponentPackage.UIM_FIELD__BINDING, null, msgs);
				return basicSetBinding((FieldBinding)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,int featureID,NotificationChain msgs){
		switch (featureID) {
			case ComponentPackage.UIM_FIELD__LABEL_OVERRIDE:
				return basicSetLabelOverride(null, msgs);
			case ComponentPackage.UIM_FIELD__CONTROL:
				return basicSetControl(null, msgs);
			case ComponentPackage.UIM_FIELD__BINDING:
				return basicSetBinding(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID,boolean resolve,boolean coreType){
		switch (featureID) {
			case ComponentPackage.UIM_FIELD__NAME:
				return getName();
			case ComponentPackage.UIM_FIELD__UNDER_USER_CONTROL:
				return isUnderUserControl();
			case ComponentPackage.UIM_FIELD__LABEL_OVERRIDE:
				return getLabelOverride();
			case ComponentPackage.UIM_FIELD__PREFERRED_WIDTH:
				return getPreferredWidth();
			case ComponentPackage.UIM_FIELD__PREFERRED_HEIGHT:
				return getPreferredHeight();
			case ComponentPackage.UIM_FIELD__FILL_HORIZONTALLY:
				return getFillHorizontally();
			case ComponentPackage.UIM_FIELD__FILL_VERTICALLY:
				return getFillVertically();
			case ComponentPackage.UIM_FIELD__CONTROL:
				return getControl();
			case ComponentPackage.UIM_FIELD__CONTROL_KIND:
				return getControlKind();
			case ComponentPackage.UIM_FIELD__MINIMUM_LABEL_WIDTH:
				return getMinimumLabelWidth();
			case ComponentPackage.UIM_FIELD__BINDING:
				return getBinding();
			case ComponentPackage.UIM_FIELD__ORIENTATION:
				return getOrientation();
		}
		return super.eGet(featureID, resolve, coreType);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID,Object newValue){
		switch (featureID) {
			case ComponentPackage.UIM_FIELD__NAME:
				setName((String)newValue);
				return;
			case ComponentPackage.UIM_FIELD__UNDER_USER_CONTROL:
				setUnderUserControl((Boolean)newValue);
				return;
			case ComponentPackage.UIM_FIELD__LABEL_OVERRIDE:
				setLabelOverride((Labels)newValue);
				return;
			case ComponentPackage.UIM_FIELD__PREFERRED_WIDTH:
				setPreferredWidth((Integer)newValue);
				return;
			case ComponentPackage.UIM_FIELD__PREFERRED_HEIGHT:
				setPreferredHeight((Integer)newValue);
				return;
			case ComponentPackage.UIM_FIELD__FILL_HORIZONTALLY:
				setFillHorizontally((Boolean)newValue);
				return;
			case ComponentPackage.UIM_FIELD__FILL_VERTICALLY:
				setFillVertically((Boolean)newValue);
				return;
			case ComponentPackage.UIM_FIELD__CONTROL:
				setControl((UimControl)newValue);
				return;
			case ComponentPackage.UIM_FIELD__CONTROL_KIND:
				setControlKind((ControlKind)newValue);
				return;
			case ComponentPackage.UIM_FIELD__MINIMUM_LABEL_WIDTH:
				setMinimumLabelWidth((Integer)newValue);
				return;
			case ComponentPackage.UIM_FIELD__BINDING:
				setBinding((FieldBinding)newValue);
				return;
			case ComponentPackage.UIM_FIELD__ORIENTATION:
				setOrientation((Orientation)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID){
		switch (featureID) {
			case ComponentPackage.UIM_FIELD__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ComponentPackage.UIM_FIELD__UNDER_USER_CONTROL:
				setUnderUserControl(UNDER_USER_CONTROL_EDEFAULT);
				return;
			case ComponentPackage.UIM_FIELD__LABEL_OVERRIDE:
				setLabelOverride((Labels)null);
				return;
			case ComponentPackage.UIM_FIELD__PREFERRED_WIDTH:
				setPreferredWidth(PREFERRED_WIDTH_EDEFAULT);
				return;
			case ComponentPackage.UIM_FIELD__PREFERRED_HEIGHT:
				setPreferredHeight(PREFERRED_HEIGHT_EDEFAULT);
				return;
			case ComponentPackage.UIM_FIELD__FILL_HORIZONTALLY:
				setFillHorizontally(FILL_HORIZONTALLY_EDEFAULT);
				return;
			case ComponentPackage.UIM_FIELD__FILL_VERTICALLY:
				setFillVertically(FILL_VERTICALLY_EDEFAULT);
				return;
			case ComponentPackage.UIM_FIELD__CONTROL:
				setControl((UimControl)null);
				return;
			case ComponentPackage.UIM_FIELD__CONTROL_KIND:
				setControlKind(CONTROL_KIND_EDEFAULT);
				return;
			case ComponentPackage.UIM_FIELD__MINIMUM_LABEL_WIDTH:
				setMinimumLabelWidth(MINIMUM_LABEL_WIDTH_EDEFAULT);
				return;
			case ComponentPackage.UIM_FIELD__BINDING:
				setBinding((FieldBinding)null);
				return;
			case ComponentPackage.UIM_FIELD__ORIENTATION:
				setOrientation(ORIENTATION_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID){
		switch (featureID) {
			case ComponentPackage.UIM_FIELD__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ComponentPackage.UIM_FIELD__UNDER_USER_CONTROL:
				return underUserControl != UNDER_USER_CONTROL_EDEFAULT;
			case ComponentPackage.UIM_FIELD__LABEL_OVERRIDE:
				return labelOverride != null;
			case ComponentPackage.UIM_FIELD__PREFERRED_WIDTH:
				return PREFERRED_WIDTH_EDEFAULT == null ? preferredWidth != null : !PREFERRED_WIDTH_EDEFAULT.equals(preferredWidth);
			case ComponentPackage.UIM_FIELD__PREFERRED_HEIGHT:
				return PREFERRED_HEIGHT_EDEFAULT == null ? preferredHeight != null : !PREFERRED_HEIGHT_EDEFAULT.equals(preferredHeight);
			case ComponentPackage.UIM_FIELD__FILL_HORIZONTALLY:
				return FILL_HORIZONTALLY_EDEFAULT == null ? fillHorizontally != null : !FILL_HORIZONTALLY_EDEFAULT.equals(fillHorizontally);
			case ComponentPackage.UIM_FIELD__FILL_VERTICALLY:
				return FILL_VERTICALLY_EDEFAULT == null ? fillVertically != null : !FILL_VERTICALLY_EDEFAULT.equals(fillVertically);
			case ComponentPackage.UIM_FIELD__CONTROL:
				return control != null;
			case ComponentPackage.UIM_FIELD__CONTROL_KIND:
				return controlKind != CONTROL_KIND_EDEFAULT;
			case ComponentPackage.UIM_FIELD__MINIMUM_LABEL_WIDTH:
				return MINIMUM_LABEL_WIDTH_EDEFAULT == null ? minimumLabelWidth != null : !MINIMUM_LABEL_WIDTH_EDEFAULT.equals(minimumLabelWidth);
			case ComponentPackage.UIM_FIELD__BINDING:
				return binding != null;
			case ComponentPackage.UIM_FIELD__ORIENTATION:
				return orientation != ORIENTATION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID,Class<?> baseClass){
		if (baseClass == UserInteractionElement.class) {
			switch (derivedFeatureID) {
				case ComponentPackage.UIM_FIELD__NAME: return UimPackage.USER_INTERACTION_ELEMENT__NAME;
				case ComponentPackage.UIM_FIELD__UNDER_USER_CONTROL: return UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL;
				default: return -1;
			}
		}
		if (baseClass == LabelContainer.class) {
			switch (derivedFeatureID) {
				case ComponentPackage.UIM_FIELD__LABEL_OVERRIDE: return UimPackage.LABEL_CONTAINER__LABEL_OVERRIDE;
				default: return -1;
			}
		}
		if (baseClass == UimComponent.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Outlayable.class) {
			switch (derivedFeatureID) {
				case ComponentPackage.UIM_FIELD__PREFERRED_WIDTH: return PanelPackage.OUTLAYABLE__PREFERRED_WIDTH;
				case ComponentPackage.UIM_FIELD__PREFERRED_HEIGHT: return PanelPackage.OUTLAYABLE__PREFERRED_HEIGHT;
				case ComponentPackage.UIM_FIELD__FILL_HORIZONTALLY: return PanelPackage.OUTLAYABLE__FILL_HORIZONTALLY;
				case ComponentPackage.UIM_FIELD__FILL_VERTICALLY: return PanelPackage.OUTLAYABLE__FILL_VERTICALLY;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID,Class<?> baseClass){
		if (baseClass == UserInteractionElement.class) {
			switch (baseFeatureID) {
				case UimPackage.USER_INTERACTION_ELEMENT__NAME: return ComponentPackage.UIM_FIELD__NAME;
				case UimPackage.USER_INTERACTION_ELEMENT__UNDER_USER_CONTROL: return ComponentPackage.UIM_FIELD__UNDER_USER_CONTROL;
				default: return -1;
			}
		}
		if (baseClass == LabelContainer.class) {
			switch (baseFeatureID) {
				case UimPackage.LABEL_CONTAINER__LABEL_OVERRIDE: return ComponentPackage.UIM_FIELD__LABEL_OVERRIDE;
				default: return -1;
			}
		}
		if (baseClass == UimComponent.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == Outlayable.class) {
			switch (baseFeatureID) {
				case PanelPackage.OUTLAYABLE__PREFERRED_WIDTH: return ComponentPackage.UIM_FIELD__PREFERRED_WIDTH;
				case PanelPackage.OUTLAYABLE__PREFERRED_HEIGHT: return ComponentPackage.UIM_FIELD__PREFERRED_HEIGHT;
				case PanelPackage.OUTLAYABLE__FILL_HORIZONTALLY: return ComponentPackage.UIM_FIELD__FILL_HORIZONTALLY;
				case PanelPackage.OUTLAYABLE__FILL_VERTICALLY: return ComponentPackage.UIM_FIELD__FILL_VERTICALLY;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString(){
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
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
		result.append(", controlKind: ");
		result.append(controlKind);
		result.append(", minimumLabelWidth: ");
		result.append(minimumLabelWidth);
		result.append(", orientation: ");
		result.append(orientation);
		result.append(')');
		return result.toString();
	}
} // UimFieldImpl
