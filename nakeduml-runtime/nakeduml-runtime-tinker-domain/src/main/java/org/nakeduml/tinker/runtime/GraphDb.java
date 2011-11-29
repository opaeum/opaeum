package org.nakeduml.tinker.runtime;



public class GraphDb {

	private GraphDb() {
	}

	private static ThreadLocal<NakedGraph> dbVar = new ThreadLocal<NakedGraph>() {
		@Override
		public NakedGraph get() {
			return super.get();
		}
		@Override
		public void set(NakedGraph db) {
			super.set(db);
		}
	};

	public static NakedGraph getDb() {
		return dbVar.get();
	}
	
	public static void setDb(NakedGraph db) {
		dbVar.set(db);
		if (db==null) {
			dbVar.remove();	
		}
	}

	public static void remove() {
		dbVar.remove();
	}
	
	public static void incrementTransactionCount() {
		getDb().incrementTransactionCount();
	}	

}
