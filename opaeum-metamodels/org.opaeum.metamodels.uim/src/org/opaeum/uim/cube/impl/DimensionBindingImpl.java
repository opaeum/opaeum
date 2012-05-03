/**
 */
package org.opaeum.uim.cube.impl;

import org.eclipse.emf.ecore.EClass;

import org.opaeum.uim.UmlReference;
import org.opaeum.uim.binding.PropertyRef;
import org.opaeum.uim.binding.impl.UimBindingImpl;

import org.opaeum.uim.cube.CubePackage;
import org.opaeum.uim.cube.DimensionBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dimension Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DimensionBindingImpl extends UimBindingImpl implements DimensionBinding {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DimensionBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CubePackage.Literals.DIMENSION_BINDING;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String toString() {
		if(getLastPropertyUuid()==null){
			return "";
		}else {
			StringBuilder sb  = new StringBuilder(getUmlElementUid());
			PropertyRef pr = getNext();
			while(pr!=null && pr.getUmlElementUid()!=null){
				sb.append(pr.getUmlElementUid());
				pr=pr.getNext();
			}
			return sb.toString();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public int hashCode(){
		return toString().hashCode();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean equals(Object o) {
		if(o ==null){
			return false;
		}
		return toString().equals(o.toString());
	}

} //DimensionBindingImpl
