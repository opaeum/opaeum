package tinker.util;

import com.tinkerpop.blueprints.pgm.Graph;

public class DbThreadVar {

	private DbThreadVar() {
	}

	private static ThreadLocal<Graph> dbVar = new ThreadLocal<Graph>() {
		Graph db = null;

		public Graph get() {
			return db;
		}

		public void set(Graph newValue) {
			db = newValue;
		}

	};

	public static Graph getDB() {
		return (Graph) dbVar.get();
	}
	
	public static void setDB(Graph db) {
		dbVar.set(db);
	}

}
