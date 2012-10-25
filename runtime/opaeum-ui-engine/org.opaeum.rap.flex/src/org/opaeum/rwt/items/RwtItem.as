package org.opaeum.rwt.items
{
	import org.opaeum.rwt.widgets.IRwtWidget;
	
	public class RwtItem implements IRwtWidget
	{
		private var _id:String;
		public function RwtItem()
		{
		}
		
		public function get id():String
		{
			return _id;
		}
		
		public function set id(s:String):void
		{
			_id=s;
		}
		
		public function addEventListener(type:String, listener:Function, useCapture:Boolean=false, priority:int=0, useWeakReference:Boolean=false):void
		{
		}
		
		public function removeEventListener(type:String, listener:Function, useCapture:Boolean=false):void
		{
		}
	}
}