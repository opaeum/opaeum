package net.hibernatehbmmetamodel;


public enum Access {
	FIELD("field"),
	PROPERTY("property");
	private String accessName;
	/** Constructor for Access
	 * 
	 * @param accessName 
	 */
	private Access(String accessName) {
		this.setAccessName(accessName);
	}

	static public Access accessNametoAccess(String accessName) {
		if ( accessName.equals("field") ) {
			return FIELD;
		}
		if ( accessName.equals("property") ) {
			return PROPERTY;
		}
		return null;
	}
	
	public String getAccessName() {
		return accessName;
	}
	
	public String getName() {
		return name();
	}
	
	public void setAccessName(String accessName) {
		this.accessName=accessName;
	}

}