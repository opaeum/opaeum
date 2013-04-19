package qx.core
{
	import flash.events.Event;
	
	import org.opaeum.rap.IObserving;
	
	import rwt.util.Object;
	
	public class Target {
		private var disposed:Boolean;
		private var __listeners:Array;
		private static var __availableHashCode:int;
		public function Target() {
		}
		public function addEventListener(type:String, func:Function, obj:IObserving):void
		{
			if (this.disposed) {
				return;
			}
			
			// If this is the first event of given type, we need to create a subobject
			// that contains all the actions that will be assigned to this type
			if (this.__listeners === null) {
				this.__listeners = new Array();
			}
			
			if (this.__listeners[type] === undefined) {
				this.__listeners[type] = new Array();
			}
			
			// Create a special key string to allow identification of each bound action
			var key:String = "event" + toHashCodeF(func) + (obj ? "$" + toHashCode(obj) : "");
			
			// Finally set up the listeners object
			this.__listeners[type][key] =
				{
					handler : func,
					object  : obj
				};
		}
		
		
		public function removeEventListener(type:String, func:Function, obj:IObserving):void
		{
			if (this.disposed) {
				return;
			}
			
			var listeners:Array = this.__listeners;
			
			if (!listeners || listeners[type] === undefined) {
				return;
			}
			
			// Create a special key string to allow identification of each bound action
			var key:String = "event" + toHashCodeF(func) + (obj ? "$" + toHashCode(obj) : "");
			
			// Delete object entry for this action
			delete this.__listeners[type][key];
		}
		
		
		
		
		
		public function hasEventListeners(type:String) :Boolean{
			
			return this.__listeners && this.__listeners[type] !== undefined && !rwt.util.Object.isEmpty(this.__listeners[type]);
		}
		
		
		//   public function createDispatchEvent(type)
		//    {
		//      if (this.hasEventListeners(type)) {
		//        this.dispatchEvent(new qx.event.type.Event(type), true);
		//      }
		//    }
		//
		//
		//   public function createDispatchDataEvent(type, data)
		//    {
		//      if (this.hasEventListeners(type)) {
		//        this.dispatchEvent(new qx.event.type.DataEvent(type, data), true);
		//      }
		//    }
		//
		//
		//   public function createDispatchChangeEvent(type, value, old)
		//    {
		//      if (this.hasEventListeners(type)) {
		//        this.dispatchEvent(new qx.event.type.ChangeEvent(type, value, old), true);
		//      }
		//    }
		//
		
		
		
		
		public function dispatchEvent( evt:Event, dispose:Boolean ) :void{
			// Ignore event if eventTarget is disposed
			if( this.disposed ) {
				return;
			}
			this._dispatchEvent( evt, dispose );
		}
		
		
		public function dispatchSimpleEvent( type:String, data:String, bubbles :Boolean) :Boolean{
			var listeners:Array = this.__listeners;
			var propagate :Boolean= bubbles === true;
			var result :Boolean= true;
			if( listeners ) {
				var typeListeners:Array = listeners[ type ];
				if( typeListeners ) {
					var func:Function;
					var obj:Object;
					for( var hashCode:String  in typeListeners ) {
						// Shortcuts for handler and object
						func = typeListeners[ hashCode ].handler;
						obj = typeListeners[ hashCode ].object || this;
						result = func.call( obj, data ) && result !== false;
						if( result === false ) {
							propagate = false;
						}
					}
				}
			}
			return result !== false;
		}
		
		public function _dispatchEvent(evt:Event, dispose:Boolean):void
		{
			var listeners:Array = this.__listeners;
			
			if (listeners)
			{
				// Setup current target
				
				//        evt.currentTarget=this;
				
				// Shortcut for listener data
				var typeListeners:Array = listeners[evt.type];
				
				if (typeListeners)
				{
					var func:Function;
					var obj:Object;
					
					// Handle all events for the specified type
					for (var vHashCode:String in typeListeners)
					{
						// Shortcuts for handler and object
						func = typeListeners[vHashCode].handler;
						obj = typeListeners[vHashCode].object || this;
						
						// Call object function
						func.call(obj, evt);
					}
				}
			}
		}
		
		private function toHashCode (obj:IObserving):Number
		{
			if (obj.getHashCode() == 0) {
				obj.setHashCode(__availableHashCode++);
			}
			return obj.getHashCode();
			
			
		}
		private function toHashCodeF (obj:Function):Number
		{
			return obj.prototype.toString();
		}
		
		
		
		public function destruct():void
		{
		}
	}
}
