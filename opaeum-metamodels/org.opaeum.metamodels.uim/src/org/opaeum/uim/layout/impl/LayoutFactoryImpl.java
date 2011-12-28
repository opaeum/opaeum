/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.opaeum.uim.layout.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.layout.*;
import org.opaeum.uim.layout.LayoutFactory;
import org.opaeum.uim.layout.LayoutPackage;
import org.opaeum.uim.layout.UimBorderLayout;
import org.opaeum.uim.layout.UimColumnLayout;
import org.opaeum.uim.layout.UimFullLayout;
import org.opaeum.uim.layout.UimGridLayout;
import org.opaeum.uim.layout.UimLayout;
import org.opaeum.uim.layout.UimToolbarLayout;
import org.opaeum.uim.layout.UimXYLayout;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LayoutFactoryImpl extends EFactoryImpl implements LayoutFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LayoutFactory init() {
		try {
			LayoutFactory theLayoutFactory = (LayoutFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/layout/1.0"); 
			if (theLayoutFactory != null) {
				return theLayoutFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new LayoutFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LayoutFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case LayoutPackage.UIM_COLUMN_LAYOUT: return createUimColumnLayout();
			case LayoutPackage.UIM_FULL_LAYOUT: return createUimFullLayout();
			case LayoutPackage.UIM_XY_LAYOUT: return createUimXYLayout();
			case LayoutPackage.UIM_BORDER_LAYOUT: return createUimBorderLayout();
			case LayoutPackage.UIM_TOOLBAR_LAYOUT: return createUimToolbarLayout();
			case LayoutPackage.UIM_LAYOUT: return createUimLayout();
			case LayoutPackage.UIM_GRID_LAYOUT: return createUimGridLayout();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimColumnLayout createUimColumnLayout() {
		UimColumnLayoutImpl uimColumnLayout = new UimColumnLayoutImpl();
		return uimColumnLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimFullLayout createUimFullLayout() {
		UimFullLayoutImpl uimFullLayout = new UimFullLayoutImpl();
		return uimFullLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimXYLayout createUimXYLayout() {
		UimXYLayoutImpl uimXYLayout = new UimXYLayoutImpl();
		return uimXYLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimBorderLayout createUimBorderLayout() {
		UimBorderLayoutImpl uimBorderLayout = new UimBorderLayoutImpl();
		return uimBorderLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimToolbarLayout createUimToolbarLayout() {
		UimToolbarLayoutImpl uimToolbarLayout = new UimToolbarLayoutImpl();
		return uimToolbarLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimLayout createUimLayout() {
		UimLayoutImpl uimLayout = new UimLayoutImpl();
		return uimLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimGridLayout createUimGridLayout() {
		UimGridLayoutImpl uimGridLayout = new UimGridLayoutImpl();
		return uimGridLayout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LayoutPackage getLayoutPackage() {
		return (LayoutPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static LayoutPackage getPackage() {
		return LayoutPackage.eINSTANCE;
	}

} //LayoutFactoryImpl
