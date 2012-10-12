package org.opaeum.uim.resources;

import org.eclipse.papyrus.infra.core.services.IServiceFactory;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;


public class UimModelSetServiceFactory implements IServiceFactory{
	private UimModelSet service;
	public void init(ServicesRegistry servicesRegistry) throws ServiceException {
	}

	public void startService() throws ServiceException {

	}

	public void disposeService() throws ServiceException {
		if(service != null)
			service.unload();
	}
	public Object createServiceInstance() {
		service = new UimModelSet();
		return service;
	}


}

