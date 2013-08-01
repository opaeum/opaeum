package org.opaeum.eclipse.reverse.db;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;
import org.eclipse.uml2.uml.UMLFactory;
import org.eclipse.uml2.uml.UMLPackage;

@SuppressWarnings("unchecked")
public class UmlGenerator{
	private ClassifierFactory factory;
	private INameGenerator nameGenerator = new DefaultNameGenerator();
	public void generateUml(Collection<JDBCTable> selection,File umlFile) throws Exception{
		// EmfElementCreator.registerPathmaps(URI.createURI(findUml2ResourceJar()));
		Package library = null;
		if(umlFile.exists()){
			String absolutePath = umlFile.getAbsolutePath();
			URI uri = URI.createFileURI(absolutePath);
			ResourceSet rst = new ResourceSetImpl();
			Resource resource = rst.getResource(uri, true);
			library = getRootPackage(resource);
			generateUml(selection, library);
			library.eResource().save(null);
		}
	}
	public void generateUml(Collection<JDBCTable> selection,Package library){
		EcoreUtil.resolveAll(library);
		factory = new ClassifierFactory(library, nameGenerator);
		for(JDBCTable t:selection){
			// Only do annotations in profiles
			Classifier cls = factory.getClassifierFor(t);
			populateAttributes(library, cls, t);
			populateAssociations(library, cls, t);
		}
	}
	public Package getRootPackage(Resource resource){
		EList<EObject> contents = resource.getContents();
		for(EObject eObject:contents){
			if(eObject instanceof Package){
				return (Package) eObject;
			}
		}
		return null;
	}
	private void populateAttributes(Package modelOrProfile,Classifier classifier,JDBCTable binding){
		EList<Column> columns = binding.getColumns();
		for(Column c:columns){
			if(!(c.isPartOfForeignKey() || isIdColumn(binding, c))){
				Property found = findAttribute(c, classifier.getAttributes());
				if(found == null){
					Property attr = createAttribute(classifier, c);
					found = attr;
					found.applyStereotype(factory.getPropertyStereotype());
					found.setValue(factory.getPropertyStereotype(), "persistentName", c.getName());
					if(c.isPartOfPrimaryKey()){
						found.setValue(factory.getPropertyStereotype(), "isPrimaryKey", Boolean.TRUE);
					}
				}
				found.setIsReadOnly(false);
				found.setIsDerived(false);
				found.setLower(c.isNullable() ? 0 : 1);
				found.setUpper(1);
			}
		}
	}
	protected boolean isIdColumn(JDBCTable binding,Column c){
		// TODO return false if type is not a number
		return binding.getPrimaryKey() != null && binding.getPrimaryKey().getMembers().size() == 1 && c.isPartOfPrimaryKey()
				&& ((Column) binding.getPrimaryKey().getMembers().get(0)).getName().equals("id");
	}
	private void populateAssociations(Package modelOrProfile,Classifier classifier,JDBCTable table){
		List<ForeignKey> foreignKeys = table.getForeignKeys();
		for(ForeignKey foreignKey:foreignKeys){
			JDBCTable referencedTable;
			if(foreignKey.getReferencedTable() == null){
				referencedTable = (JDBCTable) foreignKey.getUniqueConstraint().getBaseTable();
			}else{
				referencedTable = (JDBCTable) foreignKey.getReferencedTable();
			}
			Classifier cls = factory.getClassifierFor(table);
			Association ass = null;
			if(cls.getOwner() instanceof Package){
				ass = findOrCreateAssociation(foreignKey, ((Package) cls.getOwner()).getOwnedTypes());
			}else if(cls.getOwner() instanceof org.eclipse.uml2.uml.Class){
				ass = findOrCreateAssociation(foreignKey, ((org.eclipse.uml2.uml.Class) cls.getOwner()).getNestedClassifiers());
			}else{
				throw new IllegalStateException();
			}
			EList<Column> members = foreignKey.getMembers();
			Property toOne = findAssociationEnd(foreignKey, ass.getMemberEnds());
			if(toOne == null){
				toOne = ass.createNavigableOwnedEnd(nameGenerator.calcAssociationEndName(foreignKey), factory.getClassifierFor(referencedTable));
				if(factory.getAssociationEndStereotype() != null){
					toOne.applyStereotype(factory.getAssociationEndStereotype());
					Object value = toOne.getValue(factory.getAssociationEndStereotype(), "persistentName");
					// Cater for both the single String value profile as well as the multiple string value profile which supported compound foreign
					// keys
					if(value instanceof List){
						List<String> s = (List<String>) value;
						for(Column column:members){
							s.add(column.getName());
							if(column.isPartOfPrimaryKey()){
								toOne.setValue(factory.getAssociationEndStereotype(), "isPrimaryKey", Boolean.TRUE);
							}
						}
					}else if(members.size() == 1){
						toOne.setValue(factory.getAssociationEndStereotype(), "persistentName", members.get(0).getName());
					}else{
						throw new IllegalStateException("More than one foreign key but profile only supports one");
					}
				}
			}
			Property end = ass.getNavigableOwnedEnd(nameGenerator.calcAssociationEndName(table), cls, false, UMLPackage.eINSTANCE.getProperty(),
					true);
			end.setLower(0);
			end.setUpper(-1);
		}
	}
	protected Association findOrCreateAssociation(ForeignKey foreignKey,EList<? extends Type> ownedTypes){
		Association ass = null;
		for(Type type:ownedTypes){
			if(type instanceof Association && type.getStereotypeApplication(factory.getAssociationStereotype()) != null){
				if(foreignKey.getName().equals(type.getValue(factory.getAssociationStereotype(), "persistentName"))){
					ass = (Association) type;
					break;
				}
			}
		}
		if(ass == null){
			ass = UMLFactory.eINSTANCE.createAssociation();
			String value = nameGenerator.calcAssociationName(foreignKey);
			ass.setName(value);
			((Collection) ownedTypes).add(ass);
			ass.applyStereotype(factory.getAssociationStereotype());
			ass.setValue(factory.getAssociationStereotype(), "persistentName", foreignKey.getName());
		}
		return ass;
	}
	private Property findAssociationEnd(ForeignKey fk,EList<Property> attributes){
		String expectedAssociationEndName = nameGenerator.calcAssociationEndName(fk);
		Set<String> columnNames = new HashSet<String>();
		for(Column column:(Collection<Column>) fk.getReferencedMembers()){
			columnNames.add(column.getName());
		}
		Property found = null;
		for(Property property:attributes){
			if(factory.getAssociationEndStereotype() != null && property.isStereotypeApplied(factory.getAssociationEndStereotype())){
				Object name = property.getValue(factory.getAssociationEndStereotype(), "persistentName");
				if(name instanceof List){
					List<String> names = (List<String>) name;
					if(names.containsAll(columnNames)){
						found = property;
						break;
					}
				}else{
					if(columnNames.size() == 1 && columnNames.contains(name)){
						found = property;
						break;
					}
				}
			}
			if(property.getName().equalsIgnoreCase(expectedAssociationEndName)){
				found = property;
				break;
			}
		}
		return found;
	}
	private Property findAttribute(Column column,EList<Property> attributes){
		String expectedAttributeName = nameGenerator.calcAttributeName(column);
		Property found = null;
		for(Property property:attributes){
			if(factory.getPropertyStereotype() != null){
				Object name = property.getValue(factory.getPropertyStereotype(), "persistentName");
				if(name.equals(column.getName())){
					found = property;
					break;
				}
			}
			if(property.getName().equalsIgnoreCase(expectedAttributeName)){
				found = property;
				break;
			}
		}
		return found;
	}
	private Property createAttribute(Classifier cls,Column pd){
		Property attr = null;
		if(cls instanceof org.eclipse.uml2.uml.Class){
			attr = ((org.eclipse.uml2.uml.Class) cls).getOwnedAttribute(nameGenerator.calcAttributeName(pd), factory.getDataTypeFor(pd), false,
					UMLPackage.eINSTANCE.getProperty(), true);
		}
		return attr;
	}
}
