/**
 */
package org.opaeum.uim.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see org.opaeum.uim.util.UimResourceFactoryImpl
 * @generated NOT
 */
public class UimResourceImpl extends XMIResourceImpl {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public UimResourceImpl(URI uri) {
		super(uri);
	}
	@Override
	public void save(Map<?,?> options) throws IOException{
    OutputStream outputStream = getURIConverter().createOutputStream(getURI(), options);
    doSave(outputStream, options);
    super.isModified=false;
	}

} //UimResourceImpl
