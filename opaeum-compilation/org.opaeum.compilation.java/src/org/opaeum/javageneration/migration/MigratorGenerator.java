package org.opaeum.javageneration.migration;

import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJConstructor;
import org.opaeum.java.metamodel.OJForStatement;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPackage;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJSwitchCase;
import org.opaeum.java.metamodel.OJSwitchStatement;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.commonbehaviors.INakedBehavioredClassifier;
import org.opaeum.metamodel.core.ICompositionParticipant;
import org.opaeum.metamodel.core.INakedClassifier;
import org.opaeum.metamodel.core.INakedComplexStructure;
import org.opaeum.metamodel.core.INakedDataType;
import org.opaeum.metamodel.core.INakedEnumeration;
import org.opaeum.metamodel.core.INakedEnumerationLiteral;
import org.opaeum.metamodel.core.INakedInterface;
import org.opaeum.metamodel.core.INakedInterfaceRealization;
import org.opaeum.metamodel.core.INakedProperty;
import org.opaeum.metamodel.core.INakedSimpleType;
import org.opaeum.metamodel.core.INakedStructuredDataType;
import org.opaeum.runtime.environment.IMigrator;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = MigrationGenerationPhase.class)
public class MigratorGenerator extends AbstractMigrationCodeGenerator{
	private static class MigratorContext{
		private INakedComplexStructure fromClass;
		private INakedComplexStructure toClass;
		private OJAnnotatedClass migrator;
		private OJAnnotatedOperation migratingOperation;
		private String prefix;
		private MigratorContext(INakedComplexStructure fromClass,INakedComplexStructure toClass,OJAnnotatedClass migrator,OJAnnotatedOperation migratingOperation,
				String prefix){
			super();
			this.fromClass = fromClass;
			this.toClass = toClass;
			this.migrator = migrator;
			this.migratingOperation = migratingOperation;
			this.prefix = prefix;
		}
	}
	public MigratorGenerator(){
		super();
	}
	@Override
	protected int getThreadPoolSize(){
		return 0;
	}
	protected void visitComplexStructure(INakedComplexStructure to){
		if(to instanceof ICompositionParticipant){
			if(to instanceof INakedInterface){
				generateMigratorContractForInterface(to);
			}else if(isPersistent(to)){
				ICompositionParticipant toEntity = (ICompositionParticipant) to;
				ICompositionParticipant fromEntity = (ICompositionParticipant) fromWorkspace.getModelElement(toEntity.getId());
				if(fromEntity != null){
					OJPathName migratorPath = migratorPath(toEntity);
					OJPackage pkg = findOrCreatePackage(migratorPath.getHead());
					OJAnnotatedClass migratorGen = generateMigrationContract(toEntity, migratorPath, pkg);
					generateImplementationClass(migratorPath, pkg, migratorGen);
					addMigrationEntryPoint(toEntity, fromEntity, migratorGen);
					buildMigrateTreeImpl(toEntity, fromEntity, migratorGen);
					buildMigrateDataTypeProperties(toEntity, fromEntity, migratorGen);
					buildMigrateCompositeProperties(toEntity, fromEntity, migratorGen);
					buildMigrateReferences(toEntity, fromEntity, migratorGen);
				}
			}
		}
	}
	private OJAnnotatedClass generateMigrationContract(ICompositionParticipant toEntity,OJPathName migratorPath,OJPackage pkg){
		OJAnnotatedClass migratorGen = new OJAnnotatedClass(migratorPath.getLast() + "GEN");
		pkg.addToClasses(migratorGen);
		migratorGen.setAbstract(true);
		createTextPath(migratorGen, JavaSourceFolderIdentifier.MIGRATION_GEN_SRC);
		migratorGen.addToImplementedInterfaces(new OJPathName(IMigrator.class.getName()));
		OJUtil.addTransientProperty(migratorGen, "context", new OJPathName("org.opaeum.runtime.environment.MigrationContext"), true);
		if(toEntity instanceof INakedBehavioredClassifier){
			implementMigatorInterfaces(migratorGen, (INakedBehavioredClassifier) toEntity);
		}
		if(toEntity.getSupertype() != null){
			migratorGen.setSuperclass(migratorPath((ICompositionParticipant) toEntity.getSupertype()));
		}
		return migratorGen;
	}
	private void generateImplementationClass(OJPathName migratorPath,OJPackage pkg,OJAnnotatedClass migratorGen){
		OJAnnotatedClass migratorImpl = new OJAnnotatedClass(migratorPath.getLast());
		pkg.addToClasses(migratorImpl);
		OJConstructor constructor = new OJConstructor();
		migratorImpl.addToConstructors(constructor);
		createTextPath(migratorImpl, JavaSourceFolderIdentifier.MIGRATION_SRC);
		migratorImpl.setSuperclass(migratorGen.getPathName());
	}
	private void buildMigrateTreeImpl(ICompositionParticipant toEntity,ICompositionParticipant fromEntity,OJAnnotatedClass migrator){
		OJAnnotatedOperation migrateTreeImpl = new OJAnnotatedOperation("migrateTreeImpl");
		migrator.addToOperations(migrateTreeImpl);
		if(toEntity.getEndToComposite() != null){
			migrateTreeImpl.addParam("owner", classifierPathName(toEntity.getEndToComposite().getNakedBaseType(), getToVersion()));
		}else{
		}
		migrateTreeImpl.addParam("from", classifierPathName(fromEntity, getFromVersion()));
		migrateTreeImpl.addParam("result", classifierPathName(toEntity, getToVersion()));
		migrateTreeImpl.getBody().addToStatements("");
		migrateTreeImpl.getBody().addToStatements("migrateDataTypeProperties(from,result)");
		migrateTreeImpl.getBody().addToStatements("context.persistToObject(result)");
		migrateTreeImpl.getBody().addToStatements("context.pop()");
		migrateTreeImpl.getBody().addToStatements("context.push()");
		migrateTreeImpl.getBody().addToStatements("from=context.reloadFromObject(from)");
		migrateTreeImpl.getBody().addToStatements("migrateCompositeProperties(from,result)");
	}
	private void buildMigrateCompositeProperties(ICompositionParticipant toEntity,ICompositionParticipant fromEntity,OJAnnotatedClass migrator){
		OJAnnotatedOperation migrateComposites = new OJAnnotatedOperation("migrateCompositeProperties");
		migrator.addToOperations(migrateComposites);
		migrateComposites.addParam("from", classifierPathName(fromEntity, getFromVersion()));
		migrateComposites.addParam("result", classifierPathName(toEntity, getToVersion()));
		MigratorContext ctx2 = new MigratorContext(fromEntity, toEntity, migrator, migrateComposites, "");
		if(toEntity.getSupertype() != null){
			migrateInheritedCompositeProperties(ctx2);
		}
		for(INakedProperty toProperty:toEntity.getDirectlyImplementedAttributes()){
			if(!(toProperty.isDerived() || toProperty.isReadOnly()) && isPersistent(toProperty.getNakedBaseType())){
				if(toProperty.isComposite() && toProperty.getNakedBaseType() instanceof ICompositionParticipant){
					migrateComposites(ctx2, toProperty);
				}
			}
		}
	}
	private void buildMigrateReferences(ICompositionParticipant toEntity,ICompositionParticipant fromEntity,OJAnnotatedClass migrator){
		OJAnnotatedOperation migrateReferences = new OJAnnotatedOperation("migrateReferences");
		migrator.addToOperations(migrateReferences);
		migrateReferences.addParam("from", classifierPathName(fromEntity, getFromVersion()));
		migrateReferences.addParam("result", classifierPathName(toEntity, getToVersion()));
		MigratorContext ctx2 = new MigratorContext(fromEntity, toEntity, migrator, migrateReferences, "");
		if(toEntity.getSupertype() != null){
			migrateInheritedReferences(ctx2);
		}
		for(INakedProperty toProperty:toEntity.getDirectlyImplementedAttributes()){
			if(isReference(toProperty) && toProperty.getNakedBaseType() instanceof ICompositionParticipant){
				migrateReference(ctx2, toProperty);
			}else if(toProperty.getNakedBaseType() instanceof INakedStructuredDataType
					&& !needsCustomCalculation(toProperty, findMatchingProperty(fromEntity, toProperty))){
				OJAnnotatedOperation resolve = buildMigrateReferenceOnStructuredDataType(ctx2, toProperty, findMatchingProperty(fromEntity, toProperty));
				if(OJUtil.buildStructuralFeatureMap(toProperty).isMany()){
					OJForStatement forEach = new OJForStatement("tmp", classifierPathName(toProperty.getNakedBaseType(), getToVersion()), "result."
							+ OJUtil.buildStructuralFeatureMap(toProperty).getter() + "()");
					ctx2.migratingOperation.getBody().addToStatements(forEach);
					forEach.getBody().addToStatements(resolve.getName() + "(result,tmp))");
				}else{
					ctx2.migratingOperation.getBody().addToStatements(
							resolve.getName() + "(result,result." + OJUtil.buildStructuralFeatureMap(toProperty).getter() + "())");
				}
			}
		}
	}
	private boolean isReference(INakedProperty toProperty){
		boolean participatesInComposition = toProperty.isComposite() || (toProperty.getOtherEnd() != null && toProperty.getOtherEnd().isComposite());
		return !(toProperty.isDerived() || toProperty.isReadOnly() || participatesInComposition || toProperty.isInverse()) && isPersistent(toProperty.getNakedBaseType());
	}
	private void migrateReference(MigratorContext ctx2,INakedProperty toProperty){
		INakedProperty fromProperty = findMatchingProperty(ctx2.fromClass, toProperty);
		OJPathName toBaseType = classifierPathName(toProperty.getNakedBaseType(), getToVersion());
		ctx2.migrator.addToImports(toBaseType);
		NakedStructuralFeatureMap toMap = OJUtil.buildStructuralFeatureMap(toProperty);
		String resultVarName = "result" + ctx2.prefix;
		if(needsCustomCalculation(toProperty, fromProperty)){
			OJAnnotatedOperation calc = addCalculator(ctx2, toProperty);
			ctx2.migratingOperation.getBody().addToStatements(resultVarName + "." + toMap.setter() + "(" + calc.getName() + "(from" + ctx2.prefix + "))");
		}else{
			NakedStructuralFeatureMap fromMap = OJUtil.buildStructuralFeatureMap(fromProperty);
			String fromVarName = "from" + ctx2.prefix;
			if(toMap.isMany()){
				if(fromMap.isOne()){
					ctx2.migratingOperation.getBody().addToStatements(
							new OJIfStatement(fromVarName + "." + fromMap.getter() + "()!=null", resultVarName + "." + toMap.adder() + "((" + toBaseType.getLast()
									+ ")context.resolveByUuid(" + fromVarName + "." + fromMap.getter() + "().getUid()))"));
				}else{
					OJForStatement forEach = new OJForStatement("tmp", classifierPathName(fromProperty.getNakedBaseType(), getFromVersion()), "from." + fromMap.getter()
							+ "()");
					ctx2.migratingOperation.getBody().addToStatements(forEach);
					forEach.getBody().addToStatements("result." + toMap.adder() + "((" + toBaseType.getLast() + ")context.resolveByUuid(tmp.getUid()))");
				}
			}else{
				ctx2.migratingOperation.getBody().addToStatements(
						new OJIfStatement(fromVarName + "." + fromMap.getter() + "()!=null", resultVarName + "." + toMap.setter() + "((" + toBaseType.getLast()
								+ ")context.resolveByUuid(" + fromVarName + "." + fromMap.getter() + "().getUid()))"));
			}
		}
	}
	private void buildMigrateDataTypeProperties(ICompositionParticipant toEntity,ICompositionParticipant fromEntity,OJAnnotatedClass migrator){
		OJAnnotatedOperation migrateDataTypeProperties = new OJAnnotatedOperation("migrateDataTypeProperties");
		migrator.addToOperations(migrateDataTypeProperties);
		migrateDataTypeProperties.addParam("from", classifierPathName(fromEntity, getFromVersion()));
		migrateDataTypeProperties.addParam("result", classifierPathName(toEntity, getToVersion()));
		MigratorContext ctx = new MigratorContext(fromEntity, toEntity, migrator, migrateDataTypeProperties, "");
		if(toEntity.getSupertype() != null){
			migrateInheritedDataTypeProperties(ctx);
		}
		for(INakedProperty toProperty:toEntity.getDirectlyImplementedAttributes()){
			if(!(toProperty.isDerived() || toProperty.isReadOnly())){
				if(toProperty.getNakedBaseType() instanceof INakedDataType){
					migrateDataTypeValue(ctx, toProperty);
				}
			}
		}
	}
	private void generateMigratorContractForInterface(INakedComplexStructure to){
		INakedInterface toInterface = (INakedInterface) to;
		INakedInterface fromInterface = (INakedInterface) fromWorkspace.getModelElement(toInterface.getId());
		if(fromInterface != null && toInterface.getEndToComposite() != null
				&& !(needsCustomCalculation(toInterface.getEndToComposite(), fromInterface.getEndToComposite()))){
			OJPathName migratorPath = migratorPath(toInterface);
			OJPackage pkg = javaModel.findPackage(migratorPath.getHead());
			OJAnnotatedInterface migrator = new OJAnnotatedInterface(migratorPath.getLast());
			pkg.addToClasses(migrator);
			OJAnnotatedOperation migrateTree = new OJAnnotatedOperation("migrateTree", classifierPathName(toInterface, getToVersion()));
			migrator.addToOperations(migrateTree);
			migrateTree.addParam("owner", classifierPathName(toInterface.getEndToComposite().getNakedBaseType(), getToVersion()));
			migrateTree.addParam("from", classifierPathName(fromInterface, getFromVersion()));
		}
	}
	private void implementMigatorInterfaces(OJAnnotatedClass migrator,INakedBehavioredClassifier bc){
		for(INakedInterfaceRealization ir:bc.getInterfaceRealizations()){
			INakedInterface toInterface = ir.getContract();
			INakedInterface fromInterface = (INakedInterface) fromWorkspace.getModelElement(toInterface.getId());
			if(fromInterface != null && toInterface.getEndToComposite() != null
					&& !(needsCustomCalculation(toInterface.getEndToComposite(), fromInterface.getEndToComposite()))){
				migrator.addToImplementedInterfaces(migratorPath(toInterface));
				OJAnnotatedOperation migrateTree = new OJAnnotatedOperation("migrateTree", migratorPath(toInterface));
				migrator.addToOperations(migrateTree);
				migrateTree.addParam("owner", classifierPathName(toInterface.getEndToComposite().getNakedBaseType(), getToVersion()));
				migrateTree.addParam("from", classifierPathName(fromInterface, getFromVersion()));
				migrateTree.getBody().addToStatements("migrateTreeImpl(owner,from)");
			}
		}
	}
	private void addMigrationEntryPoint(ICompositionParticipant toEntity,ICompositionParticipant fromEntity,OJAnnotatedClass migrator){
		if(toEntity.getEndToComposite() != null){
			OJPathName ownerPath = classifierPathName(toEntity.getEndToComposite().getNakedBaseType(), getToVersion());
			if(needsCustomCalculation(toEntity.getEndToComposite(), fromEntity.getEndToComposite())){
				// Containment tree changed. Not much we can do as we don't know the entities' positions in their respective
				// containment trees. Force the developer to think
				OJAnnotatedOperation populateChildren = new OJAnnotatedOperation("populate"
						+ toEntity.getEndToComposite().getOtherEnd().getMappingInfo().getJavaName().getCapped(), classifierPathName(toEntity, getToVersion()));
				migrator.addToOperations(populateChildren);
				populateChildren.addParam("owner", ownerPath);
				if(fromEntity.getEndToComposite() != null){
					populateChildren.addParam("fromOwner", classifierPathName(fromEntity.getEndToComposite().getNakedBaseType(), getToVersion()));
				}
				populateChildren.setAbstract(true);
			}else if(!toEntity.getIsAbstract()){
				OJAnnotatedOperation migrateTree = new OJAnnotatedOperation("migrateTree", classifierPathName(toEntity, getToVersion()));
				migrator.addToOperations(migrateTree);
				migrateTree.addParam("owner", ownerPath);
				migrateTree.addParam("from", classifierPathName((INakedClassifier) fromWorkspace.getModelElement(toEntity.getId()), getFromVersion()));
				migrateTree.getBody().addToStatements("context.push()");
				migrateTree.initializeResultVariable("new " + toEntity.getMappingInfo().getJavaName() + getToVersion().getSuffix() + "((" + ownerPath.getLast()
						+ ")context.reloadToObject(owner))");
				migrateTree.getBody().addToStatements("from=context.reloadFromObject(from)");
				migrateTree.getBody().addToStatements("migrateTreeImpl(owner,from,result)");
			}
		}else if(!toEntity.getIsAbstract()){
			OJAnnotatedOperation migrateTree = new OJAnnotatedOperation("migrateTree", classifierPathName(toEntity, getToVersion()));
			migrator.addToOperations(migrateTree);
			migrateTree.addParam("from", classifierPathName((INakedClassifier) fromWorkspace.getModelElement(toEntity.getId()), getFromVersion()));
			migrateTree.getBody().addToStatements("context.push()");
			migrateTree.initializeResultVariable("new " + toEntity.getMappingInfo().getJavaName() + getToVersion().getSuffix() + "()");
			migrateTree.getBody().addToStatements("from=context.reloadFromObject(from)");
			migrateTree.getBody().addToStatements("migrateTreeImpl(from,result)");
		}
	}
	private void migrateInheritedDataTypeProperties(MigratorContext ctx){
		if(ctx.fromClass.getSupertype() == null || !ctx.fromClass.getSupertype().getId().equals(ctx.toClass.getSupertype().getId())){
			// Inheritance tree changed. Force the developer to think it through
			OJAnnotatedOperation calculateSuperFields = new OJAnnotatedOperation("calculateInheritedDataTypeProperties");
			ctx.migrator.addToOperations(calculateSuperFields);
			calculateSuperFields.addParam("from", classifierPathName((INakedClassifier) fromWorkspace.getModelElement(ctx.toClass.getId()), getFromVersion()));
			calculateSuperFields.addParam("result", classifierPathName(ctx.toClass.getSupertype(), getToVersion()));
			calculateSuperFields.setAbstract(true);
			ctx.migratingOperation.getBody().addToStatements("calculateInheritedDataTypeProperties(from,result)");
		}else{
			ctx.migrator.setSuperclass(migratorPath((ICompositionParticipant) ctx.toClass.getSupertype()));
			ctx.migratingOperation.getBody().addToStatements("super.migrateDataTypeProperties(from,result)");
		}
	}
	private void migrateInheritedCompositeProperties(MigratorContext ctx){
		if(ctx.fromClass.getSupertype() == null || !ctx.fromClass.getSupertype().getId().equals(ctx.toClass.getSupertype().getId())){
			// Inheritance tree changed. Force the developer to think it through
			OJAnnotatedOperation calculateSuperFields = new OJAnnotatedOperation("calculateInheritedCompositeProperties");
			ctx.migrator.addToOperations(calculateSuperFields);
			calculateSuperFields.addParam("from", classifierPathName((INakedClassifier) fromWorkspace.getModelElement(ctx.toClass.getId()), getFromVersion()));
			calculateSuperFields.addParam("result", classifierPathName(ctx.toClass.getSupertype(), getToVersion()));
			calculateSuperFields.setAbstract(true);
			ctx.migratingOperation.getBody().addToStatements("calculateInheritedCompositeProperties(from,result)");
		}else{
			ctx.migrator.setSuperclass(migratorPath((ICompositionParticipant) ctx.toClass.getSupertype()));
			ctx.migratingOperation.getBody().addToStatements("super.migrateCompositeProperties(from,result)");
		}
	}
	private void migrateInheritedReferences(MigratorContext ctx){
		if(ctx.fromClass.getSupertype() == null || !ctx.fromClass.getSupertype().getId().equals(ctx.toClass.getSupertype().getId())){
			// Inheritance tree changed. Force the developer to think it through
			OJAnnotatedOperation calculateSuperFields = new OJAnnotatedOperation("calculateInheritedReferences");
			ctx.migrator.addToOperations(calculateSuperFields);
			calculateSuperFields.addParam("from", classifierPathName((INakedClassifier) fromWorkspace.getModelElement(ctx.toClass.getId()), getFromVersion()));
			calculateSuperFields.addParam("result", classifierPathName(ctx.toClass.getSupertype(), getToVersion()));
			calculateSuperFields.setAbstract(true);
			ctx.migratingOperation.getBody().addToStatements("calculateInheritedReferences(from,result)");
		}else{
			ctx.migrator.setSuperclass(migratorPath((ICompositionParticipant) ctx.toClass.getSupertype()));
			ctx.migratingOperation.getBody().addToStatements("super.migrateReferences(from,result)");
		}
	}
	private void migrateComposites(MigratorContext ctx,INakedProperty toProperty){
		NakedStructuralFeatureMap toMap = OJUtil.buildStructuralFeatureMap(toProperty);
		INakedProperty fromProp = findMatchingProperty(ctx.fromClass, toProperty);
		if(!needsCustomCalculation(toProperty, fromProp)){
			NakedStructuralFeatureMap fromMap = OJUtil.buildStructuralFeatureMap(fromProp);
			OJPathName migratorPath2 = migratorPath((ICompositionParticipant) toProperty.getNakedBaseType());
			ctx.migrator.addToImports(migratorPath2);
			if(toMap.isMany() && fromMap.isMany()){
				OJForStatement forEach = new OJForStatement("tmp", classifierPathName(fromProp.getNakedBaseType(), getFromVersion()), "from." + fromMap.getter() + "()");
				ctx.migratingOperation.getBody().addToStatements(forEach);
				OJAnnotatedField migrator = new OJAnnotatedField("migrator", migratorPath2);
				migrator.setInitExp("(" + migratorPath2.getLast() + ")context.getMigratorFor(tmp)");
				forEach.getBody().addToLocals(migrator);
				forEach.getBody().addToStatements("migrator.migrateTree(result,tmp)");
			}else{
				String thenPart = "migrator.migrateTree(result,from." + fromMap.getter() + "())";
				OJIfStatement ifNotNull = new OJIfStatement("from." + fromMap.getter() + "()!=null", thenPart);
				OJAnnotatedField migrator = new OJAnnotatedField("migrator", migratorPath2);
				migrator.setInitExp("(" + migratorPath2.getLast() + ")context.getMigratorFor(from." + fromMap.getter() + "())");
				ifNotNull.getThenPart().addToLocals(migrator);
				ctx.migratingOperation.getBody().addToStatements(ifNotNull);
			}
		}else{
			OJPathName baseTypePath = classifierPathName(toProperty.getNakedBaseType(), getToVersion());
			OJPathName pn = baseTypePath;
			if(toMap.isMany()){
				OJPathName coll = new OJPathName("java.util.Collection");
				coll.addToElementTypes(pn);
			}
			OJAnnotatedOperation calc = new OJAnnotatedOperation("calc" + ctx.prefix + toProperty.getMappingInfo().getJavaName().getCapped(), pn);
			calc.addParam("from", classifierPathName(ctx.fromClass, getFromVersion()));
			ctx.migrator.addToOperations(calc);
			if(fromWorkspace.getModelElement(toProperty.getNakedBaseType().getId()) == null){
				if(toMap.isMany()){
					calc.initializeResultVariable("new ArrayList<" + baseTypePath.getLast() + ">()");
				}else{
					calc.initializeResultVariable("null");
				}
			}else{
				calc.setAbstract(true);
			}
		}
	}
	private INakedProperty findMatchingProperty(INakedClassifier fromEntity,INakedProperty toProperty){
		INakedProperty result = (INakedProperty) fromEntity.getOwnedElement(toProperty.getId());
		if(result == null){
			result = fromEntity.findEffectiveAttribute(toProperty.getName());
		}
		return result;
	}
	private void migrateDataTypeValue(MigratorContext ctx,INakedProperty toProperty){
		INakedProperty fromProperty = findMatchingProperty(ctx.fromClass, toProperty);
		if(needsCustomCalculation(toProperty, fromProperty)){
			NakedStructuralFeatureMap toMap = OJUtil.buildStructuralFeatureMap(toProperty);
			OJAnnotatedOperation calc = addCalculator(ctx, toProperty);
			ctx.migratingOperation.getBody().addToStatements("result." + toMap.setter() + "(" + calc.getName() + "(from))");
		}else{
			NakedStructuralFeatureMap fromMap = OJUtil.buildStructuralFeatureMap(fromProperty);
			NakedStructuralFeatureMap toMap = OJUtil.buildStructuralFeatureMap(toProperty);
			if(toProperty.getNakedBaseType() instanceof INakedSimpleType){
				if(toMap.isOne()){
					ctx.migratingOperation.getBody().addToStatements("result." + toMap.setter() + "(from." + fromMap.getter() + "())");
				}else if(fromMap.isOne()){
					ctx.migratingOperation.getBody().addToStatements(
							new OJIfStatement("from." + fromMap.getter() + "()!=null", "result." + toMap.adder() + "(from." + fromMap.getter() + "())"));
				}else{
					OJPathName ojType = classifierPathName(fromProperty.getNakedBaseType(), getFromVersion());
					OJForStatement forEach = new OJForStatement("tmp", ojType, "from." + fromMap.getter() + "()");
					ctx.migratingOperation.getBody().addToStatements(forEach);
					forEach.getBody().addToStatements("result." + toMap.adder() + "(tmp)");
				}
			}else{
				OJAnnotatedOperation converter = null;
				if(toProperty.getNakedBaseType() instanceof INakedEnumeration){
					converter = migrateEnumerationValue(ctx, toProperty, fromProperty);
				}else if(toProperty.getNakedBaseType() instanceof INakedStructuredDataType){
					converter = migrateStructuredDataTypeDataTypeProperties(ctx, toProperty, fromProperty);
				}else{
				}
				if(toMap.isOne()){
					ctx.migratingOperation.getBody().addToStatements("result." + toMap.setter() + "(" + converter.getName() + "(from,from." + fromMap.getter() + "()))");
				}else if(fromMap.isOne()){
					String invocation = "result." + toMap.adder() + "(" + converter.getName() + "(from, from." + fromMap.getter() + "())";
					ctx.migratingOperation.getBody().addToStatements(new OJIfStatement("from." + fromMap.getter() + "()!=null", invocation));
				}else{
					OJPathName ojType = classifierPathName(fromProperty.getNakedBaseType(), getFromVersion());
					OJForStatement forEach = new OJForStatement("tmp", ojType, "from." + fromMap.getter() + "()");
					ctx.migratingOperation.getBody().addToStatements(forEach);
					String invocation = "result." + toMap.adder() + "(" + converter.getName() + "(from, tmp))";
					forEach.getBody().addToStatements(invocation);
				}
			}
		}
	}
	private boolean needsCustomCalculation(INakedProperty toProperty,INakedProperty fromProperty){
		return fromProperty == null || !haveCompatibleTypes(toProperty, fromProperty) || !fromProperty.fitsInTo(toProperty);
	}
	private boolean haveCompatibleTypes(INakedProperty toProperty,INakedProperty fromProperty){
		return fromProperty.getNakedBaseType().getId().equals(toProperty.getNakedBaseType().getId())
				|| fromProperty.getNakedBaseType().getMappingInfo().getQualifiedJavaName().equals(toProperty.getNakedBaseType().getMappingInfo().getQualifiedJavaName());
	}
	private OJAnnotatedOperation migrateStructuredDataTypeDataTypeProperties(MigratorContext ctx,INakedProperty toProperty,INakedProperty fromProperty){
		OJPathName toPathName = classifierPathName(toProperty.getNakedBaseType(), getToVersion());
		OJAnnotatedOperation convertStruct = new OJAnnotatedOperation("convert" + ctx.prefix + toProperty.getMappingInfo().getJavaName().getCapped());
		ctx.migrator.addToOperations(convertStruct);
		convertStruct.setReturnType(classifierPathName(toProperty.getNakedBaseType(), getToVersion()));
		convertStruct.addParam("fromOwner", classifierPathName(ctx.fromClass, getFromVersion()));
		convertStruct.addParam("from", classifierPathName(fromProperty.getNakedBaseType(), getFromVersion()));
		convertStruct.getBody().addToStatements(new OJIfStatement("from==null", "return null"));
		convertStruct.initializeResultVariable("new " + toPathName.getLast() + "()");
		INakedStructuredDataType fromStruct = (INakedStructuredDataType) fromProperty.getNakedBaseType();
		INakedStructuredDataType toStruct = (INakedStructuredDataType) toProperty.getNakedBaseType();
		MigratorContext childCtx = new MigratorContext(fromStruct, toStruct, ctx.migrator, convertStruct, ctx.prefix
				+ toProperty.getMappingInfo().getJavaName().getCapped());
		for(INakedProperty toProp:toStruct.getEffectiveAttributes()){
			if(toProp.getNakedBaseType() instanceof INakedDataType && !toProp.isDerived() && !toProp.isReadOnly()){
				migrateDataTypeValue(childCtx, toProp);
			}
		}
		return convertStruct;
	}
	private OJAnnotatedOperation buildMigrateReferenceOnStructuredDataType(MigratorContext ctx,INakedProperty toProperty,INakedProperty fromProperty){
		OJAnnotatedOperation resolve = new OJAnnotatedOperation("migrate" + ctx.prefix + toProperty.getMappingInfo().getJavaName().getCapped());
		ctx.migrator.addToOperations(resolve);
		resolve.addParam("owner", classifierPathName(ctx.toClass, getToVersion()));
		resolve.addParam("result", classifierPathName(toProperty.getNakedBaseType(), getToVersion()));
		resolve.getBody().addToStatements(new OJIfStatement("result==null", "return"));
		INakedStructuredDataType fromStruct = (INakedStructuredDataType) fromProperty.getNakedBaseType();
		INakedStructuredDataType toStruct = (INakedStructuredDataType) toProperty.getNakedBaseType();
		MigratorContext childCtx = new MigratorContext(fromStruct, toStruct, ctx.migrator, resolve, ctx.prefix + toProperty.getMappingInfo().getJavaName().getCapped());
		for(INakedProperty toProp:toStruct.getEffectiveAttributes()){
			if(isReference(toProp)){
				childCtx.migratingOperation.getBody().addToStatements(new OJIfStatement());
				migrateReference(childCtx, toProp);
			}
		}
		return resolve;
	}
	private OJAnnotatedOperation migrateEnumerationValue(MigratorContext ctx,INakedProperty toProperty,INakedProperty fromProperty){
		OJPathName toPathName = classifierPathName(toProperty.getNakedBaseType(), getToVersion());
		OJAnnotatedOperation convertEnum = new OJAnnotatedOperation("convert" + toProperty.getMappingInfo().getJavaName(), classifierPathName(
				toProperty.getNakedBaseType(), getToVersion()));
		ctx.migrator.addToOperations(convertEnum);
		convertEnum.addParam("fromOwner", classifierPathName(ctx.fromClass, getFromVersion()));
		convertEnum.addParam("from", classifierPathName(fromProperty.getNakedBaseType(), getFromVersion()));
		convertEnum.getBody().addToStatements(new OJIfStatement("from==null", "return null"));
		convertEnum.initializeResultVariable("null");
		OJSwitchStatement switchLiteral = new OJSwitchStatement();
		convertEnum.getBody().addToStatements(switchLiteral);
		switchLiteral.setCondition("from");
		INakedEnumeration fromEnum = (INakedEnumeration) fromProperty.getNakedBaseType();
		INakedEnumeration toEnum = (INakedEnumeration) toProperty.getNakedBaseType();
		OJAnnotatedOperation calc = null;
		for(INakedEnumerationLiteral fromLit:fromEnum.getOwnedLiterals()){
			INakedEnumerationLiteral toLit = (INakedEnumerationLiteral) toEnum.getOwnedElement(fromLit.getId());
			if(toLit == null){
				toLit = (INakedEnumerationLiteral) toEnum.lookupLiteral(fromLit.getName());
			}
			OJSwitchCase caseLit = new OJSwitchCase();
			switchLiteral.addToCases(caseLit);
			caseLit.setLabel(OJUtil.toJavaLiteral(fromLit));
			if(toLit == null){
				if(calc == null){
					calc = addCalculator(ctx, toProperty);
				}
				caseLit.getBody().addToStatements("result=" + calc.getName() + "(from)");
			}else{
				caseLit.getBody().addToStatements("result =" + toPathName.getLast() + "." + OJUtil.toJavaLiteral(toLit));
			}
		}
		return convertEnum;
	}
	private OJAnnotatedOperation addCalculator(MigratorContext ctx,INakedProperty toProperty){
		String name = "calculate" + ctx.prefix + toProperty.getMappingInfo().getJavaName().getCapped();
		OJPathName resultPath = classifierPathName(toProperty.getNakedBaseType(), getToVersion());
		NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(toProperty);
		if(map.isMany()){
			OJPathName p = map.javaTypePath();
			p.removeAllFromElementTypes();
			p.addToElementTypes(resultPath);
			resultPath = p;
		}
		OJAnnotatedOperation calc = new OJAnnotatedOperation(name, resultPath);
		ctx.migrator.addToOperations(calc);
		calc.addParam("from", classifierPathName(ctx.fromClass, getFromVersion()));
		if(toProperty.isRequired()){
			calc.setAbstract(true);
		}else if(map.isMany()){
			OJPathName javaDefaultTypePath = map.javaDefaultTypePath();
			javaDefaultTypePath.removeAllFromElementTypes();
			ctx.migrator.addToImports(javaDefaultTypePath);
			calc.initializeResultVariable("new " + javaDefaultTypePath.getLast() + "<" + resultPath.getElementTypes().get(0).getLast() + ">()");
		}else{
			calc.initializeResultVariable("null");
		}
		return calc;
	}
}
