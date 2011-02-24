package net.sf.nakeduml.javageneration.persistence;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.nakeduml.feature.visit.VisitAfter;
import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.hibernate.HibernateUtil;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.linkage.InterfaceUtil;
import net.sf.nakeduml.metamodel.actions.INakedCallOperationAction;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedMessageStructure;
import net.sf.nakeduml.metamodel.core.INakedOperation;

public abstract class AbstractMetaDefAnnotator extends AbstractJavaProducingVisitor {
	Map<INakedOperation, OJAnnotationValue> operations = new HashMap<INakedOperation, OJAnnotationValue>();

	public AbstractMetaDefAnnotator() {
		super();
	}


	protected void doInterface(INakedInterface i) {
		Collection<INakedEntity> impls = InterfaceUtil.getImplementationsOf(i);
		OJAnnotationValue metaDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDef"));
		OJAnnotatedPackage p = (OJAnnotatedPackage) this.findOrCreatePackage(OJUtil.packagePathname(i.getNameSpace()));
		createTextPathIfRequired(p, getOutputRoot());
		OJAnnotationValue anyMetaDefs = getAnyMetaDefs(p);
		anyMetaDefs.addAnnotationValue(metaDef);
		metaDef.putAttribute("name", getMetaDefName(i));
		metaDef.putAttribute("metaType", "string");
		metaDef.putAttribute("idType", getIdType());
		OJAnnotationAttributeValue metaValues = new OJAnnotationAttributeValue("metaValues");
		metaDef.putAttribute(metaValues);
		for (INakedEntity iNakedEntity : impls) {
			OJAnnotationValue metaValue = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.MetaValue"));
			NakedClassifierMap map = new NakedClassifierMap(iNakedEntity);
			OJPathName javaTypePath = map.javaTypePath();
			metaValue.putAttribute("value", javaTypePath.toString());
			metaValue.putAttribute("targetEntity", getTargetEntity(javaTypePath));
			metaValues.addAnnotationValue(metaValue);
		}
	}

	protected abstract OJPathName getTargetEntity(OJPathName javaTypePath);

	protected abstract String getIdType();

	protected abstract String getMetaDefName(INakedInterface i);

	protected final JavaTextSource.OutputRootId getOutputRoot(){
		if(workspace.isSingleModelWorkspace()){
			//One model
			return JavaTextSource.OutputRootId.DOMAIN_GEN_TEST_SRC;
		}else{
			return JavaTextSource.OutputRootId.INTEGRATED_ADAPTORS_GEN_SRC;
			
		}
	}

	private OJAnnotationValue getAnyMetaDefs(OJAnnotatedPackage p) {
		OJAnnotationValue anyMetaDefs = p.findAnnotation(new OJPathName("org.hibernate.annotations.AnyMetaDefs"));
		if (anyMetaDefs == null) {
			anyMetaDefs = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDefs"));
			p.putAnnotation(anyMetaDefs);
		}
		return anyMetaDefs;
	}

	public void visitCallAction(INakedCallOperationAction a) {
		if (a.getOperation().isUserResponsibility()) {
			OJAnnotationValue metaDef = operations.get(a.getOperation());
			if (metaDef == null) {
				metaDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDef"));
				operations.put(a.getOperation(), metaDef);
				INakedMessageStructure message = a.getMessageStructure();
				OJAnnotatedPackage p = (OJAnnotatedPackage) this.findOrCreatePackage(OJUtil.classifierPathname(message));
				createTextPathIfRequired(p, getOutputRoot());
				OJAnnotationValue anyMetaDefs = getAnyMetaDefs(p);
				anyMetaDefs.addAnnotationValue(metaDef);
				metaDef.putAttribute("name", HibernateUtil.metadefName(message));
				metaDef.putAttribute("metaType", "string");
				metaDef.putAttribute("idType", "long");
				OJAnnotationAttributeValue metaValues = new OJAnnotationAttributeValue("metaValues");
				metaDef.putAttribute(metaValues);
			}
			OJAnnotationValue metaValue = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.MetaValue"));
			NakedClassifierMap map = new NakedClassifierMap(a.getActivity());
			OJPathName javaTypePath = map.javaTypePath();
			metaValue.putAttribute("value", javaTypePath.toString());
			metaValue.putAttribute("targetEntity", javaTypePath);
			metaDef.findAttribute("metaValues").addAnnotationValue(metaValue);
		}
	}

	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root) {
		return root.getOwnedElements();
	}
}