package org.opaeum.environment;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;

import org.apache.commons.io.FileUtils;
import org.opaeum.runtime.domain.IActiveObject;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.runtime.environment.Environment;
import org.opaeum.runtime.persistence.UmtPersistence;
import org.opaeum.tinker.runtime.NakedNeo4jGraph;
import org.util.NakedGraph;
import org.util.TransactionThreadEntityVar;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;

public class JunitTestNeo4jEnvironment extends Environment {

	@Override
	public <T> Class<T> getImplementationClass(T o) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getComponent(Class<T> clazz) {
		if (clazz == NakedGraph.class) {
			File dir = new File(this.properties.getProperty("tinkerdb"));
			if (dir.exists()) {
				try {
					FileUtils.deleteDirectory(dir);
				} catch (IOException e) {
					throw new RuntimeException(e);
				} 
			}	
			Neo4jGraph db = new Neo4jGraph(this.properties.getProperty("tinkerdb"));
			db.setTransactionMode(Mode.valueOf(this.properties.getProperty("tinkerdb.transactionmode")));
			TransactionThreadEntityVar.clear();
			NakedGraph nakedGraph = new NakedNeo4jGraph(db);
			nakedGraph.registerListeners();
			return (T)nakedGraph;
		}
		return null;
	}

	@Override
	public <T> T getComponent(Class<T> clazz, Annotation qualifiers) {
		return null;
	}

	@Override
	public void reset() {
	}

	@Override
	public void endRequestContext(){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startRequestContext(){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendSignal(IActiveObject target,ISignal s){
		// TODO Auto-generated method stub
		
	}

	@Override
	public UmtPersistence newUmtPersistence(){
		// TODO Auto-generated method stub
		return null;
	}

}
