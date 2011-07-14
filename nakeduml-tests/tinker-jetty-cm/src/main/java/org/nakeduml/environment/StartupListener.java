package org.nakeduml.environment;

import javax.enterprise.event.Observes;
import javax.faces.event.PostConstructApplicationEvent;
import javax.faces.event.PreDestroyApplicationEvent;
import javax.inject.Inject;

import org.apache.commons.lang.time.StopWatch;
import org.apache.myfaces.extensions.cdi.core.api.logging.Logger;
import org.apache.myfaces.extensions.cdi.core.api.projectstage.ProjectStage;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.NakedGraph;

import com.rorotika.cm.core.CmApplication;
import com.rorotika.cm.core.network.Group;
import com.rorotika.cm.core.network.Network;
import com.rorotika.cm.core.network.NetworkSoftwareVersion;
import com.rorotika.cm.core.network.SoftwareVersion;
import com.rorotika.cm.core.nodedefinition.NodeDefinition;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.Vertex;

public class StartupListener {

	@Inject
	Logger logger;

	@Inject
	private ProjectStage projectStage;
	@ApplicationScopedDb
	@Inject
	NakedGraph db;

	public void initApp(@Observes PostConstructApplicationEvent proPostConstructApplicationEvent) {
		if (ProjectStage.Development.equals(this.projectStage)) {
			this.logger
					.info("Welcome to a MyFaces CODI demo! Please also have a look at the documentation: https://cwiki.apache.org/confluence/display/EXTCDI/Documentation");
		} else {
			this.logger.info("Observed MyFaces CODI for JSF 2.0 startup.");
		}
		createDefaultData();
	}
	
	public void destroyApp(@Observes PreDestroyApplicationEvent preDestroyApplicationEvent) {
		db.shutdown();
	}	

	private void createDefaultData() {
		GraphDb.setDb(db);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		db.startTransaction();
		if (db.getCompositeRoots().isEmpty()) {
			CmApplication cmApplication = new CmApplication(true);
			cmApplication.setName("CmApp");
			cmApplication.getUid();
			Group group = new Group(cmApplication);
			group.setName("group1");
			Group group2 = new Group(cmApplication);
			group2.setName("group2");
			Network network = new Network(group);
			network.setName("network1");
			NetworkSoftwareVersion r13SouthAfrica = new NetworkSoftwareVersion(network);
			r13SouthAfrica.setName("R13_SouthAfrica");
			r13SouthAfrica.setSoftwareVersion(SoftwareVersion.R13);
			NodeDefinition nodeDefinitionAAAA1 = new NodeDefinition(r13SouthAfrica);
			nodeDefinitionAAAA1.setName("AAAA1");
			NodeDefinition nodeDefinitionBBBB1 = new NodeDefinition(r13SouthAfrica);
			nodeDefinitionBBBB1.setName("BBBB1");
			NodeDefinition nodeDefinitionCCCC1 = new NodeDefinition(r13SouthAfrica);
			nodeDefinitionCCCC1.setName("CCCC1");
			
			NetworkSoftwareVersion r12Ghana = new NetworkSoftwareVersion(network);
			r12Ghana.setName("r12Ghana");
			r12Ghana.setSoftwareVersion(SoftwareVersion.R12);
			NodeDefinition nodeDefinitionAAAA2 = new NodeDefinition(r12Ghana);
			nodeDefinitionAAAA2.setName("AAAA2");
			NodeDefinition nodeDefinitionBBBB2 = new NodeDefinition(r12Ghana);
			nodeDefinitionBBBB2.setName("BBBB2");
			NodeDefinition nodeDefinitionCCCC2 = new NodeDefinition(r12Ghana);
			nodeDefinitionCCCC2.setName("CCCC2");
			
			NetworkSoftwareVersion r12Botswana = new NetworkSoftwareVersion(network);
			r12Botswana.setName("r12Botswana");
			r12Botswana.setSoftwareVersion(SoftwareVersion.R12);
			NodeDefinition nodeDefinitionAAAA3 = new NodeDefinition(r12Botswana);
			nodeDefinitionAAAA3.setName("AAAA3");
			NodeDefinition nodeDefinitionBBBB3 = new NodeDefinition(r12Botswana);
			nodeDefinitionBBBB3.setName("BBBB3");
			NodeDefinition nodeDefinitionCCCC3 = new NodeDefinition(r12Botswana);
			nodeDefinitionCCCC3.setName("CCCC3");
			
		}
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		logger.info(   String.valueOf(((CmApplication)db.getCompositeRoots().get(0)).getGroup().size()));
		logger.info(   String.valueOf(db.countVertices()));
		for (Vertex v : db.getVertices()) {
			logger.info((String)v.toString());
		}
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		GraphDb.remove();
	}
}
