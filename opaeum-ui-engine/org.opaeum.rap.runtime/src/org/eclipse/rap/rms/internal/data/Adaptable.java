// Created on 26.09.2007
package org.eclipse.rap.rms.internal.data;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.opaeum.runtime.domain.IPersistentObject;

abstract class Adaptable implements IPersistentObject, IAdaptable {
	public int getObjectVersion() {
		return 0;
	}

	public void setId(Long id) {
	}

	public String getUid() {
		return getClass().getName() + getId();
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(final Class adapter) {
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}
}
