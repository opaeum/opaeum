package org.opaeum.rwt.adapters
{
	import flash.events.Event;
	import flash.ui.Keyboard;
	
	import mx.events.TreeEvent;
	
	import org.opaeum.rap.EventUtil;
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.events.RwtGridItemEvent;
	import org.opaeum.rwt.events.RwtTreeEvent;
	import org.opaeum.rwt.items.RwtGridtItem;
	import org.opaeum.rwt.widgets.IRwtControl;
	import org.opaeum.rwt.widgets.RwtTable;
	import org.opaeum.rwt.widgets.RwtTree;
	
	import rwt.remote.Server;
	
	import spark.events.GridEvent;
	
	
	public class GridAdapter extends WidgetAdapter
	{
		public function GridAdapter()
		{
			super();
			super.registerEventListenerType("selection", RwtGridItemEvent.SELECTED,function (ev:RwtGridItemEvent):void{
				var req:Server=Server.getInstance();
				var id:String=IRwtControl( ev.target).id
				req.addParameter(id +".selection", ev.item.id);
				req.addParameter(id +".focusItem", ev.item.id);
				var eventName:String="org.eclipse.swt.events.widgetSelected";
				req.addEvent( eventName, id );
				EventUtil.addWidgetSelectedModifier(ev);
				req.addParameter( eventName + ".item", ev.item.id );
//				if( detail != null ) {
//					req.addParameter( eventName + ".detail", detail );
//				}
				req.addParameter( eventName + ".index", ev.index);
				EventUtil.addWidgetSelectedModifier(ev);
				req.addParameter( eventName + ".item", ev.item.id);
				req.send();
			});
		}
		
		override protected function newInstance(args:*):Object
		{
			var result1:RwtTree=new RwtTree(args.style);
			result1.addEventListener(TreeEvent.ITEM_CLOSE,function(ev:TreeEvent):void{
				sendItemEvent(RwtGridtItem( ev.item),"org.eclipse.swt.events.treeCollapsed");
			});
			result1.addEventListener(TreeEvent.ITEM_OPEN,function(ev:RwtTreeEvent):void{
				var listener:Function=function (e:Event):void{
					Server.getInstance().removeEventListener("received",listener);
					result1.asyncOpenItemAt(ev.index, ev.item);			
				};
				Server.getInstance().addEventListener("received",listener);
				sendItemEvent(RwtGridtItem( ev.item),"org.eclipse.swt.events.treeExpanded");
			});
			result1.showRoot=args.appearance=="tree";
			return result1;
		}
		private function sendItemEvent( item:RwtGridtItem, type:String ):void { // TODO [tb] : item events should be send by item
			var req:Server = Server.getInstance();
			req.addEvent( type, item.id );
			req.send();
		}
		

		override protected function setObjectProperty(target:Object, swtName:String, value:*):void
		{
			super.setObjectProperty(target, swtName, value);
		}
		
		
	}
}