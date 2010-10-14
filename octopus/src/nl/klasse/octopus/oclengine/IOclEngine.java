/*
 * Created on Jan 21, 2004
 *
 * Copyright Klasse Objecten
 */
package nl.klasse.octopus.oclengine;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import nl.klasse.octopus.expressions.IOclExpression;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.stdlib.IOclLibrary;


/**
 * IOclEngine : 
 */
public interface IOclEngine {
	/** Returns the OclLibrary object that is currently used.
	 * @return OclLibrary
	 */
	public abstract IOclLibrary getOclLibrary();
	/** Sets the OclLibrary object to be used.
	 * @return OclLibrary
	 */
	public abstract void setOclLibrary(IOclLibrary lib);
	/**
	 * Method parseAndAnalyze parses and analyzes a single OCL expression.
	 * 
	 * @param exp: the string that is the expression that should be parsed and analyzed
	 * @param model: the model that holds the elements that the expression refers to.
	 * @param context: the model element that is the context of the expression to be parsed.
	 * @param errors: a list of errors found in the input. Each element is of type IOclError.
	 * @return IOclExpression: the ast build from 'exp'
	 */
	public abstract IOclExpression parseAndAnalyze(
		String exp,
		IPackage model,
		IClassifier contextualType,
		List<IOclError> errors);
	/**
	 * Method parseAndAnalyze parses and analyzes a single OCL context definition followed
	 * by a number of OCL expressions.
	 * 
	 * @param contextExp: one context declaration and a series of ocl expressions with usage type
	 * as defined in the OCL standard.
	 * @param model: the model that holds the model elements to which the OCL expressions
	 * may refer.
	 * @param errors: a list of errors found in the input. Each element is of type IOclError.
	 * @return List: set of IOclContext objects
	 */
	public abstract List<IOclContext> parseAndAnalyze(
		String contextExp,
		IPackage model,
		List<IOclError> errors);
	/**
	 * Method parseAndAnalyze parses and analyzes a single file that holds a series
	 * of OCL context definitions, each with a number of OCL expressions.
	 * 
	 * @param filename: the name of the file; should correspond to 'in'.
	 * @param in: the stream that 'is' the file.
	 * @param model: the model that holds the model elements to which the OCL expressions
	 * may refer.
	 * @param errors: a list of errors found in the input. Each element is of type IOclError.
	 * @return List: set of IOclContext objects.
	 */
	public abstract List<IOclContext> parseAndAnalyze(
		String filename,
		InputStream in,
		IPackage model,
		List<IOclError> errors);
	/**
	 * Method parseAndAnalyze parses and analyzes a series of files that each hold a series
	 * of OCL context definitions, each with a number of OCL expressions.
	 * 
	 * @param filenames: the names of the files; should correspond to 'streams'.
	 * @param streams: a List of InputStream objects
	 * @param model: the model that holds the model elements to which the OCL expressions
	 * may refer.
	 * @param errors: a list of errors found in the input. Each element is of type IOclError.
	 * @return List: set of IOclContext objects.
	 */
	public abstract List<IOclContext> parseAndAnalyze(
		List<String> filenames,
		List<FileInputStream> streams,
		IPackage model,
		List<IOclError> errors);
}