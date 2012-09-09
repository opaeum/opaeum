package org.opaeum.javageneration.migration;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.BehavioredClassifier;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.InterfaceRealization;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfClassifierUtil;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.EmfPropertyUtil;
import org.opaeum.emf.workspace.EmfWorkspace;
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
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.name.NameConverter;
import org.opaeum.runtime.environment.IMigrator;
import org.opaeum.textmetamodel.JavaSourceFolderIdentifier;

@StepDependency(phase = MigrationGenerationPhase.class)
public class MigratorGenerator extends AbstractMigrationCodeGenerator{
	private static class MigratorContext{
		private Classifier fromClass;
		private Classifier toClass;
		private OJAnnotatedClass migrator;
		private OJAnnotatedOperation migratingOperation;
		private String prefix;
		private MigratorContext(Classifier fromClass,Classifier toClass,OJAnnotatedClass migrator,OJAnnotatedOperation migratingOperation,
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
	@Override
	protected boolean visitComplexStructure(OJAnnotatedClass ojTo, Classifier to){
		if(EmfClassifierUtil.isCompositionParticipant(to )){
			if(to instanceof Interface){
				generateMigratorContractForInterface(to);
			}else if(isPersistent(to)){
				Classifier toClass = (Classifier) to;
				Classifier fromClass = (Classifier) fromWorkspace.getModelElement(EmfWorkspace.getId(toClass));
				if(fromClass != null){
					OJPathName migratorPath = migratorPath(toClass);
					OJPackage pkg = findOrCreatePackage(migratorPath.getHead());
					OJAnnotatedClass migratorGen = generateMigrationContract(toClass, migratorPath, pkg);
					generateImplementationClass(migratorPath, pkg, migratorGen);
					addMigrationEntryPoint(toClass, fromClass, migratorGen);
					buildMigrateTreeImpl(toClass, fromClass, migratorGen);
					buildMigrateDataTypeProperties(toClass, fromClass, migratorGen);
					buildMigrateCompositeProperties(toClass, fromClass, migratorGen);
					buildMigrateReferences(toClass, fromClass, migratorGen);
				}
			}
		}
		return false;
	}
	private OJAnnotatedClass generateMigrationContract(Classifier toClass,OJPathName migratorPath,OJPackage pkg){
		OJAnnotatedClass migratorGen = new OJAnnotatedClass(migratorPath.getLast() + "GEN");
		pkg.addToClasses(migratorGen);
		migratorGen.setAbstract(true);
		createTextPath(migratorGen, JavaSourceFolderIdentifier.MIGRATION_GEN_SRC);
		migratorGen.addToImplementedInterfaces(new OJPathName(IMigrator.class.getName()));
		OJUtil.addTransientProperty(migratorGen, "context", new OJPathName("org.opaeum.runtime.environment.MigrationContext"), true);
		if(toClass instanceof BehavioredClassifier){
			implementMigatorInterfaces(migratorGen, (BehavioredClassifier) toClass);
		}
		if(toClass.getGenerals().size() >= 1){
			migratorGen.setSuperclass(migratorPath(toClass.getGenerals().get(0)));
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
	private void buildMigrateTreeImpl(Classifier toClass,Classifier fromClass,OJAnnotatedClass migrator){
		OJAnnotatedOperation migrateTreeImpl = new OJAnnotatedOperation("migrateTreeImpl");
		migrator.addToOperations(migrateTreeImpl);
		if(getLibrary().getEndToComposite( toClass) != null){
			migrateTreeImpl.addParam("owner", classifierPathName(getLibrary().getEndToComposite( toClass).getType(), getToVersion()));
		}else{
		}
		migrateTreeImpl.addParam("from", classifierPathName(fromClass, getFromVersion()));
		migrateTreeImpl.addParam("result", classifierPathName(toClass, getToVersion()));
		migrateTreeImpl.getBody().addToStatements("");
		migrateTreeImpl.getBody().addToStatements("migrateDataTypeProperties(from,result)");
		migrateTreeImpl.getBody().addToStatements("context.persistToObject(result)");
		migrateTreeImpl.getBody().addToStatements("context.pop()");
		migrateTreeImpl.getBody().addToStatements("context.push()");
		migrateTreeImpl.getBody().addToStatements("from=context.reloadFromObject(from)");
		migrateTreeImpl.getBody().addToStatements("migrateCompositeProperties(from,result)");
	}
	private void buildMigrateCompositeProperties(Classifier toClass,Classifier fromClass,OJAnnotatedClass migrator){
		OJAnnotatedOperation migrateComposites = new OJAnnotatedOperation("migrateCompositeProperties");
		migrator.addToOperations(migrateComposites);
		migrateComposites.addParam("from", classifierPathName(fromClass, getFromVersion()));
		migrateComposites.addParam("result", classifierPathName(toClass, getToVersion()));
		MigratorContext ctx2 = new MigratorContext(fromClass, toClass, migrator, migrateComposites, "");
		if(toClass.getGenerals().size() > 0){
			migrateInheritedCompositeProperties(ctx2);
		}
		for(Property toProperty: getLibrary().getDirectlyImplementedAttributes( toClass)){
			if(!(EmfPropertyUtil.isDerived( toProperty) || toProperty.isReadOnly()) && isPersistent(toProperty.getType())){
				if(toProperty.isComposite() && EmfClassifierUtil.isCompositionParticipant((Classifier) toProperty.getType())){
					migrateComposites(ctx2, toProperty);
				}
			}
		}
	}
	private void buildMigrateReferences(Classifier toClass,Classifier fromClass,OJAnnotatedClass migrator){
		OJAnnotatedOperation migrateReferences = new OJAnnotatedOperation("migrateReferences");
		migrator.addToOperations(migrateReferences);
		migrateReferences.addParam("from", classifierPathName(fromClass, getFromVersion()));
		migrateReferences.addParam("result", classifierPathName(toClass, getToVersion()));
		MigratorContext ctx2 = new MigratorContext(fromClass, toClass, migrator, migrateReferences, "");
		if(toClass.getGenerals().size() > 0){
			migrateInheritedReferences(ctx2);
		}
		for(Property toProperty: getLibrary().getDirectlyImplementedAttributes( toClass)){
			if(isReference(toProperty) && EmfClassifierUtil.isCompositionParticipant((Classifier) toProperty.getType() )){
				migrateReference(ctx2, toProperty);
			}else if(EmfClassifierUtil.isStructuredDataType(toProperty.getType())
					&& !needsCustomCalculation(toProperty, findMatchingProperty(fromClass, toProperty))){
				OJAnnotatedOperation resolve = buildMigrateReferenceOnStructuredDataType(ctx2, toProperty,
						findMatchingProperty(fromClass, toProperty));
				if(ojUtil.buildStructuralFeatureMap(toProperty).isMany()){
					OJForStatement forEach = new OJForStatement("tmp", classifierPathName((Classifier) toProperty.getType(), getToVersion()),
							"result." + ojUtil.buildStructuralFeatureMap(toProperty).getter() + "()");
					ctx2.migratingOperation.getBody().addToStatements(forEach);
					forEach.getBody().addToStatements(resolve.getName() + "(result,tmp))");
				}else{
					ctx2.migratingOperation.getBody().addToStatements(
							resolve.getName() + "(result,result." + ojUtil.buildStructuralFeatureMap(toProperty).getter() + "())");
				}
			}
		}
	}
	private boolean isReference(Property toProperty){
		boolean participatesInComposition = toProperty.isComposite()
				|| (toProperty.getOtherEnd() != null && toProperty.getOtherEnd().isComposite());
		return !(EmfPropertyUtil.isDerived( toProperty) || toProperty.isReadOnly() || participatesInComposition || EmfPropertyUtil.isInverse(toProperty))
				&& isPersistent(toProperty.getType());
	}
	private void migrateReference(MigratorContext ctx2,Property toProperty){
		Property fromProperty = findMatchingProperty(ctx2.fromClass, toProperty);
		OJPathName toBaseType = classifierPathName((Classifier) toProperty.getType(), getToVersion());
		ctx2.migrator.addToImports(toBaseType);
		PropertyMap toMap = ojUtil.buildStructuralFeatureMap(toProperty);
		String resultVarName = "result" + ctx2.prefix;
		if(needsCustomCalculation(toProperty, fromProperty)){
			OJAnnotatedOperation calc = addCalculator(ctx2, toProperty);
			ctx2.migratingOperation.getBody().addToStatements(
					resultVarName + "." + toMap.setter() + "(" + calc.getName() + "(from" + ctx2.prefix + "))");
		}else{
			PropertyMap fromMap = ojUtil.buildStructuralFeatureMap(fromProperty);
			String fromVarName = "from" + ctx2.prefix;
			if(toMap.isMany()){
				if(fromMap.isOne()){
					ctx2.migratingOperation.getBody().addToStatements(
							new OJIfStatement(fromVarName + "." + fromMap.getter() + "()!=null", resultVarName + "." + toMap.adder() + "(("
									+ toBaseType.getLast() + ")context.resolveByUuid(" + fromVarName + "." + fromMap.getter() + "().getUid()))"));
				}else{
					OJForStatement forEach = new OJForStatement("tmp", classifierPathName(fromProperty.getType(), getFromVersion()), "from."
							+ fromMap.getter() + "()");
					ctx2.migratingOperation.getBody().addToStatements(forEach);
					forEach.getBody().addToStatements(
							"result." + toMap.adder() + "((" + toBaseType.getLast() + ")context.resolveByUuid(tmp.getUid()))");
				}
			}else{
				ctx2.migratingOperation.getBody().addToStatements(
						new OJIfStatement(fromVarName + "." + fromMap.getter() + "()!=null", resultVarName + "." + toMap.setter() + "(("
								+ toBaseType.getLast() + ")context.resolveByUuid(" + fromVarName + "." + fromMap.getter() + "().getUid()))"));
			}
		}
	}
	private void buildMigrateDataTypeProperties(Classifier toClass,Classifier fromClass,OJAnnotatedClass migrator){
		OJAnnotatedOperation migrateDataTypeProperties = new OJAnnotatedOperation("migrateDataTypeProperties");
		migrator.addToOperations(migrateDataTypeProperties);
		migrateDataTypeProperties.addParam("from", classifierPathName(fromClass, getFromVersion()));
		migrateDataTypeProperties.addParam("result", classifierPathName(toClass, getToVersion()));
		MigratorContext ctx = new MigratorContext(fromClass, toClass, migrator, migrateDataTypeProperties, "");
		if(toClass.getGenerals().size() > 0){
			migrateInheritedDataTypeProperties(ctx);
		}
		for(Property toProperty: getLibrary().getDirectlyImplementedAttributes( toClass)){
			if(!(EmfPropertyUtil.isDerived( toProperty) || toProperty.isReadOnly())){
				if(toProperty.getType() instanceof DataType){
					migrateDataTypeValue(ctx, toProperty);
				}
			}
		}
	}
	private void generateMigratorContractForInterface(Classifier to){
		Interface toInterface = (Interface) to;
		Interface fromInterface = (Interface) fromWorkspace.getModelElement(EmfWorkspace.getId(toInterface));
		if(fromInterface != null && getLibrary().getEndToComposite(toInterface) != null
				&& !(needsCustomCalculation(getLibrary().getEndToComposite(toInterface), getLibrary().getEndToComposite(fromInterface)))){
			OJPathName migratorPath = migratorPath(toInterface);
			OJPackage pkg = javaModel.findPackage(migratorPath.getHead());
			OJAnnotatedInterface migrator = new OJAnnotatedInterface(migratorPath.getLast());
			pkg.addToClasses(migrator);
			OJAnnotatedOperation migrateTree = new OJAnnotatedOperation("migrateTree", classifierPathName(toInterface, getToVersion()));
			migrator.addToOperations(migrateTree);
			migrateTree.addParam("owner", classifierPathName(getLibrary().getEndToComposite(toInterface).getType(), getToVersion()));
			migrateTree.addParam("from", classifierPathName(fromInterface, getFromVersion()));
		}
	}
	private void implementMigatorInterfaces(OJAnnotatedClass migrator,BehavioredClassifier bc){
		for(InterfaceRealization ir:bc.getInterfaceRealizations()){
			Interface toInterface = ir.getContract();
			Interface fromInterface = (Interface) fromWorkspace.getModelElement(EmfWorkspace.getId(toInterface));
			if(fromInterface != null && getLibrary().getEndToComposite(toInterface) != null
					&& !(needsCustomCalculation(getLibrary().getEndToComposite(toInterface), getLibrary().getEndToComposite(fromInterface)))){
				migrator.addToImplementedInterfaces(migratorPath(toInterface));
				OJAnnotatedOperation migrateTree = new OJAnnotatedOperation("migrateTree", migratorPath(toInterface));
				migrator.addToOperations(migrateTree);
				migrateTree.addParam("owner", classifierPathName(getLibrary().getEndToComposite(toInterface).getType(), getToVersion()));
				migrateTree.addParam("from", classifierPathName(fromInterface, getFromVersion()));
				migrateTree.getBody().addToStatements("migrateTreeImpl(owner,from)");
			}
		}
	}
	private void addMigrationEntryPoint(Classifier toClass,Classifier fromClass,OJAnnotatedClass migrator){
		if(getLibrary().getEndToComposite(toClass) != null){
			OJPathName ownerPath = classifierPathName(getLibrary().getEndToComposite(toClass).getType(), getToVersion());
			if(needsCustomCalculation(getLibrary().getEndToComposite(toClass), getLibrary().getEndToComposite(fromClass))){
				// Containment tree changed. Not much we can do as we don't know the entities' positions in their respective
				// containment trees. Force the developer to think
				OJAnnotatedOperation populateChildren = new OJAnnotatedOperation("populate" 
						+ NameConverter.capitalize(getLibrary().getEndToComposite(toClass).getOtherEnd().getName()), classifierPathName(toClass, getToVersion()));
				migrator.addToOperations(populateChildren);
				populateChildren.addParam("owner", ownerPath);
				if(getLibrary().getEndToComposite(fromClass) != null){
					populateChildren.addParam("fromOwner", classifierPathName(getLibrary().getEndToComposite(fromClass).getType(), getToVersion()));
				}
				populateChildren.setAbstract(true);
			}else if(!toClass.isAbstract()){
				OJAnnotatedOperation migrateTree = new OJAnnotatedOperation("migrateTree", classifierPathName(toClass, getToVersion()));
				migrator.addToOperations(migrateTree);
				migrateTree.addParam("owner", ownerPath);
				migrateTree.addParam("from",
						classifierPathName((Classifier) fromWorkspace.getModelElement(EmfWorkspace.getId(toClass)), getFromVersion()));
				migrateTree.getBody().addToStatements("context.push()");
				migrateTree.initializeResultVariable("new " + toClass.getName() + getToVersion().getSuffix() + "((" + ownerPath.getLast()
						+ ")context.reloadToObject(owner))");
				migrateTree.getBody().addToStatements("from=context.reloadFromObject(from)");
				migrateTree.getBody().addToStatements("migrateTreeImpl(owner,from,result)");
			}
		}else if(!toClass.isAbstract()){
			OJAnnotatedOperation migrateTree = new OJAnnotatedOperation("migrateTree", classifierPathName(toClass, getToVersion()));
			migrator.addToOperations(migrateTree);
			migrateTree.addParam("from",
					classifierPathName((Classifier) fromWorkspace.getModelElement(EmfWorkspace.getId(toClass)), getFromVersion()));
			migrateTree.getBody().addToStatements("context.push()");
			migrateTree.initializeResultVariable("new " + toClass.getName() + getToVersion().getSuffix() + "()");
			migrateTree.getBody().addToStatements("from=context.reloadFromObject(from)");
			migrateTree.getBody().addToStatements("migrateTreeImpl(from,result)");
		}
	}
	private void migrateInheritedDataTypeProperties(MigratorContext ctx){
		if(ctx.fromClass.getGenerals().isEmpty()
				|| !EmfWorkspace.getId(ctx.fromClass.getGenerals().get(0)).equals(EmfWorkspace.getId(ctx.toClass.getGenerals().get(0)))){
			// Inheritance tree changed. Force the developer to think it through
			OJAnnotatedOperation calculateSuperFields = new OJAnnotatedOperation("calculateInheritedDataTypeProperties");
			ctx.migrator.addToOperations(calculateSuperFields);
			calculateSuperFields.addParam("from",
					classifierPathName((Classifier) fromWorkspace.getModelElement(EmfWorkspace.getId(ctx.toClass)), getFromVersion()));
			calculateSuperFields.addParam("result", classifierPathName(ctx.toClass.getGenerals().get(0), getToVersion()));
			calculateSuperFields.setAbstract(true);
			ctx.migratingOperation.getBody().addToStatements("calculateInheritedDataTypeProperties(from,result)");
		}else{
			ctx.migrator.setSuperclass(migratorPath(ctx.toClass.getGenerals().get(0)));
			ctx.migratingOperation.getBody().addToStatements("super.migrateDataTypeProperties(from,result)");
		}
	}
	private void migrateInheritedCompositeProperties(MigratorContext ctx){
		if(ctx.fromClass.getGenerals().isEmpty()
				|| !EmfWorkspace.getId(ctx.fromClass.getGenerals().get(0)).equals(EmfWorkspace.getId(ctx.toClass.getGenerals().get(0)))){
			// Inheritance tree changed. Force the developer to think it through
			OJAnnotatedOperation calculateSuperFields = new OJAnnotatedOperation("calculateInheritedCompositeProperties");
			ctx.migrator.addToOperations(calculateSuperFields);
			calculateSuperFields.addParam("from",
					classifierPathName((Classifier) fromWorkspace.getModelElement(EmfWorkspace.getId(ctx.toClass)), getFromVersion()));
			calculateSuperFields.addParam("result", classifierPathName(ctx.toClass.getGenerals().get(0), getToVersion()));
			calculateSuperFields.setAbstract(true);
			ctx.migratingOperation.getBody().addToStatements("calculateInheritedCompositeProperties(from,result)");
		}else{
			ctx.migrator.setSuperclass(migratorPath(ctx.toClass.getGenerals().get(0)));
			ctx.migratingOperation.getBody().addToStatements("super.migrateCompositeProperties(from,result)");
		}
	}
	private void migrateInheritedReferences(MigratorContext ctx){
		if(ctx.fromClass.getGenerals().isEmpty()
				|| !EmfWorkspace.getId(ctx.fromClass.getGenerals().get(0)).equals(EmfWorkspace.getId(ctx.toClass.getGenerals().get(0)))){
			// Inheritance tree changed. Force the developer to think it through
			OJAnnotatedOperation calculateSuperFields = new OJAnnotatedOperation("calculateInheritedReferences");
			ctx.migrator.addToOperations(calculateSuperFields);
			calculateSuperFields.addParam("from",
					classifierPathName((Classifier) fromWorkspace.getModelElement(EmfWorkspace.getId(ctx.toClass)), getFromVersion()));
			calculateSuperFields.addParam("result", classifierPathName(ctx.toClass.getGenerals().get(0), getToVersion()));
			calculateSuperFields.setAbstract(true);
			ctx.migratingOperation.getBody().addToStatements("calculateInheritedReferences(from,result)");
		}else{
			ctx.migrator.setSuperclass(migratorPath(ctx.toClass.getGenerals().get(0)));
			ctx.migratingOperation.getBody().addToStatements("super.migrateReferences(from,result)");
		}
	}
	private void migrateComposites(MigratorContext ctx,Property toProperty){
		PropertyMap toMap = ojUtil.buildStructuralFeatureMap(toProperty);
		Property fromProp = findMatchingProperty(ctx.fromClass, toProperty);
		if(!needsCustomCalculation(toProperty, fromProp)){
			PropertyMap fromMap = ojUtil.buildStructuralFeatureMap(fromProp);
			OJPathName migratorPath2 = migratorPath((Classifier) toProperty.getType());
			ctx.migrator.addToImports(migratorPath2);
			if(toMap.isMany() && fromMap.isMany()){
				OJForStatement forEach = new OJForStatement("tmp", classifierPathName(fromProp.getType(), getFromVersion()), "from."
						+ fromMap.getter() + "()");
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
			OJPathName baseTypePath = classifierPathName(toProperty.getType(), getToVersion());
			OJPathName pn = baseTypePath;
			if(toMap.isMany()){
				OJPathName coll = new OJPathName("java.util.Collection");
				coll.addToElementTypes(pn);
			}
			OJAnnotatedOperation calc = new OJAnnotatedOperation("calc" + ctx.prefix + NameConverter.capitalize(toProperty.getName()), pn);
			calc.addParam("from", classifierPathName(ctx.fromClass, getFromVersion()));
			ctx.migrator.addToOperations(calc);
			if(fromWorkspace.getModelElement(EmfWorkspace.getId(toProperty.getType())) == null){
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
	private Property findMatchingProperty(Classifier fromClass,Property toProperty){
		Property result = (Property) EmfElementFinder.getOwnedElement(fromClass,EmfWorkspace.getId(toProperty));
		if(result == null){
			result = getLibrary().findEffectiveAttribute( fromClass, toProperty.getName());
		}
		return result;
	}
	private void migrateDataTypeValue(MigratorContext ctx,Property toProperty){
		Property fromProperty = findMatchingProperty(ctx.fromClass, toProperty);
		if(needsCustomCalculation(toProperty, fromProperty)){
			PropertyMap toMap = ojUtil.buildStructuralFeatureMap(toProperty);
			OJAnnotatedOperation calc = addCalculator(ctx, toProperty);
			ctx.migratingOperation.getBody().addToStatements("result." + toMap.setter() + "(" + calc.getName() + "(from))");
		}else{
			PropertyMap fromMap = ojUtil.buildStructuralFeatureMap(fromProperty);
			PropertyMap toMap = ojUtil.buildStructuralFeatureMap(toProperty);
			if(EmfClassifierUtil.isSimpleType(toProperty.getType())){
				if(toMap.isOne()){
					ctx.migratingOperation.getBody().addToStatements("result." + toMap.setter() + "(from." + fromMap.getter() + "())");
				}else if(fromMap.isOne()){
					ctx.migratingOperation.getBody().addToStatements(
							new OJIfStatement("from." + fromMap.getter() + "()!=null", "result." + toMap.adder() + "(from." + fromMap.getter() + "())"));
				}else{
					OJPathName ojType = classifierPathName((Classifier) fromProperty.getType(), getFromVersion());
					OJForStatement forEach = new OJForStatement("tmp", ojType, "from." + fromMap.getter() + "()");
					ctx.migratingOperation.getBody().addToStatements(forEach);
					forEach.getBody().addToStatements("result." + toMap.adder() + "(tmp)");
				}
			}else{
				OJAnnotatedOperation converter = null;
				if(toProperty.getType() instanceof Enumeration){
					converter = migrateEnumerationValue(ctx, toProperty, fromProperty);
				}else if(EmfClassifierUtil.isStructuredDataType(toProperty.getType())){
					converter = migrateStructuredDataTypeDataTypeProperties(ctx, toProperty, fromProperty);
				}else{
				}
				if(toMap.isOne()){
					ctx.migratingOperation.getBody().addToStatements(
							"result." + toMap.setter() + "(" + converter.getName() + "(from,from." + fromMap.getter() + "()))");
				}else if(fromMap.isOne()){
					String invocation = "result." + toMap.adder() + "(" + converter.getName() + "(from, from." + fromMap.getter() + "())";
					ctx.migratingOperation.getBody().addToStatements(new OJIfStatement("from." + fromMap.getter() + "()!=null", invocation));
				}else{
					OJPathName ojType = classifierPathName((Classifier) fromProperty.getType(), getFromVersion());
					OJForStatement forEach = new OJForStatement("tmp", ojType, "from." + fromMap.getter() + "()");
					ctx.migratingOperation.getBody().addToStatements(forEach);
					String invocation = "result." + toMap.adder() + "(" + converter.getName() + "(from, tmp))";
					forEach.getBody().addToStatements(invocation);
				}
			}
		}
	}
	private boolean needsCustomCalculation(Property toProperty,Property fromProperty){
		return fromProperty == null || !haveCompatibleTypes(toProperty, fromProperty) || !haveCompatibleMultiplicty(toProperty, fromProperty);
	}
	public boolean haveCompatibleMultiplicty(Property toProperty,Property fromProperty){
		if(toProperty.isMultivalued()==fromProperty.isMultivalued()){
			if(!toProperty.isMultivalued()){
				return true;
			}else{
				return toProperty.isUnique()==fromProperty.isUnique() && toProperty.isOrdered()==fromProperty.isOrdered();
			}
		}
		return false;
	}
	private boolean haveCompatibleTypes(Property toProperty,Property fromProperty){
		return EmfWorkspace.getId(fromProperty.getType()).equals(EmfWorkspace.getId(toProperty.getType()))
				|| ojUtil.classifierPathname(fromProperty.getType()).equals(ojUtil.classifierPathname(toProperty.getType()));
	}
	private OJAnnotatedOperation migrateStructuredDataTypeDataTypeProperties(MigratorContext ctx,Property toProperty,Property fromProperty){
		OJPathName toPathName = classifierPathName((Classifier) toProperty.getType(), getToVersion());
		OJAnnotatedOperation convertStruct = new OJAnnotatedOperation("convert" + ctx.prefix + NameConverter.capitalize(toProperty.getName()));
		ctx.migrator.addToOperations(convertStruct);
		convertStruct.setReturnType(classifierPathName((Classifier) toProperty.getType(), getToVersion()));
		convertStruct.addParam("fromOwner", classifierPathName(ctx.fromClass, getFromVersion()));
		convertStruct.addParam("from", classifierPathName(fromProperty.getType(), getFromVersion()));
		convertStruct.getBody().addToStatements(new OJIfStatement("from==null", "return null"));
		convertStruct.initializeResultVariable("new " + toPathName.getLast() + "()");
		DataType fromStruct = (DataType) fromProperty.getType();
		DataType toStruct = (DataType) toProperty.getType();
		MigratorContext childCtx = new MigratorContext(fromStruct, toStruct, ctx.migrator, convertStruct, ctx.prefix
				+ NameConverter.capitalize(toProperty.getName()));
		for(Property toProp:getLibrary().getEffectiveAttributes(toStruct)){
			if(toProp.getType() instanceof DataType && !EmfPropertyUtil.isDerived( toProp) && !toProp.isReadOnly()){
				migrateDataTypeValue(childCtx, toProp);
			}
		}
		return convertStruct;
	}
	private OJAnnotatedOperation buildMigrateReferenceOnStructuredDataType(MigratorContext ctx,Property toProperty,Property fromProperty){
		OJAnnotatedOperation resolve = new OJAnnotatedOperation("migrate" + ctx.prefix + NameConverter.capitalize(toProperty.getName()));
		ctx.migrator.addToOperations(resolve);
		resolve.addParam("owner", classifierPathName(ctx.toClass, getToVersion()));
		resolve.addParam("result", classifierPathName(toProperty.getType(), getToVersion()));
		resolve.getBody().addToStatements(new OJIfStatement("result==null", "return"));
		DataType fromStruct = (DataType) fromProperty.getType();
		DataType toStruct = (DataType) toProperty.getType();
		MigratorContext childCtx = new MigratorContext(fromStruct, toStruct, ctx.migrator, resolve, ctx.prefix
				+ NameConverter.capitalize(toProperty.getName()));
		for(Property toProp:getLibrary().getEffectiveAttributes(toStruct)){
			if(isReference(toProp)){
				childCtx.migratingOperation.getBody().addToStatements(new OJIfStatement());
				migrateReference(childCtx, toProp);
			}
		}
		return resolve;
	}
	private OJAnnotatedOperation migrateEnumerationValue(MigratorContext ctx,Property toProperty,Property fromProperty){
		OJPathName toPathName = classifierPathName(toProperty.getType(), getToVersion());
		OJAnnotatedOperation convertEnum = new OJAnnotatedOperation("convert" + toProperty.getName(), classifierPathName(toProperty.getType(),
				getToVersion()));
		ctx.migrator.addToOperations(convertEnum);
		convertEnum.addParam("fromOwner", classifierPathName(ctx.fromClass, getFromVersion()));
		convertEnum.addParam("from", classifierPathName(fromProperty.getType(), getFromVersion()));
		convertEnum.getBody().addToStatements(new OJIfStatement("from==null", "return null"));
		convertEnum.initializeResultVariable("null");
		OJSwitchStatement switchLiteral = new OJSwitchStatement();
		convertEnum.getBody().addToStatements(switchLiteral);
		switchLiteral.setCondition("from");
		Enumeration fromEnum = (Enumeration) fromProperty.getType();
		Enumeration toEnum = (Enumeration) toProperty.getType();
		OJAnnotatedOperation calc = null;
		for(EnumerationLiteral fromLit:fromEnum.getOwnedLiterals()){
			EnumerationLiteral toLit = (EnumerationLiteral) EmfElementFinder.getOwnedElement( toEnum, EmfWorkspace.getId(fromLit));
			if(toLit == null){
				toLit = (EnumerationLiteral) toEnum.getOwnedLiteral(fromLit.getName());
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
	private OJAnnotatedOperation addCalculator(MigratorContext ctx,Property toProperty){
		String name = "calculate" + ctx.prefix + NameConverter.capitalize(toProperty.getName());
		OJPathName resultPath = classifierPathName(toProperty.getType(), getToVersion());
		PropertyMap map = ojUtil.buildStructuralFeatureMap(toProperty);
		if(map.isMany()){
			OJPathName p = map.javaTypePath();
			p.removeAllFromElementTypes();
			p.addToElementTypes(resultPath);
			resultPath = p;
		}
		OJAnnotatedOperation calc = new OJAnnotatedOperation(name, resultPath);
		ctx.migrator.addToOperations(calc);
		calc.addParam("from", classifierPathName(ctx.fromClass, getFromVersion()));
		if(EmfPropertyUtil.isRequired(toProperty)){
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
