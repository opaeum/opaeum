package org.tinker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

public class TestDbStartupShutdownBug {

	protected OrientGraph db;
	
	@Before
	public void before() {
		db = new OrientGraph("local:/tmp/orientdbtest444");
		db.clear();
	}

	@After
	public void after() {
		db.shutdown();
	}
	
	@Test
	public void testNothing() {
		
	}
	
}
