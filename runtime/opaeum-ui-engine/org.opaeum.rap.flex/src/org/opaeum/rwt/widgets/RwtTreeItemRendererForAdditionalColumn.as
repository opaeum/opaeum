
package org.opaeum.rwt.widgets
{
	import flash.display.DisplayObject;
	
	import flashx.textLayout.formats.VerticalAlign;
	
	import flexlib.containers.utilityClasses.FlowLayout;
	import flexlib.controls.treeGridClasses.TreeGridItemRenderer;
	import flexlib.controls.treeGridClasses.TreeGridListData;
	
	import mx.controls.Image;
	import mx.controls.Label;
	import mx.controls.dataGridClasses.DataGridItemRenderer;
	import mx.controls.listClasses.BaseListData;
	import mx.controls.treeClasses.TreeListData;
	import mx.core.IFactory;
	import mx.core.UIComponent;
	import mx.core.mx_internal;
	
	import spark.components.HGroup;
	
	
	public class RwtTreeItemRendererForAdditionalColumn extends DataGridItemRenderer
	{
		
//		private var hg:HGroup;
//		private var img:Image = new Image();
//		private var lbl:Label = new Label();
		
		protected function createChildren():void
		{
//			super.createChildren();
//			hg = new HGroup();
//			hg.paddingTop=2;
//			hg.paddingBottom=2;
//			hg.percentHeight=100;
//			hg.percentWidth=100;
//			addChild(hg);
//			hg.verticalAlign=VerticalAlign.BOTTOM;
		}
		public function RwtTreeItemRendererForAdditionalColumn()
		{
			trace("asdf");
		}
		
		override public function set listData(value:BaseListData):void
		{
			super.listData=value;
		}
		
	
		
		
		override public function set data(value:Object):void
		{
			if(value != null){
				if(value.images!=undefined && value.images[listData.columnIndex]!=null){
					TreeGridListData(listData).icon=Image;
				}		
				if(value.texts!=undefined && value.texts[listData.columnIndex]!=null){
					super.listData.label=value.texts[listData.columnIndex];
				}else{
					trace("asf");
				}
			}		
			super.data=value;
		}
		
		
		
	}
}