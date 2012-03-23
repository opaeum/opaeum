/**
 */
package org.opaeum.metamodels.simulation.simulation.impl;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Slot;
import org.eclipse.uml2.uml.internal.impl.InstanceSpecificationImpl;
import org.opaeum.metamodels.simulation.simulation.ActualInstance;
import org.opaeum.metamodels.simulation.simulation.SimulationPackage;
import org.opaeum.metamodels.simulation.util.NameUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actual Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ActualInstanceImpl extends InstanceSpecificationImpl implements ActualInstance {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActualInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimulationPackage.Literals.ACTUAL_INSTANCE;
	}
	public String getQualifiedName(){
		return NameUtil.getQualifiedName(this);
	}
} //ActualInstanceImpl
