package org.opaeum.uim.panel;


public enum Orientation {
	HORIZONTAL,
	VERTICAL;


	static public Orientation getByName(String name) {
		Orientation result = null;
		if ( "horizontal".equals(name) ) {
			return HORIZONTAL;
		}
		if ( "vertical".equals(name) ) {
			return VERTICAL;
		}
		return result;
	}

}