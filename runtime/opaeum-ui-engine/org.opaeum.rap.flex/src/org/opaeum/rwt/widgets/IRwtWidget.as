package org.opaeum.rwt.widgets
{
	public interface IRwtWidget
	{
		function get id():String;
		function set id(s:String):void;
		function addEventListener(type:String, listener:Function, useCapture:Boolean=false, priority:int=0, useWeakReference:Boolean=false):void;
		function removeEventListener(type:String, listener:Function, useCapture:Boolean=false):void;

	}
}