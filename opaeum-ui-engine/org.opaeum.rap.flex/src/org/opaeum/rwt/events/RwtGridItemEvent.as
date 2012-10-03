package org.opaeum.rwt.events
{
	import flash.events.Event;
	
	import org.opaeum.rwt.items.RwtGridtItem;
	
	public class RwtGridItemEvent extends Event
	{
		public static const SELECTED:String="Selected";
		public var index:Number;
		public var item:RwtGridtItem;
		public function RwtGridItemEvent(type:String,item:RwtGridtItem,index:Number )
		{
			super(type, false,false);
			this.item=item;
			this.index=index;
		}
	}
}