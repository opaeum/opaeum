package org.opaeum.rwt.items
{
	import mx.controls.listClasses.BaseListData;
	import mx.core.IUIComponent;
	
	public class RwtListData extends BaseListData
	{
		public function RwtListData(label:String, uid:String, owner:IUIComponent, rowIndex:int=0, columnIndex:int=0)
		{
			super(label, uid, owner, rowIndex, columnIndex);
		}
		
	}
}