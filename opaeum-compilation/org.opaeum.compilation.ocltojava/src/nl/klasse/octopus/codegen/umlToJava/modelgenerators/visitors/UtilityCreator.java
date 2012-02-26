/*
 * Created on Jun 4, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors;

import nl.klasse.octopus.codegen.helpers.GenerationHelpers;

import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;

/**
 * ModelTransformer :
 */
public class UtilityCreator{
	static private final ThreadLocal<OJPathName> utilPath = new ThreadLocal<OJPathName>();
	static private final ThreadLocal<OJPackage> utilPack = new ThreadLocal<OJPackage>();
	/**
	 * 
	 */
	public UtilityCreator(){
		super();
		utilPath.set(new OJPathName("utilities"));
	}
	public OJPackage makeUtilPack(OJPackage javamodel){
		utilPack.set(GenerationHelpers.createPackage(javamodel, utilPath.get()));
		utilPath.set(utilPack.get().getPathName());
		return utilPack.get();
	}
	/**
	 * @return
	 */
	public static OJPathName getUtilPathName(){
		if(utilPath.get()==null){
			System.out.println();
		}
		return utilPath.get().getCopy();
	}
	/**
	 * @param name
	 */
	public static void setUtilPathName(OJPathName name){
		utilPath.set(name);
	}
	/**
	 * @return
	 */
	public static OJPackage getUtilPack(){
		return utilPack.get();
	}
	public static void setUtilPackage(OJPackage findPackage){
		utilPack.set(findPackage);
		if(findPackage == null){

			utilPath.set(null);
		}else{
			utilPath.set(findPackage.getPathName());
		}
	}
}
