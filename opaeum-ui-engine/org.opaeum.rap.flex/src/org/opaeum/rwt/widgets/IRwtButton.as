package org.opaeum.rwt.widgets
{
	import flash.text.TextLineMetrics;
	
	import mx.core.IVisualElement;

	public interface IRwtButton extends IRwtControl,IVisualElement
	{
		function get text():String;
		function set text(s:String):void;
		function setImage(s:String, w:Number,h:Number):void;
		function measureText(s:String):TextLineMetrics;
		
	}
}