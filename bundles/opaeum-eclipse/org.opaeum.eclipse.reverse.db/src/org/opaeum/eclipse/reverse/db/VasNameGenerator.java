package org.opaeum.eclipse.reverse.db;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.opaeum.name.NameConverter;

public class VasNameGenerator extends DefaultNameGenerator implements INameGenerator{
	protected String calcTypeName(String tableName){
		String rawName = tableName.split("\\_")[1];
		return NameConverter.capitalize(NameConverter.underscoredToCamelCase(rawName));
	}
	@Override
	public String calcPackagename(JDBCTable returnType){
		return returnType.getName().split("\\_")[0];
	}

}
