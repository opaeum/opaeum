package net.hibernatehbmmetamodel;


public enum Fetch {
	SELECT("select"),
	JOIN("join");
	private String fetchName;
	/** Constructor for Fetch
	 * 
	 * @param fetchName 
	 */
	private Fetch(String fetchName) {
		this.setFetchName(fetchName);
	}

	static public Fetch fetchNametoFetch(String fetchName) {
		if ( fetchName.equals("select") ) {
			return SELECT;
		}
		if ( fetchName.equals("join") ) {
			return JOIN;
		}
		return null;
	}
	
	public String getFetchName() {
		return fetchName;
	}
	
	public String getName() {
		return name();
	}
	
	public void setFetchName(String fetchName) {
		this.fetchName=fetchName;
	}

}