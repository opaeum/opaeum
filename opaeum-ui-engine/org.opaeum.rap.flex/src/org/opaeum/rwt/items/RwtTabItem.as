package org.opaeum.rwt.items
{
	import flash.display.DisplayObject;
	
	import mx.containers.HBox;
	import mx.controls.Button;
	import mx.core.Container;
	import mx.core.ContainerCreationPolicy;
	import mx.core.IContainer;
	import mx.core.INavigatorContent;
	import mx.core.IVisualElement;
	import mx.core.UIComponent;
	import mx.events.FlexEvent;
	import mx.events.IndexChangedEvent;
	
	import org.opaeum.rwt.widgets.IRwtControl;
	import org.opaeum.rwt.widgets.RwtTabFolder;
	import org.opaeum.rwt.widgets.RwtTable;
	
	import spark.components.Button;
	import spark.components.DataGrid;
	import spark.components.Group;
	import spark.components.HGroup;
	import spark.components.NavigatorContent;
	import spark.components.SkinnableContainer;
	import spark.components.supportClasses.SkinnableContainerBase;
	import spark.layouts.VerticalLayout;

	public class RwtTabItem extends RwtItem 
	{
		public var tabFolder:RwtTabFolder;
		public var text:String;		
		private  var _control:IVisualElement;
		public function RwtTabItem(tabFolder:RwtTabFolder, style:Array)
		{
			super();
			this.tabFolder=tabFolder;
			this.tabFolder.items.push(this);
		}
		public function get control():IVisualElement{
			return _control;
		}
		public function set control(control:IVisualElement):void{
			this._control=control;
			tabFolder.addTabItem(this);
		}
		
	}
}