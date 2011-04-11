package tinker.test;
import org.junit.After;
import org.junit.Before;
import org.util.DbThreadVar;


import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;


public class BaseTest {

	protected OrientGraph db;
	
	@Before
	public void before() {
		db = new OrientGraph("memory:/tmp/graph/db");
		DbThreadVar.setDB(db);
	}

	@After
	public void after() {
		db.shutdown();
		DbThreadVar.setDB(null);
	}

}
