package org.opaeum.rwt.widgets
{
	import mx.collections.ArrayCollection;
	import mx.events.FlexEvent;
	
	import spark.components.ComboBox;
	import spark.components.DropDownList;
	import spark.components.HGroup;
	import spark.components.supportClasses.DropDownListBase;
	
	public class RwtCombo extends HGroup implements IRwtControl
	{
		private var combo:DropDownListBase;
		private var _items:Array;
		public function RwtCombo(style:Array)
		{
			super();
			if(style.indexOf("READONLY")>-1){
				addElement( combo=new DropDownList());
			}else{
				addElement( combo=new ComboBox());
			}
			combo.addEventListener(FlexEvent.VALUE_COMMIT,function(ev:FlexEvent):void{
				dispatchEvent(new FlexEvent(ev.type));
				
			});
		}
		public function get selectedIndex():Number{
			return combo.selectedIndex;
		}
		public function set items(items:Array):void{
			this._items=items;
			combo.dataProvider=new ArrayCollection();
			for(var i :Number=0; i < items.length;i++){
				combo.dataProvider.addItem({label:items[i]});
			}
			
		}
		public function get items():Array{
			return _items;
		}
	}
}