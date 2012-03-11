/**
 */
package org.opaeum.uim.perspective.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.opaeum.uim.perspective.ExplorerConfiguration;
import org.opaeum.uim.perspective.HiddenClass;
import org.opaeum.uim.perspective.HiddenCompositeProperty;
import org.opaeum.uim.perspective.PerspectiveFactory;
import org.opaeum.uim.perspective.PerspectivePackage;
import org.opaeum.uim.perspective.PositionInPerspective;
import org.opaeum.uim.perspective.UimPerspective;
import org.opaeum.uim.perspective.ViewAllocation;
import org.opaeum.uim.perspective.ViewKind;
import org.opaeum.uim.perspective.VisibleNonCompositeProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PerspectiveFactoryImpl extends EFactoryImpl implements PerspectiveFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PerspectiveFactory init() {
		try {
			PerspectiveFactory thePerspectiveFactory = (PerspectiveFactory)EPackage.Registry.INSTANCE.getEFactory("http://opaeum.org/uimetamodel/perspective/1.0"); 
			if (thePerspectiveFactory != null) {
				return thePerspectiveFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PerspectiveFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerspectiveFactoryImpl() {
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
			case PerspectivePackage.UIM_PERSPECTIVE: return createUimPerspective();
			case PerspectivePackage.VIEW_ALLOCATION: return createViewAllocation();
			case PerspectivePackage.EXPLORER_CONFIGURATION: return createExplorerConfiguration();
			case PerspectivePackage.HIDDEN_CLASS: return createHiddenClass();
			case PerspectivePackage.VISIBLE_NON_COMPOSITE_PROPERTY: return createVisibleNonCompositeProperty();
			case PerspectivePackage.HIDDEN_COMPOSITE_PROPERTY: return createHiddenCompositeProperty();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case PerspectivePackage.VIEW_KIND:
				return createViewKindFromString(eDataType, initialValue);
			case PerspectivePackage.POSITION_IN_PERSPECTIVE:
				return createPositionInPerspectiveFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case PerspectivePackage.VIEW_KIND:
				return convertViewKindToString(eDataType, instanceValue);
			case PerspectivePackage.POSITION_IN_PERSPECTIVE:
				return convertPositionInPerspectiveToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UimPerspective createUimPerspective() {
		UimPerspectiveImpl uimPerspective = new UimPerspectiveImpl();
		return uimPerspective;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewAllocation createViewAllocation() {
		ViewAllocationImpl viewAllocation = new ViewAllocationImpl();
		return viewAllocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExplorerConfiguration createExplorerConfiguration() {
		ExplorerConfigurationImpl explorerConfiguration = new ExplorerConfigurationImpl();
		return explorerConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HiddenClass createHiddenClass() {
		HiddenClassImpl hiddenClass = new HiddenClassImpl();
		return hiddenClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VisibleNonCompositeProperty createVisibleNonCompositeProperty() {
		VisibleNonCompositePropertyImpl visibleNonCompositeProperty = new VisibleNonCompositePropertyImpl();
		return visibleNonCompositeProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HiddenCompositeProperty createHiddenCompositeProperty() {
		HiddenCompositePropertyImpl hiddenCompositeProperty = new HiddenCompositePropertyImpl();
		return hiddenCompositeProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViewKind createViewKindFromString(EDataType eDataType, String initialValue) {
		ViewKind result = ViewKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertViewKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PositionInPerspective createPositionInPerspectiveFromString(EDataType eDataType, String initialValue) {
		PositionInPerspective result = PositionInPerspective.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPositionInPerspectiveToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerspectivePackage getPerspectivePackage() {
		return (PerspectivePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static PerspectivePackage getPackage() {
		return PerspectivePackage.eINSTANCE;
	}

} //PerspectiveFactoryImpl
