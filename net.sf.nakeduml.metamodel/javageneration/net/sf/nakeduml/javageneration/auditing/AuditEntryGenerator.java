package net.sf.nakeduml.javageneration.auditing;

import java.sql.Timestamp;
import java.util.List;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.persistence.JpaAnnotator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJClassifier;
import net.sf.nakeduml.javametamodel.OJInterface;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJWhileStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedEnumeration;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSimpleType;
import net.sf.nakeduml.metamodel.core.INakedStructuredDataType;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.util.AbstractEntity;
import net.sf.nakeduml.util.AbstractProcess;
import net.sf.nakeduml.util.AbstractUserRole;
import nl.klasse.octopus.codegen.umlToJava.maps.StructuralFeatureMap;
import nl.klasse.octopus.model.IAttribute;
import nl.klasse.octopus.model.IModelElement;

/**
 * This class builds all the operations specified by the AbstractEntity interface. It also provides an implementation for the equals method
 * that uses the id of the instance involved
 */
public class AuditEntryGenerator extends AbstractJavaProducingVisitor{
	OJInterface abstractEntry;
	private OJClass userEntity;
	@VisitBefore(matchSubclasses = true)
	public void visitPackage(INakedModel model){
		String modelName = model.getMappingInfo().getJavaName().getLowerCase().toString();
		OJPackage ojp = this.javaModel.findPackage(new OJPathName(modelName));
		if(ojp == null){
			ojp = new OJPackage();
			ojp.setName(modelName);
			this.javaModel.addToSubpackages(ojp);
		}
		this.abstractEntry = new OJInterface();
		this.abstractEntry.setName("Abstract" + model.getMappingInfo().getJavaName().getCapped() + "AuditEntry");
		ojp.addToInterfaces(this.abstractEntry);
		this.abstractEntry.addToSuperInterfaces(new OJPathName("net.sf.nakeduml.util.Audited"));
		addGetUser(this.abstractEntry, false);
		OJUtil.addProperty(this.abstractEntry, "changedOn", new OJPathName(Timestamp.class.getName()), false);
		OJUtil.addProperty(this.abstractEntry, "auditedAction", new OJPathName("String"), false);
		OJUtil.addProperty(this.abstractEntry, "objectVersion", new OJPathName("int"), false);
		OJClass auditDummyDataGenerator = new OJClass();
		auditDummyDataGenerator.setName(model.getMappingInfo().getJavaName().getCapped() + "AuditDummyDataGenerator");
		ojp.addToClasses(auditDummyDataGenerator);
		super.createTextPath(auditDummyDataGenerator, JavaTextSource.GEN_SRC);
		OJAnnotatedField allClasses = new OJAnnotatedField();
		allClasses.setName("ALL_CLASSES");
		allClasses.setType(new OJPathName("Class[]"));
		StringBuilder init = new StringBuilder("new Class[]{");
		List<INakedEntity> entityList = workspace.getClasses(INakedEntity.class);
		for(INakedEntity entity:entityList){
			if(!entity.getIsAbstract()){
				init.append(entity.getMappingInfo().getQualifiedJavaName());
				init.append(".class,");
			}
		}
		if(entityList.size() > 0){
			init.delete(init.length() - 1, init.length());
		}
		allClasses.setInitExp(init + "}");
		allClasses.setStatic(true);
		auditDummyDataGenerator.addToFields(allClasses);
		OJAnnotatedField allAuditClasses = new OJAnnotatedField();
		allAuditClasses.setName("ALL_AUDIT_CLASSES");
		allAuditClasses.setType(new OJPathName("Class[]"));
		init = new StringBuilder("new Class[]{");
		for(INakedEntity entity:entityList){
			if(!entity.getIsAbstract()){
				init.append(entity.getMappingInfo().getQualifiedJavaName());
				init.append("AuditEntry.class,");
			}
		}
		if(entityList.size() > 0){
			init.delete(init.length() - 1, init.length());
		}
		allAuditClasses.setInitExp(init + "}");
		allAuditClasses.setStatic(true);
		auditDummyDataGenerator.addToFields(allAuditClasses);
		auditDummyDataGenerator.addToImports(new OJPathName(AbstractEntity.class.getName()));
		auditDummyDataGenerator.addToImports(new OJPathName("net.sf.nakeduml.util.Audited"));
		auditDummyDataGenerator.addToImports("java.util.Iterator");
		OJOperation main = new OJAnnotatedOperation();
		main.setStatic(true);
		main.setName("main");
		main.addToThrows("Exception");
		main.addParam("args", new OJPathName("String[]"));
		OJBlock mainBlock = new OJBlock();
		main.setBody(mainBlock);
		auditDummyDataGenerator.addToOperations(main);
		addToLocals(auditDummyDataGenerator, main.getBody(), "cfg", "new Configuration()", "org.hibernate.cfg.Configuration");
		main.getBody().addToStatements("cfg.configure(Thread.currentThread().getContextClassLoader().getResource(\"hibernate.cfg.xml\"))");
		addToLocals(auditDummyDataGenerator, main.getBody(), "factory", "cfg.buildSessionFactory()", "org.hibernate.SessionFactory");
		addToLocals(auditDummyDataGenerator, main.getBody(), "session", "factory.openSession()", "org.hibernate.Session");
		addToLocals(auditDummyDataGenerator, main.getBody(), "tx", "session.beginTransaction()", "org.hibernate.Transaction");
//		addToLocals(auditDummyDataGenerator, main.getBody(), "user", "(" + getUserEntity().getPathName().toJavaString()
//				+ ")session.createQuery(\"from " + getUserEntity().getPathName().toJavaString() + "\").iterate().next()", getUserEntity()
//				.getPathName().toJavaString());
		addToLocals(auditDummyDataGenerator, main.getBody(), "i", "0", "int");
		OJWhileStatement ws = new OJWhileStatement();
		main.getBody().addToStatements(ws);
		ws.setCondition("i<ALL_AUDIT_CLASSES.length");
		OJBlock whileBody = new OJBlock();
		ws.setBody(whileBody);
		whileBody.addToStatements("Iterator iter=session.createQuery(\"from \"+ ALL_CLASSES[i].getName()).iterate()");
		OJWhileStatement ws1 = new OJWhileStatement();
		ws1.setCondition("iter.hasNext()");
		ws.getBody().addToStatements(ws1);
		OJBlock while1Body = new OJBlock();
		while1Body.addToStatements("AbstractEntity object = (AbstractEntity)iter.next()");
		while1Body.addToStatements("AuditEntry entry = (AuditEntry)ALL_AUDIT_CLASSES[i].newInstance()");
		while1Body.addToStatements("entry.init(object,user,\"update\")");
		while1Body.addToStatements("session.save(entry)");
		ws1.setBody(while1Body);
		whileBody.addToStatements("i++");
		main.getBody().addToStatements("session.flush()");
		main.getBody().addToStatements("tx.commit()");
		main.getBody().addToStatements("session.close()");
		main.getBody().addToStatements("System.out.println(\"Success\")");
	}
	private void addToLocals(OJClass auditDummyDataGenerator,OJBlock block,String name,String initExpression,String type){
		OJPathName sessionPathName = new OJPathName(type);
		auditDummyDataGenerator.addToImports(sessionPathName);
		// OJAnnotatedField session = new OJAnnotatedField();
		// session.setName(name);
		// session.setType(sessionPathName);
		// session.setInitExp(initExpression);
		block.addToStatements(sessionPathName.getLast() + " " + name + "=" + initExpression);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedClassifier c){
		if(c instanceof INakedEntity){
			INakedEntity entity = (INakedEntity) c;
			OJPathName auditedEntity = new OJPathName(entity.getMappingInfo().getQualifiedJavaName());
			OJClass auditEntry = new OJClass();
			auditEntry.setName(entity.getMappingInfo().getJavaName() + "AuditEntry");
			OJPackage owner = findOrCreatePackageForClass(entity.getMappingInfo().getQualifiedJavaName());
			owner.addToClasses(auditEntry);
			auditEntry.addToImplementedInterfaces(this.abstractEntry.getPathName());
			auditEntry.addToImports(ReflectionUtil.getUtilInterface(AbstractEntity.class));
			auditEntry.addToImports(ReflectionUtil.getUtilInterface(AbstractUserRole.class));
			auditEntry.addToImports(ReflectionUtil.getUtilInterface(AbstractProcess.class));
			auditEntry.setAbstract(entity.getIsAbstract());
			if(entity.hasSupertype()){
				auditEntry.setSuperclass(new OJPathName(entity.getSupertype().getMappingInfo().getQualifiedJavaName() + "AuditEntry"));
			}
			addFields(auditEntry, entity);
			addInit(entity, auditEntry, auditedEntity);
			IAttribute nameAttribute = entity.findAttribute("name");
			if(nameAttribute == null || nameAttribute.isDerived()){
				OJUtil.addProperty(auditEntry, "name", new OJPathName("String"), true);
			}
			JpaAnnotator.addEquals(auditEntry);
			if(!entity.hasSupertype()){
				addGetAuditedEntity(entity, auditEntry, auditedEntity);
				addGetUser(auditEntry, true);
				OJUtil.addProperty(auditEntry, "changedOn", new OJPathName(Timestamp.class.getName()), true);
				OJUtil.addProperty(auditEntry, "id", new OJPathName("Long"), true);
				OJUtil.addProperty(auditEntry, "objectVersion", new OJPathName("int"), true);
				OJUtil.addProperty(auditEntry, "auditedAction", new OJPathName("String"), true);
			}else{
			}
			super.createTextPath(auditEntry, JavaTextSource.GEN_SRC);
		}else if(c instanceof INakedStructuredDataType){
			INakedStructuredDataType dataType = (INakedStructuredDataType) c;
			OJClass auditEntry = new OJClass();
			auditEntry.setAbstract(dataType.getIsAbstract());
			auditEntry.setName(dataType.getMappingInfo().getJavaName() + "AuditEntry");
			OJPackage owner = findOrCreatePackageForClass(dataType.getMappingInfo().getQualifiedJavaName());
			owner.addToClasses(auditEntry);
			auditEntry.setSuperclass(new OJPathName(dataType.getMappingInfo().getQualifiedJavaName()));
			OJPathName auditedDataType = new OJPathName(dataType.getMappingInfo().getQualifiedJavaName());
			addDataTypeInit(dataType, auditEntry, auditedDataType);
			super.createTextPath(auditEntry, JavaTextSource.GEN_SRC);
		}
	}
	private void addFields(OJClass auditEntry,INakedEntity classifier){
		List attrs = classifier.getOwnedAttributes();
		for(INakedProperty attr:classifier.getOwnedAttributes()){
			NakedStructuralFeatureMap map=OJUtil.buildStructuralFeatureMap(attr);
			if(!(attr.isDerived() || (map.isOneToOne() && attr.isInverse()))){
				// TODO support component oneToManies???
				OJPathName javaType = map.javaTypePath();
				if(attr.getNakedMultiplicity().isSingleObject() && attr.getNakedBaseType() instanceof INakedStructuredDataType){
					// TODO Needs to be fixed when Octopus supports generics
					javaType = new OJPathName(((INakedStructuredDataType) attr.getNakedBaseType()).getMappingInfo().getQualifiedJavaName());
				}
				auditEntry.addToImports(javaType);
				auditEntry.addToImports(map.javaDefaultTypePath());
				OJAnnotatedField userField = new OJAnnotatedField();
				userField.setName(map.umlName());
				userField.setType(javaType);
				userField.setInitExp(map.javaDefaultValue());
				auditEntry.addToFields(userField);
				OJOperation setter = new OJAnnotatedOperation();
				setter.setName(map.setter());
				setter.addParam(map.umlName(), javaType);
				OJBlock setterImpl = new OJBlock();
				setter.setBody(setterImpl);
				if(map.isMany()){
					// TODO do mapping without the need for oneToManies
					setterImpl.addToStatements("this." + map.umlName() + ".clear()");
					setterImpl.addToStatements("this." + map.umlName() + ".addAll(" + map.umlName() + ")");
					OJOperation adder = new OJAnnotatedOperation();
					adder.setName(map.adder());
					adder.addParam(map.umlName(), javaType);
					adder.setBody(setter.getBody());
					auditEntry.addToOperations(adder);
					if(attr.getNakedBaseType() instanceof INakedStructuredDataType){
						OJOperation singleAdder = new OJAnnotatedOperation();
						singleAdder.setName(map.adder());
						singleAdder.addParam(map.umlName(), ((INakedStructuredDataType) attr.getNakedBaseType()).getMappingInfo().getQualifiedJavaName()
								+ "AuditEntry");
						singleAdder.getBody().addToStatements("this." + map.umlName() + ".add(" + map.umlName() + ")");
						auditEntry.addToOperations(singleAdder);
					}
				}else{
					setterImpl.addToStatements("this." + map.umlName() + "=" + map.umlName());
				}
				auditEntry.addToOperations(setter);
				OJOperation getter = new OJAnnotatedOperation();
				getter.setName(map.getter());
				getter.setReturnType(javaType);
				OJBlock getterImpl = new OJBlock();
				getter.setBody(getterImpl);
				getterImpl.addToStatements("return " + map.umlName());
				auditEntry.addToOperations(getter);
			}
		}
	}
	private void addGetUser(OJClassifier ojClass,boolean withBody){
		OJOperation getAbstractUser = new OJAnnotatedOperation();
		getAbstractUser.setName("getResponsibleAbstractUser");
		getAbstractUser.setReturnType(ReflectionUtil.getUtilInterface(AbstractUserRole.class));
		getAbstractUser.setBody(new OJBlock());
		if(withBody){
			getAbstractUser.getBody().addToStatements("return getResponsibleUser()");
		}
		ojClass.addToOperations(getAbstractUser);
		
//TODO hacking here now		
//		ojClass.addToImports(getUserEntity().getPathName());
//		OJUtil.addProperty(ojClass, "responsibleUser", getUserEntity().getPathName(), withBody);
	}
	/**
	 * Removes initialization logic from the default constructor and adds it to the init method which takes the
	 */
	private void addInit(INakedEntity classifier,OJClass ojClass,OJPathName auditedEntity){
		OJOperation init = new OJAnnotatedOperation();
		init.addParam("auditedObject", ReflectionUtil.getUtilInterface(AbstractEntity.class));
		init.addParam("auditedUser", ReflectionUtil.getUtilInterface(AbstractUserRole.class));
		init.addParam("action", new OJPathName("String"));
		init.setName("init");
		OJBlock block = new OJBlock();
		init.setBody(block);
		if(classifier.hasSupertype()){
			block.addToStatements("super.init(auditedObject, auditedUser,action)");
		}else{
			block.addToStatements("this.auditedAction=action");
			block.addToStatements("this.auditedObject=(" + auditedEntity.getLast() + ")auditedObject");
			block.addToStatements("this.objectVersion=this.auditedObject.getObjectVersion()");
			block.addToStatements("this.changedOn=new Timestamp(System.currentTimeMillis())");
		}
//		ojClass.addToImports(getUserEntity().getPathName());
//		block.addToStatements("this.setResponsibleUser((" + getUserEntity().getName() + ")auditedUser)");
		block.addToStatements("copyState((" + classifier.getMappingInfo().getJavaName() + ")this.getAuditedObject(),this)");
		ojClass.addToOperations(init);
		OJOperation copyState = new OJAnnotatedOperation();
		copyState.setName("copyState");
		copyState.addParam("from", auditedEntity);
		copyState.addParam("to", ojClass.getPathName());
		copyState.setBody(new OJBlock());
		addInitialization(classifier, copyState.getBody());
		IAttribute nameAttribute = classifier.findAttribute("name");
		if(nameAttribute == null || nameAttribute.isDerived()){
			copyState.getBody().addToStatements("to.setName(from.getName())");
		}
		ojClass.addToOperations(copyState);
	}
	/**
	 * Removes initialization logic from the default constructor and adds it to the init method which takes the
	 */
	private void addDataTypeInit(INakedStructuredDataType classifier,OJClass ojClass,OJPathName auditedEntity){
		OJOperation init = new OJAnnotatedOperation();
		init.addParam("auditedObject", new OJPathName(classifier.getMappingInfo().getQualifiedJavaName()));
		init.setName("init");
		OJBlock block = new OJBlock();
		init.setBody(block);
//		ojClass.addToImports(getUserEntity().getPathName());
		block.addToStatements("copyState((" + classifier.getMappingInfo().getJavaName() + ")auditedObject,this)");
		ojClass.addToOperations(init);
		OJOperation copyState = new OJAnnotatedOperation();
		copyState.setName("copyState");
		copyState.addParam("from", auditedEntity);
		copyState.addParam("to", ojClass.getPathName());
		copyState.setBody(new OJBlock());
		addInitialization(classifier, copyState.getBody());
		ojClass.addToOperations(copyState);
	}
	protected OJPathName getOJPathName(INakedClassifier nc){
		return new OJPathName(nc.getMappingInfo().getQualifiedJavaName());
	}
	private void addInitialization(INakedClassifier classifier,OJBlock body){
		List properties = classifier.getEffectiveAttributes();
		for(int i = 0;i < properties.size();i++){
			IModelElement a = (IModelElement) properties.get(i);
			if(a instanceof INakedProperty){
				INakedProperty np = (INakedProperty) a;
				NakedStructuralFeatureMap map=new NakedStructuralFeatureMap(np);
				if(!(np.isDerived() || np.isReadOnly())){
					StructuralFeatureMap featureMap = new NakedStructuralFeatureMap(np);
					if(np.getNakedBaseType() instanceof INakedSimpleType || np.getNakedBaseType() instanceof INakedEnumeration){
						body.addToStatements("to." + featureMap.setter() + "(" + "from." + featureMap.getter() + "())");
					}else if(np.getNakedBaseType() instanceof INakedEntity){
						String get = "from." + featureMap.getter() + "()";
						if(np.getNakedMultiplicity().isSingleObject() && !np.isInverse()){
							body.addToStatements("to." + featureMap.setter() + "(" + get + ")");
						}else if(map.isManyToMany()){
							body.addToStatements("to." + featureMap.adder() + "(" + get + ")");
						}
					}else if(np.getNakedBaseType() instanceof INakedStructuredDataType){
						String get = "from." + featureMap.getter() + "()";
						String javaName = np.getMappingInfo().getJavaName().toString();
						String javaType = ((INakedClassifier) np.getNakedBaseType()).getMappingInfo().getQualifiedJavaName();
						if(map.isMany()){
							body.addToStatements("java.util.Iterator " + javaName + "Iter=" + get + ".iterator()");
							OJWhileStatement ws = new OJWhileStatement();
							ws.setCondition(javaName + "Iter.hasNext()");
							OJBlock whileBody = new OJBlock();
							ws.setBody(whileBody);
							whileBody.addToStatements(javaType + " child=(" + javaType + ")" + javaName + "Iter.next()");
							whileBody.addToStatements(javaType + "AuditEntry childEntry=new " + javaType + "AuditEntry()");
							whileBody.addToStatements("childEntry.init(child)");
							whileBody.addToStatements("to." + featureMap.adder() + "(childEntry)");
							body.addToStatements(ws);
						}else{
							body.addToStatements(javaType + "AuditEntry " + javaName + "Entry=new " + javaType + "AuditEntry()");
							body.addToStatements(javaName + "Entry.init(" + get + ")");
							body.addToStatements("to." + featureMap.setter() + "(" + javaName + "Entry)");
						}
					}
				}
			}
		}
	}
	private void addGetAuditedEntity(INakedEntity classifier,OJClass ojClass,OJPathName auditedEntity){
		OJAnnotatedField auditedEntityField = new OJAnnotatedField();
		auditedEntityField.setName("auditedObject");
		auditedEntityField.setType(auditedEntity);
		ojClass.addToFields(auditedEntityField);
		OJOperation getAuditedEntity = new OJAnnotatedOperation();
		getAuditedEntity.setName("getAuditedObject");
		getAuditedEntity.setReturnType(ReflectionUtil.getUtilInterface(AbstractEntity.class));
		getAuditedEntity.setBody(new OJBlock());
		getAuditedEntity.getBody().addToStatements("return auditedObject");
		ojClass.addToOperations(getAuditedEntity);
	}
	void setUserEntity(OJClass userEntity){
		this.userEntity = userEntity;
	}
	protected OJClass getUserEntity(){
		if(this.userEntity == null){
			if(this.workspace.getRootUserEntity() == null){
				this.javaModel.findClass(ReflectionUtil.getUtilInterface(AbstractUserRole.class));
			}else{
				this.userEntity = this.javaModel.findClass(new OJPathName(this.workspace.getRootUserEntity().getMappingInfo()
						.getQualifiedJavaName()));
			}
		}
		return this.userEntity;
	}
}
