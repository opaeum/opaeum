package org.opaeum.rwt.widgets
{
	import flexlib.controls.treeGridClasses.TreeGridColumn;
	
	import mx.core.ClassFactory;
	import mx.core.mx_internal;
	

	use namespace mx_internal;
	public class RwtTreeColumn extends TreeGridColumn implements IRwtWidget
	{
		private var _id:String;
		public function RwtTreeColumn(header:String, tree:RwtTree)
		{
			super();
			headerText=header;
			dataField="parent";
			super.owner=tree;
			if(tree.columnCount==0){
				super.itemRenderer =new ClassFactory(RwtTreeItemRendererForFirstColumn);
			}else {
				super.itemRenderer =new ClassFactory(RwtTreeItemRendererForAdditionalColumn);
			}
			var columns:Array=tree.columns;
			columns.push(this);
			tree.columns=columns;
		}
		
		public function get id():String
		{
			return _id;
		}
		
		public function set id(s:String):void
		{
			_id=s;
		}
	}
}