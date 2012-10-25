
package org.opaeum.rwt.items
{
	import org.opaeum.rwt.items.RwtGridtItem;
	import mx.collections.ArrayCollection;
	
	import org.opaeum.rwt.widgets.RwtTable;
	import org.opaeum.rwt.widgets.RwtTree;

	public class RwtGridtItem extends RwtItem implements IRwtGridItemContainer
	{
		public var index:Number;
		public var itemCount:Number;
		public var texts:Array;
		public var images:Array;
		public var children:ArrayCollection;
		public var parent:IRwtGridItemContainer;
		public function RwtGridtItem(parent:IRwtGridItemContainer)
		{
			super();
			this.parent=parent;
		}
		public function destroy():void {
			parent.remove(this);
		}
		
		public function remove(item:RwtGridtItem):void
		{
			children.removeItemAt(children.getItemIndex(item));
			refreshControl();
		}
		private function refreshControl():void{
			if(parent is RwtTree){
				var t:RwtTree=RwtTree(parent);
				t.dataProvider=t.dataProvider;
			}else if(parent is RwtGridtItem){
				var g:RwtGridtItem=RwtGridtItem(parent);
				g.refreshControl();	
			}
		}
		
		public function add(result:RwtGridtItem):void
		{
			children.addItem(result);
			refreshControl();
			
		}
	}
}