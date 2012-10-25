package org.opaeum.rwt.adapters
{
	
	import mx.collections.ArrayList;
	import mx.containers.Grid;
	import mx.controls.Alert;
	import mx.controls.dataGridClasses.DataGridColumn;
	
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.widgets.RwtTable;
	import org.opaeum.rwt.widgets.RwtTableColumn;
	import org.opaeum.rwt.widgets.RwtTableItemRenderer;
	import org.opaeum.rwt.widgets.RwtTree;
	import org.opaeum.rwt.widgets.RwtTreeColumn;
	
	
	
	public class GridColumnAdapter extends WidgetAdapter
	{
		public function GridColumnAdapter()
		{
			super();
			registerProperty("text","headerText");
			registerProperty("width","width");
		}
		
		override public function create(parent:Object, args:*):Object
		{
			if(parent is RwtTable){
				var table:RwtTable=RwtTable(parent);
				if(table.columns==null){
					table.columns=new ArrayList();
				}
				var result:RwtTableColumn=new RwtTableColumn(args["text"]);
				table.columns.addItem(result);
				return result;
			}else if(parent is RwtTree){
				var tree:RwtTree=RwtTree(parent);
				var result2:RwtTreeColumn=new RwtTreeColumn(args["text"],tree);
				return result2;
			}else{
				trace("asdf");
			}
			return null;
		}
		
	}
}