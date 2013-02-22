package org.opaeum.uim.perspective;


public enum PositionInPerspective {
	LEFT,
	RIGHT,
	TOP,
	BOTTOM,
	RIGHT_TOP,
	RIGHT_BOTTOM,
	LEFT_TOP,
	LEFT_BOTTOM,
	TOP_RIGHT,
	TOP_LEFT,
	BOTTOM_RIGHT,
	BOTTOM_LEFT;


	static public PositionInPerspective getByName(String name) {
		PositionInPerspective result = null;
		if ( "left".equals(name) ) {
			return LEFT;
		}
		if ( "right".equals(name) ) {
			return RIGHT;
		}
		if ( "top".equals(name) ) {
			return TOP;
		}
		if ( "bottom".equals(name) ) {
			return BOTTOM;
		}
		if ( "rightTop".equals(name) ) {
			return RIGHT_TOP;
		}
		if ( "rightBottom".equals(name) ) {
			return RIGHT_BOTTOM;
		}
		if ( "leftTop".equals(name) ) {
			return LEFT_TOP;
		}
		if ( "leftBottom".equals(name) ) {
			return LEFT_BOTTOM;
		}
		if ( "topRight".equals(name) ) {
			return TOP_RIGHT;
		}
		if ( "topLeft".equals(name) ) {
			return TOP_LEFT;
		}
		if ( "bottomRight".equals(name) ) {
			return BOTTOM_RIGHT;
		}
		if ( "bottomLeft".equals(name) ) {
			return BOTTOM_LEFT;
		}
		return result;
	}

}