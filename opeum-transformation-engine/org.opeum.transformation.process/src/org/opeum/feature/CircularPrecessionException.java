package org.opeum.feature;

import java.util.List;

public class CircularPrecessionException extends RuntimeException {
	private static final long serialVersionUID = 2315498205580642858L;
	private List<?> traversalPath;
	private Object feature;

	public CircularPrecessionException(List<?> traversalPath, Object feature) {
		this.traversalPath = traversalPath;
		this.feature = feature;
	}

	public Object getFeature() {
		return this.feature;
	}

	public List<?> getTraversalPath() {
		return this.traversalPath;
	}
	@Override
	public String toString(){
		return this.traversalPath.toString();
	}
}
