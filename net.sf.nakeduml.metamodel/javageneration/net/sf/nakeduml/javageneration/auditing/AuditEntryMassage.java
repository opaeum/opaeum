package net.sf.nakeduml.javageneration.auditing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.feature.visit.VisitBefore;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedStructuralFeatureMap;
import net.sf.nakeduml.javageneration.hibernate.HibernateUtil;
import net.sf.nakeduml.javageneration.persistence.JpaUtil;
import net.sf.nakeduml.javageneration.util.ActionFeatureBridge;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJBlock;
import net.sf.nakeduml.javametamodel.OJClass;
import net.sf.nakeduml.javametamodel.OJField;
import net.sf.nakeduml.javametamodel.OJForStatement;
import net.sf.nakeduml.javametamodel.OJIfStatement;
import net.sf.nakeduml.javametamodel.OJOperation;
import net.sf.nakeduml.javametamodel.OJParameter;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.OJSimpleStatement;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedClass;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedField;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedInterface;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedOperation;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.javametamodel.generated.OJVisibilityKindGEN;
import net.sf.nakeduml.linkage.BehaviorUtil;
import net.sf.nakeduml.linkage.TypeResolver;
import net.sf.nakeduml.metamodel.actions.IActionWithTarget;
import net.sf.nakeduml.metamodel.actions.INakedCallAction;
import net.sf.nakeduml.metamodel.activities.INakedActivity;
import net.sf.nakeduml.metamodel.activities.INakedActivityNode;
import net.sf.nakeduml.metamodel.activities.INakedActivityVariable;
import net.sf.nakeduml.metamodel.activities.INakedExpansionNode;
import net.sf.nakeduml.metamodel.activities.INakedOutputPin;
import net.sf.nakeduml.metamodel.commonbehaviors.INakedBehavior;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedTypedElement;
import net.sf.nakeduml.metamodel.core.internal.emulated.TypedElementPropertyBridge;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.util.AbstractEntity;

public class AuditEntryMassage extends AbstractJavaProducingVisitorForAudit {
	private static final String AUDIT_ID_USER_TYPE = "net.sf.nakeduml.util.AuditIdUserType";

	@VisitBefore(matchSubclasses = true)
	public void visitProperty(INakedProperty p) {
		INakedClassifier owner = p.getOwner();
		visitProperty(owner, p);
	}

	private void visitProperty(INakedClassifier owner, INakedProperty p) {
		NakedStructuralFeatureMap map = new NakedStructuralFeatureMap(p);
		if (isPersistent(owner) && !p.isDerived() && !(owner instanceof INakedInterface || p.getNakedBaseType() instanceof INakedInterface)
				&& (isPersistent(p.getNakedBaseType()))) {
			OJPathName path = OJUtil.classifierPathname(owner);
			path.replaceTail(path.getLast() + "_Audit");
			OJAnnotatedClass auditClass = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
			OJAnnotatedField field = (OJAnnotatedField) auditClass.findField(map.umlName());
			if (map.isOneToMany()) {
				field.removeAnnotation(new OJPathName("javax.persistence.JoinColumn"));
				field.removeAnnotation(new OJPathName("javax.persistence.OneToMany"));
				field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
				addOneToManyGetter(auditClass, (INakedEntity) owner, p, map);
			} else if (map.isManyToMany()) {
				if (p.isInverse() || p.getOtherEnd() == null) {
					// TODO support for cases where otherEnd=null
					field.removeAnnotation(new OJPathName("javax.persistence.JoinTable"));
					field.removeAnnotation(new OJPathName("javax.persistence.ManyToMany"));
					field.addAnnotationIfNew(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
				} else {
					field.removeAnnotation(new OJPathName("javax.persistence.JoinTable"));
					OJAnnotationValue joinTable = new OJAnnotationValue(new OJPathName("javax.persistence.JoinTable"));
					OJAnnotationAttributeValue joinColumns = new OJAnnotationAttributeValue("joinColumns");
					OJAnnotationValue joinColumn1 = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
					joinColumn1.putAttribute("name", p.getOtherEnd().getMappingInfo().getPersistentName().getWithoutId() + "_original_id");
					joinColumn1.putAttribute("referencedColumnName", getRoot(owner).getMappingInfo().getPersistentName() + "_id");
					joinColumns.addAnnotationValue(joinColumn1);
					OJAnnotationValue joinColumn2 = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
					joinColumn2.putAttribute("name", p.getOtherEnd().getMappingInfo().getPersistentName().getWithoutId() + "_object_version");
					joinColumn2.putAttribute("referencedColumnName", "object_version");
					joinColumns.addAnnotationValue(joinColumn2);
					joinTable.putAttribute(joinColumns);
					OJAnnotationAttributeValue inverseJoinColumns = new OJAnnotationAttributeValue("joinColumns");
					OJAnnotationValue inverseJoinColumn1 = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
					inverseJoinColumn1.putAttribute("name", p.getMappingInfo().getPersistentName().getWithoutId() + "_original_id");
					inverseJoinColumn1.putAttribute("referencedColumnName", getBaseTypeRoot(p).getMappingInfo().getPersistentName() + "_id");
					inverseJoinColumns.addAnnotationValue(joinColumn1);
					OJAnnotationValue inverseJoinColumn2 = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
					inverseJoinColumn2.putAttribute("name", p.getMappingInfo().getPersistentName().getWithoutId() + "_object_version");
					inverseJoinColumn2.putAttribute("referencedColumnName", "object_version");
					inverseJoinColumns.addAnnotationValue(inverseJoinColumn2);
					joinTable.putAttribute(inverseJoinColumns);
					field.addAnnotationIfNew(joinTable);
				}
			} else if (map.isOne()) {
				field.removeAnnotation(new OJPathName("javax.persistence.JoinColumn"));
				OJAnnotationValue joinColumns = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumns"));
				OJAnnotationValue joinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
				OJAnnotationAttributeValue nameAnnotationAttribute = new OJAnnotationAttributeValue("name");
				nameAnnotationAttribute.addStringValue(p.getMappingInfo().getPersistentName().getWithoutId() + "_original_id");
				joinColumn.putAttribute(nameAnnotationAttribute);
				OJAnnotationAttributeValue referencedAnnotationAttribute = new OJAnnotationAttributeValue("referencedColumnName");
				referencedAnnotationAttribute.addStringValue(getBaseTypeRoot(p).getMappingInfo().getPersistentName() + "_id");
				joinColumn.putAttribute(referencedAnnotationAttribute);
				joinColumn.putAttribute(new OJAnnotationAttributeValue("unique", false));
				joinColumns.addAnnotationValue(joinColumn);
				joinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
				nameAnnotationAttribute = new OJAnnotationAttributeValue("name");
				nameAnnotationAttribute.addStringValue(p.getMappingInfo().getPersistentName().getWithoutId() + "_object_version");
				joinColumn.putAttribute(nameAnnotationAttribute);
				referencedAnnotationAttribute = new OJAnnotationAttributeValue("referencedColumnName");
				referencedAnnotationAttribute.addStringValue("object_version");
				joinColumn.putAttribute(referencedAnnotationAttribute);
				joinColumns.addAnnotationValue(joinColumn);
				field.addAnnotationIfNew(joinColumns);
			}
		}
	}

	private void addOriginalNamedQuery(OJAnnotatedClass auditClass, INakedClassifier owner) {
		OJAnnotationValue namedQueries = auditClass.findAnnotation(new OJPathName("javax.persistence.NamedQueries"));
		if (namedQueries == null) {
			namedQueries = new OJAnnotationValue(new OJPathName("javax.persistence.NamedQueries"));
			auditClass.addAnnotationIfNew(namedQueries);
		}
		OJAnnotationAttributeValue oneToManyNamedQueryAttr = namedQueries.findAttribute("value");
		if (oneToManyNamedQueryAttr == null) {
			oneToManyNamedQueryAttr = new OJAnnotationAttributeValue("value");
			namedQueries.putAttribute(oneToManyNamedQueryAttr);
		}
		OJAnnotationValue oneToManyNamedQuery = new OJAnnotationValue(new OJPathName("javax.persistence.NamedQuery"));
		oneToManyNamedQuery.putAttribute("name", "GetAuditsFor" + owner.getMappingInfo().getJavaName());
		oneToManyNamedQuery.putAttribute("query", "from " + owner.getMappingInfo().getJavaName() + "_Audit a where a."
				+ /*owner.getMappingInfo().getJavaName().getDecapped()*/ "_original" + " =:original and a.deletedOn > "
				+ HibernateUtil.getHibernateDialect(this.config).getCurrentTimestampSQLFunctionName());
		oneToManyNamedQueryAttr.addAnnotationValue(oneToManyNamedQuery);
	}

	private void addOneToManyGetter(OJAnnotatedClass auditClass, INakedEntity owner, INakedProperty p, NakedStructuralFeatureMap map) {
		OJPathName resultPath = map.javaTypePath();
		resultPath.getElementTypes().get(0).replaceTail(resultPath.getElementTypes().get(0).getLast() + "_Audit");
		List<OJPathName> elementTypes = new ArrayList<OJPathName>();
		elementTypes.add(resultPath.getElementTypes().get(0));
		OJAnnotatedOperation oper = new OJAnnotatedOperation();
		oper.setName(map.getter());
		OJParameter param = new OJParameter();
		param.setType(new OJPathName("javax.persistence.EntityManager"));
		param.setName("entityManager");
		oper.addToParameters(param);
		oper.setReturnType(resultPath);
		auditClass.addToOperations(oper);
		OJField resultField = new OJField();
		OJPathName resultFieldPath = new OJPathName("java.util.Map");
		List<OJPathName> resultMapTypes = new ArrayList<OJPathName>();
		resultMapTypes.add(new OJPathName("java.lang.Long"));
		resultMapTypes.add(resultPath.getElementTypes().get(0));
		resultFieldPath.setElementTypes(resultMapTypes);
		resultField.setType(resultFieldPath);
		resultField.setName("result");
		resultField.setInitExp("new HashMap<Long," + resultPath.getElementTypes().get(0).getLast() + ">()");
		auditClass.addToImports(new OJPathName("java.util.HashMap"));
		auditClass.addToImports(new OJPathName("java.util.Map"));
		oper.getBody().addToLocals(resultField);
		OJPathName result = new OJPathName("java.util.List");
		result.setElementTypes(elementTypes);
		auditClass.addToImports(result);
		OJField f = new OJField();
		f.setName("queryResult");
		f.setType(result);
		f.setInitExp("entityManager.createNativeQuery(\"select * from " + resultPath.getElementTypes().get(0).getLast() + " a where a."
				+ p.getOtherEnd().getMappingInfo().getPersistentName().getWithoutId() + "_original_id = :originalId and a.deleted_on > "
				+ HibernateUtil.getHibernateDialect(this.config).getCurrentTimestampSQLFunctionName() + "\", " + resultPath.getElementTypes().get(0).getLast()
				+ ".class).setParameter(\"originalId\", getId().getOriginalId()).getResultList()");
		oper.getBody().addToLocals(f);
		OJForStatement forS = new OJForStatement("", "", "tmpAudit", "queryResult");
		forS.setElemType(resultPath.getElementTypes().get(0));
		OJField tmp = new OJField();
		tmp.setType(resultPath.getElementTypes().get(0));
		tmp.setName("tmp");
		tmp.setInitExp("result.get(tmpAudit.getId().getOriginalId())");
		forS.setBody(new OJBlock());
		forS.getBody().addToLocals(tmp);
		oper.getBody().addToStatements(forS);
		OJIfStatement ifS = new OJIfStatement("tmp==null || tmpAudit.getId().getObjectVersion()>tmp.getId().getObjectVersion()",
				"result.put(tmpAudit.getId().getOriginalId(), tmpAudit);");
		forS.getBody().addToStatements(ifS);
		String replace = map.javaDefaultValue().replace(map.javaBaseType(), resultPath.getElementTypes().get(0).getLast());
		OJSimpleStatement returnS = new OJSimpleStatement("return " + replace.substring(0, replace.length() - 1) + "result.values())");
		oper.getBody().addToStatements(returnS);
	}

	private INakedClassifier getBaseTypeRoot(INakedProperty p) {
		INakedClassifier baseType = p.getNakedBaseType();
		return getRoot(baseType);
	}

	private INakedClassifier getRoot(INakedClassifier baseType) {
		while (baseType.getSupertype() != null) {
			baseType = baseType.getSupertype();
		}
		return baseType;
	}

	@VisitAfter(matchSubclasses = true)
	public void visitFeature(INakedEntity entity) {
		for (INakedProperty p : entity.getEffectiveAttributes()) {
			if (p.getOwner() instanceof INakedInterface) {
				visitProperty(entity, p);
			}
		}
	}

	@VisitBefore(matchSubclasses = true)
	public void visitClasses(INakedClassifier c) {
		OJPathName path = OJUtil.classifierPathname(c);
		OJAnnotatedClass auditClass = (OJAnnotatedClass) this.javaModel.findIntfOrCls(path);
		if ((isPersistent(c) || c instanceof INakedInterface) && OJUtil.hasOJClass(c)) {
			if (auditClass != null) {
				if (auditClass.isAbstract()) {
					auditClass.addToImplementedInterfaces(new OJPathName("java.io.Serializable"));
				}
				auditClass.removeFromOperations(OJUtil.findOperation(auditClass, "allInstances"));
				removeAbstractEntityInterface(auditClass);
				// TODO remove the underscore
				auditClass.renameAll(this.classPathNames, "_Audit");
				if (isPersistent(c)) {
					renameTableAnnotation(auditClass);
					renameAnyMetaDefAnnotations(auditClass);
					addRevisionField(auditClass);
					addPreviousVersionField(auditClass, c);
					addRevisionTypeField(auditClass);
					addOriginalNamedQuery(auditClass, c);
					addOriginalNamedQueryWithStartEndDate(auditClass, c);
					// implementAudited(c, auditClass);
					removeDeletedOnFilter(auditClass);
					// remove uniqueConstraints
					OJAnnotationValue table = auditClass.findAnnotation(new OJPathName("javax.persistence.Table"));
					table.removeAttribute("uniqueConstraints");
					addOriginalField(c, auditClass);
					if (c.getGeneralizations().isEmpty()) {
						// remove id and object version from audited class
						auditClass.removeFromFields(auditClass.findField("id"));
						auditClass.removeFromFields(auditClass.findField("objectVersion"));
						List<OJPathName> params = new ArrayList<OJPathName>();
						params.add(new OJPathName(Long.class.getName()));
						auditClass.removeFromOperations(auditClass.findOperation("getId", Collections.EMPTY_LIST));
						auditClass.removeFromOperations(auditClass.findOperation("getObjectVersion", Collections.EMPTY_LIST));
						auditClass.removeFromOperations(auditClass.findOperation("setId", params));
						params = new ArrayList<OJPathName>();
						params.add(new OJPathName("int"));
						auditClass.removeFromOperations(auditClass.findOperation("setObjectVersion", params));
						// Add auditedId field
						OJUtil.addProperty(auditClass, "id", new OJPathName("net.sf.nakeduml.util.AuditId"), true);
						annotateEmbeddedId(c, auditClass);
					}
					// Add Audited
					auditClass.addToImplementedInterfaces(new OJPathName("net.sf.nakeduml.util.Audited"));
					// Fix toOne join columns to reference 2 columns, id and
					// objectVersion
					// fixToOneJoinColumns(c, auditClass);
					addShallowCopy(c, auditClass);
				}
				super.createTextPath(auditClass, JavaTextSource.GEN_SRC);
			}
		}
		massageBehaviorLogic(c, auditClass);
	}

	private void massageBehaviorLogic(INakedClassifier c, OJAnnotatedClass auditClass) {
		if (c instanceof INakedBehavior) {
			INakedBehavior b = (INakedBehavior) c;
			if (b.isProcess()) {
				OJAnnotatedField contextObject = (OJAnnotatedField) auditClass.findField("contextObject");
				contextObject.removeAnnotation(new OJPathName("javax.persistence.JoinColumn"));
				OJAnnotationValue joinColumns = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumns"));
				OJAnnotationValue contextObjectId = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
				joinColumns.addAnnotationValue(contextObjectId);
				contextObjectId.putAttribute("unique", Boolean.FALSE);
				contextObjectId.putAttribute("referencedColumnName", getRoot(b.getContext()).getMappingInfo().getPersistentName() + "_id");
				contextObjectId.putAttribute("name", "context_object_id");
				OJAnnotationValue contextObjectVersion = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
				contextObjectVersion.putAttribute("unique", Boolean.FALSE);
				contextObjectVersion.putAttribute("referencedColumnName", "object_version");
				contextObjectVersion.putAttribute("name", "context_object_version");
				joinColumns.addAnnotationValue(contextObjectVersion);
				contextObject.putAnnotation(joinColumns);
				List<? extends INakedParameter> parms = b.getOwnedParameters();
				for (INakedParameter parm : parms) {
					visitProperty(b, new TypedElementPropertyBridge(b, parm));
				}
				if (b instanceof INakedActivity) {
					INakedActivity activity = (INakedActivity) b;
					List<INakedActivityNode> nodes = ((INakedActivity) b).getActivityNodesRecursively();
					for (INakedActivityNode node : nodes) {
						if (node instanceof INakedOutputPin && BehaviorUtil.mustBeStoredOnActivity((INakedOutputPin) node)) {
							visitProperty(new TypedElementPropertyBridge(activity, (INakedTypedElement) node));
						} else if (node instanceof INakedExpansionNode && BehaviorUtil.mustBeStoredOnActivity((INakedExpansionNode) node)) {
							visitProperty(new TypedElementPropertyBridge(activity, (INakedTypedElement) node));
						} else if (node instanceof INakedCallAction && BehaviorUtil.mustBeStoredOnActivity((INakedCallAction) node)) {
							ActionFeatureBridge bridge = new ActionFeatureBridge((IActionWithTarget) node);
							TypeResolver.resolveCollection(bridge, bridge.getBaseType(), getOclEngine().getOclLibrary());
							visitProperty(bridge);
						}
					}
					Collection<INakedActivityVariable> vars = activity.getVariables();
					for (INakedActivityVariable var : vars) {
						visitProperty(new TypedElementPropertyBridge(activity, var));
					}
				}
			}
			INakedClassifier ctx = b.getContext();
			if (ctx != null) {
				OJAnnotatedOperation oper = null;
				OJPathName ctxPath = OJUtil.classifierPathname(ctx);
				ctxPath.replaceTail(ctxPath.getLast() + "_Audit");
				OJAnnotatedClass ojCtx = (OJAnnotatedClass) this.javaModel.findIntfOrCls(ctxPath);
				// TODO support overloading
				if (b.getSpecification() == null) {
					oper = (OJAnnotatedOperation) OJUtil.findOperation(ojCtx, b.getName());
				} else {
					oper = (OJAnnotatedOperation) OJUtil.findOperation(ojCtx, b.getSpecification().getName());
				}
				if (oper != null) {
					oper.setBody(new OJBlock());
					if (!oper.getReturnType().getLast().equals("void")) {
						oper.getBody().addToStatements("return null");
					}
				}
			}
			if (BehaviorUtil.hasExecutionInstance(b)) {
				List<OJOperation> operations = auditClass.getOperations();
				for (OJOperation oper : operations) {
					if ((oper.getName().startsWith("do") || oper.getName().startsWith("fire") || oper.getName().startsWith("cancel"))
							&& oper.getReturnType().getLast().equals("void")) {
						oper.setBody(new OJBlock());
					}
				}
			}
		}
	}

	private void addOriginalNamedQueryWithStartEndDate(OJAnnotatedClass auditClass, INakedClassifier owner) {
		OJAnnotationValue namedQueries = auditClass.findAnnotation(new OJPathName("javax.persistence.NamedQueries"));
		if (namedQueries == null) {
			namedQueries = new OJAnnotationValue(new OJPathName("javax.persistence.NamedQueries"));
			auditClass.addAnnotationIfNew(namedQueries);
		}
		OJAnnotationAttributeValue oneToManyNamedQueryAttr = namedQueries.findAttribute("value");
		if (oneToManyNamedQueryAttr == null) {
			oneToManyNamedQueryAttr = new OJAnnotationAttributeValue("value");
			namedQueries.putAttribute(oneToManyNamedQueryAttr);
		}
		OJAnnotationValue oneToManyNamedQuery = new OJAnnotationValue(new OJPathName("javax.persistence.NamedQuery"));
		oneToManyNamedQuery.putAttribute("name", "GetAuditsBetweenFor" + owner.getMappingInfo().getJavaName());
		oneToManyNamedQuery.putAttribute("query", "from " + owner.getMappingInfo().getJavaName() + "_Audit a where a."
				+ /*owner.getMappingInfo().getJavaName().getDecapped()*/ "_original" + " =:original and (a.createdOn between :start and :end) and a.deletedOn > "
				+ HibernateUtil.getHibernateDialect(this.config).getCurrentTimestampSQLFunctionName());
		oneToManyNamedQueryAttr.addAnnotationValue(oneToManyNamedQuery);
	}

	private void addPreviousVersionField(OJAnnotatedClass c, INakedClassifier umlClass) {
		OJAnnotatedField previousVersion = new OJAnnotatedField();
		OJPathName previousVersionPath = c.getPathName();
		previousVersion.setType(c.getPathName());
		previousVersion.setName("previousVersion");
		previousVersion.setOwner(c);
		OJAnnotationValue joinColumns = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumns"));
		OJAnnotationValue joinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
		OJAnnotationAttributeValue nameAnnotationAttribute = new OJAnnotationAttributeValue("name");
		nameAnnotationAttribute.addStringValue(umlClass.getMappingInfo().getPersistentName().getWithoutId() + "_previous_id");
		joinColumn.putAttribute(nameAnnotationAttribute);
		OJAnnotationAttributeValue referencedAnnotationAttribute = new OJAnnotationAttributeValue("referencedColumnName");
		referencedAnnotationAttribute.addStringValue(getRoot(umlClass).getMappingInfo().getPersistentName() + "_id");
		joinColumn.putAttribute(referencedAnnotationAttribute);
		joinColumn.putAttribute(new OJAnnotationAttributeValue("unique", false));
		joinColumns.addAnnotationValue(joinColumn);
		joinColumn = new OJAnnotationValue(new OJPathName("javax.persistence.JoinColumn"));
		nameAnnotationAttribute = new OJAnnotationAttributeValue("name");
		nameAnnotationAttribute.addStringValue("previous_object_version");
		joinColumn.putAttribute(nameAnnotationAttribute);
		joinColumn.putAttribute(new OJAnnotationAttributeValue("unique", false));
		referencedAnnotationAttribute = new OJAnnotationAttributeValue("referencedColumnName");
		referencedAnnotationAttribute.addStringValue("object_version");
		joinColumn.putAttribute(referencedAnnotationAttribute);
		joinColumns.addAnnotationValue(joinColumn);
		previousVersion.addAnnotationIfNew(joinColumns);
		OJAnnotationValue toOne = new OJAnnotationValue(new OJPathName("javax.persistence.ManyToOne"));
		JpaUtil.fetchLazy(toOne);
		previousVersion.addAnnotationIfNew(toOne);
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName("getPreviousVesion");
		getter.setReturnType(previousVersionPath);
		getter.getBody().addToStatements("return previousVersion");
		getter.setStatic(false);
		c.addToOperations(getter);
		OJOperation setter = new OJAnnotatedOperation();
		setter.setName("setPreviousVersion");
		setter.addParam("previousVersion", previousVersionPath);
		setter.getBody().addToStatements("this.previousVersion = previousVersion");
		setter.setStatic(false);
		c.addToOperations(setter);
		setter = new OJAnnotatedOperation();
		setter.setName("setPreviousVersion");
		OJPathName auditedPathName = new OJPathName("net.sf.nakeduml.util.Audited");
		setter.addParam("previousVersion", auditedPathName);
		setter.getBody().addToStatements("setPreviousVersion((" + previousVersionPath.getLast() + ") previousVersion)");
		setter.setStatic(false);
		c.addToOperations(setter);
		setter = new OJAnnotatedOperation();
		setter.setName("setPreviousVersionWithoutCheck");
		setter.addParam("previousVersion", auditedPathName);
		setter.getBody().addToStatements("setPreviousVersion((" + previousVersionPath.getLast() + ") previousVersion)");
		setter.setStatic(false);
		c.addToOperations(setter);
		getter = new OJAnnotatedOperation();
		getter.setName("getPreviousVersion");
		getter.setReturnType(auditedPathName);
		getter.getBody().addToStatements("return previousVersion");
		getter.setStatic(false);
		c.addToOperations(getter);
	}

	private void annotateEmbeddedId(INakedClassifier c, OJAnnotatedClass auditClass) {
		OJAnnotatedField idField = (OJAnnotatedField) auditClass.findField("id");
		OJAnnotationValue id = new OJAnnotationValue(new OJPathName("javax.persistence.EmbeddedId"));
		idField.putAnnotation(id);
		OJAnnotationValue attributeOverrides = new OJAnnotationValue(new OJPathName("javax.persistence.AttributeOverrides"));
		OJAnnotationValue originalIdAttributeOverride = new OJAnnotationValue(new OJPathName("javax.persistence.AttributeOverride"));
		OJAnnotationAttributeValue name = new OJAnnotationAttributeValue("name");
		name.addStringValue("originalId");
		originalIdAttributeOverride.putAttribute(name);
		OJAnnotationAttributeValue column = new OJAnnotationAttributeValue("column");
		OJAnnotationValue columnAnnotation = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		columnAnnotation.putAttribute(new OJAnnotationAttributeValue("name", JpaUtil.BACKTICK + c.getMappingInfo().getPersistentName() + "_id"
				+ JpaUtil.BACKTICK));
		column.addAnnotationValue(columnAnnotation);
		originalIdAttributeOverride.putAttribute(column);
		attributeOverrides.addAnnotationValue(originalIdAttributeOverride);
		OJAnnotationValue objectVersionIdAttributeOverride = new OJAnnotationValue(new OJPathName("javax.persistence.AttributeOverride"));
		name = new OJAnnotationAttributeValue("name");
		name.addStringValue("objectVersion");
		objectVersionIdAttributeOverride.putAttribute(name);
		column = new OJAnnotationAttributeValue("column");
		columnAnnotation = new OJAnnotationValue(new OJPathName("javax.persistence.Column"));
		columnAnnotation.putAttribute(new OJAnnotationAttributeValue("name", JpaUtil.BACKTICK + "object_version" + JpaUtil.BACKTICK));
		column.addAnnotationValue(columnAnnotation);
		objectVersionIdAttributeOverride.putAttribute(column);
		attributeOverrides.addAnnotationValue(objectVersionIdAttributeOverride);
		idField.putAnnotation(attributeOverrides);
	}

	private void removeAbstractEntityInterface(OJAnnotatedClass auditClass) {
		OJPathName toRemove = null;
		Set<OJPathName> implementedInterfaces = auditClass.getImplementedInterfaces();
		for (OJPathName ojPathName : implementedInterfaces) {
			if (ojPathName.equals(new OJPathName(AbstractEntity.class.getName()))) {
				toRemove = ojPathName;
				break;
			}
		}
		auditClass.removeFromImplementedInterfaces(toRemove);
		Set<OJPathName> imports = auditClass.getImports();
		for (OJPathName ojPathName : imports) {
			if (ojPathName.equals(new OJPathName(AbstractEntity.class.getName()))) {
				toRemove = ojPathName;
				break;
			}
		}
		if (auditClass instanceof OJAnnotatedInterface) {
			OJAnnotatedInterface i = (OJAnnotatedInterface) auditClass;
			for (OJPathName ojPathName : i.getSuperInterfaces()) {
				if (ojPathName.equals(new OJPathName(AbstractEntity.class.getName()))) {
					toRemove = ojPathName;
					break;
				}
			}
			i.removeFromSuperInterfaces(toRemove);
		}
		auditClass.removeFromImports(toRemove);
	}

	private void removeDeletedOnFilter(OJAnnotatedClass ojClass) {
		List<OJField> fields = ojClass.getFields();
		for (OJField ojField : fields) {
			OJAnnotatedField field = (OJAnnotatedField) ojField;
			List<OJAnnotationValue> remove = new ArrayList<OJAnnotationValue>();
			for (OJAnnotationValue ojAnnotationValue : field.getAnnotations()) {
				if (ojAnnotationValue.getType().equals(new OJPathName("org.hibernate.annotations.Filter"))) {
					remove.add(ojAnnotationValue);
				}
			}
			field.getAnnotations().removeAll(remove);
		}
	}

	private void addShallowCopy(INakedClassifier classifier, OJAnnotatedClass c) {
		c.addToImports(new OJPathName("net.sf.nakeduml.util.AuditId"));
		OJOperation oper = new OJAnnotatedOperation();
		oper.setVisibility(OJVisibilityKindGEN.PUBLIC);
		oper.setName("copyShallowState");
		oper.addParam("from", OJUtil.classifierPathname(classifier));
		oper.addParam("to", c.getPathName());
		c.addToOperations(oper);
		if (!classifier.getGeneralizations().isEmpty()) {
			OJSimpleStatement superCopy = new OJSimpleStatement("super.copyShallowState(from,to)");
			oper.getBody().addToStatements(superCopy);
		} else {
			OJSimpleStatement setId = new OJSimpleStatement("to.setId(new AuditId(from.getId(), from.getObjectVersion()))");
			oper.getBody().addToStatements(setId);
		}
		addCopyToAuditStatements(c, classifier, oper.getBody(), false);
	}

	private OJOperation findMethodIgnorecase(OJClass c, String name) {
		List<OJOperation> operations = c.getOperations();
		for (OJOperation ojOperation : operations) {
			if (ojOperation.getName().equalsIgnoreCase(name)) {
				return ojOperation;
			}
		}
		return null;
	}

	private void addCopyToAuditStatements(OJAnnotatedClass c, INakedClassifier classifier, OJBlock body, boolean deep) {
		// TODO use MappedTypes

		Set<String> javaTypes = new HashSet<String>();
		javaTypes.add("String");
		javaTypes.add("Integer");
		javaTypes.add("Integer");
		javaTypes.add("int");
		javaTypes.add("Boolean");
		javaTypes.add("boolean");
		List<OJField> fields = c.getFields();
		for (OJField ojField : fields) {

			NakedStructuralFeatureMap map = null;
			for (INakedProperty attr : classifier.getEffectiveAttributes()) {
				map = new NakedStructuralFeatureMap(attr);
				if (map.umlName().equals(ojField.getName())) {
					break;
				}
			}

			String javaType = ojField.getType().getLast();
			if (javaTypes.contains(javaType)) {
				OJOperation setter = findMethodIgnorecase(c, map.setter());
				OJOperation getter = findMethodIgnorecase(c, map.getter());
				body.addToStatements("to." + setter.getName() + "(from." + getter.getName() + "())");
			}
		}
	}

	private void addOriginalField(INakedClassifier umlClass, OJAnnotatedClass javaClass) {
		OJAnnotatedField original = new OJAnnotatedField();
		String auditClassPath = javaClass.getPathName().toJavaString();
		OJPathName originalPathName = new OJPathName(auditClassPath.substring(0, auditClassPath.length() - 6));
		original.setType(originalPathName);
		String auditClassName = javaClass.getPathName().getNames().get(javaClass.getPathName().getNames().size() - 1);
		auditClassName = auditClassName.substring(0, auditClassName.length() - 6);
//		original.setName(umlClass.getMappingInfo().getJavaName().getDecapped().toString());
		original.setName("_original");
		original.setOwner(javaClass);
		OJAnnotationValue toOne = new OJAnnotationValue(new OJPathName("javax.persistence.ManyToOne"));
		JpaUtil.fetchLazy(toOne);
		original.addAnnotationIfNew(toOne);
		OJAnnotationValue column = JpaUtil.addJoinColumn(original, umlClass.getMappingInfo().getPersistentName() + "_id", true);
		column.putAttribute(new OJAnnotationAttributeValue("insertable", false));
		column.putAttribute(new OJAnnotationAttributeValue("updatable", false));
		column.putAttribute(new OJAnnotationAttributeValue("unique", false));
		original.addAnnotationIfNew(column);
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName("getOriginal");
		getter.setReturnType(originalPathName);
//		getter.getBody().addToStatements("return " + umlClass.getMappingInfo().getJavaName().getDecapped());
		getter.getBody().addToStatements("return this._original");
		getter.setStatic(false);
		javaClass.addToOperations(getter);
		OJOperation setter = new OJAnnotatedOperation();
		setter.setName("setOriginal");
		setter.addParam(NameConverter.decapitalize(auditClassName), originalPathName);
//		setter.getBody().addToStatements("this." + umlClass.getMappingInfo().getJavaName().getDecapped() + "= " + NameConverter.decapitalize(auditClassName));
		setter.getBody().addToStatements("this._original = " + NameConverter.decapitalize(auditClassName));
		setter.setStatic(false);
		if (umlClass.getGeneralizations().size() > 0) {
			setter.getBody().addToStatements("super.setOriginal( " + NameConverter.decapitalize(auditClassName) + ")");
		}
		javaClass.addToOperations(setter);
		OJAnnotatedOperation originalForAbstractEntity = new OJAnnotatedOperation();
		originalForAbstractEntity.setName("setOriginal");
		originalForAbstractEntity.addParam(NameConverter.decapitalize(auditClassName), new OJPathName("net.sf.nakeduml.util.AbstractEntity"));
		originalForAbstractEntity.getBody().addToStatements(
				"setOriginal((" + umlClass.getMappingInfo().getJavaName() + ") " + NameConverter.decapitalize(auditClassName) + ")");
		javaClass.addToOperations(originalForAbstractEntity);
	}

	private void addRevisionField(OJAnnotatedClass c) {
		OJAnnotatedField revision = new OJAnnotatedField();
		OJPathName revisionPath = new OJPathName("net.sf.nakeduml.util.RevisionEntity");
		revision.setType(revisionPath);
		revision.setName("revision");
		revision.setOwner(c);
		OJAnnotationValue toOne = new OJAnnotationValue(new OJPathName("javax.persistence.ManyToOne"));
		JpaUtil.fetchLazy(toOne);
		revision.addAnnotationIfNew(toOne);
		OJAnnotationValue column = JpaUtil.addJoinColumn(revision, revision.getName() + "_id", true);
		revision.addAnnotationIfNew(column);
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName("getRevision");
		getter.setReturnType(revisionPath);
		getter.getBody().addToStatements("return revision");
		getter.setStatic(false);
		c.addToOperations(getter);
		OJOperation setter = new OJAnnotatedOperation();
		setter.setName("setRevision");
		setter.addParam("revision", revisionPath);
		setter.getBody().addToStatements("this.revision = revision");
		setter.setStatic(false);
		c.addToOperations(setter);
	}

	private void addRevisionTypeField(OJAnnotatedClass c) {
		OJAnnotatedField revisionType = new OJAnnotatedField();
		OJPathName revisionPath = new OJPathName("net.sf.nakeduml.util.RevisionType");
		revisionType.setType(revisionPath);
		revisionType.setName("revisionType");
		revisionType.setOwner(c);
		OJOperation getter = new OJAnnotatedOperation();
		getter.setName("getRevisionType");
		getter.setReturnType(revisionPath);
		getter.getBody().addToStatements("return revisionType");
		getter.setStatic(false);
		c.addToOperations(getter);
		OJOperation setter = new OJAnnotatedOperation();
		setter.setName("setRevisionType");
		setter.addParam("revisionType", revisionPath);
		setter.getBody().addToStatements("this.revisionType = revisionType");
		setter.setStatic(false);
		c.addToOperations(setter);
	}

	private void renameAnyMetaDefAnnotations(OJAnnotatedClass c) {
		for (OJField f : c.getFields()) {
			OJAnnotatedField annotatedField = (OJAnnotatedField) f;
			Set<OJAnnotationValue> annotations = annotatedField.getAnnotations();
			for (OJAnnotationValue ojAnnotationValue : annotations) {
				if (ojAnnotationValue.getType().equals(new OJPathName("org.hibernate.annotations.AnyMetaDef"))) {
					OJAnnotationAttributeValue rolDefMeta = ojAnnotationValue.findAttribute("name");
					String tableName = (String) rolDefMeta.getValues().remove(0);
					rolDefMeta.addStringValue(tableName.substring(0, tableName.length()) + "Audit");
					OJAnnotationAttributeValue idType = ojAnnotationValue.findAttribute("idType");
					idType.getValues().remove(0);
					idType.addStringValue(AUDIT_ID_USER_TYPE);
				}
				if (ojAnnotationValue.getType().equals(new OJPathName("org.hibernate.annotations.Any"))) {
					OJAnnotationAttributeValue rolDefMeta = ojAnnotationValue.findAttribute("metaDef");
					String tableName = (String) rolDefMeta.getValues().remove(0);
					rolDefMeta.addStringValue(tableName.substring(0, tableName.length()) + "Audit");
				}
			}
			if (annotatedField.findAnnotation(new OJPathName("org.hibernate.annotations.ManyToAny")) != null) {
				annotatedField.putAnnotation(new OJAnnotationValue(new OJPathName("javax.persistence.Transient")));
				annotatedField.removeAnnotation(new OJPathName("org.hibernate.annotations.ManyToAny"));
			}
		}
	}

	private void renameTableAnnotation(OJAnnotatedClass c) {
		Set<OJAnnotationValue> annotations = c.getAnnotations();
		for (OJAnnotationValue ojAnnotationValue : annotations) {
			if (ojAnnotationValue.getType().equals(new OJPathName("javax.persistence.Table"))) {
				OJAnnotationAttributeValue tableAttribute = ojAnnotationValue.findAttribute("name");
				String tableName = (String) tableAttribute.getValues().remove(0);
				tableAttribute.addStringValue(tableName.substring(0, tableName.length()) + "_audit" + JpaUtil.BACKTICK);
			}
			if (ojAnnotationValue.getType().equals(new OJPathName("javax.persistence.Entity"))) {
				OJAnnotationAttributeValue tableAttribute = ojAnnotationValue.findAttribute("name");
				String entityName = (String) tableAttribute.getValues().remove(0);
				tableAttribute.addStringValue(entityName.substring(0, entityName.length()) + "_Audit");
			}
			if (ojAnnotationValue.getType().equals(new OJPathName("org.jboss.seam.annotations.Name"))) {
				String seamName = (String) ojAnnotationValue.getValues().remove(0);
				ojAnnotationValue.addStringValue(seamName.substring(0, seamName.length()) + "_audit");
			}
		}
	}
}
