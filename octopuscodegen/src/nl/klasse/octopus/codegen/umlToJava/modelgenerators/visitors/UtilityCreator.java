/*
 * Created on Jun 4, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors;

import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import nl.klasse.octopus.codegen.helpers.GenerationHelpers;

/**
 * ModelTransformer :
 */
public class UtilityCreator {
	static private OJPathName utilPath = new OJPathName("utilities");
	static private OJPackage utilPack = null;

	/**
	 * 
	 */
	public UtilityCreator() {
		super();
	}

	public OJPackage makeUtilPack(OJPackage javamodel) {
		utilPack = GenerationHelpers.createPackage(javamodel, utilPath);
		utilPath = utilPack.getPathName();
		return utilPack;
	}

	/**
	 * @return
	 */
	public static OJPathName getUtilPathName() {
		return utilPath.getCopy();
	}

	/**
	 * @param name
	 */
	public static void setUtilPathName(OJPathName name) {
		utilPath = name;
	}

	/**
	 * @return
	 */
	public static OJPackage getUtilPack() {
		return utilPack;
	}

	public static void setUtilPackage(OJPackage findPackage) {
		utilPack = findPackage;
		utilPath=findPackage.getPathName();
	}
}
