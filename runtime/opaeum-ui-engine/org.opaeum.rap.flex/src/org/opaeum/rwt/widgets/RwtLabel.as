package org.opaeum.rwt.widgets
{
	import flash.text.TextLineMetrics;
	
	import flashx.textLayout.formats.VerticalAlign;
	
	import org.osmf.layout.HorizontalAlign;
	
	import spark.components.HGroup;
	import spark.components.Image;
	import spark.components.Label;
	
	public class RwtLabel extends HGroup
	{
		private var _label:Label;
		private var _image:Image;
		public function RwtLabel(styles:Array)
		{
			super();
			for(var i:Number;i<styles.length;i++){
				if(styles[i]=="right"){
					super.horizontalAlign=HorizontalAlign.RIGHT;
				}else if(styles[i]=="left"){
					super.horizontalAlign=HorizontalAlign.LEFT;
				}
			}
			super.verticalAlign=VerticalAlign.MIDDLE;
		}
		
		override public function measureText(text:String):TextLineMetrics
		{
			return _label.measureText(text);
		}
		
		
		public function get text():String{
			return _label==null?null: _label.text;
		}
		public function set text(s:String):void{
			removeAllElements();
			addElement(_label=new Label());
			_label.maxDisplayedLines=1;
			_label.text=s;
			invalidateDisplayList();
		}
		public function get image():Image{
			return _image;
		}
		public function set image(image:Image):void{
			removeAllElements();
			addElement(_image=image);
			invalidateDisplayList();
		}
	
	}
}