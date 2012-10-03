package org.opaeum.rwt.adapters
{
	import flash.events.Event;
	import flash.events.TextEvent;
	import flash.text.TextField;
	
	import mx.controls.Text;
	import mx.core.UIComponent;
	
	import org.opaeum.rap.WidgetAdapter;
	
	import rwt.protocol.ObjectRegistry;
	import rwt.remote.Server;
	
	import spark.components.TextArea;
	import spark.components.TextInput;
	import spark.events.TextOperationEvent;
	
	public class TextAdapter extends WidgetAdapter
	{
		public function TextAdapter()
		{
			super();
			super.registerEventListenerType("modify",TextOperationEvent.CHANGE, sendModifyEvent);
			registerProperty("text","text");

		}
		
		override protected function newInstance(args:*):Object
		{
			var style:Array=args.style;
			var result:UIComponent;
			for(var i:Number=0; i < style.length;i++){
				switch(style[i]){
					case "SINGLE":
						result=new TextInput();
					case "MULTI":
						result=new TextArea();
				}
			}
			result.addEventListener(TextOperationEvent.CHANGE,handleModify);
			
			return result;
		}
		function handleModify(ev:TextOperationEvent):void {
			var id:String = ObjectRegistry.getId( ev.target);
			var req:Server = Server.getInstance();
			req.addParameter( id + ".text", ev.target.text);
		}
		function sendModifyEvent(ev:TextOperationEvent):void{
			var id:String = ObjectRegistry.getId( ev.target);
			var req:Server = Server.getInstance();
			req.addParameter( id + ".text", ev.target.text);
			req.addEvent( "org.eclipse.swt.events.modifyText", id );
		}
	}
	
}