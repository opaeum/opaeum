package org.opaeum.rwt.widgets
{
	import flash.events.ContextMenuEvent;
	import flash.events.MouseEvent;
	import flash.events.TimerEvent;
	import flash.text.TextLineMetrics;
	import flash.ui.ContextMenu;
	import flash.ui.ContextMenuItem;
	import flash.utils.Timer;
	
	import mx.controls.Menu;
	import mx.controls.Text;
	import mx.core.FlexGlobals;
	import mx.events.MenuEvent;
	import mx.managers.PopUpManager;
	
	import org.opaeum.rwt.items.RwtMenuItem;
	
	import spark.components.Button;
	import spark.components.HGroup;
	import spark.components.Image;
	import spark.components.Label;
	import spark.primitives.BitmapImage;
	
	public class RwtButton extends Button implements IRwtButton
	{
		private var _text:String;
		private var _imageSource:String

		public function RwtButton(styles:Array)
		{
			super();
		}
		
		override public function addEventListener(type:String, listener:Function, useCapture:Boolean=false, priority:int=0, useWeakReference:Boolean=false):void
		{
			super.addEventListener(type, listener, useCapture, priority, useWeakReference);
		}
		
		
		public function setImage(source:String, width:Number,height:Number):void{
			setStyle("icon",source);
			iconDisplay=new BitmapImage();
			iconDisplay.source=source;

			iconDisplay.height=height;
			iconDisplay.width=width;
		}
		public function set text(text:String):void{
			label=text;
		}
		public function get text():String{
			return label;
		}
		public override function measureText(s:String):TextLineMetrics{
			return super.measureText(s);
		}
		
		
	}
}