package nakedumllibraryforbpm.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.arquillian.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.nakeduml.environment.Environment;
import org.nakeduml.runtime.domain.IntrospectionUtil;
import org.nakeduml.test.NakedUtilTestClasses;
import org.nakeduml.test.adaptor.ArquillianUtils;

public class NakedUmlTestUtil {



	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		WebArchive war = ArquillianUtils.createWarArchive(false);
		war.addWebResource("META-INF/beans.xml", "beans.xml");
		war.addWebResource("NakedUMLLibraryForBPM-hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
		war.addWebResource("nakedumllibraryforbpm/AbstractRequest.rf", "nakedumllibraryforbpm/AbstractRequest.rf");
		war.addWebResource("nakedumllibraryforbpm/ProcessRequest.rf", "nakedumllibraryforbpm/ProcessRequest.rf");
		war.addWebResource("nakedumllibraryforbpm/TaskRequest.rf", "nakedumllibraryforbpm/TaskRequest.rf");
		war.addClasses(NakedUtilTestClasses.getTestClasses());
		war.addClasses(getTestClasses());
		war.addClasses(getTestProcessClasses());
		war.addWebResource(Environment.PROPERTIES_FILE_NAME, Environment.PROPERTIES_FILE_NAME);
		war.addManifestResource("hornetq-jms.xml");
		war.addPackage(IntrospectionUtil.classForName("nakedumllibraryforbpm.util.hibernate.adaptor.package-info").getPackage());;
		return war;
	}
	
	static public Class[] getTestClasses() throws IOException, ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		classes.add(nakedumllibraryforbpm.AbstractRequest.class);
		classes.add(nakedumllibraryforbpm.BusinessRole.class);
		classes.add(nakedumllibraryforbpm.RequestParticipationKind.class);
		classes.add(nakedumllibraryforbpm.TaskParticipationKind.class);
		classes.add(nakedumllibraryforbpm.TaskRequest.class);
		classes.add(nakedumllibraryforbpm.OperationProcessObject.class);
		classes.add(nakedumllibraryforbpm.TaskObject.class);
		classes.add(nakedumllibraryforbpm.ParticipationInTask.class);
		classes.add(nakedumllibraryforbpm.ParticipationInTaskDataGenerator.class);
		classes.add(nakedumllibraryforbpm.Participant.class);
		classes.add(nakedumllibraryforbpm.Participation.class);
		classes.add(nakedumllibraryforbpm.ParticipationDataGenerator.class);
		classes.add(nakedumllibraryforbpm.ProcessRequest.class);
		classes.add(nakedumllibraryforbpm.BusinessComponent.class);
		classes.add(nakedumllibraryforbpm.ParticipationInRequest.class);
		classes.add(nakedumllibraryforbpm.ParticipationInRequestDataGenerator.class);
		classes.add(nakedumllibraryforbpm.RequestObject.class);
		classes.add(Stdlib.class);
		classes.add(nakedumllibraryforbpm.util.Stdlib.class);
		classes.add(NakedUmlTestUtil.class);
		Class[] result = new Class[classes.size()];
		classes.toArray(result);
		return result;
	}
	
	static public Class[] getTestProcessClasses() throws IOException, ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		classes.add(nakedumllibraryforbpm.AbstractRequest.class);
		classes.add(nakedumllibraryforbpm.AbstractRequestState.class);
		classes.add(nakedumllibraryforbpm.ProcessRequest.class);
		classes.add(nakedumllibraryforbpm.ProcessRequestState.class);
		classes.add(nakedumllibraryforbpm.TaskRequest.class);
		classes.add(nakedumllibraryforbpm.TaskRequestState.class);
		Class[] result = new Class[classes.size()];
		classes.toArray(result);
		return result;
	}

}