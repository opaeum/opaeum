package org.opaeum.ocl.uml;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.AbstractTypeChecker;
import org.eclipse.ocl.Environment;
import org.eclipse.ocl.TypeResolver;
import org.eclipse.ocl.expressions.CollectionKind;
import org.eclipse.ocl.types.CollectionType;
import org.eclipse.ocl.types.TupleType;
import org.eclipse.ocl.util.OCLStandardLibraryUtil;
import org.eclipse.ocl.utilities.TypedElement;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;

public final class OpaeumTypeChecker extends AbstractTypeChecker<Classifier,Operation,Property,Parameter>{
	TypeResolver<Classifier,Operation,Property> typeResolver = null;
	
	OpaeumTypeChecker(Environment<?,Classifier,Operation,Property,?,Parameter,?,?,?,?,?,?> env){
		super(env);
		typeResolver=env.getTypeResolver();
	}
	public TypeResolver<Classifier,Operation,Property> getTypeResolver(){
		return typeResolver;
	}
	@Override
	protected Classifier resolve(Classifier type){
		TypeResolver<Classifier,Operation,Property> typeResolver = getTypeResolver();
		return typeResolver.resolve(type);
	}
	@Override
	protected CollectionType<Classifier,Operation> resolveCollectionType(CollectionKind kind,Classifier elementType){
		return getTypeResolver().resolveCollectionType(kind, elementType);
	}
	@Override
	protected TupleType<Operation,Property> resolveTupleType(EList<? extends org.eclipse.ocl.utilities.TypedElement<Classifier>> parts){
		return getTypeResolver().resolveTupleType(parts);
	}
	@Override
	public Classifier commonSuperType(Object problemObject,Classifier type1,Classifier type2){
		if(type1 == null || type2 == null){
			return null;
		}
		Classifier commonSuperType = super.commonSuperType(problemObject, type1, type2);
		if(commonSuperType.equals(getEnvironment().getOCLStandardLibrary().getOclAny())){
			Classifier c = EmfClassifierUtil.findCommonSuperType(type1, type2);
			if(c != null){
				commonSuperType = c;
			}
		}
		return commonSuperType;
	}
	@Override
	public boolean isStandardLibraryFeature(Classifier owner,Object feature){
		if(feature instanceof Operation && ((Operation) feature).getName().equals("toString")){
			return false;
		}
		return super.isStandardLibraryFeature(owner, feature);
	}
	@Override
	public Classifier getPropertyType(Classifier owner,Property property){
		return super.getPropertyType(owner, property);
	}
	@Override
	public Classifier getResultType(Object problemObject,Classifier owner,Operation operation,
			List<? extends org.eclipse.ocl.utilities.TypedElement<Classifier>> args){
		Classifier actualOwner = getUMLReflection().getOwningClassifier(operation);
		if(isStandardLibraryFeature(actualOwner, operation)){
			int opcode = OCLStandardLibraryUtil.getOperationCode(getUMLReflection().getName(operation));
			if(opcode > 0){
				// OCL Standard Library operation. Not customizable
				Classifier result = OCLStandardLibraryUtil.getResultTypeOf(problemObject, getEnvironment(), owner, opcode, args);
				if((result == null) && (owner != actualOwner)){
					// the actual owner s different from the declared owner.
					// This happens when we re-interpret user-defined types as
					// corresponding OCL-defined types
					result = OCLStandardLibraryUtil.getResultTypeOf(problemObject, getEnvironment(), actualOwner, opcode, args);
				}
				return result;
			}
		}
		return resolve(getUMLReflection().getOCLType(operation));
	}
}