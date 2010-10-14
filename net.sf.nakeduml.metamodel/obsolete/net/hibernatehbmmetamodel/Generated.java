package net.hibernatehbmmetamodel;


public enum Generated {
	NEVER("never"),
	INSERT("insert"),
	ALWAYS("always");
	private String generatedName;
	/** Constructor for Generated
	 * 
	 * @param generatedName 
	 */
	private Generated(String generatedName) {
		this.setGeneratedName(generatedName);
	}

	static public Generated generatedNametoGenerated(String generatedName) {
		if ( generatedName.equals("never") ) {
			return NEVER;
		}
		if ( generatedName.equals("insert") ) {
			return INSERT;
		}
		if ( generatedName.equals("always") ) {
			return ALWAYS;
		}
		return null;
	}
	
	public String getGeneratedName() {
		return generatedName;
	}
	
	public String getName() {
		return name();
	}
	
	public void setGeneratedName(String generatedName) {
		this.generatedName=generatedName;
	}

}