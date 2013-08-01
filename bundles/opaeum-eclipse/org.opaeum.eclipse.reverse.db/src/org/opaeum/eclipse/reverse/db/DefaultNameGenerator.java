package org.opaeum.eclipse.reverse.db;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.tables.Column;
import org.opaeum.name.NameConverter;

public class DefaultNameGenerator implements INameGenerator{
	@Override
	public String calcAssociationName(ForeignKey foreignKey){
		String typeName = calcTypeName(foreignKey.getBaseTable().getName());
		Column object = (Column) foreignKey.getMembers().get(0);
		return typeName + "_" + calcAttributeName(object);
	}
	protected String calcTypeName(String tableName){
		return NameConverter.capitalize(NameConverter.underscoredToCamelCase(tableName));
	}

	@Override
	public String calcTypeName(JDBCTable returnType){
		return calcTypeName(returnType.getName());
	}
	public String calcPackagename(JDBCTable returnType){
		return returnType.getSchema().getName();
	}
	@Override
	public String calcAttributeName(Column c){
		String raw = c.getName();
		if(raw.endsWith("_id")){
			raw = raw.substring(0, raw.length() - 3);
		}
		String result = NameConverter.underscoredToCamelCase(raw);
		return result;
	}
	@Override
	public String calcAssociationEndName(JDBCTable table){
		return NameConverter.decapitalize(calcTypeName(table)) ;
	}
	@Override
	public String calcAssociationEndName(ForeignKey foreignKey){
		return calcAttributeName((Column) foreignKey.getMembers().get(0));
	}
}
