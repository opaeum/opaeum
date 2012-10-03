
package org.opaeum.rwt.widgets
{
	import flexlib.containers.utilityClasses.FlowLayout;
	
	import mx.core.IFactory;
	import mx.core.UIComponent;
	
	import spark.components.HGroup;
	import spark.components.IItemRenderer;
	import spark.components.Image;
	import spark.components.Label;
	import spark.components.gridClasses.GridItemRenderer;
	import spark.components.supportClasses.TextBase;
	import spark.layouts.VerticalAlign;
	import spark.skins.spark.DefaultGridItemRenderer;
	
	public class RwtTableItemRenderer extends GridItemRenderer
	{
		private var hg:HGroup;
		private var img:Image = new Image();
		private var lbl:Label = new Label();
		
		override protected function createChildren():void
		{
			super.createChildren();
			hg = new HGroup();
			hg.paddingTop=2;
			hg.paddingBottom=2;
			hg.percentHeight=100;
			hg.percentWidth=100;
			addElement(hg);
			hg.verticalAlign=VerticalAlign.BOTTOM;
		}
		public function RwtTableItemRenderer()
		{
		}
		
		
		override public function set data(value:Object):void
		{
			if(value != null){
				if(value.images!=undefined && value.images[columnIndex]!=null){
					var image:Array=value.images[columnIndex];
					img.source=image[0];
					img.width=image[1];
					img.height=image[2];
					hg.addElement(img);
				}		
				if(value.texts!=undefined && value.texts[columnIndex]!=null){
					lbl.text=value.texts[columnIndex];
					hg.addElement(lbl);
				}		
			}		
		}
		
		
		
	}
}