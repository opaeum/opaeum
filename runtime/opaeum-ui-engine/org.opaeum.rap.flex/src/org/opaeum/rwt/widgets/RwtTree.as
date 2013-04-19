package org.opaeum.rwt.widgets
{
	import flash.events.Event;
	import flash.events.MouseEvent;
	
	import flexlib.controls.TreeGrid;
	import flexlib.controls.treeGridClasses.TreeGridListData;
	
	import mx.collections.ArrayCollection;
	import mx.events.TreeEvent;
	
	import org.opaeum.rwt.SWT;
	import org.opaeum.rwt.events.RwtGridItemEvent;
	import org.opaeum.rwt.events.RwtTreeEvent;
	import org.opaeum.rwt.items.IRwtGridItemContainer;
	import org.opaeum.rwt.items.RwtGridtItem;
	
	import rwt.remote.Server;
	
	import spark.events.GridEvent;
	
	public class RwtTree extends TreeGrid implements IRwtControl,IRwtGridItemContainer
	{
		public static var EXPANDED:String="Expanded";
		public static var COLLAPSED:String="Collapsed";
		[Embed(source='tree/tree_openNode.png')]
		private var defaultDisclosureClosedClass:Class;
		
		[Embed(source='tree/tree_closeNode.png')]
		private var defaultDisclosureOpenClass:Class;
		public function RwtTree(style:Array)
		{
			super();
			dataProvider=new ArrayCollection();
			addEventListener(MouseEvent.CLICK,function(ev:MouseEvent):void{
				if(selectedItem!=null){
					dispatchEvent(new RwtGridItemEvent(RwtGridItemEvent.SELECTED,RwtGridtItem (selectedItem),selectedIndex));
				}
			});
//			setStyle("disclosureClosedIcon", defaultDisclosureClosedClass);
//			setStyle("disclosureOpenIcon",defaultDisclosureOpenClass);
			if(style.indexOf(SWT.BORDER)==-1){
				super.setStyle("borderStyle", "none");
			}
			super.showRoot=false;
		}
		override public function closeItemAt(rowNum:Number, item:Object=null, closeItem:Boolean=true):void{
			dispatchEvent(new RwtTreeEvent(TreeEvent.ITEM_CLOSE,item,rowNum));
			super.closeItemAt(rowNum,item,closeItem);
		}
		
		override protected function initListData(item:Object, treeListData:TreeGridListData):void
		{
			super.initListData(item, treeListData);
			if(showRoot){
				treeListData.trunkColor=0x000000;
			}else{
				treeListData.trunk="none";
			}
		}
		
		
		override public function openItemAt(rowNum:Number, item:Object=null):Number
		{
			dispatchEvent(new RwtTreeEvent(TreeEvent.ITEM_OPEN,item,rowNum));
			if(!hasEventListener(TreeEvent.ITEM_OPEN)){
				super.openItemAt(rowNum,item);
			}
			return 0;
		}
		public function asyncOpenItemAt(rowNum:Number, item:Object=null):Number
		{
			return super.openItemAt(rowNum,item);
		}
		
		override protected function updateDisplayList(unscaledWidth:Number, unscaledHeight:Number):void
		{
			super.updateDisplayList(unscaledWidth, unscaledHeight);
		}
		

		public function remove(item:RwtGridtItem):void
		{
			 ArrayCollection( dataProvider).removeItemAt(ArrayCollection( dataProvider).getItemIndex(item));
			
		}
		
	}
}