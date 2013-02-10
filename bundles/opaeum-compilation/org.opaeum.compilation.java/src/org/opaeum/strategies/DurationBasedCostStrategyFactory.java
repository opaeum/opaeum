package org.opaeum.strategies;

import java.util.Date;

import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import nl.klasse.octopus.codegen.umlToJava.maps.PropertyMap;

import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Property;
import org.opaeum.eclipse.EmfElementFinder;
import org.opaeum.eclipse.PersistentNameUtil;
import org.opaeum.java.metamodel.OJBlock;
import org.opaeum.java.metamodel.OJIfStatement;
import org.opaeum.java.metamodel.OJPathName;
import org.opaeum.java.metamodel.annotation.OJAnnotatedClass;
import org.opaeum.java.metamodel.annotation.OJAnnotatedField;
import org.opaeum.java.metamodel.annotation.OJAnnotatedOperation;
import org.opaeum.java.metamodel.annotation.OJAnnotationAttributeValue;
import org.opaeum.java.metamodel.annotation.OJAnnotationValue;
import org.opaeum.java.metamodel.annotation.OJEnumValue;
import org.opaeum.javageneration.basicjava.AttributeInJava;
import org.opaeum.javageneration.basicjava.AttributeStrategy;
import org.opaeum.javageneration.basicjava.FormatterStrategy;
import org.opaeum.javageneration.composition.ConfigurableDataStrategy;
import org.opaeum.javageneration.persistence.JpaStrategy;
import org.opaeum.javageneration.persistence.JpaUtil;
import org.opaeum.metamodel.name.NameWrapper;
import org.opaeum.metamodel.workspace.AbstractStrategyFactory;
import org.opaeum.runtime.domain.BusinessTimeUnit;
import org.opaeum.strategies.DateStrategyFactory.DateTestModelValueStrategy;

public class DurationBasedCostStrategyFactory extends AbstractStrategyFactory{
	public static class MyAttributeStrategy implements AttributeStrategy{
		@Override
		public void applyTo(OJAnnotatedClass owner,AttributeInJava a,PropertyMap map){
			OJPathName listOfEntries = new OJPathName("java.util.List");
			listOfEntries.addToElementTypes(new OJPathName("org.opaeum.runtime.costing.DurationBasedCostEntry"));
			OJAnnotatedField field = new OJAnnotatedField(a.field.getName() + "Entries", listOfEntries);
			owner.addToFields(field);
			field.setInitExp("new ArrayList<DurationBasedCostEntry>()");
			OJAnnotatedOperation getter = new OJAnnotatedOperation(a.getter.getName() + "Entry", listOfEntries);
			getter.initializeResultVariable(field.getName());
			owner.addToOperations(getter);
			OJAnnotatedOperation fromEventOccurred = new OJAnnotatedOperation(map.fieldname() + "FromEventOccurred");
			fromEventOccurred.addParam("firstEvent", new OJPathName("Boolean"));
			fromEventOccurred.getBody().addToStatements(new OJIfStatement(map.getter() + "()==null", map.setter() + "(new DurationBasedCost())"));
			fromEventOccurred.getBody().addToStatements(map.getter() + "().fromEventOccurred(firstEvent)");
			owner.addToOperations(fromEventOccurred);
			OJAnnotatedOperation toEventOccurred = new OJAnnotatedOperation(map.fieldname() + "ToEventOccurred");
			OJPathName setOfResources = new OJPathName("java.util.Set");
			setOfResources.addToElementTypes(new OJPathName("org.opaeum.runtime.bpm.costing.ITimedResource"));
			toEventOccurred.addParam("resources", setOfResources);
			toEventOccurred.addParam("firstEvent", new OJPathName("Boolean"));
			toEventOccurred.getBody().addToStatements(new OJIfStatement(map.getter() + "()==null", map.setter() + "(new DurationBasedCost())"));
			toEventOccurred.getBody().addToStatements(
					a.getter.getName() + "Entry().addAll(" + map.getter() + "().toEventOccurred(resources,firstEvent))");
			owner.addToOperations(toEventOccurred);
			OJAnnotatedOperation addCostEntry = new OJAnnotatedOperation(map.adder() + "Entry");
			addCostEntry.addParam("fromDate", new OJPathName(Date.class.getName()));
			addCostEntry.addParam("toDate", new OJPathName(Date.class.getName()));
			addCostEntry.addParam("resource", new OJPathName("org.opaeum.runtime.bpm.costing.ITimedResource"));
			addCostEntry.getBody().addToStatements(new OJIfStatement(map.getter() + "()==null", map.setter() + "(new DurationBasedCost())"));
			addCostEntry.getBody().addToStatements(
					a.getter.getName() + "Entry().add(" + map.getter() + "().addCostEntry(fromDate,toDate, resource)");
			owner.addToOperations(addCostEntry);
		}
	}
	public static class MyFormattingStrategy implements FormatterStrategy{
		@Override
		public void implementParse(OJAnnotatedOperation parse){
			parse.initializeResultVariable("value==null||value.length()==0?null: new DurationBasedCost(value)");
		}
		@Override
		public void implementFormat(OJAnnotatedOperation format){
			format.initializeResultVariable("value==null?\"\":value.toString()");
		}
	}
	public static class MyJpaStrategy implements JpaStrategy{
		@Override
		public void annotate(OJAnnotatedClass c,OJAnnotatedField f,Property p){
			OJAnnotationValue embedded = new OJAnnotationValue(new OJPathName(Embedded.class.getName()));
			f.putAnnotation(embedded);
			OJAnnotationValue overrides = new OJAnnotationValue(new OJPathName(AttributeOverrides.class.getName()));
			f.putAnnotation(overrides);
			NameWrapper persistentName = PersistentNameUtil.getPersistentName(p);
			overrides.addAnnotationValue(JpaUtil.createOverride("fromDate", persistentName));
			overrides.addAnnotationValue(JpaUtil.createOverride("toDate", persistentName));
			overrides.addAnnotationValue(JpaUtil.createOverride("costToCompany", persistentName));
			overrides.addAnnotationValue(JpaUtil.createOverride("measurementCount", persistentName));
			OJAnnotatedField entriesField = (OJAnnotatedField) c.findField(f.getName() + "Entries");
			OJAnnotationValue oneToMany = new OJAnnotationValue(new OJPathName(OneToMany.class.getName()));
//			oneToMany.putAttribute("orphanRemoval", Boolean.TRUE);
			oneToMany.putAttribute("cascade", new OJEnumValue(new OJPathName(CascadeType.class.getName()), "ALL"));
			entriesField.addAnnotationIfNew(oneToMany);
			OJAnnotationValue index = new OJAnnotationValue(new OJPathName("org.hibernate.annotations.IndexColumn"));
			index.putAttribute(new OJAnnotationAttributeValue("name", "index"));
			entriesField.addAnnotationIfNew(index);
			
			OJAnnotationValue joinTable = new OJAnnotationValue(new OJPathName(JoinTable.class.getName()));
			Classifier owner = EmfElementFinder.getNearestClassifier(p);
			NameWrapper ownerName = PersistentNameUtil.getPersistentName(owner);
			joinTable.putAttribute("name", ownerName + "_" + persistentName.getWithoutId() + "_entry");
			String nearestSchema = JpaUtil.getNearestSchema(owner);
			if(nearestSchema != null){
				joinTable.putAttribute("schema", nearestSchema);
			}
			entriesField.addAnnotationIfNew(joinTable);
			OJAnnotationValue joinColumns = new OJAnnotationValue(new OJPathName(JoinColumn.class.getName()));
			joinTable.putAttribute("joinColumns", joinColumns);
			joinColumns.putAttribute("name", persistentName.getWithoutId() + "_entry_id");
			OJAnnotationValue inverseJoinColumns = new OJAnnotationValue(new OJPathName(JoinColumn.class.getName()));
			joinTable.putAttribute("inverseJoinColumns ", inverseJoinColumns);
			inverseJoinColumns.putAttribute("name", ownerName + "_id");
		}
	}
	public static class MyConfigurableDataStrategy implements ConfigurableDataStrategy{
		@Override
		public String getDefaultStringValue(OJAnnotatedClass owner,OJBlock block,Property p){
			return getDefaultStringValue();
		}
		@Override
		public String parseConfiguredValue(OJAnnotatedClass owner,OJBlock block,Property p,String configuredValue){
			return "new DurationBasedCostEntry(" + configuredValue + ")";
		}
		@Override
		public String getDefaultStringValue(){
			return "10 business days";
		}
	}
	public static class MyTestValueStrategy extends DateTestModelValueStrategy{
		@Override
		public String getDefaultStringValue(int seed){
			return seed + " " + BusinessTimeUnit.values()[seed % BusinessTimeUnit.values().length];
		}
	}
	@SuppressWarnings("unchecked")
	public DurationBasedCostStrategyFactory(){
		super(MyJpaStrategy.class, MyConfigurableDataStrategy.class, MyTestValueStrategy.class, MyFormattingStrategy.class, MyAttributeStrategy.class);
	}
	@Override
	public boolean appliesTo(DataType st){
		return st.getName().equals("DurationBasedCost");
	}
	@Override
	public String getRuntimeStrategyFactory(){
		return "org.opaeum.runtime.strategy.DurationBasedCostStrategyFactory";
	}
}
