package org.opaeum.rwt.adapters
{
	import mx.collections.ArrayCollection;
	import mx.collections.ArrayList;
	import mx.controls.Alert;
	import mx.core.UIComponent;
	
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.items.RwtGridtItem;
	import org.opaeum.rwt.widgets.RwtTable;
	import org.opaeum.rwt.widgets.RwtTableColumn;
	import org.opaeum.rwt.widgets.RwtTableItemRenderer;
	import org.opaeum.rwt.widgets.RwtTree;
	
	import spark.components.DataGrid;
	import spark.components.gridClasses.GridColumn;
	
	public class GridItemAdapter extends WidgetAdapter
	{
		public function GridItemAdapter()
		{
			super();
			super.registerProperty("texts");
			super.registerProperty("images");
			super.registerProperty("itemCount","itemCount");
			super.registerProperty("index","index");
		}
		
		override protected function setObjectProperty(target:Object, swtName:String, value:*):void
		{
			if(swtName=="texts"){
				target.texts=value;
			}else if(swtName=="images"){
				target.images=value;
			}
			super.setObjectProperty(target, swtName, value);
		}
		
		override public function destroy(target:Object):void
		{
			RwtGridtItem(target).destroy();
		}
		
		
		override public function create(parent:Object, args:*):Object
		{
			var result:RwtGridtItem;
			if(parent is RwtTable){
				var grid:RwtTable=RwtTable( parent);
				result=new RwtGridtItem(grid);
				if(grid.columns==null || grid.columns.length==0){
					grid.columns=new ArrayList();
					var texts:Array=args.texts;
					for (var i:Number =0; i<texts.length;i++) 
					{
						var gridColumn:RwtTableColumn=new RwtTableColumn();
						grid.columns.addItem(gridColumn);
						
					}
					
				}
				grid.dataProvider.addItem(result);
				grid.invalidateDisplayList();
				grid.validateDisplayList();
			}else if(parent is RwtTree){
				var tree:RwtTree=RwtTree(parent);
				result=new RwtGridtItem(tree);
				tree.dataProvider.addItem(result);
			}else if(parent is RwtGridtItem){
				var p:RwtGridtItem=RwtGridtItem(parent);
				result=new RwtGridtItem(p);
				if(p.children==null){
					p.children=new ArrayCollection();
				}
				p.add(result);
			}
			return result;
		}
		
		
	}
}