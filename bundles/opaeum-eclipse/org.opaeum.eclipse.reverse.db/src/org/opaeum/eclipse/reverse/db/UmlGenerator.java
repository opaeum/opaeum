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

public class UmlGenerator{
	private ClassifierFactory factory;
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
		factory = new ClassifierFactory(library);
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
					attr.setIsReadOnly(false);
					attr.setIsDerived(false);
					attr.setLower(c.isNullable() ? 0 : 1);
					attr.setUpper(1);
					attr.applyStereotype(factory.getPropertyStereotype());
					attr.setValue(factory.getPropertyStereotype(), "persistentName", c.getName());
					if(c.isPartOfPrimaryKey()){
						attr.setValue(factory.getPropertyStereotype(), "isPrimaryKey", Boolean.TRUE);
					}
				}
			}
		}
	}
	protected boolean isIdColumn(JDBCTable binding,Column c){
		//TODO return false if type is not a number
		return binding.getPrimaryKey() != null && binding.getPrimaryKey().getMembers().size() == 1 && c.isPartOfPrimaryKey()
				&& ((Column) binding.getPrimaryKey().getMembers().get(0)).getName().equals("id");
	}
	private void populateAssociations(Package modelOrProfile,Classifier classifier,JDBCTable table){
		List<ForeignKey> foreignKeys = table.getForeignKeys();
		for(ForeignKey foreignKey:foreignKeys){
			List<Column> referencedMembers = foreignKey.getReferencedMembers();
			JDBCTable referencedTable;
			if(foreignKey.getReferencedTable() == null){
				referencedMembers = foreignKey.getUniqueConstraint().getMembers();
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
			Property toOne = findAssociationEnd(members, ass.getMemberEnds());
			if(toOne == null){
				toOne = ass.createNavigableOwnedEnd(referenceUmlName(members), factory.getClassifierFor(referencedTable));
				if(factory.getPropertyStereotype() != null){
					toOne.applyStereotype(factory.getAssociationEndStereotype());
					List<String> s = (List<String>) toOne.getValue(factory.getAssociationEndStereotype(), "persistentName");
					for(Column column:members){
						s.add(column.getName());
						if(column.isPartOfPrimaryKey()){
							toOne.setValue(factory.getAssociationEndStereotype(), "isPrimaryKey", Boolean.TRUE);
						}
					}
				}
			}
			ass.getNavigableOwnedEnd(table.getName(), cls, false, UMLPackage.eINSTANCE.getProperty(), true).setUpper(-1);
		}
	}
	protected String referenceUmlName(EList<Column> members){
		return ((Column) members.get(0)).getName();
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
			// TODO concatenate name from members
			Column object = (Column) foreignKey.getMembers().get(0);
			ass.setName(foreignKey.getBaseTable().getName() + object.getName());
			((Collection) ownedTypes).add(ass);
			ass.applyStereotype(factory.getAssociationStereotype());
			ass.setValue(factory.getAssociationStereotype(), "persistentName", foreignKey.getName());
		}
		return ass;
	}
	private Property findAssociationEnd(List<Column> pd,EList<Property> attributes){
		Set<String> columnNames = new HashSet<String>();
		for(Column column:pd){
			columnNames.add(column.getName());
		}
		Property found = null;
		for(Property property:attributes){
			if(factory.getAssociationEndStereotype() != null && property.isStereotypeApplied(factory.getAssociationEndStereotype())){
				Object name = property.getValue(factory.getAssociationEndStereotype(), "persistentName");
				List<String> names = (List<String>) name;
				if(names.containsAll(columnNames)){
					found = property;
					break;
				}
			}
			if(pd.size() == 1 && property.getName().equalsIgnoreCase(pd.get(0).getName())){
				found = property;
				break;
			}
		}
		return found;
	}
	private Property findAttribute(Column pd,EList<Property> attributes){
		Property found = null;
		for(Property property:attributes){
			if(factory.getPropertyStereotype() != null){
				Object name = property.getValue(factory.getPropertyStereotype(), "persistentName");
				if(name.equals(pd.getName())){
					found = property;
					break;
				}
			}
			if(property.getName().equalsIgnoreCase(pd.getName())){
				found = property;
				break;
			}
		}
		return found;
	}
	private Property createAttribute(Classifier cls,Column pd){
		Property attr = null;
		if(cls instanceof org.eclipse.uml2.uml.Class){
			attr = ((org.eclipse.uml2.uml.Class) cls).getOwnedAttribute(pd.getName(), factory.getDataTypeFor(pd), false, UMLPackage.eINSTANCE.getProperty(), true);
		}
		return attr;
	}
}
