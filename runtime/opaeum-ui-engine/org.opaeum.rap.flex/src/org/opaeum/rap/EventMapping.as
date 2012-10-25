package org.opaeum.rap
{
	public class EventMapping
	{
		public var asEventType:String;
		public var handler:Function;
		public function EventMapping(asEventType:String, handler:Function)
		{
			this.asEventType=asEventType;
			this.handler=handler;
		}
	}
}