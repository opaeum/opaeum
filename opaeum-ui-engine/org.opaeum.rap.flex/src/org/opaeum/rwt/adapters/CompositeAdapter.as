package org.opaeum.rwt.adapters
{
	import flash.events.Event;
	
	import mx.containers.Canvas;
	import mx.containers.Panel;
	import mx.core.Container;
	import mx.core.UIComponent;
	
	import org.opaeum.rap.EventUtil;
	import org.opaeum.rap.WidgetAdapter;
	import org.opaeum.rwt.widgets.RwtComposite;
	
	import rwt.remote.Server;
	
	import spark.components.BorderContainer;
	import spark.components.SkinnableContainer;
	import spark.layouts.BasicLayout;
	import spark.skins.spark.BorderContainerSkin;
	
	public class CompositeAdapter extends WidgetAdapter
	{
		public function CompositeAdapter()
		{
			super();
		}
		
		override protected function newInstance(args:*):Object
		{
			var result:RwtComposite=new RwtComposite(args.style);
			return result;
		}
		
		
	}
	
}