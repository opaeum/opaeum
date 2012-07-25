package org.opaeum.javageneration.hibernate;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interface;
import org.opaeum.emf.extraction.StereotypesHelper;
import org.opaeum.feature.StepDependency;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.OJVisibilityKind;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedInterface;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.javageneration.JavaTransformationPhase;
import org.opaeum.javageneration.basicjava.AttributeImplementor;
import org.opaeum.javageneration.maps.NakedStructuralFeatureMap;
import org.opaeum.javageneration.util.OJUtil;
import org.opaeum.metamodel.core.internal.StereotypeNames;

@StepDependency(phase = JavaTransformationPhase.class,replaces = AttributeImplementor.class)
public class HibernateAttributeImplementor extends AttributeImplementor{
	@Override
	protected OJAnnotatedOperation buildGetter(Classifier umlOwner, OJAnnotatedClass owner,NakedStructuralFeatureMap map,boolean derived){
		if(isInterfaceValue(owner, map)){
			OJAnnotatedOperation getter = new OJAnnotatedOperation(map.getter());
			getter.setReturnType(map.javaTypePath());
			owner.addToOperations(getter);
			getter.initializeResultVariable("(" + map.javaType() + ")" + getReferencePrefix(owner, map) + map.fieldname()
					+ ".getValue(" +(isPersistent(umlOwner)? "persistence":"null")+")");
			Element property = map.getProperty();
			addPropertyMetaInfo(umlOwner, getter, map.getProperty(),getLibrary() );

			OJUtil.addMetaInfo(getter, property);
			return getter;
		}else{
			return super.buildGetter(umlOwner, owner, map, derived);
		}
	}
	@Override
	protected void buildInternalRemover(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		if(isInterfaceValue(owner, map)){
			OJAnnotatedOperation remover = new OJAnnotatedOperation(map.internalRemover());
			String condition = map.getter() + "()!=null && val!=null && val.equals(" + map.getter() + "())";
			OJIfStatement ifEquals = new OJIfStatement(condition);
			remover.getBody().addToStatements(ifEquals);
			ifEquals.getThenPart().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".setValue(null)");
			remover.addParam("val", map.javaBaseTypePath());
			owner.addToOperations(remover);
		}else{
			super.buildInternalRemover(owner, map);
		}
	}
	@Override
	protected void buildInternalAdder(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		if(isInterfaceValue(owner, map)){
			OJAnnotatedOperation adder = new OJAnnotatedOperation(map.internalAdder());
			adder.setVisibility(map.getProperty().isReadOnly() ? OJVisibilityKind.PRIVATE : OJVisibilityKind.PUBLIC);
			adder.getBody().addToStatements(getReferencePrefix(owner, map) + map.fieldname() + ".setValue(val)");
			adder.addParam("val", map.javaBaseTypePath());
			owner.addToOperations(adder);
		}else{
			super.buildInternalAdder(owner, map);
		}
	}
	@Override
	protected OJAnnotatedField buildField(OJAnnotatedClass owner,NakedStructuralFeatureMap map){
		if(isInterfaceValue(owner, map)){
			OJAnnotatedField field=null;
			if(map.getProperty().isComposite()){
				field = new OJAnnotatedField(map.fieldname(), new OJPathName("org.opaeum.hibernate.domain.CascadingInterfaceValue"));
				owner.addToFields(field);
				field.setInitExp("new CascadingInterfaceValue()");
			}else{
				field = new OJAnnotatedField(map.fieldname(), new OJPathName("org.opaeum.hibernate.domain.InterfaceValue"));
				owner.addToFields(field);
				field.setInitExp("new InterfaceValue()");
			}
			return field;
		}else{
			return super.buildField(owner, map);
		}
	}
	private boolean isInterfaceValue(OJAnnotatedClass c,NakedStructuralFeatureMap map){
		return !(c instanceof OJAnnotatedInterface) && !map.getProperty().isDerived() && map.isOne()
				&& map.getProperty().getType() instanceof Interface
				&& ! StereotypesHelper.hasStereotype(map.getProperty().getType(),StereotypeNames.HELPER);
	}
}
