package net.hibernatehbmmetamodel;


public enum GeneratorClass {
	HILO("hilo");
	private String generatorClassName;
	/** Constructor for GeneratorClass
	 * 
	 * @param generatorClassName 
	 */
	private GeneratorClass(String generatorClassName) {
		this.setGeneratorClassName(generatorClassName);
	}

	static public GeneratorClass generatorClassNametoGeneratorClass(String generatorClassName) {
		if ( generatorClassName.equals("hilo") ) {
			return HILO;
		}
		return null;
	}
	
	public String getGeneratorClassName() {
		return generatorClassName;
	}
	
	public String getName() {
		return name();
	}
	
	public void setGeneratorClassName(String generatorClassName) {
		this.generatorClassName=generatorClassName;
	}

}