package org.nakeduml.runtime.domain.activity;

import org.nakeduml.runtime.domain.TinkerNode;
import org.nakeduml.tinker.runtime.GraphDb;
import org.opaeum.runtime.domain.ISignal;
import org.opaeum.runtime.domain.IntrospectionUtil;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public class SignalEvent extends Event {

	private static final long serialVersionUID = 7073156823339080917L;
	
	public SignalEvent(String name) {
		super(name);
	}

	public SignalEvent(ISignal signal, String name) {
		super(name);
		setSignal(signal);
	}

	public SignalEvent(Vertex vertex) {
		super(vertex);
	}

	public ISignal getSignal() {
		Iterable<Edge> iter1 = this.vertex.getInEdges("event_signal");
		if ( iter1.iterator().hasNext() ) {
			Edge edge = iter1.iterator().next();
			try {
				Class<?> c = Class.forName((String)edge.getProperty("outClass"));
				return (ISignal) c.getConstructor(Vertex.class).newInstance(edge.getOutVertex());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	public void setSignal(ISignal signal) {
		Edge edge = GraphDb.getDb().addEdge(null, ((TinkerNode)signal).getVertex(), this.vertex,"event_signal");
		edge.setProperty("outClass", signal.getClass().getName());
		edge.setProperty("inClass", IntrospectionUtil.getOriginalClass(this.getClass()).getName());
	}

	@Override
	public void clearCache() {
		// TODO Auto-generated method stub
		
	}
	
}
