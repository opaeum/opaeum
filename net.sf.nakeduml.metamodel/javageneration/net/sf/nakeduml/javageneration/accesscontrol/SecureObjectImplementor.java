package net.sf.nakeduml.javageneration.accesscontrol;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javageneration.util.ReflectionUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.util.AbstractUser;
import net.sf.nakeduml.util.AbstractUserRole;
import net.sf.nakeduml.util.CompositionNode;
import net.sf.nakeduml.util.SecureObject;

public class SecureObjectImplementor extends AbstractJavaProducingVisitor{
	

		
	@VisitBefore(matchSubclasses = true)
	public void visitModel(INakedModel model){
		super.createTextPath(ReflectionUtil.duplicateInterface(SecureObject.class), JavaTextSource.GEN_SRC);
	}
	@VisitAfter(matchSubclasses = true)
	public void visitClass(INakedEntity entity){
		OJAnnotatedClass ojClass = findJavaClass(entity);
		ojClass.addToImplementedInterfaces(ReflectionUtil.getUtilInterface(SecureObject.class));
		
		ojClass.addToImports(ReflectionUtil.getUtilInterface(AbstractUser.class));
		ojClass.addToImports(ReflectionUtil.getUtilInterface(AbstractUserRole.class));
		
		addIsOwnedByUser(ojClass, entity);
		addCanBeOwnedByUser(ojClass, entity);
		addIsGroupOwnershipValid(ojClass, entity);
		addIsUserOwnershipValid(ojClass);
	}
	//TODO Come up with a new solution to overriding user ownership
	private void addIsUserOwnershipValid(OJAnnotatedClass ojClass){
		OJAnnotatedOperation isUserOwnershipValid = new OJAnnotatedOperation();
		isUserOwnershipValid.setName("isUserOwnershipValid");
		isUserOwnershipValid.addParam("user", ReflectionUtil.getUtilInterface(AbstractUser.class));
		isUserOwnershipValid.setBody(new OJBlock());
		ojClass.addToOperations(isUserOwnershipValid);
		OJIfStatement ifCan = new OJIfStatement("canBeOwnedByUser(user)", "return isOwnedByUser(user)");
		ifCan.addToElsePart("return false");
		isUserOwnershipValid.setReturnType(new OJPathName("boolean"));
		isUserOwnershipValid.getBody().addToStatements(ifCan);
		isUserOwnershipValid.setComment("User Ownership is bypassed if the current user does not share the role required for ownership");
	}
	private void addCanBeOwnedByUser(OJClass owner,INakedEntity entity){
		OJOperation canBeOwnedByUser = OJUtil.findOperation(owner, "canBeOwnedByUser");
		if(canBeOwnedByUser == null || canBeOwnedByUser.getParameters().size() > 1){
			OJPathName secureObject = ReflectionUtil.getUtilInterface(SecureObject.class);
			canBeOwnedByUser = new OJAnnotatedOperation();
			canBeOwnedByUser.setName("canBeOwnedByUser");
			canBeOwnedByUser.addParam("user", ReflectionUtil.getUtilInterface(AbstractUser.class));
			canBeOwnedByUser.setBody(new OJBlock());
			canBeOwnedByUser.setReturnType(new OJPathName("boolean"));
			OJForStatement forRoles = new OJForStatement("", "", "role", "user.getRoles()");
			forRoles.setBody(new OJBlock());
			forRoles.setElemType(ReflectionUtil.getUtilInterface(AbstractUserRole.class));
			OJIfStatement ifEquals = new OJIfStatement("role instanceof " + entity.getMappingInfo().getJavaName(), "return true");
			forRoles.getBody().addToStatements(ifEquals);
			canBeOwnedByUser.getBody().addToStatements(forRoles);
			OJIfStatement ifIsSecureObject = new OJIfStatement("getOwningObject() instanceof " + secureObject.getLast() + "&&(("
					+ secureObject.getLast() + ")getOwningObject()).canBeOwnedByUser(user)", "return true");
			ifEquals.addToElsePart(ifIsSecureObject);
			canBeOwnedByUser.getBody().addToStatements("return false");
			owner.addToOperations(canBeOwnedByUser);
		}
		canBeOwnedByUser.setReturnType(new OJPathName("boolean"));
	}
	private void addIsOwnedByUser(OJClass owner,INakedEntity entity){
		OJOperation isOwnedByUser = OJUtil.findOperation(owner, "isOwnedByUser");
		if(isOwnedByUser == null || isOwnedByUser.getParameters().size() > 1){
			OJPathName secureObject = ReflectionUtil.getUtilInterface(SecureObject.class);
			isOwnedByUser = new OJAnnotatedOperation();
			isOwnedByUser.setName("isOwnedByUser");
			isOwnedByUser.addParam("user", ReflectionUtil.getUtilInterface(AbstractUser.class));
			isOwnedByUser.setBody(new OJBlock());
			isOwnedByUser.setReturnType(new OJPathName("boolean"));
			OJForStatement forRoles = new OJForStatement("", "", "role", "user.getRoles()");
			forRoles.setBody(new OJBlock());
			forRoles.setElemType(ReflectionUtil.getUtilInterface(AbstractUserRole.class));
			OJIfStatement ifEquals = new OJIfStatement("this.equals(role)", "return true");
			forRoles.getBody().addToStatements(ifEquals);
			isOwnedByUser.getBody().addToStatements(forRoles);
			OJIfStatement ifIsSecureObject = new OJIfStatement("getOwningObject() instanceof " + secureObject.getLast() + "&&(("
					+ secureObject.getLast() + ")getOwningObject()).isOwnedByUser(user)", "return true");
			ifEquals.addToElsePart(ifIsSecureObject);
			isOwnedByUser.getBody().addToStatements("return false");
			owner.addToOperations(isOwnedByUser);
		}
		isOwnedByUser.setReturnType(new OJPathName("boolean"));
	}
	private void addIsGroupOwnershipValid(OJClass owner,INakedEntity entity){
		OJOperation isGroupOwnershipValid = OJUtil.findOperation(owner, "isGroupOwnershipValid");
		if(isGroupOwnershipValid == null || isGroupOwnershipValid.getParameters().size() > 0){
			OJPathName secureObject = ReflectionUtil.getUtilInterface(SecureObject.class);
			isGroupOwnershipValid = new OJAnnotatedOperation();
			isGroupOwnershipValid.setName("isGroupOwnershipValid");
			isGroupOwnershipValid.addParam("user", ReflectionUtil.getUtilInterface(AbstractUser.class));
			OJForStatement forRoles = new OJForStatement("", "", "role", "user.getRoles()");
			forRoles.setBody(new OJBlock());
			forRoles.setElemType(ReflectionUtil.getUtilInterface(AbstractUserRole.class));
			OJForStatement forGroups = new OJForStatement("", "", "group", "role.getGroupsForSecurity()");
			forGroups.setBody(new OJBlock());
			forGroups.setElemType(ReflectionUtil.getUtilInterface(CompositionNode.class));
			forRoles.getBody().addToStatements(forGroups);
			OJIfStatement ifEquals = new OJIfStatement("group.equals(this)", "return true");
			forGroups.getBody().addToStatements(ifEquals);
			OJIfStatement ifIsSecureObject = new OJIfStatement("getOwningObject() instanceof " + secureObject.getLast() + "&&(("
					+ secureObject.getLast() + ")getOwningObject()).isGroupOwnershipValid(user)", "return true");
			ifEquals.addToElsePart(ifIsSecureObject);
			isGroupOwnershipValid.getBody().addToStatements(forRoles);
			isGroupOwnershipValid.getBody().addToStatements("return false");
			owner.addToOperations(isGroupOwnershipValid);
		}
		isGroupOwnershipValid.setReturnType(new OJPathName("boolean"));
	}
}