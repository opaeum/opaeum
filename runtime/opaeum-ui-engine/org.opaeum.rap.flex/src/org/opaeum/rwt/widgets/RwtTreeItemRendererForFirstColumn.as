
package org.opaeum.rwt.widgets
{
	import flash.display.DisplayObject;
	
	import flashx.textLayout.formats.VerticalAlign;
	
	import flexlib.containers.utilityClasses.FlowLayout;
	import flexlib.controls.treeGridClasses.TreeGridItemRenderer;
	import flexlib.controls.treeGridClasses.TreeGridListData;
	
	import mx.controls.Image;
	import mx.controls.Label;
	import mx.controls.listClasses.BaseListData;
	import mx.controls.treeClasses.TreeListData;
	import mx.core.IFactory;
	import mx.core.UIComponent;
	import mx.core.mx_internal;
	
	import spark.components.HGroup;
	
	
	public class RwtTreeItemRendererForFirstColumn extends TreeGridItemRenderer
	{
		
//		private var hg:HGroup;
//		private var img:Image = new Image();
//		private var lbl:Label = new Label();
		
		override protected function createChildren():void
		{
			super.createChildren();
//			hg = new HGroup();
//			hg.paddingTop=2;
//			hg.paddingBottom=2;
//			hg.percentHeight=100;
//			hg.percentWidth=100;
//			addChild(hg);
//			hg.verticalAlign=VerticalAlign.BOTTOM;
		}
		public function RwtTreeItemRendererForFirstColumn()
		{
			trace("asdf");
		}
		
		override public function set listData(value:BaseListData):void
		{
			super.listData=value;
			if(listData!=null){
				TreeGridListData( listData).icon;
			}
		}
		
		override public  function addChild(child:DisplayObject):DisplayObject
		{
			if(child==icon){
 				var image:Array= data.images[listData.columnIndex];
				Image(icon).source=image[0];
				icon.width=image[1];
				icon.height=image[2];

			}
			return super.addChild(child);
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