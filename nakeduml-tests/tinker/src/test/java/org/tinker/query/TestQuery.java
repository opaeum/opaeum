package org.tinker.query;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.tinker.BaseTest;
import org.tinker.God;
import org.tinker.Universe;

import com.orientechnologies.orient.core.query.nativ.ONativeSynchQuery;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNative;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNativeSchema;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.pipes.Pipe;
import com.tinkerpop.pipes.Pipeline;
import com.tinkerpop.pipes.filter.ComparisonFilterPipe;
import com.tinkerpop.pipes.pgm.InVertexPipe;
import com.tinkerpop.pipes.pgm.LabelFilterPipe;
import com.tinkerpop.pipes.pgm.OutEdgesPipe;

public class TestQuery extends BaseTest {

	@Test
	public void testSQLQuery() {
		God god = new God();
		god.setName("THEGOD");
		List<ODocument> result = db.getRawGraph().query(new OSQLSynchQuery<ODocument>("SELECT FROM OGraphVertex WHERE org__tinker__God__name = 'THEGOD'"));
		assertEquals(1, result.size());
		List<ODocument> nativeResult = db.getRawGraph().query(
				new ONativeSynchQuery<ODocument, OQueryContextNative<ODocument>>(db.getRawGraph(), "OGraphVertex", new OQueryContextNative<ODocument>()) {
					@Override
					public boolean filter(OQueryContextNative<ODocument> iRecord) {
						return iRecord.and().eq("asdas").go();
					};
				});

		assertEquals(0, nativeResult.size());
	}
	
	@Test
	public void testPipeQuery() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		Universe universe2 = new Universe(god);
		Universe universe3 = new Universe(god);
		Universe universe4 = new Universe(god);
		Universe universe5 = new Universe(god);
		Universe universe6 = new Universe(god);
		
		
        Vertex godVertex = god.getVertex();
        Pipe pipe1 = new OutEdgesPipe();
        Pipe pipe2 = new LabelFilterPipe("A__god___universe_", ComparisonFilterPipe.Filter.NOT_EQUAL);
        Pipe pipe3 = new InVertexPipe();
        Pipe<Vertex, Vertex> pipeline = new Pipeline<Vertex, Vertex>(Arrays.asList(pipe1, pipe2, pipe3));
        pipeline.setStarts(Arrays.asList(godVertex).iterator());
        System.out.println(pipeline);
        assertTrue(pipeline.hasNext());
        int counter = 0;
        while (pipeline.hasNext()) {
        	System.out.println(pipeline.next().getId());
            List path = pipeline.getPath();
            System.out.println(path);
            counter++;
        }
	}
	
	@Test
	public void testLazyList() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		Universe universe3 = new Universe(god);
		universe3.setName("universe3");
		Universe universe4 = new Universe(god);
		universe4.setName("universe4");
		Universe universe5 = new Universe(god);
		universe5.setName("universe5");
		Universe universe6 = new Universe(god);
		universe6.setName("universe6");
		Set<Universe> universeList = god.getUniverse();
		for (Universe universe : universeList ) {
			System.out.println(universe.getName());
		}
	}
	                            
}
