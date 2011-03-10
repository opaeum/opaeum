package net.sf.nakeduml.jsf2generation;

import javax.faces.application.Application;

public class MockApplicationFactory extends org.jboss.seam.mock.MockApplicationFactory {

	private Application application;

	@Override
	public Application getApplication() {
		if (application == null) {
			application = new MockApplication();
		}
		return application;
	}

	@Override
	public void setApplication(Application application) {
		this.application = application;
	}
}
