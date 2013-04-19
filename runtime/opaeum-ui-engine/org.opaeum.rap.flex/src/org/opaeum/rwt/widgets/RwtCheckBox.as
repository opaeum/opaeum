package org.opaeum.rwt.widgets
{
	import flash.text.TextLineMetrics;
	
	import spark.components.CheckBox;
	import spark.components.RadioButton;
	import spark.primitives.BitmapImage;
	
	public class RwtCheckBox extends CheckBox implements IRwtButton
	{
		private var _text:String;
		public function RwtCheckBox(style:Array)
		{
			super();
		}

		public function get text():String
		{
			return _text;
		}

		public function set text(value:String):void
		{
			_text = value;
			label=value;
		}
		public function setImage(s:String, w:Number,h:Number):void{
			setStyle("icon",s);
			iconDisplay=new BitmapImage();
			iconDisplay.source=s;
			iconDisplay.height=height;
			iconDisplay.width=width;
		}
		override public function measureText(s:String):TextLineMetrics{
			return super.measureText(s);
		}

	}
}