package org.opaeum.rwt.widgets
{
	import mx.collections.ArrayList;
	
	import org.opaeum.rwt.SWT;
	import org.opaeum.rwt.events.RwtGridItemEvent;
	import org.opaeum.rwt.items.IRwtGridItemContainer;
	import org.opaeum.rwt.items.RwtGridtItem;
	
	import spark.components.DataGrid;
	import spark.components.GridColumnHeaderGroup;
	import spark.events.GridEvent;
	
	public class RwtTable extends DataGrid implements IRwtControl,IRwtGridItemContainer
	{
		public function RwtTable(style:Array)
		{
			super();
			dataProvider=new ArrayList();
			addEventListener(GridEvent.GRID_CLICK,function(ev:GridEvent):void{
				dispatchEvent(new RwtGridItemEvent(RwtGridItemEvent.SELECTED,RwtGridtItem (ev.item),selectedIndex));
			});
			
			super.setStyle("borderVisible",style.indexOf(SWT.BORDER)>-1);
		}
		
		public function remove(item:RwtGridtItem):void
		{
			ArrayList(dataProvider).removeItem(item);
			
		}
		
		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void
		{
			if(skin!=null && skin['columnHeaderGroup']!=null){
				var s:GridColumnHeaderGroup=skin['columnHeaderGroup'];
			}
			super.updateDisplayList(unscaledWidth, unscaledHeight);
		}
		
		
	}
}