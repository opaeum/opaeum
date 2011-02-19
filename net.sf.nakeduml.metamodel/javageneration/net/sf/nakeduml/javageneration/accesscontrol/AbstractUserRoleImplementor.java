package net.sf.nakeduml.javageneration.accesscontrol;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.hibernate.HibernateConfiguratorGenerator;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.internal.StereotypeNames;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.name.NameConverter;
import nl.klasse.octopus.codegen.umlToJava.modelgenerators.visitors.UtilityCreator;

/**
 */
// TODO currently only ONE role name per concrete user class is made available to Seam. Find a way to make all abstract and interface roles
// available too
public class AbstractUserRoleImplementor extends AbstractJavaProducingVisitor{
	
	private static OJPathName abstractUserRoleInterface = new OJPathName("com.rorotika.cm.user.AbstractUserRoleInterface");
	private static OJPathName abstractUserInterface = new OJPathName("com.rorotika.cm.user.AbstractUserInterface");
	private static OJPathName secureObjectInterface = new OJPathName("com.rorotika.cm.user.SecureObjectInterface");
	private static OJPathName compositionNodeInterface = new OJPathName("com.rorotika.cm.user.CompositionNodeInterface");
	
	private static final String USER_ENTITY = "BaseUser";
	@VisitAfter
	public void visitModel(INakedModel model){
		//TODO this needs to become a uml library
//		super.createTextPath(UserUtil.duplicateInterface(AbstractUser.class), JavaTextSource.GEN_SRC);
//		super.createTextPath(UserUtil.duplicateInterface(AbstractUserRole.class), JavaTextSource.GEN_SRC);
//		OJAnnotatedClass abstractUser = buildAbstractUser();
//		buildRoleMapping(abstractUser.getPathName());
//		buildBaseUserTestData(abstractUser.getPathName());
		
//		buildBaseUserTestData(new OJPathName("net.sf.numlm.crud.model.user.SeamUser"));
	}
	private void buildBaseUserTestData(OJPathName pathName){
		OJAnnotatedClass ojClass = new OJAnnotatedClass();
		ojClass.setName(pathName.getLast() + "TestData");
		UtilityCreator.getUtilPack().addToClasses(ojClass);
		super.createTextPath(ojClass, JavaTextSource.GEN_TEST_SRC);
		ojClass.addToImports(HibernateConfiguratorGenerator.getConfiguratorPathName());
		ojClass.addToImports("java.util.List");
		OJAnnotatedOperation main = OJUtil.buildMain(ojClass);
		main.getBody().addToStatements(pathName.getLast() + " user");
		for(INakedClassifier c:getConcreteUserRoles()){
			main.getBody().addToStatements("user=new " + pathName.getLast() + "()");
			main.getBody().addToStatements("user.setUsername(\""+NameConverter.decapitalize(c.getName())+"\")");
			main.getBody().addToStatements("user.setPassword(\""+NameConverter.decapitalize(c.getName())+"\")");
			main.getBody().addToStatements("HibernateConfigurator.getInstance().getEntityManager().getTransaction().begin()");
			main.getBody().addToStatements("HibernateConfigurator.getInstance().getEntityManager().persist(user)");
			main.getBody().addToStatements("HibernateConfigurator.getInstance().getEntityManager().flush()");
			
			main.getBody().addToStatements(new OJPathName("net.sf.numlm.crud.model.user.SeamRole").getLast() + " role");
			
			
			OJPathName roleEntity = OJUtil.classifierPathname(c);
			main.getBody().addToStatements("addRole(user," + roleEntity + ".class)");
			main.getBody().addToStatements("HibernateConfigurator.getInstance().getEntityManager().getTransaction().commit()");
		}
		OJAnnotatedOperation addRole = new OJAnnotatedOperation();
		addRole.setStatic(true);
		addRole.setName("addRole");
		ojClass.addToOperations(addRole);
		addRole.addParam("user", pathName);
		addRole.addParam("roleEntity", new OJPathName("java.lang.Class"));
		String fetchQuery = "List list=HibernateConfigurator.getInstance().getInstance().getEntityManager().createQuery(\"from \" +roleEntity.getName()).getResultList()";
		addRole.getBody().addToStatements(fetchQuery);
		//TODO this needs to become a uml library
//		OJPathName userRoleInterface = ReflectionUtil.getUtilInterface(AbstractUserRole.class);
		OJPathName userRoleInterface = abstractUserRoleInterface;
		OJIfStatement ifNotEmpty = new OJIfStatement("!list.isEmpty()", "user.addRole((" + userRoleInterface.getLast() + ")list.get(0))");
		addRole.getBody().addToStatements(ifNotEmpty);
	}
//	private void buildRoleMapping(OJPathName abstractUserPathame){
//		OJAnnotatedClass userRoleMapping = new OJAnnotatedClass();
//		userRoleMapping.setName("UserRoleMapping");
//		JpaUtil.addAndAnnotatedIdAndVersion(userRoleMapping, "user_role_mapping");
//		JpaUtil.addEntity(userRoleMapping);
//		JpaUtil.buildTableAnnotation(userRoleMapping, "user_role_mapping", this.config);
//		UtilityCreator.getUtilPack().addToClasses(userRoleMapping);
//		createTextPath(userRoleMapping, JavaTextSource.GEN_SRC);
//		OJPathName mappedRoles = new OJPathName("java.util.Collection");
//		mappedRoles.addToElementTypes(userRoleMapping.getPathName());
//		//TODO this needs to become a uml library
////		OJPathName userRolePath = ReflectionUtil.getUtilInterface(AbstractUserRole.class);
//		OJPathName userRolePath = abstractUserRoleInterface;
//		OJAnnotatedField role = OJUtil.addProperty(userRoleMapping, "role", userRolePath, true);
//		HibernateUtil.addAny(userRoleMapping, role, "role_id", getConcreteUserRoles());
//		OJAnnotatedField user = OJUtil.addProperty(userRoleMapping, "user", abstractUserPathame, true);
//		JpaUtil.addJoinColumn(user, "user_id", false);
//		OJAnnotationValue manyToOne = new OJAnnotationValue(new OJPathName("javax.persistence.ManyToOne"));
//		JpaUtil.cascadeAll(manyToOne);
//		user.addAnnotationIfNew(manyToOne);
//		OJAnnotatedField roleName = OJUtil.addProperty(userRoleMapping, "roleName", new OJPathName("String"), true);
//		JpaUtil.addColumn(roleName, "role_name", false);
//		roleName.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.security.management.RoleName")));
//		OJAnnotatedOperation setRole = (OJAnnotatedOperation) userRoleMapping.findOperation("setRole", Collections.singletonList(userRolePath));
//		setRole.getBody().addToStatements("this.roleName=role.getRoleNameForSecurity()");
//	}
	private Collection<INakedEntity> getConcreteUserRoles(){
		Collection<INakedEntity> results = new ArrayList<INakedEntity>();
		for(INakedElement e:workspace.getAllElements()){
			if(e instanceof INakedEntity){
				INakedEntity en = (INakedEntity) e;
				if(en.representsUser() && !en.getIsAbstract()){
					results.add(en);
				}
			}
		}
		return results;
	}
//	private OJAnnotatedClass buildAbstractUser(){
//		OJAnnotatedClass abstractUser = new OJAnnotatedClass();
//		abstractUser.setName(USER_ENTITY);
//		abstractUser.addToImplementedInterfaces(ReflectionUtil.getUtilInterface(AbstractUser.class));
//		UtilityCreator.getUtilPack().addToClasses(abstractUser);
//		OJAnnotatedField username = OJUtil.addProperty(abstractUser, "username", new OJPathName("String"), true);
//		username.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Column")));
//		JpaIdStrategy jpaIdStrategy = JpaIdStrategyFactory.getStrategy(GenerationType.valueOf(config.getGeneratorStrategy()));
//		//TODO obsolete more like
//		JpaUtil.addAndAnnotatedIdAndVersion(jpaIdStrategy, abstractUser, "abstract_user");
//		JpaUtil.addEntity(abstractUser);
//		JpaUtil.buildTableAnnotation(abstractUser, "base_user", this.config);
//		username.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.security.management.UserPrincipal")));
//		OJAnnotatedField password = OJUtil.addProperty(abstractUser, "password", new OJPathName("String"), true);
//		password.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Column")));
//		OJAnnotationValue userPassword = new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.security.management.UserPassword"));
//		password.addAnnotationIfNew(userPassword);
//		userPassword.putAttribute(new OJAnnotationAttributeValue("hash", "none"));
//		super.createTextPath(abstractUser, JavaTextSource.GEN_SRC);
//		OJPathName userRoleMappingPathName = UtilityCreator.getUtilPathName();
//		userRoleMappingPathName.addToNames("UserRoleMapping");
//		OJPathName collectionPath = new OJPathName("java.util.Collection");
//		collectionPath.addToElementTypes(userRoleMappingPathName);
//		OJAnnotatedField mappedRoles = OJUtil.addProperty(abstractUser, "mappedRoles", collectionPath, true);
//		mappedRoles.setInitExp("new ArrayList<" + userRoleMappingPathName.getLast() +">()");
//		
//		mappedRoles.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("org.jboss.seam.annotations.security.management.UserRoles")));
//		OJAnnotationValue toMany = new OJAnnotationValue(new OJPathName("javax.persistence.OneToMany"));
//		toMany.putAttribute(new OJAnnotationAttributeValue("mappedBy", "user"));
//		toMany.putAttribute(new OJAnnotationAttributeValue("targetEntity", userRoleMappingPathName));
//		JpaUtil.cascadeAll(toMany);
//		JpaUtil.fetchLazy(toMany);
//		mappedRoles.addAnnotationIfNew(toMany);
//		addGetRoles(abstractUser);
//		addAddRole(abstractUser, userRoleMappingPathName);
//		return abstractUser;
//	}
	private void addGetRoles(OJAnnotatedClass abstractUser){
		abstractUser.addToImports("java.util.ArrayList");
		OJAnnotatedOperation getRoles = new OJAnnotatedOperation();
		getRoles.setName("getRoles");
		OJField getRolesResult = new OJField();
		getRolesResult.setName("results");
		OJPathName resultsPath = new OJPathName("java.util.Collection");
		//TODO this needs to become a uml library
//		OJPathName rolePath = ReflectionUtil.getUtilInterface(AbstractUserRole.class);
		OJPathName rolePath = abstractUserRoleInterface;
		resultsPath.addToElementTypes(rolePath);
		getRolesResult.setType(resultsPath);
		getRolesResult.setInitExp("new ArrayList<" + rolePath.getLast() + ">()");
		getRoles.getBody().addToLocals(getRolesResult);
		abstractUser.addToOperations(getRoles);
		OJForStatement forRoleMappings = new OJForStatement("", "", "role", "this.mappedRoles");
		forRoleMappings.setBody(new OJBlock());
		forRoleMappings.setElemType(new OJPathName("UserRoleMapping"));
		forRoleMappings.getBody().addToStatements("results.add(role.getRole())");
		getRoles.getBody().addToStatements(forRoleMappings);
		getRoles.setReturnType(resultsPath);
		getRoles.getBody().addToStatements("return results");
	}
	private void addAddRole(OJAnnotatedClass abstractUser,OJPathName roleMapping){
		OJAnnotatedOperation getRoles = new OJAnnotatedOperation();
		getRoles.setName("addRole");
		//TODO this needs to become a uml library
//		OJPathName rolePath = ReflectionUtil.getUtilInterface(AbstractUserRole.class);
		OJPathName rolePath = abstractUserRoleInterface;
		getRoles.addParam("role", rolePath);
		getRoles.getBody().addToStatements(roleMapping.getLast() + " mapping = new " + roleMapping.getLast() + "()");
		getRoles.getBody().addToStatements("mapping.setRole(role)");
		getRoles.getBody().addToStatements("mapping.setUser(this)");
		getRoles.getBody().addToStatements("mappedRoles.add(mapping)");
		abstractUser.addToOperations(getRoles);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity entity){
		if(OJUtil.hasOJClass(entity)){
			if(entity.representsUser()){
				OJAnnotatedClass ojClass = findJavaClass(entity);
				//TODO this needs to become a uml library
//				ojClass.addToImplementedInterfaces(ReflectionUtil.getUtilInterface(AbstractUserRole.class));

				//In the model for now
//				ojClass.addToImplementedInterfaces(abstractUserRoleInterface);
				addGetGroupsForSecurity(ojClass, entity);
				addGetRoleNameForSecurity(ojClass, entity);
			}
		}
	}
	private void addGetRoleNameForSecurity(OJAnnotatedClass ojClass,INakedEntity entity){
		OJUtil.addMethod(ojClass, "getRoleNameForSecurity", "String", "\"" + entity.getMappingInfo().getJavaName().getAsIs() + "\"");
	}
	private void addGetGroupsForSecurity(OJClass owner,INakedEntity entity){
		OJOperation getGroupsForSecurity = OJUtil.findOperation(owner, "getGroupsForSecurity");
		//TODO this needs to become a uml library
//		OJPathName returnType = new OJPathName(Collection.class.getName());
		OJPathName returnType = new OJPathName(Set.class.getName());
		//TODO this needs to become a uml library
//		returnType.addToElementTypes(ReflectionUtil.getUtilInterface(CompositionNode.class));
		returnType.addToElementTypes(compositionNodeInterface);
		if(getGroupsForSecurity == null || getGroupsForSecurity.getParameters().size() > 0){
			getGroupsForSecurity = new OJAnnotatedOperation();
			getGroupsForSecurity.setName("getGroupsForSecurity");
			getGroupsForSecurity.setReturnType(returnType);
			OJField results = new OJField();
			results.setType(returnType);
			results.setName("results");
			//TODO this needs to become a uml library
//			results.setInitExp("new ArrayList<" + ReflectionUtil.getUtilInterface(CompositionNode.class).getLast() + ">()");
			owner.addToImports("java.util.HashSet");
			results.setInitExp("new HashSet<" + compositionNodeInterface.getLast() + ">()");
			getGroupsForSecurity.getBody().addToLocals(results);
			getGroupsForSecurity.getBody().addToStatements("results.add(getOwningObject())");
			for(INakedProperty p:entity.getEffectiveAttributes()){
				if(p.hasStereotype(StereotypeNames.USER_GROUP) || p.getNakedBaseType().hasStereotype(StereotypeNames.USER_GROUP)){
					NakedStructuralFeatureMap map = OJUtil.buildStructuralFeatureMap(p);
					if(map.isMany()){
						getGroupsForSecurity.getBody().addToStatements("results.addAll(" + map.getter() + "())");
					}else{
						getGroupsForSecurity.getBody().addToStatements("results.add(" + map.getter() + "())");
					}
				}
			}
			getGroupsForSecurity.getBody().addToStatements("return results");
			owner.addToOperations(getGroupsForSecurity);
		}
	}
}
