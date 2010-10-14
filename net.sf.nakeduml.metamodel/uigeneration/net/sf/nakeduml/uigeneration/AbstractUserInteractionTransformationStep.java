package net.sf.nakeduml.uigeneration;

import static net.sf.nakeduml.uigeneration.StereotypeNames.getTag;

import java.util.Collection;
import java.util.List;

import net.sf.nakeduml.domainmetamodel.DomainClassifier;
import net.sf.nakeduml.domainmetamodel.DomainPackage;
import net.sf.nakeduml.domainmetamodel.SecurityOnUserAction;
import net.sf.nakeduml.feature.NakedUmlConfig;
import net.sf.nakeduml.feature.TransformationStep;
import net.sf.nakeduml.feature.visit.VisitSpec;
import net.sf.nakeduml.feature.visit.VisitorAdapter;
import net.sf.nakeduml.javametamodel.OJPackage;
import net.sf.nakeduml.metamodel.core.INakedClassifier;
import net.sf.nakeduml.metamodel.core.INakedElement;
import net.sf.nakeduml.metamodel.core.INakedElementOwner;
import net.sf.nakeduml.metamodel.core.INakedEntity;
import net.sf.nakeduml.metamodel.core.INakedInstanceSpecification;
import net.sf.nakeduml.metamodel.core.INakedInterface;
import net.sf.nakeduml.metamodel.core.INakedNameSpace;
import net.sf.nakeduml.metamodel.core.INakedOperation;
import net.sf.nakeduml.metamodel.core.INakedPackage;
import net.sf.nakeduml.metamodel.core.INakedParameter;
import net.sf.nakeduml.metamodel.core.INakedProperty;
import net.sf.nakeduml.metamodel.core.INakedSlot;
import net.sf.nakeduml.metamodel.core.INakedValueSpecification;
import net.sf.nakeduml.metamodel.workspace.INakedModelWorkspace;
import net.sf.nakeduml.name.NameConverter;
import net.sf.nakeduml.textmetamodel.TextWorkspace;
import net.sf.nakeduml.userinteractionmetamodel.AbstractUserInteractionFolder;
import net.sf.nakeduml.userinteractionmetamodel.ClassifierUserInteraction;
import net.sf.nakeduml.userinteractionmetamodel.OperationUserInteractionKind;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionKind;
import net.sf.nakeduml.userinteractionmetamodel.UserInteractionWorkspace;

public abstract class AbstractUserInteractionTransformationStep extends VisitorAdapter<INakedElementOwner,INakedModelWorkspace> implements
		TransformationStep{
	protected TextWorkspace textWorkspace;
	protected INakedPackage entryModel;
	protected UserInteractionWorkspace uiModel;
	protected OJPackage javaModel;
	protected NakedUmlConfig config;
	public void initialize(INakedPackage workspace,TextWorkspace textWorkspace,UserInteractionWorkspace uiModel,OJPackage javaModel, NakedUmlConfig config){
		this.entryModel = workspace;
		this.textWorkspace = textWorkspace;
		this.uiModel = uiModel;
		this.javaModel = javaModel;
		this.config = config;
	}
	@Override
	public Collection<? extends INakedElementOwner> getChildren(INakedElementOwner root){
		return root.getOwnedElements();
	}
	protected DomainPackage findDomainPackageFor(INakedNameSpace p){
		if(p.getParent() == null){
			return uiModel.findPackage(getDomainNameOfPackage(p));
		}else{
			DomainPackage parent = findDomainPackageFor(p.getParent());
			return (DomainPackage) parent.findOwnedElement(getDomainNameOfPackage(p));
		}
	}
	protected boolean hasUserInteractions(INakedClassifier instance){
		Boolean hasUserInteraction = StereotypeNames.getTag(instance, StereotypeNames.CLASSIFIER_SPECIFICATION, StereotypeNames.HAS_USER_INTERACTION);
		if (hasUserInteraction==null) {
			hasUserInteraction = true;
		}
		return (hasUserInteraction) && instance instanceof INakedEntity && isInEntryModel(instance.getNameSpace()) && !instance.getIsAbstract();
	}
	private boolean isInEntryModel(INakedNameSpace owner){
		return owner != null && (owner.equals(entryModel) || isInEntryModel(owner.getParent()));
	}
	protected DomainClassifier findDomainClassifierFor(INakedClassifier p){
		DomainPackage parent = findDomainPackageFor(p.getNameSpace());
		return (DomainClassifier) parent.findOwnedElement(getDomainNameOf(p));
	}
	protected AbstractUserInteractionFolder findFolderFor(INakedNameSpace p){
		if(p.getParent()==null){
			return uiModel.findFolder(getDomainNameOfPackage(p));
		}else{
			AbstractUserInteractionFolder parent = findFolderFor(p.getParent());
			return (AbstractUserInteractionFolder) parent.findOwnedElement(getDomainFQNameOfPackage(p));
		}
	}
	protected AbstractUserInteractionFolder findFolderFor(INakedClassifier c){
		AbstractUserInteractionFolder packageFolder = findFolderFor(c.getNameSpace());
		if (packageFolder!=null) {
			return (AbstractUserInteractionFolder) packageFolder.findOwnedElement(getDomainFQNameOf(c));
		} else {
			return null;
		}
	}
	@Override
	protected void maybeVisit(INakedElementOwner o,VisitSpec v){
		super.maybeVisit(o, v);
	}
	// These methods decide which name to use on the domain. Usually the implementation names
	protected String getDomainNameOf(INakedParameter parameter){
		return parameter.getMappingInfo().getJavaName().toString();
	}
	protected String getDomainNameOf(INakedProperty property){
		return property.getMappingInfo().getJavaName().toString();
	}
	protected String getDomainNameOf(INakedClassifier c){
		return c.getMappingInfo().getJavaName().toString();
	}
	protected String getDomainNameOf(INakedOperation o){
		return o.getMappingInfo().getJavaName().toString();
	}
	protected String getDomainNameOfPackage(INakedNameSpace o){
		return o.getMappingInfo().getJavaName().toString().toLowerCase();
	}
	protected String getUserInteractionNameOf(INakedOperation operation,OperationUserInteractionKind kind){
		return operation.getMappingInfo().getJavaName().getCapped() + NameConverter.capitalize(kind.name().toLowerCase());
	}
	protected String getUserInteractionNameOf(INakedClassifier c,UserInteractionKind kind){
		String result = c.getMappingInfo().getJavaName() + NameConverter.capitalize(kind.name().toLowerCase());
		return result;
	}
	protected String getUserInteractionNameOf(INakedInstanceSpecification s){
		//Currently assumes uniqueness of instanceSpec within entity
		return s.getMappingInfo().getJavaName().toString();
	}

	
	// These methods decide which name to use on the domain. Usually the implementation names
	protected String getDomainFQNameOf(INakedParameter parameter){
		return parameter.getMappingInfo().getQualifiedJavaName().toString().replace(".", "::");
	}
	protected String getDomainFQNameOf(ClassifierUserInteraction owner, INakedProperty property){
		return property.getOwner().getMappingInfo().getQualifiedJavaName().toString().concat(".").concat(owner.getName()).concat(".").concat(property.getMappingInfo().getJavaName().toString()).replace(".", "::");
	}
	protected String getDomainFQNameOf(ClassifierUserInteraction owner, INakedClassifier c, INakedProperty property){
		return c.getMappingInfo().getQualifiedJavaName().toString().concat(".").concat(owner.getName()).concat(".").concat(property.getMappingInfo().getJavaName().toString()).replace(".", "::");
	}
	protected String getDomainFQNameOf(INakedClassifier c){
		return c.getMappingInfo().getQualifiedJavaName().toString().replace(".", "::");
	}
	protected String getDomainFQNameOf(INakedOperation o){
		return o.getMappingInfo().getQualifiedJavaName().toString().replace(".", "::");
	}
	protected String getDomainFQNameOfPackage(INakedNameSpace o){
		return o.getMappingInfo().getQualifiedJavaName().toString().toLowerCase().replace(".", "::");
	}
	protected String getUserInteractionFQNameOf(INakedOperation operation,OperationUserInteractionKind kind){
		String result = operation.getOwner().getMappingInfo().getQualifiedJavaName() + "." + operation.getMappingInfo().getJavaName().getCapped() + NameConverter.capitalize(kind.name().toLowerCase());
		return result.replace(".", "::");
	}
	protected String getUserInteractionFQNameOf(INakedClassifier c,UserInteractionKind kind){
		String result = c.getMappingInfo().getQualifiedJavaName() + "." + c.getMappingInfo().getJavaName() + NameConverter.capitalize(kind.name().toLowerCase());
		return result.replace(".", "::");
	}
	protected String getUserInteractionFQNameOf(INakedInstanceSpecification s){
		//Currently assumes uniqueness of instanceSpec within entity
		return s.getMappingInfo().getQualifiedJavaName().toString().replace(".", "::");
	}	
	
	
	protected SecurityOnUserAction createSecureUserAction(INakedElement e,String stereotypeName){
		SecurityOnUserAction result = new SecurityOnUserAction();
		INakedInstanceSpecification stereotype = e.getStereotype(stereotypeName);
		if(stereotype != null){
			INakedSlot slot = stereotype.getSlotForFeature("requiredRoles");
			if(slot != null){
				List<INakedValueSpecification> values = slot.getValues();
				for(INakedValueSpecification v:values){
					INakedInterface ni = (INakedInterface) v.getValue();
					Collection<INakedClassifier> roles = ni.getImplementingClassifiers();
					for (INakedClassifier role : roles) {
						result.addToRequiredRoles(getDomainNameOf(role));
					}
				}
			}
			result.setRequiresUserOwnership((Boolean) getTag(e, stereotypeName, "requiresUserOwnership"));
			result.setRequiresGroupOwnership((Boolean) stereotype.getFirstValueFor("requiresGroupOwnership").getValue());
		}
		return result;
	}

}
