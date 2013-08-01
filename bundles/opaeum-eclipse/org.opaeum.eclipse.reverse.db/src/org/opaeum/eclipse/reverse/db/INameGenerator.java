package org.opaeum.eclipse.reverse.db;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.datatools.modelbase.sql.constraints.ForeignKey;
import org.eclipse.datatools.modelbase.sql.tables.Column;

public interface INameGenerator{
	public abstract String calcAssociationEndName(JDBCTable table);
	public abstract String calcAssociationEndName(ForeignKey foreignKey);
	public abstract String calcAssociationName(ForeignKey foreignKey);
	public abstract String calcTypeName(JDBCTable returnType);
	public abstract String calcAttributeName(Column c);
	public abstract String calcPackagename(JDBCTable returnType);
}
