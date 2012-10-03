package org.opaeum.rwt.adapters
{
	import mx.containers.HBox;
	import mx.controls.MenuBar;
	import mx.core.UIComponent;
	
	import org.opaeum.rap.WidgetAdapter;
	
	public class ToolbarAdapter extends WidgetAdapter
	{
		public function ToolbarAdapter()
		{
			super();
		}
		
		override protected function newInstance(args:*):UIComponent
		{
			return new  HBox();
		}
		
		
	}
}