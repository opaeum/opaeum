package org.tinker;

import org.apache.commons.lang.time.StopWatch;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.util.TinkerSchemaGenerator;

import com.tinkerpop.blueprints.pgm.TransactionalGraph;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestCompositeComponent.class})
public class TestSuite {

	@BeforeClass
    public static void setUp() {
        System.out.println("setting up");
        TransactionalGraph db = new OrientGraph("local:/tmp/tinker-orient-auditwithcache");
		StopWatch watch = new StopWatch();
		watch.start();
		TinkerSchemaGenerator.createSchema( ((OrientGraph)db).getRawGraph());
		watch.stop();
		System.out.println("Schema creation took " + watch.toString());
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("tearing down");
    }

}
