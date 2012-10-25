package org.opaeum.jbpmtestintegration.test;

import java.io.IOException;

import javax.inject.Inject;

import jbpm.jbpm.dispatch.SimpleAsyncShipping;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opaeum.jbpmtestintegration.util.NakedUmlTestUtil;
import org.opaeum.test.adaptor.ArtifactNames;
import org.opaeum.test.adaptor.MavenArtifactResolver;

//@RunWith(Arquillian.class)
public class SimpleAsyncWebDebug {
//	@Inject
	private SimpleAsyncShippingController processController;

//	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		WebArchive war = (WebArchive) NakedUmlTestUtil.createTestArchive();
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.SEAM_SERVLET_API));
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.SEAM_SERVLET_IMPL));
		war.addClass(org.opaeum.jbpmtestintegration.util.jbpm.adaptor.JbpmKnowledgeBase.class);
		war.addClass(SimpleAsyncShippingController.class);
		war.addClass(SimpleAsyncWebDebug.class);
		war.addClass(StartDataGeneration.class);
		return war;
	}
	
//	@Test
	public void testSignal() throws InterruptedException {
		SimpleAsyncShipping shipping = processController.testSignal();
		Thread.sleep(2000);
		Assert.assertTrue( processController.isActivityFinal() );
	}	

}