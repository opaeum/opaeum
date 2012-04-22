package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class OneOutputPin<O> extends OutputPin<O, SingleObjectToken<O>> {

	public OneOutputPin() {
		super();
	}

	public OneOutputPin(boolean persist, String name) {
		super(persist, name);
	}

	public OneOutputPin(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected abstract List<OneObjectFlowKnown<O>> getInFlows();

	@Override
	protected List<OneObjectFlowKnown<O>> getOutFlows() {
		return Collections.emptyList();
	}		
	@Override
	public List<SingleObjectToken<O>> getInTokens() {
		List<SingleObjectToken<O>> result = new ArrayList<SingleObjectToken<O>>();
		for (OneObjectFlowKnown<O> flow : getInFlows()) {
			Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
			for (Edge edge : iter) {
				try {
					result.add(contructToken(edge));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@Override
	public List<SingleObjectToken<O>> getInTokens(String inFlowName) {
		List<SingleObjectToken<O>> result = new ArrayList<SingleObjectToken<O>>();
		for (OneObjectFlowKnown<O> flow : getInFlows()) {
			if (inFlowName.equals(flow.getName())) {
				if (flow instanceof ObjectFlowKnown) {
					Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
					for (Edge edge : iter) {
						result.add(contructToken(edge));
					}
				} else {
					throw new IllegalStateException("wtf");
				}
			}
		}
		return result;
	}	
	
	@Override
	public List<SingleObjectToken<O>> getOutTokens() {
		List<SingleObjectToken<O>> result = new ArrayList<SingleObjectToken<O>>();
		Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + getName());
		for (Edge edge : iter) {
			result.add(contructToken(edge));
		}
		return result;
	}
	
	@Override
	public List<SingleObjectToken<O>> getOutTokens(String outFlowName) {
		List<SingleObjectToken<O>> result = new ArrayList<SingleObjectToken<O>>();
		for (OneObjectFlowKnown<O> flow : getOutFlows()) {
			if (flow.getName().equals(outFlowName)) {
				Iterable<Edge> iter = this.vertex.getOutEdges(Token.TOKEN + flow.getName());
				for (Edge edge : iter) {
					result.add(contructToken(edge));
				}
			}
		}
		return result;
	}
	
	
	@Override
	protected int countNumberOfElementsOnTokens() {
		return getInTokens().size();
	}	

	@SuppressWarnings("unchecked")
	protected SingleObjectToken<O> contructToken(Edge edge) {
		try {
			Class<?> c = Class.forName((String) edge.getProperty("tokenClass"));
			return (SingleObjectToken<O>) c.getConstructor(Vertex.class).newInstance(edge.getInVertex());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
