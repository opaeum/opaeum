package org.opaeum.rwt.events
{
	import flash.events.Event;
	
	import mx.controls.listClasses.IListItemRenderer;
	import mx.events.TreeEvent;
	
	public class RwtTreeEvent extends TreeEvent
	{
		public var index:Number;
		public function RwtTreeEvent(type:String, item:Object, index:Number)
		{
			super(type, false, false, item, null, null);
			this.index=index;
		}
	}
}