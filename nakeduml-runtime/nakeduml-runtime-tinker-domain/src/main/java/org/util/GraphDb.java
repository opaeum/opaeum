package org.util;

import org.nakeduml.environment.Environment;


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
//		@Override
//	    protected NakedGraph initialValue() {
//	        return Environment.getInstance().getComponent(NakedGraph.class);
//	    }		
	};

	public static NakedGraph getDb() {
		return dbVar.get();
	}
	
	public static void setDb(NakedGraph db) {
		dbVar.set(db);
	}
	
	public static void incrementTransactionCount() {
		getDb().incrementTransactionCount();
	}	

}
