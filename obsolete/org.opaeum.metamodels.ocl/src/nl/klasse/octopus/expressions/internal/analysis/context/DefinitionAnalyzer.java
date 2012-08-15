package nl.klasse.octopus.expressions.internal.analysis.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nl.klasse.octopus.expressions.IVariableDeclaration;
import nl.klasse.octopus.expressions.internal.analysis.Analyzer;
import nl.klasse.octopus.expressions.internal.analysis.Environment;
import nl.klasse.octopus.expressions.internal.analysis.expressions.AnalysisException;
import nl.klasse.octopus.expressions.internal.analysis.expressions.TypeAnalyzer;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedOperDefinition;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedType;
import nl.klasse.octopus.expressions.internal.parser.parsetree.ParsedVariableDeclaration;
import nl.klasse.octopus.expressions.internal.parser.parsetree.context.ParsedOclClassContext;
import nl.klasse.octopus.expressions.internal.parser.parsetree.context.ParsedOclContext;
import nl.klasse.octopus.expressions.internal.parser.parsetree.context.ParsedOclFile;
import nl.klasse.octopus.expressions.internal.parser.parsetree.context.ParsedOclUsage;
import nl.klasse.octopus.expressions.internal.types.PathName;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.model.IModelElement;
import nl.klasse.octopus.model.IOperation;
import nl.klasse.octopus.model.IPackage;
import nl.klasse.octopus.model.IPackageableElement;
import nl.klasse.octopus.model.IParameter;
import nl.klasse.octopus.model.OclUsageType;
import nl.klasse.octopus.model.VisibilityKind;
import nl.klasse.octopus.model.internal.types.AttributeImpl;
import nl.klasse.octopus.model.internal.types.MultiplicityKindImpl;
import nl.klasse.octopus.model.internal.types.OperationImpl;
import nl.klasse.octopus.model.internal.types.ParameterImpl;
import nl.klasse.octopus.oclengine.IOclError;
import nl.klasse.octopus.oclengine.internal.OclEngine;
import nl.klasse.octopus.oclengine.internal.OclError;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.tools.common.Check;


public class DefinitionAnalyzer extends Analyzer{
	
	public DefinitionAnalyzer(String filename, List<IOclError> errors){
		super(filename, errors);
	}

	/**
	 * Method createDefs: takes a parsed file containing context expressions and builds OclContext
	 * objects from them.
	 * @param parsetree: the result of parsing the file containing the context expressions
	 * @param model: the model for which the expressions are defined
	 * @param errors: a list to which all errors encountered can be added. This should not be null.
	 * @return ArrayList: set of IAttribute and IOperation objects, all of which have isOclDef() return
	 * true. Each of these has already been added to the correct IClassifier in 'model'.
	 * 
	 */
	public List<IPackageableElement> createDefs(ParsedOclFile parsedFile, IPackage model) {
		if (Check.ENABLED) Check.pre("DefinitionAnalyzer.createDefs: parsedFile should not be null", parsedFile != null);
		if (Check.ENABLED) Check.pre("DefinitionAnalyzer.createDefs: model should not be null", model != null);
		if (Check.ENABLED) Check.pre("DefinitionAnalyzer.createDefs: errors should not be null", errors != null);
		List<IPackageableElement> result = new ArrayList<IPackageableElement>();
		IPackage pack = findPackage(parsedFile, model, errors);
		if (pack != null){
	    	Iterator it = parsedFile.getContents().iterator();
	    	while (it.hasNext() ) {
	    		ParsedOclContext elem = (ParsedOclContext) it.next();
	    		PathName elemN = elem.getReferredElem().toPathName();
				if( elem instanceof ParsedOclClassContext) {
					result.addAll(addDefsToClassContext(pack, (ParsedOclClassContext) elem, elemN, errors));
				} 
	    	}
	    	//START TEST
//			System.out.println("The file '" + currentFile + "' contains the following definitions:");
//	    	it = result.iterator();
//	    	while (it.hasNext() ) {
//	    		Object elem = it.next();
//	      		IModelElement elem = (IModelElement) it.next();
//	    		if (elem == null) {
//	    			System.out.println("elem is null");
//	    		} else {
//	    			System.out.println(elem.toString());
//	    		}
//	    	}
	       	//END TEST
		}
    	return result;
	}
	
/*******************************************************************************
 *       Creation of attributes and operations declared in ocl 'def' expressions
 * 
 ********************************************************************************/

	/**
	 * Method addDefsToClassContext.
	 * @param pack
	 * @param parsedOclClassContext
	 * @param elemN
	 * @param errors
	 * @return List: the correctly analyzed attributes and operations are returned
	 */
	private List<IPackageableElement> addDefsToClassContext(IPackage pack, ParsedOclClassContext elem,
										PathName elemN, List<IOclError> errors) {
		List<IPackageableElement> result = new ArrayList<IPackageableElement>();
		OclError err = new OclError();
		IClassifier foundClass = findClass(pack, elem, elemN, err);
		if ( foundClass == null) {
			errors.add(err);
		} else {
			Iterator defs = elem.getDefinitions();
			while( defs.hasNext() ) {
				ParsedOclUsage parsedDef = (ParsedOclUsage)defs.next();					
				if (parsedDef.getType() == OclUsageType.DEF){
					if (parsedDef.getVariable() != null) {
						result.add(analyzeAttributeDef(foundClass, parsedDef, errors));
					} else if (parsedDef.getOperation() != null) {
						result.add(analyzeOperationDef(foundClass, parsedDef, errors));
					}
				}
			}
		}
		return result;
	}

	private IOperation analyzeOperationDef(IClassifier foundClass, ParsedOclUsage parsedDef, List<IOclError> errors) {
		OperationImpl newOper = createOperation( parsedDef.getOperation(), foundClass, errors);
		newOper.setVisibility(VisibilityKind.getOperationDefault());
		if (newOper != null){
			IModelElement foundInClass = foundClass.findOperation(newOper.getName(), 
																						  newOper.getParamTypes());
			if ( foundInClass == null ) {
				foundClass.addOclDefOperation(newOper); 
				newOper.setOwner(foundClass);
				newOper.setFilename(currentFile);
				newOper.setLineAndColumn(parsedDef.getStartLine(), parsedDef.getStartColumn()); 
				return newOper;
			} else {
				errors.add( new OclError(currentFile, parsedDef.getStartLine(), parsedDef.getStartColumn(), 
               				"Operation '" + newOper.getSignature() + "' already exists in " 
               				+ foundClass.getName()));
               	return null;
			}
		}
		return null;
	}

	private IAttribute analyzeAttributeDef(IClassifier foundClass,ParsedOclUsage parsedDef, List<IOclError> errors) {
		AttributeImpl newAttr = createAttribute( parsedDef.getVariable(), foundClass, errors);
		newAttr.setVisibility(VisibilityKind.getFeatureDefault());
		if (newAttr != null){
			IModelElement foundInClass = foundClass.findAttribute(newAttr.getName());
			if (foundInClass == null )
				foundInClass = foundClass.findAssociationEnd(newAttr.getName());
			if ( foundInClass == null ) {
				foundClass.addOclDefAttribute(newAttr); 
				newAttr.setOwner(foundClass);
				newAttr.setFilename(currentFile);
				newAttr.setLineAndColumn( parsedDef.getStartLine(), parsedDef.getStartColumn() );
				return newAttr;
			} else {
				errors.add( new OclError(currentFile, parsedDef.getStartLine(), parsedDef.getStartColumn(),
                   "Attribute or association end with name '" + newAttr.getName() + "' already exists in " + foundClass.getName()));
				return null;
			}
		}
		return null;
	}

	/**
	 * Method createOperation.
	 * @param parsedOperDefinition
	 * @param foundClass
	 * @param errors
	 * @return OperationImpl
	 */
	public OperationImpl createOperation(ParsedOperDefinition pOper, IClassifier foundClass, List<IOclError> errors) {
		OperationImpl					result 		= null;		
		List<IVariableDeclaration>	parsParams	= pOper.getParameters();
		String              				operName 	= pOper.getName().toString();
		ParsedType          			parsedtype 	= (ParsedType)pOper.getReturnType();

		List<IParameter>		pars		= new ArrayList<IParameter>();
		IClassifier				type		= null;

		Environment env  = Environment.createEnvironment(foundClass.getOwner(), foundClass);
		if (parsParams != null) {
			pars = createParams(pOper.getParameters(), env, errors);
		}
		TypeAnalyzer asc = new TypeAnalyzer(currentFile, new ArrayList<IOclError>());
		try{
			type = asc.analyzeType(parsedtype, env);
		} catch(AnalysisException e) {
			errors.add(e.getError());
//			provide a dummy
			type = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
		}
		result = new OperationImpl(operName, pars, type);
		result.setIsOclDef(true);
		return result;
	}

	/**
	 * Method createParams.
	 * @param list
	 * @param env
	 * @return List
	 */
	public List<IParameter> createParams(List list, Environment env, List<IOclError> errors) {
		List<IParameter> result = new ArrayList<IParameter>();
		Iterator it = list.iterator();
		while (it.hasNext()){
			ParsedVariableDeclaration elem = (ParsedVariableDeclaration) it.next();
			String paramName = elem.getName().toString();
			ParsedType parsedType = elem.getType();
	        if( (parsedType == null) ){
	            errors.add( new OclError(currentFile, elem.getStartLine(), elem.getStartColumn(), 
	                  "Definition of '" + paramName + "' must include a type\n"));
	        } else { 
				TypeAnalyzer asc = new TypeAnalyzer(currentFile, new ArrayList<IOclError>());
				IClassifier actualType = null;
				try{
	            	actualType = asc.analyzeType(parsedType, env);
				} catch(AnalysisException e) {
					errors.add(e.getError());
					// provide a dummy
					actualType = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclVoidTypeName);
				}
				result.add( new ParameterImpl(paramName, actualType) );												
	        }			
		}
		return result;
	}

	/**
	 * Method createAttribute.
	 * @param pvar
	 * @param foundClass
	 * @param errors
	 * @return AttributeImpl
	 */
	private AttributeImpl createAttribute(ParsedVariableDeclaration pvar, IClassifier foundClass, List<IOclError> errors) {
		AttributeImpl 		result  = null;		
        IClassifier         varType = null;
		String              varName = pvar.getName().toString();
		ParsedType          type    = (ParsedType)pvar.getType();

		Environment env  = Environment.createEnvironment(foundClass.getOwner(), foundClass);
        if( (type == null) ){
            errors.add( new OclError(currentFile, pvar.getStartLine(), pvar.getStartColumn(), 
                  "Definition of '" + varName + "' must include a type\n"));
        } else { 
			TypeAnalyzer asc = new TypeAnalyzer(currentFile, new ArrayList<IOclError>());
			try{
            	varType = asc.analyzeType(type, env);
			} catch(AnalysisException e) {
				errors.add(e.getError());
//				provide a dummy
				varType = OclEngine.getCurrentOclLibrary().lookupStandardType(IOclLibrary.OclAnyTypeName);												
            }
			MultiplicityKindImpl mult = null;
			if (varType.isCollectionKind()) {
				mult = new MultiplicityKindImpl(0, MultiplicityKindImpl.MAX);
			} else {
				mult = new MultiplicityKindImpl(0, 1);
			}
			result = new AttributeImpl(varName, varType, mult);												
			result.setIsOclDef(true);
        }
		return result;
	}
		
/*******************************************************************************
 * 				Helper methods
 * 
 ********************************************************************************/

	private IPackage findPackage(ParsedOclFile parsedFile, IPackage model, List<IOclError> errors) {
		if (Check.ENABLED) Check.pre("DefinitionAnalyzer.findPackage: model may not be null", model != null);
		IPackage result = null;
		if (parsedFile.getPackageName() != null) {
			PathName packN = parsedFile.getPackageName().toPathName();
			IModelElement elem = model.lookup(packN);
			if ( elem == null || !( elem instanceof IPackage) ) {
				errors.add( new OclError(currentFile, parsedFile.getPackageName().getStartLine(), 
				                         parsedFile.getPackageName().getStartColumn(),
		                                 "Cannot find package " + packN.toString()) );
				result = model;
			} else {
				result = (IPackage) elem;
			}
		} else {
			result = model;
		}
		return result;
	}
	
	/**
	 * Method findClass.
	 * @param pack: the directly enclosing package
	 * @param elem
	 * @param elemN
	 * @param err
	 * @return IClassifier
	 */
	private IClassifier findClass(IPackage pack, ParsedOclContext elem, PathName elemN, OclError err) {
		IClassifier foundClass = null;
		IModelElement xx = pack.lookup(elemN);
		if ( xx == null || !(xx instanceof IClassifier) ) {
    		err.setLineNumber(elem.getStartLine()); 
    		err.setColumnNumber(elem.getStartColumn());
    		err.setFilename(currentFile);
            err.setErrorMessage("Cannot find class " + elemN.toString()+ " in package " 
            					+ pack.getPathName().toString() + " or its subpackages");
//			printSubPackNames(pack);
		} else {
			foundClass = (IClassifier) xx;
		}
		return foundClass;
	}

	/**
	 * Method printSubPackNames. A testroutine.
	 * @param pack
	 */
//	private void printSubPackNames(IPackage pack) {
//		Iterator it = pack.getSubpackages().iterator();
//		while (it.hasNext()) {
//			IPackage sub = (IPackage) it.next();
//			System.out.println(sub.getPathName().toString() );
//			printSubPackNames(sub);
//			printIncludingClassNames(sub);
//		}
//	}	

	/**
	 * Method printSubPackNames. A testroutine.
	 * @param pack
	 */
//	private void printIncludingClassNames(IPackage pack) {
//		Iterator it = pack.getClassifiers().iterator();
//		while (it.hasNext()) {
//			IClassifier cls = (IClassifier) it.next();
//			System.out.println("\t\t" + cls.getPathName().toString() );
//		}
//	}
}
