/** (c) Copyright 2002, Klasse Objecten
 */
package nl.klasse.octopus.stdlib.internal.library;

import java.util.HashMap;

import nl.klasse.octopus.model.IClassifier;
import nl.klasse.octopus.stdlib.IOclLibrary;
import nl.klasse.octopus.stdlib.internal.types.StdlibClassifier;
import nl.klasse.octopus.stdlib.internal.types.StdlibDataType;
import nl.klasse.octopus.stdlib.internal.types.StdlibOperation;
import nl.klasse.octopus.stdlib.internal.types.StdlibPrimitiveType;

/**
 * The StdlibBasic class is responsible for creating the basic types of the OCL
 * Standard Library. It contains static operations only.
 * 
 * @author Jos Warmer
 * @version $Id: StdlibBasic.java,v 1.2 2008/01/19 13:31:08 annekekleppe Exp $
 */
public class StdlibBasic {
	private StdlibBasic() {
	}

	public static IClassifier getBasicType(String name) {
		IClassifier result = (IClassifier) basicTypes.get(name);
		return result;
	}

	private static HashMap<String, StdlibClassifier> basicTypes = new HashMap<String, StdlibClassifier>();
	protected static StdlibPrimitiveType OCL_Boolean = new StdlibPrimitiveType(IOclLibrary.BooleanTypeName);
	protected static StdlibPrimitiveType OCL_Integer = new StdlibPrimitiveType(IOclLibrary.IntegerTypeName);
	protected static StdlibPrimitiveType OCL_Real = new StdlibPrimitiveType(IOclLibrary.RealTypeName);
	protected static StdlibPrimitiveType OCL_String = new StdlibPrimitiveType(IOclLibrary.StringTypeName);
	protected static StdlibDataType OCL_Any = new StdlibDataType(IOclLibrary.OclAnyTypeName);
	protected static StdlibDataType OCL_Void = new StdlibDataType(IOclLibrary.OclVoidTypeName);
	protected static StdlibDataType OCL_Type = new StdlibDataType(IOclLibrary.OclTypeTypeName);
	protected static StdlibDataType OCL_State = new StdlibDataType(IOclLibrary.OclStateTypeName);
	protected static StdlibDataType OCL_Message = new StdlibDataType("OclMessage");
	protected static StdlibDataType OCL_ModelElement = new StdlibDataType("OclModelElement");
	protected static StdlibDataType DependsOnArgumentType = new StdlibDataType("DependsOnArgumentType");
	protected static StdlibDataType DependsOnSourceType = new StdlibDataType("DependsOnSourceType");

	public static void initialize() {
		basicTypes.put(IOclLibrary.BooleanTypeName, OCL_Boolean);
		basicTypes.put(IOclLibrary.IntegerTypeName, OCL_Integer);
		basicTypes.put(IOclLibrary.RealTypeName, OCL_Real);
		basicTypes.put(IOclLibrary.StringTypeName, OCL_String);
		basicTypes.put(IOclLibrary.OclAnyTypeName, OCL_Any);
		basicTypes.put(IOclLibrary.OclVoidTypeName, OCL_Void);
		basicTypes.put(IOclLibrary.OclTypeTypeName, OCL_Type);
		basicTypes.put(IOclLibrary.OclStateTypeName, OCL_State);
		basicTypes.put("OclMessage", OCL_Message);
		basicTypes.put("OclModelElement", OCL_ModelElement);
		basicTypes.put("DependsOnArgumentType", DependsOnArgumentType);
		basicTypes.put("DependsOnSourceType", DependsOnSourceType);
		// Real
		OCL_Real.addOperation(new StdlibOperation("=", "object2", OCL_Real, OCL_Boolean, true));
		OCL_Real.addOperation(new StdlibOperation("<>", "object2", OCL_Real, OCL_Boolean, true));
		OCL_Real.addOperation(new StdlibOperation("<", "object2", OCL_Real, OCL_Boolean, true));
		OCL_Real.addOperation(new StdlibOperation(">", "object2", OCL_Real, OCL_Boolean, true));
		OCL_Real.addOperation(new StdlibOperation("<=", "object2", OCL_Real, OCL_Boolean, true));
		OCL_Real.addOperation(new StdlibOperation(">=", "object2", OCL_Real, OCL_Boolean, true));
		OCL_Real.addOperation(new StdlibOperation("+", "object2", OCL_Real, OCL_Real, true));
		OCL_Real.addOperation(new StdlibOperation("-", "object2", OCL_Real, OCL_Real, true));
		OCL_Real.addOperation(new StdlibOperation("*", "object2", OCL_Real, OCL_Real, true));
		OCL_Real.addOperation(new StdlibOperation("/", "object2", OCL_Real, OCL_Real, true));
		OCL_Real.addOperation(new StdlibOperation("max", "object2", OCL_Real, OCL_Real));
		OCL_Real.addOperation(new StdlibOperation("min", "object2", OCL_Real, OCL_Real));
		OCL_Real.addOperation(new StdlibOperation("abs", OCL_Real));
		OCL_Real.addOperation(new StdlibOperation("floor", OCL_Integer));
		OCL_Real.addOperation(new StdlibOperation("round", OCL_Integer));
		OCL_Real.addOperation(new StdlibOperation("toString", OCL_String));
		OCL_Real.addOperation(new StdlibOperation("-", DependsOnSourceType, true));
		// Integer
		OCL_Integer.addOperation(new StdlibOperation("+", "i2", OCL_Integer, OCL_Integer, true));
		OCL_Integer.addOperation(new StdlibOperation("-", "i2", OCL_Integer, OCL_Integer, true));
		OCL_Integer.addOperation(new StdlibOperation("*", "i2", OCL_Integer, OCL_Integer, true));
		OCL_Integer.addOperation(new StdlibOperation("/", "i2", OCL_Integer, OCL_Real, true));
		OCL_Integer.addOperation(new StdlibOperation("=", "i2", OCL_Integer, OCL_Boolean, true));
		OCL_Integer.addOperation(new StdlibOperation("abs", OCL_Integer));
		OCL_Integer.addOperation(new StdlibOperation("div", "i2", OCL_Integer, OCL_Integer));
		OCL_Integer.addOperation(new StdlibOperation("mod", "i2", OCL_Integer, OCL_Integer));
		OCL_Integer.addOperation(new StdlibOperation("max", "object2", OCL_Integer, OCL_Integer));
		OCL_Integer.addOperation(new StdlibOperation("min", "object2", OCL_Integer, OCL_Integer));
		OCL_Integer.addOperation(new StdlibOperation("-", DependsOnSourceType, true));
		OCL_Integer.addGeneralization(OCL_Real);
		// OclAny
		OCL_Any.addOperation(new StdlibOperation("oclIsNew", OCL_Boolean));
		OCL_Any.addOperation(new StdlibOperation("oclIsUndefined", OCL_Boolean));
		OCL_Any.addOperation(new StdlibOperation("oclInState", "object2", OCL_State, OCL_Boolean));
		OCL_Any.addOperation(new StdlibOperation("oclIsInState", "object2", OCL_State, OCL_Boolean));
		OCL_Any.addOperation(new StdlibOperation("oclIsKindOf", "object2", OCL_Type, OCL_Boolean));
		OCL_Any.addOperation(new StdlibOperation("oclIsTypeOf", "object2", OCL_Type, OCL_Boolean));
		OCL_Any.addOperation(new StdlibOperation("oclAsType", "object2", OCL_Type, DependsOnArgumentType));
		OCL_Any.addOperation(new StdlibOperation("asSet", DependsOnSourceType));
		StdlibOperation allInstancesOper = new StdlibOperation("allInstances", DependsOnSourceType);
		allInstancesOper.setIsClassOperation(true);
		OCL_Any.addOperation(allInstancesOper);
		OCL_Any.addOperation(new StdlibOperation("=", "object", DependsOnSourceType, OCL_Boolean, true));
		OCL_Any.addOperation(new StdlibOperation("<>", "object", DependsOnSourceType, OCL_Boolean, true));
		// String
		OCL_String.addOperation(new StdlibOperation("=", "object2", OCL_String, OCL_Boolean, true));
		OCL_String.addOperation(new StdlibOperation("<>", "object2", OCL_String, OCL_Boolean, true));
		OCL_String.addOperation(new StdlibOperation("size", OCL_Integer));
		OCL_String.addOperation(new StdlibOperation("concat", "s", OCL_String, OCL_String));
		OCL_String.addOperation(new StdlibOperation("+", "s", OCL_String, OCL_String, true));
		OCL_String.addOperation(new StdlibOperation("toInteger", OCL_Integer));
		OCL_String.addOperation(new StdlibOperation("toReal", OCL_Real));
		OCL_String.addOperation(new StdlibOperation("toUpper", OCL_String));
		OCL_String.addOperation(new StdlibOperation("toLower", OCL_String));
		OCL_String.addOperation(new StdlibOperation("substring", "from", OCL_Integer, "to", OCL_Integer, OCL_String));
		OCL_String.addOperation(new StdlibOperation("replaceAll", "find", OCL_String, "replaceWith", OCL_String, OCL_String));
		// Boolean
		OCL_Boolean.addOperation(new StdlibOperation("=", "object2", OCL_Boolean, OCL_Boolean, true));
		OCL_Boolean.addOperation(new StdlibOperation("<>", "object2", OCL_Boolean, OCL_Boolean, true));
		OCL_Boolean.addOperation(new StdlibOperation("not", OCL_Boolean, true));
		OCL_Boolean.addOperation(new StdlibOperation("or", "object2", OCL_Boolean, OCL_Boolean, true));
		OCL_Boolean.addOperation(new StdlibOperation("xor", "object2", OCL_Boolean, OCL_Boolean, true));
		OCL_Boolean.addOperation(new StdlibOperation("and", "object2", OCL_Boolean, OCL_Boolean, true));
		OCL_Boolean.addOperation(new StdlibOperation("implies", "object2", OCL_Boolean, OCL_Boolean, true));
		OCL_Boolean.addOperation(new StdlibOperation("toString", OCL_String));
		// OclType
		OCL_Type.addOperation(new StdlibOperation("=", "object", OCL_Type, OCL_Boolean, true));
		OCL_Type.addOperation(new StdlibOperation("<>", "object", OCL_Type, OCL_Boolean, true));
		// OclState
		OCL_State.addOperation(new StdlibOperation("=", "object", OCL_State, OCL_Boolean, true));
		OCL_State.addOperation(new StdlibOperation("<>", "object", OCL_State, OCL_Boolean, true));
		// OclMessage(T)
		// OclModelElement
		OCL_ModelElement.addOperation(new StdlibOperation("=", "object", OCL_ModelElement, OCL_Boolean, true));
		OCL_ModelElement.addOperation(new StdlibOperation("<>", "object", OCL_ModelElement, OCL_Boolean, true));
		// OclVoid
		OCL_Void.addOperation(new StdlibOperation("oclIsUndefined", OCL_Boolean));
	}
}