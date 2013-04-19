package org.opaeum.reverse.popup.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCCatalog;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCSchema;
import org.eclipse.datatools.connectivity.sqm.core.rte.jdbc.JDBCTable;
import org.eclipse.jface.viewers.IStructuredSelection;

public class SelectedTableCollector{
	public static Collection<JDBCTable> collectEffectivelySelectedTables(IStructuredSelection selection2){
		Iterator<?> iterator = selection2.iterator();
		Collection<JDBCTable> result = new HashSet<JDBCTable>();
		while(iterator.hasNext()){
			addTablesOnly(result, (Object) iterator.next());
		}
		return result;
	}
	private static void addTablesOnly(Collection<JDBCTable> result,Object object){
		if(object instanceof JDBCTable){
			result.add((JDBCTable) object);
		}else if(object instanceof JDBCSchema){
			for(Object object2:((JDBCSchema) object).getTables()){
				addTablesOnly(result, object2);
			}
		}else if(object instanceof JDBCCatalog){
			for(Object jdbcSchema:((JDBCCatalog) object).getSchemas()){
				addTablesOnly(result, jdbcSchema);
			}
		}
	}
}
