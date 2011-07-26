package org.nakeduml.environment;

import javax.enterprise.event.Observes;
import javax.faces.event.PostConstructApplicationEvent;
import javax.inject.Inject;

import org.apache.commons.lang.time.StopWatch;
import org.apache.myfaces.extensions.cdi.core.api.logging.Logger;
import org.apache.myfaces.extensions.cdi.core.api.projectstage.ProjectStage;
import org.nakeduml.tinker.runtime.ApplicationScopedDb;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.NakedGraph;
import org.tinker.Angel;
import org.tinker.BlackHole;
import org.tinker.Demon;
import org.tinker.DreamWorld;
import org.tinker.God;
import org.tinker.SpaceTime;
import org.tinker.Universe;
import org.tinker.interfacetest.Alien;
import org.tinker.interfacetest.Creature;
import org.tinker.interfacetest.Human;
import org.tinker.interfacetest.Soul;
import org.tinker.interfacetest.Spook;
import org.tinker.polymorphism.ConcreteA1;
import org.tinker.polymorphism.ConcreteA2;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

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

	private void createDefaultData() {
		GraphDb.setDb(db);
		StopWatch totalStopWatch = new StopWatch();
		totalStopWatch.start();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		db.startTransaction();
		if (db.getCompositeRoots().isEmpty()) {
			God god = new God(true);
			god.setName("didthiswork");
			StopWatch stopWatch1 = new StopWatch();
			stopWatch1.start();
			for (int i = 0; i < 2; i++) {
				Universe universe1 = new Universe(god);
				universe1.setName("universe" + i);
				Angel angel1 = new Angel(god);
				angel1.setName("angel" + i);
				universe1.setAngel(angel1);
				SpaceTime spaceTime = new SpaceTime(universe1);
				spaceTime.setName("largerThanThis" + i);
				
				for (int j = 0; j < 100000; j++) {
					BlackHole blackHole1 = new BlackHole(universe1);
					blackHole1.setName("blackHole" + i + "_" + j);
					if (j % 500 == 0) {
						System.out.println(j);
						db.stopTransaction(Conclusion.SUCCESS);
						stopWatch1.stop();
						System.out.println(stopWatch1.toString());
						db.startTransaction();
						stopWatch1.reset();
						stopWatch1.start();
					}
				}
				
			}

			Creature creature = new Creature(god);
			creature.setName("creature");
			Human human = new Human(god);
			human.setName("human");
			Alien alien = new Alien(god);
			alien.setName("alien");
			Spook spook = new Spook(god);
			spook.setName("spook");
			Soul soul = new Soul(god);
			soul.setName("soul");
			human.setSpirit(spook);

			ConcreteA1 concreteA1_1 = new ConcreteA1(god);
			concreteA1_1.setName("concreteA1_1");
			ConcreteA1 concreteA1_2 = new ConcreteA1(god);
			concreteA1_2.setName("concreteA1_2");
			ConcreteA2 concreteA2_1 = new ConcreteA2(god);
			concreteA2_1.setName("concreteA2_1");
			ConcreteA2 concreteA2_2 = new ConcreteA2(god);
			concreteA2_2.setName("concreteA2_2");
			
			DreamWorld dreamWorld1 = new DreamWorld(god);
			dreamWorld1.setName("dreamWorld1");
			DreamWorld dreamWorld2 = new DreamWorld(god);
			dreamWorld2.setName("dreamWorld2");
			DreamWorld dreamWorld3 = new DreamWorld(god);
			dreamWorld3.setName("dreamWorld3");

			Demon demon1 = new Demon(god);
			demon1.setName("demon1");
			Demon demon2 = new Demon(god);
			demon2.setName("demon2");
			Demon demon3 = new Demon(god);
			demon3.setName("demon3");

		}
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		totalStopWatch.stop();
		System.out.println(totalStopWatch.toString());

		GraphDb.remove();
	}
}
