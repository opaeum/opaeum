package org.opaeum.rwt.adapters
{
	import mx.events.FlexEvent;
	
	import org.opaeum.rap.EventUtil;
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.widgets.RwtCombo;
	
	import rwt.remote.Server;
	
	public class ComboAdapter extends WidgetAdapter
	{
		public function ComboAdapter()
		{
			super();
			registerProperty("items");
			super.registerEventListenerType("selection",FlexEvent.VALUE_COMMIT,function(ev:FlexEvent):void{
				var req:Server = Server.getInstance();
				var combo:RwtCombo=RwtCombo(ev.target);
				req.addParameter( combo.id + ".selectedItem", combo.selectedIndex );
				req.addEvent( "org.eclipse.swt.events.widgetSelected", combo.id );
				EventUtil.addWidgetSelectedModifier(ev);
				req.send();
			});
		}
		
		override protected function newInstance(args:*):Object
		{
			var result:RwtCombo=new RwtCombo(args.style);
			return result;
		}
		
		override protected function setObjectProperty(target:Object, swtName:String, value:*):void
		{
			var combo:RwtCombo=RwtCombo(target);
			switch(swtName){
				case "items":
					combo.items=value;
					break;
			}
		}
		
		
	}
}