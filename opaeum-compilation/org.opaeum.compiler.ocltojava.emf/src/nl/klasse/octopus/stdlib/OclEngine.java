package nl.klasse.octopus.stdlib;

public class OclEngine {
	private static IOclLibrary currentLib;

	static public IOclLibrary getCurrentOclLibrary() {
		return currentLib;
	}
}
