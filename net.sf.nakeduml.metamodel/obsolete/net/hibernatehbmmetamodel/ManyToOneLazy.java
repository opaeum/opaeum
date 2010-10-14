package net.hibernatehbmmetamodel;


public enum ManyToOneLazy {
	FALSE("false"),
	PROXY("proxy"),
	NOPROXY("no-proxy");
	private String manyToOneLazyValue;
	/** Constructor for ManyToOneLazy
	 * 
	 * @param manyToOneLazyValue 
	 */
	private ManyToOneLazy(String manyToOneLazyValue) {
		this.setManyToOneLazyValue(manyToOneLazyValue);
	}

	public String getManyToOneLazyValue() {
		return manyToOneLazyValue;
	}
	
	public String getName() {
		return name();
	}
	
	static public ManyToOneLazy manyToOneLazyValuetoManyToOneLazy(String manyToOneLazyValue) {
		if ( manyToOneLazyValue.equals("false") ) {
			return FALSE;
		}
		if ( manyToOneLazyValue.equals("proxy") ) {
			return PROXY;
		}
		if ( manyToOneLazyValue.equals("no-proxy") ) {
			return NOPROXY;
		}
		return null;
	}
	
	public void setManyToOneLazyValue(String manyToOneLazyValue) {
		this.manyToOneLazyValue=manyToOneLazyValue;
	}

}