package net.sf.nakeduml.javageneration.hibernate;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.nakeduml.javageneration.AbstractJavaProducingVisitor;
import net.sf.nakeduml.javageneration.JavaTextSource;
import net.sf.nakeduml.javageneration.NakedClassifierMap;
import net.sf.nakeduml.javageneration.util.OJUtil;
import net.sf.nakeduml.javametamodel.OJPathName;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotatedPackage;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationAttributeValue;
import net.sf.nakeduml.javametamodel.annotation.OJAnnotationValue;
import net.sf.nakeduml.linkage.InterfaceUtil;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedRootObject;
import net.sf.nakeduml.metamodel.models.INakedModel;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;

public abstract class AbstractMetaDefAnnotator extends AbstractJavaProducingVisitor {
	private boolean isIntegrationPhase;

	public final class InterfaceCollector extends AbstractJavaProducingVisitor {
		Set<INakedInterface> interfaces = new HashSet<INakedInterface>();
		public void visitInterface(INakedInterface i){
			interfaces.add(i);
		}
	}

	public AbstractMetaDefAnnotator(boolean isIntegrationPhase) {
		super();
		this.isIntegrationPhase = isIntegrationPhase;
	}

	public void startVisiting(INakedModelWorkspace root) {
		if(isIntegrationPhase){
			Set<INakedInterface> interfaces = collectInterfaces(((Collection<? extends INakedElement>) root.getOwnedElements()));
			for (INakedInterface i : interfaces) {
				doInterface(i, new OJPathName(workspace.getName() + ".integratedutil"), InterfaceUtil.getImplementationsOf(i,null));
			}
		}else{
			List<INakedRootObject> generatingModelsOrProfiles = root.getGeneratingModelsOrProfiles();
			for (INakedRootObject rootObject : generatingModelsOrProfiles) {
				if(rootObject instanceof INakedModel){
					Set<INakedInterface> interfaces = collectInterfaces(((INakedModel) rootObject).getDependencies());
					for (INakedInterface i : interfaces) {
						doInterface(i, new OJPathName(rootObject.getMappingInfo().getQualifiedJavaName()+ ".util"), InterfaceUtil.getImplementationsOf(i,null));
					}
				}
			}
		}
	}

	private Set<INakedInterface> collectInterfaces(Collection<? extends INakedElement> ownedElements) {
		InterfaceCollector collector = new InterfaceCollector();
		for (INakedElement e : ownedElements) {
			if(e instanceof INakedModel){
				INakedModel model = (INakedModel)e;
				collector.visitRecursively(model);
			}
		}
		Set<INakedInterface> interfaces = collector.interfaces;
		return interfaces;
	}

	private void doInterface(INakedInterface i, OJPathName path, Collection<INakedEntity> impls) {
		OJAnnotationValue metaDef = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.AnyMetaDef"));
		OJAnnotatedPackage p = (OJAnnotatedPackage) this.findOrCreatePackage(path);
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

	protected final JavaTextSource.OutputRootId getOutputRoot() {
		if (isIntegrationPhase) {
			return JavaTextSource.OutputRootId.INTEGRATED_ADAPTORS_GEN_SRC;
		} else {
			// One model
			return JavaTextSource.OutputRootId.DOMAIN_GEN_TEST_SRC;
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
}